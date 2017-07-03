package com.myplas.q.supdem.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.myplas.q.R;
import com.myplas.q.common.netresquset.ResultCallBack;
import com.myplas.q.supdem.Beans.SearchResultBean;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

/**
 * 编写： 黄双
 * 电话：15378412400
 * 邮箱：15378412400@163.com
 * 时间：2017/3/19 14:55
 */
public class SupDem_Search_List_Adapter extends BaseAdapter implements ResultCallBack{
    Context context;
    List<SearchResultBean.ListBean> list;

    public SupDem_Search_List_Adapter(Context context, List<SearchResultBean.ListBean> list) {
        this.context = context;
        this.list = list;
    }

    public void setList(List<SearchResultBean.ListBean> list) {
        this.list = list;
    }

    @Override
    public int getCount() {
        if (list != null)
            return list.size();
        return list.size();
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
            convertView = LayoutInflater.from(context).inflate(R.layout.supdem_list_item, parent, false);
            viewHolder.textView_title = (TextView) convertView.findViewById(R.id.fx_tt_title_text);
            viewHolder.textView_content = (TextView) convertView.findViewById(R.id.fx_tt_title_content);
            viewHolder.textView_time = (TextView) convertView.findViewById(R.id.fx_tt_title_author);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (viewHolder) convertView.getTag();
        }
        viewHolder.textView_title.setText(list.get(position).getCompany());
        viewHolder.textView_content.setText("货物位置:"+list.get(position).getGoodssPosition()+
                "  牌号:"+list.get(position).getPlsticNumber()+
                "  产地:"+list.get(position).getProduction()+
                "  "+list.get(position).getISForward());
        viewHolder.textView_time.setText((list.get(position).getType().equals("9"))?
                ("来自QQ群  "+list.get(position).getUpdateTime()):
                ("来自供求  "+list.get(position).getUpdateTime()));
        return convertView;
    }
    class viewHolder {
        TextView textView_title,textView_content,textView_time;
    }

    @Override
    public void callBack(Object object, int type) {
        try {
            String err = new JSONObject(object.toString()).getString("err");
        } catch (JSONException e) {
        }
    }
    @Override
    public void failCallBack(int type) {

    }
}
