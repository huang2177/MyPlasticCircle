package com.myplas.q.common.netresquset;

/**
 * 编写： 黄双
 * 电话：15378412400
 * 邮箱：15378412400@163.com
 * 时间：2017/3/26 15:42
 */
public interface ResultCallBack {
    void callBack(Object object,int type);
    void failCallBack(int type);
}
