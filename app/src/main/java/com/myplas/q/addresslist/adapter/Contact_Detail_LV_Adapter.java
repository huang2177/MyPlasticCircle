package com.myplas.q.addresslist.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.myplas.q.R;
import com.myplas.q.addresslist.beans.ContactSupDemBean;
import com.myplas.q.common.view.MyImageView;
import com.myplas.q.supdem.beans.DeliverPriceBean;

import java.util.List;

/**
 * 编写： 黄双
 * 电话：15378412400
 * 邮箱：15378412400@163.com
 * 时间：2017/3/29 10:57
 */
public class Contact_Detail_LV_Adapter extends BaseAdapter {
    Context context;
    List<ContactSupDemBean.DataBean> list;
    private List<DeliverPriceBean.DataBean> mList;

    public Contact_Detail_LV_Adapter(Context context, List<ContactSupDemBean.DataBean> list) {
        this.list = list;
        this.context = context;
    }

    @Override
    public int getCount() {
        return (list == null) ? (20) : (list.size());
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
            convertView = LayoutInflater.from(context).inflate(R.layout.item_contact_lv_layout, parent, false);
            viewHolder.mIVType = (ImageView) convertView.findViewById(R.id.item_nf);
            viewHolder.mTVTime = (TextView) convertView.findViewById(R.id.item_time);
            viewHolder.mIVHead = (MyImageView) convertView.findViewById(R.id.item_head);
            viewHolder.mIVStart = (ImageView) convertView.findViewById(R.id.item_start);
            viewHolder.mTVContent = (TextView) convertView.findViewById(R.id.item_content);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (viewHolder) convertView.getTag();
        }
        try {
            viewHolder.mIVHead.setBorderColor(context
                    .getResources()
                    .getColor(R.color.color_white));
//            viewHolder.mTVContent.setText(list.get(position).getPrice());
//            viewHolder.mTVTime.setText(list.get(position).getInput_time());
//
//            Glide.with(context)
//                    .load(list.get(position).getThumb())
//                    .placeholder(R.drawable.contact_image_defaul_male)
//                    .into(viewHolder.mIVHead);
//            viewHolder.mIVStart.setImageResource(list.get(position).getIs_pass().equals("0")
//                    ? R.drawable.icon_identity
//                    : R.drawable.icon_identity_hl);
        } catch (Exception e) {
        }

        return convertView;
    }

    public void setList(List<DeliverPriceBean.DataBean> list) {
        mList = list;
    }

    class viewHolder {
        MyImageView mIVHead;
        ImageView mIVStart, mIVType;
        TextView mTVCompany, mTVContent, mTVTime;
    }
}
