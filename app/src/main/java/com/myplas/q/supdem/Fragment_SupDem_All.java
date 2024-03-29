package com.myplas.q.supdem;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.myplas.q.R;
import com.myplas.q.app.fragment.BaseFragment;
import com.myplas.q.common.api.API;
import com.myplas.q.common.appcontext.Constant;
import com.myplas.q.common.listener.MyOnItemClickListener;
import com.myplas.q.common.net.ResultCallBack;
import com.myplas.q.common.utils.ACache;
import com.myplas.q.common.utils.ContactAccessUtils;
import com.myplas.q.common.utils.NetUtils;
import com.myplas.q.common.utils.SharedUtils;
import com.myplas.q.common.utils.TextUtils;
import com.myplas.q.common.view.MyListview;
import com.myplas.q.common.view.MyNestedScrollView;
import com.myplas.q.common.view.RefreshPopou;
import com.myplas.q.sockethelper.RabbitMQConfig;
import com.myplas.q.supdem.activity.SupDem_Detail_Activity;
import com.myplas.q.supdem.activity.SupDem_QQ_DetailActivity;
import com.myplas.q.supdem.adapter.SupDem_LV_Adapter;
import com.myplas.q.supdem.beans.SupDemBean;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 编写： 黄双
 * 电话：15378412400
 * 邮箱：15378412400@163.com
 * 时间：2017/3/17 14:45
 */
public class Fragment_SupDem_All extends BaseFragment implements View.OnClickListener
        , ResultCallBack
        , SwipeRefreshLayout.OnRefreshListener
        , MyNestedScrollView.onScrollIterface, MyOnItemClickListener {

    public int page;
    private ACache mAcache;
    private SharedUtils sharedUtils;
    private boolean isRefreshing, isLoading;

    private SupDemBean mSupDemBean;
    private SupDemBean.TopBean topBean;
    private SupDem_LV_Adapter mSupDemLVAdapter;

    private MyListview mListView;
    private FrameLayout layoutDown;
    private View view, mViewDivider;
    public RefreshPopou refreshPopou;
    private MyNestedScrollView mScrollView;
    private SwipeRefreshLayout mRefreshLayout;
    private LinearLayout layoutFirstitem, layoutUp;
    private List<SupDemBean.DataBean> mDataBeanList;
    private ImageView typeSupDem, typeNowFutures, imgUp;
    private TextView company, content, time, mTVPromit, reply, deliver;

    private ContactAccessUtils utils;
    private String mLastData, hotSearch, jsonStr;
    private RefreshPopouInterface mPopouinterface;
    public String followRelease, user_id, type, mergeThere;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        getNetData("1", false);
    }

    private void initView() {
        page = 1;
        type = "0";
        mDataBeanList = new ArrayList<>();
        mAcache = ACache.get(getActivity());
        sharedUtils = SharedUtils.getSharedUtils();
        utils = new ContactAccessUtils(getActivity());
        jsonStr = mAcache.getAsString(Constant.SUPDEMCACHE);
        refreshPopou = new RefreshPopou(getActivity(), 3);

        view = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_supdem_all_layout, null, false);
        mViewDivider = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_supdem_head_divider, null, false);

        mListView = f(R.id.gq_listview);
        mScrollView = f(R.id.mynested_sv);
        reply = f(R.id.supply_demand_reply);
        mRefreshLayout = f(R.id.smartlayout);
        mTVPromit = f(R.id.supply_demand_text);
        deliver = f(R.id.supply_demand_deliver);
        layoutFirstitem = f(R.id.supdem_head_ll);

        mListView.setHeaderDividersEnabled(false);
        mViewDivider.setVisibility(View.GONE);
        mListView.addHeaderView(mViewDivider);

        mScrollView.setOnScrollIterface(this);
        layoutFirstitem.setOnClickListener(this);
        mRefreshLayout.setOnRefreshListener(this);
        mRefreshLayout.setColorSchemeResources(R.color.color_red);

        //item详情
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (NetUtils.isNetworkStateed(getActivity())) {
                    goToDetail(mDataBeanList.get(position - 1).getC_name()
                            , mDataBeanList.get(position - 1).getModel()
                            , mDataBeanList.get(position - 1).getId()
                            , mDataBeanList.get(position - 1).getUser_id()
                            , "1".equals(mDataBeanList.get(position - 1).getFrom()));
                }

            }
        });

        initTopItem();
        loadCacheData(jsonStr, new Gson());
    }

    public void initTopItem() {
        company = f(R.id.gq_listview_gs);
        time = f(R.id.supply_demand_time);
        typeSupDem = f(R.id.supdem_img_type);
        content = f(R.id.supply_demand_content);
        layoutUp = f(R.id.supply_demand_company);
        layoutDown = f(R.id.supply_demand_detail);
        typeNowFutures = f(R.id.supply_demand_now_futures);

        layoutUp.setOnClickListener(this);
        layoutDown.setOnClickListener(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return view;
    }


    public <T extends View> T f(int id) {
        return (T) view.findViewById(id);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.supply_demand_company://判断是否消耗积分
                if ("1".equals(topBean.getFrom())) {
                    utils.checkPremissions(user_id, mergeThere);
                }
                break;
            case R.id.supply_demand_detail://跳转到详情
                goToDetail(topBean.getC_name()
                        , topBean.getModel()
                        , topBean.getId()
                        , topBean.getUser_id()
                        , "1".equals(topBean.getFrom()));
                break;
            default:
                break;
        }
    }

    public void getNetData(String page, boolean isShowLoading) {
        Map<String, String> map = new HashMap<String, String>();
        map.put("page", page);
        map.put("size", "15");
        map.put("type", type);
        map.put("keywords", "");
        map.put("sortField2", "");
        map.put("sortField1", "ALL");
        map.put("token", sharedUtils.getData(getActivity(), "token"));
        getAsyn(getActivity(), API.RELEASE_MSG, map, this, 1, isShowLoading);
    }


    /**
     * 跳转至详情
     */

    public void goToDetail(String company, String model, String id_, String userid, boolean isFromSupdem) {
        if (isFromSupdem) {
            Intent intent = new Intent(getActivity(), SupDem_Detail_Activity.class);
            intent.putExtra("id", id_);
            intent.putExtra("userid", userid);
            startActivity(intent);
        } else {
            Intent intent = new Intent(getActivity(), SupDem_QQ_DetailActivity.class);
            intent.putExtra("company", company);
            intent.putExtra("plastic_number", model);
            intent.putExtra("id", id_);
            startActivity(intent);
        }
    }

    @Override
    public void callBack(Object object, int type) {
        try {
            Gson gson = new Gson();
            if (type == 1) {
                loadCacheData(object, gson);
                mAcache.put(Constant.SUPDEMCACHE, object.toString());
            }
        } catch (Exception e) {
        }
    }

    @Override
    public void failCallBack(int type, String message, int httpCode) {

        if (type == 1) {
            isLoading = false;
            refreshPopou.setCanShowPopou(false);
            mRefreshLayout.setRefreshing(false);
        }
    }


    private void loadCacheData(Object object, Gson gson) {
        try {
            JSONObject jsonObject = new JSONObject(object.toString());
            String result = jsonObject.getString("code");
            if ("0".equals(result)) {
                mSupDemBean = gson.fromJson(object.toString(), SupDemBean.class);
                if (page == 1) {
                    mRefreshLayout.setRefreshing(false);
                    mSupDemLVAdapter = new SupDem_LV_Adapter(getActivity(), mSupDemBean.getData());
                    mSupDemLVAdapter.setListener(this);
                    mListView.setAdapter(mSupDemLVAdapter);
                    mDataBeanList.clear();
                    mDataBeanList.addAll(mSupDemBean.getData());

                    hotSearch = mSupDemBean.getHot_search();
                    mPopouinterface.showRefreshPopou(hotSearch, mSupDemBean.getShow_msg());

                    if (isRefreshing) {
                        isRefreshing = false;
                        RabbitMQConfig.getInstance(getActivity()).readMsg("unread_supply_and_demand", 11);
                    }
                } else { //加载更多
                    isLoading = false;
                    refreshPopou.setCanShowPopou(false);
                    mDataBeanList.addAll(mSupDemBean.getData());
                    mSupDemLVAdapter.setList(mDataBeanList);
                    mSupDemLVAdapter.notifyDataSetChanged();
                }
                //展示头部
                if (jsonObject.getJSONObject("top").length() != 0) {
                    showTopInfo(mSupDemBean.getTop());
                } else {
                    topBean = null;
                    mViewDivider.setVisibility(View.GONE);
                    layoutFirstitem.setVisibility(View.GONE);
                }

            } else {//显示提示信息：
                refreshPopou.setCanShowPopou(false);
                if (page == 1) {
                    mRefreshLayout.setRefreshing(false);
                    mTVPromit.setText(new JSONObject(object.toString()).getString("message"));
                } else {
                    TextUtils.toast(getContext(), "没有更多数据了！");
                }
            }
        } catch (Exception e) {
            e.toString();
        }
    }

    public void showTopInfo(SupDemBean.TopBean topBean) {
        try {
            user_id = topBean.getUser_id();
            mergeThere = topBean.getMerge_three();
            mViewDivider.setVisibility(View.VISIBLE);
            layoutFirstitem.setVisibility(View.VISIBLE);

            time.setText(topBean.getInput_time());
            company.setText(topBean.getC_name() + "  " + topBean.getName());

            if ("1".equals(topBean.getFrom())) {
                reply.setText("回复(" + topBean.getReplyCount() + ")");
                deliver.setText("出价(" + topBean.getPlaticCount() + ")");
                reply.setCompoundDrawablesWithIntrinsicBounds(R.drawable.icon_sd_reply, 0, 0, 0);
                deliver.setCompoundDrawablesWithIntrinsicBounds(R.drawable.icon_sd_offer, 0, 0, 0);
            }

            String html1 = "<font color='#9c9c9c'>" + " 交货地:" + "</font>" + topBean.getStore_house()
                    + "<font color='#9c9c9c'>" + " 牌号:" + "</font>" + topBean.getModel()
                    + "<font color='#9c9c9c'>" + " 厂家:" + "</font>" + topBean.getF_name()
                    + "<font color='#9c9c9c'>" + " 价格:" + "</font>" + topBean.getUnit_price();
            content.setText(Html.fromHtml(html1));

            typeSupDem.setImageResource("1".equals(topBean.getType())
                    ? R.drawable.icon_supdem_purchase
                    : R.drawable.icon_supdem_supply);
            typeNowFutures.setImageResource("1".equals(topBean.getCargo_type())
                    ? R.drawable.icon_now
                    : R.drawable.icon_futures);
        } catch (Exception e) {
        }
    }


    public void setRefreshPopouInterface(RefreshPopouInterface refreshPopouinterface) {
        this.mPopouinterface = refreshPopouinterface;
    }


    @Override
    public void onRefresh() {
        page = 1;
        isRefreshing = true;
        getNetData(page + "", false);
        refreshPopou.setCanShowPopou(true);
    }

    @Override
    public void loadMore() {
        if (!isLoading) {
            page++;
            isLoading = true;
            getNetData(page + "", false);
        }
    }

    /**
     * 点击公司名称的回调
     *
     * @param userId
     * @param flag
     * @param id
     * @param pur_id
     * @param user_id (前两个参数有值)
     */
    @Override
    public void onItemClick(String userId, String flag, String id, String pur_id, String user_id) {
        utils.checkPremissions(userId, flag);
    }

    public interface RefreshPopouInterface {
        void showRefreshPopou(String content, String hotSearch);
    }
}

