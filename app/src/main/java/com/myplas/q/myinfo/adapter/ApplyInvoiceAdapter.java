package com.myplas.q.myinfo.adapter;

import android.app.Dialog;
import android.content.Context;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.myplas.q.R;
import com.myplas.q.common.utils.DialogShowUtils;
import com.myplas.q.common.utils.TextUtils;
import com.myplas.q.myinfo.beans.EDuBean;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 编写：黄双
 * 邮箱：15378412400@163.com
 * 时间： 2017/6/121519.
 */

public class ApplyInvoiceAdapter extends BaseAdapter {
    private Context context;
    private List<EDuBean.DataBean> list;

    private String num;
    private Map<Integer, View> mViewMap;
    private Map<Integer, TextView> mTextViewMap;

    private EditText mEditText;
    private ImageView mImageView;
    private Dialog normalDialog;

    public ApplyInvoiceAdapter(Context context, List<EDuBean.DataBean> list) {
        this.list = list;
        this.context = context;
        mViewMap = new HashMap<>();
        mTextViewMap = new HashMap<>();
    }

    @Override
    public int getCount() {
//        if (list != null)
//            return list.size();
        return 3;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final viewHolder viewHolder;
        if (mViewMap.get(position) == null) {
            viewHolder = new viewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.item_listview_applyinvoices, null);
            viewHolder.textView_mode = (TextView) convertView.findViewById(R.id.item_lv_applyinvoice_mode);
            viewHolder.textView_num = (TextView) convertView.findViewById(R.id.item_lv_applyinvoice_num);
            viewHolder.textView_applied = (TextView) convertView.findViewById(R.id.item_lv_invoice_applied);
            viewHolder.textView_applyable = (TextView) convertView.findViewById(R.id.item_lv_invoice_applyable);
            viewHolder.textView_apply = (TextView) convertView.findViewById(R.id.item_lv_invoice_apply);
            viewHolder.textView_allprice = (TextView) convertView.findViewById(R.id.item_lv_invoice_allprice);
            viewHolder.mImageView_modify = (ImageView) convertView.findViewById(R.id.item_lv_invoice_img);

            convertView.setTag(viewHolder);
            mViewMap.put(position, convertView);
            mTextViewMap.put(position, viewHolder.textView_allprice);
        } else {
            convertView = mViewMap.get(position);
            viewHolder = (viewHolder) convertView.getTag();
        }
        try {
            if (position == 2) {
                mTextViewMap.get(position).setVisibility(View.VISIBLE);
            }
//            viewHolder.textView_content.setText(Html.fromHtml(list.get(position).getA()));
//            viewHolder.textView_title.setText("Q:" + Html.fromHtml(list.get(position).getQ()));

            viewHolder.mImageView_modify.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    showDialog(viewHolder);
                }
            });
        } catch (Exception e) {
        }
        return convertView;
    }


    public void showDialog(final viewHolder viewHolder) {
        Log.e("-----", viewHolder.toString());
        View view = View.inflate(context, R.layout.dialog_layout_modify_num, null);

        normalDialog = new Dialog(context, R.style.dialog);
        normalDialog.setContentView(view);
        normalDialog.setCancelable(true);
        normalDialog.setCanceledOnTouchOutside(false);
        setDialogWindowAttr(context);
        normalDialog.show();

        mImageView = (ImageView) view.findViewById(R.id.dialog_layout_modify_img);
        mEditText = (EditText) view.findViewById(R.id.dialog_layout_modify_editText);
        mImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String num = mEditText.getText().toString();
                if (num != null && !"".equals(num)) {
                    normalDialog.dismiss();
                    viewHolder.textView_apply.setText(num);
                } else {
                    TextUtils.Toast(context, "你还没有输入开票数量！");
                }
            }
        });

    }

    //设置dialog属性
    public void setDialogWindowAttr(Context context) {
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics outMetrics = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(outMetrics);
        int width = outMetrics.widthPixels;
        int height = outMetrics.heightPixels;

        Window window = normalDialog.getWindow();
        WindowManager.LayoutParams lp = window.getAttributes();
        lp.gravity = Gravity.CENTER;
        lp.width = (int) ((width * 2) / 2.6);//宽高可设置具体大小
        lp.height = (int) (height / 5);
        normalDialog.getWindow().setAttributes(lp);
    }

    class viewHolder {
        ImageView mImageView_modify;
        TextView textView_mode, textView_num, textView_applied, textView_applyable, textView_apply, textView_allprice;
    }

}
