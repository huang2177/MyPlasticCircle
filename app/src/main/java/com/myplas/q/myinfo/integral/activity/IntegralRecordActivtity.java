package com.myplas.q.myinfo.integral.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AbsListView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.myplas.q.R;
import com.myplas.q.common.utils.TextUtils;
import com.myplas.q.myinfo.integral.adapter.Integral_Record_Adapter;
import com.myplas.q.myinfo.beans.RecordBean;
import com.myplas.q.guide.activity.BaseActivity;
import com.myplas.q.common.api.API;
import com.myplas.q.common.netresquset.ResultCallBack;
import com.myplas.q.common.utils.SharedUtils;
import com.umeng.analytics.MobclickAgent;

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
    private SharedUtils sharedUtils;
    private int page = 1, visibleItemCount;
    private List<RecordBean.DataBean> list;
    private Integral_Record_Adapter record_adapter;

    private TextView textView;
    private LinearLayout layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ingeral_record_activity_layout);
        goBack(findViewById(R.id.back));

        list = new ArrayList<>();
        sharedUtils = SharedUtils.getSharedUtils();
        textView = F(R.id.integral_text);
        layout = F(R.id.integral_prompt);
        listView = F(R.id.integral_record_listview);

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
                            TextUtils.Toast(IntegralRecordActivtity.this, "没有更多数据了！");
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
        postAsyn(this, API.BASEURL + API.GET_PURCHASE_RECORD, map, this, 1);
    }

    @Override
    public void callBack(Object object, int type) {
        try {
            RecordBean recordBean = null;
            if (new JSONObject(object.toString()).getString("err").equals("0")) {
                Gson gson = new Gson();
                layout.setVisibility(View.GONE);
                listView.setVisibility(View.VISIBLE);
                recordBean = gson.fromJson(object.toString(), RecordBean.class);
                if (page == 1) {
                    record_adapter = new Integral_Record_Adapter(this, recordBean.getData());
                    listView.setAdapter(record_adapter);
                    list.addAll(recordBean.getData());
                } else {
                    list.addAll(recordBean.getData());
                    record_adapter.setList(list);
                    record_adapter.notifyDataSetChanged();
                }
            } else {
                hasMoerData = false;
                listView.setVisibility(View.GONE);
                layout.setVisibility(View.VISIBLE);
                textView.setText(new JSONObject(object.toString()).getString("msg"));
            }
        } catch (Exception e) {
        }
    }

    @Override
    public void failCallBack(int type) {

    }

    public void onResume() {
        super.onResume();
        MobclickAgent.onResume(this);
    }

    public void onPause() {
        super.onPause();
        MobclickAgent.onPause(this);
    }
}
