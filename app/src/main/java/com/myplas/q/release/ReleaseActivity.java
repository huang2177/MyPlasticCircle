package com.myplas.q.release;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.androidkun.xtablayout.XTabLayout;
import com.google.gson.Gson;
import com.myplas.q.R;
import com.myplas.q.guide.activity.BaseActivity;
import com.myplas.q.common.netresquset.ResultCallBack;
import com.myplas.q.common.utils.SharedUtils;
import com.myplas.q.release.adapter.ReleaseViewPagerAdapter;
import com.myplas.q.release.bean.SecondPurBean;
import com.umeng.analytics.MobclickAgent;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 编写： 黄双
 * 电话：15378412400
 * 邮箱：15378412400@163.com
 * 时间：2017/3/21 10:26
 */
public class ReleaseActivity extends BaseActivity implements View.OnClickListener
        , ResultCallBack
        , MyOnPageChangeListener.OnPageChangeListener {
    private Button button;
    private ViewPager viewPager;
    private XTabLayout tabLayout;
    private List<String> listTitle;
    private List<Fragment> fragmentList;

    private int currentItem;
    private SharedUtils sharedUtils;
    private QuicklyFragment mQ_Fragment;
    private StandardFragment mS_Fragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_release_supdem_activity);

        initView();
        initViewPager();
    }

    public void initView() {
        initTileBar();
        setTitle("发布信息");
        setRightTVText("取消");
        listTitle = new ArrayList<>();
        fragmentList = new ArrayList<>();
        sharedUtils = SharedUtils.getSharedUtils();

        button = F(R.id.release_btn);
        tabLayout = F(R.id.release_tablayout);
        viewPager = F(R.id.release_viewpager);

        button.setOnClickListener(this);
        mTVLeft.setOnClickListener(this);
        mLayoutBack.setOnClickListener(this);
        viewPager.addOnPageChangeListener(new MyOnPageChangeListener(this));

//        id = getIntent().getStringExtra("id");
//        type = getIntent().getStringExtra("type");
//        title.setText(this.type.equals("2") ? ("发布供给") : ("发布求购"));
//        getSecondPub();
//        editText.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//
//            }
//
//            @Override
//            public void onTextChanged(CharSequence s, int start, int before, int count) {
//                String s1 = s.toString();
//                if (s.length() >= 100) {
//                    TextUtils.Toast(ReleaseActivity.this, "输入的字符已达上限！");
//                }
//            }
//
//            @Override
//            public void afterTextChanged(Editable s) {
//
//            }
//        });

    }

    public void initViewPager() {
        listTitle = Arrays.asList("标准发布", "快速发布");
        tabLayout.addTab(tabLayout.newTab().setText("标准发布"));
        tabLayout.addTab(tabLayout.newTab().setText("快速发布"));
        mS_Fragment = new StandardFragment();
        fragmentList.add(mS_Fragment);
        mQ_Fragment = new QuicklyFragment();
        fragmentList.add(mQ_Fragment);
        ReleaseViewPagerAdapter adapter = new ReleaseViewPagerAdapter(getSupportFragmentManager(), fragmentList, listTitle);
        viewPager.setAdapter(adapter);

        viewPager.setCurrentItem(0);
        //将选项卡和viewpager关连起来
        tabLayout.setupWithViewPager(viewPager);
        //给TabLayout设置适配器
        tabLayout.setTabsFromPagerAdapter(adapter);
    }

    @Override
    public void onPageSelected(int position) {
        currentItem = position;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.titlebar_text_right:
                break;
            case R.id.titlebar_img_back:
                onBackPressed();
                break;
            case R.id.release_btn:
                Log.e("------", currentItem + "---");
                if (currentItem == 0) {
                    mS_Fragment.pub();
                } else {
                    mQ_Fragment.pub();
                }
                break;
        }
    }

    @Override
    public void callBack(Object object, int type) {
        try {
            Gson gson = new Gson();
            if (type == 1) {
                SecondPurBean secondPurBean = gson.fromJson(object.toString(), SecondPurBean.class);
//                f_type = secondPurBean.getData().getF_type();
//                this.type = secondPurBean.getData().getType();
//                editText_jz_jg.setText(secondPurBean.getData().getUnit_price());
//                editText_jz_ph.setText(secondPurBean.getData().getModel());
//                editText_jz_chj.setText(secondPurBean.getData().getF_name());
//                editText_jz_jhd.setText(secondPurBean.getData().getStore_house());
//                editText.setText(secondPurBean.getData().getContent());
//                editText.setSelection(editText.getText().length());
//                editText_jz_jg.setSelection(editText_jz_jg.getText().length());
//                editText_jz_ph.setSelection(editText_jz_ph.getText().length());
//                editText_jz_chj.setSelection(editText_jz_chj.getText().length());
//                editText_jz_jhd.setSelection(editText_jz_jhd.getText().length());
//                if (f_type == 2) {
//                    mode = "1";
////                    changeTextColor_Speed();
//                } else {
//                    mode = "2";
////                    changeTextColor_Right();
//                }
            }

        } catch (Exception e) {
        }
    }

    @Override
    public void failCallBack(int type) {

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
