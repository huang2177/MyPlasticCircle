package com.myplas.q.myinfo.integral.adapter;

import android.content.Context;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.myplas.q.R;
import com.myplas.q.myinfo.beans.IntegralBean;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 编写： 黄双
 * 电话：15378412400
 * 邮箱：15378412400@163.com
 * 时间：2017/3/29 10:57
 */
public class Integral_SupDem_Adapter extends BaseAdapter {
    Context context;
    List<IntegralBean.InfoBean.MyMsgBean> list;
    Map<Integer,ImageView>map;
    Map<Integer,View>map_view;
    MyInterface myinterface;
    public Integral_SupDem_Adapter(Context context, List<IntegralBean.InfoBean.MyMsgBean> list, MyInterface myinterface) {
        this.context = context;
        this.list = list;
        this.myinterface=myinterface;
        map=new HashMap<>();
        map_view=new HashMap<>();
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
    public View getView(final int position, View convertView, ViewGroup parent) {
        viewHolder viewHolder = null;
        if (map_view.get(position) == null) {
            viewHolder = new viewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.item_lv_integral_supdem, parent, false);
            map_view.put(position,convertView);
            viewHolder.linearLayout = (LinearLayout) convertView.findViewById(R.id.linearlayout1_);
            viewHolder.imageView = (ImageView) convertView.findViewById(R.id.img_check);
            map.put(position,viewHolder.imageView);
            viewHolder.textView= (TextView) convertView.findViewById(R.id.radio_);
            viewHolder.time= (TextView) convertView.findViewById(R.id.radio_1);
            convertView.setTag(viewHolder);
        } else {
            convertView=map_view.get(position);
            viewHolder = (viewHolder) convertView.getTag();
        }
        String html=null;
        viewHolder.time.setText(list.get(position).getInput_time());
        if ("1".equals(list.get(position).getType())) {
            html="<font color='#EEAD0E'>"+"求购:"+"</font>"+list.get(position).getContents();
            viewHolder.textView.setText(Html.fromHtml(html));
        } else {
            html="<font color='#9AC0CD'>"+"供给:"+"</font>"+list.get(position).getContents();
            viewHolder.textView.setText(Html.fromHtml(html));
        }
        return convertView;
    }
    class viewHolder {
        TextView content, time,textView;
        ImageView imageView;
        LinearLayout linearLayout;
    }
    interface MyInterface{
        void select(int position);
    }
    public void changeImg(int position){
        for (int i = 0; i < map.size(); i++) {
            map.get(i).setImageResource(R.drawable.btn_checkbox);
        }
        map.get(position).setImageResource(R.drawable.btn_chekbox_hl1);
        myinterface.select(position);
    }
}
