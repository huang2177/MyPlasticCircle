package com.myplas.q.homepage.activity;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.huangbryant.hbanner.HBanner;
import com.huangbryant.hbanner.HBannerConfig;
import com.huangbryant.hbanner.Transformer;
import com.huangbryant.hbanner.listener.OnHBannerClickListener;
import com.huangbryant.hbanner.loader.ImageLoader;
import com.huangbryant.hbanner.loader.ImageLoaderInterface;
import com.myplas.q.R;
import com.myplas.q.app.activity.BaseActivity;
import com.myplas.q.common.api.API;
import com.myplas.q.common.listener.MyOnItemClickListener;
import com.myplas.q.common.listener.OnKeyboardChangeListener;
import com.myplas.q.common.netresquset.ResultCallBack;
import com.myplas.q.common.utils.TextUtils;
import com.myplas.q.headlines.HeadLineListFragment;
import com.myplas.q.headlines.bean.SubcribleBean;
import com.myplas.q.homepage.adapter.BlackListDetailAdapter;
import com.myplas.q.homepage.beans.BlackListsDetailBean;
import com.yanzhenjie.album.Album;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 黄双
 * @date 2018/1/17 0017
 */

public class BlackListDetailActivity extends BaseActivity implements View.OnClickListener
        , MyOnItemClickListener
        , OnKeyboardChangeListener.OnChangeListener
        , ResultCallBack
        , OnHBannerClickListener {

    private int color;
    private String id, content, commentId;

    private Button button;
    private HBanner mBanner;
    private EditText editText;
    private RecyclerView mRecycleView;
    private NestedScrollView scrollView;
    private LinearLayout layoutComments, layoutInput;
    private TextView tvTitle, tvAuthor, tvTime, tvPV, tvContent;

    private BlackListDetailAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blacklistdetail_layout);
        initTileBar();
        setTitle("详细信息");

        initView();
        getBlackLists();
    }

    private void initView() {
        commentId = "0";
        id = getIntent().getStringExtra("id");
        color = ContextCompat.getColor(this, R.color.color_red);

        tvPV = F(R.id.detail_pv);
        tvTime = F(R.id.detail_time);
        mBanner = F(R.id.detail_banner);
        tvTitle = F(R.id.detail_title);
        scrollView = F(R.id.detail_sv);
        mRecycleView = F(R.id.detail_rv);
        tvAuthor = F(R.id.detail_author);
        tvContent = F(R.id.detail_content);
        layoutComments = F(R.id.layout_comments);
        layoutInput = F(R.id.detail_layout_input);

        button = F(R.id.blacklist_detail_btn);
        editText = F(R.id.blacklist_detail_ev);

        editText.setHint("写留言");
        button.setOnClickListener(this);
        mBanner.setOnBannerListener(this);
        layoutInput.addOnLayoutChangeListener(new OnKeyboardChangeListener(this, this));

        LinearLayoutManager manager = new LinearLayoutManager(this);
        mRecycleView.setNestedScrollingEnabled(false);
        mRecycleView.setLayoutManager(manager);

    }

    /**
     * 初始化banner
     *
     * @param illustration
     */
    public void initBanner(List<String> illustration) {
        if (illustration.size() == 0) {
            return;
        }
        mBanner.setVisibility(View.VISIBLE);
        mBanner.setBannerStyle(HBannerConfig.CIRCLE_INDICATOR);
        //设置图片加载器
        mBanner.setImageLoader(new ImageLoader() {
            @Override
            public void displayImage(Context context, Object o, ImageView imageView, int i) {
                Glide.with(context).load(o.toString()).into(imageView);
            }
        });
        //设置图片集合
        mBanner.setImages(illustration);
        //设置banner动画效果
        mBanner.setBannerAnimation(Transformer.Accordion);
        //设置标题集合（当banner样式有显示title时）
        //mBanner.setBannerTitles(list1);
        //设置自动轮播，默认为true
        mBanner.isAutoPlay(false);
        //设置轮播时间
        mBanner.setDelayTime(3000);
        //设置指示器位置（当banner模式中有指示器时）
        mBanner.setIndicatorGravity(HBannerConfig.RIGHT);
        //banner设置方法全部调用完毕时最后调用
        mBanner.start();
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.blacklist_detail_btn:
                reply();
                break;
//            case R.id.blacklist_detail_btn:
//                break;
            default:
                break;
        }
    }

    @Override
    public void OnBannerClick(int position) {

    }

    @Override
    public void onItemClick(String sign, String name, String id, String pur_id, String user_id) {
        commentId = sign;
        editText.setHint("回复:" + name);
        showInPutKeybord(editText);
    }

    @Override
    public void onKeyboardHidden() {
        commentId = "0";
        editText.setHint("写留言");
    }

    /**
     * 获取黑名单详情数据
     */
    public void getBlackLists() {
        Map<String, String> map = new HashMap<>(16);
        map.put("id", id);
        getAsyn(this, API.BLACKLISTS + "/" + id, null, this, 1, false);
    }

    /**
     * 回复
     */
    public void reply() {
        content = editText.getText().toString();
        if (TextUtils.notEmpty(content)) {
            Map<String, String> map = new HashMap<>(16);
            map.put("blacklist_id", id);
            map.put("comments", content);
            map.put("comment_id", commentId);
            postAsyn(this, API.BLACKLISTSCOMMENTS, map, this, 2, false);
        } else {
            TextUtils.toast(this, "请输入回复内容！");
        }
    }

    @Override
    public void callBack(Object object, int type) {
        try {
            Gson gson = new Gson();
            JSONObject jsonObject = new JSONObject(object.toString());
            if (type == 1 && "0".equals(jsonObject.getString("code"))) {
                BlackListsDetailBean bean = gson.fromJson(object.toString(), BlackListsDetailBean.class);
                showDetail(bean.getBlacklist());
            }
            if (type == 2 && "0".equals(jsonObject.getString("code"))) {
                getBlackLists();
                editText.setText("");
                editText.setHint("写留言");
                TextUtils.toast(this, jsonObject.getString("message"));
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(editText.getWindowToken(), 0);
            }
        } catch (Exception e) {
        }
    }

    @Override
    public void failCallBack(int type, String message, int httpCode) {
        try {
            JSONObject jsonObject = new JSONObject(message);
            TextUtils.toast(this, jsonObject.getString("message"));
        } catch (Exception e) {

        }
    }

    /**
     * 显示数据
     *
     * @param bean
     */
    private void showDetail(BlackListsDetailBean.BlacklistBean bean) {
        if (mBanner.getVisibility() == View.GONE) {
            initBanner(bean.getIllustration());

            tvPV.setText(bean.getPv());
            tvTitle.setText(bean.getSubject());
            tvContent.setText(bean.getContent());
            tvTime.setText(bean.getCreated_at());
            //tvAuthor.setText(bean.getCreated_at());

            adapter = new BlackListDetailAdapter(this, this);
            adapter.setList(bean.getComments());
            mRecycleView.setAdapter(adapter);

        } else {
            adapter.setList(bean.getComments());
            adapter.notifyDataSetChanged();
        }

        layoutComments.setVisibility(bean.getComments().size() == 0
                ? View.GONE
                : View.VISIBLE);
    }

    @Override
    public void onResume() {
        super.onResume();
        mBanner.startAutoPlay();
    }

    @Override
    public void onPause() {
        super.onPause();
        mBanner.stopAutoPlay();
    }
}
