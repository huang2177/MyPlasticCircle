package com.myplas.q.myself.invoices.adapter;

import android.app.Dialog;
import android.content.Context;
import android.text.Editable;
import android.text.Html;
import android.text.InputFilter;
import android.text.TextWatcher;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.myplas.q.R;
import com.myplas.q.common.utils.TextUtils;
import com.myplas.q.common.view.InputFilterMinMax;
import com.myplas.q.myself.beans.ApplyInvoiceBean;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

/**
 * 编写：黄双
 * 邮箱：15378412400@163.com
 * 时间： 2017/6/121519.
 */

public class ApplyInvoiceAdapter extends BaseAdapter {
    private Context context;
    private List<ApplyInvoiceBean.DataBean.ListBean> list;

    private String num, maxString;
    private Map<Integer, Double> mDoubleMap;
    private Map<Integer, String> mStringMap;
    private List<Double> mDoubleList;
    private List<String> mStringList;

    private EditText mEditText;
    private ImageView mImageView;
    private Dialog normalDialog;
    private MyOnClickListener mListener;

    public ApplyInvoiceAdapter(Context context, List<ApplyInvoiceBean.DataBean.ListBean> list) {
        this.list = list;
        this.context = context;
        mDoubleMap = new HashMap<>();
        mStringMap = new HashMap<>();
        mStringList = new ArrayList<>();
        mDoubleList = new ArrayList<>();
    }

    @Override
    public int getCount() {
        return list != null ? list.size() : 0;
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
        if (convertView == null) {
            viewHolder = new viewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.item_listview_applyinvoices, null);
            viewHolder.textView_mode = (TextView) convertView.findViewById(R.id.item_lv_applyinvoice_mode);
            viewHolder.textView_num = (TextView) convertView.findViewById(R.id.item_lv_applyinvoice_num);
            viewHolder.textView_applied = (TextView) convertView.findViewById(R.id.item_lv_invoice_applied);
            viewHolder.textView_applyable = (TextView) convertView.findViewById(R.id.item_lv_invoice_applyable);
            viewHolder.textView_apply = (TextView) convertView.findViewById(R.id.item_lv_invoice_apply);
            viewHolder.mImageView_modify = (ImageView) convertView.findViewById(R.id.item_lv_invoice_img);
            convertView.setTag(viewHolder);

            try {
                double total_num = list.get(position).getTotal_num();
                double b_number = list.get(position).getB_number();
                double unit_price = getDecimalFormatData(list.get(position).getUnit_price() + "");
                double number = list.get(position).getNumber();

                viewHolder.textView_mode.setText(list.get(position).getF_name() + " " + list.get(position).getModel());

                String html = "<font color='#ff5550'>" + unit_price + "</font>" + " x " + b_number + " = "
                        + getDecimalFormatData((b_number * unit_price) + "");
                viewHolder.textView_num.setText(Html.fromHtml(html));
                viewHolder.textView_applied.setText(getDecimalFormatData(list.get(position).getLast_num() + "") + "");
                viewHolder.textView_applyable.setText(b_number + "");
                viewHolder.textView_apply.setText(b_number + "");
                mDoubleMap.put(position, getDecimalFormatData((b_number * unit_price) + ""));
                mStringMap.put(position, list.get(position).getB_number() + "");

                viewHolder.mImageView_modify.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        showDialog(viewHolder
                                , mStringMap.get(position) + ""
                                , Double.parseDouble(list.get(position).getUnit_price() + "")
                                , position);
                    }
                });
            } catch (Exception e) {
            }
        } else {
            viewHolder = (viewHolder) convertView.getTag();
        }
        return convertView;
    }


    public void showDialog(final viewHolder viewHolder, String num, final double unit_price, final int pos) {
        View view = View.inflate(context, R.layout.dialog_layout_modify_num, null);

        normalDialog = new Dialog(context, R.style.commondialog_style);
        normalDialog.setContentView(view);
        normalDialog.setCancelable(true);
        normalDialog.setCanceledOnTouchOutside(true);

        setDialogWindowAttr(context);
        normalDialog.show();

        mImageView = (ImageView) view.findViewById(R.id.dialog_layout_modify_img);
        mEditText = (EditText) view.findViewById(R.id.dialog_layout_modify_editText);
        mEditText.setText(num);
        mEditText.setSelection(num.length());
        mEditText.setFilters(new InputFilter[]{new InputFilterMinMax("0", list.get(pos).getB_number() + "")});
        mImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hideInPutKeyboord();
                normalDialog.dismiss();
                String num = mEditText.getText().toString();

                if (TextUtils.isNullOrEmpty(num)) {
                    double numed = Double.parseDouble(num);
                    mStringMap.put(pos, getDecimalFormatData(num) + "");
                    mDoubleMap.put(pos, getDecimalFormatData((numed * unit_price) + ""));

                    viewHolder.textView_apply.setText(getDecimalFormatData(num) + "");
                    viewHolder.textView_num.setText(unit_price + " x " + getDecimalFormatData(num) + " = " + getDecimalFormatData((numed * unit_price) + ""));
                    mListener.onClick(mDoubleMap, mStringMap);
                }
            }
        });
        showInPutKeybord();

        mEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                try {
                    if (s.toString().contains(".")) {
                        if (isFourFloat(s.toString()) == 2) {
                            maxString = s.toString();
                            mEditText.removeTextChangedListener(this);
                            mEditText.setText(maxString);
                            mEditText.setSelection(s.length());
                            mEditText.addTextChangedListener(this);
                        } else if (isFourFloat(s.toString()) == 3) {
                            mEditText.setText(maxString);
                            TextUtils.Toast(context, "最多可以输入四位小数！");
                        }
                    }
                } catch (Exception e) {
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
    }

    public int isFourFloat(String s) {
        if (s.substring(s.indexOf(".") + 1).length() < 4) {
            return 1;
        } else if (s.substring(s.indexOf(".") + 1).length() == 4) {
            return 2;
        } else {
            return 3;
        }
    }

    public void hideInPutKeyboord() {
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(mEditText.getWindowToken(), 0);
    }

    private void showInPutKeybord() {
        mEditText.setFocusable(true);
        mEditText.setFocusableInTouchMode(true);
        mEditText.requestFocus();
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {

            @Override
            public void run() {
                InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.toggleSoftInput(0, InputMethodManager.SHOW_FORCED);
            }

        }, 200);
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

    public interface MyOnClickListener {
        void onClick(Map<Integer, Double> map1, Map<Integer, String> map2);
    }

    public void setMyOnClickListener(MyOnClickListener myOnClickListener) {
        this.mListener = myOnClickListener;
    }

    class viewHolder {
        ImageView mImageView_modify;
        TextView textView_mode, textView_num, textView_applied, textView_applyable, textView_apply;
    }

    public double getDecimalFormatData(String data) {
        DecimalFormat format = new DecimalFormat("#.0000");
        return Double.parseDouble(format.format(Double.parseDouble(data)));
    }
}
