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
import android.util.Log;
import android.util.SparseArray;
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
import com.myplas.q.app.activity.BaseActivity;
import com.myplas.q.common.api.API;
import com.myplas.q.common.appcontext.Constant;
import com.myplas.q.common.netresquset.ResultCallBack;
import com.myplas.q.common.utils.SharedUtils;
import com.myplas.q.common.utils.TextUtils;
import com.myplas.q.common.view.CommonDialog;
import com.myplas.q.common.view.MyNestedScrollView;
import com.myplas.q.common.view.RefreshPopou;
import com.myplas.q.common.view.marqueeview.MarqueeFactory;
import com.myplas.q.common.view.marqueeview.MarqueeViewHelper;
import com.myplas.q.contact.activity.AD_DialogActivtiy;
import com.myplas.q.contact.activity.ContactDaliySignActivity;
import com.myplas.q.contact.activity.ContactDetailActivity;
import com.myplas.q.contact.activity.NewContactDetailActivity;
import com.myplas.q.contact.activity.Contact_Search_Activity;
import com.myplas.q.contact.activity.Cover_WebActivity;
import com.myplas.q.contact.adapter.Fragment_Contact_LV_Adapter;
import com.myplas.q.contact.adapter.Fragment_Dialog_Adapter;
import com.myplas.q.contact.beans.ContactBean;
import com.myplas.q.myself.integral.activity.IntegralPayActivtity;
import com.myplas.q.myself.login.LoginActivity;
import com.myplas.q.myself.store.MyStoreActivity;
import com.myplas.q.sockethelper.DefConfigBean;
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
        , SwipeRefreshLayout.OnRefreshListener, MarqueeFactory.OnItemClickListener {

    private List<String> mList;
    private SparseArray<Integer> map;
    private MarqueeViewHelper mVHelper;
    private Fragment_Contact_LV_Adapter mLVAdapter;
    private List<ContactBean.PersonsBean> mListBean;

    private ListView listView;
    private ImageButton imageButton;
    private HIndicatorDialog dialog;
    private RefreshPopou mRefreshPopou;
    private MyNestedScrollView mScrollView;
    private View view, shareView1, shareView2;
    private SwipeRefreshLayout mRefreshLayout;
    private ImageView mIVBanner, mIVTop, mSign;
    private TextView mTVClass, mTVRegion, mTVTitle;
    private LinearLayout mLayoutCofig, mLayoutTop, mLayoutSearch, notifyRoot;

    public int page;
    private SharedUtils sharedUtils;
    private ContactBean mContactBean;
    private boolean isRefreshing, isLoading;
    private String region, c_type, stauts, showCType;
    private String userId, jumpUrl, jumpToWhere, jumpTitle, mergeThere, isShop;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        getNetData("1", true);
    }

    private void initView() {
        page = 1;
        region = "0";
        c_type = "8"; //传‘8’表示 默认数据由后台控制
        map = new SparseArray<>();
        mListBean = new ArrayList<>();
        mVHelper = new MarqueeViewHelper();
        sharedUtils = SharedUtils.getSharedUtils();
        mRefreshPopou = new RefreshPopou(getActivity(), 2);
        sharedUtils.setData(getActivity(), Constant.POINTSINFO, "您还未登录,请先登录塑料圈!");
        view = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_layout_contact, null, false);

        mIVTop = F(R.id.top_img);
        mSign = F(R.id.contact_sign);
        listView = F(R.id.contact_lv);
        imageButton = F(R.id.img_reload);
        notifyRoot = F(R.id.notify_root);
        mTVTitle = F(R.id.contanct_title);
        mTVRegion = F(R.id.contact_region);
        mLayoutTop = F(R.id.contact_top_ll);
        mTVClass = F(R.id.contact_classify);
        mIVBanner = F(R.id.contact_banner_img);
        mScrollView = F(R.id.contact_scrollview);
        mLayoutCofig = F(R.id.contact_config_ll);
        mLayoutSearch = F(R.id.contact_search_ll);
        mRefreshLayout = F(R.id.contact_swipelayout);

        mSign.setOnClickListener(this);
        mTVClass.setOnClickListener(this);
        mTVRegion.setOnClickListener(this);
        mIVBanner.setOnClickListener(this);
        notifyRoot.setOnClickListener(this);
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
                isLogin();
                shareView1 = view.findViewById(R.id.xq_tx);
                shareView2 = view.findViewById(R.id.xq_rz);
                userId = mListBean.get(position).getUser_id();
                mergeThere = mListBean.get(position).getMerge_three();
                getPersonInfoData(userId, "1", 2);
            }
        });

        //填充数据
        loadCacheData(new Gson(), sharedUtils.getData(getActivity(), "txlBean"), false);

//        popupWindow.setInputMethodMode(PopupWindow.INPUT_METHOD_NEEDED);//设置弹出窗体需要软键盘，
//        popupWindow.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);  //再设置模式，和Activity的一样，覆盖。
    }

    public <T extends View> T F(int id) {
        return (T) view.findViewById(id);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return view;
    }

    @Override
    public void onClick(View v) {
        if (!isLogin() && v.getId() != R.id.img_reload) {
            return;
        }
        switch (v.getId()) {
            case R.id.contact_banner_img:
                jumpToWhere();
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
                mergeThere = mContactBean.getTop().getMerge_three();
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

        Fragment_Dialog_Adapter adapter = new Fragment_Dialog_Adapter(type, showCType, map) {
            @Override
            public void onItemSelected(String show, String value) {
                dialog.dismiss();
                textView.setText(show);
                textView.setTextColor(getResources().getColor(R.color.color_red));
                textView.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.down, 0);
                if (type == 1) {
                    c_type = value;
                } else {
                    region = value;
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
        map.put("c_type", c_type);
        map.put("region", region);
        String url = API.BASEURL + API.GET_PLASTIC_PERSON;
        BaseActivity.postAsyn(getActivity(), url, map, this, 1, isShowDialog);
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
        BaseActivity.postAsyn(getActivity(), url, map, this, type, false);
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
                isLoading = false;
                if (err.equals("0")) {
                    sharedUtils.setData(getActivity(), "txlBean", object.toString());
                    loadCacheData(gson, object.toString(), true);

                    if (page == 1 && isRefreshing) {
                        isRefreshing = false;
                        //mVHelper.start();
                        RabbitMQConfig.getInstance(getActivity()).readMsg("unread_customer", 10);
                    }
                }
                if ("2".equals(err) || "3".equals(err)) {
                    TextUtils.toast(getActivity(), jsonObject.getString("msg"));
                }
            }
            //是否消耗积分
            if (type == 2 && err.equals("99")) {
                String content = new JSONObject(object.toString()).getString("msg");
                CommonDialog commonDialog = new CommonDialog();
                commonDialog.showDialog(getActivity(), content, 1, this);
            }
            //已经消费了积分 或者 减积分成功
            boolean b = type == 2 || type == 3;
            if (b && "0".equals(err)) {
                Intent intent = getIntent(mergeThere);
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

    /**
     * 通过共享元素启动activtiy
     *
     * @param intent
     */
    private void startActivityByTras(Intent intent) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(getActivity()
                    , shareView1
                    , "shareView1").toBundle());
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
            if (page == 1) {
                showInfo(mContactBean);
            } else {
                mListBean.addAll(mContactBean.getPersons());
                mLVAdapter.setList(mListBean);
                mLVAdapter.notifyDataSetChanged();
            }
        } catch (Exception e) {
        }
    }


    private void showInfo(ContactBean bean) {
        showCType = bean.getShow_ctype();
        mTVClass.setText(showCType);
        mTVTitle.setText("塑料圈通讯录(" + bean.getMember() + "人)");
//        editText.setHint(txlBean.getHot_search().equals("") ? "大家都在搜：" + txlBean.getHot_search() : "大家都在搜：7000F");

//        显示list数据
        mLVAdapter = new Fragment_Contact_LV_Adapter(getActivity(), bean.getPersons());
        listView.setAdapter(mLVAdapter);
        mListBean.clear();
        mListBean.addAll(bean.getPersons());

        //展示已更新多少数据
        mRefreshLayout.setRefreshing(false);
        mRefreshPopou.show(mLayoutCofig, bean.getShow_msg());

        /*展示置顶信息*/
        showTop(bean.getTop());

        //判断是否显示banner ；
        if ("1".equals(bean.getIs_show_banner())) {
            jumpUrl = bean.getBanner_jump_url();
            jumpTitle = bean.getBanner_jump_url_title();
            jumpToWhere = bean.getIs_banner_jump_native();
            sharedUtils.setData(getActivity(), Constant.STAUTS, bean.getShop_audit_status());

            mIVBanner.setVisibility(View.VISIBLE);
            Glide.with(getActivity()).load(bean.getBanner_url()).into(mIVBanner);
        } else {
            mIVBanner.setVisibility(View.GONE);
        }
        //判断图层是否显示
        boolean isshow = sharedUtils.getBoolean(getActivity(), "isshow");
        if ("1".equals(bean.getIs_show_cover()) && isshow) {
            Intent intent = new Intent(getActivity(), AD_DialogActivtiy.class);
            intent.putExtra("imgurl", bean.getCover_url());
            intent.putExtra("url", bean.getCover_jump_url());
            intent.putExtra("title", bean.getCover_jump_url_title());
            startActivity(intent);
        }
    }

    private void showTop(ContactBean.TopBean topBean) {
        topBean = topBean.getC_name() == null ? null : topBean;
        if (topBean != null) {
            ContactBean.PersonsBean personsBean = new ContactBean.PersonsBean();
            personsBean.setSex(topBean.getSex());
            personsBean.setType(topBean.getType());
            personsBean.setName(topBean.getName());
            personsBean.setThumb(topBean.getThumb());
            personsBean.setMobile(topBean.getMobile());
            personsBean.setC_name(topBean.getC_name());
            personsBean.setIs_pass(topBean.getIs_pass());
            personsBean.setMerge_three(topBean.getMerge_three());
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
    public void dialogClick(int type) {
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
        // mVHelper.stop();
        mRefreshPopou.setCanShowPopou(true);
        getNetData("1", false);
    }

    /**
     * 加载更多
     */
    @Override
    public void loadMore() {
        if (!isLoading) {
            page++;
            checkPageNum();
            isLoading = true;
            getNetData(page + "", false);
        }
    }


    /**
     * Jump to where.
     */
    private void jumpToWhere() {
        isLogin();
        if ("1".equals(jumpToWhere)) {  //原生
            stauts = sharedUtils.getData(getActivity(), Constant.STAUTS);
            if ("1".equals(stauts)) {
                Intent intent = new Intent(getActivity(), NewContactDetailActivity.class);
                intent.putExtra(Constant.USERID, sharedUtils.getData(getActivity(), Constant.USERID));
                startActivity(intent);
            } else {
                Intent intent = new Intent(getActivity(), MyStoreActivity.class);
                intent.putExtra(Constant.STAUTS, stauts);
                startActivity(intent);
            }
        } else {                       //指定的url
            Intent intent = new Intent(getActivity(), Cover_WebActivity.class);
            intent.putExtra("url", jumpUrl);
            intent.putExtra("title", jumpTitle);
            startActivity(intent);
        }
    }

    /**
     * 检查是否登录
     */
    private boolean isLogin() {
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

    /**
     * 判断是否跳转到店铺
     *
     * @param flag
     * @return
     */
    public Intent getIntent(String flag) {
        Intent intent = new Intent();
        intent.setClass(getActivity(), "1".equals(flag)
                ? NewContactDetailActivity.class
                : ContactDetailActivity.class);
        return intent;
    }

    @Override
    public void onResume() {
        super.onResume();
        //统计页面，"MainScreen"为页面名称，可自定义
        MobclickAgent.onPageStart("MainScreen");
        //检查登录状态
        BaseActivity.postAsyn(getActivity(), API.BASEURL + API.VALIDUSERTOKEN, null, this, 10, false);
    }


    @Override
    public void onPause() {
        super.onPause();
        MobclickAgent.onPageEnd("MainScreen");
        mRefreshPopou.dismiss();
    }


    @Override
    public void onStart() {
        super.onStart();
        mVHelper.start();
    }

    @Override
    public void onStop() {
        super.onStop();
        mVHelper.stop();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mVHelper != null) {
            mVHelper.onDestroy();
        }
    }

    /**
     * 展示滚动通知
     *
     * @param datas
     */
    public void showMarquee(List datas, int type) {
        try {
            mVHelper.onResume(getActivity(), notifyRoot, datas, type, this);
        } catch (Exception e) {
            notifyRoot.setVisibility(View.GONE);
        }
    }

    public void hideMarquee() {
        if (mVHelper != null) {
            mVHelper.hide();
        }
    }

    @Override
    public void onItemClickListener(MarqueeFactory.ViewHolder holder) {
        if (!isLogin()) {
            return;
        }
        DefConfigBean.NoticeBean.CommunicateContentBean bean = (DefConfigBean.NoticeBean.CommunicateContentBean) holder.data;
        if (bean != null) {
            userId = bean.getId();
            mergeThere = bean.getMerge_three();
            getPersonInfoData(userId, "1", 2);
        }
    }
}
