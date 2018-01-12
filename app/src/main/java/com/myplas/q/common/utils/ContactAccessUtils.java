package com.myplas.q.common.utils;

import android.content.Context;
import android.content.Intent;

import com.myplas.q.app.activity.BaseActivity;
import com.myplas.q.common.api.API;
import com.myplas.q.common.netresquset.ResultCallBack;
import com.myplas.q.common.view.CommonDialog;
import com.myplas.q.homepage.activity.ContactDetailActivity;
import com.myplas.q.homepage.activity.NewContactDetailActivity;
import com.myplas.q.homepage.beans.ContactBean;
import com.myplas.q.myself.integral.activity.IntegralPayActivtity;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * @author 黄双
 * @date 2018/1/4 0004
 */

public class ContactAccessUtils extends BaseActivity implements ResultCallBack,
        CommonDialog.DialogShowInterface {

    private Context context;
    private String userId, isShop;

    public ContactAccessUtils(Context context) {
        this.context = context;
    }

    /**
     * 检查是否有权限查看详情
     *
     * @param userId
     */
    public void checkPremissions(ContactBean.PersonsBean personsBean) {
        if (personsBean == null) {
            return;
        }
        checkPremissions(personsBean.getUser_id(), personsBean.getIsshop());
    }

    public void checkPremissions(String userId, String isShop) {
        if (!TextUtils.notEmpty(userId)) {
            return;
        }
        this.userId = userId;
        this.isShop = isShop;
        Map<String, String> map = new HashMap(8);
        map.put("user_id", userId);
        getAsyn(context, API.PERMISSIONS, map, this, 1, false);
    }

    /**
     * 确定消耗积分
     *
     * @param userId
     */
    private void getPersonInfoData() {
        Map<String, String> map = new HashMap<String, String>(8);
        map.put("user_id", userId);
        map.put("permission", "1");
        getAsyn(context, API.GET_ZONE_FRIEND, map, this, 2, false);
    }

    @Override
    public void callBack(Object object, int type) {
        try {
            JSONObject jsonObject = new JSONObject(object.toString());
            switch (type) {
                case 1:
                    if ("0".equals(jsonObject.getString("code"))) {
                        if (jsonObject.getBoolean("permission")) {
                            Intent intent = getIntent(isShop);
                            intent.putExtra("userid", userId);
                            context.startActivity(intent);
                        } else {
                            String content = jsonObject.getString("message");
                            CommonDialog commonDialog = new CommonDialog();
                            commonDialog.showDialog(context, content, 1, this);
                        }
                    }
                    break;
                case 2:
                    if ("0".equals(jsonObject.getString("code"))) {
                        Intent intent = getIntent(isShop);
                        intent.putExtra("userid", userId);
                        context.startActivity(intent);
                    }
                    break;
                default:
                    break;
            }

        } catch (Exception e) {

        }
    }

    @Override
    public void failCallBack(int type, String message, int httpCode) {
        try {
            JSONObject jsonObject = new JSONObject(message);
            String err = jsonObject.getString("code");
            switch (httpCode) {
                case 412:
                    //是否消耗积分
                    if (type == 2 && "100".equals(err)) {
                        String content = jsonObject.getString("message");
                        CommonDialog commonDialog = new CommonDialog();
                        commonDialog.showDialog(context, content, 3, this);
                    }
                    break;
                default:
                    break;
            }
        } catch (Exception e) {

        }
    }

    @Override
    public void dialogClick(int type) {
        if (type == 1) {
            getPersonInfoData();
        } else if (type == 3) {
            context.startActivity(new Intent(context, IntegralPayActivtity.class));
        }
    }

    /**
     * 判断是否跳转到店铺
     *
     * @param flag
     * @return
     */
    private Intent getIntent(String flag) {
        Intent intent = new Intent();
        intent.setClass(context, "1".equals(flag) ? NewContactDetailActivity.class : ContactDetailActivity.class);
        return intent;
    }
}
