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

import com.bumptech.glide.Glide;
import com.myplas.q.R;
import com.myplas.q.common.utils.TextUtils;
import com.myplas.q.common.view.CommonDialog;
import com.myplas.q.homepage.activity.BlackListDetailActivity;
import com.myplas.q.homepage.beans.BlackListsBean;
import com.myplas.q.homepage.beans.BlackListsDetailBean;

import java.util.List;

/**
 * @author 黄双
 * @date 2018/1/11 0011
 */

public class BlackListDetailIMGAdapter extends RecyclerView.Adapter {

    private Context context;
    private List<String> blacklists;
    private CommonDialog.DialogShowInterface listener;

    public BlackListDetailIMGAdapter(Context context, List<String> blacklists) {
        this.context = context;
        this.blacklists = blacklists;
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View convertView = LayoutInflater.from(context).inflate(R.layout.layout_blacklist_detail_img, parent, false);
        ViewHolder viewHolder = new ViewHolder(convertView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ViewHolder viewHolder = (ViewHolder) holder;
        Glide.with(context)
                .load(blacklists.get(position))
                .placeholder(R.drawable.ic_waiting)
                .into(viewHolder.imageView);
    }

    @Override
    public int getItemCount() {
        return blacklists != null && blacklists.size() != 0 ? blacklists.size() : 0;
    }


    class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;

        public ViewHolder(View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.detail_img);
        }
    }
}
