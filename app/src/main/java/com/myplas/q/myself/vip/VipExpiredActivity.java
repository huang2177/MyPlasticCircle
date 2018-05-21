package com.myplas.q.myself.vip;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.myplas.q.R;
import com.myplas.q.app.activity.BaseActivity;

/**
 * Created by Administrator on 2018/5/18 0018.
 */

public class VipExpiredActivity extends BaseActivity {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_layout_vip_expired);

        initTileBar();
        setTitle("我的店铺");
    }

    public void call(View view) {
        call("05337859005");
    }
}
