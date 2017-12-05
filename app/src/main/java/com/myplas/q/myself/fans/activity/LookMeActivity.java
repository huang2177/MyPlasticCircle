package com.myplas.q.myself.fans.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.AbsListView;
import android.widget.TextView;

import com.androidkun.xtablayout.XTabLayout;
import com.google.gson.Gson;
import com.myplas.q.R;
import com.myplas.q.contact.activity.Contact_Detail_Activity;
import com.myplas.q.common.api.API;
import com.myplas.q.common.netresquset.ResultCallBack;
import com.myplas.q.common.view.CommonDialog;
import com.myplas.q.common.utils.SharedUtils;
import com.myplas.q.common.utils.TextUtils;
import com.myplas.q.common.view.EmptyView;
import com.myplas.q.common.view.pinnedheadlistview.PinnedHeaderListView;
import com.myplas.q.app.activity.BaseActivity;
import com.myplas.q.myself.fans.adapter.LookMeAdapter;
import com.myplas.q.myself.fans.adapter.LookViewPagerAdapter;
import com.myplas.q.myself.beans.LookMeBean;
import com.myplas.q.myself.integral.activity.IntegralPayActivtity;
import com.myplas.q.sockethelper.RabbitMQConfig;
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
public class LookMeActivity extends BaseActivity implements ResultCallBack
        , CommonDialog.DialogShowInterface
        , LookMeAdapter.OnItemClickListener {
    private String mode;

    private String userid;
    private boolean hasMoreData;
    private StringBuffer promit1, promit2;
    private Map<Integer, String> mStringMap1;
    private Map<Integer, String> mStringMap2;
    private int page, visibleItemCount, position;

    private View mView;
    private ViewPager mViewPager;
    private XTabLayout mTabLayout;
    private LookMeAdapter adapter;
    private TextView textView_num;
    private PinnedHeaderListView listView;
    private LookViewPagerAdapter mAdapter;

    private List<View> mViewList;
    private List<PinnedHeaderListView> mListViews;
    private List<LookMeBean.DataBean.HistoryBean> mList1, mList2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_layout_lookme);
        initTileBar();
        setTitle("谁看过我");

        page = 1;
        hasMoreData = true;
        mList1 = new ArrayList<>();
        mList2 = new ArrayList<>();
        mViewList = new ArrayList<>();
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
                        page++;
                        getViewHistoryDetails(String.valueOf(page), 1, false);
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

            mView = View.inflate(this, R.layout.activity_layout_lookme_vp, null);
            listView = (PinnedHeaderListView) mView.findViewById(R.id.look_listview);

            mListViews.add(listView);
            mViewList.add(mView);
            setListener(i);

            mode = i == 0 ? "0" : "1";
            getViewHistoryDetails("1", i, true);
        }
        mAdapter = new LookViewPagerAdapter(mViewList, titles);
        mViewPager.setAdapter(mAdapter);

        mViewPager.setCurrentItem(0);

        //将选项卡和viewpager关连起来
        mTabLayout.setupWithViewPager(mViewPager);
        //给TabLayout设置适配器
        mTabLayout.setTabsFromPagerAdapter(mAdapter);
    }

    public void getViewHistoryDetails(String page, int type, boolean isShowLoading) {
        Map<String, String> map = new HashMap<String, String>();
        map.put("page", page);
        map.put("size", "15");
        map.put("mode", mode);
        postAsyn1(this, API.BASEURL + API.GET_VIEW_HISTORY_DETAILS, map, this, type, isShowLoading);
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
                    RabbitMQConfig.getInstance(this).readMsg("unread_who_saw_me", 14);
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
                    String msg = new JSONObject(object.toString()).getString("msg");
                    if (page == 1) {
                        mStringMap2.put(type, "0");
                        mStringMap1.put(type, "0");
                        showInfo(mStringMap1.get(0), mStringMap2.get(0));

                        EmptyView emptyView = new EmptyView(this);
                        emptyView.mustCallInitWay(mListViews.get(position));
                        emptyView.setNoMessageText(msg);
                        emptyView.setMyManager(R.drawable.icon_null);
                        mListViews.get(position).setEmptyView(emptyView);
                    } else {
                        TextUtils.toast(this, msg);
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
                Intent intent = new Intent(this, Contact_Detail_Activity.class);
                intent.putExtra("userid", userid);
                intent.putExtra("id", userid);
                startActivity(intent);
            }
            //减积分成功
            if (type == 2 && err.equals("0")) {
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
    public void dialogClick(int type) {
        switch (type) {
            case 1:
                getPersonInfoData(userid, "5", 2);
                break;
            case 2:
                startActivity(new Intent(this, IntegralPayActivtity.class));
                break;
            default:
                break;
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        MobclickAgent.onResume(this);
    }

    @Override
    public void onPause() {
        super.onPause();
        MobclickAgent.onPause(this);
    }
}
