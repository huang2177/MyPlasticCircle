package com.myplas.q.myself.supdem;

import android.os.Bundle;
import android.widget.AbsListView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.myplas.q.R;
import com.myplas.q.common.utils.TextUtils;
import com.myplas.q.common.view.EmptyView;
import com.myplas.q.guide.activity.BaseActivity;
import com.myplas.q.common.netresquset.ResultCallBack;
import com.myplas.q.common.utils.SharedUtils;
import com.myplas.q.common.api.API;
import com.myplas.q.myself.beans.MySupDemBean;
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
 * 时间：2017/3/23 16:22
 */
public class MySupDemActivity extends BaseActivity implements ResultCallBack, SupDemAdapter.MyInterface {

    private String type;
    private SharedUtils sharedUtils;
    private int page = 1, visibleItemCount;

    private TextView textView;
    private ListView listView;
    private List<MySupDemBean.DataBean> list_more;

    private SupDemAdapter supplyDemandAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_layout_myself_supdem);
        initTileBar();
        setTitle(getIntent().getStringExtra("title"));

        list_more = new ArrayList<>();
        type = getIntent().getStringExtra("type");
        sharedUtils = SharedUtils.getSharedUtils();

        listView = F(R.id.wd_gj_listview);
        supplyDemandAdapter = new SupDemAdapter(this, null, type, this);
        listView.setAdapter(supplyDemandAdapter);

        listView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                if (scrollState == AbsListView.OnScrollListener.SCROLL_STATE_IDLE
                        && listView.getCount() > visibleItemCount) {
                    if (view.getLastVisiblePosition() == view.getCount() - 1) {
                        page++;
                        getSupplyDemandList(String.valueOf(page), false);
                    }
                }
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                MySupDemActivity.this.visibleItemCount = visibleItemCount;
            }
        });

        getSupplyDemandList("1", true);
    }

    public void getSupplyDemandList(String page, boolean isShow) {
        Map<String, String> map = new HashMap<String, String>();
        map.put("token", sharedUtils.getData(this, "token"));
        map.put("type", type);
        map.put("page", page);
        map.put("size", "10");
        postAsyn1(this, API.BASEURL + API.GET_MY_MSG, map, this, 1, isShow);
    }

    @Override
    public void callBack(Object object, int type) {
        try {
            Gson gson = new Gson();
            if (type == 1) {
                if (new JSONObject(object.toString()).getString("err").equals("0")) {
                    MySupDemBean supplyDemandBean = gson.fromJson(object.toString(), MySupDemBean.class);
                    if (page == 1) {
                        list_more.clear();
                        list_more.addAll(supplyDemandBean.getData());
                        supplyDemandAdapter.setList(list_more);
                        supplyDemandAdapter.notifyDataSetChanged();
                    } else {
                        list_more.addAll(supplyDemandBean.getData());
                        supplyDemandAdapter.setList(list_more);
                        supplyDemandAdapter.notifyDataSetChanged();
                    }
                } else {
                    if (page == 1) {
                        EmptyView emptyView = new EmptyView(this);
                        emptyView.mustCallInitWay(listView);
                        emptyView.setMyManager(R.drawable.icon_intelligent_recommendation2);
                        emptyView.setNoMessageText(new JSONObject(object.toString()).getString("msg"));
                        listView.setEmptyView(emptyView);
                    } else {
                        TextUtils.Toast(this, "没有更多数据了！");
                    }
                }
            }
        } catch (Exception e) {
        }
    }

    @Override
    public void failCallBack(int type) {

    }

    @Override
    public void reQuestNet() {
        page = 1;
        getSupplyDemandList(String.valueOf(page), false);
    }

//    //刷新
//    @Override
//    public void onRefresh() {
//        page = 1;
//        getSupplyDemandList(String.valueOf(page));
//    }

}
