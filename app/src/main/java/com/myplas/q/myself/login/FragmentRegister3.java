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

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.GlideDrawableImageViewTarget;
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

public class FragmentRegister3 extends Fragment implements View.OnClickListener, ResultCallBack {
    private View mView;
    private ImageView mImgSuccess;
    private TextView mTVSuccess, mTVLevel, mTVInfo, mTVLook;


    @SuppressLint("HandlerLeak")
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        mView = View.inflate(container.getContext(), R.layout.fragment_register3_layout, null);
        mTVInfo = (TextView) mView.findViewById(R.id.register_info);
        mTVLook = (TextView) mView.findViewById(R.id.register_look);
        mTVLevel = (TextView) mView.findViewById(R.id.register_level);
        mTVSuccess = (TextView) mView.findViewById(R.id.register_tv_success);
        mImgSuccess = (ImageView) mView.findViewById(R.id.register_img_success);

        Glide.with(this)
                .load(R.drawable.register_success)
                .into(new GlideDrawableImageViewTarget(mImgSuccess, 1));
        return mView;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.register_info:
                break;
            case R.id.register_look:
                break;
            default:
                break;
        }
    }

    //获取验证码
    private void getIndentify() {
//        Map<String, String> map1 = new HashMap<String, String>();
//        map1.put("mobile", tel);
//        map1.put("type", "0");
//        String url = API.BASEURL + API.SEND_MSG;
//        BaseActivity.postAsyn(getActivity(), url, map1, this, 1);
    }


    @Override
    public void callBack(Object object, int type) {
        try {
            JSONObject jsonObject = new JSONObject(object.toString());
            if (type == 1) {
                String s = jsonObject.getString("msg");
                TextUtils.Toast(getActivity(), s);
                mTVLevel.setText(Html.fromHtml("已阅读<font color='#0099cc'>" + "《塑料圈通讯录协议</font>"));
            }
        } catch (Exception e) {
        }
    }

    @Override
    public void failCallBack(int type) {

    }
}
