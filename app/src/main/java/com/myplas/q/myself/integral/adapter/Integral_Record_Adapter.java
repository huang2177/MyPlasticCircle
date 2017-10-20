package com.myplas.q.myself.integral.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.myplas.q.R;
import com.myplas.q.myself.beans.RecordBean;

import java.util.List;

/**
 * 编写： 黄双
 * 电话：15378412400
 * 邮箱：15378412400@163.com
 * 时间：2017/3/29 10:57
 */
public class Integral_Record_Adapter extends BaseAdapter {
    Context context;
    List<RecordBean.DataBean> list;

    public void setList(List<RecordBean.DataBean> list) {
        this.list = list;
    }

    public Integral_Record_Adapter(Context context, List<RecordBean.DataBean> list) {
        this.context = context;
        this.list = list;
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
        viewHolder viewHolder = null;
        if (convertView == null) {
            viewHolder = new viewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.integral_record_item_layout, parent, false);
            viewHolder.num = (TextView) convertView.findViewById(R.id.integral_record_num);
            viewHolder.title = (TextView) convertView.findViewById(R.id.integral_record_tiele);
            viewHolder.content = (TextView) convertView.findViewById(R.id.integral_record_content);
            viewHolder.buy_date = (TextView) convertView.findViewById(R.id.integral_record_buy_date);
            viewHolder.use_date = (TextView) convertView.findViewById(R.id.integral_record_use_date);
            viewHolder.imageView = (ImageView) convertView.findViewById(R.id.integral_record_img);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (viewHolder) convertView.getTag();
        }
        try {
            String type = list.get(position).getType() + "";
            viewHolder.title.setText(list.get(position).getGoods_name() + "  " + list.get(position).getGoods_num() + "张");
            viewHolder.buy_date.setText("购买日期：" + list.get(position).getPurchase_date());
            viewHolder.use_date.setText("使用日期：" + list.get(position).getStart_time());
            viewHolder.num.setText(list.get(position).getCost_points() + "塑豆");
            Glide.with(context).load(list.get(position).getImage()).placeholder(R.drawable.img_purchase_record1).into(viewHolder.imageView);
            if (type.equals("1")) {
                viewHolder.content.setText("置顶供求信息：" + list.get(position).getContents());
            } else if (type.equals("2")) {
                viewHolder.content.setText("置顶人：" + list.get(position).getUser_name());
            }
        } catch (Exception e) {
        }
        return convertView;
    }

    class viewHolder {
        TextView content, title, buy_date, use_date, num;
        ImageView imageView;
    }
}
