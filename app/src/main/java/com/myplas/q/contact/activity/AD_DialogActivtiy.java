package com.myplas.q.contact.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.myplas.q.R;
import com.myplas.q.app.activity.BaseActivity;
import com.myplas.q.common.utils.SharedUtils;

/**
 * 编写： 黄双
 * 电话：15378412400
 * 邮箱：15378412400@163.com
 * 时间：2017/4/17 20:27
 */
public class AD_DialogActivtiy extends BaseActivity {
    ImageView imageView, close;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_address_ad);
        getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);

        imageView = F(R.id.cover_img);
        close = F(R.id.cover_img_close);

        close.setImageResource(R.drawable.btn_skip_ad);
        Glide.with(this).load(getIntent().getStringExtra("imgurl")).into(imageView);

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
        close.setOnClickListener(new View.OnClickListener() {
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
}
