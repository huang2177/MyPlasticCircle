package com.myplas.q.contact.adapter;

import android.content.Context;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.myplas.q.R;
import com.myplas.q.contact.beans.ContactInfoBean;
import com.myplas.q.common.view.RoundCornerImageView;

import java.util.List;

/**
 * 编写： 黄双
 * 电话：15378412400
 * 邮箱：15378412400@163.com
 * 时间：2017/3/29 10:57
 */
public class Contact_Detail_LV_Adapter extends BaseAdapter {
    Context context;
    List<ContactInfoBean.DataBean.DemandBean> list;
    private List<ContactInfoBean.DataBean.SuppliesBean> mList;

    public Contact_Detail_LV_Adapter(Context context, List<ContactInfoBean.DataBean.DemandBean> list, int type) {
        this.list = list;
        this.context = context;
    }

    public Contact_Detail_LV_Adapter(Context context, List<ContactInfoBean.DataBean.SuppliesBean> list) {
        this.mList = list;
        this.context = context;
    }

    @Override
    public int getCount() {
        if (list != null) {
            return (list.size() == 0) ? (0) : (list.size());
        } else {
            return (mList.size() == 0) ? (0) : (mList.size());
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
        if (convertView == null) {
            viewHolder = new viewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.item_contact_lv_layout, parent, false);
            viewHolder.mIVType = (ImageView) convertView.findViewById(R.id.item_nf);
            viewHolder.mTVTime = (TextView) convertView.findViewById(R.id.item_time);
            viewHolder.mIVStart = (ImageView) convertView.findViewById(R.id.item_start);
            viewHolder.mTVContent = (TextView) convertView.findViewById(R.id.item_content);
            viewHolder.mIVHead = (RoundCornerImageView) convertView.findViewById(R.id.item_head);

            viewHolder.reply = (TextView) convertView.findViewById(R.id.supply_demand_reply);
            viewHolder.deliver = (TextView) convertView.findViewById(R.id.supply_demand_deliver);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (viewHolder) convertView.getTag();
        }
        try {
            if (list != null) {
                String html1 = "<font color='#9c9c9c'>" + "交货地:" + "</font>" + list.get(position).getStore_house()
                        + "   <font color='#9c9c9c'>" + "  牌号:" + "</font>" + list.get(position).getModel()
                        + "   <font color='#9c9c9c'>" + "  厂家:" + "</font>" + list.get(position).getF_name()
                        + "   <font color='#9c9c9c'>" + "  价格:" + "</font>" + list.get(position).getUnit_price();
                viewHolder.mTVContent.setText(Html.fromHtml(html1));

                viewHolder.mTVTime.setText(list.get(position).getInput_time());
                viewHolder.deliver.setText("出价:" + list.get(position).getBid());
                viewHolder.reply.setText("回复:" + list.get(position).getReply());

                Glide.with(context)
                        .load(list.get(position).getThumb())
                        .placeholder(R.drawable.img_defaul_male)
                        .into(viewHolder.mIVHead);

//            viewHolder.mIVStart.setImageResource("0".equals(list.get(position).get)
//                    ? R.drawable.icon_identity
//                    : R.drawable.icon_identity_hl);

                viewHolder.mIVType.setImageResource("1".equals(list.get(position).getCargo_type())
                        ? R.drawable.icon_now
                        : R.drawable.icon_futures);
            } else {
                String html1 = "<font color='#9c9c9c'>" + "交货地:" + "</font>" + mList.get(position).getStore_house()
                        + "   <font color='#9c9c9c'>" + "  牌号:" + "</font>" + mList.get(position).getModel()
                        + "   <font color='#9c9c9c'>" + "  厂家:" + "</font>" + mList.get(position).getF_name()
                        + "   <font color='#9c9c9c'>" + "  价格:" + "</font>" + mList.get(position).getUnit_price();
                viewHolder.mTVContent.setText(Html.fromHtml(html1));

                viewHolder.mTVTime.setText(mList.get(position).getInput_time());
                viewHolder.deliver.setText("出价:" + mList.get(position).getBid());
                viewHolder.reply.setText("回复:" + mList.get(position).getReply());

                Glide.with(context)
                        .load(mList.get(position).getThumb())
                        .placeholder(R.drawable.img_defaul_male)
                        .into(viewHolder.mIVHead);

                viewHolder.mIVType.setImageResource("1".equals(mList.get(position).getCargo_type())
                        ? R.drawable.icon_now
                        : R.drawable.icon_futures);
            }
            viewHolder.mIVHead.setBorderColor(context.getResources().getColor(R.color.color_white));
        } catch (Exception e) {
        }

        return convertView;
    }

    //public void setList(List<ContactSupDemBean.DataBean> list) {
//        mList = list;
//    }

    class viewHolder {
        ImageView mIVStart, mIVType;
        RoundCornerImageView mIVHead;
        TextView mTVCompany, mTVContent, mTVTime, reply, deliver;
    }
}
