package com.myplas.q.common.view;

import android.content.Context;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.myplas.q.R;

/**
 * 编写： 黄双
 * 电话：15378412400
 * 邮箱：15378412400@163.com
 * 时间：2017/4/28 16:47
 */
public class LoadingDialog {
    private static TextView txt;
    private static AlertDialog dialog = null;
    public static AlertDialog getInstance(final Context context) {
        if (dialog == null) {
            dialog = new AlertDialog.Builder(context, R.style.dialog).create();
        }
        View view = LayoutInflater.from(context).inflate(R.layout.layout_loading_dialog, null);
        dialog.setView(view);
        txt = (TextView) view.findViewById(R.id.loading_text);
        txt.setText("loading...");
        return dialog;
    }
}
