package com.myplas.q.guide.activity;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.myplas.q.R;
import com.myplas.q.common.netresquset.NetRequest;
import com.myplas.q.common.netresquset.ResultCallBack;
import com.myplas.q.common.utils.NetUtils;
import com.myplas.q.common.view.CustomPopupWindow;
import com.myplas.q.common.view.LoadingDialog;
import com.umeng.analytics.MobclickAgent;

import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import rx.Observable;
import rx.Observer;
import rx.Subscriber;

/**
 * 编写： 黄双
 * 电话：15378412400
 * 邮箱：15378412400@163.com
 * 时间：2017/3/17 11:46
 */
public class BaseActivity extends FragmentActivity {
    private View mView;
    public LinearLayout mLayoutBack;
    public ImageView mIVConact;
    public TextView mTextView, mTVLeft, mTVRight;

    private String type;
    private Observer observer;

    public void initTileBar() {
        mTVLeft = F(R.id.titlebar_text_left);
        mTVRight = F(R.id.titlebar_text_right);
        mTextView = F(R.id.titlebar_text_title);
        mLayoutBack = F(R.id.titlebar_img_back);
        mIVConact = F(R.id.titlebar_img_right);
        goBack(mLayoutBack);
    }

    public void setTitle(String title) {
        mTextView.setText(title);
    }

    public void setRightIVVisibility(int isShow) {
        mIVConact.setVisibility(isShow);
    }

    //右边确定按钮
    public void setRightTVVisibility(int isShow) {
        mTVRight.setVisibility(isShow);
    }

    //右边设置文字（默认为确定）
    public void setRightTVText(String text) {
        mTVRight.setText(text);
        mTVRight.setVisibility(View.VISIBLE);
    }

    //设置右边图片
    public void setRightIVResId(int resId) {
        mIVConact.setImageResource(resId);
        mIVConact.setVisibility(View.VISIBLE);
    }

    //左边取消按钮
    public void setLeftTVVisibility(int isShow) {
        mTVLeft.setVisibility(isShow);
        findViewById(R.id.titlebar_img_left).setVisibility(View.GONE);
        mTVLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    //绑定订阅者
    public void setObserver(Observer observer, String type) {
        this.type = type;
        this.observer = observer;
        mTVRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Observable observable = Observable.create(new Observable.OnSubscribe<String>() {
                    @Override
                    public void call(Subscriber<? super String> subscriber) {
                        subscriber.onNext(BaseActivity.this.type);
                        subscriber.onCompleted();
                    }
                });
                observable.subscribe(BaseActivity.this.observer);
            }
        });
    }

    public <T extends View> T F(int id) {
        return (T) findViewById(id);
    }

    public void goBack(View view) {
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    //get请求
    public synchronized void getAsyn(Activity context
            , String url
            , Map<String
            , String> map
            , ResultCallBack resultCallBack
            , int type
            , String jison) {
        if (NetUtils.isNetworkStateed(context)) {
            NetRequest netRequest = new NetRequest(context, url, map, resultCallBack, type);
            netRequest.getAsyn();
        }
    }

    //post请求
    public static synchronized void postAsyn(Context context
            , String url
            , Map<String
            , String> map
            , ResultCallBack resultCallBack
            , int type) {
        try {
            if (NetUtils.isNetworkStateed(context)) {
                NetRequest netRequest = new NetRequest(context, url, map, resultCallBack, type);
                netRequest.postAsyn();
            } else {
                resultCallBack.failCallBack(type);
            }
            LoadingDialog.getInstance(context).show();
        } catch (Exception e) {
        }
    }

    //post请求
    public static synchronized void postAsyn1(Context context
            , String url
            , Map<String
            , String> map
            , ResultCallBack resultCallBack
            , int type
            , boolean isShowDialog) {
        try {
            if (NetUtils.isNetworkStateed(context)) {
                NetRequest netRequest = new NetRequest(context, url, map, resultCallBack, type);
                netRequest.postAsyn();
            } else {
                resultCallBack.failCallBack(type);
            }
            if (isShowDialog) {
                LoadingDialog.getInstance(context).show();
            }
        } catch (Exception e) {
        }
    }

    //post之上传图片
    public synchronized void postUpLoadIMG(Context context
            , String url
            , String imgpath
            , String token
            , ResultCallBack resultCallBack
            , int type) {
        if (NetUtils.isNetworkStateed(context)) {
            NetRequest netRequest = new NetRequest(context, url, null, resultCallBack, type);
            netRequest.post_UpLoadIMG(imgpath, token);
        }
    }


    public void call(String tel) {
        Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + tel));
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    public void showInPutKeybord(final EditText editText) {
        editText.requestFocus();
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {

            @Override
            public void run() {
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                if (imm != null) {
                    imm.showSoftInput(editText, 0);
                }
            }

        }, 100);
    }
}
