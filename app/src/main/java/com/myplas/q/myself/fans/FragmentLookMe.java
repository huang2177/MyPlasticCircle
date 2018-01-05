package com.myplas.q.myself.fans;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;

import com.google.gson.Gson;
import com.myplas.q.R;
import com.myplas.q.app.fragment.BaseFragment;
import com.myplas.q.common.api.API;
import com.myplas.q.common.netresquset.ResultCallBack;
import com.myplas.q.common.utils.TextUtils;
import com.myplas.q.common.view.EmptyView;
import com.myplas.q.common.view.pinnedheadlistview.PinnedHeaderListView;
import com.myplas.q.myself.beans.LookMeBean;
import com.myplas.q.myself.fans.adapter.LookMeAdapter;
import com.myplas.q.sockethelper.RabbitMQConfig;

import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 黄双
 * @date 2018/1/4 0004
 */

public class FragmentLookMe extends BaseFragment implements ResultCallBack, LookMeAdapter.OnItemClickListener {
    private View view;
    private LookMeAdapter adapter;
    private PinnedHeaderListView listView;

    private int page;
    private String position;
    private int visibleItemCount;
    private List<LookMeBean.DataBean.HistoryBean> mList;

    private InfoCallBackListener listener;


    public static FragmentLookMe newInstance(int position, InfoCallBackListener listener) {
        FragmentLookMe fragment = new FragmentLookMe();
        Bundle bundle = new Bundle();
        bundle.putString("position", position + "");
        bundle.putSerializable("listener", listener);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        view = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_layout_lookme, null, false);
        listView = (PinnedHeaderListView) view.findViewById(R.id.look_listview);

        page = 1;
        mList = new ArrayList<>();
        position = getArguments().getString("position");
        listener = (InfoCallBackListener) getArguments().getSerializable("listener");
        getViewHistoryDetails("1", position);

        listView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                if (scrollState == AbsListView.OnScrollListener.SCROLL_STATE_IDLE &&
                        listView.getCount() >= visibleItemCount) {
                    if (view.getLastVisiblePosition() == view.getCount() - 1) {
                        page++;
                        getViewHistoryDetails(String.valueOf(page), position);
                    }
                }
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                FragmentLookMe.this.visibleItemCount = visibleItemCount;
            }
        });
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return view;
    }

    public void getViewHistoryDetails(String page, String position) {
        Map<String, String> map = new HashMap<String, String>(16);
        map.put("page", page);
        map.put("size", "15");
        map.put("mode", position);
        getAsyn(getActivity(), API.GET_VIEW_HISTORY_DETAILS, map, this, 1, false);
    }

    @Override
    public void callBack(Object object, int type) {
        try {
            Gson gson = new Gson();
            LookMeBean lookMeBean = null;
            String err = new JSONObject(object.toString()).getString("code");
            if ("0".equals(err)) {
                lookMeBean = gson.fromJson(object.toString(), LookMeBean.class);
                if (page == 1) {
                    adapter = new LookMeAdapter(getActivity(), lookMeBean.getData().getHistory());
                    adapter.setOnItemClickListener(this);
                    listView.setAdapter(adapter);
                    mList.clear();
                    mList.addAll(lookMeBean.getData().getHistory());
                    if (listener != null) {
                        listener.onResult(position, lookMeBean.getData().getToday(), lookMeBean.getData().getTotals());
                    }
                } else {
                    mList.addAll(lookMeBean.getData().getHistory());
                    adapter.setList(mList);
                    adapter.notifyDataSetChanged();
                }
                RabbitMQConfig.getInstance(getActivity()).readMsg("unread_who_saw_me", 14);
            }
        } catch (Exception e) {
        }
    }

    @Override
    public void failCallBack(int type, String message, int httpCode) {
        try {
            Gson gson = new Gson();
            JSONObject jsonObject = new JSONObject(message);
            if ("2".equals(0)) {
                String msg = jsonObject.getString("message");
                if (page == 1) {
                    EmptyView emptyView = new EmptyView(getActivity());
                    emptyView.mustCallInitWay(listView);
                    emptyView.setNoMessageText(msg);
                    emptyView.setMyManager(R.drawable.icon_null);
                    listView.setEmptyView(emptyView);

                    if (listener != null) {
                        listener.onResult(position, jsonObject.getString(""), jsonObject.getString(""));
                    }
                } else {
                    TextUtils.toast(getActivity(), msg);
                }
            }
        } catch (Exception e) {
        }
    }

    @Override
    public void onItemClick(int section, int position) {
        String userid = mList.get(section).getPerson().get(position).getUserid();
        String mergeThere = mList.get(section).getPerson().get(position).getMerge_three();
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public interface InfoCallBackListener extends Serializable {
        void onResult(String position, String today, String totals);
    }

    public void setListener(InfoCallBackListener listener) {
        this.listener = listener;
    }
}
