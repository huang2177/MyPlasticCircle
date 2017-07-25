package com.myplas.q.appupdate;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Environment;
import android.widget.Toast;

import com.myplas.q.common.utils.GetNumUtil;
import com.myplas.q.common.utils.NetUtils;

import java.io.File;


/**
 * Apk下载
 * Created by Song on 2016/11/2.
 */
public class DownloadApk {
    static InstallInterface installInterface;

    //private static MainActivity.MyReceiver apkInstallReceiver;
    public DownloadApk(InstallInterface installInterface) {
        this.installInterface = installInterface;
    }

    /**
     * 下载APK文件
     *
     * @param context
     * @param url
     * @param title
     * @param appName
     */
    public static void downloadApk(Context context, String url, String title, final String appName) {
        File file = context.getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS + "/塑料圈通讯录.apk");
        String path = null;
        path = file.getAbsolutePath();
        Uri downloadFileUri = Uri.parse("file://" + path);

        //比较是否安装的版本是一样
        if (fileIsExists(path) && compare(getApkInfo(context, downloadFileUri.getPath()), context)) {
            installInterface.install();
        } else {
            //删除下载任务以及文件
            DownloadApk.removeFile(path);
            start(context, url, title, appName);
        }
    }

    //如果有安装包则直接安装
    public interface InstallInterface {
        void install();
    }

    /**
     * 开始下载
     *
     * @param context
     * @param url
     * @param title
     * @param appName
     */
    private static void start(Context context, String url, String title, String appName) {
        if (hasSDKCard()) {
            if (NetUtils.isNetworkStateed(context)) {
                DownLoadUtils.getInstance(context).download(url, title, "下载完成后点击打开", appName);
            }
        } else {
            Toast.makeText(context, "手机未安装SD卡，下载失败", Toast.LENGTH_LONG).show();
        }
    }

    /**
     * 跳转到安装界面
     *
     * @param context
     * @param uri
     */
    public static void startInstall(Context context, Uri uri) {
        Intent install = new Intent(Intent.ACTION_VIEW);
        install.setDataAndType(uri, "application/vnd.android.package-archive");
        install.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(install);
    }

    /**
     * 获取APK程序信息
     *
     * @param context
     * @param path
     * @return
     */
    private static PackageInfo getApkInfo(Context context, String path) {

        PackageManager pm = context.getPackageManager();
        PackageInfo pi = pm.getPackageArchiveInfo(path, PackageManager.GET_ACTIVITIES);
        if (null != pi) {
            return pi;
        }
        return null;
    }


    /**
     * 比较两个APK的信息
     *
     * @param apkInfo
     * @param context
     * @return
     */
    private static boolean compare(PackageInfo apkInfo, Context context) {
        if (null == apkInfo) {
            return false;
        }
        //本地的apk版本
        String localPackageName = context.getPackageName();
        if (localPackageName.equals(apkInfo.packageName)) {
            try {
                PackageInfo packageInfo = context.getPackageManager().getPackageInfo(localPackageName, 0);
                //比较当前APK和下载的APK版本号
                if (GetNumUtil.getNum(apkInfo.versionName) > GetNumUtil.getNum(packageInfo.versionName)) {
                    //如果下载的APK版本号大于当前安装的APK版本号，返回true
                    return true;
                }
            } catch (PackageManager.NameNotFoundException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    /**
     * 是否存在SD卡
     */
    private static boolean hasSDKCard() {
        return Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED);
    }

    /**
     * 删除已下载的文件
     */
    public static void removeFile(String path) {
        //String filePath = SystemParam.getInstance().getString("downloadApk", null);
        if (null != path) {
            File downloadFile = new File(path);
            if (null != downloadFile && downloadFile.exists()) {
                //删除之前先判断用户是否已经安装了，安装了才删除。
                downloadFile.delete();
            }
        }
    }

    public static boolean fileIsExists(String path) {
        try {
            File f = new File(path);
            if (f.exists()) {
                return f.isFile();
            } else {
                return false;
            }
        } catch (Exception e) {
            return false;
        }
    }
}
