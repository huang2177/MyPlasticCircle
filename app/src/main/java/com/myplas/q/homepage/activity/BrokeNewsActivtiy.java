package com.myplas.q.homepage.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.myplas.q.R;
import com.myplas.q.app.activity.BaseActivity;
import com.myplas.q.common.utils.TextUtils;
import com.myplas.q.common.utils.UCloudUtils;
import com.myplas.q.common.view.MyEditText;
import com.myplas.q.common.view.MyGridview;
import com.myplas.q.homepage.adapter.BlackListAdapter;
import com.myplas.q.homepage.adapter.BrokeNewsAdapter;
import com.yanzhenjie.album.Album;
import com.yanzhenjie.durban.Durban;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * @author 黄双
 * @date 2018/1/17 0017
 */

public class BrokeNewsActivtiy extends BaseActivity implements View.OnClickListener
        , MyEditText.OnTextWatcher
        , UCloudUtils.UCloudListener {

    private Button button;
    private MyGridview mGridview;
    private MyEditText editTheme, editDetail;

    private ArrayList<String> list;
    private BrokeNewsAdapter adapter;


    private UCloudUtils uCloudUtils;

    private String mTheme, mDetail;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blacklist_layout);

        initTileBar();
        setTitle("我要爆料");
        initView();
    }

    private void initView() {
        button = F(R.id.blacklist_btn);
        mGridview = F(R.id.blacklist_gv);
        editTheme = F(R.id.blacklist_edit_theme);
        editDetail = F(R.id.blacklist_edit_detail);

        button.setOnClickListener(this);
        editTheme.addOnTextWatcher(this);
        editDetail.addOnTextWatcher(this);
        uCloudUtils = new UCloudUtils(this, this);

        list = new ArrayList<>();
        adapter = new BrokeNewsAdapter(this, list);
        mGridview.setAdapter(adapter);

        mGridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.blacklist_btn:
                break;
            default:
                break;
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        try {
            if (requestCode == 100) {
                list.addAll(Album.parseResult(data));
                adapter.setList(list);
                adapter.notifyDataSetChanged();

                putFile();
                changeBtnColor();
            }
        } catch (Exception e) {

        }
    }

    private void putFile() {
        for (int i = 0; i < list.size(); i++) {
            uCloudUtils.putFile(new File(list.get(i)), i);
        }
    }

    /**
     * 实时改变button颜色
     */
    private void changeBtnColor() {
        button.setBackgroundResource(isWriteInfo()
                ? R.drawable.login_btn_shape_hl
                : R.drawable.login_btn_shape);
    }

    /**
     * 判断用户是否填写信息
     *
     * @return
     */
    private boolean isWriteInfo() {
        mTheme = editTheme.getText().toString();
        mDetail = editDetail.getText().toString();

        return TextUtils.notEmpty(mTheme) && TextUtils.notEmpty(mDetail) && list.size() != 0;
    }

    @Override
    public void onTextChanged(View v, String s) {
        changeBtnColor();
    }

    @Override
    public void uCloudSucess(int type, String flieName) {

    }

    @Override
    public void uCloudProcess(int type, int value) {
        adapter.setProgress(type, value);
    }
}
