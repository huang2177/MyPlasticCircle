package com.myplas.q.release.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.myplas.q.R;
import com.myplas.q.guide.activity.BaseActivity;
import com.myplas.q.common.netresquset.ResultCallBack;
import com.myplas.q.common.utils.SharedUtils;
import com.myplas.q.common.utils.TextUtils;
import com.myplas.q.common.api.API;
import com.myplas.q.release.bean.SecondPurBean;
import com.myplas.q.supdem.activity.SupDem_Detail_Activity;
import com.umeng.analytics.MobclickAgent;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * 编写： 黄双
 * 电话：15378412400
 * 邮箱：15378412400@163.com
 * 时间：2017/3/21 10:26
 */
public class ReleaseSupDemActivity extends BaseActivity implements View.OnClickListener, ResultCallBack {
    private String id;
    private int f_type = 1;
    private TextView title;
    private ImageView imageView;
    private String type, mode = "1";
    private SharedUtils sharedUtils;
    private LinearLayout linearLayout;
    private Button btn_ks, btn_jz, fb;
    private Map<String, String> map = new HashMap<>();
    private EditText editText, editText_jz_ph, editText_jz_chj, editText_jz_jg, editText_jz_jhd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_release_supdem_activity);
        goBack(findViewById(R.id.back_img));
        initView();
    }

    public void initView() {
        sharedUtils = SharedUtils.getSharedUtils();

        fb = (Button) findViewById(R.id.fb_ok);
        btn_ks = (Button) findViewById(R.id.fb_ks);
        btn_jz = (Button) findViewById(R.id.fb_jz);

        editText = (EditText) findViewById(R.id.fb_edit);
        title = (TextView) findViewById(R.id.fb_titlebar);
        imageView = (ImageView) findViewById(R.id.image_k_j);
        editText_jz_chj = (EditText) findViewById(R.id.jzfb_edit_cj);
        editText_jz_jg = (EditText) findViewById(R.id.jzfb_edit_jg);
        editText_jz_jhd = (EditText) findViewById(R.id.jzfb_edit_jhd);
        editText_jz_ph = (EditText) findViewById(R.id.jzfb_edit_ph);

        linearLayout = (LinearLayout) findViewById(R.id.jzfb_linear);

        fb.setOnClickListener(this);
        btn_ks.setOnClickListener(this);
        btn_jz.setOnClickListener(this);

        id = getIntent().getStringExtra("id");
        type = getIntent().getStringExtra("type");
        getSecondPub();
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String s1 = s.toString();
                if (s.length() >= 100) {
                    TextUtils.Toast(ReleaseSupDemActivity.this, "输入的字符已达上限！");
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.fb_jz:
                mode = "2";
                changeTextColor_Right();
                break;
            case R.id.fb_ks:
                mode = "1";
                changeTextColor_Speed();
                break;
            case R.id.fb_ok:
                if ("2".equals(mode)) {
                    if (TextUtils.isNullOrEmpty(editText_jz_jhd.getText().toString()) && TextUtils.isNullOrEmpty(editText_jz_chj.getText().toString())
                            && TextUtils.isNullOrEmpty(editText_jz_ph.getText().toString()) && TextUtils.isNullOrEmpty(editText_jz_jg.getText().toString())) {
                        Pub();
                    } else {
                        TextUtils.Toast(this, "请输入正确的数据！");
                    }
                }
                if ("1".equals(mode)) {
                    if (TextUtils.isNullOrEmpty(editText.getText().toString())) {
                        Pub();
                    } else {
                        TextUtils.Toast(this, "发布内容不能为空！");
                    }
                }
                break;
        }
    }

    public void changeTextColor_Speed() {
        linearLayout.setVisibility(View.GONE);
        editText.setVisibility(View.VISIBLE);
        imageView.setImageResource(R.drawable.btn_switch_quick_release);
    }

    public void changeTextColor_Right() {
        editText.setVisibility(View.GONE);
        linearLayout.setVisibility(View.VISIBLE);
        imageView.setImageResource(R.drawable.btn_switch_accurate_release);


    }

    public void getSecondPub() {
        try {
            map.put("token", sharedUtils.getData(this, "token"));
            map.put("id", id);
            postAsyn(this, API.BASEURL + API.SECOND_PUB, map, this, 1);
        } catch (Exception e) {
        }
    }

    public void Pub() {
        Map<String, String> map = new HashMap<>();
        map.put("type", type);
        map.put("mode", mode);
        map.put("channel", "1");
        map.put("content", editText.getText().toString());
        map.put("model", editText_jz_ph.getText().toString());
        map.put("price", editText_jz_jg.getText().toString());
        map.put("vendor", editText_jz_chj.getText().toString());
        map.put("storehouse", editText_jz_jhd.getText().toString());
        postAsyn(this, API.BASEURL + API.PUB, map, this, 2);
    }

    @Override
    public void callBack(Object object, int type) {
        try {
            Gson gson = new Gson();
            if (type == 1) {
                SecondPurBean secondPurBean = gson.fromJson(object.toString(), SecondPurBean.class);
                f_type = secondPurBean.getData().getF_type();
                this.type = secondPurBean.getData().getType();
                editText_jz_jg.setText(secondPurBean.getData().getUnit_price());
                editText_jz_ph.setText(secondPurBean.getData().getModel());
                editText_jz_chj.setText(secondPurBean.getData().getF_name());
                editText_jz_jhd.setText(secondPurBean.getData().getStore_house());
                editText.setText(secondPurBean.getData().getContent());
                editText.setSelection(editText.getText().length());
                editText_jz_jg.setSelection(editText_jz_jg.getText().length());
                editText_jz_ph.setSelection(editText_jz_ph.getText().length());
                editText_jz_chj.setSelection(editText_jz_chj.getText().length());
                editText_jz_jhd.setSelection(editText_jz_jhd.getText().length());
                if (f_type == 2) {
                    mode = "2";
                    changeTextColor_Speed();
                } else {
                    changeTextColor_Right();
                    mode = "1";
                }
                title.setText(this.type.equals("2") ? ("发布供给") : ("发布求购"));
            }
            if (type == 2) {
                TextUtils.Toast(this, new JSONObject(object.toString()).getString("msg"));
                if (new JSONObject(object.toString()).getString("err").equals("0")) {
                    //发送广播关闭activity
                    Intent intent = new Intent("com.broadcast.test");
                    intent.putExtra("data", "1");
                    intent.putExtra("what", "1");
                    sendBroadcast(intent);
                    sharedUtils.setBooloean(this, "isfinish", true);

                    //跳转到供求详情
                    Intent intent1 = new Intent(this, SupDem_Detail_Activity.class);

                    String id_ = new JSONObject(object.toString()).getString("id");
                    String user_id = sharedUtils.getData(this, "userid");

                    intent1.putExtra("id", id_);
                    intent1.putExtra("type", "0");
                    intent1.putExtra("userid", user_id);
                    intent1.putExtra("what", "5");

                    startActivity(intent1);
                    finish();
                }
            }
        } catch (Exception e) {
        }
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
