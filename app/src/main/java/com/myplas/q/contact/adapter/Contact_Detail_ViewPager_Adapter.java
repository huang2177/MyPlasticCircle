package com.myplas.q.contact.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.view.ViewGroup;

import java.util.List;

/**
 * 作者:huangshuang
 * 事件 2017/10/17 0017.
 * 邮箱： 15378412400@163.com
 */

public class Contact_Detail_ViewPager_Adapter extends FragmentStatePagerAdapter {
    private List<String> mTitles;
    private List<Fragment> mFragments;

    public Contact_Detail_ViewPager_Adapter(FragmentManager fm, List<Fragment> fragments, List<String> titles) {
        super(fm);
        mTitles = titles;
        mFragments = fragments;
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
    public void destroyItem(ViewGroup container, int position, Object object) {
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mTitles.get(position);
    }
}
