package com.myplas.q.contact.adapter;

import android.content.Context;
import android.text.Html;
import android.text.Spanned;
import android.util.Log;
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
        return list.size() != 0 ? list.size() : 0;
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
        showInfo(viewHolder, list.get(position));
        return convertView;
    }

    public void showInfo(viewHolder viewHolder, ContactBean.PersonsBean personsBean) {
        try {
            viewHolder.mHead.setBorderColor(context
                    .getResources()
                    .getColor(R.color.color_white));

            Glide.with(context)
                    .load(personsBean.getThumb())
                    .into(viewHolder.mHead);

            viewHolder.mInfo.setText(replace(personsBean.getC_name()
                    + "    " + personsBean.getName()
                    + "    " + personsBean.getSex()));

            viewHolder.mSign.setVisibility(View.VISIBLE);
            String type = personsBean.getType();
            CharSequence product = replace(personsBean.getMain_product());
            if (type.equals("1")) {
                viewHolder.mNeed.setVisibility(View.VISIBLE);
                viewHolder.mPro.setVisibility(View.VISIBLE);
                viewHolder.mSign.setImageResource(R.drawable.icon_factory);
                viewHolder.mNeed.setText("产品:" + product
                        + "  月用量:" + personsBean.getMonth_consum());

                viewHolder.mPro.setText(replace("需求:" + personsBean.getNeed_product()));

            } else if (type.equals("2")) {
                viewHolder.mNeed.setVisibility(View.INVISIBLE);
                viewHolder.mPro.setVisibility(View.VISIBLE);
                viewHolder.mSign.setImageResource(R.drawable.icon_raw_material);
                viewHolder.mPro.setText(replace("主营:" + personsBean.getNeed_product()));
            } else {
                viewHolder.mNeed.setVisibility(View.INVISIBLE);
                viewHolder.mPro.setVisibility(View.VISIBLE);
                viewHolder.mPro.setText(replace("主营产品:" + personsBean.getNeed_product()));
                if (type.equals("4")) {
                    viewHolder.mSign.setImageResource(R.drawable.icon_logistics);
                }
            }
            viewHolder.mStart.setImageResource(personsBean.getIs_pass().equals("0")
                    ? R.drawable.icon_identity
                    : R.drawable.icon_identity_hl);
        } catch (Exception e) {
        }
    }


    public void initView(viewHolder viewHolder, View layout) {
        viewHolder.mStart = (ImageView) layout.findViewById(R.id.xq_rz);
        viewHolder.mHead = (MyImageView) layout.findViewById(R.id.xq_tx);
        viewHolder.mTop_Img = (ImageView) layout.findViewById(R.id.top_img);
        viewHolder.mSign = (ImageView) layout.findViewById(R.id.contact_sign_img);
        viewHolder.mNeed = (TextView) layout.findViewById(R.id.txl_listview_need);
        viewHolder.mInfo = (TextView) layout.findViewById(R.id.txl_listview_info);
        viewHolder.mPro = (TextView) layout.findViewById(R.id.txl_listview_product);
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
        TextView mInfo, mNeed, mPro, need_text;
    }
}



