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

import com.bumptech.glide.Glide;
import com.myplas.q.R;
import com.myplas.q.guide.activity.BaseActivity;
import com.myplas.q.guide.activity.ShareActivity;
import com.myplas.q.common.api.API;
import com.myplas.q.common.netresquset.ResultCallBack;
import com.myplas.q.common.view.CommonDialog;
import com.myplas.q.common.utils.SharedUtils;
import com.myplas.q.myinfo.integral.activity.IntegralPayActivtity;
import com.myplas.q.myinfo.fans.activity.PersonInfoActivity;

import com.myplas.q.release.activity.ReleaseSupDemActivity;
import com.myplas.q.supdem.Beans.Supply_DemandBean;
import com.myplas.q.supdem.activity.SupDem_Detail_Activity;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.myplas.q.supdem.Beans.ItemBean.itemBean;

/**
 * 编写： 黄双
 * 电话：15378412400
 * 邮箱：15378412400@163.com
 * 时间：2017/3/19 14:55
 */
public class GQ_ListviewAdapter extends BaseAdapter implements ResultCallBack, CommonDialog.DialogShowInterface {
    Context context;
    String type, user_id, id_;
    List<Supply_DemandBean.DataBean> list;
    SharedUtils sharedUtils = SharedUtils.getSharedUtils();

    public GQ_ListviewAdapter(String type, Context context, List<Supply_DemandBean.DataBean> list) {
        this.context = context;
        this.list = list;
        this.type = type;
    }

    public void setList(List<Supply_DemandBean.DataBean> list) {
        this.list = list;
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
        viewHolder viewHolder = null;
        if (convertView == null) {
            viewHolder = new viewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.item_listview_supdem_firstitem, parent, false);
            viewHolder.tx = (ImageView) convertView.findViewById(R.id.xq_tx);
            viewHolder.rz = (ImageView) convertView.findViewById(R.id.xq_rz);
            viewHolder.img = (ImageView) convertView.findViewById(R.id.supply_demand_img);
            viewHolder.name = (TextView) convertView.findViewById(R.id.gq_listview_name);
            viewHolder.gs = (TextView) convertView.findViewById(R.id.gq_listview_gs);
            viewHolder.content = (TextView) convertView.findViewById(R.id.supply_demand_content);
            viewHolder.linearLayout = (LinearLayout) convertView.findViewById(R.id.supply_demand_linear);
            viewHolder.time = (TextView) convertView.findViewById(R.id.supply_demand_time);
            viewHolder.repeat = (TextView) convertView.findViewById(R.id.supply_demand_repeat);
            viewHolder.isbuyed = (TextView) convertView.findViewById(R.id.supply_demand_isbuyed);
            viewHolder.send = (TextView) convertView.findViewById(R.id.supply_demand_send);
            viewHolder.share = (TextView) convertView.findViewById(R.id.supply_demand_share);
            if ("4".equals(type)) {
                viewHolder.send.setVisibility(View.VISIBLE);
                viewHolder.share.setVisibility(View.VISIBLE);
            } else {
                viewHolder.send.setVisibility(View.GONE);
                viewHolder.share.setVisibility(View.GONE);
            }
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (viewHolder) convertView.getTag();
        }
        try {
            Glide.with(context)
                    .load(list.get(position).getThumb())
                    .placeholder((list.get(position).getSex().equals("男")) ? (R.drawable.contact_image_defaul_male) : (R.drawable.contact_image_defaul_female))
                    .into(viewHolder.tx);
            Spanned s1 = replace(list.get(position).getC_name());
            viewHolder.gs.setText((s1.length() > 15) ? (s1.subSequence(0, 15) + "...") : (s1));
            viewHolder.name.setText("  " + list.get(position).getName());
            viewHolder.time.setText(list.get(position).getInput_time());
            String s = ("4".equals(type)) ? ("看") : ("");
            viewHolder.isbuyed.setText(s + "出价(" + list.get(position).getDeliverPriceCount() + ")");
            viewHolder.repeat.setText(s + "回复(" + list.get(position).getSaysCount() + ")");
            if (list.get(position).getIs_pass().equals("0")) {
                viewHolder.rz.setImageResource(R.drawable.icon_identity);
            } else if (list.get(position).getIs_pass().equals("1")) {
                viewHolder.rz.setImageResource(R.drawable.icon_identity_hl);
            }
            if (list.get(position).getType().equals("1")) {
                String html1 = "<font color='#EEAD0E'>" + " 求购：" + "</font>" + list.get(position).getContents();
                viewHolder.img.setImageResource(R.drawable.icon_purchase);
                viewHolder.content.setText(replace(html1));
            } else {
                viewHolder.img.setImageResource(R.drawable.icon_supply);
                String html1 = "<font color='#36648B'>" + " 供给：" + "</font>" + list.get(position).getContents();
                viewHolder.content.setText(replace(html1));
            }
            if (list.get(position).getContents().equals("")) {
                viewHolder.img.setVisibility(View.GONE);
                viewHolder.content.setVisibility(View.GONE);
            } else {
                viewHolder.img.setVisibility(View.VISIBLE);
                viewHolder.content.setVisibility(View.VISIBLE);
            }

        } catch (Exception e) {
        }
        //点击判断是否消耗积分
        viewHolder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                user_id = list.get(position).getUser_id();
                id_ = list.get(position).getId();
                getPersonInfoData(user_id, "1", 1);
            }
        });
        //出价
        viewHolder.isbuyed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToDetail(position, "1");
            }
        });
        //回复
        viewHolder.repeat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToDetail(position, "2");
            }
        });
        //重发
        viewHolder.send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ReleaseSupDemActivity.class);
                intent.putExtra("id", (list.get(position).getId()));
                intent.putExtra("type", list.get(position).getType());
                context.startActivity(intent);
            }
        });
        //分享
        viewHolder.share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s = (list.get(position).getType().equals("1")) ? ("求购信息：") : ("供给信息：");
                Intent in = new Intent(context, ShareActivity.class);
                in.putExtra("type", "4");
                in.putExtra("id", list.get(position).getId());
                in.putExtra("title", s + list.get(position).getContents());
                in.putExtra("t", list.get(position).getType());
                context.startActivity(in);
            }
        });
        return convertView;
    }

    //跳转至详情
    public void goToDetail(int position, String type) {
        Intent intent = new Intent(context, SupDem_Detail_Activity.class);
        String id_ = list.get(position).getId();
        String userid = list.get(position).getUser_id();
        String user_id = sharedUtils.getData(context, "userid");

        intent.putExtra("id", id_);
        intent.putExtra("type", type);
        intent.putExtra("userid", userid);
        intent.putExtra("what", (user_id.equals(userid)) ? ("5") : (itemBean.what));

        context.startActivity(intent);
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
        ImageView tx, rz, img;
        LinearLayout linearLayout;
        TextView gs, content, time, isbuyed, repeat, send, share, name;
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
                Intent intent = new Intent(context, PersonInfoActivity.class);
                intent.putExtra("userid", user_id);
                intent.putExtra("id", user_id);
                context.startActivity(intent);
            }
            //减积分成功
            if (type == 2 && err.equals("0")) {
                Intent intent = new Intent(context, PersonInfoActivity.class);
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
        } catch (JSONException e) {
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
        }
    }

    public Spanned replace(String s) {
        s = s.replace("<strong style='color: #ff5000;'>", "<font color='#ff5000'><b>");
        s = s.replace("</strong>", "</b></font>");
        Spanned s1 = Html.fromHtml(s);
        return s1;
    }
}
