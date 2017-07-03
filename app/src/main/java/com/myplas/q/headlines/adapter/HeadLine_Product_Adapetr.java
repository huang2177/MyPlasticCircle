package com.myplas.q.headlines.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.myplas.q.R;
import com.myplas.q.headlines.bean.CateListSelectBean;

import java.util.ArrayList;
import java.util.List;

/**
 * 编写： 黄双
 * 电话：15378412400
 * 邮箱：15378412400@163.com
 * 时间：2017/3/21 19:27
 */
public class HeadLine_Product_Adapetr extends BaseAdapter{
    Context context;
    List<ImageView> list_img;
    List<CateListSelectBean> list;
    viewHolder viewHolder = null;
    List<ImageView> list_checkbox;
    public void setList(List<CateListSelectBean> list) {
        this.list = list;
    }

    public void setPosition(int position) {
        if(!list.get(position).isSelected()){
            list.get(position).setSelected(true);
            list_checkbox.get(position).setImageResource(R.drawable.btn_chekbox_hl1);
        } else if(list.get(position).isSelected()){
            list.get(position).setSelected(false);
            list_checkbox.get(position).setImageResource(R.drawable.btn_checkbox);
        }
    }
    public HeadLine_Product_Adapetr(Context context) {
        this.context = context;
        list_checkbox = new ArrayList<ImageView>();
        list_img = new ArrayList<ImageView>();
    }
    @Override
    public int getCount() {
        if (list.size() != 0)
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
        if (convertView == null) {
            viewHolder = new viewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.layout_find_cate_all_gridview_item, parent, false);
            viewHolder.img = (ImageView) convertView.findViewById(R.id.fx_gd_gridview_img);
            viewHolder.img_check = (ImageView) convertView.findViewById(R.id.fx_gd_gridview_check_img);
            list_checkbox.add(viewHolder.img_check);
            list_img.add(viewHolder.img);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (viewHolder) convertView.getTag();
        }
        if(list.get(position).isSaveed()){
            list_checkbox.get(position).setImageResource(R.drawable.btn_chekbox_hl1);
        }else {
            list_checkbox.get(position).setImageResource(R.drawable.btn_checkbox);
        }
        viewHolder.img.setImageResource(Integer.parseInt(list.get(position).getImg()));
        return convertView;
    }
    class viewHolder {
        ImageView img,img_check;
    }
    public interface MyInterface{
        void getSeleted(String cate_id);
    }
}
