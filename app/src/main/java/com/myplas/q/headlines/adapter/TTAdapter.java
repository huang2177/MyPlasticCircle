package com.myplas.q.headlines.adapter;

import android.content.Context;
import android.text.Html;
import android.text.Spanned;
import android.util.Log;
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
            viewHolder.title1 = (TextView) convertView.findViewById(R.id.fx_tt_title_text1);
            viewHolder.title2 = (TextView) convertView.findViewById(R.id.fx_tt_title_text2);
            viewHolder.content= (TextView) convertView.findViewById(R.id.fx_tt_title_content);
            viewHolder.author= (TextView) convertView.findViewById(R.id.fx_tt_title_author);
            convertView.setTag(viewHolder);
        }else {
            viewHolder=(viewHolder)convertView.getTag();
        }
        viewHolder.content.setText(replaceContent(list.get(position).getDescription()));
        viewHolder.num.setText("  " +list.get(position).getPv());
        //viewHolder.title1.setText(replaceContent(list.get(position).getType()));
        viewHolder.title2.setText(replaceContent(list.get(position).getTitle()));
        viewHolder.time.setText("  " + list.get(position).getInput_time());
        viewHolder.author.setText(replaceContent(list.get(position).getType()));
        return convertView;
    }

    public void setList(List<SubcribleBean.DataBean> list) {
        this.list = list;
    }

    class viewHolder{
        TextView title1, title2, content, time, num, author;
    }

    public Spanned replace(String s) {
        s = s.replace("<span style='color: #ff5000;'>", "<font color='#ff5000'><b>");
        s = s.replace("</span>", "</b></font>");
        Spanned s1 = Html.fromHtml(s);
        return s1;
    }

    public Spanned replaceContent(String s) {
        s = s.replace("<span style='color: #FF0000;'>", "<font color='#FF0000'><b>");
        s = s.replace("</span>", "</b></font>");
        Spanned s1 = Html.fromHtml(s);
        return s1;
    }
}
