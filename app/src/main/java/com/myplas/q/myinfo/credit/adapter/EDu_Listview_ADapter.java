package com.myplas.q.myinfo.credit.adapter;

import android.content.Context;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.myplas.q.R;
import com.myplas.q.myinfo.beans.EDuBean;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 编写：黄双
 * 邮箱：15378412400@163.com
 * 时间： 2017/6/121519.
 */

public class EDu_Listview_ADapter extends BaseAdapter {
    private Context context;
    private List<EDuBean.DataBean> list;

    public EDu_Listview_ADapter(Context context, List<EDuBean.DataBean> list) {
        this.list = list;
        this.context = context;
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
    public View getView(int position, View convertView, ViewGroup parent) {
        viewHolder viewHolder;
        if (convertView == null) {
            viewHolder = new viewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.layout_edu_listview_item, null);
            viewHolder.textView_title = (TextView) convertView.findViewById(R.id.textView_title);
            viewHolder.textView_content = (TextView) convertView.findViewById(R.id.textView_content);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (viewHolder) convertView.getTag();
        }
        try {
            viewHolder.textView_content.setText(Html.fromHtml(list.get(position).getA()));
            viewHolder.textView_title.setText("Q:" + Html.fromHtml(list.get(position).getQ()));
            if(position%3==0){
                viewHolder.textView_title.setBackgroundResource(R.drawable.bg_assets_question1);
            }else if (position%3==1){
                viewHolder.textView_title.setBackgroundResource(R.drawable.bg_assets_question2);
            }else {
                viewHolder.textView_title.setBackgroundResource(R.drawable.bg_assets_question3);
            }
        } catch (Exception e) {
        }
        return convertView;
    }

    class viewHolder {
        TextView textView_title, textView_content;
    }
}
