package com.myplas.q.homepage.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.myplas.q.R;
import com.myplas.q.app.activity.BaseActivity;

/**
 * @author 黄双
 * @date 2018/1/17 0017
 */

public class BlackListDetailActivity extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blacklistdetail_layout);

        initTileBar();
    }
}
