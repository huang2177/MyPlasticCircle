package com.myplas.q.headlines.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.myplas.q.R;
import com.myplas.q.headlines.bean.SucribleDetailBean;

import java.util.List;

/**
 * 编写： 黄双
 * 电话：15378412400
 * 邮箱：15378412400@163.com
 * 时间：2017/3/21 19:27
 */
public class HeadLinesDetail_More_LVAdapetr extends BaseAdapter {
    Context context;
    List<SucribleDetailBean.InfoBean.HotBean> mHotBeanList;
    List<SucribleDetailBean.InfoBean.SubscribeBean> mSubscribeBeanList;
    viewHolder viewHolder = null;

    public HeadLinesDetail_More_LVAdapetr(Context context
            , List<SucribleDetailBean.InfoBean.HotBean> mHotBeanList
            , List<SucribleDetailBean.InfoBean.SubscribeBean> mSubscribeBeanList) {
        this.context = context;
        this.mHotBeanList = mHotBeanList;
        this.mSubscribeBeanList = mSubscribeBeanList;
    }

    @Override
    public int getCount() {
        if (mHotBeanList == null) {
            if (mSubscribeBeanList != null)
                return mSubscribeBeanList.size();
            return 0;
        } else {
            if (mHotBeanList != null)
                return mHotBeanList.size();
            return 0;
        }
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
        if (convertView == null) {
            viewHolder = new viewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.layout_find_topline_listview_item, parent, false);
            viewHolder.time = (TextView) convertView.findViewById(R.id.fx_tt_title_shj);
            viewHolder.num = (TextView) convertView.findViewById(R.id.fx_tt_title_num);
            viewHolder.title2 = (TextView) convertView.findViewById(R.id.fx_tt_title_text2);
            viewHolder.author = (TextView) convertView.findViewById(R.id.fx_tt_title_author);
            viewHolder.mImgFree = (ImageView) convertView.findViewById(R.id.headline_img_free);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (viewHolder) convertView.getTag();
        }
        try {
            if (mHotBeanList == null) {
                viewHolder.title2.setText(mSubscribeBeanList.get(position).getTitle());
                viewHolder.time.setText(mSubscribeBeanList.get(position).getInput_time());
                viewHolder.author.setText(mSubscribeBeanList.get(position).getPhysical_label());
                viewHolder.num.setText(mSubscribeBeanList.get(position).getPv());
                viewHolder.mImgFree.setVisibility(mSubscribeBeanList.get(position).getIs_free().equals("1")
                        ? View.VISIBLE
                        : View.GONE);
            } else {
                viewHolder.title2.setText(mHotBeanList.get(position).getTitle());
                viewHolder.time.setText(mHotBeanList.get(position).getTime());
                viewHolder.author.setText(mHotBeanList.get(position).getPhysical_label());
                viewHolder.num.setText(mHotBeanList.get(position).getPv());
                viewHolder.mImgFree.setVisibility(mHotBeanList.get(position).getIs_free().equals("1")
                        ? View.VISIBLE
                        : View.GONE);
            }
        } catch (Exception e) {
        }
        return convertView;
    }

    class viewHolder {
        ImageView mImgFree;
        TextView title2, content, time, num, author;
    }

}
