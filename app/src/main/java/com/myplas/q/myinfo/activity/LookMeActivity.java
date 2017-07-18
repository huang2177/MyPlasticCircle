package com.myplas.q.myinfo.activity;

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
import com.myplas.q.common.utils.DialogShowUtils;
import com.myplas.q.common.utils.SharedUtils;
import com.myplas.q.common.utils.TextUtils;
import com.myplas.q.common.view.XListView;
import com.myplas.q.guide.activity.BaseActivity;
import com.myplas.q.myinfo.adapter.MyFollowAdapter;
import com.myplas.q.myinfo.beans.MyFollowBean;
import com.umeng.analytics.MobclickAgent;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * 编写： 黄双
 * 电话：15378412400
 * 邮箱：15378412400@163.com
 * 时间：2017/3/20 22:15
 */
public class LookMeActivity extends BaseActivity implements ResultCallBack, View.OnClickListener {

    private SharedUtils sharedUtils;
    private int page, visibleItemCount;

    private ListView listView;
    private TextView textView_other, textView_me, textView_other_bg, textView_me_bg, textView_num;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wholookme_layout);
        goBack(findViewById(R.id.img_back));

        page = 1;
        sharedUtils = SharedUtils.getSharedUtils();
        listView = F(R.id.look_listview);
        textView_num = F(R.id.look_num_text);
        textView_me = F(R.id.look_me_text);
        textView_other = F(R.id.look_other_text);
        textView_me_bg = F(R.id.look_me_text_bg);
        textView_other_bg = F(R.id.look_other_text_bg);

        textView_me.setOnClickListener(this);
        textView_other.setOnClickListener(this);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });
        listView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                if (scrollState == AbsListView.OnScrollListener.SCROLL_STATE_IDLE && listView.getCount() > visibleItemCount) {
                    if (view.getLastVisiblePosition() == view.getCount() - 1) {
                        page++;
                        //getMyFans(String.valueOf(page));
                    }
                }
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                LookMeActivity.this.visibleItemCount = visibleItemCount;
            }
        });
    }

    public void getMyFans(String page) {
        try {
            Map<String, String> map = new HashMap<String, String>();
            map.put("token", sharedUtils.getData(this, "token"));
            map.put("page", page);
            map.put("size", "10");
            postAsyn(this, API.BASEURL + API.GET_MY_FUNS, map, this, 1);
        } catch (Exception e) {
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.look_other_text:
                textView_me_bg.setVisibility(View.INVISIBLE);
                textView_other_bg.setVisibility(View.VISIBLE);
                textView_other.setTextColor(getResources().getColor(R.color.color_balank));
                textView_me.setTextColor(getResources().getColor(R.color.color_gray));
                break;
            case R.id.look_me_text:
                textView_me_bg.setVisibility(View.VISIBLE);
                textView_other_bg.setVisibility(View.INVISIBLE);
                textView_me.setTextColor(getResources().getColor(R.color.color_balank));
                textView_other.setTextColor(getResources().getColor(R.color.color_gray));
                break;
        }
    }

    @Override
    public void callBack(Object object, int type) {
        try {
            Gson gson = new Gson();
            String err = new JSONObject(object.toString()).getString("err");
            if (type == 1) {
                MyFansBean wdgzBean = null;
                if (err.equals("0")) {

                } else {
                    TextUtils.Toast(this, new JSONObject(object.toString()).getString("msg"));
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
