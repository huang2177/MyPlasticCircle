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
import com.myplas.q.myself.message.activity.MessageActivity;
import com.myplas.q.myself.setting.SettingActivity;
import com.myplas.q.myself.setting.activity.MyDataActivity;
import com.myplas.q.myself.supdem.MySupDemActivity;
import com.myplas.q.sockethelper.DefConfigBean;
import com.myplas.q.sockethelper.RabbitMQCallBack;
import com.myplas.q.sockethelper.RabbitMQConfig;
import com.umeng.analytics.MobclickAgent;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * 编写： 黄双
 * 电话：15378412400
 * 邮箱：15378412400@163.com
 * 时间：2017/3/17 14:45
 *
 * @author Administrator
 */
public class Fragment_MySelf extends Fragment implements View.OnClickListener
        , ResultCallBack
        , RabbitMQCallBack {
    private View view;
    private MyZone myZone;

    private ImageView imageStart;
    private SharedUtils sharedUtils;
    private RoundCornerImageView imageTx;
    private ImageView mimageNews, mimageMore;
    private TextView textTitle, textName, textCompany, textRank;
    private DragView mDragViewMsg, mDragViewLook, mDragViewOrder;
    private LinearLayout linearTitle, linearYj, linearFs, linearGz, linearJf, linearQg, linearGj;
    private TextView textDd, textYj, textFs, textGz, textLook, textSet, textPz, textEdu, textQg, textGj;

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
        RabbitMQConfig.getInstance(getActivity()).setResultCallBack(this);
    }


    /**
     * Init view.
     */
    public void initView() {
        mHandler = new Handler();
        mACache = ACache.get(getActivity());
        sharedUtils = SharedUtils.getSharedUtils();
        view = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_layout_myself, null, false);

        imageTx = f(R.id.xq_tx);
        imageStart = f(R.id.xq_rz);
        textQg = f(R.id.wd_text_qg);
        textGj = f(R.id.wd_text_gj);
        textPz = f(R.id.wd_text_pz);
        textDd = f(R.id.wd_text_dd);
        textSet = f(R.id.wd_text_set);
        textEdu = f(R.id.wd_text_edu);
        textFs = f(R.id.wd_text_fans);
        textRank = f(R.id.wd_title_pm);
        linearGj = f(R.id.wd_linear_gj);
        linearQg = f(R.id.wd_linear_qg);
        linearJf = f(R.id.wd_linear_jf);
        textGz = f(R.id.wd_text_follow);
        textLook = f(R.id.wd_text_look);
        textName = f(R.id.wd_title_name);
        textCompany = f(R.id.wd_title_gs);
        textYj = f(R.id.wd_text_introdus);
        textTitle = f(R.id.toolbar_title);

        linearFs = f(R.id.wd_linear_fans);
        mimageMore = f(R.id.wd_title_more);
        linearGz = f(R.id.wd_linear_follow);
        mDragViewLook = f(R.id.look_dragview);
        linearTitle = f(R.id.wd_linear_title);
        linearYj = f(R.id.wd_linear_introdus);
        mDragViewOrder = f(R.id.order_dragview);
        mFrameLayout = f(R.id.wd_logined_news_fl);
        mimageNews = f(R.id.wd_logined_news_img);
        mDragViewMsg = f(R.id.wd_logined_news_text);
        mNestedScrollView = f(R.id.scrollView_myself);

        mToolbar = f(R.id.toolbar);
        mBarLayout = f(R.id.app_bar_layout);
        mCollapsingToolbarLayout = f(R.id.collapsing_toolbar);

        textDd.setOnClickListener(this);
        textPz.setOnClickListener(this);
        textSet.setOnClickListener(this);
        textEdu.setOnClickListener(this);
        linearGj.setOnClickListener(this);
        linearQg.setOnClickListener(this);
        linearJf.setOnClickListener(this);
        textLook.setOnClickListener(this);
        linearYj.setOnClickListener(this);
        linearFs.setOnClickListener(this);
        linearGz.setOnClickListener(this);
        mDragViewMsg.setOnClickListener(this);
        linearTitle.setOnClickListener(this);
        mFrameLayout.setOnClickListener(this);

        linearFs.setBackgroundResource(R.color.color_white);
        linearGz.setBackgroundResource(R.color.color_white);
        linearYj.setBackgroundResource(R.color.color_white);
        imageTx.setBorderColor(getResources().getColor(R.color.color_white));
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
        if (!NetUtils.isNetworkStateed(getActivity()) || myZone == null) {
            return;
        }
        switch (v.getId()) {
            case R.id.wd_logined_news_fl:
                Intent i0 = new Intent(getActivity(), MessageActivity.class);
                startActivity(i0);
                break;
            case R.id.wd_text_dd:
                Intent i1 = new Intent(getActivity(), TradeOrderActivity.class);
                startActivity(i1);
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
            case R.id.wd_text_look:
                Intent intent0 = new Intent(getActivity(), LookMeActivity.class);
                startActivity(intent0);
                break;
            case R.id.wd_linear_jf:
                startActivity(new Intent(getActivity(), IntegralActivity.class));
                break;
            case R.id.wd_text_edu:
                startActivity(new Intent(getActivity(), LineOfCreditActivity.class));
                break;
            case R.id.wd_text_pz:
                startActivity(new Intent(getActivity(), PlasticMoneyActivity.class));
                break;
            case R.id.wd_linear_title:
                Intent intent6 = new Intent(getActivity(), MyDataActivity.class);
                intent6.putExtra("from", "2");
                startActivity(intent6);
                break;
            case R.id.wd_text_set:
                Intent intent7 = new Intent(getActivity(), SettingActivity.class);
                startActivity(intent7);
                break;
            default:
                break;
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

    }

    /**
     * Show info.
     *
     * @param myZone the my zone
     */
    public void showInfo(MyZone myZone) {
        try {
            textCompany.setText(myZone.getData().getC_name());

            String sex = myZone.getData().getName() + "  "
                    + myZone.getData().getMobile() + "  " +
                    ("0".equals(myZone.getData().getSex()) ? ("男") : ("女"));
            textName.setText(sex);

            String rank = "等级：" + myZone.getData().getMemberlevel()
                    + "  排名：" + myZone.getData().getRank() + "位";
            textRank.setText(rank);

            textFs.setText(myZone.getMyfans());
            textGz.setText(myZone.getMyconcerns());
            textYj.setText(myZone.getIntroduction());

            textQg.setText(myZone.getS_in_count());
            textGj.setText(myZone.getS_out_count());

            Glide.with(getActivity())
                    .load(myZone.getData().getThumb())
                    .placeholder(R.drawable.img_defaul_male)
                    .into(imageTx);

            imageStart.setImageResource(("0".equals(myZone.getData().getIs_pass()))
                    ? (R.drawable.icon_identity)
                    : (R.drawable.icon_identity_hl));
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
        textTitle.setTextColor(Color.TRANSPARENT);
        imageStart.getDrawable().setAlpha(alpha);
        mimageMore.getDrawable().setAlpha(alpha);
        mimageNews.getDrawable().setAlpha(alpha);
        imageTx.getDrawable().mutate().setAlpha(alpha);
        textRank.setTextColor(Color.argb(alpha, 255, 255, 255));
        textCompany.setTextColor(Color.argb(alpha, 255, 255, 255));
        textName.setTextColor(Color.argb(alpha, 255, 255, 255));
        imageTx.setBorderColor(Color.argb(alpha, 255, 255, 255));
        mDragViewMsg.setTextColor(Color.argb(alpha, 255, 255, 255));
    }

    /**
     * Sets toolbar 2 alpha.
     *
     * @param alpha the alpha
     */
    public void setToolbar2Alpha(int alpha) {
        textTitle.setTextColor(Color.argb(alpha, 255, 255, 255));
        mCollapsingToolbarLayout.setContentScrimColor(Color.argb(alpha, 255, 130, 0));
    }

    /*rabbitmq */
    @Override
    public void rCallback(boolean showRedDot) {
        try {
            int numSeeMe = Integer.parseInt(mACache.getAsString(Constant.R_SEEME));
            int numMyOrder = Integer.parseInt(mACache.getAsString(Constant.R_MYORDER));
            int numMyMsg = Integer.parseInt(mACache.getAsString(Constant.R_SUPDEM_MSG))
                    + Integer.parseInt(mACache.getAsString(Constant.R_PUR_MSG))
                    + Integer.parseInt(mACache.getAsString(Constant.R_REPLY_MSG))
                    + Integer.parseInt(mACache.getAsString(Constant.R_INTER_MSG));

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
        rCallback(true);
        getLoginInfo(false);
        MobclickAgent.onPageStart("MainScreen"); //统计页面，"MainScreen"为页面名称，可自定义
    }

    @Override
    public void onPause() {
        super.onPause();
        MobclickAgent.onPageEnd("MainScreen");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mHandler != null) {
            mHandler.removeCallbacksAndMessages(null);
        }
    }
}
