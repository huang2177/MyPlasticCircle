package com.myplas.q.homepage.activity;

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
import com.myplas.q.common.utils.TextUtils;
import com.myplas.q.common.view.EmptyView;
import com.myplas.q.homepage.adapter.TheirFansFollowAdapter;
import com.myplas.q.homepage.beans.TheirFansBean;
import com.myplas.q.app.activity.BaseActivity;

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
public class TheirFansFollowActivity extends BaseActivity implements ResultCallBack {

    private ListView listView;
    private TheirFansFollowAdapter mFansAdapter;

    private String userid, type;
    private ContactAccessUtils utils;
    private int page = 1, visibleItemCount;

    private List<TheirFansBean.DataBean> mList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_layout_myself_fansfollow);
        initTileBar();
        initView();
        getMyFans("1", true);
    }

    private void initView() {
        setTitle(getIntent().getStringExtra("title"));

        listView = F(R.id.wdgz_listview);

        mList = new ArrayList<>();
        utils = new ContactAccessUtils(this);
        type = getIntent().getStringExtra("type");
        userid = getIntent().getStringExtra("userid");

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
                if (scrollState == AbsListView.OnScrollListener.SCROLL_STATE_IDLE && listView.getCount() > visibleItemCount) {
                    if (view.getLastVisiblePosition() == view.getCount() - 1) {
                        page++;
                        getMyFans(page + "", false);
                    }
                }
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                TheirFansFollowActivity.this.visibleItemCount = visibleItemCount;
            }
        });
    }

    public void getMyFans(String page, boolean isShow) {
        Map<String, String> map = new HashMap<String, String>(16);
        map.put("user_id", userid);
        map.put("type", type);
        map.put("page", page);
        getAsyn(this, "3".equals(type)
                ? API.GET_MY_INTRODUCTION
                : API.GET_MY_FUNS, map, this, 1, isShow);
    }


    @Override
    public void callBack(Object object, int type) {
        try {
            Gson gson = new Gson();
            String err = new JSONObject(object.toString()).getString("code");

            TheirFansBean fansBean = null;
            if ("0".equals(err)) {
                fansBean = gson.fromJson(object.toString(), TheirFansBean.class);
                if (page == 1) {
                    mFansAdapter = new TheirFansFollowAdapter(this, fansBean.getData());
                    listView.setAdapter(mFansAdapter);
                    mList.clear();
                    mList.addAll(fansBean.getData());
                } else {
                    mList.addAll(fansBean.getData());
                    mFansAdapter.setList(mList);
                    mFansAdapter.notifyDataSetChanged();
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
                    TextUtils.toast(this, "没有更多数据了！");
                }
            }
        } catch (Exception e) {

        }
    }
}
