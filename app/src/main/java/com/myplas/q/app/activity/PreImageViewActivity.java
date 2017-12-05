package com.myplas.q.app.activity;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.preference.Preference;
import android.support.v7.graphics.Palette;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.GlideDrawableImageViewTarget;
import com.bumptech.glide.request.target.SimpleTarget;
import com.myplas.q.R;
import com.myplas.q.common.utils.ScreenUtils;
import com.myplas.q.common.utils.StatusUtils;
import com.sobot.chat.widget.photoview.PhotoView;
import com.sobot.chat.widget.photoview.PhotoViewAttacher;

import me.panpf.sketch.SketchImageView;
import me.panpf.sketch.decode.ImageAttrs;
import me.panpf.sketch.request.CancelCause;
import me.panpf.sketch.request.DisplayListener;
import me.panpf.sketch.request.DownloadProgressListener;
import me.panpf.sketch.request.ErrorCause;
import me.panpf.sketch.request.ImageFrom;


/**
 * 编写： 黄双
 * 电话：15378412400
 * 邮箱：15378412400@163.com
 * 时间：2017/3/31 09:11
 */
public class PreImageViewActivity extends BaseActivity {

    private LinearLayout layout;
    private SketchImageView photoView;

    @SuppressLint("HandlerLeak")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StatusUtils.setStatusBar(this, false, false);
        StatusUtils.setStatusTextColor(true, this);
        setContentView(R.layout.layout_bigimage_activity);

        layout = F(R.id.preimg_ll);
        photoView = F(R.id.photoview);

//        Glide.with(this)
//                .load(getIntent().getStringExtra("imgurl"))
//                .asBitmap()
//                .placeholder(R.drawable.card)
//                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
//                .into(new SimpleTarget<Bitmap>() {
//                    @Override
//                    public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
//                        photoView.setImageBitmap(resource);
//                        changeBackgroundColor(resource);
//                    }
//                });

//        photoView.setOnPhotoTapListener(new PhotoViewAttacher.OnPhotoTapListener() {
//            @Override
//            public void onPhotoTap(View view, float x, float y) {
//                onBackPressed();
//            }
//        });
        // setDownloadProgressListener() 一定要在 displayImage() 之前
        photoView.setDisplayListener(new DisplayListener() {
            @Override
            public void onStarted() {
                // 只有在需要进入非主线程加载图片时才会回调 onStarted() 方法
            }

            @Override
            public void onCompleted(Drawable drawable, ImageFrom imageFrom, ImageAttrs imageAttrs) {
                if (drawable != null) {
                    changeBackgroundColor(((BitmapDrawable) drawable).getBitmap());
                }
            }

            @Override
            public void onError(ErrorCause errorCause) {

            }

            @Override
            public void onCanceled(CancelCause cancelCause) {

            }
        });

        photoView.setShowDownloadProgressEnabled(true, Color.parseColor("#000000"));
        //photoView.setZoomEnabled(true);
        photoView.displayImage(getIntent().getStringExtra("imgurl"));

        photoView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    /**
     * 改变背景颜色
     *
     * @param bitmap
     */
    private void changeBackgroundColor(Bitmap bitmap) {
        if (bitmap == null) {
            return;
        }
        Palette.Builder builder = Palette.from(bitmap);
        builder.generate(new Palette.PaletteAsyncListener() {
            @Override
            public void onGenerated(Palette palette) {
                //获取到充满活力的样本
                int vibrant = palette.getLightVibrantColor(990000);
                layout.setBackgroundColor(deepenColor(vibrant));
                Window window = getWindow();
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    window.setNavigationBarColor(deepenColor(vibrant));
                }

            }
        });
    }

    /**
     * 对颜色进行加深处理
     *
     * @return
     */
    private int deepenColor(int RGBValues) {
        int alpha = RGBValues >> 24;
        int red = RGBValues >> 16 & 0xFF;
        int green = RGBValues >> 8 & 0xFF;
        int blue = RGBValues & 0xFF;
        red = (int) Math.floor(red * (1 - 0.1));
        green = (int) Math.floor(green * (1 - 0.1));
        blue = (int) Math.floor(blue * (1 - 0.1));
        return Color.rgb(red, green, blue);
    }
}
