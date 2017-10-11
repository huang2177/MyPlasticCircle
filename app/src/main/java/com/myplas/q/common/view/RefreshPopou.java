package com.myplas.q.common.view;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.os.Handler;
import android.support.v7.app.ActionBar;
import android.view.Gravity;
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

public class RefreshPopou {
    private Handler mHandler;
    private Activity mContext;
    private TextView tvRefresh;
    private CustomPopupWindow mPopupWindow;

    private StringBuffer defautContent;

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
        if (mPopupWindow == null) {
            View view = View.inflate(mContext, R.layout.layout_refresh_popou, null);
            tvRefresh = (TextView) view.findViewById(R.id.text_refresh_fragement);
            mPopupWindow = new CustomPopupWindow(view, ActionBar.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.WRAP_CONTENT);
            mPopupWindow.setBackgroundDrawable(new BitmapDrawable());
            mPopupWindow.setAnimationStyle(R.style.my_anim_popou);
            mPopupWindow.setOutsideTouchable(true);
            //popupWindow.setFocusable(true);
            mPopupWindow.update();
        }


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

    private void showRefreshPopou(View v) {
        if (android.os.Build.VERSION.SDK_INT >= 24) {
            int[] a = new int[2];
            v.getLocationInWindow(a);
            mPopupWindow.showAtLocation(mContext.getWindow().getDecorView()
                    , a[1] + v.getHeight()
                    , Gravity.NO_GRAVITY
                    , 0);
        } else {
            mPopupWindow.showAsDropDown(v);
        }
    }
}
