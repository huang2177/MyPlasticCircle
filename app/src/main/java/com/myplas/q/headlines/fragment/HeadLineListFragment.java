package com.myplas.q.headlines.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import com.google.gson.Gson;
import com.myplas.q.R;
import com.myplas.q.common.api.API;
import com.myplas.q.common.netresquset.ResultCallBack;
import com.myplas.q.common.utils.NetUtils;
import com.myplas.q.common.utils.SharedUtils;
import com.myplas.q.common.utils.TextUtils;
import com.myplas.q.common.view.NoResultLayout;
import com.myplas.q.common.view.XListView;
import com.myplas.q.guide.activity.BaseActivity;
import com.myplas.q.headlines.activity.Head_Lines_DetailActivity;
import com.myplas.q.headlines.adapter.CateListAdapter;
import com.myplas.q.headlines.adapter.TTAdapter;
import com.myplas.q.headlines.bean.CateListBean;
import com.myplas.q.headlines.bean.SubcribleBean;
import com.myplas.q.myinfo.setting.activity.MyDataActivity;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HeadLineListFragment extends Fragment implements ResultCallBack, XListView.IXListViewListener, View.OnClickListener {
    private boolean isRefresh;
    private SharedUtils sharedUtils;
    private int lastvisibleItemCount;
    public String cate_id, keywords, title;
    public int page, po, visibleItemCount;

    private View view;
    private TTAdapter ttAdapter;
    private XListView mXListView;
    private LinearLayout mLayoutNoData;
    private CateListAdapter cateListAdapter;
    private ImageButton imageButton, imageButton_backup;

    private List<CateListBean.InfoBean> list_catelist;
    private List<SubcribleBean.DataBean> list_subcirble;
    private List<CateListBean.InfoBean> list_catelist_more;
    private List<SubcribleBean.DataBean> list_subcirble_more;

    public Myinterface mMyinterface;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        page = 1;
        isRefresh = false;
        list_catelist_more = new ArrayList<>();
        list_subcirble_more = new ArrayList<>();
        sharedUtils = SharedUtils.getSharedUtils();

        view = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_layout_headline_list_fm, null, false);
        imageButton = (ImageButton) view.findViewById(R.id.img_reload);
        mLayoutNoData = (LinearLayout) view.findViewById(R.id.layout_noresult);
        imageButton_backup = (ImageButton) view.findViewById(R.id.image_backup);
        mXListView = (XListView) view.findViewById(R.id.fragment_headline_list_lv);

        mXListView.setPullRefreshEnable(true);
        mXListView.setXListViewListener(this);
        imageButton.setOnClickListener(this);
        mXListView.setDividerViewVisibility(false);
        imageButton_backup.setOnClickListener(this);

        //item的监听
        mXListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (NetUtils.isNetworkStateed(getActivity())) {
                    Intent intent = new Intent(getActivity(), Head_Lines_DetailActivity.class);
                    if (po == 0 || po == -1) {
                        intent.putExtra("title", "推荐");
                        intent.putExtra("id", list_subcirble_more.get(position - 1).getId());
                        startActivity(intent);
                    } else {
                        intent.putExtra("title", title);
                        intent.putExtra("id", list_catelist_more.get(position - 1).getId());
                        startActivity(intent);
                    }
                }
            }
        });
        //加载更多。。。。
        mXListView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                if (scrollState == AbsListView.OnScrollListener.SCROLL_STATE_IDLE && mXListView.getCount() > visibleItemCount) {
                    InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
                    if (view.getLastVisiblePosition() == view.getCount() - 1) {
                        page++;
                        if (po == -1) {
                            get_Subscribe(page, keywords, "1", false);
                        } else if (po == 0) {
                            get_Subscribe(page, "", "2", false);
                        } else {
                            get_CateList(page, cate_id, false);
                        }
                    }
                }
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                HeadLineListFragment.this.visibleItemCount = visibleItemCount;
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
        get_Subscribe(1, "", "2", true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return view;
    }

    //获取推荐
    public void get_Subscribe(int page, String keywords, String subscribe, boolean isShow) {
        this.page = page;
        this.keywords = keywords;

        Map<String, String> map = new HashMap<String, String>();
        map.put("token", sharedUtils.getData(getActivity(), "token"));
        map.put("page", page + "");
        map.put("keywords", keywords);
        map.put("subscribe", subscribe);
        BaseActivity.postAsyn1(getActivity(), API.BASEURL + API.GET_SUBSCRIBE, map, this, 4, isShow);
    }

    //获取其他
    public void get_CateList(int page, String cate_id, boolean isShow) {
        this.cate_id = cate_id;
        Map<String, String> map = new HashMap<String, String>();
        map.put("token", sharedUtils.getData(getActivity(), "token"));
        map.put("page", page + "");
        map.put("size", "10");
        map.put("cate_id", cate_id);
        BaseActivity.postAsyn1(getActivity(), API.BASEURL + API.GET_CATE_LIST, map, this, 5, isShow);
    }

    @Override
    public void callBack(Object object, int type) {
        try {
//            imageButton.setVisibility(View.GONE);
//            myListview.setVisibility(View.VISIBLE);
//            imageButton_backup.setVisibility(View.GONE);
            Gson gson = new Gson();
            String err = new JSONObject(object.toString()).getString("err");
//            if (type == 4) {//推荐
//                if (err.equals("0")) {
//                    SubcribleBean subcribleBean = gson.fromJson(object.toString(), SubcribleBean.class);
//                    list_subcirble = subcribleBean.getData();
//                    if (page == 1) {
//                        ttAdapter = new TTAdapter(getActivity(), list_subcirble);
//                        mXListView.setAdapter(ttAdapter);
//                        mXListView.stopRefresh();
//                        list_subcirble_more.clear();
//                        list_subcirble_more.addAll(list_subcirble);
//                        showRefreshPopou(subcribleBean.getShow_msg());
//                        lastvisibleItemCount = list_subcirble.size();
//                    } else {
//                        isRefresh = false;
//                        if (list_subcirble != null && list_subcirble.size() != 0) {
//                            mXListView.stopLoadMore();
//                            list_subcirble_more.addAll(list_subcirble);
//                            ttAdapter.setList(list_subcirble_more);
//                            ttAdapter.notifyDataSetChanged();
//                        }
//                    }
//                } else if (err.equals("1") || "998".equals(err)) {
//                    isRefresh = false;
//                    mXListView.stopRefresh();
//                    sharedUtils.setData(getActivity(), "token", "");
//                    sharedUtils.setData(getActivity(), "userid", "");
//                    sharedUtils.setBooloean(getActivity(), "logined", false);
//
//                } else {
//                    isRefresh = false;
//                    mXListView.stopRefresh();
////                    myListview.setVisibility(View.GONE);
////                    layout_nodata.setVisibility(View.VISIBLE);
////                    imageButton_backup.setVisibility(View.GONE);
////                    text_nodata.setText(new JSONObject(object.toString()).getString("msg"));
//                }
//            }
            if (type == 5) {//其他
                if (err.equals("0")) {
                    CateListBean cateListBean = gson.fromJson(object.toString(), CateListBean.class);
                    list_catelist = cateListBean.getInfo();
                    if (page == 1) {
                        mXListView.setVisibility(View.VISIBLE);
                        mLayoutNoData.setVisibility(View.GONE);
                        imageButton_backup.setVisibility(View.GONE);
                        cateListAdapter = new CateListAdapter(getActivity(), list_catelist);
                        mXListView.setAdapter(cateListAdapter);
                        mXListView.stopRefresh();
                        list_catelist_more.clear();
                        list_catelist_more.addAll(list_catelist);
                        lastvisibleItemCount = list_catelist.size();

                        if (isRefresh) {
                            isRefresh = false;
                            mMyinterface.callBack(cateListBean.getShow_msg());
                        } else {
                            //TextUtils.Toast(getActivity(), "已是最新头条信息！");
                        }
                    } else {
                        isRefresh = false;
                        if (list_catelist != null && list_catelist.size() != 0) {
                            mXListView.stopLoadMore();
                            list_catelist_more.addAll(list_catelist);
                            cateListAdapter.setList(list_catelist_more);
                            cateListAdapter.notifyDataSetChanged();
                        }
                    }
                } else if (err.equals("1") || "998".equals(err)) {
                    isRefresh = false;
                    mXListView.stopRefresh();
                    sharedUtils.setData(getActivity(), "token", "");
                    sharedUtils.setData(getActivity(), "userid", "");
                    sharedUtils.setBooloean(getActivity(), "logined", false);
                    sharedUtils.setData(getActivity(), "toast_msg", new JSONObject(object.toString()).getString("msg"));
                } else {
                    isRefresh = false;
                    mXListView.stopRefresh();
                    mXListView.setVisibility(View.GONE);
                    mLayoutNoData.setVisibility(View.VISIBLE);
                    imageButton_backup.setVisibility(View.GONE);
                }
            }
        } catch (Exception e) {
        }
    }

    @Override
    public void failCallBack(int type) {
        if (list_subcirble_more.size() == 0) {
            mXListView.setVisibility(View.GONE);
            mLayoutNoData.setVisibility(View.GONE);
            imageButton.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onRefresh() {
        page = 1;
        isRefresh = true;
        if (po == -1) {
            get_Subscribe(page, "", "1", false);
        } else if (po == 0) {
            get_Subscribe(page, "", "2", false);
        } else {
            get_CateList(page, cate_id, false);
        }
    }

    @Override
    public void onLoadMore() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.img_reload:
                get_Subscribe(1, "", "2", true);
                break;
            case R.id.image_backup:
                mXListView.setSelection(0);
                imageButton_backup.setVisibility(View.GONE);
                break;
        }
    }

    interface Myinterface {
        void callBack(String s);
    }

    public void setMyinterface(Myinterface mMyinterface) {
        this.mMyinterface = mMyinterface;
    }
}
