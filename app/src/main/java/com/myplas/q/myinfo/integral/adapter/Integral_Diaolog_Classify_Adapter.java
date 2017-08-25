package com.myplas.q.myinfo.integral.adapter;

import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;
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
public class Integral_Diaolog_Classify_Adapter extends BaseAdapter {
    Context context;
    int defPosition;
    MyInterface myinterface;
    Map<Integer, View> mViewMap;
    Map<Integer, TextView> mTextViewMap;
    List<IntegralBean.InfoBean.ExtraConfigBean> mExtraConfigList;
    List<IntegralBean.InfoBean.ExtraConfigBean.ChildrenBean> mChildrenList;


    public Integral_Diaolog_Classify_Adapter(Context context
            , List<IntegralBean.InfoBean.ExtraConfigBean> mExtraConfigList
            , List<IntegralBean.InfoBean.ExtraConfigBean.ChildrenBean> mChildrenList
            , int position) {

        this.context = context;
        mViewMap = new HashMap<>();
        this.defPosition = position;
        mTextViewMap = new HashMap<>();
        this.myinterface = myinterface;
        this.mChildrenList = mChildrenList;
        this.mExtraConfigList = mExtraConfigList;
    }

    @Override
    public int getCount() {
        if (mExtraConfigList == null) {
            if (mChildrenList != null)
                return mChildrenList.size();
            return 0;
        } else {
            if (mExtraConfigList != null)
                return mExtraConfigList.size();
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
    public View getView(final int position, View convertView, ViewGroup parent) {
        viewHolder viewHolder = null;
        if (mViewMap.get(position) == null) {
            viewHolder = new viewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.item_lv_integral_classify, parent, false);
            viewHolder.mTextView = (TextView) convertView.findViewById(R.id.item_lv_integral_classify_text);
            mViewMap.put(position, convertView);
            mTextViewMap.put(position, viewHolder.mTextView);
            convertView.setTag(viewHolder);
        } else {
            convertView = mViewMap.get(position);
            viewHolder = (viewHolder) convertView.getTag();
        }
        if (position == defPosition) {
            viewHolder.mTextView.setBackgroundResource(R.drawable.corners_integral_classify);
            viewHolder.mTextView.setTextColor(context.getResources().getColor(R.color.colorAccent));
        }
        if (mExtraConfigList == null) {
            viewHolder.mTextView.setText(mChildrenList.get(position).getCate_name());
        } else {
            viewHolder.mTextView.setText(mExtraConfigList.get(position).getCate_name());
        }
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
            mTextViewMap.get(i).setTextColor(context.getResources().getColor(R.color.color_gray3));
            mTextViewMap.get(i).setBackgroundResource(R.drawable.corners_integral_claaify_unslected);
        }
        mTextViewMap.get(position).setBackgroundResource(R.drawable.corners_integral_classify);
        mTextViewMap.get(position).setTextColor(context.getResources().getColor(R.color.colorAccent));
        //myinterface.select(position);
    }
}
