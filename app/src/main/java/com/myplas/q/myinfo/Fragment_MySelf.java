package com.myplas.q.myinfo;

import android.annotation.TargetApi;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.app.Fragment;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.animation.AlphaAnimation;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.google.gson.Gson;
import com.myplas.q.appupdate.DownLoadUtils;
import com.myplas.q.appupdate.DownloadApk;
import com.myplas.q.common.utils.ImgUtils;
import com.myplas.q.common.utils.NetUtils;
import com.myplas.q.common.view.DragView;
import com.myplas.q.guide.activity.ShareActivity;
import com.myplas.q.common.utils.GetNumUtil;
import com.myplas.q.R;
import com.myplas.q.myinfo.credit.activity.LineOfCreditActivity;
import com.myplas.q.myinfo.credit.activity.PlasticMoneyActivity;
import com.myplas.q.myinfo.fans.activity.LookMeActivity;
import com.myplas.q.myinfo.fans.activity.MyFansFollowActivity;
import com.myplas.q.guide.activity.BaseActivity;
import com.myplas.q.common.netresquset.ResultCallBack;
import com.myplas.q.common.utils.SharedUtils;
import com.myplas.q.common.utils.TextUtils;
import com.myplas.q.common.utils.VersionUtils;
import com.myplas.q.common.view.MyImageView;
import com.myplas.q.myinfo.integral.activity.IntegralActivity;

import com.myplas.q.myinfo.fans.activity.MyIntroductionActivity;

import com.myplas.q.common.api.API;

import com.myplas.q.myinfo.invoices.activity.TradeOrderActivity;
import com.myplas.q.myinfo.beans.MyZone;
import com.myplas.q.myinfo.message.activity.MessageListsActivity;
import com.myplas.q.myinfo.setting.activity.MyDataActivity;
import com.myplas.q.myinfo.setting.SettingActivity;
import com.myplas.q.myinfo.supdem.activity.MySupDemActivity;
import com.myplas.q.myinfo.websockethelper.WebSocketCallBack;
import com.myplas.q.myinfo.websockethelper.WebSocketHelper;
import com.tencent.mm.sdk.platformtools.Util;
import com.umeng.analytics.MobclickAgent;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import rx.Observer;

/**
 * 编写： 黄双
 * 电话：15378412400
 * 邮箱：15378412400@163.com
 * 时间：2017/3/17 14:45
 */
public class Fragment_MySelf extends Fragment implements View.OnClickListener, ResultCallBack, WebSocketCallBack {

    private View view;
    private MyZone myZone;

    private ImageView image_rz;
    private DragView mDragView;
    private MyImageView image_tx;
    private ImageButton imageButton;
    private NestedScrollView scrollingView;
    private ImageView mImageView_news, mImage_more;
    private SharedUtils sharedUtils = SharedUtils.getSharedUtils();
    private TextView text_title, text_dd, text_gj, text_qg, text_yj, text_fs, text_gz, text_look, text_name, text_gs, text_pm;
    private LinearLayout linear_title, linear_dd, linear_qg, linear_gj, linear_yj, linear_fs, linear_gz, linear_jf, linear_look, linear_edu, linear_pz, linear_set;

    private Toolbar mToolbar;
    private AppBarLayout mBarLayout;
    private FrameLayout mFrameLayout;
    private CollapsingToolbarLayoutState state;
    private CoordinatorLayout mCoordinatorLayout;
    private CollapsingToolbarLayout mCollapsingToolbarLayout;

    private WebSocketHelper mSocketHelper;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initWebSocket();
        initView();
        setAppBarListener();
    }

    private void initWebSocket() {
        mSocketHelper = new WebSocketHelper(this);
        mSocketHelper.startConnect(getActivity());
    }

    public void initView() {
        view = LayoutInflater.from(getActivity()).inflate(R.layout.layout_myselffragment, null, false);
        //获取登陆后相关数据
        image_tx = f(R.id.xq_tx);
        image_rz = f(R.id.xq_rz);
        text_gs = f(R.id.wd_title_gs);

        text_pm = f(R.id.wd_title_pm);
        text_dd = f(R.id.wd_text_dd);
        text_gj = f(R.id.wd_text_gj);
        text_qg = f(R.id.wd_text_qg);
        text_fs = f(R.id.wd_text_fans);
        text_name = f(R.id.wd_title_name);
        text_gz = f(R.id.wd_text_follow);
        text_look = f(R.id.wd_text_look);
        text_yj = f(R.id.wd_text_introdus);
        text_title = f(R.id.toolbar_title);

        linear_jf = f(R.id.wd_linear_jf);
        linear_dd = f(R.id.wd_linear_dd);
        linear_gj = f(R.id.wd_linear_gj);
        linear_qg = f(R.id.wd_linear_qg);
        linear_pz = f(R.id.wd_linear_pz);
        imageButton = f(R.id.img_reload);
        linear_fs = f(R.id.wd_linear_fans);
        linear_set = f(R.id.wd_linear_set);
        linear_edu = f(R.id.wd_linear_edu);
        mImage_more = f(R.id.wd_title_more);
        linear_gz = f(R.id.wd_linear_follow);
        linear_look = f(R.id.wd_linear_look);
        linear_title = f(R.id.wd_linear_title);
        linear_yj = f(R.id.wd_linear_introdus);
        mDragView = f(R.id.wd_logined_news_text);
        mFrameLayout = f(R.id.wd_logined_news_fl);
        scrollingView = f(R.id.scrollView_myself);
        mImageView_news = f(R.id.wd_logined_news_img);

        mToolbar = f(R.id.toolbar);
        mBarLayout = f(R.id.app_bar_layout);
        mCoordinatorLayout = f(R.id.coordinatorlayout);
        mCollapsingToolbarLayout = f(R.id.collapsing_toolbar);

        linear_dd.setOnClickListener(this);
        linear_gj.setOnClickListener(this);
        linear_qg.setOnClickListener(this);
        linear_yj.setOnClickListener(this);
        linear_fs.setOnClickListener(this);
        linear_gz.setOnClickListener(this);
        linear_jf.setOnClickListener(this);
        linear_pz.setOnClickListener(this);
        mDragView.setOnClickListener(this);
        linear_set.setOnClickListener(this);
        linear_edu.setOnClickListener(this);
        linear_look.setOnClickListener(this);
        imageButton.setOnClickListener(this);
        linear_title.setOnClickListener(this);
        mFrameLayout.setOnClickListener(this);

        image_tx.setBorderColor(getActivity(), R.color.color_white);
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
        if (NetUtils.isNetworkStateed(getActivity())) {
            switch (v.getId()) {
                case R.id.wd_logined_news_fl:
                    Intent i0 = new Intent(getActivity(), MessageListsActivity.class);
                    startActivity(i0);
                    break;
                case R.id.wd_linear_dd:
                    Intent i1 = new Intent(getActivity(), TradeOrderActivity.class);
                    startActivity(i1);
                    break;
                case R.id.img_reload:
                    getLoginInfo(true);
                    break;
                case R.id.wd_linear_share:
                    Intent in = new Intent(getActivity(), ShareActivity.class);
                    in.putExtra("type", "5");
                    startActivity(in);
                    break;
                case R.id.wd_linear_gj:
                    Intent intent = new Intent(getActivity(), MySupDemActivity.class);
                    intent.putExtra("title", "我的供给");
                    intent.putExtra("type", "2");
                    startActivity(intent);
                    break;
                case R.id.wd_linear_qg:
                    Intent intent1 = new Intent(getActivity(), MySupDemActivity.class);
                    intent1.putExtra("title", "我的求购");
                    intent1.putExtra("type", "1");
                    startActivity(intent1);
                    break;
                case R.id.wd_linear_introdus:
                    Intent intent2 = new Intent(getActivity(), MyIntroductionActivity.class);
                    startActivity(intent2);
                    break;
                case R.id.wd_linear_fans:
                    Intent intent3 = new Intent(getActivity(), MyFansFollowActivity.class);
                    intent3.putExtra("titlename", "我的粉丝");
                    intent3.putExtra("type", "1");
                    startActivity(intent3);
                    break;
                case R.id.wd_linear_follow:
                    Intent intent4 = new Intent(getActivity(), MyFansFollowActivity.class);
                    intent4.putExtra("titlename", "我的关注");
                    intent4.putExtra("type", "2");
                    startActivity(intent4);
                    break;
                case R.id.wd_linear_look:
                    Intent intent0 = new Intent(getActivity(), LookMeActivity.class);
                    startActivity(intent0);
                    break;
                case R.id.wd_linear_jf:
                    startActivity(new Intent(getActivity(), IntegralActivity.class));
                    break;
                case R.id.wd_linear_edu:
                    startActivity(new Intent(getActivity(), LineOfCreditActivity.class));
                    break;
                case R.id.wd_linear_pz:
                    startActivity(new Intent(getActivity(), PlasticMoneyActivity.class));
                    break;
                case R.id.wd_linear_title:
                    Intent intent6 = new Intent(getActivity(), MyDataActivity.class);
                    startActivity(intent6);
                    break;
                case R.id.wd_linear_set:
                    Intent intent7 = new Intent(getActivity(), SettingActivity.class);
                    startActivity(intent7);
                    break;
            }
        }
    }


    public void getLoginInfo(boolean isShow) {
        Map<String, String> map = new HashMap<String, String>();
        map.put("token", sharedUtils.getData(getActivity(), "token"));
        BaseActivity.postAsyn1(getActivity(), API.BASEURL + API.MY_ZONE, map, this, 2, isShow);
    }

    @Override
    public void callBack(Object object, int type) {
        try {
            scrollingView.setVisibility(View.VISIBLE);
            imageButton.setVisibility(View.GONE);
            JSONObject jsonObject = new JSONObject(object.toString());
            Gson gson = new Gson();
            if (type == 2) {
                if ("0".equals(jsonObject.getString("err"))) {
                    myZone = gson.fromJson(object.toString(), MyZone.class);
                    showInfo(myZone);
                } else if ("1".equals(jsonObject.getString("err")) || "998".equals(jsonObject.getString("err"))) {
                    sharedUtils.setData(getActivity(), "token", "");
                    sharedUtils.setData(getActivity(), "userid", "");
                    sharedUtils.setBooloean(getActivity(), "logined", false);
                }
            }
        } catch (Exception e) {
        }
    }

    @Override
    public void failCallBack(int type) {
        if (myZone == null) {
            scrollingView.setVisibility(View.GONE);
            imageButton.setVisibility(View.VISIBLE);
        }
    }

    public void showInfo(MyZone myZone) {
        try {
            String ispass = myZone.getData().getIs_pass();
            Glide.with(getActivity()).load(myZone.getData().getThumb()).placeholder(R.drawable.contact_image_defaul_male).into(image_tx);
            image_rz.setImageResource((ispass.equals("0")) ? (R.drawable.icon_identity) : (R.drawable.icon_identity_hl));
            text_name.setText(myZone.getData().getName() + "  " +
                    myZone.getData().getMobile() + "  " +
                    ((!myZone.getData().getSex().equals("0")) ? ("女") : ("男")));
            text_gs.setText(myZone.getData().getC_name());
            text_pm.setText("等级：" + myZone.getData().getMemberlevel() + "  排名：" + myZone.getData().getRank() + "位");

            text_yj.setText(myZone.getIntroduction());
            text_fs.setText(myZone.getMyfans());
            text_gz.setText(myZone.getMyconcerns());

            text_gj.setText(myZone.getS_out_count() + "  ");
            text_qg.setText(myZone.getS_in_count() + "  ");
            text_look.setText(myZone.getMyviewhistory() + "  ");

        } catch (Exception e) {
        }
    }

    public void onResume() {
        super.onResume();
        getLoginInfo(false);
        MobclickAgent.onPageStart("MainScreen"); //统计页面，"MainScreen"为页面名称，可自定义
    }

    public void onPause() {
        super.onPause();
        MobclickAgent.onPageEnd("MainScreen");
    }

    private void setAppBarListener() {
        mBarLayout.addOnOffsetChangedListener(
                new AppBarLayout.OnOffsetChangedListener() {
                    @Override
                    public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                        if (verticalOffset == 0) {//张开
                            setToolbar1Alpha(255);
                        } else if (Math.abs(verticalOffset) >= appBarLayout.getTotalScrollRange()) {//收缩
                            setToolbar2Alpha(255);
                        } else {
                            int alpha = 255 - Math.abs(verticalOffset) - 150;
                            if (alpha <= 0) {//收缩
                                setToolbar2Alpha(Math.abs(verticalOffset));
                            } else {//张开
                                setToolbar1Alpha(alpha);
                            }
                        }
                    }
                });
    }

    //设置展开时各控件的透明度
    public void setToolbar1Alpha(int alpha) {
        text_title.setTextColor(Color.TRANSPARENT);

        image_rz.getDrawable().setAlpha(alpha);
        mImage_more.getDrawable().setAlpha(alpha);
        mImageView_news.getDrawable().setAlpha(alpha);
        image_tx.getDrawable().mutate().setAlpha(alpha);
        text_pm.setTextColor(Color.argb(alpha, 255, 255, 255));
        text_gs.setTextColor(Color.argb(alpha, 255, 255, 255));
        mDragView.setTextColor(Color.argb(alpha, 255, 255, 255));
        text_name.setTextColor(Color.argb(alpha, 255, 255, 255));
        image_tx.setBorderColor(Color.argb(alpha, 255, 255, 255));
    }

    //设置闭合时各控件的透明度
    public void setToolbar2Alpha(int alpha) {
        text_title.setTextColor(Color.argb(alpha, 255, 255, 255));
        mCollapsingToolbarLayout.setContentScrimColor(Color.argb(alpha, 255, 80, 0));
    }

    //websocket回调
    @Override
    public void callback(String msg) {
        //mDragView.setText(msg);
    }

    private enum CollapsingToolbarLayoutState {
        EXPANDED,
        COLLAPSED,
        INTERNEDIATE
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mSocketHelper.stopConnect();
    }
}
