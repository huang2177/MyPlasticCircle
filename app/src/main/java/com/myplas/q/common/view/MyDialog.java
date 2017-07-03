package com.myplas.q.common.view;

import android.app.Dialog;
import android.content.Context;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

/**
 * 编写： 黄双
 * 电话：15378412400
 * 邮箱：15378412400@163.com
 * 时间：2017/4/28 10:21
 */
public class MyDialog extends Dialog{
    private MyDialog myDialog;
    private Context context;
    private int themeResId;
    View view;
    public MyDialog(Context context) {
        super(context);
    }
    public MyDialog(Context context, int themeResId,View view) {
        super(context, themeResId);
        this.view=view;
        this.context=context;
        this.themeResId=themeResId;
    }
    public void showDialog(){
            myDialog.setContentView(view);
            if (!myDialog.isShowing()) {
                myDialog.show();
        }
        setDialogWindowAttr(myDialog, context);
    }
    //设置dialog属性
    public  void setDialogWindowAttr(Dialog dlg, Context ctx) {
        WindowManager wm = (WindowManager)context.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics outMetrics = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(outMetrics);
        int width = outMetrics.widthPixels;
        int height = outMetrics.heightPixels;

        Window window = dlg.getWindow();
        WindowManager.LayoutParams lp = window.getAttributes();
        lp.gravity = Gravity.CENTER;
        lp.width = (int)((width * 2) / 2.8);//宽高可设置具体大小
        lp.height = (int) (height / 4);
        dlg.getWindow().setAttributes(lp);
    }
}
