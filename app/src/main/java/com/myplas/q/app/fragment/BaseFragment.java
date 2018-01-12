package com.myplas.q.app.fragment;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.Fragment;
import android.view.View;

import com.myplas.q.common.netresquset.NetRequest;
import com.myplas.q.common.netresquset.ResultCallBack;
import com.myplas.q.common.utils.NetUtils;
import com.myplas.q.common.view.LoadingDialog;
import com.umeng.analytics.MobclickAgent;

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
