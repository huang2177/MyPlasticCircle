package com.myplas.q.myinfo.message.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.myplas.q.R;
import com.myplas.q.myinfo.beans.MyMessageBean;

import java.util.List;

/**
 * 编写： 黄双
 * 电话：15378412400
 * 邮箱：15378412400@163.com
 * 时间：2017/3/29 10:57
 */
public class MyMessageAdapter extends BaseAdapter {
    Context context;
    List<MyMessageBean.DataBean> list;

    public MyMessageAdapter(Context context, List<MyMessageBean.DataBean> list) {
        this.context = context;
        this.list = list;
    }
    @Override
    public int getCount() {
        if (list!=null)
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
            convertView = LayoutInflater.from(context).inflate(R.layout.mymessage_listview_item, parent, false);
            viewHolder.content = (TextView) convertView.findViewById(R.id.mymessage_listview_item_text);
            viewHolder.time = (TextView) convertView.findViewById(R.id.mymessage_listview_item_text1);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (viewHolder) convertView.getTag();
        }
        viewHolder.content.setText(list.get(position).getContent());
        viewHolder.time.setText(list.get(position).getInput_time());
        return convertView;
    }

    public void setList(List<MyMessageBean.DataBean> list) {
        this.list = list;
    }

    class viewHolder {
        TextView content,time;
    }
}
