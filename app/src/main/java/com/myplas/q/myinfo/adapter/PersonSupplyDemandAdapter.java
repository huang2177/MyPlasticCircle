package com.myplas.q.myinfo.adapter;

import android.content.Context;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.myplas.q.R;
import com.myplas.q.myinfo.beans.PersonSupplyDemadBean;

import java.util.List;

/**
 * 编写： 黄双
 * 电话：15378412400
 * 邮箱：15378412400@163.com
 * 时间：2017/3/29 10:57
 */
public class PersonSupplyDemandAdapter extends BaseAdapter {
    Context context;
    List<PersonSupplyDemadBean.DataBean> list;

    public PersonSupplyDemandAdapter(Context context, List<PersonSupplyDemadBean.DataBean> list) {
        this.context = context;
        this.list = list;
    }
    @Override
    public int getCount() {
        if (list.size() != 0)
        return (list.size()>=5)?(5):(list.size());
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
            convertView = LayoutInflater.from(context).inflate(R.layout.person_supplydemand_listview_item, parent, false);
            viewHolder.time = (TextView) convertView.findViewById(R.id.person_supplydemand_listview_text_time);
            viewHolder.show= (TextView) convertView.findViewById(R.id.person_supplydemand_title);
            viewHolder.content = (TextView) convertView.findViewById(R.id.person_supplydemand_listview_text_content);
            viewHolder.linearLayout = (LinearLayout) convertView.findViewById(R.id.linear_layout);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (viewHolder) convertView.getTag();
        }
        viewHolder.time.setText(list.get(position).getInput_time());
        viewHolder.content.setText(list.get(position).getContents());
        String type=list.get(position).getType();
        String demand="<font color='#EEAD0E'>"+"求购:"+"</font>";
        String supply="<font color='#9AC0CD'>"+"供给:"+"</font>";
        viewHolder.show.setText((type.equals("1"))?(Html.fromHtml(demand)):(Html.fromHtml(supply)));
        return convertView;
    }
    class viewHolder {
        TextView content, time,show;
        LinearLayout linearLayout;
    }
}
