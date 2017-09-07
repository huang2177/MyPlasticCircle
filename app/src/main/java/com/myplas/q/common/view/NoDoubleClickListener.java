package com.myplas.q.common.view;

import android.util.Log;
import android.view.View;

import com.myplas.q.common.utils.TextUtils;

/**
 * Created by Administrator on 2016/12/8.
 */
public abstract class NoDoubleClickListener implements View.OnClickListener {
    private long lastClickTime = 0;
    public static final int MIN_CLICK_DELAY_TIME = 800;

    @Override
    public void onClick(View v) {
        long currentTime = System.currentTimeMillis();
        Log.e("+++++", currentTime - lastClickTime + "");
        if (currentTime - lastClickTime > MIN_CLICK_DELAY_TIME) {
            lastClickTime = currentTime;
            onNoDoubleClick(v);
        } else {
            TextUtils.Toast(v.getContext(), "您的操作过于频繁，请稍后重试！");
        }
    }
    public abstract void onNoDoubleClick(View view);
}
