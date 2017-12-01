package com.myplas.q.myself.integral.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.myplas.q.R;
import com.myplas.q.common.api.API;
import com.myplas.q.common.netresquset.ResultCallBack;
import com.myplas.q.common.view.CommonDialog;
import com.myplas.q.common.utils.NumUtils;
import com.myplas.q.common.utils.SharedUtils;
import com.myplas.q.common.utils.TextUtils;
import com.myplas.q.app.activity.BaseActivity;
import com.myplas.q.myself.beans.IntegralBean;
import com.myplas.q.myself.beans.TookDateBean;
import com.myplas.q.myself.integral.activity.IntegralPayActivtity;
import com.myplas.q.myself.integral.dialogutils.ClassifyDialogShowUtils;
import com.myplas.q.myself.integral.dialogutils.DateDialogShowUtils;
import com.myplas.q.myself.integral.dialogutils.SupDemDialogShowUtils;

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
public class IntegralAdapter extends RecyclerView.Adapter implements ResultCallBack
        , Integral_Diaolog_SupDem_Adapter.MyInterface
        , CommonDialog.DialogShowInterface
        , ClassifyDialogShowUtils.MyInterface {

    private MyInterface myInterface;

    private Map<Integer, View> map;
    private Map<Integer, Integer> mIntegerMap;
    private Map<Integer, viewHolder> mHolderMap;
    private Map<Integer, Integer> mDefFPostionMap;
    private Map<Integer, Integer> mDefCPostionMap;
    private List<Date> list_date_conact;

    private List<Date> list_date_supdem;
    private ClassifyDialogShowUtils utils;
    private CommonDialog mCommonDialog;
    private List<IntegralBean.InfoBean> list;
    private List<IntegralBean.InfoBean.MyMsgBean> list_msg;

    private Integral_Date_Grid_Adapter gridAdapter;
    private Integral_Diaolog_SupDem_Adapter integralSupplydemandadapter;

    private Activity context;
    private boolean isFinish;
    private String type, cate_ids;
    private TookDateBean tookDateBean;
    private int num = -1, month_num, plasticNum;
    private int datePosition, supDemPosition, classifyPosition, payPosition;

    public IntegralAdapter(Context context, Activity activity, List<IntegralBean.InfoBean> list, MyInterface myInterface) {
        this.list = list;
        this.context = activity;

        map = new HashMap<>();
        mHolderMap = new HashMap<>();
        mIntegerMap = new HashMap<>();
        mDefCPostionMap = new HashMap<>();
        mDefFPostionMap = new HashMap<>();
        list_date_conact = new ArrayList<>();
        list_date_supdem = new ArrayList<>();

        this.myInterface = myInterface;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_integral_rv_layout, parent, false);
        viewHolder viewHolder = new viewHolder(view, viewType);
        mHolderMap.put(viewType, viewHolder);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        final viewHolder viewHolder = mHolderMap.get(position);
        type = list.get(position).getType();
        Glide.with(context).load(list.get(position).getThumb()).into(viewHolder.imageView);
        if (type.equals("1")) {
            supDemPosition = position;
            viewHolder.shm.setVisibility(View.VISIBLE);
            viewHolder.linearSupdem.setVisibility(View.VISIBLE);
        } else if (type.equals("2")) {
            viewHolder.type.setText("请选择置顶日期：");
        } else {
            viewHolder.type.setText("请选择分类：");
        }

        //日期dialog 与 选择分类dialog
        viewHolder.linearTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String type = list.get(position).getType();
                if (type.equals("1") || type.equals("2")) {
                    datePosition = position;
                    getValidDate(list.get(position).getType());
                } else {
                    payPosition = position;
                    classifyPosition = position;
                    utils = new ClassifyDialogShowUtils();
                    utils.showDialog(context
                            , list.get(position)
                            , IntegralAdapter.this
                            , classifyPosition
                            , mDefFPostionMap
                            , mDefCPostionMap
                            , mIntegerMap);
                }
            }
        });

        //供求dialog
        viewHolder.linearSupdem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                list_msg = list.get(position).getMyMsg();
                if (list_msg.size() != 0) {
                    integralSupplydemandadapter = new Integral_Diaolog_SupDem_Adapter(context, list_msg, IntegralAdapter.this);
                    SupDemDialogShowUtils supDemDialogShowUtils = new SupDemDialogShowUtils();
                    supDemDialogShowUtils.showDialog(context, list_msg, integralSupplydemandadapter);
                } else {
                    TextUtils.toast(context, "你还没有发布相关的供求信息！");
                }
            }
        });
        //兑换
        viewHolder.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String dates = null;
                payPosition = position;
                String type = list.get(position).getType();
                if (type.equals("1")) { //供求
                    dates = getDates(list_date_supdem);
                    if (dates != null) {
                        if (num != -1) {
                            viewHolder.button.setClickable(false);
                            //viewHolder.button.setBackgroundResource(R.drawable.btn_cacle);
                            String id = list.get(datePosition).getId();
                            String p_id = list_msg.get(num).getId();
                            exchangeSupOrDem(id, dates, p_id, 1);
                        } else {
                            TextUtils.toast(context, "你还没有选择置顶的供求信息！");
                        }
                    } else {
                        TextUtils.toast(context, "你还没有选择置顶的日期！");
                    }

                }
                if (type.equals("2")) { //通讯录
                    dates = getDates(list_date_conact);
                    if (dates != null) {
                        viewHolder.button.setClickable(false);
                        //viewHolder.button.setBackgroundResource(R.drawable.btn_cacle);
                        String id = list.get(datePosition).getId();
                        exchangeSupOrDem(id, dates, "", 0);
                    } else {
                        TextUtils.toast(context, "你还没有选择置顶的日期！");
                    }
                }
                if (type.equals("3")) {
                    int num = NumUtils.getNum(viewHolder.numAll.getText().toString());
                    if (num != 0) {
                        viewHolder.button.setClickable(false);
                        //viewHolder.button.setBackgroundResource(R.drawable.btn_cacle);
                        String goods_id = list.get(classifyPosition).getId();
                        newExchangeToutiao(goods_id, plasticNum, cate_ids, month_num, 3);
                    } else {
                        TextUtils.toast(context, "你还没有选择头条分类!");
                    }
                }
            }
        });

    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public int getItemCount() {
        if (list != null) {
            return list.size();
        }
        return 0;
    }


    //兑换--通讯录 供求
    public void exchangeSupOrDem(String goods_id, String dates, String p_id, int type) {
        Map<String, String> map = new HashMap<String, String>();
        map.put("token", SharedUtils.getSharedUtils().getData(context, "token"));
        map.put("goods_id", goods_id);
        map.put("dates", dates);
        map.put("pur_id", p_id);
        BaseActivity.postAsyn(context, API.BASEURL + API.NEW_EXCHANGE_SUPPLYORDEMAND, map, this, type);
    }

    //兑换--头条
    public void newExchangeToutiao(String goods_id, int plastic_num, String cate_ids, int month_num, int type) {
        Map<String, String> map = new HashMap<String, String>();
        map.put("month_num", month_num + "");
        map.put("cate_ids", cate_ids);
        map.put("plasticbean_num", plastic_num + "");
        map.put("goods_id", goods_id);
        BaseActivity.postAsyn(context, API.BASEURL + API.NEW_EXCHANGE_TOUTIAO, map, this, type);
    }

    //获取可选日期
    public void getValidDate(String type) {
        Map<String, String> map = new HashMap<String, String>();
        map.put("type", type);
        BaseActivity.postAsyn(context, API.BASEURL + API.GET_VALID_DATE, map, this, 2);
    }

    //根据选择的日期改变显示
    public void changeTextShow(List<Date> list) {
        int num = list.size();
        mHolderMap.get(datePosition).numAll.setText("共" + num + "件：");
        mHolderMap.get(datePosition).pointAll.setText("总计：" + (num * 100) + "塑豆");
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

    public void setClickIsFinish(boolean isFinish) {
        this.isFinish = isFinish;
    }

    class viewHolder extends RecyclerView.ViewHolder {

        boolean isPay;
        Button button;
        GridView gridView;
        ImageView imageView;
        TextView shm, pointAll, numAll, textView, time, type;
        LinearLayout linearSupdem, linearTime, linearSupdemIsselected,
                linearDateIsselected, linearDateIsselected1, supplyDemandShm;

        public viewHolder(View convertView, int position) {
            super(convertView);
            isPay = false;
            shm = (TextView) convertView.findViewById(R.id.shm);
            button = (Button) convertView.findViewById(R.id.commit);
            time = (TextView) convertView.findViewById(R.id.radio_1);
            gridView = (GridView) convertView.findViewById(R.id.grid);
            textView = (TextView) convertView.findViewById(R.id.radio_);
            numAll = (TextView) convertView.findViewById(R.id.num_all);
            pointAll = (TextView) convertView.findViewById(R.id.point_all);
            type = (TextView) convertView.findViewById(R.id.integral_text_type);
            imageView = (ImageView) convertView.findViewById(R.id.jf_gridview_img);
            linearTime = (LinearLayout) convertView.findViewById(R.id.linear_time_dialog);
            supplyDemandShm = (LinearLayout) convertView.findViewById(R.id.supply_demand_shm);
            linearSupdem = (LinearLayout) convertView.findViewById(R.id.supply_demand_listview);
            linearDateIsselected1 = (LinearLayout) convertView.findViewById(R.id.integral_date_linearlayout);
            linearSupdemIsselected = (LinearLayout) convertView.findViewById(R.id.integral_isselected_linearlayout);
            linearDateIsselected = (LinearLayout) convertView.findViewById(R.id.integral_isselected_linearlayout_date);
        }

    }

    @Override
    public void callBack(Object object, int type) {
        try {
            if (type == 0) {//通讯录
                String err = new JSONObject(object.toString()).getString("err");
                if (err.equals("0")) {
                    list_date_conact.clear();
                    myInterface.refreshData();
                    mHolderMap.get(datePosition).linearDateIsselected.setVisibility(View.VISIBLE);
                    mHolderMap.get(datePosition).linearDateIsselected1.setVisibility(View.GONE);
                    mCommonDialog = new CommonDialog();
                    mCommonDialog.setCanceledOnTouchOutside(false);
                    mCommonDialog.showDialog(context, "兑换成功!", 2, this);

                    changeTextShow(list_date_conact);
                } else if (err.equals("15")) {
                    mCommonDialog = new CommonDialog();
                    mCommonDialog.setCanceledOnTouchOutside(false);
                    mCommonDialog.showDialog(context, "塑豆不足!", 1, this);
                } else {
                    String content = new JSONObject(object.toString()).getString("msg");
                    mCommonDialog = new CommonDialog();
                    mCommonDialog.setCanceledOnTouchOutside(false);
                    mCommonDialog.showDialog(context, content, 3, this);
                }
            }
            if (type == 1) {//供求
                String err = new JSONObject(object.toString()).getString("err");
                if (err.equals("0")) {
                    list_date_supdem.clear();
                    myInterface.refreshData();
                    mHolderMap.get(supDemPosition).supplyDemandShm.setVisibility(View.VISIBLE);
                    mHolderMap.get(supDemPosition).linearSupdemIsselected.setVisibility(View.GONE);
                    mHolderMap.get(datePosition).linearDateIsselected.setVisibility(View.VISIBLE);
                    mHolderMap.get(datePosition).linearDateIsselected1.setVisibility(View.GONE);
                    mCommonDialog = new CommonDialog();
                    mCommonDialog.setCanceledOnTouchOutside(false);
                    mCommonDialog.showDialog(context, "兑换成功!", 2, this);

                    changeTextShow(list_date_supdem);
                } else if (err.equals("15")) {
                    mCommonDialog = new CommonDialog();
                    mCommonDialog.setCanceledOnTouchOutside(false);
                    mCommonDialog.showDialog(context, "塑豆不足!", 1, this);
                } else {
                    String content = new JSONObject(object.toString()).getString("msg");
                    mCommonDialog = new CommonDialog();
                    mCommonDialog.setCanceledOnTouchOutside(false);
                    mCommonDialog.showDialog(context, content, 3, this);
                }
            }
            if (type == 3) {//分类
                String err = new JSONObject(object.toString()).getString("err");
                if (err.equals("0")) {
                    myInterface.refreshData();
                    mHolderMap.get(classifyPosition).numAll.setText("共0件：");
                    mHolderMap.get(classifyPosition).type.setText("请选择分类：");
                    mHolderMap.get(classifyPosition).pointAll.setText("总计：0塑豆");

                    mCommonDialog = new CommonDialog();
                    mCommonDialog.setCanceledOnTouchOutside(false);
                    mCommonDialog.showDialog(context, "兑换成功!", 2, this);
                } else if (err.equals("15")) {
                    mCommonDialog = new CommonDialog();
                    mCommonDialog.setCanceledOnTouchOutside(false);
                    mCommonDialog.showDialog(context, "塑豆不足!", 1, this);
                } else {
                    String content = new JSONObject(object.toString()).getString("msg");
                    mCommonDialog = new CommonDialog();
                    mCommonDialog.setCanceledOnTouchOutside(false);
                    mCommonDialog.showDialog(context, content, 3, this);
                }
            }
            //展示时间dialog
            if (type == 2) {
                String err = new JSONObject(object.toString()).getString("err");
                if (err.equals("0")) {
                    Gson gson = new Gson();
                    tookDateBean = gson.fromJson(object.toString(), TookDateBean.class);
                    List<Date> list1 = (list.get(datePosition).getType().equals("1")) ? (list_date_supdem) : (list_date_conact);
                    DateDialogShowUtils dialogShowUtils = new DateDialogShowUtils(list1, this);
                    dialogShowUtils.showDialog(context
                            , tookDateBean.getTook_date()
                            , parseDate(tookDateBean.getStart_date())
                            , parseDate(tookDateBean.getEnd_date()));
                }
            }
        } catch (Exception e) {
        }
    }

    //提示dialog接口回掉；
    @Override
    public void ok(int type) {
        setIsPay();
        switch (type) {
            case 1:
                context.startActivity(new Intent(context, IntegralPayActivtity.class));
                break;
            case 2:
                if (utils != null) {
                    utils.dismiss();
                }
                if (isFinish) {
                    context.finish();
                }
                break;
            default:
                break;
        }
    }

    @Override
    public void failCallBack(int type) {
        if (type == 3) {
            setIsPay();
        }
    }

    //设置支付按钮可点击
    public void setIsPay() {
        if (utils != null) {
            utils.setIsPay(false);
        }
        mHolderMap.get(payPosition).button.setClickable(true);
    }


    //日期选择后显示在列表上
    public void dateSelected() {
        String type = list.get(datePosition).getType();
        switch (type) {
            case "2"://通讯录
                if (list_date_conact.size() != 0) {
                    mHolderMap.get(datePosition).linearDateIsselected.setVisibility(View.GONE);
                    mHolderMap.get(datePosition).linearDateIsselected1.setVisibility(View.VISIBLE);
                    setGridViewParams(mHolderMap.get(datePosition).gridView, list_date_conact);
                    gridAdapter = new Integral_Date_Grid_Adapter(context, list_date_conact);
                    mHolderMap.get(datePosition).gridView.setAdapter(gridAdapter);
                } else {
                    mHolderMap.get(datePosition).linearDateIsselected.setVisibility(View.VISIBLE);
                    mHolderMap.get(datePosition).linearDateIsselected1.setVisibility(View.GONE);
                }
                break;
            case "1"://供求
                if (list_date_supdem.size() != 0) {
                    mHolderMap.get(datePosition).linearDateIsselected.setVisibility(View.GONE);
                    mHolderMap.get(datePosition).linearDateIsselected1.setVisibility(View.VISIBLE);
                    setGridViewParams(mHolderMap.get(datePosition).gridView, list_date_supdem);
                    gridAdapter = new Integral_Date_Grid_Adapter(context, list_date_supdem);
                    mHolderMap.get(datePosition).gridView.setAdapter(gridAdapter);
                } else {
                    mHolderMap.get(datePosition).linearDateIsselected.setVisibility(View.VISIBLE);
                    mHolderMap.get(datePosition).linearDateIsselected1.setVisibility(View.GONE);
                }
                break;
            default:
                break;
        }
    }

    //供求选择后显示在列表上
    @Override
    public void supDemSelected(int position) {
        this.num = position;
        mHolderMap.get(supDemPosition).supplyDemandShm.setVisibility(View.GONE);
        mHolderMap.get(supDemPosition).linearSupdemIsselected.setVisibility(View.VISIBLE);
        String html = (list_msg.get(position).getType().equals("1")) ?
                ("<font color='#EEAD0E'>" + "求购:" + "</font>" + list_msg.get(position).getContents()) :
                ("<font color='#9AC0CD'>" + "供给:" + "</font>" + list_msg.get(position).getContents());
        mHolderMap.get(supDemPosition).textView.setText(Html.fromHtml(html));
        mHolderMap.get(supDemPosition).time.setText(list_msg.get(position).getInput_time());
    }

    //分类 选择后显示在列表上
    @Override
    public void classifySelected(int t, String fName, String cName, String fid, String cId, int num) {
        month_num = num;
        mIntegerMap.put(classifyPosition, num);
        cate_ids = (cId.equals("")) ? (fid) : (cId);
        String type = (cName.equals("")) ? (fName) : (fName + cName);
        plasticNum = Integer.parseInt(list.get(classifyPosition).getPoints()) * num;

        mHolderMap.get(classifyPosition).type.setText("已选择：" + type);
        mHolderMap.get(classifyPosition).numAll.setText("共" + num + "件：");
        mHolderMap.get(classifyPosition).pointAll.setText("总计：" + plasticNum + "塑豆");
        if (t == -1) {
            String goods_id = list.get(classifyPosition).getId();
            newExchangeToutiao(goods_id, plasticNum, cate_ids, month_num, 3);
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

    public interface MyInterface {
        void refreshData();
    }
}
