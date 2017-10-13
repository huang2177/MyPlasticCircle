package com.myplas.q.common.view;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.os.Handler;
import android.support.v7.app.ActionBar;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

import com.myplas.q.R;
import com.myplas.q.common.utils.TextUtils;
import com.myplas.q.guide.activity.BaseActivity;

/**
 * 作者:huangshuang
 * 事件 2017/10/11 0011.
 * 邮箱： 15378412400@163.com
 */

public class RefreshPopou implements View.OnTouchListener {
    private Handler mHandler;
    private boolean canShowPopou;
    private StringBuffer defautContent;

    private View mDismissView;
    private Activity mContext;
    private TextView tvRefresh;
    private CustomPopupWindow mPopupWindow;

    public RefreshPopou(Activity content, int type) {
        this.mContext = content;
        mHandler = new Handler();
        if (type == 1) {
            defautContent = new StringBuffer("已是最新头条信息！");
        } else if (type == 2) {
            defautContent = new StringBuffer("已是最新通讯录信息！");
        } else {
            defautContent = new StringBuffer("已是最新供求信息！");
        }
    }

    public void show(View locationView, String content) {
        if (canShowPopou) {
            if (mPopupWindow == null) {
                View view = View.inflate(mContext, R.layout.layout_refresh_popou, null);
                mDismissView = view.findViewById(R.id.refresh_view);
                tvRefresh = (TextView) view.findViewById(R.id.text_refresh_fragement);
                mPopupWindow = new CustomPopupWindow(view, ActionBar.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.MATCH_PARENT);
                mPopupWindow.setAnimationStyle(R.style.my_anim_popou);
                mPopupWindow.setOutsideTouchable(true);
                mPopupWindow.setFocusable(true);
            }
            mDismissView.setOnTouchListener(this);
            tvRefresh.setText((TextUtils.isNullOrEmpty(content)) ? (content) : (defautContent));
            showRefreshPopou(locationView);
            if (mPopupWindow.isShowing()) {
                mHandler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mPopupWindow.dismiss();
                    }
                }, 1800);
            }
        }

    }

    public void dismiss() {
        if (mPopupWindow != null && mPopupWindow.isShowing()) {
            mPopupWindow.dismiss();
        }
    }

    private void showRefreshPopou(View v) {
//        if (android.os.Build.VERSION.SDK_INT >= 24) {
//            int[] a = new int[2];
//            v.getLocationInWindow(a);
//            mPopupWindow.showAtLocation(mContext.getWindow().getDecorView()
//                    , a[1] + v.getHeight()
//                    , Gravity.NO_GRAVITY
//                    , 0);
//        } else {
        mPopupWindow.showAsDropDown(v);
//        }
    }

    public void setCanShowPopou(boolean canShowPopou) {
        this.canShowPopou = canShowPopou;
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        if (mPopupWindow != null && mPopupWindow.isShowing()) {
            mPopupWindow.dismiss();
            return true;
        }
        return false;
    }
}
