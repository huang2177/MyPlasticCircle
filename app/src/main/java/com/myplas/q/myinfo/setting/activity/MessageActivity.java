package com.myplas.q.myinfo.setting.activity;

import android.os.Bundle;
import android.view.View;

import com.myplas.q.R;
import com.myplas.q.guide.activity.BaseActivity;
import com.sobot.chat.SobotApi;
import com.sobot.chat.api.model.Information;
import com.umeng.analytics.MobclickAgent;

/**
 * 编写： 黄双
 * 电话：15378412400
 * 邮箱：15378412400@163.com
 * 时间：2017/3/28 10:25
 */
public class MessageActivity extends BaseActivity {
    private Information information;
    private String appkey = "c1ff771c06254db796cd7ce1433d2004";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_layout_setting_message);
        initTileBar();
        setTitle("短信设置");
    }

    public void onResume() {
        super.onResume();
        MobclickAgent.onResume(this);
    }

    public void onPause() {
        super.onPause();
        MobclickAgent.onPause(this);
    }
}
