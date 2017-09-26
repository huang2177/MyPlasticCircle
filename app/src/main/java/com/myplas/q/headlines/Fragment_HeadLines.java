package com.myplas.q.headlines;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
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

import com.androidkun.xtablayout.XTabLayout;
import com.myplas.q.R;
import com.myplas.q.common.appcontext.Constant;
import com.myplas.q.common.utils.SharedUtils;
import com.myplas.q.common.utils.TextUtils;
import com.myplas.q.common.view.CustomPopupWindow;
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
public class Fragment_HeadLines extends Fragment implements View.OnClickListener, HeadLineListFragment.Myinterface {
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
    private TextView search_src_text, textView_refresh;
    private HeadLineViewPagerAdapter mViewPagerAdapter;

    private ViewPager mViewPager;
    private XTabLayout mTabLayout;
    private ImageButton gd_imgbtn;
    private List<HeadLineListFragment> mFragments;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        view = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_layout_headlins, null, false);

        handler = new Handler();
        sharedUtils = SharedUtils.getSharedUtils();
        logined = sharedUtils.getBoolean(getActivity(), Constant.LOGINED);

        editText = F(R.id.find_edit);
        gd_imgbtn = F(R.id.fx_gd_imgbtn);
        mViewPager = F(R.id.headline_viewpager);
        mTabLayout = F(R.id.headline_tablayout);
        mLayoutTitle = F(R.id.headline_ll_title);
        search_src_text = F(R.id.search_src_text);

        editText.setOnClickListener(this);
        gd_imgbtn.setOnClickListener(this);
        search_src_text.setOnClickListener(this);

        if (logined) {
            initViewPager();
        }

        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                currentItem = position;
                mViewPager.setCurrentItem(position);
                mFragments.get(position).po = position;
//                mViewDivider.setVisibility(position == 0 ? View.GONE : View.VISIBLE);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
    }

    public void initViewPager() {
        if (mFragments == null) {
            mFragments = new ArrayList<>();
            list1 = Arrays.asList("推荐", "塑料上游", "早盘预报", "企业动态", "中晨塑说", "美金市场", "期货资讯", "装置动态", "期刊报告", "独家解读");
            list2 = Arrays.asList("", "2", "1", "9", "76", "20", "21", "11", "12", "22");
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
                getActivity().overridePendingTransition(R.anim.fade, R.anim.fade);
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
    public void callBack(String hotSearch, String content, boolean isRefresh) {
        if (isRefresh) {
            mFragments.get(currentItem).isRefresh = false;
            if (popupWindow == null) {
                View view = View.inflate(getActivity(), R.layout.layout_refresh_popou, null);
                textView_refresh = (TextView) view.findViewById(R.id.text_refresh_fragement);
                popupWindow = new CustomPopupWindow(view, ActionBar.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.MATCH_PARENT);
                popupWindow.setFocusable(true);
                popupWindow.setOutsideTouchable(true);
                popupWindow.setAnimationStyle(R.style.my_anim_popou);
            }
            textView_refresh.setText((TextUtils.isNullOrEmpty(content))
                    ? (content)
                    : ("已是最新头条信息！"));
            showPopou(popupWindow);
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    popupWindow.dismiss();
                }
            }, 1500);
//            TextUtils.topTSnackbar(editText, (TextUtils.isNullOrEmpty(s)) ? (s) : ("已是最新头条信息！"));
        }
        //editText.setHint(hotSearch.equals("") ? "大家都在搜：" + hotSearch : "大家都在搜：7000F");
    }

    public void onResume() {
        super.onResume();
        MobclickAgent.onPageStart("MainScreen");
        boolean isLogined = sharedUtils.getBoolean(getActivity(), Constant.IS_LOGINED_H);

        if (isLogined) {//防止第一次登陆以后没有数据
            initViewPager();
            sharedUtils.setBooloean(getActivity(), Constant.IS_LOGINED_H, false);
        }
    }

    public void onPause() {
        super.onPause();
        MobclickAgent.onPageEnd("MainScreen");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
