package com.myplas.q.myinfo.adapter;

import android.content.Context;
import android.os.Handler;
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

public class InvoiceListviewAdapter extends BaseAdapter {
    private Context context;
    private List<EDuBean.DataBean> list;


    public InvoiceListviewAdapter(Context context, List<EDuBean.DataBean> list) {
        this.list = list;
        this.context = context;
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
        if (convertView == null) {
            viewHolder = new viewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.item_listview_invoices, parent, false);
            viewHolder.textView_num = (TextView) convertView.findViewById(R.id.item_lv_invoice_num);
            viewHolder.textView_time = (TextView) convertView.findViewById(R.id.item_lv_invoice_time);
            viewHolder.textView_tprice = (TextView) convertView.findViewById(R.id.item_lv_invoice_tprice);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (viewHolder) convertView.getTag();
        }
        try {
//            viewHolder.textView_content.setText(Html.fromHtml(list.get(position).getA()));
//            viewHolder.textView_title.setText("Q:" + Html.fromHtml(list.get(position).getQ()));

        } catch (Exception e) {
        }
        return convertView;
    }

    class viewHolder {
        TextView textView_num, textView_time, textView_tprice;
    }

}
