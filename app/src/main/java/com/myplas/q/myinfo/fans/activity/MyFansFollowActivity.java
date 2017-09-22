package com.myplas.q.myinfo.fans.activity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.myplas.q.R;
import com.myplas.q.addresslist.Beans.MyFansBean;
import com.myplas.q.addresslist.adapter.MyFansFollowAdapter;
import com.myplas.q.common.api.API;
import com.myplas.q.common.netresquset.ResultCallBack;
import com.myplas.q.common.view.CommonDialog;
import com.myplas.q.common.utils.SharedUtils;
import com.myplas.q.common.utils.TextUtils;
import com.myplas.q.common.view.EmptyView;
import com.myplas.q.guide.activity.BaseActivity;
import com.myplas.q.myinfo.beans.MyFollowBean;
import com.myplas.q.myinfo.fans.adapter.MyFollowAdapter;
import com.myplas.q.myinfo.integral.activity.IntegralPayActivtity;
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
 * 时间：2017/3/20 22:15
 */
public class MyFansFollowActivity extends BaseActivity implements ResultCallBack, CommonDialog.DialogShowInterface {
    private ListView listView;
    private Dialog normalDialog;
    private TextView textView_title;
    private MyFollowAdapter myFollowAdapter;
    private MyFansFollowAdapter wdgz_adapter;

    private SharedUtils sharedUtils;
    private int page = 1, visibleItemCount;
    private String type = "1", user_id, id_;

    private List<MyFansBean.DataBean> mListFans;
    private List<MyFollowBean.DataBean> mListFloow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_layout_myself_fansfollow);
        initTileBar();
        setTitle(getIntent().getStringExtra("titlename"));

        type = getIntent().getStringExtra("type");
        sharedUtils = SharedUtils.getSharedUtils();
        listView = F(R.id.wdgz_listview);

        mListFans = new ArrayList<>();
        mListFloow = new ArrayList<>();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                try {
                    if (type.equals("1")) {
                        user_id = mListFans.get(position).getUser_id().getUser_id();
                        id_ = mListFans.get(position).getFocused_id();
                    }
                    if (type.equals("2")) {
                        user_id = mListFloow.get(position).getFocused_id().getUser_id();
                        id_ = mListFloow.get(position).getFocused_id().getUser_id();
                    }
                    //判断是否消耗积分
                    getPersonInfoData(user_id, "1", 5);
                } catch (Exception e) {
                }
            }
        });
        listView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                if (scrollState == AbsListView.OnScrollListener.SCROLL_STATE_IDLE && listView.getCount() >= visibleItemCount) {
                    if (view.getLastVisiblePosition() == view.getCount() - 1) {
                        page++;
                        getMyFans(String.valueOf(page));
                    }
                }
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                MyFansFollowActivity.this.visibleItemCount = visibleItemCount;
            }
        });

        //关注、粉丝
        if (TextUtils.isNullOrEmpty(type)) {
            getMyFans("1");
        }
    }

    public void getMyFans(String page) {
        try {
            Map<String, String> map = new HashMap<String, String>();
            map.put("token", sharedUtils.getData(this, "token"));
            map.put("page", page);
            map.put("type", type);
            map.put("size", "10");
            postAsyn(this, API.BASEURL + API.GET_MY_FUNS, map, this, Integer.parseInt(type));
        } catch (Exception e) {
        }
    }

    public void getPersonInfoData(String userid, String showtype, int type) {
        Map<String, String> map = new HashMap<String, String>();
        map.put("token", sharedUtils.getData(this, "token"));
        map.put("user_id", userid);
        map.put("showType", showtype);
        String url = API.BASEURL + API.GET_ZONE_FRIEND;
        postAsyn(this, url, map, this, 5);
    }

    @Override
    public void callBack(Object object, int type) {
        try {
            Gson gson = new Gson();
            String err = new JSONObject(object.toString()).getString("err");
            if (type == 1) {
                MyFansBean wdgzBean = null;
                if (err.equals("0")) {
                    wdgzBean = gson.fromJson(object.toString(), MyFansBean.class);
                    if (page == 1) {
                        wdgz_adapter = new MyFansFollowAdapter(this, wdgzBean.getData());
                        listView.setAdapter(wdgz_adapter);
                        mListFans.clear();
                        mListFans.addAll(wdgzBean.getData());
                    } else {
                        mListFans.addAll(wdgzBean.getData());
                        wdgz_adapter.setList(mListFans);
                        wdgz_adapter.notifyDataSetChanged();
                    }
                } else {
                    if (page == 1) {
                        EmptyView emptyView = new EmptyView(this);
                        emptyView.mustCallInitWay(listView);
                        emptyView.setNoMessageText(new JSONObject(object.toString()).getString("msg"));
                        emptyView.setMyManager(R.drawable.icon_null);
                        listView.setEmptyView(emptyView);
                    }
                }
            } else if (type == 2) {
                MyFollowBean wdgzBean = null;
                if (err.equals("0")) {
                    wdgzBean = gson.fromJson(object.toString(), MyFollowBean.class);
                    if (page == 1) {
                        myFollowAdapter = new MyFollowAdapter(this, wdgzBean.getData());
                        listView.setAdapter(myFollowAdapter);
                        mListFloow.clear();
                        mListFloow.addAll(wdgzBean.getData());
                    } else {
                        mListFloow.addAll(wdgzBean.getData());
                        myFollowAdapter.setList(mListFloow);
                        myFollowAdapter.notifyDataSetChanged();
                    }
                } else {
                    if (page == 1) {
                        EmptyView emptyView = new EmptyView(this);
                        emptyView.mustCallInitWay(listView);
                        emptyView.setNoMessageText(new JSONObject(object.toString()).getString("msg"));
                        emptyView.setMyManager(R.drawable.icon_null);
                        listView.setEmptyView(emptyView);
                    }
                }
            }
            //是否消耗积分//
            if (type == 5 && err.equals("99")) {
                String content = new JSONObject(object.toString()).getString("msg");
                CommonDialog commonDialog = new CommonDialog();
                commonDialog.showDialog(this, content, 1, this);
            }
            //已经消耗积分
            if (type == 5 && err.equals("0")) {
                Intent intent = new Intent(this, PersonInfoActivity.class);
                intent.putExtra("userid", user_id);
                intent.putExtra("id", user_id);
                startActivity(intent);
            }
            //减积分成功
            if (type == 3 && err.equals("0")) {
                Intent intent = new Intent(this, PersonInfoActivity.class);
                intent.putExtra("id", id_);
                intent.putExtra("userid", user_id);
                startActivity(intent);
            }
            //积分不足
            if (type == 3 && !err.equals("0")) {
                String content = new JSONObject(object.toString()).getString("msg");
                CommonDialog commonDialog = new CommonDialog();
                commonDialog.showDialog(this, content, (err.equals("100")) ? (2) : (3), this);
            }
        } catch (Exception e) {
        }
    }

    @Override
    public void failCallBack(int type) {

    }

    @Override
    public void ok(int type) {
        switch (type) {
            case 1:
                getPersonInfoData(user_id, "5", 3);
                break;
            case 2:
                startActivity(new Intent(this, IntegralPayActivtity.class));
                break;
        }
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
