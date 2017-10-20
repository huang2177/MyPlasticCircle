package com.myplas.q.myself.integral.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.myplas.q.R;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 编写： 黄双
 * 电话：15378412400
 * 邮箱：15378412400@163.com
 * 时间：2017/3/29 10:57
 */
public class Integral_Detial_GV_Adapter extends BaseAdapter {
    int position;
    Context context;
    List<String> list;
    Map<Integer, View> mViewMap;
    Map<Integer, TextView> mTextViewMap;

    public Integral_Detial_GV_Adapter(Context context, List<String> list, int position) {
        this.list = list;
        this.context = context;
        this.position = position;
        mViewMap = new HashMap<>();
        mTextViewMap = new HashMap<>();
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
        if (mViewMap.get(position) == null) {
            viewHolder = new viewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.supdem_search_grid_item_layout, parent, false);
            viewHolder.mTextView = (TextView) convertView.findViewById(R.id.item_search_text);
            mViewMap.put(position, convertView);
            mTextViewMap.put(position, viewHolder.mTextView);
            convertView.setTag(viewHolder);
        } else {
            convertView = mViewMap.get(position);
            viewHolder = (viewHolder) convertView.getTag();
        }
        if (position == this.position) {
            viewHolder.mTextView.setBackgroundResource(R.drawable.btn_shape_red_coners);
            viewHolder.mTextView.setTextColor(context.getResources().getColor(R.color.colorAccent));
        }
        viewHolder.mTextView.setText(list.get(position));
        return convertView;
    }

    class viewHolder {
        TextView mTextView;
    }

    interface MyInterface {
        void select(int position);
    }

    public void changeImg(int position) {
        for (int i = 0; i < mViewMap.size(); i++) {
            mTextViewMap.get(i).setTextColor(context.getResources().getColor(R.color.color_balank));
            mTextViewMap.get(i).setBackgroundResource(R.drawable.corner_titlebar);
        }
        mTextViewMap.get(position).setBackgroundResource(R.drawable.btn_shape_red_coners);
        mTextViewMap.get(position).setTextColor(context.getResources().getColor(R.color.colorAccent));
    }
}
