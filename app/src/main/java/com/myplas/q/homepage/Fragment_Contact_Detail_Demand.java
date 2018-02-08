package com.myplas.q.homepage;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.myplas.q.R;
import com.myplas.q.common.net.ResultCallBack;
import com.myplas.q.common.utils.SharedUtils;
import com.myplas.q.common.view.EmptyView;
import com.myplas.q.common.view.MyNestedScrollView;
import com.myplas.q.homepage.adapter.Contact_Detail_LV_Adapter;
import com.myplas.q.homepage.beans.ContactInfoBean;
import com.myplas.q.supdem.activity.SupDem_Detail_Activity;

import java.util.List;

/**
 * 事件 2017/10/9 0009.
 * 邮箱： 15378412400@163.com
 *
 * @author huangshuang
 */

public class Fragment_Contact_Detail_Demand extends Fragment implements ResultCallBack {

    private View mView;
    private ListView mMyListview;
    private MyNestedScrollView mScrollView;

    private Intent mIntent;
    private SharedUtils mSharedUtils;
    private Contact_Detail_LV_Adapter mAdapter;

    public int page;
    private List<ContactInfoBean.DataBean.DemandBean> demandList;

    public static Fragment_Contact_Detail_Demand newInstance() {
        Fragment_Contact_Detail_Demand fragment = new Fragment_Contact_Detail_Demand();
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        page = 1;
        mIntent = getActivity().getIntent();
        mSharedUtils = SharedUtils.getSharedUtils();

        mView = View.inflate(getActivity(), R.layout.fragment_layout_supdem_detail_chj, null);
        mMyListview = (ListView) mView.findViewById(R.id.fragment_supdem_detail_lv);
        mScrollView = (MyNestedScrollView) mView.findViewById(R.id.scrollview);


        mMyListview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                goToDetail(position);
            }
        });

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return mView;
    }


    /**
     * 跳转至详情
     */

    private void goToDetail(int position) {
        Intent intent = new Intent(getActivity(), SupDem_Detail_Activity.class);
        intent.putExtra("id", demandList.get(position).getId());
        intent.putExtra("userid", demandList.get(position).getUser_id());
        startActivity(intent);
    }

    public void showDemand(List<ContactInfoBean.DataBean.DemandBean> demandList) {
        if (demandList.size() != 0) {
            setListener(false);
            this.demandList = demandList;
            mAdapter = new Contact_Detail_LV_Adapter(getActivity(), null, demandList);
            mMyListview.setAdapter(mAdapter);
        } else {
            setListener(true);
            EmptyView emptyView = new EmptyView(getActivity());
            emptyView.mustCallInitWay(mMyListview);
            emptyView.setNoMessageText("暂无求购信息~");
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
//                    TextUtils.toast(getActivity(), "没有更多数据了！");
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
    public void failCallBack(int type, String message, int httpCode) {

    }
}
