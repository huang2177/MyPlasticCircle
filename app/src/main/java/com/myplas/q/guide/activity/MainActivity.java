package com.myplas.q.guide.activity;

import android.Manifest;
import android.app.ActivityOptions;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.myplas.q.R;
import com.myplas.q.common.utils.ACache;
import com.myplas.q.contact.Fragment_Contact;
import com.myplas.q.common.api.API;
import com.myplas.q.common.appcontext.ActivityManager;
import com.myplas.q.common.appcontext.Constant;
import com.myplas.q.common.netresquset.ResultCallBack;
import com.myplas.q.common.utils.NumUtils;
import com.myplas.q.common.utils.SharedUtils;
import com.myplas.q.common.utils.StatusUtils;
import com.myplas.q.common.utils.TextUtils;
import com.myplas.q.common.utils.VersionUtils;
import com.myplas.q.common.view.CommonDialog;
import com.myplas.q.common.view.DragView;
import com.myplas.q.common.view.MyViewPager;
import com.myplas.q.guide.adapter.ViewPager_Adapter;
import com.myplas.q.headlines.Fragment_HeadLines;
import com.myplas.q.myself.Fragment_MySelf;
import com.myplas.q.myself.login.LoginActivity;
import com.myplas.q.release.ReleaseActivity;
import com.myplas.q.sockethelper.RabbitMQCallBack;
import com.myplas.q.sockethelper.RabbitMQConfig;
import com.myplas.q.sockethelper.RabbitMQHelper;
import com.myplas.q.sockethelper.DefConfigBean;
import com.myplas.q.supdem.Fragment_SupplyDemand;
import com.myplas.q.appupdate.VersionUpdateDialogUtils;
import com.umeng.analytics.MobclickAgent;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * The type Main activity.
 *
 * @author Administrator
 */
public class MainActivity extends FragmentActivity implements View.OnClickListener
        , ResultCallBack
        , RabbitMQCallBack
        , CommonDialog.DialogShowInterface
        , VersionUpdateDialogUtils.VersionUpdateInterface {

    private boolean logined;
    private String promit, url;
    private long exitTime = 0;
    private SharedUtils sharedUtils;
    private static Resources resources;
    private int versionService, versionLocate;


    private ImageView mIVRelease;
    private MyViewPager viewPager;
    private List<Fragment> fragmentlist;
    private Fragment_MySelf fragmentWd;
    private Fragment_Contact fragmentContact;
    private Fragment_HeadLines fragmentHeadLine;
    private Fragment_SupplyDemand fragmentSupDem;

    private ViewPager_Adapter viewpagerAdapter;
    private TextView textView_gq, textView_wd, textView_fx, textView_txl;
    private ImageView imageView_gq, imageView_wd, imageView_fx, imageView_txl;
    private LinearLayout layout_gq, layout_txl, layout_jia, layout_fx, layout_wd;

    private VersionUpdateDialogUtils mUpdateDialogUtils;
    private DragView mMsgContact, mMsgSupDem, mMsgMySelf;
    private ACache mACache;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        StatusUtils.setStatusBar(this, false, false);
        StatusUtils.setStatusTextColor(false, this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_layout_main);
        ActivityManager.addActivity(this);

        initView();
    }

    public void initView() {
        resources = getResources();
        mACache = ACache.get(this);
        fragmentlist = new ArrayList<Fragment>();
        sharedUtils = SharedUtils.getSharedUtils();
        logined = sharedUtils.getBoolean(this, Constant.LOGINED);

        viewPager = f(R.id.viewpager_main);
        layout_gq = f(R.id.buttom_linear_gq);
        layout_wd = f(R.id.buttom_linear_wd);
        layout_fx = f(R.id.buttom_linear_fx);
        layout_txl = f(R.id.buttom_linear_txl);
        layout_jia = f(R.id.buttom_linear_jia);

        mIVRelease = f(R.id.buttom_img_jia);
        imageView_fx = f(R.id.buttom_img_fx);
        imageView_gq = f(R.id.buttom_img_gq);
        imageView_wd = f(R.id.buttom_img_wd);
        imageView_txl = f(R.id.buttom_img_txl);

        textView_fx = f(R.id.buttom_text_fx);
        textView_gq = f(R.id.buttom_text_gq);
        textView_wd = f(R.id.buttom_text_wd);
        textView_txl = f(R.id.buttom_text_txl);

        mMsgSupDem = f(R.id.dragview_supdem);
        mMsgMySelf = f(R.id.dragview_myself);
        mMsgContact = f(R.id.dragview_contact);

        layout_fx.setOnClickListener(this);
        layout_wd.setOnClickListener(this);
        layout_jia.setOnClickListener(this);
        layout_txl.setOnClickListener(this);
        layout_gq.setOnClickListener(this);

        fragmentContact = new Fragment_Contact();
        fragmentlist.add(fragmentContact);
        fragmentHeadLine = new Fragment_HeadLines();
        fragmentlist.add(fragmentHeadLine);
        fragmentSupDem = new Fragment_SupplyDemand();
        fragmentlist.add(fragmentSupDem);
        fragmentWd = new Fragment_MySelf();
        fragmentlist.add(fragmentWd);

        viewpagerAdapter = new ViewPager_Adapter(getSupportFragmentManager(), fragmentlist);
        viewPager.setAdapter(viewpagerAdapter);
        sharedUtils.setBooloean(this, "isshow", true);

        firstInto();
        getVersion();
        getConfig();
        RabbitMQHelper.getInstance(this).setResultCallBack(this);
        MobclickAgent.setScenarioType(this, MobclickAgent.EScenarioType.E_UM_NORMAL);
        if (!sharedUtils.getBoolean(this, "isrequest")) {
            checkPermission();
        }
    }

    @Override
    public void onClick(View v) {
        if (!checkIsLogin()) {
            return;
        }
        switch (v.getId()) {
            case R.id.buttom_linear_gq:
                goToSupDem();
                break;
            case R.id.buttom_linear_txl:
                firstInto();
                break;
            case R.id.buttom_linear_fx:
                goToHeadLine();
                break;
            case R.id.buttom_linear_wd:
                goToMySelf();
                break;
            case R.id.buttom_linear_jia:
                Intent intent = new Intent(this, ReleaseActivity.class);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(this
                            , mIVRelease
                            , "sharedView_Release");
                    startActivity(intent, options.toBundle());
                } else {
                    startActivity(intent);
                }
                break;
            default:
                break;
        }
    }

    /**
     * F t.
     *
     * @param <T> the type parameter
     * @param id  the id
     * @return the t
     */
    public <T extends View> T f(int id) {
        return (T) findViewById(id);
    }

    /**
     * Clear color.
     */
    public void clearColor() {
        imageView_wd.setImageResource(R.drawable.tabbar_me);
        imageView_gq.setImageResource(R.drawable.tabbar_trade);
        imageView_fx.setImageResource(R.drawable.tabbar_headlines);
        imageView_txl.setImageResource(R.drawable.tabbar_contacts);

        textView_fx.setTextColor(resources.getColor(R.color.color_gray));
        textView_wd.setTextColor(resources.getColor(R.color.color_gray));
        textView_txl.setTextColor(resources.getColor(R.color.color_gray));
        textView_gq.setTextColor(resources.getColor(R.color.color_gray));
    }


    /**
     * First into.
     */
    public void firstInto() {
        clearColor();
        viewPager.setCurrentItem(0);
        imageView_txl.setImageResource(R.drawable.tabbar_contacts_hl);
        textView_txl.setTextColor(resources.getColor(R.color.color_red));
    }

    /**
     * Go to headline.
     */
    public void goToHeadLine() {
        clearColor();
        viewPager.setCurrentItem(1);
        imageView_fx.setImageResource(R.drawable.tabbar_headlines_hl);
        textView_fx.setTextColor(resources.getColor(R.color.color_red));
    }

    /**
     * Go to supdem.
     */
    public void goToSupDem() {
        clearColor();
        viewPager.setCurrentItem(2);
        imageView_gq.setImageResource(R.drawable.tabbar_trade_hl);
        textView_gq.setTextColor(resources.getColor(R.color.color_red));
    }

    /**
     * Go to myself.
     */
    public void goToMySelf() {
        clearColor();
        viewPager.setCurrentItem(3);
        imageView_wd.setImageResource(R.drawable.tabbar_mehl);
        textView_wd.setTextColor(resources.getColor(R.color.color_red));
    }


    private boolean checkIsLogin() {
        boolean logined = sharedUtils.getBoolean(this, Constant.LOGINED);
        if (!logined) {
            CommonDialog commonDialog = new CommonDialog();
            commonDialog.showDialog(this, fragmentContact.content.toString(), 4, this);
        }
        return logined;
    }

    @Override
    public void ok(int type) {
        if (type == 4) {
            startActivity(new Intent(this, LoginActivity.class));
        }
    }

    /**
     * Gets version.
     */
    public void getVersion() {
        Map<String, String> map = new HashMap<String, String>();
        map.put("version", VersionUtils.getVersionName(this));
        map.put("platform", "android");
        BaseActivity.postAsyn1(MainActivity.this, API.BASEURL + API.CHECK_VERSION, map, this, 1, false);
    }

    /**
     * rabbitMQ Gets config.
     */
    public void getConfig() {
        BaseActivity.postAsyn1(this, API.BASEURL + API.INIT, null, this, 3, false);
    }

    @Override
    public void callBack(Object object, int type) {
        try {
            Gson gson = new Gson();
            JSONObject jsonObject = new JSONObject(object.toString());
            String err = jsonObject.getString("err");
            if (type == 1 && err.equals("1")) {
                url = jsonObject.getString("url");
                promit = jsonObject.getString("msg");
                versionLocate = NumUtils.getNum(VersionUtils.getVersionName(this));
                versionService = NumUtils.getNum(jsonObject.getString("new_version"));
                //比较版本号
                if (versionService > versionLocate) {
                    mUpdateDialogUtils = new VersionUpdateDialogUtils(this, promit, url);
                    mUpdateDialogUtils.setVersionUpdateInterface(this);
                    mUpdateDialogUtils.showDialog(true);
                }
            }
            if (type == 3 && err.equals("0")) {
                mACache.put(Constant.R_CONFIG, object.toString());
                DefConfigBean bean = gson.fromJson(object.toString(), DefConfigBean.class);

                mACache.put(Constant.R_MYMSG, bean.getRedDot().getUnread_mymsg());
                mACache.put(Constant.R_MYORDER, bean.getRedDot().getUnread_myorder());
                mACache.put(Constant.R_CONTACT, bean.getRedDot().getUnread_customer());
                mACache.put(Constant.R_SEEME, bean.getRedDot().getUnread_who_saw_me());
                mACache.put(Constant.R_SUPDEM, bean.getRedDot().getUnread_supply_and_demand());
                rCallback(true);
                onConnect();
            }
        } catch (Exception e) {
        }
    }

    @Override
    public void failCallBack(int type) {

    }

    //更新dialog的返回监听 回调
    @Override
    public void exitCallBack() {
        exit();
    }

    /*rabbitmq*/
    @Override
    public void rCallback(boolean showRedDot) {
        try {
            int numContact = Integer.parseInt(mACache.getAsString(Constant.R_CONTACT));
            int numSupDem = Integer.parseInt(mACache.getAsString(Constant.R_SUPDEM));
            int numMySelf = Integer.parseInt(mACache.getAsString(Constant.R_SEEME))
                    + Integer.parseInt(mACache.getAsString(Constant.R_MYORDER))
                    + Integer.parseInt(mACache.getAsString(Constant.R_MYMSG));

            mMsgSupDem.setVisibility(!showRedDot || 0 == numSupDem
                    ? View.GONE
                    : View.VISIBLE);
            mMsgMySelf.setVisibility(!showRedDot || 0 == numMySelf
                    ? View.GONE
                    : View.VISIBLE);
            mMsgContact.setVisibility(!showRedDot || 0 == numContact
                    ? View.GONE
                    : View.VISIBLE);

            if (showRedDot) {
                mMsgMySelf.setText(numMySelf > 99 ? "..." : numMySelf + "");
                mMsgSupDem.setText(numSupDem > 99 ? "..." : numSupDem + "");
            }
        } catch (Exception e) {
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 100) {
            sharedUtils.setBooloean(this, "isrequest", true);
        }
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            exit();
            return true;
        } else {
            return super.onKeyDown(keyCode, event);
        }
    }

    /**
     * Exit.
     */
    public void exit() {
        if ((System.currentTimeMillis() - exitTime) > 2500) {
            TextUtils.Toast(getApplicationContext(), "再按一次塑料圈通讯录!");
            exitTime = System.currentTimeMillis();
        } else {
            finish();
            System.exit(0);
            viewPager.removeAllViews();
            MobclickAgent.onKillProcess(this);
            RabbitMQConfig.getInstance(this).closed();
        }
    }

    /**
     * Check permission.
     */
    public void checkPermission() {
        if (Build.VERSION.SDK_INT >= 23) {
            String[] mPermissionList = new String[]{Manifest.permission.READ_LOGS
                    , Manifest.permission.CALL_PHONE
                    , Manifest.permission.GET_ACCOUNTS
                    , Manifest.permission.SET_DEBUG_APP
                    , Manifest.permission.READ_PHONE_STATE
                    , Manifest.permission.SYSTEM_ALERT_WINDOW
                    , Manifest.permission.ACCESS_FINE_LOCATION
                    , Manifest.permission.WRITE_EXTERNAL_STORAGE
                    , Manifest.permission.WRITE_APN_SETTINGS};
            ActivityCompat.requestPermissions(this, mPermissionList, 100);
        }
    }


    /**
     * rabbitMQ On connect.
     */
    public void onConnect() {
        RabbitMQHelper.getInstance(this).onConnect();
        RabbitMQConfig.getInstance(this).connected();
    }

    /**
     * rabbitMQ On closed.
     */
    public void onClosed() {
        RabbitMQHelper.getInstance(this).onDisConnect();
        RabbitMQConfig.getInstance(this).closed();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        onClosed();
        if (mUpdateDialogUtils != null) {
            mUpdateDialogUtils.unregisterReceiver();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        MobclickAgent.onResume(this);
    }

    @Override
    public void onPause() {
        super.onPause();
        MobclickAgent.onPause(this);
    }
}

