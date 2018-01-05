package com.myplas.q.sockethelper;


import android.content.Context;

import com.google.gson.Gson;
import com.myplas.q.common.api.API;
import com.myplas.q.common.appcontext.Constant;
import com.myplas.q.common.utils.ACache;
import com.myplas.q.common.utils.SharedUtils;
import com.myplas.q.app.activity.BaseActivity;

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
        BaseActivity.postAsyn(context, API.BASEURL + API.CLOSED, map, this, 2, false);
    }

    /**
     * Connected.
     */

    public void connected() {
        BaseActivity.postAsyn(context, API.BASEURL + API.CONNECTED, null, this, 3, false);
    }

    /**
     * Read msg.
     *
     * @param type the type
     */
    public void getRedDotInfo() {
        BaseActivity.postAsyn(context, API.BASEURL + API.GETREDDOTINFO, null, this, 4, false);
    }

    /**
     * Read msg.
     *
     * @param type the type
     */
    public void readMsg(String type, int _type) {
        Map<String, String> map = new HashMap<String, String>();
        map.put("type", type);
        BaseActivity.postAsyn(context, API.BASEURL + API.READ, map, this, _type, false);
    }

    @Override
    public void callBack(Object object, int type) {
        try {
            if (type == 2 || type == 3) {
                return;
            }
            mACache = ACache.get(context);
            boolean isShowNotify = false;
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
                    isShowNotify = true;
                    JSONObject jsonObject = new JSONObject(object.toString());
                    if ("0".equals(jsonObject.getString("err"))) {
                        setNoticeBean(object);
                    }
                    break;
                default:
                    break;
            }
            for (int i = 0; i < mqCallBackList.size(); i++) {
                RabbitMQCallBack mMQCallBack = mqCallBackList.get(i);
                if (mMQCallBack != null) {
                    mMQCallBack.rCallback(true, isShowNotify);
                }
            }
        } catch (Exception e) {
        }
    }

    private void setNoticeBean(Object object) {
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


        DefConfigBean.NoticeBean noticeBean = new DefConfigBean.NoticeBean();
        List<DefConfigBean.NoticeBean.CommunicateContentBean> list1 = new ArrayList<>();
        List<DefConfigBean.NoticeBean.ToutiaoContentBean> list2 = new ArrayList<>();
        List<DefConfigBean.NoticeBean.PurchaseContentBean> list3 = new ArrayList<>();
        for (int i = 0; i < dotBean.getNotice().getCommunicate_content().size(); i++) {
            DefConfigBean.NoticeBean.CommunicateContentBean communicateBean = new DefConfigBean.NoticeBean.CommunicateContentBean();
            communicateBean.setInfo(dotBean.getNotice().getCommunicate_content().get(i).getInfo());
            communicateBean.setId(dotBean.getNotice().getCommunicate_content().get(i).getId());
            communicateBean.setMerge_three(dotBean.getNotice().getCommunicate_content().get(i).getMerge_three());
            list1.add(communicateBean);
        }
        for (int i = 0; i < dotBean.getNotice().getToutiao_content().size(); i++) {
            DefConfigBean.NoticeBean.ToutiaoContentBean toutiaoBean = new DefConfigBean.NoticeBean.ToutiaoContentBean();
            toutiaoBean.setInfo(dotBean.getNotice().getToutiao_content().get(i).getInfo());
            toutiaoBean.setId(dotBean.getNotice().getToutiao_content().get(i).getId());
            toutiaoBean.setFree(dotBean.getNotice().getToutiao_content().get(i).getFree());
            list2.add(toutiaoBean);
        }
        for (int i = 0; i < dotBean.getNotice().getPurchase_content().size(); i++) {
            DefConfigBean.NoticeBean.PurchaseContentBean purchaseBean = new DefConfigBean.NoticeBean.PurchaseContentBean();
            purchaseBean.setInfo(dotBean.getNotice().getPurchase_content().get(i).getInfo());
            purchaseBean.setId(dotBean.getNotice().getPurchase_content().get(i).getId());
            purchaseBean.setUser_id(dotBean.getNotice().getPurchase_content().get(i).getUser_id());
            list3.add(purchaseBean);
        }
        noticeBean.setCommunicate_content(list1);
        noticeBean.setToutiao_content(list2);
        noticeBean.setPurchase_content(list3);
        mACache.put(Constant.R_MARQUEE_CONTENT, noticeBean);
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
