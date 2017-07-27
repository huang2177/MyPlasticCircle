package com.myplas.q.myinfo.adapter;

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
import com.myplas.q.myinfo.beans.EDuBean;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 编写：黄双
 * 邮箱：15378412400@163.com
 * 时间： 2017/6/121519.
 */

public class TradeOrderListviewAdapter extends BaseAdapter {
    private Context context;
    private List<EDuBean.DataBean> list;

    private Handler mHandler;
    private MyOnClickListener mListener;

    private Map<Integer, View> mMapViews;
    private Map<Integer, TextView> mMapTextViews;

    public TradeOrderListviewAdapter(Context context, List<EDuBean.DataBean> list) {
        this.list = list;
        mHandler = new Handler();
        this.context = context;
        mMapViews = new HashMap<>();
        mMapTextViews = new HashMap<>();
    }

    @Override
    public int getCount() {
//        if (list != null)
//            return list.size();
        return 7;
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
        viewHolder viewHolder;
        if (mMapViews.get(position) == null) {
            viewHolder = new viewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.item_layout_tradeorder, parent, false);
            viewHolder.textView_signed = (TextView) convertView.findViewById(R.id.popou_layout_signed_text);
            viewHolder.textView_title = (TextView) convertView.findViewById(R.id.tradeorder_item_title);
            viewHolder.textView_content = (TextView) convertView.findViewById(R.id.tradeorder_item_content);
            viewHolder.textView_uprice = (TextView) convertView.findViewById(R.id.tradeorder_item_uprice);
            viewHolder.textView_tprice = (TextView) convertView.findViewById(R.id.tradeorder_item_tprice);
            viewHolder.textView_feight = (TextView) convertView.findViewById(R.id.tradeorder_item_feight);
            viewHolder.textView_num1 = (TextView) convertView.findViewById(R.id.tradeorder_item_num);
            viewHolder.textView_num2 = (TextView) convertView.findViewById(R.id.tradeorder_item_num2);
            viewHolder.mImageView1 = (ImageView) convertView.findViewById(R.id.tradeorder_item_img1);
            viewHolder.mImageView2 = (ImageView) convertView.findViewById(R.id.tradeorder_item_img2);
            convertView.setTag(viewHolder);
            mMapViews.put(position, convertView);
            mMapTextViews.put(position, viewHolder.textView_signed);
        } else {
            convertView = mMapViews.get(position);
            viewHolder = (viewHolder) convertView.getTag();
        }
        try {
//            viewHolder.textView_content.setText(Html.fromHtml(list.get(position).getA()));
//            viewHolder.textView_title.setText("Q:" + Html.fromHtml(list.get(position).getQ()));
            viewHolder.mImageView1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mMapTextViews.get(position).setVisibility(View.VISIBLE);
                    mMapTextViews.get(position).setText("签收时间：2017-07-07 13:30");
                    mHandler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            mMapTextViews.get(position).setVisibility(View.GONE);
                        }
                    }, 2000);
                }
            });
            viewHolder.mImageView2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mListener.onClick2(position);
                }
            });
        } catch (Exception e) {
        }
        return convertView;
    }

    class viewHolder {
        TextView textView_signed;
        ImageView mImageView1, mImageView2;
        TextView textView_title, textView_content, textView_uprice, textView_tprice, textView_feight, textView_num1, textView_num2;
    }

    public interface MyOnClickListener {
        void onClickSigned(View view);

        void onClick2(int position);
    }

    public void setMyOnClickListener(MyOnClickListener myOnClickListener) {
        this.mListener = myOnClickListener;
    }
}
