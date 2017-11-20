package com.myplas.q.headlines;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.androidkun.xtablayout.XTabLayout;
import com.myplas.q.R;
import com.myplas.q.common.appcontext.Constant;
import com.myplas.q.common.utils.SharedUtils;
import com.myplas.q.common.utils.TextUtils;
import com.myplas.q.common.view.CustomPopupWindow;
import com.myplas.q.common.view.MyOnPageChangeListener;
import com.myplas.q.headlines.activity.Cate_Dialog_Activtiy;
import com.myplas.q.headlines.activity.HeadLineSearchActivity;
import com.myplas.q.headlines.adapter.HeadLineViewPagerAdapter;
import com.umeng.analytics.MobclickAgent;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * 编写： 黄双
 * 电话：15378412400
 * 邮箱：15378412400@163.com
 * 时间：2017/3/17 14:45
 */
public class Fragment_HeadLines extends Fragment implements View.OnClickListener
        , HeadLineListFragment.Myinterface
        , MyOnPageChangeListener.OnPageChangeListener {
    private Handler handler;
    private String keywords;
    private int currentItem;
    private boolean logined;
    private SharedUtils sharedUtils;
    private List<String> list1, list2;

    private View view;
    private GridView gridView;
    private EditText editText;
    private LinearLayout mLayoutTitle;
    private CustomPopupWindow popupWindow;
    private TextView textView_refresh;
    private HeadLineViewPagerAdapter mViewPagerAdapter;

    private ViewPager mViewPager;
    private XTabLayout mTabLayout;
    private ImageButton gd_imgbtn;
    private List<HeadLineListFragment> mFragments;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        if (logined) {
            initViewPager();
        }
    }

    private void initView() {
        view = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_layout_headlins, null, false);

        handler = new Handler();
        sharedUtils = SharedUtils.getSharedUtils();
        logined = sharedUtils.getBoolean(getActivity(), Constant.LOGINED);

        editText = F(R.id.find_edit);
        gd_imgbtn = F(R.id.fx_gd_imgbtn);
        mViewPager = F(R.id.headline_viewpager);
        mTabLayout = F(R.id.headline_tablayout);
        mLayoutTitle = F(R.id.headline_ll_title);

        editText.setOnClickListener(this);
        gd_imgbtn.setOnClickListener(this);
        mViewPager.addOnPageChangeListener(new MyOnPageChangeListener(this));
    }

    private void initViewPager() {
        if (mFragments == null) {
            mFragments = new ArrayList<>();
            list1 = Arrays.asList("推荐", "塑料上游", "早盘预报", "企业动态", "中晨塑说", "美金市场", "期货资讯", "装置动态", "期刊报告", "独家解读");
            list2 = Arrays.asList("", "2", "1", "9", "76", "20", "21", "11", "12", "22");
            for (int i = 0; i < list1.size(); i++) {
                mTabLayout.addTab(mTabLayout.newTab().setText(list1.get(i).toString()));
                HeadLineListFragment fragment = HeadLineListFragment.newInstance(list2.get(i), i);
                fragment.setMyinterface(this);
                mFragments.add(fragment);
            }
            mViewPagerAdapter = new HeadLineViewPagerAdapter(getChildFragmentManager(), mFragments, list1);
            mViewPager.setAdapter(mViewPagerAdapter);

            mViewPager.setCurrentItem(0);
            //将选项卡和viewpager关连起来
            mTabLayout.setupWithViewPager(mViewPager);
            //给TabLayout设置适配器
            mTabLayout.setTabsFromPagerAdapter(mViewPagerAdapter);
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return view;
    }

    @Override
    public void onPageSelected(int position) {
        currentItem = position;
        // mViewPager.setCurrentItem(position);
        //mFragments.get(position).po = position;
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.fx_gd_imgbtn:
                Intent intent = new Intent(getActivity(), Cate_Dialog_Activtiy.class);
                startActivity(intent);
                break;
            case R.id.find_edit:
                Intent in = new Intent(getActivity(), HeadLineSearchActivity.class);
                startActivity(in);
                getActivity().overridePendingTransition(R.anim.fade, R.anim.fade);
                break;
            default:
                break;
        }
    }


    public <T extends View> T F(int id) {
        return (T) view.findViewById(id);
    }

    //展示刷新后的popou
    @Override
    public void callBack(String hotSearch, String content) {
        mFragments.get(currentItem).mRefreshPopou.show(mTabLayout, content);
//            TextUtils.topTSnackbar(editText, (TextUtils.isNullOrEmpty(s)) ? (s) : ("已是最新头条信息！"));
        //editText.setHint(hotSearch.equals("") ? "大家都在搜：" + hotSearch : "大家都在搜：7000F");
    }

    @Override
    public void onResume() {
        super.onResume();
        MobclickAgent.onPageStart("MainScreen");
    }

    @Override
    public void onPause() {
        super.onPause();
        MobclickAgent.onPageEnd("MainScreen");
        if (mFragments != null
                && mFragments.get(currentItem).mRefreshPopou != null) {
            mFragments.get(currentItem).mRefreshPopou.dismiss();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    public void onLogined() {
        initViewPager();
    }
}
