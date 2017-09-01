package com.myplas.q.common.utils;

import android.app.Activity;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者：  黄双
 * 事件 2017/9/1 0001.
 * 邮箱： 15378412400@163.com
 */

public class ActivityManager {
    private static List<Activity> mActivityList = new ArrayList<>();

    public static List<Activity> getActivityList() {
        return mActivityList;
    }
}
