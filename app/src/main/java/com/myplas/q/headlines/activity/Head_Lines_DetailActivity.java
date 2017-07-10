package com.myplas.q.headlines.activity;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.myplas.q.R;
import com.myplas.q.common.utils.ScreenUtils;
import com.myplas.q.guide.activity.BaseActivity;
import com.myplas.q.guide.activity.MainActivity;
import com.myplas.q.guide.activity.ShareActivity;
import com.myplas.q.common.netresquset.ResultCallBack;
import com.myplas.q.common.utils.SharedUtils;
import com.myplas.q.common.utils.TextUtils;
import com.myplas.q.common.view.MyListview;
import com.myplas.q.headlines.adapter.Find_Detail_More_ListviewAdapetr;
import com.myplas.q.common.api.API;
import com.myplas.q.headlines.bean.SucribleDetailBean;
import com.myplas.q.release.activity.ReleaseActivity;
import com.umeng.analytics.MobclickAgent;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static android.R.attr.width;

/**
 * 编写： 黄双
 * 电话：15378412400
 * 邮箱：15378412400@163.com
 * 时间：2017/3/21 16:18
 */
public class Head_Lines_DetailActivity extends BaseActivity implements ResultCallBack, View.OnClickListener {
    private WebView webView;
    private MyListview listview;
    private Resources resources;
    private WebSettings webSettings;
    private SharedUtils sharedUtils;
    private LinearLayout linearLayout_share;
    private SucribleDetailBean sucribleDetailBean;
    private TextView textView_tt_title, textView_shj;
    private TextView textView_title, textView_content;
    private ImageView imageView_btn_next, imageView_btn_last;
    private TextView textView_gq, textView_wd, textView_fx, textView_txl;
    private ImageView imageView_gq, imageView_wd, imageView_fx, imageView_txl;
    private LinearLayout layout_gq, layout_txl, layout_jia, layout_fx, layout_wd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_topline_detail_activity);
        goBack(findViewById(R.id.img_back));

        resources = getResources();
        sharedUtils = SharedUtils.getSharedUtils();

        webView = F(R.id.find_detail_text);
        textView_content = F(R.id.hot_search);
        imageView_btn_last = F(R.id.btn_last);
        imageView_btn_next = F(R.id.btn_next);
        textView_title = F(R.id.fx_ttxq_title);
        textView_shj = F(R.id.fx_tt_title_shj);
        listview = F(R.id.find_detail_mliatview);
        textView_tt_title = F(R.id.fx_tt_title_text);
        linearLayout_share = F(R.id.wd_linear_share);

        layout_gq = F(R.id.buttom_linear_gq);
        layout_txl = F(R.id.buttom_linear_txl);
        layout_jia = F(R.id.buttom_linear_jia);
        layout_wd = F(R.id.buttom_linear_wd);
        layout_fx = F(R.id.buttom_linear_fx);

        imageView_fx = F(R.id.buttom_img_fx);
        imageView_gq = F(R.id.buttom_img_gq);
        imageView_txl = F(R.id.buttom_img_txl);
        imageView_wd = F(R.id.buttom_img_wd);

        textView_fx = F(R.id.buttom_text_fx);
        textView_gq = F(R.id.buttom_text_gq);
        textView_txl = F(R.id.buttom_text_txl);
        textView_wd = F(R.id.buttom_text_wd);

        layout_fx.setOnClickListener(this);
        layout_wd.setOnClickListener(this);
        layout_jia.setOnClickListener(this);
        layout_txl.setOnClickListener(this);
        layout_gq.setOnClickListener(this);
        imageView_btn_last.setOnClickListener(this);
        imageView_btn_next.setOnClickListener(this);
        linearLayout_share.setOnClickListener(this);

        textView_title.setText(getIntent().getStringExtra("title"));

        webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setDefaultTextEncodingName("UTF-8");
        webSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NARROW_COLUMNS);
        webView.setWebViewClient(new WebViewClient());

        WindowManager wm = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
        int width = wm.getDefaultDisplay().getWidth();
        if (width > 650) {
            webView.setInitialScale(250);
        } else if (width > 520) {
            webView.setInitialScale(200);
        } else if (width > 450) {
            webView.setInitialScale(180);
        } else if (width > 300) {
            webView.setInitialScale(160);
        } else {
            webView.setInitialScale(140);
        }
        webView.setWebViewClient(new WebViewClient());

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                getNetData(sucribleDetailBean.getInfo().getSubscribe().get(position).getId());
            }
        });

        firstInto();
        getNetData(getIntent().getStringExtra("id"));
    }

    public void getNetData(String id) {
        Map<String, String> map = new HashMap<String, String>();
        map.put("id", id);
        map.put("token", sharedUtils.getData(this, "token"));
        BaseActivity.postAsyn(this, API.BASEURL + API.GET_DETAIL_INFO, map, this, 1);
    }

    @Override
    public void callBack(Object object, int type) {
        try {
            Gson gson = new Gson();
            sucribleDetailBean = gson.fromJson(object.toString(), SucribleDetailBean.class);
            textView_tt_title.setText("[" + sucribleDetailBean.getInfo().getTitle() + "]");
            textView_shj.setText(Html.fromHtml("作者：" + sucribleDetailBean.getInfo().getAuthor()
                    + "    阅读数量：" + "<font color='#FF5000'>" + sucribleDetailBean.getInfo().getPv() + "</font>"
                    + "    发布时间：" + sucribleDetailBean.getInfo().getInput_time()));

            String html = sucribleDetailBean.getInfo().getContent();
            webView.loadData(html, "text/html;charset=UTF-8", null);
            //webView.loadDataWithBaseURL(null, html, "textselecthandle/html", "UTF-8", null);

            List<SucribleDetailBean.InfoBean.SubscribeBean> l = sucribleDetailBean.getInfo().getSubscribe();
            if (l != null) {
                textView_content.setVisibility(View.VISIBLE);
                Find_Detail_More_ListviewAdapetr f = new Find_Detail_More_ListviewAdapetr(this, l);
                listview.setAdapter(f);
            } else {
                textView_content.setVisibility(View.GONE);
            }
        } catch (Exception e) {
        }
    }

    @Override
    public void failCallBack(int type) {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_last:
                if (!("").equals(sucribleDetailBean.getInfo().getLastOne())) {
                    getNetData(sucribleDetailBean.getInfo().getLastOne());
                } else {
                    TextUtils.Toast(this, "没有更多了！");
                }
                break;
            case R.id.btn_next:
                if (!("").equals(sucribleDetailBean.getInfo().getNextOne())) {
                    getNetData(sucribleDetailBean.getInfo().getNextOne());
                } else {
                    TextUtils.Toast(this, "没有更多了！");
                }
                break;
            case R.id.wd_linear_share:
                try {
                    Intent intent = new Intent(this, ShareActivity.class);
                    intent.putExtra("type", "2");
                    intent.putExtra("id", sucribleDetailBean.getInfo().getId());
                    intent.putExtra("title", sucribleDetailBean.getInfo().getTitle());
                    startActivity(intent);
                } catch (Exception e) {
                }
                break;
            case R.id.buttom_linear_gq:
                finish();
                clearColor();
                imageView_gq.setImageResource(R.drawable.tabbar_tradehl);
                textView_gq.setTextColor(resources.getColor(R.color.color_red));
                MainActivity.goToSupDem();
                break;
            case R.id.buttom_linear_txl:
                finish();
                clearColor();
                imageView_txl.setImageResource(R.drawable.tabbar_contactshl);
                textView_txl.setTextColor(resources.getColor(R.color.color_red));
                MainActivity.firstInto();
                break;
            case R.id.buttom_linear_fx:
                finish();
                firstInto();
                MainActivity.goToHeadLine();
                break;
            case R.id.buttom_linear_wd:
                finish();
                clearColor();
                imageView_wd.setImageResource(R.drawable.tabbar_mehl);
                textView_wd.setTextColor(resources.getColor(R.color.color_red));
                MainActivity.goToMySelf();
                break;
            case R.id.buttom_linear_jia:
                finish();
                Intent intent = new Intent(this, ReleaseActivity.class);
                startActivity(intent);
                break;
        }
    }

    public void firstInto() {
        clearColor();
        imageView_fx.setImageResource(R.drawable.tabbar_headlines_hl);
        textView_fx.setTextColor(resources.getColor(R.color.color_red));
    }

    public void clearColor() {
        imageView_wd.setImageResource(R.drawable.tabbar_me);
        imageView_gq.setImageResource(R.drawable.tabbar_trade);
        imageView_fx.setImageResource(R.drawable.tabbar_headlines);
        imageView_txl.setImageResource(R.drawable.tabbar_contacts);

        textView_fx.setTextColor(resources.getColor(R.color.color_gray));
        textView_wd.setTextColor(resources.getColor(R.color.color_gray));
        textView_txl.setTextColor(resources.getColor(R.color.color_gray));
        textView_gq.setTextColor(resources.getColor(R.color.color_gray));
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && webView.canGoBack()) {
            webView.goBack();// 返回前一个页面
            return true;
        }
        return super.onKeyDown(keyCode, event);
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
