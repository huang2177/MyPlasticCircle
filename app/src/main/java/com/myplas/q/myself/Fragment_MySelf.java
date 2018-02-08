package com.myplas.q.myself;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
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
import com.myplas.q.app.fragment.BaseFragment;
import com.myplas.q.common.api.API;
import com.myplas.q.common.appcontext.Constant;
import com.myplas.q.common.net.ResultCallBack;
import com.myplas.q.common.utils.ACache;
import com.myplas.q.common.utils.NetUtils;
import com.myplas.q.common.utils.SharedUtils;
import com.myplas.q.common.view.DragView;
import com.myplas.q.common.view.RoundCornerImageView;
import com.myplas.q.homepage.activity.NewContactDetailActivity;
import com.myplas.q.myself.beans.MyZone;
import com.myplas.q.myself.credit.activity.LineOfCreditActivity;
import com.myplas.q.myself.credit.activity.PlasticMoneyActivity;
import com.myplas.q.myself.fans.activity.LookMeActivity;
import com.myplas.q.myself.fans.activity.MyFansFollowActivity;
import com.myplas.q.myself.fans.activity.MyIntroductionActivity;
import com.myplas.q.myself.integral.activity.NewIntegralActivity;
import com.myplas.q.myself.invoices.activity.TradeOrderActivity;
import com.myplas.q.myself.message.activity.MessageActivity;
import com.myplas.q.myself.setting.SettingActivity;
import com.myplas.q.myself.setting.activity.MyInfomationActivity;
import com.myplas.q.myself.store.MyStoreActivity;
import com.myplas.q.myself.supdem.MySupDemActivity;
import com.myplas.q.sockethelper.DefConfigBean;
import com.myplas.q.sockethelper.RabbitMQCallBack;
import com.myplas.q.sockethelper.RabbitMQConfig;
import com.umeng.analytics.MobclickAgent;

import org.json.JSONObject;

/**
 * 编写： 黄双
 * 电话：15378412400
 * 邮箱：15378412400@163.com
 * 时间：2017/3/17 14:45
 *
 * @author Administrator
 */
public class Fragment_MySelf extends BaseFragment implements View.OnClickListener
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
    private TextView textDd, textYj, textFs, textGz, textLook, textSet, textPz, textEdu, textQg, textGj;
    private LinearLayout linearTitle, linearYj, linearFs, linearGz, linearJf, linearQg, linearGj, linearStore;

    private Toolbar mToolbar;
    private AppBarLayout mBarLayout;
    private FrameLayout mFrameLayout;
    private CollapsingToolbarLayout mCollapsingToolbarLayout;

    private ACache mACache;
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
        mACache = ACache.get(getActivity());
        sharedUtils = SharedUtils.getSharedUtils();
        view = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_layout_myself, null, false);

        imageTx = F(view, R.id.xq_tx);
        imageStart = F(view, R.id.xq_rz);
        textQg = F(view, R.id.wd_text_qg);
        textGj = F(view, R.id.wd_text_gj);
        textPz = F(view, R.id.wd_text_pz);
        textDd = F(view, R.id.wd_text_dd);
        textSet = F(view, R.id.wd_text_set);
        textEdu = F(view, R.id.wd_text_edu);
        textFs = F(view, R.id.wd_text_fans);
        textRank = F(view, R.id.wd_title_pm);
        linearGj = F(view, R.id.wd_linear_gj);
        linearQg = F(view, R.id.wd_linear_qg);
        linearJf = F(view, R.id.wd_linear_jf);
        textGz = F(view, R.id.wd_text_follow);
        textLook = F(view, R.id.wd_text_look);
        textName = F(view, R.id.wd_title_name);
        textCompany = F(view, R.id.wd_title_gs);
        textYj = F(view, R.id.wd_text_introdus);
        textTitle = F(view, R.id.toolbar_title);

        linearFs = F(view, R.id.wd_linear_fans);
        mimageMore = F(view, R.id.wd_title_more);
        linearGz = F(view, R.id.wd_linear_follow);
        linearStore = F(view, R.id.wd_text_store);
        mDragViewLook = F(view, R.id.look_dragview);
        linearTitle = F(view, R.id.wd_linear_title);
        linearYj = F(view, R.id.wd_linear_introdus);
        mDragViewOrder = F(view, R.id.order_dragview);
        mimageNews = F(view, R.id.wd_logined_news_img);
        mFrameLayout = F(view, R.id.wd_logined_news_fl);
        mDragViewMsg = F(view, R.id.wd_logined_news_text);

        mToolbar = F(view, R.id.toolbar);
        mBarLayout = F(view, R.id.app_bar_layout);
        mCollapsingToolbarLayout = F(view, R.id.collapsing_toolbar);

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
        linearStore.setOnClickListener(this);
        linearTitle.setOnClickListener(this);
        mDragViewMsg.setOnClickListener(this);
        mFrameLayout.setOnClickListener(this);

        linearFs.setBackgroundResource(R.color.color_white);
        linearGz.setBackgroundResource(R.color.color_white);
        linearYj.setBackgroundResource(R.color.color_white);
        imageTx.setBorderColor(getResources().getColor(R.color.color_white));
        Glide.with(getActivity()).load(R.drawable.img_defaul_male).into(imageTx);
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return view;
    }


    @Override
    public void onClick(View v) {
        if (!NetUtils.isNetworkStateed(getActivity())) {
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
                intent3.putExtra("type", "1");
                startActivity(intent3);
                break;
            case R.id.wd_linear_follow:
                Intent intent4 = new Intent(getActivity(), MyFansFollowActivity.class);
                intent4.putExtra("type", "2");
                startActivity(intent4);
                break;
            case R.id.wd_text_look:
                Intent intent0 = new Intent(getActivity(), LookMeActivity.class);
                startActivity(intent0);
                break;
            case R.id.wd_linear_jf:
                startActivity(new Intent(getActivity(), NewIntegralActivity.class));
                break;
            case R.id.wd_text_edu:
                startActivity(new Intent(getActivity(), LineOfCreditActivity.class));
                break;
            case R.id.wd_text_pz:
                startActivity(new Intent(getActivity(), PlasticMoneyActivity.class));
                break;
            case R.id.wd_linear_title:
                Intent intent6 = new Intent(getActivity(), MyInfomationActivity.class);
                intent6.putExtra("from", "2");
                startActivity(intent6);
                break;
            case R.id.wd_text_set:
                Intent intent7 = new Intent(getActivity(), SettingActivity.class);
                startActivity(intent7);
                break;
            case R.id.wd_text_store:
                if (myZone == null) {
                    return;
                }
                if ("1".equals(myZone.getData().getShop_audit_status())) {
                    Intent i = new Intent(getActivity(), NewContactDetailActivity.class);
                    i.putExtra("userid", myZone.getData().getUser_id());
                    startActivity(i);
                } else {
                    Intent i = new Intent(getActivity(), MyStoreActivity.class);
                    i.putExtra(Constant.STAUTS, myZone.getData().getShop_audit_status());
                    startActivity(i);
                }
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
    public void getLoginInfo() {
        getAsyn(getActivity(), API.MY_ZONE, null, this, 1, false);
    }

    @Override
    public void callBack(Object object, int type) {
        try {
            JSONObject jsonObject = new JSONObject(object.toString());
            String code = jsonObject.getString("code");
            Gson gson = new Gson();

            if ("0".equals(code)) {
                myZone = gson.fromJson(object.toString(), MyZone.class);
                showInfo(myZone);
            } else if ("1".equals(code)
                    || "998".equals(code)) {
                sharedUtils.setData(getActivity(), "token", "");
                sharedUtils.setData(getActivity(), "userid", "");
                sharedUtils.setBooloean(getActivity(), "logined", false);
            }
        } catch (Exception e) {
        }
    }

    @Override
    public void failCallBack(int type, String message, int httpCode) {

    }

    /**
     * Show info.
     *
     * @param myZone the my zone
     */
    public void showInfo(MyZone myZone) {

        textCompany.setText(myZone.getData().getC_name());

        String sex = myZone.getData().getName() + "  "
                + myZone.getData().getMobile() + "  " +
                ("0".equals(myZone.getData().getSex()) ? ("男") : ("女"));
        textName.setText(sex);

        String rank = "等级：" + myZone.getData().getMember_level()
                + "  排名：" + myZone.getData().getRank() + "位";
        textRank.setText(rank);

        textFs.setText(myZone.getMyfans());
        textGz.setText(myZone.getMyconcerns());
        textYj.setText(myZone.getIntroduction());

        textQg.setText(myZone.getS_in_count());
        textGj.setText(myZone.getS_out_count());

        Glide.with(getActivity())
                .load(myZone.getData().getThumb())
                .into(imageTx);

        imageStart.setImageResource(("1".equals(myZone.getData().getIsshop()))
                ? (R.drawable.icon_identity_hl)
                : 0);

        sharedUtils.setData(getActivity(), Constant.STAUTS, myZone.getData().getShop_audit_status());

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
        mimageMore.getDrawable().setAlpha(alpha);
        mimageNews.getDrawable().setAlpha(alpha);
        textRank.setTextColor(Color.argb(alpha, 255, 255, 255));
        textCompany.setTextColor(Color.argb(alpha, 255, 255, 255));
        textName.setTextColor(Color.argb(alpha, 255, 255, 255));
        mDragViewMsg.setTextColor(Color.argb(alpha, 255, 255, 255));
        if (imageStart.getDrawable() != null) {
            imageStart.getDrawable().setAlpha(alpha);
        }
        if (imageTx.getDrawable() != null) {
            imageTx.getDrawable().setAlpha(alpha);
            imageTx.setBorderColor(Color.argb(alpha, 255, 255, 255));
        }
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
    public void rCallback(boolean showRedDot, boolean isShowNotify) {
        try {
            int numSeeMe = sharedUtils.getInt(getActivity(), Constant.R_SEEME);
            int numMyOrder = sharedUtils.getInt(getActivity(), Constant.R_MYORDER);
            int numMyMsg = sharedUtils.getInt(getActivity(), Constant.R_SUPDEM_MSG)
                    + sharedUtils.getInt(getActivity(), Constant.R_PUR_MSG)
                    + sharedUtils.getInt(getActivity(), Constant.R_REPLY_MSG)
                    + sharedUtils.getInt(getActivity(), Constant.R_INTER_MSG);

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
        getLoginInfo();
        rCallback(true, false);
        MobclickAgent.onPageStart("MainScreen"); //统计页面，"MainScreen"为页面名称，可自定义
    }

    @Override
    public void onPause() {
        super.onPause();
        MobclickAgent.onPageEnd("MainScreen");
    }
}
