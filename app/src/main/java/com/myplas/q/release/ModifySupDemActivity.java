package com.myplas.q.release;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.EditText;

import com.google.gson.Gson;
import com.myplas.q.R;
import com.myplas.q.common.appcontext.ActivityManager;
import com.myplas.q.common.appcontext.Constant;
import com.myplas.q.common.netresquset.ResultCallBack;
import com.myplas.q.common.utils.SharedUtils;
import com.myplas.q.common.utils.TextUtils;
import com.myplas.q.common.view.MyBottomSheetDialog;
import com.myplas.q.guide.activity.BaseActivity;
import com.myplas.q.guide.activity.MainActivity;
import com.myplas.q.supdem.activity.SupDem_Detail_Activity;

import org.json.JSONObject;

/**
 * @author 黄双
 * @date 2017/11/13 0013
 */

public class ModifySupDemActivity extends BaseActivity implements
        View.OnClickListener {
    private MyBottomSheetDialog dialog;
    private EditText mEditModel, mEditfName, meditJh, mEditPirce, mtvNf;

    private int which = 0;
    private SharedUtils mSharedUtils;
    private String mode, model, production, jhd, pirce, nf, type, id;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_release_modify_layout);

        initTileBar();
        setTitle("供求修改");
        initView();
    }

    private void initView() {
        mode = "2";
        mSharedUtils = SharedUtils.getSharedUtils();
        id = getIntent().getStringExtra("id");

        meditJh = F(R.id.release_ev_jh);
        mEditModel = F(R.id.release_ev_mode);
        mEditPirce = F(R.id.release_ev_pirce);
        mtvNf = F(R.id.release_ev_nowfutrue);
        mEditfName = F(R.id.release_ev_production);

        mtvNf.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {

    }

}
