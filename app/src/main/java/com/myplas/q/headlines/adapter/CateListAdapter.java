package com.myplas.q.headlines.adapter;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.text.Html;
import android.text.Spanned;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.myplas.q.R;
import com.myplas.q.headlines.bean.CateListBean;

import java.util.List;

/**
 * 编写： 黄双
 * 电话：15378412400
 * 邮箱：15378412400@163.com
 * 时间：2017/4/5 10:42
 */
public class CateListAdapter extends BaseAdapter {
    Context context;
    List<CateListBean.InfoBean> list;

    public CateListAdapter(Context context, List<CateListBean.InfoBean> list) {
        this.context = context;
        this.list = list;
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
    public View getView(final int position, View convertView, ViewGroup parent) {
        viewHolder viewHolder = null;
        if (convertView == null) {
            viewHolder = new viewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.layout_find_topline_listview_item, parent, false);
            viewHolder.num = (TextView) convertView.findViewById(R.id.fx_tt_title_num);
            viewHolder.time = (TextView) convertView.findViewById(R.id.fx_tt_title_shj);
            viewHolder.title2 = (TextView) convertView.findViewById(R.id.fx_tt_title_text2);
            viewHolder.author = (TextView) convertView.findViewById(R.id.fx_tt_title_author);
            viewHolder.mImgSee = (ImageView) convertView.findViewById(R.id.fx_tt_img_see);
            viewHolder.mImgTime = (ImageView) convertView.findViewById(R.id.fx_tt_img_time);
            viewHolder.mImgFree = (ImageView) convertView.findViewById(R.id.headline_img_free);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (viewHolder) convertView.getTag();
        }

//        ViewGroup.LayoutParams lp_time = viewHolder.time.getLayoutParams();
//        ViewGroup.LayoutParams lp_Timg = viewHolder.mImgTime.getLayoutParams();
//        lp_Timg.height = getFontHeight(viewHolder.time.getTextSize())*2/3;
//        lp_Timg.width = getFontHeight(viewHolder.time.getTextSize())*2/3;
//        Log.e("-------",getFontHeight(viewHolder.time.getTextSize())+"");
//        viewHolder.mImgTime.setLayoutParams(lp_Timg);

        viewHolder.num.setText(list.get(position).getPv());
        viewHolder.author.setText(list.get(position).getType());
        viewHolder.time.setText(list.get(position).getInput_time());
        viewHolder.title2.setText(replace(list.get(position).getTitle()));
        viewHolder.mImgFree.setVisibility("1".equals(list.get(position).getIs_free())
                ? View.VISIBLE
                : View.GONE);
        return convertView;
    }

    public void setList(List<CateListBean.InfoBean> list) {
        this.list = list;
    }

    class viewHolder {
        TextView title2, time, num, author;
        ImageView mImgTime, mImgSee, mImgFree;
    }

    public Spanned replace(String s) {
        s = s.replace("<strong style='color: #ff5000;'>", "<font color='#ff5000'><b>");
        s = s.replace("</strong>", "</b></font>");
        Spanned s1 = Html.fromHtml(s);
        return s1;
    }

    public int getFontHeight(float fontSize) {
        Paint paint = new Paint();
        paint.setTextSize(fontSize);
        Paint.FontMetrics fm = paint.getFontMetrics();
        return (int) Math.ceil(fm.descent - fm.top) + 2;
    }
}
