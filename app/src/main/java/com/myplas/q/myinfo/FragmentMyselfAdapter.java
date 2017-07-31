package com.myplas.q.myinfo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.myplas.q.R;
import com.myplas.q.myinfo.beans.Integraldetialbean;

import java.util.List;

/**
 * Created by Administrator on 2017/7/24 0024.
 */

public class FragmentMyselfAdapter extends BaseAdapter {
    Context context;
    List<Integraldetialbean.DataBean> list;

    public void setList(List<Integraldetialbean.DataBean> list) {
        this.list = list;
    }

    public FragmentMyselfAdapter(Context context, List<Integraldetialbean.DataBean> list) {
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
            convertView = LayoutInflater.from(context).inflate(R.layout.integral_detial_listview_item, parent, false);
            viewHolder.time = (TextView) convertView.findViewById(R.id.integral_detial_time);
            viewHolder.point = (TextView) convertView.findViewById(R.id.integral_detial_point);
            viewHolder.content = (TextView) convertView.findViewById(R.id.integral_detial_what);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (viewHolder) convertView.getTag();
        }
        viewHolder.point.setText("塑豆：" + list.get(position).getPoints());
        viewHolder.time.setText(list.get(position).getAddtime());
        viewHolder.content.setText(list.get(position).getTypename());
        return convertView;
    }

    class viewHolder {
        TextView content, time, point;
    }
}
