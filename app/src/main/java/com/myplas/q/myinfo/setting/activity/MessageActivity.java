package com.myplas.q.myinfo.setting.activity;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Switch;

import com.myplas.q.R;
import com.myplas.q.common.api.API;
import com.myplas.q.common.netresquset.ResultCallBack;
import com.myplas.q.common.utils.SharedUtils;
import com.myplas.q.guide.activity.BaseActivity;
import com.myplas.q.myinfo.beans.MySelfInfo;
import com.sobot.chat.SobotApi;
import com.sobot.chat.api.model.Information;
import com.umeng.analytics.MobclickAgent;

import java.util.HashMap;
import java.util.Map;

/**
 * 编写： 黄双
 * 电话：15378412400
 * 邮箱：15378412400@163.com
 * 时间：2017/3/28 10:25
 */
public class MessageActivity extends BaseActivity implements CompoundButton.OnCheckedChangeListener, ResultCallBack {
    private Information information;
    private String appkey = "c1ff771c06254db796cd7ce1433d2004";

    private Switch switch_gz, switch_hf;

    private Map<String, String> map;
    private MySelfInfo.DataBean.AllowSendBean mBean;
    private SharedUtils sharedUtils;
    private String allow_sendmsg_gz, allow_sendmsg_hf;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_layout_setting_message);
        initTileBar();
        setTitle("短信设置");
        init();
    }

    private void init() {
        map = new HashMap<>();
        sharedUtils = SharedUtils.getSharedUtils();

        switch_gz = F(R.id.message_switch_gz);
        switch_hf = F(R.id.message_switch_hf);

        switch_hf.setOnCheckedChangeListener(this);
        switch_gz.setOnCheckedChangeListener(this);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            switch_gz.setThumbResource(R.drawable.switch_thumb);
            switch_gz.setTrackResource(R.drawable.switch_track);
            switch_hf.setThumbResource(R.drawable.switch_thumb);
            switch_hf.setTrackResource(R.drawable.switch_track);
        }

        mBean = (MySelfInfo.DataBean.AllowSendBean) getIntent().getSerializableExtra("Allow_send");
        if (mBean != null) {
            switch_gz.setChecked((mBean.getFocus() == 1) ? (false) : (true));
            switch_hf.setChecked(mBean.getRepeat() == 1 ? false : true);
        }
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        switch (buttonView.getId()) {
            case R.id.message_switch_gz:
                allow_sendmsg_gz = (isChecked) ? "0" : "1";
                map.put("type", "0");
                map.put("token", sharedUtils.getData(this, "token"));
                map.put("is_allow", allow_sendmsg_gz);
                saveSelfInfo(API.FAVORATE_SET, map, 1);
                break;
            case R.id.message_switch_hf:
                allow_sendmsg_hf = (isChecked) ? "0" : "1";
                map.put("type", "1");
                map.put("is_allow", allow_sendmsg_hf);
                map.put("token", sharedUtils.getData(this, "token"));
                saveSelfInfo(API.FAVORATE_SET, map, 2);
                break;
        }
    }

    public void saveSelfInfo(String method, Map map, int type) {
        String url = API.BASEURL + method;
        postAsyn(this, url, map, this, type);
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
    public void callBack(Object object, int type) {

    }

    @Override
    public void failCallBack(int type) {

    }
}
