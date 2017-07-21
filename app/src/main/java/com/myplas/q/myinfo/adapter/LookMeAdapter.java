package com.myplas.q.myinfo.adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.myplas.q.R;
import com.myplas.q.common.api.API;
import com.myplas.q.common.netresquset.ResultCallBack;
import com.myplas.q.common.utils.DialogShowUtils;
import com.myplas.q.common.utils.SharedUtils;
import com.myplas.q.common.utils.TextUtils;
import com.myplas.q.common.view.SectionedBaseAdapter;
import com.myplas.q.guide.activity.BaseActivity;
import com.myplas.q.myinfo.activity.IntegralPayActivtity;
import com.myplas.q.myinfo.activity.PersonInfoActivity;
import com.myplas.q.myinfo.beans.LookMeBean;
import com.myplas.q.myinfo.beans.MyIntroductionBean;

import org.json.JSONObject;

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
public class LookMeAdapter extends SectionedBaseAdapter  {
    String userid;
    Context context;
    List<String> time;
    Map<Integer, View> map;
    Map<Integer, LinearLayout> map1;
    List<LookMeBean.DataBean.HistoryBean> list;
    List<LookMeBean.DataBean.HistoryBean.PersonBean> listPerson;
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
        if (list != null)

        return list.size();
        return 0;
    }

    @Override
    public int getCountForSection(int section) {
        if (list.get(section).getPerson() != null)
            return list.get(section).getPerson().size();
        return 0;
    }

    @Override
    public View getItemView(int section, int position, View convertView, ViewGroup parent) {
        viewHolder viewHolder = null;
        if (convertView == null) {
            viewHolder = new viewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.item_listview_lookme_layout, null, false);
            viewHolder.tx = (ImageView) convertView.findViewById(R.id.xq_tx);
            viewHolder.rz = (ImageView) convertView.findViewById(R.id.xq_rz);
            viewHolder.gs = (TextView) convertView.findViewById(R.id.lookme_listview_gs);
            viewHolder.time_detail = (TextView) convertView.findViewById(R.id.lookme_listview_time);
            viewHolder.layout = (LinearLayout) convertView.findViewById(R.id.lookme_time_layout);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (viewHolder) convertView.getTag();
        }
        try {
            listPerson = list.get(section).getPerson();
            Glide.with(context).load(listPerson.get(position).getThumb()).
                    placeholder(R.drawable.contact_image_defaul_male).into(viewHolder.tx);
            viewHolder.gs.setText(listPerson.get(position).getCompany() + "  " + listPerson.get(position).getName());
            viewHolder.time_detail.setText(listPerson.get(position).getDate());
            if (listPerson.get(position).getIsvip().equals("0")) {
                viewHolder.rz.setImageResource(R.drawable.icon_identity);
            } else if (listPerson.get(position).getIsvip().equals("1")) {
                viewHolder.rz.setImageResource(R.drawable.icon_identity_hl);
            }

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
            Log.e("==========",list.size()+"======");
            viewHolder.time.setText(list.get(section).getTime());
        } catch (Exception e) {

        }
        return convertView;
    }

    class viewHolder {
        ImageView tx, rz;
        LinearLayout layout;
        TextView gs, time_detail;
    }

    class viewHearderHolder {
        TextView time;
        LinearLayout layout;
    }
}
