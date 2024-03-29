package com.myplas.q.supdem.adapter;

import android.content.Context;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.myplas.q.R;
import com.myplas.q.common.listener.MyOnItemClickListener;
import com.myplas.q.common.utils.SharedUtils;
import com.myplas.q.supdem.beans.SupDemBean;

import java.util.List;


/**
 * 编写： 黄双
 * 电话：15378412400
 * 邮箱：15378412400@163.com
 * 时间：2017/3/19 14:55
 */
public class SupDem_LV_Adapter extends BaseAdapter {
    Context context;
    List<SupDemBean.DataBean> list;
    SharedUtils sharedUtils = SharedUtils.getSharedUtils();

    private MyOnItemClickListener listener;

    public SupDem_LV_Adapter(Context context, List<SupDemBean.DataBean> list) {
        this.context = context;
        this.list = list;
    }

    public void setList(List<SupDemBean.DataBean> list) {
        this.list = list;
    }

    @Override
    public int getCount() {
        return list != null ? list.size() : 0;
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
            convertView = LayoutInflater.from(context).inflate(R.layout.item_supdem_lv_layout, parent, false);
            viewHolder.company = (TextView) convertView.findViewById(R.id.gq_listview_gs);
            viewHolder.time = (TextView) convertView.findViewById(R.id.supply_demand_time);
            viewHolder.typeSupDem = (ImageView) convertView.findViewById(R.id.supdem_img_type);
            viewHolder.content = (TextView) convertView.findViewById(R.id.supply_demand_content);
            viewHolder.reply = (TextView) convertView.findViewById(R.id.supply_demand_reply);
            viewHolder.deliver = (TextView) convertView.findViewById(R.id.supply_demand_deliver);
            viewHolder.mLayout = (LinearLayout) convertView.findViewById(R.id.supply_demand_company);
            viewHolder.typeNowFutures = (ImageView) convertView.findViewById(R.id.supply_demand_now_futures);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (viewHolder) convertView.getTag();
        }
        try {
            viewHolder.company.setText((list.get(position).getC_name()) + "  " + list.get(position).getName());

            viewHolder.time.setText(list.get(position).getInput_time());
            if ("1".equals(list.get(position).getFrom())) {
                viewHolder.reply.setText("回复(" + list.get(position).getComments() + ")");
                viewHolder.deliver.setText("出价(" + list.get(position).getOffers() + ")");
                viewHolder.reply.setCompoundDrawablesWithIntrinsicBounds(R.drawable.icon_sd_reply, 0, 0, 0);
                viewHolder.deliver.setCompoundDrawablesWithIntrinsicBounds(R.drawable.icon_sd_offer, 0, 0, 0);
            } else {
                viewHolder.reply.setText("");
                viewHolder.deliver.setText("");
                viewHolder.reply.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
                viewHolder.deliver.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
            }

            String content = "<font color='#9c9c9c'>" + "交货地:" + "</font>" + list.get(position).getStore_house()
                    + "<font color='#9c9c9c'>" + "   牌号:" + "</font>" + list.get(position).getModel()
                    + "<font color='#9c9c9c'>" + "   厂家:" + "</font>" + list.get(position).getF_name()
                    + "<font color='#9c9c9c'>" + "   价格:" + "</font>" + list.get(position).getUnit_price();
            viewHolder.content.setText(Html.fromHtml(content));

            viewHolder.typeSupDem.setImageResource("1".equals(list.get(position).getType())
                    ? R.drawable.icon_supdem_purchase
                    : R.drawable.icon_supdem_supply);
            viewHolder.typeNowFutures.setImageResource("1".equals(list.get(position).getCargo_type())
                    ? R.drawable.icon_now
                    : R.drawable.icon_futures);

            //点击判断是否消耗积分
            viewHolder.mLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (list.get(position).getFrom().equals("1")) {
                        if (listener != null) {
                            listener.onItemClick(list.get(position).getUser_id()
                                    , list.get(position).getIs_shop()
                                    , null
                                    , null
                                    , null);
                        }
                    }
                }
            });
        } catch (Exception e) {
        }
        return convertView;
    }

    class viewHolder {
        LinearLayout mLayout;
        ImageView typeSupDem, typeNowFutures;
        TextView company, content, time, deliver, reply;
    }

    public void setListener(MyOnItemClickListener listener) {
        this.listener = listener;
    }
}
