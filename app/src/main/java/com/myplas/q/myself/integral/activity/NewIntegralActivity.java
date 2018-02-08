package com.myplas.q.myself.integral.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.myplas.q.R;
import com.myplas.q.app.activity.BaseActivity;
import com.myplas.q.common.api.API;
import com.myplas.q.common.net.ResultCallBack;
import com.myplas.q.common.utils.TextUtils;
import com.myplas.q.common.view.EmptyView;
import com.myplas.q.myself.beans.NewIntergralBean;
import com.myplas.q.myself.integral.adapter.NewIntegralDetialLVAdapter;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * @author 黄双
 * @date 2018/2/5 0005
 */

public class NewIntegralActivity extends BaseActivity implements View.OnClickListener,
        ResultCallBack {

    private TextView textView;
    private ListView listView;
    private EmptyView mEmptyView;
    private LinearLayout layoutMore;
    private NewIntegralDetialLVAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_activity_intergral_layout);
        initTileBar();
        setTitle("我的塑豆");
        setRightTVText("如何赚塑豆");

        initView();
    }

    private void initView() {
        textView = F(R.id.detail_points);
        mEmptyView = F(R.id.detial_empty);
        listView = F(R.id.new_intergral_lv);
        layoutMore = F(R.id.detail_more_detail);

        mTVRight.setOnClickListener(this);
        layoutMore.setOnClickListener(this);
    }

    @Override
    public void onResume() {
        super.onResume();
        getData();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.titlebar_text_right://规则
                startActivity(new Intent(this, IntegralRuleActivtity.class));
                break;
            case R.id.detail_more_detail:
                startActivity(new Intent(this, IntegralDetialActivtity.class));
                break;
            default:
                break;
        }
    }


    private void getData() {
        Map<String, String> map = new HashMap<>(16);
        map.put("page", "1");
        map.put("size", "10");
        map.put("type", "0");
        getAsyn(this, API.POINTSBILLLOG, map, this, 1);
    }

    /**
     * 充值
     *
     * @param view
     */
    public void recharge(View view) {
        startActivity(new Intent(this, IntegralPayActivtity.class));
    }

    @Override
    public void callBack(Object object, int type) {

        try {
            JSONObject jsonObject = new JSONObject(object.toString());
            String code = jsonObject.getString("code");
            if ("0".equals(code)) {
                Gson gson = new Gson();
                layoutMore.setVisibility(View.VISIBLE);
                NewIntergralBean bean = gson.fromJson(object.toString(), NewIntergralBean.class);
                adapter = new NewIntegralDetialLVAdapter(this, bean.getData());
                listView.setAdapter(adapter);

                textView.setText(bean.getPoints());
            }
        } catch (Exception e) {

        }
    }

    @Override
    public void failCallBack(int type, String message, int httpCode) {
        if (httpCode == 404) {
            listView.setVisibility(View.GONE);
            mEmptyView.setVisibility(View.VISIBLE);
            mEmptyView.setMyManager(R.drawable.icon_intelligent_recommendation2);
            mEmptyView.setNoMessageText("没有相关数据！");
        }
    }

}
