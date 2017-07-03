package com.myplas.q.appupdate;

import android.app.DownloadManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Environment;
import android.support.v4.content.FileProvider;
import android.util.Log;

import com.myplas.q.common.utils.SystemUtils;
import com.myplas.q.common.utils.TextUtils;

import java.io.File;


/**
 * 封装 DownLoadManager 下载
 * Created by Song on 2016/11/2.
 */
public class DownLoadUtils {

    private Context mContext;
    private DownloadManager mDownloadManager;
    private static volatile DownLoadUtils instance;

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
        //date_selected.构建下载请求
        DownloadManager.Request downloadRequest = new DownloadManager.Request(Uri.parse(uri));
        downloadRequest.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_WIFI | DownloadManager.Request.NETWORK_MOBILE);
        downloadRequest.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
        /**设置漫游状态下是否可以下载*/
        downloadRequest.setAllowedOverRoaming(false);
        /**如果我们希望下载的文件可以被系统的Downloads应用扫描到并管理，
         我们需要调用Request对象的setVisibleInDownloadsUi方法，传递参数true.*/
        downloadRequest.setVisibleInDownloadsUi(true);
        //文件保存位置
        downloadRequest.setDestinationInExternalFilesDir(mContext, Environment.DIRECTORY_DOWNLOADS, appName + ".apk");
        // 设置一些基本显示信息
        downloadRequest.setTitle(title);
        downloadRequest.setDescription(description);
        return mDownloadManager.enqueue(downloadRequest);//异步请求
    }

    public DownloadManager getDownloadManager() {
        return mDownloadManager;
    }

    /**
     * 判断下载管理程序是否可用
     *
     * @return
     */
    public boolean canDownload() {

        try {
            int state = mContext.getPackageManager().getApplicationEnabledSetting("com.android.providers.downloads");
            if (state == PackageManager.COMPONENT_ENABLED_STATE_DISABLED
                    || state == PackageManager.COMPONENT_ENABLED_STATE_DISABLED_USER
                    || state == PackageManager.COMPONENT_ENABLED_STATE_DISABLED_UNTIL_USED) {
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    /**
     * 进入 启用/禁用 下载管理程序界面
     */
    public void skipToDownloadManager() {
        String packageName = "com.myplas.q";
        Intent intent = new Intent(android.provider.Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        intent.setData(Uri.parse("package:" + packageName));
        mContext.startActivity(intent);
    }

    //安装
    public static void installApk(Context context) {
        try {
            Intent install = new Intent(Intent.ACTION_VIEW);
            Uri downloadFileUri;
            File file = context.getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS + "/塑料圈通讯录.apk");
            if (file != null) {
                String path = file.getAbsolutePath();
                downloadFileUri = Uri.parse("file://" + path);
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
            } else {
                TextUtils.Toast(context, "安装失败");
            }
        } catch (Exception e) {
        }
    }

    //卸载
    public void unInstallApp() {
        Uri packageURI = Uri.parse("package:com.myplas.q");
        //创建Intent意图  
        Intent intent = new Intent(Intent.ACTION_DELETE);
        //设置Uri  
        intent.setData(packageURI);
        //卸载程序  
        mContext.startActivity(intent);
    }
}
