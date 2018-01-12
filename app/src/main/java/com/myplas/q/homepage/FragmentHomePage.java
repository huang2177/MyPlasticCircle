package com.myplas.q.homepage;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.flyco.tablayout.SegmentTabLayout;
import com.flyco.tablayout.listener.OnTabSelectListener;
import com.myplas.q.R;
import com.myplas.q.app.fragment.BaseFragment;
import com.myplas.q.common.view.MyOnPageChangeListener;
import com.myplas.q.homepage.activity.ContactDaliySignActivity;
import com.myplas.q.homepage.activity.Contact_Search_Activity;
import com.myplas.q.homepage.adapter.HomePageViewPagerAdapter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author 黄双
 * @date 2018/1/11 0011
 */

public class FragmentHomePage extends BaseFragment implements View.OnClickListener
        , MyOnPageChangeListener.OnPageChangeListener {

    private View view;

    private ImageView mSign;
    private TextView mTVTab;
    private ViewPager viewPager;
    private SegmentTabLayout mTabLayout;
    private LinearLayout mLayoutSearch;

    private ArrayList<Fragment> mFragments;
    private HomePageViewPagerAdapter mViewPagerAdapter;

    private FragmentHomePageContact contact;
    private FragmentHomePageBlackList blackList;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        view = View.inflate(getActivity(), R.layout.fragment_homepage_layout, null);

        mSign = F(view, R.id.contact_sign);
        viewPager = F(view, R.id.homepage_viewpager);
        mTabLayout = F(view, R.id.homepage_tablayout);
        mLayoutSearch = F(view, R.id.contact_search_ll);

        mSign.setOnClickListener(this);
        mLayoutSearch.setOnClickListener(this);
        viewPager.addOnPageChangeListener(new MyOnPageChangeListener(this));

        initViewPager();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return view;
    }

    /**
     * 初始化viewpager相关
     */
    private void initViewPager() {
        mFragments = new ArrayList<>();
        String[] title = new String[]{"塑料圈通讯录(11111人)", "塑料黑名单"};
        mTabLayout.setTabData(title);

        contact = new FragmentHomePageContact();
        blackList = new FragmentHomePageBlackList();
        mFragments.add(contact);
        mFragments.add(blackList);

        mViewPagerAdapter = new HomePageViewPagerAdapter(getChildFragmentManager(), mFragments, title);
        viewPager.setAdapter(mViewPagerAdapter);

        viewPager.setCurrentItem(0);
        mTabLayout.setCurrentTab(0);

        mTabLayout.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
                viewPager.setCurrentItem(position);
                if (blackList != null) {
                    blackList.setUserVisible(position == 0 ? false : true);
                }
            }

            @Override
            public void onTabReselect(int position) {

            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.contact_sign:
                startActivity(new Intent(getActivity(), ContactDaliySignActivity.class));
                break;
            case R.id.contact_search_ll:
                startActivity(new Intent(getActivity(), Contact_Search_Activity.class));
                getActivity().overridePendingTransition(R.anim.fade, R.anim.hold);
                break;
            default:
                break;
        }
    }

    @Override
    public void onPageSelected(int position) {
        mTabLayout.setCurrentTab(position);
        if (blackList != null) {
            blackList.setUserVisible(position == 0 ? false : true);
        }
    }


    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);

        if (blackList != null) {
            blackList.setUserVisible(isVisibleToUser);
        }
    }
}
