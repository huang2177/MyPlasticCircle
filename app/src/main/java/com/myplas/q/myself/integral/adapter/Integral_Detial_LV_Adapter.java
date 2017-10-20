package com.myplas.q.myself.integral.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.myplas.q.R;
import com.myplas.q.common.view.SectionedBaseAdapter;
import com.myplas.q.myself.beans.Integraldetialbean;

import java.util.List;

/**
 * 编写： 黄双
 * 电话：15378412400
 * 邮箱：15378412400@163.com
 * 时间：2017/3/29 10:57
 */
public class Integral_Detial_LV_Adapter extends SectionedBaseAdapter {
    Context context;
    List<Integraldetialbean.DataBean.RecordsBean> list;

    public void setList(List<Integraldetialbean.DataBean.RecordsBean> list) {
        this.list = list;
    }

    public Integral_Detial_LV_Adapter(Context context, List<Integraldetialbean.DataBean.RecordsBean> list) {
        this.context = context;
        this.list = list;
    }


    @Override
    public Object getItem(int section, int position) {
        return null;
    }

    @Override
    public long getItemId(int section, int position) {
        return 0;
    }

    @Override
    public int getSectionCount() {
        if (list != null)
            return list.size();
        return 0;
    }

    @Override
    public int getCountForSection(int section) {
        if (list.get(section).getRecord() != null)
            return list.get(section).getRecord().size();
        return 0;
    }

    @Override
    public View getItemView(int section, int position, View convertView, ViewGroup parent) {
        viewHolder viewHolder = null;
        if (convertView == null) {
            viewHolder = new viewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.integral_detial_listview_item, parent, false);
            viewHolder.time = (TextView) convertView.findViewById(R.id.integral_detial_time);
            viewHolder.point = (TextView) convertView.findViewById(R.id.integral_detial_point);
            viewHolder.content = (TextView) convertView.findViewById(R.id.integral_detial_what);
            viewHolder.mImageView = (ImageView) convertView.findViewById(R.id.integral_detail_img);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (viewHolder) convertView.getTag();
        }
        viewHolder.point.setText(list.get(section).getRecord().get(position).getPoints() + "塑豆");
        viewHolder.time.setText(list.get(section).getRecord().get(position).getAddtime());
        viewHolder.content.setText(list.get(section).getRecord().get(position).getTypename());
        Glide.with(context)
                .load(list.get(section).getRecord().get(position).getImg())
                .into(viewHolder.mImageView);
        return convertView;
    }

    @Override
    public View getSectionHeaderView(int section, View convertView, ViewGroup parent) {
        viewHearderHolder viewHolder = null;
        if (convertView == null) {
            viewHolder = new viewHearderHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.item_listview_lookme_header_layout, null, false);
            viewHolder.time = (TextView) convertView.findViewById(R.id.lookme_time);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (viewHearderHolder) convertView.getTag();
        }
        viewHolder.time.setText(list.get(section).getTime());
        return convertView;
    }

    class viewHolder {
        ImageView mImageView;
        TextView content, time, point;
    }

    class viewHearderHolder {
        TextView time;
    }
}
