package com.myplas.q.homepage;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.flyco.tablayout.SegmentTabLayout;
import com.flyco.tablayout.listener.OnTabSelectListener;
import com.myplas.q.R;
import com.myplas.q.app.fragment.BaseFragment;
import com.myplas.q.common.api.API;
import com.myplas.q.common.appcontext.Constant;
import com.myplas.q.common.listener.BaseInterface;
import com.myplas.q.common.net.ResultCallBack;
import com.myplas.q.common.utils.SharedUtils;
import com.myplas.q.common.view.CommonDialog;
import com.myplas.q.common.view.MyOnPageChangeListener;
import com.myplas.q.common.view.MyViewPager;
import com.myplas.q.homepage.activity.ContactDaliySignActivity;
import com.myplas.q.homepage.activity.Contact_Search_Activity;
import com.myplas.q.homepage.adapter.HomePageViewPagerAdapter;
import com.myplas.q.myself.login.LoginActivity;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 黄双
 * @date 2018/1/11 0011
 */

public class FragmentHomePage extends BaseFragment implements View.OnClickListener
        , MyOnPageChangeListener.OnPageChangeListener
        , ResultCallBack
        , CommonDialog.DialogShowInterface, BaseInterface {

    private View view;

    private ImageView mSign;
    private TextView mTVTab;
    private MyViewPager viewPager;
    private SegmentTabLayout mTabLayout;
    private LinearLayout mLayoutSearch;

    private ArrayList<Fragment> mFragments;
    private HomePageViewPagerAdapter mViewPagerAdapter;

    private FragmentHomePageContact contact;
    private FragmentHomePageBlackList blackList;
    private SharedUtils sharedUtils;

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.contact_sign:
                startActivity(new Intent(getActivity(), ContactDaliySignActivity.class));
                break;
            case R.id.contact_search_ll:
                startActivity(new Intent(getActivity(), Contact_Search_Activity.class));
                getActivity().overridePendingTransition(R.anim.fade, R.anim.hold);
                break;
            default:
                break;
        }
    }

    @Override
    public void onPageSelected(int position) {
        mTabLayout.setCurrentTab(position);
        if (blackList != null) {
            blackList.setUserVisible(position == 0 ? false : true);
        }
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);

        if (blackList != null) {
            blackList.setUserVisible(isVisibleToUser);
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sharedUtils = SharedUtils.getSharedUtils();
        view = View.inflate(getActivity(), R.layout.fragment_homepage_layout, null);

        mSign = F(view, R.id.contact_sign);
        viewPager = F(view, R.id.homepage_viewpager);
        mTabLayout = F(view, R.id.homepage_tablayout);
        mLayoutSearch = F(view, R.id.contact_search_ll);

        mSign.setOnClickListener(this);
        mLayoutSearch.setOnClickListener(this);
        viewPager.setEnableScroll(true);
        viewPager.addOnPageChangeListener(new MyOnPageChangeListener(this));

        initViewPager();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return view;
    }

    /**
     * 初始化viewpager相关
     */
    private void initViewPager() {
        mFragments = new ArrayList<>();
        String[] title = new String[]{"塑料圈通讯录", "塑料黑名单"};
        mTabLayout.setTabData(title);

        contact = new FragmentHomePageContact();
        contact.setOnLoadDataSuccessListener(this);
        blackList = new FragmentHomePageBlackList();
        mFragments.add(contact);
        mFragments.add(blackList);

        mViewPagerAdapter = new HomePageViewPagerAdapter(getChildFragmentManager(), mFragments, title);
        viewPager.setAdapter(mViewPagerAdapter);

        viewPager.setCurrentItem(0);
        mTabLayout.setCurrentTab(0);

        mTabLayout.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
                viewPager.setCurrentItem(position);
                if (blackList != null) {
                    blackList.setUserVisible(position == 0 ? false : true);
                }
            }

            @Override
            public void onTabReselect(int position) {

            }
        });
    }

    /**
     * 检查登录状态
     */
    public void validations() {
        getAsyn(getActivity(), API.VALIDUSERTOKEN, null, this, 10, false);
    }

    @Override
    public void callBack(Object object, int type) {

    }

    @Override
    public void failCallBack(int type, String message, int httpCode) {
        try {
            JSONObject jsonObject = new JSONObject(message);
            String err = jsonObject.getString("code");

            if (httpCode == 401) {
                sharedUtils.setData(getActivity(), Constant.TOKEN, "");
                sharedUtils.setData(getActivity(), Constant.USERID, "");
                sharedUtils.setBooloean(getActivity(), Constant.LOGINED, false);
                sharedUtils.setData(getActivity(), Constant.POINTSINFO, jsonObject.getString("message"));
            }
        } catch (Exception e) {

        }
    }

    @Override
    public void dialogClick(int type) {
        if (type == 4) {
            Intent intent = new Intent(getActivity(), LoginActivity.class);
            getActivity().startActivity(intent);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
//        validations();
//        boolean isLogin = SharedUtils.getSharedUtils().getBoolean(getContext(), Constant.LOGINED);
    }

    @Override
    public void complete(int position, String msg) {
        mTabLayout.setTabData(new String[]{"塑料圈通讯录(" + msg + "人)", "塑料黑名单"});
    }

    @Override
    public void dataBack(Fragment fragment, List agrs) {

    }
}
