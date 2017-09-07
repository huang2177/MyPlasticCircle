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
public class XQ_ListView_HFAdapter extends BaseAdapter {
    Context context;
    List<ReplyBean.DataBeanX.DataBean> list;

    public void setList(List<ReplyBean.DataBeanX.DataBean> list) {
        this.list = list;
    }

    public XQ_ListView_HFAdapter(Context context) {
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
            convertView = LayoutInflater.from(context).inflate(R.layout.layout_supdem_listview_hf_item, parent, false);
            viewHolder.tx = (ImageView) convertView.findViewById(R.id.xq_tx);
            viewHolder.rz = (ImageView) convertView.findViewById(R.id.xq_rz);
            viewHolder.gs = (TextView) convertView.findViewById(R.id.listview_gs);
            viewHolder.content = (TextView) convertView.findViewById(R.id.hf_content);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (viewHolder) convertView.getTag();
        }
        viewHolder.content.setText(list.get(position).getContent());
        viewHolder.gs.setText(list.get(position).getInfo().getC_name()
                + "  " + list.get(position).getInfo().getName()
                + "\n" + list.get(position).getInput_time());
        Glide.with(context)
                .load(list.get(position).getInfo().getThumb())
                .placeholder(R.drawable.contact_image_defaul_male)
                .into(viewHolder.tx);
        if (list.get(position).getInfo().getIs_pass().equals("0")) {
            viewHolder.rz.setImageResource(R.drawable.icon_identity);
        } else {
            viewHolder.rz.setImageResource(R.drawable.icon_identity_hl);
        }
        return convertView;
    }

    class viewHolder {
        ImageView tx, rz;
        TextView gs, content;
    }
}
