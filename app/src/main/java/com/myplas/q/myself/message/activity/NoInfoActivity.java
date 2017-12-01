package com.myplas.q.myself.message.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.myplas.q.R;
import com.myplas.q.common.view.EmptyView;
import com.myplas.q.app.activity.BaseActivity;

/**
 * 作者:huangshuang
 * 事件 2017/10/20 0020.
 * 邮箱： 15378412400@163.com
 */

public class NoInfoActivity extends BaseActivity {
    private EmptyView mEmptyView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_layout_noinfo);
        initTileBar();
        setTitle("详细信息");

        mEmptyView = F(R.id.noinfo_emptyview);
        mEmptyView.setMyManager(R.drawable.icon_noinfo);
        mEmptyView.setNoMessageText(getIntent().getStringExtra("msg"));
    }
}
