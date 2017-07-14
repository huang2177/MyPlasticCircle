package com.myplas.q.headlines.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.myplas.q.R;
import com.myplas.q.headlines.bean.ItemBean;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by 15378 on 2017/3/15.
 */
public class GridViewAdapter extends BaseAdapter {
    Context context;
    List<ItemBean> list;
    Map<Integer, View> map;
    Myinterface myinterface;
    Map<Integer, TextView> map_text;
    Map<Integer, TextView> map_text_bg;

    viewHolder viewHolder = null;

    public GridViewAdapter(Context context, List<ItemBean> list,Myinterface myinterface) {
        this.context = context;
        this.list = list;
        map = new HashMap<>();
        map_text = new HashMap<>();
        map_text_bg=new HashMap<>();
        this.myinterface=myinterface;
    }

    public void setList(List<ItemBean> list) {
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
    public View getView(final int position, View convertView, final ViewGroup parent) {
        viewHolder viewHolder = null;
        if (map.get(position) == null) {
            viewHolder = new viewHolder();
            this.viewHolder = viewHolder;
            convertView = LayoutInflater.from(context).inflate(R.layout.layout_find_recycleview_item, parent, false);
            map.put(position, convertView);
            viewHolder.title = (TextView) convertView.findViewById(R.id.fx_titlebar_show);
            viewHolder.text_bg = (TextView) convertView.findViewById(R.id.text_bg);
            map_text.put(position,viewHolder.title);
            map_text_bg.put(position,viewHolder.text_bg);
            convertView.setTag(viewHolder);
        } else {
            convertView = map.get(position);
            viewHolder = (viewHolder) convertView.getTag();
            this.viewHolder = viewHolder;
        }
        viewHolder.title.setText(list.get(position).getString());
        viewHolder.title.setTextColor(list.get(position).getColor());
        viewHolder.text_bg.setBackgroundColor(list.get(position).getColor());
        final GridViewAdapter.viewHolder finalViewHolder = viewHolder;
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //点击时改变颜色  并将没有点击的item设置为false
               for(int i=0;i<map_text.size();i++){
                   list.get(i).setColor(context.getResources().getColor(R.color.color_balank));
                   map_text.get(i).setTextColor(context.getResources().getColor(R.color.color_balank));
                   map_text_bg.get(i).setBackgroundColor(context.getResources().getColor(R.color.color_balank));
               }
                list.get(position).setColor(context.getResources().getColor(R.color.color_red));
                finalViewHolder.title.setTextColor(context.getResources().getColor(R.color.color_red));
                finalViewHolder.text_bg.setBackgroundColor(context.getResources().getColor(R.color.color_red));

                myinterface.getdata(position);
            }
        });
        return convertView;
    }

    public void chageBackgroudColor(int po) {
        for (int i = 0; i < map_text.size(); i++) {
            list.get(i).setColor(context.getResources().getColor(R.color.color_balank));
            map_text.get(i).setTextColor(context.getResources().getColor(R.color.color_balank));
            map_text_bg.get(i).setBackgroundColor(context.getResources().getColor(R.color.color_balank));
        }
        list.get(po).setColor(context.getResources().getColor(R.color.color_red));
        viewHolder.title.setTextColor(context.getResources().getColor(R.color.color_red));
        viewHolder.text_bg.setBackgroundColor(context.getResources().getColor(R.color.color_red));
    }

    class viewHolder {
        TextView title,text_bg;
    }
    public interface Myinterface {
        void getdata(int position);
    }
}
