package com.myplas.q.supdem.activity;

import android.content.Context;
import android.os.Bundle;
import android.text.Html;
import android.text.InputType;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.SpannedString;
import android.text.style.AbsoluteSizeSpan;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.myplas.q.R;
import com.myplas.q.guide.activity.BaseActivity;
import com.myplas.q.common.netresquset.ResultCallBack;
import com.myplas.q.common.utils.SharedUtils;
import com.myplas.q.common.utils.TextUtils;
import com.myplas.q.supdem.Beans.DeliverPriceBean;
import com.myplas.q.supdem.Beans.ReplyBean;
import com.myplas.q.supdem.Beans.SupplyDemandDetailBean;
import com.myplas.q.supdem.adapter.XQ_ListView_CHJAdapter;
import com.myplas.q.supdem.adapter.XQ_ListView_HFAdapter;
import com.myplas.q.common.api.API;
import com.umeng.analytics.MobclickAgent;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 编写： 黄双
 * 电话：15378412400
 * 邮箱：15378412400@163.com
 * 时间：2017/3/19 15:44
 */

public class SupDem_Detail_Activity extends BaseActivity implements View.OnClickListener, ResultCallBack {
    private String what;
    private int type = 5;
    private EditText editText;
    private SpannableString ss;
    private AbsoluteSizeSpan ass;
    private SharedUtils sharedUtils;
    private ListView xq_listview_chj;
    private String method = API.DELIVER_PRICE;
    private Button btn_gz, btn_chj, btn_hf, btn_chj_ok;
    private LinearLayout linearLayout, linearLayout_edit;
    private ImageView img_tx, img_rz, img_gong_qiu,imageView;
    private TextView text_gs, text_name,text_fs,text_content, text_shj, text_chj, text_hf;

    private XQ_ListView_HFAdapter hfAdapter;
    private XQ_ListView_CHJAdapter chjAdapter;
    private Map<String, String> map = new HashMap<>();
    private List<ReplyBean.DataBeanX.DataBean> list_hf;
    private SupplyDemandDetailBean supplyDemandDetailBean;
    private List<DeliverPriceBean.DataBeanX.DataBean> list_chj;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.supdem_detail_layout);
        goBack(findViewById(R.id.back_img));
        initView();
        getNetData();
        String s=getIntent().getStringExtra("type");
        if (s.equals("0")||s.equals("1")) {
            intDeLiverPrice();
        } else {
            intReply();
        }
    }
    public void initView() {

        img_tx = F(R.id.xq_tx);
        img_rz = F(R.id.xq_rz);
        btn_gz = F(R.id.xq_gz);
        text_fs=F(R.id.xq_fs);
        text_gs = F(R.id.xq_gs);
        btn_hf = F(R.id.xq_hfxx);
        text_name=F(R.id.xq_name);
        text_shj = F(R.id.xq_shj);
        text_hf = F(R.id.gq_huifu);
        btn_chj = F(R.id.xq_chjxx);
        imageView=F(R.id.image_d_r);
        text_chj = F(R.id.gq_chujian);
        editText = F(R.id.edit_chj_hf);
        btn_chj_ok = F(R.id.btn_chj_hf);
        linearLayout = F(R.id.linear_chj);
        text_content = F(R.id.xq_content);
        img_gong_qiu = F(R.id.img_gong_qiu);
        linearLayout_edit = F(R.id.detail_edit);
        xq_listview_chj = F(R.id.xq_listview_chj);

        btn_gz.setOnClickListener(this);
        btn_hf.setOnClickListener(this);
        btn_chj.setOnClickListener(this);
        editText.setOnClickListener(this);
        btn_chj_ok.setOnClickListener(this);

        ass = new AbsoluteSizeSpan(16, true);
        sharedUtils = SharedUtils.getSharedUtils();

        chjAdapter = new XQ_ListView_CHJAdapter(this);
        hfAdapter = new XQ_ListView_HFAdapter(this);

        //是否显示编辑框
        what = getIntent().getStringExtra("what");
        switch (what) {
            case "1":
            case "2":
            case "3":
                linearLayout_edit.setVisibility(View.VISIBLE);
                break;
            case "4":
            case "5":
                linearLayout_edit.setVisibility(View.GONE);
                break;
        }
    }
    //获取首页数据
    public void getNetData() {
        try {
            map.put("token", sharedUtils.getData(this, "token"));
            map.put("user_id", getIntent().getStringExtra("userid"));
            map.put("id", getIntent().getStringExtra("id"));
            postAsyn(this, API.BASEURL + API.GET_RELEASE_MSG_DETAIL, map, this, 1);
        } catch (Exception e) {
        }
    }

    public <T extends View> T F(int id) {
        return (T) findViewById(id);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.xq_gz:
                if (what.equals("5")) {
                    TextUtils.Toast(this, "自己不能关注自己！");
                } else {
                    map.put("token", sharedUtils.getData(this, "token"));
                    map.put("focused_id", getIntent().getStringExtra("userid"));
                    String url = API.BASEURL + API.FOCUS_OR_CANCEL;
                    postAsyn(this, url, map, this, 2);
                }
                break;
            case R.id.xq_chjxx://出价
                intDeLiverPrice();
                break;
            case R.id.xq_hfxx://回复消息
                intReply();
                break;
            case R.id.btn_chj_hf://提交
                String s = editText.getText().toString();
                if (TextUtils.isNullOrEmpty(s)) {
                    map.put("token", sharedUtils.getData(this, "token"));
                    map.put("type", supplyDemandDetailBean.getData().getType());
                    if (type == 6) {
                        map.put("pur_id", getIntent().getStringExtra("id"));
                        map.put("content", s);
                        map.put("send_id", getIntent().getStringExtra("userid"));
                    } else {
                        map.put("id", getIntent().getStringExtra("id"));
                        map.put("rev_id", getIntent().getStringExtra("userid"));
                        map.put("price", s);
                    }
                    String url4 = API.BASEURL + method;
                    postAsyn(this, url4, map, this, type);
                } else {
                    TextUtils.Toast(this, "内容不能为空！");
                }
                break;
            case R.id.edit_chj_hf:
                break;
        }
    }
    //出价消息
    public void intDeLiverPrice() {
        list_hf=null;
        hfAdapter.setList(list_hf);
        hfAdapter.notifyDataSetChanged();

        imageView.setImageResource(R.drawable.btn_switch_bid_message);
        linearLayout.setVisibility(View.VISIBLE);
        btn_chj_ok.setText("出价");
        ss = new SpannableString("期待您的出价...");
        ss.setSpan(ass, 0, ss.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        editText.setHint(new SpannedString(ss));
        editText.setInputType(InputType.TYPE_CLASS_NUMBER);
        method = API.DELIVER_PRICE;
        type = 5;
        getDeLiverPrice();
    }
    //回复
    public void intReply() {
        list_chj=null;
        chjAdapter.setList(list_chj);
        chjAdapter.notifyDataSetChanged();

        imageView.setImageResource(R.drawable.btn_switch_reply_message);
        linearLayout.setVisibility(View.GONE);
        btn_chj_ok.setText("回复");
        ss = new SpannableString("期待您的回复...");
        ss.setSpan(ass, 0, ss.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        editText.setHint(new SpannedString(ss));
        editText.setInputType(InputType.TYPE_CLASS_TEXT);
        method = API.SAVE_MSG;
        type = 6;
        getReply();
    }

    public void getDeLiverPrice() {
        map.put("token", sharedUtils.getData(this, "token"));
        map.put("id", getIntent().getStringExtra("id"));
        map.put("rev_id", getIntent().getStringExtra("userid"));
        map.put("page", "1");
        map.put("size", "10");
        String url1 = API.BASEURL + API.GET_DELIVER_PRICE;
        postAsyn(this, url1, map, this, 3);
    }

    public void getReply() {
        map.put("token", sharedUtils.getData(this, "token"));
        map.put("id", getIntent().getStringExtra("id"));
        map.put("user_id", getIntent().getStringExtra("userid"));
        map.put("page", "1");
        map.put("size", "10");
        String url3 = API.BASEURL + API.GET_RELEASE_MSG_DETAIL_REPLY;
        postAsyn(this, url3, map, this, 4);
    }

    @Override
    public void callBack(Object object, int type) {
        try {
            Gson gson = new Gson();
            if (type == 1) {
                if (new JSONObject(object.toString()).getString("err").equals("0")) {
                    supplyDemandDetailBean = gson.fromJson(object.toString(), SupplyDemandDetailBean.class);
                    showInfo(supplyDemandDetailBean);
                }
            }
            if (type == 2 && new JSONObject(object.toString()).getString("err").equals("0")) {
                TextUtils.Toast(this, new JSONObject(object.toString()).getString("msg"));
                if (new JSONObject(object.toString()).getString("msg").equals("关注成功")) {
                    btn_gz.setText("取消关注");
                } else {
                    btn_gz.setText("关注");
                }
            }
            //出价消息的结果
            if (type == 3 && new JSONObject(object.toString()).getString("err").equals("0")) {
                DeliverPriceBean deliverPriceBean = gson.fromJson(object.toString(), DeliverPriceBean.class);
                list_chj = deliverPriceBean.getData().getData();
                chjAdapter.setList(list_chj);
                chjAdapter.notifyDataSetChanged();
                xq_listview_chj.setAdapter(chjAdapter);

            }
            //得到回复消息的结果
            if (type == 4 && new JSONObject(object.toString()).getString("err").equals("0")) {
                ReplyBean replyBean = gson.fromJson(object.toString(), ReplyBean.class);
                list_hf = replyBean.getData().getData();
                hfAdapter.setList(list_hf);
                hfAdapter.notifyDataSetChanged();
                xq_listview_chj.setAdapter(hfAdapter);
            }
            //提交后的刷新
            if (type == 5 && new JSONObject(object.toString()).getString("err").equals("0")) {
                TextUtils.Toast(this, new JSONObject(object.toString()).getString("msg"));
                getDeLiverPrice();
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(editText.getWindowToken(), 0);
                editText.setText("");
            }
            //提交后的刷新
            if (type == 6 && new JSONObject(object.toString()).getString("err").equals("0")) {
                TextUtils.Toast(this, new JSONObject(object.toString()).getString("msg"));
                getReply();
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(editText.getWindowToken(), 0);
                editText.setText("");
            }
        } catch (Exception e) {
        }
    }

    @Override
    public void failCallBack(int type) {

    }

    public void showInfo(SupplyDemandDetailBean supplyDemandDetailBean) {
        btn_gz.setText(supplyDemandDetailBean.getData().getInfo().getStatus());
        Glide.with(this).load(supplyDemandDetailBean.getData().getInfo().getThumb()).placeholder(R.drawable.contact_image_defaul_male).into(img_tx);
        String s = supplyDemandDetailBean.getData().getInfo().getC_name();
        text_gs.setText("  " + s );
        text_name.setText("  " + supplyDemandDetailBean.getData().getInfo().getName());
        text_fs.setText("  粉丝：" + supplyDemandDetailBean.getData().getInfo().getFans() + "   等级：" + supplyDemandDetailBean.getData().getInfo().getMember_level());
        text_shj.setText(supplyDemandDetailBean.getData().getInput_time());
        text_chj.setText("出价(" + supplyDemandDetailBean.getData().getDeliverPriceCount() + ")");
        text_hf.setText("回复(" + supplyDemandDetailBean.getData().getSaysCount() + ")");
        if (supplyDemandDetailBean.getData().getInfo().getStatus().equals("0")) {
            img_rz.setImageResource(R.drawable.icon_identity);
        } else if (supplyDemandDetailBean.getData().getInfo().getStatus().equals("0")) {
            img_rz.setImageResource(R.drawable.icon_identity_hl);
        }
        if (supplyDemandDetailBean.getData().getType().equals("1")) {
            String html1 = "<font color='#EEAD0E'>" + " 求购：" + "</font>" + supplyDemandDetailBean.getData().getContents();
            img_gong_qiu.setImageResource(R.drawable.icon_purchase);
            text_content.setText(Html.fromHtml(html1));
        } else {
            img_gong_qiu.setImageResource(R.drawable.icon_supply);
            String html1 = "<font color='#36648B'>" + " 供给：" + "</font>" + supplyDemandDetailBean.getData().getContents();
            text_content.setText(Html.fromHtml(html1));
        }
        if (supplyDemandDetailBean.getData().getContents().equals("")) {
            img_gong_qiu.setVisibility(View.GONE);
            text_content.setVisibility(View.GONE);
        } else {
            img_gong_qiu.setVisibility(View.VISIBLE);
            text_content.setVisibility(View.VISIBLE);
        }
    }

    public void onResume() {
        super.onResume();
        MobclickAgent.onResume(this);
    }

    public void onPause() {
        super.onPause();
        MobclickAgent.onPause(this);
    }
}
