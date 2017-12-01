package com.myplas.q.myself.credit.activity;

import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import com.myplas.q.R;
import com.myplas.q.app.activity.BaseActivity;
import com.umeng.analytics.MobclickAgent;

/**
 * 编写： 黄双
 * 电话：15378412400
 * 邮箱：15378412400@163.com
 * 时间：2017/4/14 15:32
 */
public class Ed_Call_Dialog_Activity extends BaseActivity implements View.OnClickListener {
    private View view;
    private TextView ed_call1, ed_call2, ed_cancle;

    //3333333

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_ed_call_activity);
        getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);

        view = findViewById(R.id.share_view);
        ed_call1 = (TextView) findViewById(R.id.ed_call1);
        ed_call2 = (TextView) findViewById(R.id.ed_call2);
        ed_cancle = (TextView) findViewById(R.id.ed_cancle);

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
