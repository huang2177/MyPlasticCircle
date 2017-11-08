package com.myplas.q.sockethelper;


import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.myplas.q.common.api.API;
import com.myplas.q.common.appcontext.ActivityManager;
import com.myplas.q.common.appcontext.Constant;
import com.myplas.q.common.utils.ACache;
import com.myplas.q.common.utils.SharedUtils;
import com.myplas.q.guide.activity.BaseActivity;
import com.myplas.q.guide.activity.MainActivity;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 作者:huangshuang
 * 事件 2017/10/16 0016.
 * 邮箱： 15378412400@163.com
 */
public class RabbitMQConfig implements com.myplas.q.common.netresquset.ResultCallBack {

    private ACache mACache;
    private Context context;
    private static RabbitMQConfig mRabbitMQConfig;

    private static List<RabbitMQCallBack> mqCallBackList;

    private RabbitMQConfig(Context context) {
        this.context = context;
    }

    /**
     * Gets instance.
     *
     * @param context the context
     * @return the instance
     */
    public static RabbitMQConfig getInstance(Context context) {
        if (mRabbitMQConfig == null) {
            mqCallBackList = new ArrayList<>();
            mRabbitMQConfig = new RabbitMQConfig(context.getApplicationContext());
            return mRabbitMQConfig;
        }
        return mRabbitMQConfig;
    }

    /**
     * Closed.
     */

    public void closed() {
        Map<String, String> map = new HashMap<>();
        map.put("userid", SharedUtils.getSharedUtils().getData(context, Constant.USERID));
        BaseActivity.postAsyn1(context, API.BASEURL + API.CLOSED, map, this, 2, false);
    }

    /**
     * Connected.
     */

    public void connected() {
        BaseActivity.postAsyn1(context, API.BASEURL + API.CONNECTED, null, this, 3, false);
    }

    /**
     * Read msg.
     *
     * @param type the type
     */
    public void getRedDotInfo() {
        BaseActivity.postAsyn1(context, API.BASEURL + API.GETREDDOTINFO, null, this, 4, false);
    }

    /**
     * Read msg.
     *
     * @param type the type
     */
    public void readMsg(String type, int _type) {
        Map<String, String> map = new HashMap<String, String>();
        map.put("type", type);
        BaseActivity.postAsyn1(context, API.BASEURL + API.READ, map, this, _type, false);
    }

    @Override
    public void callBack(Object object, int type) {
        try {
            if (type == 2 || type == 3) {
                return;
            }
            mACache = ACache.get(context);
            switch (type) {
                case 10:
                    mACache.put(Constant.R_CONTACT, "0");
                    break;
                case 11:
                    mACache.put(Constant.R_SUPDEM, "0");
                    break;
                case 13:
                    mACache.put(Constant.R_MYORDER, "0");
                    break;
                case 14:
                    mACache.put(Constant.R_SEEME, "0");
                    break;
                case 15:
                    mACache.put(Constant.R_SUPDEM_MSG, "0");
                    break;
                case 16:
                    mACache.put(Constant.R_PUR_MSG, "0");
                    break;
                case 17:
                    mACache.put(Constant.R_REPLY_MSG, "0");
                    break;
                case 18:
                    mACache.put(Constant.R_INTER_MSG, "0");
                    break;
                case 4:
                    if ("0".equals(new JSONObject(object.toString()).getString("err"))) {
                        Gson gson = new Gson();
                        DotBean dotBean = gson.fromJson(object.toString(), DotBean.class);
                        mACache.put(Constant.R_MYORDER, dotBean.getData().getUnread_myorder());
                        mACache.put(Constant.R_SEEME, dotBean.getData().getUnread_who_saw_me());
                        mACache.put(Constant.R_CONTACT, dotBean.getData().getUnread_customer());
                        mACache.put(Constant.R_PUR_MSG, dotBean.getData().getUnread_plastic_msg());
                        mACache.put(Constant.R_SUPDEM_MSG, dotBean.getData().getUnread_purchase_msg());
                        mACache.put(Constant.R_INTER_MSG, dotBean.getData().getUnread_reply_user_msg());
                        mACache.put(Constant.R_SUPDEM, dotBean.getData().getUnread_supply_and_demand());
                        mACache.put(Constant.R_REPLY_MSG, dotBean.getData().getUnread_reply_purchase_msg());
                    }
                    break;
                default:
                    break;
            }
            for (int i = 0; i < mqCallBackList.size(); i++) {
                RabbitMQCallBack mMQCallBack = mqCallBackList.get(i);
                if (mMQCallBack != null) {
                    mMQCallBack.rCallback(true);
                }
            }
        } catch (Exception e) {
        }
    }

    @Override
    public void failCallBack(int type) {

    }

    /**
     * Sets result callback.
     *
     * @param mMQCallBack 设置监听
     */
    public void setResultCallBack(RabbitMQCallBack mMQCallBack) {
        if (!mqCallBackList.contains(mMQCallBack)) {
            mqCallBackList.add(mMQCallBack);
        }
    }
}
