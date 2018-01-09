package com.myplas.q.common.utils;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.util.Log;

import com.myplas.q.common.appcontext.Constant;

import org.json.JSONObject;

import java.io.File;

import cn.ucloud.ufilesdk.Callback;
import cn.ucloud.ufilesdk.UFileRequest;
import cn.ucloud.ufilesdk.UFileSDK;
import cn.ucloud.ufilesdk.UFileUtils;
import cn.ucloud.ufilesdk.task.HttpAsyncTask;


/**
 * @author 黄双
 * @date 2017/12/21 0021
 */

public class UCloudUtils {
    private ACache mACache;
    private Context context;

    private static String bucket;
    private static String authServer;
    private static String proxySuffix;

    private UFileSDK uFileSDK;
    private HttpAsyncTask httpAsyncTask;
    private UCloudListener uCloudListener;

    private static String TAG = "------>UCloudUtils";

    public UCloudUtils(Context context) {
        this.context = context;
        mACache = ACache.get(context);
        bucket = "myplas";
        bucket = mACache.getAsString(Constant.BUCKET);
        authServer = mACache.getAsString(Constant.AUTHSERVER);
        proxySuffix = mACache.getAsString(Constant.PROXYSUFFIX);
        uFileSDK = new UFileSDK(bucket, proxySuffix, authServer);
    }

    public void putFile(final File file) {
        String date = "";
        String httpMethod = "PUT";
        String keyName = file.getName();
        String contentType = "text/plain";
        String contentMd5 = UFileUtils.getFileMD5(file);


        final UFileRequest request = new UFileRequest();
        request.setHttpMethod(httpMethod);
        request.setContentMD5(contentMd5);
        request.setContentType(contentType);
        request.setDate(date);
        request.setKeyName(keyName);

        final ProgressDialog dialog = new ProgressDialog(context);
        httpAsyncTask = uFileSDK.putFile(request, file, keyName, new Callback() {
            @Override
            public void onSuccess(JSONObject response) {
                Log.i(TAG, "onSuccess " + response);
                dialog.dismiss();
                if (uCloudListener != null) {
                    uCloudListener.uCloudCallBack(response.toString());
                }
            }

            @Override
            public void onProcess(long len) {
                int value = (int) (len * 100 / file.length());
                dialog.setProgress(value);
                Log.i(TAG, "progress value is " + value);
                if (uCloudListener != null) {
                    uCloudListener.uCloudProcess(value);
                }
            }

            @Override
            public void onFail(JSONObject response) {
                Log.i(TAG, "onFail " + response);
                dialog.dismiss();
            }
        });

        dialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        dialog.setMax(100);
        dialog.setButton(DialogInterface.BUTTON_NEUTRAL, "取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                httpAsyncTask.cancel();
                dialog.dismiss();
            }
        });
        dialog.show();
    }


    /**
     * 取消请求
     */
    public void cancleRequest() {
        if (httpAsyncTask != null) {
            httpAsyncTask.cancel();
        }
    }


    public interface UCloudListener {
        /**
         * 上传成功后回调
         *
         * @param url
         */
        void uCloudCallBack(String url);

        /**
         * 进度回调
         *
         * @param value
         */
        void uCloudProcess(int value);
    }

    public void setUCloudListener(UCloudListener uCloudListener) {
        this.uCloudListener = uCloudListener;
    }
}
