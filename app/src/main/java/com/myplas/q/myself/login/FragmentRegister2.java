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

public class FragmentRegister2 extends Fragment implements View.OnClickListener, ResultCallBack {
    private View mView;
    private Button buttonNext;
    private MyEditText mName, mCompany, mCompanyType;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        mView = View.inflate(container.getContext(), R.layout.fragment_register2_layout, null);
        buttonNext = (Button) mView.findViewById(R.id.register_ok);
        mName = (MyEditText) mView.findViewById(R.id.register_name);
        mCompany = (MyEditText) mView.findViewById(R.id.register_company);
        mCompanyType = (MyEditText) mView.findViewById(R.id.register_type);

        buttonNext.setOnClickListener(this);
        mCompanyType.setOnClickListener(this);
        return mView;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.register_ok:
                getNetData();
                break;
            case R.id.register_type:
                getNetData();
                break;
            default:
                break;
        }
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

    @Override
    public void callBack(Object object, int type) {
        try {
            JSONObject jsonObject = new JSONObject(object.toString());
            if (type == 1) {
                String s = jsonObject.getString("msg");
                TextUtils.Toast(getActivity(), s);

            }
        } catch (Exception e) {
        }
    }

    @Override
    public void failCallBack(int type) {

    }
}
