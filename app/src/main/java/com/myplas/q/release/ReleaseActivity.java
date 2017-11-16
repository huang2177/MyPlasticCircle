package com.myplas.q.release;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.Button;

import com.androidkun.xtablayout.XTabLayout;
import com.myplas.q.R;
import com.myplas.q.common.appcontext.ActivityManager;
import com.myplas.q.common.view.CommonDialog;
import com.myplas.q.common.view.MyOnPageChangeListener;
import com.myplas.q.guide.activity.BaseActivity;
import com.myplas.q.common.utils.SharedUtils;
import com.myplas.q.release.adapter.ReleaseViewPagerAdapter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 电话：15378412400
 * 邮箱：15378412400@163.com
 * 时间：2017/3/21 10:26
 * @author 黄双
 */
public class ReleaseActivity extends BaseActivity implements View.OnClickListener
        , CommonDialog.DialogShowInterface
        , MyOnPageChangeListener.OnPageChangeListener {
    private Button button;
    private ViewPager viewPager;
    private XTabLayout tabLayout;
    private List<String> listTitle;
    private List<Fragment> fragmentList;

    private CommonDialog mCommonDialog;
    private QuicklyFragment mQ_Fragment;
    private StandardFragment mS_Fragment;

    private int currentItem;
    private SharedUtils sharedUtils;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_release_supdem_activity);
        ActivityManager.addActivity(this);

        initView();
        initViewPager();
    }

    public void initView() {
        initTileBar();
        setTitle("发布信息");
        setRightTVText("取消");
        setLeftIVVisibility(View.GONE);
        listTitle = new ArrayList<>();
        fragmentList = new ArrayList<>();
        sharedUtils = SharedUtils.getSharedUtils();


        button = F(R.id.release_btn);
        tabLayout = F(R.id.release_tablayout);
        viewPager = F(R.id.release_viewpager);

        button.setOnClickListener(this);
        mTVRight.setOnClickListener(this);
        mLayoutBack.setOnClickListener(this);
        viewPager.addOnPageChangeListener(new MyOnPageChangeListener(this));
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
                if (!mS_Fragment.isInputContent(1) && !mQ_Fragment.isInPutContent(1)) {
                    onBackPressed();
                } else {
                    mCommonDialog = new CommonDialog();
                    mCommonDialog.showDialog(this, "确定放弃编辑?", 1, this);
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
            default:
                break;
        }
    }

    @Override
    public void ok(int type) {
        onBackPressed();
    }
}
