package com.myplas.q.myinfo.activity;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.util.TypedValue;
import android.view.View;
import android.widget.AbsListView;
import android.widget.LinearLayout;
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
import com.myplas.q.myinfo.adapter.LookViewPagerAdapter;
import com.myplas.q.myinfo.beans.LookMeBean;
import com.umeng.analytics.MobclickAgent;

import org.json.JSONObject;

import java.lang.reflect.Field;
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
public class LookMeActivity extends BaseActivity implements ResultCallBack, DialogShowUtils.DialogShowInterface, LookMeAdapter.OnItemClickListener {
    private String mode;
    private String userid;
    private boolean hasMoreData;
    private StringBuffer promit1,promit2;
    private int page, visibleItemCount,position;

    private ViewPager mViewPager;
    private TabLayout mTabLayout;
    private LookMeAdapter adapter;
    private PinnedHeaderListView listView;
    private LookViewPagerAdapter mAdapter;
    private NoResultLayout mNoResultLayout1;
    private TextView textView_num;

    private List<NoResultLayout> mView;
    private List<PinnedHeaderListView> mListViews;
    private List<LookMeBean.DataBean.HistoryBean> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lookme_layout);
        goBack(findViewById(R.id.img_back));

        page = 1;
        mode = "0";
        hasMoreData = true;
        list = new ArrayList<>();
        mView = new ArrayList<>();
        mListViews = new ArrayList<>();
        promit2 = new StringBuffer("今日看我：");
        promit1 = new StringBuffer("看过我的人的总数：");

        mViewPager = F(R.id.look_viewpager);
        textView_num = F(R.id.look_num_text);
        mTabLayout = F(R.id.look_me_tablayout);

        initViewPager();

        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }
            @Override
            public void onPageSelected(int position) {
                LookMeActivity.this.position=position;
                chageColor(position);
            }
            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }
    public void setListener(final int position){
        mListViews.get(position).setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                if (scrollState == AbsListView.OnScrollListener.SCROLL_STATE_IDLE &&
                        mListViews.get(position).getCount() > visibleItemCount) {
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
    }

    public void initViewPager() {
        List<String> titles = new ArrayList<>();
        titles.add("谁看过我");
        titles.add("我看过谁");
        for (int i = 0; i < titles.size(); i++) {
            mTabLayout.addTab(mTabLayout.newTab().setText(titles.get(i)));
        }
        for (int i = 0; i < 2; i++) {
            mNoResultLayout1 = new NoResultLayout(this);
            listView = (PinnedHeaderListView) mNoResultLayout1.findViewById(R.id.look_listview);
            listView.setPullRefreshEnable(false);
            mView.add(mNoResultLayout1);
            mListViews.add(listView);
            setListener(i);
        }
        mAdapter = new LookViewPagerAdapter(mView, titles);
        mViewPager.setAdapter(mAdapter);

        mViewPager.setCurrentItem(0);
        getViewHistoryDetails("1", 1);

        //将选项卡和viewpager关连起来
        mTabLayout.setupWithViewPager(mViewPager);
        //给TabLayout设置适配器
        mTabLayout.setTabsFromPagerAdapter(mAdapter);
        mTabLayout.post(new Runnable() {
            @Override
            public void run() {
                setIndicator(mTabLayout, 30, 30);
            }
        });
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


    public void chageColor(int id) {
        switch (id) {
            case 0:
                page = 1;
                mode = "0";
                hasMoreData = true;
                getViewHistoryDetails("1", 1);
                mViewPager.setCurrentItem(0);
                promit2=new StringBuffer("今日看我：");
                promit1 = new StringBuffer("看过我的人的总数：");
                break;
            case 1:
                page = 1;
                mode = "1";
                hasMoreData = true;
                getViewHistoryDetails("1", 1);
                mViewPager.setCurrentItem(1);
                promit2=new StringBuffer("今日查看：");
                promit1 = new StringBuffer("我看过的人的总数：");
                break;
        }
    }

    //listview的item点击事件
    @Override
    public void onItemClick(int section, int position) {
        userid = list.get(section).getPerson().get(position).getUserid();
        getPersonInfoData(userid, "1", 5);
    }

    @Override
    public void callBack(Object object, int type) {
        try {
            Gson gson = new Gson();
            String err = new JSONObject(object.toString()).getString("err");
            if (type == 1) {
                LookMeBean lookMeBean = null;
                if (err.equals("0")) {
                    mView.get(position).setVisibility(false);
                    lookMeBean = gson.fromJson(object.toString(), LookMeBean.class);
                    if (page == 1) {
                        adapter = new LookMeAdapter(this, lookMeBean.getData().getHistory());
                        mListViews.get(position).setAdapter(adapter);
                        adapter.setOnItemClickListener(this);
                        showInfo(lookMeBean.getData().getTotals(),lookMeBean.getData().getToday());
                        list.clear();
                        list.addAll(lookMeBean.getData().getHistory());
                    } else {
                        list.addAll(lookMeBean.getData().getHistory());
                        adapter.setList(list);
                        adapter.notifyDataSetChanged();
                    }
                } else if (err.equals("2")) {
                    showInfo("0","0");
                    hasMoreData = false;
                    adapter = new LookMeAdapter(this,null);
                    mListViews.get(position).setAdapter(adapter);
                    String msg= new JSONObject(object.toString()).getString("msg");
                    mView.get(position).setNoResultData(R.drawable.icon_null,msg, true);
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

    private void showInfo(String num1,String num2) {
        textView_num.setText(promit1.toString() +
                num1 +"  "+
                promit2.toString() +
                num2);
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

    public void setIndicator(TabLayout tabs, int leftDip, int rightDip) {
        Class<?> tabLayout = tabs.getClass();
        Field tabStrip = null;
        try {
            tabStrip = tabLayout.getDeclaredField("mTabStrip");
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
        tabStrip.setAccessible(true);
        LinearLayout llTab = null;
        try {
            llTab = (LinearLayout) tabStrip.get(tabs);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        int left = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, leftDip, Resources.getSystem().getDisplayMetrics());
        int right = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, rightDip, Resources.getSystem().getDisplayMetrics());
        for (int i = 0; i < llTab.getChildCount(); i++) {
            View child = llTab.getChildAt(i);
            child.setPadding(0, 0, 0, 0);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.MATCH_PARENT, 1);
            params.leftMargin = left;
            params.rightMargin = right;
            child.setLayoutParams(params);
            child.invalidate();
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
