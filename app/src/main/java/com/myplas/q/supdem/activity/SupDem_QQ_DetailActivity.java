package com.myplas.q.supdem.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.text.InputType;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.SpannedString;
import android.text.style.AbsoluteSizeSpan;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.myplas.q.R;
import com.myplas.q.common.api.API;
import com.myplas.q.common.netresquset.ResultCallBack;
import com.myplas.q.common.utils.SharedUtils;
import com.myplas.q.common.utils.TextUtils;
import com.myplas.q.common.view.MyListview;
import com.myplas.q.guide.activity.BaseActivity;
import com.myplas.q.supdem.Beans.DeliverPriceBean;
import com.myplas.q.supdem.Beans.ReplyBean;
import com.myplas.q.supdem.Beans.SupplyDemandDetailBean;
import com.myplas.q.supdem.adapter.SupDem_Search_QQ_Detail_Adapter;
import com.myplas.q.supdem.adapter.XQ_ListView_CHJAdapter;
import com.myplas.q.supdem.adapter.XQ_ListView_HFAdapter;
import com.umeng.analytics.MobclickAgent;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static android.R.attr.type;
import static com.myplas.q.R.id.btn_chj;
import static com.myplas.q.R.id.img_gong_qiu;
import static com.myplas.q.R.id.xq_listview_chj;

/**
 * 编写： 黄双
 * 电话：15378412400
 * 邮箱：15378412400@163.com
 * 时间：2017/3/19 15:44
 */

public class SupDem_QQ_DetailActivity extends BaseActivity implements View.OnClickListener, ResultCallBack {
    private SharedUtils sharedUtils;
    private ImageView img_tell,img_zx,img_find;
    private MyListview listview_tell,listView_zx,listView_find;
    private LinearLayout layout_zx, layout_find,layout_wx,layout_tell,layout_zx_more;
    private TextView text_gs, text_hw,text_ph,text_chd, text_jg, text_xq, text_qq,title_rs;

    private boolean isClicked1,isClicked2,isClicked3;
    private SupDem_Search_QQ_Detail_Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.supdem_detail_qq_activity);
        goBack(findViewById(R.id.back));
        initView();
        //getNetData();

    }
    public void initView() {
        isClicked1=false;
        isClicked2=false;
        isClicked3=false;
        sharedUtils = SharedUtils.getSharedUtils();

        title_rs=F(R.id.title_rs);
        text_gs = F(R.id.supdem_qq_text_gs);
        text_hw = F(R.id.supdem_qq_text_wz);
        text_ph = F(R.id.supdem_qq_text_ph);
        text_chd = F(R.id.supdem_qq_text_chd);
        text_jg = F(R.id.supdem_qq_text_jg);
        text_xq = F(R.id.supdem_qq_text_xq);
        text_qq = F(R.id.supdem_qq_text_qq);

        img_zx = F(R.id.supdem_qq_img_zx);
        img_find = F(R.id.supdem_qq_img_find);
        img_tell = F(R.id.supdem_qq_img_tell);

        listView_zx = F(R.id.supdem_qq_listview_zx);
        listView_find = F(R.id.supdem_qq_listview_find);
        listview_tell = F(R.id.supdem_qq_listview_tell);

        layout_zx = F(R.id.supdem_qq_layout_zx);
        layout_find = F(R.id.supdem_qq_layout_find);
        layout_wx = F(R.id.supdem_qq_layout_wx);
        layout_tell = F(R.id.supdem_qq_layout_tell);
        layout_zx_more=F(R.id.supdem_qq_layout_zx_more);

        layout_zx.setOnClickListener(this);
        layout_find.setOnClickListener(this);
        layout_wx.setOnClickListener(this);
        layout_tell.setOnClickListener(this);
    }
    //获取首页数据
    public void getNetData() {
        try {
            Map<String,String>map=new HashMap<>();
            map.put("token", sharedUtils.getData(this, "token"));
            map.put("user_id", getIntent().getStringExtra("userid"));
            map.put("id", getIntent().getStringExtra("id"));
            postAsyn(this, API.BASEURL + API.GET_RELEASE_MSG_DETAIL, map, this, 1);
        } catch (Exception e) {
        }
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.supdem_qq_layout_zx:
                if (!isClicked1) {
                    isClicked1=true;
                    listView_zx.setVisibility(View.VISIBLE);
                    layout_zx_more.setVisibility(View.VISIBLE);
                    img_zx.setImageResource(R.drawable.icon_more_hl);
                    adapter=new SupDem_Search_QQ_Detail_Adapter(this,null,1);
                    listView_zx.setAdapter(adapter);
                } else {
                    isClicked1=false;
                    listView_zx.setVisibility(View.GONE);
                    layout_zx_more.setVisibility(View.GONE);
                    img_zx.setImageResource(R.drawable.icon_more);
                }
                break;
            case R.id.supdem_qq_layout_find:
                if (!isClicked2) {
                    isClicked2=true;
                    listView_find.setVisibility(View.VISIBLE);
                    img_find.setImageResource(R.drawable.icon_more_hl);
                    adapter=new SupDem_Search_QQ_Detail_Adapter(this,null,2);
                    listView_find.setAdapter(adapter);
                } else {
                    isClicked2=false;
                    listView_find.setVisibility(View.GONE);
                    img_find.setImageResource(R.drawable.icon_more);
                }
                break;
            case R.id.supdem_qq_layout_wx:
                Intent intent=new Intent(this,Physical_Property_Activity.class);
                startActivity(intent);
                break;
            case R.id.supdem_qq_layout_tell:
                if (!isClicked3) {
                    isClicked3=true;
                    listview_tell.setVisibility(View.VISIBLE);
                    listview_tell.setSelection(layout_tell.getChildCount());
                    img_tell.setImageResource(R.drawable.icon_more_hl);
                    adapter=new SupDem_Search_QQ_Detail_Adapter(this,null,3);
                    listview_tell.setAdapter(adapter);
                } else {
                    isClicked3=false;
                    listview_tell.setVisibility(View.GONE);
                    img_tell.setImageResource(R.drawable.icon_more);
                }

                break;
        }
    }

    @Override
    public void callBack(Object object, int type) {
        try {
            Gson gson = new Gson();
            if (type == 1) {

            }
            if (type == 2 && new JSONObject(object.toString()).getString("err").equals("0")) {

            }
            //出价消息的结果
            if (type == 3 && new JSONObject(object.toString()).getString("err").equals("0")) {

            }

        } catch (Exception e) {
        }
    }



    @Override
    public void failCallBack(int type) {

    }

    public void showInfo(SupplyDemandDetailBean supplyDemandDetailBean) {
       // Glide.with(this).load(supplyDemandDetailBean.getData().getInfo().getThumb()).placeholder(R.drawable.contact_image_defaul_male).into(img_tx);
        String s = supplyDemandDetailBean.getData().getInfo().getC_name();
        text_gs.setText("  " + s );
//        text_name.setText("  " + supplyDemandDetailBean.getData().getInfo().getName());
//        text_fs.setText("  粉丝：" + supplyDemandDetailBean.getData().getInfo().getFans() + "   等级：" + supplyDemandDetailBean.getData().getInfo().getMember_level());
//        text_shj.setText(supplyDemandDetailBean.getData().getInput_time());
//        text_chj.setText("出价(" + supplyDemandDetailBean.getData().getDeliverPriceCount() + ")");
//        text_hf.setText("回复(" + supplyDemandDetailBean.getData().getSaysCount() + ")");
    }

    public <T extends View> T F(int id) {
        return (T) findViewById(id);
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
