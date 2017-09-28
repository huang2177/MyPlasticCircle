package com.myplas.q.common.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by 刘锐 on 2016/12/16 15:37.
 * Tel 13554142421
 */
public class NumUtils {
    static String str1;

    public static int getNum(String str) {
        try {
            str = str.trim();
            str1 = "";
            if (str != null && !"".equals(str)) {
                for (int i = 0; i < str.length(); i++) {
                    if (str.charAt(i) >= 48 && str.charAt(i) <= 57) {
                        str1 += str.charAt(i);
                    }
                }
            }
            return Integer.parseInt(str1);
        } catch (Exception e) {
            return 0;
        }
    }

    public static int getCharIndex(String str, int num) {
        Pattern pattern = Pattern.compile("/");
        Matcher findMatcher = pattern.matcher(str);
        int number = 0;
        while (findMatcher.find()) {
            number++;
            if (number == num) {//当“/”第二次出现时停止
                break;
            }
        }
        int i = findMatcher.start();//“/”第二次出现的位置
        return i;
    }
}
