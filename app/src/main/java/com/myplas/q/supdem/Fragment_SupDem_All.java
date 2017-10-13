package com.myplas.q.supdem;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.myplas.q.R;
import com.myplas.q.common.api.API;
import com.myplas.q.common.appcontext.Constant;
import com.myplas.q.common.netresquset.ResultCallBack;
import com.myplas.q.common.utils.NetUtils;
import com.myplas.q.common.utils.SharedUtils;
import com.myplas.q.common.utils.TextUtils;
import com.myplas.q.common.view.CommonDialog;
import com.myplas.q.common.view.MyListview;
import com.myplas.q.common.view.MyNestedScrollView;
import com.myplas.q.guide.activity.BaseActivity;
import com.myplas.q.guide.activity.MainActivity;
import com.myplas.q.myinfo.fans.activity.PersonInfoActivity;
import com.myplas.q.myinfo.integral.activity.IntegralPayActivtity;
import com.myplas.q.release.ReleaseActivity;
import com.myplas.q.supdem.Beans.ConfigData;
import com.myplas.q.supdem.Beans.SupDemBean;
import com.myplas.q.supdem.activity.SupDem_Detail_Activity;
import com.myplas.q.supdem.adapter.SupDem_LV_Adapter;

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
public class Fragment_SupDem_All extends Fragment implements View.OnClickListener
        , CommonDialog.DialogShowInterface
        , ResultCallBack
        , SwipeRefreshLayout.OnRefreshListener
        , MyNestedScrollView.onScrollIterface {
    public boolean isRefresh;
    private SharedUtils sharedUtils;
    public int visibleItemCount, page;

    private SupDemBean.TopBean topBean;
    private SupDem_LV_Adapter mSupDemLVAdapter;
    private SupDemBean mSupDemBean;

    private View view, mViewHead;
    private MyListview mListView;
    private LinearLayout mLayout;
    private MyNestedScrollView mScrollView;
    private TextView company, content, time;
    private SwipeRefreshLayout mRefreshLayout;
    private ImageView typeSupDem, typeNowFutures, imgUp;
    private List<SupDemBean.DataBean> mDataBeanList;
    private LinearLayout layoutPrompt, layoutFirstitem, layoutUp, layoutDown;

    private String mLastData, hotSearch;
    public String follow_release, user_id, type;
    private RefreshPopouInterface mPopouinterface;

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
        sharedUtils = SharedUtils.getSharedUtils();

        mViewHead = LayoutInflater.from(getActivity()).inflate(R.layout.xlistview_header, null, false);
        view = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_supdem_all_layout, null, false);
        mListView = F(R.id.gq_listview);
        mScrollView = F(R.id.mynested_sv);
        mRefreshLayout = F(R.id.smartlayout);
        layoutFirstitem = F(R.id.supdem_head_ll);
        layoutPrompt = F(R.id.supply_demand_prompt_linear);

        mScrollView.setOnScrollIterface(this);
        layoutFirstitem.setOnClickListener(this);
        mRefreshLayout.setOnRefreshListener(this);
        mRefreshLayout.setColorSchemeResources(R.color.color_red);

        //item详情
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (NetUtils.isNetworkStateed(getActivity())) {
                    goToDetail("0"
                            , mDataBeanList.get(position).getId()
                            , mDataBeanList.get(position).getUser_id());
                }

            }
        });

        initFirstItem();
    }

    //实例化置顶控件
    public void initFirstItem() {
        company = F(R.id.gq_listview_gs);
        time = F(R.id.supply_demand_time);
        typeSupDem = F(R.id.supdem_img_type);
        content = F(R.id.supply_demand_content);
        layoutUp = F(R.id.supply_demand_company);
        layoutDown = F(R.id.supply_demand_detail);
        typeNowFutures = F(R.id.supply_demand_now_futures);

        layoutUp.setOnClickListener(this);
        layoutDown.setOnClickListener(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return view;
    }


    public <T extends View> T F(int id) {
        return (T) view.findViewById(id);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.supply_demand_company://判断是否消耗积分
                getPersonInfoData(user_id, "1", 2);
                break;
            case R.id.supply_demand_detail://跳转到详情
                goToDetail("0", topBean.getId(), topBean.getUser_id());
                break;
            case R.id.supply_demand_follow:
                if (follow_release.equals("follow")) {
                    MainActivity.firstInto();
                } else if (follow_release.equals("release")) {
                    startActivity(new Intent(getActivity(), ReleaseActivity.class));
                }
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
        BaseActivity.postAsyn1(getActivity(), API.BASEURL + API.GET_RELEASE_MSG, map, this, 1, isShowLoading);
    }

    //判断是否消耗积分
    public void getPersonInfoData(String userid, String showtype, int type) {
        Map<String, String> map = new HashMap<String, String>();
        map.put("token", sharedUtils.getData(getActivity(), "token"));
        map.put("user_id", userid);
        map.put("showType", showtype);
        String url = API.BASEURL + API.GET_ZONE_FRIEND;
        BaseActivity.postAsyn(getActivity(), url, map, this, type);
    }

    //跳转至详情
    public void goToDetail(String type, String id_, String userid) {
        String userId = sharedUtils.getData(getActivity(), "userid");
        Intent intent = new Intent(getActivity(), SupDem_Detail_Activity.class);
        intent.putExtra("id", id_);
        intent.putExtra("type", "0");
        intent.putExtra("userid", userid);
        intent.putExtra("what", (userId.equals(userid)) ? ("5") : "1");
        startActivity(intent);
    }

    @Override
    public void callBack(Object object, int type) {
        try {
            if (object.toString().equals(mLastData)) {
                mRefreshLayout.setRefreshing(false);
                mPopouinterface.showRefreshPopou(hotSearch, "", isRefresh);
                return;
            }
            mLastData = object.toString();
            Gson gson = new Gson();
            String result = new JSONObject(object.toString()).getString("err");
            if (type == 1) {
                if (result.equals("0")) {
                    mListView.setVisibility(View.VISIBLE);
                    layoutPrompt.setVisibility(View.GONE);
                    mSupDemBean = gson.fromJson(object.toString(), SupDemBean.class);
                    if (page == 1) {
                        mRefreshLayout.setRefreshing(false);
                        mSupDemLVAdapter = new SupDem_LV_Adapter(ConfigData.what, getActivity(), mSupDemBean.getData());
                        mListView.setAdapter(mSupDemLVAdapter);
                        mDataBeanList.clear();
                        mDataBeanList.addAll(mSupDemBean.getData());

                        hotSearch = mSupDemBean.getHot_search();
                        mPopouinterface.showRefreshPopou(hotSearch, mSupDemBean.getShow_msg(), isRefresh);
                    } else { //加载更多
                        isRefresh = false;
                        mDataBeanList.addAll(mSupDemBean.getData());
                        mSupDemLVAdapter.setList(mDataBeanList);
                        mSupDemLVAdapter.notifyDataSetChanged();
                    }
                    //展示头部
                    if (new JSONObject(object.toString()).getJSONObject("top").length() != 0) {
                        topBean = mSupDemBean.getTop();
                        layoutFirstitem.setVisibility(View.VISIBLE);
                        user_id = topBean.getUser_id();
                        showTopInfo(topBean);
                    } else {
                        topBean = null;
                        layoutFirstitem.setVisibility(View.GONE);
                    }

                } else {//显示提示信息：
                    isRefresh = false;
                    if (page == 1) {
                        mRefreshLayout.setRefreshing(false);
                        mListView.setVisibility(View.GONE);
                        layoutPrompt.setVisibility(View.VISIBLE);
                        layoutPrompt.removeAllViews();
                        switch (result) {
                            case "1":
                            case "998":
                                sharedUtils.setData(getActivity(), "token", "");
                                sharedUtils.setData(getActivity(), "userid", "");
                                sharedUtils.setBooloean(getActivity(), "logined", false);
                                break;
                            //没有更多数据
                            case "2":
                                View v = View.inflate(getActivity(), R.layout.layout_supplydemand_prompt_release2, null);
                                layoutPrompt.addView(v);
                                TextView text = (TextView) v.findViewById(R.id.supply_demand_text);
                                text.setText(new JSONObject(object.toString()).getString("msg"));
                                break;
                            //未匹配
                            case "4":
                                layoutPrompt.addView(View.inflate(getActivity(), R.layout.layout_supplydemand_prompt_release2, null));
                                break;
                            case "9"://去关注
                                follow_release = "follow";
                                View view = View.inflate(getActivity(), R.layout.layout_supplydemand_prompt_care1, null);
                                layoutPrompt.addView(view);
                                view.findViewById(R.id.supply_demand_follow).setOnClickListener(this);
                                break;
                            //关注 ——未发布
                            case "6":
                                layoutPrompt.addView(View.inflate(getActivity(), R.layout.layout_supplydemand_prompt_care2, null));
                                break;
                            //职能推荐——区发布
                            case "7":
                                follow_release = "release";
                                View view3 = View.inflate(getActivity(), R.layout.layout_supplydemand_prompt_release1, null);
                                layoutPrompt.addView(view3);
                                TextView t = (TextView) view3.findViewById(R.id.supply_demand_text);
                                t.setText(new JSONObject(object.toString()).getString("msg"));
                                view3.findViewById(R.id.supply_demand_follow).setOnClickListener(this);
                                view3.findViewById(R.id.img_supplydemad_down).setVisibility(View.VISIBLE);
                                break;
                            //我的供求——去发布
                            case "8":
                                follow_release = "release";
                                View view2 = View.inflate(getActivity(), R.layout.layout_supplydemand_prompt_release1, null);
                                layoutPrompt.addView(view2);
                                TextView textView = (TextView) view2.findViewById(R.id.supply_demand_text);
                                textView.setText(new JSONObject(object.toString()).getString("msg"));
                                view2.findViewById(R.id.supply_demand_follow).setOnClickListener(this);
                                view2.findViewById(R.id.img_supplydemad_down).setVisibility(View.GONE);
                                break;
                        }
                    } else {
                        TextUtils.Toast(getContext(), "没有更多数据了！");
                    }
                }
            }
            //是否消耗积分
            if (type == 2 && result.equals("99")) {
                String content = new JSONObject(object.toString()).getString("msg");
                CommonDialog commonDialog = new CommonDialog();
                commonDialog.showDialog(getActivity(), content, 1, this);
            }
            //已经消费了积分
            if (type == 2 && result.equals("0")) {
                Intent intent = new Intent(getActivity(), PersonInfoActivity.class);
                intent.putExtra("userid", user_id);
                intent.putExtra("id", user_id);
                startActivity(intent);
            }
            //减积分成功
            if (type == 3 && result.equals("0")) {
                Intent intent = new Intent(getActivity(), PersonInfoActivity.class);
                intent.putExtra("userid", user_id);
                intent.putExtra("id", user_id);
                startActivity(intent);
            }
            //积分不够
            if (type == 3 && !result.equals("0")) {
                String content = new JSONObject(object.toString()).getString("msg");
                CommonDialog commonDialog = new CommonDialog();
                commonDialog.showDialog(getActivity(), content, (result.equals("100")) ? (2) : (3), this);
            }
        } catch (Exception e) {
        }
    }

    @Override
    public void failCallBack(int type) {
        isRefresh = false;
        mRefreshLayout.setRefreshing(false);
        if (mDataBeanList.size() == 0) {
            mListView.setVisibility(View.GONE);
            layoutPrompt.setVisibility(View.VISIBLE);
            layoutPrompt.removeAllViews();

            ImageButton imageButton = new ImageButton(getActivity());
            imageButton.setImageResource(R.drawable.img_reload);
            layoutPrompt.addView(imageButton);
            imageButton.setBackgroundColor(getResources().getColor(R.color.color_white));
            imageButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    page = 1;
                    getNetData("1", true);
                }
            });
        }
    }

    public void showTopInfo(SupDemBean.TopBean topBean) {
        if (topBean != null) {
            try {
                company.setText(topBean.getC_name() + "  " + topBean.getName());

//                String s = topBean.getFrom().equals("1") ? "来自供求 " : "来自QQ群 " + topBean.getInput_time();
//                time.setText(s);

                String html1 = "<font color='#9c9c9c'>" + " 货物位置:" + "</font>" + topBean.getStore_house()
                        + "<font color='#9c9c9c'>" + " 牌号:" + "</font>" + topBean.getModel()
                        + "<font color='#9c9c9c'>" + " 厂家:" + "</font>" + topBean.getF_name()
                        + "<font color='#9c9c9c'>" + " 价格:" + "</font>" + topBean.getUnit_price();
                content.setText(Html.fromHtml(html1));

                typeSupDem.setImageResource(topBean.getType().equals("1")
                        ? R.drawable.icon_supdem_purchase
                        : R.drawable.icon_supdem_supply);
                typeNowFutures.setImageResource(topBean.getCargo_type().equals("1")
                        ? R.drawable.icon_now
                        : R.drawable.icon_futures);
            } catch (Exception e) {
            }
        }
    }

    //dialog回调
    @Override
    public void ok(int type) {
        switch (type) {
            case 1:
                getPersonInfoData(user_id, "5", 3);
                break;
            case 2:
                startActivity(new Intent(getActivity(), IntegralPayActivtity.class));
                break;
        }
    }

    public void setRefreshPopouInterface(RefreshPopouInterface refreshPopouinterface) {
        this.mPopouinterface = refreshPopouinterface;
    }


    @Override
    public void onRefresh() {
        page = 1;
        isRefresh = true;
        getNetData(page + "", false);
    }

    @Override
    public void loadMore() {
        page++;
        getNetData(page + "", false);
    }

    @Override
    public void onResume() {
        super.onResume();
        boolean isLogined = sharedUtils.getBoolean(getActivity(), Constant.IS_LOGINED_SD);
        if (isLogined) {//防止第一次登陆以后没有数据
            getNetData("1", false);
            sharedUtils.setBooloean(getActivity(), Constant.IS_LOGINED_SD, false);
        }
    }

    public interface RefreshPopouInterface {
        void showRefreshPopou(String content, String hotSearch, boolean isRefresh);
    }
}

