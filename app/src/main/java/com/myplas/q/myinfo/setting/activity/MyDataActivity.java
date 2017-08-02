package com.myplas.q.myinfo.setting.activity;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.myplas.q.R;
import com.myplas.q.common.view.MyImageView;
import com.myplas.q.guide.activity.BaseActivity;
import com.myplas.q.guide.activity.BigImageViewActivity;
import com.myplas.q.common.netresquset.ResultCallBack;
import com.myplas.q.common.utils.BitmapUtils;
import com.myplas.q.common.utils.SharedUtils;
import com.myplas.q.common.utils.SystemUtils;
import com.myplas.q.common.utils.TextUtils;
import com.myplas.q.common.api.API;
import com.myplas.q.myinfo.beans.MySelfInfo;
import com.umeng.analytics.MobclickAgent;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;


/**
 * 编写： 黄双
 * 电话：15378412400
 * 邮箱：15378412400@163.com
 * 时间：2017/3/23 13:39
 */
public class MyDataActivity extends BaseActivity implements View.OnClickListener, ResultCallBack {

    private MySelfInfo mySelfInfo;
    private SharedUtils sharedUtils;
    private Map<String, String> map;
    private String address, sex, region, product, monthUse, mainPro, model;

    private ImageView image_shch;
    private MyImageView image_tx;
    private RadioGroup radioGroup_sex, radioGroup_address;
    private LinearLayout ll_pro_month, ll_add, ll_sex, ll_region, ll_pro, ll_mothonuse, ll_mainsell, ll_mode;
    private TextView text_xb, textView_dzh, text_gs, text_dh, textView_zhy, textView_ph, textView_num, textView_product,
            textView_address, textView_company, my_main_prod, my_main_prod_save;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_layout_seeting_data);
        initTileBar();
        setTitle("个人信息");
        initView();
        requestNetData();
    }

    public void initView() {
        map = new HashMap<String, String>();
        sharedUtils = SharedUtils.getSharedUtils();

        text_gs = F(R.id.wd_zl_gs);
        text_dh = F(R.id.wd_zl_tel);
        image_tx = F(R.id.wd_zl_text_headimg);
        image_shch = F(R.id.wd_zl_text_upload);

        my_main_prod = F(R.id.textView8);
        text_xb = F(R.id.wd_zl_text_xb);
        textView_ph = F(R.id.wd_zl_text_ph);
        textView_num = F(R.id.wd_zl_text_num);
        textView_dzh = F(R.id.wd_zl_text_dzh);
        textView_zhy = F(R.id.wd_zl_text_zhy);
        textView_address = F(R.id.wd_zl_text_address);
        textView_company = F(R.id.wd_zl_text_company);
        textView_product = F(R.id.wd_zl_text_products);

        ll_sex = F(R.id.setting_data_sex);
        ll_mode = F(R.id.wd_zl_linear_ph);
        ll_pro = F(R.id.setting_data_product);
        ll_add = F(R.id.setting_data_address);
        ll_region = F(R.id.setting_data_region);
        ll_pro_month = F(R.id.linear_show_close);
        ll_mainsell = F(R.id.setting_data_mainsell);
        ll_mothonuse = F(R.id.setting_data_monthlyuse);

        image_tx.setOnClickListener(this);
        image_shch.setOnClickListener(this);

        ll_sex.setOnClickListener(this);
        ll_pro.setOnClickListener(this);
        ll_add.setOnClickListener(this);
        ll_region.setOnClickListener(this);
        ll_mainsell.setOnClickListener(this);
        ll_pro_month.setOnClickListener(this);
        ll_mothonuse.setOnClickListener(this);

    }


//    public void initView_save() {
//        radioGroup_sex = f(R.id.radio_sex);
//        textView_ph_save = f(R.id.wd_zl_text_ph);
//        textView_dzh_save = f(R.id.wd_zl_text_dzh);
//        textView_zhy_save = f(R.id.wd_zl_text_zhy);
//        textView_num_save = f(R.id.wd_zl_text_num);
//        radioGroup_address = f(R.id.radio_address);
//        textView_product_save = f(R.id.wd_zl_text_products);
//        linearLayout = (LinearLayout) view_save.findViewById(R.id.linear_show_close);
//        linearLayout_ph = (LinearLayout) view_save.findViewById(R.id.wd_zl_linear_ph);
//
//        radioGroup_sex.setOnCheckedChangeListener(this);
//        radioGroup_address.setOnCheckedChangeListener(this);
//
//        //设置塑料制品厂所需栏目显示与否
//        String type = mySelfInfo.getData().getType();
//        linearLayout.setVisibility((type.equals("1")) ? (View.VISIBLE) : (View.GONE));
//        linearLayout_ph.setVisibility((type.equals("1") || (type.equals("2")) ? (View.VISIBLE) : (View.GONE)));
//    }


//    @Override
//    public void onCheckedChanged(RadioGroup group, int checkedId) {
//        switch (group.getId()) {
//            case R.id.radio_sex:
//                sex = (checkedId == R.id.radio_sex_man) ? ("0") : ("1");
//                break;
//            case R.id.radio_address:
//                switch (checkedId) {
//                    case R.id.radio_address_se:
//                        address = "EC";
//                        break;
//                    case R.id.radio_address_sn:
//                        address = "NC";
//                        break;
//                    case R.id.radio_address_ss:
//                        address = "SC";
//                        break;
//                    case R.id.radio_address_qt:
//                        address = "OT";
//                        break;
//                }
//                break;
//        }
//    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.wd_zl_text_headimg:
                Intent intent1 = new Intent(this, TakePhotoDialogActivity.class);
                intent1.putExtra("type", "1");
                startActivityForResult(intent1, 100);
                break;
            case R.id.wd_zl_text_upload:
                Intent intent2 = new Intent(this, TakePhotoDialogActivity.class);
                intent2.putExtra("type", "2");
                startActivityForResult(intent2, 200);
                break;
            case R.id.setting_data_sex:

                break;
            case R.id.setting_data_region:
                break;

            case R.id.setting_data_address:

                break;
            case R.id.setting_data_product:
                Intent intent6 = new Intent(this, DataCommonInputActivity.class);
                intent6.putExtra("title", "生产产品");
                intent6.putExtra("hint", product);
                startActivityForResult(intent6, 6);
                break;
            case R.id.setting_data_monthlyuse:
                Intent intent7 = new Intent(this, DataCommonInputActivity.class);
                intent7.putExtra("title", "月用量");
                intent7.putExtra("hint", monthUse);
                startActivityForResult(intent7, 7);
                break;
            case R.id.setting_data_mainsell:
                Intent intent8 = new Intent(this, DataCommonInputActivity.class);
                intent8.putExtra("title", "我的主营");
                intent8.putExtra("hint", mainPro);
                startActivityForResult(intent8, 8);
                break;
        }
    }

    //保存资料。。。
    public void saveData() {
        Map map = new HashMap();
        if (mySelfInfo.getData().getType().equals("1")) {
            map.put("month_consum", monthUse);
            map.put("main_product", product);
        }
        String s[] = model.split(" ");
        String str[] = mainPro.split(" ");
        String s1 = "", s2 = "";
        for (int i = 0; i < s.length; i++) {
            s1 += s[i] + ",";
        }
        for (int i = 0; i < str.length; i++) {
            s2 += str[i] + ",";
        }
        map.put("sex", sex);
        map.put("dist", address);
        map.put("address", region);
        map.put("type", mySelfInfo.getData().getType());
        map.put("major", s2.substring(0, s2.length() - 1));
        map.put("concern", s1.substring(0, s1.length() - 1));
        map.put("token", sharedUtils.getData(this, "token"));
        saveSelfInfo(API.SAVE_SELFINFO, map, 3);
    }

    public void requestNetData() {
        Map<String, String> map = new HashMap<>();
        map.put("token", sharedUtils.getData(this, "token"));
        String url = API.BASEURL + API.GET_SELF_INFO;
        postAsyn(this, url, map, this, 1);
    }

    public void saveSelfInfo(String method, Map map, int type) {
        String url = API.BASEURL + method;
        postAsyn(this, url, map, this, type);
    }

    public void upLoadImg(String method, String imgpath, int type) {
        Map<String, String> map = new HashMap<>();
        String token = sharedUtils.getData(this, "token");
        postUpLoadIMG(this, API.BASEURL + method, imgpath, token, this, type);
    }


//    //编辑时数据的填充
//    public void saveInfo(MySelfInfo mySelfInfo) {
//        try {
////            textView_dzh_save.setText(mySelfInfo.getData().getAddress());
////            textView_zhy_save.setText(mySelfInfo.getData().getNeed_product());
////            textView_ph_save.setText(mySelfInfo.getData().getConcern_model());
////            textView_num_save.setText(mySelfInfo.getData().getMonth_consum());
////            textView_product_save.setText(mySelfInfo.getData().getMain_product());
//            //性别
//            RadioButton rB = (RadioButton) radioGroup_sex.findViewById((mySelfInfo.getData().getSex().equals("男")) ? (R.id.radio_sex_man) : (R.id.radio_sex_woman));
//            rB.setChecked(true);
//
//            //设置地区
//            switch (mySelfInfo.getData().getAdistinct()) {
//                case "华东":
//                    RadioButton radioButton = (RadioButton) radioGroup_address.findViewById(R.id.radio_address_se);
//                    radioButton.setChecked(true);
//                    break;
//                case "华北":
//                    RadioButton radioButton1 = (RadioButton) radioGroup_address.findViewById(R.id.radio_address_sn);
//                    radioButton1.setChecked(true);
//                    break;
//                case "华南":
//                    RadioButton radioButton2 = (RadioButton) radioGroup_address.findViewById(R.id.radio_address_ss);
//                    radioButton2.setChecked(true);
//                    break;
//                case "其他":
//                    RadioButton radioButton3 = (RadioButton) radioGroup_address.findViewById(R.id.radio_address_qt);
//                    radioButton3.setChecked(true);
//                    break;
//            }
//            //设置企业类型
//            switch (mySelfInfo.getData().getType()) {
//                case "1":
//                    ll_pro_month.setVisibility(View.VISIBLE);
//                    my_main_prod_save.setText("我的需求：");
////                    textView_product_save.setText(mySelfInfo.getData().getMain_product());
////                    textView_num_save.setText(mySelfInfo.getData().getMonth_consum());
//                    break;
//                default:
//                    my_main_prod_save.setText("我的主营：");
//                    ll_pro_month.setVisibility(View.GONE);
//                    break;
//            }
//        } catch (Exception e) {
//        }
//    }

    @Override
    public void callBack(Object object, int type) {
        try {
            String err = new JSONObject(object.toString()).getString("err");
            if (type == 1) {
                mySelfInfo = null;
                if (err.equals("0")) {
                    Gson gson = new Gson();
                    mySelfInfo = gson.fromJson(object.toString(), MySelfInfo.class);
                    showInfo(mySelfInfo);
                }
            }
            Log.e("---******---", object.toString());
            if (type == 3 && err.equals("0")) {
                requestNetData();
            }
        } catch (JSONException e) {
        }
    }

    @Override
    public void failCallBack(int type) {
    }

    public void showInfo(MySelfInfo mySelfInfo) {
        try {
            sex = mySelfInfo.getData().getSex();
            address = mySelfInfo.getData().getAddress();
            region = mySelfInfo.getData().getAdistinct();
            model = mySelfInfo.getData().getConcern_model();
            product = mySelfInfo.getData().getMain_product();
            mainPro = mySelfInfo.getData().getNeed_product();
            monthUse = mySelfInfo.getData().getMonth_consum();


            text_xb.setText(sex + "  ");
            textView_ph.setText(model + "  ");
            textView_dzh.setText(address + "  ");
            textView_zhy.setText(mainPro + "  ");
            textView_address.setText(region + "  ");
            text_gs.setText(mySelfInfo.getData().getC_name() + "  ");
            text_dh.setText(mySelfInfo.getData().getMobile() + "  ");
            Glide.with(this).load(mySelfInfo.getData().getThumbcard()).placeholder(R.drawable.card).into(image_shch);
            Glide.with(this).load(mySelfInfo.getData().getThumb()).placeholder(R.drawable.contact_image_defaul_male).into(image_tx);

            String type = mySelfInfo.getData().getType();
            switch (type) {
                case "1":
                    ll_mode.setVisibility(View.VISIBLE);       //"‘关注的牌号’是否显示"
                    ll_pro_month.setVisibility(View.VISIBLE); //"‘月用量与生产产品’是否显示"
                    textView_company.setText("塑料制品厂  ");
                    my_main_prod.setText("我的需求：");
                    textView_product.setText(product + "  ");
                    textView_num.setText(monthUse + "  ");
                    break;
                case "2":
                    ll_mode.setVisibility(View.VISIBLE);
                    textView_company.setText("原料供应商  ");
                    ll_pro_month.setVisibility(View.GONE);
                    break;
                case "4":
                    ll_mode.setVisibility(View.GONE);
                    ll_pro_month.setVisibility(View.GONE);
                    my_main_prod.setText("我的主营：");
                    textView_company.setText("物流商  ");
                    break;
                case "5":
                    ll_mode.setVisibility(View.GONE);
                    ll_pro_month.setVisibility(View.GONE);
                    textView_company.setText("金融公司  ");
                    break;
                case "6":
                    ll_mode.setVisibility(View.GONE);
                    ll_pro_month.setVisibility(View.GONE);
                    textView_company.setText("塑化电商  ");
                    break;
                case "7":
                    ll_mode.setVisibility(View.GONE);
                    ll_pro_month.setVisibility(View.GONE);
                    textView_company.setText("回料(含新材料)  ");
                    break;
                case "8":
                    ll_mode.setVisibility(View.GONE);
                    ll_pro_month.setVisibility(View.GONE);
                    textView_company.setText("期货  ");
                    break;
                case "9":
                    textView_company.setText("塑机  ");
                    ll_mode.setVisibility(View.GONE);
                    ll_pro_month.setVisibility(View.GONE);
                    break;
            }
        } catch (Exception e) {
        }
    }

    //选择照片返回结果
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 100 && resultCode == 1) {
            String imagePath = data.getStringExtra("img_url");
            Glide.with(this).load(imagePath).into(image_tx);
            upLoadImg(API.SAVE_PIC_TO_SERVER, imagePath, 5);
        }
        if (requestCode == 200 && resultCode == 2) {
            String imagePath = data.getStringExtra("img_url");
            Glide.with(this).load(imagePath).into(image_shch);
            upLoadImg(API.SAVE_CARD_IMG, imagePath, 6);
        }
        if (requestCode == 6 && data != null) {
            if (!product.equals(data.getStringExtra("updateData"))) {
                Log.e("====", product);
                product = data.getStringExtra("updateData");
                saveData();
            }
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
