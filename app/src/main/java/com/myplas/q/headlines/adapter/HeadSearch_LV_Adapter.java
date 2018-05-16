package com.myplas.q.headlines.adapter;

import android.content.Context;
import android.text.Html;
import android.text.Spanned;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.myplas.q.R;
import com.myplas.q.headlines.bean.HeadSearchBean;

import java.util.List;

/**
 * 编写： 黄双
 * 电话：15378412400
 * 邮箱：15378412400@163.com
 * 时间：2017/3/21 15:40
 */
public class HeadSearch_LV_Adapter extends BaseAdapter {
    Context context;
    List<HeadSearchBean.NewsBean> list;

    public HeadSearch_LV_Adapter(Context context, List<HeadSearchBean.NewsBean> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        if (list != null) {
            return list.size();
        }
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
        viewHolder viewHolder = null;
        if (convertView == null) {
            viewHolder = new viewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.layout_find_topline_listview_item, parent, false);
            viewHolder.time = (TextView) convertView.findViewById(R.id.fx_tt_title_shj);
            viewHolder.num = (TextView) convertView.findViewById(R.id.fx_tt_title_num);
            viewHolder.title2 = (TextView) convertView.findViewById(R.id.fx_tt_title_text2);
            viewHolder.author = (TextView) convertView.findViewById(R.id.fx_tt_title_author);
            viewHolder.mImgFree = (ImageView) convertView.findViewById(R.id.headline_img_free);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (viewHolder) convertView.getTag();
        }
        viewHolder.num.setText(list.get(position).getPv());
        viewHolder.time.setText(list.get(position).getInput_time());
        viewHolder.title2.setText(replaceContent(list.get(position).getTitle()));
        viewHolder.author.setText(replaceContent(list.get(position).getPhysical_label()));
        viewHolder.mImgFree.setVisibility("1".equals(list.get(position).getIs_free())
                ? View.VISIBLE
                : View.GONE);
        return convertView;
    }

    public void setList(List<HeadSearchBean.NewsBean> list) {
        this.list = list;
    }

    class viewHolder {
        ImageView mImgFree;
        TextView title2, time, num, author;
    }

    public Spanned replaceContent(String s) {
        s = s.replace("<strong style='color: #FF0000;'>", "<font color='#FF0000'><b>");
        s = s.replace("</strong>", "</b></font>");
        Spanned s1 = Html.fromHtml(s);
        return s1;
    }
}
