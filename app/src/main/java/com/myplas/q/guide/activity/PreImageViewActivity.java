package com.myplas.q.guide.activity;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.graphics.Palette;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.myplas.q.R;
import com.myplas.q.common.utils.StatusUtils;
import com.sobot.chat.widget.photoview.PhotoView;
import com.sobot.chat.widget.photoview.PhotoViewAttacher;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;


/**
 * 编写： 黄双
 * 电话：15378412400
 * 邮箱：15378412400@163.com
 * 时间：2017/3/31 09:11
 */
public class PreImageViewActivity extends BaseActivity {

    private PhotoView photoView;
    private LinearLayout layout;

    private Handler handler;

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
//                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
//                .into(photoView);
//        Matrix max = new Matrix();
//        photoView.setDrawingCacheEnabled(true);
//        Bitmap bm = Bitmap.createBitmap(photoView.getDrawingCache());  ;
//        photoView.setDrawingCacheEnabled(false);
//        Log.e("=======", (bm==null)+"====");{'Michael': 95, 1: 75, 'Tracy': 85}

        photoView.setOnPhotoTapListener(new PhotoViewAttacher.OnPhotoTapListener() {
            @Override
            public void onPhotoTap(View view, float x, float y) {
                onBackPressed();
            }
        });

        handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                Bitmap bitmap = (Bitmap) msg.obj;
                photoView.setImageBitmap(bitmap);
                Palette.generateAsync(bitmap, new Palette.PaletteAsyncListener() {

                    @Override
                    public void onGenerated(Palette palette) {
                        //通过palette来获取对应的色调
                        //Palette.Swatch vibrant=palette.getLightVibrantSwatch();
                        int vibrant = palette.getMutedColor(990000);
                        layout.setBackgroundColor(vibrant);
                    }
                });
            }
        };

        new Thread() {
            @Override
            public void run() {
                final Bitmap bitmap = getImageFromInternet();
                Message message = Message.obtain();
                message.obj = bitmap;
                handler.sendMessage(message);
            }
        }.start();
    }

    public Bitmap getImageFromInternet() {
        String url = getIntent().getStringExtra("imgurl");
        InputStream is = null;
        try {
            is = new URL(url).openStream();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return BitmapFactory.decodeStream(is);
    }
}
