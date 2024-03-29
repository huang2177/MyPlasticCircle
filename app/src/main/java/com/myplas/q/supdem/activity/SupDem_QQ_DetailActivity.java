package com.myplas.q.supdem.activity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.myplas.q.R;
import com.myplas.q.app.activity.PreImageViewActivity;
import com.myplas.q.common.api.API;
import com.myplas.q.common.net.ResultCallBack;
import com.myplas.q.common.utils.TextUtils;
import com.myplas.q.common.view.CommonDialog;
import com.myplas.q.common.view.MyListview;
import com.myplas.q.common.view.RoundCornerImageView;
import com.myplas.q.app.activity.BaseActivity;
import com.myplas.q.app.activity.ShareActivity;
import com.myplas.q.headlines.activity.HeadLineSearchActivity;
import com.myplas.q.headlines.activity.HeadLinesDetailActivity;
import com.myplas.q.myself.integral.activity.IntegralActivity;
import com.myplas.q.supdem.beans.PhysicalBean;
import com.myplas.q.supdem.beans.SearchResultDetailBean;
import com.myplas.q.supdem.adapter.SupDem_Search_QQ_Detail_Adapter;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import static com.myplas.q.R.id.supdem_qq_listview_find;

/**
 * 编写： 黄双
 * 电话：15378412400
 * 邮箱：15378412400@163.com
 * 时间：2017/3/19 15:44
 */

public class SupDem_QQ_DetailActivity extends BaseActivity implements View.OnClickListener
        , ResultCallBack
        , AdapterView.OnItemClickListener
        , CommonDialog.DialogShowInterface {

    private ImageView imgTell, imgZx, imgFind;
    private RoundCornerImageView roundImagView;
    private MyListview listviewTell, listviewZx, listviewFind;
    private LinearLayout layoutZx, layoutFind, layoutWx, layoutTell, layoutZxMore;
    private TextView textGs, textQqNum, textHw, textPh, textChd, textJg, textXq, textQq;

    private PhysicalBean bean;
    private SupDem_Search_QQ_Detail_Adapter adapter;
    private SearchResultDetailBean.DataBean detailBean;

    private String clickId;
    private boolean isClicked1, isClicked2, isClicked3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.supdem_detail_qq_activity);

        initTileBar();
        initView();
        getSearch_Detail();
        getPhysical_Search();
    }

    public void initView() {
        isClicked1 = true;
        isClicked2 = false;
        isClicked3 = false;

        textQqNum = F(R.id.qq_number);
        textGs = F(R.id.supdem_qq_text_gs);
        textHw = F(R.id.supdem_qq_text_wz);
        textPh = F(R.id.supdem_qq_text_ph);
        textChd = F(R.id.supdem_qq_text_chd);
        textJg = F(R.id.supdem_qq_text_jg);
        textXq = F(R.id.supdem_qq_text_xq);
        textQq = F(R.id.supdem_qq_text_qq);

        imgZx = F(R.id.supdem_qq_img_zx);
        imgFind = F(R.id.supdem_qq_img_find);
        imgTell = F(R.id.supdem_qq_img_tell);
        roundImagView = F(R.id.roundimagviewutil);

        listviewZx = F(R.id.supdem_qq_listview_zx);
        listviewFind = F(supdem_qq_listview_find);
        listviewTell = F(R.id.supdem_qq_listview_tell);

        layoutZx = F(R.id.supdem_qq_layout_zx);
        layoutFind = F(R.id.supdem_qq_layout_find);
        layoutWx = F(R.id.supdem_qq_layout_wx);
        layoutTell = F(R.id.supdem_qq_layout_tell);
        layoutZxMore = F(R.id.supdem_qq_layout_zx_more);

        roundImagView.setShapeType(1);

        layoutZx.setOnClickListener(this);
        layoutWx.setOnClickListener(this);
        mIVRight.setOnClickListener(this);
        layoutFind.setOnClickListener(this);
        layoutTell.setOnClickListener(this);
        layoutZxMore.setOnClickListener(this);
        roundImagView.setOnClickListener(this);
        listviewZx.setOnItemClickListener(this);
        listviewTell.setOnItemClickListener(this);
    }

    /**
     * 获取数据
     */
    public void getSearch_Detail() {
        String id = getIntent().getStringExtra("id");
        String url = API.RELEASEMSGDETAIL + "/" + id + "?from=2";
        getAsyn(this, url, null, this, 1);
    }

    /**
     * 获取物性列表数据
     */
    public void getPhysical_Search() {
        Map map = new HashMap(8);
        map.put("model", getIntent().getStringExtra("plastic_number"));
        getAsyn(this, API.PHYSICAL_SEARCH, map, this, 2);
    }

    /**
     * 检查文章权限
     */
    public void isPaidSubscription(String cate_id) {
        Map<String, String> map = new HashMap<String, String>(8);
        map.put("id", cate_id);
        getAsyn(this, API.IS_PAID_SUBSCRIPTION, map, this, 3, false);
    }

    @Override
    public void onClick(View v) {
        try {
            switch (v.getId()) {
                case R.id.supdem_qq_layout_zx:
                    if (!isClicked1) {
                        isClicked1 = true;
                        imgZx.setImageResource(R.drawable.icon_more_hl);
                        listviewZx.setVisibility(View.VISIBLE);
                        layoutZxMore.setVisibility(View.VISIBLE);
                        adapter = new SupDem_Search_QQ_Detail_Adapter(this, 1);
                        adapter.setList_showinfo(detailBean.getShow_information());
                        listviewZx.setAdapter(adapter);
                    } else {
                        isClicked1 = false;
                        listviewZx.setVisibility(View.GONE);
                        layoutZxMore.setVisibility(View.GONE);
                        imgZx.setImageResource(R.drawable.icon_more);
                    }
                    break;
//                case R.id.supdem_qq_layout_find:
//                    if (detailBean.getFind_relevant().size() == 0) {
//                        TextUtils.toast(this, "没有相关数据！");
//                        return;
//                    }
//                    if (!isClicked2) {
//                        isClicked2 = true;
//                        imgFind.setImageResource(R.drawable.icon_more_hl);
//                        listviewFind.setVisibility(View.VISIBLE);
//                        adapter = new SupDem_Search_QQ_Detail_Adapter(this, 2);
//                        adapter.setList_friend(detailBean.getFind_relevant());
//                        listviewFind.setAdapter(adapter);
//                    } else {
//                        isClicked2 = false;
//                        listviewFind.setVisibility(View.GONE);
//                        imgFind.setImageResource(R.drawable.icon_more);
//                    }
//                    break;
                case R.id.supdem_qq_layout_wx:
                    if (bean.getData().size() == 0) {
                        TextUtils.toast(this, "没有相关数据！");
                        return;
                    }
                    if (bean != null && bean.getData().size() == 1) {
                        Intent intent = new Intent(this, Physical_Detail_Activity.class);
                        intent.putExtra("id", bean.getData().get(0).getLid());
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
                    if (!TextUtils.notEmpty(detailBean.getMobile())) {
                        TextUtils.toast(this, "没有相关数据！");
                        return;
                    }
                    if (!isClicked3) {
                        isClicked3 = true;
                        imgTell.setImageResource(R.drawable.icon_more_hl);
                        listviewTell.setVisibility(View.VISIBLE);
                        listviewTell.setSelection(layoutTell.getChildCount());
                        adapter = new SupDem_Search_QQ_Detail_Adapter(this, 3);
                        adapter.setList_phone(detailBean.getMobile());
                        listviewTell.setAdapter(adapter);
                    } else {
                        isClicked3 = false;
                        listviewTell.setVisibility(View.GONE);
                        imgTell.setImageResource(R.drawable.icon_more);
                    }
                    break;
                case R.id.supdem_qq_layout_zx_more:
                    Intent i = new Intent(this, HeadLineSearchActivity.class);
                    i.putExtra("data", getIntent().getStringExtra("plastic_number"));
                    startActivity(i);
                    break;
                case R.id.titlebar_img_right:
                    share();
                    break;
                case R.id.roundimagviewutil:
                    Intent intent1 = new Intent(this, PreImageViewActivity.class);
                    intent1.putExtra("imgurl", detailBean.getThumb_qq());
                    intent1.putExtra("type", "3");
                    startActivityByTras(intent1);
                    break;
                default:
                    break;
            }
        } catch (Exception e) {
        }
    }

    private void share() {
        if (detailBean == null) {
            return;
        }
        String s = ("1".equals(detailBean.getType())
                ? "求:"
                : "供：")
                + detailBean.getVendor()
                + detailBean.getModel()
                + "价格" + detailBean.getPrice()
                + detailBean.getStorehouse()
                + ("1".equals(detailBean.getTransaction_type()) ? "期货" : "现货");
        Intent in = new Intent(this, ShareActivity.class);
        in.putExtra("title", s);
        in.putExtra("type", "5");
        in.putExtra("id", detailBean.getId());
        in.putExtra("supdemType", detailBean.getType());
        startActivity(in);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        try {
            switch (parent.getId()) {
                case R.id.supdem_qq_listview_zx:
                    clickId = detailBean.getShow_information().get(position).getId();
                    isPaidSubscription(clickId);
                    break;
                case R.id.supdem_qq_listview_find:
                    break;
                case R.id.supdem_qq_listview_tell:
                    call(detailBean.getMobile());
                    break;
                default:
                    break;
            }
        } catch (Exception e) {
        }
    }

    @Override
    public void callBack(Object object, int type) {
        try {
            Gson gson = new Gson();
            String err = new JSONObject(object.toString()).getString("code");
            if (type == 1 && "0".equals(err)) {
                SearchResultDetailBean bean = gson.fromJson(object.toString(), SearchResultDetailBean.class);
                detailBean = bean.getData();
                showInfo(bean);

                adapter = new SupDem_Search_QQ_Detail_Adapter(this, 1);
                adapter.setList_showinfo(detailBean.getShow_information());
                listviewZx.setAdapter(adapter);
            }
            if (type == 2 && "0".equals(err)) {
                bean = gson.fromJson(object.toString(), PhysicalBean.class);
            }
            if (type == 3) {
                if ("0".equals(err)) {
                    Intent intent = new Intent(this, HeadLinesDetailActivity.class);
                    intent.putExtra("id", clickId);
                    startActivity(intent);
                } else {
                    String content = new JSONObject(object.toString()).getString("msg");
                    CommonDialog commonDialog = new CommonDialog();
                    commonDialog.showDialog(this, content, (err.equals("2")) ? (1) : (3), this);
                }
            }
        } catch (Exception e) {
        }
    }

    @Override
    public void dialogClick(int type) {
        if (type != -1) {
            Intent intent = new Intent(this, IntegralActivity.class);
            startActivity(intent);
        }
    }

    public void showInfo(SearchResultDetailBean bean) {
        SearchResultDetailBean.DataBean dataBean = bean.getData();
        setTitle(detailBean.getC_name());
        textPh.setText(detailBean.getModel());
        textGs.setText(detailBean.getC_name());
        textQqNum.setText(detailBean.getQq());
        textQq.setText(detailBean.getQq_nick());
        textChd.setText(detailBean.getVendor());
        textJg.setText(detailBean.getPrice());
        textHw.setText(detailBean.getStorehouse());
        textXq.setText("1".equals(detailBean.getTransaction_type()) ? "期货" : "现货");

        Glide.with(this)
                .load(detailBean.getThumb_qq())
                .placeholder(R.drawable.default_qq)
                .into(roundImagView);

        if (bean.getQapp_status().contains("operator")) {
            setRightIVResId(R.drawable.btn_share);
        }
    }

    @Override
    public void failCallBack(int type, String message, int httpCode) {

    }

    /**
     * 使用共享元素--activity跳转
     *
     * @param intent
     */
    private void startActivityByTras(Intent intent) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(this
                    , roundImagView
                    , "bigImageView"
            ).toBundle());
        } else {
            startActivity(intent);
        }
    }
}
