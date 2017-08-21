package com.myplas.q.myinfo.message.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;

import com.google.gson.Gson;
import com.myplas.q.R;
import com.myplas.q.common.utils.TextUtils;
import com.myplas.q.guide.activity.BaseActivity;
import com.myplas.q.common.netresquset.ResultCallBack;
import com.myplas.q.common.utils.SharedUtils;
import com.myplas.q.common.view.XListView;
import com.myplas.q.myinfo.message.adapter.MessageCommonAdapter;
import com.myplas.q.common.api.API;
import com.myplas.q.myinfo.beans.MyCommentBean;
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
public class MessageCommonActivity extends BaseActivity implements ResultCallBack, XListView.IXListViewListener {
    private String title;
    private SharedUtils sharedUtils;
    private int page = 1, visibleItemCount;
    private List<MyCommentBean.DataBean> list;
    private List<MyCommentBean.DataBean> list1;

    private RecyclerView mRecyclerView;
    private MessageCommonAdapter myCommentAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_layout_myself_msg);
        initTileBar();
        title = getIntent().getStringExtra("title");
        setTitle(title);

        list1 = new ArrayList<>();
        mRecyclerView = F(R.id.wd_gj_listview);
        sharedUtils = SharedUtils.getSharedUtils();


        myCommentAdapter = new MessageCommonAdapter(this, list);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);//设置为一个1列的纵向网格布局
        mRecyclerView.setLayoutManager(mLayoutManager);

        mRecyclerView.setAdapter(myCommentAdapter);


//        getMyComments("1");
//        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
//            @Override
//            public void onScrollStateChanged(RecyclerView view, int newState) {
//                if (newState == AbsListView.OnScrollListener.SCROLL_STATE_IDLE && mRecyclerView.getChildCount() > visibleItemCount) {
//                    if (view.getLastVisiblePosition() == view.getCount() - 1) {
//                        page++;
//                        getMyComments(String.valueOf(page));
//                    }
//                }
//            }
//
//            @Override
//            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
//                MessageCommonActivity.this.visibleItemCount = visibleItemCount;
//            }
//        });
    }


    public void getMyComments(String page) {
        Map<String, String> map = new HashMap<String, String>();
        map.put("token", sharedUtils.getData(this, "token"));
        map.put("page", page);
        map.put("size", "20");
        postAsyn(this, API.BASEURL + API.GET_MY_COMMENT, map, this, 1);
    }

    @Override
    public void callBack(Object object, int type) {
        try {
            if (new JSONObject(object.toString()).getString("err").equals("0")) {
                Gson gson = new Gson();
                MyCommentBean myCommentBean = gson.fromJson(object.toString(), MyCommentBean.class);
                list = myCommentBean.getData();
                if (page == 1) {
                    myCommentAdapter = new MessageCommonAdapter(this, list);
                    mRecyclerView.setAdapter(myCommentAdapter);

                    list1.clear();
                    list1.addAll(list);
                } else {
                    if (list != null && list.size() != 0) {

                        list1.addAll(list);
                        myCommentAdapter.setList(list1);
                        myCommentAdapter.notifyDataSetChanged();
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
        getMyComments(String.valueOf(page));
    }

    @Override
    public void onLoadMore() {
//        page++;
//        getMyComments(String.valueOf(page));
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
