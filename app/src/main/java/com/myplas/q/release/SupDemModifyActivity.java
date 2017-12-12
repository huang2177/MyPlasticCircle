package com.myplas.q.release;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.myplas.q.R;
import com.myplas.q.common.utils.TextUtils;
import com.myplas.q.common.view.MyBottomSheetDialog;
import com.myplas.q.app.activity.BaseActivity;
import com.myplas.q.release.bean.PreViewBean;

/**
 * @author 黄双
 * @date 2017/11/13 0013
 */

public class SupDemModifyActivity extends BaseActivity implements
        View.OnClickListener {

    private Button button;
    private MyBottomSheetDialog dialog;
    private EditText mEditModel, mEditfName, mEditJh, mEditPirce, mTvNf;

    private Intent intent;
    private PreViewBean.DataBean bean;
    private String model, fName, jhd, pirce, nf;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_release_modify_layout);

        initTileBar();
        setTitle("供求修改");
        initView();
        showInfo();
    }

    private void initView() {
        intent = getIntent();
        bean = (PreViewBean.DataBean) intent.getSerializableExtra("preViewBean");

        button = F(R.id.modify_btn);
        mEditJh = F(R.id.release_ev_jh);
        mEditModel = F(R.id.release_ev_mode);
        mEditPirce = F(R.id.release_ev_pirce);
        mTvNf = F(R.id.release_ev_nowfutrue);
        mEditfName = F(R.id.release_ev_production);

        mTvNf.setOnClickListener(this);
        button.setOnClickListener(this);
    }

    private void showInfo() {
        pirce = bean.getPrice();
        model = bean.getModel();
        fName = bean.getVendor();
        jhd = bean.getStorehouse();
        nf = bean.getTransaction_type();

        mEditPirce.setText(pirce);
        mEditModel.setText(model);
        mEditfName.setText(fName);
        mEditJh.setText(jhd);

        mTvNf.setText(nf.equals("0")
                ? "现货"
                : "期货");

        mEditJh.setSelection(jhd.length());
        mEditModel.setSelection(model.length());
        mEditPirce.setSelection(pirce.length());
        mEditfName.setSelection(fName.length());
    }

    /**
     * 获取页面数据 并返回
     */

    private void getDataFromPage() {
        if (!isInputContent()) {
            return;
        }
        bean.setModel(model);
        bean.setPrice(pirce);
        bean.setVendor(fName);
        bean.setStorehouse(jhd);
        bean.setTransaction_type(nf);

        intent.putExtra("bean", bean);
        setResult(100, intent);
        finish();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.modify_btn:
                getDataFromPage();
                break;
            case R.id.buttom_dialog_tv1:
                dialog.dismiss();
                nf = "0";
                mTvNf.setText("现货");
                break;
            case R.id.buttom_dialog_tv2:
                dialog.dismiss();
                nf = "1";
                mTvNf.setText("期货");
                break;
            case R.id.release_ev_nowfutrue:
                openBottom();
                break;
            default:
                break;
        }
    }


    private void openBottom() {
        TextView textView1, textView2;
        View view = View.inflate(this, R.layout.release_buttom_dialog_layout, null);
        textView1 = (TextView) view.findViewById(R.id.buttom_dialog_tv1);
        textView2 = (TextView) view.findViewById(R.id.buttom_dialog_tv2);

        textView1.setOnClickListener(this);
        textView2.setOnClickListener(this);
        textView1.setText("现货");
        textView2.setText("期货");

        dialog = new MyBottomSheetDialog(this);
        dialog.setContentView(view);
        dialog.show();
    }

    /**
     * 判断是否填入数据
     *
     * @return
     */
    public boolean isInputContent() {
        model = mEditModel.getText().toString();
        pirce = mEditPirce.getText().toString();
        fName = mEditfName.getText().toString();
        jhd = mEditJh.getText().toString();

        if (!TextUtils.notEmpty(jhd)
                || !TextUtils.notEmpty(fName)
                || !TextUtils.notEmpty(model)
                || !TextUtils.notEmpty(pirce)) {
            TextUtils.toast(this, "请输入完整的数据！");
            return false;
        } else {
            return true;
        }
    }
}
