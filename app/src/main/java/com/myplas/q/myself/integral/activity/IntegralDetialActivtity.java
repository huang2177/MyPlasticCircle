package com.myplas.q.myself.integral.activity;

import android.app.ActionBar;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.PopupWindow;

import com.google.gson.Gson;
import com.myplas.q.R;
import com.myplas.q.common.api.API;
import com.myplas.q.common.netresquset.ResultCallBack;
import com.myplas.q.common.utils.SharedUtils;
import com.myplas.q.common.utils.TextUtils;
import com.myplas.q.common.view.CustomPopupWindow;
import com.myplas.q.common.view.EmptyView;
import com.myplas.q.common.view.PinnedHeaderListView;
import com.myplas.q.guide.activity.BaseActivity;
import com.myplas.q.myself.beans.Integraldetialbean;
import com.myplas.q.myself.integral.adapter.Integral_Detial_GV_Adapter;
import com.myplas.q.myself.integral.adapter.Integral_Detial_LV_Adapter;
import com.umeng.analytics.MobclickAgent;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 编写： 黄双
 * 电话：15378412400
 * 邮箱：15378412400@163.com
 * 时间：2017/3/29 09:57
 */
public class IntegralDetialActivtity extends BaseActivity implements ResultCallBack, View.OnClickListener {
    private String type = "0";
    private boolean hasMoreData;
    private SharedUtils sharedUtils;
    private int page, positionItem, visibleItemCount;

    private GridView mGridView;
    private List<String> listString;
    private PinnedHeaderListView listView;
    private List<Integraldetialbean.DataBean.RecordsBean> list;
    private Integral_Detial_LV_Adapter mLVAdapter;
    private Integral_Detial_GV_Adapter mGVAdapter;

    private View view;
    private EmptyView mEmptyView;
    private CustomPopupWindow popupWindow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ingeral_detial_activity_layout);
        initTileBar();
        setTitle("塑豆明细");
        setRightTVText("全部");

        page = 1;
        hasMoreData = true;
        list = new ArrayList<>();
        sharedUtils = SharedUtils.getSharedUtils();


        mEmptyView = F(R.id.integral_detial_nrll);
        listView = F(R.id.integral_detial_listview);
        view = View.inflate(this, R.layout.popou_layout_ingeral_detial, null);
        mGridView = (GridView) view.findViewById(R.id.integral_detial_gv);

        mTVRight.setOnClickListener(this);


        listView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                if (scrollState == AbsListView.OnScrollListener.SCROLL_STATE_IDLE &&
                        listView.getCount() > visibleItemCount) {
                    if (view.getLastVisiblePosition() == view.getCount() - 1) {
                        if (hasMoreData) {
                            page++;
                            getData(String.valueOf(page), type);
                        } else {
                            TextUtils.Toast(IntegralDetialActivtity.this, "没有更多数据！");
                        }
                    }
                }
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                IntegralDetialActivtity.this.visibleItemCount = visibleItemCount;
            }
        });

        mGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                popupWindow.dismiss();
                mGVAdapter.changeImg(position);
                mTextView.setText(listString.get(position));

                page = 1;
                hasMoreData = true;
                positionItem = position;
                switch (position) {
                    case 0:
                        type = "0";
                        break;
                    case 1:
                        type = "2";
                        break;
                    case 2:
                        type = "4";
                        break;
                    case 3:
                        type = "7";
                        break;
                    case 4:
                        type = "3";
                        break;
                    case 5:
                        type = "1";
                        break;
                    case 6:
                        type = "6";
                        break;
                    case 7:
                        type = "5";
                        break;
                }
                getData("1", type);
            }
        });
        getData("1", type);
    }


    @Override
    public void onClick(View v) {
        showPopou();
    }

    public void getData(String page, String type) {
        Map<String, String> map = new HashMap<String, String>();
        map.put("token", sharedUtils.getData(this, "token"));
        map.put("page", page);
        map.put("type", type);
        postAsyn(this, API.BASEURL + API.SCORE_RECORD, map, this, 1);
    }

    public void showPopou() {
        setBackgroundAlpha(0.8f);//设置屏幕透明度
        popupWindow = new CustomPopupWindow(view, ActionBar.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.WRAP_CONTENT);
        popupWindow.setFocusable(true);
        popupWindow.setOutsideTouchable(true);
        ColorDrawable dw = new ColorDrawable(0000000000);
        popupWindow.setBackgroundDrawable(dw);
        if (Build.VERSION.SDK_INT >= 24) {
            int[] a = new int[2];
            mTextView.getLocationInWindow(a);
            popupWindow.showAtLocation(getWindow().getDecorView(), Gravity.NO_GRAVITY, 0, a[1] + mTextView.getHeight());
        } else {
            popupWindow.showAsDropDown(mTextView);
        }
        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                setBackgroundAlpha(1f);
            }
        });
        listString = Arrays.asList("全部", "分享", "充值", "拉新", "发布", "每日登录", "查看通讯录", "兑换卡片");
        mGVAdapter = new Integral_Detial_GV_Adapter(this, listString, positionItem);
        mGridView.setAdapter(mGVAdapter);
    }

    @Override
    public void callBack(Object object, int type) {
        try {
            Integraldetialbean integraldetialbean = null;
            if (new JSONObject(object.toString()).getString("err").equals("0")) {
                Gson gson = new Gson();
                mEmptyView.setVisibility(View.GONE);
                listView.setVisibility(View.VISIBLE);
                integraldetialbean = gson.fromJson(object.toString(), Integraldetialbean.class);
                if (page == 1) {
                    list.clear();
                    list.addAll(integraldetialbean.getData().getRecords());
                    mLVAdapter = new Integral_Detial_LV_Adapter(this, list);
                    listView.setAdapter(mLVAdapter);
                } else {
                    list.addAll(integraldetialbean.getData().getRecords());
                    mLVAdapter.setList(list);
                    mLVAdapter.notifyDataSetChanged();
                }
            } else {
                if (page == 1) {
                    hasMoreData = false;
                    listView.setVisibility(View.GONE);
                    mEmptyView.setVisibility(View.VISIBLE);
                    mEmptyView.setMyManager(R.drawable.icon_intelligent_recommendation2);
                    mEmptyView.setNoMessageText("没有更多数据！");
                }
            }
        } catch (Exception e) {
        }
    }

    @Override
    public void failCallBack(int type) {

    }

    public void setBackgroundAlpha(float bgAlpha) {
        WindowManager.LayoutParams lp = getWindow().getAttributes();
        lp.alpha = bgAlpha;
        getWindow().setAttributes(lp);
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
