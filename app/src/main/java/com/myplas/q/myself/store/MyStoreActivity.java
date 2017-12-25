package com.myplas.q.myself.store;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ScrollView;

import com.bumptech.glide.Glide;
import com.myplas.q.R;
import com.myplas.q.app.activity.BaseActivity;
import com.myplas.q.common.api.API;
import com.myplas.q.common.appcontext.Constant;
import com.myplas.q.common.netresquset.ProgressListener;
import com.myplas.q.common.netresquset.ResultCallBack;
import com.myplas.q.common.utils.SharedUtils;
import com.myplas.q.common.utils.TextUtils;
import com.myplas.q.common.view.EmptyView;
import com.myplas.q.common.view.MyEditText;
import com.myplas.q.common.view.ProgressImageView;
import com.yanzhenjie.album.Album;
import com.yanzhenjie.durban.Controller;
import com.yanzhenjie.durban.Durban;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * @author 黄双
 * @date 2017/12/20 0020
 */

public class MyStoreActivity extends BaseActivity implements View.OnClickListener
        , MyEditText.OnTextWatcher, ResultCallBack, ProgressListener {
    private Button button;
    private EmptyView emptyView;
    private ScrollView scrollView;
    private FrameLayout flLicence, flHead;
    private MyEditText editName, editIntroduction;
    private ProgressImageView imageLicence, imageHead;

    private int color;
    private SharedUtils sharedUtils;
    private final int HCODE = 10, ICODE = 20;
    private String companyName, companyIntroduction, headPath, licencePath;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_layout_myself_store);
        initTileBar();
        setTitle("我的店铺");

        initView();
    }

    private void initView() {
        button = F(R.id.store_button);
        flHead = F(R.id.store_fl_head);
        emptyView = F(R.id.store_emptyview);
        flLicence = F(R.id.store_fl_licence);
        scrollView = F(R.id.store_scrollview);
        imageHead = F(R.id.store_img_show_head);
        editName = F(R.id.store_edit_company_name);
        imageLicence = F(R.id.store_img_show_licence);
        editIntroduction = F(R.id.store_edit_introduction);

        button.setOnClickListener(this);
        flHead.setOnClickListener(this);
        editName.addOnTextWatcher(this);
        flLicence.setOnClickListener(this);
        editIntroduction.addOnTextWatcher(this);

        emptyView.setMyManager(R.drawable.icon_auditing);
        emptyView.setNoMessageText1("提交成功，请等待客服人员审核！");
        emptyView.setNoMessageText("预计3个工作日内审核完毕，审核结果会短信通知到您的注册手机。");

        sharedUtils = SharedUtils.getSharedUtils();
        color = getResources().getColor(R.color.color_red);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.store_fl_head:
                pickPhoto("上传头像", HCODE);
                break;
            case R.id.store_fl_licence:
                pickPhoto("上传营业执照", ICODE);
                break;
            case R.id.store_button:
                commit();
                break;
            default:
                break;
        }
    }

    /**
     * 选取图片
     */
    private void pickPhoto(String title, int code) {
        Album.albumRadio(this)
                .toolBarColor(color) // Toolbar 颜色，默认蓝色。
                .statusBarColor(color) // StatusBar 颜色，默认蓝色。
                .title(title) // 配置title。

                .columnCount(3) // 相册展示列数，默认是2列。
                .camera(true) // 是否有拍照功能。
                .start(code); // 200是请求码，返回时onActivityResult()的第一个参数。
    }

    /**
     * 裁剪
     *
     * @param pathList
     * @param i
     */
    private void cutPhoto(ArrayList<String> pathList, int code) {
        Durban.with(this)
                // 裁剪界面的标题。
                .title("裁剪")
                .statusBarColor(color)
                .toolBarColor(color)
                // 图片路径list或者数组。
                .inputImagePaths(pathList)
                // 图片输出文件夹路径。
                // 裁剪图片输出的最大宽高。
                //.maxWidthHeight(code == 100 ? 340 : 288, code == 100 ? 485 : 288)
                //设置裁剪比例
                .aspectRatio(code == 100 ? 1 : 339, code == 100 ? 1 : 486)
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
                .requestCode(code)
                .start();
    }

    /**
     * 提交
     */
    private void commit() {
        if (isWriteInfo()) {
            upLoadFile(API.BUSINESSLICENSEUPLOAD, licencePath, 1);
        } else {
            TextUtils.toast(this, "请先填写完整资料！");
        }
    }


    /**
     * 改变button颜色
     */
    private void changeBtnColor() {
        if (isWriteInfo()) {
            button.setBackgroundResource(R.drawable.login_btn_shape_hl);
        } else {
            button.setBackgroundResource(R.drawable.login_btn_shape);
        }
    }

    /**
     * 判断是否已经填写数据
     *
     * @return
     */
    private boolean isWriteInfo() {
        companyName = editName.getText().toString();
        companyIntroduction = editIntroduction.getText().toString();

        return TextUtils.notEmpty(companyName)
                && TextUtils.notEmpty(companyIntroduction)
                && TextUtils.notEmpty(headPath)
                && TextUtils.notEmpty(licencePath);
    }

    /**
     * 上传图片
     */
    public void upLoadFile(String method, String path, int type) {
        String url = API.BASEURL + method;
        String token = sharedUtils.getData(this, Constant.TOKEN);
        postUpLoadImg(this, url, path, token, this, type, this);
    }

    /**
     * 保存资料
     */
    public void saveInfo() {
        String url = API.BASEURL + API.SUBMISSION;
        Map<String, String> map = new HashMap(16);
        map.put("company_description", companyIntroduction);
        map.put("company", companyName);
        postAsyn(this, url, map, this, 3, false);
    }

    @Override
    public void onTextChanged(View v, String s) {
        changeBtnColor();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == HCODE || requestCode == ICODE) {
            if (resultCode == RESULT_OK) {  //相册
                cutPhoto(Album.parseResult(data), requestCode * 10);
            }
        } else if (resultCode == RESULT_OK) {
            ArrayList<String> mImageList = Durban.parseResult(data);
            if (mImageList.size() == 0) {
                return;
            }
            if (requestCode == HCODE * 10) {  // 解析剪切结果：
                headPath = mImageList.get(0);
                imageHead.setUseProgress(true);
                Glide.with(this).load(headPath).into(imageHead);

            } else if (requestCode == ICODE * 10) {
                licencePath = mImageList.get(0);
                imageLicence.setUseProgress(true);
                Glide.with(this).load(licencePath).into(imageLicence);
            }
            changeBtnColor();
        }
    }

    @Override
    public void callBack(Object object, int type) {
        try {
            Log.e("-----callBack" + type + type, object.toString());
            JSONObject jsonObject = new JSONObject(object.toString());
            String err = jsonObject.getString("err");
            if (type == 1 && "0".equals(err)) {
                upLoadFile(API.USERPICUPLOAD, headPath, 2);
            }
            if (type == 2 && "0".equals(err)) {
                saveInfo();
            }
            if (type == 3 && "0".equals(err)) {
                TextUtils.toast(this, "提交成功！");
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(editName.getWindowToken(), 0);

                ObjectAnimator
                        .ofFloat(scrollView, "alpha", 1.0F, 0F)
                        .setDuration(700)
                        .start();

                emptyView.setVisibility(View.VISIBLE);
                ObjectAnimator
                        .ofFloat(emptyView, "alpha", 0F, 1.0F)
                        .setDuration(700)
                        .start();
            }

        } catch (Exception e) {

        }
    }

    @Override
    public void failCallBack(int type) {

    }

    /**
     * 上传进度监听
     *
     * @param currentBytes
     * @param contentLength
     * @param done
     * @param type
     */
    @Override
    public void onProgress(long currentBytes, long contentLength, boolean done, int type) {
        if (type == 2) {
            imageHead.setProgress((float) currentBytes / contentLength);
        } else {
            imageLicence.setProgress((float) currentBytes / contentLength);
        }
    }
}