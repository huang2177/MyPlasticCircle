package com.myplas.q.myinfo.setting;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;

import com.google.gson.Gson;
import com.myplas.q.R;
import com.myplas.q.common.api.API;
import com.myplas.q.common.netresquset.ResultCallBack;
import com.myplas.q.common.utils.DialogShowUtils;
import com.myplas.q.common.utils.FileUtils;
import com.myplas.q.common.utils.SharedUtils;
import com.myplas.q.common.view.LoadingDialog;
import com.myplas.q.common.view.MyListview;
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
 * Created by Administrator on 2017/7/31 0031.
 */

public class SettingActivity extends BaseActivity implements ResultCallBack, DialogShowUtils.DialogShowInterface, SettingAdapter.mySwitchCheckedListenler {
    private Information information;
    private String appkey = "c1ff771c06254db796cd7ce1433d2004";

    private Button logout;
    private MyListview mListView;
    private SettingAdapter mAdapter;

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
        mListView = F(R.id.setting_listview);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String content = "确定退出？";
                DialogShowUtils dialogShowUtils = new DialogShowUtils();
                dialogShowUtils.showDialog(SettingActivity.this, content, 4, SettingActivity.this);
            }
        });
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
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
                        DialogShowUtils dialogShowUtils = new DialogShowUtils();
                        dialogShowUtils.showDialog(SettingActivity.this, content, 10, SettingActivity.this);
                        break;
                    case 7:
                        break;
                    case 8:
                        Intent intent8 = new Intent(SettingActivity.this, AboutPlasticActivity.class);
                        startActivity(intent8);
                        break;
                }
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
        mListView.setAdapter(mAdapter);
    }

    public void requestNetData() {
        Map<String, String> map = new HashMap<>();
        map.put("token", sharedUtils.getData(this, "token"));
        String url = API.BASEURL + API.GET_SELF_INFO;
        postAsyn(this, url, map, this, 3);
    }

    public void saveSelfInfo(String method, Map map, int type) {
        String url = API.BASEURL + method;
        postAsyn(this, url, map, this, type);
    }

    @Override
    public void callBack(Object object, int type) {
        try {
            Log.e("-------", object.toString());
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
                    mAdapter.setSwitchChecked(ab.getShow() == 1 ? false : true);
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
        saveSelfInfo(API.FAVORATE_SET, map, 2);
    }

    @Override
    public void ok(int type) {
        if (type == 4) {//退出登陆；
            Map<String, String> map = new HashMap<String, String>();
            map.put("token", sharedUtils.getData(this, "token"));
            String url = API.BASEURL + API.MY_ZONE;
            BaseActivity.postAsyn1(this, url, map, this, 1, true);
        }
        if (type == 10) {//清除缓存；
            FileUtils.clearAllCache(this);
            mAdapter.notifyDataSetChanged();
        }
    }

    public void onResume() {
        super.onResume();
        MobclickAgent.onResume(this);
        requestNetData();
    }

    public void onPause() {
        super.onPause();
        MobclickAgent.onPause(this);
    }
}
