package com.myplas.q.supdem.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.myplas.q.R;
import com.myplas.q.common.netresquset.ResultCallBack;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

/**
 * 编写： 黄双
 * 电话：15378412400
 * 邮箱：15378412400@163.com
 * 时间：2017/3/19 14:55
 */
public class SupDem_Search_QQ_Detail_Adapter extends BaseAdapter {
    int type;
    int resId;
    Context context;
    List<String> list;

    public SupDem_Search_QQ_Detail_Adapter(Context context, List<String> list,int type) {
        this.context = context;
        this.list = list;
        this.type=type;
    }

    public void setType(int type) {
        this.type = type;
    }


    public void setList(List<String> list) {
        this.list = list;
    }

    @Override
    public int getCount() {
        if (list!=null)
            return list.size();
        return 4;
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
        viewHolder viewHolder = null;
        if (convertView == null) {
            viewHolder = new viewHolder();
            if (type == 1) {  //资讯
                convertView = LayoutInflater.from(context).inflate(R.layout.supdem_detail_qq_item1, parent, false);
                viewHolder.textView_zx_title = (TextView) convertView.findViewById(R.id.supdem_qq_item1_text_title);
                viewHolder.textView_zx_content = (TextView) convertView.findViewById(R.id.supdem_qq_item1_text_content);
            } else if (type == 2) {
                convertView = LayoutInflater.from(context).inflate(R.layout.supdem_detail_qq_item2, parent, false);
                viewHolder.textView_find = (TextView) convertView.findViewById(R.id.supdem_qq_item2_text);
                viewHolder.imageView = (ImageView) convertView.findViewById(R.id.supdem_qq_item2_img);
            } else {
                convertView = LayoutInflater.from(context).inflate(R.layout.supdem_detail_qq_item3, parent, false);
                viewHolder.textView_tell = (TextView) convertView.findViewById(R.id.supdem_qq_item3_text);
            }
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (viewHolder) convertView.getTag();
        }
        if (type == 1) {  //资讯
            //viewHolder.textView_zx.setText(list.get(position));
        } else if (type == 2) {

        } else {

        }

        return convertView;
    }

    class viewHolder {
        TextView textView_zx_title,textView_zx_content,textView_find,textView_tell;
        ImageView imageView;
    }
}
