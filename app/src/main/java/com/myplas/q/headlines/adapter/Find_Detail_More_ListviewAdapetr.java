package com.myplas.q.headlines.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.myplas.q.R;
import com.myplas.q.headlines.bean.SucribleDetailBean;

import java.util.List;

/**
 * 编写： 黄双
 * 电话：15378412400
 * 邮箱：15378412400@163.com
 * 时间：2017/3/21 19:27
 */
public class Find_Detail_More_ListviewAdapetr extends BaseAdapter{
    Context context;
    List<SucribleDetailBean.InfoBean.SubscribeBean> l;
    viewHolder viewHolder = null;
    public Find_Detail_More_ListviewAdapetr(Context context, List<SucribleDetailBean.InfoBean.SubscribeBean> l) {
        this.context = context;
        this.l=l;
    }
    @Override
    public int getCount() {
        if (l!=null)
            return l.size();
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
        if (convertView == null) {
            viewHolder = new viewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.layout_find_more_listview_item, parent, false);
            viewHolder.name = (TextView) convertView.findViewById(R.id.title_find_listview);
            viewHolder.time = (TextView) convertView.findViewById(R.id.time_find_listview);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (viewHolder) convertView.getTag();
        }
        viewHolder.name.setText("["+l.get(position).getTitle()+"]");
        viewHolder.time.setText(l.get(position).getInput_time());
        return convertView;
    }
    class viewHolder {
        TextView name,time;
    }

}