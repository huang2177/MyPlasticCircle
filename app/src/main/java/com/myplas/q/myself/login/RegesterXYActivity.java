package com.myplas.q.myself.login;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.webkit.JavascriptInterface;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.myplas.q.R;
import com.myplas.q.guide.activity.BaseActivity;
import com.umeng.analytics.MobclickAgent;

/**
 * 编写： 黄双
 * 电话：15378412400
 * 邮箱：15378412400@163.com
 * 时间：2017/3/24 11:41
 */
public class RegesterXYActivity extends BaseActivity {
    private WebView webView;
    private String mimeType = "textselecthandle/html";
    private String encoding = "utf-8";

    @SuppressLint("JavascriptInterface")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.wd_layout_xy);
        goBack(findViewById(R.id.back));
        webView = (WebView) findViewById(R.id.xy_web_re);
        WebSettings webSettings = webView.getSettings();

        webSettings.setSupportZoom(true);
        webSettings.setUseWideViewPort(true);//设置此属性，可任意比例缩放
        webSettings.setDomStorageEnabled(true);
        webSettings.setJavaScriptEnabled(true);
        webSettings.setBlockNetworkImage(false);
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true);

        webSettings.setBuiltInZoomControls(true);
        webSettings.setLoadWithOverviewMode(true);

        webView.addJavascriptInterface(new AndroidtoJs(), "test");//AndroidtoJS类对象映射到js的test对象
        String url = "http://q.myplas.com/#/protocol2";
//        webView.loadUrl("file:///android_asset/demo.html");
        webView.loadUrl(url);
    }

    class MyWebClient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
            webView.loadUrl("javascript:" + "");
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

    // 继承自Object类
    public class AndroidtoJs extends Object {

        // 定义JS需要调用的方法
        // 被JS调用的方法必须加入@JavascriptInterface注解
        @JavascriptInterface
        public void hello(String msg) {
            Log.e("-------", msg + "-----");
        }
    }

}
