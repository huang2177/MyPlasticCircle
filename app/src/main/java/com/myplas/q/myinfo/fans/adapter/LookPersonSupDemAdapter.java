package com.myplas.q.myinfo.fans.adapter;

import android.content.Context;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.myplas.q.R;
import com.myplas.q.addresslist.beans.ContactSupDemBean;

import java.util.List;

/**
 * 编写： 黄双
 * 电话：15378412400
 * 邮箱：15378412400@163.com
 * 时间：2017/3/29 10:57
 */
public class LookPersonSupDemAdapter extends BaseAdapter {
    Context context;
    List<ContactSupDemBean.DataBean> list;
    String type;

    public LookPersonSupDemAdapter(Context context, List<ContactSupDemBean.DataBean> list, String type) {
        this.context = context;
        this.list = list;
        this.type=type;
    }
    @Override
    public int getCount() {
        if (list!= null)
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
            convertView = LayoutInflater.from(context).inflate(R.layout.lookperson_supplydemand_listview_item, parent, false);
            viewHolder.time = (TextView) convertView.findViewById(R.id.lookperson_inputtime);
            viewHolder.content = (TextView) convertView.findViewById(R.id.lookperson_content);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (viewHolder) convertView.getTag();
        }
        viewHolder.time.setText(list.get(position).getInput_time());
        String html=null;
        if (type.equals("供给信息")) {
            html="<font color='#9AC0CD'>"+"供给:"+"</font>"+list.get(position).getContents();
            viewHolder.content.setText(Html.fromHtml(html));
        } else {
            html="<font color='#EEAD0E'>"+"求购:"+"</font>"+list.get(position).getContents();
            viewHolder.content.setText(Html.fromHtml(html));
        }
        return convertView;
    }
    class viewHolder {
        TextView content, time;
        LinearLayout linearLayout;
    }
}
