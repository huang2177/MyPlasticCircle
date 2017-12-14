package com.myplas.q.contact.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.androidkun.xtablayout.XTabLayout;
import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.myplas.q.R;
import com.myplas.q.common.view.MyOnPageChangeListener;
import com.myplas.q.contact.Fragment_Contact_Detail_Demand;
import com.myplas.q.contact.Fragment_Contact_Detail_Supply;
import com.myplas.q.contact.adapter.Contact_Detail_ViewPager_Adapter;
import com.myplas.q.contact.beans.ContactInfoBean;
import com.myplas.q.contact.beans.ContactSupDemBean;
import com.myplas.q.common.api.API;
import com.myplas.q.common.appcontext.Constant;
import com.myplas.q.common.netresquset.ResultCallBack;
import com.myplas.q.common.utils.SharedUtils;
import com.myplas.q.common.utils.TextUtils;
import com.myplas.q.common.view.RoundCornerImageView;
import com.myplas.q.app.activity.BaseActivity;
import com.myplas.q.myself.setting.activity.MyDataActivity;

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
 * 时间：2017/3/23 13:39
 */
public class ContactDetailActivity extends BaseActivity implements View.OnClickListener
        , ResultCallBack
        , MyOnPageChangeListener.OnPageChangeListener {

    private boolean isSelf;
    private SharedUtils sharedUtils;

    private RoundCornerImageView mHead;
    private ViewPager mViewPager;
    private XTabLayout mTabLayout;
    private ImageView mStart, mFollow, mCall, mBack, mSign;
    private TextView mCompany, mName, mLevel, mFans, mFollow_, mIntrudtion;
    private LinearLayout mLayoutInfo, mLayoutFans, mLayoutFollow, mLayoutIntrudtion;

    private ContactSupDemBean p1, p2;
    private List<ContactSupDemBean.DataBean> list;

    private List<String> mStringList;
    private ContactInfoBean contactBean;
    private List<Fragment> mFragmentList;
    private Fragment_Contact_Detail_Demand mfragmentDemand;
    private Fragment_Contact_Detail_Supply mfragmentSupply;
    private Contact_Detail_ViewPager_Adapter mPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_layout_contact_detail);

        initView();
        initViewPager();
        getPersonInfoData();
    }

    public void initView() {
        initTileBar();
        setTitle("详细信息");
        setRightIVResId(R.drawable.btn_customer_service);

        sharedUtils = SharedUtils.getSharedUtils();

        mHead = F(R.id.xq_tx);
        mStart = F(R.id.xq_rz);
        mFans = F(R.id.wd_text_fans);
        mViewPager = F(R.id.viewpager);
        mTabLayout = F(R.id.tabLayout);
        mSign = F(R.id.contact_sign_img);
        mFollow_ = F(R.id.wd_text_follow);
        mCall = F(R.id.titlebar_img_right);
        mName = F(R.id.contact_detail_name);
        mLevel = F(R.id.contact_detail_fans);
        mLayoutFans = F(R.id.wd_linear_fans);
        mLayoutInfo = F(R.id.contact_info_ll);
        mIntrudtion = F(R.id.wd_text_introdus);
        mFollow = F(R.id.contact_detail_follow);
        mLayoutFollow = F(R.id.wd_linear_follow);
        mCompany = F(R.id.contact_detail_company);
        mLayoutIntrudtion = F(R.id.wd_linear_introdus);

        mSign.setVisibility(View.VISIBLE);
        mHead.setBorderColor(getResources().getColor(R.color.color_white));

        mCall.setOnClickListener(this);
        mFollow.setOnClickListener(this);
        mIVConact.setOnClickListener(this);
        mLayoutBack.setOnClickListener(this);
        mLayoutInfo.setOnClickListener(this);
        mLayoutFans.setOnClickListener(this);
        mLayoutFollow.setOnClickListener(this);
        mLayoutIntrudtion.setOnClickListener(this);
    }

    private void initViewPager() {
        mFragmentList = new ArrayList<>();
        mStringList = Arrays.asList("供给信息", "求购信息");

        for (int i = 0; i < mStringList.size(); i++) {
            mTabLayout.addTab(mTabLayout.newTab().setText(mStringList.get(i)));
        }
        mfragmentDemand = Fragment_Contact_Detail_Demand.newInstance();
        mfragmentSupply = Fragment_Contact_Detail_Supply.newInstance();
        mFragmentList.add(mfragmentSupply);
        mFragmentList.add(mfragmentDemand);

        mPagerAdapter = new Contact_Detail_ViewPager_Adapter(getSupportFragmentManager()
                , mFragmentList
                , mStringList);
        mViewPager.setAdapter(mPagerAdapter);

        //将选项卡和viewpager关连起来
        mTabLayout.setupWithViewPager(mViewPager);
        //给TabLayout设置适配器
        mTabLayout.setTabsFromPagerAdapter(mPagerAdapter);
    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onClick(View v) {
        if (v.getId() != R.id.titlebar_img_back && contactBean == null) {
            return;
        }
        switch (v.getId()) {
            case R.id.contact_detail_follow:
                follow();
                break;
            case R.id.titlebar_img_right:
                call(contactBean.getData().getMobile());
                break;
            case R.id.contact_info_ll:
                Intent intent = new Intent(this, MyDataActivity.class);
                intent.putExtra("userid", contactBean.getData().getUser_id());
                intent.putExtra("from", "1");
                startActivity(intent);
                break;
            case R.id.wd_linear_fans:
                Intent intent1 = new Intent(this, TheirFansFollowActivity.class);
                intent1.putExtra("userid", contactBean.getData().getUser_id());
                intent1.putExtra("function", API.GETFANS);
                intent1.putExtra("title", "Ta的粉丝");
                startActivity(intent1);
                break;
            case R.id.wd_linear_follow:
                Intent intent2 = new Intent(this, TheirFansFollowActivity.class);
                intent2.putExtra("userid", contactBean.getData().getUser_id());
                intent2.putExtra("function", API.GETFOLLOWERS);
                intent2.putExtra("title", "Ta的关注");
                startActivity(intent2);
                break;
            case R.id.wd_linear_introdus:
                Intent intent3 = new Intent(this, TheirFansFollowActivity.class);
                intent3.putExtra("userid", contactBean.getData().getUser_id());
                intent3.putExtra("function", API.GETRECOMMENDATION);
                intent3.putExtra("title", "Ta的引荐");
                startActivity(intent3);
                break;
            case R.id.titlebar_img_back:
                onBackPressed();
                break;
            default:
                break;
        }
    }


    //关注
    public void follow() {
        Map<String, String> map = new HashMap<>();
        map.put("token", sharedUtils.getData(this, "token"));
        map.put("focused_id", getIntent().getStringExtra("userid"));
        String url = API.BASEURL + API.FOCUS_OR_CANCEL;
        postAsyn(this, url, map, this, 2);
    }

    public void getPersonInfoData() {
        Map<String, String> map = new HashMap<>();
        map.put("showType", "1");
        map.put("token", sharedUtils.getData(this, "token"));
        map.put("user_id", getIntent().getStringExtra("userid"));
        String url = API.BASEURL + API.GET_ZONE_FRIEND;
        postAsyn1(this, url, map, this, 1, false);
    }

    @Override
    public void callBack(Object object, int type) {
        try {
            Gson gson = new Gson();
            String err = new JSONObject(object.toString()).getString("err");
            if (type == 1 && "0".equals(err)) {
                contactBean = gson.fromJson(object.toString(), ContactInfoBean.class);
                showInfo(contactBean);
            }

            if (type == 2 && "0".equals(err)) {
                String msg = new JSONObject(object.toString()).getString("msg");
                TextUtils.toast(this, msg);
                mFollow.setImageResource("关注成功".equals(msg)
                        ? R.drawable.img_supdem_detail_followed
                        : R.drawable.img_supdem_detail_follow);
            }

        } catch (Exception e) {
        }
    }

    @Override
    public void failCallBack(int type) {

    }

    public void showInfo(ContactInfoBean contactBean) {
        try {
            mName.setText(contactBean.getData().getName()
                    + "  " + contactBean.getData().getMobile());

            mCompany.setText(contactBean.getData().getC_name());

            mLevel.setText("等级：" + contactBean.getData().getMember_level());

            mFans.setText(contactBean.getData().getFans());
            mFollow_.setText(contactBean.getData().getFollowers());
            mIntrudtion.setText(contactBean.getData().getRecommendation());

            Glide.with(this)
                    .load(contactBean.getData().getThumb())
                    .into(mHead);

//            mStart.setImageResource(("0".equals(contactBean.getData().getIs_vip()))
//                    ? (R.drawable.icon_identity)
//                    : (R.drawable.icon_identity_hl));

            mFollow.setImageResource("0".equals(contactBean.getData().getIs_follow())
                    ? R.drawable.img_supdem_detail_follow
                    : R.drawable.img_supdem_detail_followed);


            if ("1".equals(contactBean.getData().getType())) {
                mSign.setImageResource(R.drawable.icon_factory);
            }
            if ("2".equals(contactBean.getData().getType())) {
                mSign.setImageResource(R.drawable.icon_raw_material);
            }
            if ("4".equals(contactBean.getData().getType())) {
                mSign.setImageResource(R.drawable.icon_logistics);
            }

            mfragmentDemand.showDemand(contactBean.getData().getDemand());
            mfragmentSupply.showSupplies(contactBean.getData().getSupplies());

            isSelf = (contactBean.getData().getUser_id())
                    .equals(sharedUtils.getData(this, Constant.USERID));

            mFollow.setVisibility(isSelf ? View.GONE : View.VISIBLE);

        } catch (Exception e) {
        }
    }
}
