package com.myplas.q.supdem.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.myplas.q.R;
import com.myplas.q.supdem.Beans.PhysicalBean;

import java.util.List;

/**
 * 编写： 黄双
 * 电话：15378412400
 * 邮箱：15378412400@163.com
 * 时间：2017/3/20 22:33
 */
public class Physical_Property_Adapter extends BaseAdapter {
    Context context;
    List<PhysicalBean.DataBean> list;

    public Physical_Property_Adapter(Context context, List<PhysicalBean.DataBean> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        if (list != null)
            return list.size();
        return 5;
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
            convertView = LayoutInflater.from(context).inflate(R.layout.physical_property_item_layout, null, false);
            viewHolder.variety = (TextView) convertView.findViewById(R.id.physical_variety);
            viewHolder.mark = (TextView) convertView.findViewById(R.id.physical_mark);
            viewHolder.maker = (TextView) convertView.findViewById(R.id.physical_maker);
            viewHolder.content = (TextView) convertView.findViewById(R.id.physical_content);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (viewHolder) convertView.getTag();
        }
        viewHolder.variety.setText(list.get(position).getType());
        viewHolder.mark.setText(list.get(position).getName());
        viewHolder.maker.setText(list.get(position).getCompany());
        viewHolder.content.setText("特性备注:" + list.get(position).getStandard());
        return convertView;
    }

    class viewHolder {
        TextView variety, mark, maker, content;
    }
}
