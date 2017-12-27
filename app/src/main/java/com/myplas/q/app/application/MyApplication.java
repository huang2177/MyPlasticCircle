package com.myplas.q.app.application;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.multidex.MultiDexApplication;
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
import me.panpf.sketch.Sketch;

/**
 * 作者：  黄双
 * 事件 2017/8/15 0015.
 * 邮箱： 15378412400@163.com
 */

public class MyApplication extends MultiDexApplication {

    @Override
    public void onCreate() {
        super.onCreate();
        initJPush();
        initStetho();
        registerActivity();
    }


    /**
     * 初始化Stetho相关
     */
    private void initStetho() {
        if (BuildConfig.USE_STETHO) {
            Stetho.initialize(
                    Stetho.newInitializerBuilder(this)
                            .enableDumpapp(Stetho.defaultDumperPluginsProvider(this))
                            .enableWebKitInspector(Stetho.defaultInspectorModulesProvider(this))
                            .build());
        }
    }

    /**
     * 初始化极光推送相关
     */
    private void initJPush() {
        String userId = SharedUtils
                .getSharedUtils()
                .getData(this, Constant.USERID);

        JPushInterface.init(this);

        JPushInterface.setPowerSaveMode(this, true);

        JPushInterface.setDebugMode(BuildConfig.USE_STETHO
                ? true
                : false);

        if (TextUtils.notEmpty(userId)) {
            JPushInterface.setAlias(this, 10, userId);
        }
    }

    /**
     * 注册activity生命周期的监听
     */
    private void registerActivity() {
        registerActivityLifecycleCallbacks(new ActivityLifecycleCallbacks() {
            @Override
            public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
                if (activity instanceof SplashActivity) {
                    StatusUtils.setStatusBar(activity, false, false);
                    StatusUtils.setStatusTextColor(false, activity);
                }

            }

            @Override
            public void onActivityStarted(Activity activity) {

            }

            @Override
            public void onActivityResumed(Activity activity) {
                ActivityManager.getInstance().setCurrentActivity(activity);
            }

            @Override
            public void onActivityPaused(Activity activity) {
                final View v = activity.getWindow().peekDecorView();
                if (v != null && v.getWindowToken() != null) {
                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                }
            }

            @Override
            public void onActivityStopped(Activity activity) {

            }

            @Override
            public void onActivitySaveInstanceState(Activity activity, Bundle outState) {

            }

            @Override
            public void onActivityDestroyed(Activity activity) {
                LoadingDialog.clear(activity);
                AlertDialog dialog = LoadingDialog.getInstance(activity);
                if (dialog != null && dialog.isShowing()) {
                    dialog.dismiss();
                }
            }
        });
    }

    @Override
    public void onTrimMemory(int level) {
        super.onTrimMemory(level);
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.ICE_CREAM_SANDWICH) {
            Sketch.with(getBaseContext()).onTrimMemory(level);
        }
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.ICE_CREAM_SANDWICH) {
            Sketch.with(getBaseContext()).onLowMemory();
        }
    }
}
