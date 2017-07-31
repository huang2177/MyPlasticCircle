package com.myplas.q.myinfo.integral.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.myplas.q.R;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * 编写： 黄双
 * 电话：15378412400
 * 邮箱：15378412400@163.com
 * 时间：2017/3/20 22:33
 */
public class Date_Grid_Adapter extends BaseAdapter {
    Context context;
    List<Date> list;

    public Date_Grid_Adapter(Context context, List<Date>list){
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
            convertView= LayoutInflater.from(context).inflate(R.layout.layout_addressselect_listview,null,false);
            viewHolder.name= (TextView) convertView.findViewById(R.id.address_textview);
            convertView.setTag(viewHolder);
        }else {
            viewHolder=(viewHolder)convertView.getTag();
        }
            viewHolder.name.setText(parseDate(list.get(position)));
        return convertView;
    }

    public void setList(List<Date> list) {
        this.list = list;
    }
    public String parseDate(Date date) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            return sdf.format(date).substring(sdf.format(date).indexOf("-")+1);
        } catch (Exception e) {
            return null;
        }
    }

    class viewHolder{
        TextView name;
    }
}
