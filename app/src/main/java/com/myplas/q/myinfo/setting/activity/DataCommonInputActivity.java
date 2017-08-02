package com.myplas.q.myinfo.setting.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.myplas.q.R;
import com.myplas.q.common.utils.TextUtils;
import com.myplas.q.common.utils.VersionUtils;
import com.myplas.q.guide.activity.BaseActivity;
import com.optimus.edittextfield.EditTextField;
import com.umeng.analytics.MobclickAgent;

import rx.Subscriber;

/**
 * 编写： 黄双
 * 电话：15378412400
 * 邮箱：15378412400@163.com
 * 时间：2017/3/28 10:25
 */
public class DataCommonInputActivity extends BaseActivity {
    private EditTextField mTextField;

    private Subscriber mSubscriber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_layout_datacommon_input);
        initTileBar();
        setLeftTVVisibility(View.VISIBLE);
        setRightTVVisibility(View.VISIBLE);
        setTitle(getIntent().getStringExtra("title"));
        init();

    }

    private void init() {
        mTextField = F(R.id.datacommon_input_edit);
        mTextField.setHint(getIntent().getStringExtra("hint"));

        mSubscriber = new Subscriber() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(Object o) {
                String text = mTextField.getText().toString();
                if (TextUtils.isNullOrEmpty(text)) {
                    Intent intent = new Intent();
                    intent.putExtra("updateData", text);
                    setResult(1, intent);
                    DataCommonInputActivity.this.finish();
                } else {
                    TextUtils.Toast(DataCommonInputActivity.this, "你还没输入内容！");
                }
            }
        };

        setObserver(mSubscriber, "");
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
