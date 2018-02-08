package com.myplas.q.release;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.google.gson.Gson;
import com.myplas.q.R;
import com.myplas.q.app.activity.BaseActivity;
import com.myplas.q.app.activity.MainActivity;
import com.myplas.q.common.api.API;
import com.myplas.q.common.appcontext.ActivityManager;
import com.myplas.q.common.appcontext.Constant;
import com.myplas.q.common.net.ResultCallBack;
import com.myplas.q.common.utils.SharedUtils;
import com.myplas.q.common.utils.TextUtils;
import com.myplas.q.common.view.CommonDialog;
import com.myplas.q.myself.supdem.MySupDemActivity;
import com.myplas.q.release.adapter.InstantReleaseLVAdapter;
import com.myplas.q.release.bean.PreViewBean;
import com.myplas.q.supdem.activity.SupDem_Detail_Activity;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 黄双
 * @date 2017/11/13 0013
 */

public class InstantReleaseActivity extends BaseActivity implements ResultCallBack
        , InstantReleaseLVAdapter.MyInterface, CommonDialog.DialogShowInterface {
    private Button button;
    private ListView listView;

    private PreViewBean preViewBean;
    private InstantReleaseLVAdapter adapter;
    private List<PreViewBean.DataBean> mList;
    private int modifyPosition, deletePosition;

    private String id;
    private SharedUtils mSharedUtils;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_release_instantrelease_layout);
        ActivityManager.addActivity(this);

        initTileBar();
        setTitle("供求预览");
        initView();
    }

    private void initView() {
        button = F(R.id.release_btn);
        listView = F(R.id.instant_lv);
        mSharedUtils = SharedUtils.getSharedUtils();

        id = getIntent().getStringExtra("id");
        preViewBean = (PreViewBean) getIntent().getSerializableExtra("preViewBean");
        mList = preViewBean.getData();

        adapter = new InstantReleaseLVAdapter(this, mList, this);
        listView.setAdapter(adapter);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                instantRelease();
            }
        });
    }

    /**
     * 解析后发布
     */

    public void instantRelease() {
        Map<String, String> map = new HashMap<>();
        map.put("channel", "1");
        map.put("mode", "3");
        map.put("data", new Gson().toJson(mList));
        map.put("type", getIntent().getStringExtra("type"));
        postAsyn(this, API.RELEASE_MSG, map, this, 1);
    }

    @Override
    public void callBack(Object object, int type) {
        try {
            Gson gson = new Gson();
            JSONObject jsonObject = new JSONObject(object.toString());
            String err = jsonObject.getString("code");
            if ("0".equals(err)) {
                //关闭activity
                MainActivity mainActivity = (MainActivity) ActivityManager.getActivity(MainActivity.class);
                mainActivity.goToSupDem();

                //跳转到供求详情
                Intent intent1 = new Intent(this, SupDem_Detail_Activity.class);
                intent1.putExtra("id", jsonObject.getString("id"));
                intent1.putExtra("userid", mSharedUtils.getData(this, Constant.USERID));
                startActivity(intent1);

                finish();
                ActivityManager.finishActivity(ReleaseActivity.class);
                if (id != null) {
                    ActivityManager.finishActivity(MySupDemActivity.class);
                }
            } else {
                TextUtils.toast(this, jsonObject.getString("message"));
            }
        } catch (Exception e) {
        }
    }

    @Override
    public void failCallBack(int type, String message, int httpCode) {
        try {
            JSONObject jsonObject = new JSONObject(message);
            if (type == 1 && httpCode == 400) {
                TextUtils.toast(this, jsonObject.getString("message"));
            }
        } catch (Exception e) {

        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == 100 && data != null) {
            mList.remove(modifyPosition);
            mList.add((PreViewBean.DataBean) data.getSerializableExtra("bean"));
            adapter = new InstantReleaseLVAdapter(this, mList, this);
            listView.setAdapter(adapter);
        }
    }

    @Override
    public void delete(int position) {
        deletePosition = position;
        CommonDialog commonDialog = new CommonDialog();
        commonDialog.showDialog(this, "确定删除?", 1, this);
    }

    @Override
    public void modify(int position) {
        modifyPosition = position;
        Intent intent = new Intent(this, SupDemModifyActivity.class);
        intent.putExtra("preViewBean", preViewBean.getData().get(position));
        startActivityForResult(intent, 1);
    }

    @Override
    public void dialogClick(int type) {
        if (type != -1) {
            if (mList.size() == 1) {
                finish();
            } else if (deletePosition < mList.size()) {
                mList.remove(deletePosition);
                adapter = new InstantReleaseLVAdapter(this, mList, this);
                listView.setAdapter(adapter);
            }
        }
    }
}
