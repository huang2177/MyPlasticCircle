package com.myplas.q.addresslist;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.NestedScrollView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.google.gson.Gson;
import com.myplas.q.R;
import com.myplas.q.addresslist.adapter.Contact_Detail_LV_Adapter;
import com.myplas.q.common.api.API;
import com.myplas.q.common.netresquset.ResultCallBack;
import com.myplas.q.common.utils.SharedUtils;
import com.myplas.q.common.view.EmptyView;
import com.myplas.q.guide.activity.BaseActivity;
import com.myplas.q.supdem.beans.DeliverPriceBean;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 作者:huangshuang
 * 事件 2017/10/9 0009.
 * 邮箱： 15378412400@163.com
 */

public class Fragment_Contact_Detail extends Fragment implements ResultCallBack {
    private View mView;
    private EmptyView mEmptyView;
    private ListView mMyListview;
    private NestedScrollView mScrollView;

    private Intent mIntent;
    private SharedUtils mSharedUtils;
    private Contact_Detail_LV_Adapter mAdapter;
    private List<DeliverPriceBean.DataBean> mBeanList;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mIntent = getActivity().getIntent();
        mSharedUtils = SharedUtils.getSharedUtils();
        mAdapter = new Contact_Detail_LV_Adapter(getActivity(), null);

        mView = View.inflate(getActivity(), R.layout.fragment_layout_supdem_detail_chj, null);
        mEmptyView = (EmptyView) mView.findViewById(R.id.fragment_supdem_detail_editview);
        mMyListview = (ListView) mView.findViewById(R.id.fragment_supdem_detail_lv);
        mScrollView = (NestedScrollView) mView.findViewById(R.id.scrollview);

        mMyListview.setAdapter(mAdapter);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return mView;
    }

    public void getTaPur(String type, int type1) {
//        Map<String, String> map = new HashMap<>();
//        map.put("userid", mIntent.getStringExtra("userid"));
//        map.put("type", type);
//        map.put("page", "1");
//        map.put("size", "30");
//        String url = API.BASEURL + API.GET_TA_PUR;
//        BaseActivity.postAsyn1(getActivity(), url, map, this, type1, false);
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
    public void callBack(Object object, int type) {
        try {
            Gson gson = new Gson();
            String err = new JSONObject(object.toString()).getString("err");
            if (type == 1) {
                if (err.equals("0")) {
                    setListener(false);
                    mEmptyView.setVisibility(View.GONE);
                    DeliverPriceBean deliverPriceBean = gson.fromJson(object.toString(), DeliverPriceBean.class);
                    mBeanList = deliverPriceBean.getData();
                    mAdapter.setList(mBeanList);
                    mAdapter.notifyDataSetChanged();
                } else {
                    setListener(true);
                    mEmptyView.setMyManager(R.drawable.icon_intelligent_recommendation1);
                    mEmptyView.setNoMessageText(new JSONObject(object.toString()).getString("msg"));
                }
            }
        } catch (Exception e) {
        }
    }

    @Override
    public void failCallBack(int type) {

    }
}
