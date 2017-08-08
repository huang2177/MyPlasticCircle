package com.myplas.q.myinfo.adapter;

import android.content.Context;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.myplas.q.R;
import com.myplas.q.common.api.API;
import com.myplas.q.common.netresquset.ResultCallBack;
import com.myplas.q.common.utils.TextUtils;
import com.myplas.q.common.view.MyListview;
import com.myplas.q.guide.activity.BaseActivity;
import com.myplas.q.myinfo.beans.EDuBean;
import com.myplas.q.myinfo.beans.OrderListsBean;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.Observer;
import rx.Subscriber;

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

    private String invoice_status, sign_status, billing_status, isHaveInvoicesDetail;

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
        if (mMapViews.get(position) == null) {
            viewHolder = new viewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.item_layout_tradeorder, null, false);
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
            viewHolder.mView = convertView.findViewById(R.id.tradeorder_item_divider);
            mMapViews.put(position, convertView);
            mMapTextViews.put(position, viewHolder.textView_signed);
            convertView.setTag(viewHolder);
            try {
                if (position == mList.size() - 1) {
                    viewHolder.mView.setVisibility(View.GONE);
                }
                viewHolder.textView_title.setText("订单号：" + mList.get(position).getOrder_sn());
                viewHolder.textView_num2.setText("共 " + mList.get(position).getTotal_num() + "吨");
                viewHolder.textView_tprice.setText("   合计 " + mList.get(position).getTotal_price());
                viewHolder.textView_feight.setText("   (含运费 " + mList.get(position).getTransport() + ")");


                List<OrderListsBean.DataBean.ListBean.ProductBean> listProduct = mList.get(position).getProduct();
                TradeOrderLV_ItemAdapter itemAdapter = new TradeOrderLV_ItemAdapter(context, listProduct);
                viewHolder.mMyListview.setAdapter(itemAdapter);

                setStatus(position);

                //设置按钮的状态
                showStatusInfo(viewHolder);

                //签收类
                viewHolder.mImageView1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        setStatus(position);
                        if (sign_status.equals("0")) {
                            TradeOrderListviewAdapter.this.position = position;
                            confirmSign(mList.get(position).getOrder_sn());
                        } else if (sign_status.equals("2")) {
                            TextUtils.Toast(context, "订单未全部发货，暂无法签收!");
                        }
                    }
                });
                //申请---
                viewHolder.mImageView2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        setStatus(position);
//                        if (billing_status.equals("0")) {
                        mListener.onClick1(mList.get(position).getOrder_sn());
//                        } else if (billing_status.equals("2")) {
//                            TextUtils.Toast(context, "订单未签收或未付款，暂无法开票!");
//                        }
                    }
                });
                //发票详情
                viewHolder.mImageView3.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        setStatus(position);
                        mListener.onClick3(mList.get(position).getOrder_sn());
                    }
                });
            } catch (Exception e) {
            }

        }
        return mMapViews.get(position);
    }

    private void setStatus(int position) {
        sign_status = mList.get(position).getConfirm_receipt();
        invoice_status = mList.get(position).getInvoice_status();
        billing_status = mList.get(position).getApply_billing();
        isHaveInvoicesDetail = mList.get(position).getBilling_detail_list();
    }

    private void showStatusInfo(viewHolder viewHolder) {
        if (invoice_status.equals("1")) {
            viewHolder.textView_status.setText("未开票");
        } else if (invoice_status.equals("2")) {
            viewHolder.textView_status.setText("部分开票");
        } else if (invoice_status.equals("3")) {
            viewHolder.mImageView2.setVisibility(View.GONE); //如果全部开票
            viewHolder.textView_status.setText("全部开票");
        }

        if (sign_status.equals("1")) {//已签收
            viewHolder.mImageView1.setImageResource(R.drawable.btn_signed);
        } else {                      //未签收
            viewHolder.mImageView1.setImageResource(R.drawable.btn_submit);
        }

        if (!billing_status.equals("3")) { //判断是否显示申请开票的按钮
            if (billing_status.equals("1")) { //申请中。。。。
                viewHolder.mImageView2.setImageResource(R.drawable.btn_in_application);
            } else {
                viewHolder.mImageView2.setImageResource(R.drawable.btn_apply);
            }
        } else {
            viewHolder.mImageView2.setVisibility(View.GONE);
        }

        setMarginRight(viewHolder.mImageView1, 30);
        setMarginRight(viewHolder.mImageView2, 0);

//        if (invoice_status.equals("2") || invoice_status.equals("3")) {
//            setMarginRight(viewHolder.mImageView2,30);
//            viewHolder.mImageView3.setImageResource(R.drawable.btn_invoice_details);
//        }
        if (isHaveInvoicesDetail.equals("1") || invoice_status.equals("3")) {
            setMarginRight(viewHolder.mImageView2, 30);
            viewHolder.mImageView3.setImageResource(R.drawable.btn_invoice_details);
        }
    }

    public void setMarginRight(ImageView imageView, int right) {
        LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams) imageView.getLayoutParams();
        lp.rightMargin = right;
        imageView.setLayoutParams(lp);
    }

    public void confirmSign(String orderNum) {
        Map<String, String> map = new HashMap<String, String>();
        map.put("order_sn", orderNum);
        BaseActivity.postAsyn(context, API.BASEURL + API.ORDERSIGN, map, this, 1);
    }

    @Override
    public void callBack(Object object, int type) {
        try {
            Log.e("11111111", object.toString());
            String err = new JSONObject(object.toString()).getString("err");
            if (err.equals("0")) {
                //notifyDataSetChanged();
                //TextUtils.Toast(context,"签收时间：" + mList.get(position).getInput_time());
                mMapTextViews.get(position).setVisibility(View.VISIBLE);
                mMapTextViews.get(position).setText("签收时间：" + mList.get(position).getInput_time());
                mHandler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mMapTextViews.get(position).setVisibility(View.GONE);
                    }
                }, 1500);
            } else {
                String msg = new JSONObject(object.toString()).getString("msg");
                TextUtils.Toast(context, msg);
            }
        } catch (Exception e) {
        }
    }

    @Override
    public void failCallBack(int type) {

    }


    class viewHolder {
        View mView;
        MyListview mMyListview;
        ImageView mImageView1, mImageView2, mImageView3;
        TextView textView_title, textView_tprice, textView_feight, textView_num2, textView_signed, textView_status;
    }

    public interface MyOnClickListener {
        void onClick1(String order_sn);

        void onClick2(int position);

        void onClick3(String order_sn);
    }

    public void setMyOnClickListener(MyOnClickListener myOnClickListener) {
        this.mListener = myOnClickListener;
    }
}
