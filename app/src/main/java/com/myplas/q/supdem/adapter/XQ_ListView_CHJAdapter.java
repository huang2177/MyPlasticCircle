package com.myplas.q.supdem.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.myplas.q.R;
import com.myplas.q.supdem.Beans.DeliverPriceBean;

import java.util.List;

/**
 * 编写： 黄双
 * 电话：15378412400
 * 邮箱：15378412400@163.com
 * 时间：2017/3/20 16:12
 */
public class XQ_ListView_CHJAdapter extends BaseAdapter {
    Context context;
    List<DeliverPriceBean.DataBeanX.DataBean> list;

    public void setList(List<DeliverPriceBean.DataBeanX.DataBean> list) {
        this.list = list;
    }

    public XQ_ListView_CHJAdapter(Context context) {
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
            convertView = LayoutInflater.from(context).inflate(R.layout.layout_supdem_listview_chj_item, parent, false);
            viewHolder.tx = (ImageView) convertView.findViewById(R.id.xq_tx);
            viewHolder.rz = (ImageView) convertView.findViewById(R.id.xq_rz);
            viewHolder.chj = (Button) convertView.findViewById(R.id.btn_chj);
            viewHolder.cz = (ImageView) convertView.findViewById(R.id.btn_cz);
            viewHolder.gs = (TextView) convertView.findViewById(R.id.listview_gs);
            viewHolder.shj= (TextView) convertView.findViewById(R.id.listview_shj);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (viewHolder) convertView.getTag();
        }
        viewHolder.chj.setText(list.get(position).getPrice());
        viewHolder.shj.setText(list.get(position).getInput_time());
        viewHolder.gs.setText(list.get(position).getInfo().getC_name() + "\n" + list.get(position).getInput_time());
        viewHolder.rz.setImageResource((("0".equals(list.get(position).getInfo().getIs_pass())))?(R.drawable.icon_identity):(R.drawable.icon_identity_hl));

        Glide.with(context)
                .load(list.get(position).getInfo().getThumb())
                .placeholder(R.drawable.contact_image_defaul_male)
                .into(viewHolder.tx);

        boolean b=list.get(position).getInfo().getMobile().contains("*");
        if(!b){
            viewHolder.cz.setImageResource(R.drawable.btn_dial);
            viewHolder.cz.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + list.get(position).getInfo().getMobile()));
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(intent);
                }
            });
        }

        return convertView;
    }
    class viewHolder {
        ImageView tx, rz,cz;
        TextView gs,shj;
        Button chj;
    }
}
