package com.myplas.q.supdem.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.RequiresApi;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.gson.Gson;
import com.myplas.q.R;
import com.myplas.q.addresslist.fragment.Fragment_AddressList;
import com.myplas.q.common.api.API;
import com.myplas.q.common.netresquset.ResultCallBack;
import com.myplas.q.common.utils.DialogShowUtils;
import com.myplas.q.common.utils.SharedUtils;
import com.myplas.q.common.utils.TextUtils;
import com.myplas.q.common.view.MyGridview;
import com.myplas.q.common.view.XListView;
import com.myplas.q.guide.activity.BaseActivity;
import com.myplas.q.guide.activity.MainActivity;
import com.myplas.q.myinfo.beans.SelectableBean;
import com.myplas.q.supdem.Beans.HistoryBean;
import com.myplas.q.supdem.Beans.SearchNoResultBean;
import com.myplas.q.supdem.Beans.SearchResultBean;
import com.myplas.q.supdem.Beans.TabCofigBean;
import com.myplas.q.supdem.adapter.SupDem_Search_Grid_Adapter;
import com.myplas.q.supdem.adapter.SupDem_Search_List_Adapter;
import com.myplas.q.supdem.adapter.SupDem_Search_QQ_Detail_Adapter;
import com.myplas.q.supdem.popoushowutils.PopouShowUtils;
import com.optimus.edittextfield.EditTextField;
import com.umeng.analytics.MobclickAgent;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.myplas.q.supdem.Beans.ItemBean.itemBean;
import static com.umeng.analytics.pro.x.k;
import static com.umeng.analytics.pro.x.o;
import static com.umeng.analytics.pro.x.p;


/**
 * 编写： 黄双
 * 电话：15378412400
 * 邮箱：15378412400@163.com
 * 时间：2017/3/19 15:44
 */

public class SupDem_Search_Activity extends BaseActivity implements View.OnClickListener, ResultCallBack,
        PopouShowUtils.poPouCallBackInterface, AdapterView.OnItemClickListener {
    private Spinner spinner;
    private ListView listView;
    private ImageView imageView;
    private EditTextField editText;
    private FrameLayout frameLayout;
    private RelativeLayout layout_time, layout_add;
    private MyGridview gridview_history, gridview_subcribe, gridview_subcribe_no;
    private TextView textView, textView_time, textView_add, textView_hint, textView_no;
    private LinearLayout search_default_linear, search_result_linear, search_result_linear_no;

    private Handler handler;
    private boolean isRefresh;
    private SearchNoResultBean bean;
    private HistoryBean historyBean;
    private String level[], level1[];
    private TabCofigBean tabCofigBean;
    private List<SearchResultBean.ListBean> list;
    private SupDem_Search_Grid_Adapter adapter_grid;
    private SupDem_Search_List_Adapter adapter_list;
    private List<TabCofigBean.DataBeanXXX.AreaBean.DataBeanXX> list_area;
    private List<TabCofigBean.DataBeanXXX.TimeBean.DataBean> list_time;

    private String keywords = "7000f", is_buy = "1", area, time;
    private int page = 1, visibleItemCount;
    private boolean hasMoerData = true;
    private boolean spinnerSelected = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.supdem_search_layout);
        goBack(findViewById(R.id.back));
        initView();
        getSearch_Record();
        getTab_Config();
    }
    public void initView() {
        isRefresh = true;
        list = new ArrayList<>();
        handler = new Handler();
        spinner = F(R.id.spinner_content);
        textView_hint = F(R.id.text_result);
        textView_add = F(R.id.text_result_add);
        imageView = F(R.id.img_search_delete);
        textView_time = F(R.id.text_result_time);
        listView = F(R.id.search_listview_result);
        layout_add = F(R.id.relative_result_add);
        layout_time = F(R.id.relative_result_time);
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
        layout_add.setOnClickListener(this);
        layout_time.setOnClickListener(this);
        listView.setOnItemClickListener(this);
        gridview_history.setOnItemClickListener(this);
        gridview_subcribe.setOnItemClickListener(this);
        gridview_subcribe_no.setOnItemClickListener(this);
        editText.setHintTextColor(getResources().getColor(R.color.color_gray));


        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.simple_spinner_item);
        level = new String[]{"供给", "求购"};//资源文件
        level1 = new String[]{"1", "0"};//资源文件
        for (int i = 0; i < level.length; i++) {
            adapter.add(level[i]);
        }
        adapter.setDropDownViewResource(android.R.layout.simple_list_item_1);
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (spinnerSelected) {
                    page = 1;
                    isRefresh = true;
                    hasMoerData = true;
                    is_buy = level1[position];
                    keywords = (editText.getText().toString().equals("")) ? ("7000f") : (editText.getText().toString());
                    getPhysical_Search(1, keywords, time, is_buy, area);
                } else {
                    spinnerSelected = true;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        //edittext的回车监听
        editText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView arg0, int arg1, KeyEvent arg2) {
                if (arg1 == EditorInfo.IME_ACTION_SEARCH | (arg2 != null && arg2.getAction() == KeyEvent.ACTION_DOWN)) {
                    page = 1;
                    isRefresh = true;
                    hasMoerData = true;
                    keywords = (editText.getText().toString().equals("")) ? ("7000f") : (editText.getText().toString());
                    getPhysical_Search(1, keywords, time, is_buy, area);
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
                        Log.e("----", page + "");
                        page++;
                        if (hasMoerData) {
                            getPhysical_Search(page, keywords, time, is_buy, area);
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
    public void getPhysical_Search(int page, String keyWords, String time, String is_buy, String area) {
        Map map = new HashMap();
        map.put("keywords", keyWords);
        map.put("page", page + "");
        map.put("size", "10");
        map.put("time", time);
        map.put("is_buy", is_buy);
        map.put("is_futures", "0");
        map.put("area", area);
        postAsyn(this, API.BASEURL + API.PLASTIC_SEARCH, map, this, 2);
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
                isRefresh = true;
                hasMoerData = true;
                keywords = (editText.getText().toString().equals("")) ? ("7000f") : (editText.getText().toString());
                getPhysical_Search(1, keywords, time, is_buy, area);
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
                page = 1;
                isRefresh = true;
                hasMoerData = true;
                keywords = historyBean.getHistory().get(position);
                editText.setText(keywords);
                editText.setSelection(keywords.length());
                getPhysical_Search(1, keywords, time, is_buy, area);
                break;
            case R.id.mygrid_search_subcribe://猜你所想
                page = 1;
                isRefresh = true;
                hasMoerData = true;
                keywords = historyBean.getRecommend().get(position);
                editText.setText(keywords);
                editText.setSelection(keywords.length());
                getPhysical_Search(1, keywords, time, is_buy, area);
                break;
            case R.id.mygrid_search_null://相关搜索
                page = 1;
                isRefresh = true;
                hasMoerData = true;
                keywords = bean.getCombine().get(position);
                editText.setText(keywords);
                editText.setSelection(keywords.length());
                getPhysical_Search(1, keywords, time, is_buy, area);
                break;
            case R.id.search_listview_result:
                if (list.get(position).getType().equals("9")) {//来自QQ群
                    Intent intent = new Intent(SupDem_Search_Activity.this, SupDem_QQ_DetailActivity.class);
                    intent.putExtra("company", list.get(position).getCompany());
                    intent.putExtra("plastic_number", list.get(position).getPlsticNumber());
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
                        intent.putExtra("what", (user_id.equals(userid)) ? ("5") : (itemBean.what));

                        startActivity(intent);
                    } catch (Exception e) {
                    }
                }
                break;
        }
    }

    @Override
    public void callBack(Object object, int type) {
        try {
            Gson gson = new Gson();
            boolean err = new JSONObject(object.toString()).getString("err").equals("0");
            Log.e("---------" + type, object.toString());
            if (type == 1 && err) {
                historyBean = gson.fromJson(object.toString(), HistoryBean.class);
                adapter_grid = new SupDem_Search_Grid_Adapter(this, historyBean.getHistory());
                gridview_history.setAdapter(adapter_grid);
                SupDem_Search_Grid_Adapter adapter_grid1 = new SupDem_Search_Grid_Adapter(this, historyBean.getRecommend());
                gridview_subcribe.setAdapter(adapter_grid1);
            }
            if (type == 2) {
                search_default_linear.setVisibility(View.GONE);
                search_result_linear.setVisibility(View.VISIBLE);
                InputMethodManager in = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                in.hideSoftInputFromWindow(editText.getWindowToken(), 0);
                SearchResultBean resultBean = null;
                if (err) {
                    resultBean = gson.fromJson(object.toString(), SearchResultBean.class);
                    if (page == 1) {
                        frameLayout.setVisibility(View.VISIBLE);
                        search_result_linear_no.setVisibility(View.GONE);
                        adapter_list = new SupDem_Search_List_Adapter(this, resultBean.getList());
                        listView.setAdapter(adapter_list);
                        showRefreshPopou("为你搜索" + resultBean.getList().size() + "条信息");
                        list.clear();
                        list.addAll(resultBean.getList());
                    } else {
                        list.addAll(resultBean.getList());
                        adapter_list.setList(list);
                        adapter_list.notifyDataSetChanged();
                    }
                } else {
                    hasMoerData = false;
                    frameLayout.setVisibility(View.GONE);
                    search_result_linear_no.setVisibility(View.VISIBLE);
                    textView_no.setText("抱歉，未能找到相关搜索！");
                    bean = gson.fromJson(object.toString(), SearchNoResultBean.class);
                    adapter_grid = new SupDem_Search_Grid_Adapter(this, bean.getCombine());
                    gridview_subcribe_no.setAdapter(adapter_grid);
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
        }
    }

    //展示刷新后的popou
    public void showRefreshPopou(String text) {
        if (isRefresh) {
            textView_hint.setVisibility(View.VISIBLE);
            isRefresh = false;
            if (TextUtils.isNullOrEmpty(text)) {
                textView_hint.setText(text);
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        textView_hint.setVisibility(View.GONE);
                    }
                }, 1500);
            } else {
                TextUtils.Toast(this, "已是最新头条信息！");
            }
        }
    }

    @Override
    public void failCallBack(int type) {
    }

    //地区回调
    @Override
    public void addCallBack(int po_pro, int position) {
        page = 1;
        isRefresh = true;
        hasMoerData = true;
        textView_add.setText(list_area.get(po_pro).getData().get(position).getShow());
        textView_add.setTextColor(getResources().getColor(R.color.color_red));
        area = list_area.get(po_pro).getData().get(position).getValue();
        getPhysical_Search(1, keywords, time, is_buy, area);
    }

    //时间回调
    @Override
    public void timeCallBack(int po) {
        page = 1;
        isRefresh = true;
        hasMoerData = true;
        textView_time.setText(list_time.get(po).getShow());
        textView_time.setTextColor(getResources().getColor(R.color.color_red));
        time = list_time.get(po).getValue();
        getPhysical_Search(1, keywords, time, is_buy, area);
    }

    public void onResume() {
        super.onResume();
        MobclickAgent.onResume(this);
        if (SharedUtils.getSharedUtils().getBoolean(this, "fromsearch")) {
            SharedUtils.getSharedUtils().setBooloean(this, "fromsearch", false);
            MainActivity.goToHeadLine();
            finish();
        }
    }

    public void onPause() {
        super.onPause();
        MobclickAgent.onPause(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(editText.getWindowToken(), 0);
    }
}
