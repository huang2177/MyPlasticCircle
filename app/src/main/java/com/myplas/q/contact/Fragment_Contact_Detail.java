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
import android.widget.ListView;

import com.google.gson.Gson;
import com.myplas.q.R;
import com.myplas.q.common.api.API;
import com.myplas.q.contact.adapter.Contact_Detail_LV_Adapter;
import com.myplas.q.common.netresquset.ResultCallBack;
import com.myplas.q.common.utils.SharedUtils;
import com.myplas.q.common.view.EmptyView;
import com.myplas.q.contact.beans.ContactSupDemBean;
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
    private ListView mMyListview;
    private NestedScrollView mScrollView;

    private Intent mIntent;
    private SharedUtils mSharedUtils;
    private Contact_Detail_LV_Adapter mAdapter;
    private List<ContactSupDemBean.DataBean> mBeanList;

    public int type;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        type = type == 0 ? 2 : 1;
        mIntent = getActivity().getIntent();
        mSharedUtils = SharedUtils.getSharedUtils();

        mView = View.inflate(getActivity(), R.layout.fragment_layout_supdem_detail_chj, null);
        mMyListview = (ListView) mView.findViewById(R.id.fragment_supdem_detail_lv);
        mScrollView = (NestedScrollView) mView.findViewById(R.id.scrollview);

        getTaPur(type, type);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return mView;
    }

    public void getTaPur(int type, int _type) {
        Map<String, String> map = new HashMap<>();
        map.put("page", "1");
        map.put("size", "15");
        map.put("type", type + "");
        map.put("user_id", mIntent.getStringExtra("userid"));
        String url = API.BASEURL + API.GET_TA_PUR;
        BaseActivity.postAsyn1(getActivity(), url, map, this, _type, false);
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

            if (err.equals("0")) {
                setListener(false);
                ContactSupDemBean supDemBean = gson.fromJson(object.toString(), ContactSupDemBean.class);
                mBeanList = supDemBean.getData();
                mAdapter = new Contact_Detail_LV_Adapter(getActivity(), mBeanList);
                mMyListview.setAdapter(mAdapter);
            } else {
                setListener(true);
                EmptyView emptyView = new EmptyView(getActivity());
                emptyView.mustCallInitWay(mMyListview);
                emptyView.setNoMessageText(new JSONObject(object.toString()).getString("msg"));
                emptyView.setMyManager(type == 2
                        ? R.drawable.icon_intelligent_recommendation1
                        : R.drawable.icon_intelligent_recommendation2);
                mMyListview.setEmptyView(emptyView);
            }

        } catch (Exception e) {
        }
    }

    @Override
    public void failCallBack(int type) {

    }
}
