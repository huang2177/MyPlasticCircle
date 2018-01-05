package com.myplas.q.common.utils;

import android.content.Context;
import android.content.Intent;

import com.myplas.q.app.activity.BaseActivity;
import com.myplas.q.common.api.API;
import com.myplas.q.common.appcontext.Constant;
import com.myplas.q.common.netresquset.ResultCallBack;
import com.myplas.q.common.view.CommonDialog;
import com.myplas.q.contact.activity.ContactDetailActivity;
import com.myplas.q.contact.activity.NewContactDetailActivity;
import com.myplas.q.contact.beans.ContactBean;
import com.myplas.q.myself.integral.activity.IntegralPayActivtity;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * @author 黄双
 * @date 2018/1/4 0004
 */

public class AccessContactUtils extends BaseActivity implements ResultCallBack,
        CommonDialog.DialogShowInterface {

    private Context context;
    private ContactBean.PersonsBean personsBean;

    private AccessContactUtils(Context context) {
        this.context = context;
    }

    /**
     * 检查是否有权限查看详情
     *
     * @param userId
     */
    public void checkPremissions(ContactBean.PersonsBean personsBean) {
        this.personsBean = personsBean;
        Map<String, String> map = new HashMap(8);
        map.put("user_id", personsBean.getUser_id());
        getAsyn(context, API.PERMISSIONS, map, this, 1, false);
    }

    /**
     * 确定消耗积分
     *
     * @param userId
     * @param showtype
     * @param type
     */
    private void getPersonInfoData(String userId, String showtype, int type) {
        Map<String, String> map = new HashMap<String, String>(8);
        map.put("user_id", userId);
        map.put("showType", showtype);
        getAsyn(context, API.GET_ZONE_FRIEND, map, this, type, false);
    }

    @Override
    public void callBack(Object object, int type) {

    }

    @Override
    public void failCallBack(int type, String message, int httpCode) {
        try {
            JSONObject jsonObject = new JSONObject(message);
            String err = jsonObject.getString("code");
            switch (httpCode) {
                case 412:
                    //是否消耗积分
                    if (type == 2 && "99".equals(err)) {
                        String content = jsonObject.getString("message");
                        CommonDialog commonDialog = new CommonDialog();
                        commonDialog.showDialog(context, content, 1, this);
                    }

                    //已经消费了积分 或者 减积分成功
                    boolean b = type == 2 || type == 3;
                    if (b && "0".equals(err)) {
                        Intent intent = getIntent(personsBean.getMerge_three());
                        intent.putExtra("userid", personsBean.getUser_id());
                        startActivity(intent);
                    }
                    //积分不够
                    if (type == 3 && !"0".equals(err)) {
                        String content = jsonObject.getString("message");
                        CommonDialog commonDialog = new CommonDialog();
                        commonDialog.showDialog(context, content, ("100".equals(err)) ? (2) : (3), this);
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
        if (type == 3) {
            startActivity(new Intent(context, IntegralPayActivtity.class));
        }
    }

    /**
     * 判断是否跳转到店铺
     *
     * @param flag
     * @return
     */
    public Intent getIntent(String flag) {
        Intent intent = new Intent();
        intent.setClass(context, "1".equals(flag) ? NewContactDetailActivity.class : ContactDetailActivity.class);
        return intent;
    }
}
