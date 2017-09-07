package com.myplas.q.common.view;

import android.view.View;

/**
 * Created by Administrator on 2016/12/8.
 */
public abstract class NoDoubleClickListener implements View.OnClickListener {
    private long lastClickTime = 0;
    public static final int MIN_CLICK_DELAY_TIME = 50;

    @Override
    public void onClick(View v) {
        long currentTime = System.currentTimeMillis();
        if (currentTime - lastClickTime > MIN_CLICK_DELAY_TIME) {
            lastClickTime = currentTime;
            onNoDoubleClick(v);
        }
    }

    public abstract void onNoDoubleClick(View view);
}
