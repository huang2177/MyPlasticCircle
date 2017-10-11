package com.myplas.q.supdem.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.myplas.q.R;
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
    List<ReplyBean.DataBeanX.DataBean> list;

    public void setList(List<ReplyBean.DataBeanX.DataBean> list) {
        this.list = list;
    }

    public SupDem_Detail_LV_HFAdapter(Context context) {
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
    public View getView(int position, View convertView, ViewGroup parent) {
        viewHolder viewHolder = null;
        if (convertView == null) {
            viewHolder = new viewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.item_layout_supdem_listview_hf, parent, false);
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
        viewHolder.mTVContent.setText(list.get(position).getContent());
        viewHolder.mTVTime.setText(list.get(position).getInput_time());
        viewHolder.mTVCompany.setText(list.get(position).getInfo().getC_name()
                + "  " + list.get(position).getInfo().getName());

        Glide.with(context)
                .load(list.get(position).getInfo().getThumb())
                .placeholder(R.drawable.contact_image_defaul_male)
                .into(viewHolder.mIVHead);
        viewHolder.mIVStart.setImageResource(list.get(position).getInfo().getIs_pass().equals("0")
                ? R.drawable.icon_identity
                : R.drawable.icon_identity_hl);
        return convertView;
    }

    class viewHolder {
        ImageView mIVHead, mIVStart, mIVCall;
        TextView mTVCompany, mTVContent, mTVTime;
    }
}
