package com.myplas.q.versionupdate;

import android.content.Context;
import android.net.Uri;
import android.os.Environment;
import android.widget.Toast;

import com.myplas.q.common.utils.NetUtils;

import java.io.File;


/**
 * Apk下载
 * Created by Song on 2016/11/2.
 */
public class DownloadApk {
    private static InstallInterface installInterface;

    public DownloadApk(InstallInterface installInterface) {
        DownloadApk.installInterface = installInterface;
    }

    /**
     * 下载APK文件
     *
     * @param context
     * @param url
     * @param title
     * @param appName
     */
    public static long downloadApk(Context context, String url, String title, final String appName) {
        File file = context.getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS + "/塑料圈通讯录.apk");
        String path = null;
        path = file.getAbsolutePath();
        Uri downloadFileUri = Uri.parse("file://" + path);

        //删除下载任务以及文件
        DownloadApk.removeFile(path);
        return start(context, url, title, appName);
    }

    /**
     * 开始下载
     *
     * @param context
     * @param url
     * @param title
     * @param appName
     */
    private static long start(Context context, String url, String title, String appName) {
        long id = 0;
        if (hasSDKCard()) {
            if (NetUtils.isNetworkStateed(context)) {
                id = DownLoadUtils.getInstance(context).download(url, title, "下载完成后点击打开", appName);
            }
        } else {
            Toast.makeText(context, "手机未安装SD卡，下载失败", Toast.LENGTH_LONG).show();
        }
        return id;
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

    //如果有安装包则直接安装
    public interface InstallInterface {
        void install();
    }
}
