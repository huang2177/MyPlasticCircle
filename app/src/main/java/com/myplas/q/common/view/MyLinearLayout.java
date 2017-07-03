package com.myplas.q.common.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.ViewParent;
import android.widget.LinearLayout;

/**
 * 编写： 黄双
 * 电话：15378412400
 * 邮箱：15378412400@163.com
 * 时间：2017/5/23 18:17
 */
public class MyLinearLayout extends LinearLayout{
    private boolean ennablerefresh;
    public MyLinearLayout(Context context) {
        super(context);
    }

    public MyLinearLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyLinearLayout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        super.onInterceptTouchEvent(ev);
        if (ennablerefresh) {
            switch (ev.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    lastDownY = ev.getY();
                    break;
                case MotionEvent.ACTION_MOVE:
                    boolean isTop = ev.getY() - lastDownY > 0 && this.getScrollY() == 0;
                    if (isTop) { // 允许父控件拦截，即不允许父控件拦截设为false
                        return true;
                    } else { // 不允许父控件拦截，即不允许父控件拦截设为true
                        return false;
                    }
            }
        }
        return super.onInterceptTouchEvent(ev);
    }

    float lastDownY;

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        super.onTouchEvent(event);
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                lastDownY = event.getY();
                parentRequestDisallowInterceptTouchEvent(true); // 保证事件可往下传递
                return true;
//                break;
            case MotionEvent.ACTION_MOVE:
                boolean isTop = event.getY() - lastDownY > 0 && this.getScrollY() == 0;
                if (isTop) { // 允许父控件拦截，即不允许父控件拦截设为false
                    parentRequestDisallowInterceptTouchEvent(false);
                    return true;
                } else { // 不允许父控件拦截，即不允许父控件拦截设为true
                    parentRequestDisallowInterceptTouchEvent(false);
                    return true;
                }
//                break;
            case MotionEvent.ACTION_UP:
                parentRequestDisallowInterceptTouchEvent(true); // 保证事件可往下传递
                break;
            case MotionEvent.ACTION_CANCEL:

                break;
        }
        return false;
    }
    /**
     * 是否允许父控件拦截事件
     *
     * @param
     */
    private void parentRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {
        ViewParent vp = getParent();
        if (null == vp) {
            return;
        }
        vp.requestDisallowInterceptTouchEvent(disallowIntercept);
    }
    public void setEnnablerefresh(boolean ennablerefresh){
        this.ennablerefresh=ennablerefresh;
    }
}
