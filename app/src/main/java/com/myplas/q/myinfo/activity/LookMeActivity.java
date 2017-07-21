package com.myplas.q.myinfo.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.myplas.q.R;
import com.myplas.q.common.api.API;
import com.myplas.q.common.netresquset.ResultCallBack;
import com.myplas.q.common.utils.DialogShowUtils;
import com.myplas.q.common.utils.SharedUtils;
import com.myplas.q.common.utils.TextUtils;
import com.myplas.q.common.view.NoResultLayout;
import com.myplas.q.common.view.PinnedHeaderListView;
import com.myplas.q.guide.activity.BaseActivity;
import com.myplas.q.myinfo.adapter.LookMeAdapter;
import com.myplas.q.myinfo.adapter.Look_ViewPager_Adapter;
import com.myplas.q.myinfo.beans.LookMeBean;
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
public class LookMeActivity extends BaseActivity implements ResultCallBack, View.OnClickListener, DialogShowUtils.DialogShowInterface {
    private String mode;
    private String userid;
    private StringBuffer promit;
    private boolean hasMoreData;
    private int page, visibleItemCount;

    private ViewPager mViewPager;
    private LookMeAdapter adapter;
    private Look_ViewPager_Adapter mAdapter;
    private PinnedHeaderListView listView, mListView;
    private NoResultLayout mNoResultLayout1, mNoResultLayout2;
    private TextView textView_other, textView_me, textView_other_bg, textView_me_bg, textView_num;

    private List<View> mViewList;
    private List<LookMeBean.DataBean.HistoryBean> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wholookme_layout);
        goBack(findViewById(R.id.img_back));

        page = 1;
        mode = "0";
        hasMoreData = true;
        list = new ArrayList<>();
        mViewList = new ArrayList<>();
        promit = new StringBuffer("看过我的人的总数：");

        mViewPager = F(R.id.look_viewpager);
        textView_num = F(R.id.look_num_text);
        textView_me = F(R.id.look_me_text);
        textView_other = F(R.id.look_other_text);
        textView_me_bg = F(R.id.look_me_text_bg);
        textView_other_bg = F(R.id.look_other_text_bg);

        textView_me.setOnClickListener(this);
        textView_other.setOnClickListener(this);

        setView();

        listView.setOnItemClickListener(new PinnedHeaderListView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int section, int position, long id) {
                userid = list.get(section).getPerson().get(position - 1).getUserid();
                getPersonInfoData(userid, "1", 5);
            }

            @Override
            public void onSectionClick(AdapterView<?> adapterView, View view, int section, long id) {
            }
        });
        listView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                if (scrollState == AbsListView.OnScrollListener.SCROLL_STATE_IDLE && listView.getCount() > visibleItemCount) {
                    if (view.getLastVisiblePosition() == view.getCount() - 1) {
                        if (hasMoreData) {
                            page++;
                            getViewHistoryDetails(String.valueOf(page), 1);
                        } else {
                            TextUtils.Toast(LookMeActivity.this, "没有更多数据！");
                        }
                    }
                }
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                LookMeActivity.this.visibleItemCount = visibleItemCount;
            }
        });
        mListView.setOnItemClickListener(new PinnedHeaderListView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int section, int position, long id) {
                userid = list.get(section).getPerson().get(position - 1).getUserid();
                getPersonInfoData(userid, "1", 5);
            }

            @Override
            public void onSectionClick(AdapterView<?> adapterView, View view, int section, long id) {
            }
        });
        mListView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                if (scrollState == AbsListView.OnScrollListener.SCROLL_STATE_IDLE && listView.getCount() > visibleItemCount) {
                    if (view.getLastVisiblePosition() == view.getCount() - 1) {
                        if (hasMoreData) {
                            page++;
                            getViewHistoryDetails(String.valueOf(page), 1);
                        } else {
                            TextUtils.Toast(LookMeActivity.this, "没有更多数据！");
                        }
                    }
                }
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                LookMeActivity.this.visibleItemCount = visibleItemCount;
            }
        });

        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                mode = (position == 0) ? ("0") : ("1");
                int id = (position == 0) ? (R.id.look_me_text) : (R.id.look_other_text);
                chageColor(id);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    public void setView() {
        mNoResultLayout1 = new NoResultLayout(this);
        listView = (PinnedHeaderListView) mNoResultLayout1.findViewById(R.id.look_listview);
        mNoResultLayout2 = new NoResultLayout(this);
        mListView = (PinnedHeaderListView) mNoResultLayout2.findViewById(R.id.look_listview);
        listView.setPullRefreshEnable(false);
        mListView.setPullRefreshEnable(false);
        mViewList.add(mNoResultLayout1);
        mViewList.add(mNoResultLayout2);
        mAdapter = new Look_ViewPager_Adapter(mViewList);
        mViewPager.setAdapter(mAdapter);

        mViewPager.setCurrentItem(0);
        getViewHistoryDetails("1", 1);
    }

    public void getViewHistoryDetails(String page, int type) {
        Map<String, String> map = new HashMap<String, String>();
        map.put("page", page);
        map.put("size", "15");
        map.put("mode", mode);
        postAsyn(this, API.BASEURL + API.GET_VIEW_HISTORY_DETAILS, map, this, type);
    }

    public void getPersonInfoData(String userid, String showtype, int type) {
        Map<String, String> map = new HashMap<String, String>();
        map.put("token", SharedUtils.getSharedUtils().getData(this, "token"));
        map.put("user_id", userid);
        map.put("showType", showtype);
        String url = API.BASEURL + API.GET_ZONE_FRIEND;
        BaseActivity.postAsyn(this, url, map, this, type);
    }

    @Override
    public void onClick(View v) {
        chageColor(v.getId());
    }

    public void chageColor(int id) {
        switch (id) {
            case R.id.look_me_text:
                page = 1;
                mode = "0";
                hasMoreData = true;
                getViewHistoryDetails("1", 1);
                mViewPager.setCurrentItem(0);
                promit = new StringBuffer("看过我的人的总数：");
                textView_num.setText("看过我的人的总数： 0 今日：0");
                textView_me_bg.setVisibility(View.VISIBLE);
                textView_other_bg.setVisibility(View.INVISIBLE);
                textView_me.setTextColor(getResources().getColor(R.color.color_balank));
                textView_other.setTextColor(getResources().getColor(R.color.color_gray));
                break;
            case R.id.look_other_text:
                page = 1;
                mode = "1";
                hasMoreData = true;
                getViewHistoryDetails("1", 3);
                mViewPager.setCurrentItem(1);
                textView_num.setText("我看过的人的总数： 0 今日：0");
                promit = new StringBuffer("我看过的人的总数： ");
                textView_me_bg.setVisibility(View.INVISIBLE);
                textView_other_bg.setVisibility(View.VISIBLE);
                textView_other.setTextColor(getResources().getColor(R.color.color_balank));
                textView_me.setTextColor(getResources().getColor(R.color.color_gray));
                break;
        }
    }

    @Override
    public void callBack(Object object, int type) {
        try {
            Log.e("------", object.toString());
            Gson gson = new Gson();
            String err = new JSONObject(object.toString()).getString("err");
            if (type == 1) {
                LookMeBean lookMeBean = null;
                if (err.equals("0")) {
                    mNoResultLayout1.setVisibility(false);
                    lookMeBean = gson.fromJson(object.toString(), LookMeBean.class);
                    if (page == 1) {
                        adapter = new LookMeAdapter(this, lookMeBean.getData().getHistory());
                        listView.setAdapter(adapter);
                        showInfo(lookMeBean);
                        list.clear();
                        list.addAll(lookMeBean.getData().getHistory());
                    } else {
                        list.addAll(lookMeBean.getData().getHistory());
                        adapter.setList(list);
                        adapter.notifyDataSetChanged();
                    }
                } else if (err.equals("2")) {
                    hasMoreData = false;
                    adapter.setList(null);
                    adapter.notifyDataSetChanged();
                    mNoResultLayout1.setNoResultData(R.drawable.seenumempty, new JSONObject(object.toString()).getString("msg"), true);
                }
            }
            if (type == 3) {
                LookMeBean lookMeBean = null;
                if (err.equals("0")) {
                    mNoResultLayout2.setVisibility(false);
                    lookMeBean = gson.fromJson(object.toString(), LookMeBean.class);
                    if (page == 1) {
                        adapter = new LookMeAdapter(this, lookMeBean.getData().getHistory());
                        mListView.setAdapter(adapter);
                        showInfo(lookMeBean);
                        list.clear();
                        list.addAll(lookMeBean.getData().getHistory());
                    } else {
                        list.addAll(lookMeBean.getData().getHistory());
                        adapter.setList(list);
                        adapter.notifyDataSetChanged();
                    }
                } else if (err.equals("2")) {
                    hasMoreData = false;
                    adapter.setList(null);
                    adapter.notifyDataSetChanged();
                    mNoResultLayout2.setNoResultData(R.drawable.seenumempty, new JSONObject(object.toString()).getString("msg"), true);
                }
            }
            //是否消耗积分
            if (type == 5 && err.equals("99")) {
                String content = new JSONObject(object.toString()).getString("msg");
                DialogShowUtils dialogShowUtils = new DialogShowUtils();
                dialogShowUtils.showDialog(this, content, 1, this);
            }
            //已经消耗积分
            if (type == 5 && err.equals("0")) {
                Intent intent = new Intent(this, PersonInfoActivity.class);
                intent.putExtra("userid", userid);
                intent.putExtra("id", userid);
                startActivity(intent);
            }
            //减积分成功
            if (type == 2 && err.equals("0")) {
                Intent intent = new Intent(this, PersonInfoActivity.class);
                intent.putExtra("userid", userid);
                intent.putExtra("id", userid);
                startActivity(intent);
            }
            //积分不足
            if (type == 2 && !err.equals("0")) {
                String content = new JSONObject(object.toString()).getString("msg");
                DialogShowUtils dialogShowUtils = new DialogShowUtils();
                dialogShowUtils.showDialog(this, content, (err.equals("100")) ? (2) : (3), this);
            }
        } catch (Exception e) {
        }
    }

    private void showInfo(LookMeBean lookMeBean) {
        textView_num.setText(promit.toString() +
                lookMeBean.getData().getTotals() + "  今日：" +
                lookMeBean.getData().getToday());
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
