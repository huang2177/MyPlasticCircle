package com.myplas.q.myinfo.message.adapter;

import android.content.Context;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.myplas.q.R;
import com.myplas.q.myinfo.beans.MyCommentBean;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 编写： 黄双
 * 电话：15378412400
 * 邮箱：15378412400@163.com
 * 时间：2017/3/23 16:29
 */
public class MyCommentAdapter extends BaseAdapter {
    Context context;
    List<MyCommentBean.DataBean> list;
    Map<Integer,View>viewMap;
    public MyCommentAdapter(Context context, List<MyCommentBean.DataBean> list) {
        this.context = context;
        this.list = list;
        viewMap=new HashMap<>();
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
        if (viewMap.get(position) == null) {
            viewHolder = new viewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.mycomment_listview_item, parent, false);
            viewMap.put(position,convertView);
            viewHolder.time = (TextView) convertView.findViewById(R.id.wd_wdqg_shj);
            viewHolder.price = (TextView) convertView.findViewById(R.id.wd_qg_price);
            viewHolder.content = (TextView) convertView.findViewById(R.id.mycomment_content);
            viewHolder.xtxx = (TextView) convertView.findViewById(R.id.wd_qg_xtxx);
            viewHolder.linearLayout= (LinearLayout) convertView.findViewById(R.id.mycomment_says);
            convertView.setTag(viewHolder);
        } else {
            convertView=viewMap.get(position);
            viewHolder = (viewHolder) convertView.getTag();
        }
        viewHolder.content.setText("  我的供给："+list.get(position).getContents());
        viewHolder.time.setText(list.get(position).getInput_time());
        viewHolder.xtxx.setText("在信息库中，没有找到在卖（买）此牌号的商家！");
        viewHolder.price.setText("塑料圈查无此价格！");
        if(list.get(position).getSays().size()!=0){
            TextView textView=null;
            //判断父布局中不为空；
            if (textView==null&&viewHolder.linearLayout.getChildAt(0)==null) {
                textView=new TextView(context);
                viewHolder.linearLayout.addView(textView);
                LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams) textView.getLayoutParams();
                lp.setMargins(10, 15, 10, 15);
                textView.setLayoutParams(lp);
                //String html="<html><body><p style="color:red">This is a paragraph.</p></body></html>";
                for (int i=0;i<list.get(position).getSays().size();i++) {
                    if (i==0) {
                        String html1="<font color='#36648B'>"+list.get(position).getSays().get(0).getUser_name()+"</font>"+" 说:"+list.get(position).getSays().get(0).getContent();
                        textView.setText(Html.fromHtml(html1));
                    }else {
                        textView=new TextView(context);
                        viewHolder.linearLayout.addView(textView);
                        LinearLayout.LayoutParams lp1 = (LinearLayout.LayoutParams) textView.getLayoutParams();
                        lp1.setMargins(10, 10, 10, 15);
                        textView.setLayoutParams(lp1);
                        String html1="<font color='#36648B'>"+list.get(position).getSays().get(i).getUser_name()+"</font>"+"回复"+"<font color='#36648B'>"+list.get(position).getSays().get(i).getRev_name()+"</font>"+":"+list.get(position).getSays().get(i).getContent();
                        textView.setText(Html.fromHtml(html1));
                    }
                }
            }
        }
        return convertView;
    }

    public void setList(List<MyCommentBean.DataBean> list) {
        this.list = list;
    }

    class viewHolder {
        TextView content, time, xtxx,price;
        LinearLayout linearLayout;
    }
}
