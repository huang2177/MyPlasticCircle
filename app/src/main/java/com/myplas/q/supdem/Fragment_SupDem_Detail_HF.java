package com.myplas.q.supdem;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;
import com.myplas.q.R;
import com.myplas.q.app.fragment.BaseFragment;
import com.myplas.q.common.api.API;
import com.myplas.q.common.listener.MyOnItemClickListener;
import com.myplas.q.common.net.ResultCallBack;
import com.myplas.q.common.utils.SharedUtils;
import com.myplas.q.common.view.EmptyView;
import com.myplas.q.supdem.beans.ReplyBean;
import com.myplas.q.supdem.adapter.SupDem_Detail_LV_HFAdapter;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 作者:huangshuang
 * 事件 2017/10/9 0009.
 * 邮箱： 15378412400@163.com
 */

public class Fragment_SupDem_Detail_HF extends BaseFragment implements ResultCallBack {
    private View mView;
    private EmptyView mEmptyView;
    private RecyclerView mRecyclerView;
    private NestedScrollView mScrollView;

    private Intent mIntent;
    private SharedUtils mSharedUtils;
    private List<ReplyBean.DataBean> mBeanList;
    private SupDem_Detail_LV_HFAdapter mHFAdapter;

    public MyOnItemClickListener mMyOnItemClickListener;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mIntent = getActivity().getIntent();
        mSharedUtils = SharedUtils.getSharedUtils();
        mHFAdapter = new SupDem_Detail_LV_HFAdapter(getActivity(), mMyOnItemClickListener);
        LinearLayoutManager manager = new LinearLayoutManager(getActivity());

        mView = View.inflate(getActivity(), R.layout.fragment_layout_supdem_detail_hf, null);
        mEmptyView = (EmptyView) mView.findViewById(R.id.fragment_supdem_detail_editview);
        mRecyclerView = (RecyclerView) mView.findViewById(R.id.fragment_supdem_detail_rv);

        mRecyclerView.setLayoutManager(manager);
        mRecyclerView.setAdapter(mHFAdapter);
        getReply();
        return mView;
    }

    public void getReply() {
        Map<String, String> map = new HashMap<>(16);
        map.put("page", "1");
        map.put("size", "15");
        map.put("id", mIntent.getStringExtra("id"));
        map.put("user_id", mIntent.getStringExtra("userid"));
        getAsyn(getActivity(), API.COMMENTS, map, this, 1);
    }

    @Override
    public void callBack(Object object, int type) {
        try {
            Gson gson = new Gson();
            String err = new JSONObject(object.toString()).getString("code");
            if ("0".equals(err)) {
                mEmptyView.setVisibility(View.GONE);
                mRecyclerView.setVisibility(View.VISIBLE);
                ReplyBean replyBean = gson.fromJson(object.toString(), ReplyBean.class);
                mBeanList = replyBean.getData();
                mHFAdapter.setList(mBeanList);
                mHFAdapter.notifyDataSetChanged();
            }
        } catch (Exception e) {
        }
    }

    @Override
    public void failCallBack(int type, String message, int httpCode) {
        try {
            String err = new JSONObject(message).getString("code");
            if ("404".equals(err) && httpCode == 404) {
                mEmptyView.setVisibility(View.VISIBLE);
                mRecyclerView.setVisibility(View.GONE);
                mEmptyView.setMyManager(R.drawable.icon_intelligent_recommendation2);
                mEmptyView.setNoMessageText(new JSONObject(message).getString("message"));
            }
        } catch (Exception e) {

        }
    }
}
