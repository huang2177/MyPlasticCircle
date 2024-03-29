package com.myplas.q.myself.login;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.myplas.q.R;
import com.myplas.q.common.api.API;
import com.myplas.q.common.appcontext.Constant;
import com.myplas.q.common.net.ResultCallBack;
import com.myplas.q.common.utils.ACache;
import com.myplas.q.common.utils.KeyboardHelper;
import com.myplas.q.common.utils.SharedUtils;
import com.myplas.q.common.utils.StatusUtils;
import com.myplas.q.common.utils.SystemUtils;
import com.myplas.q.common.utils.TextUtils;
import com.myplas.q.common.utils.VerifyCodeUtils;
import com.myplas.q.common.view.MyEditText;
import com.myplas.q.app.activity.BaseActivity;
import com.myplas.q.app.activity.MainActivity;
import com.myplas.q.myself.setting.activity.FindPSWActivity;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * 编写： 黄双
 * 电话：15378412400
 * 邮箱：15378412400@163.com
 * 时间：2017/3/24 11:41
 */
public class LoginActivity extends BaseActivity implements View.OnClickListener
        , ResultCallBack
        , VerifyCodeUtils.CountListener
        , MyEditText.OnTextWatcher {
    private int count;
    private String key;
    private SharedUtils sharedUtils;
    private boolean clicked, isRemember, isNomalNull, isPhoneNull;

    private RelativeLayout mLayoutType;
    private Button mButtonNomal, mButtonPhone;
    private TextView textviewZhc, textviewWj, mtextviewSend;
    private ImageView imageView1, imageView2, imageviewVerification;
    private LinearLayout linearlayoutNomal, linearlayoutPhone, buttonNomal, buttonPhone, mLayoutRoot;
    private MyEditText edittextTel, edittextTel1, edittextPass, edittextVerification1, edittextVerification2;

    private ACache mACache;
    private MainActivity mainActivity;

    private VerifyCodeUtils utils;
    private KeyboardHelper mKeyboardHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StatusUtils.setStatusBar(this, false, false);
        StatusUtils.setStatusTextColor(true, this);
        setContentView(R.layout.layout_login_activity);

        initTileBar();
        setTitle("普通登录");
        setLeftIVResId(R.drawable.btn_back_black);
        setTitleBarBackground(R.color.color_white);
        setTitleBarTextColor(R.color.color_black1);

        initView();
        getVCode();

    }

    public void initView() {
        count = 60;
        clicked = true;
        mACache = ACache.get(this);
        sharedUtils = SharedUtils.getSharedUtils();
        utils = new VerifyCodeUtils(this, this);
        isRemember = sharedUtils.getBoolean(this, "remember_password");

        edittextTel = F(R.id.dl_tel);
        edittextPass = F(R.id.dl_pass);
        edittextTel1 = F(R.id.dl_tel2);
        edittextVerification1 = F(R.id.dl_verification1);
        edittextVerification2 = F(R.id.dl_verification2);

        imageView1 = F(R.id.imageView1);
        imageView2 = F(R.id.imageView2);
        imageviewVerification = F(R.id.login_phone_send1);

        mButtonNomal = F(R.id.dl_ok);
        mButtonPhone = F(R.id.dl_ok2);
        mtextviewSend = F(R.id.zhc_hq_yzm);
        buttonNomal = F(R.id.ll_login_nomal);
        buttonPhone = F(R.id.ll_login_phone);

        textviewWj = F(R.id.dl_wjmm);
        textviewZhc = F(R.id.dl_zhc);

        mLayoutRoot = F(R.id.login_root);
        linearlayoutNomal = F(R.id.linearlayout_login_nomal);
        linearlayoutPhone = F(R.id.linearlayout_login_phone);

        mLayoutRoot.setOnClickListener(this);
        mButtonNomal.setOnClickListener(this);
        mButtonPhone.setOnClickListener(this);
        textviewWj.setOnClickListener(this);
        textviewZhc.setOnClickListener(this);
        buttonNomal.setOnClickListener(this);
        buttonPhone.setOnClickListener(this);
        mtextviewSend.setOnClickListener(this);
        imageviewVerification.setOnClickListener(this);

        edittextTel.addOnTextWatcher(this);
        edittextPass.addOnTextWatcher(this);
        edittextTel1.addOnTextWatcher(this);
        edittextVerification1.addOnTextWatcher(this);
        edittextVerification2.addOnTextWatcher(this);

        mKeyboardHelper = new KeyboardHelper(this, mLayoutRoot);
        mKeyboardHelper.enable();

        // 将账号和密码都设置到文本框中
        if (isRemember) {
            String account = sharedUtils.getData(this, "tel");
            String password = sharedUtils.getData(this, "password");
            edittextTel.setText(account);
            edittextPass.setText(password);
            mButtonNomal.setBackgroundResource(R.drawable.login_btn_shape_hl);
            edittextPass.setSelection(edittextPass.getText().length());
            edittextTel.setSelection(edittextTel.getText().length());
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.dl_zhc:
                Intent intent = new Intent(this, RegisterActivity.class);
                startActivityForResult(intent, 0);
                break;
            case R.id.dl_wjmm:
                Intent intent1 = new Intent(this, FindPSWActivity.class);
                intent1.putExtra("title", "找回密码");
                startActivityForResult(intent1, 0);
                break;
            //登陆；
            case R.id.dl_ok:
                if (edittextTel.getText().toString().length() == 11
                        && TextUtils.notEmpty(edittextPass.getText().toString())) {
                    Map<String, String> map = new HashMap<String, String>();
                    map.put("username", edittextTel.getText().toString());
                    map.put("password", edittextPass.getText().toString());
                    map.put("chanel", "6");
                    postAsyn(this, API.LOGIN, map, this, 1);
                    mButtonNomal.setClickable(false);
                    mButtonNomal.setBackgroundResource(R.drawable.login_btn_shape);
                } else {
                    TextUtils.toast(this, "手机号码或密码输入有误！");
                }
                break;
            //手机动态码登陆
            case R.id.dl_ok2:
                if (edittextTel1.getText().toString().length() == 11
                        && TextUtils.notEmpty(edittextVerification1.getText().toString())
                        && TextUtils.notEmpty(edittextVerification2.getText().toString())) {
                    Map<String, String> map = new HashMap<String, String>();
                    map.put("phonenum", edittextTel1.getText().toString());
                    map.put("phonevaild", edittextVerification2.getText().toString());
                    map.put("regcode", edittextVerification1.getText().toString());
                    map.put("key", key);
                    //登陆；
                    postAsyn(this, API.SIMPLE_LOGIN, map, this, 1);
                    mButtonPhone.setClickable(false);
                    mButtonPhone.setBackgroundResource(R.drawable.login_btn_shape);
                } else {
                    TextUtils.toast(this, "手机号码或验证码输入有误！");
                }
                break;

            case R.id.ll_login_nomal:
                mTextView.setText("普通登录");
                imageView2.setVisibility(View.GONE);
                imageView1.setVisibility(View.VISIBLE);
                textviewWj.setVisibility(View.VISIBLE);
                linearlayoutPhone.setVisibility(View.GONE);
                linearlayoutNomal.setVisibility(View.VISIBLE);
                break;
            case R.id.ll_login_phone:
                mTextView.setText("手机动态码登录");
                imageView1.setVisibility(View.GONE);
                textviewWj.setVisibility(View.GONE);
                imageView2.setVisibility(View.VISIBLE);
                linearlayoutNomal.setVisibility(View.GONE);
                linearlayoutPhone.setVisibility(View.VISIBLE);
                break;
            case R.id.zhc_hq_yzm:
                checkVCide();
                break;
            case R.id.login_phone_send1:
                getVCode();
                break;
            case R.id.login_root:
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(mButtonNomal.getWindowToken(), 0);
                break;
            default:
                break;
        }
    }

    /**
     * 获取验证码
     */
    private void getVCode() {
        getAsyn(this, API.VCODE, null, this, 2, false);
    }

    /**
     * 发送动态码之前先验证
     */
    public void checkVCide() {
        String value = edittextVerification1.getText().toString();
        if (TextUtils.notEmpty(value)) {
            Map<String, String> map1 = new HashMap<String, String>();
            map1.put("value", value);
            map1.put("key", key);
            getAsyn(this, API.CHK_VCODE, map1, this, 3);
        } else {
            TextUtils.toast(this, "验证码不能为空！");
        }
    }

    /**
     * 发送验证码
     */
    public void sendMSG() {
        String tel = edittextTel1.getText().toString();
        if (tel.length() != 11) {
            Toast.makeText(this, "手机号输入有误！", Toast.LENGTH_SHORT).show();
        } else {
            Map<String, String> map1 = new HashMap<String, String>();
            map1.put("mobile", tel);
            map1.put("type", "2");
            postAsyn(this, API.SEND_MSG, map1, this, 4);
        }
    }


    @Override
    public void callBack(Object object, int type) {
        try {
            JSONObject jsonObject = new JSONObject(object.toString());
            String s = jsonObject.getString("code");
            if (type == 1) {
                TextUtils.toast(this, new JSONObject(object.toString()).getString("message"));
                if ("0".equals(s)) {
                    setData(jsonObject);

                    //回到主界面
                    Intent intent = new Intent(this, MainActivity.class);
                    intent.putExtra("type", Constant.LOGINED);
                    startActivity(intent);
                } else {
                    mButtonNomal.setClickable(true);
                    mButtonPhone.setClickable(true);
                    mButtonNomal.setBackgroundResource(isNomalNull ? R.drawable.login_btn_shape_hl : R.drawable.login_btn_shape);
                    mButtonPhone.setBackgroundResource(isPhoneNull ? R.drawable.login_btn_shape_hl : R.drawable.login_btn_shape);
                }
            }
            if (type == 2 && "0".equals(s)) {
                key = jsonObject.getString("key");
                Glide.with(this).load(jsonObject.getString("img")).into(imageviewVerification);
            }
            if (type == 3 && "0".equals(s)) {
                sendMSG();
            }
            if (type == 4 && "0".equals(s)) {
                TextUtils.toast(this, "发送成功！");
                utils.startCount();
            }
        } catch (Exception e) {
        }
    }

    @Override
    public void failCallBack(int type, String message, int httpCode) {
        try {
            if (type == 1) {
                mButtonNomal.setClickable(true);
                mButtonPhone.setClickable(true);
                mButtonNomal.setBackgroundResource(isNomalNull ? R.drawable.login_btn_shape_hl : R.drawable.login_btn_shape);
                mButtonPhone.setBackgroundResource(isPhoneNull ? R.drawable.login_btn_shape_hl : R.drawable.login_btn_shape);

            }
            if (httpCode == 412 && type == 3) {
                getVCode();
            }
            TextUtils.toast(this, new JSONObject(message).getString("message"));
        } catch (Exception e) {

        }
    }

    //edittext的内容变化监听：
    @Override
    public void onTextChanged(View v, String s) {
        if (linearlayoutNomal.getVisibility() == View.VISIBLE) {

            isNomalNull = TextUtils.notEmpty(edittextTel.getText().toString())
                    && TextUtils.notEmpty(edittextPass.getText().toString());
            mButtonNomal.setBackgroundResource(isNomalNull
                    ? R.drawable.login_btn_shape_hl
                    : R.drawable.login_btn_shape);
        } else {
            isPhoneNull = TextUtils.notEmpty(edittextTel1.getText().toString())
                    && TextUtils.notEmpty(edittextVerification1.getText().toString())
                    && TextUtils.notEmpty(edittextVerification2.getText().toString());
            mButtonPhone.setBackgroundResource(isPhoneNull
                    ? R.drawable.login_btn_shape_hl
                    : R.drawable.login_btn_shape);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 0 && resultCode == 1 && data != null) {
            String tel = data.getStringExtra("phone");
            String pass = data.getStringExtra("pass");
            edittextTel.setText(tel);
            edittextPass.setText(pass);
            edittextPass.setSelection(edittextPass.getText().length());
            edittextTel.setSelection(edittextTel.getText().length());
        }
    }


    public void setData(JSONObject jsonObject) {
        try {
            utils.setStop(true);

            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(mButtonNomal.getWindowToken(), 0);
            //记住密码：
            sharedUtils.setBooloean(this, "remember_password", true);
            sharedUtils.setData(this, "tel", edittextTel.getText().toString());
            sharedUtils.setData(this, "password", edittextPass.getText().toString());

            sharedUtils.setData(this, Constant.TOKEN, jsonObject.getString("dataToken"));
            sharedUtils.setData(this, Constant.USERID, jsonObject.getString("user_id"));
            sharedUtils.setBooloean(this, Constant.LOGINED, true);
            sharedUtils.setData(this, Constant.UUID, SystemUtils.getMyUUID(this));

        } catch (Exception e) {
        }
    }

    /**
     * 60s 倒计时
     *
     * @param activity
     * @param count
     */
    @Override
    public void count(Activity activity, String count) {
        LoginActivity act = (LoginActivity) activity;
        act.mtextviewSend.setText(count + "秒后重试");
        act.mtextviewSend.setClickable(false);
        if ("0".equals(count)) {
            act.mtextviewSend.setText("重新发送");
            act.mtextviewSend.setClickable(true);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        utils.setStop(true);
        mKeyboardHelper.disable();
        Glide.clear(imageviewVerification);
    }
}
