package com.myplas.q.release.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.myplas.q.R;
import com.myplas.q.common.appcontext.ActivityManager;
import com.myplas.q.common.utils.StatusUtils;
import com.myplas.q.guide.activity.BaseActivity;
import com.myplas.q.common.netresquset.ResultCallBack;
import com.myplas.q.common.utils.SharedUtils;
import com.myplas.q.release.adapter.Release_Demand_ListviewAdapter;
import com.myplas.q.release.adapter.Release_Supply_ListviewAdapter;
import com.myplas.q.common.api.API;
import com.myplas.q.supdem.Beans.Supply_DemandBean;
import com.umeng.analytics.MobclickAgent;

import java.util.HashMap;
import java.util.Map;

/**
 * 编写： 黄双
 * 电话：15378412400
 * 邮箱：15378412400@163.com
 * 时间：2017/3/21 09:34
 */
public class ReleaseActivity extends BaseActivity implements View.OnClickListener, ResultCallBack {
    private ListView listView_qg, listView_gj;
    private TextView textView_gq, textView_gj;
    private ImageButton imageButton_back;
    private Release_Demand_ListviewAdapter release_demandAdapter;
    private Release_Supply_ListviewAdapter release_supplyAdapter;
    private Supply_DemandBean supply_demandBean;
    private SharedUtils sharedUtils;
    private Map<String, String> map = new HashMap<>();
    private boolean isfinish = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StatusUtils.setStatusBar(this, false, false);
        StatusUtils.setStatusTextColor(true, this);
        setContentView(R.layout.layout_release_activity);
        initView();
        getSupplyDemandList("1", 1);
        getSupplyDemandList("2", 2);
        ActivityManager.addActivity(this);
    }

    public void initView() {
        sharedUtils = SharedUtils.getSharedUtils();
        listView_qg = (ListView) findViewById(R.id.fb_qg_listview);
        listView_gj = (ListView) findViewById(R.id.fb_gj_listview);
        textView_gq = (TextView) findViewById(R.id.fb_qg_img);
        textView_gj = (TextView) findViewById(R.id.fb_gj_img);
        imageButton_back = (ImageButton) findViewById(R.id.fb_back_imgbtn);
        textView_gq.setOnClickListener(this);
        textView_gj.setOnClickListener(this);
        imageButton_back.setOnClickListener(this);
    }

    @Override
    protected void onRestart() {
        super.onRestart();
//        isfinish = sharedUtils.getBoolean(this, "isfinish");
//        if (isfinish) {
//            sharedUtils.setBooloean(this, "isfinish", false);
//            this.finish();
//        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.fb_qg_img:
                Intent intent = new Intent(this, ReleaseSupDemActivity.class);
                intent.putExtra("type", "1");
                startActivity(intent);
                break;
            case R.id.fb_gj_img:
                Intent intent1 = new Intent(this, ReleaseSupDemActivity.class);
                intent1.putExtra("type", "2");
                startActivity(intent1);
                break;
            case R.id.fb_back_imgbtn:
                finish();
                break;
        }
    }

    public void getSupplyDemandList(String type, int t) {
        map.put("token", sharedUtils.getData(this, "token"));
        map.put("type", type);
        map.put("page", "1");
        map.put("size", "5");
        postAsyn(this, API.BASEURL + API.SUPPLYDEMAND_LIST, map, this, t);
    }

    @Override
    public void callBack(Object object, int type) {
        try {
            Gson gson = new Gson();
            //供给
            if (type == 2) {
                supply_demandBean = gson.fromJson(object.toString(), Supply_DemandBean.class);
                release_demandAdapter = new Release_Demand_ListviewAdapter(this, supply_demandBean.getData());
                listView_gj.setAdapter(release_demandAdapter);
            }
            //求购
            if (type == 1) {
                supply_demandBean = gson.fromJson(object.toString(), Supply_DemandBean.class);
                release_supplyAdapter = new Release_Supply_ListviewAdapter(this, supply_demandBean.getData());
                listView_qg.setAdapter(release_supplyAdapter);
            }
        } catch (Exception e) {
        }
    }

    @Override
    public void failCallBack(int type) {

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
