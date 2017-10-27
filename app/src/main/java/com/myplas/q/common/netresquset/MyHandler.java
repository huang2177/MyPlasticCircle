package com.myplas.q.common.netresquset;

import android.content.Context;
import android.os.Handler;
import android.os.Message;

import com.myplas.q.common.view.LoadingDialog;

public class MyHandler extends Handler {
    private Context context;

    public MyHandler(Context context) {
        this.context = context;
    }

    @Override
    public void handleMessage(Message msg) {
        try {
            ResultCallBack resultCallBack = (ResultCallBack) msg.obj;
            switch (msg.what) {
                case 1:
                    if (resultCallBack != null) {
                        resultCallBack.callBack(msg.getData().get("result"), msg.arg1);
                    }
                    if (LoadingDialog.getInstance(context) != null) {
                        LoadingDialog.getInstance(context).dismiss();
                    }
                    break;
                case 2:
                    if (resultCallBack != null) {
                        resultCallBack.failCallBack(msg.arg1);
                    }
                    if (LoadingDialog.getInstance(context) != null) {
                        LoadingDialog.getInstance(context).dismiss();
                    }
                    break;
                default:
                    break;
            }
        } catch (Exception e) {

        }
    }
}
