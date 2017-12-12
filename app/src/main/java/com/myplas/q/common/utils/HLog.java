package com.myplas.q.common.utils;

import android.app.Activity;
import android.util.Log;

import com.myplas.q.BuildConfig;

/**
 * 作者:huangshuang
 * 事件 2017/10/17 0017.
 * 邮箱： 15378412400@163.com
 */

public class HLog {
    public static void e(Activity activity, String msg) {
        if (BuildConfig.LOGABLE && TextUtils.notEmpty(msg)) {
            Log.e("------>" + activity.getClass().getSimpleName(), msg);
        }
    }
}
