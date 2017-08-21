package com.myplas.q.myinfo.message.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.myplas.q.R;
import com.myplas.q.common.view.MyImageView;
import com.myplas.q.myinfo.beans.MyMessageBean;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 编写： 黄双
 * 电话：15378412400
 * 邮箱：15378412400@163.com
 * 时间：2017/3/29 10:57
 */
public class MessageListsAdapter extends BaseAdapter {
    Context context;
    Map<Integer, View> mViewMap;
    List<MyMessageBean.DataBean> list;

    public MessageListsAdapter(Context context, List<MyMessageBean.DataBean> list) {
        this.list = list;
        this.context = context;
        mViewMap = new HashMap<>();
    }

    @Override
    public int getCount() {
        if (list != null)
            return list.size();
        return 3;
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
        if (mViewMap.get(position) == null) {
            viewHolder = new viewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.item_messagelist_lv, parent, false);
            viewHolder.mView = convertView.findViewById(R.id.msglist_view_divider);
            viewHolder.dis = (TextView) convertView.findViewById(R.id.msglist_text_dis);
            viewHolder.num = (TextView) convertView.findViewById(R.id.msglist_text_num);
            viewHolder.type = (TextView) convertView.findViewById(R.id.msglist_text_type);
            viewHolder.time = (TextView) convertView.findViewById(R.id.msglist_text_time);
            viewHolder.mImageView = (MyImageView) convertView.findViewById(R.id.msglist_img);
            mViewMap.put(position, convertView);
            convertView.setTag(viewHolder);
        } else {
            convertView = mViewMap.get(position);
            viewHolder = (viewHolder) convertView.getTag();
        }
        viewHolder.mImageView.setBorderColor(Color.WHITE);
        if (position == 2) {
            viewHolder.mView.setVisibility(View.GONE);
        }
//        viewHolder.type.setText(list.get(position).getContent());
//        viewHolder.time.setText(list.get(position).getInput_time());
//        viewHolder.dis.setText(list.get(position).getInput_time());
        return convertView;
    }

    public void setList(List<MyMessageBean.DataBean> list) {
        this.list = list;
    }

    class viewHolder {
        View mView;
        MyImageView mImageView;
        TextView type, time, dis, num;
    }
}
