package com.myplas.q.release;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.google.gson.Gson;
import com.myplas.q.R;
import com.myplas.q.common.api.API;
import com.myplas.q.common.appcontext.ActivityManager;
import com.myplas.q.common.appcontext.Constant;
import com.myplas.q.common.netresquset.ResultCallBack;
import com.myplas.q.common.utils.SharedUtils;
import com.myplas.q.common.utils.TextUtils;
import com.myplas.q.guide.activity.BaseActivity;
import com.myplas.q.guide.activity.MainActivity;
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

public class InstantReleaseActivity extends BaseActivity implements ResultCallBack {
    private Button button;
    private ListView listView;

    private String type;
    private PreViewBean bean;
    private InstantReleaseLVAdapter adapter;
    private List<PreViewBean.DataBean> mList;

    private SharedUtils mSharedUtils;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_release_instantrelease_layout);

        initTileBar();
        setTitle("供求预览");
        initView();
    }

    private void initView() {
        button = F(R.id.release_btn);
        listView = F(R.id.instant_lv);

        mSharedUtils = SharedUtils.getSharedUtils();

        type = getIntent().getStringExtra("type");
        bean = (PreViewBean) getIntent().getSerializableExtra("preViewBean");

        adapter = new InstantReleaseLVAdapter(this, bean.getData(), null, type);
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
        map.put("type", type);
        map.put("channel", "1");
        map.put("data", new Gson().toJson(bean.getData()));
        postAsyn(this, API.BASEURL + API.INSTANTRELEASE, map, this, 1);
    }

    @Override
    public void callBack(Object object, int type) {
        try {
            Gson gson = new Gson();
            JSONObject jsonObject = new JSONObject(object.toString());
            String err = jsonObject.getString("err");
            if ("0".equals(err)) {
                //关闭activity
                MainActivity mainActivity = (MainActivity) ActivityManager.getActivity(MainActivity.class);
                mainActivity.goToSupDem();

                //跳转到供求详情
                Intent intent1 = new Intent(this, SupDem_Detail_Activity.class);
                intent1.putExtra("id", jsonObject.getString("id"));
                intent1.putExtra("userid", mSharedUtils.getData(this, Constant.USERID));
                startActivity(intent1);

                ActivityManager.finishActivity(ReleaseActivity.class);
//                if (id != null) {
//                    ActivityManager.finishActivity(MySupDemActivity.class);
//                }
            } else {
                TextUtils.Toast(this, jsonObject.getString("msg"));
            }
        } catch (Exception e) {
        }
    }

    @Override
    public void failCallBack(int type) {

    }
}
