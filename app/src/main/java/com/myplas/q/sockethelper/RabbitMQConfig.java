package com.myplas.q.sockethelper;


import android.content.Context;
import android.util.Log;

import com.myplas.q.app.activity.BaseActivity;
import com.myplas.q.common.api.API;
import com.myplas.q.common.appcontext.Constant;
import com.myplas.q.common.utils.SharedUtils;

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
public class RabbitMQConfig implements com.myplas.q.common.net.ResultCallBack {

    private Context context;
    private SharedUtils sharedUtils;
    private static RabbitMQConfig mRabbitMQConfig;

    private static List<RabbitMQCallBack> mqCallBackList;

    private RabbitMQConfig(Context context) {
        this.context = context;
        mqCallBackList = new ArrayList<>();
        sharedUtils = SharedUtils.getSharedUtils();
    }

    /**
     * Gets instance.
     *
     * @param context the context
     * @return the instance
     */
    public static RabbitMQConfig getInstance(Context context) {
        if (mRabbitMQConfig == null) {
            mRabbitMQConfig = new RabbitMQConfig(context);
            return mRabbitMQConfig;
        }
        return mRabbitMQConfig;
    }

    /**
     * Closed.
     */

    public void closed() {
        Map<String, String> map = new HashMap<>(8);
        map.put("userid", SharedUtils.getSharedUtils().getData(context, Constant.USERID));
        BaseActivity.postAsyn(context, API.CLOSED, map, this, 2, false);
    }

    /**
     * 连接成功后回调服务器
     */

    public void connected() {
        BaseActivity.putAsyn(context, API.CONNECTED, null, this, 3, false);
    }

    /**
     * Read msg.
     *
     * @param type the type
     */
    public void readMsg(String type, int _type) {
        Map<String, String> map = new HashMap<String, String>(16);
        map.put("type", type);
        BaseActivity.getAsyn(context, API.CONNECTED, map, this, _type, false);
    }

    @Override
    public void callBack(Object object, int type) {
        try {
            if (type == 2 || type == 3) {
                return;
            }
            switch (type) {
                case 10:
                    sharedUtils.setInt(context, Constant.R_CONTACT, 0);
                    break;
                case 11:
                    sharedUtils.setInt(context, Constant.R_SUPDEM, 0);
                    break;
                case 13:
                    sharedUtils.setInt(context, Constant.R_MYORDER, 0);
                    break;
                case 14:
                    sharedUtils.setInt(context, Constant.R_SEEME, 0);
                    break;
                case 15:
                    sharedUtils.setInt(context, Constant.R_SUPDEM_MSG, 0);
                    break;
                case 16:
                    sharedUtils.setInt(context, Constant.R_PUR_MSG, 0);
                    break;
                case 17:
                    sharedUtils.setInt(context, Constant.R_REPLY_MSG, 0);
                    break;
                case 18:
                    sharedUtils.setInt(context, Constant.R_INTER_MSG, 0);
                    break;
                default:
                    break;

            }

            changeRedDots();

        } catch (Exception e) {
        }
    }

    /**
     * 改变红点消息
     */
    public void changeRedDots(String msg) {
        try {
            Log.e("----->RabbitMQ", msg);
            JSONObject jsonObject = new JSONObject(msg);
            JSONObject data = jsonObject.getJSONObject("data");
            if (data.has("key")) {
                switch (data.getString("key")) {
                    case "unread_who_saw_me":
                        sharedUtils.setInt(context, Constant.R_SEEME, data.getInt("value"));
                        break;
                    case "unread_myorder":
                        sharedUtils.setInt(context, Constant.R_MYORDER, data.getInt("value"));
                        break;
                    case "unread_plastic_msg":
                        sharedUtils.setInt(context, Constant.R_PUR_MSG, data.getInt("value"));
                        break;
                    case "unread_reply_user_msg":
                        sharedUtils.setInt(context, Constant.R_INTER_MSG, data.getInt("value"));
                        break;
                    case "unread_reply_purchase_msg":
                        sharedUtils.setInt(context, Constant.R_REPLY_MSG, data.getInt("value"));
                        break;
                    case "unread_purchase_msg":
                        sharedUtils.setInt(context, Constant.R_SUPDEM_MSG, data.getInt("value"));
                        break;
                    default:
                        break;
                }
            } else {
                switch (data.getString("info")) {
                    case "unread_customer":
                        sharedUtils.setInt(context, Constant.R_CONTACT, 1);
                        break;
                    case "unread_supply_and_demand":
                        sharedUtils.setInt(context
                                , Constant.R_SUPDEM
                                , sharedUtils.getInt(context, Constant.R_SUPDEM) + 1);
                        break;
                    default:
                        break;
                }
            }
        } catch (Exception e) {
            return;
        }
        changeRedDots();
    }

    /**
     * 改变红点消息
     */
    public void changeRedDots() {
        boolean isShowNotify = false;
        for (int i = 0; i < mqCallBackList.size(); i++) {
            RabbitMQCallBack mMQCallBack = mqCallBackList.get(i);
            if (mMQCallBack != null) {
                mMQCallBack.rCallback(true, isShowNotify);
            }
        }

//        Gson gson = new Gson();
//        DotBean dotBean = gson.fromJson(object.toString(), DotBean.class);
//        sharedUtils.setInt((Constant.R_MYORDER, dotBean.getData().getUnread_myorder());
//        sharedUtils.setInt((Constant.R_SEEME, dotBean.getData().getUnread_who_saw_me());
//        sharedUtils.setInt((Constant.R_CONTACT, dotBean.getData().getUnread_customer());
//        sharedUtils.setInt((Constant.R_PUR_MSG, dotBean.getData().getUnread_plastic_msg());
//        sharedUtils.setInt((Constant.R_SUPDEM_MSG, dotBean.getData().getUnread_purchase_msg());
//        sharedUtils.setInt((Constant.R_INTER_MSG, dotBean.getData().getUnread_reply_user_msg());
//        sharedUtils.setInt((Constant.R_SUPDEM, dotBean.getData().getUnread_supply_and_demand());
//        sharedUtils.setInt((Constant.R_REPLY_MSG, dotBean.getData().getUnread_reply_purchase_msg());
//
//        DefConfigBean.NoticeBean noticeBean = new DefConfigBean.NoticeBean();
//        List<DefConfigBean.NoticeBean.CommunicateContentBean> list1 = new ArrayList<>();
//        List<DefConfigBean.NoticeBean.ToutiaoContentBean> list2 = new ArrayList<>();
//        List<DefConfigBean.NoticeBean.PurchaseContentBean> list3 = new ArrayList<>();
//        for (int i = 0; i < dotBean.getNotice().getCommunicate_content().size(); i++) {
//            DefConfigBean.NoticeBean.CommunicateContentBean communicateBean = new DefConfigBean.NoticeBean.CommunicateContentBean();
//            communicateBean.setInfo(dotBean.getNotice().getCommunicate_content().get(i).getInfo());
//            communicateBean.setId(dotBean.getNotice().getCommunicate_content().get(i).getId());
//            communicateBean.setMerge_three(dotBean.getNotice().getCommunicate_content().get(i).getMerge_three());
//            list1.add(communicateBean);
//        }
//        for (int i = 0; i < dotBean.getNotice().getToutiao_content().size(); i++) {
//            DefConfigBean.NoticeBean.ToutiaoContentBean toutiaoBean = new DefConfigBean.NoticeBean.ToutiaoContentBean();
//            toutiaoBean.setInfo(dotBean.getNotice().getToutiao_content().get(i).getInfo());
//            toutiaoBean.setId(dotBean.getNotice().getToutiao_content().get(i).getId());
//            toutiaoBean.setFree(dotBean.getNotice().getToutiao_content().get(i).getFree());
//            list2.add(toutiaoBean);
//        }
//        for (int i = 0; i < dotBean.getNotice().getPurchase_content().size(); i++) {
//            DefConfigBean.NoticeBean.PurchaseContentBean purchaseBean = new DefConfigBean.NoticeBean.PurchaseContentBean();
//            purchaseBean.setInfo(dotBean.getNotice().getPurchase_content().get(i).getInfo());
//            purchaseBean.setId(dotBean.getNotice().getPurchase_content().get(i).getId());
//            purchaseBean.setUser_id(dotBean.getNotice().getPurchase_content().get(i).getUser_id());
//            list3.add(purchaseBean);
//        }
//        noticeBean.setCommunicate_content(list1);
//        noticeBean.setToutiao_content(list2);
//        noticeBean.setPurchase_content(list3);
//        sharedUtils.setInt((Constant.R_MARQUEE_CONTENT, noticeBean);
    }

    @Override
    public void failCallBack(int type, String message, int httpCode) {

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
