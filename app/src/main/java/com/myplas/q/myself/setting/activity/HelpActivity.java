package com.myplas.q.myself.setting.activity;

import android.graphics.BitmapRegionDecoder;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.myplas.q.R;
import com.myplas.q.app.activity.BaseActivity;
import com.myplas.q.common.view.largeimageview.LargeImageView;
import com.sobot.chat.SobotApi;
import com.sobot.chat.api.model.Information;

import java.io.IOException;
import java.io.InputStream;

/**
 * 编写： 黄双
 * 电话：15378412400
 * 邮箱：15378412400@163.com
 * 时间：2017/3/28 10:25
 */
public class HelpActivity extends BaseActivity {
    private Information information;
    private String appkey = "c1ff771c06254db796cd7ce1433d2004";

    private LargeImageView imageView;
    private BitmapRegionDecoder decoder;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_layout_setting_help);
        imageView = F(R.id.sketchimg);

        initTileBar();
        setTitle("帮助");
        setRightIVVisibility(View.VISIBLE);

        mIVRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                information = new Information();
                information.setAppkey(appkey);
                SobotApi.startSobotChat(HelpActivity.this, information);
            }
        });

//        try {
//            InputStream open = getAssets().open("help.jpg");
//            imageView.setInputStream(open);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }
}
