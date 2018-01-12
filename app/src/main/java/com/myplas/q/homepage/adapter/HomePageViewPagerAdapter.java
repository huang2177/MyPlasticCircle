package com.myplas.q.homepage.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.List;

/**
 * @author 黄双
 * @date 2018/1/11 0011
 */

public class HomePageViewPagerAdapter extends FragmentPagerAdapter {
    private String[] title;
    private List<Fragment> fragments;

    public HomePageViewPagerAdapter(FragmentManager fm, List<Fragment> fragments, String[] title) {
        super(fm);
        this.title = title;
        this.fragments = fragments;
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return title[position];
    }
}
