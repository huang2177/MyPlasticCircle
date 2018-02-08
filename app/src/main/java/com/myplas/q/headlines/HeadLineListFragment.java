package com.myplas.q.headlines;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.widget.NestedScrollView;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.MotionEvent;
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
import com.myplas.q.app.fragment.BaseFragment;
import com.myplas.q.common.api.API;
import com.myplas.q.common.net.ResultCallBack;
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
import com.myplas.q.headlines.activity.HeadLinesDetailActivity;
import com.myplas.q.headlines.adapter.SubcribleAdapter;
import com.myplas.q.headlines.bean.SubcribleBean;
import com.myplas.q.myself.integral.activity.IntegralActivity;
import com.myplas.q.myself.integral.activity.IntegralPayActivtity;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HeadLineListFragment extends BaseFragment implements ResultCallBack
        , View.OnClickListener
        , CommonDialog.DialogShowInterface
        , MyNestedScrollView.onScrollIterface
        , SwipeRefreshLayout.OnRefreshListener {
    public boolean isFree;
    public String keywords1;
    public int page, currentItem;
    private SharedUtils sharedUtils;
    public String cateId, keywords, clickId;

    private HBanner mBanner;
    private ListView mListView;
    private EmptyView emptyView;
    private View view, mHeadView;
    private ImageView mBannerImg;
    public RefreshPopou mRefreshPopou;
    private MyNestedScrollView mScrollView;
    private SwipeRefreshLayout mRefreshLayout;
    private SubcribleAdapter mSubcribleAdapter;
    private ImageButton imageButton, imagebuttonBackup;

    private List<SubcribleBean.NewsBean> mCateList;
    private List<SubcribleBean.BannerBean> mBeanList;

    private List<String> mListId;
    private List<String> mListImg;
    private List<Boolean> mListTag;
    private List<String> mListTitle;
    public Myinterface mMyInterface;

    private String code;
    private ACache mACache;
    private Handler mHandler;

    public static HeadLineListFragment newInstance(String cateId, int position) {
        HeadLineListFragment fragment = new HeadLineListFragment();
        Bundle bundle = new Bundle();
        bundle.putString("cateId", cateId);
        bundle.putInt("position", position);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        intiView();
    }

    private void intiView() {
        page = 1;
        mHandler = new Handler();
        mListId = new ArrayList<>();
        mListImg = new ArrayList<>();
        mListTag = new ArrayList<>();
        mListTitle = new ArrayList<>();
        mCateList = new ArrayList<>();
        mACache = ACache.get(getActivity());
        emptyView = new EmptyView(getActivity());
        sharedUtils = SharedUtils.getSharedUtils();
        mRefreshPopou = new RefreshPopou(getActivity(), 1);

        currentItem = getArguments().getInt("position");
        cateId = getArguments().getString("cateId");

        view = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_layout_headline_list_fm, null, false);
        imageButton = (ImageButton) view.findViewById(R.id.img_reload);
        imagebuttonBackup = (ImageButton) view.findViewById(R.id.image_backup);
        mListView = (ListView) view.findViewById(R.id.fragment_headline_list_lv);
        mRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.headline_swipelayout);
        mScrollView = (MyNestedScrollView) view.findViewById(R.id.headline_nestedsceollview);

        imageButton.setOnClickListener(this);
        mScrollView.setOnScrollIterface(this);
        mRefreshLayout.setOnRefreshListener(this);
        imagebuttonBackup.setOnClickListener(this);
        mRefreshLayout.setColorSchemeResources(R.color.color_red);

        if (currentItem == 0) {
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
                    boolean isFree = "1".equals(mBeanList.get(position).getIs_free());
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
        }
        getCatelist(1, 4, false);

        //item的监听
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (NetUtils.isNetworkStateed(getActivity())) {
                    int index = currentItem == 0 ? position - 1 : position;

                    clickId = mCateList.get(index).getId();
                    cateId = mCateList.get(index).getCate_id();
                    isFree = "1".equals(mCateList.get(index).getIs_free());

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
                imagebuttonBackup.setVisibility(isOverScreen
                        ? (View.VISIBLE)
                        : (View.GONE));
            }
        });
    }

    private void loadCache() {
        page = 1;
        mACache = ACache.get(getActivity());
        String data = mACache.getAsString("subcrible_cache");
        if (TextUtils.notEmpty(data)) {
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


    /**
     * 获取其他
     */
    public void getCatelist(int page, int type, boolean isShow) {
        Map<String, String> map = new HashMap<String, String>(16);
        map.put("page", page + "");
        map.put("size", "10");
        map.put("category", cateId);
        getAsyn(getActivity(), API.GET_CATE_LIST, map, this, type, isShow);
    }

    /**
     * 检查权限
     */
    public void isPaidSubscription(String cate_id) {
        Map<String, String> map = new HashMap<String, String>(8);
        map.put("id", cate_id);
        getAsyn(getActivity(), API.IS_PAID_SUBSCRIPTION, map, this, 6, false);
    }


    /**
     * 获取详情 检查塑豆
     *
     * @param id
     * @param type
     */
    public void getNetData(String id) {
        Map<String, String> map = new HashMap<String, String>(8);
        map.put("id", id);
        map.put("ispass", code);
        getAsyn(getActivity(), API.GET_DETAIL_INFO, map, this, 7);
    }

    @Override
    public void callBack(Object object, int type) {
        try {
            Gson gson = new Gson();
            String err = new JSONObject(object.toString()).getString("code");
            if (type == 4) {//推荐
                loadCacheSubcrible(object.toString());
                mACache.put("subcrible_cache", object.toString());//加入缓存
            }

            if (type == 6) {
                code = err;
                if ("0".equals(err)) {
                    Intent intent = new Intent(getActivity(), HeadLinesDetailActivity.class);
                    intent.putExtra("id", clickId);
                    startActivity(intent);
                } else {
                    String content = new JSONObject(object.toString()).getString("message");
                    CommonDialog commonDialog = new CommonDialog();
                    commonDialog.showDialog(getActivity(), content, ("2".equals(err)) ? (1) : (3), this);
                }
            }
            if (type == 7) {
                if ("0".equals(err)) {
                    Intent intent = new Intent(getActivity(), HeadLinesDetailActivity.class);
                    intent.putExtra("id", clickId);
                    startActivity(intent);
                }
            }
        } catch (Exception e) {

        }
    }

    private void loadCacheSubcrible(String data) {
        try {
            Gson gson = new Gson();
            String err = new JSONObject(data.toString()).getString("code");
            if ("0".equals(err)) {
                setListener(false);
                SubcribleBean subcribleBean = gson.fromJson(data.toString(), SubcribleBean.class);
                if (page == 1) {
                    imageButton.setVisibility(View.GONE);
                    imagebuttonBackup.setVisibility(View.GONE);

                    mSubcribleAdapter = new SubcribleAdapter(getActivity(), subcribleBean.getNews());
                    mListView.setAdapter(mSubcribleAdapter);
                    mRefreshLayout.setRefreshing(false);
                    mCateList.clear();
                    mCateList.addAll(subcribleBean.getNews());

                    //展示刷新后的popou
                    mMyInterface.callBack(subcribleBean.getTotal_found()
                            , subcribleBean.getTotal_found());

                    //显示banner
                    mBeanList = subcribleBean.getBanners();
                    initBanner(mBeanList);
                } else {
                    mRefreshPopou.setCanShowPopou(false);
                    mCateList.addAll(subcribleBean.getNews());
                    mSubcribleAdapter.setList(mCateList);
                    mSubcribleAdapter.notifyDataSetChanged();
                }
            } else {
                setListener(true);
                mRefreshPopou.setCanShowPopou(false);
                if (page == 1) {
                    mRefreshLayout.setRefreshing(false);
                    imagebuttonBackup.setVisibility(View.GONE);
                    emptyView.mustCallInitWay(mListView);
                    emptyView.setMyManager(R.drawable.headline_icon_null);
                    emptyView.setNoMessageText(new JSONObject(data).getString("message"));
                    mListView.setEmptyView(emptyView);
                } else {
                    TextUtils.toast(getActivity(), "没有更多数据了！");
                }
            }
        } catch (Exception e) {

        }
    }

    @Override
    public void failCallBack(int type, String message, int httpCode) {
        if (mRefreshLayout.isRefreshing()) {
            mRefreshLayout.setRefreshing(false);
        }
        if (currentItem != 0) {
            if (mCateList.size() == 0) {
                imageButton.setVisibility(View.VISIBLE);
            }
        }

        if (type == 7 && httpCode == 412) {
            try {
                JSONObject jsonObject = new JSONObject(message);
                String code = jsonObject.getString("code");
                if ("100".equals(code)) {
                    String content = jsonObject.getString("message");
                    CommonDialog commonDialog = new CommonDialog();
                    commonDialog.showDialog(getActivity(), content, 10, this);
                }
            } catch (JSONException e) {

            }
        }
    }

    @Override
    public void dialogClick(int type) {
        if (type == 1) {
            getNetData(clickId);
        }
        if (type == 10) {
            Intent intent = new Intent(getActivity(), IntegralPayActivtity.class);
            startActivity(intent);
        }
    }

    @Override
    public void onRefresh() {
        page = 1;
        mRefreshPopou.setCanShowPopou(true);
        getCatelist(page, 4, false);
    }


    @Override
    public void loadMore() {
        page++;
        getCatelist(page, 4, false);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.img_reload:
                page = 1;
                getCatelist(1, 4, true);
                break;
            case R.id.image_backup:

                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        mScrollView.fullScroll(ScrollView.FOCUS_UP);
                    }
                });
                imagebuttonBackup.setVisibility(View.GONE);
                break;
            default:
                break;
        }
    }

    private void setListener(final boolean scrollabled) {
        mScrollView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return scrollabled;
            }
        });
    }

    interface Myinterface {
        void callBack(String hotSearch, String content);
    }

    public void setMyinterface(Myinterface mMyInterface) {
        this.mMyInterface = mMyInterface;
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
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mHandler.removeCallbacksAndMessages(null);
    }
}
