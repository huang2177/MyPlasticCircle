package com.myplas.q.myself.setting.activity;

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
import com.yanzhenjie.durban.Controller;
import com.yanzhenjie.durban.Durban;

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
    private ArrayList<String> pathList;

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
                String s = (type.equals("1")) ? ("选择头像") : ("选择名片");
                Album.albumRadio(this)
                        .toolBarColor(color) // Toolbar 颜色，默认蓝色。
                        .statusBarColor(color) // StatusBar 颜色，默认蓝色。
                        .title(s) // 配置title。

                        .columnCount(2) // 相册展示列数，默认是2列。
                        .camera(false) // 是否有拍照功能。
                        .start(200); // 200是请求码，返回时onActivityResult()的第一个参数。
                break;
            case R.id.takephoto_cancle:
            case R.id.takephoto_close:
                finish();
                break;
            default:
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        try {

            if (requestCode == 100) {
                if (resultCode == RESULT_OK) {  //相机
                    List<String> pathList = Album.parseResult(data);
                    int backcode = Integer.parseInt(type);
                    Intent intent = new Intent();
                    intent.putExtra("img_url", pathList.get(0));
                    setResult(backcode, intent);
                    finish();
                }
            }

            if (requestCode == 200) {
                if (resultCode == RESULT_OK) {  //相册
                    pathList = Album.parseResult(data);
                    cutPhoto(pathList);
//                    int backcode = Integer.parseInt(type);
//                    String pick_url = (pathList.size() == 0) ? (pathList.get(0)) : (pathList.get(0));
//                    Intent intent = new Intent();
//                    intent.putExtra("img_url", pick_url);
//                    setResult(backcode, intent);
//                    finish();
                }
            }
            if (requestCode == 300) {  // 解析剪切结果：
                if (resultCode == RESULT_OK) {
                    int backcode = Integer.parseInt(type);
                    ArrayList<String> mImageList = Durban.parseResult(data);
                    String pick_url = (mImageList.size() == 0) ? (pathList.get(0)) : (mImageList.get(0));
                    Intent intent = new Intent();
                    intent.putExtra("img_url", pick_url);
                    setResult(backcode, intent);
                    finish();
                }
            }
        } catch (Exception e) {
        }
    }

    /**
     * 裁剪
     *
     * @param pathList
     */
    private void cutPhoto(ArrayList<String> pathList) {
        Durban.with(this)
                // 裁剪界面的标题。
                .title("裁剪")
                .statusBarColor(color)
                .toolBarColor(color)
                // 图片路径list或者数组。
                .inputImagePaths(pathList)
                // 图片输出文件夹路径。
                // 裁剪图片输出的最大宽高。
                //.maxWidthHeight(100, 100)
                // 裁剪时的宽高比。
                //.aspectRatio(1, 1)
                // 图片压缩格式：JPEG、PNG。
                .compressFormat(Durban.COMPRESS_PNG)
                // 图片压缩质量，请参考：Bitmap#compress(Bitmap.CompressFormat, int, OutputStream)
                .compressQuality(100)
                // 裁剪时的手势支持：ROTATE, SCALE, ALL, NONE.
                .gesture(Durban.GESTURE_SCALE)
                .controller(Controller.newBuilder()
                        .enable(true) // 是否开启控制面板。
                        .rotation(true) // 是否有旋转按钮。
                        .rotationTitle(true) // 旋转控制按钮上面的标题。
                        .scale(true) // 是否有缩放按钮。
                        .scaleTitle(true) // 缩放控制按钮上面的标题。
                        .build()) // 创建控制面板配置。
                .requestCode(300)
                .start();
    }


}
