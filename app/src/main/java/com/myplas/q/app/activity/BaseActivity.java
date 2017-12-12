package com.myplas.q.app.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Handler;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.myplas.q.R;
import com.myplas.q.common.netresquset.NetRequest;
import com.myplas.q.common.netresquset.ResultCallBack;
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
    public ImageView mIVConact, mIVLeft;
    public TextView mTextView, mTVLeft, mTVRight;

    private String type;

    /**
     * 实例化titleBar
     */
    public void initTileBar() {
        mFrameLayout = F(R.id.title_bar);
        mIVLeft = F(R.id.titlebar_img_left);
        mTVLeft = F(R.id.titlebar_text_left);
        mIVConact = F(R.id.titlebar_img_right);
        mTVRight = F(R.id.titlebar_text_right);
        mTextView = F(R.id.titlebar_text_title);
        mLayoutBack = F(R.id.titlebar_img_back);
        goBack(mLayoutBack);
    }

    public void setTitle(String title) {
        mTextView.setText(title);
    }

    public void setRightIVVisibility(int isShow) {
        mIVConact.setVisibility(isShow);
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
        mIVConact.setImageResource(resId);
        mIVConact.setVisibility(View.VISIBLE);
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
     */

    public void getAsyn(Activity context
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

    /**
     * post请求
     */

    public static void postAsyn(Context context
            , String url
            , Map<String, String> map
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

    /**
     * post请求
     */

    public static void postAsyn1(Context context
            , String url
            , Map<String, String> map
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

    /**
     * post之上传图片
     */

    public void postUpLoadIMG(Context context
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
