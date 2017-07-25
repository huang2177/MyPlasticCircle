package com.myplas.q.myinfo.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.myplas.q.R;
import com.myplas.q.myinfo.beans.EDuBean;

import java.util.List;

/**
 * 编写：黄双
 * 邮箱：15378412400@163.com
 * 时间： 2017/6/121519.
 */

public class TradeOrder_Listview_Adapter extends BaseAdapter {
    private Context context;
    private List<EDuBean.DataBean> list;

    public TradeOrder_Listview_Adapter(Context context, List<EDuBean.DataBean> list) {
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
    public View getView(int position, View convertView, ViewGroup parent) {
        viewHolder viewHolder;
        if (convertView == null) {
            viewHolder = new viewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.item_layout_tradeorder, null);
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
        ImageView mImageView1, mImageView2;
        TextView textView_title, textView_content, textView_uprice, textView_tprice, textView_feight, textView_num1, textView_num2;
    }
}
