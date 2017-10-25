package com.myplas.q.common.view;

import android.app.Dialog;
import android.content.Context;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import com.myplas.q.R;

/**
 * 编写： 黄双
 * 电话：15378412400
 * 邮箱：15378412400@163.com
 * 时间：2017/5/16 20:05
 */
public class CommonDialog {
    private int type;
    private Dialog normalDialog;
    private Button button_cancle, button_ok;
    private DialogShowInterface dialogShowInterface;
    private TextView textView_content, textView_title;

    private boolean isCanceledOnTouchOutside = true;

    public CommonDialog() {
    }

    public void showDialog(Context context, String content, int type, final DialogShowInterface dialogShowInterface) {
        this.type = type;
        this.dialogShowInterface = dialogShowInterface;
        View view = View.inflate(context, R.layout.dialog_layout_common, null);
        if (normalDialog == null) {
            normalDialog = new Dialog(context, R.style.commondialog_style);
            normalDialog.setCancelable(true);
            normalDialog.setCanceledOnTouchOutside(isCanceledOnTouchOutside);
            normalDialog.setContentView(view);
            setDialogWindowAttr(context);
        }
        if (!normalDialog.isShowing()) {
            normalDialog.show();
        }
        textView_title = (TextView) view.findViewById(R.id.dialog_title);
        textView_content = (TextView) view.findViewById(R.id.dialog_message);
        button_cancle = (Button) view.findViewById(R.id.btn_cancle);
        button_ok = (Button) view.findViewById(R.id.btn_ok);
        if (type == 2) {
            textView_title.setText("塑料圈通讯录");
            button_cancle.setVisibility(View.GONE);
        } else if (type == 5) {
            textView_title.setText("支付成功");
            button_cancle.setVisibility(View.GONE);
        } else if (type == 6) {
            textView_title.setText("支付失败");
        } else if (type == 7) {
            textView_title.setText("支付取消");
        } else {
            textView_title.setText("塑料圈通讯录");
        }
        textView_content.setText(content);
        button_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (dialogShowInterface != null) {
                    CommonDialog.this.dialogShowInterface.ok(CommonDialog.this.type);
                }
                normalDialog.dismiss();
            }
        });
        button_cancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                normalDialog.dismiss();
            }
        });
    }

    public void dismissDialog() {
        normalDialog.dismiss();
    }

    public void setText(String content) {
        textView_content.setText(content);
    }

    public void setCanceledOnTouchOutside(boolean isCanceledOnTouchOutside) {
        this.isCanceledOnTouchOutside = isCanceledOnTouchOutside;
    }

    //设置dialog属性
    private void setDialogWindowAttr(Context context) {
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics outMetrics = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(outMetrics);
        int width = outMetrics.widthPixels;
        int height = outMetrics.heightPixels;

        Window window = normalDialog.getWindow();
        WindowManager.LayoutParams lp = window.getAttributes();
        lp.gravity = Gravity.CENTER;
        lp.width = (int) ((width * 2) / 3.0);//宽高可设置具体大小
//        lp.height = (int) (height / 4.88);
        lp.height = ViewGroup.LayoutParams.WRAP_CONTENT;
        normalDialog.getWindow().setAttributes(lp);
    }

    public interface DialogShowInterface {
        void ok(int type);
    }
}
