package com.myplas.q.headlines.fragment;

import android.app.ActionBar;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Rect;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.myplas.q.R;
import com.myplas.q.common.api.API;
import com.myplas.q.common.netresquset.ResultCallBack;
import com.myplas.q.common.utils.NetUtils;
import com.myplas.q.common.utils.SharedUtils;
import com.myplas.q.common.utils.TextUtils;
import com.myplas.q.common.view.CustomPopupWindow;
import com.myplas.q.common.view.XListView;
import com.myplas.q.headlines.activity.Cate_Dialog_Activtiy;
import com.myplas.q.headlines.activity.Head_Lines_DetailActivity;
import com.myplas.q.headlines.adapter.CateListAdapter;
import com.myplas.q.headlines.adapter.GridViewAdapter;
import com.myplas.q.headlines.adapter.TTAdapter;
import com.myplas.q.headlines.bean.CateListBean;
import com.myplas.q.headlines.bean.ItemBean;
import com.myplas.q.headlines.bean.MyCateBean;
import com.myplas.q.headlines.bean.SubcribleBean;
import com.myplas.q.guide.activity.BaseActivity;
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
 * 时间：2017/3/17 14:45
 */
public class Fragment_HeadLines extends Fragment implements View.OnClickListener, ResultCallBack, XListView.IXListViewListener, GridViewAdapter.Myinterface {
    private Handler handler;
    private String keywords;
    private boolean isRefresh;
    private SharedUtils sharedUtils;
    private int lastvisibleItemCount;
    private int page, po, visibleItemCount;

    private List list1, list2;
    private List<ItemBean> list;
    private List<CateListBean.InfoBean> list_catelist;
    private List<SubcribleBean.DataBean> list_subcirble;
    private List<CateListBean.InfoBean> list_catelist_more;
    private List<SubcribleBean.DataBean> list_subcirble_more;

    private TTAdapter ttAdapter;
    private CateListAdapter cateListAdapter;
    private GridViewAdapter gridViewAdapter;


    private View view;
    private GridView gridView;
    private EditText editText;
    private XListView myListview;
    private CustomPopupWindow popupWindow;
    private TextView search_src_text, textView_refresh;
    private ImageButton gd_imgbtn, imageButton, imageButton_backup;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        page = 1;
        sharedUtils = SharedUtils.getSharedUtils();
        view = LayoutInflater.from(getActivity()).inflate(R.layout.layout_find_fragment, null, false);

        handler = new Handler();
        list_catelist_more = new ArrayList<>();
        list_subcirble_more = new ArrayList<>();

        gridView = F(R.id.grid);
        editText = F(R.id.find_edit);
        imageButton = F(R.id.img_reload);
        gd_imgbtn = F(R.id.fx_gd_imgbtn);
        myListview = F(R.id.find_mylistview);
        imageButton_backup = F(R.id.image_backup);
        search_src_text = F(R.id.search_src_text);

        gd_imgbtn.setOnClickListener(this);
        imageButton.setOnClickListener(this);
        myListview.setPullRefreshEnable(true);
        myListview.setXListViewListener(this);
        search_src_text.setOnClickListener(this);
        imageButton_backup.setOnClickListener(this);

        getData();

        int length = 85;
        DisplayMetrics dm = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(dm);
        float density = dm.density;
        int gridviewWidth = (int) (list.size() * (length + 1) * density);
        int itemWidth = (int) (length * density);
        final LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(gridviewWidth, LinearLayout.LayoutParams.FILL_PARENT);
        gridView.setLayoutParams(params); // 设置GirdView布局参数,横向布局的关键
        gridView.setColumnWidth(itemWidth); // 设置列表项宽
        gridView.setHorizontalSpacing(4); // 设置列表项水平间距
        gridView.setStretchMode(GridView.NO_STRETCH);
        gridView.setNumColumns(list.size()); // 设置列数量=列表集合数

        gridViewAdapter = new GridViewAdapter(getActivity(), list, this);
        gridView.setAdapter(gridViewAdapter);

        //edittext 回车监听
        editText.setOnEditorActionListener(new TextView.OnEditorActionListener() {

            @Override
            public boolean onEditorAction(TextView arg0, int arg1, KeyEvent arg2) {
                if (arg1 == EditorInfo.IME_ACTION_UNSPECIFIED && arg2 != null && arg2.getAction() == KeyEvent.ACTION_DOWN) {
                    keywords = (editText.getText().toString().equals("")) ? ("") : (editText.getText().toString());
                    get_Subscribe("1", keywords, "1", true);
                    InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(editText.getWindowToken(), 0);
                    return true;
                }
                return false;
            }
        });
        //item的监听
        myListview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (NetUtils.isNetworkStateed(getActivity())) {
                    Intent intent = new Intent(getActivity(), Head_Lines_DetailActivity.class);
                    if (po == 0 || po == -1) {
                        intent.putExtra("title", "推荐");
                        intent.putExtra("id", list_subcirble_more.get(position - 1).getId());
                        startActivity(intent);
                    } else {
                        intent.putExtra("title", list1.get(po).toString());
                        intent.putExtra("id", list_catelist_more.get(position - 1).getId());
                        startActivity(intent);
                    }
                }
            }
        });
        //加载更多。。。。
        myListview.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                if (scrollState == AbsListView.OnScrollListener.SCROLL_STATE_IDLE && myListview.getCount() > visibleItemCount) {
                    if (view.getLastVisiblePosition() == view.getCount() - 1) {
                        page++;
                        keywords = editText.getText().toString();
                        if (po == -1) {
                            get_Subscribe(page + "", keywords, "1", false);
                        } else if (po == 0) {
                            get_Subscribe(page + "", "", "2", false);
                        } else {
                            get_CateList(page + "", list2.get(po).toString(), false);
                        }
                    }
                }
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                Fragment_HeadLines.this.visibleItemCount = visibleItemCount;
                switch (po) {
                    case -1:
                        if (list_subcirble != null) {
                            imageButton_backup.setVisibility((view.getLastVisiblePosition() > lastvisibleItemCount) ? (View.VISIBLE) : (View.GONE));
                        }
                        break;
                    default:
                        if (list_catelist != null) {
                            imageButton_backup.setVisibility((view.getLastVisiblePosition() > lastvisibleItemCount) ? (View.VISIBLE) : (View.GONE));
                        }
                        break;
                }

            }
        });

        get_Subscribe("1", "", "2", true);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return view;
    }


    //获取推荐
    public void get_Subscribe(String page, String keywords, String subscribe, boolean isShow) {
        Map<String, String> map = new HashMap<String, String>();
        map.put("token", sharedUtils.getData(getActivity(), "token"));
        map.put("page", page);
        map.put("keywords", keywords);
        map.put("subscribe", subscribe);
        BaseActivity.postAsyn1(getActivity(), API.BASEURL + API.GET_SUBSCRIBE, map, this, 4, isShow);
    }

    //获取其他
    public void get_CateList(String page, String cate_id, boolean isShow) {
        Map<String, String> map = new HashMap<String, String>();
        map.put("token", sharedUtils.getData(getActivity(), "token"));
        map.put("page", page);
        map.put("size", "10");
        map.put("cate_id", cate_id);
        BaseActivity.postAsyn1(getActivity(), API.BASEURL + API.GET_CATE_LIST, map, this, 5, isShow);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.search_src_text:
                searchData(editText.getText().toString());
                break;
            case R.id.fx_gd_imgbtn:
                Intent intent = new Intent(getActivity(), Cate_Dialog_Activtiy.class);
                startActivity(intent);
                break;
            case R.id.img_reload:
                get_Subscribe("1", "", "2", true);
                break;
            case R.id.image_backup:
                myListview.setSelection(0);
                imageButton_backup.setVisibility(View.GONE);
                break;
        }
    }

    public void searchData(String keywords) {
        if (TextUtils.isNullOrEmpty(keywords)) {
            po = -1;
            page = 1;
            get_Subscribe("1", keywords, "1", true);
            InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(editText.getWindowToken(), 0);
        } else {
            TextUtils.Toast(getActivity(), "请输入关键字！");
        }
    }

    public <T extends View> T F(int id) {
        return (T) view.findViewById(id);
    }

    @Override
    public void callBack(Object object, int type) {
        try {
            myListview.setVisibility(View.VISIBLE);
            imageButton.setVisibility(View.GONE);
            Gson gson = new Gson();
            String err = new JSONObject(object.toString()).getString("err");
            if (type == 4) {//推荐
                if (err.equals("0")) {
                    SubcribleBean subcribleBean = gson.fromJson(object.toString(), SubcribleBean.class);
                    list_subcirble = subcribleBean.getData();
                    if (page == 1) {
                        ttAdapter = new TTAdapter(getActivity(), list_subcirble);
                        myListview.setAdapter(ttAdapter);
                        myListview.stopRefresh();
                        list_subcirble_more.clear();
                        list_subcirble_more.addAll(list_subcirble);
                        showRefreshPopou(subcribleBean.getShow_msg());
                        lastvisibleItemCount = list_catelist.size();
                    } else {
                        isRefresh = false;
                        if (list_subcirble != null && list_subcirble.size() != 0) {
                            myListview.stopLoadMore();
                            list_subcirble_more.addAll(list_subcirble);
                            ttAdapter.setList(list_subcirble_more);
                            ttAdapter.notifyDataSetChanged();
                        }
                    }
                } else if (err.equals("1") || "998".equals(err)) {
                    isRefresh = false;
                    myListview.stopRefresh();
//                    sharedUtils.setData(getActivity(), "token", "");
//                    sharedUtils.setData(getActivity(), "userid", "");
//                    sharedUtils.setBooloean(getActivity(), "logined", false);

                } else {
                    isRefresh = false;
                    myListview.stopRefresh();
                    TextUtils.Toast(getActivity(), new JSONObject(object.toString()).getString("msg"));
                }
            }

            if (type == 5) {//其他
                if (new JSONObject(object.toString()).getString("err").equals("0")) {
                    CateListBean cateListBean = gson.fromJson(object.toString(), CateListBean.class);
                    list_catelist = cateListBean.getInfo();
                    if (page == 1) {
                        cateListAdapter = new CateListAdapter(getActivity(), list_catelist);
                        myListview.setAdapter(cateListAdapter);
                        myListview.stopRefresh();
                        list_catelist_more.clear();
                        list_catelist_more.addAll(list_catelist);
                        showRefreshPopou(cateListBean.getShow_msg());
                        lastvisibleItemCount = list_catelist.size();
                    } else {
                        isRefresh = false;
                        if (list_catelist != null && list_catelist.size() != 0) {
                            myListview.stopLoadMore();
                            list_catelist_more.addAll(list_catelist);
                            cateListAdapter.setList(list_catelist_more);
                            cateListAdapter.notifyDataSetChanged();
                        }
                    }
                } else if (err.equals("1") || "998".equals(err)) {
                    isRefresh = false;
                    myListview.stopRefresh();
                    sharedUtils.setData(getActivity(), "token", "");
                    sharedUtils.setData(getActivity(), "userid", "");
                    sharedUtils.setBooloean(getActivity(), "logined", false);
                    sharedUtils.setData(getActivity(), "toast_msg", new JSONObject(object.toString()).getString("msg"));
                } else {
                    isRefresh = false;
                    myListview.stopRefresh();
                    TextUtils.Toast(getActivity(), new JSONObject(object.toString()).getString("msg"));
                }
            }
        } catch (Exception e) {
        }
    }

    @Override
    public void failCallBack(int type) {
        if (list_subcirble_more.size() == 0) {
            myListview.setVisibility(View.GONE);
            imageButton.setVisibility(View.VISIBLE);
        }
    }

    //初始化横向滑动 item数据
    public void getData() {
        list = new ArrayList();
        list1 = Arrays.asList("推荐", "塑料上游", "早盘预报", "企业动态", "中晨塑说", "美金市场", "期货资讯", "装置动态", "期刊报告", "独家解读");
        list2 = Arrays.asList("", "2", "1", "9", "4", "5", "21", "11", "13", "22");
        for (int i = 0; i < list1.size(); i++) {
            ItemBean itemBean = new ItemBean();
            itemBean.setString(list1.get(i).toString());
            itemBean.setCate_id(list2.get(i).toString());
            itemBean.setIsclicked(false);
            if (i == 0) {
                itemBean.setColor(getResources().getColor(R.color.color_red));
            } else {
                itemBean.setColor(getResources().getColor(R.color.color_balank));
            }
            list.add(itemBean);
        }
    }
    //展示刷新后的popou
    public void showRefreshPopou(String text) {
        if (isRefresh) {
            isRefresh = false;
            if (TextUtils.isNullOrEmpty(text)) {
                View view = View.inflate(getActivity(), R.layout.layout_refresh_popou, null);
                textView_refresh = (TextView) view.findViewById(R.id.text_refresh_fragement);
                popupWindow = new CustomPopupWindow(view, ActionBar.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.MATCH_PARENT);
                popupWindow.setFocusable(true);
                popupWindow.setOutsideTouchable(true);
                textView_refresh.setText(text);
                showPopou(popupWindow);
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        popupWindow.dismiss();
                    }
                }, 1500);
            } else {
                TextUtils.Toast(getActivity(), "已是最新头条信息！");
            }
        }
    }

    public void showPopou(CustomPopupWindow popupWindow) {
        if (android.os.Build.VERSION.SDK_INT >= 24) {
            int[] a = new int[2];
            editText.getLocationInWindow(a);
            popupWindow.showAtLocation(getActivity().getWindow().getDecorView(), Gravity.NO_GRAVITY, 0, a[1] + editText.getHeight());
        } else {
            popupWindow.showAsDropDown(editText);
        }
    }
    //刷新
    @Override
    public void onRefresh() {
        page = 1;
        isRefresh = true;
        if (po == -1) {
            get_Subscribe(page + "", keywords, "1", false);
        } else if (po == 0) {
            get_Subscribe(page + "", "", "2", false);
        } else {
            get_CateList(page + "", list2.get(po).toString(), false);
        }
    }

    //适配器的回调
    @Override
    public void getdata(int position) {
        page = 1;
        po = position;
        imageButton_backup.setVisibility(View.GONE);
        if (position == 0) {
            get_Subscribe("1", editText.getText().toString(), "2", true);
        } else {
            get_CateList("1", list2.get(position).toString(), true);
        }
    }

    @Override
    public void onLoadMore() {
    }

    public void onResume() {
        super.onResume();
        page=1;
        imageButton_backup.setVisibility(View.GONE);
        boolean isLogined=sharedUtils.getBoolean(getActivity(),"isLogined_headlines");
        if (isLogined){
            get_Subscribe("1", "", "2", true);
            sharedUtils.setBooloean(getActivity(), "isLogined_headlines", false);
        }
        MobclickAgent.onPageStart("MainScreen"); //统计页面，"MainScreen"为页面名称，可自定义
    }

    public void onPause() {
        super.onPause();
        MobclickAgent.onPageEnd("MainScreen");
    }

}
