package com.myplas.q.myinfo.adapter;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.text.Html;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.myplas.q.R;
import com.myplas.q.common.utils.DialogShowUtils;
import com.myplas.q.guide.activity.BaseActivity;
import com.myplas.q.common.api.API;
import com.myplas.q.myinfo.integral.IntegralPayActivtity;
import com.myplas.q.myinfo.integral.mydatepicker.DateDialogShowUtils;
import com.myplas.q.myinfo.integral.mydatepicker.SupDemDialogShowUtils;
import com.myplas.q.common.netresquset.ResultCallBack;
import com.myplas.q.common.utils.SharedUtils;
import com.myplas.q.common.utils.TextUtils;
import com.myplas.q.myinfo.beans.IntegralBean;
import com.myplas.q.myinfo.beans.TookDateBean;

import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 编写： 黄双
 * 电话：15378412400
 * 邮箱：15378412400@163.com
 * 时间：2017/3/23 16:29
 */
public class IntegralAdapter extends BaseAdapter implements ResultCallBack, Integral_SupDem_Adapter.MyInterface
        , DialogShowUtils.DialogShowInterface {
    private Activity context;
    private viewHolder viewHolder;
    private Map<Integer, View> map;
    private TookDateBean tookDateBean;
    private List<Date> list_date_conact;
    private List<Date> list_date_supdem;
    private Map<Integer, GridView> map_grid;
    private Map<Integer,Boolean> map_boolean;
    private List<IntegralBean.InfoBean> list;
    private Map<Integer, LinearLayout> map_date;
    private Map<Integer, LinearLayout> map_date1;
    private Map<Integer, TextView> map_textview1;
    private Map<Integer, TextView> map_textview2;
    private Map<Integer, LinearLayout> map_supdem;
    private List<IntegralBean.InfoBean.MyMsgBean> list_msg;

    private int num = -1, mPosition;
    private Date_Grid_Adapter gridAdapter;
    private DialogShowUtils dialogShowUtils;
    private Integral_SupDem_Adapter integral_supplyDemandAdapter;

    public IntegralAdapter(Context context, Activity activity, List<IntegralBean.InfoBean> list, callBackiterface callBackiterface) {
        this.list = list;
        this.context = activity;

        map = new HashMap<>();
        map_grid = new HashMap<>();
        map_date = new HashMap<>();
        map_date1 = new HashMap<>();
        map_supdem = new HashMap<>();
        map_boolean=new HashMap<>();
        map_textview1 = new HashMap<>();
        map_textview2 = new HashMap<>();
        list_date_conact = new ArrayList<>();
        list_date_supdem = new ArrayList<>();
    }

    @Override
    public int getCount() {
//        if (list != null)
//            return list.size();
        return 2;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        if (map.get(position) == null) {
            viewHolder = new viewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.integrintegral_gridview_item, parent, false);
            viewHolder.shm = (TextView) convertView.findViewById(R.id.shm);
            viewHolder.button = (Button) convertView.findViewById(R.id.commit);
            viewHolder.time = (TextView) convertView.findViewById(R.id.radio_1);
            viewHolder.gridView = (GridView) convertView.findViewById(R.id.grid);
            viewHolder.textView = (TextView) convertView.findViewById(R.id.radio_);
            viewHolder.num_all = (TextView) convertView.findViewById(R.id.num_all);
            viewHolder.point_all = (TextView) convertView.findViewById(R.id.point_all);
            viewHolder.imageView = (ImageView) convertView.findViewById(R.id.jf_gridview_img);
            viewHolder.linear_time = (LinearLayout) convertView.findViewById(R.id.linear_time_dialog);
            viewHolder.supply_demand_shm = (LinearLayout) convertView.findViewById(R.id.supply_demand_shm);
            viewHolder.linear_supdem = (LinearLayout) convertView.findViewById(R.id.supply_demand_listview);
            viewHolder.linear_date_isselected1 = (LinearLayout) convertView.findViewById(R.id.integral_date_linearlayout);
            viewHolder.linear_supdem_isselected = (LinearLayout) convertView.findViewById(R.id.integral_isselected_linearlayout);
            viewHolder.linear_date_isselected = (LinearLayout) convertView.findViewById(R.id.integral_isselected_linearlayout_date);
            map.put(position, convertView);
            map_grid.put(position, viewHolder.gridView);
            map_textview1.put(position, viewHolder.num_all);
            map_textview2.put(position, viewHolder.point_all);
            map_boolean.put(position,true);
            map_date.put(position, viewHolder.linear_date_isselected);
            map_date1.put(position, viewHolder.linear_date_isselected1);
            map_supdem.put(position, viewHolder.linear_supdem_isselected);
            convertView.setTag(viewHolder);
        } else {
            convertView = map.get(position);
            viewHolder = (viewHolder) convertView.getTag();
        }
        Glide.with(context).load(list.get(position).getThumb()).into(viewHolder.imageView);
        if (list.get(position).getType().equals("1")) {
            viewHolder.shm.setVisibility(View.VISIBLE);
            viewHolder.linear_supdem.setVisibility(View.VISIBLE);
        } else {
            viewHolder.shm.setVisibility(View.GONE);
            viewHolder.linear_supdem.setVisibility(View.GONE);
        }
        //日期dialog
        viewHolder.linear_time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPosition = position;
                if (map_boolean.get(position)) {
                    map_boolean.put(position,false);
                    getValidDate(list.get(position).getType());
                }
            }
        });
        //供求dialog
        viewHolder.linear_supdem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                list_msg = list.get(position).getMyMsg();
                if (list_msg.size()!=0) {
                    integral_supplyDemandAdapter = new Integral_SupDem_Adapter(context, list_msg, IntegralAdapter.this);
                    SupDemDialogShowUtils supDemDialogShowUtils = new SupDemDialogShowUtils();
                    supDemDialogShowUtils.showDialog(context, list_msg, integral_supplyDemandAdapter);
                } else {
                    TextUtils.Toast(context,"你还没有发布相关的供求信息！");
                }
            }
        });
        //兑换
        viewHolder.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String dates = getDates((position == 0) ? (list_date_conact) : (list_date_supdem));
                if (dates != null) {
                    if (position == 0) {//通讯录
                        String id = list.get(0).getId();

                        exchangeSupplyOrDemand(id, dates, "",0);
                    } else {//供求
                        if (num!=-1) {
                            String id = list.get(1).getId();
                            String p_id = list_msg.get(num).getId();
                            exchangeSupplyOrDemand(id, dates, p_id,1);
                        } else {
                            TextUtils.Toast(context, "你还没有选择置顶的供求信息！");
                        }
                    }
                } else {
                    TextUtils.Toast(context, "你还没有选择置顶的日期！");
                }
            }
        });
        return convertView;
    }
    //兑换
    public void exchangeSupplyOrDemand(String goods_id, String dates, String p_id,int type) {
        Map<String, String> map = new HashMap<String, String>();
        map.put("token", SharedUtils.getSharedUtils().getData(context, "token"));
        map.put("goods_id", goods_id);
        map.put("dates", dates);
        map.put("pur_id", p_id);
        BaseActivity.postAsyn(context, API.BASEURL + API.NEW_EXCHANGE_SUPPLYORDEMAND, map, this, type);
    }
    //获取可选日期
    public void getValidDate(String type) {
        Map<String, String> map = new HashMap<String, String>();
        map.put("type", type);
        BaseActivity.postAsyn(context, API.BASEURL + API.GET_VALID_DATE, map, this, 2);
    }
    //选择的供求
    @Override
    public void select(int position) {
        this.num = position;
        viewHolder.supply_demand_shm.setVisibility(View.GONE);
        map_supdem.get(1).setVisibility(View.VISIBLE);
        String html = (list_msg.get(position).getType().equals("1")) ?
                ("<font color='#EEAD0E'>" + "求购:" + "</font>" + list_msg.get(position).getContents()) :
                ("<font color='#9AC0CD'>" + "供给:" + "</font>" + list_msg.get(position).getContents());
        viewHolder.textView.setText(Html.fromHtml(html));
        viewHolder.time.setText(list_msg.get(position).getInput_time());
    }
    @Override
    public void callBack(Object object, int type) {
        try {
            if (type == 0) {//通讯录
                String err = new JSONObject(object.toString()).getString("err");
                if (err.equals("0")) {
                    list_date_conact.clear();
                    map_date.get(0).setVisibility(View.VISIBLE);
                    map_date1.get(0).setVisibility(View.GONE);
                    dialogShowUtils = new DialogShowUtils();
                    dialogShowUtils.showDialog(context, "兑换成功!", 2, this);

                    changeTextShow(list_date_conact);
                } else if (err.equals("15")) {
                    dialogShowUtils = new DialogShowUtils();
                    dialogShowUtils.showDialog(context, "塑豆不足!", 1, this);
                } else {
                    String content = new JSONObject(object.toString()).getString("msg");
                    dialogShowUtils = new DialogShowUtils();
                    dialogShowUtils.showDialog(context, content, 3, this);
                }
            }
            if (type == 1) {//供求
                String err = new JSONObject(object.toString()).getString("err");
                if (err.equals("0")) {
                    list_date_supdem.clear();
                    viewHolder.supply_demand_shm.setVisibility(View.VISIBLE);
                    map_supdem.get(1).setVisibility(View.GONE);
                    map_date.get(1).setVisibility(View.VISIBLE);
                    map_date1.get(1).setVisibility(View.GONE);
                    dialogShowUtils = new DialogShowUtils();
                    dialogShowUtils.showDialog(context, "兑换成功!", 2, this);

                    changeTextShow(list_date_supdem);
                } else if (err.equals("15")) {
                    dialogShowUtils = new DialogShowUtils();
                    dialogShowUtils.showDialog(context, "塑豆不足!", 1, this);
                } else {
                    String content = new JSONObject(object.toString()).getString("msg");
                    dialogShowUtils = new DialogShowUtils();
                    dialogShowUtils.showDialog(context, content, 3, this);
                }
            }
            //展示时间dialog
            if (type == 2) {
                String err = new JSONObject(object.toString()).getString("err");
                if (err.equals("0")) {
                    Gson gson = new Gson();
                    tookDateBean = gson.fromJson(object.toString(), TookDateBean.class);
                    List<Date> list1 = (list.get(mPosition).getType().equals("1")) ? (list_date_supdem) : (list_date_conact);
                    DateDialogShowUtils dialogShowUtils = new DateDialogShowUtils(list1, this);
                    dialogShowUtils.showDialog(context, tookDateBean.getTook_date(),parseDate(tookDateBean.getStart_date()),parseDate(tookDateBean.getEnd_date()));
                }else {
                    map_boolean.put(mPosition,true);
                }
            }
        } catch (Exception e) {
        }
    }

    @Override
    public void failCallBack(int type) {

    }

    //dialog接口回i掉；
    @Override
    public void ok(int type) {
        switch (type) {
            case 1:
                context.startActivity(new Intent(context, IntegralPayActivtity.class));
                break;
        }
    }
    //根据选择的日期改变显示
    public void changeTextShow(List<Date>list){
        int num=list.size();
        map_textview1.get(mPosition).setText("共"+num+"件：");
        map_textview2.get(mPosition).setText("总计："+(num*100)+"塑豆");
    }
    class viewHolder {
        Button button;
        GridView gridView;
        ImageView imageView;
        TextView shm, point_all, num_all, textView, time;
        LinearLayout linear_supdem, linear_time, linear_supdem_isselected,
                linear_date_isselected, linear_date_isselected1, supply_demand_shm;
    }
    //日期上传之前拼接
    public String getDates(List<Date> list) {
        if (list.size() == 0) {
            return null;
        }
        try {
            String s = "";
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            for (int i = 0; i < list.size(); i++) {
                s += sdf.format(list.get(i)) + ",";
            }
            return s.substring(0, s.length() - 1);
        } catch (Exception e) {
            return null;
        }
    }
    //日期标准化
    public Date parseDate(String dateString) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            return sdf.parse(dateString);
        } catch (Exception e) {
            return null;
        }
    }

    //已经选择了得日期
    public void showGrid_isSelected() {
        map_boolean.put(mPosition,true);
        switch (mPosition) {
            case 0://通讯录
                if (list_date_conact.size() != 0) {
                    map_date.get(0).setVisibility(View.GONE);
                    map_date1.get(0).setVisibility(View.VISIBLE);
                    setGridViewParams(map_grid.get(0), list_date_conact);
                    gridAdapter = new Date_Grid_Adapter(context, list_date_conact);
                    map_grid.get(0).setAdapter(gridAdapter);
                } else {
                    map_date.get(0).setVisibility(View.VISIBLE);
                    map_date1.get(0).setVisibility(View.GONE);
                }
                break;
            case 1://供求
                if (list_date_supdem.size() != 0) {
                    map_date.get(1).setVisibility(View.GONE);
                    map_date1.get(1).setVisibility(View.VISIBLE);
                    setGridViewParams(map_grid.get(1), list_date_supdem);
                    gridAdapter = new Date_Grid_Adapter(context, list_date_supdem);
                    map_grid.get(1).setAdapter(gridAdapter);
                } else {
                    map_date.get(1).setVisibility(View.VISIBLE);
                    map_date1.get(1).setVisibility(View.GONE);
                }
                break;
        }
    }


    public void setGridViewParams(GridView gridView, List<Date> list) {
        int length = 75;
        DisplayMetrics dm = new DisplayMetrics();
        context.getWindowManager().getDefaultDisplay().getMetrics(dm);
        float density = dm.density;
        int gridviewWidth = (int) (list.size() * (length + 1) * density);
        int itemWidth = (int) (length * density);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                gridviewWidth, LinearLayout.LayoutParams.FILL_PARENT);
        gridView.setLayoutParams(params); // 设置GirdView布局参数,横向布局的关键
        gridView.setColumnWidth(itemWidth); // 设置列表项宽
        gridView.setHorizontalSpacing(0); // 设置列表项水平间距
        gridView.setStretchMode(GridView.NO_STRETCH);
        gridView.setNumColumns(list.size()); // 设置列数量=列表集合数
    }
    public interface callBackiterface {
        void finishActivity();
    }
}
