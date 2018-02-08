package com.myplas.q.myself.fans.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListView;

import com.google.gson.Gson;
import com.myplas.q.R;
import com.myplas.q.common.api.API;
import com.myplas.q.common.net.ResultCallBack;
import com.myplas.q.common.utils.ContactAccessUtils;
import com.myplas.q.common.utils.SharedUtils;
import com.myplas.q.common.utils.TextUtils;
import com.myplas.q.common.view.EmptyView;
import com.myplas.q.homepage.beans.MyFansBean;
import com.myplas.q.app.activity.BaseActivity;
import com.myplas.q.myself.fans.adapter.MyFansFollowAdapter;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 编写： 黄双
 * 电话：15378412400
 * 邮箱：15378412400@163.com
 * 时间：2017/3/20 22:15
 */
public class MyFansFollowActivity extends BaseActivity implements ResultCallBack {
    private ListView listView;
    private MyFansFollowAdapter mFansAdapter;

    private String type = "1";
    private SharedUtils sharedUtils;
    private int page = 1, visibleItemCount;

    private ContactAccessUtils utils;
    private List<MyFansBean.DataBean> mList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_layout_myself_fansfollow);
        initTileBar();

        type = getIntent().getStringExtra("type");
        setTitle("1".equals(type) ? "我的粉丝" : "我的关注");
        sharedUtils = SharedUtils.getSharedUtils();
        listView = F(R.id.wdgz_listview);

        mList = new ArrayList<>();
        utils = new ContactAccessUtils(this);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                utils.checkPremissions(mList.get(position).getUser_id()
                        , mList.get(position).getIsshop());
            }
        });
        listView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                if (scrollState == AbsListView.OnScrollListener.SCROLL_STATE_IDLE
                        && listView.getCount() > visibleItemCount) {
                    if (view.getLastVisiblePosition() == view.getCount() - 1) {
                        page++;
                        getMyFans(String.valueOf(page), false);
                    }
                }
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                MyFansFollowActivity.this.visibleItemCount = visibleItemCount;
            }
        });

        //关注、粉丝
        getMyFans("1", true);
    }

    public void getMyFans(String page, boolean isShow) {
        Map<String, String> map = new HashMap<String, String>();
        map.put("page", page);
        map.put("size", "10");
        map.put("type", type);
        getAsyn(this, API.GET_MY_FUNS, map, this, 1, isShow);
    }


    @Override
    public void callBack(Object object, int type) {
        try {
            Gson gson = new Gson();
            String err = new JSONObject(object.toString()).getString("code");
            if (type == 1) {
                MyFansBean wdgzBean = null;
                if ("0".equals(err)) {
                    wdgzBean = gson.fromJson(object.toString(), MyFansBean.class);
                    if (page == 1) {
                        mFansAdapter = new MyFansFollowAdapter(this, wdgzBean.getData());
                        listView.setAdapter(mFansAdapter);
                        mList.clear();
                        mList.addAll(wdgzBean.getData());
                    } else {
                        mList.addAll(wdgzBean.getData());
                        mFansAdapter.setList(mList);
                        mFansAdapter.notifyDataSetChanged();
                    }
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
                    EmptyView emptyView = new EmptyView(this);
                    emptyView.mustCallInitWay(listView);
                    emptyView.setNoMessageText(jsonObject.getString("message"));
                    emptyView.setMyManager(R.drawable.icon_null);
                    listView.setEmptyView(emptyView);
                } else {
                    TextUtils.toast(this, "没有更多数据！");
                }
            }
        } catch (Exception e) {

        }
    }

}
