package com.myplas.q.myinfo.invoices;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.myplas.q.R;
import com.myplas.q.common.view.MyListview;
import com.myplas.q.common.view.NoResultLayout;
import com.myplas.q.guide.activity.BaseActivity;
import com.myplas.q.myinfo.adapter.ApplyInvoiceAdapter;
import com.myplas.q.myinfo.adapter.InvoiceListviewAdapter;
import com.sobot.chat.SobotApi;
import com.sobot.chat.api.model.Information;
import com.umeng.analytics.MobclickAgent;

/**
 * 编写： 黄双
 * 电话：15378412400
 * 邮箱：15378412400@163.com
 * 时间：2017/3/28 10:25
 */
public class ApplyInvoicesActivity extends BaseActivity implements View.OnClickListener, ApplyInvoiceAdapter.MyOnClickListener {
    private Information information;
    private String appkey = "c1ff771c06254db796cd7ce1433d2004";

    private Button mButton;
    private EditText mEditText;
    private MyListview mListView;
    private ImageView mImageView;
    private TextView mTextView_cm, mTextView_tprice, mTextView_notapplied, mTextView_apply, textView_allprice;

    private ApplyInvoiceAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_layout_applyinvoices);
        goBack(findViewById(R.id.img_back));

        initView();
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

        mImageView.setOnClickListener(this);

        mAdapter = new ApplyInvoiceAdapter(this, null);
        mListView.setAdapter(mAdapter);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.img_contact:
                information = new Information();
                information.setAppkey(appkey);
                SobotApi.startSobotChat(this, information);
                break;

        }
    }

    //适配器的回调
    @Override
    public void onClick(int position) {

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
