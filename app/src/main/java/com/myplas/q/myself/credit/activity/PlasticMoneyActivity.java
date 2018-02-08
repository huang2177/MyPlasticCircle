package com.myplas.q.myself.credit.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.myplas.q.R;
import com.myplas.q.common.api.API;
import com.myplas.q.common.net.ResultCallBack;
import com.myplas.q.app.activity.BaseActivity;
import com.myplas.q.myself.credit.adapter.EDu_Listview_ADapter;
import com.myplas.q.myself.beans.EDuBean;
import com.sobot.chat.SobotApi;
import com.sobot.chat.api.model.Information;

import org.json.JSONObject;

/**
 * 编写： 黄双
 * 电话：15378412400
 * 邮箱：15378412400@163.com
 * 时间：2017/3/28 10:25
 */
public class PlasticMoneyActivity extends BaseActivity implements ResultCallBack, View.OnClickListener {
    private Information information;
    private String sobot_appkey = "c1ff771c06254db796cd7ce1433d2004";

    private ListView listView;
    private TextView textView_title;
    private EDu_Listview_ADapter aDapter;
    private ImageView img_contact_sobot;
    private LinearLayout img_contact_call;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_plasticmoney_activity);
        goBack(findViewById(R.id.img_back));

        listView = (ListView) findViewById(R.id.plastic_listview);
        textView_title = (TextView) findViewById(R.id.fx_ttxq_title);
        img_contact_sobot = (ImageView) findViewById(R.id.img_contact_sobot);
        img_contact_call = (LinearLayout) findViewById(R.id.img_contact_call);

        img_contact_call.setOnClickListener(this);
        img_contact_sobot.setOnClickListener(this);

        textView_title.setText("塑料配资");
        getData();

    }

    public void getData() {
        getAsyn(this, API.CREDIT_LIMIT_PAGE, null, this, 1);
    }


    @Override
    public void callBack(Object object, int type) {
        try {
            Gson gson = new Gson();
            String err = new JSONObject(object.toString()).getString("code");
            if ("0".equals(err)) {
                EDuBean eDuBean = gson.fromJson(object.toString(), EDuBean.class);
                aDapter = new EDu_Listview_ADapter(this, eDuBean.getData());
                listView.setAdapter(aDapter);
            }
        } catch (Exception e) {
        }
    }

    @Override
    public void failCallBack(int type, String message, int httpCode) {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.img_contact_sobot:
                information = new Information();
                information.setAppkey(sobot_appkey);
                SobotApi.startSobotChat(this, information);
                break;
            case R.id.img_contact_call:
                startActivity(new Intent(this, Ed_Call_Dialog_Activity.class));
                break;
            default:
                break;
        }
    }
}
