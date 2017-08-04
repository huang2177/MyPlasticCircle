package com.myplas.q.myinfo.invoices.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.myplas.q.R;
import com.myplas.q.common.api.API;
import com.myplas.q.common.netresquset.ResultCallBack;
import com.myplas.q.common.utils.TextUtils;
import com.myplas.q.common.view.MyListview;
import com.myplas.q.common.view.NoResultLayout;
import com.myplas.q.guide.activity.BaseActivity;
import com.myplas.q.myinfo.beans.IntegralBean;
import com.myplas.q.myinfo.beans.OrderListsBean;
import com.myplas.q.myinfo.integral.adapter.IntegralAdapter;
import com.myplas.q.myinfo.invoices.adapter.TradeOrderListviewAdapter;
import com.sobot.chat.SobotApi;
import com.sobot.chat.api.model.Information;
import com.umeng.analytics.MobclickAgent;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 编写： 黄双
 * 电话：15378412400
 * 邮箱：15378412400@163.com
 * 时间：2017/3/28 10:25
 */
public class TradeOrderActivity extends BaseActivity implements OnClickListener, TradeOrderListviewAdapter.MyOnClickListener
        , ResultCallBack {
    private Information information;
    private String appkey = "c1ff771c06254db796cd7ce1433d2004";

    private EditText mEditText;
    private MyListview mListView;
    private ImageView mImageView;
    private NoResultLayout mNoResultLayout;

    private TradeOrderListviewAdapter mAdapter;
    private List<OrderListsBean.DataBean.ListBean> mList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_layout_tradeorder);
        goBack(findViewById(R.id.img_back));

        initView();
        getorderLists("");
    }

    public void initView() {
        mImageView = F(R.id.img_contact);
        mListView = F(R.id.trade_order_listview);
        mEditText = F(R.id.trade_order_edittext);
        mNoResultLayout = F(R.id.trade_order_noresultlayout);

        mImageView.setOnClickListener(this);
        //edittext 回车监听
        mEditText.setOnEditorActionListener(new TextView.OnEditorActionListener() {

            @Override
            public boolean onEditorAction(TextView arg0, int arg1, KeyEvent arg2) {
                if (arg1 == EditorInfo.IME_ACTION_SEARCH | (arg2 != null && arg2.getAction() == KeyEvent.ACTION_DOWN)) {
                    String keywords = mEditText.getText().toString();
                    if (TextUtils.isNullOrEmpty(keywords)) {
                        getorderLists(keywords);
                    } else {
                        TextUtils.Toast(TradeOrderActivity.this, "你还没有输入搜索内容！");
                    }
                    return true;
                }
                return false;
            }
        });
    }

    public void getorderLists(String keywords) {
        Map<String, String> map = new HashMap<String, String>();
        map.put("page", "1");
        map.put("size", "20");
        map.put("order_sn", keywords);
        postAsyn(this, API.BASEURL + API.BILLINGLIST, map, this, 1);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.img_contact:
                information = new Information();
                information.setAppkey(appkey);
                SobotApi.startSobotChat(TradeOrderActivity.this, information);
                break;
        }
    }

    //que
    @Override
    public void onClick1(String order_sn) {
        Intent intent = new Intent(this, ApplyInvoicesActivity.class);
        intent.putExtra("order_sn", order_sn);
        startActivity(intent);
    }

    @Override
    public void onClick2(int position) {
        //startActivity(new Intent(this, InvoicesDetailActivity.class));

    }

    @Override
    public void onClick3(int position) {
        //startActivity(new Intent(this, InvoicesDetailActivity.class));
        startActivity(new Intent(this, ApplyInvoicesActivity.class));
    }


    @Override
    public void callBack(Object object, int type) {
        try {
            Gson gson = new Gson();
            String err = new JSONObject(object.toString()).getString("err");
            Log.e("------", object.toString());
            if (type == 1) {
                if (err.equals("0")) {
                    mNoResultLayout.setVisibility(false);
                    mListView.setVisibility(View.VISIBLE);
                    OrderListsBean bean = gson.fromJson(object.toString(), OrderListsBean.class);
                    mList = bean.getData().getList();
                    mAdapter = new TradeOrderListviewAdapter(this, mList);
                    mAdapter.setMyOnClickListener(this);
                    mListView.setAdapter(mAdapter);
                } else {
                    mListView.setVisibility(View.GONE);
                    //String msg= new JSONObject(object.toString()).getString("msg");
                    mNoResultLayout.setNoResultData(R.drawable.zxtb, "gsughduycdghuydcguwd", true);
                }
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
