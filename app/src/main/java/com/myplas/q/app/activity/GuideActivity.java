package com.myplas.q.app.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.WindowManager;

import com.myplas.q.R;
import com.myplas.q.common.utils.StatusUtils;
import com.myplas.q.app.adapter.Guide_Adapter;
import com.myplas.q.app.fragment.FirstFragment;
import com.myplas.q.app.fragment.SecondFragment;
import com.myplas.q.app.fragment.ThirdFragment;
import com.umeng.analytics.MobclickAgent;

import java.util.ArrayList;
import java.util.List;

/**
 * 编写： 黄双
 * 电话：15378412400
 * 邮箱：15378412400@163.com
 * 时间：2017/5/27 15:50
 */
public class GuideActivity extends FragmentActivity implements ViewPager.OnPageChangeListener{
    private List<Fragment>list;
    private ViewPager viewPager;
    private Guide_Adapter guide_adapter;

    private FirstFragment firstFragment;
    private SecondFragment secondFragment;
    private ThirdFragment thirdFragment;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StatusUtils.setStatusBar(this, false, false);
        StatusUtils.setStatusTextColor(true, this);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        setContentView(R.layout.layout_guide_activity);
        initView();
    }
    public void initView(){
        list=new ArrayList<>();
        firstFragment=new FirstFragment();
        secondFragment=new SecondFragment();
        thirdFragment=new ThirdFragment();

        list.add(firstFragment);
        list.add(secondFragment);
        list.add(thirdFragment);

        viewPager= (ViewPager) findViewById(R.id.guide_viewpager);
        guide_adapter=new Guide_Adapter(getSupportFragmentManager(),list);
        viewPager.setAdapter(guide_adapter);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        viewPager.setCurrentItem(position);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    public void onResume() {
        super.onResume();
        MobclickAgent.onResume(this);
    }
    public void onPause() {
        super.onPause();
        MobclickAgent.onPause(this);
    }
}
