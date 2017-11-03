package com.myplas.q.headlines.activity;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Handler;
import android.text.Html;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.myplas.q.R;
import com.myplas.q.common.appcontext.ActivityManager;
import com.myplas.q.common.view.CommonDialog;
import com.myplas.q.common.utils.StatusUtils;
import com.myplas.q.guide.activity.BaseActivity;
import com.myplas.q.guide.activity.MainActivity;
import com.myplas.q.guide.activity.ShareActivity;
import com.myplas.q.common.netresquset.ResultCallBack;
import com.myplas.q.common.utils.SharedUtils;
import com.myplas.q.common.utils.TextUtils;
import com.myplas.q.common.view.MyListview;
import com.myplas.q.headlines.adapter.HeadLinesDetail_More_LVAdapetr;
import com.myplas.q.common.api.API;
import com.myplas.q.headlines.bean.SucribleDetailBean;
import com.myplas.q.myself.integral.activity.IntegralActivity;
import com.myplas.q.release.ReleaseActivity;
import com.umeng.analytics.MobclickAgent;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 编写： 黄双
 * 电话：15378412400
 * 邮箱：15378412400@163.com
 * 时间：2017/3/21 16:18
 */
public class HeadLinesDetailActivity extends BaseActivity implements ResultCallBack
        , View.OnClickListener, CommonDialog.DialogShowInterface {
    private WebView webView;
    private Resources resources;
    private WebSettings webSettings;
    private SharedUtils sharedUtils;
    private LinearLayout mlayoutWebView;
    private LinearLayout linearLayout_share;
    private SucribleDetailBean sucribleDetailBean;
    private MyListview mListviewHot, mListviewAbout;
    private TextView textView_tt_title, textView_shj;
    private TextView textView_title, textView_content;
    private TextView imageView_btn_next, imageView_btn_last;

    private Handler mHandler;
    private ScrollView mScrollView;

    private String clickId;
    private MainActivity mMainActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_topline_detail_activity);
        StatusUtils.setStatusBar(this, true, true);
        StatusUtils.setStatusTextColor(true, this);
        goBack(findViewById(R.id.img_back));

        mHandler = new Handler();
        resources = getResources();
        sharedUtils = SharedUtils.getSharedUtils();
        mMainActivity = (MainActivity) ActivityManager.getActivity(MainActivity.class);

        textView_content = F(R.id.hot_search);
        imageView_btn_last = F(R.id.btn_last);
        imageView_btn_next = F(R.id.btn_next);
        textView_title = F(R.id.fx_ttxq_title);
        textView_shj = F(R.id.fx_tt_title_shj);
        mlayoutWebView = F(R.id.find_detail_ll);
        mScrollView = F(R.id.headline_sceollview);
        mListviewHot = F(R.id.find_detail_mliatview);
        textView_tt_title = F(R.id.fx_tt_title_text);
        linearLayout_share = F(R.id.wd_linear_share);
        mListviewAbout = F(R.id.find_detail_about_mlv);

        imageView_btn_last.setOnClickListener(this);
        imageView_btn_next.setOnClickListener(this);
        linearLayout_share.setOnClickListener(this);

        webView = new WebView(getApplicationContext());
        mlayoutWebView.addView(webView);
        webView.setVerticalScrollBarEnabled(false);

        webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setDomStorageEnabled(true);
        webSettings.setDefaultTextEncodingName("UTF-8");
        webSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NARROW_COLUMNS);

//        webSettings.setUseWideViewPort(true); //将图片调整到适合webview的大小
//        webSettings.setLoadWithOverviewMode(true); // 缩放至屏幕的大小

        webView.setWebViewClient(new WebViewClient());

        mListviewHot.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                clickId = sucribleDetailBean.getInfo().getHot().get(position).getId();
                boolean isFree = sucribleDetailBean.getInfo().getHot().get(position).getIs_free().equals("1");
                if (isFree) {
                    getNetData(clickId, 2);
                } else {
                    isPaidSubscription(clickId);
                }
            }
        });
        mListviewAbout.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                clickId = sucribleDetailBean.getInfo().getSubscribe().get(position).getId();
                boolean isFree = sucribleDetailBean.getInfo().getSubscribe().get(position).getIs_free().equals("1");
                if (isFree) {
                    getNetData(clickId, 2);
                } else {
                    isPaidSubscription(clickId);
                }
            }
        });
        getNetData(getIntent().getStringExtra("id"), 1);
    }

    public void getNetData(String id, int type) {
        Map<String, String> map = new HashMap<String, String>();
        map.put("id", id);
        map.put("token", sharedUtils.getData(this, "token"));
        BaseActivity.postAsyn(this, API.BASEURL + API.GET_DETAIL_INFO, map, this, type);
    }

    //检查权限
    public void isPaidSubscription(String cate_id) {
        Map<String, String> map = new HashMap<String, String>();
        map.put("id", cate_id);
        BaseActivity.postAsyn(this, API.BASEURL + API.IS_PAID_SUBSCRIPTION, map, this, 3);
    }

    @Override
    public void callBack(Object object, int type) {
        try {
            JSONObject jsonObject = new JSONObject(object.toString());
            String err = jsonObject.getString("err");
            if (type == 1 || type == 2 && err.equals("0")) {
                Gson gson = new Gson();
                sucribleDetailBean = gson.fromJson(object.toString(), SucribleDetailBean.class);
                showInfo(sucribleDetailBean);
            }
            //滚动到顶部
            if (type == 2) {
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        mScrollView.fullScroll(ScrollView.FOCUS_UP);
                    }
                });
            }
            if (type == 3) {
                if (err.equals("0")) {
                    getNetData(clickId, 2);
                } else {
                    String content = new JSONObject(object.toString()).getString("msg");
                    CommonDialog commonDialog = new CommonDialog();
                    commonDialog.showDialog(this, content, (err.equals("2")) ? (1) : (3), this);
                }
            }
        } catch (Exception e) {
        }
    }

    public void showInfo(SucribleDetailBean sucribleDetailBean) {
        textView_title.setText(sucribleDetailBean.getInfo().getCate_name());
        textView_tt_title.setText("[" + sucribleDetailBean.getInfo().getType() + "]"
                + sucribleDetailBean.getInfo().getTitle());
        textView_shj.setText(Html.fromHtml(sucribleDetailBean.getInfo().getAuthor()
                + "   " + sucribleDetailBean.getInfo().getInput_time()
                + "   " + sucribleDetailBean.getInfo().getPv()));

        String html = sucribleDetailBean.getInfo().getContent();
        String s = "<style>img,div{ width:100%;}table{width:100%;}</style>" + html;
        webView.loadData(s, "text/html;charset=UTF-8", null);

        List<SucribleDetailBean.InfoBean.SubscribeBean> subscribeBeanList = sucribleDetailBean.getInfo().getSubscribe();
        List<SucribleDetailBean.InfoBean.HotBean> hotBeanList = sucribleDetailBean.getInfo().getHot();
        textView_content.setVisibility((subscribeBeanList == null) ? (View.GONE) : (View.VISIBLE));
        HeadLinesDetail_More_LVAdapetr adapetrHot = new HeadLinesDetail_More_LVAdapetr(this, null, subscribeBeanList);
        mListviewAbout.setAdapter(adapetrHot);

        HeadLinesDetail_More_LVAdapetr adapetrAbout = new HeadLinesDetail_More_LVAdapetr(this, hotBeanList, null);
        mListviewHot.setAdapter(adapetrAbout);
    }

    @Override
    public void failCallBack(int type) {

    }

    @Override
    public void ok(int type) {
        Intent intent = new Intent(this, IntegralActivity.class);
        intent.putExtra("type", "5");
        startActivity(intent);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_last:
                clickId = sucribleDetailBean.getInfo().getLastOne();
                if (!("").equals(clickId)) {
                    isPaidSubscription(clickId);
                } else {
                    TextUtils.Toast(this, "没有更多了！");
                }
                break;
            case R.id.btn_next:
                clickId = sucribleDetailBean.getInfo().getNextOne();
                if (!("").equals(clickId)) {
                    isPaidSubscription(clickId);
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
            default:
                break;
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mHandler.removeCallbacksAndMessages(null);
        if (webView != null) {
            webView.loadDataWithBaseURL(null, "", "text/html", "utf-8", null);
            webView.clearHistory();

            ((ViewGroup) webView.getParent()).removeView(webView);
            webView.destroy();
            webView = null;
        }
    }
}
