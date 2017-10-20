package com.myplas.q.myself.credit.activity;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.text.Html;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.myplas.q.R;
import com.myplas.q.common.api.API;
import com.myplas.q.common.netresquset.ResultCallBack;
import com.myplas.q.common.utils.SharedUtils;
import com.myplas.q.common.utils.TextUtils;
import com.myplas.q.common.view.MyListview;
import com.myplas.q.guide.activity.BaseActivity;
import com.myplas.q.headlines.activity.MySelfActivity;
import com.myplas.q.headlines.adapter.CompanyAdapter;
import com.myplas.q.headlines.bean.CompanyBean;
import com.myplas.q.myself.credit.adapter.EDu_Listview_ADapter;
import com.myplas.q.myself.beans.EDuBean;
import com.sobot.chat.SobotApi;
import com.sobot.chat.api.model.Information;
import com.umeng.analytics.MobclickAgent;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * 编写： 黄双
 * 电话：15378412400
 * 邮箱：15378412400@163.com
 * 时间：2017/3/28 10:25
 */
public class LineOfCreditActivity extends BaseActivity implements View.OnClickListener, ResultCallBack {
    private Information information;
    private String sobot_appkey = "c1ff771c06254db796cd7ce1433d2004";

    private EditText edu_edit;
    private MyListview mMyListview;
    private TextView textView_title;
    private SharedUtils sharedUtils;
    private ImageView img_contact_sobot;
    private ListView edu_dialog_listview;
    private LinearLayout img_contact_call;
    private TextView textView_search, textView_company;

    private CompanyBean companyBean;
    private EDu_Listview_ADapter aDapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_lineofcredit_activity);
        goBack(findViewById(R.id.img_back));
        initView();
        getData();
    }

    public void initView() {
        sharedUtils = SharedUtils.getSharedUtils();

        edu_edit = (EditText) findViewById(R.id.edu_edit);
        mMyListview = (MyListview) findViewById(R.id.edu_listview);
        textView_title = (TextView) findViewById(R.id.fx_ttxq_title);
        textView_company = (TextView) findViewById(R.id.text_company);
        textView_search = (TextView) findViewById(R.id.search_src_text);
        img_contact_call = (LinearLayout) findViewById(R.id.img_contact_call);
        img_contact_sobot = (ImageView) findViewById(R.id.img_contact_sobot);

        textView_title.setText("企业信用额度");
        textView_company.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG); //下划线

        textView_search.setOnClickListener(this);
        img_contact_call.setOnClickListener(this);
        textView_company.setOnClickListener(this);
        img_contact_sobot.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.img_contact_sobot:
                information = new Information();
                information.setAppkey(sobot_appkey);
                SobotApi.startSobotChat(LineOfCreditActivity.this, information);
                break;
            case R.id.img_contact_call:
                startActivity(new Intent(this, Ed_Call_Dialog_Activity.class));
                break;
            case R.id.search_src_text:
                getComPany(edu_edit.getText().toString());
                break;
            case R.id.text_company:
                Intent intent1 = new Intent(this, MySelfActivity.class);
                intent1.putExtra("data", "1");
                startActivity(intent1);
                break;
        }
    }

    public void getComPany(String keywords) {
        if (TextUtils.isNullOrEmpty(keywords)) {
            Map<String, String> map = new HashMap<>();
            map.put("type", "2");
            map.put("page", "1");
            map.put("fname", keywords);
            map.put("token", sharedUtils.getData(this, "token"));
            postAsyn(this, API.BASEURL + API.CREDIT_CERTIFICATE, map, this, 1);
        } else {
            TextUtils.Toast(this, "请输入搜索关键字！");
        }
    }

    public void getData() {
        postAsyn(this, API.BASEURL + API.CREDIT_PAGE, null, this, 2);
    }

    @Override
    public void callBack(Object object, int type) {
        try {
            Gson gson = new Gson();
            String err = new JSONObject(object.toString()).getString("err");
            if (type == 1) {
                if (err.equals("0")) {
                    companyBean = gson.fromJson(object.toString(), CompanyBean.class);
                    if (companyBean.getData().size() != 1) {
                        showDialog();
                    } else {
                        Intent intent = new Intent(LineOfCreditActivity.this, MySelfActivity.class);
                        intent.putExtra("id", companyBean.getData().get(0).getContact_id());
                        intent.putExtra("data", "2");
                        startActivity(intent);
                    }
                } else {
                    TextUtils.Toast(this, new JSONObject(object.toString()).getString("msg"));
                }
            }
            if (type == 2 && err.equals("0")) {
                EDuBean eDuBean = gson.fromJson(object.toString(), EDuBean.class);
                String html1 = "<font color='#ff4500'>" + eDuBean.getC_name() + "</font>";
                textView_company.setText(Html.fromHtml(html1));
                aDapter = new EDu_Listview_ADapter(this, eDuBean.getData());
                mMyListview.setAdapter(aDapter);
            }
        } catch (Exception e) {
        }
    }

    public void showDialog() {
        Dialog normalDialog = null;
        View view = View.inflate(this, R.layout.layout_edu_dialog, null);
        if (normalDialog == null) {
            normalDialog = new Dialog(this, R.style.commondialog_style);
            normalDialog.setContentView(view);
            setDialogWindowAttr(normalDialog);
        }
        if (!normalDialog.isShowing()) {
            normalDialog.show();
        }
        edu_dialog_listview = (ListView) view.findViewById(R.id.edu_dialog_listview);
        CompanyAdapter arrayAdapter = new CompanyAdapter(this, companyBean.getData());
        edu_dialog_listview.setAdapter(arrayAdapter);
        edu_dialog_listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(LineOfCreditActivity.this, MySelfActivity.class);
                intent.putExtra("id", companyBean.getData().get(position).getContact_id());
                intent.putExtra("data", "2");
                startActivity(intent);
            }
        });
    }

    //设置dialog属性
    public void setDialogWindowAttr(Dialog normalDialog) {
        WindowManager wm = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics outMetrics = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(outMetrics);
        int width = outMetrics.widthPixels;
        int height = outMetrics.heightPixels;

        Window window = normalDialog.getWindow();
        WindowManager.LayoutParams lp = window.getAttributes();
        lp.gravity = Gravity.CENTER;
        lp.width = (int) ((width * 2) / 3);//宽高可设置具体大小
//      lp.height = (int) (height *2/ 3);
        normalDialog.getWindow().setAttributes(lp);
    }

    @Override
    public void failCallBack(int type) {

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
