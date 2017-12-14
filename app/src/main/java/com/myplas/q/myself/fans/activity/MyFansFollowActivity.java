package com.myplas.q.myself.fans.activity;

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
import com.myplas.q.common.api.API;
import com.myplas.q.common.netresquset.ResultCallBack;
import com.myplas.q.common.utils.SharedUtils;
import com.myplas.q.common.utils.TextUtils;
import com.myplas.q.common.view.CommonDialog;
import com.myplas.q.common.view.EmptyView;
import com.myplas.q.contact.activity.ContactDetailActivity;
import com.myplas.q.contact.activity.NewContactDetailActivity;
import com.myplas.q.contact.beans.MyFansBean;
import com.myplas.q.app.activity.BaseActivity;
import com.myplas.q.myself.fans.adapter.MyFansFollowAdapter;
import com.myplas.q.myself.integral.activity.IntegralPayActivtity;

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
    private MyFansFollowAdapter mFansAdapter;

    private SharedUtils sharedUtils;
    private int page = 1, visibleItemCount;
    private String type = "1", user_id, id_, flag;

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

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                user_id = mList.get(position).getUser_id();
                flag = mList.get(position).getMerge_three();
                //判断是否消耗积分
                getPersonInfoData(user_id, "1", 5);
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
        map.put("token", sharedUtils.getData(this, "token"));
        map.put("page", page);
        map.put("type", type);
        map.put("size", "10");
        postAsyn1(this, API.BASEURL + API.GET_MY_FUNS, map, this, 1, isShow);
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
                        mFansAdapter = new MyFansFollowAdapter(this, wdgzBean.getData());
                        listView.setAdapter(mFansAdapter);
                        mList.clear();
                        mList.addAll(wdgzBean.getData());
                    } else {
                        mList.addAll(wdgzBean.getData());
                        mFansAdapter.setList(mList);
                        mFansAdapter.notifyDataSetChanged();
                    }
                } else {
                    if (page == 1) {
                        EmptyView emptyView = new EmptyView(this);
                        emptyView.mustCallInitWay(listView);
                        emptyView.setNoMessageText(new JSONObject(object.toString()).getString("msg"));
                        emptyView.setMyManager(R.drawable.icon_null);
                        listView.setEmptyView(emptyView);
                    } else {
                        TextUtils.toast(this, "没有更多数据了！");
                    }
                }
            }
            //是否消耗积分//
            if (type == 5 && err.equals("99")) {
                String content = new JSONObject(object.toString()).getString("msg");
                CommonDialog commonDialog = new CommonDialog();
                commonDialog.showDialog(this, content, 1, this);
            }
            //已经消耗积分或者减积分成功
            boolean b = type == 5 || type == 3;
            if (b && err.equals("0")) {
                Intent intent = getJumpIntent();
                intent.putExtra("userid", user_id);
                intent.putExtra("id", user_id);
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

    /**
     * 判断是否跳转到店铺
     *
     * @param flag
     * @return
     */
    public Intent getJumpIntent() {
        Intent intent = new Intent();
        intent.setClass(this, "1".equals(flag)
                ? NewContactDetailActivity.class
                : ContactDetailActivity.class);
        return intent;
    }

    @Override
    public void failCallBack(int type) {

    }

    @Override
    public void dialogClick(int type) {
        switch (type) {
            case 1:
                getPersonInfoData(user_id, "5", 3);
                break;
            case 2:
                startActivity(new Intent(this, IntegralPayActivtity.class));
                break;
            default:
                break;
        }
    }
}
