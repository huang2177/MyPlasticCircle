package com.myplas.q.common.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.RelativeLayout;

import com.myplas.q.R;

/**
 * Created by 15378 on 2017/3/14.
 */
public class XCRoundRectRelativeLayout extends RelativeLayout {
    public XCRoundRectRelativeLayout(Context context) {
        super(context);
    }
    public XCRoundRectRelativeLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.layout_tx,this,true);
    }
}
