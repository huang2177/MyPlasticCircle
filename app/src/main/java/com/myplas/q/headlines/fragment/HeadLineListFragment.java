package com.myplas.q.headlines.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.myplas.q.R;
import com.myplas.q.common.api.API;
import com.myplas.q.common.netresquset.ResultCallBack;
import com.myplas.q.common.view.CommonDialog;
import com.myplas.q.common.utils.NetUtils;
import com.myplas.q.common.utils.ScreenUtils;
import com.myplas.q.common.utils.SharedUtils;
import com.myplas.q.common.utils.TextUtils;
import com.myplas.q.common.view.XListView;
import com.myplas.q.guide.activity.BaseActivity;
import com.myplas.q.headlines.activity.HeadLinesDetailActivity;
import com.myplas.q.headlines.adapter.CateListAdapter;
import com.myplas.q.headlines.adapter.TTAdapter;
import com.myplas.q.headlines.bean.CateListBean;
import com.myplas.q.headlines.bean.SubcribleBean;
import com.myplas.q.myinfo.integral.activity.IntegralActivity;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.listener.OnBannerListener;
import com.youth.banner.loader.ImageLoader;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HeadLineListFragment extends Fragment implements ResultCallBack, XListView.IXListViewListener, View.OnClickListener, CommonDialog.DialogShowInterface {
    public String keywords1;
    private SharedUtils sharedUtils;
    public boolean isRefresh, isFree;
    public String cate_id, keywords, clickId;
    public int page, po, visibleItemCount, lastvisibleItemCount;

    private Banner mBanner;
    private View view, mHeadView;
    private TTAdapter ttAdapter;
    private XListView mXListView;
    private LinearLayout mLayoutNoData;
    private CateListAdapter cateListAdapter;
    private ImageButton imageButton, imageButton_backup;

    private List<CateListBean.InfoBean> list_catelist;
    private List<SubcribleBean.DataBean> list_subcirble;

    private List<String> mListId;
    private List<String> mListImg;
    public Myinterface mMyinterface;
    private List<String> mListTitle;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        page = 1;
        isRefresh = false;
        mListId = new ArrayList<>();
        mListImg = new ArrayList<>();
        mListTitle = new ArrayList<>();
        list_catelist = new ArrayList<>();
        list_subcirble = new ArrayList<>();
        sharedUtils = SharedUtils.getSharedUtils();

        view = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_layout_headline_list_fm, null, false);
        imageButton = (ImageButton) view.findViewById(R.id.img_reload);
        mLayoutNoData = (LinearLayout) view.findViewById(R.id.layout_noresult);
        imageButton_backup = (ImageButton) view.findViewById(R.id.image_backup);
        mXListView = (XListView) view.findViewById(R.id.fragment_headline_list_lv);

        mXListView.setPullRefreshEnable(true);
        mXListView.setXListViewListener(this);
        imageButton.setOnClickListener(this);
        mXListView.setDividerViewVisibility(false);
        imageButton_backup.setOnClickListener(this);

        if (po == 0) {
            mHeadView = LayoutInflater.from(getActivity()).inflate(R.layout.header_layout_deadline_banner, null, false);
            mBanner = (Banner) mHeadView.findViewById(R.id.headline_banner);
            ViewGroup.LayoutParams lp = mBanner.getLayoutParams();
            lp.height = (int) (ScreenUtils.getScreenWidth(getActivity()) / 2.1);
            mBanner.setLayoutParams(lp);
            mXListView.addHeaderView(mHeadView);

            mBanner.setOnBannerListener(new OnBannerListener() {
                @Override
                public void OnBannerClick(int position) {
                    if (NetUtils.isNetworkStateed(getActivity())) {
                        clickId = mListId.get(position);
                        //isFree=list_subcirble.
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

            get_Subscribe(1, "", "2", true);
        } else {
            get_CateList(1, cate_id, false);
        }

        //item的监听
        mXListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
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
        //加载更多。。。。
        mXListView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                if (scrollState == AbsListView.OnScrollListener.SCROLL_STATE_IDLE && mXListView.getCount() > visibleItemCount) {
                    InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
                    if (view.getLastVisiblePosition() == view.getCount() - 1) {
                        page++;
                        if (po == 0) {
                            get_CateList(page, "999", false);
                        } else {
                            get_CateList(page, cate_id, false);
                        }
                    }
                }
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                HeadLineListFragment.this.visibleItemCount = visibleItemCount;
                switch (po) {
                    case -1:
                        if (list_subcirble != null && list_subcirble.size() != 0) {
                            imageButton_backup.setVisibility((view.getLastVisiblePosition() > lastvisibleItemCount)
                                    ? (View.VISIBLE)
                                    : (View.GONE));
                        }
                        break;
                    default:
                        if (list_catelist != null && list_catelist.size() != 0) {
                            imageButton_backup.setVisibility((view.getLastVisiblePosition() > lastvisibleItemCount)
                                    ? (View.VISIBLE)
                                    : (View.GONE));
                        }
                        break;
                }

            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return view;
    }

    public void initBanner(List<SubcribleBean.BannerBean> list) {
        if (list.size() != 0) {
            mListId.clear();
            mListImg.clear();
            mListTitle.clear();
            for (int i = 0; i < list.size(); i++) {
                mListId.add(list.get(i).getId());
                mListImg.add(list.get(i).getImg());
                mListTitle.add(list.get(i).getTitle());
            }
            mBanner.setVisibility(View.VISIBLE);
            mBanner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE_INSIDE);
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
            mBanner.setIndicatorGravity(BannerConfig.RIGHT);
            //banner设置方法全部调用完毕时最后调用
            mBanner.start();
        } else {
            mBanner.setVisibility(View.GONE);
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
        BaseActivity.postAsyn(getActivity(), API.BASEURL + API.IS_PAID_SUBSCRIPTION, map, this, 6);
    }

    @Override
    public void callBack(Object object, int type) {
        try {
            Gson gson = new Gson();
            String err = new JSONObject(object.toString()).getString("err");
            if (type == 4) {//推荐
                if (err.equals("0")) {
                    SubcribleBean subcribleBean = gson.fromJson(object.toString(), SubcribleBean.class);
                    if (page == 1) {
                        imageButton.setVisibility(View.GONE);
                        mXListView.setVisibility(View.VISIBLE);
                        mLayoutNoData.setVisibility(View.GONE);
                        imageButton_backup.setVisibility(View.GONE);

                        ttAdapter = new TTAdapter(getActivity(), subcribleBean.getData());
                        mXListView.setAdapter(ttAdapter);
                        mXListView.stopRefresh();
                        list_subcirble.clear();
                        list_subcirble.addAll(subcribleBean.getData());
                        lastvisibleItemCount = subcribleBean.getData().size();

                        //展示刷新后的popou
                        mMyinterface.callBack(subcribleBean.getHot_search()
                                , subcribleBean.getShow_msg()
                                , isRefresh);

                        //显示banner
                        initBanner(subcribleBean.getBanner());
                    } else {
                        isRefresh = false;
                        list_subcirble.addAll(subcribleBean.getData());
                        ttAdapter.setList(list_subcirble);
                        ttAdapter.notifyDataSetChanged();
                    }
                } else if (err.equals("1") || "998".equals(err)) {
                    isRefresh = false;
                    mXListView.stopRefresh();
                    sharedUtils.setData(getActivity(), "token", "");
                    sharedUtils.setData(getActivity(), "userid", "");
                    sharedUtils.setBooloean(getActivity(), "logined", false);

                } else {
                    isRefresh = false;
                    if (page == 1) {
                        mXListView.stopRefresh();
                        mXListView.setVisibility(View.GONE);
                        mLayoutNoData.setVisibility(View.VISIBLE);
                        imageButton_backup.setVisibility(View.GONE);
                    } else {
                        TextUtils.Toast(getActivity(), "没有更多数据了！");
                    }
                }
            }
            if (type == 5) {//其他
                if (err.equals("0")) {
                    CateListBean cateListBean = gson.fromJson(object.toString(), CateListBean.class);
                    if (page == 1) {
                        imageButton.setVisibility(View.GONE);
                        mXListView.setVisibility(View.VISIBLE);
                        mLayoutNoData.setVisibility(View.GONE);
                        imageButton_backup.setVisibility(View.GONE);
                        cateListAdapter = new CateListAdapter(getActivity(), cateListBean.getInfo());
                        mXListView.setAdapter(cateListAdapter);
                        mXListView.stopRefresh();
                        list_catelist.clear();
                        list_catelist.addAll(cateListBean.getInfo());
                        lastvisibleItemCount = cateListBean.getInfo().size();

                        //展示刷新后的popou
                        mMyinterface.callBack(cateListBean.getHot_search()
                                , cateListBean.getShow_msg()
                                , isRefresh);

                    } else {
                        isRefresh = false;
                        list_catelist.addAll(cateListBean.getInfo());
                        cateListAdapter.setList(list_catelist);
                        cateListAdapter.notifyDataSetChanged();
                    }
                } else if (err.equals("1") || "998".equals(err)) {
                    isRefresh = false;
                    mXListView.stopRefresh();
                    sharedUtils.setData(getActivity(), "token", "");
                    sharedUtils.setData(getActivity(), "userid", "");
                    sharedUtils.setBooloean(getActivity(), "logined", false);
                    sharedUtils.setData(getActivity(), "toast_msg", new JSONObject(object.toString()).getString("msg"));
                } else {
                    isRefresh = false;
                    if (page == 1) {
                        mXListView.stopRefresh();
                        mXListView.setVisibility(View.GONE);
                        mLayoutNoData.setVisibility(View.VISIBLE);
                        imageButton_backup.setVisibility(View.GONE);
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

    @Override
    public void ok(int type) {
        Intent intent = new Intent(getActivity(), IntegralActivity.class);
        intent.putExtra("type", po + "");
        startActivity(intent);
    }

    @Override
    public void failCallBack(int type) {
        mXListView.stopRefresh();
        if (list_subcirble != null && list_subcirble.size() == 0) {
            mXListView.setVisibility(View.GONE);
            mLayoutNoData.setVisibility(View.GONE);
            imageButton.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onRefresh() {
        page = 1;
        isRefresh = true;
        if (po == 0) {
            get_Subscribe(page, "", "2", false);
        } else {
            get_CateList(page, cate_id, false);
        }
    }

    @Override
    public void onLoadMore() {

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
                mXListView.setSelection(0);
                imageButton_backup.setVisibility(View.GONE);
                break;
        }
    }

    interface Myinterface {
        void callBack(String hotSearch, String content, boolean isRefresh);
    }

    public void setMyinterface(Myinterface mMyinterface) {
        this.mMyinterface = mMyinterface;
    }

    public class GlideImageLoader extends ImageLoader {
        @Override
        public void displayImage(Context context, Object path, ImageView imageView) {
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            Glide.with(context).load(path).into(imageView);
        }

        @Override
        public ImageView createImageView(Context context) {
            ImageView simpleDraweeView = new ImageView(context);
            return simpleDraweeView;
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
}
