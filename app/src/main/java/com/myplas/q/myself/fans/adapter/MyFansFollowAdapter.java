package com.myplas.q.myself.fans.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.myplas.q.R;
import com.myplas.q.common.view.MyImageView;
import com.myplas.q.contact.beans.MyFansBean;

import java.util.List;

/**
 * 编写： 黄双
 * 电话：15378412400
 * 邮箱：15378412400@163.com
 * 时间：2017/3/20 22:33
 */
public class MyFansFollowAdapter extends BaseAdapter {
    Context context;
    List<MyFansBean.DataBean> list;

    public void setList(List<MyFansBean.DataBean> list) {
        this.list = list;
    }

    public MyFansFollowAdapter(Context context, List<MyFansBean.DataBean> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        if (list != null)
            return list.size();
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
    public View getView(int position, View convertView, ViewGroup parent) {
        viewHolder viewHolder = null;
        if (convertView == null) {
            viewHolder = new viewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.item_layout_lv_myself_fansfollow, parent, false);
            viewHolder.tx = (MyImageView) convertView.findViewById(R.id.xq_tx);
            viewHolder.rz = (ImageView) convertView.findViewById(R.id.xq_rz);
            viewHolder.gs = (TextView) convertView.findViewById(R.id.txl_listview_gs);
            viewHolder.mz = (TextView) convertView.findViewById(R.id.txl_listview_mz);
            viewHolder.gj = (TextView) convertView.findViewById(R.id.txl_listview_gj);
            viewHolder.qg = (TextView) convertView.findViewById(R.id.txl_listview_qg);
            viewHolder.dh = (TextView) convertView.findViewById(R.id.txl_listview_dh);
            viewHolder.sign = (ImageView) convertView.findViewById(R.id.contact_sign_img);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (viewHolder) convertView.getTag();
        }
        try {
            viewHolder.gs.setText(list.get(position).getC_name());
            viewHolder.dh.setText(list.get(position).getMobile());
            viewHolder.mz.setText(list.get(position).getName());
            viewHolder.gj.setText("发布供给：" + list.get(position).getSale() + "条");
            viewHolder.qg.setText("发布求购：" + list.get(position).getBuy() + "条");
            viewHolder.rz.setImageResource(list.get(position).getIs_pass().equals("0")
                    ? R.drawable.icon_identity
                    : R.drawable.icon_identity_hl);

            Glide.with(context)
                    .load(list.get(position).getThumb())
                    .placeholder(R.drawable.contact_image_defaul_male)
                    .into(viewHolder.tx);

            viewHolder.tx.setBorderColor(context.getResources().getColor(R.color.color_white));

            if ("1".equals(list.get(position).getType())) {
                viewHolder.sign.setImageResource(R.drawable.icon_factory);
            }
            if ("2".equals(list.get(position).getType())) {
                viewHolder.sign.setImageResource(R.drawable.icon_raw_material);
            }
            if ("4".equals(list.get(position).getType())) {
                viewHolder.sign.setImageResource(R.drawable.icon_logistics);
            }

        } catch (Exception e) {
        }

        return convertView;
    }

    class viewHolder {
        MyImageView tx;
        ImageView rz, sign;
        TextView gs, mz, dh, gj, qg;
    }
}
