package com.myplas.q.supdem.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.myplas.q.R;
import com.myplas.q.guide.activity.BaseActivity;
import com.myplas.q.guide.activity.ShareActivity;
import com.myplas.q.supdem.adapter.Physical_Property_Adapter;
import com.umeng.analytics.MobclickAgent;

import java.io.File;

/**
 * 编写：黄双
 * 邮箱：15378412400@163.com
 * 时间： 2017/6/271459.
 */

public class Physical_Property_Detail_Activity extends BaseActivity {
    private WebView webView;
    private WebSettings webSettings;
    private LinearLayout layout_share;
    private static final String APP_CACAHE_DIRNAME = "/webcache";
    private TextView physical_variety, physical_remark, physical_maker, physical_content, physical_comment, physical_color, physical_use;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.physical_property_detail_activity_layout);
        goBack(findViewById(R.id.back_img));
        initViews();
    }

    private void initViews() {
        layout_share=F(R.id.share_physical);
        webView = F(R.id.physical_detail_webview);
        physical_use = F(R.id.physical_detail_use);
        physical_maker = F(R.id.physical_detail_maker);
        physical_color = F(R.id.physical_detail_color);
        physical_remark = F(R.id.physical_detail_remark);
        physical_variety = F(R.id.physical_detail_variety);
        physical_content = F(R.id.physical_detail_comment);
        physical_comment = F(R.id.physical_detail_comment);

        webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setUseWideViewPort(true);//设置此属性，可任意比例缩放
        webSettings.setLoadWithOverviewMode(true);
        webSettings.setJavaScriptEnabled(true);
        webSettings.setBuiltInZoomControls(true);
        webSettings.setSupportZoom(true);

        clearWebViewCache();
        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl(getIntent().getStringExtra("url"));

        layout_share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Physical_Property_Detail_Activity.this, ShareActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && webView.canGoBack()) {
            webView.goBack();// 返回前一个页面
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    public void clearWebViewCache() {
        //清理Webview缓存数据库
        try {
            deleteDatabase("webview.db");
            deleteDatabase("webviewCache.db");
        } catch (Exception e) {
            e.printStackTrace();
        }
        //WebView 缓存文件
        File appCacheDir = new File(getFilesDir().getAbsolutePath() + APP_CACAHE_DIRNAME);
        File webviewCacheDir = new File(getCacheDir().getAbsolutePath() + "/webviewCache");
        //删除webview 缓存目录
        if (webviewCacheDir.exists()) {
            deleteFile(webviewCacheDir);
        }
        //删除webview 缓存 缓存目录
        if (appCacheDir.exists()) {
            deleteFile(appCacheDir);
        }
    }

    public void deleteFile(File file) {
        if (file.exists()) {
            if (file.isFile()) {
                file.delete();
            } else if (file.isDirectory()) {
                File files[] = file.listFiles();
                for (int i = 0; i < files.length; i++) {
                    deleteFile(files[i]);
                }
            }
            file.delete();
        } else {
        }
    }
    public void onResume() {
        super.onResume();
        MobclickAgent.onResume(this);
    }
    public void onPause() {
        super.onPause();
        MobclickAgent.onPause(this);
    }
    private <T extends View> T F(int id) {
        return (T) findViewById(id);
    }
}
