package com.myplas.q.myself.login;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.LinearLayout;

import com.myplas.q.R;
import com.myplas.q.common.utils.StatusUtils;
import com.myplas.q.common.view.stepview.HorizontalStepView;
import com.myplas.q.common.view.MyViewPager;
import com.myplas.q.common.view.stepview.StepBean;
import com.myplas.q.app.activity.BaseActivity;
import com.myplas.q.common.view.MyOnPageChangeListener;

import java.util.ArrayList;
import java.util.List;

/**
 * 编写： 黄双
 * 电话：15378412400
 * 邮箱：15378412400@163.com
 * 时间：2017/3/24 11:41
 */
public class RegisterActivity extends BaseActivity implements View.OnClickListener
        , BaseInterface
        , MyOnPageChangeListener.OnPageChangeListener {

    private LinearLayout mRootView;
    private MyViewPager mViewPager;
    private HorizontalStepView mStepView;

    private FragmentRegister1 mFmRegister1;
    private FragmentRegister2 mFmRegister2;
    private FragmentRegister3 mFmRegister3;

    private int currentItem;
    private List<Fragment> FragmentList;
    private List<StepBean> stepsBeanList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StatusUtils.setStatusBar(this, false, false);
        StatusUtils.setStatusTextColor(true, this);
        setContentView(R.layout.layout_regester_activity);

        initTileBar();
        setTitle("注册塑料圈");
        setLeftIVResId(R.drawable.btn_back_black);
        setTitleBarBackground(R.color.color_white);
        setTitleBarTextColor(R.color.color_transparent);
        initView();
        initStepView(getStepBeans());
    }

    public void initView() {
        mRootView = F(R.id.rootview);
        mViewPager = F(R.id.container);
        mStepView = (HorizontalStepView) findViewById(R.id.stepview);

        mIVLeft.setOnClickListener(this);
        mRootView.setOnClickListener(this);
        mViewPager.addOnPageChangeListener(new MyOnPageChangeListener(this));

        initViewPager();
    }

    private void initViewPager() {
        FragmentList = new ArrayList<>();

        mFmRegister1 = FragmentRegister1.newInstance();
        mFmRegister2 = FragmentRegister2.newInstance();
        mFmRegister3 = new FragmentRegister3();
        mFmRegister1.mBaseInterface = this;
        mFmRegister2.mBaseInterface = this;

        FragmentList.add(mFmRegister1);
        FragmentList.add(mFmRegister2);
        FragmentList.add(mFmRegister3);

        ViewPager_Adapter adapter = new ViewPager_Adapter(getSupportFragmentManager(), FragmentList);
        mViewPager.setAdapter(adapter);
        mViewPager.setCurrentItem(0);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.rootview) {
            if (v != null && v.getWindowToken() != null) {
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
            }
        } else {
            switch (currentItem) {
                case 0:
                    finish();
                    break;
                case 1:
                    mViewPager.setCurrentItem(0);
                    break;
                default:
                    break;
            }
        }
    }

    private void initStepView(List<StepBean> stepsBeanList) {
        mStepView.setStepViewTexts(stepsBeanList)  //总步骤
                .setTextSize(12)//set textSize
                .setStepsViewIndicatorCompletedLineColor(ContextCompat.getColor(this, R.color.color_red))//设置StepsViewIndicator完成线的颜色
                .setStepsViewIndicatorUnCompletedLineColor(ContextCompat.getColor(this, R.color.color_gray))//设置StepsViewIndicator未完成线的颜色
                .setStepViewComplectedTextColor(ContextCompat.getColor(this, R.color.color_gray))//设置StepsView text完成线的颜色
                .setStepViewUnComplectedTextColor(ContextCompat.getColor(this, R.color.color_gray))//设置StepsView text未完成线的颜色
                .setStepsViewIndicatorCompleteIcon(ContextCompat.getDrawable(this, R.drawable.complted))//设置StepsViewIndicator CompleteIcon
                .setStepsViewIndicatorDefaultIcon(ContextCompat.getDrawable(this, R.drawable.default_icon))//设置StepsViewIndicator DefaultIcon
                .setStepsViewIndicatorAttentionIcon(ContextCompat.getDrawable(this, R.drawable.attention));//设置StepsViewIndicator AttentionIcon
    }

    private List<StepBean> getStepBeans() {
        stepsBeanList = new ArrayList<>();
        StepBean stepBean0 = new StepBean("验证手机号码填写密码", 0);
        StepBean stepBean1 = new StepBean("填写个人信息", -1);
        StepBean stepBean2 = new StepBean("注册成功", -1);

        stepsBeanList.clear();
        stepsBeanList.add(stepBean0);
        stepsBeanList.add(stepBean1);
        stepsBeanList.add(stepBean2);

        return stepsBeanList;
    }

    @Override
    public void complete(int position) {
        try {
            mViewPager.setCurrentItem(position);
            setLeftIVVisibility(position == 2 ? View.GONE : View.VISIBLE);
            switch (currentItem) {
                case 0:
                    stepsBeanList.get(0).setState(0);
                    stepsBeanList.get(1).setState(-1);
                    stepsBeanList.get(2).setState(-1);
                    initStepView(stepsBeanList);
                    break;
                case 1:
                    stepsBeanList.get(0).setState(1);
                    stepsBeanList.get(1).setState(0);
                    stepsBeanList.get(2).setState(-1);
                    initStepView(stepsBeanList);
                    break;
                case 2:
                    stepsBeanList.get(0).setState(1);
                    stepsBeanList.get(1).setState(1);
                    stepsBeanList.get(2).setState(1);
                    initStepView(stepsBeanList);
                    break;
                default:
                    break;
            }
        } catch (Exception e) {

        }
    }

    @Override
    public void dataBack(Fragment fragment, List agrs) {
        if (fragment instanceof FragmentRegister1) {
            mFmRegister2.setData(agrs);
        } else {
            mFmRegister3.showImg();
            mFmRegister3.setData(agrs);
        }
    }

    @Override
    public void onPageSelected(int position) {
        currentItem = position;
    }
}
