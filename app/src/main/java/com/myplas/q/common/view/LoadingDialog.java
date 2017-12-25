package com.myplas.q.common.view;

import android.content.Context;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import com.myplas.q.R;

import java.util.LinkedHashMap;

/**
 * 编写： 黄双
 * 电话：15378412400
 * 邮箱：15378412400@163.com
 * 时间：2017/4/28 16:47
 */
public class LoadingDialog {
    private static AlertDialog dialog = null;
    private static LinkedHashMap<Context, AlertDialog> mHashMap = new LinkedHashMap();

    public static AlertDialog getInstance(Context context) {
        if (mHashMap.get(context) == null) {
            dialog = new AlertDialog.Builder(context, R.style.loadind_dialog_style).create();
            mHashMap.put(context, dialog);
            View view = LayoutInflater.from(context).inflate(R.layout.layout_loading_dialog, null);
            dialog.setView(view);
            dialog.setCancelable(true);
            dialog.setCanceledOnTouchOutside(true);
        }
        return mHashMap.get(context);
    }

    public static void clear(Context context) {
        if (mHashMap != null) {
            mHashMap.remove(context);
            context = null;
        }
    }
}
