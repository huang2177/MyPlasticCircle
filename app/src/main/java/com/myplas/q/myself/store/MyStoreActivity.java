package com.myplas.q.myself.store;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ScrollView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.myplas.q.R;
import com.myplas.q.app.activity.BaseActivity;
import com.myplas.q.common.api.API;
import com.myplas.q.common.appcontext.Constant;
import com.myplas.q.common.net.ResultCallBack;
import com.myplas.q.common.utils.SharedUtils;
import com.myplas.q.common.utils.TextUtils;
import com.myplas.q.common.utils.UCloudUtils;
import com.myplas.q.common.view.EmptyView;
import com.myplas.q.common.view.MyEditText;
import com.myplas.q.common.view.ProgressImageView;
import com.myplas.q.homepage.beans.ContactInfoBean;
import com.yanzhenjie.album.Album;
import com.yanzhenjie.durban.Controller;
import com.yanzhenjie.durban.Durban;

import org.json.JSONObject;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * @author 黄双
 * @date 2017/12/20 0020
 */

public class MyStoreActivity extends BaseActivity implements View.OnClickListener
        , MyEditText.OnTextWatcher
        , ResultCallBack
        , UCloudUtils.UCloudListener {
    private Button button;
    private EmptyView emptyView;
    private ScrollView scrollView;
    private FrameLayout flLicence, flHead;
    private ProgressImageView imageLicence, imageHead;
    private MyEditText editName, editIntroduction, editBusiness;

    private int color;
    private SharedUtils sharedUtils;
    private final int HCODE = 10, ICODE = 20;
    private String companyName, companyIntroduction, headPath, licencePath, stauts, business;
    private UCloudUtils uCloudUtils;
    private String userId;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_layout_myself_store);
        initTileBar();
        setTitle("我的店铺");

        initView();
        getNetData();

    }

    private void initView() {
        button = F(R.id.store_button);
        flHead = F(R.id.store_fl_head);
        emptyView = F(R.id.store_emptyview);
        flLicence = F(R.id.store_fl_licence);
        scrollView = F(R.id.store_scrollview);
        imageHead = F(R.id.store_img_show_head);
        editBusiness = F(R.id.store_edit_business);
        editName = F(R.id.store_edit_company_name);
        imageLicence = F(R.id.store_img_show_licence);
        editIntroduction = F(R.id.store_edit_introduction);

        sharedUtils = SharedUtils.getSharedUtils();
        userId = sharedUtils.getData(this, Constant.USERID);

        button.setOnClickListener(this);
        flHead.setOnClickListener(this);
        editName.addOnTextWatcher(this);
        flLicence.setOnClickListener(this);
        editIntroduction.addOnTextWatcher(this);

        uCloudUtils = new UCloudUtils(this, this);

        sharedUtils = SharedUtils.getSharedUtils();
        color = getResources().getColor(R.color.color_red);
        stauts = getIntent().getStringExtra(Constant.STAUTS);

        if ("2".equals(stauts)) {
            loadAnimation(false);
        }
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
                button.setClickable(false);
                //button.setBackgroundResource(R.drawable.login_btn_shape);
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
                // .maxWidthHeight(code == 100 ? 340 : 288, code == 100 ? 485 : 288)
                // 设置裁剪比例
                .aspectRatio(code == 100 ? 1 : 339, code == 100 ? 1 : 486)
                // 图片压缩格式：JPEG、PNG。
                .compressFormat(Durban.COMPRESS_JPEG)
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
     * 图片上传后回调服务器
     */
    private void uploadNotify(String path, String type) {
        Map<String, String> map = new HashMap(16);
        map.put("type", type);
        map.put("path", path);
        postAsyn(this, API.UPLOADNOTIFY, map, this, 1, false);
    }

    /**
     * 提交
     */
    private void commit() {
        if (isWriteInfo()) {
            Map<String, String> map = new HashMap(16);
            map.put("company", companyName);
            map.put("avatar_url", headPath);
            map.put("business_license", business);
            map.put("business_license_url", licencePath);
            map.put("company_description", companyIntroduction);
            postAsyn(this, API.SHOPS, map, this, 2, false);
        } else {
            TextUtils.toast(this, "请先填写完整资料！");
        }
    }

    /**
     * 获取数据
     */
    public void getNetData() {
        Map<String, String> map = new HashMap(16);
        map.put("user_id", userId);
        getAsyn(this, API.GET_ZONE_FRIEND, map, this, 3, false);
    }

    /**
     * 改变button颜色
     */
    private void changeBtnColor() {
//        if (isWriteInfo()) {
//            button.setBackgroundResource(R.drawable.login_btn_shape_hl);
//        } else {
//            button.setBackgroundResource(R.drawable.login_btn_shape);
//        }
    }

    /**
     * 判断是否已经填写数据
     *
     * @return
     */
    private boolean isWriteInfo() {
        companyName = editName.getText().toString();
        business = editBusiness.getText().toString();
        companyIntroduction = editIntroduction.getText().toString();

        return TextUtils.notEmpty(companyName)
                && TextUtils.notEmpty(business)
                && TextUtils.notEmpty(headPath)
                && TextUtils.notEmpty(licencePath)
                && TextUtils.notEmpty(companyIntroduction);
    }

    @Override
    public void onTextChanged(View v, String s) {
        changeBtnColor();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        try {
            if (requestCode == ICODE) {
                ArrayList<String> mList = Album.parseResult(data);
                if (mList.size() == 0) {
                    return;
                }
                imageLicence.setUseProgress(true);
                Glide.with(this).load(mList.get(0)).into(imageLicence);
                uCloudUtils.putFile(new File(mList.get(0)), 1);

                changeBtnColor();
            } else if (requestCode == HCODE) {
                cutPhoto(Album.parseResult(data), HCODE * 10);
            } else {
                ArrayList<String> mList = Durban.parseResult(data);
                if (mList.size() == 0) {
                    return;
                }
                imageHead.setUseProgress(true);
                Glide.with(this).load(mList.get(0)).into(imageHead);
                uCloudUtils.putFile(new File(mList.get(0)), 2);

                changeBtnColor();
            }
        } catch (Exception e) {

        }
    }

    @Override
    public void callBack(Object object, int type) {
        try {
            JSONObject jsonObject = new JSONObject(object.toString());
            String err = jsonObject.getString("code");

            if (type == 1 && "0".equals(err)) {
                TextUtils.toast(this, jsonObject.getString("message"));
            }
            if (type == 2 && "0".equals(err)) {
                TextUtils.toast(this, jsonObject.getString("message"));
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(editName.getWindowToken(), 0);

                loadAnimation(true);
            } else if (type == 2) {
                button.setClickable(true);
                //button.setBackgroundResource(R.drawable.login_btn_shape_hl);
                TextUtils.toast(this, jsonObject.getString("message"));
            }
            if (type == 3 && "0".equals(err)) {
                ContactInfoBean contactInfoBean = new Gson().fromJson(object.toString(), ContactInfoBean.class);
                editName.setText(contactInfoBean.getData().getC_name());
                editBusiness.setText(contactInfoBean.getData().getBusiness_licence());
                editIntroduction.setText(contactInfoBean.getData().getCom_intro());
                changeBtnColor();
            }
        } catch (Exception e) {

        }
    }

    @Override
    public void failCallBack(int type, String message, int httpCode) {
        try {
            JSONObject jsonObject = new JSONObject(message);

            if (type == 2) {
                button.setClickable(true);
                //button.setBackgroundResource(R.drawable.login_btn_shape_hl);
            }
            TextUtils.toast(this, jsonObject.getString("message"));

        } catch (Exception e) {

        }
    }


    @Override
    public void uCloudSucess(int type, String flieName) {
        if (type == 1) {
            licencePath = flieName;
            uploadNotify(flieName, "license");
        } else {
            headPath = flieName;
            uploadNotify(flieName, "thumb");
        }
    }

    @Override
    public void uCloudProcess(int type, int value) {
        if (type == 1) {
            imageLicence.setProgress(value);
        } else {
            imageHead.setProgress(value);
        }
    }

    @Override
    public void uCloudFail(int type) {

    }

    /**
     * 保存成功后显示审核中
     */
    private void loadAnimation(boolean animateEnable) {
        emptyView.setVisibility(View.VISIBLE);
        emptyView.setMyManager(R.drawable.icon_auditing);

        emptyView.setNoMessageText1("提交成功，请等待客服人员审核！");
        emptyView.setNoMessageText("预计3个工作日内审核完毕，审核结果会短信通知到您的注册手机。");

        if (animateEnable) {
            emptyView.startAnimation(AnimationUtils.loadAnimation(this, R.anim.in_top));
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        uCloudUtils.cancleRequest();
        //getResources().getDisplayMetrics().density;
    }
}
