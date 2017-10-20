package com.myplas.q.myself.invoices.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.myplas.q.R;
import com.myplas.q.myself.beans.InvoiceDetailBean;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 编写：黄双
 * 邮箱：15378412400@163.com
 * 时间： 2017/6/121519.
 */

public class InvoiceListviewAdapter extends BaseAdapter {
    private Context context;
    private List<InvoiceDetailBean.DataBean> list;

    private Map<Integer, View> mMap;

    public InvoiceListviewAdapter(Context context, List<InvoiceDetailBean.DataBean> list) {
        this.list = list;
        this.context = context;
        mMap = new HashMap<>();
    }

    @Override
    public int getCount() {
        if (list != null)
            return list.size();
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
        if (mMap.get(position) == null) {
            viewHolder = new viewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.item_listview_invoices, parent, false);
            viewHolder.textView_num = (TextView) convertView.findViewById(R.id.item_lv_invoice_num);
            viewHolder.textView_time = (TextView) convertView.findViewById(R.id.item_lv_invoice_time);
            viewHolder.textView_tprice = (TextView) convertView.findViewById(R.id.item_lv_invoice_tprice);
            viewHolder.mLayout = (LinearLayout) convertView.findViewById(R.id.item_lv_invioces_ll_hch);
            convertView.setTag(viewHolder);
            mMap.put(position, convertView);
        } else {
            convertView = mMap.get(position);
            viewHolder = (viewHolder) convertView.getTag();
        }
        try {
            viewHolder.textView_num.setText(list.get(position).getInvoice_sn());
            viewHolder.textView_time.setText(list.get(position).getInput_time());
            viewHolder.textView_tprice.setText(list.get(position).getBilling_price());
            if (list.get(position).getInvoice_status().equals("3")) {
                viewHolder.mLayout.setVisibility(View.VISIBLE);
            }
        } catch (Exception e) {
        }
        return convertView;
    }

    class viewHolder {
        LinearLayout mLayout;
        TextView textView_num, textView_time, textView_tprice;
    }

}
