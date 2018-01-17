package com.myplas.q.common.utils;

import android.content.Context;
import android.util.Log;

import org.json.JSONObject;

import java.io.File;
import java.util.Calendar;
import java.util.Random;

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

    private Random random;
    private static String TAG = "------>UCloudUtils";

    public UCloudUtils(Context context, UCloudListener uCloudListener) {
        this.context = context;
        this.uCloudListener = uCloudListener;

        random = new Random();
        mACache = ACache.get(context);

        bucket = "myplas";
        proxySuffix = ".ufile.ucloud.com.cn";
        authServer = "http://api.91su.cn";

        uFileSDK = new UFileSDK(bucket, proxySuffix, authServer);
    }

    /**
     * 上传文件
     *
     * @param file
     */
    public void putFile(final File file, final int type) {
        String date = "";
        String httpMethod = "PUT";
        final String keyName = getFileName();
        String contentType = "text/plain";
        String contentMd5 = UFileUtils.getFileMD5(file);

        final UFileRequest request = new UFileRequest();
        request.setHttpMethod(httpMethod);
        request.setContentMD5(contentMd5);
        request.setContentType(contentType);
        request.setDate(date);
        request.setKeyName(keyName);

        httpAsyncTask = uFileSDK.putFile(request, file, keyName, new Callback() {
            @Override
            public void onSuccess(JSONObject response) {
                Log.e(TAG, "onSuccess " + response);
                if (uCloudListener != null) {
                    uCloudListener.uCloudSucess(type, keyName);
                }
            }

            @Override
            public void onProcess(long len) {
                int value = (int) (len * 100 / file.length());
                if (uCloudListener != null) {
                    uCloudListener.uCloudProcess(type, value);
                }
            }

            @Override
            public void onFail(JSONObject response) {
                TextUtils.toast(context, "图片上传失败！");
            }
        });

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
         * @param type
         * @param flieName
         */
        void uCloudSucess(int type, String flieName);

        /**
         * 进度回调
         *
         * @param value
         * @param type
         * @param flieName
         */
        void uCloudProcess(int type, int value);
    }


    /**
     * 生成文件名字
     *
     * @return
     */
    public String getFileName() {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH) + 1;
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        String str = getRandomString(10);
        return new StringBuffer().append("upload/")
                .append(year)
                .append("/")
                .append(month)
                .append("/")
                .append(str)
                .append(".jpg").toString();
    }

    /**
     * 获取一个随机字符串
     *
     * @param length
     * @return
     */
    public String getRandomString(int length) {
        String base = "abcdefghijklmnopqrstuvwxyz12345657890";
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < length; i++) {
            int number = random.nextInt(base.length());
            sb.append(base.charAt(number));
        }
        return sb.toString();
    }
}
