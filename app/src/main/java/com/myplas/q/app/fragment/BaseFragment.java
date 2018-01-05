package com.myplas.q.app.fragment;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import com.myplas.q.common.netresquset.NetRequest;
import com.myplas.q.common.netresquset.ResultCallBack;
import com.myplas.q.common.utils.NetUtils;
import com.myplas.q.common.view.CommonDialog;
import com.myplas.q.common.view.LoadingDialog;
import com.myplas.q.contact.activity.ContactDetailActivity;
import com.myplas.q.contact.activity.NewContactDetailActivity;
import com.umeng.analytics.MobclickAgent;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Map;

/**
 * Created by 黄双 on 2017/12/19 0019.
 */

public class BaseFragment extends Fragment {
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

    /**
     * get请求
     *
     * @param context
     * @param url
     * @param map
     * @param resultCallBack
     * @param type
     * @param isShowDialog
     */

    public static void getAsyn(Context context, String url, Map<String, String> map, ResultCallBack resultCallBack, int type, boolean isShowDialog) {
        try {
            if (NetUtils.isNetworkStateed(context)) {
                NetRequest netRequest = new NetRequest(context, url, map, resultCallBack, type);
                netRequest.getAsyn();
            }
            if (isShowDialog) {
                LoadingDialog.getInstance(context).show();
            }
        } catch (Exception e) {

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

    public void postAsyn(Context context, String url, Map<String, String> map, ResultCallBack resultCallBack, int type) {
        postAsyn(context, url, map, resultCallBack, type, true);
    }

    /**
     * post请求
     *
     * @param context
     * @param url
     * @param map
     * @param resultCallBack
     * @param type
     * @param isShowDialog
     */
    public void postAsyn(Context context, String url, Map<String, String> map, ResultCallBack resultCallBack, int type, boolean isShowDialog) {
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
     * post之上传图片
     *
     * @param context
     * @param url
     * @param imgpath
     * @param token
     * @param resultCallBack
     * @param type
     */

    public void postUpLoadIMG(Context context, String url, String imgpath, String token, ResultCallBack resultCallBack, int type) {
        if (NetUtils.isNetworkStateed(context)) {
            NetRequest netRequest = new NetRequest(context, url, null, resultCallBack, type);
            netRequest.postUploadImg(imgpath, token, null);
        }
    }


    public void call(String tel) {
        Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + tel));
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    /**
     * 判断是否已经消耗积分
     */
    public void judgeCanSeeDetail(Context context, int type, int httpCode, String message, String mergeThere, String user_id, CommonDialog.DialogShowInterface showInterface) {
        try {
            JSONObject jsonObject = new JSONObject(message);
            String err = jsonObject.getString("code");
            switch (httpCode) {
                case 412:
                    //是否消耗积分
                    if (type == 2 && "99".equals(err)) {
                        String content = jsonObject.getString("message");
                        CommonDialog commonDialog = new CommonDialog();
                        commonDialog.showDialog(context, content, 1, showInterface);
                    }
                    //已经消费了积分 或者 减积分成功
                    boolean b = type == 2 || type == 3;
                    if (b && "0".equals(err)) {
                        Intent intent = getIntent(mergeThere);
                        intent.putExtra("userid", user_id);
                        startActivity(intent);
                    }
                    //积分不够
                    if (type == 3 && !"0".equals(err)) {
                        String content = jsonObject.getString("message");
                        CommonDialog commonDialog = new CommonDialog();
                        commonDialog.showDialog(context, content, ("100".equals(err)) ? (2) : (3), showInterface);
                    }
                    break;
                default:
                    break;
            }
        } catch (Exception e) {

        }
    }

    /**
     * 判断是否跳转到店铺
     *
     * @param flag
     * @return
     */
    public Intent getIntent(String flag) {
        Intent intent = new Intent();
        intent.setClass(getActivity(), "1".equals(flag)
                ? NewContactDetailActivity.class
                : ContactDetailActivity.class);
        return intent;
    }

    public <T extends View> T F(View view, int id) {
        return (T) view.findViewById(id);
    }

    @Override
    public void onResume() {
        super.onResume();
        MobclickAgent.onResume(getActivity());
    }

    @Override
    public void onPause() {
        super.onPause();
        MobclickAgent.onPause(getActivity());
    }
}
