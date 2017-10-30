package com.myplas.q.supdem;

import android.app.Dialog;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.androidkun.xtablayout.XTabLayout;
import com.huangbryant.hindicator.HIndicatorAdapter;
import com.huangbryant.hindicator.HIndicatorBuilder;
import com.huangbryant.hindicator.HIndicatorDialog;
import com.huangbryant.hindicator.OnDismissListener;
import com.myplas.q.R;
import com.myplas.q.common.appcontext.Constant;
import com.myplas.q.common.utils.ScreenUtils;
import com.myplas.q.common.utils.SharedUtils;
import com.myplas.q.release.MyOnPageChangeListener;
import com.myplas.q.supdem.beans.ConfigData;
import com.myplas.q.supdem.activity.SupDem_Search_Activity;
import com.myplas.q.supdem.adapter.SupDem_ViewPager_Adapter;
import com.umeng.analytics.MobclickAgent;

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
public class Fragment_SupplyDemand extends Fragment implements View.OnClickListener
        , Fragment_SupDem_All.RefreshPopouInterface
        , MyOnPageChangeListener.OnPageChangeListener {

    private View view;
    private TextView tvType;
    private EditText editText;
    private LinearLayout mLayout;

    private boolean logined;
    public int currentItem;
    private List<String> mList;
    public String user_id, sType;
    private List<String> mListTitle;
    private SharedUtils mSharedUtils;
    private List<Fragment> mFragments;
    private Map<String, Integer> mMap;

    private ViewPager mViewPager;
    private XTabLayout mTabLayout;
    private HIndicatorDialog dialog;

    private Fragment_SupDem_All mFragmentAll;
    private SupDem_ViewPager_Adapter mViewPagerAdapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        initViewPager();
    }

    private void initView() {
        sType = "0";
        mMap = new HashMap<>();
        mSharedUtils = SharedUtils.getSharedUtils();
        mList = Arrays.asList("全部", "供给", "求购");
        logined = mSharedUtils.getBoolean(getActivity(), Constant.LOGINED);
        view = View.inflate(getActivity(), R.layout.layout_supdem_fragment, null);

        tvType = F(R.id.supplydemand_btn);
        mLayout = F(R.id.supdem_titlebar_ll);
        editText = F(R.id.supplydemand_edit);
        mTabLayout = F(R.id.supdem_tablayout);
        mViewPager = F(R.id.supdem_viewpager);

        tvType.setOnClickListener(this);
        editText.setOnClickListener(this);
        mViewPager.addOnPageChangeListener(new MyOnPageChangeListener(this));
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return view;
    }

    private void initViewPager() {
        if (mFragments == null) {
            mFragments = new ArrayList<>();
            mListTitle = Arrays.asList("全部", "智能推荐", "我的关注", "我的供求");
            for (int i = 0; i < mListTitle.size(); i++) {
                mTabLayout.addTab(mTabLayout.newTab().setText(mListTitle.get(i).toString()));
                if (i == 0) {
                    mFragmentAll = new Fragment_SupDem_All();
                    mFragments.add(mFragmentAll);
                    mFragmentAll.setRefreshPopouInterface(this);
                } else {
                    Fragment_SupDem_Other fragmentSupDemOther = new Fragment_SupDem_Other();
                    fragmentSupDemOther.position = i;
                    mFragments.add(fragmentSupDemOther);
                }
            }
            mViewPagerAdapter = new SupDem_ViewPager_Adapter(getChildFragmentManager(), mFragments, mListTitle);
            mViewPager.setAdapter(mViewPagerAdapter);

            mViewPager.setCurrentItem(0);
            //将选项卡和viewpager关连起来
            mTabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
            mTabLayout.setxTabDisplayNum(4);
            mTabLayout.setupWithViewPager(mViewPager);
            //给TabLayout设置适配器
            mTabLayout.setTabsFromPagerAdapter(mViewPagerAdapter);
        }
    }


    public <T extends View> T F(int id) {
        return (T) view.findViewById(id);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.supplydemand_edit:
                Intent intent = new Intent(getActivity(), SupDem_Search_Activity.class);
                startActivity(intent);
                getActivity().overridePendingTransition(R.anim.fade, R.anim.fade);
                break;
            case R.id.supplydemand_btn:
                dialog = new HIndicatorBuilder(getActivity())
                        .width(300)
                        .height(-1)
                        .ArrowDirection(HIndicatorBuilder.TOP)
                        .bgColor(Color.parseColor("#ffffff"))
                        .gravity(HIndicatorBuilder.GRAVITY_LEFT)
                        .radius(10)
                        .arrowWidth(30)
                        .ArrowRectage(0.2f)
                        .layoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false))
                        .dimEnabled(true)
                        .dimAmount(0.4f)
                        .adapter(new Supdem_Dialog_Adapter())
                        .create();
                dialog.setCanceledOnTouchOutside(true);
                dialog.show(tvType);
                tvType.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.icon_supdem_xiala_up, 0);
                dialog.setOnDismissListener(new OnDismissListener() {
                    @Override
                    public void onDismiss(Dialog dialog) {
                        tvType.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.icon_supdemxiala, 0);
                    }
                });
                break;
            default:
                break;
        }
    }

    @Override
    public void onPageSelected(int position) {
        currentItem = position;
        ConfigData.setCurrentItem(position);
        getChirldData(position, false);
    }

    private void getChirldData(int position, boolean isShowLoading) {
        try {
            if (mFragments != null) {
                if (position == 0) {
                    if (mFragmentAll != null) {
                        mFragmentAll.page = 1;
                        mFragmentAll.type = sType;
                        mFragmentAll.getNetData("1", isShowLoading);
                    }
                } else {
                    Fragment_SupDem_Other fragmentO = ((Fragment_SupDem_Other) mFragments.get(position));
                    if (fragmentO != null) {
                        fragmentO.page = 1;
                        fragmentO.type = sType;
                        fragmentO.getNetData("1", isShowLoading);
                    }
                }
            }
        } catch (Exception e) {
        }
    }

    //从其他页面跳转回调的刷新。。。
    public void refreshData(int type) {
        try {
            if (type == 1 && mFragments != null) {
                if (mTabLayout != null && mViewPager != null) {
                    mTabLayout.getTabAt(0).select();
                    mViewPager.setCurrentItem(0);
                }
                if (mFragmentAll != null) {
                    mFragmentAll.page = 1;
                    mFragmentAll.type = "0";
                    mFragmentAll.getNetData("1", false);
                }
            }
        } catch (Exception e) {
        }
    }

    //展示刷新多少数据的popou
    @Override
    public void showRefreshPopou(String hotSearch, String content) {
        mFragmentAll.refreshPopou.show(mTabLayout, content);
//            TextUtils.topTSnackbar(editText, (TextUtils.isNullOrEmpty(s)) ? (s) : ("已是最新头条信息！"));
        //editText.setHint(hotSearch.equals("") ? "大家都在搜：" + hotSearch : "大家都在搜：7000F");
    }


    public class Supdem_Dialog_Adapter extends HIndicatorAdapter {

        @Override
        public void onBindView(BaseViewHolder holder, int position) {
            TextView tv = holder.getView(R.id.item_tv);
            ImageView iv = holder.getView(R.id.item_iv);

            tv.setText(mList.get(position));
            tv.setGravity(Gravity.LEFT);
            tv.setTextColor(Color.BLACK);

            boolean checked = mMap.get("sd") != null && position == mMap.get("sd")
                    || mMap.get("sd") == null && position == 0;
            if (checked) {
                iv.setImageResource(R.drawable.icon_check);
            }

            holder.setVisibility(R.id.item_line, position == mList.size() - 1
                    ? BaseViewHolder.GONE
                    : BaseViewHolder.VISIBLE);
        }

        @Override
        public int getLayoutID(int position) {
            return R.layout.hindicator_item_layout;
        }

        @Override
        public boolean clickable() {
            return true;
        }

        @Override
        public void onItemClick(View v, int position) {
            mMap.put("sd", position);
            sType = ConfigData.getType(position);
            getChirldData(currentItem, true);

            dialog.dismiss();
            tvType.setText(mList.get(position));
            tvType.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.icon_supdemxiala, 0);
        }

        @Override
        public int getItemCount() {
            return mList.size();
        }
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
        if (mFragmentAll.refreshPopou != null) {
            mFragmentAll.refreshPopou.dismiss();
        }
    }

}

