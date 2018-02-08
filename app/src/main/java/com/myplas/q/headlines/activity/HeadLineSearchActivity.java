package com.myplas.q.headlines.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.myplas.q.R;
import com.myplas.q.common.api.API;
import com.myplas.q.common.net.ResultCallBack;
import com.myplas.q.common.view.CommonDialog;
import com.myplas.q.common.utils.TextUtils;
import com.myplas.q.common.view.EditTextField;
import com.myplas.q.common.view.MyGridview;
import com.myplas.q.common.view.RefreshPopou;
import com.myplas.q.app.activity.BaseActivity;
import com.myplas.q.headlines.adapter.HeadSearch_LV_Adapter;
import com.myplas.q.headlines.bean.HeadSearchBean;
import com.myplas.q.myself.integral.activity.IntegralActivity;
import com.myplas.q.myself.integral.activity.IntegralPayActivtity;
import com.myplas.q.supdem.beans.HistoryBean;
import com.myplas.q.headlines.bean.SearchNoResultBean;
import com.myplas.q.supdem.adapter.SupDem_Search_Grid_Adapter;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 编写： 黄双
 * 电话：15378412400
 * 邮箱：15378412400@163.com
 * 时间：2017/3/19 15:44
 */
public class HeadLineSearchActivity extends BaseActivity implements View.OnClickListener
        , ResultCallBack
        , AdapterView.OnItemClickListener
        , CommonDialog.DialogShowInterface {

    private ListView listView;
    private ImageView imageView;
    private EditTextField editText;
    private RefreshPopou mRefreshPopou;
    private TextView textView, textviewNo;
    private MyGridview mgvHistory, mgvEmpty, mgvSubcribe;
    private LinearLayout mLayoutDefault, mLayoutResult, mLayoutEmpty;

    private HistoryBean historyBean;
    private SearchNoResultBean bean;
    private List<HeadSearchBean.NewsBean> list;
    private HeadSearch_LV_Adapter mSearchLvAdapter;
    private SupDem_Search_Grid_Adapter mSearchGridAdapter;

    private String code;
    private String keywords;
    private int page, visibleItemCount, position;
    private String clickId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_layout_headline_search);
        goBack(findViewById(R.id.back));
        initView();
        decideFrom();
    }

    private void decideFrom() {
        String data = getIntent().getStringExtra("data");
        if (TextUtils.notEmpty(data)) {//从供求qq页面跳转过来
            getData(data);
        } else {                      //从头条跳转过来
            getSearch_Record();
        }
    }

    /**
     * Initview.
     */
    public void initView() {
        page = 1;
        keywords = "7000F";
        list = new ArrayList<>();
        mRefreshPopou = new RefreshPopou(this, 1);

        imageView = F(R.id.img_search_delete);
        mgvEmpty = F(R.id.mygrid_search_null);
        listView = F(R.id.search_listview_result1);
        textView = F(R.id.supplydemand_btn_search);
        editText = F(R.id.supplydemand_search_edit);
        mgvHistory = F(R.id.mygrid_search_history);
        mLayoutResult = F(R.id.search_result_linear);
        mgvSubcribe = F(R.id.mygrid_search_subcribe);
        textviewNo = F(R.id.search_result_text_null);
        mLayoutDefault = F(R.id.search_default_linear);
        mLayoutEmpty = F(R.id.search_result_linear_null);

        textView.setOnClickListener(this);
        imageView.setOnClickListener(this);
        listView.setOnItemClickListener(this);
        mgvEmpty.setOnItemClickListener(this);
        mgvHistory.setOnItemClickListener(this);
        mgvSubcribe.setOnItemClickListener(this);

        editText.setHintTextColor(getResources().getColor(R.color.color_gray));
        editText.setPadding(20, 0, 0, 0);


        //edittext的回车监听
        editText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView arg0, int arg1, KeyEvent arg2) {
                if (arg1 == EditorInfo.IME_ACTION_SEARCH | (arg2 != null && arg2.getAction() == KeyEvent.ACTION_DOWN)) {
                    page = 1;
                    mRefreshPopou.setCanShowPopou(true);
                    mLayoutDefault.setVisibility(View.GONE);
                    mLayoutResult.setVisibility(View.VISIBLE);
                    keywords = ("".equals(editText.getText().toString())) ? ("7000f") : (editText.getText().toString());
                    getCateList(page, keywords, true);
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
                        getCateList(page, keywords, false);
                    }
                }
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                HeadLineSearchActivity.this.visibleItemCount = visibleItemCount;
            }
        });
    }

    /**
     * 获取历史搜索
     */
    public void getSearch_Record() {
        getAsyn(this, API.TOUTIAO_SEARCH_LOG, null, this, 1);
    }

    /**
     * 查询
     *
     * @param page          the page
     * @param keywords      the keywords
     * @param isShowLoading the is show loading
     */

    public void getCateList(int page, String keywords, boolean isShowLoading) {
        Map<String, String> map = new HashMap<String, String>(16);
        map.put("page", page + "");
        map.put("size", "15");
        map.put("keywords", keywords);
        getAsyn(this, API.GET_CATE_LIST, map, this, 2, isShowLoading);
    }


    /**
     * 删除搜索历史记录
     */
    public void delsearchRecord() {
        deleteAsyn(this, API.DEL_TOUTIAO_SEARCH_LOG, null, this, 3, false);
    }

    /**
     * 检查权限
     *
     * @param cate_id the cate id
     */
    public void isPaidSubscription(String cate_id) {
        Map<String, String> map = new HashMap<String, String>();
        map.put("id", cate_id);
        getAsyn(this, API.IS_PAID_SUBSCRIPTION, map, this, 4);
    }

    /**
     * 获取详情 检查塑豆
     *
     * @param id
     * @param type
     */
    public void getNetData(String id) {
        Map<String, String> map = new HashMap<String, String>(8);
        map.put("id", id);
        map.put("ispass", code);
        getAsyn(this, API.GET_DETAIL_INFO, map, this, 7);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.supplydemand_btn_search:
                page = 1;
                mRefreshPopou.setCanShowPopou(true);
                mLayoutDefault.setVisibility(View.GONE);
                mLayoutResult.setVisibility(View.VISIBLE);
                keywords = (editText.getText().toString().equals(""))
                        ? ("7000f")
                        : (editText.getText().toString());
                getCateList(page, keywords, true);
                break;
            case R.id.img_search_delete:
                delsearchRecord();
                break;
            default:
                break;
        }
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        switch (parent.getId()) {
            case R.id.mygrid_search_history://历史搜索
                keywords = historyBean.getHistory().get(position);
                getData(keywords);
                break;
            case R.id.mygrid_search_subcribe://猜你所想
                keywords = historyBean.getRecommend().get(position);
                getData(keywords);
                break;
            case R.id.mygrid_search_null://相关搜索
                keywords = bean.getRecommendation().get(position);
                getData(keywords);
                break;
            case R.id.search_listview_result1:
                clickId = list.get(position).getId();
                HeadLineSearchActivity.this.position = position;
                isPaidSubscription(list.get(position).getId());
                break;
            default:
                break;
        }
    }

    private void getData(String keywords) {
        page = 1;
        mRefreshPopou.setCanShowPopou(true);
        mLayoutDefault.setVisibility(View.GONE);
        mLayoutResult.setVisibility(View.VISIBLE);

        editText.setText(keywords);
        editText.setSelection(keywords.length());
        getCateList(page, keywords, true);
    }

    @Override
    public void callBack(Object object, int type) {
        try {

            if (type == 3) {
                TextUtils.toast(this, "删除成功！");
                mSearchGridAdapter = new SupDem_Search_Grid_Adapter(this, null);
                mgvHistory.setAdapter(mSearchGridAdapter);
            }

            Gson gson = new Gson();
            String err = new JSONObject(object.toString()).getString("code");

            if (type == 1 && "0".equals(err)) {
                historyBean = gson.fromJson(object.toString(), HistoryBean.class);
                mSearchGridAdapter = new SupDem_Search_Grid_Adapter(this, historyBean.getHistory());
                mgvHistory.setAdapter(mSearchGridAdapter);
                SupDem_Search_Grid_Adapter adapter_grid1 = new SupDem_Search_Grid_Adapter(this, historyBean.getRecommend());
                mgvSubcribe.setAdapter(adapter_grid1);

//                keywords = historyBean.getHot_search().getContent();
//                editText.setHint(keywords);
            }
            if (type == 2) {
                InputMethodManager in = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                in.hideSoftInputFromWindow(editText.getWindowToken(), 0);
                if ("0".equals(err)) {
                    HeadSearchBean searchBean = gson.fromJson(object.toString(), HeadSearchBean.class);
                    if (page == 1) {
                        if (listView.getVisibility() == View.GONE) {
                            listView.setVisibility(View.VISIBLE);
                            mLayoutEmpty.setVisibility(View.GONE);
                        }
                        mSearchLvAdapter = new HeadSearch_LV_Adapter(this, searchBean.getNews());
                        listView.setAdapter(mSearchLvAdapter);

                        list.clear();
                        list.addAll(searchBean.getNews());
                        mRefreshPopou.show(F(R.id.divider_result), "为你搜索" + searchBean.getTotal_found() + "条信息");
                    } else {
                        list.addAll(searchBean.getNews());
                        mSearchLvAdapter.setList(list);
                        mSearchLvAdapter.notifyDataSetChanged();
                    }
                } else {
                    if (page == 1) {

                        if (mLayoutEmpty.getVisibility() == View.GONE) {
                            listView.setVisibility(View.GONE);
                            mLayoutEmpty.setVisibility(View.VISIBLE);
                        }

                        textviewNo.setText("抱歉，未能找到相关搜索！");
                        bean = gson.fromJson(object.toString(), SearchNoResultBean.class);
                        mSearchGridAdapter = new SupDem_Search_Grid_Adapter(this, bean.getRecommendation());
                        mgvEmpty.setAdapter(mSearchGridAdapter);
                    } else {
                        TextUtils.toast(this, "没有更多数据了！");
                    }
                }
            }

            if (type == 4) {
                code = err;
                if ("0".equals(err)) {
                    Intent intent = new Intent(this, HeadLinesDetailActivity.class);
                    intent.putExtra("id", list.get(position).getId());
                    startActivity(intent);
                } else {
                    String content = new JSONObject(object.toString()).getString("message");
                    CommonDialog commonDialog = new CommonDialog();
                    commonDialog.showDialog(this, content, ("2".equals(err)) ? (1) : (3), this);
                }
            }
            if (type == 7) {
                if ("0".equals(err)) {
                    Intent intent = new Intent(this, HeadLinesDetailActivity.class);
                    intent.putExtra("id", clickId);
                    startActivity(intent);
                }
            }
        } catch (Exception e) {
        }
    }

    @Override
    public void dialogClick(int type) {
        if (type == 1) {
            getNetData(clickId);
        }
        if (type == 10) {
            Intent intent = new Intent(this, IntegralPayActivtity.class);
            startActivity(intent);
        }
    }

    @Override
    public void failCallBack(int type, String message, int httpCode) {
        if (type == 7 && httpCode == 412) {
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
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.fade, R.anim.hold);
    }
}
