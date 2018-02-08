package com.myplas.q.app.activity;

import android.Manifest;
import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;

import com.google.gson.Gson;
import com.myplas.q.R;
import com.myplas.q.app.adapter.ViewPager_Adapter;
import com.myplas.q.common.api.API;
import com.myplas.q.common.appcontext.ActivityManager;
import com.myplas.q.common.appcontext.Constant;
import com.myplas.q.common.net.ResultCallBack;
import com.myplas.q.common.utils.ACache;
import com.myplas.q.common.utils.NumUtils;
import com.myplas.q.common.utils.SharedUtils;
import com.myplas.q.common.utils.StatusUtils;
import com.myplas.q.common.utils.TextUtils;
import com.myplas.q.common.utils.VersionUtils;
import com.myplas.q.common.view.CommonDialog;
import com.myplas.q.common.view.MyOnPageChangeListener;
import com.myplas.q.common.view.MyViewPager;
import com.myplas.q.common.view.NavegationBar;
import com.myplas.q.headlines.Fragment_HeadLines;
import com.myplas.q.homepage.FragmentHomePage;
import com.myplas.q.myself.Fragment_MySelf;
import com.myplas.q.myself.login.LoginActivity;
import com.myplas.q.release.ReleaseActivity;
import com.myplas.q.sockethelper.DefConfigBean;
import com.myplas.q.sockethelper.RabbitMQCallBack;
import com.myplas.q.sockethelper.RabbitMQConfig;
import com.myplas.q.sockethelper.RabbitMQHelper;
import com.myplas.q.supdem.Fragment_SupplyDemand;
import com.myplas.q.versionhelper.VersionUpdateDialogUtils;
import com.umeng.analytics.MobclickAgent;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.jpush.android.api.JPushInterface;

/**
 * The type Main activity.
 *
 * @author Administrator
 */
public class MainActivity extends BaseActivity implements ResultCallBack
        , RabbitMQCallBack
        , CommonDialog.DialogShowInterface
        , MyOnPageChangeListener.OnPageChangeListener
        , VersionUpdateDialogUtils.VersionUpdateInterface, NavegationBar.OnItemSelectedListener {

    private long exitTime = 0;
    private String promit, url;
    private SharedUtils sharedUtils;
    private int versionService, versionLocate;

    private MyViewPager viewPager;
    private Fragment_MySelf fragmentWd;
    private List<Fragment> fragmentlist;
    private FragmentHomePage fragmentHomePage;
    private Fragment_HeadLines fragmentHeadLine;
    private Fragment_SupplyDemand fragmentSupDem;

    private ViewPager_Adapter viewpagerAdapter;

    private ACache mACache;
    private NavegationBar navegationBar;
    private DefConfigBean.NoticeBean mNticeBean;
    private VersionUpdateDialogUtils mUpdateDialogUtils;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        StatusUtils.setStatusBar(this, false, false);
        StatusUtils.setStatusTextColor(false, this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_layout_main);
        ActivityManager.addActivity(this);

        initView();
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);

//        fragmentHomePage.page = 1;
//        fragmentHomePage.getNetData("1", false);

        String type = intent.getStringExtra("type");
        String userId = sharedUtils.getData(this, Constant.USERID);

        if (Constant.LOGINED.equals(type)) { //登录
            navegationBar.firstInto();
            getConfig();
            fragmentSupDem.onLogined();
            fragmentHeadLine.onLogined();
            JPushInterface.setAlias(this, 10, userId);
        } else if (Constant.LOGINOUT.equals(type)) { //退出登录
            navegationBar.firstInto();
            onClosed();
            JPushInterface.setAlias(this, 10, "");
            rCallback(false, false);
        } else {  //注册
            navegationBar.goToMySelf();
            getConfig();
        }
    }

    public void initView() {
        mACache = ACache.get(this);
        fragmentlist = new ArrayList<Fragment>();
        sharedUtils = SharedUtils.getSharedUtils();

        viewPager = F(R.id.viewpager_main);
        viewPager.addOnPageChangeListener(new MyOnPageChangeListener(this));

        navegationBar = F(R.id.navigation_bar);
        navegationBar.setOnItemSelectedListener(this);

        fragmentHomePage = new FragmentHomePage();
        fragmentlist.add(fragmentHomePage);
        fragmentHeadLine = new Fragment_HeadLines();
        fragmentlist.add(fragmentHeadLine);
        fragmentSupDem = new Fragment_SupplyDemand();
        fragmentlist.add(fragmentSupDem);
        fragmentWd = new Fragment_MySelf();
        fragmentlist.add(fragmentWd);

        viewpagerAdapter = new ViewPager_Adapter(getSupportFragmentManager(), fragmentlist);
        viewPager.setAdapter(viewpagerAdapter);
        sharedUtils.setBooloean(this, "isshow", true);

        navegationBar.firstInto();
        getVersion();
        getConfig();
        RabbitMQConfig.getInstance(this).setResultCallBack(this);
        MobclickAgent.setScenarioType(this, MobclickAgent.EScenarioType.E_UM_NORMAL);
        if (!sharedUtils.getBoolean(this, "isrequest")) {
            checkPermission();
        }
    }

    @Override
    public void dialogClick(int type) {
        if (type == 4) {
            startActivity(new Intent(this, LoginActivity.class));
        }
    }

    @Override
    public void callBack(Object object, int type) {
        try {
            Gson gson = new Gson();
            JSONObject jsonObject = new JSONObject(object.toString());
            String err = jsonObject.getString("code");
            if (type == 1 && "1".equals(err)) {
                url = jsonObject.getString("url");
                promit = jsonObject.getString("message");
                versionLocate = NumUtils.getNum(VersionUtils.getVersionName(this));
                versionService = NumUtils.getNum(jsonObject.getString("new_version"));
                //比较版本号
                if (versionService > versionLocate) {
                    mUpdateDialogUtils = new VersionUpdateDialogUtils(this, promit, url);
                    mUpdateDialogUtils.setVersionUpdateInterface(this);
                    mUpdateDialogUtils.showDialog(true);
                }
            }

            if (type == 3 && "0".equals(err)) {
                setCacheData(object, gson);
                rCallback(true, true);
                onConnect();
            }
        } catch (Exception e) {
        }
    }

    /**
     * 保存红点信息
     *
     * @param object
     * @param gson
     */
    private void setCacheData(Object object, Gson gson) {
        try {
            DefConfigBean bean = gson.fromJson(object.toString(), DefConfigBean.class);
            mACache.put(Constant.R_MARQUEE_CONTENT, bean.getNotice());
            mACache.put(Constant.R_CONFIG, gson.toJson(bean.getConfig()));

            sharedUtils.setInt(this, Constant.R_MYORDER, bean.getRedDot().getUnread_myorder());
            sharedUtils.setInt(this, Constant.R_SEEME, bean.getRedDot().getUnread_who_saw_me());
            sharedUtils.setInt(this, Constant.R_CONTACT, bean.getRedDot().getUnread_customer());
            sharedUtils.setInt(this, Constant.R_PUR_MSG, bean.getRedDot().getUnread_plastic_msg());
            sharedUtils.setInt(this, Constant.R_SUPDEM_MSG, bean.getRedDot().getUnread_purchase_msg());
            sharedUtils.setInt(this, Constant.R_INTER_MSG, bean.getRedDot().getUnread_reply_user_msg());
            sharedUtils.setInt(this, Constant.R_SUPDEM, bean.getRedDot().getUnread_supply_and_demand());
            sharedUtils.setInt(this, Constant.R_REPLY_MSG, bean.getRedDot().getUnread_reply_purchase_msg());

        } catch (Exception e) {
        }
    }

    @Override
    public void failCallBack(int type, String message, int httpCode) {
    }

    //更新dialog的返回监听 回调
    @Override
    public void exitCallBack() {
        exit();
    }

    /**
     * rabbitmq的回调
     */
    @Override
    public void rCallback(boolean isShowRedDot, boolean isShowNotify) {
        navegationBar.showRedDots(isShowRedDot);
        showNotify(isShowNotify);
    }

    /**
     * 回调后处理滚动通知
     *
     * @param isShowNotify
     */
    private void showNotify(boolean isShowNotify) {
        try {
            mNticeBean = (DefConfigBean.NoticeBean) mACache.getAsObject(Constant.R_MARQUEE_CONTENT);
            if (mNticeBean == null) {
                return;
            }
            if (isShowNotify && mNticeBean.getCommunicate_content().size() != 0) {
                //fragmentHomePage.showMarquee(mNticeBean.getCommunicate_content(), 1);
            }
            if (isShowNotify && mNticeBean.getToutiao_content().size() != 0) {
                fragmentHeadLine.showMarquee(mNticeBean.getToutiao_content(), 1);
            }
            if (isShowNotify && mNticeBean.getPurchase_content().size() != 0) {
                fragmentSupDem.showMarquee(mNticeBean.getPurchase_content(), 1);
            }
        } catch (Exception e) {
        }
    }

    /**
     * 底部导航栏
     *
     * @param position
     * @param view
     */
    @Override
    public void onItemSelected(int position, View view) {
        if (position == -1) {
            Intent intent = new Intent(this, ReleaseActivity.class);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(this
                        , view
                        , "sharedView_Release");
                startActivity(intent, options.toBundle());
            } else {
                startActivity(intent);
            }
        } else {
            viewPager.setCurrentItem(position);
        }
    }

    @Override
    public void onPageSelected(int position) {
        try {
            boolean b = (sharedUtils.getBoolean(this, Constant.LOGINED));
            if (mNticeBean == null || !b) {
                return;
            }
            switch (position) {
                case 0:
                    if (mNticeBean.getCommunicate_content().size() != 0) {
                        //fragmentContact.showMarquee(mNticeBean.getCommunicate_content(), 2);
                    }
                    break;
                case 1:
                    if (mNticeBean.getToutiao_content().size() != 0) {
                        fragmentHeadLine.showMarquee(mNticeBean.getToutiao_content(), 2);
                    }
                    break;
                case 2:
                    if (mNticeBean.getPurchase_content().size() != 0) {
                        fragmentSupDem.showMarquee(mNticeBean.getPurchase_content(), 2);
                    }
                    break;
                default:
                    break;
            }
        } catch (Exception e) {

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
            TextUtils.toast(this, "再按一次塑料圈通讯录!");
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
     * Gets version.
     */
    public void getVersion() {
        Map<String, String> map = new HashMap<String, String>(8);
        map.put("version", VersionUtils.getVersionName(this));
        map.put("platform", "android");
        getAsyn(MainActivity.this, API.CHECK_VERSION, map, this, 1, false);
    }

    /**
     * rabbitMQ Gets config.
     */
    public void getConfig() {
        getAsyn(this, API.INIT, null, this, 3, false);
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

    public void goToSupDem() {
        navegationBar.goToSupDem();
    }

    public void firstInto() {
        navegationBar.firstInto();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 100) {
            sharedUtils.setBooloean(this, "isrequest", true);
        }
    }
}

