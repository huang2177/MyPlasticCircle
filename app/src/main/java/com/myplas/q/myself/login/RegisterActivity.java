package com.myplas.q.myself.login;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.text.Html;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.myplas.q.R;
import com.myplas.q.common.utils.StatusUtils;
import com.myplas.q.common.view.HorizontalStepView;
import com.myplas.q.common.view.StepBean;
import com.myplas.q.guide.activity.BaseActivity;
import com.myplas.q.common.netresquset.ResultCallBack;
import com.myplas.q.common.utils.SharedUtils;
import com.myplas.q.common.utils.TextUtils;
import com.myplas.q.common.api.API;
import com.umeng.analytics.MobclickAgent;

import org.json.JSONObject;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 编写： 黄双
 * 电话：15378412400
 * 邮箱：15378412400@163.com
 * 时间：2017/3/24 11:41
 */
public class RegisterActivity extends BaseActivity implements View.OnClickListener {

    private LinearLayout mRootView;
    private FrameLayout frameLayout;
    private HorizontalStepView mStepView;

    private FragmentRegister1 mFmRegister1;
    private FragmentRegister2 mFmRegister2;
    private FragmentRegister3 mFmRegister3;

    private FragmentTransaction mTransaction;

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
        initStepView();
    }

    public void initView() {
        mRootView = F(R.id.rootview);
        frameLayout = F(R.id.container);
        mFmRegister1 = new FragmentRegister1();
        mFmRegister2 = new FragmentRegister2();
        mFmRegister3 = new FragmentRegister3();

        mRootView.setOnClickListener(this);

        //开启事务，fragment的控制是由事务来实现的
        mTransaction = getSupportFragmentManager().beginTransaction();
        showFragment(mFmRegister3);
    }

    @Override
    public void onClick(View v) {
        if (v != null && v.getWindowToken() != null) {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
        }
    }

    private void showFragment(Fragment fragment) {
        //第一种方式（add），初始化fragment并添加到事务中，如果为null就new一个
        mTransaction.add(R.id.container, fragment);
        //隐藏所有fragment
        hideFragment(mTransaction);
        //显示需要显示的fragment
        mTransaction.show(fragment);
        //提交事务
        mTransaction.commit();
    }

    private void hideFragment(FragmentTransaction mTransaction) {
        if (mFmRegister1 != null) {
            mTransaction.hide(mFmRegister1);
        }
        if (mFmRegister2 != null) {
            mTransaction.hide(mFmRegister2);
        }
        if (mFmRegister3 != null) {
            mTransaction.hide(mFmRegister3);
        }
    }

    private void initStepView() {
        HorizontalStepView setpview = (HorizontalStepView) findViewById(R.id.stepview);
        List<StepBean> stepsBeanList = new ArrayList<>();
        StepBean stepBean0 = new StepBean("验证手机号码填写密码", 0);
        StepBean stepBean1 = new StepBean("填写个人信息", -1);
        StepBean stepBean2 = new StepBean("注册成功", -1);
        stepsBeanList.add(stepBean0);
        stepsBeanList.add(stepBean1);
        stepsBeanList.add(stepBean2);


        setpview.setStepViewTexts(stepsBeanList)//总步骤
                .setTextSize(12)//set textSize
                .setStepsViewIndicatorCompletedLineColor(ContextCompat.getColor(this, R.color.color_red))//设置StepsViewIndicator完成线的颜色
                .setStepsViewIndicatorUnCompletedLineColor(ContextCompat.getColor(this, R.color.color_gray))//设置StepsViewIndicator未完成线的颜色
                .setStepViewComplectedTextColor(ContextCompat.getColor(this, R.color.color_red))//设置StepsView text完成线的颜色
                .setStepViewUnComplectedTextColor(ContextCompat.getColor(this, R.color.color_gray))//设置StepsView text未完成线的颜色
                .setStepsViewIndicatorCompleteIcon(ContextCompat.getDrawable(this, R.drawable.complted))//设置StepsViewIndicator CompleteIcon
                .setStepsViewIndicatorDefaultIcon(ContextCompat.getDrawable(this, R.drawable.default_icon))//设置StepsViewIndicator DefaultIcon
                .setStepsViewIndicatorAttentionIcon(ContextCompat.getDrawable(this, R.drawable.attention));//设置StepsViewIndicator AttentionIcon
    }
}
