package com.myplas.q.guide.activity;

import android.content.Intent;
import android.os.Bundle;

import com.myplas.q.R;
import com.myplas.q.common.utils.GetNumUtil;
import com.myplas.q.common.utils.SharedUtils;
import com.myplas.q.common.utils.VersionUtils;
import com.umeng.analytics.MobclickAgent;

/**
 * 编写： 黄双
 * 电话：15378412400
 * 邮箱：15378412400@163.com
 * 时间：2017/3/17 11:52
 */
public class SplashActivity extends BaseActivity {
    boolean isGuided=false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_splash_activity);
        isGuided= SharedUtils.getSharedUtils().getBoolean(this,"isGuided");
        new MyThread().start();
    }
    class MyThread extends Thread{
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

    public void goMainOrGuide(){
        if(!isGuided){
            startActivity(new Intent(SplashActivity.this, GuideActivity.class));
        }else {
            startActivity(new Intent(SplashActivity.this, MainActivity.class));
        }
        finish();
    }
    public void onResume() {
        super.onResume();
        MobclickAgent.onResume(this);
    }
    public void onPause() {
        super.onPause();
        MobclickAgent.onPause(this);
    }

    public void onResum() {
        //测试Git
    }
}
