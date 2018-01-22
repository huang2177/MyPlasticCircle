package com.myplas.q.homepage.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.myplas.q.R;
import com.myplas.q.common.listener.MyOnItemClickListener;
import com.myplas.q.homepage.beans.BlackListsDetailBean;
import com.myplas.q.supdem.beans.ReplyBean;

import java.util.List;

/**
 * 编写： 黄双
 * 电话：15378412400
 * 邮箱：15378412400@163.com
 * 时间：2017/3/20 17:12
 */
public class BlackListDetailChildAdapter extends BaseAdapter {
    Context context;
    MyOnItemClickListener mListener;
    List<BlackListsDetailBean.BlacklistBean.CommentsBeanX.CommentsBean> list;

    public void setList(List<BlackListsDetailBean.BlacklistBean.CommentsBeanX.CommentsBean> list) {
        this.list = list;
    }

    public BlackListDetailChildAdapter(Context context, MyOnItemClickListener myListener) {
        this.mListener = myListener;
        this.context = context;
    }

    @Override

    public int getCount() {
        return (list != null) ? (list.size()) : (0);
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
            convertView = LayoutInflater.from(context).inflate(R.layout.item_layout_blacklist_detail_child, parent, false);
            viewHolder.mTVTime = (TextView) convertView.findViewById(R.id.item_time);
            viewHolder.mIVHead = (ImageView) convertView.findViewById(R.id.item_head);
            viewHolder.mTVReply = (TextView) convertView.findViewById(R.id.item_reply);
            viewHolder.mTVCompany = (TextView) convertView.findViewById(R.id.item_cmpany);
            viewHolder.mTVContent = (TextView) convertView.findViewById(R.id.item_content);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (viewHolder) convertView.getTag();
        }
        try {
            viewHolder.mTVContent.setText(list.get(position).getComment());
            viewHolder.mTVTime.setText(list.get(position).getCreated_at());
            viewHolder.mTVCompany.setText(list.get(position).getName() + "回复");

            Glide.with(context)
                    .load(list.get(position).getAvatar())
                    .placeholder(R.drawable.img_defaul_male)
                    .into(viewHolder.mIVHead);

             /*二三级回复的点击事件*/
            viewHolder.mTVReply.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mListener != null) {
                        mListener.onItemClick(list.get(position).getId()
                                , list.get(position).getName()
                                , ""
                                , ""
                                , "");
                    }
                }
            });
        } catch (Exception e) {
        }
        return convertView;
    }

    class viewHolder {
        ImageView mIVHead;
        TextView mTVCompany, mTVContent, mTVTime, mTVReply;
    }
}
