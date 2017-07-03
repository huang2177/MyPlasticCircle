package com.myplas.q.supdem.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.myplas.q.R;
import com.myplas.q.supdem.Beans.TabCofigBean;

import java.util.List;

/**
 * 编写： 黄双
 * 电话：15378412400
 * 邮箱：15378412400@163.com
 * 时间：2017/3/20 22:33
 */
public class AddressSelectAdapter extends BaseAdapter {
    int type;
    Context context;
    List<TabCofigBean.DataBeanXXX.AreaBean.DataBeanXX> list_area_pro;
    List<TabCofigBean.DataBeanXXX.AreaBean.DataBeanXX.DataBeanX> list_area_city;

    public AddressSelectAdapter(Context context, int type,
                                List<TabCofigBean.DataBeanXXX.AreaBean.DataBeanXX> list_area,
                                List<TabCofigBean.DataBeanXXX.AreaBean.DataBeanXX.DataBeanX> list_area_city) {
        this.type = type;
        this.context = context;
        this.list_area_pro = list_area;
        this.list_area_city = list_area_city;
    }

    @Override
    public int getCount() {
        if (type == 0) {
            if (list_area_pro != null)
                return list_area_pro.size();
            return 0;
        } else {
            if (list_area_city != null)
                return list_area_city.size();
            return 0;
        }
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
            convertView = LayoutInflater.from(context).inflate(R.layout.layout_addressselect_listview_item, null, false);
            viewHolder.name = (TextView) convertView.findViewById(R.id.address_textview);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (viewHolder) convertView.getTag();
        }
        if (type==0) {
            viewHolder.name.setText(list_area_pro.get(position).getShow());
        } else {
            viewHolder.name.setText(list_area_city.get(position).getShow());
        }
        return convertView;
    }

    public void setPosition(int po) {
    }

    class viewHolder {
        TextView name;
    }
}
