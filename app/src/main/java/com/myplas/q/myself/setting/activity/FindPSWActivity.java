package com.myplas.q.myself.setting.activity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.myplas.q.R;
import com.myplas.q.common.api.API;
import com.myplas.q.common.netresquset.ResultCallBack;
import com.myplas.q.common.utils.StatusUtils;
import com.myplas.q.common.utils.TextUtils;
import com.myplas.q.common.utils.VerifyCodeUtils;
import com.myplas.q.common.view.MyEditText;
import com.myplas.q.app.activity.BaseActivity;
import com.myplas.q.myself.login.LoginActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Map;

/**
 * 编写： 黄双
 * 电话：15378412400
 * 邮箱：15378412400@163.com
 * 时间：2017/3/24 11:41
 */
public class FindPSWActivity extends BaseActivity implements View.OnClickListener
        , ResultCallBack
        , MyEditText.OnTextWatcher
        , VerifyCodeUtils.CountListener {

    private Button buttonNext;
    private TextView mTextView, mTextViewYZM;
    private MyEditText edittextTel, edittextPass, edittextYzm;

    private VerifyCodeUtils utils;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StatusUtils.setStatusBar(this, false, false);
        StatusUtils.setStatusTextColor(true, this);
        setContentView(R.layout.layout_resetpass_activity);

        initTileBar();
        setLeftIVResId(R.drawable.btn_back_black);
        setTitleBarTextColor(R.color.color_transparent);
        setTitleBarBackground(R.color.color_white);
        setTitle(getIntent().getStringExtra("title"));

        initView();
    }

    @SuppressLint("HandlerLeak")
    public void initView() {
        mTextView = F(R.id.title_rs);
        edittextTel = F(R.id.zhh_tel);
        edittextYzm = F(R.id.zhh_yzm);
        buttonNext = F(R.id.zhh_next);
        mTextViewYZM = F(R.id.zhh_hq_yzm);
        edittextPass = F(R.id.zhh_pass);

        edittextTel.addOnTextWatcher(this);
        edittextYzm.addOnTextWatcher(this);
        buttonNext.setOnClickListener(this);
        edittextPass.addOnTextWatcher(this);
        mTextViewYZM.setOnClickListener(this);

        utils = new VerifyCodeUtils(this, this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.zhh_hq_yzm:
                String tel = edittextTel.getText().toString();
                if (tel.length() != 11) {
                    Toast.makeText(this, "手机号输入有误！", Toast.LENGTH_SHORT).show();
                } else {
                    Map<String, String> map = new HashMap<String, String>(8);
                    map.put("mobile", tel);
                    map.put("type", "1");
                    //发送验证码
                    postAsyn(this, API.SEND_MSG, map, this, 1);
                }
                break;
            case R.id.zhh_next:
                String yzm = edittextYzm.getText().toString();
                String phone = edittextTel.getText().toString();
                String pass = edittextPass.getText().toString();
                if (TextUtils.notEmpty(phone) && TextUtils.notEmpty(pass) && TextUtils.notEmpty(yzm)) {
                    Map<String, String> map = new HashMap<String, String>(8);
                    map.put("mobile", phone);
                    map.put("password", pass);
                    map.put("code", yzm);

                    postAsyn(this, API.FINF_MY_PWD, map, this, 2);
                } else {
                    TextUtils.toast(this, "请输入完整信息！");
                }
                break;
            default:
                break;
        }
    }

    @Override
    public void callBack(Object object, int type) {
        try {
            JSONObject jsonObject = new JSONObject(object.toString());
            String s = jsonObject.getString("message");
            TextUtils.toast(this, s);
            if (type == 1 && "0".equals(jsonObject.getString("code"))) {
                utils.startCount();
            }
            if (type == 2 && "0".equals(jsonObject.getString("code"))) {
                utils.setStop(true);

                Intent intent = new Intent(this, LoginActivity.class);
                String phone = edittextTel.getText().toString();
                String pass = edittextPass.getText().toString();
                intent.putExtra("phone", phone);
                intent.putExtra("pass", pass);
                intent.putExtra("isRegerter", "1");
                setResult(1, intent);
                finish();
            }
        } catch (Exception e) {
        }
    }

    @Override
    public void failCallBack(int type, String message, int httpCode) {
        try {
            if (httpCode == 412) {
                TextUtils.toast(this, new JSONObject(message).getString("message"));
            }
        } catch (Exception e) {

        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        utils.setStop(true);
    }

    @Override
    public void onTextChanged(View v, String s) {
        boolean isNomalNull = TextUtils.notEmpty(edittextTel.getText().toString())
                && TextUtils.notEmpty(edittextPass.getText().toString())
                && TextUtils.notEmpty(edittextYzm.getText().toString());
        buttonNext.setBackgroundResource(isNomalNull
                ? R.drawable.login_btn_shape_hl
                : R.drawable.login_btn_shape);
    }

    @Override
    public void count(Activity activity, String count) {
        FindPSWActivity act = (FindPSWActivity) activity;
        act.mTextViewYZM.setText(count + "秒后重试");
        act.mTextViewYZM.setClickable(false);
        if ("0".equals(count)) {
            act.mTextViewYZM.setText("重新发送");
            act.mTextViewYZM.setClickable(true);
        }
    }
}
