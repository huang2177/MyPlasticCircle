package com.myplas.q.supdem.fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.myplas.q.R;
import com.myplas.q.common.appcontext.Constant;
import com.myplas.q.common.utils.NetUtils;
import com.myplas.q.guide.activity.BaseActivity;
import com.myplas.q.common.api.API;
import com.myplas.q.common.netresquset.ResultCallBack;
import com.myplas.q.common.utils.DialogShowUtils;
import com.myplas.q.common.utils.SharedUtils;
import com.myplas.q.common.utils.TextUtils;
import com.myplas.q.common.view.MyLinearLayout;
import com.myplas.q.common.view.RefreshableView;
import com.myplas.q.myinfo.integral.activity.IntegralPayActivtity;
import com.myplas.q.myinfo.fans.activity.PersonInfoActivity;
import com.myplas.q.release.activity.ReleaseActivity;
import com.myplas.q.guide.activity.MainActivity;
import com.myplas.q.supdem.Beans.ItemBean;
import com.myplas.q.supdem.Beans.Supply_DemandBean;
import com.myplas.q.supdem.activity.SupDem_Detail_Activity;
import com.myplas.q.supdem.adapter.GQ_ListviewAdapter;

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
public class Fragment_SupDem_All extends Fragment implements View.OnClickListener, DialogShowUtils.DialogShowInterface,
        ResultCallBack, RefreshableView.onRefreshListener {
    private Activity context;
    private ItemBean itemBean;
    private boolean isRefresh;
    public int visibleItemCount;
    private SharedUtils sharedUtils;

    public String follow_release, user_id;
    private Supply_DemandBean.TopBean topBean;
    private Supply_DemandBean supply_demandBean;
    private GQ_ListviewAdapter gq_listviewAdapter;

    private View view;
    private ListView gq_listview;
    private ImageView tx, rz, img;
    private MyLinearLayout myLinearLayout;
    private RefreshableView refreshableView;
    private TextView gs, content, time, isbuyed, repeat;
    private List<Supply_DemandBean.DataBean> list, list_more;
    private LinearLayout linearLayout_prompt, linearLayout_firstitem, linearLayout_up, linearLayout_down;
    private ShowRefreshPopouinterface showRefreshPopouinterface;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        view = LayoutInflater.from(getActivity()).inflate(R.layout.layout_fragment_all, null, false);

        list_more = new ArrayList<>();
        itemBean = ItemBean.getItemBean();
        gq_listview = F(R.id.gq_listview);
        myLinearLayout = F(R.id.mylinearlayout);
        refreshableView = F(R.id.refreshableview);
        linearLayout_firstitem = (LinearLayout) view.findViewById(R.id.gq_first_item);
        linearLayout_prompt = (LinearLayout) view.findViewById(R.id.supply_demand_prompt_linear);

        initView(linearLayout_firstitem);
        sharedUtils = SharedUtils.getSharedUtils();

        repeat.setOnClickListener(this);
        isbuyed.setOnClickListener(this);
        refreshableView.setOnRefreshListener(this);
        linearLayout_firstitem.setOnClickListener(this);

        //item详情
        gq_listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                try {
                    if (NetUtils.isNetworkStateed(context)) {
                        goToDetail("0", list_more.get(position).getId(), list_more.get(position).getUser_id());
                    }
                } catch (Exception e) {
                }
            }
        });
        gq_listview.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                if (scrollState == AbsListView.OnScrollListener.SCROLL_STATE_IDLE && gq_listview.getCount() > visibleItemCount) {
                    if (view.getLastVisiblePosition() == view.getCount() - 1) {
                        itemBean.page++;
                        if (itemBean.hasMoreData) {
                            getNetData(context, itemBean.page + "", itemBean.keywords, itemBean.sortField1, itemBean.sortField2, itemBean.type, false);
                        } else {
                            TextUtils.Toast(getActivity(), "没有更多数据了！");
                        }
                    }
                }
                View firstVisibleItem1 = gq_listview.getChildAt(0);
                if (scrollState == 0 && firstVisibleItem1 != null && firstVisibleItem1.getTop() != 0) {
                    myLinearLayout.setEnnablerefresh(false);
                }
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                Fragment_SupDem_All.this.visibleItemCount = visibleItemCount;
                View firstVisibleItem1 = gq_listview.getChildAt(0);
                if (firstVisibleItem1 != null && firstVisibleItem1.getTop() == 0) {
                    myLinearLayout.setEnnablerefresh(true);
                } else {
                    myLinearLayout.setEnnablerefresh(false);
                }
            }
        });
        getNetData(getActivity(), "1", "", "ALL", "", itemBean.type, true);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        itemBean.page = 1;
        itemBean.hasMoreData = true;
        myLinearLayout.setEnnablerefresh(false);
        boolean isLogined = sharedUtils.getBoolean(getActivity(), Constant.IS_LOGINED_SD);
        if (isLogined) {
            getNetData(getActivity(), "1", "", "ALL", "", itemBean.type, true);
            sharedUtils.setBooloean(getActivity(), Constant.IS_LOGINED_SD, false);
        }
    }

    //实例化置顶控件
    public void initView(View viewfirst) {
        tx = (ImageView) viewfirst.findViewById(R.id.xq_tx);
        rz = (ImageView) viewfirst.findViewById(R.id.xq_rz);
        img = (ImageView) viewfirst.findViewById(R.id.supply_demand_img);
        gs = (TextView) viewfirst.findViewById(R.id.gq_listview_gs);
        content = (TextView) viewfirst.findViewById(R.id.supply_demand_content);
        linearLayout_up = (LinearLayout) viewfirst.findViewById(R.id.supply_demand_linear);
        linearLayout_down = (LinearLayout) viewfirst.findViewById(R.id.first_item);
        time = (TextView) viewfirst.findViewById(R.id.supply_demand_time);
        repeat = (TextView) viewfirst.findViewById(R.id.supply_demand_repeat);
        isbuyed = (TextView) viewfirst.findViewById(R.id.supply_demand_isbuyed);
        linearLayout_up.setOnClickListener(this);
        linearLayout_down.setOnClickListener(this);
    }

    //跳转至详情
    public void goToDetail(String type, String id_, String userid) {
        Intent intent = new Intent(getActivity(), SupDem_Detail_Activity.class);
        String user_id = sharedUtils.getData(getActivity(), "userid");

        intent.putExtra("id", id_);
        intent.putExtra("type", "0");
        intent.putExtra("userid", userid);
        intent.putExtra("what", (user_id.equals(userid)) ? ("5") : (itemBean.what));

        startActivity(intent);
    }

    public <T extends View> T F(int id) {
        return (T) view.findViewById(id);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.supply_demand_linear:
                getPersonInfoData(user_id, "1", 2);
                break;
            case R.id.first_item:
                goToDetail("0", topBean.getId(), topBean.getUser_id());
                break;
            case R.id.supply_demand_isbuyed:
                goToDetail("1", topBean.getId(), topBean.getUser_id());
                break;
            case R.id.supply_demand_repeat:
                goToDetail("2", topBean.getId(), topBean.getUser_id());
                break;

            case R.id.supply_demand_follow:
                if (follow_release.equals("follow")) {
                    MainActivity.firstInto();
                } else if (follow_release.equals("release")) {
                    startActivity(new Intent(getActivity(), ReleaseActivity.class));
                }
                break;
            case R.id.img_reload:
                getNetData(getActivity(), "1", "", "ALL", "AUTO", "0", true);
                break;
        }
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

    public void getNetData(Activity context, String page, String keywords, String sortField1, String sortField2, String type, boolean a) {
        try {
            this.context = context;
            Map<String, String> map = new HashMap<String, String>();
            sharedUtils = SharedUtils.getSharedUtils();
            map.put("token", sharedUtils.getData(getActivity(), "token"));
            map.put("page", page);
            map.put("size", "15");
            map.put("keywords", keywords);
            map.put("sortField1", sortField1);
            map.put("sortField2", sortField2);
            map.put("type", type);
            BaseActivity.postAsyn1(context, API.BASEURL + API.GET_RELEASE_MSG, map, this, 1, a);
        } catch (Exception e) {
        }
    }

    @Override
    public void callBack(Object object, int type) {
        try {
            Gson gson = new Gson();
            String result = new JSONObject(object.toString()).getString("err");
            if (type == 1) {
                if (result.equals("0")) {
                    refreshableView.setVisibility(View.VISIBLE);
                    linearLayout_prompt.setVisibility(View.GONE);
                    supply_demandBean = gson.fromJson(object.toString(), Supply_DemandBean.class);
                    list = supply_demandBean.getData();
                    if (itemBean.page == 1) {
                        refreshableView.onCompleteRefresh();
                        gq_listviewAdapter = new GQ_ListviewAdapter(itemBean.what, getActivity(), list);
                        gq_listview.setAdapter(gq_listviewAdapter);
                        list_more = list;
                        list = null;
                        //展示已更新多少数据
                        showRefreshPopou(supply_demandBean.getShow_msg());
                        //加载更多
                    } else if (list.size() != 0) {
                        isRefresh = false;
                        list_more.addAll(list);
                        gq_listviewAdapter.setList(list_more);
                        gq_listviewAdapter.notifyDataSetChanged();
                    }
                    //展示头部
                    if (new JSONObject(object.toString()).getJSONObject("top").length() != 0) {
                        topBean = supply_demandBean.getTop();
                        linearLayout_firstitem.setVisibility(View.VISIBLE);
                        user_id = topBean.getUser_id();
                        showInfo(topBean);
                    } else {
                        topBean = null;
                        linearLayout_firstitem.setVisibility(View.GONE);
                    }

                } else {//显示提示信息：
                    isRefresh = false;
                    itemBean.hasMoreData = false;
                    if (itemBean.page == 1) {
                        refreshableView.onCompleteRefresh();
                        refreshableView.setVisibility(View.GONE);
                        linearLayout_prompt.setVisibility(View.VISIBLE);
                        linearLayout_prompt.removeAllViews();
                        switch (result) {
                            case "1":
                            case "998":
//                                sharedUtils.setData(getActivity(), "token", "");
//                                sharedUtils.setData(getActivity(), "userid", "");
//                                sharedUtils.setBooloean(getActivity(), "logined", false);
                                break;
                            //没有更多数据
                            case "2":
                                View v = View.inflate(getActivity(), R.layout.layout_supplydemand_prompt_release2, null);
                                linearLayout_prompt.addView(v);
                                TextView text = (TextView) v.findViewById(R.id.supply_demand_text);
                                text.setText(new JSONObject(object.toString()).getString("msg"));
                                break;
                            //未匹配
                            case "4":
                                linearLayout_prompt.addView(View.inflate(getActivity(), R.layout.layout_supplydemand_prompt_release2, null));
                                break;
                            case "9"://去关注
                                follow_release = "follow";
                                View view = View.inflate(getActivity(), R.layout.layout_supplydemand_prompt_care1, null);
                                linearLayout_prompt.addView(view);
                                view.findViewById(R.id.supply_demand_follow).setOnClickListener(this);
                                break;
                            //关注 ——未发布
                            case "6":
                                linearLayout_prompt.addView(View.inflate(getActivity(), R.layout.layout_supplydemand_prompt_care2, null));
                                break;
                            //职能推荐——区发布
                            case "7":
                                follow_release = "release";
                                View view3 = View.inflate(getActivity(), R.layout.layout_supplydemand_prompt_release1, null);
                                linearLayout_prompt.addView(view3);
                                TextView t = (TextView) view3.findViewById(R.id.supply_demand_text);
                                t.setText(new JSONObject(object.toString()).getString("msg"));
                                view3.findViewById(R.id.supply_demand_follow).setOnClickListener(this);
                                view3.findViewById(R.id.img_supplydemad_down).setVisibility(View.VISIBLE);
                                break;
                            //我的供求——去发布
                            case "8":
                                follow_release = "release";
                                View view2 = View.inflate(getActivity(), R.layout.layout_supplydemand_prompt_release1, null);
                                linearLayout_prompt.addView(view2);
                                TextView textView = (TextView) view2.findViewById(R.id.supply_demand_text);
                                textView.setText(new JSONObject(object.toString()).getString("msg"));
                                view2.findViewById(R.id.supply_demand_follow).setOnClickListener(this);
                                view2.findViewById(R.id.img_supplydemad_down).setVisibility(View.GONE);
                                break;
                        }
                    }
                }
            }
            //是否消耗积分
            if (type == 2 && result.equals("99")) {
                String content = new JSONObject(object.toString()).getString("msg");
                DialogShowUtils dialogShowUtils = new DialogShowUtils();
                dialogShowUtils.showDialog(getActivity(), content, 1, this);
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
                DialogShowUtils dialogShowUtils = new DialogShowUtils();
                dialogShowUtils.showDialog(getActivity(), content, (result.equals("100")) ? (2) : (3), this);
            }
        } catch (Exception e) {
        }
    }

    @Override
    public void failCallBack(int type) {
        if (list_more.size() == 0) {
            refreshableView.setVisibility(View.GONE);
            linearLayout_prompt.setVisibility(View.VISIBLE);
            linearLayout_prompt.removeAllViews();

            ImageButton imageButton = new ImageButton(getActivity());
            imageButton.setImageResource(R.drawable.img_reload);
            linearLayout_prompt.addView(imageButton);
            imageButton.setBackgroundColor(getResources().getColor(R.color.color_white));
            imageButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    itemBean = ItemBean.getItemBean();
                    itemBean.page = 1;
                    itemBean.hasMoreData = true;
                    getNetData(getActivity(), "1", itemBean.keywords, itemBean.sortField1, itemBean.sortField2, itemBean.type, true);
                }
            });
        }
    }

    public void showInfo(Supply_DemandBean.TopBean topBean) {
        if (topBean != null) {
            Glide.with(getActivity()).load(topBean.getThumb()).placeholder(R.drawable.contact_image_defaul_male).into(tx);
            gs.setText("  " + topBean.getC_name() + "  " + topBean.getName());
            time.setText("  " + topBean.getInput_time());
            isbuyed.setText("出价(" + topBean.getDeliverPriceCount() + ")");
            repeat.setText("回复(" + topBean.getSaysCount() + ")");
            if (topBean.getIs_pass().equals("0")) {
                rz.setImageResource(R.drawable.icon_identity);
            } else if (topBean.getIs_pass().equals("1")) {
                rz.setImageResource(R.drawable.icon_identity_hl);
            }
            if (topBean.getType().equals("1")) {
                String html1 = "<font color='#EEAD0E'>" + " 求购：" + "</font>" + topBean.getContents();
                img.setImageResource(R.drawable.icon_purchase);
                content.setText(Html.fromHtml(html1));
            } else {
                img.setImageResource(R.drawable.icon_supply);
                String html1 = "<font color='#36648B'>" + " 供给：" + "</font>" + topBean.getContents();
                content.setText(Html.fromHtml(html1));
            }
            if (topBean.getContents().equals("")) {
                img.setVisibility(View.GONE);
                content.setVisibility(View.GONE);
            } else {
                img.setVisibility(View.VISIBLE);
                content.setVisibility(View.VISIBLE);
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

    //下拉刷新
    @Override
    public void refresh() {
        isRefresh = true;
        itemBean.page = 1;
        itemBean.hasMoreData = true;
        getNetData(getActivity(), itemBean.page + "", itemBean.keywords, itemBean.sortField1, itemBean.sortField2, itemBean.type, false);
    }

    public void showRefreshPopou(String text) {
        if (isRefresh) {
            isRefresh = false;
            if (TextUtils.isNullOrEmpty(text)) {
                showRefreshPopouinterface.showRefreshPopou(text);
            } else {
                TextUtils.Toast(getActivity(), "已是最新供求信息！");
            }
        }
    }

    public void setShowRefreshPopouinterface(ShowRefreshPopouinterface showRefreshPopouinterface) {
        this.showRefreshPopouinterface = showRefreshPopouinterface;
    }

    public interface ShowRefreshPopouinterface {
        void showRefreshPopou(String text);
    }
}

