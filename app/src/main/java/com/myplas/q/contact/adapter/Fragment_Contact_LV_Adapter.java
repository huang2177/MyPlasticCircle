package com.myplas.q.contact.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.text.Html;
import android.text.Spanned;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.myplas.q.R;
import com.myplas.q.common.appcontext.Constant;
import com.myplas.q.common.utils.SharedUtils;
import com.myplas.q.common.utils.TextUtils;
import com.myplas.q.common.view.CommonDialog;
import com.myplas.q.common.view.RoundCornerImageView;
import com.myplas.q.contact.beans.ContactBean;
import com.myplas.q.myself.login.LoginActivity;

import java.util.List;

/**
 * 编写： 黄双
 * 电话：15378412400
 * 邮箱：15378412400@163.com
 * 时间：2017/3/20 19:31
 *
 * @author Administrator
 */
public class Fragment_Contact_LV_Adapter extends BaseAdapter implements CommonDialog.DialogShowInterface {
    Context context;
    viewHolder viewHolder;
    SparseArray<ImageView> array;
    List<ContactBean.PersonsBean> list;
    private CommonDialog.DialogShowInterface showInterface;


    public void setList(List<ContactBean.PersonsBean> list) {
        this.list = list;
    }

    public Fragment_Contact_LV_Adapter(Context context, List<ContactBean.PersonsBean> list) {
        this.list = list;
        this.context = context;
        array = new SparseArray<>();
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
            convertView = LayoutInflater.from(context).inflate(R.layout.item_fragment_contact_lv_layout
                    , parent
                    , false);
            initView(viewHolder, convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (viewHolder) convertView.getTag();
        }
        showInfo(viewHolder, list.get(position));
        return convertView;
    }

    public void showInfo(viewHolder viewHolder, final ContactBean.PersonsBean personsBean) {
        try {
            viewHolder.mHead.setBorderColor(context.getResources().getColor(R.color.color_white));

            Glide.with(context).load(personsBean.getThumb())
                    .placeholder("男".equals(personsBean.getSex())
                            ? R.drawable.img_defaul_male
                            : R.drawable.img_defaul_female)
                    .into(viewHolder.mHead);

            viewHolder.mInfo.setText(replace(personsBean.getC_name()));

            viewHolder.mSign.setVisibility(View.VISIBLE);

            String type = personsBean.getType();
            CharSequence product = personsBean.getMain_product();

            int resId = 0;
            Spanned mid, down;
            if ("1".equals(type)) {
                resId = R.drawable.icon_factory;
                mid = replace("产品:" + product
                        + "  需求:" + personsBean.getNeed_product());
                down = replace(personsBean.getName()
                        + "  " + personsBean.getSex()
                        + "  月用量:" + personsBean.getMonth_consum());

            } else if ("2".equals(type)) {
                resId = R.drawable.icon_raw_material;
                mid = replace("主营:" + product);
                down = replace(personsBean.getName()
                        + "  " + personsBean.getSex());
            } else {
                mid = replace("主营产品:" + product);
                down = replace(personsBean.getName()
                        + "  " + personsBean.getSex());
                if ("4".equals(type)) {
                    resId = R.drawable.icon_logistics;
                }
            }
            viewHolder.mNeed.setText(mid);
            viewHolder.mPro.setText(down);
            viewHolder.mSign.setImageResource(resId);

            if ("1".equals(personsBean.getMerge_three())) {
                Glide.with(context).load(R.drawable.icon_identity_hl).into(viewHolder.mStart);
            }

            viewHolder.mDial.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    call(personsBean.getMobile());
                }
            });
        } catch (Exception e) {
        }
    }

    public void initView(viewHolder viewHolder, View layout) {
        viewHolder.mStart = (ImageView) layout.findViewById(R.id.xq_rz);
        viewHolder.mtopImg = (ImageView) layout.findViewById(R.id.top_img);
        viewHolder.mDial = (ImageView) layout.findViewById(R.id.contact_dial);
        viewHolder.mHead = (RoundCornerImageView) layout.findViewById(R.id.xq_tx);
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

    public void call(String tel) {
        if (!tel.contains("*") && isLogin()) {
            Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + tel));
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
        } else {
            TextUtils.toast(context, tel);
        }
    }

    /**
     * 检查是否登录
     */
    private boolean isLogin() {
        SharedUtils sharedUtils = SharedUtils.getSharedUtils();
        boolean logined = sharedUtils.getBoolean(context, Constant.LOGINED);
        if (!logined) {
            CommonDialog commonDialog = new CommonDialog();
            commonDialog.showDialog(context
                    , sharedUtils.getData(context, Constant.POINTSINFO)
                    , 4
                    , this);
        }
        return logined;
    }

    public void setDialogShowInterface(CommonDialog.DialogShowInterface showInterface) {
        this.showInterface = showInterface;
    }

    @Override
    public void dialogClick(int type) {
        if (type == 4) {
            context.startActivity(new Intent(context, LoginActivity.class));
        }
    }

    class viewHolder {
        RoundCornerImageView mHead;
        TextView mInfo, mNeed, mPro, need_text;
        ImageView mStart, mtopImg, mSign, mDial;
    }
}



