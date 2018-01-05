package com.myplas.q.common.netresquset;

import android.content.Context;

import com.myplas.q.common.utils.SharedUtils;
import com.myplas.q.common.utils.SystemUtils;
import com.myplas.q.common.utils.VersionUtils;

/**
 * 编写： 黄双
 * 电话：15378412400
 * 邮箱：15378412400@163.com
 * 时间：2017/3/25 20:09
 */
public class Utils {
    private static String token,
            userid,
            uuid,
            packgename,
            inch,
            comtroname,
            controversion,
            kernelVersion,
            makename,
            phonename,
            chrome,
            chromeversion;

    /**
     * Uicode 解码
     *
     * @param unicodeStr
     * @return
     */
    public static String decode(String unicodeStr) {
        if (unicodeStr == null) {
            return null;
        }
        StringBuffer retBuf = new StringBuffer();
        int maxLoop = unicodeStr.length();
        for (int i = 0; i < maxLoop; i++) {
            if (unicodeStr.charAt(i) == '\\') {
                if ((i < maxLoop - 5) && ((unicodeStr.charAt(i + 1) == 'u') || (unicodeStr.charAt(i + 1) == 'U'))) {
                    try {
                        retBuf.append((char) Integer.parseInt(unicodeStr.substring(i + 2, i + 6), 16));
                        i += 5;
                    } catch (NumberFormatException localNumberFormatException) {
                        retBuf.append(unicodeStr.charAt(i));
                    }
                } else {
                    retBuf.append(unicodeStr.charAt(i));
                }
            } else {
                retBuf.append(unicodeStr.charAt(i));
            }
        }
        return retBuf.toString();

    }

    /**
     * 获取头文件
     *
     * @param context
     * @return
     */
    public static String getHeader(Context context) {
        StringBuffer stringBuffer = new StringBuffer();
        token = SharedUtils.getSharedUtils().getData(context, "token");
        userid = SharedUtils.getSharedUtils().getData(context, "userid");
        uuid = SharedUtils.getSharedUtils().getData(context, "uuid");
        packgename = VersionUtils.getVersionCode(context) + "";
        inch = SystemUtils.getInch(context) + "";
        comtroname = SystemUtils.getSystem3() + "";
        controversion = SystemUtils.getSystem2();
        kernelVersion = SystemUtils.getKernelVersion() + "";
        makename = SystemUtils.getSystem1() + "";
        phonename = SystemUtils.getSystem() + "";
        chrome = "";
        chromeversion = "";

        return stringBuffer.append("android").append("|")
                .append(inch).append("|")
                .append(userid).append("|")
                .append(token).append("|")
                .append(uuid).append("|")
                .append(packgename).append("|")
                .append(comtroname).append("|")
                .append(controversion).append("|")
                .append(kernelVersion).append("|")
                .append(chrome).append("|")
                .append(chromeversion).append("|")
                .append(makename).append("|")
                .append(phonename).append("").toString();
    }
}
