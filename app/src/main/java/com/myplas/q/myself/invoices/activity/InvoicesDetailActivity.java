package com.myplas.q.myself.invoices.activity;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;

import com.google.gson.Gson;
import com.myplas.q.R;
import com.myplas.q.common.api.API;
import com.myplas.q.common.netresquset.ResultCallBack;
import com.myplas.q.common.view.MyListview;
import com.myplas.q.app.activity.BaseActivity;
import com.myplas.q.myself.beans.InvoiceDetailBean;


import com.myplas.q.myself.invoices.adapter.InvoiceListviewAdapter;
import com.umeng.analytics.MobclickAgent;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * 编写： 黄双
 * 电话：15378412400
 * 邮箱：15378412400@163.com
 * 时间：2017/3/28 10:25
 */
public class InvoicesDetailActivity extends BaseActivity implements ResultCallBack {
    private EditText mEditText;
    private MyListview mListView;
    private ImageView mImageView;

    private InvoiceListviewAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_layout_invoicesdetail);
        initTileBar();
        setTitle("发票详情");

        initView();
        getBillingDetailList(getIntent().getStringExtra("order_sn"));
    }

    public void initView() {
        mListView = F(R.id.invoices_listview);
    }


    public void getBillingDetailList(String keywords) {
        Map<String, String> map = new HashMap<String, String>();
        map.put("order_sn", keywords);
        postAsyn(this, API.BASEURL + API.BILLINGDETAILLIST, map, this, 1);
    }


    @Override
    public void callBack(Object object, int type) {
        try {
            Gson gson = new Gson();
            String err = new JSONObject(object.toString()).getString("err");
            if (err.equals("0")) {
                InvoiceDetailBean bean = gson.fromJson(object.toString(), InvoiceDetailBean.class);
                mAdapter = new InvoiceListviewAdapter(this, bean.getData());
                mListView.setAdapter(mAdapter);
            } else {

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
