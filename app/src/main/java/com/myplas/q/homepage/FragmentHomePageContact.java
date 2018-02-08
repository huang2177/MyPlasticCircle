package com.myplas.q.homepage;

import android.app.ActivityOptions;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
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
import com.myplas.q.app.fragment.BaseFragment;
import com.myplas.q.common.api.API;
import com.myplas.q.common.appcontext.Constant;
import com.myplas.q.common.net.ResultCallBack;
import com.myplas.q.common.utils.ContactAccessUtils;
import com.myplas.q.common.utils.SharedUtils;
import com.myplas.q.common.utils.TextUtils;
import com.myplas.q.common.view.CommonDialog;
import com.myplas.q.common.view.MyNestedScrollView;
import com.myplas.q.common.view.RefreshPopou;
import com.myplas.q.common.view.marqueeview.MarqueeFactory;
import com.myplas.q.common.view.marqueeview.MarqueeViewHelper;
import com.myplas.q.homepage.activity.AD_DialogActivtiy;
import com.myplas.q.homepage.activity.Cover_WebActivity;
import com.myplas.q.homepage.activity.NewContactDetailActivity;
import com.myplas.q.homepage.adapter.Fragment_Contact_LV_Adapter;
import com.myplas.q.homepage.adapter.Fragment_Dialog_Adapter;
import com.myplas.q.homepage.beans.ContactBean;
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
 * 电话：15378412400
 * 邮箱：15378412400@163.com
 * 时间：2017/3/17 14:45
 *
 * @author 黄双
 */
public class FragmentHomePageContact extends BaseFragment implements View.OnClickListener
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
    private ImageView mIVBanner, mIVTop;
    private TextView mTVClass, mTVRegion;
    private MyNestedScrollView mScrollView;
    private View view, shareView1, shareView2;
    private SwipeRefreshLayout mRefreshLayout;
    private LinearLayout mLayoutCofig, mLayoutTop, notifyRoot;

    public int page;
    private SharedUtils sharedUtils;
    private ContactBean mContactBean;
    private ContactAccessUtils accessUtils;
    private boolean isRefreshing, isLoading;
    private String region, c_type, stauts, showCType;
    private String jumpUrl, jumpToWhere, jumpTitle, isShop;


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
        accessUtils = new ContactAccessUtils(getActivity());
        mRefreshPopou = new RefreshPopou(getActivity(), 2);
        sharedUtils.setData(getActivity(), Constant.POINTSINFO, "您还未登录,请先登录塑料圈!");
        view = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_layout_homepage_contact, null, false);

        mIVTop = F(view, R.id.top_img);
        listView = F(view, R.id.contact_lv);
        imageButton = F(view, R.id.img_reload);
        notifyRoot = F(view, R.id.notify_root);
        mTVRegion = F(view, R.id.contact_region);
        mLayoutTop = F(view, R.id.contact_top_ll);
        mTVClass = F(view, R.id.contact_classify);
        mIVBanner = F(view, R.id.contact_banner_img);
        mScrollView = F(view, R.id.contact_scrollview);
        mLayoutCofig = F(view, R.id.contact_config_ll);
        mRefreshLayout = F(view, R.id.contact_swipelayout);

        mTVClass.setOnClickListener(this);
        mTVRegion.setOnClickListener(this);
        mIVBanner.setOnClickListener(this);
        notifyRoot.setOnClickListener(this);
        mLayoutTop.setOnClickListener(this);
        imageButton.setOnClickListener(this);
        mScrollView.setOnScrollIterface(this);
        mRefreshLayout.setOnRefreshListener(this);
        mRefreshLayout.setColorSchemeResources(R.color.color_red);


        //点击item判断是否消耗积分
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                TextUtils.isLogin(getContext(), FragmentHomePageContact.this);
                shareView1 = view.findViewById(R.id.xq_tx);
                shareView2 = view.findViewById(R.id.xq_rz);
                accessUtils.checkPremissions(mListBean.get(position));
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
        if (!TextUtils.isLogin(getContext(), this) && v.getId() != R.id.img_reload) {
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
                accessUtils.checkPremissions(mContactBean.getTop().getUser_id()
                        , mContactBean.getTop().getIsshop());
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
        Map<String, String> map = new HashMap<String, String>(16);
        map.put("page", page);
        map.put("size", "15");
        map.put("keywords", "");
        map.put("type", c_type);
        map.put("region", region);
        getAsyn(getActivity(), API.PLASTICPERSON, map, this, 1, isShowDialog);
    }

    @Override
    public void callBack(Object object, int type) {
        try {
            Gson gson = new Gson();
            JSONObject jsonObject = new JSONObject(object.toString());
            String err = jsonObject.getString("code");
            isLoading = false;
            if ("0".equals(err)) {
                sharedUtils.setData(getActivity(), "txlBean", object.toString());
                loadCacheData(gson, object.toString(), true);

                if (page == 1 && isRefreshing) {
                    isRefreshing = false;
                    //mVHelper.start();
                    RabbitMQConfig.getInstance(getActivity()).readMsg("unread_customer", 10);
                }
            }
        } catch (Exception e) {
        }
    }

    @Override
    public void failCallBack(int type, String message, int httpCode) {
        try {
            JSONObject jsonObject = new JSONObject(message);
            isLoading = false;
            if (page == 1) {
                mRefreshLayout.setRefreshing(false);
            }
            if (httpCode == 404) {
                TextUtils.toast(getActivity(), jsonObject.getString("message"));
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
        showCType = bean.getSelected_type();
        mTVClass.setText(showCType);
        //mTVTitle.setText("塑料圈通讯录(" + bean.getMember() + "人)");
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
            personsBean.setIsshop(topBean.getIsshop());
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

    //dialog接口回调
    @Override
    public void dialogClick(int type) {
        switch (type) {
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
        TextUtils.isLogin(getContext(), this);
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
        if (!TextUtils.isLogin(getContext(), this)) {
            return;
        }
        DefConfigBean.NoticeBean.CommunicateContentBean bean = (DefConfigBean.NoticeBean.CommunicateContentBean) holder.data;
        if (bean != null && accessUtils != null) {
            accessUtils.checkPremissions(bean.getId(), bean.getMerge_three());
        }
    }
}
