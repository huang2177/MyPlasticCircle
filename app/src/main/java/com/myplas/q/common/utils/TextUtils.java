package com.myplas.q.common.utils;

import android.app.Activity;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Looper;
import android.support.design.widget.Snackbar;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.androidadvance.topsnackbar.TSnackbar;
import com.myplas.q.R;
import com.myplas.q.common.appcontext.ActivityManager;
import com.myplas.q.guide.activity.MainActivity;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import cn.jpush.android.api.JPushInterface;

/**
 * 编写： 黄双
 * 电话：15378412400
 * 邮箱：15378412400@163.com
 * 时间：2017/3/27 09:34
 */
public class TextUtils {

    public static final String PREFS_NAME = "JPUSH_EXAMPLE";
    public static final String PREFS_DAYS = "JPUSH_EXAMPLE_DAYS";
    public static final String PREFS_START_TIME = "PREFS_START_TIME";
    public static final String PREFS_END_TIME = "PREFS_END_TIME";
    public static final String KEY_APP_KEY = "JPUSH_APPKEY";

    public static final boolean isPhoneNum(String phone) {
        Pattern p = Pattern.compile("13\\d{9}|14[57]\\d{8}|15[012356789]\\d{8}|18[01256789]\\d{8}|17[0678]\\d{8}");
        Matcher m = p.matcher(phone);
        return m.matches();
    }

    public static boolean isNullOrEmpty(String s) {
        if ((null == s) || ("").equals(s)) {
            return false;
        } else {
            return true;
        }
    }

    public static void Toast(Context context, String msg) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_toast, null);
        TextView textView = (TextView) view.findViewById(R.id.toast_text);

        Toast toast = Toast.makeText(context, "", Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER, 0, 400);
        toast.setView(view);
        textView.setText(msg);
        toast.show();
    }

    public static boolean isQQ(String s) {
        if (s.matches("[1-9][0-9]{4,14}")) {
            return true;
        } else {
            return false;
        }
    }

    public static void topTSnackbar(View view, String s) {
        View view1 = null;
        try {
            Activity activity = ActivityManager.getInstance().getCurrentActivity();
            view1 = activity.findViewById(android.R.id.content);
        } catch (Exception e) {
        }

        TSnackbar snackbar = TSnackbar.make(view1, s, Snackbar.LENGTH_SHORT);
        View snackbarView = snackbar.getView();
        snackbarView.setBackgroundColor(Color.WHITE);
        ViewGroup.LayoutParams lp = snackbarView.getLayoutParams();
        lp.height = 200;
        snackbarView.setLayoutParams(lp);
        TextView textView = (TextView) snackbarView.findViewById(com.androidadvance.topsnackbar.R.id.snackbar_text);
        textView.setTextColor(Color.BLACK);
        textView.setGravity(Gravity.CENTER);
        textView.setTextSize(18);
        snackbar.show();
    }


    public static boolean isEmpty(String s) {
        if (null == s)
            return true;
        if (s.length() == 0)
            return true;
        if (s.trim().length() == 0)
            return true;
        return false;
    }

    // 校验Tag Alias 只能是数字,英文字母和中文
    public static boolean isValidTagAndAlias(String s) {
        Pattern p = Pattern.compile("^[\u4E00-\u9FA50-9a-zA-Z_!@#$&*+=.|]+$");
        Matcher m = p.matcher(s);
        return m.matches();
    }

    // 取得AppKey
    public static String getAppKey(Context context) {
        Bundle metaData = null;
        String appKey = null;
        try {
            ApplicationInfo ai = context.getPackageManager().getApplicationInfo(
                    context.getPackageName(), PackageManager.GET_META_DATA);
            if (null != ai)
                metaData = ai.metaData;
            if (null != metaData) {
                appKey = metaData.getString(KEY_APP_KEY);
                if ((null == appKey) || appKey.length() != 24) {
                    appKey = null;
                }
            }
        } catch (PackageManager.NameNotFoundException e) {

        }
        return appKey;
    }

    // 取得版本号
    public static String GetVersion(Context context) {
        try {
            PackageInfo manager = context.getPackageManager().getPackageInfo(
                    context.getPackageName(), 0);
            return manager.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            return "Unknown";
        }
    }


    public static boolean isConnected(Context context) {
        ConnectivityManager conn = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = conn.getActiveNetworkInfo();
        return (info != null && info.isConnected());
    }

    public static String getImei(Context context, String imei) {
        String ret = null;
        try {
            TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
            ret = telephonyManager.getDeviceId();
        } catch (Exception e) {
            Log.e(TextUtils.class.getSimpleName(), e.getMessage());
        }
        if (isReadableASCII(ret)) {
            return ret;
        } else {
            return imei;
        }
    }

    private static boolean isReadableASCII(CharSequence string) {
        if (android.text.TextUtils.isEmpty(string)) return false;
        try {
            Pattern p = Pattern.compile("[\\x20-\\x7E]+");
            return p.matcher(string).matches();
        } catch (Throwable e) {
            return true;
        }
    }

    public static String getDeviceId(Context context) {
        return JPushInterface.getUdid(context);
    }
}
