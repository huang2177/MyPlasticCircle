package com.myplas.q.myinfo.fans.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.widget.AbsListView;
import android.widget.TextView;

import com.androidkun.xtablayout.XTabLayout;
import com.google.gson.Gson;
import com.myplas.q.R;
import com.myplas.q.common.api.API;
import com.myplas.q.common.netresquset.ResultCallBack;
import com.myplas.q.common.view.CommonDialog;
import com.myplas.q.common.utils.SharedUtils;
import com.myplas.q.common.utils.TextUtils;
import com.myplas.q.common.view.NoResultLayout;
import com.myplas.q.common.view.PinnedHeaderListView;
import com.myplas.q.guide.activity.BaseActivity;
import com.myplas.q.myinfo.fans.adapter.LookMeAdapter;
import com.myplas.q.myinfo.fans.adapter.LookViewPagerAdapter;
import com.myplas.q.myinfo.beans.LookMeBean;
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
public class LookMeActivity extends BaseActivity implements ResultCallBack, CommonDialog.DialogShowInterface, LookMeAdapter.OnItemClickListener {
    private String mode;
    private String userid;
    private boolean hasMoreData;
    private StringBuffer promit1, promit2;
    private Map<Integer, String> mStringMap1;
    private Map<Integer, String> mStringMap2;
    private int page, visibleItemCount, position;

    private ViewPager mViewPager;
    private XTabLayout mTabLayout;
    private LookMeAdapter adapter;
    private TextView textView_num;
    private PinnedHeaderListView listView;
    private LookViewPagerAdapter mAdapter;
    private NoResultLayout mNoResultLayout1;

    private List<NoResultLayout> mView;
    private List<PinnedHeaderListView> mListViews;
    private List<LookMeBean.DataBean.HistoryBean> mList1, mList2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lookme_layout);
        initTileBar();
        setTitle("谁看过我");

        page = 1;
        hasMoreData = true;
        mView = new ArrayList<>();
        mList1 = new ArrayList<>();
        mList2 = new ArrayList<>();
        mStringMap1 = new HashMap<>();
        mStringMap2 = new HashMap<>();
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
                mViewPager.setCurrentItem(position);
                LookMeActivity.this.position = position;
                promit2 = new StringBuffer(position == 0 ? "今日看我：" : "今日查看：");
                promit1 = new StringBuffer(position == 0 ? "看过我的人的总数：" : "我看过的人的总数：");
                showInfo(mStringMap1.get(position), mStringMap2.get(position));
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    public void setListener(final int position) {
        mListViews.get(position).setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                if (scrollState == AbsListView.OnScrollListener.SCROLL_STATE_IDLE &&
                        mListViews.get(position).getCount() >= visibleItemCount) {
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

            mNoResultLayout1 = new NoResultLayout(this);
            listView = (PinnedHeaderListView) mNoResultLayout1.findViewById(R.id.look_listview);
            listView.setPullRefreshEnable(false);

            mListViews.add(listView);
            mView.add(mNoResultLayout1);
            setListener(i);

            mode = i == 0 ? "0" : "1";
            getViewHistoryDetails("1", i);
        }
        mAdapter = new LookViewPagerAdapter(mView, titles);
        mViewPager.setAdapter(mAdapter);

        mViewPager.setCurrentItem(0);

        //将选项卡和viewpager关连起来
        mTabLayout.setupWithViewPager(mViewPager);
        //给TabLayout设置适配器
        mTabLayout.setTabsFromPagerAdapter(mAdapter);
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


    //listview的item点击事件
    @Override
    public void onItemClick(int section, int p) {
        userid = (position == 0 ? mList1 : mList2)
                .get(section)
                .getPerson()
                .get(p)
                .getUserid();
        getPersonInfoData(userid, "1", 5);
    }

    @Override
    public void callBack(Object object, int type) {
        try {
            Gson gson = new Gson();
            int position = type == 0 ? 0 : 1;
            String err = new JSONObject(object.toString()).getString("err");
            List<LookMeBean.DataBean.HistoryBean> list = type == 0 ? mList1 : mList2;
            if (type == position) {
                LookMeBean lookMeBean = null;
                if (err.equals("0")) {
                    mView.get(position).setVisibility(false);
                    lookMeBean = gson.fromJson(object.toString(), LookMeBean.class);
                    if (page == 1) {
                        adapter = new LookMeAdapter(this, lookMeBean.getData().getHistory());
                        mListViews.get(position).setAdapter(adapter);
                        adapter.setOnItemClickListener(this);
                        list.clear();
                        list.addAll(lookMeBean.getData().getHistory());
                        mStringMap2.put(type, lookMeBean.getData().getToday());
                        mStringMap1.put(type, lookMeBean.getData().getTotals());
                        showInfo(mStringMap1.get(0), mStringMap2.get(0));
                    } else {
                        list.addAll(lookMeBean.getData().getHistory());
                        adapter.setList(list);
                        adapter.notifyDataSetChanged();
                    }
                } else if (err.equals("2")) {
                    if (page == 1) {
                        hasMoreData = false;
                        mStringMap2.put(type, "0");
                        mStringMap1.put(type, "0");
                        adapter = new LookMeAdapter(this, null);
                        mListViews.get(position).setAdapter(adapter);
                        showInfo(mStringMap1.get(0), mStringMap2.get(0));
                        String msg = new JSONObject(object.toString()).getString("msg");
                        mView.get(position).setNoResultData(R.drawable.icon_null, msg, true);
                    }
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
                CommonDialog commonDialog = new CommonDialog();
                commonDialog.showDialog(this, content, (err.equals("100")) ? (2) : (3), this);
            }
        } catch (Exception e) {
        }
    }

    private void showInfo(String num1, String num2) {
        textView_num.setText(promit1.toString() +
                num1 + "  " +
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

    public void onResume() {
        super.onResume();
        MobclickAgent.onResume(this);
    }

    public void onPause() {
        super.onPause();
        MobclickAgent.onPause(this);
    }
}
