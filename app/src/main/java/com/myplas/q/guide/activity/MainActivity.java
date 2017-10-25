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
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.myplas.q.R;
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
import com.myplas.q.sockethelper.Result;
import com.myplas.q.supdem.Fragment_SupplyDemand;
import com.myplas.q.appupdate.VersionUpdateDialogUtils;
import com.umeng.analytics.MobclickAgent;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    private Fragment_MySelf fragment_wd;
    private Fragment_Contact fragment_txl;
    private Fragment_HeadLines fragment_fx;
    private Fragment_SupplyDemand fragment_gq;

    private ViewPager_Adapter viewPager_adapter;
    private TextView textView_gq, textView_wd, textView_fx, textView_txl;
    private ImageView imageView_gq, imageView_wd, imageView_fx, imageView_txl;
    private LinearLayout layout_gq, layout_txl, layout_jia, layout_fx, layout_wd;

    private VersionUpdateDialogUtils mUpdateDialogUtils;
    private DragView mMsgContact, mMsgSupDem, mMsgMySelf;

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
        fragmentlist = new ArrayList<Fragment>();
        sharedUtils = SharedUtils.getSharedUtils();

        viewPager = F(R.id.viewpager_main);
        layout_gq = F(R.id.buttom_linear_gq);
        layout_wd = F(R.id.buttom_linear_wd);
        layout_fx = F(R.id.buttom_linear_fx);
        layout_txl = F(R.id.buttom_linear_txl);
        layout_jia = F(R.id.buttom_linear_jia);

        mIVRelease = F(R.id.buttom_img_jia);
        imageView_fx = F(R.id.buttom_img_fx);
        imageView_gq = F(R.id.buttom_img_gq);
        imageView_wd = F(R.id.buttom_img_wd);
        imageView_txl = F(R.id.buttom_img_txl);

        textView_fx = F(R.id.buttom_text_fx);
        textView_gq = F(R.id.buttom_text_gq);
        textView_wd = F(R.id.buttom_text_wd);
        textView_txl = F(R.id.buttom_text_txl);

        mMsgSupDem = F(R.id.dragview_supdem);
        mMsgMySelf = F(R.id.dragview_myself);
        mMsgContact = F(R.id.dragview_contact);

        layout_fx.setOnClickListener(this);
        layout_wd.setOnClickListener(this);
        layout_jia.setOnClickListener(this);
        layout_txl.setOnClickListener(this);
        layout_gq.setOnClickListener(this);

        fragment_txl = new Fragment_Contact();
        fragmentlist.add(fragment_txl);
        fragment_fx = new Fragment_HeadLines();
        fragmentlist.add(fragment_fx);
        fragment_gq = new Fragment_SupplyDemand();
        fragmentlist.add(fragment_gq);
        fragment_wd = new Fragment_MySelf();
        fragmentlist.add(fragment_wd);

        viewPager_adapter = new ViewPager_Adapter(getSupportFragmentManager(), fragmentlist);
        viewPager.setAdapter(viewPager_adapter);
        sharedUtils.setBooloean(this, "isshow", true);

        firstInto();
        getVersion();
        getConfig();
        onConnect();
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

    public <T extends View> T F(int id) {
        return (T) findViewById(id);
    }

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


    //通讯录
    public void firstInto() {
        clearColor();
        viewPager.setCurrentItem(0);
        imageView_txl.setImageResource(R.drawable.tabbar_contacts_hl);
        textView_txl.setTextColor(resources.getColor(R.color.color_red));
    }

    //头条
    public void goToHeadLine() {
        clearColor();
        viewPager.setCurrentItem(1);
        imageView_fx.setImageResource(R.drawable.tabbar_headlines_hl);
        textView_fx.setTextColor(resources.getColor(R.color.color_red));
    }

    //供求
    public void goToSupDem() {
        clearColor();
        viewPager.setCurrentItem(2);
        imageView_gq.setImageResource(R.drawable.tabbar_trade_hl);
        textView_gq.setTextColor(resources.getColor(R.color.color_red));
    }

    //我的
    public void goToMySelf() {
        clearColor();
        viewPager.setCurrentItem(3);
        imageView_wd.setImageResource(R.drawable.tabbar_mehl);
        textView_wd.setTextColor(resources.getColor(R.color.color_red));
    }

    /*检查是否登录*/
    private boolean checkIsLogin() {
        boolean logined = sharedUtils.getBoolean(this, Constant.LOGINED);
        if (!logined) {
            CommonDialog commonDialog = new CommonDialog();
            commonDialog.showDialog(this, fragment_txl.content.toString(), 4, this);
        }
        return logined;
    }

    @Override
    public void ok(int type) {
        if (type == 4) {
            startActivity(new Intent(this, LoginActivity.class));
        }
    }

    public void getVersion() {
        Map<String, String> map = new HashMap<String, String>();
        map.put("version", VersionUtils.getVersionName(this));
        map.put("platform", "android");
        BaseActivity.postAsyn1(MainActivity.this, API.BASEURL + API.CHECK_VERSION, map, this, 3, false);
    }

    @Override
    public void callBack(Object object, int type) {
        try {
            String err = new JSONObject(object.toString()).getString("err");
            if (type == 3 && err.equals("1")) {
                url = new JSONObject(object.toString()).getString("url");
                promit = new JSONObject(object.toString()).getString("msg");
                versionLocate = NumUtils.getNum(VersionUtils.getVersionName(this));
                versionService = NumUtils.getNum(new JSONObject(object.toString()).getString("new_version"));
                //比较版本号
                if (versionService > versionLocate) {
                    mUpdateDialogUtils = new VersionUpdateDialogUtils(this, promit, url);
                    mUpdateDialogUtils.setVersionUpdateInterface(this);
                    mUpdateDialogUtils.showDialog(true);
                }
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
    public void r_Callback(Result result) {
        mMsgSupDem.setVisibility(View.VISIBLE);
        mMsgMySelf.setVisibility(View.VISIBLE);
        mMsgContact.setVisibility(View.VISIBLE);
        mMsgMySelf.setText("1");
        mMsgSupDem.setText("3");
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

    /*获取rabbitMQ配置参数*/
    private void getConfig() {
        RabbitMQConfig.getInstance(this).getConfig();
    }

    /*rabbitMQ链接*/
    public void onConnect() {
        RabbitMQHelper.getInstance(this).onConnect();
        RabbitMQConfig.getInstance(this).connected();
    }

    /*rabbitMQ断开链接*/
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

