package com.myplas.q.guide.application;

import android.app.Application;

import com.facebook.stetho.Stetho;
import com.myplas.q.myinfo.nsqhelper.NSQHelper;

/**
 * 作者：  黄双
 * 事件 2017/8/15 0015.
 * 邮箱： 15378412400@163.com
 */

public class MyApplication extends Application {
    private NSQHelper mNSQHelper;

    @Override
    public void onCreate() {
        super.onCreate();
        Stetho.initialize(
                Stetho.newInitializerBuilder(this)
                        .enableDumpapp(
                                Stetho.defaultDumperPluginsProvider(this))
                        .enableWebKitInspector(
                                Stetho.defaultInspectorModulesProvider(this))
                        .build());

        mNSQHelper = new NSQHelper();
        new Thread(new Runnable() {
            @Override
            public void run() {

            }
        }).start();
    }
}
