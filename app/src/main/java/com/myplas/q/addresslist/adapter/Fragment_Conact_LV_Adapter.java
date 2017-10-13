package com.myplas.q.addresslist.adapter;

import android.content.Context;
import android.text.Html;
import android.text.Spanned;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.myplas.q.R;
import com.myplas.q.addresslist.Beans.TXL_Bean;
import com.myplas.q.common.view.SectionedBaseAdapter;
import com.tencent.mm.sdk.platformtools.Log;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 编写： 黄双
 * 电话：15378412400
 * 邮箱：15378412400@163.com
 * 时间：2017/3/20 19:31
 */
public class Fragment_Conact_LV_Adapter extends BaseAdapter {
    Context context;
    viewHolder viewHolder;
    TXL_Bean.TopBean topBean;
    List<TXL_Bean.PersonsBean> list;

    public Map<Integer, ImageView> mMap;

    public void setTopBean(TXL_Bean.TopBean topBean) {
        this.topBean = topBean;
    }

    public void setList(List<TXL_Bean.PersonsBean> list) {
        this.list = list;
    }

    public Fragment_Conact_LV_Adapter(Context context, List<TXL_Bean.PersonsBean> list, TXL_Bean.TopBean topBean) {
        this.list = list;
        this.context = context;
        this.topBean = topBean;
        mMap = new HashMap<>();
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
        if (convertView == null) {
            viewHolder = new viewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.item_fragment_conact_lv_layout, parent, false);
            initView(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (viewHolder) convertView.getTag();
        }
        mMap.put(position, viewHolder.tx);
        shouInfo(viewHolder, position);
        return convertView;
    }

    class viewHolder {
        View view;
        LinearLayout gd;
        ImageView tx, rz, top_img;
        RelativeLayout mLayoutClassify, mLayoutRegion;
        TextView gs, mz, sex, gj, zg, zhy, need_text;
    }

    public void shouInfo(viewHolder viewHolder, int position) {
        try {
            Glide.with(context)
                    .load(list.get(position).getThumb())
                    .placeholder((list.get(position).getSex().equals("男")) ? (R.drawable.contact_image_defaul_male) : (R.drawable.contact_image_defaul_female))
                    .into(viewHolder.tx);
            Spanned s1 = replace(list.get(position).getC_name());
            viewHolder.gs.setText(s1);
            viewHolder.mz.setText(replace(list.get(position).getName()));
            viewHolder.sex.setText(replace(list.get(position).getSex()));
            String type = list.get(position).getType();
            CharSequence product = replace(list.get(position).getMain_product());
            if (type.equals("1")) {
                viewHolder.zg.setVisibility(View.VISIBLE);
                viewHolder.gj.setVisibility(View.VISIBLE);
                viewHolder.zhy.setText(replace(list.get(position).getNeed_product()));
                viewHolder.zg.setText("产品：" + product
                        + "  月用量：" + list.get(position).getMonth_consum());

                viewHolder.gj.setText("供给:" + list.get(position).getSale_count()
                        + " 求购:" + list.get(position).getBuy_count()
                        + " 需求:");
            } else if (type.equals("2")) {
                viewHolder.zg.setVisibility(View.INVISIBLE);
                viewHolder.gj.setVisibility(View.VISIBLE);
                viewHolder.zhy.setText(replace(list.get(position).getNeed_product()));
                viewHolder.gj.setText("供给:" + list.get(position).getSale_count()
                        + " 求购:" + list.get(position).getBuy_count()
                        + " 主营:");
            } else {
                viewHolder.zg.setVisibility(View.INVISIBLE);
                viewHolder.gj.setVisibility(View.VISIBLE);
                viewHolder.gj.setText("主营产品：" + replace(list.get(position).getNeed_product()));
            }
            viewHolder.rz.setImageResource(list.get(position).getIs_pass().equals("0")
                    ? R.drawable.icon_identity
                    : R.drawable.icon_identity_hl);

            viewHolder.top_img.setVisibility(View.GONE);
        } catch (Exception e) {
        }
    }

    public void initView(View layout) {
        viewHolder.tx = (ImageView) layout.findViewById(R.id.xq_tx);
        viewHolder.rz = (ImageView) layout.findViewById(R.id.xq_rz);
        viewHolder.top_img = (ImageView) layout.findViewById(R.id.top_img);
        viewHolder.gd = (LinearLayout) layout.findViewById(R.id.item_top_ll);
        viewHolder.gs = (TextView) layout.findViewById(R.id.txl_listview_gs);
        viewHolder.mz = (TextView) layout.findViewById(R.id.txl_listview_mz);
        viewHolder.gj = (TextView) layout.findViewById(R.id.txl_listview_gj);
        viewHolder.zg = (TextView) layout.findViewById(R.id.txl_listview_pro_month);
        viewHolder.zhy = (TextView) layout.findViewById(R.id.txl_listview_zhy);
        viewHolder.sex = (TextView) layout.findViewById(R.id.txl_listview_sex);
        // viewHolder.need_text= (TextView) layout.findViewById(R.id.need_text);
    }

    public Spanned replace(String s) {
        s = s.replace("<strong style='color: #ff5000;'>", "<font color='#ff5000'><b>");
        s = s.replace("</strong>", "</b></font>");
        Spanned s1 = Html.fromHtml(s);
        return s1;
    }
}
