package com.myplas.q.headlines.activity;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ImageView;

import com.google.gson.Gson;
import com.myplas.q.R;
import com.myplas.q.common.api.API;
import com.myplas.q.common.netresquset.ResultCallBack;
import com.myplas.q.common.utils.TextUtils;
import com.myplas.q.common.view.CommonDialog;
import com.myplas.q.common.view.MyGridview;
import com.myplas.q.app.activity.BaseActivity;
import com.myplas.q.headlines.adapter.HeadLine_Column_Adapetr;
import com.myplas.q.headlines.adapter.HeadLine_Product_Adapetr;
import com.myplas.q.headlines.bean.CateListSelectBean;
import com.myplas.q.headlines.bean.MyCateBean;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 编写：黄双
 * 邮箱：15378412400@163.com
 * 时间： 2017/6/81123.
 */

public class Cate_Dialog_Activtiy extends BaseActivity implements ResultCallBack
        , View.OnClickListener
        , CommonDialog.DialogShowInterface {
    private MyCateBean myCateBean;
    private ImageView cateImgBack;
    private HeadLine_Column_Adapetr columnAdapetr;
    private HeadLine_Product_Adapetr productAdapetr;
    private MyGridview gridviewColumn, gridviewProduct;
    private List<String> listColumnSelected, listProductSelected;
    private List<CateListSelectBean> listCateselectColumn, listCateselectProduct;
    private List listProductClassify, listSubscriptionColumn, list1, list2;

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_find_cate_popou);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);

        initView();
        getproductClassify();
        getsubscriptionColumn();
        getSelectCate();
    }

    public void initView() {
        listColumnSelected = new ArrayList<>();
        listProductSelected = new ArrayList<>();
        cateImgBack = (ImageView) findViewById(R.id.cate_img_back);
        gridviewColumn = (MyGridview) findViewById(R.id.fx_gd_wd_girdview);
        gridviewProduct = (MyGridview) findViewById(R.id.fx_gd_qb_girdview);

        cateImgBack.setOnClickListener(this);
        //myCateBean= (MyCateBean) getIntent().getSerializableExtra("mycatebean");

        gridviewColumn.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                columnAdapetr.setPosition(position);
                if (!listCateselectColumn.get(position).isUnSelecteable()) {
                    if (listCateselectColumn.get(position).isSelected()) {
                        listColumnSelected.add(list1.get(position) + "");
                    } else {
                        listColumnSelected.remove(list1.get(position) + "");
                    }
                }
            }
        });
        gridviewProduct.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                productAdapetr.setPosition(position);
                if (listCateselectProduct.get(position).isSelected()) {
                    listProductSelected.add(list2.get(position) + "");
                } else {
                    listProductSelected.remove(list2.get(position) + "");
                }
            }
//            }
        });
    }

    /**
     * 获取我的频道
     */
    public void getSelectCate() {
        Map<String, String> map = new HashMap<>();
        map.put("type", "2");
        getAsyn(this, API.GET_SELECT_CATE, map, this, 2, false);
    }

    /**
     * 保存我的频道
     */
    public void setSelectCate() {
        Map<String, String> map = new HashMap<>();
        map.put("type", "1");
        map.put("cate_id", getData(listColumnSelected));
        map.put("prop_id", getData(listProductSelected));
        getAsyn(this, API.GET_SELECT_CATE, map, this, 3, false);
    }

    /**
     * 初始化订阅栏目 item数据
     */
    public void getsubscriptionColumn() {
        listCateselectColumn = new ArrayList<>();
        listSubscriptionColumn = Arrays.asList(
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
        for (int i = 0; i < listSubscriptionColumn.size(); i++) {
            CateListSelectBean itemBean = new CateListSelectBean();
            itemBean.setSelected(false);
            itemBean.setIssaveed(false);
            itemBean.setCate_id(list1.get(i).toString());
            itemBean.setImg(listSubscriptionColumn.get(i).toString());
            listCateselectColumn.add(itemBean);
        }
        columnAdapetr = new HeadLine_Column_Adapetr(this);
        columnAdapetr.setList(listCateselectColumn);
        gridviewColumn.setAdapter(columnAdapetr);
    }

    /**
     * 初始化制品分类 item数据
     */

    public void getproductClassify() {
        listCateselectProduct = new ArrayList<>();
        listProductClassify = Arrays.asList(
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
        for (int i = 0; i < listProductClassify.size(); i++) {
            CateListSelectBean itemBean = new CateListSelectBean();
            itemBean.setImg(listProductClassify.get(i).toString());
            itemBean.setSelected(false);
            itemBean.setIssaveed(false);
            listCateselectProduct.add(itemBean);
        }
        productAdapetr = new HeadLine_Product_Adapetr(this);
        productAdapetr.setList(listCateselectProduct);
        gridviewProduct.setAdapter(productAdapetr);
    }

    /**
     * cate_id上传之前拼接
     *
     * @param list
     * @return
     */
    public String getData(List list) {
        if (list.size() == 0) {
            return "";
        } else {
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
        if (listColumnSelected.size() >= 2 && listProductSelected.size() >= 6) {
            setSelectCate();
        } else {
            TextUtils.toast(this, "订阅栏目与制品分类至少各选6个!");
        }
    }

    @Override
    public void callBack(Object object, int type) {
        try {
            Gson gson = new Gson();
            String err = new JSONObject(object.toString()).getString("code");
            if (type == 2 && "0".equals(err)) {
                myCateBean = gson.fromJson(object.toString(), MyCateBean.class);
                for (int i = 0; i < list1.size(); i++) {
                    if (myCateBean.getData().getSubscribe().contains(list1.get(i))) {
                        //已经订阅的频道设置为已选择
                        listCateselectColumn.get(i).setIssaveed(true);
                        listCateselectColumn.get(i).setSelected(true);
                    }
                    //设置不可选
                    if (myCateBean.getData().getUnconcealed_subscribe().contains(list1.get(i))) {
                        listCateselectColumn.get(i).setUnSelecteable(true);
                    }
                }
                for (int i = 0; i < list2.size(); i++) {
                    if (myCateBean.getData().getProperty().contains(list2.get(i))) {
                        //已经订阅的频道设置为已选择
                        listCateselectProduct.get(i).setIssaveed(true);
                        listCateselectProduct.get(i).setSelected(true);
                    }
                }
                listColumnSelected.clear();
                listColumnSelected.addAll(myCateBean.getData().getSubscribe());
                columnAdapetr.setList(listCateselectColumn);
                columnAdapetr.notifyDataSetChanged();

                listProductSelected.clear();
                listProductSelected.addAll(myCateBean.getData().getProperty());
                productAdapetr.setList(listCateselectProduct);
                productAdapetr.notifyDataSetChanged();
            }
            if (type == 3 && "0".equals(err)) {
                finish();
            }
        } catch (Exception e) {
        }
    }

    @Override
    public void failCallBack(int type, String message, int httpCode) {

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            CommonDialog dialogShowUtils = new CommonDialog();
            dialogShowUtils.showDialog(this, "您所选栏目还未保存！确定离开？", 1, this);
        }
        return true;
    }

    @Override
    public void dialogClick(int type) {
        if (type != -1) {
            finish();
        }
    }
}
