package com.myplas.q.supdem.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.myplas.q.R;
import com.myplas.q.common.netresquset.ResultCallBack;
import com.myplas.q.supdem.Beans.SearchResultDetailBean;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import static android.R.id.list;

/**
 * 编写： 黄双
 * 电话：15378412400
 * 邮箱：15378412400@163.com
 * 时间：2017/3/19 14:55
 */
public class SupDem_Search_QQ_Detail_Adapter extends BaseAdapter {
    int type;
    Context context;
    List<SearchResultDetailBean.DataBean.IphoneListBean> list_phone;
    List<SearchResultDetailBean.DataBean.FriendSearchBean> list_friend;
    List<SearchResultDetailBean.DataBean.ShowInformationBean> list_showinfo;

    public SupDem_Search_QQ_Detail_Adapter(Context context, int type) {
        this.context = context;
        this.type = type;
    }

    public void setList_phone(List<SearchResultDetailBean.DataBean.IphoneListBean> list_phone) {
        this.list_phone = list_phone;
    }

    public void setList_friend(List<SearchResultDetailBean.DataBean.FriendSearchBean> list_friend) {
        this.list_friend = list_friend;
    }

    public void setList_showinfo(List<SearchResultDetailBean.DataBean.ShowInformationBean> list_showinfo) {
        this.list_showinfo = list_showinfo;
    }

    @Override
    public int getCount() {
        switch (type) {
            case 1:
                return (list_showinfo != null) ? (list_showinfo.size()) : (0);
            case 2:
                return (list_friend != null) ? (list_friend.size()) : (0);
            case 3:
                return (list_phone != null) ? (list_phone.size()) : (0);
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
    public View getView(final int position, View convertView, ViewGroup parent) {
        viewHolder viewHolder = null;
        if (convertView == null) {
            viewHolder = new viewHolder();
            if (type == 1) {  //资讯
                convertView = LayoutInflater.from(context).inflate(R.layout.supdem_detail_qq_item1, parent, false);
                viewHolder.textView_zx_title = (TextView) convertView.findViewById(R.id.supdem_qq_item1_text_title);
                viewHolder.textView_zx_content = (TextView) convertView.findViewById(R.id.supdem_qq_item1_text_content);
            } else if (type == 2) {//他们在找
                convertView = LayoutInflater.from(context).inflate(R.layout.supdem_detail_qq_item2, parent, false);
                viewHolder.textView_find1 = (TextView) convertView.findViewById(R.id.supdem_qq_item2_text1);
                viewHolder.textView_find2 = (TextView) convertView.findViewById(R.id.supdem_qq_item2_text2);
                viewHolder.textView_find3 = (TextView) convertView.findViewById(R.id.supdem_qq_item2_text3);
                viewHolder.imageView = (ImageView) convertView.findViewById(R.id.supdem_qq_item2_img);
            } else {//电话
                convertView = LayoutInflater.from(context).inflate(R.layout.supdem_detail_qq_item3, parent, false);
                viewHolder.textView_tell = (TextView) convertView.findViewById(R.id.supdem_qq_item3_text);
            }
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (viewHolder) convertView.getTag();
        }
        if (type == 1) {  //资讯
            viewHolder.textView_zx_content.setText(list_showinfo.get(position).getTitle());
            viewHolder.textView_zx_title.setText(list_showinfo.get(position).getCate_name());
        } else if (type == 2) {
            viewHolder.textView_find1.setText(list_friend.get(position).getCompany());
            viewHolder.textView_find2.setText(list_friend.get(position).getMobile());
            viewHolder.textView_find3.setText(list_friend.get(position).getContent());
            Glide.with(context).load(list_friend.get(position).getQQImage()).into(viewHolder.imageView);
        } else {
            viewHolder.textView_tell.setText(list_phone.get(position).getUserName() + "  " + list_phone.get(position).getIphone());
        }
        return convertView;
    }

    class viewHolder {
        TextView textView_zx_title, textView_zx_content, textView_find1, textView_find2, textView_find3, textView_tell;
        ImageView imageView;
    }
}
