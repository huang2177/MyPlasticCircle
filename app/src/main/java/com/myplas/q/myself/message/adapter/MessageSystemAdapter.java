package com.myplas.q.myself.message.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.myplas.q.R;
import com.myplas.q.myself.beans.MsgSystem;
import com.myplas.q.myself.vip.EstablishedVipActivity;

import java.util.List;

/**
 * @author Huangshuang  2018/5/2 0002
 */

public class MessageSystemAdapter extends RecyclerView.Adapter {
    Context context;
    List<MsgSystem.DataBean> mListSystem;
    SparseArray<ViewHolder> mHolderMap;

    public MessageSystemAdapter(Context context, List<MsgSystem.DataBean> mListSystem) {
        this.context = context;
        this.mListSystem = mListSystem;
        mHolderMap = new SparseArray<>();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_lv_msg_system, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        mHolderMap.put(viewType, viewHolder);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ViewHolder viewHolder = mHolderMap.get(position);

        viewHolder.msg.setText(mListSystem.get(position).getMsg());
        viewHolder.time.setText(mListSystem.get(position).getInput_time());
        viewHolder.detail.setOnClickListener(new MyOnClickListener(position));
        viewHolder.title.setText(getVipType(mListSystem.get(position).getType()));
    }

    private String getVipType(String type) {
        switch (type) {
            case "7":
                return "店铺会员到期";
            case "8":
                return "试用会员到期";
            case "9":
                return "头条会员到期";
            default:
                return "店铺会员到期";
        }
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        return mListSystem != null ? mListSystem.size() : 0;
    }

    public void setList(List<MsgSystem.DataBean> list) {
        this.mListSystem = list;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView msg, title, detail, time;

        public ViewHolder(View itemView) {
            super(itemView);
            msg = (TextView) itemView.findViewById(R.id.msg_detail_msg);
            time = (TextView) itemView.findViewById(R.id.msg_detail_time);
            title = (TextView) itemView.findViewById(R.id.msg_detail_title);
            detail = (TextView) itemView.findViewById(R.id.msg_detail_detail);
        }
    }

    public class MyOnClickListener implements View.OnClickListener {
        int position;

        public MyOnClickListener(int position) {
            this.position = position;
        }

        @Override
        public void onClick(View v) {
            context.startActivity(new Intent(context, EstablishedVipActivity.class));
        }
    }
}