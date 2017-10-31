package com.myplas.q.myself;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.Fragment;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.myplas.q.R;
import com.myplas.q.common.api.API;
import com.myplas.q.common.appcontext.Constant;
import com.myplas.q.common.netresquset.ResultCallBack;
import com.myplas.q.common.utils.ACache;
import com.myplas.q.common.utils.NetUtils;
import com.myplas.q.common.utils.SharedUtils;
import com.myplas.q.common.view.DragView;
import com.myplas.q.common.view.RoundCornerImageView;
import com.myplas.q.guide.activity.BaseActivity;
import com.myplas.q.myself.beans.MyZone;
import com.myplas.q.myself.credit.activity.LineOfCreditActivity;
import com.myplas.q.myself.credit.activity.PlasticMoneyActivity;
import com.myplas.q.myself.fans.activity.LookMeActivity;
import com.myplas.q.myself.fans.activity.MyFansFollowActivity;
import com.myplas.q.myself.fans.activity.MyIntroductionActivity;
import com.myplas.q.myself.integral.activity.IntegralActivity;
import com.myplas.q.myself.invoices.activity.TradeOrderActivity;
import com.myplas.q.myself.message.activity.MessageListsActivity;
import com.myplas.q.myself.setting.SettingActivity;
import com.myplas.q.myself.setting.activity.MyDataActivity;
import com.myplas.q.myself.supdem.MySupDemActivity;
import com.myplas.q.sockethelper.DefConfigBean;
import com.umeng.analytics.MobclickAgent;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * 编写： 黄双
 * 电话：15378412400
 * 邮箱：15378412400@163.com
 * 时间：2017/3/17 14:45
 */
public class Fragment_MySelf extends Fragment implements View.OnClickListener
        , ResultCallBack {
    private View view;
    private MyZone myZone;

    private ImageView image_rz;
    private RoundCornerImageView image_tx;
    private SharedUtils sharedUtils;
    private ImageButton imageButton;
    private ImageView mImage_news, mImage_more;
    private DragView mDragViewMsg, mDragViewLook, mDragViewOrder;
    private TextView text_title, text_dd, text_gj, text_qg, text_yj, text_fs, text_gz, text_look, text_name, text_gs, text_pm;
    private LinearLayout linear_title, linear_dd, linear_qg, linear_gj, linear_yj, linear_fs, linear_gz, linear_jf, linear_look, linear_edu, linear_pz, linear_set;

    private Toolbar mToolbar;
    private AppBarLayout mBarLayout;
    private FrameLayout mFrameLayout;
    private NestedScrollView mNestedScrollView;
    private CollapsingToolbarLayout mCollapsingToolbarLayout;

    private ACache mACache;
    private Handler mHandler;
    private DefConfigBean.RedDotBean mDotBean;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        setAppBarListener();
        getLoginInfo(false);
    }


    /**
     * Init view.
     */
    public void initView() {
        mHandler = new Handler();
        mACache = ACache.get(getActivity());
        sharedUtils = SharedUtils.getSharedUtils();
        view = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_layout_myself, null, false);
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
        mDragViewLook = f(R.id.look_dragview);
        linear_title = f(R.id.wd_linear_title);
        linear_yj = f(R.id.wd_linear_introdus);
        mDragViewOrder = f(R.id.order_dragview);
        mFrameLayout = f(R.id.wd_logined_news_fl);
        mImage_news = f(R.id.wd_logined_news_img);
        mDragViewMsg = f(R.id.wd_logined_news_text);
        mNestedScrollView = f(R.id.scrollView_myself);

        mToolbar = f(R.id.toolbar);
        mBarLayout = f(R.id.app_bar_layout);
        mCollapsingToolbarLayout = f(R.id.collapsing_toolbar);

        linear_dd.setOnClickListener(this);
        linear_gj.setOnClickListener(this);
        linear_qg.setOnClickListener(this);
        linear_yj.setOnClickListener(this);
        linear_fs.setOnClickListener(this);
        linear_gz.setOnClickListener(this);
        linear_jf.setOnClickListener(this);
        linear_pz.setOnClickListener(this);
        linear_set.setOnClickListener(this);
        linear_edu.setOnClickListener(this);
        linear_look.setOnClickListener(this);
        imageButton.setOnClickListener(this);
        mDragViewMsg.setOnClickListener(this);
        linear_title.setOnClickListener(this);
        mFrameLayout.setOnClickListener(this);

        linear_fs.setBackgroundResource(R.color.color_white);
        linear_gz.setBackgroundResource(R.color.color_white);
        linear_yj.setBackgroundResource(R.color.color_white);
        image_tx.setBorderColor(getResources().getColor(R.color.color_white));
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return view;
    }

    /**
     * F t.
     *
     * @param <T> the type parameter
     * @param id  the id
     * @return the t
     */
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
                default:
                    break;
            }
        }
    }


    /**
     * Gets logininfo.
     *
     * @param isShow the is show
     */
    public void getLoginInfo(boolean isShow) {
        Map<String, String> map = new HashMap<String, String>();
        map.put("token", sharedUtils.getData(getActivity(), "token"));
        BaseActivity.postAsyn1(getActivity(), API.BASEURL + API.MY_ZONE, map, this, 2, isShow);
    }

    @Override
    public void callBack(Object object, int type) {
        try {
            imageButton.setVisibility(View.GONE);
            mNestedScrollView.setVisibility(View.VISIBLE);
            JSONObject jsonObject = new JSONObject(object.toString());
            Gson gson = new Gson();
            if (type == 2) {
                if ("0".equals(jsonObject.getString("err"))) {
                    myZone = gson.fromJson(object.toString(), MyZone.class);
                    showInfo(myZone);
                } else if ("1".equals(jsonObject.getString("err"))
                        || "998".equals(jsonObject.getString("err"))) {
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
            mBarLayout.setExpanded(false);
            mNestedScrollView.setVisibility(View.GONE);
            imageButton.setVisibility(View.VISIBLE);
        }
    }

    /**
     * Show info.
     *
     * @param myZone the my zone
     */
    public void showInfo(MyZone myZone) {
        try {
            String ispass = myZone.getData().getIs_pass();
            Glide.with(getActivity())
                    .load(myZone.getData().getThumb())
                    .placeholder(R.drawable.contact_image_defaul_male)
                    .into(image_tx);
            image_rz.setImageResource((ispass.equals("0"))
                    ? (R.drawable.icon_identity)
                    : (R.drawable.icon_identity_hl));
            text_name.setText(myZone.getData().getName() + "  "
                    + myZone.getData().getMobile() + "  " +
                    (!myZone.getData().getSex().equals("0")
                            ? ("女")
                            : ("男")));
            text_gs.setText(myZone.getData().getC_name());
            text_pm.setText("等级：" + myZone.getData().getMemberlevel()
                    + "  排名：" + myZone.getData().getRank() + "位");

            text_yj.setText(myZone.getIntroduction());
            text_fs.setText(myZone.getMyfans());
            text_gz.setText(myZone.getMyconcerns());

            text_gj.setText(String.valueOf(myZone.getS_out_count()));
            text_qg.setText(String.valueOf(myZone.getS_in_count()));

        } catch (Exception e) {
        }
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

    /**
     * Sets toolbar 1 alpha.
     *
     * @param alpha the alpha
     */
    public void setToolbar1Alpha(int alpha) {
        text_title.setTextColor(Color.TRANSPARENT);
        image_rz.getDrawable().setAlpha(alpha);
        mImage_more.getDrawable().setAlpha(alpha);
        mImage_news.getDrawable().setAlpha(alpha);
        image_tx.getDrawable().mutate().setAlpha(alpha);
        text_pm.setTextColor(Color.argb(alpha, 255, 255, 255));
        text_gs.setTextColor(Color.argb(alpha, 255, 255, 255));
        text_name.setTextColor(Color.argb(alpha, 255, 255, 255));
        image_tx.setBorderColor(Color.argb(alpha, 255, 255, 255));
        mDragViewMsg.setTextColor(Color.argb(alpha, 255, 255, 255));
    }

    /**
     * Sets toolbar 2 alpha.
     *
     * @param alpha the alpha
     */
    public void setToolbar2Alpha(int alpha) {
        text_title.setTextColor(Color.argb(alpha, 255, 255, 255));
        mCollapsingToolbarLayout.setContentScrimColor(Color.argb(alpha, 255, 130, 0));
    }

    /*rabbitmq */
    public void showRedDot(boolean showRedDot) {
        try {
            int numSeeMe = Integer.parseInt(mACache.getAsString(Constant.R_SEEME));
            int numMyMsg = Integer.parseInt(mACache.getAsString(Constant.R_MYMSG));
            int numMyOrder = Integer.parseInt(mACache.getAsString(Constant.R_MYORDER));

            mDragViewMsg.setVisibility(!showRedDot || 0 == numMyMsg
                    ? View.GONE
                    : View.VISIBLE);
            mDragViewLook.setVisibility(!showRedDot || 0 == numSeeMe
                    ? View.GONE
                    : View.VISIBLE);
            mDragViewOrder.setVisibility(!showRedDot || 0 == numMyOrder
                    ? View.GONE
                    : View.VISIBLE);

            if (showRedDot) {
                mDragViewMsg.setText(numMyMsg > 99 ? "..." : numMyMsg + "");
                mDragViewLook.setText(numSeeMe > 99 ? "..." : numSeeMe + "");
                mDragViewOrder.setText(numMyOrder > 99 ? "..." : numMyOrder + "");
            }
        } catch (Exception e) {
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        showRedDot(true);
        getLoginInfo(false);
        MobclickAgent.onPageStart("MainScreen"); //统计页面，"MainScreen"为页面名称，可自定义
    }

    @Override
    public void onPause() {
        super.onPause();
        MobclickAgent.onPageEnd("MainScreen");
    }

    @Override
    public void onStop() {
        super.onStop();
        mBarLayout.setExpanded(true, true);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mHandler != null) {
            mHandler.removeCallbacksAndMessages(null);
        }
    }
}
