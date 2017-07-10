package com.myplas.q.supdem.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.RequiresApi;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
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
import com.myplas.q.common.api.API;
import com.myplas.q.common.netresquset.ResultCallBack;
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
    private String level[];
    private boolean isRefresh;
    private HistoryBean historyBean;
    private List<SearchResultBean.ListBean> list;
    private SupDem_Search_Grid_Adapter adapter_grid;
    private SupDem_Search_List_Adapter adapter_list;
    private List<TabCofigBean.DataBeanXXX.AreaBean.DataBeanXX> list_area;
    private List<TabCofigBean.DataBeanXXX.TimeBean.DataBean> list_time;

    private String keywords = "7000f", is_buy = "2", area, time;

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


        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.simple_spinner_item);
        level = new String[]{"全部", "供给", "求购"};//资源文件
        for (int i = 0; i < level.length; i++) {
            adapter.add(level[i]);
        }
        adapter.setDropDownViewResource(android.R.layout.simple_list_item_1);
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                is_buy = level[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    //获取历史搜索
    public void getSearch_Record() {
        Map map = new HashMap();
        map.put("keywords", editText.getText().toString());
        postAsyn(this, API.BASEURL + API.SEARCH_RECORD, map, this, 1);
    }
    //查询
    public void getPhysical_Search(String keyWords, String time, String is_buy, String area) {
        Map map = new HashMap();
        map.put("keywords", keyWords);
        map.put("page", "1");
        map.put("size", "30");
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
                keywords = editText.getText().toString();
                getPhysical_Search(keywords, time, is_buy, area);
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
            case R.id.mygrid_search_history:
                keywords = historyBean.getHistory().get(position);
                getPhysical_Search(keywords, time, is_buy, area);
                break;
            case R.id.mygrid_search_subcribe:
                keywords = historyBean.getHistory().get(position);
                getPhysical_Search(keywords, time, is_buy, area);
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
            if (type == 1 && err) {
                Log.e("----->11", object.toString());
                historyBean = gson.fromJson(object.toString(), HistoryBean.class);
                adapter_grid = new SupDem_Search_Grid_Adapter(this, historyBean.getHistory());
                gridview_history.setAdapter(adapter_grid);
                gridview_subcribe.setAdapter(adapter_grid);
            }
            if (type == 2) {
                Log.e("----->22", object.toString());
                search_default_linear.setVisibility(View.GONE);
                search_result_linear.setVisibility(View.VISIBLE);
                InputMethodManager in = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                in.hideSoftInputFromWindow(editText.getWindowToken(), 0);
                if (err) {
                    frameLayout.setVisibility(View.VISIBLE);
                    search_result_linear_no.setVisibility(View.GONE);
                    SearchResultBean bean = gson.fromJson(object.toString(), SearchResultBean.class);
                    list = bean.getList();
                    adapter_list = new SupDem_Search_List_Adapter(this, list);
                    listView.setAdapter(adapter_list);
                    showRefreshPopou("为你搜索" + list.size() + "条信息");
                } else {
                    frameLayout.setVisibility(View.GONE);
                    search_result_linear_no.setVisibility(View.VISIBLE);
                    textView_no.setText("抱歉，未能找到相关搜索！");
                    SearchNoResultBean bean = gson.fromJson(object.toString(), SearchNoResultBean.class);
                    adapter_grid = new SupDem_Search_Grid_Adapter(this, bean.getCombine());
                    gridview_subcribe_no.setAdapter(adapter_grid);
                }
            }
            if (type == 3 && err) {
                Log.e("----->33", object.toString());
                TabCofigBean bean = gson.fromJson(object.toString(), TabCofigBean.class);
                list_area = bean.getData().getArea().getData();
                list_time = bean.getData().getTime().getData();

                //设置默认的时间
                time = bean.getData().getTime().getCurrentValue();
                int currentitem_time = Integer.parseInt(bean.getData().getTime().getCurrentItem());
                SharedUtils.getSharedUtils().setData(this, "position", currentitem_time + "");
                textView_time.setText(list_time.get(currentitem_time).getShow());

                //设置默认的地区
                area = bean.getData().getArea().getCurrentValue();
                int currentitem_add = Integer.parseInt(bean.getData().getArea().getCurrentItem());
                for (int i = 0; i < list_area.get(currentitem_add).getData().size(); i++) {
                    if (list_area.get(currentitem_add).getData().get(i).getValue().equals(bean.getData().getArea().getCurrentValue())) {
                        textView_add.setText(list_area.get(currentitem_add).getShow() + "-" + list_area.get(currentitem_add).getData().get(i).getShow());
                    }
                }
            }
            if (type == 4 && err) {
                Log.e("----->44", object.toString());
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
                }, 2000);
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
    public void addCallBack(int po_pro,int position) {
        isRefresh = true;
        textView_add.setText(list_area.get(po_pro).getShow()+"-"+list_area.get(po_pro).getData().get(position).getShow());
        textView_add.setTextColor(getResources().getColor(R.color.color_red));
        area = list_area.get(po_pro).getData().get(position).getValue();
        getPhysical_Search(keywords, time, is_buy, area);
    }
    //时间回调
    @Override
    public void timeCallBack(int po) {
        isRefresh = true;
        textView_time.setText(list_time.get(po).getShow());
        textView_time.setTextColor(getResources().getColor(R.color.color_red));
        time = list_time.get(po).getValue();
        getPhysical_Search(keywords, time, is_buy, area);
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
}
