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
import com.myplas.q.common.api.API;
import com.myplas.q.common.appcontext.ActivityManager;
import com.myplas.q.common.view.CommonDialog;
import com.myplas.q.guide.activity.BaseActivity;
import com.myplas.q.common.netresquset.ResultCallBack;
import com.myplas.q.common.utils.SharedUtils;
import com.myplas.q.release.adapter.ReleaseViewPagerAdapter;
import com.myplas.q.release.bean.SecondPurBean;
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

    private CommonDialog mCommonDialog;
    private QuicklyFragment mQ_Fragment;
    private StandardFragment mS_Fragment;

    private String type, id;
    private SharedUtils sharedUtils;
    private int currentItem, position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_release_supdem_activity);
        ActivityManager.addActivity(this);

        initView();
        initViewPager(position);
    }

    public void initView() {
        initTileBar();
        setTitle("发布信息");
        setRightTVText("取消");
        setLeftIVVisibility(View.GONE);
        listTitle = new ArrayList<>();
        fragmentList = new ArrayList<>();
        id = getIntent().getStringExtra("id");
        type = getIntent().getStringExtra("type");
        sharedUtils = SharedUtils.getSharedUtils();
        position = id == null ? 0 : (type.equals("1") ? 0 : 1);


        button = F(R.id.release_btn);
        tabLayout = F(R.id.release_tablayout);
        viewPager = F(R.id.release_viewpager);

        button.setOnClickListener(this);
        mTVRight.setOnClickListener(this);
        mLayoutBack.setOnClickListener(this);
        viewPager.addOnPageChangeListener(new MyOnPageChangeListener(this));

        if (id != null) {
            secondPub();
        }
    }

    public void initViewPager(int position) {
        listTitle = Arrays.asList("标准发布", "快速发布");
        tabLayout.addTab(tabLayout.newTab().setText("标准发布"));
        tabLayout.addTab(tabLayout.newTab().setText("快速发布"));
        mS_Fragment = new StandardFragment();
        fragmentList.add(mS_Fragment);
        mQ_Fragment = new QuicklyFragment();
        fragmentList.add(mQ_Fragment);
        ReleaseViewPagerAdapter adapter = new ReleaseViewPagerAdapter(getSupportFragmentManager(), fragmentList, listTitle);
        viewPager.setAdapter(adapter);

        viewPager.setCurrentItem(position);
        tabLayout.getTabAt(position).select();
        //将选项卡和viewpager关连起来
        tabLayout.setupWithViewPager(viewPager);
        //给TabLayout设置适配器
        tabLayout.setTabsFromPagerAdapter(adapter);
    }

    public void secondPub() {
        Map<String, String> map = new HashMap<String, String>();
        map.put("id", id);
        postAsyn(this, API.BASEURL + API.SECOND_PUB, map, this, 1);
    }

    @Override
    public void onPageSelected(int position) {
        currentItem = position;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.titlebar_text_right:
                if (!mS_Fragment.isInputContent(1) && !mQ_Fragment.isInPutContent(1)) {
                    onBackPressed();
                } else {
                    mCommonDialog = new CommonDialog();
                    mCommonDialog.showDialog(this, "确定放弃编辑?", 3, null);
                }
                break;
            case R.id.titlebar_img_back:
                onBackPressed();
                break;
            case R.id.release_btn:
                if (currentItem == 0) {
                    mS_Fragment.pub();
                } else {
                    mQ_Fragment.showDialog();
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
            }

        } catch (Exception e) {
        }
    }

    @Override
    public void failCallBack(int type) {

    }

}
