package com.myplas.q.homepage.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.myplas.q.R;
import com.myplas.q.common.listener.MyOnItemClickListener;
import com.myplas.q.common.view.MyListview;
import com.myplas.q.homepage.beans.BlackListsDetailBean;
import com.myplas.q.supdem.beans.ReplyBean;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 电话：15378412400
 * 邮箱：15378412400@163.com
 * 时间：2017/3/20 17:12
 *
 * @author Administrator
 */
public class BlackListDetailAdapter extends RecyclerView.Adapter {
    Context context;
    MyOnItemClickListener mListener;
    List<BlackListsDetailBean.BlacklistBean.CommentsBeanX> list;
    SparseArray<viewHolder> mHolderMap;
    BlackListDetailChildAdapter mChildAdapter;
    private float y;

    public BlackListDetailAdapter(Context context, MyOnItemClickListener myOnItemClickListener) {
        this.context = context;
        mHolderMap = new SparseArray<>();
        this.mListener = myOnItemClickListener;
    }

    public void setList(List<BlackListsDetailBean.BlacklistBean.CommentsBeanX> list) {
        this.list = list;
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_layout_blacklist_detail, parent, false);
        viewHolder viewHolder = new viewHolder(view, viewType);
        viewHolder.setIsRecyclable(false);
        mHolderMap.put(viewType, viewHolder);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        try {
            final viewHolder viewHolder = mHolderMap.get(position);
            viewHolder.mTVContent.setText(list.get(position).getComment());
            viewHolder.mTVTime.setText(list.get(position).getCreated_at());
            viewHolder.mTVCompany.setText(list.get(position).getName() + "  " + list.get(position).getC_name());

            Glide.with(context).load(list.get(position).getAvatar()).into(viewHolder.mIVHead);


            /*一级回复的点击事件*/
            viewHolder.mReply.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mListener != null) {
                        mListener.onItemClick(list.get(position).getId() + ""
                                , list.get(position).getName()
                                , ""
                                , ""
                                , "");
                    }
                }
            });
        } catch (Exception e) {
        }
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public int getItemCount() {
        return (list != null) ? (list.size()) : (0);
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    public void call(String tel) {
        Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + tel));
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    class viewHolder extends RecyclerView.ViewHolder {
        View view;
        ImageView mIVHead;
        MyListview myListview;
        TextView mTVCompany, mTVContent, mTVTime, mReply;

        public viewHolder(View convertView, int position) {
            super(convertView);
            mReply = (TextView) convertView.findViewById(R.id.item_reply);
            mTVTime = (TextView) convertView.findViewById(R.id.item_time);
            mIVHead = (ImageView) convertView.findViewById(R.id.item_head);
            view = convertView.findViewById(R.id.blacklist_detail_divider);
            mTVCompany = (TextView) convertView.findViewById(R.id.item_cmpany);
            mTVContent = (TextView) convertView.findViewById(R.id.item_content);
            myListview = (MyListview) convertView.findViewById(R.id.item_rv);

            mChildAdapter = new BlackListDetailChildAdapter(context, mListener);
            List<BlackListsDetailBean.BlacklistBean.CommentsBeanX.CommentsBean> childList = list.get(position).getComments();

            if (childList != null && childList.size() != 0) {
                mChildAdapter.setList(childList);
                myListview.setAdapter(mChildAdapter);
            }
        }
    }
}
