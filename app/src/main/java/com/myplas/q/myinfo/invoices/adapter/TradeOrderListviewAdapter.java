package com.myplas.q.myinfo.invoices.adapter;

import android.content.Context;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.myplas.q.R;
import com.myplas.q.common.api.API;
import com.myplas.q.common.netresquset.ResultCallBack;
import com.myplas.q.common.utils.TextUtils;
import com.myplas.q.common.view.MyListview;
import com.myplas.q.guide.activity.BaseActivity;
import com.myplas.q.myinfo.beans.EDuBean;
import com.myplas.q.myinfo.beans.OrderListsBean;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 编写：黄双
 * 邮箱：15378412400@163.com
 * 时间： 2017/6/121519.
 */

public class TradeOrderListviewAdapter extends BaseAdapter implements ResultCallBack {
    private Context context;
    private List<OrderListsBean.DataBean.ListBean> mList;

    private int position;
    private Handler mHandler;
    private MyOnClickListener mListener;

    private Map<Integer, View> mMapViews;
    private Map<Integer, TextView> mMapTextViews;

    private String invoice_status, sign, billing_status;

    public TradeOrderListviewAdapter(Context context, List<OrderListsBean.DataBean.ListBean> mList) {
        this.mList = mList;
        mHandler = new Handler();
        this.context = context;
        mMapViews = new HashMap<>();
        mMapTextViews = new HashMap<>();
    }

    @Override
    public int getCount() {
        if (mList != null)
            return mList.size();
        return 0;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final viewHolder viewHolder;
        if (convertView == null) {
            viewHolder = new viewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.item_layout_tradeorder, parent, false);
            viewHolder.textView_signed = (TextView) convertView.findViewById(R.id.popou_layout_signed_text);
            viewHolder.textView_title = (TextView) convertView.findViewById(R.id.tradeorder_item_title);
            viewHolder.textView_tprice = (TextView) convertView.findViewById(R.id.tradeorder_item_tprice);
            viewHolder.textView_feight = (TextView) convertView.findViewById(R.id.tradeorder_item_feight);
            viewHolder.textView_num2 = (TextView) convertView.findViewById(R.id.tradeorder_item_num2);
            viewHolder.mImageView1 = (ImageView) convertView.findViewById(R.id.tradeorder_item_img1);
            viewHolder.mImageView2 = (ImageView) convertView.findViewById(R.id.tradeorder_item_img2);
            viewHolder.mImageView3 = (ImageView) convertView.findViewById(R.id.tradeorder_item_img3);
            viewHolder.textView_status = (TextView) convertView.findViewById(R.id.tradeorder_item_zht);
            viewHolder.mMyListview = (MyListview) convertView.findViewById(R.id.tradeorder_item_listview);
            convertView.setTag(viewHolder);
            mMapViews.put(position, convertView);
            mMapTextViews.put(position, viewHolder.textView_signed);
        } else {
            //convertView = mMapViews.get(position);
            viewHolder = (viewHolder) convertView.getTag();
        }
        try {
            viewHolder.textView_title.setText("订单号：" + mList.get(position).getOrder_sn());
            viewHolder.textView_num2.setText("共 " + mList.get(position).getTotal_num() + "吨");
            viewHolder.textView_tprice.setText("   合计 " + mList.get(position).getTotal_price());
            viewHolder.textView_feight.setText("   (含运费 " + mList.get(position).getTransport() + ")");


            List<OrderListsBean.DataBean.ListBean.ProductBean> listProduct = mList.get(position).getProduct();
            TradeOrderLV_ItemAdapter itemAdapter = new TradeOrderLV_ItemAdapter(context, listProduct);
            viewHolder.mMyListview.setAdapter(itemAdapter);

            //设置按钮的状态
            setStatus(viewHolder, position);

            //签收类
            viewHolder.mImageView1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    TradeOrderListviewAdapter.this.position = position;
                    if (sign.equals("0")) {
                        confirmSign(mList.get(position).getOrder_sn());
                    }
                }
            });
            //申请---
            viewHolder.mImageView2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
//                    if (!billing_status.equals("0")) {
                    if (!mList.get(position).getLast_invoice_status().equals("1")) {
                        if (!mList.get(position).getCollection_status().equals("3")) {
                            mListener.onClick1(mList.get(position).getOrder_sn());
                        } else {
                            TextUtils.Toast(context, "您还未全部付款！");
                        }
                    } else {
                        TextUtils.Toast(context, "您上家采购或销库存的订单还未完全开票！");
                    }
//                    }
                }
            });
            //发票详情
            viewHolder.mImageView3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });


        } catch (Exception e) {
        }
        return convertView;
    }

    private void setStatus(viewHolder viewHolder, int position) {
        sign = mList.get(position).getSign();
        invoice_status = mList.get(position).getInvoice_status();
        billing_status = mList.get(position).getBilling_status();

        if (invoice_status.equals("2")) {
            viewHolder.textView_status.setText("部分开票");
        } else if (invoice_status.equals("3")) {
            viewHolder.mImageView2.setVisibility(View.GONE); //如果全部开票
            viewHolder.textView_status.setText("全部开票");
        }

        if (sign.equals("1")) {
            viewHolder.mImageView1.setImageResource(R.drawable.btn_signed);
        } else {
            viewHolder.mImageView1.setImageResource(R.drawable.btn_submit);
        }

        if (billing_status.equals("1")) {
            viewHolder.mImageView2.setImageResource(R.drawable.btn_in_application);
        } else {
            viewHolder.mImageView2.setImageResource(R.drawable.btn_apply);
        }

        if (invoice_status.equals("2") || invoice_status.equals("3")) {
            viewHolder.mImageView3.setImageResource(R.drawable.btn_invoice_details);
        }
    }

    public void confirmSign(String orderNum) {
        Map<String, String> map = new HashMap<String, String>();
        map.put("order_sn", orderNum);
        BaseActivity.postAsyn(context, API.BASEURL + API.ORDERSIGN, map, this, 1);
    }

    @Override
    public void callBack(Object object, int type) {
        Log.e("-----", object.toString());
//        mMapTextViews.get(position).setVisibility(View.VISIBLE);
//        mMapTextViews.get(position).setText("签收时间：" + mList.get(position).getInput_time());
//        mHandler.postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                mMapTextViews.get(position).setVisibility(View.GONE);
//            }
//        }, 2000);
    }

    @Override
    public void failCallBack(int type) {

    }


    class viewHolder {
        MyListview mMyListview;
        ImageView mImageView1, mImageView2, mImageView3;
        TextView textView_title, textView_tprice, textView_feight, textView_num2, textView_signed, textView_status;
    }

    public interface MyOnClickListener {
        void onClick1(String order_sn);

        void onClick2(int position);

        void onClick3(int position);
    }

    public void setMyOnClickListener(MyOnClickListener myOnClickListener) {
        this.mListener = myOnClickListener;
    }
}
