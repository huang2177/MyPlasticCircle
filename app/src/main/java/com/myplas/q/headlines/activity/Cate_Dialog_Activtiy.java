package com.myplas.q.headlines.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ImageView;

import com.google.gson.Gson;
import com.myplas.q.R;
import com.myplas.q.common.api.API;
import com.myplas.q.common.netresquset.ResultCallBack;
import com.myplas.q.common.utils.DialogShowUtils;
import com.myplas.q.common.utils.SharedUtils;
import com.myplas.q.common.utils.TextUtils;
import com.myplas.q.common.view.MyGridview;
import com.myplas.q.guide.activity.BaseActivity;
import com.myplas.q.headlines.adapter.HeadLine_Column_Adapetr;
import com.myplas.q.headlines.adapter.HeadLine_Product_Adapetr;
import com.myplas.q.headlines.bean.CateListSelectBean;
import com.myplas.q.headlines.bean.MyCateBean;
import com.myplas.q.myinfo.activity.IntegralPayActivtity;

import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 编写：黄双
 * 邮箱：15378412400@163.com
 * 时间： 2017/6/81123.
 */

public class Cate_Dialog_Activtiy extends BaseActivity implements ResultCallBack, View.OnClickListener ,DialogShowUtils.DialogShowInterface{
    private MyCateBean myCateBean;
    private ImageView cate_img_back;
    private HeadLine_Column_Adapetr column_adapetr;
    private HeadLine_Product_Adapetr product_adapetr;
    private MyGridview gridView_column, gridView_product;
    private List<String> list_column_selected, list_product_selected;
    private List<CateListSelectBean> list_cateselect_Column, list_cateselect_Product;
    private List list_Product_Classify, list_Subscription_Column, list1, list2;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_find_cate_popou);
        getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);
        initView();
        getProduct_Classify();
        getSubscription_Column();
        getSelectCate();
    }

    public void initView() {
        list_column_selected = new ArrayList<>();
        list_product_selected = new ArrayList<>();
        cate_img_back = (ImageView) findViewById(R.id.cate_img_back);
        gridView_column = (MyGridview) findViewById(R.id.fx_gd_wd_girdview);
        gridView_product = (MyGridview) findViewById(R.id.fx_gd_qb_girdview);

        cate_img_back.setOnClickListener(this);
        //myCateBean= (MyCateBean) getIntent().getSerializableExtra("mycatebean");

        gridView_column.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                column_adapetr.setPosition(position);
                if (list_cateselect_Column.get(position).isSelected()) {
                    list_column_selected.add(list1.get(position) + "");
                } else {
                    list_column_selected.remove(list1.get(position) + "");
                }
            }
        });
        gridView_product.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                product_adapetr.setPosition(position);
                if (list_cateselect_Product.get(position).isSelected()) {
                    list_product_selected.add(list2.get(position) + "");
                } else {
                    list_product_selected.remove(list2.get(position) + "");
                }
            }
//            }
        });
    }

    //获取我的频道
    public void getSelectCate() {
        Map<String, String> map = new HashMap<>();
        map.put("type", "2");
        postAsyn(this, API.BASEURL + API.GET_SELECT_CATE, map, this, 2);
    }

    //保存我的频道
    public void setSelectCate() {
        Map<String, String> map = new HashMap<>();
        map.put("type", "1");
        map.put("cate_id", getData(list_column_selected));
        map.put("prop_id", getData(list_product_selected));
        postAsyn(this, API.BASEURL + API.GET_SELECT_CATE, map, this, 3);
    }

    //初始化订阅栏目 item数据
    public void getSubscription_Column() {
        list_cateselect_Column = new ArrayList<>();
        list_Subscription_Column = Arrays.asList(
                R.drawable.img_upstream_plastic,
                R.drawable.img_forecast,
                R.drawable.img_enterprise_dynamics,
                R.drawable.img_plastic_said,
                R.drawable.img_dollar_market,
                R.drawable.img_futures_information,
                R.drawable.img_device_dynamics,
                R.drawable.img_journal_report,
                R.drawable.img_exclusive_interpretation);
        list1 = Arrays.asList("2", "1", "9", "4", "20", "21", "11", "13", "22");
        for (int i = 0; i < list_Subscription_Column.size(); i++) {
            CateListSelectBean itemBean = new CateListSelectBean();
            itemBean.setSelected(false);
            itemBean.setIssaveed(false);
            itemBean.setCate_id(list1.get(i).toString());
            itemBean.setImg(list_Subscription_Column.get(i).toString());
            list_cateselect_Column.add(itemBean);
        }
        column_adapetr = new HeadLine_Column_Adapetr(this);
        column_adapetr.setList(list_cateselect_Column);
        gridView_column.setAdapter(column_adapetr);
    }

    //初始化制品分类 item数据
    public void getProduct_Classify() {
        list_cateselect_Product = new ArrayList<>();
        list_Product_Classify = Arrays.asList(
                R.drawable.img_high_pressure,
                R.drawable.img_coating,
                R.drawable.img_blown_film,
                R.drawable.img_wire_drawing,
                R.drawable.img_injection_molding,
                R.drawable.img_hollow,
                R.drawable.img_film,
                R.drawable.img_linear,
                R.drawable.img_pipe,
                R.drawable.img_homopolymerization_drawing,
                R.drawable.img_metallocene,
                R.drawable.img_copolymerization,
                R.drawable.img_others);
        list2 = Arrays.asList("1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13");
        for (int i = 0; i < list_Product_Classify.size(); i++) {
            CateListSelectBean itemBean = new CateListSelectBean();
            itemBean.setImg(list_Product_Classify.get(i).toString());
            itemBean.setSelected(false);
            itemBean.setIssaveed(false);
            list_cateselect_Product.add(itemBean);
        }
        product_adapetr = new HeadLine_Product_Adapetr(this);
        product_adapetr.setList(list_cateselect_Product);
        gridView_product.setAdapter(product_adapetr);
    }

    //cate_id上传之前拼接
    public String getData(List list) {
        if (list.size() == 0) {
            return "";
        }else {
            try {
                String s = "";
                for (int i = 0; i < list.size(); i++) {
                    s += list.get(i) + ",";
                }
                return s.substring(0, s.length() - 1);
            } catch (Exception e) {
                return "";
            }
        }
    }

    @Override
    public void onClick(View v) {
        if (list_column_selected.size()>=2&&list_product_selected.size()>=6) {
            setSelectCate();
            finish();
        } else {
            TextUtils.Toast(this,"订阅栏目与制品分类至少各选6个!");
        }
    }
    @Override
    public void callBack(Object object, int type) {
        try {
            Gson gson = new Gson();
            String err = new JSONObject(object.toString()).getString("err");
            if (type == 2 && err.equals("0")) {
                myCateBean = gson.fromJson(object.toString(), MyCateBean.class);
                for (int i = 0; i < list1.size(); i++) {
                    if (myCateBean.getData().getSubscribe().contains(list1.get(i))) {
                        //已经订阅的频道设置为已选择
                        list_cateselect_Column.get(i).setIssaveed(true);
                        list_cateselect_Column.get(i).setSelected(true);
                    }
                    //设置不可选
                    if (myCateBean.getData().getUnconcealed_subscribe().contains(list1.get(i))) {
                        list_cateselect_Column.get(i).setUnSelecteable(true);
                    }
                }
                for (int i = 0; i < list2.size(); i++) {
                    if (myCateBean.getData().getProperty().contains(list2.get(i))) {
                        //已经订阅的频道设置为已选择
                        list_cateselect_Product.get(i).setIssaveed(true);
                        list_cateselect_Product.get(i).setSelected(true);
                    }
                }

                list_column_selected.clear();
                list_column_selected.addAll(myCateBean.getData().getSubscribe());
                column_adapetr.setList(list_cateselect_Column);
                column_adapetr.notifyDataSetChanged();

                list_product_selected.clear();
                list_product_selected.addAll(myCateBean.getData().getProperty());
                product_adapetr.setList(list_cateselect_Product);
                product_adapetr.notifyDataSetChanged();
            }
        } catch (Exception e) {
        }
    }
    @Override
    public void failCallBack(int type) {

    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            DialogShowUtils dialogShowUtils = new DialogShowUtils();
            dialogShowUtils.showDialog(this, "您所选栏目还未保存！确定离开？", 1, this);
        }
        return true;
    }

    @Override
    public void ok(int type) {
        finish();
    }
}