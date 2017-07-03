package com.myplas.q.headlines.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.myplas.q.R;
import com.myplas.q.headlines.bean.CompanyBean;

import java.util.List;

/**
 * 编写： 黄双
 * 电话：15378412400
 * 邮箱：15378412400@163.com
 * 时间：2017/4/5 18:25
 */
public class CompanyAdapter extends BaseAdapter {
    Context context;
    List<CompanyBean.DataBean> list;
    public CompanyAdapter(Context context, List<CompanyBean.DataBean> list){
        this.context=context;
        this.list=list;
    }
    @Override
    public int getCount() {
        if (list!=null)
            return (list.size()>10)?(10):(list.size());
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
        if(convertView==null){
            viewHolder=new viewHolder();
            convertView= LayoutInflater.from(context).inflate(R.layout.layout_company_listview_item,parent,false);
            viewHolder.title= (TextView) convertView.findViewById(R.id.company_listview_title);
            convertView.setTag(viewHolder);
        }else {
            viewHolder=(viewHolder)convertView.getTag();
        }
        try {
            viewHolder.title.setText(list.get(position).getC_name());
        } catch (Exception e) {
        }
        return convertView;
    }
    class viewHolder{
        TextView title;
    }
}
