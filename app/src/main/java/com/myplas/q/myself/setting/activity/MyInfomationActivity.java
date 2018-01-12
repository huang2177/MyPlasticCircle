package com.myplas.q.myself.setting.activity;

import android.app.ActivityOptions;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.myplas.q.R;
import com.myplas.q.common.api.API;
import com.myplas.q.common.appcontext.Constant;
import com.myplas.q.common.netresquset.ResultCallBack;
import com.myplas.q.common.utils.SharedUtils;
import com.myplas.q.common.utils.TextUtils;
import com.myplas.q.common.view.RoundCornerImageView;
import com.myplas.q.app.activity.BaseActivity;
import com.myplas.q.app.activity.MainActivity;
import com.myplas.q.app.activity.PreImageViewActivity;
import com.myplas.q.myself.beans.MySelfInfo;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;


/**
 * 编写： 黄双
 * 电话：15378412400
 * 邮箱：15378412400@163.com
 * 时间：2017/3/23 13:39
 */
public class MyInfomationActivity extends BaseActivity implements View.OnClickListener, ResultCallBack {

    private IntentFilter mFilter;
    private MyReceiver myReceiver;

    private int regionPosition;
    private MySelfInfo mySelfInfo;
    private SharedUtils sharedUtils;
    private Map<String, String> map;
    private String sexInPut, regionInPut;
    private String type, address, location, sex, region, mainProduct, monthUse, needProduct, model;

    private View shareView;
    private RoundCornerImageView imageHead;
    private ImageView imageCard, cardArrow, headArrow, licenceArrow, imageLicence;
    private LinearLayout llMonthUse, llAdd, llSex, llRegion, llProduct, llMothonuse, llMainsell, llMode, llLicence;
    private TextView textXb, tvLocation, textGs, tvPhone, tvNeedProduct, tvCareModel, tvMonthUse, tvProduct, tvAddress,
            tvCompany, _tvNeedProduct, mName;

    private String from, imgCard, imgHead, imgLicence;
    private final String ACTION = "com.broadcast.databack";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_layout_seeting_data);
        initTileBar();

        initView();
        judgeFrom();
    }

    public void initView() {
        map = new HashMap<String, String>();
        sharedUtils = SharedUtils.getSharedUtils();
        from = getIntent().getStringExtra("from");

        textGs = F(R.id.wd_zl_gs);
        tvPhone = F(R.id.wd_zl_tel);
        mName = F(R.id.wd_zl_name);
        cardArrow = F(R.id.data_img_card);
        headArrow = F(R.id.data_head_card);
        imageCard = F(R.id.wd_zl_text_upload);
        imageHead = F(R.id.wd_zl_text_headimg);
        licenceArrow = F(R.id.data_img_licence);

        textXb = F(R.id.wd_zl_text_xb);
        _tvNeedProduct = F(R.id.textView8);
        tvAddress = F(R.id.wd_zl_text_address);
        tvCompany = F(R.id.wd_zl_text_company);
        tvProduct = F(R.id.wd_zl_text_products);
        tvMonthUse = F(R.id.wd_zl_text_monthuse);
        tvLocation = F(R.id.wd_zl_text_location);
        tvCareModel = F(R.id.wd_zl_text_caremodel);
        tvNeedProduct = F(R.id.wd_zl_text_needproduct);

        llLicence = F(R.id.ll_licence);
        llSex = F(R.id.setting_data_sex);
        imageLicence = F(R.id.wd_licence);
        llAdd = F(R.id.setting_data_location);
        llRegion = F(R.id.setting_data_region);
        llMonthUse = F(R.id.linear_show_close);
        llMode = F(R.id.wd_zl_linear_caremodel);
        llProduct = F(R.id.setting_data_product);
        llMothonuse = F(R.id.setting_data_monthuse);
        llMainsell = F(R.id.setting_data_needproduct);
    }

    /**
     * 判断是从哪里跳转
     */
    private void judgeFrom() {
        if ("1".equals(from)) {  //从通讯录跳转
            setTitle("个人资料");
            getPersonInfo();
        } else {
            requestNetData();
            setTitle("个人信息");

            //注册广播；
            myReceiver = new MyReceiver();
            mFilter = new IntentFilter(ACTION);
            registerReceiver(myReceiver, mFilter);

            llSex.setOnClickListener(this);
            llAdd.setOnClickListener(this);
            llMode.setOnClickListener(this);
            mIVLeft.setOnClickListener(this);
            llRegion.setOnClickListener(this);
            llProduct.setOnClickListener(this);
            llMainsell.setOnClickListener(this);
            llMonthUse.setOnClickListener(this);
            llMothonuse.setOnClickListener(this);

            cardArrow.setVisibility(View.VISIBLE);
            headArrow.setVisibility(View.VISIBLE);
            licenceArrow.setVisibility(View.VISIBLE);
            textXb.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.btn_more_small, 0);
            tvAddress.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.btn_more_small, 0);
            tvProduct.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.btn_more_small, 0);
            tvMonthUse.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.btn_more_small, 0);
            tvLocation.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.btn_more_small, 0);
            tvCareModel.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.btn_more_small, 0);
            tvNeedProduct.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.btn_more_small, 0);

            licenceArrow.setImageResource(R.drawable.btn_more);
            mName.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.btn_more, 0);
            textGs.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.btn_more, 0);
            tvPhone.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.btn_more, 0);
            tvCompany.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.btn_more, 0);
        }
        imageHead.setOnClickListener(this);
        imageCard.setOnClickListener(this);
        imageLicence.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {

        if (mySelfInfo == null && v.getId() != R.id.titlebar_img_left) {
            return;
        }
        switch (v.getId()) {
            case R.id.wd_zl_text_headimg:
                shareView = imageHead;
                preViewOrUpLoad("1", imgHead, 100);
                break;
            case R.id.wd_zl_text_upload:
                shareView = imageCard;
                preViewOrUpLoad("2", imgCard, 200);
                break;
            case R.id.wd_licence:
                shareView = imageLicence;
                preViewOrUpLoad("5", imgLicence, 200);
                break;
            case R.id.setting_data_sex:
                Intent intent3 = new Intent(this, DataCommonActivity.class);
                intent3.putExtra("type", "0");
                intent3.putExtra("title", "性别");
                intent3.putExtra("hint", sexInPut);
                startActivity(intent3);
                break;
            case R.id.setting_data_region:
                Intent intent4 = new Intent(this, DataCommonActivity.class);
                intent4.putExtra("type", "1");
                intent4.putExtra("title", "所属地区");
                intent4.putExtra("hint", regionPosition + "");
                startActivity(intent4);
                break;

            case R.id.setting_data_location:
                Intent intent5 = new Intent(this, AddressSelectedActivity.class);
                intent5.putExtra("type", "3");
                intent5.putExtra("title", "地址");
                intent5.putExtra("address", address);
                intent5.putExtra("location", location);
                startActivity(intent5);
                break;
            case R.id.setting_data_product:
                Intent intent6 = new Intent(this, DataCommonActivity.class);
                intent6.putExtra("type", "2");
                intent6.putExtra("title", "生产产品");
                intent6.putExtra("hint", mainProduct);
                startActivityForResult(intent6, 6);
                break;
            case R.id.setting_data_monthuse:
                Intent intent7 = new Intent(this, DataCommonActivity.class);
                intent7.putExtra("type", "2");
                intent7.putExtra("title", "月用量");
                intent7.putExtra("hint", monthUse);
                startActivityForResult(intent7, 7);
                break;
            case R.id.setting_data_needproduct:
                Intent intent8 = new Intent(this, ("4".equals(type))
                        ? (MyMainPro_LogisticsActivity.class)
                        : (DataCommonActivity.class));
                intent8.putExtra("type", "2");
                intent8.putExtra("hint", (type.equals("1") ? needProduct : mainProduct));
                intent8.putExtra("title", (type.equals("1")) ? ("我的需求") : ("我的主营"));
                startActivityForResult(intent8, 8);
                break;
            case R.id.wd_zl_linear_caremodel:
                Intent intent9 = new Intent(this, DataCommonActivity.class);
                intent9.putExtra("type", "2");
                intent9.putExtra("title", "关注的牌号");
                intent9.putExtra("hint", model);
                startActivityForResult(intent9, 9);
                break;
            case R.id.titlebar_img_left:
                eixt();
                break;
            default:
                break;
        }
    }

    /**
     * 判断是上传图片还是放大图片
     *
     * @param type        使相机还是相册
     * @param imgurl      图片url
     * @param requestCode
     */

    private void preViewOrUpLoad(String type, String imgurl, int requestCode) {
        if ("1".equals(from) || "5".equals(type)) {
            Intent intent1 = new Intent(this, PreImageViewActivity.class);
            intent1.putExtra("imgurl", imgurl);
            intent1.putExtra("type", "1".equals(type) ? sexInPut : type);
            startActivityByTras(intent1);
        } else {
            Intent intent1 = new Intent(this, TakePhotoDialogActivity.class);
            intent1.putExtra("type", type);
            startActivityForResult(intent1, requestCode);
        }
    }

    /**
     * 使用共享元素--activity跳转
     *
     * @param intent
     */
    private void startActivityByTras(Intent intent) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(this
                    , shareView
                    , "bigImageView"
            ).toBundle());
        } else {
            startActivity(intent);
        }
    }

    public void requestNetData() {
        getAsyn(this, API.GET_SELF_INFO, null, this, 1);
    }


    /**
     * 从通讯录跳转过来 -- 请求数据
     */
    public void getPersonInfo() {
        Map<String, String> map = new HashMap<>();
        map.put("user_id", getIntent().getStringExtra("userid"));
        getAsyn(this, API.GETINFORMATION, map, this, 1);
    }

    /**
     * 保存资料
     */
    public void saveData() {
        Map map = new HashMap(16);
        if ("1".equals(type)) {
            map.put("month_consum", monthUse);
            map.put("need_product", getString(needProduct));
        }
        map.put("type", type);
        map.put("sex", sexInPut);
        map.put("address", address);
        map.put("dist", regionInPut);
        map.put("address_id", location);
        map.put("main_product", getString(mainProduct));

        putAsyn(this, API.SAVE_SELFINFO, map, this, 3, true);

    }

    /**
     * 上传图片
     *
     * @param method
     * @param imgpath
     * @param type
     */
    public void upLoadImg(String method, String imgpath, int type) {
        String token = sharedUtils.getData(this, "token");
        postUpLoadImg(this, API.BASEURL + method, imgpath, token, this, type, null);
    }


    @Override
    public void callBack(Object object, int type) {
        try {
            String err = new JSONObject(object.toString()).getString("code");
            if (type == 1) {
                mySelfInfo = null;
                if ("0".equals(err)) {
                    Gson gson = new Gson();
                    mySelfInfo = gson.fromJson(object.toString(), MySelfInfo.class);
                    showInfo(mySelfInfo);
                }
            }
            if (type == 3 && "0".equals(err)) {
                requestNetData();
            }
        } catch (Exception e) {
        }
    }


    @Override
    public void failCallBack(int type, String message, int httpCode) {
        Log.e("----", "----");
    }

    public void showInfo(MySelfInfo mySelfInfo) {
        try {
            sex = mySelfInfo.getData().getSex();
            type = mySelfInfo.getData().getType();
            imgHead = mySelfInfo.getData().getThumb();
            address = mySelfInfo.getData().getAddress();
            location = mySelfInfo.getData().getOrigin();
            region = mySelfInfo.getData().getAdistinct();
            imgCard = mySelfInfo.getData().getThumbcard();
            monthUse = mySelfInfo.getData().getMonth_consum();
            mainProduct = mySelfInfo.getData().getMain_product();
            needProduct = mySelfInfo.getData().getNeed_product();
            //imgLicence = mySelfInfo.getData().getBusiness_licence_pic();

            sexInPut = ("男".equals(sex)) ? ("0") : ("1");
            imgCard = imgCard.startsWith("http") ? imgCard : "http:" + imgCard;
            imgHead = imgHead.startsWith("http") ? imgHead : "http:" + imgHead;
            //imgLicence = imgLicence.startsWith("http") ? imgLicence : imgLicence + "http:";

            textXb.setText(sex);
            tvAddress.setText(region);
            tvCareModel.setText(model);
            mName.setText(mySelfInfo.getData().getName());
            textGs.setText(mySelfInfo.getData().getC_name());
            tvPhone.setText(mySelfInfo.getData().getMobile());
            tvLocation.setText(address.replace("|", ""));
            tvNeedProduct.setText("1".equals(type) ? needProduct : mainProduct);

            llLicence.setVisibility(!"http:".equals(imgLicence) ? View.VISIBLE : View.GONE);
            Glide.with(this).load(imgLicence).into(imageLicence);

            Glide.with(this).load(imgCard).error(R.drawable.card).into(imageCard);

            Glide.with(this).load(imgHead).error("男".equals(sex)
                    ? R.drawable.img_defaul_male
                    : R.drawable.img_defaul_female).into(imageHead);

            switch (type) {
                case "1":
                    //llMode.setVisibility(View.VISIBLE);       //"‘关注的牌号’是否显示"
                    llMonthUse.setVisibility(View.VISIBLE); //"‘月用量与生产产品’是否显示"
                    tvCompany.setText("塑料制品厂");
                    _tvNeedProduct.setText("我的需求：");

                    tvProduct.setText(mainProduct);//生产产品
                    tvMonthUse.setText(monthUse);
                    break;
                case "2":
                    //llMode.setVisibility(View.VISIBLE);
                    tvCompany.setText("原料供应商");
                    llMonthUse.setVisibility(View.GONE);
                    break;
                case "4":
                    _tvNeedProduct.setText("我的主营：");
                    tvCompany.setText("物流商");
                    break;
                case "5":
                    tvCompany.setText("金融公司");
                    break;
                case "6":
                    tvCompany.setText("塑化电商");
                    break;
                case "7":
                    tvCompany.setText("回料(含新材料)");
                    break;
                case "8":
                    tvCompany.setText("期货");
                    break;
                case "9":
                    tvCompany.setText("塑机");
                default:
                    break;
            }
            //设置地区
            switch (region) {
                case "华东":
                    regionPosition = 0;
                    regionInPut = "EC";
                    break;
                case "华南":
                    regionPosition = 1;
                    regionInPut = "SC";
                    break;
                case "华北":
                    regionPosition = 2;
                    regionInPut = "NC";
                    break;
                case "其他":
                    regionPosition = 3;
                    regionInPut = "OT";
                    break;
                default:
                    break;
            }
        } catch (Exception e) {
        }
    }

    private String getString(String str) {
        if (!TextUtils.notEmpty(str)) {
            return "";
        }
        return str.replace("  ", ",")
                .replace(" ", ",")
                .replace("，", ",")
                .replace("|", ",")
                .replace("、", ",")
                .replace("/", ",")
                .replace("。", ",")
                .replace(".", ",");
    }

    //选择照片返回结果
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 100 && resultCode == 1) {
            String imagePath = data.getStringExtra("img_url");
            Glide.with(this).load(imagePath).into(imageHead);
            upLoadImg(API.SAVE_PIC_TO_SERVER, imagePath, 5);
        }
        if (requestCode == 200 && resultCode == 2) {
            String imagePath = data.getStringExtra("img_url");
            Glide.with(this).load(imagePath).into(imageCard);
            upLoadImg(API.SAVE_CARD_IMG, imagePath, 6);
        }
        if (requestCode == 6 && data != null) {
            if (!mainProduct.equals(data.getStringExtra("updateData"))) {
                mainProduct = data.getStringExtra("updateData");
                saveData();
            }
        }
        if (requestCode == 7 && data != null) {
            if (!monthUse.equals(data.getStringExtra("updateData"))) {
                monthUse = data.getStringExtra("updateData");
                saveData();
            }
        }
        if (requestCode == 8 && data != null) {
            if ("1".equals(type)) {
                needProduct = data.getStringExtra("updateData");
            } else {
                mainProduct = data.getStringExtra("updateData");
            }
            saveData();
        }
        if (requestCode == 9 && data != null) {
            if (!model.equals(data.getStringExtra("updateData"))) {
                model = data.getStringExtra("updateData");
                saveData();
            }
        }
    }

    public class MyReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals(ACTION)) {
                String type = intent.getStringExtra("type");
                String data = intent.getStringExtra("updateData");
                if ("0".equals(type)) {
                    sexInPut = data;
                    saveData();
                } else if ("1".equals(type)) {
                    regionInPut = data;
                    saveData();
                } else {
                    address = data;
                    location = intent.getStringExtra("location");
                    saveData();
                }
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (myReceiver != null) {
            unregisterReceiver(myReceiver);
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            eixt();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    private void eixt() {
        if ("0".equals(from)) {
            Intent intent = new Intent(this, MainActivity.class);
            intent.putExtra("type", Constant.REGISTER);
            startActivity(intent);
        } else {
            finish();
        }
    }
}
