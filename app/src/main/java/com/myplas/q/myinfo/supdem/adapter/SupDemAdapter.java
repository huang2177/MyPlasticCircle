package com.myplas.q.myinfo.supdem.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.myplas.q.R;
import com.myplas.q.common.view.CommonDialog;
import com.myplas.q.guide.activity.BaseActivity;
import com.myplas.q.guide.activity.ShareActivity;
import com.myplas.q.common.netresquset.ResultCallBack;
import com.myplas.q.common.utils.SharedUtils;
import com.myplas.q.common.api.API;
import com.myplas.q.myinfo.beans.MySupDemBean;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 编写： 黄双
 * 电话：15378412400
 * 邮箱：15378412400@163.com
 * 时间：2017/3/23 16:29
 */
public class SupDemAdapter extends BaseAdapter implements ResultCallBack, CommonDialog.DialogShowInterface {
    String type;
    int position;
    Context context;
    MyInterface myInterface;
    SharedUtils sharedUtils;
    Map<Integer, View> viewMap;
    List<MySupDemBean.DataBean> list;


    public void setList(List<MySupDemBean.DataBean> list) {
        this.list = list;
    }

    public SupDemAdapter(Context context, List<MySupDemBean.DataBean> list, String type, MyInterface myInterface) {
        this.context = context;
        this.list = list;
        this.type = type;
        this.myInterface = myInterface;
        viewMap = new HashMap<>();
        sharedUtils = SharedUtils.getSharedUtils();
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
            convertView = LayoutInflater.from(context).inflate(R.layout.item_myself_supdem_layout, parent, false);
            viewMap.put(position, convertView);
            viewHolder.name = (TextView) convertView.findViewById(R.id.gq_listview_name);
            viewHolder.company = (TextView) convertView.findViewById(R.id.gq_listview_gs);
            viewHolder.time = (TextView) convertView.findViewById(R.id.supply_demand_time);
            viewHolder.typeSupDem = (ImageView) convertView.findViewById(R.id.supdem_img_type);
            viewHolder.content = (TextView) convertView.findViewById(R.id.supply_demand_content);
            viewHolder.mImageS = (ImageView) convertView.findViewById(R.id.myself_supdem_img_share);
            viewHolder.mImageD = (ImageView) convertView.findViewById(R.id.myself_supdem_img_delete);
            viewHolder.mLayout = (LinearLayout) convertView.findViewById(R.id.supply_demand_company);
            viewHolder.mImageR = (ImageView) convertView.findViewById(R.id.myself_supdem_img_repeat);
            viewHolder.typeNowFutures = (ImageView) convertView.findViewById(R.id.supply_demand_now_futures);
            try {
                viewHolder.name.setText(list.get(position).getName());
                viewHolder.company.setText(list.get(position).getC_name());
                String reply = "出价:" + list.get(position).getHui_count() + "  出价:" + list.get(position).getChu_count();
                viewHolder.time.setText(reply);

                viewHolder.content.setText(Html.fromHtml(getContent(position)));

                viewHolder.typeSupDem.setImageResource(list.get(position).getType().equals("1")
                        ? R.drawable.icon_supdem_purchase
                        : R.drawable.icon_supdem_supply);
                viewHolder.typeNowFutures.setImageResource(list.get(position).getCargo_type().equals("1")
                        ? R.drawable.icon_now
                        : R.drawable.icon_futures);


                //重发
                viewHolder.mImageR.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
//                Intent intent = new Intent(context, ReleaseSupDemActivity.class);
//                intent.putExtra("id", (list.get(position).getId()));
//                intent.putExtra("type", list.get(position).getType());
//                context.startActivity(intent);
                    }
                });
                //删除
                viewHolder.mImageD.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        SupDemAdapter.this.position = position;
                        CommonDialog commonDialog = new CommonDialog();
                        commonDialog.showDialog(context, "确定删除?", 1, SupDemAdapter.this);
                    }
                });
                //分享
                viewHolder.mImageR.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String s = (list.get(position).getType().equals("1")) ? ("求购信息:") : ("供给信息：");
                        Intent in = new Intent(context, ShareActivity.class);
                        in.putExtra("type", "4");
                        in.putExtra("id", list.get(position).getId());
                        in.putExtra("title", s + getContent(position));
                        in.putExtra("t", list.get(position).getType());
                        context.startActivity(in);
                    }
                });
            } catch (Exception e) {
            }
        }
        return convertView;
    }

    private String getContent(int position) {
        return "<font color='#9c9c9c'>" + " 货物位置:" + "</font>" + list.get(position).getStore_house()
                + "<font color='#9c9c9c'>" + " 牌号:" + "</font>" + list.get(position).getModel()
                + "<font color='#9c9c9c'>" + " 厂家:" + "</font>" + list.get(position).getF_name()
                + "<font color='#9c9c9c'>" + " 价格:" + "</font>" + list.get(position).getUnit_price();
    }

    class viewHolder {
        LinearLayout mLayout;
        TextView company, name, content, time;
        ImageView typeSupDem, typeNowFutures, mImageR, mImageS, mImageD;
    }

    @Override
    public void callBack(Object object, int type) {
        try {
            JSONObject jsonObject = new JSONObject(object.toString());
            if (type == 1 && jsonObject.getString("err").equals("0")) {
                myInterface.reQuestNet();
                if (list.size() == 1) {
                    list.remove(0);
                    notifyDataSetChanged();
                }
            }
        } catch (Exception e) {
        }
    }

    @Override
    public void ok(int type) {
        switch (type) {
            case 1:
                Map<String, String> map = new HashMap<String, String>();
                map.put("token", sharedUtils.getData(context, "token"));
                map.put("id", list.get(position).getId());
                BaseActivity.postAsyn(context, API.BASEURL + API.DELETE_MY_MSG, map, SupDemAdapter.this, 1);
                break;
        }
    }

    @Override
    public void failCallBack(int type) {

    }

    public interface MyInterface {
        void reQuestNet();
    }
}
