package com.myplas.q.supdem.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.myplas.q.R;
import com.myplas.q.common.api.API;
import com.myplas.q.common.netresquset.ResultCallBack;
import com.myplas.q.common.utils.SharedUtils;
import com.myplas.q.common.utils.TextUtils;
import com.myplas.q.common.view.MyListview;
import com.myplas.q.common.view.RoundImageView;
import com.myplas.q.guide.activity.BaseActivity;
import com.myplas.q.headlines.activity.Head_Lines_DetailActivity;
import com.myplas.q.supdem.Beans.PhysicalBean;
import com.myplas.q.supdem.Beans.SearchResultDetailBean;
import com.myplas.q.supdem.adapter.Physical_Property_Adapter;
import com.myplas.q.supdem.adapter.SupDem_Search_QQ_Detail_Adapter;
import com.umeng.analytics.MobclickAgent;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import static android.R.id.list;
import static com.myplas.q.R.id.supdem_qq_listview_find;
import static com.myplas.q.supdem.Beans.ItemBean.itemBean;

/**
 * 编写： 黄双
 * 电话：15378412400
 * 邮箱：15378412400@163.com
 * 时间：2017/3/19 15:44
 */

public class SupDem_QQ_DetailActivity extends BaseActivity implements View.OnClickListener, ResultCallBack, AdapterView.OnItemClickListener {
    private RoundImageView roundImagView;
    private ImageView img_tell, img_zx, img_find;
    private MyListview listview_tell, listView_zx, listView_find;
    private LinearLayout layout_zx, layout_find, layout_wx, layout_tell, layout_zx_more;
    private TextView text_gs, text_qq_num, text_hw, text_ph, text_chd, text_jg, text_xq, text_qq, title_rs;

    private boolean isClicked1, isClicked2, isClicked3;
    private SupDem_Search_QQ_Detail_Adapter adapter;
    private SearchResultDetailBean.DataBean detailBean;
    private PhysicalBean bean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.supdem_detail_qq_activity);
        goBack(findViewById(R.id.back));
        initView();
        getSearch_Detail();
        getPhysical_Search();
    }

    public void initView() {
        isClicked1 = true;
        isClicked2 = false;
        isClicked3 = false;

        title_rs = F(R.id.title_rs);
        text_qq_num = F(R.id.qq_number);
        text_gs = F(R.id.supdem_qq_text_gs);
        text_hw = F(R.id.supdem_qq_text_wz);
        text_ph = F(R.id.supdem_qq_text_ph);
        text_chd = F(R.id.supdem_qq_text_chd);
        text_jg = F(R.id.supdem_qq_text_jg);
        text_xq = F(R.id.supdem_qq_text_xq);
        text_qq = F(R.id.supdem_qq_text_qq);

        img_zx = F(R.id.supdem_qq_img_zx);
        img_find = F(R.id.supdem_qq_img_find);
        img_tell = F(R.id.supdem_qq_img_tell);
        roundImagView = F(R.id.roundimagviewutil);

        listView_zx = F(R.id.supdem_qq_listview_zx);
        listView_find = F(supdem_qq_listview_find);
        listview_tell = F(R.id.supdem_qq_listview_tell);

        layout_zx = F(R.id.supdem_qq_layout_zx);
        layout_find = F(R.id.supdem_qq_layout_find);
        layout_wx = F(R.id.supdem_qq_layout_wx);
        layout_tell = F(R.id.supdem_qq_layout_tell);
        layout_zx_more = F(R.id.supdem_qq_layout_zx_more);

        layout_zx.setOnClickListener(this);
        layout_find.setOnClickListener(this);
        layout_wx.setOnClickListener(this);
        layout_tell.setOnClickListener(this);
        layout_zx_more.setOnClickListener(this);
        listView_zx.setOnItemClickListener(this);
        listview_tell.setOnItemClickListener(this);
    }

    //获取数据
    public void getSearch_Detail() {
        Map map = new HashMap();
        map.put("c_name", getIntent().getStringExtra("company"));
        map.put("model", getIntent().getStringExtra("plastic_number"));
        postAsyn(this, API.BASEURL + API.PLASTIC_SEARCH_DETAIL, map, this, 1);
    }

    //获取物性列表数据
    public void getPhysical_Search() {
        Map map = new HashMap();
        map.put("keywords", getIntent().getStringExtra("plastic_number"));
        postAsyn(this, API.BASEURL + API.PHYSICAL_SEARCH, map, this, 2);
    }
    @Override
    public void onClick(View v) {
        try {
            switch (v.getId()) {
                case R.id.supdem_qq_layout_zx:
                    if (detailBean.getShow_information().size() == 0) {
                        return;
                    }
                    if (!isClicked1) {
                        isClicked1 = true;
                        img_zx.setImageResource(R.drawable.icon_more_hl);
                        listView_zx.setVisibility(View.VISIBLE);
                        layout_zx_more.setVisibility(View.VISIBLE);
                        adapter = new SupDem_Search_QQ_Detail_Adapter(this, 1);
                        adapter.setList_showinfo(detailBean.getShow_information());
                        listView_zx.setAdapter(adapter);
                    } else {
                        isClicked1 = false;
                        listView_zx.setVisibility(View.GONE);
                        layout_zx_more.setVisibility(View.GONE);
                        img_zx.setImageResource(R.drawable.icon_more);
                    }
                    break;
                case R.id.supdem_qq_layout_find:
                    if (detailBean.getFind_relevant().size() == 0) {
                        TextUtils.Toast(this, "没有相关数据！");
                        return;
                    }
                    if (!isClicked2) {
                        isClicked2 = true;
                        img_find.setImageResource(R.drawable.icon_more_hl);
                        listView_find.setVisibility(View.VISIBLE);
                        adapter = new SupDem_Search_QQ_Detail_Adapter(this, 2);
                        adapter.setList_friend(detailBean.getFind_relevant());
                        listView_find.setAdapter(adapter);
                    } else {
                        isClicked2 = false;
                        listView_find.setVisibility(View.GONE);
                        img_find.setImageResource(R.drawable.icon_more);
                    }
                    break;
                case R.id.supdem_qq_layout_wx:
                    if (bean.getData().size() == 0) {
                        TextUtils.Toast(this, "没有相关数据！");
                        return;
                    }
                    if (bean != null && bean.getData().size() == 1) {
                        Intent intent = new Intent(this, Physical_Detail_Activity.class);
                        intent.putExtra("lid", bean.getData().get(0).getLid());
                        startActivity(intent);
                    }
                    if (bean != null && bean.getData().size() > 1) {
                        Intent intent = new Intent(this, Physical_Property_Activity.class);
                        intent.putExtra("plastic_number", getIntent().getStringExtra("plastic_number"));
                        intent.putExtra("PhysicalBean", bean);
                        startActivity(intent);
                    }
                    break;
                case R.id.supdem_qq_layout_tell:
                    if (detailBean.getMobile_list().size() == 0) {
                        TextUtils.Toast(this, "没有相关数据！");
                        return;
                    }
                    if (!isClicked3) {
                        isClicked3 = true;
                        img_tell.setImageResource(R.drawable.icon_more_hl);
                        listview_tell.setVisibility(View.VISIBLE);
                        listview_tell.setSelection(layout_tell.getChildCount());
                        adapter = new SupDem_Search_QQ_Detail_Adapter(this, 3);
                        adapter.setList_phone(detailBean.getMobile_list());
                        listview_tell.setAdapter(adapter);
                    } else {
                        isClicked3 = false;
                        listview_tell.setVisibility(View.GONE);
                        img_tell.setImageResource(R.drawable.icon_more);
                    }
                    break;
                case R.id.supdem_qq_layout_zx_more:
                    SharedUtils.getSharedUtils().setBooloean(this, "fromsearch", true);
                    SharedUtils.getSharedUtils().setData(this, "refreshdata", getIntent().getStringExtra("plastic_number"));
                    finish();
                    break;
            }
        } catch (Exception e) {
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        try {
            switch (parent.getId()) {
                case R.id.supdem_qq_listview_zx:
                    Intent intent = new Intent(this, Head_Lines_DetailActivity.class);
                    intent.putExtra("title", detailBean.getShow_information().get(position).getCate_name());
                    intent.putExtra("id", detailBean.getShow_information().get(position).getId());
                    startActivity(intent);
                    break;
                case R.id.supdem_qq_listview_find:
                    break;
                case R.id.supdem_qq_listview_tell:
                    call(detailBean.getMobile_list().get(position).getMobile());
                    break;
            }
        } catch (Exception e) {
        }
    }

    @Override
    public void callBack(Object object, int type) {
        try {
            Gson gson = new Gson();
            Log.e("---------" + type, object.toString());
            boolean err = new JSONObject(object.toString()).getString("err").equals("0");
            if (type == 1 && err) {
                SearchResultDetailBean bean = gson.fromJson(object.toString(), SearchResultDetailBean.class);
                detailBean = bean.getData();
                showInfo(detailBean);

                adapter = new SupDem_Search_QQ_Detail_Adapter(this, 1);
                adapter.setList_showinfo(detailBean.getShow_information());
                listView_zx.setAdapter(adapter);
            }
            if (type == 2 && err) {
                bean = gson.fromJson(object.toString(), PhysicalBean.class);
            }
        } catch (Exception e) {
        }
    }

    public void showInfo(SearchResultDetailBean.DataBean detailBean) {
        title_rs.setText(detailBean.getC_name());
        text_gs.setText(detailBean.getC_name());
        text_qq_num.setText(detailBean.getQq());
        text_hw.setText(detailBean.getStore_house());
        text_ph.setText(detailBean.getModel());
        text_chd.setText(detailBean.getProduce_place());
        text_jg.setText(detailBean.getUnit_price());
        text_xq.setText(detailBean.getCargo_type());
        text_qq.setText(detailBean.getQq_name());
        Glide.with(this).load(detailBean.getThumbqq()).placeholder(R.drawable.contact_image_defaul_male).into(roundImagView);
    }

    public void onResume() {
        super.onResume();
        MobclickAgent.onResume(this);
    }

    public void onPause() {
        super.onPause();
        MobclickAgent.onPause(this);
    }

    @Override
    public void failCallBack(int type) {

    }
}
