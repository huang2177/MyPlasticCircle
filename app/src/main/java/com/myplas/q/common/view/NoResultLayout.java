package com.myplas.q.common.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.myplas.q.R;

/**
 * Created by Administrator on 2017/7/20 0020.
 */

public class NoResultLayout extends LinearLayout {
    View mView;
    TextView mTextView;
    ImageView mImageView;
    LinearLayout mLinearLayout;
    public NoResultLayout(Context context) {
        super(context);
        initView(context);
    }
    public NoResultLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public NoResultLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }
    public void initView(Context context){
        mView= LayoutInflater.from(context).inflate(R.layout.linearlayout_noresult,this,true);
        mTextView= (TextView) mView.findViewById(R.id.noresult_text);
        mImageView= (ImageView) mView.findViewById(R.id.noresult_image);
        mLinearLayout= (LinearLayout) mView.findViewById(R.id.layout_noresult);
    }
    public void setNoResultData(int resId,String text,boolean visibility){
        mLinearLayout.setVisibility((visibility)?(VISIBLE):(GONE));
        mImageView.setImageResource(resId);
        mTextView.setText(text);
    }
    public void setVisibility(boolean visibility){
        mLinearLayout.setVisibility((visibility)?(VISIBLE):(GONE));
    }
}
