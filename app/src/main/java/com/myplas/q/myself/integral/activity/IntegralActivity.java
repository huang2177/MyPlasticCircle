package com.myplas.q.myself.integral.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.View;
import android.widget.ScrollView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.myplas.q.R;
import com.myplas.q.common.api.API;
import com.myplas.q.common.netresquset.ResultCallBack;
import com.myplas.q.common.utils.SharedUtils;
import com.myplas.q.common.utils.TextUtils;
import com.myplas.q.guide.activity.BaseActivity;
import com.myplas.q.myself.beans.IntegralBean;
import com.myplas.q.myself.integral.adapter.IntegralAdapter;
import com.umeng.analytics.MobclickAgent;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 编写： 黄双
 * 电话：15378412400
 * 邮箱：15378412400@163.com
 * 时间：2017/3/28 14:10
 */
public class IntegralActivity extends BaseActivity implements ResultCallBack, View.OnClickListener, IntegralAdapter.MyInterface {
    private int position;
    private boolean move;
    private SharedUtils sharedUtils;
    private RecyclerView mRecyclerView;
    private LinearLayoutManager manager;
    private NestedScrollView mScrollView;
    private IntegralAdapter integralAdapter;
    private List<IntegralBean.InfoBean> list;
    private CoordinatorLayout mCoordinatorLayout;
    private TextView integral_all, integral_all_, intergral_chz, intergral_record, intergral_rule;

    private Handler mHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_layout_integral);
        initTileBar();
        setTitle("塑豆商城");
        setRightTVText("如何赚塑豆");

        position = -1;
        mHandler = new Handler();
        sharedUtils = SharedUtils.getSharedUtils();

        intergral_chz = F(R.id.jf_chz);
        mScrollView = F(R.id.scrollview);
        integral_all = F(R.id.integral_all);
        mRecyclerView = F(R.id.jf_gridview);
        intergral_record = F(R.id.jf_record);
        integral_all_ = F(R.id.integral_all_);
        mCoordinatorLayout = F(R.id.coordinatorlayout);

        manager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(manager);
        mRecyclerView.setNestedScrollingEnabled(false);

        mTVRight.setOnClickListener(this);
        integral_all.setOnClickListener(this);
        integral_all_.setOnClickListener(this);
        intergral_chz.setOnClickListener(this);
        intergral_record.setOnClickListener(this);

        String type = getIntent().getStringExtra("type");
        if (type != null) {
            position = Integer.parseInt(type);
        }
        getProducts(1);
    }

    public void getProducts(int type) {
        Map<String, String> map = new HashMap<String, String>();
        map.put("token", sharedUtils.getData(this, "token"));
        map.put("page", "1");
        map.put("size", "20");
        postAsyn(this, API.BASEURL + API.GET_PRODUCT_LIST, map, this, type);

    }

    @Override
    public void callBack(Object object, int type) {
        try {
            Gson gson = new Gson();
            String err = new JSONObject(object.toString()).getString("err");
            if (type == 1 && err.equals("0")) {
                IntegralBean integralBean = gson.fromJson(object.toString(), IntegralBean.class);
                list = integralBean.getInfo();
                integral_all.setText(" " + integralBean.getPointsAll().toString());
                integralAdapter = new IntegralAdapter(this, this, list, this);
                mRecyclerView.setAdapter(integralAdapter);

                if (position != -1) {
                    //  moveToPosition();
                }
            }
            if (type == 2 && err.equals("0")) {
                IntegralBean integralBean = gson.fromJson(object.toString(), IntegralBean.class);
                integral_all.setText(" " + integralBean.getPointsAll().toString());
            }
        } catch (Exception e) {
            TextUtils.Toast(this, "数据解析错啦！");
        }
    }

    private void moveToPosition() {
        Log.e("-----", mRecyclerView.getChildCount() + "---");
        int firstVisibleItems = manager.findFirstVisibleItemPosition();
        // 真实Position就是position - firstVisibleItems[0]
        View childAt = mRecyclerView.getChildAt(5 - firstVisibleItems);
        final int top = childAt.getTop();
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                mScrollView.smoothScrollBy(0, top);
            }
        });
    }

    @Override
    public void failCallBack(int type) {

    }

    @Override
    public void refresgData() {
        getProducts(2);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.integral_all:
            case R.id.integral_all_://明细
                startActivity(new Intent(this, IntegralDetialActivtity.class));
                break;
            case R.id.jf_chz://充值
                startActivity(new Intent(this, IntegralPayActivtity.class));
                break;
            case R.id.jf_record://兑换记录
                startActivity(new Intent(this, IntegralRecordActivtity.class));
                break;
            case R.id.titlebar_text_right://规则
                startActivity(new Intent(this, IntegralRuleActivtity.class));
                break;
            default:
                break;
        }
    }

}
