package com.myplas.q.myinfo.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.myplas.q.R;
import com.myplas.q.myinfo.beans.MyFollowBean;

import java.util.List;

/**
 * 编写： 黄双
 * 电话：15378412400
 * 邮箱：15378412400@163.com
 * 时间：2017/3/20 22:33
 */
public class LookMeAdapter extends BaseAdapter {
    Context context;
    List<MyFollowBean.DataBean> list;

    public LookMeAdapter(Context context, List<MyFollowBean.DataBean> list) {
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
    public View getView(int position, View convertView, ViewGroup parent) {
        viewHolder viewHolder = null;
        if (convertView == null) {
            viewHolder = new viewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.lookme_listview_item, parent, false);
            viewHolder.tx = (ImageView) convertView.findViewById(R.id.xq_tx);
            viewHolder.rz = (ImageView) convertView.findViewById(R.id.xq_rz);
            viewHolder.gs = (TextView) convertView.findViewById(R.id.lookme_listview_gs);
            viewHolder.time = (TextView) convertView.findViewById(R.id.lookme_time);
            viewHolder.time_detail = (TextView) convertView.findViewById(R.id.lookme_listview_time);
            viewHolder.layout = (LinearLayout) convertView.findViewById(R.id.lookme_time_layout);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (viewHolder) convertView.getTag();
        }
        try {
            Glide.with(context).load(list.get(position).getFocused_id().getThumb()).
                    placeholder(R.drawable.contact_image_defaul_male).into(viewHolder.tx);
            viewHolder.gs.setText("  " + list.get(position).getFocused_id().getC_name());
            viewHolder.time.setText("  " + list.get(position).getFocused_id().getMobile());
            viewHolder.time_detail.setText("  " + list.get(position).getFocused_id().getName());
            if (list.get(position).getFocused_id().getIs_pass().equals("0")) {
                viewHolder.rz.setImageResource(R.drawable.icon_identity);
            } else if (list.get(position).getFocused_id().getIs_pass().equals("1")) {
                viewHolder.rz.setImageResource(R.drawable.icon_identity_hl);
            }
        } catch (Exception e) {
        }
        return convertView;
    }

    public void setList(List<MyFollowBean.DataBean> list) {
        this.list = list;
    }

    class viewHolder {
        ImageView tx, rz;
        LinearLayout layout;
        TextView gs, time, time_detail;
    }
}
