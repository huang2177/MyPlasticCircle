package com.myplas.q.common.netresquset;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.widget.Toast;

import com.myplas.q.common.view.LoadingDialog;

public class MyHandler extends Handler {
    private  Context context;
    public MyHandler(Context context){
        this.context=context;
    }
    @Override
    public void handleMessage(Message msg) {
        switch (msg.what){
            case 1:
                ResultCallBack resultCallBack= (ResultCallBack)msg.obj;
                resultCallBack.callBack(msg.getData().get("result"),msg.arg1);
                LoadingDialog.getInstance(context).dismiss();
            break;
            case 2:
                LoadingDialog.getInstance(context).dismiss();
                ResultCallBack resultCallBack1= (ResultCallBack)msg.obj;
                resultCallBack1.failCallBack(msg.arg1);
            break;
        }
    }
}
