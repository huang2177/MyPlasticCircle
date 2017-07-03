package com.myplas.q.headlines.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.myplas.q.R;
import com.myplas.q.headlines.bean.SubcribleBean;

import java.util.List;

/**
 * 编写： 黄双
 * 电话：15378412400
 * 邮箱：15378412400@163.com
 * 时间：2017/3/21 15:40
 */
public class TTAdapter extends BaseAdapter {
    Context context;
    List<SubcribleBean.DataBean> list;
    public TTAdapter(Context context, List<SubcribleBean.DataBean> list){
        this.context=context;
        this.list=list;
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
    public View getView(int position, View convertView, ViewGroup parent) {
        viewHolder viewHolder = null;
        if(convertView==null){
            viewHolder=new viewHolder();
            convertView= LayoutInflater.from(context).inflate(R.layout.layout_find_topline_listview_item,parent,false);
            viewHolder.time= (TextView) convertView.findViewById(R.id.fx_tt_title_shj);
            viewHolder.num= (TextView) convertView.findViewById(R.id.fx_tt_title_num);
            viewHolder.title= (TextView) convertView.findViewById(R.id.fx_tt_title_text);
            viewHolder.content= (TextView) convertView.findViewById(R.id.fx_tt_title_content);
            viewHolder.author= (TextView) convertView.findViewById(R.id.fx_tt_title_author);
            convertView.setTag(viewHolder);
        }else {
            viewHolder=(viewHolder)convertView.getTag();
        }
        viewHolder.content.setText(list.get(position).getDescription());
        viewHolder.num.setText("  " +list.get(position).getPv());
        viewHolder.title.setText("["+list.get(position).getType()+"]"+list.get(position).getTitle());
        viewHolder.time.setText("  " + list.get(position).getInput_time());
        viewHolder.author.setText(list.get(position).getAuthor());
        return convertView;
    }

    public void setList(List<SubcribleBean.DataBean> list) {
        this.list = list;
    }

    class viewHolder{
        TextView title,content,time,num,author;
    }
}
