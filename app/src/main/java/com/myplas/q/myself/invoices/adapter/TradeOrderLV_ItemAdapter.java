package com.myplas.q.myself.invoices.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.myplas.q.R;
import com.myplas.q.myself.beans.OrderListsBean;

import java.util.List;

/**
 * 编写：黄双
 * 邮箱：15378412400@163.com
 * 时间： 2017/6/121519.
 */

public class TradeOrderLV_ItemAdapter extends BaseAdapter {
    private Context context;
    private List<OrderListsBean.DataBean.ProductBean> listProduct;

    public TradeOrderLV_ItemAdapter(Context context, List<OrderListsBean.DataBean.ProductBean> listProduct) {
        this.context = context;
        this.listProduct = listProduct;
    }

    @Override
    public int getCount() {
        if (listProduct != null) {
            return listProduct.size();
        }
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
        viewHolder viewHolder;
        if (convertView == null) {
            viewHolder = new viewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.item_layout_tradeorder_lv, parent, false);
            viewHolder.textView_content = (TextView) convertView.findViewById(R.id.tradeorder_item_content);
            viewHolder.textView_uprice = (TextView) convertView.findViewById(R.id.tradeorder_item_uprice);
            viewHolder.textView_num1 = (TextView) convertView.findViewById(R.id.tradeorder_item_num);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (viewHolder) convertView.getTag();
        }
        try {
            viewHolder.textView_content.setText(listProduct.get(position).getF_name() + " " +
                    listProduct.get(position).getModel());
            viewHolder.textView_uprice.setText("¥" + listProduct.get(position).getUnit_price());
            viewHolder.textView_num1.setText("x" + listProduct.get(position).getInit());
        } catch (Exception e) {
        }
        return convertView;
    }

    class viewHolder {
        TextView textView_content, textView_uprice, textView_num1;
    }
}
