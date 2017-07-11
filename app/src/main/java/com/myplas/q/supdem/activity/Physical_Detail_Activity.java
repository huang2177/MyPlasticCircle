package com.myplas.q.supdem.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.myplas.q.R;
import com.myplas.q.common.api.API;
import com.myplas.q.common.netresquset.ResultCallBack;
import com.myplas.q.guide.activity.BaseActivity;
import com.myplas.q.guide.activity.ShareActivity;
import com.myplas.q.supdem.Beans.PhysicalDetailBean;
import com.umeng.analytics.MobclickAgent;

import org.json.JSONObject;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 * 编写：黄双
 * 邮箱：15378412400@163.com
 * 时间： 2017/6/271459.
 */

public class Physical_Detail_Activity extends BaseActivity implements ResultCallBack {
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
        getPhysical_Detail();
    }

    private void initViews() {
        layout_share = F(R.id.share_physical);
        webView = F(R.id.physical_detail_webview);
        physical_use = F(R.id.physical_detail_use);
        physical_content = F(R.id.physical_content);
        physical_maker = F(R.id.physical_detail_maker);
        physical_color = F(R.id.physical_detail_color);
        physical_remark = F(R.id.physical_detail_remark);
        physical_variety = F(R.id.physical_detail_variety);
        physical_comment = F(R.id.physical_detail_comment);

        webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setDefaultTextEncodingName("UTF-8");
        webSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NARROW_COLUMNS);

        WindowManager wm = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
        int width = wm.getDefaultDisplay().getWidth();
        if (width > 650) {
            webView.setInitialScale(240);
        } else if (width > 520) {
            webView.setInitialScale(180);
        } else if (width > 450) {
            webView.setInitialScale(160);
        } else if (width > 300) {
            webView.setInitialScale(140);
        } else {
            webView.setInitialScale(120);
        }

        clearWebViewCache();
        webView.setWebViewClient(new WebViewClient());

        layout_share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Physical_Detail_Activity.this, ShareActivity.class);
                startActivity(intent);
            }
        });
    }

    //获取数据
    public void getPhysical_Detail() {
        Map map = new HashMap();
        map.put("lid", getIntent().getStringExtra("lid"));
        postAsyn(this, API.BASEURL + API.PHYSICAL_DETAIL, map, this, 1);
    }

    @Override
    public void callBack(Object object, int type) {
        try {
            if (new JSONObject(object.toString()).getString("err").equals("0")) {
                Gson gson = new Gson();
                PhysicalDetailBean bean = gson.fromJson(object.toString(), PhysicalDetailBean.class);
                showInfo(bean);
            }
        } catch (Exception e) {
        }
    }

    private void showInfo(PhysicalDetailBean bean) {
        physical_variety.setText(bean.getType());
        physical_remark.setText(bean.getName());
        physical_maker.setText("  " + bean.getCompany());
        physical_content.setText("特性备注：" + bean.getRemark());

        physical_comment.setText("特性备注：" + bean.getRemark());
        physical_color.setText("外观颜色：" + bean.getColor());
        physical_use.setText(bean.getPurpose());

        String html = "<style>\n" +
                "*{ margin:0; padding:0;}" +
                "table { border-collapse: collapse;border-spacing: 0;}" +
                "#wxbTable{ background: #FFFFFF; width:100%;}\n" +
                "#wxbTable table{ border: 1px solid #D9D9D9; background: #FFFFFF;}\n" +
                "#wxbTable td { border: 1px solid #D9D9D9; text-align: center; color: #646464; height: 30px;}\n" +
                "</style>\n" +
                "<div id=\"wxbTable\">" + bean.getParams() + "</div>";
        webView.loadData(html, "text/html;charset=UTF-8", null);
//      webView.loadDataWithBaseURL(null, html, "textselecthandle/html", "UTF-8", null);
    }

    @Override
    public void failCallBack(int type) {

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
}
