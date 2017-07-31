package com.myplas.q.myinfo.setting.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.ListView;

import com.myplas.q.R;
import com.myplas.q.guide.activity.BaseActivity;

/**
 * Created by Administrator on 2017/7/31 0031.
 */

public class SettingActivity extends BaseActivity {
    private ListView mListView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_layout_setting);
        initTileBar();
        setTitle("设置");
        mListView = F(R.id.setting_listview);
    }

}
