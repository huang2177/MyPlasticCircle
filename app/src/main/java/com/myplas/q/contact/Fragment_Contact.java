package com.myplas.q.contact;

import android.app.ActivityOptions;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.huangbryant.hindicator.HIndicatorBuilder;
import com.huangbryant.hindicator.HIndicatorDialog;
import com.huangbryant.hindicator.OnDismissListener;
import com.myplas.q.R;
import com.myplas.q.common.api.API;
import com.myplas.q.common.appcontext.Constant;
import com.myplas.q.common.netresquset.ResultCallBack;
import com.myplas.q.common.utils.SharedUtils;
import com.myplas.q.common.utils.TextUtils;
import com.myplas.q.common.view.BaiduProgressBar;
import com.myplas.q.common.view.CommonDialog;
import com.myplas.q.common.view.MyNestedScrollView;
import com.myplas.q.common.view.RefreshPopou;
import com.myplas.q.contact.activity.AD_DialogActivtiy;
import com.myplas.q.contact.activity.ContactDaliySignActivity;
import com.myplas.q.contact.activity.Contact_Detail_Activity;
import com.myplas.q.contact.activity.Contact_Search_Activity;
import com.myplas.q.contact.activity.Cover_WebActivity;
import com.myplas.q.contact.adapter.Fragment_Contact_LV_Adapter;
import com.myplas.q.contact.adapter.Fragment_Dialog_Adapter;
import com.myplas.q.contact.beans.ContactBean;
import com.myplas.q.guide.activity.BaseActivity;
import com.myplas.q.myself.integral.activity.IntegralActivity;
import com.myplas.q.myself.integral.activity.IntegralPayActivtity;
import com.myplas.q.myself.login.LoginActivity;
import com.myplas.q.sockethelper.RabbitMQConfig;
import com.umeng.analytics.MobclickAgent;

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
public class Fragment_Contact extends Fragment implements View.OnClickListener
        , ResultCallBack
        , CommonDialog.DialogShowInterface
        , MyNestedScrollView.onScrollIterface
        , SwipeRefreshLayout.OnRefreshListener {

    private List<String> mList;
    private Map<Integer, Integer> map;
    private Fragment_Contact_LV_Adapter mLVAdapter;
    private List<ContactBean.PersonsBean> mListBean;

    private ListView listView;
    private ImageButton imageButton;
    private HIndicatorDialog dialog;
    private RefreshPopou mRefreshPopou;
    private MyNestedScrollView mScrollView;
    private ImageView mIVBanner, mIVTop, mSign;
    private View view, shareView1, shareView2;
    private SwipeRefreshLayout mRefreshLayout;
    private TextView mTVClass, mTVRegion, mTVTitle;
    private LinearLayout mLayoutCofig, mLayoutTop, mLayoutSearch;

    private int page;
    private StringBuffer region;
    private StringBuffer c_type;
    private boolean isRefreshing;
    private SharedUtils sharedUtils;
    private ContactBean mContactBean;
    private String userId, jumpUrl, jumpToWhere, jumpTitle;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        getNetData("1", true);
    }

    private void initView() {
        page = 1;
        map = new HashMap<>();
        region = new StringBuffer("0");
        c_type = new StringBuffer("0");
        mListBean = new ArrayList<>();
        sharedUtils = SharedUtils.getSharedUtils();
        mRefreshPopou = new RefreshPopou(getActivity(), 2);
        sharedUtils.setData(getActivity(), Constant.POINTSINFO, "您还未登录,请先登录塑料圈!");
        view = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_layout_contact, null, false);

        mIVTop = (ImageView) view.findViewById(R.id.top_img);
        mSign = (ImageView) view.findViewById(R.id.contact_sign);
        listView = (ListView) view.findViewById(R.id.contact_lv);
        mTVTitle = (TextView) view.findViewById(R.id.contanct_title);
        mTVRegion = (TextView) view.findViewById(R.id.contact_region);
        mTVClass = (TextView) view.findViewById(R.id.contact_classify);
        imageButton = (ImageButton) view.findViewById(R.id.img_reload);
        mIVBanner = (ImageView) view.findViewById(R.id.contact_banner_img);
        mLayoutTop = (LinearLayout) view.findViewById(R.id.contact_top_ll);
        mLayoutCofig = (LinearLayout) view.findViewById(R.id.contact_config_ll);
        mLayoutSearch = (LinearLayout) view.findViewById(R.id.contact_search_ll);
        mScrollView = (MyNestedScrollView) view.findViewById(R.id.contact_scrollview);
        mRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.contact_swipelayout);

        mSign.setOnClickListener(this);
        mTVClass.setOnClickListener(this);
        mTVRegion.setOnClickListener(this);
        mIVBanner.setOnClickListener(this);
        mLayoutTop.setOnClickListener(this);
        imageButton.setOnClickListener(this);
        mScrollView.setOnScrollIterface(this);
        mLayoutSearch.setOnClickListener(this);
        mRefreshLayout.setOnRefreshListener(this);
        mRefreshLayout.setColorSchemeResources(R.color.color_red);


        //点击item判断是否消耗积分
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                checkIsLogin();
                shareView1 = view.findViewById(R.id.xq_tx);
                shareView2 = view.findViewById(R.id.xq_rz);
                userId = mListBean.get(position).getUser_id();
                getPersonInfoData(userId, "1", 2);
            }
        });

        //填充数据
        loadCacheData(new Gson(), sharedUtils.getData(getActivity(), "txlBean"), false);

//        popupWindow.setInputMethodMode(PopupWindow.INPUT_METHOD_NEEDED);//设置弹出窗体需要软键盘，
//        popupWindow.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);  //再设置模式，和Activity的一样，覆盖。
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return view;
    }

    @Override
    public void onClick(View v) {
        if (!checkIsLogin() && v.getId() != R.id.img_reload) {
            return;
        }
        switch (v.getId()) {
            case R.id.contact_banner_img:
                JumpToWhere();
                break;
            case R.id.contact_classify:
                openDialog(1, mTVClass);
                break;
            case R.id.contact_region:
                openDialog(2, mTVRegion);
                break;
            case R.id.img_reload:
                page = 1;
                getNetData("1", true);
                break;
            case R.id.contact_top_ll:
                shareView1 = view.findViewById(R.id.xq_tx);
                shareView2 = view.findViewById(R.id.xq_rz);
                userId = mContactBean.getTop().getUser_id();
                getPersonInfoData(userId, "1", 2);
                break;
            case R.id.contact_search_ll:
                startActivity(new Intent(getActivity(), Contact_Search_Activity.class));
                getActivity().overridePendingTransition(R.anim.fade, R.anim.hold);
                break;
            case R.id.contact_sign:
                startActivity(new Intent(getActivity(), ContactDaliySignActivity.class));
                break;
            default:
                break;
        }

    }

    private void openDialog(final int type, final TextView textView) {
        Fragment_Dialog_Adapter adapter = new Fragment_Dialog_Adapter(type, map) {
            @Override
            public void onItemSelected(String show, String value) {
                dialog.dismiss();
                textView.setText(show);
                textView.setTextColor(getResources().getColor(R.color.color_red));
                textView.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.down, 0);
                if (type == 1) {
                    c_type = new StringBuffer(value);
                } else {
                    region = new StringBuffer(value);
                }
                page = 1;
                mRefreshPopou.setCanShowPopou(false);
                getNetData("1", true);
            }
        };
        dialog = new HIndicatorBuilder(getActivity())
                .width(400)
                .height(-1)
                .ArrowDirection(HIndicatorBuilder.TOP)
                .bgColor(Color.parseColor("#ffffff"))
                .gravity(HIndicatorBuilder.GRAVITY_LEFT)
                .radius(10)
                .arrowWidth(30)
                .ArrowRectage(0.5f)
                .layoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false))
                .dimEnabled(true)
                .dimAmount(0.4f)
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

    /**
     * 获取数据
     *
     * @param page         the page
     * @param isShowDialog 是否显示dialog
     */
    public void getNetData(String page, boolean isShowDialog) {
        sharedUtils = SharedUtils.getSharedUtils();
        Map<String, String> map = new HashMap<String, String>();
        map.put("page", page);
        map.put("size", "15");
        map.put("keywords", "");
        map.put("c_type", c_type.toString());
        map.put("region", region.toString());
        String url = API.BASEURL + API.GET_PLASTIC_PERSON;
        BaseActivity.postAsyn1(getActivity(), url, map, this, 1, isShowDialog);
    }

    /**
     * 检查是否消耗过积分
     *
     * @param userId
     * @param showtype
     * @param type
     */
    private void getPersonInfoData(String userId, String showtype, int type) {
        Map<String, String> map = new HashMap<String, String>();
        map.put("user_id", userId);
        map.put("showType", showtype);
        map.put("token", sharedUtils.getData(getActivity(), "token"));
        String url = API.BASEURL + API.GET_ZONE_FRIEND;
        BaseActivity.postAsyn1(getActivity(), url, map, this, type, false);
    }

    @Override
    public void callBack(Object object, int type) {
        try {
            Gson gson = new Gson();
            listView.setVisibility(View.VISIBLE);
            imageButton.setVisibility(View.GONE);
            JSONObject jsonObject = new JSONObject(object.toString());
            String err = jsonObject.getString("err");
            if (type == 1) {
                if (err.equals("0")) {
                    sharedUtils.setData(getActivity(), "txlBean", object.toString());
                    loadCacheData(gson, object.toString(), true);

                    if (page == 1 && isRefreshing) {
                        isRefreshing = false;
                        RabbitMQConfig.getInstance(getActivity()).readMsg("unread_customer", 10);
                    }
                }
                if (err.equals("2") || err.equals("3")) {
                    TextUtils.Toast(getActivity(), jsonObject.getString("msg"));
                }
            }
            //是否消耗积分
            if (type == 2 && err.equals("99")) {
                String content = new JSONObject(object.toString()).getString("msg");
                CommonDialog commonDialog = new CommonDialog();
                commonDialog.showDialog(getActivity(), content, 1, this);
            }
            //已经消费了积分
            if (type == 2 && err.equals("0")) {
                Intent intent = new Intent(getActivity(), Contact_Detail_Activity.class);
                intent.putExtra("userid", userId);
                startActivity(intent);
            }
            //减积分成功
            if (type == 3 && err.equals("0")) {
                Intent intent = new Intent(getActivity(), Contact_Detail_Activity.class);
                intent.putExtra("userid", userId);
                startActivity(intent);
            }
            //积分不够
            if (type == 3 && !err.equals("0")) {
                String content = jsonObject.getString("msg");
                CommonDialog commonDialog = new CommonDialog();
                commonDialog.showDialog(getActivity(), content, (err.equals("100")) ? (2) : (3), this);
            }
            boolean isLoginout = (type == 1 && err.equals("1") || err.equals("998"))
                    || (type == 10 && !err.equals("0"));
            if (isLoginout) {
                sharedUtils.setData(getActivity(), Constant.TOKEN, "");
                sharedUtils.setData(getActivity(), Constant.USERID, "");
                sharedUtils.setBooloean(getActivity(), Constant.LOGINED, false);
                sharedUtils.setData(getActivity(), Constant.POINTSINFO, jsonObject.getString("msg"));
            }
        } catch (Exception e) {
        }
    }

    private void _startActivity(Intent intent) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(getActivity()
                    , shareView1
                    , "shareView1"
            ).toBundle());
        } else {
            startActivity(intent);
        }
    }

    /**
     * 默认先加载缓存里面数据
     *
     * @param gson
     * @param json
     * @param isShowCover
     */
    private void loadCacheData(Gson gson, String json, boolean isShowCover) {
        try {
            mContactBean = gson.fromJson(json, ContactBean.class);
            ContactBean.TopBean topBean = (mContactBean.getTop().getC_name() != null) ? mContactBean.getTop() : (null);
            if (page == 1) {
                showInfo(mContactBean, topBean);
            } else {
                mListBean.addAll(mContactBean.getPersons());
                mLVAdapter.setList(mListBean);
                mLVAdapter.notifyDataSetChanged();
            }
        } catch (Exception e) {
        }
    }


    private void showInfo(ContactBean bean, ContactBean.TopBean topBean) {
        mTVTitle.setText("塑料圈通讯录(" + bean.getMember() + "人)");
        //editText.setHint(txlBean.getHot_search().equals("") ? "大家都在搜：" + txlBean.getHot_search() : "大家都在搜：7000F");

//        显示list数据
        mLVAdapter = new Fragment_Contact_LV_Adapter(getActivity(), bean.getPersons(), topBean);
        listView.setAdapter(mLVAdapter);
        mListBean.clear();
        mListBean.addAll(bean.getPersons());

        //展示已更新多少数据
        mRefreshLayout.setRefreshing(false);
        mRefreshPopou.show(mLayoutCofig, bean.getShow_msg());

        /*展示置顶信息*/
        showTop(topBean);

        //判断是否显示banner ；
        if (bean.getIs_show_banner().equals("1")) {
            mIVBanner.setVisibility(View.VISIBLE);
            jumpUrl = bean.getBanner_jump_url();
            jumpTitle = bean.getBanner_jump_url_title();
            jumpToWhere = bean.getIs_banner_jump_native();
            Glide.with(getActivity())
                    .load(bean.getBanner_url())
                    .into(mIVBanner);
        } else {
            mIVBanner.setVisibility(View.GONE);
        }
        //判断图层是否显示
        boolean isshow = sharedUtils.getBoolean(getActivity(), "isshow");
        if (bean.getIs_show_cover().equals("1") && isshow) {
            Intent intent = new Intent(getActivity(), AD_DialogActivtiy.class);
            intent.putExtra("imgurl", bean.getCover_url());
            intent.putExtra("url", bean.getCover_jump_url());
            intent.putExtra("title", bean.getCover_jump_url_title());
            startActivity(intent);
        }
    }

    private void showTop(ContactBean.TopBean topBean) {
        if (topBean != null) {
            ContactBean.PersonsBean personsBean = new ContactBean.PersonsBean();
            personsBean.setSex(topBean.getSex());
            personsBean.setType(topBean.getType());
            personsBean.setName(topBean.getName());
            personsBean.setThumb(topBean.getThumb());
            personsBean.setC_name(topBean.getC_name());
            personsBean.setIs_pass(topBean.getIs_pass());
            personsBean.setMain_product(topBean.getMain_product());
            personsBean.setNeed_product(topBean.getNeed_product());
            personsBean.setMonth_consum(topBean.getMonth_consum());

            mLVAdapter.initView(mLVAdapter.getviewHolder(), mLayoutTop);
            mLVAdapter.showInfo(mLVAdapter.getviewHolder(), personsBean);
            mIVTop.setVisibility(View.VISIBLE);
            mLayoutTop.setVisibility(View.VISIBLE);
        } else {
            mLayoutTop.setVisibility(View.GONE);
        }
    }

    @Override
    public void failCallBack(int type) {
        mRefreshLayout.setRefreshing(false);
        if (mListBean.size() == 0) {
            listView.setVisibility(View.GONE);
            imageButton.setVisibility(View.VISIBLE);
        }
    }

    //dialog接口回调
    @Override
    public void ok(int type) {
        switch (type) {
            case 1:
                getPersonInfoData(userId, "5", 3);
                break;
            case 2:
                startActivity(new Intent(getActivity(), IntegralPayActivtity.class));
                break;
            case 4:
                startActivity(new Intent(getActivity(), LoginActivity.class));
                break;
            default:
                break;
        }
    }

    /**
     * 下拉刷新
     */
    @Override
    public void onRefresh() {
        page = 1;
        isRefreshing = true;
        mRefreshPopou.setCanShowPopou(true);
        getNetData("1", false);
    }

    /**
     * 加载更多
     */
    @Override
    public void loadMore() {
        page++;
        checkPageNum();
        getNetData(page + "", false);
    }


    /**
     * Jump to where.
     */
    public void JumpToWhere() {
        checkIsLogin();
        if ("1".equals(jumpToWhere)) {  //原生
            Intent intent = new Intent(getActivity(), IntegralActivity.class);
            startActivity(intent);
        } else {                        //指定的url
            Intent intent = new Intent(getActivity(), Cover_WebActivity.class);
            intent.putExtra("url", jumpUrl);
            intent.putExtra("title", jumpTitle);
            startActivity(intent);
        }
    }

    /**
     * 检查是否登录
     */
    private boolean checkIsLogin() {
        boolean logined = sharedUtils.getBoolean(getActivity(), Constant.LOGINED);
        if (!logined) {
            CommonDialog commonDialog = new CommonDialog();
            commonDialog.showDialog(getActivity()
                    , sharedUtils.getData(getActivity(), Constant.POINTSINFO)
                    , 4
                    , this);
        }
        return logined;
    }

    /**
     * 未登录情况下检查上拉加载的page是否大于4
     */
    private void checkPageNum() {
        boolean logined = sharedUtils.getBoolean(getActivity(), "logined");
        if (!logined && page > 4) {
            CommonDialog commonDialog = new CommonDialog();
            commonDialog.showDialog(getActivity()
                    , sharedUtils.getData(getActivity(), Constant.POINTSINFO)
                    , 4
                    , this);
            return;
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        //统计页面，"MainScreen"为页面名称，可自定义
        MobclickAgent.onPageStart("MainScreen");
        //检查登录状态
        BaseActivity.postAsyn1(getActivity(), API.BASEURL + API.VALIDUSERTOKEN, null, this, 10, false);
    }

    @Override
    public void onPause() {
        super.onPause();
        MobclickAgent.onPageEnd("MainScreen");
        mRefreshPopou.dismiss();
    }
}
