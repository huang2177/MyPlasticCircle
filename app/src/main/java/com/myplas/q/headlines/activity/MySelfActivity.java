package com.myplas.q.headlines.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.myplas.q.R;
import com.myplas.q.app.activity.BaseActivity;
import com.myplas.q.app.activity.ShareActivity;
import com.myplas.q.common.netresquset.ResultCallBack;
import com.myplas.q.common.utils.SharedUtils;
import com.myplas.q.common.api.API;
import com.myplas.q.headlines.bean.MySelfBean;
import com.umeng.analytics.MobclickAgent;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * 编写： 黄双
 * 电话：15378412400
 * 邮箱：15378412400@163.com
 * 时间：2017/3/22 16:05
 */
public class MySelfActivity extends BaseActivity implements ResultCallBack, View.OnClickListener {
    ImageView  img_self;
    ImageButton share_btn, shuom_btn;
    TextView textView_title, textView_cname, textView_content, textView_title_, img_name, img_content;
    private MySelfBean mySelfBean;
    private SharedUtils sharedUtils;
    private Map<String, String> map = new HashMap<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_find_myself_activity);
        initTileBar();

        initView();
    }

    private void initView() {
        sharedUtils = SharedUtils.getSharedUtils();
        //share= (ImageView) findViewById(R.id.img_share);
        img_self = (ImageView) findViewById(R.id.img_myself);
        textView_cname = (TextView) findViewById(R.id.c_name);
        textView_content = (TextView) findViewById(R.id.myself_content);
        textView_title_ = (TextView) findViewById(R.id.myself_content_next);
        img_name = (TextView) findViewById(R.id.img_name);
        img_content = (TextView) findViewById(R.id.img_content);
        share_btn = (ImageButton) findViewById(R.id.btn_share);
        shuom_btn = (ImageButton) findViewById(R.id.btn_shm);
        share_btn.setOnClickListener(this);
        shuom_btn.setOnClickListener(this);
        if (getIntent().getStringExtra("data").equals("2")) {
           setTitle("企业信用信息");
            share_btn.setVisibility(View.GONE);
            map.put("link_id",getIntent().getStringExtra("id"));
            getSelectCate();
        } else if (getIntent().getStringExtra("data").equals("1")) {
            setTitle("我的信用信息");
            share_btn.setVisibility(View.VISIBLE);
            getSelectCate();
        }
    }

    public void getSelectCate() {
        map.put("token", sharedUtils.getData(this, "token"));
        postAsyn(this, API.BASEURL + API.CREDIT_CERTIFICATE, map, this, 1);
    }

    @Override
    public void callBack(Object object, int type) {
        try {
            String err = new JSONObject(object.toString()).getString("err");
            if (type == 1 && err.equals("0")) {
                Gson gson = new Gson();
                findViewById(R.id.tishi_text).setVisibility(View.GONE);
                findViewById(R.id.scrollView_myself).setVisibility(View.VISIBLE);
                mySelfBean = gson.fromJson(object.toString(), MySelfBean.class);
                showInfo(mySelfBean);
            } else {
                findViewById(R.id.tishi_text).setVisibility(View.VISIBLE);
                findViewById(R.id.scrollView_myself).setVisibility(View.GONE);
            }
        } catch (Exception e) {
        }
    }

    @Override
    public void failCallBack(int type) {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_share:
                Intent intent = new Intent(this, ShareActivity.class);
                intent.putExtra("type", "3");
                intent.putExtra("id", mySelfBean.getData().getUser_id());
                intent.putExtra("title", " \"热烈祝贺" + mySelfBean.getData().getC_name() + "获得企业信用等级证书" + mySelfBean.getData().getCredit_limit() + "万\"");
                startActivity(intent);
                break;
            case R.id.btn_shm:
                startActivity(new Intent(this, CreditActivity.class));
                break;
        }
    }
    public void showInfo(MySelfBean mySelfBean) {
        textView_cname.setText(mySelfBean.getData().getC_name());
        int a = mySelfBean.getData().getCredit_limit();
        String html1 = "获得信用" + "<font color='#FF4500'>" + mySelfBean.getData().getCredit_level() + "</font>" + "级客户称号/获得" + "<font color='#FF4500'>" + a/10000 + "</font>" + "万授信额度";
        textView_content.setText(Html.fromHtml(html1));
        textView_title_.setText("经“我的塑料网”塑料电商交易平台信用认证，贵公司企业信用良好，为" + mySelfBean.getData().getCredit_level() + "级，授信额度：" + a/10000 + "万人民币，特发此证！");
        img_name.setText(mySelfBean.getData().getC_name());
        img_content.setText("经我司评定，确认贵单位为二零一七年度信用" + mySelfBean.getData().getCredit_level() + "级客户，授信额度" + a/10000 + "万人民币，有效期一年。");
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
