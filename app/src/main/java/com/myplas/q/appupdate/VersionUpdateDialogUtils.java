package com.myplas.q.appupdate;

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
 * 编写： 黄双
 * 电话：15378412400
 * 邮箱：15378412400@163.com
 * 时间：2017/5/16 20:05
 */
public class VersionUpdateDialogUtils implements DownloadApk.InstallInterface {
    private int type;
    private Context mContext;
    private String url, title;

    private Button button_ok;
    private Dialog normalDialog;
    private TextView textView_content;
    private ProgressBar mNumberProgressBar;

    private long downloadId;
    private DownloadApk downloadApk;
    private MyReceiver_DownLoad myReceiver;
    private DownloadChangeObserver downloadObserver;
    private VersionUpdateInterface versionUpdateInterface;

    public Uri CONTENT_URI = Uri.parse("content://downloads/my_downloads");

    public VersionUpdateDialogUtils(Context context, String title, String url) {
        this.url = url;
        this.title = title;
        this.mContext = context;
        //注册app更新广播；
        myReceiver = new MyReceiver_DownLoad();
        context.registerReceiver(myReceiver, new IntentFilter(DownloadManager.ACTION_DOWNLOAD_COMPLETE));
    }

    //弹出dialog 点击安装
    public void showDialog() {
        View view = View.inflate(mContext, R.layout.dialog_layout_appupdate, null);
        button_ok = (Button) view.findViewById(R.id.btn_ok);
        textView_content = (TextView) view.findViewById(R.id.dialog_message);
        mNumberProgressBar = (ProgressBar) view.findViewById(R.id.versionupdate_numberbar);

        normalDialog = new Dialog(mContext, R.style.commondialog_style);
        normalDialog.setCanceledOnTouchOutside(false);
        normalDialog.setContentView(view);
        normalDialog.show();

        button_ok.setClickable(true);
        textView_content.setText(title);
        setDialogWindowAttr(normalDialog, mContext);

        button_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RelativeLayout.LayoutParams lp = (RelativeLayout.LayoutParams) button_ok.getLayoutParams();
                lp.bottomMargin = 70;
                button_ok.setLayoutParams(lp);

                button_ok.setClickable(false);
                mNumberProgressBar.setProgress(0);
                mNumberProgressBar.setVisibility(View.VISIBLE);
                button_ok.setBackgroundResource(R.drawable.btn_download);

                downloadApk = new DownloadApk(VersionUpdateDialogUtils.this);
                downloadId = downloadApk.downloadApk(mContext, url, "塑料圈通讯录更新", "塑料圈通讯录");
                downloadObserver = new DownloadChangeObserver(null);
                mContext.getContentResolver()
                        .registerContentObserver(CONTENT_URI, true, downloadObserver);
            }
        });
        normalDialog.setOnKeyListener(new DialogInterface.OnKeyListener() {
            @Override
            public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_BACK) {
                    versionUpdateInterface.exitCallBack();
                }
                return true;
            }
        });
    }

    //如果存在安装包就直接安装
    @Override
    public void install() {
        if (button_ok != null) {
            button_ok.setClickable(true);
            button_ok.setBackgroundResource(R.drawable.btn_refresh);
        }
        DownLoadUtils.getInstance(mContext).installApk(mContext);
    }

    public interface VersionUpdateInterface {
        void exitCallBack();
    }

    public void setVersionUpdateInterface(VersionUpdateInterface versionUpdateInterface) {
        this.versionUpdateInterface = versionUpdateInterface;
    }

    //设置dialog属性
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
            // TODO Auto-generated constructor stub
        }

        @Override
        public void onChange(boolean selfChange) {
            queryDownloadStatus();
        }
    }

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
                case DownloadManager.STATUS_PAUSED:
                    Log.v("tag", "STATUS_PAUSED");
                case DownloadManager.STATUS_PENDING:
                    Log.v("tag", "STATUS_PENDING");
                case DownloadManager.STATUS_RUNNING:
                    //正在下载，不做任何事情
                    Log.v("tag", "STATUS_RUNNING");
                    break;
                case DownloadManager.STATUS_SUCCESSFUL:
                    //完成
                    Log.v("tag", "下载完成");
//                  dowanloadmanager.remove(lastDownloadId);
                    break;
                case DownloadManager.STATUS_FAILED:
                    //清除已下载的内容，重新下载
                    Log.v("tag", "STATUS_FAILED");
                    DownLoadUtils.getInstance(mContext).getDownloadManager().remove(downloadId);
                    break;
            }
        }
    }

    //下载完成后点安装
    public class MyReceiver_DownLoad extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals(DownloadManager.ACTION_DOWNLOAD_COMPLETE)) {
                if (button_ok != null) {
                    button_ok.setClickable(true);
                    button_ok.setBackgroundResource(R.drawable.btn_refresh);

                    RelativeLayout.LayoutParams lp = (RelativeLayout.LayoutParams) button_ok.getLayoutParams();
                    lp.bottomMargin = 40;
                    button_ok.setLayoutParams(lp);

                    mNumberProgressBar.setVisibility(View.GONE);
                }
                DownLoadUtils.getInstance(mContext).installApk(mContext);
            }
        }
    }

    public void unregisterReceiver() {
        mContext.unregisterReceiver(myReceiver);
        mContext.getContentResolver().unregisterContentObserver(downloadObserver);
    }
}
