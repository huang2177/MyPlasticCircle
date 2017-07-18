package com.myplas.q.supdem.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.myplas.q.R;
import com.myplas.q.supdem.Beans.TabCofigBean;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 编写： 黄双
 * 电话：15378412400
 * 邮箱：15378412400@163.com
 * 时间：2017/3/20 22:33
 */
public class AddressSelectAdapter extends BaseAdapter {
    Context context;
    boolean isMarked;
    int type, selectedItem;
    Map<Integer, View> map_pro, map_city;
    List<TabCofigBean.DataBeanXXX.AreaBean.DataBeanXX> list_area_pro;
    List<TabCofigBean.DataBeanXXX.AreaBean.DataBeanXX.DataBeanX> list_area_city;

    public AddressSelectAdapter(Context context, int type, int po,
                                List<TabCofigBean.DataBeanXXX.AreaBean.DataBeanXX> list_area,
                                List<TabCofigBean.DataBeanXXX.AreaBean.DataBeanXX.DataBeanX> list_area_city) {
        this.type = type;
        this.selectedItem = po;
        this.context = context;
        this.list_area_pro = list_area;
        this.list_area_city = list_area_city;

        map_pro = new HashMap<>();
        map_city = new HashMap<>();
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
        switch (type) {
            case 0:
                if (map_pro.get(position) == null) {
                    viewHolder = new viewHolder();
                    convertView = LayoutInflater.from(context).inflate(R.layout.layout_addressselect_listview_item, null, false);
                    viewHolder.name = (TextView) convertView.findViewById(R.id.address_textview);
                    convertView.setTag(viewHolder);
                } else {
                    convertView = map_pro.get(position);
                    viewHolder = (viewHolder) convertView.getTag();
                }
                map_pro.put(position, convertView);
                viewHolder.name.setText(list_area_pro.get(position).getShow());
                if (position == selectedItem) {
                    map_pro.get(selectedItem).setBackgroundColor((isMarked) ?
                            (context.getResources().getColor(R.color.color_white)) :
                            (context.getResources().getColor(R.color.color_gray1)));
                }
                break;
            case 1:
                if (map_city.get(position) == null) {
                    viewHolder = new viewHolder();
                    convertView = LayoutInflater.from(context).inflate(R.layout.layout_addressselect_listview_item, null, false);
                    viewHolder.name = (TextView) convertView.findViewById(R.id.address_textview);
                    convertView.setTag(viewHolder);
                } else {
                    convertView = map_city.get(position);
                    viewHolder = (viewHolder) convertView.getTag();
                }
                map_city.put(position, convertView);
                viewHolder.name.setText(list_area_city.get(position).getShow());
                break;
        }
        return convertView;
    }
    class viewHolder {
        TextView name;
    }

    public void setSelectedItem(boolean isMarked) {
        this.isMarked = isMarked;
    }
}
