package com.myplas.q.supdem;

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
import com.myplas.q.common.api.API;
import com.myplas.q.common.netresquset.ResultCallBack;
import com.myplas.q.common.utils.SharedUtils;
import com.myplas.q.common.view.EmptyView;
import com.myplas.q.guide.activity.BaseActivity;
import com.myplas.q.supdem.beans.DeliverPriceBean;
import com.myplas.q.supdem.adapter.SupDem_Detail_LV_CHJAdapter;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 作者:huangshuang
 * 事件 2017/10/9 0009.
 * 邮箱： 15378412400@163.com
 */

public class Fragment_SupDem_Detail_CHJ extends Fragment implements ResultCallBack {
    private View mView;
    private ListView mMyListview;
    private NestedScrollView mScrollView;

    private Intent mIntent;
    private SharedUtils mSharedUtils;
    private SupDem_Detail_LV_CHJAdapter mCHJAdapter;
    private List<DeliverPriceBean.DataBean> mBeanList;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mIntent = getActivity().getIntent();
        mSharedUtils = SharedUtils.getSharedUtils();
        mCHJAdapter = new SupDem_Detail_LV_CHJAdapter(getActivity());

        mView = View.inflate(getActivity(), R.layout.fragment_layout_supdem_detail_chj, null);
        mMyListview = (ListView) mView.findViewById(R.id.fragment_supdem_detail_lv);
        mScrollView = (NestedScrollView) mView.findViewById(R.id.scrollview);

        mMyListview.setAdapter(mCHJAdapter);
        getDeLiverPrice();
        return mView;
    }

    public void getDeLiverPrice() {
        Map<String, String> map = new HashMap<>();
        map.put("page", "1");
        map.put("size", "15");
        map.put("id", mIntent.getStringExtra("id"));
        map.put("rev_id", mIntent.getStringExtra("userid"));
        map.put("token", mSharedUtils.getData(getActivity(), "token"));
        String url1 = API.BASEURL + API.GET_DELIVER_PRICE;
        BaseActivity.postAsyn(getActivity(), url1, map, this, 1);
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
                    DeliverPriceBean deliverPriceBean = gson.fromJson(object.toString(), DeliverPriceBean.class);
                    mBeanList = deliverPriceBean.getData();
                    mCHJAdapter.setList(mBeanList);
                    mCHJAdapter.notifyDataSetChanged();
                } else {
                    setListener(true);
                    EmptyView emptyView = new EmptyView(getActivity());
                    emptyView.mustCallInitWay(mMyListview);
                    emptyView.setMyManager(R.drawable.icon_intelligent_recommendation1);
                    emptyView.setNoMessageText(new JSONObject(object.toString()).getString("msg"));
                    mMyListview.setEmptyView(emptyView);
                }
            }
        } catch (Exception e) {
        }
    }

    @Override
    public void failCallBack(int type) {

    }
}
