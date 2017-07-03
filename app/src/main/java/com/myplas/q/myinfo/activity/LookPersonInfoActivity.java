package com.myplas.q.myinfo.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.myplas.q.R;
import com.myplas.q.guide.activity.BaseActivity;
import com.myplas.q.guide.activity.BigImageViewActivity;
import com.myplas.q.common.utils.SharedUtils;
import com.myplas.q.myinfo.adapter.LookPerson_SupplyDemandAdapter;
import com.myplas.q.myinfo.beans.PersonSupplyDemadBean;
import com.umeng.analytics.MobclickAgent;

import java.util.ArrayList;
import java.util.List;

/**
 * 编写： 黄双
 * 电话：15378412400
 * 邮箱：15378412400@163.com
 * 时间：2017/3/23 13:39
 */
public class LookPersonInfoActivity extends BaseActivity implements View.OnClickListener{
    private ImageView  image_rz, image_tx;
    private TextView text_name, text_gs, text_dh,textView_title;
    private SharedUtils sharedUtils;
    private List<PersonSupplyDemadBean.DataBean> list;
    private ListView myListview_supply;
    private PersonSupplyDemadBean personinfo;
    private LookPerson_SupplyDemandAdapter p_adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lookpersoninfo_layout_activity);
        goBack(findViewById(R.id.back));
        initView();
    }
    public void initView() {
        sharedUtils = SharedUtils.getSharedUtils();
        image_tx = f(R.id.xq_tx);
        image_rz = f(R.id.xq_rz);
        textView_title=f(R.id.title_rs);
        text_name = f(R.id.lookperson_name);
        text_dh = f(R.id.lookperson_tel);
        text_gs = f(R.id.lookperson_company);
        myListview_supply=f(R.id.personinfo_more_supply_listview);
        personinfo= (PersonSupplyDemadBean) getIntent().getSerializableExtra("bean");
        if(personinfo!=null){
            showInfo(personinfo);
        }
        textView_title.setText(getIntent().getStringExtra("type"));
        image_tx.setOnClickListener(this);
    }
    public <T extends View> T f(int id) {
        return (T) findViewById(id);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.xq_tx:
                Intent In=new Intent(this, BigImageViewActivity.class);
                In.putExtra("imgurl",personinfo.getData().get(0).getThumb());
                startActivity(In);
                break;
        }
    }
    public void showInfo(PersonSupplyDemadBean mySelfInfo) {
        try {
            Glide.with(this).load(mySelfInfo.getData().get(0).getThumb()).placeholder(R.drawable.contact_image_defaul_male).into(image_tx);
            if(mySelfInfo.getData().get(0).getIs_pass().equals("0")){
                image_rz.setImageResource(R.drawable.icon_identity);
            }else if(mySelfInfo.getData().get(0).getIs_pass().equals("1")) {
                image_rz.setImageResource(R.drawable.icon_identity_hl);
            }
            text_name.setText(mySelfInfo.getData().get(0).getName());
            text_gs.setText(mySelfInfo.getData().get(0).getC_name());
            text_dh.setText("电话： " + getIntent().getStringExtra("tel"));
            list=new ArrayList<>();
            list.addAll(personinfo.getData());
            p_adapter=new LookPerson_SupplyDemandAdapter(this,list,getIntent().getStringExtra("type"));
            myListview_supply.setAdapter(p_adapter);
        } catch (Exception e) {
        }
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
