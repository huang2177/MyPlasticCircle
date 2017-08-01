package com.myplas.q.myinfo.setting.activity;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.myplas.q.R;
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
public class MyDataActivity extends BaseActivity implements View.OnClickListener,
        ResultCallBack, RadioGroup.OnCheckedChangeListener, CompoundButton.OnCheckedChangeListener {

    private String imgurl, string;
    private SharedUtils sharedUtils;
    private int head_card, cilick_num;
    private String address = "EC", sex = "0";
    private ImageView image_tx, image_shch, image_show;
    private TextView text_name, text_dj, text_gs, text_dh, text_gj, text_px, textView_save;
    private TextView text_xb, textView_dzh, textView_zhy, textView_ph, textView_num, textView_product, textView_address, textView_company, my_main_prod, my_main_prod_save;
    private EditText textView_dzh_save, textView_zhy_save, textView_ph_save, textView_num_save, textView_product_save;
    private String allow_sendmsg_gz = "1", allow_sendmsg_hf = "1", allow_sendmsg_gk = "1";
    private Map<String, String> map = new HashMap<String, String>();

    private MySelfInfo mySelfInfo;
    private View view_save, view_edit;
    private LinearLayout linearLayout, linearLayout_save_edit, linearLayout_ph, linearLayout_ph_save;
    private RadioGroup radioGroup_sex, radioGroup_address;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_layout_seeting_data);
        goBack(findViewById(R.id.back));
        initView();
        requestNetData();
    }

    public void initView() {
        sharedUtils = SharedUtils.getSharedUtils();
        linearLayout_save_edit = f(R.id.linearlayout_edit_save);
        view_edit = View.inflate(this, R.layout.wd_zl_layout_edit, null);
        view_save = View.inflate(this, R.layout.wd_zl_layout_save, null);
        initView_edit();

        linearLayout_save_edit.addView(view_edit);

        image_tx = f(R.id.xq_tx);
        text_px = f(R.id.wd_zl_px);
        text_dj = f(R.id.wd_zl_dj);
        text_gs = f(R.id.wd_zl_gs);
        text_dh = f(R.id.wd_zl_tel);
        text_gj = f(R.id.wd_zl_gjqg);
        text_name = f(R.id.wd_zl_name);
        image_show = f(R.id.wd_zl_show);
        image_shch = f(R.id.wd_zl_shch);
        my_main_prod = f(R.id.my_main_prod);
        my_main_prod_save = f(R.id.my_main_prod_save);

        image_tx.setOnClickListener(this);
        image_shch.setOnClickListener(this);
        image_show.setOnClickListener(this);

        textView_save = f(R.id.wd_zl_bc);
        textView_save.setOnClickListener(this);


    }

    public <T extends View> T f(int id) {
        return (T) findViewById(id);
    }

    public void initView_save() {
        radioGroup_sex = f(R.id.radio_sex);
        textView_ph_save = f(R.id.wd_zl_text_ph);
        textView_dzh_save = f(R.id.wd_zl_text_dzh);
        textView_zhy_save = f(R.id.wd_zl_text_zhy);
        textView_num_save = f(R.id.wd_zl_text_num);
        radioGroup_address = f(R.id.radio_address);
        textView_product_save = f(R.id.wd_zl_text_products);
        linearLayout = (LinearLayout) view_save.findViewById(R.id.linear_show_close);
        linearLayout_ph = (LinearLayout) view_save.findViewById(R.id.wd_zl_linear_ph);

        radioGroup_sex.setOnCheckedChangeListener(this);
        radioGroup_address.setOnCheckedChangeListener(this);

        //设置塑料制品厂所需栏目显示与否
        String type = mySelfInfo.getData().getType();
        linearLayout.setVisibility((type.equals("1")) ? (View.VISIBLE) : (View.GONE));
        linearLayout_ph.setVisibility((type.equals("1") || (type.equals("2")) ? (View.VISIBLE) : (View.GONE)));
    }

    public void initView_edit() {
        text_xb = (TextView) view_edit.findViewById(R.id.wd_zl_text_xb);
        textView_ph = (TextView) view_edit.findViewById(R.id.wd_zl_text_ph);
        textView_num = (TextView) view_edit.findViewById(R.id.wd_zl_text_num);
        textView_dzh = (TextView) view_edit.findViewById(R.id.wd_zl_text_dzh);
        textView_zhy = (TextView) view_edit.findViewById(R.id.wd_zl_text_zhy);
        linearLayout = (LinearLayout) view_edit.findViewById(R.id.linear_show_close);
        textView_address = (TextView) view_edit.findViewById(R.id.wd_zl_text_address);
        textView_company = (TextView) view_edit.findViewById(R.id.wd_zl_text_company);
        textView_product = (TextView) view_edit.findViewById(R.id.wd_zl_text_products);
        linearLayout_ph_save = (LinearLayout) view_edit.findViewById(R.id.wd_zl_linear_ph);
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (group.getId()) {
            case R.id.radio_sex:
                sex = (checkedId == R.id.radio_sex_man) ? ("0") : ("1");
                break;
            case R.id.radio_address:
                switch (checkedId) {
                    case R.id.radio_address_se:
                        address = "EC";
                        break;
                    case R.id.radio_address_sn:
                        address = "NC";
                        break;
                    case R.id.radio_address_ss:
                        address = "SC";
                        break;
                    case R.id.radio_address_qt:
                        address = "OT";
                        break;
                }
                break;
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.xq_tx:
                head_card = 1;
                checkPic();
                break;
            case R.id.wd_zl_shch:
                head_card = 2;
                checkPic();
                break;
            case R.id.wd_zl_text_dzh:
                break;
            case R.id.wd_zl_bc:
                saveData();
                break;
            case R.id.wd_zl_show:
                Intent intent_im = new Intent(this, BigImageViewActivity.class);
                intent_im.putExtra("imgurl", imgurl);
                startActivity(intent_im);
                break;
        }
    }

    //保存资料。。。
    public void saveData() {
        if (mySelfInfo != null) {
            if (cilick_num == 0) {
                textView_save.setText("保存");
                linearLayout_save_edit.removeAllViews();
                linearLayout_save_edit.addView(view_save);
                initView_save();
                saveInfo(mySelfInfo);
            }
            cilick_num++;
            String address_ = textView_dzh_save.getText().toString();
            String major = textView_zhy_save.getText().toString();
            String concern = textView_ph_save.getText().toString();
            String main_p = null;
            String num = null;
            if (mySelfInfo.getData().getType().equals("1")) {
                main_p = textView_product_save.getText().toString();
                num = textView_num_save.getText().toString();
                map.put("month_consum", num);
                map.put("main_product", main_p);
            }
            String s[] = concern.split(" ");
            String str[] = major.split(" ");
            String s1 = "", s2 = "";
            for (int i = 0; i < s.length; i++) {
                s1 += s[i] + ",";
            }
            for (int i = 0; i < str.length; i++) {
                s2 += str[i] + ",";
            }
            map.put("sex", sex);
            map.put("dist", address);
            map.put("address", address_);
            map.put("type", mySelfInfo.getData().getType());
            map.put("major", s2.substring(0, s2.length() - 1));
            map.put("concern", s1.substring(0, s1.length() - 1));
            map.put("token", sharedUtils.getData(this, "token"));
            if (cilick_num == 2) {
                saveSelfInfo(API.SAVE_SELFINFO, map, 3);
            }
        }
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        switch (buttonView.getId()) {
            case R.id.wd_zl_switch_gz:
                allow_sendmsg_gz = (isChecked) ? "0" : "1";
                map.put("type", "0");
                map.put("token", sharedUtils.getData(this, "token"));
                map.put("is_allow", allow_sendmsg_gz);
                saveSelfInfo(API.FAVORATE_SET, map, 5);
                break;
            case R.id.wd_zl_switch_hf:
                allow_sendmsg_hf = (isChecked) ? "0" : "1";
                map.put("type", "1");
                map.put("is_allow", allow_sendmsg_hf);
                map.put("token", sharedUtils.getData(this, "token"));
                saveSelfInfo(API.FAVORATE_SET, map, 4);
                break;
            case R.id.wd_zl_switch_phone_public:
                allow_sendmsg_gk = (isChecked) ? "0" : "1";
                map.put("type", "2");
                map.put("is_allow", allow_sendmsg_gk);
                map.put("token", sharedUtils.getData(this, "token"));
                saveSelfInfo(API.FAVORATE_SET, map, 6);
                break;
        }
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

    public void showInfo(MySelfInfo mySelfInfo) {
        try {
            Glide.with(this).load(mySelfInfo.getData().getThumb()).placeholder(R.drawable.contact_image_defaul_male).into(image_tx);
            text_name.setText(mySelfInfo.getData().getName());
            text_dj.setText("(" + mySelfInfo.getData().getMember_level() + ")");
            text_gs.setText(mySelfInfo.getData().getC_name());
            text_dh.setText("电话： " + mySelfInfo.getData().getMobile());
            text_gj.setText("发布供给：" + mySelfInfo.getData().getSale() + "  发布求购：" + mySelfInfo.getData().getBuy());
            text_xb.setText(mySelfInfo.getData().getSex());
            textView_address.setText(mySelfInfo.getData().getAdistinct());
            textView_dzh.setText(mySelfInfo.getData().getAddress());
            textView_zhy.setText(mySelfInfo.getData().getNeed_product());
            textView_ph.setText(mySelfInfo.getData().getConcern_model());
            Glide.with(this).load(mySelfInfo.getData().getThumbcard()).into(image_show);
            MySelfInfo.DataBean.AllowSendBean ab = mySelfInfo.getData().getAllow_send();


            text_px.setText("通讯录排序：您目前排在通讯录的第" + mySelfInfo.getData().getRank() + "位，共" + mySelfInfo.getData().getTotal() + "人，按照粉丝数量、发布供给数量进行排序");
            switch (mySelfInfo.getData().getType()) {
                case "1":
                    linearLayout.setVisibility(View.VISIBLE);
                    textView_company.setText("塑料制品厂");
                    my_main_prod.setText("我的需求：");
                    textView_product.setText(mySelfInfo.getData().getMain_product());
                    textView_num.setText("  " + mySelfInfo.getData().getMonth_consum());
                    break;
                case "2":
                    textView_company.setText("原料供应商");
                    linearLayout.setVisibility(View.GONE);
                    break;
                case "4":
                    my_main_prod.setText("我的主营：");
                    linearLayout.setVisibility(View.GONE);
                    textView_company.setText("物流商");
                    break;
                case "5":
                    textView_company.setText("金融公司");
                    linearLayout.setVisibility(View.GONE);
                    break;
                case "6":
                    textView_company.setText("塑化电商");
                    linearLayout.setVisibility(View.GONE);
                    break;
                case "7":
                    textView_company.setText("回料(含新材料)");
                    linearLayout.setVisibility(View.GONE);
                    break;
                case "8":
                    textView_company.setText("期货");
                    linearLayout.setVisibility(View.GONE);
                    break;
                case "9":
                    textView_company.setText("塑机");
                    linearLayout.setVisibility(View.GONE);
                    break;
            }
            //设置“关注的牌号”是否显示
            linearLayout_ph_save.setVisibility((mySelfInfo.getData().getType().equals("1") || (mySelfInfo.getData().getType().equals("2")) ? (View.VISIBLE) : (View.GONE)));
        } catch (Exception e) {
        }
    }

    //编辑时数据的填充
    public void saveInfo(MySelfInfo mySelfInfo) {
        try {
            textView_dzh_save.setText(mySelfInfo.getData().getAddress());
            textView_zhy_save.setText(mySelfInfo.getData().getNeed_product());
            textView_ph_save.setText(mySelfInfo.getData().getConcern_model());
            textView_num_save.setText(mySelfInfo.getData().getMonth_consum());
            textView_product_save.setText(mySelfInfo.getData().getMain_product());
            //性别
            RadioButton rB = (RadioButton) radioGroup_sex.findViewById((mySelfInfo.getData().getSex().equals("男")) ? (R.id.radio_sex_man) : (R.id.radio_sex_woman));
            rB.setChecked(true);

            //设置地区
            switch (mySelfInfo.getData().getAdistinct()) {
                case "华东":
                    RadioButton radioButton = (RadioButton) radioGroup_address.findViewById(R.id.radio_address_se);
                    radioButton.setChecked(true);
                    break;
                case "华北":
                    RadioButton radioButton1 = (RadioButton) radioGroup_address.findViewById(R.id.radio_address_sn);
                    radioButton1.setChecked(true);
                    break;
                case "华南":
                    RadioButton radioButton2 = (RadioButton) radioGroup_address.findViewById(R.id.radio_address_ss);
                    radioButton2.setChecked(true);
                    break;
                case "其他":
                    RadioButton radioButton3 = (RadioButton) radioGroup_address.findViewById(R.id.radio_address_qt);
                    radioButton3.setChecked(true);
                    break;
            }
            //设置企业类型
            switch (mySelfInfo.getData().getType()) {
                case "1":
                    linearLayout.setVisibility(View.VISIBLE);
                    my_main_prod_save.setText("我的需求：");
                    textView_product_save.setText(mySelfInfo.getData().getMain_product());
                    textView_num_save.setText(mySelfInfo.getData().getMonth_consum());
                    break;
                default:
                    my_main_prod_save.setText("我的主营：");
                    linearLayout.setVisibility(View.GONE);
                    break;
            }
        } catch (Exception e) {
        }
    }

    @Override
    public void callBack(Object object, int type) {
        try {
            if (type == 1) {
                if (new JSONObject(object.toString()).getString("err").equals("0")) {
                    Gson gson = new Gson();
                    mySelfInfo = gson.fromJson(object.toString(), MySelfInfo.class);
                    imgurl = mySelfInfo.getData().getThumbcard();
                    showInfo(mySelfInfo);
                } else {
                    mySelfInfo = null;
                }
            } else if (type == 3) {
                cilick_num = 0;
                string = new JSONObject(object.toString()).getString("msg");
                TextUtils.Toast(this, string);
                if (new JSONObject(object.toString()).getString("err").equals("0")) {
                    textView_save.setText("编辑");
                    initView_edit();
                    linearLayout_save_edit.removeAllViews();
                    linearLayout_save_edit.addView(view_edit);
                    requestNetData();
                }
            }
            if (type == 6 && new JSONObject(object.toString()).getString("err").equals("0")) {
                imgurl = new JSONObject(object.toString()).getString("url");
            }
        } catch (JSONException e) {
        }
    }

    @Override
    public void failCallBack(int type) {

    }

    //选择照片返回结果
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK && data != null) {
            Uri selectedImage = data.getData();
            String[] filePathColumns = {MediaStore.Images.Media.DATA};
            Cursor c = getContentResolver().query(selectedImage, filePathColumns, null, null, null);
            c.moveToFirst();
            int columnIndex = c.getColumnIndex(filePathColumns[0]);
            String imagePath = c.getString(columnIndex);
            if (requestCode == 100) {
                showImage(imagePath, image_tx, 5);
            } else {
                showImage(imagePath, image_show, 6);
            }
            c.close();
        }
    }

    private void showImage(String imaePath, ImageView imageView, int type) {
        Bitmap bitmap = BitmapFactory.decodeFile(imaePath);
        Bitmap bp = BitmapUtils.ratio(imaePath, 70, 400);
        //imageView.setImageBitmap(bitmap);
        Glide.with(this).load(imaePath).into(imageView);
        upLoadImg((head_card == 1) ? (API.SAVE_PIC_TO_SERVER) : (API.SAVE_CARD_IMG), imaePath, type);
    }

    public void checkPic() {
        int api = SystemUtils.getSystemInfo();
        if (api >= 23) {
            checkPremission();
        } else {
            Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            startActivityForResult(intent, (head_card == 1) ? (100) : (200));
        }
    }

    //动态申请权限
    private void checkPremission() {
        final String permission1 = Manifest.permission.READ_EXTERNAL_STORAGE; //写入数据权限
        if (ContextCompat.checkSelfPermission(this, permission1) != PackageManager.PERMISSION_GRANTED
                || ContextCompat.checkSelfPermission(this, permission1) != PackageManager.PERMISSION_GRANTED) {  //先判断是否被赋予权限，没有则申请权限
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA, Manifest.permission.READ_EXTERNAL_STORAGE}, 300);
        } else {
            //赋予过权限，则直接调用相册
            Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            startActivityForResult(intent, (head_card == 1) ? (100) : (200));
        }
    }

    //权限申请返回结果
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {  //申请权限的返回值
            case 300:
                int length = grantResults.length;
                final boolean isGranted = length >= 1 && PackageManager.PERMISSION_GRANTED == grantResults[length - 1];
                if (isGranted) {//如果用户赋予权限，则调用相册
                    Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    startActivityForResult(intent, (head_card == 1) ? (100) : (200));
                }
                break;
            default:
                super.onRequestPermissionsResult(requestCode, permissions, grantResults);
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
