package com.myplas.q.myinfo.integral.dialogutils;

import android.app.Dialog;
import android.content.Context;
import android.text.Html;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.jmf.addsubutils.AddSubUtils;
import com.myplas.q.R;
import com.myplas.q.myinfo.beans.IntegralBean;
import com.myplas.q.myinfo.integral.adapter.Integral_Diaolog_Classify_Adapter;
import com.myplas.q.myinfo.integral.adapter.Integral_Diaolog_SupDem_Adapter;

import java.util.List;

/**
 * 编写： 黄双
 * 电话：15378412400
 * 邮箱：15378412400@163.com
 * 时间：2017/5/16 10:23
 */
public class ClassifyDialogShowUtils implements View.OnClickListener {
    private String html;

    private Dialog theDialog;
    private AddSubUtils mAddSubUtils;
    private GridView mGridView1, mGridView2;
    private ImageView mImageShow, mImageClose;
    private TextView mTextPrice, mTextType, mTextChoosed;

    private Integral_Diaolog_Classify_Adapter mAdapter;

    public void showDialog(Context context) {
        View view1 = LayoutInflater.from(context).inflate(R.layout.dialog_layout_intergral_classify, null, false);
        theDialog = new Dialog(context, R.style.dialog);
        Log.e("-----", "*********");
        mGridView1 = (GridView) view1.findViewById(R.id.dl_classify_gv1);
        mGridView2 = (GridView) view1.findViewById(R.id.dl_classify_gv2);
        mTextType = (TextView) view1.findViewById(R.id.dl_classify_text_type);
        mTextPrice = (TextView) view1.findViewById(R.id.dl_classify_text_price);
        mImageClose = (ImageView) view1.findViewById(R.id.dl_classify_img_close);
        mTextChoosed = (TextView) view1.findViewById(R.id.dl_classify_text_choosed);
        mAddSubUtils = (AddSubUtils) view1.findViewById(R.id.dl_classify_addsubutils);

        mAddSubUtils.setBuyMin(1);
        mImageClose.setOnClickListener(this);

        mAdapter = new Integral_Diaolog_Classify_Adapter(context, null, null);
        mGridView1.setAdapter(mAdapter);
        mAdapter = new Integral_Diaolog_Classify_Adapter(context, null, null);
        mGridView2.setAdapter(mAdapter);

        theDialog.setContentView(view1);
        setDialogWindowAttr(theDialog, context);


        mGridView1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                mAdapter.changeImg(position);
//                showData(list_msg, position);
            }
        });
        mGridView2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                mAdapter.changeImg(position);
//                showData(list_msg, position);
            }
        });
    }

    //设置dialog属性
    public void setDialogWindowAttr(Dialog dlg, Context context) {
        Window dialogWindow = dlg.getWindow();
        dialogWindow.setGravity(Gravity.BOTTOM);
        dialogWindow.setWindowAnimations(R.style.my_anim_out_in); // 添加动画

        dlg.setCancelable(true);
        dlg.setCanceledOnTouchOutside(true);

        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics outMetrics = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(outMetrics);
        int width = outMetrics.widthPixels;
        int height = outMetrics.heightPixels;

        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        lp.width = lp.MATCH_PARENT;
        lp.height = lp.WRAP_CONTENT;
        dlg.getWindow().setAttributes(lp);

        dlg.show();
    }

    public void showData(List<IntegralBean.InfoBean.MyMsgBean> list_msg, int position) {
        html = (list_msg.get(position).getType().equals("1")) ?
                ("<font color='#EEAD0E'>" + "求购:" + "</font>" + list_msg.get(position).getContents()) :
                ("<font color='#9AC0CD'>" + "供给:" + "</font>" + list_msg.get(position).getContents());
        //textView1.setText("已选择：");
//        textView.setText(Html.fromHtml(html));
//        time.setText(list_msg.get(position).getInput_time());
    }

    @Override
    public void onClick(View v) {
        theDialog.dismiss();
    }
}
