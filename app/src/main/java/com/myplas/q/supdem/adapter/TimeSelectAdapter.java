package com.myplas.q.supdem.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.myplas.q.R;
import com.myplas.q.supdem.Beans.TabCofigBean;
import com.myplas.q.supdem.popoushowutils.PopouShowUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 编写： 黄双
 * 电话：15378412400
 * 邮箱：15378412400@163.com
 * 时间：2017/3/20 22:33
 */
public class TimeSelectAdapter extends BaseAdapter {
    Context context;
    Map<Integer, View> map_view;
    Map<Integer, ImageView> map_img;
    List<PopouShowUtils.ItemBean> list;
    List<TabCofigBean.DataBeanXXX.TimeBean.DataBean> list_time;

    public TimeSelectAdapter(Context context, List<TabCofigBean.DataBeanXXX.TimeBean.DataBean> list_time,List<PopouShowUtils.ItemBean> list) {
        this.list=list;
        this.context = context;
        this.list_time = list_time;
        map_img = new HashMap<>();
        map_view = new HashMap<>();
    }

    public void setList_isSelect(List<PopouShowUtils.ItemBean> list) {
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
    public View getView(int position, View convertView, ViewGroup parent) {
        viewHolder viewHolder = null;
        if (map_view.get(position)==null) {
            viewHolder = new viewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.layout_timeselect_listview_item, parent, false);
            viewHolder.name = (TextView) convertView.findViewById(R.id.time_textview);
            viewHolder.imageView = (ImageView) convertView.findViewById(R.id.time_imgview);

            map_view.put(position, convertView);
            map_img.put(position, viewHolder.imageView);
            if (list.get(position).isSelected()) {
                viewHolder.imageView.setImageResource(R.drawable.icon_checked);
            }
            convertView.setTag(viewHolder);
        } else {
            convertView=map_view.get(position);
            viewHolder = (viewHolder) convertView.getTag();
        }

        viewHolder.name.setText(list_time.get(position).getShow());

        return convertView;
    }

    public void setPosition(int position) {
        for (int i = 0; i < map_img.size(); i++) {
            map_img.get(position).setImageResource((position == i) ? (R.drawable.icon_checked) : (-1));
        }
    }

    class viewHolder {
        TextView name;
        ImageView imageView;
    }
}
