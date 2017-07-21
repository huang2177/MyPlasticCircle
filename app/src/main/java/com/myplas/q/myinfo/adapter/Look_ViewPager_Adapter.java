package com.myplas.q.myinfo.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;

import com.myplas.q.common.view.PinnedHeaderListView;

import java.util.List;

/**
 * 编写： 黄双
 * 电话：15378412400
 * 邮箱：15378412400@163.com
 * 时间：2017/3/17 14:52
 */
public class Look_ViewPager_Adapter extends PagerAdapter {
    List<View> viewLists;

    public Look_ViewPager_Adapter(List<View> list) {
        this.viewLists = list;
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
}
