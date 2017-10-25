package com.myplas.q.common.view;

import android.content.Context;
import android.support.v4.widget.NestedScrollView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * Created by kobe on 2017/10/7.
 */

public class MyNestedScrollView extends NestedScrollView {
    private onScrollIterface onScrollIterface;

    public MyNestedScrollView(Context context) {
        super(context);
    }

    public MyNestedScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyNestedScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        View view = getChildAt(getChildCount() - 1);
        int d = view.getBottom();
        d -= (getHeight() + getScrollY());
        if (d == 0 && onScrollIterface != null) {
            onScrollIterface.loadMore();
        } else {
            super.onScrollChanged(l, t, oldl, oldt);
        }

    }

    public interface onScrollIterface {
        void loadMore();
    }

    public void setOnScrollIterface(onScrollIterface onScrollIterface) {
        this.onScrollIterface = onScrollIterface;
    }
}
