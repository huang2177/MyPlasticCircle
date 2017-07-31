package com.myplas.q.myinfo.integral.mydatepicker;

import android.app.Dialog;
import android.content.Context;
import android.text.Html;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.myplas.q.R;
import com.myplas.q.myinfo.integral.adapter.Integral_SupDem_Adapter;
import com.myplas.q.myinfo.beans.IntegralBean;

import java.util.List;

/**
 * 编写： 黄双
 * 电话：15378412400
 * 邮箱：15378412400@163.com
 * 时间：2017/5/16 10:23
 */
public class SupDemDialogShowUtils implements View.OnClickListener {
    private Dialog theDialog;
    private ListView listView;
    private TextView textView, time, textView1;
    private String html;
    private ImageView imageView;
    private LinearLayout linearLayout;

    public void showDialog(Context context, final List<IntegralBean.InfoBean.MyMsgBean> list_msg, final Integral_SupDem_Adapter integral_supplyDemandAdapter) {
        View view1 = LayoutInflater.from(context).inflate(R.layout.layout_dialog_suppdem, null, false);
        theDialog = new Dialog(context, R.style.FullHeightDialog);

        listView = (ListView) view1.findViewById(R.id.integral_supplydemand_listview);
        textView = (TextView) view1.findViewById(R.id.radio_);
        textView1 = (TextView) view1.findViewById(R.id.img_check);
        time = (TextView) view1.findViewById(R.id.radio_1);
        imageView = (ImageView) view1.findViewById(R.id.popupwindow_calendar_bt_enter);
        linearLayout = (LinearLayout) view1.findViewById(R.id.integral_isselected_linearlayout);
        linearLayout.setVisibility(View.VISIBLE);
        linearLayout.setBackgroundResource(R.color.color_lightgray);

        imageView.setOnClickListener(this);
        listView.setAdapter(integral_supplyDemandAdapter);

        theDialog.setContentView(view1);
        theDialog.setCancelable(true);
        theDialog.setCanceledOnTouchOutside(true);
        setDialogWindowAttr(theDialog, context);
        theDialog.show();
        //showData(list_msg,0);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                integral_supplyDemandAdapter.changeImg(position);
                showData(list_msg, position);
            }
        });
    }

    //设置dialog属性
    public void setDialogWindowAttr(Dialog dlg, Context context) {
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics outMetrics = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(outMetrics);
        int width = outMetrics.widthPixels;
        int height = outMetrics.heightPixels;

        Window window = dlg.getWindow();
        WindowManager.LayoutParams lp = window.getAttributes();
        lp.gravity = Gravity.CENTER;
        lp.width = (int) ((width * 3) / 4.2);//宽高可设置具体大小
        lp.height = (int) ((height * 2) / 3.3);
//        lp.width=lp.MATCH_PARENT;
//        lp.height=lp.MATCH_PARENT;
        dlg.getWindow().setAttributes(lp);
    }

    public void showData(List<IntegralBean.InfoBean.MyMsgBean> list_msg, int position) {
        html = (list_msg.get(position).getType().equals("1")) ?
                ("<font color='#EEAD0E'>" + "求购:" + "</font>" + list_msg.get(position).getContents()) :
                ("<font color='#9AC0CD'>" + "供给:" + "</font>" + list_msg.get(position).getContents());
        //textView1.setText("已选择：");
        textView.setText(Html.fromHtml(html));
        time.setText(list_msg.get(position).getInput_time());
    }

    @Override
    public void onClick(View v) {
        theDialog.dismiss();
    }
}
