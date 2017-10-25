package com.myplas.q.contact.activity;

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
import com.myplas.q.contact.adapter.TheirFansFollowAdapter;
import com.myplas.q.contact.beans.MyFansBean;
import com.myplas.q.contact.beans.TheirFansBean;
import com.myplas.q.guide.activity.BaseActivity;
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
public class TheirFansFollowActivity extends BaseActivity implements ResultCallBack
        , CommonDialog.DialogShowInterface {

    private ListView listView;
    private TextView textView_title;
    private TheirFansFollowAdapter mFansAdapter;

    private SharedUtils sharedUtils;
    private String user_id, userid, function;
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
        sharedUtils = SharedUtils.getSharedUtils();
        user_id = getIntent().getStringExtra("userid");
        function = getIntent().getStringExtra("function");

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                userid = mList.get(position).getUser_id();
                //判断是否消耗积分
                getPersonInfoData(user_id, "1", 5);

            }
        });
        listView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                if (scrollState == AbsListView.OnScrollListener.SCROLL_STATE_IDLE && listView.getCount() > visibleItemCount) {
                    if (view.getLastVisiblePosition() == view.getCount() - 1) {
                        page++;
                        getMyFans(String.valueOf(page), false);
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
        Map<String, String> map = new HashMap<String, String>();
        map.put("user_id", user_id);
        postAsyn1(this, API.BASEURL + function, map, this, 1, isShow);
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
                TheirFansBean fansBean = null;
                if (err.equals("0")) {
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
                } else {
                    if (page == 1) {
                        EmptyView emptyView = new EmptyView(this);
                        emptyView.mustCallInitWay(listView);
                        emptyView.setNoMessageText(new JSONObject(object.toString()).getString("msg"));
                        emptyView.setMyManager(R.drawable.icon_null);
                        listView.setEmptyView(emptyView);
                    } else {
                        TextUtils.Toast(this, "没有更多数据了！");
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
                Intent intent = new Intent(this, Contact_Detail_Their_Activity.class);
                intent.putExtra("userid", userid);
                startActivity(intent);
            }
            //减积分成功
            if (type == 3 && err.equals("0")) {
                Intent intent = new Intent(this, Contact_Detail_Their_Activity.class);
                intent.putExtra("userid", userid);
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
            default:
                break;
        }
    }
}
