package com.myplas.q.myself.setting.activity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;


import com.bumptech.glide.Glide;
import com.myplas.q.R;
import com.myplas.q.app.activity.BaseActivity;
import com.myplas.q.common.view.ProgressImageView;
import com.sobot.chat.SobotApi;
import com.sobot.chat.api.model.Information;
import com.umeng.analytics.MobclickAgent;

import me.panpf.sketch.SketchImageView;

/**
 * 编写： 黄双
 * 电话：15378412400
 * 邮箱：15378412400@163.com
 * 时间：2017/3/28 10:25
 */
public class HelpActivity extends BaseActivity {
    private Information information;
    private String appkey = "c1ff771c06254db796cd7ce1433d2004";

    private ProgressImageView imageView;
    private float progress = 0f;

    @SuppressLint("HandlerLeak")
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (progress >= 1) {
                progress = 1f;
                imageView.setProgress(progress);
            } else {
                progress += 0.01f;
                imageView.setProgress(progress);
                handler.sendEmptyMessageDelayed(123, 300);
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_layout_setting_help);

        initTileBar();
        setTitle("帮助");
        setRightIVVisibility(View.VISIBLE);

        imageView = F(R.id.sketchimg);
        Glide.with(this).load(R.drawable.aa).into(imageView);


        mIVConact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                information = new Information();
//                information.setAppkey(appkey);
//                SobotApi.startSobotChat(HelpActivity.this, information);
                startUpload(null);
            }
        });
    }

    public void startUpload(View view) {
        handler.sendEmptyMessageDelayed(123, 300);
    }
}
