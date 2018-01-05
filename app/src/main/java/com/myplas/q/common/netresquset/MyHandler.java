package com.myplas.q.common.netresquset;

import android.content.Context;
import android.os.Handler;
import android.os.Message;

import com.myplas.q.common.view.LoadingDialog;

/**
 * @author huangshuang
 */
public class MyHandler extends Handler {
    private Context context;

    public MyHandler(Context context) {
        this.context = context;
    }

    @Override
    public void handleMessage(Message msg) {
        try {
            ResultCallBack resultCallBack = (ResultCallBack) msg.obj;
            if (resultCallBack == null) {
                return;
            }
            switch (msg.what) {
                case 1:
                    resultCallBack.callBack(msg.getData().get("result").toString(), msg.arg1);
                    break;
                case 2:
                    resultCallBack.failCallBack(msg.arg1, msg.getData().get("result").toString(), msg.arg2);
                    break;
                default:
                    break;
            }
            if (LoadingDialog.getInstance(context) != null) {
                LoadingDialog.getInstance(context).dismiss();
            }
        } catch (Exception e) {

        }
    }
}
