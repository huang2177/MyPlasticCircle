package com.myplas.q.supdem.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.google.gson.Gson;
import com.myplas.q.R;
import com.myplas.q.common.api.API;
import com.myplas.q.common.netresquset.ResultCallBack;
import com.myplas.q.guide.activity.BaseActivity;
import com.myplas.q.supdem.Beans.PhysicalBean;
import com.myplas.q.supdem.adapter.Physical_Property_Adapter;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 编写：黄双
 * 邮箱：15378412400@163.com
 * 时间： 2017/6/271459.
 */

public class Physical_Property_Activity extends BaseActivity implements ResultCallBack {
    private ListView listView;
    private List<PhysicalBean.DataBean> list;
    private Physical_Property_Adapter adapter;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.physical_property_activity_layout);
        goBack(findViewById(R.id.back_img));

        listView= (ListView) findViewById(R.id.physical_listview);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent=new Intent(Physical_Property_Activity.this,Physical_Detail_Activity.class);
                intent.putExtra("lid",list.get(position).getLid());
                startActivity(intent);
            }
        });
        getPhysical_Search();
    }

    //获取数据
    public void getPhysical_Search() {
        Map map = new HashMap();
        map.put("keywords", getIntent().getStringExtra("plastic_number"));
        postAsyn(this, API.BASEURL + API.PHYSICAL_SEARCH, map, this, 1);
    }

    @Override
    public void callBack(Object object, int type) {
        try {
            Log.e("------->", object.toString());
            if (new JSONObject(object.toString()).getString("err").equals("0")) {
                Gson gson = new Gson();
                PhysicalBean bean = gson.fromJson(object.toString(), PhysicalBean.class);
                list = bean.getData();
                adapter = new Physical_Property_Adapter(this, list);
                listView.setAdapter(adapter);
            }
        } catch (Exception e) {
        }
    }
    @Override
    public void failCallBack(int type) {

    }
}
