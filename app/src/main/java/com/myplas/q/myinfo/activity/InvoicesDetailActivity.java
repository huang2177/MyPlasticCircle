package com.myplas.q.myinfo.activity;

import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ImageView;

import com.myplas.q.R;
import com.myplas.q.common.view.MyListview;
import com.myplas.q.common.view.NoResultLayout;
import com.myplas.q.guide.activity.BaseActivity;
import com.myplas.q.myinfo.adapter.TradeOrder_Listview_Adapter;
import com.sobot.chat.SobotApi;
import com.sobot.chat.api.model.Information;
import com.umeng.analytics.MobclickAgent;

/**
 * 编写： 黄双
 * 电话：15378412400
 * 邮箱：15378412400@163.com
 * 时间：2017/3/28 10:25
 */
public class InvoicesDetailActivity extends BaseActivity implements OnClickListener, TradeOrder_Listview_Adapter.MyOnClickListener {
    private EditText mEditText;
    private MyListview mListView;
    private ImageView mImageView;
    private NoResultLayout mNoResultLayout;

    private TradeOrder_Listview_Adapter mAdapter;
    TextInputLayout mTextInputLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_layout_invoices);
        goBack(findViewById(R.id.img_back));

        //initView();
    }

    public void initView() {
        mTextInputLayout = (TextInputLayout) findViewById(R.id.dl_tel_wrap);
        mTextInputLayout.setHint("请填写手机号码");
//        mImageView = F(R.id.img_contact);
//        mListView = F(R.id.trade_order_listview);
//        mEditText = F(R.id.trade_order_edittext);
//        mNoResultLayout = F(R.id.trade_order_noresultlayout);
//
//        mImageView.setOnClickListener(this);
//        mAdapter = new TradeOrder_Listview_Adapter(this, null);
//        mAdapter.setMyOnClickListener(this);
//        mListView.setAdapter(mAdapter);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.img_contact:

                break;

        }
    }

    @Override
    public void onClickSigned(View view) {
    }

    @Override
    public void onClick2(int position) {

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
