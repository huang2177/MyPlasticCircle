package com.myplas.q.myinfo.nsqhelper;

import android.util.Log;

import ly.bit.nsq.Message;
import ly.bit.nsq.example.PrintReader;
import ly.bit.nsq.exceptions.NSQException;
import ly.bit.nsq.lookupd.BasicLookupd;
import ly.bit.nsq.syncresponse.SyncResponseHandler;
import ly.bit.nsq.syncresponse.SyncResponseReader;


/**
 * 作者：  黄双
 * 事件 2017/8/15 0015.
 * 邮箱： 15378412400@163.com
 */

public class NSQHelper implements SyncResponseHandler {
    public boolean handleMessage(Message msg) throws NSQException {
        System.out.println("Received: " + new String(msg.getBody()));
        return true;
    }

    public void startConnection() {
        SyncResponseHandler sh = new PrintReader();
        SyncResponseReader reader = new SyncResponseReader("test", "foo", sh);
        try {
            reader.connectToNsqd("139.196.205.19", 4161);
        } catch (NSQException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        reader.addLookupd(new BasicLookupd("http://139.196.205.19:4161"));
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}
