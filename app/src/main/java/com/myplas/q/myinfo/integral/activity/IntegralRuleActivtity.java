package com.myplas.q.myinfo.integral.activity;

import android.net.http.SslError;
import android.os.Bundle;
import android.view.KeyEvent;
import android.webkit.SslErrorHandler;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.myplas.q.R;
import com.myplas.q.guide.activity.BaseActivity;
import com.myplas.q.common.utils.SharedUtils;
import com.umeng.analytics.MobclickAgent;

import java.io.File;

/**
 * 编写： 黄双
 * 电话：15378412400
 * 邮箱：15378412400@163.com
 * 时间：2017/3/29 09:57
 */
public class IntegralRuleActivtity extends BaseActivity {
    private SharedUtils sharedUtils;
    private WebView webView;
    private static final String APP_CACAHE_DIRNAME = "/webcache";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ingeral_rule_activity_layout);
        goBack(findViewById(R.id.back));
        sharedUtils = SharedUtils.getSharedUtils();
        webView = (WebView) findViewById(R.id.integral_rule_text);
        clearWebViewCache();
        WebSettings webSettings = webView.getSettings();
        //设置WebView属性，能够执行Javascript脚本    
        webSettings.setJavaScriptEnabled(true);
        //设置可以访问文件  
        webSettings.setAllowFileAccess(true);
        //设置支持缩放  
        webSettings.setBuiltInZoomControls(true);

        webSettings.setDomStorageEnabled(true);

        webView.loadUrl("http://q.myplas.com/#/pointsrule2");

        webView.setWebChromeClient(new WebChromeClient());
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && webView.canGoBack()) {
            webView.goBack();// 返回前一个页面
            return true;
        } else {
            return super.onKeyDown(keyCode, event);
        }
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
}
