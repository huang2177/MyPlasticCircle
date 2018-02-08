package com.myplas.q.homepage.activity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.myplas.q.R;
import com.myplas.q.app.activity.BaseActivity;
import com.myplas.q.app.activity.PreImageViewActivity;
import com.myplas.q.common.api.API;
import com.myplas.q.common.net.ResultCallBack;
import com.myplas.q.common.utils.TextUtils;
import com.myplas.q.common.view.RoundCornerImageView;
import com.myplas.q.homepage.beans.ContactInfoBean;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;


/**
 * 电话：15378412400
 * 邮箱：15378412400@163.com
 * 时间：2017/3/23 13:39
 *
 * @author 黄双
 */
public class TheirInfomationActivity extends BaseActivity implements View.OnClickListener
        , ResultCallBack {


    private View shareView;
    private RoundCornerImageView imageHead;
    private ImageView imageCard, imageLicence;
    private LinearLayout llMonthUse, llLicence;
    private TextView tvSex, tvLocation, tvCName, tvPhone, tvNeedProduct, tvMonthUse, tvProduct, tvAddress,
            tvCompany, needProductType, mName;

    private String imgCard, imgHead, imgLicence;
    private String sex, type, address, mainProduct, needProduct;

    private ContactInfoBean contactInfoBean;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_layout_seeting_data);
        initTileBar();
        setTitle("个人资料");

        initView();
        getPersonInfo();
    }

    public void initView() {

        tvCName = F(R.id.wd_zl_gs);
        tvPhone = F(R.id.wd_zl_tel);
        mName = F(R.id.wd_zl_name);
        imageCard = F(R.id.wd_zl_text_upload);
        imageHead = F(R.id.wd_zl_text_headimg);

        tvSex = F(R.id.wd_zl_text_xb);
        needProductType = F(R.id.textView8);
        tvAddress = F(R.id.wd_zl_text_address);
        tvCompany = F(R.id.wd_zl_text_company);
        tvProduct = F(R.id.wd_zl_text_products);
        tvMonthUse = F(R.id.wd_zl_text_monthuse);
        tvLocation = F(R.id.wd_zl_text_location);
        tvNeedProduct = F(R.id.wd_zl_text_needproduct);

        llLicence = F(R.id.ll_licence);
        imageLicence = F(R.id.wd_licence);
        llMonthUse = F(R.id.linear_show_close);

        imageHead.setOnClickListener(this);
        imageCard.setOnClickListener(this);
        imageLicence.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        if (contactInfoBean == null) {
            return;
        }
        switch (v.getId()) {
            case R.id.wd_zl_text_headimg:
                shareView = imageHead;
                preViewPic("1", imgHead);
                break;
            case R.id.wd_zl_text_upload:
                shareView = imageCard;
                preViewPic("2", imgCard);
                break;
            case R.id.wd_licence:
                shareView = imageLicence;
                preViewPic("5", imgLicence);
                break;
            default:
                break;
        }
    }

    /**
     * 从通讯录跳转过来 -- 请求数据
     */
    public void getPersonInfo() {
        Map<String, String> map = new HashMap<>(16);
        map.put("user_id", getIntent().getStringExtra("userid"));
        getAsyn(this, API.GET_ZONE_FRIEND, map, this, 1);
    }

    /**
     * 放大图片
     *
     * @param type   使相机还是相册
     * @param imgurl 图片url
     */

    private void preViewPic(String type, String imgurl) {
        Intent intent1 = new Intent(this, PreImageViewActivity.class);
        intent1.putExtra("imgurl", imgurl);
        intent1.putExtra("type", "1".equals(type) ? sex : type);
        startActivityByTras(intent1);
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


    @Override
    public void callBack(Object object, int type) {
        try {
            JSONObject jsonObject = new JSONObject(object.toString());
            String err = jsonObject.getString("code");
            if (type == 1) {
                if ("0".equals(err)) {
                    Gson gson = new Gson();
                    contactInfoBean = gson.fromJson(object.toString(), ContactInfoBean.class);
                    showInfo(contactInfoBean.getData());
                }
            }
        } catch (Exception e) {
        }
    }


    @Override
    public void failCallBack(int type, String message, int httpCode) {
        try {
            if (httpCode == 404) {
                String msg = new JSONObject(message).getString("message");
                TextUtils.toast(this, msg);
            }
        } catch (Exception e) {
        }
    }

    public void showInfo(ContactInfoBean.DataBean dataBean) {
        try {
            type = dataBean.getType();
            imgHead = dataBean.getThumb();
            address = dataBean.getAddress();
            imgCard = dataBean.getThumbcard();
            mainProduct = dataBean.getMain_product();
            needProduct = dataBean.getNeed_product();
            imgLicence = dataBean.getBusiness_licence_pic();

            sex = ("0".equals(dataBean.getSex())) ? ("男") : ("女");
            imgCard = imgCard.startsWith("http") ? imgCard : "http:" + imgCard;
            imgHead = imgHead.startsWith("http") ? imgHead : "http:" + imgHead;
            imgLicence = imgLicence.startsWith("http") ? imgLicence : imgLicence + "http:";

            tvSex.setText(sex);
            mName.setText(dataBean.getName());
            tvCName.setText(dataBean.getC_name());
            tvPhone.setText(dataBean.getMobile());
            tvAddress.setText(dataBean.getChina_area());
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
                    llMonthUse.setVisibility(View.VISIBLE); //"‘月用量与生产产品’是否显示"
                    tvCompany.setText("塑料制品厂");
                    needProductType.setText("我的需求：");

                    tvProduct.setText(mainProduct);//生产产品
                    tvMonthUse.setText(dataBean.getMonth_consum());
                    break;
                case "2":
                    tvCompany.setText("原料供应商");
                    llMonthUse.setVisibility(View.GONE);
                    break;
                case "4":
                    needProductType.setText("我的主营：");
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
        } catch (Exception e) {
        }
    }
}
