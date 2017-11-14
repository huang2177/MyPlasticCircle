package com.myplas.q.jpush;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.google.gson.Gson;
import com.myplas.q.common.appcontext.ActivityManager;
import com.myplas.q.common.appcontext.Constant;
import com.myplas.q.common.utils.ACache;
import com.myplas.q.common.utils.TextUtils;
import com.myplas.q.guide.activity.MainActivity;
import com.myplas.q.myself.fans.activity.MyFansFollowActivity;
import com.myplas.q.myself.invoices.activity.TradeOrderActivity;
import com.myplas.q.sockethelper.DefConfigBean;
import com.myplas.q.supdem.activity.SupDem_Detail_Activity;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Iterator;

import cn.jpush.android.api.JPushInterface;

/**
 * 自定义接收器
 * <p>
 * 如果不定义这个 Receiver，则：
 * 1) 默认用户会打开主界面
 * 2) 接收不到自定义消息
 *
 * @author 黄双
 */
public class MyReceiver extends BroadcastReceiver {
    private static final String TAG = "JIGUANG-Example";

    @Override
    public void onReceive(Context context, Intent intent) {
        try {
            Bundle bundle = intent.getExtras();
            Log.d(TAG, "MyReceiver- - -extras: - - -" + getExtra(bundle, JPushInterface.EXTRA_EXTRA));
            Log.d(TAG, "MyReceiver- - -message: - - -" + getExtra(bundle, JPushInterface.EXTRA_MESSAGE));

            if (JPushInterface.ACTION_NOTIFICATION_OPENED.equals(intent.getAction())) {
                openNotifycation(context
                        , getExtra(bundle, JPushInterface.EXTRA_EXTRA)
                        , getExtra(bundle, JPushInterface.EXTRA_MESSAGE));
            }
        } catch (Exception e) {
        }
    }

    /**
     * 用户点击通知后打开页面
     *
     * @param context
     */
    private void openNotifycation(Context context, JSONObject extras, JSONObject message) {
        if (extras == null) {
            return;
        }
        try {
            switch (extras.getString("jump_target")) {
                case "reply"://回复
                    Bundle bundle = new Bundle();
                    bundle.putInt("position", 1);
                    Intent i = new Intent(context, SupDem_Detail_Activity.class);
                    i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    i.putExtra("id", extras.getString("id"));
                    i.putExtra("userid", extras.getString("rev_id"));
                    i.putExtra("bundle", bundle);
                    context.startActivity(i);
                    break;
                case "price"://出价
                    Intent i1 = new Intent(context, SupDem_Detail_Activity.class);
                    i1.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    i1.putExtra("id", extras.getString("pur_id"));
                    i1.putExtra("userid", extras.getString("rev_id"));
                    context.startActivity(i1);
                    break;

                case "purchase"://发布供求
                    Intent i2 = new Intent(context, SupDem_Detail_Activity.class);
                    i2.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    i2.putExtra("id", extras.getString("pur_id"));
                    i2.putExtra("userid", extras.getString("rev_id"));
                    context.startActivity(i2);
                    break;
                case "focuse"://关注
                    Intent i3 = new Intent(context, MyFansFollowActivity.class);
                    i3.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    i3.putExtra("type", "1");
                    context.startActivity(i3);
                    break;
                case ""://开票、签收
                    Intent i4 = new Intent(context, TradeOrderActivity.class);
                    i4.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    context.startActivity(i4);
                    break;
                default:
                    break;
            }
            if (message != null) {
                Gson gson = new Gson();
                ACache mACache = ACache.get(context);
                DefConfigBean bean = gson.fromJson(message.toString(), DefConfigBean.class);
                mACache.put(Constant.R_MYORDER, bean.getRedDot().getUnread_myorder());
                mACache.put(Constant.R_SEEME, bean.getRedDot().getUnread_who_saw_me());
                mACache.put(Constant.R_CONTACT, bean.getRedDot().getUnread_customer());
                mACache.put(Constant.R_PUR_MSG, bean.getRedDot().getUnread_plastic_msg());
                mACache.put(Constant.R_SUPDEM_MSG, bean.getRedDot().getUnread_purchase_msg());
                mACache.put(Constant.R_INTER_MSG, bean.getRedDot().getUnread_reply_user_msg());
                mACache.put(Constant.R_SUPDEM, bean.getRedDot().getUnread_supply_and_demand());
                mACache.put(Constant.R_REPLY_MSG, bean.getRedDot().getUnread_reply_purchase_msg());

                Activity activity = ActivityManager.currentActivity();
                if (activity != null && activity instanceof MainActivity) {
                    MainActivity mainActivity = (MainActivity) activity;
                    mainActivity.rCallback(true);
                }
            }
        } catch (Exception e) {

        }
    }

    /**
     * 获取extra/message字段
     *
     * @param bundle
     * @return
     */
    private static JSONObject getExtra(Bundle bundle, String key) {
        JSONObject jsonObject = null;

        if (TextUtils.isEmpty(bundle.getString(key))) {
            Log.e(TAG, "This message has no Extra data");
            return jsonObject;
        }
        try {
            jsonObject = new JSONObject(bundle.getString(key));

        } catch (Exception e) {
            Log.e(TAG, "Get message extra JSON error!");
        }

        return jsonObject;
    }
}
