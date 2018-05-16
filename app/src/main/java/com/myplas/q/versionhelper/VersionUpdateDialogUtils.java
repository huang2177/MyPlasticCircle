package com.myplas.q.versionhelper;

import android.app.Dialog;
import android.app.DownloadManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.ContentObserver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Handler;
import android.support.v4.content.LocalBroadcastManager;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.myplas.q.R;

/**
 * 电话：15378412400
 * 邮箱：15378412400@163.com
 * 时间：2017/5/16 20:05
 * @author 黄双
 */
public class VersionUpdateDialogUtils {
    private int type;
    private Context mContext;
    private String url, title;

    private Button buttonOk;
    private Dialog normalDialog;
    private TextView textviewContent;
    private ProgressBar mNumberProgressBar;

    private long downloadId;
    private DownloadApk downloadApk;
    private MyReceiverDownLoad myReceiver;
    private DownloadChangeObserver downloadObserver;
    private VersionUpdateInterface versionUpdateInterface;

    public Uri CONTENT_URI = Uri.parse("content://downloads/my_downloads");

    public VersionUpdateDialogUtils(Context context, String title, String url) {
        this.url = url;
        this.title = title;
        this.mContext = context;
        //注册app更新广播；
        myReceiver = new MyReceiverDownLoad();
        context.registerReceiver(myReceiver, new IntentFilter(DownloadManager.ACTION_DOWNLOAD_COMPLETE));
    }

    /**
     * 弹出dialog 点击安装
     */
    public void showDialog(final boolean isForce) {
        View view = View.inflate(mContext, R.layout.dialog_layout_appupdate, null);
        buttonOk = (Button) view.findViewById(R.id.btn_ok);
        textviewContent = (TextView) view.findViewById(R.id.dialog_message);
        mNumberProgressBar = (ProgressBar) view.findViewById(R.id.versionupdate_numberbar);

        normalDialog = new Dialog(mContext, R.style.commondialog_style);
        normalDialog.setCanceledOnTouchOutside(false);
        normalDialog.setContentView(view);
        normalDialog.show();

        buttonOk.setClickable(true);
        textviewContent.setText(title);
        setDialogWindowAttr(normalDialog, mContext);

        buttonOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RelativeLayout.LayoutParams lp = (RelativeLayout.LayoutParams) buttonOk.getLayoutParams();
                lp.bottomMargin = 70;
                buttonOk.setLayoutParams(lp);

                buttonOk.setClickable(false);
                mNumberProgressBar.setProgress(0);
                mNumberProgressBar.setVisibility(View.VISIBLE);
                buttonOk.setBackgroundResource(R.drawable.btn_download);

                downloadApk = new DownloadApk();
                downloadId = DownloadApk.downloadApk(mContext, url, "塑料圈通讯录更新", "塑料圈通讯录");
                downloadObserver = new DownloadChangeObserver(null);
                mContext.getContentResolver()
                        .registerContentObserver(CONTENT_URI, true, downloadObserver);
            }
        });
        normalDialog.setOnKeyListener(new DialogInterface.OnKeyListener() {
            @Override
            public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_BACK && versionUpdateInterface != null) {
                    versionUpdateInterface.exitCallBack();
                }
                return isForce;
            }
        });
    }



    /**
     * 设置dialog属性
     */
    public void setDialogWindowAttr(Dialog dlg, Context ctx) {
        WindowManager wm = (WindowManager) ctx.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics outMetrics = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(outMetrics);
        int width = outMetrics.widthPixels;
        int height = outMetrics.heightPixels;

        Window window = dlg.getWindow();
        WindowManager.LayoutParams lp = window.getAttributes();
        lp.gravity = Gravity.CENTER;
        lp.width = (width * 3) / 5;//宽高可设置具体大小
        lp.height = (int) (height / 2.2);
        dlg.getWindow().setAttributes(lp);
    }

    class DownloadChangeObserver extends ContentObserver {

        public DownloadChangeObserver(Handler handler) {
            super(handler);
        }

        @Override
        public void onChange(boolean selfChange) {
            queryDownloadStatus();
        }
    }

    /**
     * 更新状态
     */
    private void queryDownloadStatus() {
        DownloadManager.Query query = new DownloadManager.Query();
        query.setFilterById(downloadId);
        Cursor c = DownLoadUtils.getInstance(mContext).getDownloadManager().query(query);
        if (c != null && c.moveToFirst()) {
            int status = c.getInt(c.getColumnIndex(DownloadManager.COLUMN_STATUS));

            int reasonIdx = c.getColumnIndex(DownloadManager.COLUMN_REASON);
            int titleIdx = c.getColumnIndex(DownloadManager.COLUMN_TITLE);
            int fileSizeIdx =
                    c.getColumnIndex(DownloadManager.COLUMN_TOTAL_SIZE_BYTES);
            int bytesDLIdx =
                    c.getColumnIndex(DownloadManager.COLUMN_BYTES_DOWNLOADED_SO_FAR);
            String title = c.getString(titleIdx);
            int fileSize = c.getInt(fileSizeIdx);
            int bytesDL = c.getInt(bytesDLIdx);

            // Translate the pause reason to friendly text.
            int reason = c.getInt(reasonIdx);
            StringBuilder sb = new StringBuilder();
            sb.append(title).append("\n");
            sb.append("Downloaded ").append(bytesDL).append(" / ").append(fileSize);

            float precent = (float) bytesDL / fileSize * 100;
            mNumberProgressBar.setProgress((int) precent);
            switch (status) {
                case DownloadManager.STATUS_FAILED:
                    //清除已下载的内容，重新下载
                    DownLoadUtils.getInstance(mContext).getDownloadManager().remove(downloadId);
                    break;
                default:
                    break;
            }
        }
    }

    /**
     * 下载完成后点安装
     */

    public class MyReceiverDownLoad extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals(DownloadManager.ACTION_DOWNLOAD_COMPLETE)) {
                if (buttonOk != null) {
                    buttonOk.setClickable(true);
                    buttonOk.setBackgroundResource(R.drawable.btn_refresh);

                    RelativeLayout.LayoutParams lp = (RelativeLayout.LayoutParams) buttonOk.getLayoutParams();
                    lp.bottomMargin = 40;
                    buttonOk.setLayoutParams(lp);

                    mNumberProgressBar.setVisibility(View.GONE);
                }
                DownLoadUtils.installApk(mContext);
            }
        }
    }

    /**
     * 注销广播
     */
    public void unregisterReceiver() {
        mContext.unregisterReceiver(myReceiver);
        mContext.getContentResolver().unregisterContentObserver(downloadObserver);
    }

    public interface VersionUpdateInterface {
        void exitCallBack();
    }

    public void setVersionUpdateInterface(VersionUpdateInterface versionUpdateInterface) {
        this.versionUpdateInterface = versionUpdateInterface;
    }
}
