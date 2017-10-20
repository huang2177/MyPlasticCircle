package com.myplas.q.contact.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.myplas.q.R;
import com.myplas.q.guide.activity.BaseActivity;
import com.myplas.q.common.utils.SharedUtils;
import com.umeng.analytics.MobclickAgent;

/**
 * 编写： 黄双
 * 电话：15378412400
 * 邮箱：15378412400@163.com
 * 时间：2017/4/17 20:27
 */
public class AD_DialogActivtiy extends BaseActivity {
    ImageView imageView,imageView_close;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_address_ad);
        imageView= (ImageView) findViewById(R.id.cover_img);
        imageView_close= (ImageView) findViewById(R.id.cover_img_close);
        Glide.with(this).load(getIntent().getStringExtra("imgurl")).into(imageView);
        imageView_close.setImageResource(R.drawable.btn_skip_ad);
        getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                Intent intent=new Intent(AD_DialogActivtiy.this,Cover_WebActivity.class);
                intent.putExtra("url",getIntent().getStringExtra("url"));
                intent.putExtra("title",getIntent().getStringExtra("title"));
                startActivity(intent);
            }
        });
        imageView_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedUtils.getSharedUtils().setBooloean(AD_DialogActivtiy.this,"isshow",false);
                finish();
            }
        });
    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode==KeyEvent.KEYCODE_BACK){
            SharedUtils.getSharedUtils().setBooloean(AD_DialogActivtiy.this,"isshow",false);
        }
        return super.onKeyDown(keyCode, event);
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
