package com.myplas.q.homepage.activity;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.util.SparseArray;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.huangbryant.hindicator.HIndicatorBuilder;
import com.huangbryant.hindicator.HIndicatorDialog;
import com.huangbryant.hindicator.OnDismissListener;
import com.myplas.q.R;
import com.myplas.q.common.api.API;
import com.myplas.q.common.net.ResultCallBack;
import com.myplas.q.common.utils.ContactAccessUtils;
import com.myplas.q.common.utils.TextUtils;
import com.myplas.q.common.view.EditTextField;
import com.myplas.q.common.view.MyGridview;
import com.myplas.q.common.view.RefreshPopou;
import com.myplas.q.homepage.adapter.Fragment_Contact_LV_Adapter;
import com.myplas.q.homepage.adapter.Fragment_Dialog_Adapter;
import com.myplas.q.homepage.beans.ContactBean;
import com.myplas.q.homepage.beans.NoSearchInfoBean;
import com.myplas.q.homepage.beans.RecordBean;
import com.myplas.q.app.activity.BaseActivity;
import com.myplas.q.supdem.adapter.SupDem_Search_Grid_Adapter;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 编写： 黄双
 * 电话：15378412400
 * 邮箱：15378412400@163.com
 * 时间：2017/3/19 15:44
 */

public class Contact_Search_Activity extends BaseActivity implements View.OnClickListener
        , ResultCallBack
        , AdapterView.OnItemClickListener {
    private ListView listView;
    private ImageView imageView;
    private EditTextField editText;
    private RefreshPopou mRefreshPopou;
    private MyGridview mgvHistory, mgvEmpty, mgvSubcribe;
    private TextView textView, textviewClassify, textviewRegion, textviewNo;
    private LinearLayout mLayoutDefault, mLayoutResult, mLayoutEmpty, mLayoutConfig;

    private RecordBean mRecordBean;
    private HIndicatorDialog dialog;
    private NoSearchInfoBean mInfoBean;
    private SupDem_Search_Grid_Adapter mGridAdapter;

    private boolean isLoading;
    private String transition;
    private SparseArray<Integer> map;
    private ContactAccessUtils utils;
    private int page, visibleItemCount;
    private StringBuffer c_type, region;
    private Fragment_Contact_LV_Adapter mLVAdapter;
    private List<ContactBean.PersonsBean> mListBean;
    private String keywords, is_buy, showCType;
    private ContactBean mContactBean;

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.supplydemand_btn_search:
                page = 1;
                mRefreshPopou.setCanShowPopou(true);
                mLayoutDefault.setVisibility(View.GONE);
                mLayoutResult.setVisibility(View.VISIBLE);
                keywords = ("".equals(editText.getText().toString())) ? ("7000f") : (editText.getText().toString());
                getNetData("1", true);
                break;
            case R.id.img_search_delete:
                delsearchRecord();
                break;
            case R.id.contact_classify:
                openDialog(1, textviewClassify);
                break;
            case R.id.contact_region:
                openDialog(2, textviewRegion);
                break;
            default:
                break;
        }
    }

    /**
     * 查询
     */
    private void getNetData(String page, boolean isShowDialog) {
        Map<String, String> map = new HashMap<String, String>(16);
        map.put("page", page);
        map.put("size", "15");
        map.put("keywords", keywords);
        map.put("type", c_type.toString());
        map.put("region", region.toString());
        getAsyn(this, API.PLASTICPERSON, map, this, 2, isShowDialog);
    }

    /**
     * 删除历史记录
     */
    public void delsearchRecord() {
        deleteAsyn(this, API.DELETEF_RECORD, null, this, 3, false);
    }

    private void openDialog(final int type, final TextView textView) {
        if (mContactBean == null) {
            return;
        }

        Fragment_Dialog_Adapter adapter = new Fragment_Dialog_Adapter(type, showCType, map, mContactBean.getTotalBeans()) {
            @Override
            public void onItemSelected(String show, String value) {
                dialog.dismiss();
                showCType = show;
                textView.setText(show);
                textView.setTextColor(getResources().getColor(R.color.color_red));
                textView.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.down, 0);
                if (type == 1) {
                    c_type = new StringBuffer(value);
                } else {
                    region = new StringBuffer(value);
                }
                page = 1;
                mRefreshPopou.setCanShowPopou(true);
                getNetData("1", true);
            }
        };
        dialog = new HIndicatorBuilder(this)
                .width(400)
                .height(-1)
                .ArrowDirection(HIndicatorBuilder.TOP)
                .bgColor(Color.parseColor("#ffffff"))
                .gravity(HIndicatorBuilder.GRAVITY_LEFT)
                .radius(10)
                .arrowWidth(20)
                .ArrowRectage(0.5f)
                .layoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false))
                .dimEnabled(true)
                .dimAmount(0.2f)
                .adapter(adapter)
                .create();
        dialog.setCanceledOnTouchOutside(true);
        dialog.show(textView);
        textView.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.up, 0);
        dialog.setOnDismissListener(new OnDismissListener() {
            @Override
            public void onDismiss(Dialog dialog) {
                textView.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.down, 0);
            }
        });
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        switch (parent.getId()) {
            case R.id.mygrid_search_history://历史搜索
                keywords = mRecordBean.getSearch_records().get(position);
                getData(keywords);
                break;
            case R.id.mygrid_search_subcribe://猜你所想
                keywords = mRecordBean.getRecommendation().get(position);
                getData(keywords);
                break;
            case R.id.mygrid_search_null://相关搜索
                keywords = mInfoBean.getRecommendation().get(position);
                getData(keywords);
                break;
            case R.id.search_listview_result:
                utils.checkPremissions(mListBean.get(position));
                break;
            default:
                break;
        }
    }

    private void getData(String keywords) {
        page = 1;
        mRefreshPopou.setCanShowPopou(true);

        editText.setText(keywords);
        editText.setSelection(keywords.length());
        mLayoutDefault.setVisibility(View.GONE);
        mLayoutResult.setVisibility(View.VISIBLE);
        getNetData("1", true);
    }

    @Override
    public void callBack(Object object, int type) {
        try {
            if (type == 3) {
                TextUtils.toast(this, "删除成功！");
                mGridAdapter = new SupDem_Search_Grid_Adapter(this, null);
                mgvHistory.setAdapter(mGridAdapter);
            }

            Gson gson = new Gson();
            String err = new JSONObject(object.toString()).getString("code");

            if (type == 1 && "0".equals(err)) {
                mRecordBean = gson.fromJson(object.toString(), RecordBean.class);
                mGridAdapter = new SupDem_Search_Grid_Adapter(this, mRecordBean.getSearch_records());
                mgvHistory.setAdapter(mGridAdapter);
                SupDem_Search_Grid_Adapter mGridAdapter1 = new SupDem_Search_Grid_Adapter(this, mRecordBean.getRecommendation());
                mgvSubcribe.setAdapter(mGridAdapter1);

//                keywords = historyBean.getHot_search().getContent();
//                editText.setHint(keywords);
            }
            if (type == 2) {
                InputMethodManager in = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                in.hideSoftInputFromWindow(editText.getWindowToken(), 0);
                if ("0".equals(err)) {
                    mContactBean = gson.fromJson(object.toString(), ContactBean.class);
                    if (page == 1) {
                        if (listView.getVisibility() == View.GONE) {
                            listView.setVisibility(View.VISIBLE);
                            mLayoutEmpty.setVisibility(View.GONE);
                        }

                        mLVAdapter = new Fragment_Contact_LV_Adapter(this, mContactBean.getPersons());
                        listView.setAdapter(mLVAdapter);

                        mListBean.clear();
                        mListBean.addAll(mContactBean.getPersons());
                        mRefreshPopou.show(F(R.id.divider_result), "为你搜索" + mContactBean.getTotals() + "条信息");
                    } else {
                        isLoading = false;
                        mListBean.addAll(mContactBean.getPersons());
                        mLVAdapter.setList(mListBean);
                        mLVAdapter.notifyDataSetChanged();
                    }
                } else {
                    if (page == 1) {

                        if (mLayoutEmpty.getVisibility() == View.GONE) {
                            listView.setVisibility(View.GONE);
                            mLayoutEmpty.setVisibility(View.VISIBLE);
                        }

                        textviewNo.setText("抱歉，未能找到相关搜索！");
                        mInfoBean = gson.fromJson(object.toString(), NoSearchInfoBean.class);
                        mGridAdapter = new SupDem_Search_Grid_Adapter(this, mInfoBean.getRecommendation());
                        mgvEmpty.setAdapter(mGridAdapter);
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
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.fade, R.anim.hold);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_search_layout);
        goBack(findViewById(R.id.back));
        initView();
        getsearchRecord();
    }

    public void initView() {
        page = 1;
        is_buy = "1";
        showCType = "全部分类";
        keywords = "7000f";
        map = new SparseArray<>();
        mListBean = new ArrayList<>();
        region = new StringBuffer("0");
        c_type = new StringBuffer("0");
        utils = new ContactAccessUtils(this);
        mRefreshPopou = new RefreshPopou(this, 2);
        transition = getIntent().getStringExtra("transition");

        imageView = F(R.id.img_search_delete);
        mgvEmpty = F(R.id.mygrid_search_null);
        textviewRegion = F(R.id.contact_region);
        listView = F(R.id.search_listview_result);
        mLayoutConfig = F(R.id.contact_config_ll);
        textView = F(R.id.supplydemand_btn_search);
        editText = F(R.id.supplydemand_search_edit);
        mgvHistory = F(R.id.mygrid_search_history);
        textviewClassify = F(R.id.contact_classify);
        mLayoutResult = F(R.id.search_result_linear);
        textviewNo = F(R.id.search_result_text_null);
        mgvSubcribe = F(R.id.mygrid_search_subcribe);
        mLayoutDefault = F(R.id.search_default_linear);
        mLayoutEmpty = F(R.id.search_result_linear_null);

        textView.setOnClickListener(this);
        imageView.setOnClickListener(this);
        listView.setOnItemClickListener(this);
        mgvEmpty.setOnItemClickListener(this);
        textviewRegion.setOnClickListener(this);
        mgvHistory.setOnItemClickListener(this);
        mgvSubcribe.setOnItemClickListener(this);
        textviewClassify.setOnClickListener(this);

        editText.setHintTextColor(getResources().getColor(R.color.color_gray));

        //edittext的回车监听
        editText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView arg0, int arg1, KeyEvent arg2) {
                if (arg1 == EditorInfo.IME_ACTION_SEARCH | (arg2 != null && arg2.getAction() == KeyEvent.ACTION_DOWN)) {
                    page = 1;
                    mRefreshPopou.setCanShowPopou(true);
                    mLayoutDefault.setVisibility(View.GONE);
                    mLayoutResult.setVisibility(View.VISIBLE);
                    keywords = ("".equals(editText.getText().toString())) ? ("7000f") : (editText.getText().toString());
                    getNetData("1", true);
                    return true;
                }
                return false;
            }
        });

        //加载更多
        listView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                if (scrollState == AbsListView.OnScrollListener.SCROLL_STATE_IDLE && listView.getCount() > visibleItemCount) {
                    InputMethodManager in = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    in.hideSoftInputFromWindow(editText.getWindowToken(), 0);
                    if (view.getLastVisiblePosition() == view.getCount() - 1) {
                        if (!isLoading) {
                            page++;
                            isLoading = true;
                            getNetData("" + page, false);
                        }
                    }
                }
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                Contact_Search_Activity.this.visibleItemCount = visibleItemCount;
            }
        });
    }

    /**
     * 获取历史搜索
     */
    public void getsearchRecord() {
        getAsyn(this, API.GETF_RECORD, null, this, 1);
    }
}
