package com.myplas.q.headlines;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.NestedScrollView;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ScrollView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.huangbryant.hbanner.HBanner;
import com.huangbryant.hbanner.HBannerConfig;
import com.huangbryant.hbanner.Transformer;
import com.huangbryant.hbanner.listener.OnHBannerClickListener;
import com.huangbryant.hbanner.loader.ImageLoader;
import com.huangbryant.hbanner.view.TagImageView;
import com.myplas.q.R;
import com.myplas.q.common.api.API;
import com.myplas.q.common.netresquset.ResultCallBack;
import com.myplas.q.common.utils.ACache;
import com.myplas.q.common.utils.AndroidUtil;
import com.myplas.q.common.utils.NetUtils;
import com.myplas.q.common.utils.ScreenUtils;
import com.myplas.q.common.utils.SharedUtils;
import com.myplas.q.common.utils.StatusUtils;
import com.myplas.q.common.utils.TextUtils;
import com.myplas.q.common.view.CommonDialog;
import com.myplas.q.common.view.EmptyView;
import com.myplas.q.common.view.MyNestedScrollView;
import com.myplas.q.common.view.RefreshPopou;
import com.myplas.q.guide.activity.BaseActivity;
import com.myplas.q.headlines.activity.HeadLinesDetailActivity;
import com.myplas.q.headlines.adapter.CateListAdapter;
import com.myplas.q.headlines.adapter.SubcribleAdapter;
import com.myplas.q.headlines.bean.CateListBean;
import com.myplas.q.headlines.bean.SubcribleBean;
import com.myplas.q.myinfo.integral.activity.IntegralActivity;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HeadLineListFragment extends Fragment implements ResultCallBack
        , View.OnClickListener
        , CommonDialog.DialogShowInterface
        , MyNestedScrollView.onScrollIterface
        , SwipeRefreshLayout.OnRefreshListener {
    public boolean isFree;
    public String keywords1;
    private SharedUtils sharedUtils;
    public String cate_id, keywords, clickId;
    public int page, po, visibleItemCount, lastvisibleItemCount;

    private HBanner mBanner;
    private ListView mListView;
    private EmptyView emptyView;
    private View view, mHeadView;
    private ImageView mBannerImg;
    public RefreshPopou mRefreshPopou;
    private MyNestedScrollView mScrollView;
    private CateListAdapter cateListAdapter;
    private SwipeRefreshLayout mRefreshLayout;
    private SubcribleAdapter mSubcribleAdapter;
    private ImageButton imageButton, imageButton_backup;

    private List<SubcribleBean.BannerBean> mBeanList;
    private List<CateListBean.InfoBean> list_catelist;
    private List<SubcribleBean.DataBean> list_subcirble;

    private List<String> mListId;
    private List<String> mListImg;
    private List<Boolean> mListTag;
    private List<String> mListTitle;
    public Myinterface mMyinterface;

    private ACache mACache;
    private Handler mHandler;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        intiView();
    }

    private void intiView() {
        mHandler = new Handler();
        mListId = new ArrayList<>();
        mListImg = new ArrayList<>();
        mListTag = new ArrayList<>();
        mListTitle = new ArrayList<>();
        list_catelist = new ArrayList<>();
        list_subcirble = new ArrayList<>();
        mACache = ACache.get(getActivity());
        emptyView = new EmptyView(getActivity());
        sharedUtils = SharedUtils.getSharedUtils();
        mRefreshPopou = new RefreshPopou(getActivity(), 1);

        view = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_layout_headline_list_fm, null, false);
        imageButton = (ImageButton) view.findViewById(R.id.img_reload);
        imageButton_backup = (ImageButton) view.findViewById(R.id.image_backup);
        mListView = (ListView) view.findViewById(R.id.fragment_headline_list_lv);
        mRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.headline_swipelayout);
        mScrollView = (MyNestedScrollView) view.findViewById(R.id.headline_nestedsceollview);

        imageButton.setOnClickListener(this);
        mScrollView.setOnScrollIterface(this);
        mRefreshLayout.setOnRefreshListener(this);
        imageButton_backup.setOnClickListener(this);
        mRefreshLayout.setColorSchemeResources(R.color.color_red);

        if (po == 0) {
            mHeadView = LayoutInflater.from(getActivity()).inflate(R.layout.header_layout_deadline_banner, null, false);
            mBanner = (HBanner) mHeadView.findViewById(R.id.headline_banner);
            mBannerImg = (ImageView) mHeadView.findViewById(R.id.headline_banner_img);
            ViewGroup.LayoutParams lp = mBanner.getLayoutParams();
            lp.height = (int) (ScreenUtils.getScreenWidth(getActivity()) / 2.1);
            mBanner.setLayoutParams(lp);
            mListView.addHeaderView(mHeadView);

            mBanner.setOnBannerListener(new OnHBannerClickListener() {
                @Override
                public void OnBannerClick(int position) {
                    boolean isFree = mBeanList.get(position).getIs_free().equals("1");
                    if (NetUtils.isNetworkStateed(getActivity())) {
                        clickId = mListId.get(position);
                        if (isFree) {
                            Intent intent = new Intent(getActivity(), HeadLinesDetailActivity.class);
                            intent.putExtra("id", clickId);
                            startActivity(intent);
                        } else {
                            isPaidSubscription(clickId);
                        }
                    }
                }
            });
            loadCache(); //先读缓存中的数据
            get_Subscribe(1, "", "2", true);
        } else {
            get_CateList(1, cate_id, false);
        }

        //item的监听
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (NetUtils.isNetworkStateed(getActivity())) {
                    clickId = (po == 0 || po == -1)
                            ? (list_subcirble.get(position - 2).getId())
                            : (list_catelist.get(position - 1).getId());
                    isFree = (po == 0 || po == -1)
                            ? (list_subcirble.get(position - 2).getIs_free().equals("1"))
                            : (list_catelist.get(position - 1).getIs_free().equals("1"));
                    if (isFree) {
                        Intent intent = new Intent(getActivity(), HeadLinesDetailActivity.class);
                        intent.putExtra("id", clickId);
                        startActivity(intent);
                    } else {
                        isPaidSubscription(clickId);
                    }
                }
            }
        });
        mScrollView.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
            @Override
            public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                boolean isOverScreen = v.getScrollY() >= ScreenUtils.getScreenHeight(getActivity())
                        - StatusUtils.getStatusBarHeight(getActivity())
                        - AndroidUtil.dp2px(getActivity(), 150);
                imageButton_backup.setVisibility(isOverScreen
                        ? (View.VISIBLE)
                        : (View.GONE));
            }
        });
    }

    private void loadCache() {
        page = 1;
        mACache = ACache.get(getActivity());
        String data = mACache.getAsString("subcrible_cache");
        if (TextUtils.isNullOrEmpty(data)) {
            loadCacheSubcrible(data);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return view;
    }

    public void initBanner(List<SubcribleBean.BannerBean> list) {
        if (list.size() != 0) {
            mListId.clear();
            mListImg.clear();
            mListTag.clear();
            mListTitle.clear();
            for (int i = 0; i < list.size(); i++) {
                mListId.add(list.get(i).getId());
                mListImg.add(list.get(i).getImg());
                mListTag.add(list.get(i).getIs_free().equals("1") ? true : false);
                mListTitle.add(list.get(i).getTitle());
            }
            mBanner.setVisibility(View.VISIBLE);
            mBanner.setBannerStyle(HBannerConfig.CIRCLE_INDICATOR_TITLE_INSIDE);
            //设置图片加载器
            mBanner.setImageLoader(new GlideImageLoader());
            //设置图片集合
            mBanner.setImages(mListImg);
            //设置banner动画效果
            mBanner.setBannerAnimation(Transformer.Accordion);
            //设置标题集合（当banner样式有显示title时）
            mBanner.setBannerTitles(mListTitle);
            //设置自动轮播，默认为true
            mBanner.isAutoPlay(true);
            //设置轮播时间
            mBanner.setDelayTime(3000);
            //设置指示器位置（当banner模式中有指示器时）
            mBanner.setIndicatorGravity(HBannerConfig.RIGHT);
            //banner设置方法全部调用完毕时最后调用
            mBanner.start();
        }
    }

    //获取推荐
    public void get_Subscribe(int page, String keywords, String subscribe, boolean isShow) {
        this.page = page;
        this.keywords = keywords;
        Map<String, String> map = new HashMap<String, String>();
        map.put("page", page + "");
        map.put("keywords", keywords);
        map.put("subscribe", subscribe);
        BaseActivity.postAsyn1(getActivity(), API.BASEURL + API.GET_SUBSCRIBE, map, this, 4, isShow);
    }

    //获取其他
    public void get_CateList(int page, String cate_id, boolean isShow) {
        this.page = page;
        this.cate_id = cate_id;
        Map<String, String> map = new HashMap<String, String>();
        map.put("page", page + "");
        map.put("size", "10");
        map.put("cate_id", cate_id);
        BaseActivity.postAsyn1(getActivity(), API.BASEURL + API.GET_CATE_LIST, map, this, 5, isShow);
    }

    //检查权限
    public void isPaidSubscription(String cate_id) {
        Map<String, String> map = new HashMap<String, String>();
        map.put("id", cate_id);
        BaseActivity.postAsyn1(getActivity(), API.BASEURL + API.IS_PAID_SUBSCRIPTION, map, this, 6, false);
    }

    @Override
    public void callBack(Object object, int type) {
        try {
            Gson gson = new Gson();
            String err = new JSONObject(object.toString()).getString("err");
            if (type == 4 && err.equals("0")) {//推荐
                loadCacheSubcrible(object.toString());
                mACache.put("subcrible_cache", object.toString());//加入缓存
            }
            if (type == 5) {//其他
                if (err.equals("0")) {
                    CateListBean cateListBean = gson.fromJson(object.toString(), CateListBean.class);
                    if (page == 1) {
                        imageButton.setVisibility(View.GONE);
                        imageButton_backup.setVisibility(View.GONE);
                        cateListAdapter = new CateListAdapter(getActivity(), cateListBean.getInfo());
                        mListView.setAdapter(cateListAdapter);
                        mRefreshLayout.setRefreshing(false);
                        list_catelist.clear();
                        list_catelist.addAll(cateListBean.getInfo());
                        lastvisibleItemCount = cateListBean.getInfo().size();

                        //展示刷新后的popou
                        mMyinterface.callBack(cateListBean.getHot_search()
                                , cateListBean.getShow_msg());

                    } else {
                        mRefreshPopou.setCanShowPopou(false);
                        list_catelist.addAll(cateListBean.getInfo());
                        cateListAdapter.setList(list_catelist);
                        cateListAdapter.notifyDataSetChanged();
                    }
                } else if (err.equals("1") || "998".equals(err)) {
                    mRefreshPopou.setCanShowPopou(false);
                    mRefreshLayout.setRefreshing(false);
                    sharedUtils.setData(getActivity(), "token", "");
                    sharedUtils.setData(getActivity(), "userid", "");
                    sharedUtils.setBooloean(getActivity(), "logined", false);
                } else {
                    mRefreshPopou.setCanShowPopou(false);
                    if (page == 1) {
                        mRefreshLayout.setRefreshing(false);
                        imageButton_backup.setVisibility(View.GONE);
                        emptyView.mustCallInitWay(mListView);
                        emptyView.setMyManager(R.drawable.headline_icon_null);
                        emptyView.setNoMessageText(new JSONObject(object.toString()).getString("msg"));
                        mListView.setEmptyView(emptyView);
                    } else {
                        TextUtils.Toast(getActivity(), "没有更多数据了！");
                    }
                }
            }

            if (type == 6) {
                if (err.equals("0")) {
                    Intent intent = new Intent(getActivity(), HeadLinesDetailActivity.class);
                    intent.putExtra("id", clickId);
                    startActivity(intent);
                } else {
                    String content = new JSONObject(object.toString()).getString("msg");
                    CommonDialog commonDialog = new CommonDialog();
                    commonDialog.showDialog(getActivity(), content, (err.equals("2")) ? (1) : (3), this);
                }
            }
        } catch (Exception e) {
        }
    }

    private void loadCacheSubcrible(String data) {
        try {
            Gson gson = new Gson();
            String err = new JSONObject(data.toString()).getString("err");
            if (err.equals("0")) {
                SubcribleBean subcribleBean = gson.fromJson(data.toString(), SubcribleBean.class);
                if (page == 1) {
                    imageButton.setVisibility(View.GONE);
                    imageButton_backup.setVisibility(View.GONE);

                    mSubcribleAdapter = new SubcribleAdapter(getActivity(), subcribleBean.getData());
                    mListView.setAdapter(mSubcribleAdapter);
                    mRefreshLayout.setRefreshing(false);
                    list_subcirble.clear();
                    list_subcirble.addAll(subcribleBean.getData());
                    lastvisibleItemCount = subcribleBean.getData().size();

                    //展示刷新后的popou
                    mMyinterface.callBack(subcribleBean.getHot_search()
                            , subcribleBean.getShow_msg());

                    //显示banner
                    mBeanList = subcribleBean.getBanner();
                    initBanner(mBeanList);
                } else {
                    mRefreshPopou.setCanShowPopou(false);
                    list_subcirble.addAll(subcribleBean.getData());
                    mSubcribleAdapter.setList(list_subcirble);
                    mSubcribleAdapter.notifyDataSetChanged();
                }
            } else if (err.equals("1") || "998".equals(err)) {
                mRefreshLayout.setRefreshing(false);
                mRefreshPopou.setCanShowPopou(false);
                sharedUtils.setData(getActivity(), "token", "");
                sharedUtils.setData(getActivity(), "userid", "");
                sharedUtils.setBooloean(getActivity(), "logined", false);
            } else {
                mRefreshPopou.setCanShowPopou(false);
                if (page == 1) {
                    mRefreshLayout.setRefreshing(false);
                    imageButton_backup.setVisibility(View.GONE);
                    emptyView.mustCallInitWay(mListView);
                    emptyView.setMyManager(R.drawable.headline_icon_null);
                    emptyView.setNoMessageText(new JSONObject(data).getString("msg"));
                    mListView.setEmptyView(emptyView);
                } else {
                    TextUtils.Toast(getActivity(), "没有更多数据了！");
                }
            }
        } catch (Exception e) {
        }
    }

    @Override
    public void ok(int type) {
        Intent intent = new Intent(getActivity(), IntegralActivity.class);
        intent.putExtra("type", po + "");
        startActivity(intent);
    }

    @Override
    public void failCallBack(int type) {
        mRefreshLayout.setRefreshing(false);
        if (po != 0) {
            if (list_catelist.size() == 0) {
                imageButton.setVisibility(View.VISIBLE);
            }
        } else {
            if (list_subcirble.size() == 0) {
                imageButton.setVisibility(View.VISIBLE);
            }
        }
    }

    @Override
    public void onRefresh() {
        page = 1;
        mRefreshPopou.setCanShowPopou(true);
        if (po == 0) {
            get_Subscribe(page, "", "2", false);
        } else {
            get_CateList(page, cate_id, false);
        }
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.img_reload:
                page = 1;
                if (po == 0) {
                    get_Subscribe(1, "", "2", true);
                } else {
                    get_CateList(1, cate_id, true);
                }
                break;
            case R.id.image_backup:

                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        mScrollView.fullScroll(ScrollView.FOCUS_UP);
                    }
                });
                imageButton_backup.setVisibility(View.GONE);
                break;
        }
    }

    @Override
    public void loadMore() {
        page++;
        if (po == 0) {
            get_CateList(page, "999", false);
        } else {
            get_CateList(page, cate_id, false);
        }
    }

    interface Myinterface {
        void callBack(String hotSearch, String content);
    }

    public void setMyinterface(Myinterface mMyinterface) {
        this.mMyinterface = mMyinterface;
    }

    public class GlideImageLoader extends ImageLoader {
        @Override
        public void displayImage(Context context, Object path, ImageView imageView, int position) {
            try {
                TagImageView tagImageView = (TagImageView) imageView;
                tagImageView.setCenterImgShow(mListTag.get(position), R.drawable.icon_free);
                imageView.setScaleType(ImageView.ScaleType.FIT_XY);
                Glide.with(context).load(path).into(imageView);
            } catch (Exception e) {
            }
        }

        @Override
        public ImageView createImageView(Context context) {
            return new TagImageView(context);

        }
    }

    @Override
    public void onStart() {
        super.onStart();
        //开始轮播
        if (mBanner != null) {
            mBanner.startAutoPlay();
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        //结束轮播
        if (mBanner != null) {
            mBanner.stopAutoPlay();
        }
        mHandler.removeCallbacksAndMessages(null);
    }
}
