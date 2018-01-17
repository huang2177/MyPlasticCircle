package com.myplas.q.myself.login;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.GlideDrawableImageViewTarget;
import com.myplas.q.R;
import com.myplas.q.common.appcontext.Constant;
import com.myplas.q.app.activity.MainActivity;
import com.myplas.q.myself.setting.activity.MyInfomationActivity;

import java.util.List;

/**
 * @author 黄双
 * @date 2017/11/1 0001
 */

public class FragmentRegister3 extends Fragment implements View.OnClickListener {
    private View mView;
    private ImageView mImgSuccess;
    private TextView mTVSuccess, mTVLevel, mTVInfo, mTVLook;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        mView = View.inflate(container.getContext(), R.layout.fragment_register3_layout, null);
        mTVInfo = (TextView) mView.findViewById(R.id.register_info);
        mTVLook = (TextView) mView.findViewById(R.id.register_look);
        mTVLevel = (TextView) mView.findViewById(R.id.register_level);
        mTVSuccess = (TextView) mView.findViewById(R.id.register_tv_success);
        mImgSuccess = (ImageView) mView.findViewById(R.id.register_img_success);

        mTVLook.setOnClickListener(this);
        mTVInfo.setOnClickListener(this);

        return mView;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.register_info:
                Intent intent = new Intent(getActivity(), MyInfomationActivity.class);
                intent.putExtra("from", "0");
                startActivity(intent);
                break;
            case R.id.register_look:
                Intent intent1 = new Intent(getActivity(), MainActivity.class);
                intent1.putExtra("type", Constant.LOGINED);
                startActivity(intent1);
                break;
            default:
                break;
        }
    }

    public void setData(List agrs) {
        if (agrs != null && agrs.size() != 0) {
            mTVLevel.setText("终于等到您，您是我们第" + agrs.get(0) + "名用户！");
        }
    }
    public void showImg() {
        if (mImgSuccess != null) {
            Glide.with(this)
                    .load(R.drawable.register_success)
                    .into(new GlideDrawableImageViewTarget(mImgSuccess, 1));
        }
    }
}
