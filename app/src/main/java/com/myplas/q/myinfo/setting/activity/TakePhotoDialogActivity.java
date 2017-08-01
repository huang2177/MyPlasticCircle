package com.myplas.q.myinfo.setting.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import com.myplas.q.R;
import com.myplas.q.guide.activity.BaseActivity;
import com.umeng.analytics.MobclickAgent;
import com.yanzhenjie.album.Album;

import java.util.ArrayList;
import java.util.List;

/**
 * 编写： 黄双
 * 电话：15378412400
 * 邮箱：15378412400@163.com
 * 时间：2017/4/14 15:32
 */
public class TakePhotoDialogActivity extends BaseActivity implements View.OnClickListener {
    private View view;
    private TextView mTextView_take, mTextView_pick, mTextView_cancle;

    private int color;
    private String type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_layout_takephoto);
        getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);

        init();
    }

    public void init() {
        type = getIntent().getStringExtra("type");
        color = getResources().getColor(R.color.color_red);

        view = findViewById(R.id.takephoto_close);
        mTextView_take = (TextView) findViewById(R.id.takephoto_take);
        mTextView_pick = (TextView) findViewById(R.id.takephoto_pick);
        mTextView_cancle = (TextView) findViewById(R.id.takephoto_cancle);

        view.setOnClickListener(this);
        mTextView_take.setOnClickListener(this);
        mTextView_pick.setOnClickListener(this);
        mTextView_cancle.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.takephoto_take:
                Album.camera(this)
                        .start(100); // 100是请求码，返回时onActivityResult()的第一个参数。
                break;
            case R.id.takephoto_pick:
                Album.album(this)
                        .toolBarColor(color) // Toolbar 颜色，默认蓝色。
                        .statusBarColor(color) // StatusBar 颜色，默认蓝色。
                        .title("选择头像") // 配置title。
                        .columnCount(2) // 相册展示列数，默认是2列。
                        .camera(false) // 是否有拍照功能。
                        .start(200); // 200是请求码，返回时onActivityResult()的第一个参数。
                break;
            case R.id.takephoto_cancle:
            case R.id.takephoto_close:
                finish();
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        try {
            //相机
            if (requestCode == 100) {
                if (resultCode == RESULT_OK) { // Successfully.
                    List<String> pathList = Album.parseResult(data); // Parse path.
                }
            }
            //相册
            if (requestCode == 200) {
                if (resultCode == RESULT_OK) { // Successfully.
                    ArrayList<String> pathList = Album.parseResult(data);
                }
            }
        } catch (Exception e) {
        }
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
