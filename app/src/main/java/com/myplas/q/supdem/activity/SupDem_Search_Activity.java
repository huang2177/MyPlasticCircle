package com.myplas.q.supdem.activity;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.LinearLayoutManager;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.huangbryant.hindicator.HIndicatorAdapter;
import com.huangbryant.hindicator.HIndicatorBuilder;
import com.huangbryant.hindicator.HIndicatorDialog;
import com.huangbryant.hindicator.OnDismissListener;
import com.myplas.q.R;
import com.myplas.q.common.api.API;
import com.myplas.q.common.netresquset.ResultCallBack;
import com.myplas.q.common.utils.HLog;
import com.myplas.q.common.utils.SharedUtils;
import com.myplas.q.common.utils.TextUtils;
import com.myplas.q.common.view.MyGridview;
import com.myplas.q.common.view.RefreshPopou;
import com.myplas.q.guide.activity.BaseActivity;
import com.myplas.q.supdem.beans.ConfigData;
import com.myplas.q.supdem.beans.HistoryBean;
import com.myplas.q.supdem.beans.SearchNoResultBean;
import com.myplas.q.supdem.beans.SearchResultBean;
import com.myplas.q.supdem.beans.TabCofigBean;
import com.myplas.q.supdem.adapter.SupDem_Search_Grid_Adapter;
import com.myplas.q.supdem.adapter.SupDem_Search_List_Adapter;
import com.myplas.q.supdem.PopouShowUtils;
import com.optimus.edittextfield.EditTextField;
import com.umeng.analytics.MobclickAgent;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 编写： 黄双
 * 电话：15378412400
 * 邮箱：15378412400@163.com
 * 时间：2017/3/19 15:44
 */

public class SupDem_Search_Activity extends BaseActivity implements View.OnClickListener, ResultCallBack,
        PopouShowUtils.poPouCallBackInterface, AdapterView.OnItemClickListener {
    private ListView listView;
    private ImageView imageView;
    private EditTextField editText;
    private RefreshPopou mRefreshPopou;
    private RelativeLayout layout_time, layout_add;
    private LinearLayout mLayoutDefault, mLayoutResult, mLayoutEmpty;
    private MyGridview gridview_history, gridview_subcribe, gridview_subcribe_no;
    private TextView textView, textView_time, textView_add, textView_no, mTextViewType;

    private Handler handler;
    private SearchNoResultBean bean;
    private HIndicatorDialog dialog;
    private HistoryBean historyBean;
    private TabCofigBean tabCofigBean;
    private List<SearchResultBean.ListBean> list;
    private SupDem_Search_Grid_Adapter adapter_grid;
    private SupDem_Search_List_Adapter adapter_list;
    private List<TabCofigBean.DataBeanXXX.AreaBean.DataBeanXX> list_area;
    private List<TabCofigBean.DataBeanXXX.TimeBean.DataBean> list_time;

    private String keywords, is_buy, area, time;
    private int page, visibleItemCount;
    private boolean hasMoerData = true;
    private List<String> mList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_supdem_search_layout);
        goBack(findViewById(R.id.back));
        initView();
        getSearch_Record();
        getTab_Config();
    }

    public void initView() {
        page = 1;
        is_buy = "1";
        keywords = "7000F";
        handler = new Handler();
        list = new ArrayList<>();
        mRefreshPopou = new RefreshPopou(this, 3);

        imageView = F(R.id.img_search_delete);
        textView_add = F(R.id.text_result_add);
        mTextViewType = F(R.id.spinner_content);
        textView_time = F(R.id.text_result_time);
        listView = F(R.id.search_listview_result);
        layout_add = F(R.id.relative_result_add);
        layout_time = F(R.id.relative_result_time);
        textView = F(R.id.supplydemand_btn_search);
        editText = F(R.id.supplydemand_search_edit);
        mLayoutResult = F(R.id.search_result_linear);
        textView_no = F(R.id.search_result_text_null);
        mLayoutDefault = F(R.id.search_default_linear);
        gridview_subcribe_no = F(R.id.mygrid_search_null);
        gridview_history = F(R.id.mygrid_search_history);
        gridview_subcribe = F(R.id.mygrid_search_subcribe);
        mLayoutEmpty = F(R.id.search_result_linear_null);

        textView.setOnClickListener(this);
        imageView.setOnClickListener(this);
        layout_add.setOnClickListener(this);
        layout_time.setOnClickListener(this);
        listView.setOnItemClickListener(this);
        mTextViewType.setOnClickListener(this);
        gridview_history.setOnItemClickListener(this);
        gridview_subcribe.setOnItemClickListener(this);
        gridview_subcribe_no.setOnItemClickListener(this);

        mTextViewType.setVisibility(View.VISIBLE);
        editText.setHintTextColor(getResources().getColor(R.color.color_gray));

        mList = Arrays.asList("供给", "求购");

        //edittext的回车监听
        editText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView arg0, int arg1, KeyEvent arg2) {
                if (arg1 == EditorInfo.IME_ACTION_SEARCH | (arg2 != null && arg2.getAction() == KeyEvent.ACTION_DOWN)) {
                    page = 1;
                    hasMoerData = true;
                    mRefreshPopou.setCanShowPopou(true);
                    mLayoutDefault.setVisibility(View.GONE);
                    mLayoutResult.setVisibility(View.VISIBLE);
                    keywords = (editText.getText().toString().equals("")) ? ("7000f") : (editText.getText().toString());
                    getPhysical_Search(1, keywords, time, is_buy, area, true);
                    return true;
                }
                return false;
            }
        });

        //加载更多
        listView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                if (scrollState == AbsListView.OnScrollListener.SCROLL_STATE_IDLE && listView.getCount() > visibleItemCount) {
                    InputMethodManager in = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    in.hideSoftInputFromWindow(editText.getWindowToken(), 0);
                    if (view.getLastVisiblePosition() == view.getCount() - 1) {
                        page++;
                        if (hasMoerData) {
                            getPhysical_Search(page, keywords, time, is_buy, area, false);
                        } else {
                            TextUtils.Toast(SupDem_Search_Activity.this, "没有更多数据了！");
                        }
                    }
                }
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                SupDem_Search_Activity.this.visibleItemCount = visibleItemCount;
            }
        });
    }

    //获取历史搜索
    public void getSearch_Record() {
        Map map = new HashMap();
        map.put("keywords", "");
        postAsyn(this, API.BASEURL + API.SEARCH_RECORD, map, this, 1);
    }

    //查询
    public void getPhysical_Search(int page, String keyWords, String time, String is_buy, String area, boolean isShowLoading) {
        Map map = new HashMap();
        map.put("keywords", keyWords);
        map.put("page", page + "");
        map.put("size", "10");
        map.put("time", time);
        map.put("type", is_buy);
        map.put("cargo_type", "0");
        map.put("area_id", area);
        postAsyn1(this, API.BASEURL + API.PLASTIC_SEARCH, map, this, 2, isShowLoading);
    }

    //获取时间和地区
    public void getTab_Config() {
        Map map = new HashMap();
        postAsyn(this, API.BASEURL + API.GET_TAB_CONFIG, map, this, 3);
    }

    //删除搜索历史记录
    public void delSearch_Record() {
        Map map = new HashMap();
        map.put("keywords", "");
        postAsyn(this, API.BASEURL + API.DEL_SEARCH_RECORD, map, this, 4);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.supplydemand_btn_search:
                page = 1;
                hasMoerData = true;
                mRefreshPopou.setCanShowPopou(true);
                mLayoutDefault.setVisibility(View.GONE);
                mLayoutResult.setVisibility(View.VISIBLE);
                keywords = (editText.getText().toString().equals("")) ? ("7000f") : (editText.getText().toString());
                getPhysical_Search(1, keywords, time, is_buy, area, true);
                break;
            case R.id.img_search_delete:
                delSearch_Record();
                break;
            case R.id.relative_result_time:
                showPopou(0, R.layout.supdem_result_time_popou);
                break;
            case R.id.relative_result_add:
                showPopou(1, R.layout.supdem_result_add_popou);
                break;
            case R.id.spinner_content:
                dialog = new HIndicatorBuilder(this)
                        .width(200)
                        .height(-1)
                        .ArrowDirection(HIndicatorBuilder.TOP)
                        .bgColor(Color.parseColor("#cacacb"))
                        .gravity(HIndicatorBuilder.GRAVITY_LEFT)
                        .radius(8)
                        .ArrowRectage(0.2f)
                        .layoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false))
                        .dimEnabled(false)
                        .adapter(new MyAdapter())
                        .create();
                dialog.setCanceledOnTouchOutside(true);
                dialog.show(mTextViewType);
                mTextViewType.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.up, 0);
                dialog.setOnDismissListener(new OnDismissListener() {
                    @Override
                    public void onDismiss(Dialog dialog) {
                        mTextViewType.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.down, 0);
                    }
                });
                break;
            default:
                break;
        }
    }

    public void showPopou(int type, int resId) {
        PopouShowUtils popouShowUtils1 = new PopouShowUtils(this, resId, type, list_area, list_time);
        popouShowUtils1.showAsDropDown(findViewById(R.id.divider_result));
        popouShowUtils1.setPoPouCallBackInterface(this);
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
                keywords = bean.getCombine().get(position);
                getData(keywords);
                break;
            case R.id.search_listview_result:
                if (list.get(position).getType().equals("9")) {//来自QQ群
                    Intent intent = new Intent(SupDem_Search_Activity.this, SupDem_QQ_DetailActivity.class);
                    intent.putExtra("company", list.get(position).getC_name());
                    intent.putExtra("plastic_number", list.get(position).getModel());
                    startActivity(intent);
                } else {//来自供求
                    try {
                        Intent intent = new Intent(this, SupDem_Detail_Activity.class);
                        String id_ = list.get(position).getId();
                        String userid = list.get(position).getUser_id();
                        String user_id = SharedUtils.getSharedUtils().getData(this, "userid");

                        intent.putExtra("id", id_);
                        intent.putExtra("type", "0");
                        intent.putExtra("userid", userid);
                        intent.putExtra("what", (user_id.equals(userid)) ? ("5") : (ConfigData.what));

                        startActivity(intent);
                    } catch (Exception e) {
                    }
                }
                break;
            default:
                break;
        }
    }

    private void getData(String keywords) {
        page = 1;
        hasMoerData = true;
        editText.setText(keywords);
        editText.setSelection(keywords.length());

        mRefreshPopou.setCanShowPopou(true);
        mLayoutDefault.setVisibility(View.GONE);
        mLayoutResult.setVisibility(View.VISIBLE);
        getPhysical_Search(1, keywords, time, is_buy, area, true);
    }

    @Override
    public void callBack(Object object, int type) {
        try {
            Gson gson = new Gson();
            boolean err = new JSONObject(object.toString()).getString("err").equals("0");
            if (type == 1 && err) {
                historyBean = gson.fromJson(object.toString(), HistoryBean.class);
                adapter_grid = new SupDem_Search_Grid_Adapter(this, historyBean.getHistory());
                gridview_history.setAdapter(adapter_grid);
                SupDem_Search_Grid_Adapter adapter_grid1 = new SupDem_Search_Grid_Adapter(this, historyBean.getRecommend());
                gridview_subcribe.setAdapter(adapter_grid1);

//                keywords = historyBean.getHot_search().getContent();
//                editText.setHint(keywords);
            }
            if (type == 2) {
                mLayoutDefault.setVisibility(View.GONE);
                mLayoutResult.setVisibility(View.VISIBLE);
                InputMethodManager in = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                in.hideSoftInputFromWindow(editText.getWindowToken(), 0);
                SearchResultBean resultBean = null;
                if (err) {
                    resultBean = gson.fromJson(object.toString(), SearchResultBean.class);
                    if (page == 1) {
                        adapter_list = new SupDem_Search_List_Adapter(this, resultBean.getList());
                        listView.setAdapter(adapter_list);

                        list.clear();
                        list.addAll(resultBean.getList());
                        mRefreshPopou.show(F(R.id.divider_result), "为你搜索" + resultBean.getTotal() + "条信息");
                    } else {
                        list.addAll(resultBean.getList());
                        adapter_list.setList(list);
                        adapter_list.notifyDataSetChanged();
                    }
                } else {
                    hasMoerData = false;
                    if (page == 1) {
                        listView.setVisibility(View.GONE);
                        mLayoutEmpty.setVisibility(View.VISIBLE);
                        textView_no.setText("抱歉，未能找到相关搜索！");
                        bean = gson.fromJson(object.toString(), SearchNoResultBean.class);
                        adapter_grid = new SupDem_Search_Grid_Adapter(this, bean.getCombine());
                        gridview_subcribe_no.setAdapter(adapter_grid);
                    } else {
                        TextUtils.Toast(this, "没有更多数据！");
                    }
                }
            }
            if (type == 3 && err) {
                tabCofigBean = gson.fromJson(object.toString(), TabCofigBean.class);
                list_area = tabCofigBean.getData().getArea().getData();
                list_time = tabCofigBean.getData().getTime().getData();
                //设置默认的时间
                time = tabCofigBean.getData().getTime().getCurrentValue();
                int currentitem_time = Integer.parseInt(tabCofigBean.getData().getTime().getCurrentItem());
                SharedUtils.getSharedUtils().setData(this, "position", currentitem_time + "");
                textView_time.setText(list_time.get(currentitem_time).getShow());
                //设置默认的地区
                area = tabCofigBean.getData().getArea().getCurrentValue();
                int currentItem = Integer.parseInt(tabCofigBean.getData().getArea().getCurrentItem());
                textView_add.setText(tabCofigBean.getData().getArea().getData().get(currentItem).getShow());
                SharedUtils.getSharedUtils().setData(this, "position_pro", currentItem + "");
                SharedUtils.getSharedUtils().setData(this, "position_city", 0 + "");
            }
            if (type == 4 && err) {
                TextUtils.Toast(this, "删除成功！");
                adapter_grid = new SupDem_Search_Grid_Adapter(this, null);
                gridview_history.setAdapter(adapter_grid);
            }
        } catch (Exception e) {
            HLog.e(this, e.toString());
        }
    }

    @Override
    public void failCallBack(int type) {
    }

    //地区回调
    @Override
    public void addCallBack(int po_pro, int position) {
        page = 1;
        mRefreshPopou.setCanShowPopou(true);
        hasMoerData = true;
        textView_add.setText(list_area.get(po_pro).getData().get(position).getShow());
        textView_add.setTextColor(getResources().getColor(R.color.color_red));
        area = list_area.get(po_pro).getData().get(position).getValue();
        getPhysical_Search(1, keywords, time, is_buy, area, true);
    }

    //时间回调
    @Override
    public void timeCallBack(int po) {
        page = 1;
        mRefreshPopou.setCanShowPopou(true);
        hasMoerData = true;
        textView_time.setText(list_time.get(po).getShow());
        textView_time.setTextColor(getResources().getColor(R.color.color_red));
        time = list_time.get(po).getValue();
        getPhysical_Search(1, keywords, time, is_buy, area, true);
    }

    @Override
    public void onResume() {
        super.onResume();
        MobclickAgent.onResume(this);
    }

    @Override
    public void onPause() {
        super.onPause();
        MobclickAgent.onPause(this);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.fade, R.anim.hold);
    }

    public class MyAdapter extends HIndicatorAdapter {
        @Override
        public void onBindView(BaseViewHolder holder, int position) {
            TextView tv = holder.getView(R.id.item_tv);
            tv.setText(mList.get(position));
            if (position == mList.size() - 1) {
                holder.setVisibility(R.id.item_line, BaseViewHolder.GONE);
            } else {
                holder.setVisibility(R.id.item_line, BaseViewHolder.VISIBLE);
            }
        }

        @Override
        public int getLayoutID(int position) {
            return R.layout.hindicator_item_layout;
        }

        @Override
        public boolean clickable() {
            return true;
        }

        @Override
        public void onItemClick(View v, int position) {
            dialog.dismiss();
            mTextViewType.setText(mList.get(position));
            mTextViewType.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.down, 0);

            page = 1;
            hasMoerData = true;
            is_buy = position == 0 ? "1" : "0";
            keywords = (editText.getText().toString().equals("")) ? ("7000f") : (editText.getText().toString());
            getPhysical_Search(1, keywords, time, is_buy, area, true);
        }

        @Override
        public int getItemCount() {
            return mList.size();
        }
    }
}
