package com.myplas.q.contact.adapter;

import android.content.Context;
import android.text.Html;
import android.text.Spanned;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.myplas.q.R;
import com.myplas.q.contact.beans.ContactBean;
import com.myplas.q.common.view.MyImageView;

import java.util.List;

/**
 * 编写： 黄双
 * 电话：15378412400
 * 邮箱：15378412400@163.com
 * 时间：2017/3/20 19:31
 */
public class Fragment_Contact_LV_Adapter extends BaseAdapter {
    Context context;
    viewHolder viewHolder;
    ContactBean.TopBean topBean;
    List<ContactBean.PersonsBean> list;

    public void setTopBean(ContactBean.TopBean topBean) {
        this.topBean = topBean;
    }

    public void setList(List<ContactBean.PersonsBean> list) {
        this.list = list;
    }

    public Fragment_Contact_LV_Adapter(Context context, List<ContactBean.PersonsBean> list, ContactBean.TopBean topBean) {
        this.list = list;
        this.context = context;
        this.topBean = topBean;
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
    public View getView(int position, View convertView, ViewGroup parent) {
        viewHolder viewHolder;
        if (convertView == null) {
            viewHolder = new viewHolder();
            convertView = LayoutInflater
                    .from(context)
                    .inflate(R.layout.item_fragment_contact_lv_layout, parent, false);
            initView(viewHolder, convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (viewHolder) convertView.getTag();
        }
        showInfo(viewHolder, position);
        return convertView;
    }

    public void showInfo(viewHolder viewHolder, int position) {
        try {
            viewHolder.mHead.setBorderColor(context
                    .getResources()
                    .getColor(R.color.color_white));
            Glide.with(context)
                    .load(list.get(position).getThumb())
                    .into(viewHolder.mHead);
            Spanned company = replace(list.get(position).getC_name());
            viewHolder.mCompany.setText(company);

            viewHolder.mSex.setText(replace(list.get(position).getSex()));
            viewHolder.mName.setText(replace(list.get(position).getName()));

            String type = list.get(position).getType();
            CharSequence product = replace(list.get(position).getMain_product());
            viewHolder.mSign.setVisibility(View.VISIBLE);
            if (type.equals("1")) {
                viewHolder.zg.setVisibility(View.VISIBLE);
                viewHolder.mSup.setVisibility(View.VISIBLE);
                viewHolder.mSign.setImageResource(R.drawable.icon_factory);
                viewHolder.zhy.setText(replace(list.get(position).getNeed_product()));
                viewHolder.zg.setText("产品:"
                        + product
                        + "  月用量:"
                        + list.get(position).getMonth_consum());

                viewHolder.mSup.setText("供给:"
                        + list.get(position).getSale_count()
                        + " 求购:"
                        + list.get(position).getBuy_count()
                        + " 需求:");
            } else if (type.equals("2")) {
                viewHolder.zg.setVisibility(View.INVISIBLE);
                viewHolder.mSup.setVisibility(View.VISIBLE);
                viewHolder.mSign.setImageResource(R.drawable.icon_raw_material);
                viewHolder.zhy.setText(replace(list.get(position).getNeed_product()));
                viewHolder.mSup.setText("供给:"
                        + list.get(position).getSale_count()
                        + " 求购:"
                        + list.get(position).getBuy_count()
                        + " 主营:");
            } else {
                viewHolder.zg.setVisibility(View.INVISIBLE);
                viewHolder.mSup.setVisibility(View.VISIBLE);
                viewHolder.mSign.setImageResource(R.drawable.icon_logistics);
                viewHolder.mSup.setText("主营产品:" + replace(list.get(position).getNeed_product()));
            }
            viewHolder.mStart.setImageResource(list.get(position).getIs_pass().equals("0")
                    ? R.drawable.icon_identity
                    : R.drawable.icon_identity_hl);
        } catch (Exception e) {
        }
    }

    public void showInfo(viewHolder viewHolder) {
        try {
            if (topBean != null) {
                viewHolder.mHead.setBorderColor(context
                        .getResources()
                        .getColor(R.color.color_white));
                Glide.with(context)
                        .load(topBean.getThumb())
                        .into(viewHolder.mHead);
                Spanned company = replace(topBean.getC_name());
                viewHolder.mCompany.setText(company);

                viewHolder.mSex.setText(replace(topBean.getSex()));
                viewHolder.mName.setText(replace(topBean.getName()));

                String type = topBean.getType();
                CharSequence product = replace(topBean.getMain_product());
                viewHolder.mSign.setVisibility(View.VISIBLE);
                if (type.equals("1")) {
                    viewHolder.zg.setVisibility(View.VISIBLE);
                    viewHolder.mSup.setVisibility(View.VISIBLE);
                    viewHolder.mSign.setImageResource(R.drawable.icon_factory);
                    viewHolder.zhy.setText(replace(topBean.getNeed_product()));
                    viewHolder.zg.setText("产品:"
                            + product
                            + "  月用量:"
                            + topBean.getMonth_consum());

                    viewHolder.mSup.setText("供给:"
                            + topBean.getSale_count()
                            + " 求购:"
                            + topBean.getBuy_count()
                            + " 需求:");
                } else if (type.equals("2")) {
                    viewHolder.zg.setVisibility(View.INVISIBLE);
                    viewHolder.mSup.setVisibility(View.VISIBLE);
                    viewHolder.mSign.setImageResource(R.drawable.icon_raw_material);
                    viewHolder.zhy.setText(replace(topBean.getNeed_product()));
                    viewHolder.mSup.setText("供给:"
                            + topBean.getSale_count()
                            + " 求购:"
                            + topBean.getBuy_count()
                            + " 主营:");
                } else {
                    viewHolder.zg.setVisibility(View.INVISIBLE);
                    viewHolder.mSup.setVisibility(View.VISIBLE);
                    viewHolder.mSign.setImageResource(R.drawable.icon_logistics);
                    viewHolder.mSup.setText("主营产品:" + replace(topBean.getNeed_product()));
                }
                viewHolder.mStart.setImageResource(topBean.getIs_pass().equals("0")
                        ? R.drawable.icon_identity
                        : R.drawable.icon_identity_hl);

                viewHolder.mTop_Img.setVisibility(View.VISIBLE);
            }
        } catch (Exception e) {
        }
    }

    public void initView(viewHolder viewHolder, View layout) {
        viewHolder.mStart = (ImageView) layout.findViewById(R.id.xq_rz);
        viewHolder.mHead = (MyImageView) layout.findViewById(R.id.xq_tx);
        viewHolder.mTop_Img = (ImageView) layout.findViewById(R.id.top_img);
        viewHolder.mSup = (TextView) layout.findViewById(R.id.txl_listview_gj);
        viewHolder.zhy = (TextView) layout.findViewById(R.id.txl_listview_zhy);
        viewHolder.mSex = (TextView) layout.findViewById(R.id.txl_listview_sex);
        viewHolder.mName = (TextView) layout.findViewById(R.id.txl_listview_mz);
        viewHolder.mSign = (ImageView) layout.findViewById(R.id.contact_sign_img);
        viewHolder.mCompany = (TextView) layout.findViewById(R.id.txl_listview_gs);
        viewHolder.zg = (TextView) layout.findViewById(R.id.txl_listview_pro_month);
    }

    public Spanned replace(String s) {
        s = s.replace("<strong style='color: #ff5000;'>", "<font color='#ff5000'><b>");
        s = s.replace("</strong>", "</b></font>");
        Spanned s1 = Html.fromHtml(s);
        return s1;
    }

    public viewHolder getviewHolder() {
        if (viewHolder == null) {
            viewHolder = new viewHolder();
            return viewHolder;
        }
        return viewHolder;
    }

    class viewHolder {
        MyImageView mHead;
        ImageView mStart, mTop_Img, mSign;
        TextView mCompany, mName, mSex, mSup, zg, zhy, need_text;
    }
}



