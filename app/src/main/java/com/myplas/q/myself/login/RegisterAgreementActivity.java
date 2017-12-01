package com.myplas.q.myself.login;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.myplas.q.R;
import com.myplas.q.app.activity.BaseActivity;

/**
 * 编写： 黄双
 * 电话：15378412400
 * 邮箱：15378412400@163.com
 * 时间：2017/3/24 11:41
 */
public class RegisterAgreementActivity extends BaseActivity {
    private WebView webView;
    private String mimeType = "textselecthandle/html";
    private String encoding = "utf-8";

    @SuppressLint("JavascriptInterface")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.wd_layout_xy);
        initTileBar();
        setTitle("注册协议");

        webView = F(R.id.xy_web_re);
        WebSettings webSettings = webView.getSettings();

        webSettings.setSupportZoom(true);
        webSettings.setUseWideViewPort(true);//设置此属性，可任意比例缩放
        webSettings.setDomStorageEnabled(true);
        webSettings.setJavaScriptEnabled(true);
        webSettings.setBlockNetworkImage(false);
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true);

        webSettings.setBuiltInZoomControls(true);
        webSettings.setLoadWithOverviewMode(true);

        String url = "http://q.myplas.com/#/protocol";
        webView.loadUrl(url);
    }

}
