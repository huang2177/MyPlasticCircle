package com.myplas.q.headlines.fragment;

import android.app.ActionBar;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.myplas.q.R;
import com.myplas.q.common.api.API;
import com.myplas.q.common.netresquset.ResultCallBack;
import com.myplas.q.common.utils.NetUtils;
import com.myplas.q.common.utils.SharedUtils;
import com.myplas.q.common.utils.TextUtils;
import com.myplas.q.common.view.CustomPopupWindow;
import com.myplas.q.common.view.XListView;
import com.myplas.q.guide.activity.BaseActivity;
import com.myplas.q.headlines.activity.Cate_Dialog_Activtiy;
import com.myplas.q.headlines.activity.Head_Lines_DetailActivity;
import com.myplas.q.headlines.adapter.CateListAdapter;
import com.myplas.q.headlines.adapter.HeadLineViewPagerAdapter;
import com.myplas.q.headlines.adapter.TTAdapter;
import com.myplas.q.headlines.bean.CateListBean;
import com.myplas.q.headlines.bean.ItemBean;
import com.myplas.q.headlines.bean.SubcribleBean;
import com.umeng.analytics.MobclickAgent;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 编写： 黄双
 * 电话：15378412400
 * 邮箱：15378412400@163.com
 * 时间：2017/3/17 14:45
 */
public class Fragment_HeadLines extends Fragment implements View.OnClickListener, HeadLineListFragment.Myinterface {
    private Handler handler;
    private String keywords;
    private SharedUtils sharedUtils;
    private List<String> list1, list2;
    private int currentItem, position;

    private HeadLineViewPagerAdapter mViewPagerAdapter;
    private View view;
    private GridView gridView;
    private EditText editText;
    private CustomPopupWindow popupWindow;
    private TextView search_src_text, textView_refresh;

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

        gd_imgbtn.setOnClickListener(this);
        search_src_text.setOnClickListener(this);

        initViewPager();

        //edittext 回车监听
        editText.setOnEditorActionListener(new TextView.OnEditorActionListener() {

            @Override
            public boolean onEditorAction(TextView arg0, int arg1, KeyEvent arg2) {
                if (arg1 == EditorInfo.IME_ACTION_SEARCH | (arg2 != null && arg2.getAction() == KeyEvent.ACTION_DOWN)) {
                    searchData(editText.getText().toString());
                    return true;
                }
                return false;
            }
        });
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                currentItem = position;
                Fragment_HeadLines.this.position = 0;
                mViewPager.setCurrentItem(position);
                mFragments.get(position).po = position;
                mFragments.get(position).title = list1.get(position);
                if (position == 0) {
                    mFragments.get(0).get_Subscribe(1, "", "2", true);
                } else {
                    mFragments.get(position).get_CateList(1, list2.get(position), true);
                }
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
        }
    }

    public void searchData(String keywords) {
        if (TextUtils.isNullOrEmpty(keywords)) {
            mFragments.get(position).po = -1;
            mFragments.get(position).get_Subscribe(1, keywords, "1", true);
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
    public void callBack(String s) {
        if (TextUtils.isNullOrEmpty(s)) {
            View view = View.inflate(getActivity(), R.layout.layout_refresh_popou, null);
            textView_refresh = (TextView) view.findViewById(R.id.text_refresh_fragement);
            popupWindow = new CustomPopupWindow(view, ActionBar.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.MATCH_PARENT);
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
