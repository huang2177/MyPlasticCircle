package com.myplas.q.myself.fans.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.widget.TextView;

import com.androidkun.xtablayout.XTabLayout;
import com.myplas.q.R;
import com.myplas.q.app.activity.BaseActivity;
import com.myplas.q.common.view.MyOnPageChangeListener;
import com.myplas.q.myself.fans.FragmentLookMe;
import com.myplas.q.myself.fans.adapter.LookViewPagerAdapter;

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
public class LookMeActivity extends BaseActivity implements MyOnPageChangeListener.OnPageChangeListener,
        FragmentLookMe.InfoCallBackListener {

    private StringBuffer promit;
    private String promit1, promit2;
    private Map<String, String> map1, map2;

    private ViewPager mViewPager;
    private XTabLayout mTabLayout;
    private TextView textviewNum;
    private List<Fragment> mViewList;
    private LookViewPagerAdapter mAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_layout_lookme);
        initTileBar();
        setTitle("谁看过我");

        promit2 = "今日看我：";
        promit1 = "看过我的人的总数：";

        promit = new StringBuffer();
        mViewList = new ArrayList<>();
        map1 = new HashMap<>(8);
        map2 = new HashMap<>(8);

        mViewPager = F(R.id.look_viewpager);
        textviewNum = F(R.id.look_num_text);
        mTabLayout = F(R.id.look_me_tablayout);

        initViewPager();
        mViewPager.addOnPageChangeListener(new MyOnPageChangeListener(this));
    }


    public void initViewPager() {
        List<String> titles = new ArrayList<>();
        titles.add("谁看过我");
        titles.add("我看过谁");
        for (int i = 0; i < titles.size(); i++) {
            mTabLayout.addTab(mTabLayout.newTab().setText(titles.get(i)));
            FragmentLookMe fragment = FragmentLookMe.newInstance(i);
            fragment.setListener(this);
            mViewList.add(fragment);
        }
        mAdapter = new LookViewPagerAdapter(getSupportFragmentManager(), mViewList, titles);
        mViewPager.setAdapter(mAdapter);

        mViewPager.setCurrentItem(0);

        //将选项卡和viewpager关连起来
        mTabLayout.setupWithViewPager(mViewPager);
        //给TabLayout设置适配器
        mTabLayout.setTabsFromPagerAdapter(mAdapter);
    }

    @Override
    public void onPageSelected(int position) {
        FragmentLookMe fragment = (FragmentLookMe) mViewList.get(position);
        fragment.setPosition(position + "");
        promit2 = position == 0 ? "今日看我：" : "今日查看：";
        promit1 = position == 0 ? "看过我的人的总数：" : "我看过的人的总数：";
        showTitle(position + "", map1.get(position + ""), map2.get(position + ""));
    }


    @Override
    public void onResult(String position, String today, String totals) {
        map1.put(position, today);
        map2.put(position, totals);
        if ("0".equals(position)) {
            showTitle(position, today, totals);
        }
    }

    private void showTitle(String position, String today, String totals) {
        promit = new StringBuffer("");
        promit.append(promit2).append(today).append("  ").append(promit1).append(totals);
        textviewNum.setText(promit);
    }

}
