package com.myplas.q.myself.invoices.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

import com.google.gson.Gson;
import com.myplas.q.R;
import com.myplas.q.common.api.API;
import com.myplas.q.common.netresquset.ResultCallBack;
import com.myplas.q.common.utils.TextUtils;
import com.myplas.q.common.view.EmptyView;
import com.myplas.q.app.activity.BaseActivity;
import com.myplas.q.myself.invoices.adapter.TradeOrderListviewAdapter;
import com.myplas.q.myself.beans.OrderListsBean;
import com.myplas.q.sockethelper.RabbitMQConfig;
import com.sobot.chat.SobotApi;
import com.sobot.chat.api.model.Information;

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
    private EmptyView mEmptyView;
    private RecyclerView mListView;
    private AppBarLayout mBarLayout;

    private TradeOrderListviewAdapter mAdapter;
    private List<OrderListsBean.DataBean.ListBean> mList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_layout_tradeorder);
        initTileBar();
        setTitle("我的订单");
        setRightIVVisibility(View.VISIBLE);

        initView();
        getorderLists("", 1);
    }

    public void initView() {
        mBarLayout = F(R.id.appbar);
        mListView = F(R.id.trade_order_listview);
        mEditText = F(R.id.trade_order_edittext);
        mEmptyView = F(R.id.trade_order_noresultlayout);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);//设置为一个1列的纵向网格布局
        mListView.setLayoutManager(mLayoutManager);

        mIVConact.setOnClickListener(this);
        //edittext 回车监听
        mEditText.setOnEditorActionListener(new TextView.OnEditorActionListener() {

            @Override
            public boolean onEditorAction(TextView arg0, int arg1, KeyEvent arg2) {
                if (arg1 == EditorInfo.IME_ACTION_SEARCH | (arg2 != null && arg2.getAction() == KeyEvent.ACTION_DOWN)) {
                    String keywords = mEditText.getText().toString();
                    if (TextUtils.notEmpty(keywords)) {
                        getorderLists(keywords, 1);
                    } else {
                        TextUtils.toast(TradeOrderActivity.this, "你还没有输入搜索内容！");
                    }
                    return true;
                }
                return false;
            }
        });
    }

    public void getorderLists(String keywords, int type) {
        Map<String, String> map = new HashMap<String, String>();
        map.put("page", "1");
        map.put("size", "20");
        map.put("order_sn", keywords);
        postAsyn(this, API.BASEURL + API.BILLINGLIST, map, this, type);
    }


    @Override
    public void onClick(View v) {
        information = new Information();
        information.setAppkey(appkey);
        SobotApi.startSobotChat(TradeOrderActivity.this, information);
    }

    //申请开票
    @Override
    public void onClick1(String order_sn) {
        Intent intent = new Intent(this, ApplyInvoicesActivity.class);
        intent.putExtra("order_sn", order_sn);
        startActivity(intent);
    }

    //确认签收后的刷新
    @Override
    public void onClick2() {
        getorderLists("", 2);
    }

    //发票详情
    @Override
    public void onClick3(String order_sn) {
        Intent intent = new Intent(this, InvoicesDetailActivity.class);
        intent.putExtra("order_sn", order_sn);
        startActivity(intent);
    }


    @Override
    public void callBack(Object object, int type) {
        try {
            Gson gson = new Gson();
            String err = new JSONObject(object.toString()).getString("err");
            if (type == 1) {
                if (err.equals("0")) {
                    mEmptyView.setVisibility(View.GONE);
                    mListView.setVisibility(View.VISIBLE);
                    mBarLayout.setVisibility(View.VISIBLE);
                    OrderListsBean bean = gson.fromJson(object.toString(), OrderListsBean.class);
                    mList = bean.getData().getList();
                    mAdapter = new TradeOrderListviewAdapter(this, mList);
                    mAdapter.setMyOnClickListener(this);
                    mListView.setAdapter(mAdapter);

                    RabbitMQConfig.getInstance(this).readMsg("unread_myorder", 13);
                } else {
                    mListView.setVisibility(View.GONE);
                    mBarLayout.setVisibility(View.GONE);
                    String msg = new JSONObject(object.toString()).getString("msg");
                    //mEmptyView.setNoMessageText(msg);
                    mEmptyView.setMyManager(R.drawable.icon_invoices_null);
                }
            }
            if (type == 2) {
                if (err.equals("0")) {
                    OrderListsBean bean = gson.fromJson(object.toString(), OrderListsBean.class);
                    mList = bean.getData().getList();
                    mAdapter.setList(mList);
                    mAdapter.notifyDataSetChanged();
                }
            }
        } catch (Exception e) {
        }
    }

    @Override
    public void failCallBack(int type, String message, int httpCode) {

    }

}
