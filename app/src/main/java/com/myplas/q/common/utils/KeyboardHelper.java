package com.myplas.q.common.utils;

import android.app.Activity;
import android.graphics.Rect;
import android.os.Build;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.WindowManager;

/**
 * @author 黄双
 * @date 2018/1/25 0025
 */

public class KeyboardHelper {
    private Activity activity;
    private View decorView;
    private View contentView;

    /**
     * 构造函数
     *
     * @param act         需要解决bug的activity
     * @param contentView 界面容器，activity中一般是R.id.content，也可能是Fragment的容器，根据个人需要传递
     */
    public KeyboardHelper(Activity act, View contentView) {
        this.activity = act;
        this.decorView = act.getWindow().getDecorView();
        this.contentView = contentView;
    }

    /**
     * 监听layout变化
     */
    public void enable() {
        if (activity == null) {
            return;
        }
        activity.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN | WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        if (Build.VERSION.SDK_INT >= 19) {
            decorView.getViewTreeObserver().addOnGlobalLayoutListener(onGlobalLayoutListener);
        }
    }

    /**
     * 取消监听
     */
    public void disable() {
        if (activity == null) {
            return;
        }
        activity.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN | WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        if (Build.VERSION.SDK_INT >= 19) {
            decorView.getViewTreeObserver().removeOnGlobalLayoutListener(onGlobalLayoutListener);
        }
    }

    private ViewTreeObserver.OnGlobalLayoutListener onGlobalLayoutListener = new ViewTreeObserver.OnGlobalLayoutListener() {
        @Override
        public void onGlobalLayout() {
            Rect r = new Rect();

            decorView.getWindowVisibleDisplayFrame(r);
            int height = decorView.getContext().getResources().getDisplayMetrics().heightPixels;
            int diff = height - r.bottom;

            if (diff != 0) {
                if (contentView.getPaddingBottom() != diff) {
                    contentView.setPadding(0, 0, 0, diff);
                }
            } else {
                if (contentView.getPaddingBottom() != 0) {
                    contentView.setPadding(0, 0, 0, 0);
                }
            }
        }
    };
}
