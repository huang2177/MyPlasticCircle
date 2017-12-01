package com.myplas.q.common.utils;

import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @author 黄双
 * @date 2017/11/27 0027
 */

public class TimeStampUtils {
    static StringBuffer timeStr;

    public static String getTime(long timeStamp) {
        if (isToday(timeStamp * 1000)) {            //今天
            timeStr = new StringBuffer("今天");
            return timeStr.toString();
        } else if (isYesterday(timeStamp * 1000)) { //昨天
            timeStr = new StringBuffer("昨天");
            return timeStr.toString();
        } else if (isThisWeek(timeStamp * 1000)) { //本周
            return timeStr.toString();
        } else {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date d1 = new Date(timeStamp * 1000);
            return format.format(d1);
        }
    }

    /**
     * 判断选择的日期是否是本周
     */

    private static boolean isThisWeek(long time) {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH) - 6;

        calendar.set(year, month, day);

        long today = System.currentTimeMillis();
        long lastToday = calendar.getTime().getTime();
        if (lastToday <= time && time <= today) {
            Calendar calendar1 = Calendar.getInstance();
            calendar1.setTime(new Date(time));
            int a = calendar1.getFirstDayOfWeek() == Calendar.SUNDAY
                    ? calendar1.get(Calendar.DAY_OF_WEEK)
                    : calendar1.get(Calendar.DAY_OF_WEEK) - 1;
            switch (a) {
                case 1:
                    timeStr = new StringBuffer("周日");
                    return true;
                case 2:
                    timeStr = new StringBuffer("周一");
                    return true;
                case 3:
                    timeStr = new StringBuffer("周二");
                    return true;
                case 4:
                    timeStr = new StringBuffer("周三");
                    return true;
                case 5:
                    timeStr = new StringBuffer("周四");
                    return true;
                case 6:
                    timeStr = new StringBuffer("周五");
                    return true;
                case 7:
                    timeStr = new StringBuffer("周六");
                    return true;
                default:
                    break;
            }
        }
        return false;
    }

    /**
     * 判断选择的日期是否是昨天
     */

    private static boolean isYesterday(long time) {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH) - 1;

        calendar.set(year, month, day);
        long yesterday = calendar.getTime().getTime();
        if ((yesterday - time) >= 0 && (yesterday - time) <= 86400000) {
            return true;
        }
        return false;
    }

    /**
     * 判断选择的日期是否是今天
     */

    private static boolean isToday(long time) {
        long today = System.currentTimeMillis();
        if ((today - time) >= 0 && (today - time) <= 86400000) {
            return true;
        }
        return false;
    }
}
