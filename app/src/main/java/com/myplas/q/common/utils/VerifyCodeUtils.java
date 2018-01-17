package com.myplas.q.common.utils;

import android.app.Activity;
import android.app.Fragment;
import android.os.Handler;
import android.os.Message;

import com.myplas.q.myself.login.LoginActivity;

import java.lang.ref.WeakReference;

/**
 * @author 黄双
 * @date 2018/1/17 0017
 */

public class VerifyCodeUtils {
    private int count;
    private boolean stop;
    private WeakReference<Activity> weakReference;

    private MyHandler mHandler;
    private CountListener listener;

    public VerifyCodeUtils(Activity activity, CountListener listener) {
        count = 60;
        this.listener = listener;
        mHandler = new MyHandler();
        weakReference = new WeakReference<Activity>(activity);
    }

    /**
     * 开始 60s 倒计时
     */
    public void startCount() {
        try {
            new Thread() {
                @Override
                public void run() {
                    for (int i = count; i >= 0; i--) {
                        if (!stop) {
                            Message msg = new Message();
                            msg.what = 1;
                            msg.obj = i;
                            mHandler.sendMessage(msg);
                            try {
                                sleep(1000);
                            } catch (Exception e) {
                            }
                        } else {
                            break;
                        }
                    }
                }
            }.start();
        } catch (Exception e) {

        }
    }

    /**
     * MyHandler 解决内存泄漏
     */
    private class MyHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            Activity activity = weakReference.get();
            if (activity != null && msg.what == 1 && listener != null) {
                listener.count(activity, msg.obj.toString());
            }
        }
    }

    /**
     * 是否终止线程
     *
     * @param stop
     */
    public void setStop(boolean stop) {
        this.stop = stop;
        if (stop && mHandler != null) {
            mHandler.removeCallbacksAndMessages(null);
            mHandler = null;
        }
    }

    /**
     * 计数的监听 接口
     */
    public interface CountListener {
        /**
         * @param activity
         * @param count
         */
        void count(Activity activity, String count);
    }
}
