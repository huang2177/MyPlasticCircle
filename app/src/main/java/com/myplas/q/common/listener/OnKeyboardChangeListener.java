package com.myplas.q.common.listener;

import android.content.Context;
import android.graphics.Rect;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.Toast;

import com.myplas.q.common.utils.ScreenUtils;

/**
 * 作者:huangshuang
 * 事件 2017/10/11 0011.
 * 邮箱： 15378412400@163.com
 */

public class OnKeyboardChangeListener implements View.OnLayoutChangeListener {
    private int keyHeight = 0;
    //屏幕高度
    private int screenHeight = 0;

    private OnChangeListener mOnChangerListener;

    public OnKeyboardChangeListener(OnChangeListener onChangeListener, Context context) {
        screenHeight = ScreenUtils.getScreenHeight(context);
        keyHeight = screenHeight / 3;
        mOnChangerListener = onChangeListener;
    }


    @Override
    public void onLayoutChange(View v, int left, int top, int right, int bottom, int oldLeft, int oldTop, int oldRight, int oldBottom) {
        //现在认为只要控件将Activity向上推的高度超过了1/3屏幕高，就认为软键盘弹起
        if (oldBottom != 0 && bottom != 0 && (oldBottom - bottom > keyHeight)) {
            mOnChangerListener.onKeyboardShow();
        } else if (oldBottom != 0 && bottom != 0 && (bottom - oldBottom > keyHeight)) {
            mOnChangerListener.onKeyboardHidden();
        }
    }

    /**
     * 软键盘监听变化接口
     */
    public interface OnChangeListener {
        void onKeyboardShow();//显示接口

        void onKeyboardHidden();//隐藏接口
    }
}
