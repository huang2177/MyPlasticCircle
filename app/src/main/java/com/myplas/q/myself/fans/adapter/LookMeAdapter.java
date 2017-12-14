package com.myplas.q.myself.fans.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.myplas.q.R;
import com.myplas.q.common.view.pinnedheadlistview.SectionedBaseAdapter;
import com.myplas.q.myself.beans.LookMeBean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 编写： 黄双
 * 电话：15378412400
 * 邮箱：15378412400@163.com
 * 时间：2017/3/20 22:33
 */
public class LookMeAdapter extends SectionedBaseAdapter {
    String userid;
    Context context;
    List<String> time;
    Map<Integer, View> map;
    Map<Integer, LinearLayout> map1;
    List<LookMeBean.DataBean.HistoryBean> list;
    List<LookMeBean.DataBean.HistoryBean.PersonBean> listPerson;
    private OnItemClickListener listener;

    public LookMeAdapter(Context context, List<LookMeBean.DataBean.HistoryBean> list) {
        this.list = list;
        this.context = context;
        map = new HashMap<>();
        map1 = new HashMap<>();
        time = new ArrayList<>();
    }

    public void setList(List<LookMeBean.DataBean.HistoryBean> list) {
        this.list = list;
    }

    @Override
    public Object getItem(int section, int position) {
        return null;
    }

    @Override
    public long getItemId(int section, int position) {
        return 0;
    }

    @Override
    public int getSectionCount() {
        return list != null ? list.size() : 0;
    }

    @Override
    public int getCountForSection(int section) {
        return list.get(section).getPerson() != null
                ? list.get(section).getPerson().size()
                : 0;
    }

    @Override
    public View getItemView(final int section, final int position, View convertView, ViewGroup parent) {
        viewHolder viewHolder = null;
        if (convertView == null) {
            viewHolder = new viewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.item_listview_lookme_layout, null, false);
            viewHolder.tx = (ImageView) convertView.findViewById(R.id.xq_tx);
            viewHolder.rz = (ImageView) convertView.findViewById(R.id.xq_rz);
            viewHolder.gs = (TextView) convertView.findViewById(R.id.lookme_listview_gs);
            viewHolder.mSign = (ImageView) convertView.findViewById(R.id.contact_sign_img);
            viewHolder.time_detail = (TextView) convertView.findViewById(R.id.lookme_listview_time);
            viewHolder.layout = (LinearLayout) convertView.findViewById(R.id.lookme_time_layout);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (viewHolder) convertView.getTag();
        }
        try {
            listPerson = list.get(section).getPerson();
            Glide.with(context).load(listPerson.get(position).getThumb()).
                    placeholder(R.drawable.img_defaul_male).into(viewHolder.tx);
            viewHolder.gs.setText(listPerson.get(position).getCompany() + "  " + listPerson.get(position).getName());
            viewHolder.time_detail.setText(listPerson.get(position).getDate());

            viewHolder.rz.setImageResource("1".equals(listPerson.get(position).getMerge_three())
                    ? R.drawable.icon_identity_hl
                    : 0);

            if ("1".equals(listPerson.get(position).getType())) {
                viewHolder.mSign.setImageResource(R.drawable.icon_factory);
            }
            if ("2".equals(listPerson.get(position).getType())) {
                viewHolder.mSign.setImageResource(R.drawable.icon_raw_material);
            }
            if ("4".equals(listPerson.get(position).getType())) {
                viewHolder.mSign.setImageResource(R.drawable.icon_logistics);
            }

            convertView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClick(section, position);
                }
            });
        } catch (Exception e) {
        }
        return convertView;
    }

    @Override
    public View getSectionHeaderView(int section, View convertView, ViewGroup parent) {
        viewHearderHolder viewHolder = null;
        if (map.get(section) == null) {
            viewHolder = new viewHearderHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.item_listview_lookme_header_layout, null, false);
            viewHolder.time = (TextView) convertView.findViewById(R.id.lookme_time);
            viewHolder.layout = (LinearLayout) convertView.findViewById(R.id.linearhearder);
            map.put(section, convertView);
            map1.put(section, viewHolder.layout);
            convertView.setTag(viewHolder);
        } else {
            convertView = map.get(section);
            viewHolder = (viewHearderHolder) convertView.getTag();
        }
        try {
            viewHolder.time.setText(list.get(section).getTime());
        } catch (Exception e) {

        }
        return convertView;
    }

    class viewHolder {
        LinearLayout layout;
        ImageView tx, rz, mSign;
        TextView gs, time_detail;
    }

    class viewHearderHolder {
        TextView time;
        LinearLayout layout;
    }

    public interface OnItemClickListener {
        void onItemClick(int section, int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }
}
