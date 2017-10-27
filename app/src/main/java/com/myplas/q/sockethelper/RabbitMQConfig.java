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

import java.util.HashMap;
import java.util.Map;

/**
 * 作者:huangshuang
 * 事件 2017/10/16 0016.
 * 邮箱： 15378412400@163.com
 */
public class RabbitMQConfig implements com.myplas.q.common.netresquset.ResultCallBack {

    private ACache mACache;
    private Context context;
    private MainActivity mActivity;
    private static RabbitMQConfig mRabbitMQConfig;

    private int CONTACT = 11, SUPDEM = 12, SEEME = 13, MYMSG = 14, MYORDER = 15;


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
    public void readMsg(String type, int _type) {
        Map<String, String> map = new HashMap<String, String>();
        map.put("type", type);
        BaseActivity.postAsyn1(context, API.BASEURL + API.READ, map, this, _type, false);
    }

    @Override
    public void callBack(Object object, int type) {
        try {
            mACache = ACache.get(context);
            mActivity = (MainActivity) ActivityManager.getActivity(MainActivity.class);
            switch (type) {
                case 10:
                    mACache.put(Constant.R_CONTACT, "0");
                    break;
                case 11:
                    mACache.put(Constant.R_SUPDEM, "0");
                    break;
                case 12:
                    mACache.put(Constant.R_MYMSG, "0");
                    break;
                case 13:
                    mACache.put(Constant.R_MYORDER, "0");
                    break;
                case 14:
                    mACache.put(Constant.R_SEEME, "0");
                    break;
                default:
                    break;
            }
            mActivity.rCallback(true);
        } catch (Exception e) {
        }
    }

    @Override
    public void failCallBack(int type) {

    }
}
