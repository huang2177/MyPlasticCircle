package com.myplas.q.app.activity;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Handler;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.myplas.q.R;
import com.myplas.q.common.net.NetRequest;
import com.myplas.q.common.net.ProgressListener;
import com.myplas.q.common.net.ResultCallBack;
import com.myplas.q.common.utils.NetUtils;
import com.myplas.q.common.utils.TextUtils;
import com.myplas.q.common.view.LoadingDialog;
import com.umeng.analytics.MobclickAgent;

import java.util.Map;

/**
 * 编写： 黄双
 * 电话：15378412400
 * 邮箱：15378412400@163.com
 * 时间：2017/3/17 11:46
 */
public class BaseActivity extends FragmentActivity {
    private View mView;
    public LinearLayout mLayoutBack;
    private FrameLayout mFrameLayout;
    public ImageView mIVRight, mIVLeft;
    public TextView mTextView, mTVLeft, mTVRight;

    private String type;

    /**
     * 实例化titleBar
     */
    public void initTileBar() {
        mFrameLayout = F(R.id.title_bar);
        mIVLeft = F(R.id.titlebar_img_left);
        mTVLeft = F(R.id.titlebar_text_left);
        mIVRight = F(R.id.titlebar_img_right);
        mTVRight = F(R.id.titlebar_text_right);
        mTextView = F(R.id.titlebar_text_title);
        mLayoutBack = F(R.id.titlebar_img_back);
        goBack(mLayoutBack);
    }

    public void setTitle(String title) {
        mTextView.setText(title);
    }

    public void setRightIVVisibility(int isShow) {
        mIVRight.setVisibility(isShow);
    }

    public void setTitleBarBackground(int resId) {
        mFrameLayout.setBackgroundResource(resId);
    }

    public void setTitleBarTextColor(int resId) {
        mTextView.setTextColor(getResources().getColor(resId));
    }

    /**
     * 右边确定按钮
     */

    public void setRightTVVisibility(int isShow) {
        mTVRight.setVisibility(isShow);
    }

    /**
     * 右边设置文字（默认为确定）
     */

    public void setRightTVText(String text) {
        mTVRight.setText(text);
        mTVRight.setVisibility(TextUtils.notEmpty(text)
                ? View.VISIBLE
                : View.GONE);
    }

    /**
     * 设置右边图片
     */

    public void setRightIVResId(int resId) {
        mIVRight.setImageResource(resId);
        mIVRight.setVisibility(View.VISIBLE);
    }

    /**
     * 左边取消按钮
     */

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

    /**
     * 左边返回按钮
     */

    public void setLeftIVVisibility(int isShow) {
        mLayoutBack.setVisibility(isShow);
    }

    /**
     * 左边返回按钮
     */

    public void setLeftIVResId(int resId) {
        mIVLeft.setImageResource(resId);
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


    /**
     * get请求
     *
     * @param context
     * @param url
     * @param map
     * @param resultCallBack
     * @param type
     */

    public static void getAsyn(Context context, String url, Map<String, String> map, ResultCallBack resultCallBack, int type) {
        getAsyn(context, url, map, resultCallBack, type, true);
    }


    public static void getAsyn(Context context, String url, Map<String, String> map, ResultCallBack resultCallBack, int type, boolean isShowDialog) {
        if (NetUtils.isNetworkStateed(context)) {
            NetRequest netRequest = new NetRequest(context, url, map, resultCallBack, type);
            netRequest.getAsyn();
        }
        if (isShowDialog) {
            LoadingDialog.getInstance(context).show();
        }
    }

    /**
     * post请求
     *
     * @param context
     * @param url
     * @param map
     * @param resultCallBack
     * @param type
     */

    public static void postAsyn(Context context, String url, Map<String, String> map, ResultCallBack resultCallBack, int type) {
        postAsyn(context, url, map, resultCallBack, type, true);
    }

    public static void postAsyn(Context context, String url, Map<String, String> map, ResultCallBack resultCallBack, int type, boolean isShowDialog) {
        try {
            if (NetUtils.isNetworkStateed(context)) {
                NetRequest netRequest = new NetRequest(context, url, map, resultCallBack, type);
                netRequest.postAsyn();
            }
            if (isShowDialog) {
                LoadingDialog.getInstance(context).show();
            }
        } catch (Exception e) {
        }
    }

    /**
     * delete
     *
     * @param context
     * @param url
     * @param map
     * @param resultCallBack
     * @param type
     * @param isShowDialog
     */
    public static void deleteAsyn(Context context, String url, Map<String, String> map, ResultCallBack resultCallBack, int type, boolean isShowDialog) {
        if (NetUtils.isNetworkStateed(context)) {
            NetRequest netRequest = new NetRequest(context, url, map, resultCallBack, type);
            netRequest.deleteAsyn();
        }
    }

    /**
     * put
     *
     * @param context
     * @param url
     * @param map
     * @param resultCallBack
     * @param type
     * @param isShowDialog
     */
    public static void putAsyn(Context context, String url, Map<String, String> map, ResultCallBack resultCallBack, int type, boolean isShowDialog) {
        if (NetUtils.isNetworkStateed(context)) {
            NetRequest netRequest = new NetRequest(context, url, map, resultCallBack, type);
            netRequest.putAsyn();
        }

    }

    /**
     * post之上传图片
     */

    public void postUpLoadImg(Context context, String url, String imgpath, String token, ResultCallBack resultCallBack, int type, ProgressListener listener) {
        if (NetUtils.isNetworkStateed(context)) {
            NetRequest netRequest = new NetRequest(context, url, null, resultCallBack, type);
            netRequest.postUploadImg(imgpath, token, listener);
        }
    }

    public void call(String tel) {
        if (tel.contains("*") || tel == null) {
            TextUtils.toast(this, "对方暂未公开电话号码！");
            return;
        }
        Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + tel));
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    /**
     * 弹出软键盘
     *
     * @param editText
     */
    public void showInPutKeybord(final EditText editText) {
        editText.requestFocus();
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                if (imm != null) {
                    imm.showSoftInput(editText, 0);
                }
            }
        }, 100);
    }

    @Override
    public void onResume() {
        super.onResume();
        MobclickAgent.onResume(this);
    }

    @Override
    public void onPause() {
        super.onPause();
        MobclickAgent.onPause(this);
    }
}
