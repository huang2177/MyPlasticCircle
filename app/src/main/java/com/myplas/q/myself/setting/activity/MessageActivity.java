package com.myplas.q.myself.setting.activity;

import android.app.Service;
import android.os.Bundle;
import android.os.Vibrator;

import com.myplas.q.R;
import com.myplas.q.common.api.API;
import com.myplas.q.common.netresquset.ResultCallBack;
import com.myplas.q.common.utils.SharedUtils;
import com.myplas.q.guide.activity.BaseActivity;
import com.myplas.q.myself.beans.MySelfInfo;
import com.sobot.chat.api.model.Information;
import com.suke.widget.SwitchButton;
import com.umeng.analytics.MobclickAgent;

import java.util.HashMap;
import java.util.Map;

/**
 * 编写： 黄双
 * 电话：15378412400
 * 邮箱：15378412400@163.com
 * 时间：2017/3/28 10:25
 */
public class MessageActivity extends BaseActivity implements SwitchButton.OnCheckedChangeListener, ResultCallBack {
    private Information information;
    private String appkey = "c1ff771c06254db796cd7ce1433d2004";

    private SwitchButton switch_gz, switch_hf;

    private Map<String, String> map;
    private MySelfInfo.DataBean.AllowSendBean mBean;
    private SharedUtils sharedUtils;
    private String allow_sendmsg_gz, allow_sendmsg_hf;

    private Vibrator mVibrator;

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
        mVibrator = (Vibrator) getApplication().getSystemService(Service.VIBRATOR_SERVICE);

        mBean = (MySelfInfo.DataBean.AllowSendBean) getIntent().getSerializableExtra("Allow_send");
        if (mBean != null) {
            switch_gz.setChecked((mBean.getFocus().equals("1")) ? (false) : (true));
            switch_hf.setChecked(mBean.getRepeat().equals("1") ? false : true);
        }
    }

    @Override
    public void onCheckedChanged(SwitchButton buttonView, boolean isChecked) {
        switch (buttonView.getId()) {
            case R.id.message_switch_gz:
//                mVibrator.vibrate(new long[]{0,100}, -1);
                allow_sendmsg_gz = (isChecked) ? "0" : "1";
                map.put("type", "0");
                map.put("token", sharedUtils.getData(this, "token"));
                map.put("is_allow", allow_sendmsg_gz);
                saveSelfInfo(API.FAVORATE_SET, map, 1);
                break;
            case R.id.message_switch_hf:
//                mVibrator.vibrate(new long[]{0,100}, -1);
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
    protected void onStop() {
        super.onStop();
        mVibrator.cancel();
    }

    @Override
    public void callBack(Object object, int type) {

    }

    @Override
    public void failCallBack(int type) {

    }
}
