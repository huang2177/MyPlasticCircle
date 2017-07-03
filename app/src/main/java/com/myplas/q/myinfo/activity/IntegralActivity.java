package com.myplas.q.myinfo.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.myplas.q.R;
import com.myplas.q.guide.activity.BaseActivity;
import com.myplas.q.common.netresquset.ResultCallBack;
import com.myplas.q.common.utils.SharedUtils;
import com.myplas.q.common.utils.TextUtils;
import com.myplas.q.myinfo.adapter.IntegralAdapter;
import com.myplas.q.common.api.API;
import com.myplas.q.myinfo.beans.IntegralBean;
import com.myplas.q.guide.activity.MainActivity;
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
public class IntegralActivity extends BaseActivity implements ResultCallBack, View.OnClickListener, IntegralAdapter.callBackiterface {
    private ListView mGridview;
    private SharedUtils sharedUtils;
    private IntegralAdapter integralAdapter;
    private List<IntegralBean.InfoBean> list;
    private TextView integral_all, integral_all_, intergral_chz,intergral_record,intergral_rule;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.jf_activity_layout);
        goBack(findViewById(R.id.back));

        sharedUtils = SharedUtils.getSharedUtils();
        mGridview = (ListView) findViewById(R.id.jf_gridview);
        View view = LayoutInflater.from(this).inflate(R.layout.layout_integral_listview_head, null);

        intergral_rule = (TextView) findViewById(R.id.jf_rule);
        intergral_chz = (TextView) view.findViewById(R.id.jf_chz);
        intergral_record= (TextView) view.findViewById(R.id.jf_record);
        integral_all = (TextView) view.findViewById(R.id.integral_all);
        integral_all_ = (TextView) view.findViewById(R.id.integral_all_);
        mGridview.addHeaderView(view);

        intergral_rule.setOnClickListener(this);
        intergral_chz.setOnClickListener(this);
        intergral_record.setOnClickListener(this);
        integral_all.setOnClickListener(this);
        integral_all_.setOnClickListener(this);
        getProducts(1);
    }

    public void getProducts(int type) {
        try {
            Map<String, String> map = new HashMap<String, String>();
            map.put("token", sharedUtils.getData(this, "token"));
            map.put("page", "1");
            map.put("size", "10");
            postAsyn(this, API.BASEURL + API.GET_PRODUCT_LIST, map, this, type);
        } catch (Exception e) {
        }
    }

    @Override
    public void callBack(Object object, int type) {
        try {
            Gson gson = new Gson();
            if (type == 1 && new JSONObject(object.toString()).getString("err").equals("0")) {
                IntegralBean integralBean = gson.fromJson(object.toString(), IntegralBean.class);
                list = integralBean.getInfo();
                integral_all.setText(" " + integralBean.getPointsAll().toString());
                integralAdapter = new IntegralAdapter(this, this, list, this);
                mGridview.setAdapter(integralAdapter);
            } else if (type==1){
                TextUtils.Toast(this, "没有相关数据！");
            }
            if (type == 2 && new JSONObject(object.toString()).getString("err").equals("0")) {
                IntegralBean integralBean = gson.fromJson(object.toString(), IntegralBean.class);
                integral_all.setText(" " + integralBean.getPointsAll().toString());
            }
        } catch (Exception e) {
        }
    }
    @Override
    public void failCallBack(int type) {

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
            case R.id.jf_rule://规则
                startActivity(new Intent(this, IntegralRuleActivtity.class));
                break;
        }
    }
    //跳转到供求
    @Override
    public void finishActivity() {
        this.finish();
        MainActivity.clearColor();
        MainActivity.goToSupDem();
    }
    public void onResume() {
        super.onResume();
        getProducts(2);
        MobclickAgent.onResume(this);
    }
    public void onPause() {
        super.onPause();
        MobclickAgent.onPause(this);
    }
}
