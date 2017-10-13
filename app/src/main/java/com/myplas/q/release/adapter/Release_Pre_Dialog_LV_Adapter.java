package com.myplas.q.release.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.myplas.q.R;
import com.myplas.q.myinfo.beans.MyFollowBean;

import java.util.List;

/**
 * 编写： 黄双
 * 电话：15378412400
 * 邮箱：15378412400@163.com
 * 时间：2017/3/20 22:33
 */
public class Release_Pre_Dialog_LV_Adapter extends BaseAdapter {
    Context context;
    List<MyFollowBean.DataBean> list;

    public Release_Pre_Dialog_LV_Adapter(Context context, List<MyFollowBean.DataBean> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
//        if (list != null)
//            return list.size();
        return 10;
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
            convertView = LayoutInflater.from(context).inflate(R.layout.item_release_pre_dialog_layout, parent, false);
            viewHolder.mTVNf = (TextView) convertView.findViewById(R.id.pre_nf);
            viewHolder.mTVMode = (TextView) convertView.findViewById(R.id.pre_mode);
            viewHolder.mTVPirce = (TextView) convertView.findViewById(R.id.pre_pirce);
            viewHolder.mTVProduction = (TextView) convertView.findViewById(R.id.pre_storehouse);
            viewHolder.mTVGoodsposition = (TextView) convertView.findViewById(R.id.pre_goodsposition);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (viewHolder) convertView.getTag();
        }
        try {
//            viewHolder.mTVGoodsposition.setText("货物位置：" + list.get(position).getFocused_id().getC_name());
//            viewHolder.mTVMode.setText("牌号：" + list.get(position).getFocused_id().getMobile());
//            viewHolder.mTVProduction.setText("厂家：" + list.get(position).getFocused_id().getName());
//            viewHolder.mTVPirce.setText("价格" + list.get(position).getFocused_id().getSale() + "条    ");
//            viewHolder.mTVNf.setText("现货/期货" + list.get(position).getFocused_id().getBuy() + "条");
            viewHolder.mTVGoodsposition.setText("货物位置：");
            viewHolder.mTVMode.setText("牌号：");
            viewHolder.mTVProduction.setText("厂家：");
            viewHolder.mTVPirce.setText("价格");
            viewHolder.mTVNf.setText("现货/期货");
        } catch (Exception e) {
        }
        return convertView;
    }

    public void setList(List<MyFollowBean.DataBean> list) {
        this.list = list;
    }

    class viewHolder {
        TextView mTVPirce, mTVNf, mTVProduction, mTVGoodsposition, mTVMode;
    }
}
