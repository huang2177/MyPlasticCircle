package com.myplas.q.myself.integral.dialogutils;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.jmf.addsubutils.AddSubUtils;
import com.myplas.q.R;
import com.myplas.q.myself.beans.IntegralBean;
import com.myplas.q.myself.integral.adapter.Integral_Diaolog_Classify_Adapter;

import java.util.List;
import java.util.Map;

/**
 * 编写： 黄双
 * 电话：15378412400
 * 邮箱：15378412400@163.com
 * 时间：2017/5/16 10:23
 */
public class ClassifyDialogShowUtils implements View.OnClickListener {
    private int fPosition;
    private TextView mButton;
    private Dialog theDialog;
    private ImageView mImageShow;
    private AddSubUtils mAddSubUtils;
    private GridView mGridView1, mGridView2;
    private LinearLayout mLayout, mLayoutClose;
    private String fId, childId, fName, childName;
    private TextView mTextPrice, mTextType, mTextChoosed;

    private int num;
    private boolean isPay;
    private Context mContext;
    private MyInterface myInterface;
    private Integral_Diaolog_Classify_Adapter mAdapter1, mAdapter2;

    private Map<Integer, Integer> mDefFPostionMap;
    private Map<Integer, Integer> mDefCPostionMap;

    public void showDialog(final Context context
            , final IntegralBean.InfoBean infoBean
            , final MyInterface myInterface
            , final int defPsition
            , Map<Integer, Integer> mDefFMap
            , final Map<Integer, Integer> mDefCMap
            , Map<Integer, Integer> mDefNum) {

        this.num = 1;
        this.mContext = context;
        this.myInterface = myInterface;
        this.mDefFPostionMap = mDefFMap;
        this.mDefCPostionMap = mDefCMap;
        if (theDialog == null) {
            theDialog = new Dialog(context, R.style.commondialog_style);
        }
        View view1 = LayoutInflater.from(context).inflate(R.layout.dialog_layout_intergral_classify, null, false);

        mButton = (TextView) view1.findViewById(R.id.dl_classify_ok);
        mGridView1 = (GridView) view1.findViewById(R.id.dl_classify_gv1);
        mGridView2 = (GridView) view1.findViewById(R.id.dl_classify_gv2);
        mTextType = (TextView) view1.findViewById(R.id.dl_classify_text_type);
        mImageShow = (ImageView) view1.findViewById(R.id.dl_classify_img_show);
        mTextPrice = (TextView) view1.findViewById(R.id.dl_classify_text_price);
        mLayout = (LinearLayout) view1.findViewById(R.id.dl_classify_ll_children);
        mLayoutClose = (LinearLayout) view1.findViewById(R.id.dl_classify_img_close);
        mTextChoosed = (TextView) view1.findViewById(R.id.dl_classify_text_choosed);
        mAddSubUtils = (AddSubUtils) view1.findViewById(R.id.dl_classify_addsubutils);

        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                num = -1;
                mButton.setClickable(false);
                //mButton.setBackgroundColor(context.getResources().getColor(R.color.color_gray3));
                myInterface.classifySelected(-1, fName, childName, fId, childId, mAddSubUtils.getNumber());
            }
        });
        mLayoutClose.setOnClickListener(this);

        theDialog.setContentView(view1);
        setDialogWindowAttr(theDialog, context);
        showInfo(defPsition, context, infoBean, mDefNum);

        mGridView1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                mAdapter2 = new Integral_Diaolog_Classify_Adapter(context
                        , null
                        , infoBean.getExtra_config().get(position).getChildren()
                        , 0);
                fId = infoBean
                        .getExtra_config()
                        .get(position)
                        .getCate_id();
                fName = infoBean
                        .getExtra_config()
                        .get(position)
                        .getCate_name();

                fPosition = position;
                mAdapter1.changeImg(position);
                mGridView2.setAdapter(mAdapter2);
                mDefFPostionMap.put(defPsition, position);
                childName = getChildrenName(infoBean.getExtra_config(), position, 0);
                showData();
            }
        });
        mGridView2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                childId = infoBean
                        .getExtra_config()
                        .get(fPosition)
                        .getChildren()
                        .get(position)
                        .getCate_id();
                childName = "-" + infoBean
                        .getExtra_config()
                        .get(fPosition)
                        .getChildren()
                        .get(position)
                        .getCate_name();
                mAdapter2.changeImg(position);
                mDefCPostionMap.put(defPsition, position);
                showData();
            }
        });
    }

    public void dismiss() {
        if (theDialog != null) {
            theDialog.dismiss();
        }
    }

    private void showInfo(int position, Context context, IntegralBean.InfoBean infoBean, Map<Integer, Integer> mDefNum) {
        try {
            int num = mDefNum.get(position) == null ? 0 : mDefNum.get(position);
            int fPosition = mDefFPostionMap.get(position) == null ? 0 : mDefFPostionMap.get(position);
            int cPosition = mDefCPostionMap.get(position) == null ? 0 : mDefCPostionMap.get(position);

            mAddSubUtils.setBuyMin(1);
            mAddSubUtils.setCurrentNumber(num);
            mTextType.setText(infoBean.getName());
            mTextPrice.setText(infoBean.getPoints() + "塑豆");
            Glide.with(context).load(infoBean.getImage()).into(mImageShow);

            if (infoBean.getExtra_config().size() != 0) {
                IntegralBean.InfoBean.ExtraConfigBean configBean = infoBean.getExtra_config().get(fPosition);
                childName = getChildrenName(infoBean.getExtra_config(), fPosition, cPosition);
                mTextChoosed.setText(configBean.getCate_name() + childName);
                fId = configBean.getCate_id();
                fName = configBean.getCate_name();
                childId = ("".equals(childName))
                        ? ("")
                        : (configBean.getChildren().get(cPosition).getCate_id());

                mAdapter1 = new Integral_Diaolog_Classify_Adapter(context, infoBean.getExtra_config(), null, fPosition);
                mGridView1.setAdapter(mAdapter1);
                mAdapter2 = new Integral_Diaolog_Classify_Adapter(context, null, configBean.getChildren(), cPosition);
                mGridView2.setAdapter(mAdapter2);
            }
        } catch (Exception e) {
        }
    }

    public void showData() {
        String type = ("".equals(childName)) ? (fName) : (fName + childName);
        mTextChoosed.setText(type);
    }

    public void setIsPay(boolean isPay) {
        mButton.setClickable(true);
        //mButton.setBackgroundColor(mContext.getResources().getColor(R.color.color_dl_integral_confirm));
    }

    public String getChildrenName(List<IntegralBean.InfoBean.ExtraConfigBean> list, int fPosition, int cPsition) {
        IntegralBean.InfoBean.ExtraConfigBean configBean = list.get(fPosition);
        boolean isNull = configBean.getChildren() == null || configBean.getChildren().size() == 0;
        mLayout.setVisibility(isNull ? View.GONE : View.VISIBLE);
        return (isNull) ? ("") : ("-" + configBean.getChildren().get(cPsition).getCate_name());
    }

    @Override
    public void onClick(View v) {
        theDialog.dismiss();
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
        lp.width = ViewGroup.LayoutParams.MATCH_PARENT;
        lp.height = (int) (height / 2.2);
        dlg.getWindow().setAttributes(lp);

        dlg.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                if (num == 1) {
                    myInterface.classifySelected(1, fName, childName, fId, childId, mAddSubUtils.getNumber());
                }
            }
        });
        dlg.show();
    }

    public interface MyInterface {
        void classifySelected(int type, String fName, String cName, String fid, String cId, int num);
    }
}
