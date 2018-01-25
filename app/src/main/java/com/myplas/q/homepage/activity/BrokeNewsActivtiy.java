package com.myplas.q.homepage.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.myplas.q.R;
import com.myplas.q.app.activity.BaseActivity;
import com.myplas.q.common.api.API;
import com.myplas.q.common.netresquset.ResultCallBack;
import com.myplas.q.common.utils.TextUtils;
import com.myplas.q.common.utils.UCloudUtils;
import com.myplas.q.common.view.EmptyView;
import com.myplas.q.common.view.MyEditText;
import com.myplas.q.common.view.MyGridview;
import com.myplas.q.homepage.adapter.BlackListAdapter;
import com.myplas.q.homepage.adapter.BrokeNewsAdapter;
import com.yanzhenjie.album.Album;
import com.yanzhenjie.durban.Durban;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 黄双
 * @date 2018/1/17 0017
 */

public class BrokeNewsActivtiy extends BaseActivity implements View.OnClickListener
        , MyEditText.OnTextWatcher
        , UCloudUtils.UCloudListener, ResultCallBack {

    private Button button;
    private EmptyView emptyView;
    private MyGridview mGridview;
    private MyEditText editTheme, editDetail;

    private ArrayList<String> list;
    private BrokeNewsAdapter adapter;


    private UCloudUtils uCloudUtils;

    private String mTheme, mDetail;
    private StringBuffer stringPath;

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
        emptyView = F(R.id.blacklist_emptyView);
        editTheme = F(R.id.blacklist_edit_theme);
        editDetail = F(R.id.blacklist_edit_detail);

        button.setOnClickListener(this);
        editTheme.addOnTextWatcher(this);
        editDetail.addOnTextWatcher(this);
        uCloudUtils = new UCloudUtils(this, this);

        list = new ArrayList<>();
        adapter = new BrokeNewsAdapter(this, list);
        mGridview.setAdapter(adapter);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.blacklist_btn:
                commit();
                button.setClickable(false);
                button.setBackgroundResource(R.drawable.login_btn_shape);
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

    /**
     * 图片上传后回调服务器
     */
    private void uploadNotify(String path, String type) {
        Map<String, String> map = new HashMap(16);
        map.put("type", type);
        map.put("path", path);
        postAsyn(this, API.UPLOADNOTIFY, map, this, 1, false);
    }

    /**
     * 提交资料
     */
    private void commit() {
        if (isWriteInfo()) {
            Map<String, String> map = new HashMap(16);
            map.put("subject", mTheme);
            map.put("content", mDetail);
            map.put("illustration", stringPath.toString());
            postAsyn(this, API.BLACKLISTS, map, this, 2, false);
        } else {
            TextUtils.toast(this, "请先填写完整资料！");
        }
    }

    /**
     * 图片上传至ucloud
     */
    private void putFile() {
        stringPath = new StringBuffer();
        for (int i = 0; i < list.size(); i++) {
            uCloudUtils.putFile(new File(list.get(i)), i);
        }
    }

    @Override
    public void onTextChanged(View v, String s) {
        changeBtnColor();
    }

    @Override
    public void uCloudSucess(int type, String flieName) {
        stringPath.append(flieName).append(",");
        uploadNotify(flieName, "blacklist");
    }

    @Override
    public void uCloudProcess(int type, int value) {
        adapter.setProgress(type, value);
    }

    @Override
    public void callBack(Object object, int type) {
        try {
            JSONObject jsonObject = new JSONObject(object.toString());
            if (type == 2 && "0".equals(jsonObject.getString("code"))) {
                loadAnimation();
            }
        } catch (Exception e) {

        }
    }

    @Override
    public void failCallBack(int type, String message, int httpCode) {
        try {
            button.setClickable(true);
            button.setBackgroundResource(R.drawable.login_btn_shape_hl);

            JSONObject jsonObject = new JSONObject(message);
            TextUtils.toast(this, jsonObject.getString("message"));
        } catch (Exception e) {

        }
    }

    /**
     * 保存成功后显示审核中
     */
    private void loadAnimation() {
        emptyView.setVisibility(View.VISIBLE);
        emptyView.setMyManager(R.drawable.icon_auditing);
        emptyView.setNoMessageText1("提交成功，请等待客服人员审核！");
        emptyView.setNoMessageText("预计3个工作日内审核完毕，审核结果会短信通知到您的注册手机。");
        emptyView.startAnimation(AnimationUtils.loadAnimation(this, R.anim.in_top));
    }
}
