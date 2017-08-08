package com.myplas.q.myinfo.setting.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
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
import java.util.Timer;
import java.util.TimerTask;

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
    private boolean isLogistics, isClicked;
    private SettingSex_RegionAdapter mAdapter;
    private Subscriber mSubscriber, subscriber_back;
    private String type, hint, dataBack, logisticsStartData, logisticsEndData;

    private ListView mListView;
    private ImageView mImageView;
    private EditTextField mTextField_other, mTextField_start, mTextField_end;

    private LinearLayout mLayout_logistics;

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
        mImageView = F(R.id.datacommon_img_logistics);
        mTextField_end = F(R.id.datacommon_edit_ending);
        mTextField_other = F(R.id.datacommon_input_edit);
        mTextField_start = F(R.id.datacommon_edit_starting);
        mLayout_logistics = F(R.id.datacommon_ll_logistics);


        if (type.equals("2")) {
            showInPutKeybord();
            setLeftTVVisibility(View.VISIBLE);
            setRightTVVisibility(View.VISIBLE);
            if (hint.equals("logistics")) {  //如果是物流服务商
                isLogistics = true;
                mListView.setVisibility(View.GONE);
                mTextField_other.setVisibility(View.GONE);
                mLayout_logistics.setVisibility(View.VISIBLE);
            } else {
                isLogistics = false;            //其他企业类型
                mListView.setVisibility(View.GONE);
                mTextField_other.setVisibility(View.VISIBLE);
                mLayout_logistics.setVisibility(View.GONE);
                mTextField_other.setText(hint);
                mTextField_other.setSelection(hint.length());
            }
        } else {
            if (type.equals("0")) {           //性别
                mList = Arrays.asList("男", "女");
            } else {                          //所属地区
                mList = Arrays.asList("华东", "华南", "华北", "其他");
            }
            mTextField_other.setVisibility(View.GONE);
            mListView.setVisibility(View.VISIBLE);
            mLayout_logistics.setVisibility(View.GONE);
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
                if (!hint.equals("logistics")) {
                    String text1 = mTextField_other.getText().toString();
                    if (TextUtils.isNullOrEmpty(text1)) {
                        Intent intent = new Intent();
                        intent.putExtra("updateData", text1);
                        setResult(1, intent);
                        DataCommonActivity.this.finish();
                    }
                } else {
                    if (TextUtils.isNullOrEmpty(dataBack)) {
                        Intent intent = new Intent();
                        intent.putExtra("updateData", dataBack);
                        setResult(1, intent);
                        DataCommonActivity.this.finish();
                    }
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
        mLayout_logistics.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isClicked) {
                    isClicked = false;
                    dataBack = "";
                    mImageView.setImageResource(R.drawable.btn_radio);
                } else {
                    isClicked = true;
                    dataBack = "全国路线";
                    logisticsEndData = "";
                    logisticsStartData = "";
                    mTextField_end.setText("");
                    mTextField_start.setText("");
                    mImageView.setImageResource(R.drawable.btn_radio_checked);
                }
            }
        });
        mTextField_start.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (count == 0) {
                    logisticsStartData = "";
                } else {
                    isClicked = false;
                    logisticsStartData = s.toString();
                    mImageView.setImageResource(R.drawable.btn_radio);
                    dataBack = logisticsStartData + "-" + logisticsEndData;
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        mTextField_end.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (count == 0) {
                    logisticsEndData = "";
                } else {
                    isClicked = false;
                    logisticsEndData = s.toString();
                    mImageView.setImageResource(R.drawable.btn_radio);
                    dataBack = logisticsStartData + "-" + logisticsEndData;
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    private void showInPutKeybord() {
        mTextField_start.setFocusable(true);
        mTextField_start.setFocusableInTouchMode(true);
        mTextField_start.requestFocus();
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {

            @Override
            public void run() {
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.toggleSoftInput(0, InputMethodManager.SHOW_FORCED);
            }

        }, 200);
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
