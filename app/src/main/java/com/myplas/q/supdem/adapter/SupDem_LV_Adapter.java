package com.myplas.q.supdem.adapter;

import android.content.Context;
import android.content.Intent;
import android.text.Html;
import android.text.Spanned;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.myplas.q.R;
import com.myplas.q.app.activity.BaseActivity;
import com.myplas.q.common.api.API;
import com.myplas.q.common.netresquset.ResultCallBack;
import com.myplas.q.common.view.CommonDialog;
import com.myplas.q.common.utils.SharedUtils;
import com.myplas.q.myself.integral.activity.IntegralPayActivtity;
import com.myplas.q.contact.activity.Contact_Detail_Activity;

import com.myplas.q.supdem.beans.SupDemBean;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 编写： 黄双
 * 电话：15378412400
 * 邮箱：15378412400@163.com
 * 时间：2017/3/19 14:55
 */
public class SupDem_LV_Adapter extends BaseAdapter implements ResultCallBack, CommonDialog.DialogShowInterface {
    Context context;
    String user_id, id_;
    List<SupDemBean.DataBean> list;
    SharedUtils sharedUtils = SharedUtils.getSharedUtils();

    public SupDem_LV_Adapter(Context context, List<SupDemBean.DataBean> list) {
        this.context = context;
        this.list = list;
    }

    public void setList(List<SupDemBean.DataBean> list) {
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size() != 0 ? list.size() : 0;
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
            convertView = LayoutInflater.from(context).inflate(R.layout.item_supdem_lv_layout, parent, false);
            viewHolder.company = (TextView) convertView.findViewById(R.id.gq_listview_gs);
            viewHolder.time = (TextView) convertView.findViewById(R.id.supply_demand_time);
            viewHolder.typeSupDem = (ImageView) convertView.findViewById(R.id.supdem_img_type);
            viewHolder.content = (TextView) convertView.findViewById(R.id.supply_demand_content);
            viewHolder.reply = (TextView) convertView.findViewById(R.id.supply_demand_reply);
            viewHolder.deliver = (TextView) convertView.findViewById(R.id.supply_demand_deliver);
            viewHolder.mLayout = (LinearLayout) convertView.findViewById(R.id.supply_demand_company);
            viewHolder.typeNowFutures = (ImageView) convertView.findViewById(R.id.supply_demand_now_futures);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (viewHolder) convertView.getTag();
        }
        try {
            viewHolder.company.setText(replace(list.get(position).getC_name()) + "  "
                    + list.get(position).getName());

            viewHolder.time.setText(list.get(position).getInput_time());
            if ("1".equals(list.get(position).getFrom())) {
                viewHolder.reply.setText("回复(" + list.get(position).getReplyCount() + ")");
                viewHolder.deliver.setText("出价(" + list.get(position).getPlaticCount() + ")");
                viewHolder.reply.setCompoundDrawablesWithIntrinsicBounds(R.drawable.icon_sd_reply, 0, 0, 0);
                viewHolder.deliver.setCompoundDrawablesWithIntrinsicBounds(R.drawable.icon_sd_offer, 0, 0, 0);
            }

            String html1 = "<font color='#9c9c9c'>" + "交货地:" + "</font>" + list.get(position).getStore_house()
                    + "<font color='#9c9c9c'>" + "   牌号:" + "</font>" + list.get(position).getModel()
                    + "<font color='#9c9c9c'>" + "   厂家:" + "</font>" + list.get(position).getF_name()
                    + "<font color='#9c9c9c'>" + "   价格:" + "</font>" + list.get(position).getUnit_price();
            viewHolder.content.setText(replace(html1));

            viewHolder.typeSupDem.setImageResource(list.get(position).getType().equals("1")
                    ? R.drawable.icon_supdem_purchase
                    : R.drawable.icon_supdem_supply);
            viewHolder.typeNowFutures.setImageResource(list.get(position).getCargo_type().equals("1")
                    ? R.drawable.icon_now
                    : R.drawable.icon_futures);

            //点击判断是否消耗积分
            viewHolder.mLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (list.get(position).getFrom().equals("1")) {
                        user_id = list.get(position).getUser_id();
                        id_ = list.get(position).getId();
                        getPersonInfoData(user_id, "1", 1);
                    }
                }
            });
        } catch (Exception e) {
        }
        return convertView;
    }

    public void getPersonInfoData(String userid, String showtype, int type) {
        Map<String, String> map = new HashMap<String, String>();
        map.put("token", sharedUtils.getData(context, "token"));
        map.put("user_id", userid);
        map.put("showType", showtype);
        String url = API.BASEURL + API.GET_ZONE_FRIEND;
        BaseActivity.postAsyn(context, url, map, this, type);
    }

    class viewHolder {
        LinearLayout mLayout;
        ImageView typeSupDem, typeNowFutures;
        TextView company, content, time, deliver, reply;
    }

    @Override
    public void callBack(Object object, int type) {
        try {
            String err = new JSONObject(object.toString()).getString("err");
            //是否消耗积分
            if (type == 1 && err.equals("99")) {
                String content = new JSONObject(object.toString()).getString("msg");
                CommonDialog commonDialog = new CommonDialog();
                commonDialog.showDialog(context, content, 1, this);
            }
            //已经消费了积分
            if (type == 1 && err.equals("0")) {
                Intent intent = new Intent(context, Contact_Detail_Activity.class);
                intent.putExtra("userid", user_id);
                intent.putExtra("id", user_id);
                context.startActivity(intent);
            }
            //减积分成功
            if (type == 2 && err.equals("0")) {
                Intent intent = new Intent(context, Contact_Detail_Activity.class);
                intent.putExtra("userid", user_id);
                intent.putExtra("id", user_id);
                context.startActivity(intent);

            }
            //积分不够
            if (type == 2 && !err.equals("0")) {
                String content = new JSONObject(object.toString()).getString("msg");
                CommonDialog commonDialog = new CommonDialog();
                commonDialog.showDialog(context, content, (err.equals("100")) ? (2) : (3), this);
            }
        } catch (Exception e) {
        }
    }

    @Override
    public void failCallBack(int type) {

    }

    //dialog回调
    @Override
    public void ok(int type) {
        switch (type) {
            case 1:
                getPersonInfoData(user_id, "5", 2);
                break;
            case 2:
                context.startActivity(new Intent(context, IntegralPayActivtity.class));
                break;
            default:
                break;
        }
    }

    public Spanned replace(String s) {
        s = s.replace("<strong style='color: #ff5000;'>", "<font color='#ff5000'><b>");
        s = s.replace("</strong>", "</b></font>");
        Spanned s1 = Html.fromHtml(s);
        return s1;
    }
}
