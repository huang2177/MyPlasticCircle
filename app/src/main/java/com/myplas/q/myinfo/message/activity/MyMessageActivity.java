package com.myplas.q.myinfo.message.activity;

import android.os.Bundle;
import android.widget.AbsListView;

import com.google.gson.Gson;
import com.myplas.q.R;
import com.myplas.q.common.utils.TextUtils;
import com.myplas.q.guide.activity.BaseActivity;
import com.myplas.q.common.netresquset.ResultCallBack;
import com.myplas.q.common.utils.SharedUtils;
import com.myplas.q.common.view.XListView;
import com.myplas.q.myinfo.message.adapter.MyMessageAdapter;
import com.myplas.q.common.api.API;
import com.myplas.q.myinfo.beans.MyMessageBean;
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
 * 时间：2017/3/29 14:35
 */
public class MyMessageActivity extends BaseActivity implements ResultCallBack, XListView.IXListViewListener {
    private XListView listView;
    private MyMessageAdapter myMessageAdapter;
    private List<MyMessageBean.DataBean> list;
    private List<MyMessageBean.DataBean> list1;
    private SharedUtils sharedUtils;
    private int page = 1, visibleItemCount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mymessage_activity_layout);
        goBack(findViewById(R.id.back));
        sharedUtils = SharedUtils.getSharedUtils();
        listView = (XListView) findViewById(R.id.mymessage_listview);
        list1 = new ArrayList<>();
        listView.setPullLoadEnable(true);
        listView.setPullRefreshEnable(false);
        listView.setXListViewListener(this);
        getNetData("1");
        listView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                if (scrollState == AbsListView.OnScrollListener.SCROLL_STATE_IDLE && listView.getCount() > visibleItemCount) {
                    if (view.getLastVisiblePosition() == view.getCount() - 1) {
                        page++;
                        getNetData(String.valueOf(page));
                    }
                }
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                MyMessageActivity.this.visibleItemCount = visibleItemCount;
            }
        });
    }

    public void getNetData(String page) {
        Map<String, String> map = new HashMap<String, String>();
        map.put("token", sharedUtils.getData(this, "token"));
        map.put("page", page);
        map.put("size", "10");
        postAsyn(this, API.BASEURL + API.GET_ROBOT_MSG, map, this, 1);
    }

    @Override
    public void callBack(Object object, int type) {
        try {
            if (new JSONObject(object.toString()).getString("err").equals("0")) {
                Gson gson = new Gson();
                MyMessageBean myMessageBean = gson.fromJson(object.toString(), MyMessageBean.class);
                list = myMessageBean.getData();
                if (page == 1) {
                    myMessageAdapter = new MyMessageAdapter(this, list);
                    listView.setAdapter(myMessageAdapter);
                    listView.stopRefresh();
                    list1.clear();
                    list1.addAll(list);
                } else {
                    if (list != null && list.size() != 0) {
                        listView.stopLoadMore();
                        list1.addAll(list);
                        myMessageAdapter.setList(list1);
                        myMessageAdapter.notifyDataSetChanged();
                    }
                }
            } else {
                list = null;
                TextUtils.Toast(this, new JSONObject(object.toString()).getString("msg"));
            }
        } catch (Exception e) {
        }
    }

    @Override
    public void failCallBack(int type) {

    }

    @Override
    public void onRefresh() {
        page = 1;
        getNetData(String.valueOf(page));
    }

    @Override
    public void onLoadMore() {
        page++;
        getNetData(String.valueOf(page));
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
