package com.myplas.q.headlines.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.myplas.q.R;
import com.myplas.q.common.api.API;
import com.myplas.q.common.netresquset.ResultCallBack;
import com.myplas.q.common.view.CommonDialog;
import com.myplas.q.common.utils.TextUtils;
import com.myplas.q.common.view.MyGridview;
import com.myplas.q.guide.activity.BaseActivity;
import com.myplas.q.headlines.adapter.TTAdapter;
import com.myplas.q.headlines.bean.SubcribleBean;
import com.myplas.q.myinfo.integral.activity.IntegralActivity;
import com.myplas.q.supdem.Beans.HistoryBean;
import com.myplas.q.headlines.bean.SearchNoResultBean;
import com.myplas.q.supdem.adapter.SupDem_Search_Grid_Adapter;
import com.optimus.edittextfield.EditTextField;
import com.umeng.analytics.MobclickAgent;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;


/**
 * 编写： 黄双
 * 电话：15378412400
 * 邮箱：15378412400@163.com
 * 时间：2017/3/19 15:44
 */

public class HeadLineSearchActivity extends BaseActivity implements View.OnClickListener, ResultCallBack,
        AdapterView.OnItemClickListener, CommonDialog.DialogShowInterface {

    private ListView listView;
    private ImageView imageView;
    private EditTextField editText;
    private FrameLayout frameLayout;
    private TextView textView, textView_hint, textView_no;
    private MyGridview gridview_history, gridview_subcribe, gridview_subcribe_no;
    private LinearLayout search_default_linear, search_result_linear, search_result_linear_no;

    private TTAdapter ttAdapter;
    private HistoryBean historyBean;
    private SearchNoResultBean bean;
    private List<SubcribleBean.DataBean> list;
    private SupDem_Search_Grid_Adapter adapter_grid;

    private Handler handler;
    private String keywords;
    private int page, visibleItemCount, position;
    private boolean isRefresh, isFinifsh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_layout_headline_search);
        goBack(findViewById(R.id.back));
        initView();
        String data = getIntent().getStringExtra("data");
        if (TextUtils.isNullOrEmpty(data)) {//从供求qq页面跳转过来
            editText.setText(data);
            get_Subscribe(1, data);
            editText.setSelection(data.length());
        } else {                            //从头条跳转过来
            getSearch_Record();
        }
    }

    public void initView() {
        page = 1;
        isRefresh = true;
        keywords = "7000F";
        handler = new Handler();
        list = new ArrayList<>();

        textView_hint = F(R.id.text_result);
        imageView = F(R.id.img_search_delete);
        listView = F(R.id.search_listview_result1);
        textView = F(R.id.supplydemand_btn_search);
        textView_no = F(R.id.search_result_text_null);
        editText = F(R.id.supplydemand_search_edit);
        frameLayout = F(R.id.search_result_framelayout);
        gridview_subcribe_no = F(R.id.mygrid_search_null);
        gridview_history = F(R.id.mygrid_search_history);
        gridview_subcribe = F(R.id.mygrid_search_subcribe);
        search_result_linear = F(R.id.search_result_linear);
        search_default_linear = F(R.id.search_default_linear);
        search_result_linear_no = F(R.id.search_result_linear_null);

        textView.setOnClickListener(this);
        imageView.setOnClickListener(this);
        listView.setOnItemClickListener(this);
        gridview_history.setOnItemClickListener(this);
        gridview_subcribe.setOnItemClickListener(this);
        gridview_subcribe_no.setOnItemClickListener(this);

        editText.setHintTextColor(getResources().getColor(R.color.color_gray));
        editText.setPadding(20, 0, 0, 0);


        //edittext的回车监听
        editText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView arg0, int arg1, KeyEvent arg2) {
                if (arg1 == EditorInfo.IME_ACTION_SEARCH | (arg2 != null && arg2.getAction() == KeyEvent.ACTION_DOWN)) {
                    page = 1;
                    isRefresh = true;
                    keywords = (editText.getText().toString().equals("")) ? ("7000f") : (editText.getText().toString());
                    get_Subscribe(page, keywords);
                    return true;
                }
                return false;
            }
        });
        //加载更多
        listView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                if (scrollState == AbsListView.OnScrollListener.SCROLL_STATE_IDLE && listView.getCount() >= visibleItemCount) {
                    InputMethodManager in = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    in.hideSoftInputFromWindow(editText.getWindowToken(), 0);
                    if (view.getLastVisiblePosition() == view.getCount() - 1) {
                        page++;
                        get_Subscribe(page, keywords);
                    }
                }
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                HeadLineSearchActivity.this.visibleItemCount = visibleItemCount;
            }
        });
    }

    //获取历史搜索
    public void getSearch_Record() {
        Map map = new HashMap();
        map.put("keywords", "");
        postAsyn(this, API.BASEURL + API.TOUTIAO_SEARCH_LOG, map, this, 1);
    }

    //查询
    public void get_Subscribe(int page, String keywords) {
        Map<String, String> map = new HashMap<String, String>();
        map.put("page", page + "");
        map.put("size", "15");
        map.put("keywords", keywords);
        map.put("subscribe", "1");
        BaseActivity.postAsyn(this, API.BASEURL + API.GET_SUBSCRIBE, map, this, 2);
    }


    //删除搜索历史记录
    public void delSearch_Record() {
        postAsyn(this, API.BASEURL + API.DEL_TOUTIAO_SEARCH_LOG, null, this, 3);
    }

    //检查权限
    public void isPaidSubscription(String cate_id) {
        Map<String, String> map = new HashMap<String, String>();
        map.put("id", cate_id);
        BaseActivity.postAsyn(this, API.BASEURL + API.IS_PAID_SUBSCRIPTION, map, this, 4);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.supplydemand_btn_search:
                page = 1;
                isRefresh = true;
                keywords = (editText.getText().toString().equals(""))
                        ? ("7000f")
                        : (editText.getText().toString());
                get_Subscribe(page, keywords);
                break;
            case R.id.img_search_delete:
                delSearch_Record();
                break;
        }
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        switch (parent.getId()) {
            case R.id.mygrid_search_history://历史搜索
                page = 1;
                isRefresh = true;
                keywords = historyBean.getHistory().get(position);
                editText.setText(keywords);
                editText.setSelection(keywords.length());
                get_Subscribe(page, keywords);
                break;
            case R.id.mygrid_search_subcribe://猜你所想
                page = 1;
                isRefresh = true;
                keywords = historyBean.getRecommend().get(position);
                editText.setText(keywords);
                editText.setSelection(keywords.length());
                get_Subscribe(page, keywords);
                break;
            case R.id.mygrid_search_null://相关搜索
                page = 1;
                isRefresh = true;
                keywords = bean.getRecommendation().get(position);
                editText.setText(keywords);
                editText.setSelection(keywords.length());
                get_Subscribe(page, keywords);
                break;
            case R.id.search_listview_result1:
                HeadLineSearchActivity.this.position = position;
                isPaidSubscription(list.get(position).getId());
                break;
        }
    }

    private void showInPutKeybord() {
        editText.setFocusable(true);
        editText.setFocusableInTouchMode(true);
        editText.requestFocus();
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {

            @Override
            public void run() {
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.toggleSoftInput(0, InputMethodManager.SHOW_FORCED);
            }

        }, 200);
    }

    @Override
    public void callBack(Object object, int type) {
        try {
            Gson gson = new Gson();
            String err = new JSONObject(object.toString()).getString("err");
            if (type == 1 && err.equals("0")) {
                historyBean = gson.fromJson(object.toString(), HistoryBean.class);
                adapter_grid = new SupDem_Search_Grid_Adapter(this, historyBean.getHistory());
                gridview_history.setAdapter(adapter_grid);
                SupDem_Search_Grid_Adapter adapter_grid1 = new SupDem_Search_Grid_Adapter(this, historyBean.getRecommend());
                gridview_subcribe.setAdapter(adapter_grid1);

//                keywords = historyBean.getHot_search().getContent();
//                editText.setHint(keywords);
            }
            if (type == 2) {
                search_default_linear.setVisibility(View.GONE);
                search_result_linear.setVisibility(View.VISIBLE);
                InputMethodManager in = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                in.hideSoftInputFromWindow(editText.getWindowToken(), 0);
                if (err.equals("0")) {
                    SubcribleBean subcribleBean = gson.fromJson(object.toString(), SubcribleBean.class);
                    if (page == 1) {
                        frameLayout.setVisibility(View.VISIBLE);
                        search_result_linear_no.setVisibility(View.GONE);
                        ttAdapter = new TTAdapter(this, subcribleBean.getData());
                        listView.setAdapter(ttAdapter);
                        //showRefreshPopou("为你搜索" + subcribleBean.getTotal() + "条信息");
                        list.clear();
                        list.addAll(subcribleBean.getData());
                    } else {
                        list.addAll(subcribleBean.getData());
                        ttAdapter.setList(list);
                        ttAdapter.notifyDataSetChanged();
                    }
                } else {
                    if (page == 1) {
                        frameLayout.setVisibility(View.GONE);
                        search_result_linear_no.setVisibility(View.VISIBLE);
                        textView_no.setText("抱歉，未能找到相关搜索！");
                        bean = gson.fromJson(object.toString(), SearchNoResultBean.class);
                        adapter_grid = new SupDem_Search_Grid_Adapter(this, bean.getRecommendation());
                        gridview_subcribe_no.setAdapter(adapter_grid);
                    } else {
                        TextUtils.Toast(this, "没有更多数据了！");
                    }
                }
            }
            if (type == 3 && err.equals("0")) {
                TextUtils.Toast(this, "删除成功！");
                adapter_grid = new SupDem_Search_Grid_Adapter(this, null);
                gridview_history.setAdapter(adapter_grid);
            }
            if (type == 4) {
                if (err.equals("0")) {
                    isFinifsh = false;
                    Intent intent = new Intent(this, HeadLinesDetailActivity.class);
                    intent.putExtra("id", list.get(position).getId());
                    startActivity(intent);
                } else {
                    String content = new JSONObject(object.toString()).getString("msg");
                    CommonDialog commonDialog = new CommonDialog();
                    commonDialog.showDialog(this, content, (err.equals("2")) ? (1) : (3), this);
                }
            }
        } catch (Exception e) {
        }
    }

    //展示刷新后的popou
    public void showRefreshPopou(String text) {
        if (isRefresh) {
            textView_hint.setVisibility(View.VISIBLE);
            isRefresh = false;

            textView_hint.setText(text);
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    textView_hint.setVisibility(View.GONE);
                }
            }, 1500);
        }
    }

    @Override
    public void ok(int type) {
        Intent intent = new Intent(this, IntegralActivity.class);
        intent.putExtra("type", "0");
        startActivity(intent);
    }

    @Override
    public void failCallBack(int type) {
    }

    public void onResume() {
        super.onResume();
        isFinifsh = true;
        MobclickAgent.onResume(this);
    }

    public void onPause() {
        super.onPause();
        MobclickAgent.onPause(this);
        if (isFinifsh) {
            overridePendingTransition(R.anim.fade, R.anim.hold);
        }
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(editText.getWindowToken(), 0);
    }
}
