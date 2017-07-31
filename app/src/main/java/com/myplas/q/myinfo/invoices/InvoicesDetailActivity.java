package com.myplas.q.myinfo.invoices;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;

import com.myplas.q.R;
import com.myplas.q.common.view.NoResultLayout;
import com.myplas.q.guide.activity.BaseActivity;
import com.myplas.q.myinfo.adapter.InvoiceListviewAdapter;
import com.umeng.analytics.MobclickAgent;

/**
 * 编写： 黄双
 * 电话：15378412400
 * 邮箱：15378412400@163.com
 * 时间：2017/3/28 10:25
 */
public class InvoicesDetailActivity extends BaseActivity {
    private EditText mEditText;
    private ListView mListView;
    private ImageView mImageView;
    private NoResultLayout mNoResultLayout;

    private InvoiceListviewAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_layout_invoicesdetail);
        goBack(findViewById(R.id.img_back));

        initView();
    }

    public void initView() {
        mListView = F(R.id.invoices_listview);
        mNoResultLayout = F(R.id.invoices_noresultlayout);

        mAdapter = new InvoiceListviewAdapter(this, null);
        mListView.setAdapter(mAdapter);
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
