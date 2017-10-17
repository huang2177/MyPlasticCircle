package com.myplas.q.sockethelper;


import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.myplas.q.common.api.API;
import com.myplas.q.common.appcontext.Constant;
import com.myplas.q.common.utils.ACache;
import com.myplas.q.common.utils.SharedUtils;
import com.myplas.q.guide.activity.BaseActivity;

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
    private static RabbitMQConfig mRabbitMQConfig;

    private RabbitMQConfig(Context context) {
        this.context = context;
        mACache = ACache.get(context);
    }

    public static RabbitMQConfig getInstance(Context context) {
        if (mRabbitMQConfig == null) {
            mRabbitMQConfig = new RabbitMQConfig(context.getApplicationContext());
            return mRabbitMQConfig;
        }
        return mRabbitMQConfig;
    }

    /*登陆以后获取rabbitMQ的配置信息*/
    public void getConfig() {
        BaseActivity.postAsyn1(context, API.BASEURL + API.INIT, null, this, 1, false);
    }

    /*关闭链接*/
    public void closed() {
        Map<String, String> map = new HashMap<>();
        map.put("userid", SharedUtils.getSharedUtils().getData(context, Constant.USERID));
        BaseActivity.postAsyn1(context, API.BASEURL + API.CLOSED, map, this, 2, false);
    }

    /*链接成功*/
    public void connected() {
        BaseActivity.postAsyn1(context, API.BASEURL + API.CONNECTED, null, this, 3, false);
    }

    @Override
    public void callBack(Object object, int type) {
        try {
            Gson gson = new Gson();
            String err = new JSONObject(object.toString()).getString("err");
            if (type == 1 && err.equals("0")) {
                _ConfigBean bean = gson.fromJson(object.toString(), _ConfigBean.class);
                mACache.put("config", bean);

                RabbitMQHelper mMQHelper = RabbitMQHelper.getInstance(context);
                mMQHelper.onConnect();
            }
        } catch (Exception e) {

        }
    }

    @Override
    public void failCallBack(int type) {

    }
}
