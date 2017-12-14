package com.myplas.q.supdem.activity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.text.InputType;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.androidkun.xtablayout.XTabLayout;
import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.myplas.q.R;
import com.myplas.q.common.appcontext.Constant;
import com.myplas.q.common.view.RoundCornerImageView;
import com.myplas.q.app.activity.BaseActivity;
import com.myplas.q.common.netresquset.ResultCallBack;
import com.myplas.q.common.utils.SharedUtils;
import com.myplas.q.common.utils.TextUtils;
import com.myplas.q.app.activity.ShareActivity;
import com.myplas.q.common.view.MyOnPageChangeListener;
import com.myplas.q.supdem.MyOnItemClickListener;
import com.myplas.q.supdem.beans.SupDemDetailBean;
import com.myplas.q.supdem.Fragment_SupDem_Detail_CHJ;
import com.myplas.q.supdem.Fragment_SupDem_Detail_HF;
import com.myplas.q.supdem.OnKeyboardChangeListener;
import com.myplas.q.supdem.adapter.SupDem_Detail_ViewPager_Adapter;
import com.myplas.q.common.api.API;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 编写： 黄双
 * 电话：15378412400
 * 邮箱：15378412400@163.com
 * 时间：2017/3/19 15:44
 */

public class SupDem_Detail_Activity extends BaseActivity implements View.OnClickListener
        , ResultCallBack
        , MyOnItemClickListener
        , MyOnPageChangeListener.OnPageChangeListener
        , OnKeyboardChangeListener.OnChangeListener {

    private SharedUtils sharedUtils;
    private int currentItem, position;
    private boolean isSelf, isWeChatOperation;

    private Button mButton;
    private EditText mEditText;
    private RoundCornerImageView mIVHead;
    private ViewPager mViewPager;
    private XTabLayout mTabLayout;
    private AppBarLayout mBarLayout;
    private LinearLayout mLayout, mLayoutRoot;
    private ImageView mIVStart, mIVFollow, mIVCall, mSign;
    private TextView mTVCompany, mTVFans, mTVType, mTVTime, mTVMode, mTVStorehouse, mTVPirce, mTVNf, mTVProduction;

    private List<String> mStringList;
    private List<Fragment> mFragmentList;
    private SupDemDetailBean mDetailBean;
    private Fragment_SupDem_Detail_HF detail_hf;
    private Fragment_SupDem_Detail_CHJ detail_chj;
    private SupDem_Detail_ViewPager_Adapter mPagerAdapter;

    private String sign, id, pur_id, send_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_layout_supdem_detail);

        initTileBar();
        setTitle("详情信息");
        setRightIVResId(R.drawable.btn_customer_service);
        mTVRight.setCompoundDrawablesWithIntrinsicBounds(R.drawable.btn_share, 0, 0, 0);

        initView();
        initViewPager(position);
        setDeliverReplyView(position);

        getNetData();
    }

    private void initView() {
        sharedUtils = SharedUtils.getSharedUtils();

        mIVHead = F(R.id.xq_tx);
        mIVStart = F(R.id.xq_rz);
        mTabLayout = F(R.id.tabLayout);
        mViewPager = F(R.id.viewpager);
        mTVNf = F(R.id.supdem_detail_nf);
        mSign = F(R.id.contact_sign_img);
        mBarLayout = F(R.id.appbar_layout);
        mIVCall = F(R.id.titlebar_img_right);
        mTVType = F(R.id.supdem_detail_type);
        mTVTime = F(R.id.supdem_detail_time);
        mTVFans = F(R.id.supdem_detail_fans);
        mTVMode = F(R.id.supdem_detail_mode);
        mTVPirce = F(R.id.supdem_detail_pirce);
        mLayoutRoot = F(R.id.supdem_detail_ll);
        mIVFollow = F(R.id.supdem_detail_follow);
        mTVCompany = F(R.id.supdem_detail_company);
        mLayout = F(R.id.fragment_supdem_detail_ll);
        mButton = F(R.id.fragment_supdem_detail_btn);
        mEditText = F(R.id.fragment_supdem_detail_ev);
        mTVStorehouse = F(R.id.supdem_detail_storehouse);
        mTVProduction = F(R.id.supdem_detail_production);


        mIVCall.setOnClickListener(this);
        mButton.setOnClickListener(this);
        mTVRight.setOnClickListener(this);
        mIVFollow.setOnClickListener(this);
        mViewPager.addOnPageChangeListener(new MyOnPageChangeListener(this));
        mLayoutRoot.addOnLayoutChangeListener(new OnKeyboardChangeListener(this, this));

        Bundle bundle = getIntent().getBundleExtra("bundle");
        position = (bundle == null) ? 0 : bundle.getInt("position");
    }

    private void initViewPager(int position) {
        mFragmentList = new ArrayList<>();
        mStringList = Arrays.asList("出价消息", "回复消息");

        mTabLayout.addTab(mTabLayout.newTab().setText("出价消息"));
        mTabLayout.addTab(mTabLayout.newTab().setText("回复消息"));
        detail_hf = new Fragment_SupDem_Detail_HF();
        detail_hf.mMyOnItemClickListener = this;

        detail_chj = new Fragment_SupDem_Detail_CHJ();
        mFragmentList.add(detail_chj);
        mFragmentList.add(detail_hf);
        mPagerAdapter = new SupDem_Detail_ViewPager_Adapter(getSupportFragmentManager()
                , mFragmentList
                , mStringList);
        mViewPager.setAdapter(mPagerAdapter);

        mTabLayout.getTabAt(position).select();
        mViewPager.setCurrentItem(position);
        //将选项卡和viewpager关连起来
        mTabLayout.setupWithViewPager(mViewPager);
        //给TabLayout设置适配器
        mTabLayout.setTabsFromPagerAdapter(mPagerAdapter);
    }

    /**
     * 获取首页数据
     */

    public void getNetData() {
        Map<String, String> map = new HashMap<>();
        map.put("id", getIntent().getStringExtra("id"));
        map.put("token", sharedUtils.getData(this, "token"));
        map.put("user_id", getIntent().getStringExtra("userid"));
        postAsyn(this, API.BASEURL + API.GET_RELEASE_MSG_DETAIL, map, this, 1);
    }

    /**
     * 关注
     */

    public void follow() {
        Map<String, String> map = new HashMap<>();
        map.put("token", sharedUtils.getData(this, "token"));
        map.put("focused_id", getIntent().getStringExtra("userid"));
        String url = API.BASEURL + API.FOCUS_OR_CANCEL;
        postAsyn(this, url, map, this, 2);
    }

    /**
     * 出价或者回复
     */

    public void deliverOrReply() {
        String s = mEditText.getText().toString();
        if (!TextUtils.notEmpty(s)) {
            TextUtils.toast(this, "内容不能为空！");
            return;
        }

        Map<String, String> map = new HashMap<>();
        map.put("type", mDetailBean.getData().getType());
        map.put("token", sharedUtils.getData(this, "token"));

        if (currentItem == 0) {//出价
            map.put("price", s);
            map.put("id", getIntent().getStringExtra("id"));
            map.put("rev_id", getIntent().getStringExtra("userid"));
            postAsyn(this, API.BASEURL + API.DELIVER_PRICE, map, this, 4);

        } else {               //回复
            map.put("id", id);
            map.put("sign", sign);
            map.put("content", s);
            map.put("pur_id", pur_id);
            map.put("send_id", send_id);
            postAsyn(this, API.BASEURL + API.SAVE_MSG, map, this, 3);
        }
    }

    /**
     * 设置回复 出价的输入框
     */

    public void setDeliverReplyView(int position) {
        if (position == 1) {
            mButton.setText("回复");
            mEditText.setHint("期待您的回复...");
            mEditText.setInputType(InputType.TYPE_CLASS_TEXT);
            mLayout.setVisibility(View.VISIBLE);
        } else {
            mButton.setText("出价");
            mEditText.setHint("期待您的出价...");
            mEditText.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);
            mLayout.setVisibility(isSelf ? View.GONE : View.VISIBLE);
        }
    }

    @Override
    public void onPageSelected(int position) {
        currentItem = position;
        setDeliverReplyView(position);
    }

    @Override
    public void onClick(View v) {
        if (mDetailBean == null) {
            return;
        }
        switch (v.getId()) {
            case R.id.titlebar_img_right:
                call(mDetailBean.getData().getMobile());
                break;
            case R.id.supdem_detail_follow:
                follow();
                break;
            case R.id.titlebar_text_right:
                share();
                break;
            case R.id.fragment_supdem_detail_btn://提交
                deliverOrReply();
                break;
            default:
                break;
        }
    }

    /*出价或者回复的点击事件*/
    @Override
    public void onItemClick(String sign, String name, String id, String pur_id, String user_id) {
        this.id = id;
        this.sign = sign;
        this.pur_id = pur_id;
        this.send_id = user_id;
        mEditText.setHint("回复" + name);
        mBarLayout.setExpanded(false, true);
        showInPutKeybord(mEditText);
    }

    @Override
    public void callBack(Object object, int type) {
        try {
            Gson gson = new Gson();
            String err = new JSONObject(object.toString()).getString("err");
            if (type == 1) {
                if (err.equals("0")) {
                    mDetailBean = gson.fromJson(object.toString(), SupDemDetailBean.class);
                    showInfo(mDetailBean);
                }
            }
            if (type == 2 && err.equals("0")) {
                TextUtils.toast(this, new JSONObject(object.toString()).getString("msg"));
                String msg = new JSONObject(object.toString()).getString("msg");
                mIVFollow.setImageResource(msg.equals("关注成功")
                        ? R.drawable.img_supdem_detail_followed
                        : R.drawable.img_supdem_detail_follow);
            }
            if (type == 3 && err.equals("0") && detail_hf != null) {
                mEditText.setText("");
                detail_hf.getReply();
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(mEditText.getWindowToken(), 0);
            }
            if (type == 4 && err.equals("0") && detail_chj != null) {
                mEditText.setText("");
                detail_chj.getDeLiverPrice();
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(mEditText.getWindowToken(), 0);
            }
        } catch (Exception e) {
        }
    }

    @Override
    public void failCallBack(int type) {

    }

    private void share() {
        String s = (mDetailBean.getData().getType().equals("1") ? "求:" : "供：")
                + mDetailBean.getData().getModel()
                + mDetailBean.getData().getF_name()
                + "价格" + mDetailBean.getData().getUnit_price()
                + mDetailBean.getData().getStore_house()
                + (mDetailBean.getData().getCargo_type().equals("1") ? "现货" : "期货");
        Intent in = new Intent(this, ShareActivity.class);
        in.putExtra("title", s);
        in.putExtra("type", "4");
        in.putExtra("id", mDetailBean.getData().getId());
        in.putExtra("userid", mDetailBean.getData().getUser_id());
        in.putExtra("supdemType", mDetailBean.getData().getType());
        startActivity(in);
    }

    @SuppressLint("SetTextI18n")
    public void showInfo(SupDemDetailBean mDetailBean) {
        String companyName = mDetailBean.getData().getC_name()
                + " "
                + mDetailBean.getData().getName();
        mTVCompany.setText(companyName);

        mTVFans.setText("粉丝：" + mDetailBean.getData().getFans()
                + "   等级：" + mDetailBean.getData().getMember_level());

        mIVStart.setImageResource("1".equals(mDetailBean.getData().getMerge_three())
                ? R.drawable.icon_identity_hl
                : 0);

        Glide.with(this)
                .load(mDetailBean.getData().getThumb())
                .into(mIVHead);

        mIVFollow.setImageResource("0".equals(mDetailBean.getData().getStatus())
                ? R.drawable.img_supdem_detail_follow
                : R.drawable.img_supdem_detail_followed);

        if ("1".equals(mDetailBean.getData().getStype())) {
            mSign.setImageResource(R.drawable.icon_factory);
        }
        if ("2".equals(mDetailBean.getData().getStype())) {
            mSign.setImageResource(R.drawable.icon_raw_material);
        }
        if ("4".equals(mDetailBean.getData().getStype())) {
            mSign.setImageResource(R.drawable.icon_logistics);
        }

        mTVType.setText(mDetailBean.getData().getType().equals("1")
                ? "求购"
                : "供给");

        mTVType.setCompoundDrawablesWithIntrinsicBounds(mDetailBean.getData().getType().equals("1")
                ? R.drawable.icon_purchase_content
                : R.drawable.icon_supply_content, 0, 0, 0);

        mTVTime.setText(mDetailBean.getData().getInput_time());
        mTVMode.setText(mDetailBean.getData().getModel());
        mTVPirce.setText(mDetailBean.getData().getUnit_price());
        mTVProduction.setText(mDetailBean.getData().getF_name());
        mTVStorehouse.setText(mDetailBean.getData().getStore_house());

        mTVNf.setText(mDetailBean.getData().getCargo_type().equals("1")
                ? "现货"
                : "期货");
        id = "";
        sign = "0";
        pur_id = mDetailBean.getData().getId();
        send_id = mDetailBean.getData().getUser_id();

        isSelf = (mDetailBean.getData().getUser_id())
                .equals(sharedUtils.getData(this, Constant.USERID));
        isWeChatOperation = mDetailBean.getQapp_status().contains("operator");

        setRightTVText("");
        mLayout.setVisibility(isSelf ? View.GONE : View.VISIBLE);
        mIVFollow.setVisibility(isSelf ? View.GONE : View.VISIBLE);

        FrameLayout.LayoutParams lp = (FrameLayout.LayoutParams) mTVRight.getLayoutParams();
        lp.rightMargin = 130;
        mTVRight.setLayoutParams(lp);

        mTVRight.setVisibility(isSelf | isWeChatOperation ? View.VISIBLE : View.GONE);
    }

    @Override
    public void onKeyboardHidden() {
        if (currentItem == 1) {
            id = "";
            sign = "0";
            pur_id = mDetailBean.getData().getId();
            send_id = mDetailBean.getData().getUser_id();

            mEditText.setHint("期待您的回复...");
        } else {
            mEditText.setHint("期待您的出价...");
        }
    }

}