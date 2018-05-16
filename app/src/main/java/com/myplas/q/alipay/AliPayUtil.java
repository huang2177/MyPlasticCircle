package com.myplas.q.alipay;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

import com.alipay.sdk.app.PayTask;
import com.myplas.q.app.activity.BaseActivity;
import com.myplas.q.common.api.API;
import com.myplas.q.common.appcontext.Constant;
import com.myplas.q.common.view.CommonDialog;
import com.myplas.q.myself.integral.activity.IntegralPayActivtity;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Huangshuang  2018/3/29 0029
 */

public class AliPayUtil {

    private Activity context;
    private static final int SDK_PAY_FLAG = 1;
    private static final int SDK_AUTH_FLAG = 2;

    /**
     * 支付宝支付业务：入参app_id
     */
    public static final String APPID = Constant.ALIPAY_APPID;

    @SuppressLint("HandlerLeak")
    private Handler mHandler = new Handler() {
        @Override
        @SuppressWarnings("unused")
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case SDK_PAY_FLAG: {
                    @SuppressWarnings("unchecked")
                    PayResult payResult = new PayResult((Map<String, String>) msg.obj);
                    /**
                     对于支付结果，请商户依赖服务端的异步通知结果。同步通知结果，仅作为支付结束的通知。
                     */
                    String resultInfo = payResult.getResult();// 同步返回需要验证的信息
                    String resultStatus = payResult.getResultStatus();

                    if (TextUtils.equals(resultStatus, "9000")) {
                        CommonDialog commonDialog = new CommonDialog();
                        commonDialog.setCanceledOnTouchOutside(false);
                        commonDialog.showDialog(context, "支付成功!", 5, dialogShowInterface);
                    } else {
                        call();
                        CommonDialog commonDialog = new CommonDialog();
                        commonDialog.showDialog(context, "请重新充值!", 6, null);
                    }
                    break;
                }
                default:
                    break;
            }
        }

        ;
    };
    private String orderId;
    private CommonDialog.DialogShowInterface dialogShowInterface;

    /**
     * 开始支付
     *
     * @param context
     * @param orderInfo
     */
    public void pay(Activity context, CommonDialog.DialogShowInterface showInterface, final String orderInfo, String orderId) {
        this.orderId = orderId;
        this.context = context;
        this.dialogShowInterface = showInterface;
        Runnable payRunnable = new Runnable() {

            @Override
            public void run() {
                PayTask alipay = new PayTask(AliPayUtil.this.context);
                Map<String, String> result = alipay.payV2(orderInfo, true);
                Log.i("msp", result.toString());

                Message msg = new Message();
                msg.what = SDK_PAY_FLAG;
                msg.obj = result;
                mHandler.sendMessage(msg);
            }
        };

        Thread payThread = new Thread(payRunnable);
        payThread.start();
    }

    /**
     * 支付宝（服务器） 回调
     */
    public void call() {
        Map<String, String> map = new HashMap<>(8);
        map.put("order_id", orderId);
        BaseActivity.postAsyn(context, API.ALIPAYFAILURE, map, null, 4, false);
    }
}
