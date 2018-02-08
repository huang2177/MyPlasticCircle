package com.myplas.q.myself.integral.adapter;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.myplas.q.R;
import com.myplas.q.myself.beans.NewIntergralBean;

import java.util.List;

/**
 * @author 黄双
 * @date 2018/2/5 0005
 */

public class NewIntegralDetialLVAdapter extends BaseAdapter {
    private Context context;
    private List<NewIntergralBean.DataBean> data;

    private int green, red;

    public NewIntegralDetialLVAdapter(Context context, List<NewIntergralBean.DataBean> data) {
        this.data = data;
        this.context = context;
        red = ContextCompat.getColor(context, R.color.color_Red);
        green = ContextCompat.getColor(context, R.color.color_green);
    }

    @Override
    public int getCount() {
        return data != null && data.size() != 0 ? data.size() : 0;
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
        ViewHolder viewHolder = null;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.new_integral_detial_lv_item, null);
            viewHolder.time = (TextView) convertView.findViewById(R.id.detial_time);
            viewHolder.type = (TextView) convertView.findViewById(R.id.detial_type);
            viewHolder.detail = (TextView) convertView.findViewById(R.id.detial_detail);
            viewHolder.balancePoint = (TextView) convertView.findViewById(R.id.detial_balance_point);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        NewIntergralBean.DataBean dataBean = data.get(position);
        if (dataBean != null) {
            viewHolder.type.setText(dataBean.getType());
            viewHolder.time.setText(dataBean.getAddtime());
            viewHolder.detail.setText(dataBean.getPoints() + "塑豆");
            viewHolder.balancePoint.setText("余数：" + dataBean.getRemainder());

            viewHolder.detail.setTextColor(dataBean.getPoints().contains("+") ? red : green);
        }

        return convertView;
    }

    public void setList(List<NewIntergralBean.DataBean> list) {
        this.data = list;
    }

    class ViewHolder {
        TextView type, balancePoint, detail, time;
    }
}
