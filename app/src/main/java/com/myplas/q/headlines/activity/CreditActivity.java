package com.myplas.q.headlines.activity;

import android.os.Bundle;

import com.myplas.q.R;
import com.myplas.q.app.activity.BaseActivity;

/**
 * 编写： 黄双
 * 电话：15378412400
 * 邮箱：15378412400@163.com
 * 时间：2017/4/5 16:58
 */
public class CreditActivity extends BaseActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_cridit_activity);
        initTileBar();
        setTitle("授信说明");
    }

}
