package com.myplas.q.headlines.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.myplas.q.R;
import com.myplas.q.app.activity.BaseActivity;
import com.myplas.q.app.activity.MainActivity;
import com.myplas.q.app.activity.ShareActivity;
import com.myplas.q.common.api.API;
import com.myplas.q.common.appcontext.ActivityManager;
import com.myplas.q.common.net.ResultCallBack;
import com.myplas.q.common.utils.SharedUtils;
import com.myplas.q.common.utils.StatusUtils;
import com.myplas.q.common.utils.TextUtils;
import com.myplas.q.common.view.CommonDialog;
import com.myplas.q.common.view.MyListview;
import com.myplas.q.headlines.adapter.HeadLinesDetail_More_LVAdapetr;
import com.myplas.q.headlines.bean.SucribleDetailBean;
import com.myplas.q.myself.integral.activity.IntegralActivity;
import com.myplas.q.myself.integral.activity.IntegralPayActivtity;

import org.json.JSONException;
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
    private TextView textviewShj;
    private WebSettings webSettings;
    private SharedUtils sharedUtils;
    private LinearLayout mlayoutWebView;
    private SucribleDetailBean sucribleDetailBean;
    private MyListview mListviewHot, mListviewAbout;
    private TextView textviewTitle, textviewContent;
    private TextView imageviewBtnNext, imageviewBtnLast;

    private ScrollView mScrollView;

    private String code;
    private String clickId;
    private MainActivity mMainActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_topline_detail_activity);
        StatusUtils.setStatusBar(this, true, true);
        StatusUtils.setStatusTextColor(true, this);
        initTileBar();
        setLeftIVResId(R.drawable.btn_back_black);
        setTitleBarBackground(R.color.colorAccent);
        setTitleBarTextColor(R.color.color_black1);
        setRightIVResId(R.drawable.btn_share_black);

        sharedUtils = SharedUtils.getSharedUtils();
        mMainActivity = (MainActivity) ActivityManager.getActivity(MainActivity.class);

        textviewContent = F(R.id.hot_search);
        imageviewBtnLast = F(R.id.btn_last);
        imageviewBtnNext = F(R.id.btn_next);
        textviewShj = F(R.id.fx_tt_title_shj);
        mlayoutWebView = F(R.id.find_detail_ll);
        textviewTitle = F(R.id.fx_tt_title_text);
        mScrollView = F(R.id.headline_sceollview);
        mListviewHot = F(R.id.find_detail_mliatview);
        mListviewAbout = F(R.id.find_detail_about_mlv);

        mIVRight.setOnClickListener(this);
        imageviewBtnLast.setOnClickListener(this);
        imageviewBtnNext.setOnClickListener(this);

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
                clickId = sucribleDetailBean.getData().getHot().get(position).getId();
                boolean isFree = sucribleDetailBean.getData().getHot().get(position).getIs_free().equals("1");
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
                clickId = sucribleDetailBean.getData().getSubscribe().get(position).getId();
                boolean isFree = sucribleDetailBean.getData().getSubscribe().get(position).getIs_free().equals("1");
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
        Map<String, String> map = new HashMap<String, String>(8);
        map.put("id", id);
        map.put("ispass", code);
        getAsyn(this, API.GET_DETAIL_INFO, map, this, type, true);
    }

    //检查权限
    public void isPaidSubscription(String cate_id) {
        Map<String, String> map = new HashMap<String, String>();
        map.put("id", cate_id);
        getAsyn(this, API.IS_PAID_SUBSCRIPTION, map, this, 3);
    }

    @Override
    public void callBack(Object object, int type) {
        try {
            JSONObject jsonObject = new JSONObject(object.toString());
            String err = jsonObject.getString("code");
            if (type == 1 || type == 2 && "0".equals(err)) {
                Gson gson = new Gson();
                sucribleDetailBean = gson.fromJson(object.toString(), SucribleDetailBean.class);
                showInfo(sucribleDetailBean);
            }
            //滚动到顶部
            if (type == 2) {
                mScrollView.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mScrollView.fullScroll(ScrollView.FOCUS_UP);
                    }
                }, 300);
            }
            if (type == 3) {
                code = err;
                if ("0".equals(err)) {
                    getNetData(clickId, 2);
                } else {
                    String content = new JSONObject(object.toString()).getString("message");
                    CommonDialog commonDialog = new CommonDialog();
                    commonDialog.showDialog(this, content, ("2".equals(err)) ? (1) : (3), this);
                }
            }
        } catch (Exception e) {
        }
    }

    public void showInfo(SucribleDetailBean sucribleDetailBean) {
        setTitle(sucribleDetailBean.getData().getCate_name());
        textviewTitle.setText("[" + sucribleDetailBean.getData().getType() + "]"
                + sucribleDetailBean.getData().getTitle());
        textviewShj.setText(Html.fromHtml(sucribleDetailBean.getData().getAuthor()
                + "   " + sucribleDetailBean.getData().getInput_time()
                + "   " + sucribleDetailBean.getData().getPv()));

        String html = sucribleDetailBean.getData().getContent();
        String s = "<style>img,div{width:100%;}table{width:100%;}</style>" + html;
        webView.loadData(s, "text/html;charset=UTF-8", null);

        List<SucribleDetailBean.DataBean.SubscribeBean> subscribeBeanList = sucribleDetailBean.getData().getSubscribe();
        List<SucribleDetailBean.DataBean.HotBean> hotBeanList = sucribleDetailBean.getData().getHot();
        textviewContent.setVisibility((subscribeBeanList == null) ? (View.GONE) : (View.VISIBLE));
        HeadLinesDetail_More_LVAdapetr adapetrHot = new HeadLinesDetail_More_LVAdapetr(this, null, subscribeBeanList);
        mListviewAbout.setAdapter(adapetrHot);

        HeadLinesDetail_More_LVAdapetr adapetrAbout = new HeadLinesDetail_More_LVAdapetr(this, hotBeanList, null);
        mListviewHot.setAdapter(adapetrAbout);
    }

    @Override
    public void failCallBack(int type, String message, int httpCode) {
        if (type == 2 && httpCode == 412) {
            try {
                JSONObject jsonObject = new JSONObject(message);
                String code = jsonObject.getString("code");
                if ("100".equals(code)) {
                    String content = jsonObject.getString("message");
                    CommonDialog commonDialog = new CommonDialog();
                    commonDialog.showDialog(this, content, 10, this);
                }
            } catch (JSONException e) {

            }
        }
    }

    @Override
    public void dialogClick(int type) {
        if (type == 1) {
            getNetData(clickId, 2);
        }
        if (type == 10) {
            Intent intent = new Intent(this, IntegralPayActivtity.class);
            startActivity(intent);
        }
    }

    @Override
    public void onClick(View v) {
        if (sucribleDetailBean == null) {
            return;
        }
        switch (v.getId()) {
            case R.id.btn_last:
                clickId = sucribleDetailBean.getData().getLastOne();
                if (!("").equals(clickId)) {
                    isPaidSubscription(clickId);
                } else {
                    TextUtils.toast(this, "没有更多了！");
                }
                break;
            case R.id.btn_next:
                clickId = sucribleDetailBean.getData().getNextOne();
                if (!("").equals(clickId)) {
                    isPaidSubscription(clickId);
                } else {
                    TextUtils.toast(this, "没有更多了！");
                }
                break;
            case R.id.titlebar_img_right:
                Intent intent = new Intent(this, ShareActivity.class);
                intent.putExtra("type", "2");
                intent.putExtra("id", sucribleDetailBean.getData().getId());
                intent.putExtra("title", sucribleDetailBean.getData().getTitle());
                startActivity(intent);
                break;
            default:
                break;
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (webView != null) {
            webView.loadDataWithBaseURL(null, "", "text/html", "utf-8", null);
            webView.clearHistory();

            ((ViewGroup) webView.getParent()).removeView(webView);
            webView.destroy();
            webView = null;
        }
    }
}
