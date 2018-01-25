package com.myplas.q.app.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.graphics.Palette;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.myplas.q.R;
import com.myplas.q.common.utils.StatusUtils;


/**
 * 编写： 黄双
 * 电话：15378412400
 * 邮箱：15378412400@163.com
 * 时间：2017/3/31 09:11
 */
public class PreImageViewActivity extends BaseActivity {

    private LinearLayout layout;
    private ImageView photoView;

    public static void launch(Activity activity, View transitionView, String resId) {
        Intent intent = new Intent(activity, PreImageViewActivity.class);
        intent.putExtra("imgurl", resId);

        ActivityOptionsCompat options = ActivityOptionsCompat
                .makeSceneTransitionAnimation(activity, transitionView, "bigImageView");

        ActivityCompat.startActivity(activity, intent, options.toBundle());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StatusUtils.setStatusBar(this, false, false);
        StatusUtils.setStatusTextColor(true, this);
        setContentView(R.layout.layout_bigimage_activity);

        layout = F(R.id.preimg_ll);
        photoView = F(R.id.photoview);


//        // setDownloadProgressListener() 一定要在 displayImage() 之前
//        photoView.setDisplayListener(new DisplayListener() {
//            @Override
//            public void onStarted() {
//                // 只有在需要进入非主线程加载图片时才会回调 onStarted() 方法
//            }
//            @Override
//            public void onCompleted(Drawable drawable, ImageFrom imageFrom, ImageAttrs imageAttrs) {
//                if (drawable != null) {
//                    changeBackgroundColor(((BitmapDrawable) drawable).getBitmap());
//                }
//            }
//            @Override
//            public void onError(ErrorCause errorCause) {
//                String type = getIntent().getStringExtra("type");
//                photoView.displayResourceImage(getDefaultDrawable(type));
//            }
//            @Override
//            public void onCanceled(CancelCause cancelCause) {
//
//            }
//        });
//        photoView.displayImage(url);
        String url = getIntent().getStringExtra("imgurl");
        String type = getIntent().getStringExtra("type");
        Glide.with(this)
                .load(!"http:".equals(url) ? url : getDefaultDrawable(type))
                .asBitmap()
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .into(new SimpleTarget<Bitmap>() {
                    @Override
                    public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                        photoView.setImageBitmap(resource);
                        changeBackgroundColor(resource);
                    }
                });

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

    /**
     * 获取加载出错时的默认图片
     *
     * @param type
     * @return
     */
    public int getDefaultDrawable(String type) {
        int resId = 0;
        switch (type) {
            case "0":
                resId = R.drawable.img_defaul_male;
                break;
            case "1":
                resId = R.drawable.img_defaul_female;
                break;
            case "2":
                resId = R.drawable.card;
                break;
            case "3":
                resId = R.drawable.default_qq;
                break;
            default:
                break;
        }
        return resId;
    }
}
