package com.myplas.q.myinfo.integral.activity;

import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.jmf.addsubutils.AddSubUtils;
import com.myplas.q.R;
import com.myplas.q.common.utils.ScreenUtils;
import com.myplas.q.common.view.MyGridview;
import com.myplas.q.guide.activity.BaseActivity;
import com.myplas.q.myinfo.integral.adapter.Integral_Diaolog_Classify_Adapter;
import com.umeng.analytics.MobclickAgent;

/**
 * 编写： 黄双
 * 电话：15378412400
 * 邮箱：15378412400@163.com
 * 时间：2017/4/14 15:32
 */
public class IntegralDialogActivity extends BaseActivity implements View.OnClickListener {
    private AddSubUtils mAddSubUtils;
    private MyGridview mGridView1, mGridView2;
    private ImageView mImageShow, mImageClose;
    private TextView mTextPrice, mTextType, mTextChoosed;

    private Integral_Diaolog_Classify_Adapter mAdapter;

    //3333333

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_layout_intergral_classify);
        getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);

        mGridView1 = (MyGridview) findViewById(R.id.dl_classify_gv1);
        mGridView2 = (MyGridview) findViewById(R.id.dl_classify_gv2);
        mTextType = (TextView) findViewById(R.id.dl_classify_text_type);
        mTextPrice = (TextView) findViewById(R.id.dl_classify_text_price);
        //mImageClose = (ImageView) findViewById(R.id.dl_classify_img_close);
        mTextChoosed = (TextView) findViewById(R.id.dl_classify_text_choosed);
        mAddSubUtils = (AddSubUtils) findViewById(R.id.dl_classify_addsubutils);

        //mImageClose.setOnClickListener(this);

        mAdapter = new Integral_Diaolog_Classify_Adapter(this, null, null);
        mGridView1.setAdapter(mAdapter);
        final Integral_Diaolog_Classify_Adapter mAdapter1 = new Integral_Diaolog_Classify_Adapter(this, null, null);
        mGridView2.setAdapter(mAdapter1);

        mGridView1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                mAdapter.changeImg(position);
//                showData(list_msg, position);
            }
        });
        mGridView2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                mAdapter1.changeImg(position);
//                showData(list_msg, position);
            }
        });
    }

    @Override
    public void onClick(View v) {
//        switch (v.getId()) {
//            case R.id.ed_call1:
//                call("400-6129-965");
//                break;
//            case R.id.ed_call2:
//                call("021-61070985");
//                break;
//            case R.id.ed_cancle:
//            case R.id.share_view:
//                finish();
//                break;
//        }
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
