package com.myplas.q.myinfo.message.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.google.gson.Gson;
import com.myplas.q.R;
import com.myplas.q.common.api.API;
import com.myplas.q.common.netresquset.ResultCallBack;
import com.myplas.q.guide.activity.BaseActivity;
import com.myplas.q.myinfo.beans.MyMessageBean;
import com.myplas.q.myinfo.message.adapter.MessageListsAdapter;
import com.umeng.analytics.MobclickAgent;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 编写： 黄双
 * 电话：15378412400
 * 邮箱：15378412400@163.com
 * 时间：2017/3/29 14:35
 */
public class MessageListsActivity extends BaseActivity implements ResultCallBack {
    private ListView listView;
    private MessageListsAdapter mAdapter;
    private List<MyMessageBean.DataBean> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_layout_messagelist);
        initTileBar();
        setTitle("我的消息");
        listView = (ListView) findViewById(R.id.messagelist_listview);

        mAdapter = new MessageListsAdapter(this, null);
        listView.setAdapter(mAdapter);
        //getNetData("1");

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                StringBuffer stringBuffer = null;
                if (position == 0) {
                    stringBuffer = new StringBuffer("供求消息");
                } else if (position == 1) {
                    stringBuffer = new StringBuffer("求购消息");
                } else {
                    stringBuffer = new StringBuffer("回复消息");
                }
                Intent intent = new Intent(MessageListsActivity.this, MessageCommonActivity.class);
                intent.putExtra("title", stringBuffer.toString());
                startActivity(intent);
            }
        });
    }

    public void getNetData(String page) {
        Map<String, String> map = new HashMap<String, String>();
        map.put("page", page);
        map.put("size", "10");
        postAsyn(this, API.BASEURL + API.GET_ROBOT_MSG, map, this, 1);
    }

    @Override
    public void callBack(Object object, int type) {
        try {
            if (new JSONObject(object.toString()).getString("err").equals("0")) {
                Gson gson = new Gson();
                MyMessageBean myMessageBean = gson.fromJson(object.toString(), MyMessageBean.class);
                list = myMessageBean.getData();
                mAdapter = new MessageListsAdapter(this, null);
                listView.setAdapter(mAdapter);
            } else {
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
