package com.myplas.q.release.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.myplas.q.R;
import com.myplas.q.release.activity.ReleaseSupDemActivity;
import com.myplas.q.supdem.Beans.Supply_DemandBean;

import java.util.List;

/**
 * 编写： 黄双
 * 电话：15378412400
 * 邮箱：15378412400@163.com
 * 时间：2017/3/21 11:25
 */
public class Release_Demand_ListviewAdapter extends BaseAdapter {
    Context context;
    List<Supply_DemandBean.DataBean> list;
    public Release_Demand_ListviewAdapter(Context context, List<Supply_DemandBean.DataBean> list){
        this.context=context;
        this.list=list;
    }
    @Override
    public int getCount() {
        if(list!=null)
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
    public View getView(final int position, View convertView, ViewGroup parent) {
        viewHolder viewHolder;
        if(convertView==null){
            viewHolder=new viewHolder();
            convertView= LayoutInflater.from(context).inflate(R.layout.layout_fb_listview_item,parent,false);
            viewHolder.shj= (TextView) convertView.findViewById(R.id.fb_listview_shj);
            viewHolder.chf= (TextView) convertView.findViewById(R.id.fb_listview_chf);
            viewHolder.content= (TextView) convertView.findViewById(R.id.fb_listview_content);
            convertView.setTag(viewHolder);
        }else {
            viewHolder= (viewHolder) convertView.getTag();
        }
        viewHolder.content.setText(list.get(position).getContent());
        viewHolder.shj.setText(list.get(position).getInput_time());
        viewHolder.chf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(context, ReleaseSupDemActivity.class);
                intent1.putExtra("id",list.get(position).getP_id());
                context.startActivity(intent1);
            }
        });
        return convertView;
    }
    class viewHolder{
        TextView shj,chf,content;
    }
}
