package com.myplas.q.sockethelper;

/**
 * 作者：  黄双
 * 事件 2017/8/16 0016.
 * 邮箱： 15378412400@163.com
 */

public interface RabbitMQCallBack {
    /**
     * 红点消息及滚动通知的回调
     *
     * @param isShowRedDot
     * @param isShowNotify
     */
    public void rCallback(boolean isShowRedDot, boolean isShowNotify);
}
