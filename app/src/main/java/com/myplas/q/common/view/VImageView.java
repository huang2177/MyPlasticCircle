package com.myplas.q.common.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.RelativeLayout;

import com.myplas.q.R;

/**
 * 作者:huangshuang
 * 事件 2017/3/14.
 * 邮箱： 15378412400@163.com
 */
public class VImageView extends RelativeLayout {
    public VImageView(Context context) {
        super(context);
    }

    public VImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.vimageview_layout, this, true);
    }
}
