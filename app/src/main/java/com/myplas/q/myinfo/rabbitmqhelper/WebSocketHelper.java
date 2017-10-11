package com.myplas.q.myinfo.rabbitmqhelper;


/**
 * 作者：  黄双
 * 事件 2017/8/15 0015.
 * 邮箱： 15378412400@163.com
 */

public class WebSocketHelper {
//    private WebSocketCallBack mCallBack;
//    private WebSocketConnection mConnect;
//    private String websocketHost = "ws://139.196.205.19:2121";
//    private String data;
//
//    public WebSocketHelper(WebSocketCallBack mCallBack) {
//        this.mCallBack = mCallBack;
//    }
//
//    public void startConnect(final Context context) {
//        try {
//            mConnect = new WebSocketConnection();
//            mConnect.connect(websocketHost, new WebSocketHandler() {
//                @Override
//                public void onOpen() {
//                    String userid = SharedUtils.getSharedUtils().getData(context, "userid");
//                    JSONObject jsonObject = new JSONObject();
//                    JSONArray jsonArray = new JSONArray();
//                    try {
//                        jsonObject.put("from_id", userid);
//                        jsonObject.put("to_id", "10000");
//                        jsonObject.put("type", "connected_notify");
//                        jsonObject.put("data", jsonArray);
//                    } catch (Exception e) {
//                    }
//                    mConnect.sendTextMessage(jsonObject.toString());
//                }
//
//                @Override
//                public void onTextMessage(final String payload) {
//                    mCallBack.callback(payload);
//                    Log.e("------", payload);
//                }
//            });
//        } catch (Exception e) {
//        }
//    }
//
//    public void stopConnect() {
//        if (mConnect != null) {
//            mConnect.disconnect();
//            mConnect = null;
//        }
//    }

}
