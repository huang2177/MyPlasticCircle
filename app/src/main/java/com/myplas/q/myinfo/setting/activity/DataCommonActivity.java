package com.myplas.q.myinfo.setting.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.myplas.q.R;
import com.myplas.q.common.utils.TextUtils;
import com.myplas.q.guide.activity.BaseActivity;
import com.myplas.q.myinfo.setting.adapter.SettingSex_RegionAdapter;
import com.optimus.edittextfield.EditTextField;
import com.umeng.analytics.MobclickAgent;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

import rx.Observable;
import rx.Subscriber;

/**
 * 编写： 黄双
 * 电话：15378412400
 * 邮箱：15378412400@163.com
 * 时间：2017/3/28 10:25
 */
public class DataCommonActivity extends BaseActivity {

    private int position;
    private List<String> mList;
    private String type, hint, dataBack;
    private SettingSex_RegionAdapter mAdapter;
    private Subscriber mSubscriber, subscriber_back;

    private ListView mListView;
    private EditTextField mTextField;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_layout_datacommon_input);
        init();
    }

    private void init() {
        initTileBar();
        setTitle(getIntent().getStringExtra("title"));

        type = getIntent().getStringExtra("type");
        hint = getIntent().getStringExtra("hint");
        subscriber_back = (Subscriber) getIntent().getSerializableExtra("Subscriber");

        mListView = F(R.id.datacommon_input_lv);
        mTextField = F(R.id.datacommon_input_edit);

        if (type.equals("2")) {  //需要输入
            setLeftTVVisibility(View.VISIBLE);
            setRightTVVisibility(View.VISIBLE);
            mListView.setVisibility(View.GONE);
            mTextField.setVisibility(View.VISIBLE);
            mTextField.setText(hint);
            mTextField.setSelection(hint.length());
        } else {
            if (type.equals("0")) { //性别
                mList = Arrays.asList("男", "女");
            } else { //所属地区
                mList = Arrays.asList("华东", "华南", "华北", "其他");
            }
            mTextField.setVisibility(View.GONE);
            mListView.setVisibility(View.VISIBLE);
            position = Integer.parseInt(hint);
            mAdapter = new SettingSex_RegionAdapter(this, mList, position);
            mListView.setAdapter(mAdapter);
        }

        //确定按钮事件的订阅
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
                    DataCommonActivity.this.finish();
                }
            }
        };
        setObserver(mSubscriber, "");


        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                mAdapter.changeImg(position);
                Intent intent = new Intent("com.broadcast.databack");
                if (type.equals("0")) {
                    intent.putExtra("type", "0");
                    switch (position) {
                        case 0:
                            dataBack = "0";
                            break;
                        case 1:
                            dataBack = "1";
                            break;
                    }

                } else {
                    intent.putExtra("type", "1");

                    switch (position) {
                        case 0:
                            dataBack = "EC";
                            break;
                        case 1:
                            dataBack = "SC";
                            break;
                        case 2:
                            dataBack = "NC";
                            break;
                        case 3:
                            dataBack = "OT";
                            break;
                    }
                }
                intent.putExtra("updateData", dataBack);
                sendBroadcast(intent);
            }
        });
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
