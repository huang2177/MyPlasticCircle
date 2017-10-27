package com.myplas.q.myself.login;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.myplas.q.R;
import com.myplas.q.common.api.API;
import com.myplas.q.common.appcontext.ActivityManager;
import com.myplas.q.common.appcontext.Constant;
import com.myplas.q.common.netresquset.ResultCallBack;
import com.myplas.q.common.utils.ACache;
import com.myplas.q.common.utils.SharedUtils;
import com.myplas.q.common.utils.StatusUtils;
import com.myplas.q.common.utils.SystemUtils;
import com.myplas.q.common.utils.TextUtils;
import com.myplas.q.common.view.MyEditText;
import com.myplas.q.guide.activity.BaseActivity;
import com.myplas.q.guide.activity.MainActivity;
import com.myplas.q.myself.setting.activity.FindPSWActivity;
import com.myplas.q.sockethelper.DefConfigBean;

import org.json.JSONException;
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
        , MyEditText.OnTextWatcher {
    private int count;
    private String key;
    private Handler mHandler;
    private ScrollView mScrollView;
    private SharedUtils sharedUtils;
    private Button mButtonNomal, mButtonPhone;
    private boolean clicked, isRemember, isNomalNull, isPhoneNull;
    private ImageView imageView1, imageView2, imageView_verification;
    private TextView textView_zhc, textView_wj, textView_title, mTextView_send;
    private LinearLayout linearLayout_nomal, linearLayout_phone, button_nomal, button_phone, mLayoutRoot;
    private MyEditText editText_tel, editText_tel1, editText_pass, editText_verification1, editText_verification2;

    private ACache mACache;
    private MainActivity mainActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StatusUtils.setStatusBar(this, false, false);
        StatusUtils.setStatusTextColor(true, this);
        setContentView(R.layout.layout_login_activity);
        goBack(findViewById(R.id.back));
        initView();
    }

    public void initView() {
        count = 60;
        clicked = true;
        mACache = ACache.get(this);
        sharedUtils = SharedUtils.getSharedUtils();
        isRemember = sharedUtils.getBoolean(this, "remember_password");

        editText_tel = F(R.id.dl_tel);
        mScrollView = F(R.id.sv_login);
        editText_pass = F(R.id.dl_pass);
        editText_tel1 = F(R.id.dl_tel2);
        editText_verification1 = F(R.id.dl_verification1);
        editText_verification2 = F(R.id.dl_verification2);

        imageView1 = F(R.id.imageView1);
        imageView2 = F(R.id.imageView2);
        imageView_verification = F(R.id.login_phone_send1);

        mButtonNomal = F(R.id.dl_ok);
        mButtonPhone = F(R.id.dl_ok2);
        mTextView_send = F(R.id.zhc_hq_yzm);
        button_nomal = F(R.id.ll_login_nomal);
        button_phone = F(R.id.ll_login_phone);

        textView_wj = F(R.id.dl_wjmm);
        textView_zhc = F(R.id.dl_zhc);
        textView_title = F(R.id.title_rs);

        mLayoutRoot = F(R.id.login_rootview);
        linearLayout_nomal = F(R.id.linearlayout_login_nomal);
        linearLayout_phone = F(R.id.linearlayout_login_phone);

        mLayoutRoot.setOnClickListener(this);
        mButtonNomal.setOnClickListener(this);
        mButtonPhone.setOnClickListener(this);
        textView_wj.setOnClickListener(this);
        textView_zhc.setOnClickListener(this);
        button_nomal.setOnClickListener(this);
        button_phone.setOnClickListener(this);
        mTextView_send.setOnClickListener(this);
        imageView_verification.setOnClickListener(this);

        editText_tel.addOnTextWatcher(this);
        editText_pass.addOnTextWatcher(this);
        editText_tel1.addOnTextWatcher(this);
        editText_verification1.addOnTextWatcher(this);
        editText_verification2.addOnTextWatcher(this);

        // 将账号和密码都设置到文本框中
        if (isRemember) {
            String account = sharedUtils.getData(this, "tel");
            String password = sharedUtils.getData(this, "password");
            editText_tel.setText(account);
            editText_pass.setText(password);
            mButtonNomal.setBackgroundResource(R.drawable.login_btn_shape_hl);
            editText_pass.setSelection(editText_pass.getText().length());
            editText_tel.setSelection(editText_tel.getText().length());
        }

        mHandler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                if (msg.what == 1) {
                    mTextView_send.setText(msg.obj.toString() + "秒后重试");
                    mTextView_send.setClickable(false);
                    if (msg.obj.toString().equals("0")) {
                        mTextView_send.setText("重新发送");
                        mTextView_send.setClickable(true);
                    }
                }
            }
        };
        //获取验证码
        String url = API.BASEURL_API + API.VCODE;
        postAsyn(this, url, null, this, 2);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.dl_zhc:
                Intent intent = new Intent(this, RegesterActivity.class);
                startActivityForResult(intent, 0);
                break;
            case R.id.dl_wjmm:
                Intent intent1 = new Intent(this, FindPSWActivity.class);
                intent1.putExtra("title", "找回密码");
                startActivityForResult(intent1, 0);
                break;
            //登陆；
            case R.id.dl_ok:
                if (TextUtils.isPhoneNum(editText_tel.getText().toString()) && TextUtils.isNullOrEmpty(editText_pass.getText().toString())) {
                    Map<String, String> map = new HashMap<String, String>();
                    map.put("username", editText_tel.getText().toString());
                    map.put("password", editText_pass.getText().toString());
                    map.put("chanel", "6");
                    postAsyn(this, API.BASEURL + API.LOGIN, map, this, 1);
                    mButtonNomal.setClickable(false);
                    mButtonNomal.setBackgroundResource(R.drawable.login_btn_shape);
                } else {
                    TextUtils.Toast(this, "手机号码或密码输入有误！");
                }
                break;
            //手机动态码登陆
            case R.id.dl_ok2:
                if (TextUtils.isPhoneNum(editText_tel1.getText().toString())
                        && TextUtils.isNullOrEmpty(editText_verification1.getText().toString())
                        && TextUtils.isNullOrEmpty(editText_verification2.getText().toString())) {
                    Map<String, String> map = new HashMap<String, String>();
                    map.put("phonenum", editText_tel1.getText().toString());
                    map.put("phonevaild", editText_verification2.getText().toString());
                    map.put("regcode", editText_verification1.getText().toString());
                    map.put("key", key);
                    //登陆；
                    postAsyn(this, API.BASEURL + API.SIMPLE_LOGIN, map, this, 1);
                    mButtonPhone.setClickable(false);
                    mButtonPhone.setBackgroundResource(R.drawable.login_btn_shape);
                } else {
                    TextUtils.Toast(this, "手机号码或验证码输入有误！");
                }
                break;

            case R.id.ll_login_nomal:
                textView_title.setText("普通登录");
                imageView2.setVisibility(View.GONE);
                imageView1.setVisibility(View.VISIBLE);
                textView_wj.setVisibility(View.VISIBLE);
                linearLayout_phone.setVisibility(View.GONE);
                linearLayout_nomal.setVisibility(View.VISIBLE);
                break;
            case R.id.ll_login_phone:
                textView_title.setText("手机动态码登录");
                imageView1.setVisibility(View.GONE);
                textView_wj.setVisibility(View.GONE);
                imageView2.setVisibility(View.VISIBLE);
                linearLayout_nomal.setVisibility(View.GONE);
                linearLayout_phone.setVisibility(View.VISIBLE);
                break;
            case R.id.zhc_hq_yzm:
                checkVCide();
                break;
            case R.id.login_phone_send1:
                //获取验证码
                String url1 = API.BASEURL_API + API.VCODE;
                postAsyn(this, url1, null, this, 2);
                break;
            case R.id.login_rootview:
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(mButtonNomal.getWindowToken(), 0);
                break;
            default:
                break;
        }
    }

    public void checkVCide() {
        //发送动态码之前先验证
        String value = editText_verification1.getText().toString();
        if (TextUtils.isNullOrEmpty(value)) {
            String url = API.BASEURL_API + API.CHK_VCODE;
            Map<String, String> map1 = new HashMap<String, String>();
            map1.put("name", "regcode");
            map1.put("value", value);
            map1.put("key", key);
            postAsyn(this, url, map1, this, 3);
        } else {
            TextUtils.Toast(this, "验证码不能为空！");
        }
    }

    public void sendMSG() {
        String tel = editText_tel1.getText().toString();
        if (!TextUtils.isPhoneNum(tel)) {
            Toast.makeText(this, "手机号输入有误！", Toast.LENGTH_SHORT).show();
        } else {
            //发送验证码
            String url = API.BASEURL_API + API.SEND_MOBILE_MSG;
            Map<String, String> map1 = new HashMap<String, String>();
            map1.put("phonenum", tel);
            map1.put("from", "android");
            postAsyn(this, url, map1, this, 4);
        }
    }


    @Override
    public void callBack(Object object, int type) {
        try {
            JSONObject jsonObject = new JSONObject(object.toString());
            String s = jsonObject.getString("err");
            if (type == 1) {
                mButtonNomal.setClickable(true);
                mButtonPhone.setClickable(true);
                mButtonNomal.setBackgroundResource(isNomalNull
                        ? R.drawable.login_btn_shape_hl
                        : R.drawable.login_btn_shape);
                mButtonPhone.setBackgroundResource(isPhoneNull
                        ? R.drawable.login_btn_shape_hl
                        : R.drawable.login_btn_shape);

                TextUtils.Toast(this, new JSONObject(object.toString()).getString("msg"));
                if (s.equals("0")) {
                    setData(jsonObject);
                    Gson gson = new Gson();
                    if (jsonObject.getString("redDot").length() != 0) {
                        DefConfigBean.RedDotBean bean = gson.fromJson(jsonObject.getString("redDot"), DefConfigBean.RedDotBean.class);
                        mACache.put(Constant.R_MYMSG, bean.getUnread_mymsg());
                        mACache.put(Constant.R_MYORDER, bean.getUnread_myorder());
                        mACache.put(Constant.R_CONTACT, bean.getUnread_customer());
                        mACache.put(Constant.R_SEEME, bean.getUnread_who_saw_me());
                        mACache.put(Constant.R_SUPDEM, bean.getUnread_supply_and_demand());
                    }
                    //回到主界面
                    mainActivity = (MainActivity) ActivityManager.getActivity(MainActivity.class);
                    mainActivity.firstInto();
                    mainActivity.onConnect();
                    mainActivity.rCallback(true);

                    finish();
                }
            }
            if (type == 2 && s.equals("0")) {
                String url = jsonObject.getString("img");
                key = jsonObject.getString("key");
                Glide.with(this).load(url).placeholder(R.drawable.bg_verification_code).into(imageView_verification);
            }
            if (type == 3 && s.equals("0")) {
                sendMSG();
            } else if (type == 3 && !s.equals("0")) {
                String msg = new JSONObject(object.toString()).getString("msg");
                TextUtils.Toast(this, msg);
            }
            if (type == 4 && s.equals("0")) {
                TextUtils.Toast(this, "发送成功！");
                initThread();
            } else if (type == 4 && !s.equals("0")) {
                String msg = new JSONObject(object.toString()).getString("msg");
                TextUtils.Toast(this, msg);
            }
        } catch (Exception e) {
        }
    }

    @Override
    public void failCallBack(int type) {
        if (type == 1) {
            mButtonNomal.setClickable(true);
            mButtonPhone.setClickable(true);
            mButtonNomal.setBackgroundResource(isNomalNull ? R.drawable.login_btn_shape_hl : R.drawable.login_btn_shape);
            mButtonPhone.setBackgroundResource(isPhoneNull ? R.drawable.login_btn_shape_hl : R.drawable.login_btn_shape);
        }
    }

    //edittext的内容变化监听：
    @Override
    public void onTextChanged(View v, String s) {
        if (linearLayout_nomal.getVisibility() == View.VISIBLE) {

            isNomalNull = TextUtils.isNullOrEmpty(editText_tel.getText().toString())
                    && TextUtils.isNullOrEmpty(editText_pass.getText().toString());
            mButtonNomal.setBackgroundResource(isNomalNull
                    ? R.drawable.login_btn_shape_hl
                    : R.drawable.login_btn_shape);
        } else {
            isPhoneNull = TextUtils.isNullOrEmpty(editText_tel1.getText().toString())
                    && TextUtils.isNullOrEmpty(editText_verification1.getText().toString())
                    && TextUtils.isNullOrEmpty(editText_verification1.getText().toString());
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
            editText_tel.setText(tel);
            editText_pass.setText(pass);
            editText_pass.setSelection(editText_pass.getText().length());
            editText_tel.setSelection(editText_tel.getText().length());
        }
    }

    public void initThread() {
        new Thread() {
            @Override
            public void run() {
                for (int i = count; i >= 0; i--) {
                    Message msg = new Message();
                    msg.what = 1;
                    msg.obj = i;
                    mHandler.sendMessage(msg);
                    try {
                        sleep(1000);
                    } catch (Exception e) {
                    }
                }
            }
        }.start();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        Glide.clear(imageView_verification);
    }

    public void setData(JSONObject jsonObject) {
        try {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(mButtonNomal.getWindowToken(), 0);
            //记住密码：
            sharedUtils.setBooloean(this, "remember_password", true);
            sharedUtils.setData(this, "tel", editText_tel.getText().toString());
            sharedUtils.setData(this, "password", editText_pass.getText().toString());

            sharedUtils.setData(this, Constant.TOKEN, jsonObject.getString("dataToken"));
            sharedUtils.setData(this, Constant.USERID, jsonObject.getString("user_id"));
            sharedUtils.setBooloean(this, Constant.LOGINED, true);
            sharedUtils.setBooloean(this, Constant.IS_LOGINED_H, true);
            sharedUtils.setBooloean(this, Constant.IS_LOGINED_SD, true);
            sharedUtils.setData(this, Constant.UUID, SystemUtils.getMyUUID(this));
        } catch (Exception e) {
        }
    }

}
