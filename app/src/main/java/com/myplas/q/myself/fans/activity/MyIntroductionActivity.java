package com.myplas.q.myself.fans.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListView;

import com.google.gson.Gson;
import com.myplas.q.R;
import com.myplas.q.contact.activity.Contact_Detail_Activity;
import com.myplas.q.common.view.CommonDialog;
import com.myplas.q.common.view.EmptyView;
import com.myplas.q.guide.activity.BaseActivity;
import com.myplas.q.common.netresquset.ResultCallBack;
import com.myplas.q.common.utils.SharedUtils;
import com.myplas.q.myself.integral.activity.IntegralPayActivtity;
import com.myplas.q.myself.fans.adapter.MyIntroductionAdapter;
import com.myplas.q.common.api.API;
import com.myplas.q.myself.beans.MyIntroductionBean;
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
 * 时间：2017/3/23 17:46
 */
public class MyIntroductionActivity extends BaseActivity implements ResultCallBack, CommonDialog.DialogShowInterface {
    private String userid;
    private SharedUtils sharedUtils;
    private int page = 1, visibleItemCount;

    private ListView listView;
    private MyIntroductionAdapter wdyjAdapter;

    private List<MyIntroductionBean.DataBean> mList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_layout_myself_introdution);
        initTileBar();
        setTitle("我的引荐");

        mList = new ArrayList<>();
        listView = F(R.id.wd_yj_listview);
        sharedUtils = SharedUtils.getSharedUtils();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                userid = mList.get(position).getUser_id();
                getPersonInfoData(userid, "1", 5);
            }
        });
        listView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                if (scrollState == AbsListView.OnScrollListener.SCROLL_STATE_IDLE && listView.getCount() > visibleItemCount) {
                    if (view.getLastVisiblePosition() == view.getCount() - 1) {
                        page++;
                        getMyFans(String.valueOf(page));
                    }
                }
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                MyIntroductionActivity.this.visibleItemCount = visibleItemCount;
            }
        });
        getMyFans("1");
    }

    public void getMyFans(String page) {
        Map<String, String> map = new HashMap<String, String>();
        map.put("token", sharedUtils.getData(this, "token"));
        map.put("page", page);
        map.put("size", "10");
        postAsyn(this, API.BASEURL + API.GET_MY_INTRODUCTION, map, this, 1);
    }

    public void getPersonInfoData(String userid, String showtype, int type) {
        Map<String, String> map = new HashMap<String, String>();
        map.put("token", sharedUtils.getData(this, "token"));
        map.put("user_id", userid);
        map.put("showType", showtype);
        String url = API.BASEURL + API.GET_ZONE_FRIEND;
        postAsyn(this, url, map, this, type);
    }

    @Override
    public void callBack(Object object, int type) {
        try {
            Gson gson = new Gson();
            String err = new JSONObject(object.toString()).getString("err");
            if (type == 1) {
                if (err.equals("0")) {
                    MyIntroductionBean myIntroductionBean = gson.fromJson(object.toString(), MyIntroductionBean.class);
                    if (page == 1) {
                        wdyjAdapter = new MyIntroductionAdapter(this, myIntroductionBean.getData());
                        listView.setAdapter(wdyjAdapter);
                        mList.clear();
                        mList.addAll(myIntroductionBean.getData());
                    } else {
                        mList.addAll(myIntroductionBean.getData());
                        wdyjAdapter.setList(mList);
                        wdyjAdapter.notifyDataSetChanged();
                    }
                } else {
                    EmptyView emptyView = new EmptyView(this);
                    emptyView.mustCallInitWay(listView);
                    emptyView.setMyManager(R.drawable.icon_null);
                    emptyView.setNoMessageText(new JSONObject(object.toString()).getString("msg"));
                    listView.setEmptyView(emptyView);
                }
            }
            //是否消耗积分
            if (type == 5 && err.equals("99")) {
                String content = new JSONObject(object.toString()).getString("msg");
                CommonDialog commonDialog = new CommonDialog();
                commonDialog.showDialog(this, content, 1, this);
            }
            //已经消耗积分
            if (type == 5 && err.equals("0")) {
                Intent intent = new Intent(this, Contact_Detail_Activity.class);
                intent.putExtra("userid", userid);
                intent.putExtra("id", userid);
                startActivity(intent);
            }
            //减积分成功
            if (type == 2 && err.equals("0")) {
                sharedUtils.setBooloean(this, userid, true);
                Intent intent = new Intent(this, Contact_Detail_Activity.class);
                intent.putExtra("userid", userid);
                intent.putExtra("id", userid);
                startActivity(intent);
            }
            //积分不足
            if (type == 2 && !err.equals("0")) {
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
                getPersonInfoData(userid, "5", 2);
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
