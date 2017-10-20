package com.myplas.q.myself.fans.adapter;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * 编写： 黄双
 * 电话：15378412400
 * 邮箱：15378412400@163.com
 * 时间：2017/3/17 14:52
 */
public class LookViewPagerAdapter extends PagerAdapter {
    List<String> mTitles;
    List<View> viewLists;

    public LookViewPagerAdapter(List<View> list, List<String> titles) {
        this.viewLists = list;
        this.mTitles=titles;
    }

    @Override
    public int getCount() {
        if (viewLists.size() != 0)
            return viewLists.size();
        return 0;
    }

    @Override
    public boolean isViewFromObject(View arg0, Object arg1) {
        return arg0 == arg1;
    }

    /*
    * 实例化item*/
    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        container.addView(viewLists.get(position));
        return viewLists.get(position);
    }
    @Override
    public CharSequence getPageTitle(int position) {
        return mTitles.get(position);
    }
}
