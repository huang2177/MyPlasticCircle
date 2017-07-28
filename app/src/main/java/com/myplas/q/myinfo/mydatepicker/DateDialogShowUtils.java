package com.myplas.q.myinfo.mydatepicker;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.myplas.q.R;
import com.myplas.q.common.utils.StatusUtils;
import com.myplas.q.myinfo.adapter.Date_Grid_Adapter;
import com.myplas.q.myinfo.adapter.IntegralAdapter;

import java.util.Date;
import java.util.List;

/**
 * 编写： 黄双
 * 电话：15378412400
 * 邮箱：15378412400@163.com
 * 时间：2017/5/16 10:23
 */
public class DateDialogShowUtils implements CalendarPickerView.OnDateSelectedListener, View.OnClickListener
        , DialogInterface.OnKeyListener {
    private List<Date> list;
    private Dialog theDialog;
    private GridView myGridview;
    private ImageView imageView;
    private LinearLayout linearLayout;
    private CalendarPickerView dialogView;
    private IntegralAdapter integralAdapter;
    private Date_Grid_Adapter dateGridAdapter;
    ;

    public DateDialogShowUtils(List<Date> list, IntegralAdapter integralAdapter) {
        this.list = list;
        this.integralAdapter = integralAdapter;

    }

    public void showDialog(Activity context, List<String> list_unclickable, Date date_start, Date date_end) {
        try {

            View view1 = LayoutInflater.from(context).inflate(R.layout.dialog_layout_datepicker, null, false);
            dialogView = (CalendarPickerView) view1.findViewById(R.id.calendar_view);
            imageView = (ImageView) view1.findViewById(R.id.popupwindow_calendar_bt_enter);
            linearLayout = (LinearLayout) view1.findViewById(R.id.date_linear);
            myGridview = (GridView) view1.findViewById(R.id.mygridview);

            dateGridAdapter = new Date_Grid_Adapter(context, list);
            myGridview.setAdapter(dateGridAdapter);
            // linearLayout.setVisibility((list.size() != 0) ? (View.VISIBLE) : (View.GONE));
            imageView.setOnClickListener(this);
            dialogView.init(date_start, getDate(date_end), list_unclickable)//
                    .inMode(CalendarPickerView.SelectionMode.MULTIPLE)
                    .withSelectedDates(list);
            theDialog = new Dialog(context, R.style.FullHeightDialog);
            theDialog.setContentView(view1);
            //theDialog.setCancelable(true);
            theDialog.setCanceledOnTouchOutside(false);
            setDialogWindowAttr(theDialog, context);
            dialogView.setOnDateSelectedListener(this);
            theDialog.setOnKeyListener(this);
            theDialog.show();
        } catch (Exception e) {
        }
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
//        lp.width = (int) ((width *3) / 3.8);//宽高可设置具体大小
//        lp.height = (int) ((height*6) / 7);
        lp.width = lp.MATCH_PARENT;
        lp.height = lp.MATCH_PARENT;
        dlg.getWindow().setAttributes(lp);
    }

    @Override
    public void onDateSelected(Date date) {
        list.add(date);
        linearLayout.setVisibility(View.VISIBLE);
        dateGridAdapter.setList(list);
        dateGridAdapter.notifyDataSetChanged();
        integralAdapter.changeTextShow(list);
    }

    @Override
    public void onDateUnselected(Date date) {
        list.remove(date);
        dateGridAdapter.setList(list);
        dateGridAdapter.notifyDataSetChanged();
        integralAdapter.changeTextShow(list);
        if (list.size() == 0) {
            //linearLayout.setVisibility(View.GONE);
        }
    }

    @Override
    public void onClick(View v) {
        theDialog.dismiss();
        integralAdapter.showGrid_isSelected();
    }

    public Date getDate(Date date) {
        try {
            long l = date.getTime() + 24 * 60 * 60 * 1000;
            return new Date(l);
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            integralAdapter.showGrid_isSelected();
        }
        return false;
    }

}
