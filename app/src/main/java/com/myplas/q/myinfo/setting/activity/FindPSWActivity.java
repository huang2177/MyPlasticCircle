package com.myplas.q.myinfo.setting.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.myplas.q.R;
import com.myplas.q.common.api.API;
import com.myplas.q.common.netresquset.ResultCallBack;
import com.myplas.q.common.utils.TextUtils;
import com.myplas.q.guide.activity.BaseActivity;
import com.myplas.q.myinfo.login.LoginActivity;
import com.umeng.analytics.MobclickAgent;

import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Map;

/**
 * 编写： 黄双
 * 电话：15378412400
 * 邮箱：15378412400@163.com
 * 时间：2017/3/24 11:41
 */
public class FindPSWActivity extends BaseActivity implements View.OnClickListener, ResultCallBack {
    private int count = 60;
    private static Handler mHandler;

    private TextView mTextView;
    private Button button_next, button_yzm;
    private EditText editText_tel, editText_pass, editText_yzm;

    private WeakReference<Activity> weakReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_resetpass_activity);
        goBack(findViewById(R.id.back));
        initView();
    }

    public void initView() {
        mTextView = F(R.id.title_rs);
        editText_tel = F(R.id.zhh_tel);
        editText_yzm = F(R.id.zhh_yzm);
        button_next = F(R.id.zhh_next);
        button_yzm = F(R.id.zhh_hq_yzm);
        editText_pass = F(R.id.zhh_pass);

        button_yzm.setOnClickListener(this);
        button_next.setOnClickListener(this);

        weakReference = new WeakReference<Activity>(FindPSWActivity.this);

        mHandler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                Activity activity = weakReference.get();
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
        mTextView.setText(getIntent().getStringExtra("title"));
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
                if (TextUtils.isNullOrEmpty(phone) && TextUtils.isNullOrEmpty(pass) && TextUtils.isNullOrEmpty(yzm)) {
                    Map<String, Object> map = new HashMap<String, Object>();
                    map.put("mobile", phone);
                    map.put("password", pass);
                    map.put("code", yzm);

                    getNetData(API.FINF_MY_PWD, map, 2);
                } else {
                    TextUtils.Toast(this, "请输入完整信息！");
                }
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
            TextUtils.Toast(this, s);
            if (type == 1 && jsonObject.getString("err").equals("0")) {
                initThread();
            }
            if (type == 2) {
                TextUtils.Toast(this, s);
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
