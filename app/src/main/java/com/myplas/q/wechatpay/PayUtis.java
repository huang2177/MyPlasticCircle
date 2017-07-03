package com.myplas.q.wechatpay;

import android.content.Context;
import android.util.Log;

import com.myplas.q.common.api.API;
import com.myplas.q.myinfo.beans.OrderBean;
import com.tencent.mm.opensdk.modelpay.PayReq;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

/**
 * 编写： 黄双
 * 电话：15378412400
 * 邮箱：15378412400@163.com
 * 时间：2017/5/19 17:26
 */
public class PayUtis {
    String WX_APPID = API.WXAPI;
    IWXAPI mWxApi;

    public PayUtis(Context mContext) {
        mWxApi = WXAPIFactory.createWXAPI(mContext, WX_APPID, true);
        mWxApi.registerApp(WX_APPID);
    }
    /**
     * 请求app服务器得到的回调结果
     */
    public boolean pay(OrderBean.DataBean dataBean) {
        PayReq req = new PayReq();
            try {
                req.appId = WX_APPID;// 微信开放平台审核通过的应用APPID
                req.partnerId = dataBean.getPartnerid();// 微信支付分配的商户号
                req.prepayId = dataBean.getPrepayid();// 预支付订单号，app服务器调用“统一下单”接口获取
                req.nonceStr = dataBean.getNoncestr();// 随机字符串，不长于32位，服务器小哥会给咱生成
                req.timeStamp = dataBean.getTimestamp()+"";// 时间戳，app服务器小哥给出
                req.packageValue = dataBean.getPackageX();// 固定值Sign=WXPay，可以直接写死，服务器返回的也是这个固定值
                req.sign = dataBean.getSign();// 签名，服务器小哥给出，他会根据：https://pay.weixin.qq.com/wiki/doc/api/app/app.php?chapter=4_3指导得到这个
            } catch (Exception e) {
            }
        return mWxApi.sendReq(req);
    }
}
