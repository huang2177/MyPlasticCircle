package com.myplas.q.guide.activity;

import android.Manifest;
import android.app.Dialog;
import android.app.DownloadManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPager;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.myplas.q.R;
import com.myplas.q.addresslist.fragment.Fragment_AddressList;
import com.myplas.q.appupdate.DownLoadUtils;
import com.myplas.q.appupdate.DownloadApk;
import com.myplas.q.common.utils.AndroidBug54971Workaround;
import com.myplas.q.common.utils.DialogShowUtils;
import com.myplas.q.guide.adapter.ViewPager_Adapter;
import com.myplas.q.common.netresquset.ResultCallBack;
import com.myplas.q.common.utils.GetNumUtil;
import com.myplas.q.common.utils.SharedUtils;
import com.myplas.q.common.utils.TextUtils;
import com.myplas.q.common.utils.VersionUtils;
import com.myplas.q.common.view.MyViewPager;
import com.myplas.q.headlines.fragment.Fragment_HeadLines;
import com.myplas.q.myinfo.activity.LoginActivity;
import com.myplas.q.common.api.API;
import com.myplas.q.myinfo.fragment.Fragment_MySelf;
import com.myplas.q.release.activity.ReleaseActivity;
import com.myplas.q.supdem.Beans.ItemBean;
import com.myplas.q.supdem.fragment.Fragment_SupplyDemand;
import com.umeng.analytics.MobclickAgent;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends FragmentActivity implements View.OnClickListener,
        ResultCallBack, DownloadApk.InstallInterface, ViewPager.OnPageChangeListener, DialogShowUtils.DialogShowInterface {

    private boolean logined;
    private String promit, url;
    private long exitTime = 0;
    private SharedUtils sharedUtils;
    private static Resources resources;
    private int version_now, version_locate;


    private Button button_ok;
    private Dialog normalDialog;
    private Fragment_HeadLines fragment_fx;
    public static MyViewPager viewPager;
    private List<Fragment> fragmentlist;
    private Fragment_MySelf fragment_wd;
    private MyReceiver_DownLoad myReceiver;
    private Fragment_SupplyDemand fragment_gq;
    private Fragment_AddressList fragment_txl;
    private MyReciver_AcitivityFinish myReciver;
    private ViewPager_Adapter viewPager_adapter;
    private static TextView textView_gq, textView_wd, textView_fx, textView_txl;
    private static ImageView imageView_gq, imageView_wd, imageView_fx, imageView_txl;
    private LinearLayout layout_gq, layout_txl, layout_jia, layout_fx, layout_wd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.requestFeature(Window.FEATURE_NO_TITLE);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
//            window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(Color.TRANSPARENT);
            //AndroidBug54971Workaround.assistActivity(findViewById(android.R.id.content));
        }
        setContentView(R.layout.activity_main);
        initView();
        firstInto();
        getVersion();
        if (!sharedUtils.getBoolean(this, "isrequest")) {
            checkPermission();
        }
        MobclickAgent.setScenarioType(this, MobclickAgent.EScenarioType.E_UM_NORMAL);
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
        //注册app更新广播；
        myReceiver = new MyReceiver_DownLoad();
        registerReceiver(myReceiver, new IntentFilter(DownloadManager.ACTION_DOWNLOAD_COMPLETE));
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
                    startActivity(intent);
                    break;
            }
        } else {
            DialogShowUtils dialogShowUtils = new DialogShowUtils();
            dialogShowUtils.showDialog(this, fragment_txl.content, 4, this);
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
            exit();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    public void exit() {
        if ((System.currentTimeMillis() - exitTime) > 2500) {
            TextUtils.Toast(getApplicationContext(), "再按一次退出程序!");
            exitTime = System.currentTimeMillis();
        } else {
            //moveTaskToBack(false);
            MobclickAgent.onKillProcess(this);
            finish();
            System.exit(0);
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
                version_locate = GetNumUtil.getNum(VersionUtils.getVersionName(this));
                version_now = GetNumUtil.getNum(new JSONObject(object.toString()).getString("new_version"));
                //比较版本号
                if (version_now > version_locate) {
                    showDialog(promit);
                }
            }
        } catch (Exception e) {
        }
    }

    @Override
    public void failCallBack(int type) {

    }

    //设置dialog属性
    public void setDialogWindowAttr(Dialog dlg, Context ctx) {
        WindowManager wm = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics outMetrics = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(outMetrics);
        int width = outMetrics.widthPixels;
        int height = outMetrics.heightPixels;

        Window window = dlg.getWindow();
        WindowManager.LayoutParams lp = window.getAttributes();
        lp.gravity = Gravity.CENTER;
        lp.width = (width * 3) / 5;//宽高可设置具体大小
        lp.height = (int) (height / 2);
        dlg.getWindow().setAttributes(lp);
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

    //下载完成后点安装
    public class MyReceiver_DownLoad extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals(DownloadManager.ACTION_DOWNLOAD_COMPLETE)) {
                if (button_ok != null) {
                    button_ok.setClickable(true);
                }
                DownLoadUtils.getInstance(MainActivity.this).installApk(MainActivity.this);
            }
        }
    }

    //如果存在安装包就直接安装
    @Override
    public void install() {
        if (button_ok != null) {
            button_ok.setClickable(true);
        }
        DownLoadUtils.getInstance(MainActivity.this).installApk(MainActivity.this);
    }

    //弹出dialog 点击安装
    public void showDialog(String s) {
        View view = View.inflate(MainActivity.this, R.layout.layout_appupdate, null);
        normalDialog = new Dialog(MainActivity.this, R.style.dialog);
        normalDialog.setCanceledOnTouchOutside(false);
        normalDialog.setContentView(view);
        normalDialog.show();
        setDialogWindowAttr(normalDialog, MainActivity.this);
        button_ok = (Button) view.findViewById(R.id.btn_ok);
        TextView textView_content = (TextView) view.findViewById(R.id.dialog_message);
        textView_content.setText(s);
        button_ok.setClickable(true);
        button_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                button_ok.setClickable(false);
                DownloadApk downloadApk = new DownloadApk(MainActivity.this);
                downloadApk.downloadApk(MainActivity.this, url, "塑料圈通讯录更新", "塑料圈通讯录");
            }
        });
        normalDialog.setOnKeyListener(new DialogInterface.OnKeyListener() {
            @Override
            public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_BACK) {
                    exit();
                }
                return true;
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(myReciver);
        unregisterReceiver(myReceiver);
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
                    break;
                case 1:
                    // fragment_txl.getNetData("1", "", "0", "0", true);
                    break;
                case 2:
                    //fragment_gq.initData();
                    break;
                case 3:
                    //fragment_wd.getLoginInfo();
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

