package com.myplas.q.myself.integral.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.myplas.q.R;
import com.myplas.q.myself.beans.SelectableBean;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 编写： 黄双
 * 电话：15378412400
 * 邮箱：15378412400@163.com
 * 时间：2017/3/29 10:57
 */
public class Integral_Pay_Adapter extends BaseAdapter {
    Context context;
    Map<Integer,View>map_bg;
    Map<Integer,TextView>map_text1;
    Map<Integer,TextView>map_text2;
    List<SelectableBean.DataBean> list;
    public Integral_Pay_Adapter(Context context, List<SelectableBean.DataBean> list) {
        this.context = context;
        this.list = list;
        map_bg=new HashMap<>();
        map_text1=new HashMap<>();
        map_text2=new HashMap<>();
    }

    @Override
    public int getCount() {
        if (list.size() != 0)
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
        if (map_bg.get(position) == null) {
            viewHolder = new viewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.layout_pay_gridview_item, parent, false);
            viewHolder.price = (TextView) convertView.findViewById(R.id.pay_item_price);
            viewHolder.content = (TextView) convertView.findViewById(R.id.pay_item_conent);
            map_bg.put(position,convertView);
            map_text1.put(position,viewHolder.price);
            map_text2.put(position,viewHolder.content);
            if(position==0){
                map_bg.get(position).setBackgroundResource(R.color.color_red);
                map_text1.get(position).setTextColor(context.getResources().getColor(R.color.color_white));
                map_text2.get(position).setTextColor(context.getResources().getColor(R.color.color_white));
            }else {
                map_bg.get(position).setBackgroundColor(context.getResources().getColor(R.color.color_lightgray));
                map_text1.get(position).setTextColor(context.getResources().getColor(R.color.color_red));
                map_text2.get(position).setTextColor(context.getResources().getColor(R.color.color_balank));
            }
            convertView.setTag(viewHolder);
        } else {
            convertView=map_bg.get(position);
            viewHolder = (viewHolder) convertView.getTag();
        }
        viewHolder.price.setText(list.get(position).getMoney()+"元");
        viewHolder.content.setText(list.get(position).getPlasticBean()+"塑豆");
        return convertView;
    }
    class viewHolder {
        TextView content, price;
    }
    public void changColor(int position,boolean isSelected){
        for (int i=0;i<map_bg.size();i++){
            map_bg.get(i).setBackgroundColor(context.getResources().getColor(R.color.color_lightgray));
            map_text1.get(i).setTextColor(context.getResources().getColor(R.color.color_red));
            map_text2.get(i).setTextColor(context.getResources().getColor(R.color.color_balank));
        }
        if (position!=-1) {
            if (isSelected) {
                map_bg.get(position).setBackgroundResource(R.color.color_red);
                map_text1.get(position).setTextColor(context.getResources().getColor(R.color.color_white));
                map_text2.get(position).setTextColor(context.getResources().getColor(R.color.color_white));
            } else {
                map_bg.get(position).setBackgroundColor(context.getResources().getColor(R.color.color_lightgray));
                map_text1.get(position).setTextColor(context.getResources().getColor(R.color.color_red));
                map_text2.get(position).setTextColor(context.getResources().getColor(R.color.color_balank));
            }
        }

    }
}
