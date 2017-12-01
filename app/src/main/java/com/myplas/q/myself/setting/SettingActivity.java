package com.myplas.q.myself.setting;

import android.app.Dialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.google.gson.Gson;
import com.myplas.q.R;
import com.myplas.q.versionupdate.VersionUpdateDialogUtils;
import com.myplas.q.common.api.API;
import com.myplas.q.common.appcontext.Constant;
import com.myplas.q.common.netresquset.ResultCallBack;
import com.myplas.q.common.utils.ACache;
import com.myplas.q.common.utils.NumUtils;
import com.myplas.q.common.utils.TextUtils;
import com.myplas.q.common.utils.VersionUtils;
import com.myplas.q.common.view.CommonDialog;
import com.myplas.q.common.utils.FileUtils;
import com.myplas.q.common.utils.SharedUtils;
import com.myplas.q.app.activity.BaseActivity;
import com.myplas.q.app.activity.MainActivity;
import com.myplas.q.myself.beans.MySelfInfo;
import com.myplas.q.myself.setting.activity.AboutPlasticActivity;
import com.myplas.q.myself.setting.activity.FindPSWActivity;
import com.myplas.q.myself.setting.activity.HelpActivity;
import com.myplas.q.myself.setting.activity.MessageActivity;
import com.myplas.q.myself.setting.activity.MyDataActivity;
import com.sobot.chat.SobotApi;
import com.sobot.chat.api.model.Information;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 作者:huangshuang
 * 事件 2017/7/31 0031.
 * 邮箱： 15378412400@163.com
 */

public class SettingActivity extends BaseActivity implements ResultCallBack
        , CommonDialog.DialogShowInterface
        , SettingAdapter.mySwitchCheckedListenler {
    private Information information;
    private String appkey = "c1ff771c06254db796cd7ce1433d2004";

    private Button logout;
    private Dialog normalDialog;
    private SettingAdapter mAdapter;
    private RecyclerView mRecyclerView;

    private List<String> mStringList;
    private List<Integer> mIntegerList;

    private MySelfInfo mySelfInfo;
    private SharedUtils sharedUtils;
    private MainActivity mainActivity;

    private boolean isOpen;
    private String promit, url;
    private int versionService, versionLocate;
    private NotificationManagerCompat managerCompat;
    private VersionUpdateDialogUtils mUpdateDialogUtils;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_layout_setting);
        initTileBar();
        setTitle("设置");
        initView();
        initSetting();
        requestNetData();
        getRecommendVersion();
    }

    public void initView() {
        sharedUtils = SharedUtils.getSharedUtils();

        logout = F(R.id.wd_linear_tc);
        mRecyclerView = F(R.id.setting_listview);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setNestedScrollingEnabled(false);

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String content = "确定退出?";
                CommonDialog commonDialog = new CommonDialog();
                commonDialog.showDialog(SettingActivity.this, content, 4, SettingActivity.this);
            }
        });

    }

    public void initSetting() {
        mStringList = new ArrayList<>();
        mIntegerList = new ArrayList<>();
        managerCompat = NotificationManagerCompat.from(this);
        isOpen = managerCompat.areNotificationsEnabled();

        mStringList.add("个人资料");
        mStringList.add("手机号码公开");
        mStringList.add("短信设置");
        mStringList.add("新消息通知");
        mStringList.add("修改密码");
        mStringList.add("帮助中心");
        mStringList.add("在线客服");
        mStringList.add("清空缓存");
        mStringList.add("检查更新");
        mStringList.add("去打分");
        mStringList.add("关于塑料圈");

        mIntegerList.add(R.drawable.setup_icon_user);
        mIntegerList.add(R.drawable.setup_icon_phone);
        mIntegerList.add(R.drawable.setup_icon_message);
        mIntegerList.add(R.drawable.setup_icon_switch);
        mIntegerList.add(R.drawable.setup_icon_password);
        mIntegerList.add(R.drawable.setup_icon_help);
        mIntegerList.add(R.drawable.setup_icon_customer_service);
        mIntegerList.add(R.drawable.setup_icon_empty);
        mIntegerList.add(R.drawable.setup_icon_check_update);
        mIntegerList.add(R.drawable.setup_icon_mark);
        mIntegerList.add(R.drawable.setup_icon_book);

        mAdapter = new SettingAdapter(this, mStringList, mIntegerList);
        mAdapter.setMySwitchCheckedListenler(this);
        mAdapter.setNotificationsEnabled(isOpen);
        mRecyclerView.setAdapter(mAdapter);

        mAdapter.setOnItemClickListener(new SettingAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                switch (position) {
                    case 0:
                        Intent intent0 = new Intent(SettingActivity.this, MyDataActivity.class);
                        startActivity(intent0);
                        break;
                    case 2:
                        if (mySelfInfo == null) {
                            return;
                        }
                        Intent intent2 = new Intent(SettingActivity.this, MessageActivity.class);
                        intent2.putExtra("Allow_send", mySelfInfo.getData().getAllow_send());
                        startActivity(intent2);
                        break;
                    case 3:
                        getAppDetailSettingIntent();
                        break;
                    case 4:
                        Intent intent3 = new Intent(SettingActivity.this, FindPSWActivity.class);
                        intent3.putExtra("title", "修改密码");
                        startActivity(intent3);
                        break;
                    case 5:
                        Intent intent4 = new Intent(SettingActivity.this, HelpActivity.class);
                        startActivity(intent4);
                        break;
                    case 6:
                        information = new Information();
                        information.setAppkey(appkey);
                        SobotApi.startSobotChat(SettingActivity.this, information);
                        break;
                    case 7:
                        String content = "确定清除？";
                        CommonDialog commonDialog = new CommonDialog();
                        commonDialog.showDialog(SettingActivity.this, content, 10, SettingActivity.this);
                        break;
                    case 8:
                        //比较版本号
                        if (versionService > versionLocate) {
                            mUpdateDialogUtils = new VersionUpdateDialogUtils(SettingActivity.this, promit, url);
                            mUpdateDialogUtils.showDialog(false);
                        } else {
                            TextUtils.toast(SettingActivity.this, "当前已是最新版本！");
                        }
                        break;
                    case 9:
                        try {
                            Uri uri = Uri.parse("market://details?id=" + getPackageName());
                            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            startActivity(intent);
                        } catch (Exception e) {
                        }
                        break;
                    case 10:
                        Intent intent8 = new Intent(SettingActivity.this, AboutPlasticActivity.class);
                        startActivity(intent8);
                        break;
                    default:
                        break;
                }
            }
        });
    }

    public void requestNetData() {
        Map<String, String> map = new HashMap<>();
        map.put("token", sharedUtils.getData(this, "token"));
        String url = API.BASEURL + API.GET_SELF_INFO;
        postAsyn1(this, url, map, this, 3, false);
    }

    public void saveSelfInfo(String method, Map map, int type, boolean isShowDialog) {
        String url = API.BASEURL + method;
        postAsyn1(this, url, map, this, type, isShowDialog);
    }

    public void getRecommendVersion() {
        postAsyn1(this, API.BASEURL + API.CHECKAPPVERSION, null, this, 4, false);
    }

    /**
     * 跳转到设置页面
     */
    private void getAppDetailSettingIntent() {
        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Intent intent = new Intent();
            intent.setAction("android.settings.APP_NOTIFICATION_SETTINGS");
            intent.putExtra("app_package", getPackageName());
            intent.putExtra("app_uid", getApplicationInfo().uid);
            startActivity(intent);
        } else if (android.os.Build.VERSION.SDK_INT == Build.VERSION_CODES.KITKAT) {
            Intent intent = new Intent();
            intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
            intent.addCategory(Intent.CATEGORY_DEFAULT);
            intent.setData(Uri.parse("package:" + getPackageName()));
            startActivity(intent);
        }
    }

    @Override
    public void callBack(Object object, int type) {
        try {
            JSONObject jsonObject = new JSONObject(object.toString());
            String err = jsonObject.getString("err");
            Gson gson = new Gson();
            if (type == 1) {
                if (("0").equals(err) || "1".equals(err) || "998".equals(err)) {

                    sharedUtils.setBooloean(this, Constant.LOGINED, false);
                    sharedUtils.setData(this, Constant.TOKEN, "");
                    sharedUtils.setData(this, Constant.USERID, "");

                    //回到主界面
                    Intent intent = new Intent(this, MainActivity.class);
                    intent.putExtra("type", Constant.LOGINOUT);
                    startActivity(intent);
                }
            }
            if (type == 3) {
                if ("0".equals(err)) {
                    mySelfInfo = gson.fromJson(object.toString(), MySelfInfo.class);
                    MySelfInfo.DataBean.AllowSendBean ab = mySelfInfo.getData().getAllow_send();
                    mAdapter.setSwitchChecked(ab.getShow().equals("1") ? false : true);
                }
            }
            if (type == 4 && err.equals("1")) {
                mAdapter.setVersionImg(R.drawable.tag_new);
                mAdapter.notifyDataSetChanged();

                url = jsonObject.getString("url");
                promit = jsonObject.getString("msg");
                versionLocate = NumUtils.getNum(VersionUtils.getVersionName(this));
                versionService = NumUtils.getNum(jsonObject.getString("new_version"));
            }
        } catch (Exception e) {
        }
    }

    @Override
    public void failCallBack(int type) {

    }

    //switch的选择回调
    @Override
    public void onChecked(String s) {
        Map<String, String> map = new HashMap<>();
        map.put("type", "2");
        map.put("is_allow", s);
        map.put("token", sharedUtils.getData(this, "token"));
        saveSelfInfo(API.FAVORATE_SET, map, 2, false);
    }

    @Override
    public void ok(int type) {
        if (type == 4) {//退出登陆；
            Map<String, String> map = new HashMap<String, String>();
            map.put("token", sharedUtils.getData(this, "token"));
            String url = API.BASEURL + API.LOGOUT;
            postAsyn(this, url, map, this, 1);
        }
        if (type == 10) {//清除缓存；
            ACache.get(this).clear();
            FileUtils.clearAllCache(this);
            mAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        managerCompat = NotificationManagerCompat.from(this);
        boolean isOpen = managerCompat.areNotificationsEnabled();
        mAdapter.setNotificationsEnabled(isOpen);
        mAdapter.notifyDataSetChanged();

        if (this.isOpen != isOpen) {
            Map<String, String> map = new HashMap<>();
            map.put("is_allow", isOpen ? "1" : "0");
            postAsyn1(this, API.BASEURL + API.JPUSHSET, map, this, 6, false);
        }
    }
}
