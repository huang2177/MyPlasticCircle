package com.myplas.q.homepage.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.myplas.q.R;
import com.myplas.q.homepage.activity.BlackListDetailActivity;

import java.io.FileOutputStream;

/**
 * @author 黄双
 * @date 2018/1/11 0011
 */

public class BlackListAdapter extends RecyclerView.Adapter {
    private Context context;

    public BlackListAdapter(Context context) {
        this.context = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_layout_blacklist, parent, false);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 15;
    }


    class MyViewHolder extends RecyclerView.ViewHolder implements OnClickListener {

        private ImageView imageView;
        private TextView tvTitle, tvTime, tvNumber;

        public MyViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            imageView = (ImageView) itemView.findViewById(R.id.blacklist_img);
            tvTitle = (TextView) itemView.findViewById(R.id.blacklist_title);
            tvNumber = (TextView) itemView.findViewById(R.id.blacklist_num);
            tvTime = (TextView) itemView.findViewById(R.id.blacklist_time);
        }

        @Override
        public void onClick(View v) {
            context.startActivity(new Intent(context, BlackListDetailActivity.class));
        }
    }
}
