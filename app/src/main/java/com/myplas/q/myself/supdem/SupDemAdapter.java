package com.myplas.q.myself.supdem;

import android.content.Context;
import android.content.Intent;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.myplas.q.R;
import com.myplas.q.common.utils.TextUtils;
import com.myplas.q.common.view.CommonDialog;
import com.myplas.q.app.activity.BaseActivity;
import com.myplas.q.app.activity.ShareActivity;
import com.myplas.q.common.net.ResultCallBack;
import com.myplas.q.common.utils.SharedUtils;
import com.myplas.q.common.api.API;
import com.myplas.q.myself.beans.MySupDemBean;
import com.myplas.q.release.ReleaseActivity;

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
    int position;
    Context context;
    MyInterface myInterface;
    SharedUtils sharedUtils;
    Map<Integer, View> viewMap;
    List<MySupDemBean.DataBean> list;


    public void setList(List<MySupDemBean.DataBean> list) {
        this.list = list;
    }

    public SupDemAdapter(Context context, List<MySupDemBean.DataBean> list, MyInterface myInterface) {
        this.context = context;
        this.list = list;
        this.myInterface = myInterface;
        viewMap = new HashMap<>();
        sharedUtils = SharedUtils.getSharedUtils();
    }

    @Override
    public int getCount() {
        return list != null ? list.size() : 0;
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
            viewHolder.time = (TextView) convertView.findViewById(R.id.supply_demand_time);
            viewHolder.typeSupDem = (ImageView) convertView.findViewById(R.id.supdem_img_type);
            viewHolder.content = (TextView) convertView.findViewById(R.id.supply_demand_content);
            viewHolder.mImageS = (ImageView) convertView.findViewById(R.id.myself_supdem_img_share);
            viewHolder.mImageD = (ImageView) convertView.findViewById(R.id.myself_supdem_img_delete);
            viewHolder.mLayout = (LinearLayout) convertView.findViewById(R.id.supply_demand_company);
            viewHolder.mImageR = (ImageView) convertView.findViewById(R.id.myself_supdem_img_repeat);
            viewHolder.typeNowFutures = (ImageView) convertView.findViewById(R.id.supply_demand_now_futures);
            try {
                viewHolder.name.setText(list.get(position).getC_name() + "  "
                        + list.get(position).getName());

                String reply = "回复:" + list.get(position).getComments()
                        + "  出价:" + list.get(position).getOffers()
                        + "  " + list.get(position).getInput_time();
                viewHolder.time.setText(reply);

                viewHolder.content.setText(Html.fromHtml(getContent(position)));

                viewHolder.typeSupDem.setImageResource("1".equals(list.get(position).getType())
                        ? R.drawable.icon_supdem_purchase
                        : R.drawable.icon_supdem_supply);
                viewHolder.typeNowFutures.setImageResource("1".equals(list.get(position).getCargo_type())
                        ? R.drawable.icon_now
                        : R.drawable.icon_futures);

                //重发
                viewHolder.mImageR.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(context, ReleaseActivity.class);
                        intent.putExtra("id", (list.get(position).getId()));
                        context.startActivity(intent);
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
                viewHolder.mImageS.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String s = (list.get(position).getType().equals("1")
                                ? "求:"
                                : "供:")
                                + list.get(position).getModel()
                                + list.get(position).getF_name()
                                + "价格" + list.get(position).getUnit_price()
                                + list.get(position).getStore_house()
                                + (list.get(position).getCargo_type().equals("1")
                                ? "现货"
                                : "期货");

                        Intent in = new Intent(context, ShareActivity.class);
                        in.putExtra("title", s);
                        in.putExtra("type", "4");
                        in.putExtra("id", list.get(position).getId());
                        in.putExtra("userid", list.get(position).getUser_id());
                        in.putExtra("supdemType", list.get(position).getType());
                        context.startActivity(in);
                    }
                });
            } catch (Exception e) {
            }
        }
        return convertView;
    }

    private String getContent(int position) {
        return "<font color='#9c9c9c'>" + " 交货地:" + "</font>" + list.get(position).getStore_house()
                + "<font color='#9c9c9c'>" + " 牌号:" + "</font>" + list.get(position).getModel()
                + "<font color='#9c9c9c'>" + " 厂家:" + "</font>" + list.get(position).getF_name()
                + "<font color='#9c9c9c'>" + " 价格:" + "</font>" + list.get(position).getUnit_price();
    }

    class viewHolder {
        LinearLayout mLayout;
        TextView name, content, time;
        ImageView typeSupDem, typeNowFutures, mImageR, mImageS, mImageD;
    }

    @Override
    public void callBack(Object object, int type) {
        TextUtils.toast(context, "删除成功！");
        if (myInterface != null) {
            myInterface.deleteCallBack();
        }
        if (list.size() == 1) {
            list.remove(0);
            notifyDataSetChanged();
        }
    }

    @Override
    public void failCallBack(int type, String message, int httpCode) {
        try {
            JSONObject jsonObject = new JSONObject(message);
            TextUtils.toast(context, jsonObject.getString("message"));
        } catch (Exception e) {

        }
    }

    @Override
    public void dialogClick(int type) {
        if (type != -1) {
            Map<String, String> map = new HashMap<String, String>(16);
            map.put("token", sharedUtils.getData(context, "token"));
            map.put("id", list.get(position).getId());
            BaseActivity.deleteAsyn(context, API.RELEASE_MSG, map, this, 1, false);
        }
    }

    public interface MyInterface {
        void deleteCallBack();
    }
}
