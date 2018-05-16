package com.myplas.q.myself.login;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * 编写： 黄双
 * 电话：15378412400
 * 邮箱：15378412400@163.com
 * 时间：2017/3/17 14:52
 */
public class ViewPager_Adapter extends FragmentPagerAdapter {
    private List<Fragment> list;


    public ViewPager_Adapter(FragmentManager fm, List<Fragment> list) {
        super(fm);
        this.list = list;

    }

    @Override
    public Fragment getItem(int position) {
        return list.get(position);
    }

//    @Override
//    public void destroyItem(ViewGroup container, int position, Object object) {
//    }

    @Override
    public int getCount() {
        if (list.size() != 0) {
            return list.size();
        }
        return 0;
    }


}
