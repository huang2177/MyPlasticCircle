package com.myplas.q.app.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.WindowManager;


import com.myplas.q.R;
import com.myplas.q.common.utils.SharedUtils;
import com.myplas.q.common.utils.StatusUtils;
import com.umeng.analytics.MobclickAgent;

/**
 * 编写： 黄双
 * 电话：15378412400
 * 邮箱：15378412400@163.com
 * 时间：2017/3/17 11:52
 */
public class SplashActivity extends Activity {
    boolean isGuided = false;
    private MyThread mMyThread = new MyThread();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StatusUtils.setStatusBar(this, false, false);
        StatusUtils.setStatusTextColor(false, this);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        setContentView(R.layout.layout_splash_activity);
        isGuided = SharedUtils.getSharedUtils().getBoolean(this, "isGuided");
        mMyThread.start();
    }

    private class MyThread extends Thread {

        @Override
        public void run() {
            super.run();
            try {
                Thread.sleep(1500);
                goMainOrGuide();
            } catch (Exception e) {
            }
        }
    }

    public void goMainOrGuide() {
        if (!isGuided) {
            startActivity(new Intent(SplashActivity.this, GuideActivity.class));
        } else {
            startActivity(new Intent(SplashActivity.this, MainActivity.class));
        }
        finish();
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
}
