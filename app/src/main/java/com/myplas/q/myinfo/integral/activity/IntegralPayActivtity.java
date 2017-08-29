package com.myplas.q.myinfo.integral.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.myplas.q.R;
import com.myplas.q.common.api.API;
import com.myplas.q.common.utils.DialogShowUtils;
import com.myplas.q.common.utils.TextUtils;
import com.myplas.q.guide.activity.BaseActivity;
import com.myplas.q.common.netresquset.ResultCallBack;
import com.myplas.q.common.view.MyGridview;
import com.myplas.q.myinfo.integral.adapter.Integral_Pay_Adapter;
import com.myplas.q.myinfo.beans.OrderBean;
import com.myplas.q.myinfo.beans.SelectableBean;
import com.myplas.q.wechatpay.PayUtis;
import com.umeng.analytics.MobclickAgent;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 编写： 黄双
 * 电话：15378412400
 * 邮箱：15378412400@163.com
 * 时间：2017/3/29 09:57
 */
public class IntegralPayActivtity extends BaseActivity implements View.OnClickListener, ResultCallBack, DialogShowUtils.DialogShowInterface {
    private int money;
    private Button button;
    private int plasticBean;
    private String order_id;
    private EditText editText;
    private TextView mTextView;
    private MyGridview myGridview;
    private boolean isCalledWeChat;
    private TextView textView_show1;
    private boolean isSelected_money;
    private List<SelectableBean.DataBean> list;
    private ImageView imageView_zfb, imageView_wx;
    private Integral_Pay_Adapter integralPayAdapter;
    private MyBroadcastReceiver myBroadcastReceiver;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ingeral_pay_activity_layout);

        goBack(findViewById(R.id.back));
        getSelectableMoney();

        button = (Button) findViewById(R.id.chz_zhf);
        mTextView = (TextView) findViewById(R.id.chz_rules);
        editText = (EditText) findViewById(R.id.chz_edittext);
//        imageView_zfb = (ImageView) findViewById(R.id.img_zhfb);
        imageView_wx = (ImageView) findViewById(R.id.img_weixin);
        myGridview = (MyGridview) findViewById(R.id.chz_gridview);
        textView_show1 = (TextView) findViewById(R.id.chz_text_show1);

        button.setOnClickListener(this);
        mTextView.setOnClickListener(this);
//        imageView_wx.setOnClickListener(this);
//        imageView_zfb.setOnClickListener(this);

        myBroadcastReceiver = new MyBroadcastReceiver();
        IntentFilter filter = new IntentFilter("wechat_pay");
        registerReceiver(myBroadcastReceiver, filter);

        myGridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                editText.setText("");
                textView_show1.setText("");
                isSelected_money = true;
                money = list.get(position).getMoney();
                plasticBean = list.get(position).getPlasticBean();
                integralPayAdapter.changColor(position, true);

                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(editText.getWindowToken(), 0);
            }
        });

        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                try {
                    if (s.length() != 0 && s.length() == 1) {
                        if (s.toString().equals("0")) {
                            editText.removeTextChangedListener(this);
                            editText.setText("");
                            editText.addTextChangedListener(this);
                            TextUtils.Toast(IntegralPayActivtity.this, "你输入的金额无效！");
                        } else {
                            getExact(s.toString(), this);
                        }
                    } else if (s.length() != 0 && s.length() != 1) {
                        getExact(s.toString(), this);
                    }
                    //edittext 清空时
                    if (s.toString().equals("")) {
                        isSelected_money = true;
                        money = list.get(0).getMoney();
                        textView_show1.setVisibility(View.GONE);
                        integralPayAdapter.changColor(0, true);
                        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                        imm.hideSoftInputFromWindow(editText.getWindowToken(), 0);
                    }
                } catch (Exception e) {
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
    }

    public void getExact(String s, TextWatcher textWatcher) {
        String string = null;
        if (Integer.parseInt(s.toString()) <= 10000) {
            string = s.toString();
        } else {
            string = "10000";
            editText.removeTextChangedListener(textWatcher);
            editText.setText(string);
            editText.addTextChangedListener(textWatcher);
            editText.setSelection(s.toString().length());
            TextUtils.Toast(IntegralPayActivtity.this, "你输入的金额已达上限！");
        }
        getExactAmount(string);
        isSelected_money = true;
        money = Integer.parseInt(string);
        integralPayAdapter.changColor(-1, true);
        textView_show1.setVisibility(View.VISIBLE);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.chz_zhf:
                getOrder();
                break;
//            case R.id.img_zhfb:
//                imageView_wx.setImageResource(R.drawable.btn_radio);
//                imageView_zfb.setImageResource(R.drawable.btn_radiohl);
//                break;
            case R.id.img_weixin:
                imageView_zfb.setImageResource(R.drawable.btn_radio);
                imageView_wx.setImageResource(R.drawable.btn_radiohl);
                break;
            case R.id.chz_rules:
                startActivity(new Intent(this, IntegralRuleActivtity.class));
                break;
        }
    }

    //  获取可选充值金额
    public void getSelectableMoney() {
        postAsyn(this, API.BASEURL + API.GET_PAY_CONFIG, null, this, 1);
    }

    //  获取固定充值金额
    public void getExactAmount(String money) {
        Map<String, String> map = new HashMap<>();
        map.put("money", money);
        postAsyn(this, API.BASEURL + API.GET_EXACT_AMOUNT, map, this, 2);
    }

    //创建订单：
    public void getOrder() {
        if (isSelected_money) {
            Map<String, String> map = new HashMap<>();
            map.put("type", "1");
            map.put("goods_id", "99");
            map.put("total_fee", money + "");
            map.put("goods_num", plasticBean + "");
            postAsyn(this, API.BASEURL + API.GET_PREPAY_ORDER, map, this, 3);
        } else {
            TextUtils.Toast(this, "请选择您需要充值的金额！");
        }
    }

    //  微信（服务器） 回调
    public void callWeChat(String order_id, String type) {
        Map<String, String> map = new HashMap<>();
        map.put("order_id", order_id);
        map.put("status", type);
        postAsyn(this, API.BASEURL + API.UPDATE_ORDER_STATUS, map, this, 4);
    }

    @Override
    public void callBack(Object object, int type) {
        try {
            Gson gson = new Gson();
            boolean err = new JSONObject(object.toString()).getString("err").equals("0");
            if (type == 1 && err) {
                SelectableBean selectableBean = gson.fromJson(object.toString(), SelectableBean.class);
                list = selectableBean.getData();
                isSelected_money = true;
                money = list.get(0).getMoney();
                plasticBean = list.get(0).getPlasticBean();
                integralPayAdapter = new Integral_Pay_Adapter(this, list);
                myGridview.setAdapter(integralPayAdapter);
            }

            if (type == 2 && err) {
                plasticBean = Integer.parseInt(new JSONObject(object.toString()).getString("plasticBean"));
                textView_show1.setText(plasticBean + "塑豆");
            }

            if (type == 3 && err) {
                OrderBean orderBean = gson.fromJson(object.toString(), OrderBean.class);
                order_id = orderBean.getOrder_id();
                PayUtis payUtis = new PayUtis(this);
                isCalledWeChat = payUtis.pay(orderBean.getData());
                if (isCalledWeChat) {       //已经唤醒微信
                    callWeChat(order_id, "2");
                } else {                     //唤醒微信失败
                    callWeChat(order_id, "-2");
                }
            } else if (type == 3) {
                TextUtils.Toast(this, new JSONObject(object.toString()).getString("msg"));
            }
        } catch (Exception e) {
        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(myBroadcastReceiver);
    }

    //支付回调
    public class MyBroadcastReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals("wechat_pay")) {
                int type = 0;
                String data = intent.getStringExtra("errcode");
                if (data.equals("0")) {//支付成功
                    type = 4;
                    DialogShowUtils dialogShowUtils = new DialogShowUtils();
                    dialogShowUtils.showDialog(context, "支付成功!", 5, IntegralPayActivtity.this);
                }
                if (data.equals("-1")) {//支付失败
                    type = -4;
                    DialogShowUtils dialogShowUtils = new DialogShowUtils();
                    dialogShowUtils.showDialog(context, "请重新充值!", 6, IntegralPayActivtity.this);
                }
                if (data.equals("-2")) {//支付取消
                    type = -3;
                    DialogShowUtils dialogShowUtils = new DialogShowUtils();
                    dialogShowUtils.showDialog(context, "您已取消支付!", 7, IntegralPayActivtity.this);
                }
                callWeChat(order_id, type + "");
            }
        }
    }

    //dialog 回调
    @Override
    public void ok(int type) {

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
