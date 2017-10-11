package com.myplas.q.supdem.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.myplas.q.R;
import com.myplas.q.common.view.MyListview;
import com.myplas.q.supdem.BaseInterFace;
import com.myplas.q.supdem.Beans.ReplyBean;

import java.util.List;

/**
 * 编写： 黄双
 * 电话：15378412400
 * 邮箱：15378412400@163.com
 * 时间：2017/3/20 17:12
 */
public class SupDem_Detail_LV_HFAdapter extends BaseAdapter {
    Context context;
    BaseInterFace mBaseInterFace;
    List<ReplyBean.DataBean> list;
    SupDem_Detail_LV_HF_Child_Adapter mChildAdapter;

    public void setList(List<ReplyBean.DataBean> list) {
        this.list = list;
    }

    public SupDem_Detail_LV_HFAdapter(Context context, BaseInterFace baseInterFace) {
        this.context = context;
        this.mBaseInterFace = baseInterFace;
        mChildAdapter = new SupDem_Detail_LV_HF_Child_Adapter(context, baseInterFace);
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
            convertView = LayoutInflater.from(context).inflate(R.layout.item_layout_supdem_lv_hf, parent, false);
            viewHolder.mTVTime = (TextView) convertView.findViewById(R.id.item_time);
            viewHolder.mIVCall = (ImageView) convertView.findViewById(R.id.item_call);
            viewHolder.mIVHead = (ImageView) convertView.findViewById(R.id.item_head);
            viewHolder.mLayout = (LinearLayout) convertView.findViewById(R.id.item_ll);
            viewHolder.mListview = (MyListview) convertView.findViewById(R.id.item_lv);
            viewHolder.mIVStart = (ImageView) convertView.findViewById(R.id.item_start);
            viewHolder.mTVContent = (TextView) convertView.findViewById(R.id.item_pirce);
            viewHolder.mTVCompany = (TextView) convertView.findViewById(R.id.item_cmpany);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (viewHolder) convertView.getTag();
        }
        try {
            viewHolder.mTVContent.setText(list.get(position).getContent());
            viewHolder.mTVTime.setText(list.get(position).getInput_time());
            viewHolder.mTVCompany.setText(list.get(position).getC_name()
                    + "  " + list.get(position).getName());

            Glide.with(context)
                    .load(list.get(position).getThumb())
                    .placeholder(R.drawable.contact_image_defaul_male)
                    .into(viewHolder.mIVHead);
            viewHolder.mIVStart.setImageResource(list.get(position).getIs_pass().equals("0")
                    ? R.drawable.icon_identity
                    : R.drawable.icon_identity_hl);

            List<ReplyBean.DataBean.ReplayBean> childList = list.get(position).getReplay();

            if (childList != null && childList.size() != 0) {
                mChildAdapter.setList(childList);
                viewHolder.mListview.setAdapter(mChildAdapter);
            }
            viewHolder.mIVCall.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    call(list.get(position).getMobile());
                }
            });

            /*一级回复的点击事件*/
            viewHolder.mLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mBaseInterFace != null) {
                        mBaseInterFace.onItemClick("2"
                                , list.get(position).getName()
                                , list.get(position).getPur_id()
                                , list.get(position).getId()
                                , list.get(position).getRev_id()
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
        LinearLayout mLayout;
        MyListview mListview;
        ImageView mIVHead, mIVStart, mIVCall;
        TextView mTVCompany, mTVContent, mTVTime;
    }
}
