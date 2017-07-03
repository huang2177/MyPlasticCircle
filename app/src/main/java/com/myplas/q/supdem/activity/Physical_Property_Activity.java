package com.myplas.q.supdem.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.myplas.q.R;
import com.myplas.q.guide.activity.BaseActivity;
import com.myplas.q.supdem.adapter.Physical_Property_Adapter;

/**
 * 编写：黄双
 * 邮箱：15378412400@163.com
 * 时间： 2017/6/271459.
 */

public class Physical_Property_Activity extends BaseActivity {
    private ListView listView;
    private Physical_Property_Adapter adapter;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.physical_property_activity_layout);
        goBack(findViewById(R.id.back_img));
        listView= (ListView) findViewById(R.id.physical_listview);
        adapter=new Physical_Property_Adapter(this,null);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent=new Intent(Physical_Property_Activity.this,Physical_Property_Detail_Activity.class);
                startActivity(intent);
            }
        });
    }
}
