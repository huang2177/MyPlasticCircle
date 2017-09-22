package com.myplas.q.guide.activity;

import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.myplas.q.R;
import com.myplas.q.common.utils.StatusUtils;
import com.sobot.chat.widget.photoview.PhotoView;
import com.sobot.chat.widget.photoview.PhotoViewAttacher;
import com.umeng.analytics.MobclickAgent;


/**
 * 编写： 黄双
 * 电话：15378412400
 * 邮箱：15378412400@163.com
 * 时间：2017/3/31 09:11
 */
public class BigImageViewActivity extends BaseActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StatusUtils.setStatusBar(this, false, false);
        StatusUtils.setStatusTextColor(true, this);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        setContentView(R.layout.layout_bigimage_activity);
        PhotoView imageView = F(R.id.photoview);
        Glide.with(this)
                .load(getIntent().getStringExtra("imgurl"))
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .into(imageView);
        imageView.setOnPhotoTapListener(new PhotoViewAttacher.OnPhotoTapListener() {
            @Override
            public void onPhotoTap(View view, float x, float y) {
                finish();
            }
        });
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
