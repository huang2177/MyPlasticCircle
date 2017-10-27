package com.myplas.q.sockethelper;


import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.myplas.q.common.appcontext.Constant;
import com.myplas.q.common.utils.ACache;
import com.myplas.q.common.utils.SharedUtils;
import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.QueueingConsumer;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者：  黄双
 * 事件 2017/8/15 0015.
 * 邮箱： 15378412400@163.com
 */
public class RabbitMQHelper {
    private ACache mACache;
    private static Context mContext;
    private Thread subscribeThread;
    private Connection mConnection;
    private DefConfigBean mConfigBean;
    private ConnectionFactory mFactory;
    private static RabbitMQHelper mRabbitMQHelper;

    private String userid;
    private SharedUtils mSharedUtils;
    private boolean isLogined, isInterrupt;
    private static List<RabbitMQCallBack> mList;

    private RabbitMQHelper(Context context) {
        mContext = context;
        mList = new ArrayList<>();
        mSharedUtils = SharedUtils.getSharedUtils();
    }

    /**
     * Gets instance.
     *
     * @param context the context
     * @return the instance
     */
    public static RabbitMQHelper getInstance(Context context) {
        if (mRabbitMQHelper == null) {
            mRabbitMQHelper = new RabbitMQHelper(context.getApplicationContext());
            return mRabbitMQHelper;
        }
        return mRabbitMQHelper;
    }

    /**
     * Sets result callback.
     *
     * @param mMQCallBack 设置监听
     */
    public void setResultCallBack(RabbitMQCallBack mMQCallBack) {
        if (!mList.contains(mMQCallBack)) {
            mList.add(mMQCallBack);
        }
    }


    /**
     * On connect.
     */
    public void onConnect() {
        try {
            mACache = ACache.get(mContext);
            isLogined = mSharedUtils.getBoolean(mContext, Constant.LOGINED);
            mConfigBean = new Gson().fromJson(mACache.getAsString(Constant.R_CONFIG)
                    , DefConfigBean.class);
            userid = isLogined
                    ? mSharedUtils.getData(mContext, Constant.USERID)
                    : "";
            if (mConfigBean == null || !isLogined) {
                return;
            }
        } catch (Exception e) {
        }
        //连接设置
        setupConnectionmmFactory();
        //用于从线程中获取数据，更新ui
        final Handler incomingMessageHandler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                parseData(msg);
            }
        };
        //开启消费者线程
        subscribe(incomingMessageHandler);
    }

    private void parseData(Message msg) {
        try {
            Gson gson = new Gson();
            String message = msg.getData().getString("msg");
            DotBean dotBean = gson.fromJson(message, DotBean.class);
            if (userid.equals(dotBean.getData().getTo())) {
                DefConfigBean.RedDotBean bean = new DefConfigBean.RedDotBean();
                switch (dotBean.getData().getKey()) {
                    case "unread_customer": //通讯录
                        mACache.put(Constant.R_CONTACT, dotBean.getData().getValue());
                        break;
                    case "unread_supply_and_demand":
                        mACache.put(Constant.R_SUPDEM, dotBean.getData().getValue());
                        break;
                    case "unread_mymsg":
                        mACache.put(Constant.R_MYMSG, dotBean.getData().getValue());
                        break;
                    case "unread_myorder":
                        mACache.put(Constant.R_MYORDER, dotBean.getData().getValue());
                        break;
                    case "unread_who_saw_me":
                        mACache.put(Constant.R_SEEME, dotBean.getData().getValue());
                        break;
                    default:
                        break;
                }
                for (int i = 0; i < mList.size(); i++) {
                    RabbitMQCallBack callback = mList.get(i);
                    if (callback != null) {
                        callback.rCallback(true);
                    }
                }
            }
        } catch (Exception e) {

        }
    }

    /**
     * 连接设置
     */
    private void setupConnectionmmFactory() {
        mFactory = new ConnectionFactory();
        mFactory.setPort(mConfigBean.getConfig().getPort());
        mFactory.setHost(mConfigBean.getConfig().getHost());
        mFactory.setUsername(mConfigBean.getConfig().getUser_name());
        mFactory.setPassword(mConfigBean.getConfig().getPassword());
        mFactory.setVirtualHost(mConfigBean.getConfig().getVhost());
        mFactory.setAutomaticRecoveryEnabled(true);

    }

    /**
     * 消费者线程
     */
    private void subscribe(final Handler handler) {
        subscribeThread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    //使用之前的设置，建立连接
                    mConnection = mFactory.newConnection();
                    //创建一个通道
                    Channel channel = mConnection.createChannel();
                    //一次只发送一个，处理完成一个再获取下一个
                    channel.basicQos(1);
                    String queueName = mConfigBean.getConfig().getQueue_config().getName().substring(0
                            , mConfigBean.getConfig().getQueue_config().getName().lastIndexOf("_") + 1);
                    AMQP.Queue.DeclareOk q = channel.queueDeclare(queueName + userid
                            , mConfigBean.getConfig().getQueue_config().isDurable()
                            , mConfigBean.getConfig().getQueue_config().isExclusive()
                            , mConfigBean.getConfig().getQueue_config().isAuto_delete()
                            , null);

                    channel.exchangeDeclare(mConfigBean.getConfig().getExchange_config().getName()
                            , mConfigBean.getConfig().getExchange_config().getType()
                            , mConfigBean.getConfig().getExchange_config().isDurable()
                            , mConfigBean.getConfig().getExchange_config().isAuto_delete()
                            , false
                            , null);
                    //将队列绑定到消息交换机exchange上
                    //                  queue         exchange              routingKey路由关键字，exchange根据这个关键字进行消息投递。
                    channel.queueBind(queueName + userid
                            , mConfigBean.getConfig().getExchange_config().getName()
                            , mConfigBean.getConfig().getRoute_key());

                    //创建消费者
                    QueueingConsumer consumer = new QueueingConsumer(channel);
                    channel.basicConsume(queueName + userid
                            , true
                            , consumer);

                    while (true) {
                        //wait for the next message delivery and return it.
                        QueueingConsumer.Delivery delivery = consumer.nextDelivery();
                        String message = new String(delivery.getBody());
                        //从message池中获取msg对象更高效
                        Message msg = handler.obtainMessage();
                        Bundle bundle = new Bundle();
                        bundle.putString("msg", message);
                        msg.setData(bundle);
                        handler.sendMessage(msg);
                    }
                } catch (InterruptedException ie) {
                    isInterrupt = true;
                } catch (Exception e) {
                    Log.e("------>RabbitMQ", e.toString());
                }

            }
        });
        subscribeThread.start();
    }

    /**
     * On dis connect.
     */
    public void onDisConnect() {
        subscribeThread.interrupt();
        if (mConnection != null && mConnection.isOpen()) {
            try {
                mConnection.close();
                mConnection = null;
            } catch (Exception e) {
            }
        }
    }
}
