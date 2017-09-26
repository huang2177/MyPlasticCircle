package com.myplas.q.headlines.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.myplas.q.headlines.HeadLineListFragment;

import java.util.List;

/**
 * Created by 15378 on 2017/3/15.
 */
public class HeadLineViewPagerAdapter extends FragmentStatePagerAdapter {
    private List<String> mTitles;
    private List<HeadLineListFragment> mFragments;

    public HeadLineViewPagerAdapter(FragmentManager fm, List<HeadLineListFragment> fragments, List<String> titles) {
        super(fm);
        mFragments = fragments;
        mTitles = titles;
    }

    @Override
    public Fragment getItem(int position) {
        return mFragments.get(position);
    }

    @Override
    public int getCount() {
        return mFragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mTitles.get(position);
    }
}
