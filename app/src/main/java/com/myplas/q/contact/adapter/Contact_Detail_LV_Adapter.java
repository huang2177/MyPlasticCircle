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
    List<ContactInfoBean.DataBean.DemandBean> demandList;
    private List<ContactInfoBean.DataBean.SuppliesBean> suppliesList;


    public Contact_Detail_LV_Adapter(Context context
            , List<ContactInfoBean.DataBean.SuppliesBean> list
            , List<ContactInfoBean.DataBean.DemandBean> list1) {
        this.demandList = list1;
        this.suppliesList = list;
        this.context = context;
    }

    @Override
    public int getCount() {
        if (demandList != null) {
            return (demandList.size() == 0) ? (0) : (demandList.size());
        } else if (suppliesList != null) {
            return (suppliesList.size() == 0) ? (0) : (suppliesList.size());
        }
        return 0;
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

            viewHolder.reply = (TextView) convertView.findViewById(R.id.supply_demand_reply);
            viewHolder.deliver = (TextView) convertView.findViewById(R.id.supply_demand_deliver);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (viewHolder) convertView.getTag();
        }
        try {
            if (demandList != null) {
                String html1 = "<font color='#9c9c9c'>" + "牌号:" + "</font>" + demandList.get(position).getModel()
                        + "   <font color='#9c9c9c'>" + "  厂家:" + "</font>" + demandList.get(position).getF_name()
                        + "   <font color='#9c9c9c'>" + "  价格:" + "</font>" + demandList.get(position).getUnit_price()
                        + "   <font color='#9c9c9c'>" + "  交货地:" + "</font>" + demandList.get(position).getStore_house();
                viewHolder.mTVContent.setText(Html.fromHtml(html1));

                viewHolder.mTVTime.setText(demandList.get(position).getInput_time());
                viewHolder.deliver.setText("出价:" + demandList.get(position).getBid());
                viewHolder.reply.setText("回复:" + demandList.get(position).getReply());


                viewHolder.mIVType.setImageResource(("1".equals(demandList.get(position).getCargo_type()))
                        ? R.drawable.icon_now
                        : R.drawable.icon_futures);
            } else {
                String html1 = "<font color='#9c9c9c'>" + "牌号:" + "</font>" + suppliesList.get(position).getModel()
                        + "   <font color='#9c9c9c'>" + "  厂家:" + "</font>" + suppliesList.get(position).getF_name()
                        + "   <font color='#9c9c9c'>" + "  价格:" + "</font>" + suppliesList.get(position).getUnit_price()
                        + "    <font color='#9c9c9c'>" + "  交货地:" + "</font>" + suppliesList.get(position).getStore_house();
                viewHolder.mTVContent.setText(Html.fromHtml(html1));

                viewHolder.mTVTime.setText(suppliesList.get(position).getInput_time());
                viewHolder.deliver.setText("出价:" + suppliesList.get(position).getBid());
                viewHolder.reply.setText("回复:" + suppliesList.get(position).getReply());


                viewHolder.mIVType.setImageResource(("1".equals(suppliesList.get(position).getCargo_type()))
                        ? R.drawable.icon_now
                        : R.drawable.icon_futures);
            }
        } catch (Exception e) {
        }

        return convertView;
    }


    class viewHolder {
        ImageView mIVStart, mIVType;
        TextView mTVCompany, mTVContent, mTVTime, reply, deliver;
    }
}
