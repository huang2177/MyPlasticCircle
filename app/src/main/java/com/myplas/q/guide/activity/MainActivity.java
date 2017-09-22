package com.myplas.q.guide.activity;

import android.Manifest;
import android.app.ActivityOptions;
import android.app.Dialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.myplas.q.R;
import com.myplas.q.addresslist.fragment.Fragment_AddressList;
import com.myplas.q.common.view.LoadingDialog;
import com.myplas.q.versionupdate.VersionUpdateDialogUtils;
import com.myplas.q.common.api.API;
import com.myplas.q.common.netresquset.ResultCallBack;
import com.myplas.q.common.view.CommonDialog;
import com.myplas.q.common.utils.GetNumUtil;
import com.myplas.q.common.utils.SharedUtils;
import com.myplas.q.common.utils.StatusUtils;
import com.myplas.q.common.utils.TextUtils;
import com.myplas.q.common.utils.VersionUtils;
import com.myplas.q.common.view.MyViewPager;
import com.myplas.q.guide.adapter.ViewPager_Adapter;
import com.myplas.q.headlines.fragment.Fragment_HeadLines;
import com.myplas.q.myinfo.login.LoginActivity;
import com.myplas.q.myinfo.Fragment_MySelf;
import com.myplas.q.release.activity.ReleaseActivity;
import com.myplas.q.supdem.fragment.Fragment_SupplyDemand;
import com.umeng.analytics.MobclickAgent;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends FragmentActivity implements View.OnClickListener, VersionUpdateDialogUtils.VersionUpdateInterface,
        ResultCallBack, ViewPager.OnPageChangeListener, CommonDialog.DialogShowInterface {

    private boolean logined;
    private String promit, url;
    private long exitTime = 0;
    private SharedUtils sharedUtils;
    private static Resources resources;
    private int versionService, versionLocate;


    private Button button_ok;
    private Dialog normalDialog;
    public static MyViewPager viewPager;
    private List<Fragment> fragmentlist;
    private Fragment_MySelf fragment_wd;
    private Fragment_HeadLines fragment_fx;

    private Fragment_SupplyDemand fragment_gq;
    private Fragment_AddressList fragment_txl;
    private MyReciver_AcitivityFinish myReciver;
    private ViewPager_Adapter viewPager_adapter;
    private static TextView textView_gq, textView_wd, textView_fx, textView_txl;
    private static ImageView imageView_gq, imageView_wd, imageView_fx, imageView_txl;
    private LinearLayout layout_gq, layout_txl, layout_jia, layout_fx, layout_wd;

    protected boolean useStatusBarColor = false;//是否使用状态栏文字和图标为暗色，如果状态栏采用了白色系，则需要使状态栏和图标为暗色，android6.0以上可以设置
    protected boolean useThemestatusBarColor = false;//是否使用特殊的标题栏背景颜色，android5.0以上可以设置状态栏背景色，如果不使用则使用透明色值

    private VersionUpdateDialogUtils mUpdateDialogUtils;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        StatusUtils.getStatusBarHeight(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_layout_main);
        StatusUtils.setStatusBar(this, false, false);
        initView();
        firstInto();
        getVersion();
        MobclickAgent.setScenarioType(this, MobclickAgent.EScenarioType.E_UM_NORMAL);
        if (!sharedUtils.getBoolean(this, "isrequest")) {
            checkPermission();
        }
    }

    public void checkPermission() {
        if (Build.VERSION.SDK_INT >= 23) {
            String[] mPermissionList = new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.CALL_PHONE, Manifest.permission.READ_LOGS,
                    Manifest.permission.READ_PHONE_STATE, Manifest.permission.WRITE_EXTERNAL_STORAGE,
                    Manifest.permission.SET_DEBUG_APP, Manifest.permission.SYSTEM_ALERT_WINDOW,
                    Manifest.permission.GET_ACCOUNTS, Manifest.permission.WRITE_APN_SETTINGS};
            ActivityCompat.requestPermissions(this, mPermissionList, 100);
        }
    }

    public void initView() {
        //注册广播；
        myReciver = new MyReciver_AcitivityFinish();
        IntentFilter filter = new IntentFilter("com.broadcast.test");
        registerReceiver(myReciver, filter);

        //保存首页是否显示图层的标签
        sharedUtils = SharedUtils.getSharedUtils();
        viewPager = F(R.id.viewpager_main);
        fragmentlist = new ArrayList<Fragment>();
        resources = getResources();
        layout_gq = F(R.id.buttom_linear_gq);
        layout_txl = F(R.id.buttom_linear_txl);
        layout_jia = F(R.id.buttom_linear_jia);
        layout_wd = F(R.id.buttom_linear_wd);
        layout_fx = F(R.id.buttom_linear_fx);

        imageView_fx = F(R.id.buttom_img_fx);
        imageView_gq = F(R.id.buttom_img_gq);
        imageView_txl = F(R.id.buttom_img_txl);
        imageView_wd = F(R.id.buttom_img_wd);

        textView_fx = F(R.id.buttom_text_fx);
        textView_gq = F(R.id.buttom_text_gq);
        textView_txl = F(R.id.buttom_text_txl);
        textView_wd = F(R.id.buttom_text_wd);

        layout_fx.setOnClickListener(this);
        layout_wd.setOnClickListener(this);
        layout_jia.setOnClickListener(this);
        layout_txl.setOnClickListener(this);
        layout_gq.setOnClickListener(this);

        fragment_fx = new Fragment_HeadLines();
        fragmentlist.add(fragment_fx);
        fragment_txl = new Fragment_AddressList();
        fragmentlist.add(fragment_txl);
        fragment_gq = new Fragment_SupplyDemand();
        fragmentlist.add(fragment_gq);
        fragment_wd = new Fragment_MySelf();
        fragmentlist.add(fragment_wd);

        viewPager_adapter = new ViewPager_Adapter(getSupportFragmentManager(), fragmentlist);
        viewPager.setAdapter(viewPager_adapter);
        viewPager.setOnPageChangeListener(this);
        sharedUtils.setBooloean(this, "isshow", true);

    }

    @Override
    public void onClick(View v) {
        logined = sharedUtils.getBoolean(this, "logined");
        if (logined) {
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
                        startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(this
                                , layout_jia
                                , "sharedView1").toBundle());
                    } else {
                        startActivity(intent);
                    }
                    break;
            }
        } else {
            CommonDialog commonDialog = new CommonDialog();
            commonDialog.showDialog(this, fragment_txl.content, 4, this);
        }
    }

    public <T extends View> T F(int id) {
        return (T) findViewById(id);
    }


    public static void clearColor() {
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
    public static void firstInto() {
        clearColor();
        viewPager.setCurrentItem(1);
        imageView_txl.setImageResource(R.drawable.tabbar_contactshl);
        textView_txl.setTextColor(resources.getColor(R.color.color_red));
    }

    //供求
    public static void goToSupDem() {
        clearColor();
        viewPager.setCurrentItem(2);
        imageView_gq.setImageResource(R.drawable.tabbar_tradehl);
        textView_gq.setTextColor(resources.getColor(R.color.color_red));
    }

    //头条
    public static void goToHeadLine() {
        clearColor();
        viewPager.setCurrentItem(0);
        imageView_fx.setImageResource(R.drawable.tabbar_headlines_hl);
        textView_fx.setTextColor(resources.getColor(R.color.color_red));
    }

    //我的
    public static void goToMySelf() {
        clearColor();
        viewPager.setCurrentItem(3);
        imageView_wd.setImageResource(R.drawable.tabbar_mehl);
        textView_wd.setTextColor(resources.getColor(R.color.color_red));
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (!fragment_txl.onKeyDown()) {
                exit();
                return true;
            } else {
                return fragment_txl.onKeyDown();
            }
        } else {
            return super.onKeyDown(keyCode, event);
        }
    }

    public void exit() {
        if ((System.currentTimeMillis() - exitTime) > 2500) {
            TextUtils.Toast(getApplicationContext(), "再按一次塑料圈通讯录!");
            exitTime = System.currentTimeMillis();
        } else {
            //moveTaskToBack(false);
            finish();
            System.exit(0);
            LoadingDialog.clearLinkHashMap();
            MobclickAgent.onKillProcess(this);
        }
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
        BaseActivity.postAsyn(MainActivity.this, API.BASEURL + API.CHECK_VERSION, map, this, 3);
    }

    @Override
    public void callBack(Object object, int type) {
        try {
            String err = new JSONObject(object.toString()).getString("err");
            if (type == 3 && err.equals("1")) {
                url = new JSONObject(object.toString()).getString("url");
                promit = new JSONObject(object.toString()).getString("msg");
                versionLocate = GetNumUtil.getNum(VersionUtils.getVersionName(this));
                versionService = GetNumUtil.getNum(new JSONObject(object.toString()).getString("new_version"));
                //比较版本号
                if (versionService > versionLocate) {
                    mUpdateDialogUtils = new VersionUpdateDialogUtils(this, promit, url);
                    mUpdateDialogUtils.setVersionUpdateInterface(this);
                    mUpdateDialogUtils.showDialog();
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

    //其他页面返回来的跳转
    class MyReciver_AcitivityFinish extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            try {
                if (intent.getAction().equals("com.broadcast.test")) {
                    String data = intent.getStringExtra("data");
                    String what = intent.getStringExtra("what");
                    clearColor();
                    if (data.equals("0")) {//通讯录
                        firstInto();
                        fragment_txl.getNetData("1", "", "0", "0", true);
                    } else {//供求
                        if (what.equals("1")) {
                            goToSupDem();
                            fragment_gq.refreshData(1);
                        } else if (what.equals("4")) {
                            goToSupDem();
                            fragment_gq.refreshData(4);
                        }
                    }
                }
            } catch (Exception e) {
            }
        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(myReciver);
        if (mUpdateDialogUtils != null) {
            mUpdateDialogUtils.unregisterReceiver();
        }
    }

    public void onResume() {
        super.onResume();
        MobclickAgent.onResume(this);
    }

    public void onPause() {
        super.onPause();
        MobclickAgent.onPause(this);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
    }

    @Override
    public void onPageSelected(int position) {
        try {
            switch (position) {
                case 0:
                    //fragment_fx.get_Subscribe("1", "", "2",true);
                    StatusUtils.setStatusTextColor(false, this);
                    break;
                case 1:
                    // fragment_txl.getNetData("1", "", "0", "0", true);
                    StatusUtils.setStatusTextColor(false, this);
                    break;
                case 2:
                    //fragment_gq.initData();
                    StatusUtils.setStatusTextColor(false, this);
                    break;
                case 3:
                    //fragment_wd.getLoginInfo();
                    StatusUtils.setStatusTextColor(true, this);
                    break;
            }
        } catch (Exception e) {
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 100) {
            sharedUtils.setBooloean(this, "isrequest", true);
        }
    }
}

