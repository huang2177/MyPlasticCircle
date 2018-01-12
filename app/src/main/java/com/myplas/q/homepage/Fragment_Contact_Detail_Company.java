package com.myplas.q.homepage;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.myplas.q.R;
import com.myplas.q.common.view.MyNestedScrollView;

/**
 * 作者:huangshuang
 * 事件 2017/10/9 0009.
 * 邮箱： 15378412400@163.com
 */

public class Fragment_Contact_Detail_Company extends Fragment {

    private View mView;
    private TextView textView;
    private ImageView imageView;
    private MyNestedScrollView mScrollView;

    private Intent mIntent;
    private final String DEFAULTDES = "暂无描述~";


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mIntent = getActivity().getIntent();

        mView = View.inflate(getActivity(), R.layout.activity_layout_contact_detail_com, null);
        textView = (TextView) mView.findViewById(R.id.com_info);
        imageView = (ImageView) mView.findViewById(R.id.com_img);
        mScrollView = (MyNestedScrollView) mView.findViewById(R.id.scrollview);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return mView;
    }


    private void setListener(final boolean scrollabled) {
        mScrollView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return scrollabled;
            }
        });
    }

    public static Fragment_Contact_Detail_Company newInstance() {
        return new Fragment_Contact_Detail_Company();
    }

    @Override
    public void onResume() {
        super.onResume();
        if (getArguments() != null) {
            String info = getArguments().getString("info");
            if (!"".equals(info)) {
                textView.setText(info);
                setListener(false);
            } else {
                imageView.setVisibility(View.VISIBLE);
                textView.setText(DEFAULTDES);
                textView.setGravity(Gravity.CENTER);
                setListener(true);
            }
        }
    }

    public void setCompanyInfo(String com_intro) {
        if (getArguments() == null) {
            Bundle bundle = new Bundle();
            bundle.putString("info", com_intro);
            setArguments(bundle);
        }
    }
}
