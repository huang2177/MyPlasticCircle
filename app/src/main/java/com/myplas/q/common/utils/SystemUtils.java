package com.myplas.q.common.utils;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.util.DisplayMetrics;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.util.UUID;

/**
 * 编写： 黄双
 * 电话：15378412400
 * 邮箱：15378412400@163.com
 * 时间：2017/4/19 11:00
 */
public class SystemUtils {
    //手机系统api
    public static int getSystemInfo() {
        return Build.VERSION.SDK_INT;
    }
    //手机系统型号
    public static String getSystem() {
        return Build.MODEL;
    }
    //手机制造商
    public static String getSystem1() {
        return Build.MANUFACTURER;
    }
    //手机操作系统版本
    public static String getSystem2() {
        return Build.VERSION.RELEASE;
    }
    //手机操作系统名称
    public static String getSystem3() {
        return android.os.Build.BRAND;
    }
    //获取uuid
    public static String getMyUUID(Activity context) {
        final TelephonyManager tm = (TelephonyManager) context.getBaseContext().getSystemService(Context.TELEPHONY_SERVICE);
        final String tmDevice, tmSerial, tmPhone, androidId;
        tmDevice = "" + tm.getDeviceId();
        tmSerial = "" + tm.getSimSerialNumber();
        androidId = "" + Settings.Secure.getString(context.getContentResolver(), android.provider.Settings.Secure.ANDROID_ID);
        UUID deviceUuid = new UUID(androidId.hashCode(), ((long) tmDevice.hashCode() << 32) | tmSerial.hashCode());
        String uniqueId = deviceUuid.toString();
        return uniqueId;
    }
//获取手机英寸
    public static double getInch(Context context) {
        DisplayMetrics metrics = context.getResources().getDisplayMetrics();
        float density = metrics.density;// 密度值
        float xdpi = metrics.xdpi;
        int width = metrics.widthPixels;
        int height = metrics.heightPixels;
        double mInch = formatDouble(Math.sqrt((width / metrics.xdpi) * (width / metrics.xdpi) + (height / metrics.ydpi) * (height / metrics.ydpi)), 1);
        return mInch;
    }
//内核版本
public static String getKernelVersion() {
    String kernelVersion = "";
    InputStream inputStream = null;
    try {
        inputStream = new FileInputStream("/proc/version");
       } catch (FileNotFoundException e) {
        e.printStackTrace();
        return kernelVersion;
        }
    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream), 8 * 1024);
    String info = "";
    String line = "";
    try {
        while ((line = bufferedReader.readLine()) != null) {
            info += line;
            }
        } catch (IOException e) {
        e.printStackTrace();
        } finally {
        try {
            bufferedReader.close();
           inputStream.close();
            } catch (IOException e) {
            e.printStackTrace();
            }
        }
    try {
        if (info != "") {
            final String keyword = "version ";
            int index = info.indexOf(keyword);
            line = info.substring(index + keyword.length());
            index = line.indexOf(" ");
            kernelVersion = line.substring(0, index);
           }
        } catch (IndexOutOfBoundsException e) {
        e.printStackTrace();
        }

    return kernelVersion;
}

    private static double formatDouble(double d,int newScale){
        BigDecimal bd=new BigDecimal(d);
        return bd.setScale(newScale, BigDecimal.ROUND_HALF_UP).doubleValue();
    }
}
