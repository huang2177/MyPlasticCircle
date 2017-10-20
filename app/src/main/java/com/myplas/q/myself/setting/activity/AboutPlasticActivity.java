package com.myplas.q.myself.setting.activity;

import android.os.Bundle;
import android.widget.TextView;

import com.myplas.q.R;
import com.myplas.q.common.utils.VersionUtils;
import com.myplas.q.guide.activity.BaseActivity;
import com.umeng.analytics.MobclickAgent;

/**
 * 编写： 黄双
 * 电话：15378412400
 * 邮箱：15378412400@163.com
 * 时间：2017/3/28 10:25
 */
public class AboutPlasticActivity extends BaseActivity {
    private TextView mTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_layout_setting_aboutplas);
        initTileBar();
        setTitle("关于塑料圈");
        init();
    }

    private void init() {
        mTextView = F(R.id.aboutplastic_versioncode);
        mTextView.setText("Android版V" + VersionUtils.getVersionName(this));
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
