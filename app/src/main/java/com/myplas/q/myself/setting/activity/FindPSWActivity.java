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
import com.myplas.q.common.view.MyEditText;
import com.myplas.q.app.activity.BaseActivity;
import com.myplas.q.myself.login.LoginActivity;

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
        , MyEditText.OnTextWatcher {
    private int count = 60;
    private static Handler mHandler;

    private Button button_next;
    private TextView mTextView, mTextViewYZM;
    private MyEditText editText_tel, editText_pass, editText_yzm;

    private WeakReference<Activity> weakReference;

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
        editText_tel = F(R.id.zhh_tel);
        editText_yzm = F(R.id.zhh_yzm);
        button_next = F(R.id.zhh_next);
        mTextViewYZM = F(R.id.zhh_hq_yzm);
        editText_pass = F(R.id.zhh_pass);

        editText_tel.addOnTextWatcher(this);
        editText_yzm.addOnTextWatcher(this);
        button_next.setOnClickListener(this);
        editText_pass.addOnTextWatcher(this);
        mTextViewYZM.setOnClickListener(this);

        weakReference = new WeakReference<Activity>(FindPSWActivity.this);

        mHandler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                Activity activity = weakReference.get();
                if (msg.what == 1 && activity != null) {
                    mTextViewYZM.setText(msg.obj.toString() + "秒后重试");
                    mTextViewYZM.setClickable(false);
                    if (msg.obj.toString().equals("0")) {
                        mTextViewYZM.setText("重新发送");
                        mTextViewYZM.setClickable(true);
                    }
                }
            }
        };
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.zhh_hq_yzm:
                String tel = editText_tel.getText().toString();
                if (!TextUtils.isPhoneNum(tel)) {
                    Toast.makeText(this, "手机号输入有误！", Toast.LENGTH_SHORT).show();
                } else {
                    Map<String, Object> map1 = new HashMap<String, Object>();
                    map1.put("mobile", tel);
                    map1.put("type", "1");
                    //发送验证码
                    getNetData(API.SEND_MSG, map1, 1);
                }
                break;
            case R.id.zhh_next:
                String yzm = editText_yzm.getText().toString();
                String phone = editText_tel.getText().toString();
                String pass = editText_pass.getText().toString();
                if (TextUtils.notEmpty(phone) && TextUtils.notEmpty(pass) && TextUtils.notEmpty(yzm)) {
                    Map<String, Object> map = new HashMap<String, Object>();
                    map.put("mobile", phone);
                    map.put("password", pass);
                    map.put("code", yzm);

                    getNetData(API.FINF_MY_PWD, map, 2);
                } else {
                    TextUtils.toast(this, "请输入完整信息！");
                }
                break;
            default:
                break;
        }
    }

    public void getNetData(String method, Map map, int type) {
        String url = API.BASEURL + method;
        postAsyn(this, url, map, this, type);
    }

    public void initThread() {
        new Thread() {
            @Override
            public void run() {
                for (int i = count; i >= 0; i--) {
                    Message msg = Message.obtain();
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
    public void callBack(Object object, int type) {
        try {
            JSONObject jsonObject = new JSONObject(object.toString());
            String s = jsonObject.getString("msg");
            TextUtils.toast(this, s);
            if (type == 1 && jsonObject.getString("err").equals("0")) {
                initThread();
            }
            if (type == 2) {
                TextUtils.toast(this, s);
                if (s.equals("密码重置成功")) {
                    Intent intent = new Intent(this, LoginActivity.class);
                    String phone = editText_tel.getText().toString();
                    String pass = editText_pass.getText().toString();
                    intent.putExtra("phone", phone);
                    intent.putExtra("pass", pass);
                    intent.putExtra("isRegerter", "1");
                    setResult(1, intent);
                    finish();
                }
            }
        } catch (Exception e) {
        }
    }

    @Override
    public void failCallBack(int type) {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mHandler != null) {
            mHandler.removeCallbacksAndMessages(null);
        }
    }

    @Override
    public void onTextChanged(View v, String s) {
        boolean isNomalNull = TextUtils.notEmpty(editText_tel.getText().toString())
                && TextUtils.notEmpty(editText_pass.getText().toString())
                && TextUtils.notEmpty(editText_yzm.getText().toString());
        button_next.setBackgroundResource(isNomalNull
                ? R.drawable.login_btn_shape_hl
                : R.drawable.login_btn_shape);
    }
}
