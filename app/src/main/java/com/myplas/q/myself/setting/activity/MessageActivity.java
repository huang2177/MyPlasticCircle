package com.myplas.q.myself.setting.activity;

import android.os.Bundle;

import com.google.gson.Gson;
import com.myplas.q.R;
import com.myplas.q.common.api.API;
import com.myplas.q.common.net.ResultCallBack;
import com.myplas.q.common.utils.SharedUtils;
import com.myplas.q.app.activity.BaseActivity;
import com.myplas.q.common.view.SwitchButton;
import com.myplas.q.myself.beans.MySelfInfo;
import com.sobot.chat.api.model.Information;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * 编写： 黄双
 * 电话：15378412400
 * 邮箱：15378412400@163.com
 * 时间：2017/3/28 10:25
 */
public class MessageActivity extends BaseActivity implements SwitchButton.OnCheckedChangeListener
        , ResultCallBack {
    private Information information;
    private String appkey = "c1ff771c06254db796cd7ce1433d2004";

    private SwitchButton switchGz, switchHf;

    private Map<String, String> map;
    private SharedUtils sharedUtils;
    private String allowSendmsgGz, allowSendmsgHf;

    private MySelfInfo.DataBean.AllowSendBean allowSendBean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_layout_setting_message);
        initTileBar();
        setTitle("短信设置");
        init();

        getData();
    }

    private void init() {
        map = new HashMap<>(16);
        sharedUtils = SharedUtils.getSharedUtils();

        switchGz = F(R.id.message_switch_gz);
        switchHf = F(R.id.message_switch_hf);

        switchHf.setOnCheckedChangeListener(this);
        switchGz.setOnCheckedChangeListener(this);
        //mVibrator = (Vibrator) getApplication().getSystemService(Service.VIBRATOR_SERVICE);

    }

    @Override
    public void onCheckedChanged(SwitchButton buttonView, boolean isChecked) {
        switch (buttonView.getId()) {
            case R.id.message_switch_gz:
//                mVibrator.vibrate(new long[]{0,100}, -1);
                allowSendmsgGz = (isChecked) ? "0" : "1";
                map.put("type", "0");
                map.put("is_allow", allowSendmsgGz);
                saveSelfInfo(1);
                break;
            case R.id.message_switch_hf:
//                mVibrator.vibrate(new long[]{0,100}, -1);
                allowSendmsgHf = (isChecked) ? "0" : "1";
                map.put("type", "1");
                map.put("is_allow", allowSendmsgHf);
                saveSelfInfo(2);
                break;
            default:
                break;
        }
    }

    public void getData() {
        getAsyn(this, API.GET_SELF_INFO, null, this, 3, false);
    }

    public void saveSelfInfo(int type) {
        postAsyn(this, API.FAVORATE_SET, map, this, type, false);
    }


    @Override
    protected void onStop() {
        super.onStop();
        //mVibrator.cancel();
    }

    @Override
    public void callBack(Object object, int type) {
        try {
            Gson gson = new Gson();
            JSONObject jsonObject = new JSONObject(object.toString());
            String err = jsonObject.getString("code");
            if (type == 3) {
                if ("0".equals(err)) {
                    MySelfInfo mySelfInfo = gson.fromJson(object.toString(), MySelfInfo.class);
                    allowSendBean = mySelfInfo.getData().getAllow_send();
                    if (allowSendBean != null) {
                        switchHf.setChecked("1".equals(allowSendBean.getRepeat()) ? false : true);
                        switchGz.setChecked(("1".equals(allowSendBean.getFocus())) ? (false) : (true));
                    }
                }
            }
        } catch (Exception e) {

        }
    }

    @Override
    public void failCallBack(int type, String message, int httpCode) {
    }
}
