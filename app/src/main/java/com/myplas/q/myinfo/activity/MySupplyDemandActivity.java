package com.myplas.q.myinfo.activity;

import android.os.Bundle;
import android.widget.AbsListView;
import android.widget.ScrollView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.myplas.q.R;
import com.myplas.q.common.utils.TextUtils;
import com.myplas.q.guide.activity.BaseActivity;
import com.myplas.q.common.netresquset.ResultCallBack;
import com.myplas.q.common.utils.SharedUtils;
import com.myplas.q.common.view.XListView;
import com.myplas.q.common.api.API;
import com.myplas.q.myinfo.adapter.*;
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
public class MySupplyDemandActivity extends BaseActivity implements ResultCallBack,SupplyDemandAdapter.MyInterface
                                            ,XListView.IXListViewListener{
    private XListView listView;
    private List<MyCommentBean.DataBean> list;
    private List<MyCommentBean.DataBean> list_more;
    private SupplyDemandAdapter supplyDemandAdapter;
    private TextView textView;
    private SharedUtils sharedUtils;
    private int page=1,visibleItemCount;
    private String type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.wd_gj_activity_layout);
        goBack(findViewById(R.id.back));
        listView= (XListView) findViewById(R.id.wd_gj_listview);
        textView= (TextView) findViewById(R.id.title_rs);
        textView.setText(getIntent().getStringExtra("title"));
        list_more=new ArrayList<>();
        listView.setPullLoadEnable(true);
        listView.setPullRefreshEnable(false);
        listView.setXListViewListener(this);
        sharedUtils=SharedUtils.getSharedUtils();
        type=getIntent().getStringExtra("type");
        getSupplyDemandList("1");
        listView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                if (scrollState == AbsListView.OnScrollListener.SCROLL_STATE_IDLE&&listView.getCount()>visibleItemCount) {
                    if (view.getLastVisiblePosition() == view.getCount() - 1) {
                        page++;
                        getSupplyDemandList(String.valueOf(page));
                    }
                }
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                MySupplyDemandActivity.this.visibleItemCount=visibleItemCount;
            }
        });
    }
    public void getSupplyDemandList(String page) {
        Map<String, String> map = new HashMap<String, String>();
        map.put("token", sharedUtils.getData(this, "token"));
        map.put("type",type);
        map.put("page", page);
        map.put("size", "10");
        postAsyn(this, API.BASEURL +API.GET_MY_MSG, map, this,1);
    }
    @Override
    public void callBack(Object object, int type) {
        try {
            Gson gson=new Gson();
            if (type==1) {
                if (new JSONObject(object.toString()).getString("err").equals("0")) {
                    MyCommentBean supplyDemandBean=gson.fromJson(object.toString(), MyCommentBean.class);
                    list=supplyDemandBean.getData();
                    if (page==1) {
                        supplyDemandAdapter=new SupplyDemandAdapter(this,list,getIntent().getStringExtra("type"),this);
                        listView.setAdapter(supplyDemandAdapter);
                        listView.stopRefresh();
                        list_more.clear();
                        list_more.addAll(list);
                    }else {
                        if(list!=null&&list.size()!=0){
                            listView.stopLoadMore();
                            list_more.addAll(list);
                            supplyDemandAdapter.setList(list_more);
                            supplyDemandAdapter.notifyDataSetChanged();
                        }
                    }
                }else {
                    list=null;
                    TextUtils.Toast(this,new JSONObject(object.toString()).getString("msg"));
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
        page=1;
        getSupplyDemandList(String.valueOf(page));
    }
    //刷新
    @Override
    public void onRefresh() {
        page=1;
        getSupplyDemandList(String.valueOf(page));
    }
    //加载
    @Override
    public void onLoadMore() {
//        page++;
//        getSupplyDemandList(String.valueOf(page));
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
