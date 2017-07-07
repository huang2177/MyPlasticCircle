package com.myplas.q.myinfo.activity;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.myplas.q.R;
import com.myplas.q.common.api.API;
import com.myplas.q.common.netresquset.ResultCallBack;
import com.myplas.q.common.utils.SharedUtils;
import com.myplas.q.common.utils.TextUtils;
import com.myplas.q.guide.activity.BaseActivity;
import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.SendMessageToWX;
import com.tencent.mm.sdk.openapi.WXAPIFactory;
import com.tencent.mm.sdk.openapi.WXMediaMessage;
import com.tencent.mm.sdk.openapi.WXWebpageObject;
import com.tencent.mm.sdk.platformtools.Util;
import com.umeng.analytics.MobclickAgent;

import java.util.HashMap;
import java.util.Map;

/**
 * 编写： 黄双
 * 电话：15378412400
 * 邮箱：15378412400@163.com
 * 时间：2017/4/14 15:32
 */
public class Ed_Call_Dialog_Activity extends BaseActivity implements View.OnClickListener{
    private View view;
    private TextView ed_call1, ed_call2,ed_cancle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_ed_call_activity);
        getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);

        view = findViewById(R.id.share_view);
        ed_call1= (TextView) findViewById(R.id.ed_call1);
        ed_call2= (TextView) findViewById(R.id.ed_call2);
        ed_cancle= (TextView) findViewById(R.id.ed_cancle);

        view.setOnClickListener(this);
        ed_call1.setOnClickListener(this);
        ed_call2.setOnClickListener(this);
        ed_cancle.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ed_call1:
                call("400-6129-965");
                break;
            case R.id.ed_call2:
                call("021-61070985");
                break;
            case R.id.ed_cancle:
            case R.id.share_view:
                finish();
                break;
        }
    }
    public void onResume() {
        super.onResume();
        MobclickAgent.onResume(this);
    }
    public void onPause() {
        super.onPause();
        MobclickAgent.onPause(this);
    }
}
