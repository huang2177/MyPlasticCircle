package com.myplas.q.guide.activity;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.myplas.q.R;
import com.myplas.q.common.netresquset.NetRequest;
import com.myplas.q.common.netresquset.ResultCallBack;
import com.myplas.q.common.utils.NetUtils;
import com.myplas.q.common.view.LoadingDialog;
import com.umeng.analytics.MobclickAgent;

import java.util.Map;

/**
 * 编写： 黄双
 * 电话：15378412400
 * 邮箱：15378412400@163.com
 * 时间：2017/3/17 11:46
 */
public class BaseActivity extends Activity{
    public void goBack(View view){
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
    //get请求
    public synchronized void getAsyn(Context context, String url, Map<String, String> map, ResultCallBack resultCallBack, int type,String jison){
        if (NetUtils.isNetworkStateed(context)) {
            NetRequest netRequest=new NetRequest(context,url,map,resultCallBack,type);
            netRequest.getAsyn();
        }
    }
    //post请求
    public static synchronized void postAsyn(Context context, String url, Map<String, String> map, ResultCallBack resultCallBack, int type){
        if (NetUtils.isNetworkStateed(context)) {
            LoadingDialog.getInstance(context).show();
            NetRequest netRequest=new NetRequest(context,url,map,resultCallBack,type);
            netRequest.postAsyn();
        }else{
            resultCallBack.failCallBack(type);
        }
    }
    //post请求
    public static synchronized void postAsyn1(Context context, String url, Map<String, String> map, ResultCallBack resultCallBack, int type,boolean isSHowDialog){
        if (NetUtils.isNetworkStateed(context)) {
            if (isSHowDialog) {
                LoadingDialog.getInstance(context).show();
            }
            NetRequest netRequest=new NetRequest(context,url,map,resultCallBack,type);
            netRequest.postAsyn();
        }else{
            resultCallBack.failCallBack(type);
        }
    }
    //post之上传图片
    public synchronized void postUpLoadIMG(Context context, String url,String imgpath ,String token,ResultCallBack resultCallBack,int type){
        if (NetUtils.isNetworkStateed(context)) {
            NetRequest netRequest=new NetRequest(context,url,null,resultCallBack,type);
            netRequest.post_UpLoadIMG(imgpath,token);
        }
    }
}
