package com.myplas.q.sockethelper;


import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.google.gson.Gson;
import com.myplas.q.common.appcontext.Constant;
import com.myplas.q.common.utils.ACache;
import com.myplas.q.common.utils.SharedUtils;
import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.QueueingConsumer;
import com.rabbitmq.client.ShutdownSignalException;

import org.json.JSONObject;

import java.io.IOException;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

/**
 * 作者：  黄双
 * 事件 2017/8/15 0015.
 * 邮箱： 15378412400@163.com
 */
public class RabbitMQHelper {
    private ACache mACache;
    private Context mContext;
    private Thread subscribeThread;
    private Connection mConnection;
    private DefConfigBean.ConfigBean mConfigBean;
    private ConnectionFactory mFactory;
    private static RabbitMQHelper mRabbitMQHelper;

    private String userid;
    private SharedUtils mSharedUtils;
    private boolean isLogined, isInterrupt;
    private static List<Connection> mConnectionList;

    private IncomingMessageHandler mHandler;

    private RabbitMQHelper(Context context) {
        mContext = context;
        mConnectionList = new ArrayList<>();
        mSharedUtils = SharedUtils.getSharedUtils();
        mHandler = new IncomingMessageHandler(mContext);
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
     * On connect.
     */
    public void onConnect() {
        try {
            isInterrupt = false;
            mACache = ACache.get(mContext);
            isLogined = mSharedUtils.getBoolean(mContext, Constant.LOGINED);
            mConfigBean = new Gson().fromJson(mACache.getAsString(Constant.R_CONFIG), DefConfigBean.ConfigBean.class);
            userid = isLogined ? mSharedUtils.getData(mContext, Constant.USERID) : "";
            if (mConfigBean == null || !isLogined) {
                return;
            }
        } catch (Exception e) {
            e.toString();
        }

        setupConnectionmmFactory();
        createConnect();
    }

    /**
     * 连接设置
     */
    private void setupConnectionmmFactory() {
        mFactory = new ConnectionFactory();
        mFactory.setPort(mConfigBean.getPort());
        mFactory.setHost(mConfigBean.getHost());
        mFactory.setUsername(mConfigBean.getUser_name());
        mFactory.setPassword(mConfigBean.getPassword());
        mFactory.setVirtualHost(mConfigBean.getVhost());
        mFactory.setAutomaticRecoveryEnabled(true);
    }

    /**
     * 创建两个链接
     */
    private void createConnect() {
        for (int i = 0; i < 2; i++) {
            subscribe(i, mHandler);
        }
    }

    /**
     * 消费者线程
     */
    private void subscribe(final int i, final Handler handler) {
        subscribeThread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    //使用之前的设置，建立连接
                    mConnection = mFactory.newConnection();
                    mConnectionList.add(mConnection);
                    //创建一个通道
                    Channel channel = mConnection.createChannel();
                    //一次只发送一个，处理完成一个再获取下一个
                    channel.basicQos(1);
                    String queueName = (i == 0)
                            ? mConfigBean.getSingle().getQueue_config().getName().substring(0
                            , mConfigBean.getSingle().getQueue_config().getName().lastIndexOf("_") + 1)
                            : mConfigBean.getAll().getQueue_config().getName().substring(0
                            , mConfigBean.getAll().getQueue_config().getName().lastIndexOf("_") + 1);
                    boolean isQDurable = i == 0
                            ? mConfigBean.getSingle().getQueue_config().isDurable()
                            : mConfigBean.getAll().getQueue_config().isDurable();

                    boolean isQExclusive = i == 0
                            ? mConfigBean.getSingle().getQueue_config().isExclusive()
                            : mConfigBean.getAll().getQueue_config().isExclusive();

                    boolean isQAuto_delete = i == 0
                            ? mConfigBean.getSingle().getQueue_config().isAuto_delete()
                            : mConfigBean.getAll().getQueue_config().isAuto_delete();

                    AMQP.Queue.DeclareOk q = channel.queueDeclare(queueName + userid
                            , isQDurable
                            , isQExclusive
                            , isQAuto_delete
                            , null);

                    boolean isEDurable = i == 0
                            ? mConfigBean.getSingle().getExchange_config().isDurable()
                            : mConfigBean.getAll().getExchange_config().isDurable();

                    String type = i == 0
                            ? mConfigBean.getSingle().getExchange_config().getType()
                            : mConfigBean.getAll().getExchange_config().getType();
                    String name = i == 0
                            ? mConfigBean.getSingle().getExchange_config().getName()
                            : mConfigBean.getAll().getExchange_config().getName();

                    boolean isEAuto_delete = i == 0
                            ? mConfigBean.getSingle().getExchange_config().isAuto_delete()
                            : mConfigBean.getAll().getExchange_config().isAuto_delete();

                    channel.exchangeDeclare(name
                            , type
                            , isEDurable
                            , isEAuto_delete
                            , false
                            , null);
                    //将队列绑定到消息交换机exchange上
                    //                  queue         exchange              routingKey路由关键字，exchange根据这个关键字进行消息投递。
                    channel.queueBind(queueName + userid
                            , name
                            , mConfigBean.getRoute_key());

                    //创建消费者
                    QueueingConsumer consumer = new QueueingConsumer(channel);
                    channel.basicConsume(queueName + userid
                            , true
                            , consumer);

                    while (!isInterrupt) {
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
                } catch (Exception e) {
                    Log.e("------>RabbitMQ111", e.toString());
                }
            }
        });
        subscribeThread.start();
    }

    /**
     * On disconnect.
     */
    public void onDisConnect() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                isInterrupt = true;
                for (int i = 0; i < mConnectionList.size(); i++) {
                    try {
                        Connection connection = mConnectionList.get(i);
                        if (connection != null && connection.isOpen()) {
                            connection.close();
                            connection = null;
                        }
                    } catch (Exception e) {
                    }
                }
                mConnectionList.clear();
            }
        }).start();
    }

    public class IncomingMessageHandler extends Handler {
        private WeakReference<Context> wk;

        public IncomingMessageHandler(Context context) {
            wk = new WeakReference<Context>(context);
        }

        @Override
        public void handleMessage(Message msg) {
            Context context = wk.get();
            Bundle data = msg.getData();
            if (context == null || data == null) {
                return;
            }
            RabbitMQConfig.getInstance(context).changeRedDots(data.getString("msg"));
        }
    }
}
