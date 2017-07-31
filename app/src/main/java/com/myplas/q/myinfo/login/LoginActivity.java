package com.myplas.q.myinfo.login;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.myplas.q.R;
import com.myplas.q.guide.activity.BaseActivity;
import com.myplas.q.common.netresquset.ResultCallBack;
import com.myplas.q.common.utils.SharedUtils;
import com.myplas.q.common.utils.SystemUtils;
import com.myplas.q.common.utils.TextUtils;
import com.myplas.q.common.api.API;
import com.myplas.q.guide.activity.MainActivity;
import com.myplas.q.myinfo.activity.setting.FindPSWActivity;
import com.umeng.analytics.MobclickAgent;

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
public class LoginActivity extends BaseActivity implements View.OnClickListener, ResultCallBack {
    private int count;
    private String key;
    private Handler mHandler;
    private SharedUtils sharedUtils;
    private boolean clicked, isRemember;
    private TextView textView_zhc, textView_wj, textView_title;
    private ImageView imageView, imageView3, imageView_verification;
    private Button button, button1, button_nomal, button_phone, button_send;
    private LinearLayout linearLayout_nomal, linearLayout_phone, linearLayout_remember;
    private EditText editText_tel, editText_tel1, editText_pass, editText_verification1, editText_verification2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_login_activity);
        goBack(findViewById(R.id.back));
        initView();
    }

    public void initView() {
        count = 60;
        clicked = false;
        sharedUtils = SharedUtils.getSharedUtils();
        isRemember = sharedUtils.getBoolean(this, "remember_password");

        editText_tel = (EditText) findViewById(R.id.dl_tel);
        editText_pass = (EditText) findViewById(R.id.dl_pass);
        editText_tel1 = (EditText) findViewById(R.id.dl_tel2);
        editText_verification1 = (EditText) findViewById(R.id.dl_verification1);
        editText_verification2 = (EditText) findViewById(R.id.dl_verification2);

        imageView = (ImageView) findViewById(R.id.img_jzh);
        imageView3 = (ImageView) findViewById(R.id.imageView3);
        imageView_verification = (ImageView) findViewById(R.id.login_phone_send1);

        button = (Button) findViewById(R.id.dl_ok);
        button1 = (Button) findViewById(R.id.dl_ok2);
        button_send = (Button) findViewById(R.id.zhc_hq_yzm);
        button_nomal = (Button) findViewById(R.id.btn_login_nomal);
        button_phone = (Button) findViewById(R.id.btn_login_phone);

        textView_wj = (TextView) findViewById(R.id.dl_wjmm);
        textView_zhc = (TextView) findViewById(R.id.dl_zhc);
        textView_title = (TextView) findViewById(R.id.title_rs);

        linearLayout_remember = (LinearLayout) findViewById(R.id.linear_jzhmm);
        linearLayout_nomal = (LinearLayout) findViewById(R.id.linearlayout_login_nomal);
        linearLayout_phone = (LinearLayout) findViewById(R.id.linearlayout_login_phone);

        button.setOnClickListener(this);
        button1.setOnClickListener(this);
        textView_wj.setOnClickListener(this);
        button_send.setOnClickListener(this);
        textView_zhc.setOnClickListener(this);
        button_nomal.setOnClickListener(this);
        button_phone.setOnClickListener(this);
        imageView_verification.setOnClickListener(this);
        linearLayout_remember.setOnClickListener(this);

        // 将账号和密码都设置到文本框中
        if (isRemember) {
            String account = sharedUtils.getData(this, "tel");
            String password = sharedUtils.getData(this, "password");
            editText_tel.setText(account);
            editText_pass.setText(password);
            imageView.setImageResource(R.drawable.btn_checkbox_hl);
            editText_pass.setSelection(editText_pass.getText().length());
            editText_tel.setSelection(editText_tel.getText().length());
        }
        mHandler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                if (msg.what == 1) {
                    button_send.setText(msg.obj.toString() + "秒后重试");
                    button_send.setClickable(false);
                    if (msg.obj.toString().equals("0")) {
                        button_send.setText("重新发送");
                        button_send.setClickable(true);
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
                } else {
                    TextUtils.Toast(this, "手机号码或验证码输入有误！");
                }
                break;
            case R.id.linear_jzhmm:
                if (clicked == false) {
                    clicked = true;
                    imageView.setImageResource(R.drawable.btn_checkbox_hl);
                } else {
                    clicked = false;
                    imageView.setImageResource(R.drawable.btn_checkbox_disabled);
                }
                break;
            case R.id.btn_login_nomal:
                textView_title.setText("普通登录");
                imageView3.setImageResource(R.drawable.btn_switch_common);
                linearLayout_phone.setVisibility(View.GONE);
                linearLayout_nomal.setVisibility(View.VISIBLE);
                break;
            case R.id.btn_login_phone:
                textView_title.setText("手机动态码登录");
                imageView3.setImageResource(R.drawable.switch_quick);
                linearLayout_phone.setVisibility(View.VISIBLE);
                linearLayout_nomal.setVisibility(View.GONE);
                break;
            case R.id.zhc_hq_yzm:
                checkVCide();
                break;
            case R.id.login_phone_send1:
                //获取验证码
                String url1 = API.BASEURL_API + API.VCODE;
                postAsyn(this, url1, null, this, 2);
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
            String s = new JSONObject(object.toString()).getString("err");
            if (type == 1) {
                String msg = new JSONObject(object.toString()).getString("msg");
                TextUtils.Toast(this, msg);
                if (s.equals("0")) {
                    setData(new JSONObject(object.toString()));
                    if (clicked) {//记住密码：
                        sharedUtils.setBooloean(this, "remember_password", true);
                        sharedUtils.setData(this, "tel", editText_tel.getText().toString());
                        sharedUtils.setData(this, "password", editText_pass.getText().toString());
                    } else {
                        sharedUtils.setBooloean(this, "remember_password", false);
                        sharedUtils.setData(this, "tel", "");
                        sharedUtils.setData(this, "password", "");
                    }
                    //回到主界面
                    finish();
                    MainActivity.firstInto();
                }
            }
            if (type == 2 && s.equals("0")) {
                JSONObject jsonObject = new JSONObject(object.toString());
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
        } catch (JSONException e) {
        }
    }

    @Override
    public void failCallBack(int type) {

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

    public void onResume() {
        super.onResume();
        MobclickAgent.onResume(this);
    }

    public void onPause() {
        super.onPause();
        MobclickAgent.onPause(this);
    }

    public void setData(JSONObject jsonObject) {
        try {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(button.getWindowToken(), 0);

            sharedUtils.setData(this, "token", jsonObject.getString("dataToken"));
            sharedUtils.setData(this, "userid", jsonObject.getString("user_id"));
            sharedUtils.setBooloean(this, "logined", true);
            sharedUtils.setBooloean(this, "isLogined_supdem", true);
            sharedUtils.setBooloean(this, "isLogined_headlines", true);
            sharedUtils.setData(this, "uuid", SystemUtils.getMyUUID(this));
        } catch (Exception e) {
        }
    }
}
