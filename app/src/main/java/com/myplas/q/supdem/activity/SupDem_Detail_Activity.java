package com.myplas.q.supdem.activity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.text.InputType;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.SpannedString;
import android.text.style.AbsoluteSizeSpan;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.androidkun.xtablayout.XTabLayout;
import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.myplas.q.R;
import com.myplas.q.common.appcontext.Constant;
import com.myplas.q.guide.activity.BaseActivity;
import com.myplas.q.common.netresquset.ResultCallBack;
import com.myplas.q.common.utils.SharedUtils;
import com.myplas.q.common.utils.TextUtils;
import com.myplas.q.supdem.Beans.SupDemDetailBean;
import com.myplas.q.supdem.Fragment_SupDem_Detail_CHJ;
import com.myplas.q.supdem.Fragment_SupDem_Detail_HF;
import com.myplas.q.supdem.adapter.SupDem_Detail_ViewPager_Adapter;
import com.myplas.q.common.api.API;
import com.umeng.analytics.MobclickAgent;

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

public class SupDem_Detail_Activity extends BaseActivity implements View.OnClickListener, ResultCallBack {
    private boolean isSelf;
    private int currentItem;
    private SpannableString ss;
    private AbsoluteSizeSpan ass;
    private SharedUtils sharedUtils;

    private Button mButton;
    private EditText mEditText;
    private ViewPager mViewPager;
    private LinearLayout mLayout;
    private XTabLayout mTabLayout;
    private ImageView mIVHead, mIVStart, mIVFollow, mIVCall;
    private TextView mTVCompany, mTVFans, mTVType, mTVTime, mTVGoodsposition, mTVMode, mTVStorehouse, mTVPirce, mTVNf, mTVProduction;

    private List<String> mStringList;
    private List<Fragment> mFragmentList;
    private SupDemDetailBean mDetailBean;
    private Fragment_SupDem_Detail_HF detail_hf;
    private Fragment_SupDem_Detail_CHJ detail_chj;
    private SupDem_Detail_ViewPager_Adapter mPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_layout_supdem_detail);

        initTileBar();
        setTitle("详情信息");
        setRightIVResId(R.drawable.btn_customer_service);

        initView();
        initViewPager();
        getNetData();
        setDeliverReplyView(0);
    }

    private void initView() {
        ass = new AbsoluteSizeSpan(16, true);
        sharedUtils = SharedUtils.getSharedUtils();

        mIVHead = F(R.id.xq_tx);
        mIVStart = F(R.id.xq_rz);
        mTabLayout = F(R.id.tabLayout);
        mViewPager = F(R.id.viewpager);
        mTVNf = F(R.id.supdem_detail_nf);
        mIVCall = F(R.id.titlebar_img_right);
        mTVType = F(R.id.supdem_detail_type);
        mTVTime = F(R.id.supdem_detail_time);
        mTVFans = F(R.id.supdem_detail_fans);
        mTVMode = F(R.id.supdem_detail_mode);
        mTVPirce = F(R.id.supdem_detail_pirce);
        mIVFollow = F(R.id.supdem_detail_follow);
        mTVCompany = F(R.id.supdem_detail_company);
        mLayout = F(R.id.fragment_supdem_detail_ll);
        mButton = F(R.id.fragment_supdem_detail_btn);
        mEditText = F(R.id.fragment_supdem_detail_ev);
        mTVStorehouse = F(R.id.supdem_detail_storehouse);
        mTVProduction = F(R.id.supdem_detail_production);
        mTVGoodsposition = F(R.id.supdem_detail_goodsposition);

        mIVCall.setOnClickListener(this);
        mButton.setOnClickListener(this);
        mIVFollow.setOnClickListener(this);

        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                currentItem = position;
                setDeliverReplyView(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private void initViewPager() {
        mFragmentList = new ArrayList<>();
        mStringList = Arrays.asList("出价消息", "回复消息");

        mTabLayout.addTab(mTabLayout.newTab().setText("出价消息"));
        mTabLayout.addTab(mTabLayout.newTab().setText("回复消息"));
        detail_hf = new Fragment_SupDem_Detail_HF();
        detail_chj = new Fragment_SupDem_Detail_CHJ();
        mFragmentList.add(detail_chj);
        mFragmentList.add(detail_hf);
        mPagerAdapter = new SupDem_Detail_ViewPager_Adapter(getSupportFragmentManager()
                , mFragmentList
                , mStringList);
        mViewPager.setAdapter(mPagerAdapter);

        mViewPager.setCurrentItem(0);
        //将选项卡和viewpager关连起来
        mTabLayout.setupWithViewPager(mViewPager);
        //给TabLayout设置适配器
        mTabLayout.setTabsFromPagerAdapter(mPagerAdapter);
    }

    //获取首页数据
    public void getNetData() {
        Map<String, String> map = new HashMap<>();
        map.put("id", getIntent().getStringExtra("id"));
        map.put("token", sharedUtils.getData(this, "token"));
        map.put("user_id", getIntent().getStringExtra("userid"));
        postAsyn(this, API.BASEURL + API.GET_RELEASE_MSG_DETAIL, map, this, 1);
    }

    //关注
    public void follow() {
        Map<String, String> map = new HashMap<>();
        map.put("token", sharedUtils.getData(this, "token"));
        map.put("focused_id", getIntent().getStringExtra("userid"));
        String url = API.BASEURL + API.FOCUS_OR_CANCEL;
        postAsyn(this, url, map, this, 2);
    }

    //出价或者回复
    public void deliverOrReply(String s) {
        Map<String, String> map = new HashMap<>();
        map.put("token", sharedUtils.getData(this, "token"));
        map.put("type", mDetailBean.getData().getType());
        if (currentItem == 1) {
            map.put("pur_id", getIntent().getStringExtra("id"));
            map.put("content", s);
            map.put("send_id", getIntent().getStringExtra("userid"));
            postAsyn(this, API.BASEURL + API.SAVE_MSG, map, this, 3);
        } else {
            map.put("id", getIntent().getStringExtra("id"));
            map.put("rev_id", getIntent().getStringExtra("userid"));
            map.put("price", s);
            postAsyn(this, API.BASEURL + API.DELIVER_PRICE, map, this, 4);
        }
    }

    public <T extends View> T F(int id) {
        return (T) findViewById(id);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.titlebar_img_right:
                if (mDetailBean != null) {
                    //call(mDetailBean.getData().get);
                }
                break;
            case R.id.supdem_detail_follow:
                follow();
                break;
            case R.id.fragment_supdem_detail_btn://提交
                String s = mEditText.getText().toString();
                if (!TextUtils.isNullOrEmpty(s)) {
                    TextUtils.Toast(this, "内容不能为空！");
                    return;
                }
                deliverOrReply(s);
                break;
        }
    }


    //设置回复 出价的输入框
    public void setDeliverReplyView(int position) {
        if (position == 1) {
            mButton.setText("回复");
            ss = new SpannableString("期待您的回复...");
            ss.setSpan(ass, 0, ss.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            mEditText.setInputType(InputType.TYPE_CLASS_TEXT);
            mEditText.setHint(new SpannedString(ss));
            mLayout.setVisibility(View.VISIBLE);
        } else {
            mButton.setText("出价");
            ss = new SpannableString("期待您的出价...");
            ss.setSpan(ass, 0, ss.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            mEditText.setInputType(InputType.TYPE_CLASS_NUMBER);
            mEditText.setHint(new SpannedString(ss));
            mLayout.setVisibility(isSelf ? View.GONE : View.VISIBLE);
        }
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
                TextUtils.Toast(this, new JSONObject(object.toString()).getString("msg"));
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

    @SuppressLint("SetTextI18n")
    public void showInfo(SupDemDetailBean mDetailBean) {
        Glide.with(this)
                .load(mDetailBean.getData().getThumb())
                .placeholder(R.drawable.contact_image_defaul_male)
                .into(mIVHead);
        String companyName = mDetailBean.getData().getC_name()
                + " "
                + mDetailBean.getData().getName();
        mTVCompany.setText(companyName);

        mTVFans.setText("粉丝：" + mDetailBean.getData().getFans()
                + "   等级："
                + mDetailBean.getData().getMember_level());

        mIVStart.setImageResource(mDetailBean.getData().getStatus().equals("0")
                ? R.drawable.icon_identity
                : R.drawable.icon_identity_hl);

        mIVFollow.setImageResource(mDetailBean.getData().getStatus().equals("关注")
                ? R.drawable.img_supdem_detail_follow
                : R.drawable.img_supdem_detail_followed);

        mTVType.setText(mDetailBean.getData().getType().equals("1")
                ? "求购"
                : "供给");

        mTVType.setCompoundDrawablesWithIntrinsicBounds(mDetailBean.getData().getType().equals("1")
                ? R.drawable.icon_purchase_content
                : R.drawable.icon_supply_content, 0, 0, 0);

        mTVTime.setText(mDetailBean.getData().getInput_time());
        mTVMode.setText("牌号:" + mDetailBean.getData().getModel());
        mTVPirce.setText("价格:" + mDetailBean.getData().getUnit_price());
        mTVStorehouse.setText("厂家:" + mDetailBean.getData().getF_name());
        mTVGoodsposition.setText("货物位置:" + mDetailBean.getData().getStore_house());

        mTVNf.setText("现货/期货:" + (mDetailBean.getData().getCargo_type().equals("1")
                ? "现货"
                : "期货"));

        isSelf = (mDetailBean.getData().getUser_id()) == sharedUtils.getData(this, Constant.USERID);
    }

    public void onResume() {
        super.onResume();
        MobclickAgent.onResume(this);
    }

    public void onPause() {
        super.onPause();
        MobclickAgent.onPause(this);
    }
}
