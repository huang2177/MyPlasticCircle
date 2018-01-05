package com.myplas.q.myself.fans.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.view.View;

import com.myplas.q.myself.fans.FragmentLookMe;

import java.util.List;

/**
 * 编写： 黄双
 * 电话：15378412400
 * 邮箱：15378412400@163.com
 * 时间：2017/3/17 14:52
 */
public class LookViewPagerAdapter extends FragmentPagerAdapter {
    List<String> mTitles;
    List<Fragment> viewLists;

    public LookViewPagerAdapter(FragmentManager fm, List<Fragment> list, List<String> titles) {
        super(fm);
        this.viewLists = list;
        this.mTitles = titles;
    }

    @Override
    public int getCount() {
        if (viewLists.size() != 0) {
            return viewLists.size();
        }
        return 0;
    }

    @Override
    public Fragment getItem(int position) {
        return viewLists.get(position);
    }


    @Override
    public CharSequence getPageTitle(int position) {
        return mTitles.get(position);
    }
}
