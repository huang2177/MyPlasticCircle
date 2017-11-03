package com.myplas.q.contact;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.NestedScrollView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.google.gson.Gson;
import com.myplas.q.R;
import com.myplas.q.common.api.API;
import com.myplas.q.common.netresquset.ResultCallBack;
import com.myplas.q.common.utils.SharedUtils;
import com.myplas.q.common.utils.TextUtils;
import com.myplas.q.common.view.EmptyView;
import com.myplas.q.common.view.MyNestedScrollView;
import com.myplas.q.contact.adapter.Contact_Detail_LV_Adapter;
import com.myplas.q.contact.beans.ContactBean;
import com.myplas.q.contact.beans.ContactInfoBean;
import com.myplas.q.contact.beans.ContactSupDemBean;
import com.myplas.q.guide.activity.BaseActivity;
import com.myplas.q.supdem.activity.SupDem_Detail_Activity;
import com.myplas.q.supdem.activity.SupDem_QQ_DetailActivity;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 作者:huangshuang
 * 事件 2017/10/9 0009.
 * 邮箱： 15378412400@163.com
 */

public class Fragment_Contact_Detail extends Fragment implements ResultCallBack
        , MyNestedScrollView.onScrollIterface {

    private View mView;
    private ListView mMyListview;
    private MyNestedScrollView mScrollView;

    private Intent mIntent;
    private SharedUtils mSharedUtils;
    private Contact_Detail_LV_Adapter mAdapter;
    private List<ContactSupDemBean.DataBean> mBeanList;

    public int type, page;
    private List<ContactInfoBean.DataBean.DemandBean> demandList;
    private List<ContactInfoBean.DataBean.SuppliesBean> suppliesList;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        page = 1;
        type = type == 0 ? 2 : 1;
        mIntent = getActivity().getIntent();
        mSharedUtils = SharedUtils.getSharedUtils();

        mView = View.inflate(getActivity(), R.layout.fragment_layout_supdem_detail_chj, null);
        mMyListview = (ListView) mView.findViewById(R.id.fragment_supdem_detail_lv);
        mScrollView = (MyNestedScrollView) mView.findViewById(R.id.scrollview);

        mScrollView.setOnScrollIterface(this);

        //getTaPur(type, type, page);
        mMyListview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                goToDetail(null, null, position);
            }
        });

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return mView;
    }

    public void getTaPur(int type, int _type, int page) {
        Map<String, String> map = new HashMap<>();
        map.put("size", "15");
        map.put("page", page + "");
        map.put("type", type + "");
        map.put("userid", mIntent.getStringExtra("userid"));
        String url = API.BASEURL + API.GET_TA_PUR;
        BaseActivity.postAsyn1(getActivity(), url, map, this, _type, false);
    }


    @Override
    public void loadMore() {
//        page++;
//        getTaPur(type, type, page);
    }

    //跳转至详情
    public void goToDetail(String id_, String userid, int position) {
        if (type == 2) {
            id_ = suppliesList.get(position).getPur_id();
            userid = suppliesList.get(position).getUser_id();
        } else {
            id_ = demandList.get(position).getPur_id();
            userid = demandList.get(position).getUser_id();
        }
        Intent intent = new Intent(getActivity(), SupDem_Detail_Activity.class);
        intent.putExtra("id", id_);
        intent.putExtra("userid", userid);
        startActivity(intent);
    }

    public void showDemand(List<ContactInfoBean.DataBean.DemandBean> demandList) {
        if (demandList.size() != 0) {
            this.demandList = demandList;
            mAdapter = new Contact_Detail_LV_Adapter(getActivity(), demandList, 0);
            mMyListview.setAdapter(mAdapter);
        } else {
            EmptyView emptyView = new EmptyView(getActivity());
            emptyView.mustCallInitWay(mMyListview);
            emptyView.setNoMessageText("没有相关数据");
            emptyView.setMyManager(R.drawable.icon_null);
            mMyListview.setEmptyView(emptyView);
        }
    }

    public void showSupplies(List<ContactInfoBean.DataBean.SuppliesBean> suppliesList) {
        if (suppliesList.size() != 0) {
            this.suppliesList = suppliesList;
            mAdapter = new Contact_Detail_LV_Adapter(getActivity(), suppliesList);
            mMyListview.setAdapter(mAdapter);
        } else {
            EmptyView emptyView = new EmptyView(getActivity());
            emptyView.mustCallInitWay(mMyListview);
            emptyView.setNoMessageText("没有相关数据");
            emptyView.setMyManager(R.drawable.icon_null);
            mMyListview.setEmptyView(emptyView);
        }
    }

    @Override
    public void callBack(Object object, int type) {
        try {
//            Gson gson = new Gson();
//            String err = new JSONObject(object.toString()).getString("err");
//            ContactSupDemBean supDemBean = null;
//            if (err.equals("0")) {
//                supDemBean = gson.fromJson(object.toString(), ContactSupDemBean.class);
//                if (page == 1) {
//                    setListener(false);
//                    mAdapter = new Contact_Detail_LV_Adapter(getActivity(), supDemBean.getData());
//                    mMyListview.setAdapter(mAdapter);
//
//                    mBeanList.clear();
//                    mBeanList.addAll(supDemBean.getData());
//                } else {
//                    mBeanList.addAll(supDemBean.getData());
//                    mAdapter.setList(mBeanList);
//                    mAdapter.notifyDataSetChanged();
//                }
//            } else {
//                if (page == 1) {
//                    setListener(true);
//                    EmptyView emptyView = new EmptyView(getActivity());
//                    emptyView.mustCallInitWay(mMyListview);
//                    emptyView.setNoMessageText(new JSONObject(object.toString()).getString("msg"));
//                    emptyView.setMyManager(type == 2
//                            ? R.drawable.icon_intelligent_recommendation1
//                            : R.drawable.icon_intelligent_recommendation2);
//                    mMyListview.setEmptyView(emptyView);
//                } else {
//                    TextUtils.Toast(getActivity(), "没有更多数据了！");
//                }
//            }

        } catch (Exception e) {
        }
    }

    private void setListener(final boolean scrollabled) {
        mScrollView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return scrollabled;
            }
        });
    }

    @Override
    public void failCallBack(int type) {

    }
}
