package com.myplas.q.supdem.adapter;

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
import com.myplas.q.supdem.beans.ReplyBean;

import java.util.List;

/**
 * 编写： 黄双
 * 电话：15378412400
 * 邮箱：15378412400@163.com
 * 时间：2017/3/20 17:12
 */
public class SupDem_Detail_LV_HF_Child_Adapter extends BaseAdapter {
    Context context;
    MyOnItemClickListener mMyOnItemClickListener;
    List<ReplyBean.DataBean.ReplayBean> list;

    public void setList(List<ReplyBean.DataBean.ReplayBean> list) {
        this.list = list;
    }

    public SupDem_Detail_LV_HF_Child_Adapter(Context context, MyOnItemClickListener myOnItemClickListener) {
        this.mMyOnItemClickListener = myOnItemClickListener;
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
            convertView = LayoutInflater.from(context).inflate(R.layout.item_layout_supdem_lv_hf_child, parent, false);
            viewHolder.mTVTime = (TextView) convertView.findViewById(R.id.item_time);
            viewHolder.mIVCall = (ImageView) convertView.findViewById(R.id.item_call);
            viewHolder.mIVHead = (ImageView) convertView.findViewById(R.id.item_head);
            viewHolder.mIVStart = (ImageView) convertView.findViewById(R.id.item_start);
            viewHolder.mTVContent = (TextView) convertView.findViewById(R.id.item_pirce);
            viewHolder.mTVCompany = (TextView) convertView.findViewById(R.id.item_cmpany);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (viewHolder) convertView.getTag();
        }
        try {
            viewHolder.mTVContent.setText("回复"
                    + list.get(position).getHui_name()
                    + "："
                    + list.get(position).getContent());
            viewHolder.mTVTime.setText(list.get(position).getInput_time());
            viewHolder.mTVCompany.setText(list.get(position).getC_name()
                    + "  " + list.get(position).getName());

            Glide.with(context)
                    .load(list.get(position).getThumb())
                    .placeholder(R.drawable.img_defaul_male)
                    .into(viewHolder.mIVHead);

            viewHolder.mIVCall.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    call(list.get(position).getMobile());
                }
            });
             /*二三级回复的点击事件*/
            convertView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mMyOnItemClickListener != null) {
                        mMyOnItemClickListener.onItemClick("2"
                                , list.get(position).getName()
                                , list.get(position).getReply_id()
                                , list.get(position).getPur_id()
                                , list.get(position).getUser_id());
                    }
                }
            });
        } catch (Exception e) {
        }

        return convertView;
    }

    public void call(String tel) {
        Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + tel));
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    class viewHolder {
        ImageView mIVHead, mIVStart, mIVCall;
        TextView mTVCompany, mTVContent, mTVTime;
    }
}
