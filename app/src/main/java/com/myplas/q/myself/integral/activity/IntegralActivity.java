package com.myplas.q.myself.integral.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.google.gson.Gson;
import com.myplas.q.R;
import com.myplas.q.common.api.API;
import com.myplas.q.common.netresquset.ResultCallBack;
import com.myplas.q.common.utils.SharedUtils;
import com.myplas.q.app.activity.BaseActivity;
import com.myplas.q.common.utils.TextUtils;
import com.myplas.q.myself.beans.IntegralBean;
import com.myplas.q.myself.integral.adapter.IntegralAdapter;

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

    private AppBarLayout appBarLayout;
    private RecyclerView mRecyclerView;
    private LinearLayoutManager manager;
    private CoordinatorLayout mCoordinatorLayout;
    private TextView integralAll, integral_all_, intergralChz, intergralRecord, intergralRule;

    private Handler mHandler;

    private String cateId;
    private int mIndex = 0;
    private boolean move = false;
    private SharedUtils sharedUtils;
    private IntegralAdapter integralAdapter;
    private List<IntegralBean.InfoBean> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_layout_integral);
        initTileBar();
        setTitle("塑豆商城");
        setRightTVText("如何赚塑豆");

        mHandler = new Handler();
        sharedUtils = SharedUtils.getSharedUtils();
        cateId = getIntent().getStringExtra("type");

        appBarLayout = F(R.id.appbar);
        intergralChz = F(R.id.jf_chz);
        integralAll = F(R.id.integral_all);
        mRecyclerView = F(R.id.jf_gridview);
        intergralRecord = F(R.id.jf_record);
        integral_all_ = F(R.id.integral_all_);
        mCoordinatorLayout = F(R.id.coordinatorlayout);

        manager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(manager);
        mRecyclerView.addOnScrollListener(new RecyclerViewListener());

        mTVRight.setOnClickListener(this);
        integralAll.setOnClickListener(this);
        integral_all_.setOnClickListener(this);
        intergralChz.setOnClickListener(this);
        intergralRecord.setOnClickListener(this);

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
                integralAll.setText(" " + integralBean.getPointsAll().toString());
                integralAdapter = new IntegralAdapter(this, this, list, this);
                mRecyclerView.setAdapter(integralAdapter);

                moveToPosition(cateId);

            }
            if (type == 2 && err.equals("0")) {
                IntegralBean integralBean = gson.fromJson(object.toString(), IntegralBean.class);
                integralAll.setText(" " + integralBean.getPointsAll().toString());
            }
        } catch (Exception e) {
//            TextUtils.toast(this, "数据解析错啦！");
        }
    }


    @Override
    public void failCallBack(int type) {

    }

    @Override
    public void refreshData() {
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

    /**
     * recycleView滚动到指定位置
     *
     * @param n
     */
    private void moveToPosition(String cateId) {
        if (cateId != null) {
            integralAdapter.setClickIsFinish(true);
            for (int n = 0; n < list.size(); n++) {
                List<IntegralBean.InfoBean.ExtraConfigBean> listConfig = list.get(n).getExtra_config();
                if (listConfig.size() == 0) {
                    return;
                }
                IntegralBean.InfoBean.ExtraConfigBean extraConfigBean = listConfig.get(0);
                if (extraConfigBean == null) {
                    return;
                }
                if (extraConfigBean.getCate_id().equals(cateId)) {
                    if (n < 0 || n >= integralAdapter.getItemCount()) {
                        return;
                    }
                    mIndex = n;
                    mRecyclerView.stopScroll();
                    appBarLayout.setExpanded(false);

                    int firstItem = manager.findFirstVisibleItemPosition();
                    int lastItem = manager.findLastVisibleItemPosition();
                    if (n <= firstItem) {
                        mRecyclerView.scrollToPosition(n);
                    } else if (n <= lastItem) {
                        int top = mRecyclerView.getChildAt(n - firstItem).getTop();
                        mRecyclerView.scrollBy(0, top);
                    } else {
                        mRecyclerView.scrollToPosition(n);
                        move = true;
                    }

                    integralAdapter.setClassifyPosition(mIndex);
                    integralAdapter.setExtraConfigBean(extraConfigBean);
                }
            }
        }
    }

    class RecyclerViewListener extends RecyclerView.OnScrollListener {
        @Override
        public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
            super.onScrollStateChanged(recyclerView, newState);
        }

        @Override
        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
            super.onScrolled(recyclerView, dx, dy);
            if (move) {
                move = false;
                int n = mIndex - manager.findFirstVisibleItemPosition();
                if (0 <= n && n < mRecyclerView.getChildCount()) {
                    int top = mRecyclerView.getChildAt(n).getTop();
                    mRecyclerView.scrollBy(0, top);
                }
            }
        }
    }

}
