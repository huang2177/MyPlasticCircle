package com.myplas.q.myself.login;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.myplas.q.R;
import com.myplas.q.common.api.API;
import com.myplas.q.common.netresquset.ResultCallBack;
import com.myplas.q.common.utils.TextUtils;
import com.myplas.q.common.view.MyEditText;
import com.myplas.q.guide.activity.BaseActivity;

import org.json.JSONObject;

import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Map;

/**
 * @author 黄双
 * @date 2017/11/1 0001
 */

public class FragmentRegister1 extends Fragment implements View.OnClickListener, ResultCallBack {
    private View mView;
    private Button buttonNext;
    private ImageView mImgRead;
    private LinearLayout mLayoutRead;
    private TextView mTVIndentify, mTVRead;
    private MyEditText mPhone, mPassWord, mIndentify;

    private int count;
    private boolean checked;
    private Handler mHandler;
    private WeakReference<Activity> wr;

    @SuppressLint("HandlerLeak")
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        count = 60;
        wr = new WeakReference<Activity>(getActivity());
        mHandler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                Activity activity = wr.get();
                if (msg.what == 1 && activity != null) {
                    mTVIndentify.setText(msg.obj.toString() + "秒后重试");
                    mTVIndentify.setClickable(false);
                    if ("0".equals(msg.obj.toString())) {
                        mTVIndentify.setText("重新发送");
                        mTVIndentify.setClickable(true);
                    }
                }
            }
        };
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        mView = View.inflate(container.getContext(), R.layout.fragment_register1_layout, null);
        buttonNext = (Button) mView.findViewById(R.id.register_ok);
        mPhone = (MyEditText) mView.findViewById(R.id.register_tel);
        mImgRead = (ImageView) mView.findViewById(R.id.register_img);
        mTVRead = (TextView) mView.findViewById(R.id.register_tv_read);
        mPassWord = (MyEditText) mView.findViewById(R.id.register_pass);
        mLayoutRead = (LinearLayout) mView.findViewById(R.id.register_read);
        mIndentify = (MyEditText) mView.findViewById(R.id.register_identify);
        mTVIndentify = (TextView) mView.findViewById(R.id.register_tv_indentify);

        mTVRead.setText(Html.fromHtml("已阅读<font color='#0099cc'>" + "《塑料圈通讯录协议》</font>"));

        mTVRead.setOnClickListener(this);
        buttonNext.setOnClickListener(this);
        mLayoutRead.setOnClickListener(this);
        mTVIndentify.setOnClickListener(this);
        return mView;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.register_tv_indentify:
                getIndentify();
                break;
            case R.id.register_ok:
                getNetData();
                break;
            case R.id.register_read:
                mImgRead.setImageResource(checked
                        ? R.drawable.btn_checkbox_hl
                        : R.drawable.btn_checkbox1);
                checked = !checked;
                break;
            case R.id.register_tv_read:
                startActivity(new Intent(getActivity(), RegisterAgreementActivity.class));
                break;
            default:
                break;
        }
    }

    //获取验证码
    private void getIndentify() {
        String tel = mPhone.getText().toString();
        if (!TextUtils.isPhoneNum(tel)) {
            TextUtils.Toast(getActivity(), "手机号输入有误！");
            return;
        }
        Map<String, String> map1 = new HashMap<String, String>();
        map1.put("mobile", tel);
        map1.put("type", "0");
        String url = API.BASEURL + API.SEND_MSG;
        BaseActivity.postAsyn(getActivity(), url, map1, this, 1);
    }

    //点击下一步
    public void getNetData() {
//        String phone = editText_tel.getText().toString();
//        String pass = editText_pass.getText().toString();
//        String yzm = editText_yzm.getText().toString();
//        String name = editText_name.getText().toString();
//        String company = editText_company.getText().toString();
//
//        if (TextUtils.isNullOrEmpty(phone) && TextUtils.isNullOrEmpty(pass) && TextUtils.isNullOrEmpty(yzm)
//                && TextUtils.isNullOrEmpty(name) && TextUtils.isNullOrEmpty(company)) {
//            if (clicked) {
//                Map<String, String> map = new HashMap<String, String>();
//                map.put("mobile", phone);
//                map.put("password", pass);
//                map.put("code", yzm);
//                map.put("qq", "");
//                map.put("parent_mobile", "");
//                map.put("name", name);
//                map.put("c_name", company);
//                map.put("chanel", "6");
//                map.put("quan_type", "1");
//                map.put("c_type", "1");
//                //注册：
//                getNetData(API.REGISTER, map, 2);
//            } else {
//                TextUtils.Toast(getActivity(), "请您先阅读《塑料圈通讯录》相关协议！");
//            }
//        } else {
//            TextUtils.Toast(getActivity(), "请输入完整信息！");
//        }
//        String url = API.BASEURL + method;
//        BaseActivity.postAsyn(getActivity(), url, map, this, 2);
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
    public void callBack(Object object, int type) {
        try {
            JSONObject jsonObject = new JSONObject(object.toString());
            if (type == 1) {
                String s = jsonObject.getString("msg");
                TextUtils.Toast(getActivity(), s);
                initThread();
            }
            if (type == 2) {
                String s = jsonObject.getString("err");
                TextUtils.Toast(getActivity(), jsonObject.getString("msg"));
                if (s.equals("0")) {
//                    InputMethodManager imm = (InputMethodManager)getActivity(). getSystemService(Context.INPUT_METHOD_SERVICE);
//                    imm.hideSoftInputFromWindow(mPhone.getWindowToken(), 0);
//                    //跳转到登陆界面
//                    Intent intent = new Intent(this, LoginActivity.class);
//                    String phone = editText_tel.getText().toString();
//                    String pass = editText_pass.getText().toString();
//                    intent.putExtra("phone", phone);
//                    intent.putExtra("pass", pass);
//                    intent.putExtra("isRegerter", "0");
//                    setResult(1, intent);
//                    finish();
                }
            }
        } catch (Exception e) {
        }
    }

    @Override
    public void failCallBack(int type) {

    }
}
