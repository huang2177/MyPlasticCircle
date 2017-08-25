package com.myplas.q.headlines.fragment;

import android.app.ActionBar;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.myplas.q.R;
import com.myplas.q.common.utils.SharedUtils;
import com.myplas.q.common.utils.TextUtils;
import com.myplas.q.common.view.CustomPopupWindow;
import com.myplas.q.headlines.activity.Cate_Dialog_Activtiy;
import com.myplas.q.headlines.activity.HeadLineSearchActivity;
import com.myplas.q.headlines.adapter.HeadLineViewPagerAdapter;
import com.umeng.analytics.MobclickAgent;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * 编写： 黄双
 * 电话：15378412400
 * 邮箱：15378412400@163.com
 * 时间：2017/3/17 14:45
 */
public class Fragment_HeadLines extends Fragment implements View.OnClickListener, HeadLineListFragment.Myinterface {
    private Handler handler;
    private String keywords;
    private int currentItem;
    private SharedUtils sharedUtils;
    private List<String> list1, list2;

    private View view;
    private GridView gridView;
    private EditText editText;
    private CustomPopupWindow popupWindow;
    private TextView search_src_text, textView_refresh;
    private HeadLineViewPagerAdapter mViewPagerAdapter;

    private ViewPager mViewPager;
    private TabLayout mTabLayout;
    private ImageButton gd_imgbtn;
    private List<HeadLineListFragment> mFragments;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        view = LayoutInflater.from(getActivity()).inflate(R.layout.layout_find_fragment, null, false);

        handler = new Handler();
        mFragments = new ArrayList<>();
        sharedUtils = SharedUtils.getSharedUtils();

        editText = F(R.id.find_edit);
        gd_imgbtn = F(R.id.fx_gd_imgbtn);
        mViewPager = F(R.id.headline_viewpager);
        mTabLayout = F(R.id.headline_tablayout);
        search_src_text = F(R.id.search_src_text);

        editText.setOnClickListener(this);
        gd_imgbtn.setOnClickListener(this);
        search_src_text.setOnClickListener(this);

        initViewPager();

        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                currentItem = position;
                mViewPager.setCurrentItem(position);
                mFragments.get(position).po = position;
                mFragments.get(position).title = list1.get(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
    }

    public void initViewPager() {
        list1 = Arrays.asList("推荐", "塑料上游", "早盘预报", "企业动态", "中晨塑说", "美金市场", "期货资讯", "装置动态", "期刊报告", "独家解读");
        list2 = Arrays.asList("", "2", "1", "9", "76", "5", "21", "11", "13", "22");
        for (int i = 0; i < list1.size(); i++) {
            mTabLayout.addTab(mTabLayout.newTab().setText(list1.get(i).toString()));
            HeadLineListFragment fragment = new HeadLineListFragment();
            fragment.setMyinterface(this);
            fragment.po = i;
            fragment.cate_id = list2.get(i);
            mFragments.add(fragment);
        }
        mViewPagerAdapter = new HeadLineViewPagerAdapter(getChildFragmentManager(), mFragments, list1);
        mViewPager.setAdapter(mViewPagerAdapter);

        mViewPager.setCurrentItem(0);
        //mFragments.get(0).get_Subscribe(1, "", "2", true);
        //将选项卡和viewpager关连起来
        mTabLayout.setupWithViewPager(mViewPager);
        //给TabLayout设置适配器
        mTabLayout.setTabsFromPagerAdapter(mViewPagerAdapter);
        //mTabLayout.post(new Runnable() {
//            @Override
//            public void run() {
//                setIndicator(mTabLayout,10,10);
//            }
//        });
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.search_src_text:
                searchData(editText.getText().toString());
                break;
            case R.id.fx_gd_imgbtn:
                Intent intent = new Intent(getActivity(), Cate_Dialog_Activtiy.class);
                startActivity(intent);
                break;
            case R.id.find_edit:
                Intent in = new Intent(getActivity(), HeadLineSearchActivity.class);
                startActivity(in);
                break;
        }
    }

    public void searchData(String keywords) {
        if (TextUtils.isNullOrEmpty(keywords)) {
            mViewPager.setCurrentItem(0);
            mTabLayout.getTabAt(0).select();
            mFragments.get(currentItem).po = -1;
            mFragments.get(currentItem).keywords1 = keywords;
            mFragments.get(currentItem).get_Subscribe(1, keywords, "1", true);
            InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(editText.getWindowToken(), 0);
        } else {
            TextUtils.Toast(getActivity(), "请输入关键字！");
        }
    }

    public <T extends View> T F(int id) {
        return (T) view.findViewById(id);
    }


    public void showPopou(CustomPopupWindow popupWindow) {
        if (android.os.Build.VERSION.SDK_INT >= 24) {
            int[] a = new int[2];
            editText.getLocationInWindow(a);
            popupWindow.showAtLocation(getActivity().getWindow().getDecorView(), Gravity.NO_GRAVITY, 0, a[1] + editText.getHeight());
        } else {
            popupWindow.showAsDropDown(editText);
        }
    }

    //展示刷新后的popou
    @Override
    public void callBack(String s, boolean b) {
        if (b) {
            mFragments.get(currentItem).isRefresh = false;
            if (TextUtils.isNullOrEmpty(s)) {
                View view = View.inflate(getActivity(), R.layout.layout_refresh_popou, null);
                textView_refresh = (TextView) view.findViewById(R.id.text_refresh_fragement);
                if (popupWindow == null) {
                    popupWindow = new CustomPopupWindow(view, ActionBar.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.MATCH_PARENT);
                }
                popupWindow.setFocusable(true);
                popupWindow.setOutsideTouchable(true);
                textView_refresh.setText(s);
                showPopou(popupWindow);
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        popupWindow.dismiss();
                    }
                }, 1500);
            } else {
                TextUtils.Toast(getActivity(), "已是最新头条信息！");
            }
        }
    }

    public void setIndicator(TabLayout tabs, int leftDip, int rightDip) {
        Class<?> tabLayout = tabs.getClass();
        Field tabStrip = null;
        try {
            tabStrip = tabLayout.getDeclaredField("mTabStrip");
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
        tabStrip.setAccessible(true);
        LinearLayout llTab = null;
        try {
            llTab = (LinearLayout) tabStrip.get(tabs);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        int left = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, leftDip, Resources.getSystem().getDisplayMetrics());
        int right = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, rightDip, Resources.getSystem().getDisplayMetrics());
        for (int i = 0; i < llTab.getChildCount(); i++) {
            View child = llTab.getChildAt(i);
            child.setPadding(0, 0, 0, 0);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.MATCH_PARENT, 1);
            params.leftMargin = left;
            params.rightMargin = right;
            child.setLayoutParams(params);
            child.invalidate();
        }
    }

    public void onResume() {
        super.onResume();
        boolean isLogined = sharedUtils.getBoolean(getActivity(), "isLogined_headlines");
        String data = sharedUtils.getData(getActivity(), "refreshdata");

        if (isLogined) {
            mFragments.get(0).get_Subscribe(1, "", "2", true);
            sharedUtils.setBooloean(getActivity(), "isLogined_headlines", false);
        }
        if (!"".equals(data)) {//从供求-qq页面跳转
            searchData(data);
            editText.setText(data);
            sharedUtils.setData(getActivity(), "refreshdata", "");
        }
        MobclickAgent.onPageStart("MainScreen"); //统计页面，"MainScreen"为页面名称，可自定义
    }

    public void onPause() {
        super.onPause();
        MobclickAgent.onPageEnd("MainScreen");
    }
}
