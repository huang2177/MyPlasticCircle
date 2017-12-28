package com.myplas.q.app.app;

import android.app.Activity;
import android.app.Application;
import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import com.facebook.stetho.Stetho;
import com.myplas.q.BuildConfig;
import com.myplas.q.app.activity.SplashActivity;
import com.myplas.q.common.appcontext.ActivityManager;
import com.myplas.q.common.appcontext.Constant;
import com.myplas.q.common.utils.SharedUtils;
import com.myplas.q.common.utils.StatusUtils;
import com.myplas.q.common.utils.TextUtils;
import com.myplas.q.common.view.LoadingDialog;

import cn.jpush.android.api.JPushInterface;

/**
 * @author 黄双
 * @date 2017/12/28 0028
 */

public class InitializeService extends IntentService {
    private static final String ACTION_INIT_WHEN_APP_CREATE = "com.myplas.q.action.INIT";

    public InitializeService() {
        super("");
    }

    public InitializeService(String name) {
        super("InitializeService");
    }

    public static void start(Context context) {
        Intent intent = new Intent(context, InitializeService.class);
        intent.setAction(ACTION_INIT_WHEN_APP_CREATE);
        context.startService(intent);
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        if (intent != null) {
            final String action = intent.getAction();
            if (ACTION_INIT_WHEN_APP_CREATE.equals(action)) {
                initJPush(this.getApplication());
                initStetho(this.getApplication());
            }
        }
    }

    /**
     * 初始化Stetho相关
     */
    private void initStetho(Context context) {
        if (BuildConfig.USE_STETHO) {
            Stetho.initialize(
                    Stetho.newInitializerBuilder(context)
                            .enableDumpapp(Stetho.defaultDumperPluginsProvider(context))
                            .enableWebKitInspector(Stetho.defaultInspectorModulesProvider(context))
                            .build());
        }
    }

    /**
     * 初始化极光推送相关
     */
    private void initJPush(Context context) {
        String userId = SharedUtils
                .getSharedUtils()
                .getData(context, Constant.USERID);

        JPushInterface.init(context);

        JPushInterface.setPowerSaveMode(context, true);

        JPushInterface.setDebugMode(BuildConfig.USE_STETHO
                ? true
                : false);

        if (TextUtils.notEmpty(userId)) {
            JPushInterface.setAlias(context, 10, userId);
        }
    }
}
