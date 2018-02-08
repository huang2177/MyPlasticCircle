package com.myplas.q.common.net;

/**
 * 电话：15378412400
 * 邮箱：15378412400@163.com
 * 时间：2017/3/26 15:42
 * @author 黄双
 */
public interface ResultCallBack {
    /**
     * 成功时调用
     *
     * @param object
     * @param type
     */
    void callBack(Object object, int type);

    /**
     * 请求失败 或者 没有数据是调用
     *
     * @param message
     * @param type
     * @param httpCode
     */
    void failCallBack(int type, String message, int httpCode);
}
