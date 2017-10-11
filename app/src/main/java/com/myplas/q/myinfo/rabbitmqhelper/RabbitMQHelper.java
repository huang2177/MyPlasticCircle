package com.myplas.q.myinfo.rabbitmqhelper;


import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.QueueingConsumer;

import java.io.IOException;

/**
 * 作者：  黄双
 * 事件 2017/8/15 0015.
 * 邮箱： 15378412400@163.com
 */

public class RabbitMQHelper {
    Thread subscribeThread;
    Connection mConnection;
    ConnectionFactory mFactory;
    RabbitMQCallBack mMQCallBack;

    public RabbitMQHelper(RabbitMQCallBack rabbitMQCallBack) {
        this.mMQCallBack = rabbitMQCallBack;
    }

    public void onConnect() {
        //连接设置
        setupConnectionmmFactory();
        //用于从线程中获取数据，更新ui
        final Handler incomingMessageHandler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                String message = msg.getData().getString("msg");
                mMQCallBack.callback(message);
            }
        };
        //开启消费者线程
        subscribe(incomingMessageHandler);
    }

    /**
     * 连接设置
     */
    private void setupConnectionmmFactory() {
        mFactory = new ConnectionFactory();
        mFactory.setPort(5672);
        mFactory.setHost("116.62.179.6");
        mFactory.setUsername("admin");
        mFactory.setPassword("admin");
        mFactory.setAutomaticRecoveryEnabled(false);
        mFactory.setVirtualHost("client");
    }

    /**
     * 消费者线程
     */
    void subscribe(final Handler handler) {
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

                    AMQP.Queue.DeclareOk q = channel.queueDeclare();
                    //将队列绑定到消息交换机exchange上
                    //                  queue         exchange              routingKey路由关键字，exchange根据这个关键字进行消息投递。
                    channel.queueBind(q.getQueue(), "CREDITHC_CS", "contract_request");

                    //创建消费者
                    QueueingConsumer consumer = new QueueingConsumer(channel);
                    channel.basicConsume(q.getQueue(), true, consumer);

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
                } catch (Exception e) {
                    Log.e("=========", e.toString());
                }


//                try {
//                    //1.打开连接和创建频道，与发送端一样
//                    ConnectionFactory factory = new ConnectionFactory();
//                    factory.setRequestedHeartbeat(3);//连接心跳
//                    mFactory = new ConnectionFactory();
//                    mFactory.setPort(5672);
//                    mFactory.setHost("116.62.179.6");
//                    mFactory.setUsername("admin");
//                    mFactory.setPassword("admin");
//                    mFactory.setVirtualHost("client");
//                    mFactory.setAutomaticRecoveryEnabled(false);
//                    //创建一个连接   创建一个频道
//                    Connection connection = factory.newConnection();
//                    Channel channel = connection.createChannel();
//                    //2.声明队列，主要为了防止消息接收者先运行此程序，队列还不存在时创建队列。
//                    channel.queueDeclare(QUEUE_NAME, false, false, false, null);
//
//                    //3.创建队列消费者
//                    QueueingConsumer consumer = new QueueingConsumer(channel);
//                    channel.basicConsume(QUEUE_NAME, true, consumer);//指定消费队列
//                    while (true){
//                        //4.开启nextDelivery阻塞方法（内部实现其实是阻塞队列的take方法）
//                        QueueingConsumer.Delivery delivery = consumer.nextDelivery();
//                        String message = new String(delivery.getBody());
//                        Log.e("===111===", message+"===111===");
//                    }
//                } catch (IOException e) {
//                    e.printStackTrace();
//                } catch (TimeoutException e) {
//                    e.printStackTrace();
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }

            }
        });
        subscribeThread.start();
    }

    public void onDisConnect() {
        subscribeThread.interrupt();
        if (mConnection != null && mConnection.isOpen()) {
            try {
                mConnection.close();
            } catch (IOException e) {
                Log.e("=========", e.toString());
            }
        }
    }
}
