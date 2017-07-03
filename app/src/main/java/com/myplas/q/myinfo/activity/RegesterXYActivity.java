package com.myplas.q.myinfo.activity;

import android.annotation.SuppressLint;
import android.os.Bundle;
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

        webSettings.setJavaScriptEnabled(true);
        webSettings.setUseWideViewPort(true);//设置此属性，可任意比例缩放
        webSettings.setLoadWithOverviewMode(true);
        webSettings.setJavaScriptEnabled(true);
        webSettings.setBuiltInZoomControls(true);
        webSettings.setSupportZoom(true);
        webSettings.setBlockNetworkImage(false);

        String url = "http://q.myplas.com/#/protocol2";
        webView.loadUrl(url);
        webView.addJavascriptInterface(new Object() {
            @SuppressWarnings("unused")
            public void oneClick(final String locX, final String locY) {//此处的参数可传入作为js参数
            }
        }, "document.getElementById('bigCustomerHeader').style.display='none'");//
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
}
