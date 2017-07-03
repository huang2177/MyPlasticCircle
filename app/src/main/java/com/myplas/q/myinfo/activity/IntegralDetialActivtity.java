package com.myplas.q.myinfo.activity;

import android.os.Bundle;
import android.util.Log;
import android.widget.AbsListView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.myplas.q.R;
import com.myplas.q.guide.activity.BaseActivity;
import com.myplas.q.common.api.API;
import com.myplas.q.common.netresquset.ResultCallBack;
import com.myplas.q.common.utils.SharedUtils;
import com.myplas.q.myinfo.adapter.Integral_Detial_Adapter;
import com.myplas.q.myinfo.beans.Integraldetialbean;
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
 * 时间：2017/3/29 09:57
 */
public class IntegralDetialActivtity extends BaseActivity implements ResultCallBack{
    private int page;
    private TextView textView;
    private ListView listView;
    private SharedUtils sharedUtils;
    private List<Integraldetialbean.DataBean> list;
    private Integral_Detial_Adapter integralDetialAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ingeral_detial_activity_layout);
        goBack(findViewById(R.id.back));
        goBack(findViewById(R.id.integral_detial_btn));

        page=1;
        list=new ArrayList<>();
        sharedUtils=SharedUtils.getSharedUtils();
        listView= (ListView) findViewById(R.id.integral_detial_listview);
        textView= (TextView) findViewById(R.id.integral_detial_text);

        getData("1");

        listView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
//                if (scrollState == AbsListView.OnScrollListener.SCROLL_STATE_IDLE&&listView.getCount()>visibleItemCount) {
                    if (view.getLastVisiblePosition() == view.getCount() - 1) {
                        page++;
                        getData(String.valueOf(page));
                    }
//                }
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
            }
        });
    }

    public void getData(String page){
        Map<String, String> map = new HashMap<String, String>();
        map.put("token", sharedUtils.getData(this, "token"));
        map.put("page", page);
        map.put("size", "50");
        postAsyn(this, API.BASEURL + API.SCORE_RECORD, map, this, 2);
    }
    @Override
    public void callBack(Object object, int type) {
        try {
            Integraldetialbean integraldetialbean=null;
            if (new JSONObject(object.toString()).getString("err").equals("0")) {
                Gson gson=new Gson();
                integraldetialbean=gson.fromJson(object.toString(),Integraldetialbean.class);
                if (page==1) {
                    textView.setText(integraldetialbean.getPointsAll());
                    integralDetialAdapter=new Integral_Detial_Adapter(this,integraldetialbean.getData());
                    listView.setAdapter(integralDetialAdapter);
                    list.addAll(integraldetialbean.getData());
                } else {
                    list.addAll(integraldetialbean.getData());
                    integralDetialAdapter.setList(list);
                    integralDetialAdapter.notifyDataSetChanged();
                }
            }
        } catch (Exception e) {
        }
    }

    @Override
    public void failCallBack(int type) {

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
