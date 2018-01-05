package com.myplas.q.myself.supdem;

import android.content.Intent;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.myplas.q.R;
import com.myplas.q.common.api.API;
import com.myplas.q.common.appcontext.ActivityManager;
import com.myplas.q.common.netresquset.ResultCallBack;
import com.myplas.q.common.utils.SharedUtils;
import com.myplas.q.common.utils.TextUtils;
import com.myplas.q.app.activity.BaseActivity;
import com.myplas.q.myself.beans.MySupDemBean;
import com.myplas.q.release.ReleaseActivity;
import com.myplas.q.supdem.activity.SupDem_Detail_Activity;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 编写： 黄双
 * 电话：15378412400
 * 邮箱：15378412400@163.com
 * 时间：2017/3/23 16:22
 */
public class MySupDemActivity extends BaseActivity implements ResultCallBack
        , SupDemAdapter.MyInterface
        , View.OnClickListener {

    private String type;
    private SharedUtils sharedUtils;
    private int page = 1, visibleItemCount;

    private ListView listView;
    private TextView emptyText;
    private LinearLayout mLayout;
    private List<MySupDemBean.DataBean> mList;

    private SupDemAdapter supplyDemandAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_layout_myself_supdem);
        ActivityManager.addActivity(this);
        initTileBar();
        setTitle(getIntent().getStringExtra("title"));

        mList = new ArrayList<>();
        type = getIntent().getStringExtra("type");
        sharedUtils = SharedUtils.getSharedUtils();

        mLayout = F(R.id.empty_ll);
        emptyText = F(R.id.empty_text);
        listView = F(R.id.wd_gj_listview);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MySupDemActivity.this, SupDem_Detail_Activity.class);
                intent.putExtra("userid", mList.get(position).getUser_id());
                intent.putExtra("id", mList.get(position).getId());
                startActivity(intent);
            }
        });

        listView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                if (scrollState == AbsListView.OnScrollListener.SCROLL_STATE_IDLE
                        && listView.getCount() > visibleItemCount) {
                    if (view.getLastVisiblePosition() == view.getCount() - 1) {
                        page++;
                        getSupplyDemandList(String.valueOf(page), false);
                    }
                }
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                MySupDemActivity.this.visibleItemCount = visibleItemCount;
            }
        });

        getSupplyDemandList("1", true);
    }

    public void getSupplyDemandList(String page, boolean isShow) {
        Map<String, String> map = new HashMap<String, String>();
        map.put("type", type);
        map.put("page", page);
        map.put("size", "10");
        getAsyn(this, API.GET_MY_MSG, map, this, 1, isShow);
    }

    @Override
    public void callBack(Object object, int type) {
        try {
            Gson gson = new Gson();
            if (type == 1) {
                if ("0".equals(new JSONObject(object.toString()).getString("code"))) {
                    MySupDemBean supplyDemandBean = gson.fromJson(object.toString(), MySupDemBean.class);
                    if (page == 1) {
                        mLayout.setVisibility(View.GONE);
                        listView.setVisibility(View.VISIBLE);
                        mList.clear();
                        mList.addAll(supplyDemandBean.getData());
                        supplyDemandAdapter = new SupDemAdapter(this, supplyDemandBean.getData(), this);
                        listView.setAdapter(supplyDemandAdapter);
                    } else {
                        mList.addAll(supplyDemandBean.getData());
                        supplyDemandAdapter.setList(mList);
                        supplyDemandAdapter.notifyDataSetChanged();
                    }
                } else {
                    if (page == 1) {
                        listView.setVisibility(View.GONE);
                        mLayout.setVisibility(View.VISIBLE);
                        SpannableString spanableInfo = new SpannableString("您还未发布任何"
                                + (this.type.equals("0")
                                ? "供给"
                                : "求购")
                                + "信息，快去发布吧！");
                        spanableInfo.setSpan(new Clickable(this), 14, 16, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                        emptyText.setText(spanableInfo);
                        emptyText.setMovementMethod(LinkMovementMethod.getInstance());

                    } else {
                        TextUtils.toast(this, "没有更多数据了！");
                    }
                }
            }
        } catch (Exception e) {
        }
    }

    @Override
    public void failCallBack(int type, String message, int httpCode) {

    }

    @Override
    public void onClick(View v) {
        startActivity(new Intent(this, ReleaseActivity.class));
    }

    @Override
    public void deleteCallBack() {
        page = 1;
        getSupplyDemandList(String.valueOf(page), false);
    }

    class Clickable extends ClickableSpan {
        private final View.OnClickListener mListener;

        public Clickable(View.OnClickListener l) {
            mListener = l;
        }

        /**
         * 重写父类点击事件
         */
        @Override
        public void onClick(View v) {
            mListener.onClick(v);
        }

        /**
         * 重写父类updateDrawState方法  我们可以给TextView设置字体颜色,背景颜色等等...
         */
        @Override
        public void updateDrawState(TextPaint ds) {
            //ds.setTextSize(45);
            ds.setColor(getResources().getColor(R.color.color_white));
            ds.bgColor = getResources().getColor(R.color.color_regions_dialog);
        }
    }

}
