package com.myplas.q.myinfo.login;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.Editable;
import android.text.Html;
import android.text.TextWatcher;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.myplas.q.R;
import com.myplas.q.guide.activity.BaseActivity;
import com.myplas.q.common.netresquset.ResultCallBack;
import com.myplas.q.common.utils.SharedUtils;
import com.myplas.q.common.utils.TextUtils;
import com.myplas.q.common.api.API;
import com.umeng.analytics.MobclickAgent;

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
public class RegesterActivity extends BaseActivity implements View.OnClickListener, ResultCallBack {
    private int count = 60;
    private static Handler mHandler;
    private boolean clicked = true;
    private SharedUtils sharedUtils;
    private String string_radiogroup = "1";

    private TextView textView_xy;
    private ImageView img, mIVRead;
    private Button button_next, button_yzm;
    private EditText editText_name, editText_company;
    private EditText editText_tel, editText_pass, editText_yzm;
    private RadioButton radioButton_sl, radioButton_wl, radioButton_yl;

    private WeakReference<Activity> wr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_regester_activity);
        goBack(findViewById(R.id.back));
        initView();
    }

    public void initView() {
        wr = new WeakReference<Activity>(this);
        sharedUtils = SharedUtils.getSharedUtils();

        img = F(R.id.img_jzh);
        mIVRead = F(R.id.img_jzh);
        textView_xy = F(R.id.xy_text);
        editText_tel = F(R.id.zhc_tel);
        editText_yzm = F(R.id.zhc_yzm);
        button_next = F(R.id.zhc_next);
        button_yzm = F(R.id.zhc_hq_yzm);
        editText_name = F(R.id.zhc_name);
        editText_pass = F(R.id.zhc_pass);
        editText_company = F(R.id.zhc_company);
        radioButton_wl = F(R.id.radio_address_c_t);
        radioButton_yl = F(R.id.radio_address_trade);
        radioButton_sl = F(R.id.radio_company_fatory);

        mIVRead.setOnClickListener(this);
        button_yzm.setOnClickListener(this);
        textView_xy.setOnClickListener(this);
        button_next.setOnClickListener(this);
        radioButton_wl.setOnClickListener(this);
        radioButton_yl.setOnClickListener(this);
        radioButton_sl.setOnClickListener(this);

        String html1 = "已阅读" + "<font color='#0099cc'>" + "《塑料圈通讯录协议》" + "</font>";
        textView_xy.setText(Html.fromHtml(html1));

        mHandler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                Activity activity = wr.get();
                if (msg.what == 1 && activity != null) {
                    button_yzm.setText(msg.obj.toString() + "秒后重试");
                    button_yzm.setClickable(false);
                    if (msg.obj.toString().equals("0")) {
                        button_yzm.setText("重新发送");
                        button_yzm.setClickable(true);
                    }
                }
            }
        };
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.radio_company_fatory:
                string_radiogroup = "1";
                radioButton_yl.setChecked(false);
                radioButton_wl.setChecked(false);
                break;
            case R.id.radio_address_trade:
                string_radiogroup = "2";
                radioButton_sl.setChecked(false);
                radioButton_wl.setChecked(false);
                break;
            case R.id.radio_address_c_t:
                string_radiogroup = "4";
                radioButton_yl.setChecked(false);
                radioButton_sl.setChecked(false);
                break;
            case R.id.zhc_hq_yzm:
                String tel = editText_tel.getText().toString();
                if (!TextUtils.isPhoneNum(tel)) {
                    Toast.makeText(this, "手机号输入有误！", Toast.LENGTH_SHORT).show();
                    button_yzm.setClickable(false);
                } else {
                    //发送验证码
                    Map<String, String> map1 = new HashMap<String, String>();
                    map1.put("mobile", tel);
                    map1.put("type", "0");
                    String url = API.BASEURL + API.SEND_MSG;
                    postAsyn(this, url, map1, this, 1);
                    button_yzm.setClickable(true);
                }
                break;
            case R.id.zhc_next:
                String phone = editText_tel.getText().toString();
                String pass = editText_pass.getText().toString();
                String yzm = editText_yzm.getText().toString();
                String name = editText_name.getText().toString();
                String company = editText_company.getText().toString();

                if (TextUtils.isNullOrEmpty(phone) && TextUtils.isNullOrEmpty(pass) && TextUtils.isNullOrEmpty(yzm)
                        && TextUtils.isNullOrEmpty(name) && TextUtils.isNullOrEmpty(company)) {
                    if (clicked) {
                        Map<String, String> map = new HashMap<String, String>();
                        map.put("mobile", phone);
                        map.put("password", pass);
                        map.put("code", yzm);
                        map.put("qq", "");
                        map.put("parent_mobile", "");
                        map.put("name", name);
                        map.put("c_name", company);
                        map.put("chanel", "6");
                        map.put("quan_type", "1");
                        map.put("c_type", "1");
                        //注册：
                        getNetData(API.REGISTER, map, 2);
                    } else {
                        TextUtils.Toast(this, "请您先阅读《塑料圈通讯录》！");
                    }
                } else {
                    TextUtils.Toast(this, "请输入完整信息！");
                }
                break;
            case R.id.img_jzh:
                if (clicked == false) {
                    clicked = true;
                    img.setImageResource(R.drawable.btn_checkbox_hl);
                } else {
                    clicked = false;
                    img.setImageResource(R.drawable.btn_checkbox1);
                }
                break;
            case R.id.xy_text:
                startActivity(new Intent(this, RegesterXYActivity.class));
                break;
        }
    }

    public void getNetData(String method, Map<String, String> map, int type) {
        String url = API.BASEURL + method;
        postAsyn(this, url, map, this, type);
    }

    @Override
    public void callBack(Object object, int type) {
        try {
            JSONObject jsonObject = new JSONObject(object.toString());
            if (type == 1) {
                String s = jsonObject.getString("msg");
                TextUtils.Toast(this, s);
                initThread();
            }
            if (type == 2) {
                String s = jsonObject.getString("err");
                TextUtils.Toast(this, jsonObject.getString("msg"));
                if (s.equals("0")) {
                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(editText_yzm.getWindowToken(), 0);
                    //跳转到登陆界面
                    Intent intent = new Intent(this, LoginActivity.class);
                    String phone = editText_tel.getText().toString();
                    String pass = editText_pass.getText().toString();
                    intent.putExtra("phone", phone);
                    intent.putExtra("pass", pass);
                    intent.putExtra("isRegerter", "0");
                    setResult(1, intent);
                    finish();
                } else {
                    TextUtils.Toast(this, jsonObject.getString("msg"));
                }
            }
        } catch (Exception e) {
        }
    }

    @Override
    public void failCallBack(int type) {

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

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mHandler != null) {
            mHandler.removeCallbacksAndMessages(null);
        }
    }
}
