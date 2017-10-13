package com.myplas.q.release;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetDialog;
import android.support.v4.app.Fragment;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.myplas.q.R;
import com.myplas.q.common.api.API;
import com.myplas.q.common.appcontext.ActivityManager;
import com.myplas.q.common.netresquset.ResultCallBack;
import com.myplas.q.common.utils.TextUtils;
import com.myplas.q.common.view.CommonDialog;
import com.myplas.q.guide.activity.BaseActivity;
import com.myplas.q.guide.activity.MainActivity;
import com.myplas.q.release.adapter.Release_Pre_Dialog_LV_Adapter;
import com.myplas.q.supdem.activity.SupDem_Detail_Activity;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * 作者:huangshuang
 * 事件 2017/10/7.
 * 邮箱： 15378412400@163.com
 */

public class QuicklyFragment extends Fragment implements View.OnClickListener
        , ResultCallBack {
    private View view;
    private TextView mTV_Type;
    private EditText mEditText;
    private ListView preListView;

    private Dialog preDialog;
    private BottomSheetDialog mButtomDialog;

    private String content, type;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        view = View.inflate(getActivity(), R.layout.fragment_layout_release_quickly, null);
        mEditText = (EditText) view.findViewById(R.id.release_edit);
        mTV_Type = (TextView) view.findViewById(R.id.release_ev_type_);

        mTV_Type.setOnClickListener(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return view;
    }

    public void pub() {
        content = mEditText.getText().toString();
        if (!TextUtils.isNullOrEmpty(content) || !TextUtils.isNullOrEmpty(type)) {
            TextUtils.Toast(getActivity(), "发布内容不能为空！");
            return;
        }
        Map<String, String> map = new HashMap<>();
        map.put("mode", "1");
        map.put("model", "");
        map.put("price", "");
        map.put("vendor", "");
        map.put("type", type);
        map.put("channel", "1");
        map.put("storehouse", "");
        map.put("is_preview", "1");
        map.put("content", content);
        map.put("transaction_type", "");
        BaseActivity.postAsyn(getActivity(), API.BASEURL + API.PUB, map, this, 1);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.release_ev_type_:
                openBottom();
                //openPreViewDialog();
                break;
            case R.id.buttom_dialog_tv1:
                mButtomDialog.dismiss();
                type = "2";
                mTV_Type.setText("供给");
                break;
            case R.id.buttom_dialog_tv2:
                mButtomDialog.dismiss();
                type = "1";
                mTV_Type.setText("求购");
                break;
        }
    }

    private void openBottom() {
        TextView textView1, textView2;
        View view = View.inflate(getActivity(), R.layout.release_buttom_dialog_layout, null);
        textView1 = (TextView) view.findViewById(R.id.buttom_dialog_tv1);
        textView2 = (TextView) view.findViewById(R.id.buttom_dialog_tv2);

        textView1.setOnClickListener(this);
        textView2.setOnClickListener(this);
        textView1.setText("供给");
        textView2.setText("求购");

        mButtomDialog = new BottomSheetDialog(getActivity());
        mButtomDialog.setContentView(view);
        mButtomDialog.show();
    }

    private void openPreViewDialog() {
        View view = View.inflate(getActivity(), R.layout.release_pre_dialog_layout, null);
        preListView = (ListView) view.findViewById(R.id.pre_dialog_lv);
        Release_Pre_Dialog_LV_Adapter adapter = new Release_Pre_Dialog_LV_Adapter(getActivity(), null);
        preListView.setAdapter(adapter);

        preDialog = new Dialog(getActivity(), R.style.commondialog_style);
        preDialog.setContentView(view);
        setDialogWindowAttr();
        preDialog.show();
    }

    @Override
    public void callBack(Object object, int type) {
        try {
            Log.e("-------", object.toString());
            Gson gson = new Gson();
            String err = new JSONObject(object.toString()).getString("err");
            if (type == 2) {
                TextUtils.Toast(getActivity(), new JSONObject(object.toString()).getString("msg"));
                if (err.equals("0")) {
                    //关闭activity
                    MainActivity mainActivity = (MainActivity) ActivityManager.getActivity(MainActivity.class);
                    mainActivity.goToSupDem();

                    //跳转到供求详情
                    Intent intent1 = new Intent(getActivity(), SupDem_Detail_Activity.class);
                    startActivity(intent1);

                    ActivityManager.finishActivity(ReleaseActivity.class);
                }
            }
        } catch (Exception e) {
        }
    }

    @Override
    public void failCallBack(int type) {

    }

    //设置dialog属性
    public void setDialogWindowAttr() {
        WindowManager wm = (WindowManager) getActivity().getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics outMetrics = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(outMetrics);
        int width = outMetrics.widthPixels;
        int height = outMetrics.heightPixels;

        Window window = preDialog.getWindow();
        WindowManager.LayoutParams lp = window.getAttributes();
        lp.gravity = Gravity.CENTER;
        lp.width = (int) (width / 1.1);
        lp.height = (int) (height / 1.5);
        preDialog.getWindow().setAttributes(lp);
    }
}
