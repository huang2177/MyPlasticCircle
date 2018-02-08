package com.myplas.q.myself.integral.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ListView;

import com.google.gson.Gson;
import com.myplas.q.R;
import com.myplas.q.common.api.API;
import com.myplas.q.common.net.ResultCallBack;
import com.myplas.q.common.utils.SharedUtils;
import com.myplas.q.common.utils.TextUtils;
import com.myplas.q.app.activity.BaseActivity;
import com.myplas.q.common.view.EmptyView;
import com.myplas.q.myself.beans.RecordBean;
import com.myplas.q.myself.integral.adapter.Integral_Record_Adapter;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 编写： 黄双
 * 电话：15378412400
 * 邮箱：15378412400@163.com
 * 时间：2017/3/29 09:57
 */
public class IntegralRecordActivtity extends BaseActivity implements ResultCallBack {
    boolean hasMoerData = true;
    private ListView listView;
    private EmptyView emptyView;
    private SharedUtils sharedUtils;
    private int page = 1, visibleItemCount;
    private List<RecordBean.DataBean> list;
    private Integral_Record_Adapter record_adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ingeral_record_activity_layout);
        initTileBar();
        setTitle("兑换记录");

        list = new ArrayList<>();
        sharedUtils = SharedUtils.getSharedUtils();
        listView = F(R.id.integral_record_listview);
        emptyView = F(R.id.integral_record_empty);

        //加载更多
        listView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                if (scrollState == AbsListView.OnScrollListener.SCROLL_STATE_IDLE && listView.getCount() > visibleItemCount) {
                    if (view.getLastVisiblePosition() == view.getCount() - 1) {
                        page++;
                        if (hasMoerData) {
                            getBuyRecord(page + "");
                        } else {
                            TextUtils.toast(IntegralRecordActivtity.this, "没有更多数据了！");
                        }
                    }
                }
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                IntegralRecordActivtity.this.visibleItemCount = visibleItemCount;
            }
        });
        getBuyRecord("1");
    }

    public void getBuyRecord(String page) {
        Map<String, String> map = new HashMap<String, String>();
        map.put("token", sharedUtils.getData(this, "token"));
        map.put("page", page);
        map.put("size", "10");
        getAsyn(this, API.GET_PURCHASE_RECORD, map, this, 1);
    }

    @Override
    public void callBack(Object object, int type) {
        try {
            RecordBean recordBean = null;
            if ("0".equals(new JSONObject(object.toString()).getString("code"))) {
                Gson gson = new Gson();
                recordBean = gson.fromJson(object.toString(), RecordBean.class);
                if (page == 1) {
                    emptyView.setVisibility(View.GONE);
                    listView.setVisibility(View.VISIBLE);
                    record_adapter = new Integral_Record_Adapter(this, recordBean.getData());
                    listView.setAdapter(record_adapter);
                    list.addAll(recordBean.getData());
                } else {
                    list.addAll(recordBean.getData());
                    record_adapter.setList(list);
                    record_adapter.notifyDataSetChanged();
                }
            }
        } catch (Exception e) {
        }
    }

    @Override
    public void failCallBack(int type, String message, int httpCode) {
        try {
            JSONObject jsonObject = new JSONObject(message);
            if (httpCode == 404) {
                if (page == 1) {
                    listView.setVisibility(View.GONE);
                    emptyView.setVisibility(View.VISIBLE);
                    emptyView.setNoMessageText(jsonObject.getString("message"));
                    emptyView.setMyManager(R.drawable.icon_intelligent_recommendation2);
                } else {
                    TextUtils.toast(this, "没有更多数据！");
                }
            }
        } catch (Exception e) {

        }
    }
}
