package com.myplas.q.versionupdate;

import android.app.DownloadManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.support.v4.content.FileProvider;

import com.myplas.q.common.utils.SystemUtils;
import com.myplas.q.common.utils.TextUtils;

import java.io.File;


/**
 * 封装 DownLoadManager 下载
 * Created by Song on 2016/11/2.
 */
public class DownLoadUtils {
    private long downloadId;
    private Context mContext;
    private DownloadManager mDownloadManager;

    private static volatile DownLoadUtils instance;

    public DownloadManager getDownloadManager() {
        return mDownloadManager;
    }

    private DownLoadUtils(Context context) {
        this.mContext = context.getApplicationContext();
        mDownloadManager = (DownloadManager) context.getSystemService(Context.DOWNLOAD_SERVICE);
    }
    /**
     * 获取单例对象
     *
     * @param context
     * @return
     */
    public static DownLoadUtils getInstance(Context context) {
        if (instance == null) {
            synchronized (DownLoadUtils.class) {
                if (instance == null) {
                    instance = new DownLoadUtils(context);
                    return instance;
                }
            }
        }
        return instance;
    }

    /**
     * 下载
     *
     * @param uri
     * @param title
     * @param description
     * @param appName
     * @return downloadId
     */
    public long download(String uri, String title, String description, String appName) {
        DownloadManager.Request downloadRequest = new DownloadManager.Request(Uri.parse(uri));
        downloadRequest.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_WIFI | DownloadManager.Request.NETWORK_MOBILE);
        downloadRequest.setNotificationVisibility(DownloadManager.Request.VISIBILITY_HIDDEN);
        /**设置漫游状态下是否可以下载*/
        downloadRequest.setAllowedOverRoaming(false);
        /**如果我们希望下载的文件可以被系统的Downloads应用扫描到并管理，
         我们需要调用Request对象的setVisibleInDownloadsUi方法，传递参数true.*/
        downloadRequest.setVisibleInDownloadsUi(true);
        //文件保存位置
        downloadRequest.setDestinationInExternalFilesDir(mContext, Environment.DIRECTORY_DOWNLOADS, appName + ".apk");
        return mDownloadManager.enqueue(downloadRequest);//异步请求
    }


    //安装
    public static void installApk(Context context) {
        try {
            Intent install = new Intent(Intent.ACTION_VIEW);
            File file = context.getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS + "/塑料圈通讯录.apk");
            if (file != null) {
                String path = file.getAbsolutePath();
                Uri downloadFileUri = Uri.parse("file://" + path);
                if (SystemUtils.getSystemInfo() <= 23) {
                    install.setDataAndType(downloadFileUri, "application/vnd.android.package-archive");
                    install.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                } else {
                    install.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                    Uri contentUri = FileProvider.getUriForFile(context, "com.myplas.q.provider", new File(path));
                    install.setDataAndType(contentUri, "application/vnd.android.package-archive");
                    install.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                }
                context.startActivity(install);
            }
        } catch (Exception e) {
            TextUtils.Toast(context, "安装失败!");
        }
    }
}
