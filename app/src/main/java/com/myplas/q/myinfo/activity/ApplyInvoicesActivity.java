package com.myplas.q.myinfo.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.myplas.q.R;
import com.myplas.q.common.api.API;
import com.myplas.q.common.netresquset.ResultCallBack;
import com.myplas.q.common.view.MyListview;
import com.myplas.q.guide.activity.BaseActivity;
import com.myplas.q.myinfo.adapter.ApplyInvoiceAdapter;
import com.myplas.q.myinfo.beans.ApplyInvoiceBean;
import com.myplas.q.myinfo.beans.OrderListsBean;
import com.sobot.chat.SobotApi;
import com.sobot.chat.api.model.Information;
import com.umeng.analytics.MobclickAgent;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DecimalFormat;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * 编写： 黄双
 * 电话：15378412400
 * 邮箱：15378412400@163.com
 * 时间：2017/3/28 10:25
 */
public class ApplyInvoicesActivity extends BaseActivity implements View.OnClickListener, ApplyInvoiceAdapter.MyOnClickListener, ResultCallBack {
    private Information information;
    private String appkey = "c1ff771c06254db796cd7ce1433d2004";

    private Button mButton;
    private EditText mEditText;
    private MyListview mListView;
    private ImageView mImageView;
    private TextView mTextView_cm, mTextView_tprice, mTextView_notapplied, mTextView_apply, textView_allprice;

    private ApplyInvoiceBean bean;
    private ApplyInvoiceAdapter mAdapter;

    private String keywords, ids, p_number;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_layout_applyinvoices);
        goBack(findViewById(R.id.img_back));

        initView();
        getInvioceList(getIntent().getStringExtra("order_sn"));
    }

    public void initView() {
        mImageView = F(R.id.img_contact);
        mButton = F(R.id.applyinvoices_confirm);
        mEditText = F(R.id.applyinvoices_remark);
        mListView = F(R.id.applyinvoices_listview);
        mTextView_cm = F(R.id.applyinvoices_company);
        mTextView_apply = F(R.id.applyinvoices_apply);
        mTextView_tprice = F(R.id.applyinvoices_tprice);
        textView_allprice = F(R.id.item_lv_invoice_allprice);
        mTextView_notapplied = F(R.id.applyinvoices_notapplied);

        mButton.setOnClickListener(this);
        mImageView.setOnClickListener(this);
    }

    public void getInvioceList(String keywords) {
        Map<String, String> map = new HashMap<String, String>();
        map.put("page", "1");
        map.put("size", "20");
        map.put("order_sn", keywords);
        postAsyn(this, API.BASEURL + API.INVOICE, map, this, 1);
    }

    public void applyInvioce() {
        Map<String, String> map = new HashMap<String, String>();
        map.put("order_sn", getIntent().getStringExtra("order_sn"));
        map.put("remark", keywords);
        map.put("billing_price", bean.getData().getDetail().getUnbilling_price());
        map.put("id", ids);
        map.put("b_number", p_number);
        postAsyn(this, API.BASEURL + API.INVOICEDETAILADD, map, this, 2);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.img_contact:
                information = new Information();
                information.setAppkey(appkey);
                SobotApi.startSobotChat(this, information);
                break;
            case R.id.applyinvoices_confirm:
                keywords = mEditText.getText().toString();
                applyInvioce();
                break;

        }
    }

    @Override
    public void callBack(Object object, int type) {
        try {
            Log.e("=======", object.toString());
            Gson gson = new Gson();
            String err = new JSONObject(object.toString()).getString("err");
            if (type == 1) {
                if (err.equals("0")) {
                    mListView.setVisibility(View.VISIBLE);
                    bean = gson.fromJson(object.toString(), ApplyInvoiceBean.class);
                    mAdapter = new ApplyInvoiceAdapter(this, bean.getData().getList());
                    mAdapter.setMyOnClickListener(this);
                    mListView.setAdapter(mAdapter);
                    showInfo();
                    getArray();
                }
            }
            if (type == 2) {

            }
        } catch (Exception e) {
        }
    }

    public void showInfo() {
//        totals = bean.getData().getDetail().getBilling_price();
//        order_total_price = bean.getData().getDetail().getTotal_price();

        mTextView_cm.setText(bean.getData().getDetail().getRise());
        mTextView_tprice.setText(getDecimalFormatData(bean.getData().getDetail().getTotal_price()) + "");
        mTextView_notapplied.setText(getDecimalFormatData(bean.getData().getDetail().getUnbilling_price()) + "");
        textView_allprice.setText("申请开票金额合计：" + getDecimalFormatData(bean.getData().getDetail().getBilling_price()) + "");
        mTextView_apply.setText(getDecimalFormatData(bean.getData().getDetail().getBilling_price()) + "");

    }

    @Override
    public void failCallBack(int type) {

    }

    //适配器的回调
    @Override
    public void onClick(Map<Integer, Double> map) {
        double d = 0;
        if (map != null) {
            Iterator<Map.Entry<Integer, Double>> entries = map.entrySet().iterator();
            while (entries.hasNext()) {
                Map.Entry<Integer, Double> entry = entries.next();
                d += entry.getValue();
            }
//            totals = d + "";
            mTextView_apply.setText(d + "");
            textView_allprice.setText("申请开票金额合计：" + d + "");
        }
    }

    public String getDecimalFormatData(String data) {
        DecimalFormat format = new DecimalFormat("#.0000");
//        return Double.parseDouble(format.format(Double.parseDouble(data)));
        return data;
    }

    public void getArray() {
        JSONArray ids = new JSONArray();
        JSONArray total_pirce = new JSONArray();

        for (int i = 0; i < bean.getData().getList().size(); i++) {
            try {
                ids.put(i, Double.parseDouble(bean.getData().getList().get(i).getId()));
                total_pirce.put(i, Double.parseDouble(bean.getData().getList().get(i).getPrice()));
            } catch (Exception e) {
            }
        }
//        this.ids = ids;
//        this.p_number = total_pirce;
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
