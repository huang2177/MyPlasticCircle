package com.myplas.q.myself.setting.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;

import com.myplas.q.R;
import com.myplas.q.common.utils.NetUtils;
import com.myplas.q.common.utils.TextUtils;
import com.myplas.q.app.activity.BaseActivity;
import com.myplas.q.common.view.EditTextField;
import com.myplas.q.myself.setting.adapter.SettingSex_RegionAdapter;

import java.util.Arrays;
import java.util.List;


/**
 * 编写： 黄双
 * 电话：15378412400
 * 邮箱：15378412400@163.com
 * 时间：2017/3/28 10:25
 */
public class DataCommonActivity extends BaseActivity implements View.OnClickListener {

    private int position;
    private List<String> mList;
    private String type, hint, dataBack;
    private SettingSex_RegionAdapter mAdapter;

    private ListView mListView;
    private ImageView mImageView;
    private EditTextField mTextField_other;


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

        mListView = F(R.id.datacommon_input_lv);
        mImageView = F(R.id.datacommon_img_logistics);
        mTextField_other = F(R.id.datacommon_input_edit);

        mTVRight.setOnClickListener(this);

        if (type.equals("2")) {
            showInPutKeybord(mTextField_other);
            setLeftTVVisibility(View.VISIBLE);
            setRightTVVisibility(View.VISIBLE);
            mListView.setVisibility(View.GONE);
            mTextField_other.setVisibility(View.VISIBLE);
            mTextField_other.setText(hint);
            mTextField_other.setSelection(hint.length());
        } else {
            if (type.equals("0")) {           //性别
                mList = Arrays.asList("男", "女");
            } else {                          //所属地区
                mList = Arrays.asList("华东", "华南", "华北", "其他");
            }
            mTextField_other.setVisibility(View.GONE);
            mListView.setVisibility(View.VISIBLE);
            position = Integer.parseInt(hint);

            mAdapter = new SettingSex_RegionAdapter(this, mList, position);
            mListView.setAdapter(mAdapter);
        }

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                mAdapter.changeImg(position);
                Intent intent = new Intent("com.broadcast.databack");
                if (type.equals("0")) {
                    intent.putExtra("type", "0");
                    dataBack = position + "";

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
                        default:
                            break;
                    }
                }
                intent.putExtra("updateData", dataBack);
                sendBroadcast(intent);
            }
        });

    }

    @Override
    public void onClick(View v) {
        dataBack = mTextField_other.getText().toString();
        if (NetUtils.isNetworkStateed(DataCommonActivity.this)) {
            if (TextUtils.notEmpty(dataBack)) {
                Intent intent = new Intent();
                intent.putExtra("updateData", dataBack);
                setResult(1, intent);
                DataCommonActivity.this.finish();
            }
        }
    }
}
