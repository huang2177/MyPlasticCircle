package com.myplas.q.myself.invoices.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.gson.Gson;
import com.myplas.q.R;
import com.myplas.q.common.api.API;
import com.myplas.q.common.netresquset.ResultCallBack;
import com.myplas.q.common.utils.TextUtils;
import com.myplas.q.common.view.MyListview;
import com.myplas.q.app.activity.BaseActivity;
import com.myplas.q.myself.invoices.adapter.ApplyInvoiceAdapter;
import com.myplas.q.myself.beans.ApplyInvoiceBean;
import com.sobot.chat.SobotApi;
import com.sobot.chat.api.model.Information;

import org.json.JSONObject;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
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
    private TextView mTextView_cm, mTextView_tprice, mTextView_notapplied, mTextView_apply, textView_allprice;

    private ApplyInvoiceBean bean;
    private ApplyInvoiceAdapter mAdapter;

    private StringBuffer ids, b_number;
    private String keywords, billing_price, rise, unbilling_price;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_layout_applyinvoices);
        initTileBar();
        setTitle("申请开票");
        setRightIVVisibility(View.VISIBLE);


        initView();
        getInvioceList(getIntent().getStringExtra("order_sn"));
    }

    public void initView() {
        ids = new StringBuffer();
        b_number = new StringBuffer();

        mButton = F(R.id.applyinvoices_confirm);
        mEditText = F(R.id.applyinvoices_remark);
        mListView = F(R.id.applyinvoices_listview);
        mTextView_cm = F(R.id.applyinvoices_company);
        mTextView_apply = F(R.id.applyinvoices_apply);
        mTextView_tprice = F(R.id.applyinvoices_tprice);
        textView_allprice = F(R.id.item_lv_invoice_allprice);
        mTextView_notapplied = F(R.id.applyinvoices_notapplied);

        mButton.setOnClickListener(this);
        mIVRight.setOnClickListener(this);
    }

    public void getInvioceList(String keywords) {
        Map<String, String> map = new HashMap<String, String>(8);
        map.put("page", "1");
        map.put("size", "20");
        map.put("order_sn", keywords);
        getAsyn(this, API.INVOICE, map, this, 1);
    }

    public void applyInvioce() {
        Map<String, String> map = new HashMap<String, String>(16);
        map.put("rise", rise);
        map.put("remark", keywords);
        map.put("id", ids.toString());
        map.put("billing_price", billing_price);
        map.put("b_number", b_number.toString());
        map.put("unbilling_price", unbilling_price);
        map.put("order_sn", getIntent().getStringExtra("order_sn"));
        postAsyn(this, API.INVOICEDETAILADD, map, this, 2);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.titlebar_img_right:
                information = new Information();
                information.setAppkey(appkey);
                SobotApi.startSobotChat(this, information);
                break;
            case R.id.applyinvoices_confirm:
                mButton.setClickable(false);
                mButton.setBackgroundColor(getResources().getColor(R.color.color_litlegray));
                keywords = mEditText.getText().toString();
                applyInvioce();
                break;
            default:
                break;

        }
    }

    @Override
    public void callBack(Object object, int type) {
        try {
            Gson gson = new Gson();
            String err = new JSONObject(object.toString()).getString("code");
            if (type == 1) {
                if ("0".equals(err)) {
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
                if ("0".equals(err)) {
                    TextUtils.toast(this, "提交成功！");
                    finish();
                } else {
                    mButton.setClickable(true);
                    mButton.setBackgroundColor(getResources().getColor(R.color.color_red));
                    String msg = new JSONObject(object.toString()).getString("message");
                    TextUtils.toast(this, msg);
                }
            }
        } catch (Exception e) {
        }
    }

    public void showInfo() {
        rise = bean.getData().getDetail().getRise();
        billing_price = bean.getData().getDetail().getBilling_price();
        unbilling_price = bean.getData().getDetail().getUnbilling_price();

        mTextView_cm.setText(bean.getData().getDetail().getRise());
        mTextView_tprice.setText(getDecimalFormatData(bean.getData().getDetail().getTotal_price()) + "");
        mTextView_notapplied.setText(getDecimalFormatData(bean.getData().getDetail().getUnbilling_price()) + "");
        textView_allprice.setText("申请开票金额合计：" + getDecimalFormatData(bean.getData().getDetail().getBilling_price()) + "");
        mTextView_apply.setText(getDecimalFormatData(bean.getData().getDetail().getBilling_price()) + "");
    }

    public void getArray() {
        for (int i = 0; i < bean.getData().getList().size(); i++) {
            ids = ids.append(bean.getData().getList().get(i).getId());
            b_number = b_number.append(bean.getData().getList().get(i).getB_number());
            if (i != bean.getData().getList().size() - 1) {
                ids = ids.append(",");
                b_number = b_number.append(",");
            }
        }
    }

    @Override
    public void failCallBack(int type, String message, int httpCode) {
        try {
            if (type == 2) {
                mButton.setClickable(true);
                mButton.setBackgroundColor(getResources().getColor(R.color.color_red));
                if (httpCode == 412) {
                    String msg = new JSONObject(message).getString("message");
                    TextUtils.toast(this, msg);
                }
            }
        } catch (Exception e) {

        }
    }

    //适配器的回调
    @Override
    public void onClick(Map<Integer, Double> map, Map<Integer, String> map2) {
        double d = 0;
        b_number = new StringBuffer();
        List<Integer> list = new ArrayList<>();
        List<Integer> list1 = new ArrayList<>();
        try {
            for (int key : map2.keySet()) {
                list.add(key);
            }
            Collections.sort(list);

            for (int i = 0; i < list.size(); i++) {
                b_number = b_number.append(map2.get(i)).append(",");
                d += map.get(i);
            }
            b_number = new StringBuffer(b_number.subSequence(0, b_number.lastIndexOf(",")));
            billing_price = d + "";
            mTextView_apply.setText(d + "");
            textView_allprice.setText("申请开票金额合计：" + d + "");
        } catch (Exception e) {
        }
    }

    public String getDecimalFormatData(String data) {
        DecimalFormat format = new DecimalFormat("#.0000");
//        return Double.parseDouble(format.format(Double.parseDouble(data)));
        return data;
    }
}
