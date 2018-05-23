package com.myplas.q.myself.vip;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;

import com.google.gson.Gson;
import com.myplas.q.R;
import com.myplas.q.app.activity.BaseActivity;
import com.myplas.q.common.api.API;
import com.myplas.q.common.appcontext.Constant;
import com.myplas.q.common.net.ResultCallBack;
import com.myplas.q.common.view.CommonDialog;
import com.myplas.q.myself.beans.Member;
import com.myplas.q.myself.beans.MyZone;
import com.myplas.q.myself.store.MyStoreActivity;

import org.json.JSONObject;

/**
 * @author Huangshuang  2018/4/27 0027
 */

public class UnEstablishedVipActivity extends BaseActivity implements View.OnClickListener, ResultCallBack, CommonDialog.DialogShowInterface {

    private ImageView ivNews, ivStore, ivTried;

    private boolean isHeadVip, isStoreVip, isTrialVip;
    private Member member;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_layout_vip_unestablished);
        initTileBar();
        setTitle("塑料圈会员");

        initView();
        memberStatus();
    }

    private void initView() {
        ivNews = F(R.id.img_vip_news);
        ivStore = F(R.id.img_vip_store);
        ivTried = F(R.id.img_vip_ontrial);

        ivNews.setOnClickListener(this);
        ivStore.setOnClickListener(this);
        ivTried.setOnClickListener(this);
    }

    /**
     * 开通店铺
     */
    public void memberStatus() {
        getAsyn(this, API.MEMBERSHIP, null, this, 1, true);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.img_vip_news:
                openDialog();
                break;
            case R.id.img_vip_store:
                //未提交资料
                if (TextUtils.equals(member.getData().getStatus(), "0")) {
                    Intent intent = new Intent(this, MyStoreActivity.class);
                    intent.putExtra(Constant.STAUTS, "1");
                    startActivity(intent);
                    finish();
                }
                //审核中
                else if (TextUtils.equals(member.getData().getStatus(), "2")) {
                    Intent intent = new Intent(this, MyStoreActivity.class);
                    intent.putExtra(Constant.STAUTS, "2");
                    startActivity(intent);
                    finish();
                }
                //过期
                else if (TextUtils.equals(member.getData().getStatus(), "4")
                        || TextUtils.equals(member.getData().getStatus(), "4")
                        && TextUtils.equals(member.getData().getCustomerVip(), "0")) {
                    openDialog();
                }
                // 已提交资料
                else if (TextUtils.equals(member.getData().getStatus(), "1")
                        && TextUtils.equals(member.getData().getCustomerVip(), "0")) {
                    openDialog();
                }
                break;
            default:
                break;
        }
    }

    private void openDialog() {
        CommonDialog dialog = new CommonDialog();
        dialog.showDialog(this, "您好，开通会员请拨打0553-7859005", 100, this);
    }

    @Override
    public void callBack(Object object, int type) {
        try {
            JSONObject jsonObject = new JSONObject(object.toString());
            String code = jsonObject.getString("code");
            if ("0".equals(code)) {
                member = new Gson().fromJson(object.toString(), Member.class);
                isVip(member.getData());
                showBtnResByVipType();
            }
        } catch (Exception e) {

        }
    }

    private void isVip(Member.DataBean dataBean) {
        isHeadVip = TextUtils.equals("1", dataBean.getHeadingVip());
        isStoreVip = TextUtils.equals("1", dataBean.getCustomerVip());
    }

    /**
     * 根据已开通Vip类型显示button样式
     */
    private void showBtnResByVipType() {

        if (isHeadVip) {
            ivNews.setClickable(false);
            ivTried.setClickable(false);
            ivTried.setImageResource(R.drawable.btn_rightnow);
            ivNews.setImageResource(R.drawable.btn_open_disabled);
        }

        if (isStoreVip) {
            ivNews.setClickable(false);
            ivTried.setClickable(false);
            ivStore.setClickable(false);
            ivNews.setImageResource(R.drawable.btn_rightnow);
            ivTried.setImageResource(R.drawable.btn_rightnow);
            ivStore.setImageResource(R.drawable.btn_open_disabled);
        }
    }

    @Override
    public void failCallBack(int type, String message, int httpCode) {

    }

    @Override
    public void dialogClick(int type) {
        if (type == 100) {
            call("05537859005");
        }
    }
}
