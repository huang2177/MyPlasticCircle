package com.myplas.q.myinfo.setting;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.myplas.q.R;
import com.myplas.q.common.api.API;
import com.myplas.q.common.netresquset.ResultCallBack;
import com.myplas.q.common.utils.ACache;
import com.myplas.q.common.view.CommonDialog;
import com.myplas.q.common.utils.FileUtils;
import com.myplas.q.common.utils.SharedUtils;
import com.myplas.q.guide.activity.BaseActivity;
import com.myplas.q.guide.activity.MainActivity;
import com.myplas.q.myinfo.beans.MySelfInfo;
import com.myplas.q.myinfo.setting.activity.AboutPlasticActivity;
import com.myplas.q.myinfo.setting.activity.FindPSWActivity;
import com.myplas.q.myinfo.setting.activity.HelpActivity;
import com.myplas.q.myinfo.setting.activity.MessageActivity;
import com.myplas.q.myinfo.setting.activity.MyDataActivity;
import com.sobot.chat.SobotApi;
import com.sobot.chat.api.model.Information;
import com.umeng.analytics.MobclickAgent;

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

public class SettingActivity extends BaseActivity implements ResultCallBack, CommonDialog.DialogShowInterface, SettingAdapter.mySwitchCheckedListenler {
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

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_layout_setting);
        initTileBar();
        setTitle("设置");
        init();
        initSetting();
    }

    public void init() {
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

        mStringList.add("个人资料");
        mStringList.add("手机号码公开");
        mStringList.add("短信设置");
        mStringList.add("修改密码");
        mStringList.add("帮助中心");
        mStringList.add("在线客服");
        mStringList.add("清空缓存");
        mStringList.add("去打分");
        mStringList.add("关于塑料圈");

        mIntegerList.add(R.drawable.setup_icon_user);
        mIntegerList.add(R.drawable.setup_icon_phone);
        mIntegerList.add(R.drawable.setup_icon_message);
        mIntegerList.add(R.drawable.setup_icon_password);
        mIntegerList.add(R.drawable.setup_icon_help);
        mIntegerList.add(R.drawable.setup_icon_customer_service);
        mIntegerList.add(R.drawable.setup_icon_empty);
        mIntegerList.add(R.drawable.setup_icon_mark);
        mIntegerList.add(R.drawable.setup_icon_book);

        mAdapter = new SettingAdapter(this, mStringList, mIntegerList);
        mAdapter.setMySwitchCheckedListenler(this);
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
                        Intent intent2 = new Intent(SettingActivity.this, MessageActivity.class);
                        intent2.putExtra("Allow_send", mySelfInfo.getData().getAllow_send());
                        startActivity(intent2);
                        break;
                    case 3:
                        Intent intent3 = new Intent(SettingActivity.this, FindPSWActivity.class);
                        intent3.putExtra("title", "修改密码");
                        startActivity(intent3);
                        break;
                    case 4:
                        Intent intent4 = new Intent(SettingActivity.this, HelpActivity.class);
                        startActivity(intent4);
                        break;
                    case 5:
                        information = new Information();
                        information.setAppkey(appkey);
                        SobotApi.startSobotChat(SettingActivity.this, information);
                        break;
                    case 6:
                        String content = "确定清除？";
                        CommonDialog commonDialog = new CommonDialog();
                        commonDialog.showDialog(SettingActivity.this, content, 10, SettingActivity.this);
                        break;
                    case 7:
                        try {
                            Uri uri = Uri.parse("market://details?id=" + getPackageName());
                            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            startActivity(intent);
                        } catch (Exception e) {
                        }
                        break;
                    case 8:
                        Intent intent8 = new Intent(SettingActivity.this, AboutPlasticActivity.class);
                        startActivity(intent8);
                        break;
                }
            }
        });
    }

    public void requestNetData() {
        Map<String, String> map = new HashMap<>();
        map.put("token", sharedUtils.getData(this, "token"));
        String url = API.BASEURL + API.GET_SELF_INFO;
        postAsyn(this, url, map, this, 3);
    }

    public void saveSelfInfo(String method, Map map, int type, boolean isShowDialog) {
        String url = API.BASEURL + method;
        postAsyn1(this, url, map, this, type, isShowDialog);
    }

    @Override
    public void callBack(Object object, int type) {
        try {
            JSONObject jsonObject = new JSONObject(object.toString());
            String err = jsonObject.getString("err");
            Gson gson = new Gson();
            if (type == 1) {
                if (("0").equals(err) || "1".equals(err) || "998".equals(err)) {
                    sharedUtils.setBooloean(this, "logined", false);
                    sharedUtils.setData(this, "token", "");
                    sharedUtils.setData(this, "userid", "");
                    MainActivity.firstInto();
                    finish();
                }
            }
            if (type == 2) {
                String msg = jsonObject.getString("msg");
                //TextUtils.Toast(this, msg);
            }
            if (type == 3) {
                if (err.equals("0")) {
                    mySelfInfo = gson.fromJson(object.toString(), MySelfInfo.class);
                    MySelfInfo.DataBean.AllowSendBean ab = mySelfInfo.getData().getAllow_send();
                    mAdapter.setSwitchChecked(ab.getShow().equals("1") ? false : true);
                }
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
            String url = API.BASEURL + API.MY_ZONE;
            postAsyn(this, url, map, this, 1);
        }
        if (type == 10) {//清除缓存；
//            showDialog();
            ACache.get(this).clear();
            FileUtils.clearAllCache(this);
            //normalDialog.dismiss();
            mAdapter.notifyDataSetChanged();
        }
    }

    public void showDialog() {
        View view = View.inflate(this, R.layout.dialog_layout_clearcache, null);
        if (normalDialog == null) {
            normalDialog = new Dialog(this, R.style.commondialog_style);
            normalDialog.setCancelable(true);
            normalDialog.setCanceledOnTouchOutside(true);
            normalDialog.setContentView(view);
            setDialogWindowAttr();
            ImageView imageView = (ImageView) view.findViewById(R.id.dialog_img_clearcache);
            Glide.with(this).load(R.drawable.clearcache).into(imageView);
        }
        normalDialog.show();
    }

    //设置dialog属性
    public void setDialogWindowAttr() {
        WindowManager wm = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics outMetrics = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(outMetrics);
        int width = outMetrics.widthPixels;
        int height = outMetrics.heightPixels;

        Window window = normalDialog.getWindow();
        WindowManager.LayoutParams lp = window.getAttributes();
        lp.gravity = Gravity.CENTER;
        lp.width = (int) ((width * 2) / 3.5);//宽高可设置具体大小
        lp.height = (int) (height / 5);
        normalDialog.getWindow().setAttributes(lp);
    }

    public void onResume() {
        super.onResume();
        requestNetData();
        MobclickAgent.onResume(this);
    }

    public void onPause() {
        super.onPause();
        MobclickAgent.onPause(this);
    }
}
