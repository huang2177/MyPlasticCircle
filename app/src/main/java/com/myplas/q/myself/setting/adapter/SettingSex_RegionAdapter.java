package com.myplas.q.myself.setting.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.myplas.q.R;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 编写： 黄双
 * 电话：15378412400
 * 邮箱：15378412400@163.com
 * 时间：2017/3/29 10:57
 */
public class SettingSex_RegionAdapter extends BaseAdapter {
    Context context;
    Map<Integer, ImageView> map;
    Map<Integer, View> map_view;

    int position;
    List<String> list;


    public SettingSex_RegionAdapter(Context context, List<String> list, int position) {
        this.list = list;
        this.context = context;
        this.position = position;

        map = new HashMap<>();
        map_view = new HashMap<>();
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
        if (map_view.get(position) == null) {
            viewHolder = new viewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.item_lv_setting_data_sexregion, parent, false);
            viewHolder.imageView = (ImageView) convertView.findViewById(R.id.item_lv_data_img);
            viewHolder.content = (TextView) convertView.findViewById(R.id.item_lv_data_text);

            convertView.setTag(viewHolder);
            map_view.put(position, convertView);
            map.put(position, viewHolder.imageView);
        } else {
            convertView = map_view.get(position);
            viewHolder = (viewHolder) convertView.getTag();
        }
        map.get(position).setImageResource((this.position == position) ?
                (R.drawable.btn_radio_checked) :
                (R.drawable.btn_radio));
        viewHolder.content.setText(list.get(position));
        return convertView;
    }

    class viewHolder {
        TextView content;
        ImageView imageView;
    }

    interface MyInterface {
        void select(int position);
    }

    public void changeImg(int position) {
        for (int i = 0; i < map.size(); i++) {
            map.get(i).setImageResource(R.drawable.btn_radio);
        }
        map.get(position).setImageResource(R.drawable.btn_radio_checked);
        //myinterface.select(position);
    }
}
