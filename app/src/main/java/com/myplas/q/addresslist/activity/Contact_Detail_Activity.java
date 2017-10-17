package com.myplas.q.addresslist.activity;

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
import com.myplas.q.addresslist.Fragment_Contact_Detail;
import com.myplas.q.addresslist.adapter.Contact_Detail_LV_Adapter;
import com.myplas.q.addresslist.adapter.Contact_Detail_ViewPager_Adapter;
import com.myplas.q.addresslist.beans.ContactBean;
import com.myplas.q.addresslist.beans.ContactInfoBean;
import com.myplas.q.addresslist.beans.ContactSupDemBean;
import com.myplas.q.common.api.API;
import com.myplas.q.common.appcontext.Constant;
import com.myplas.q.common.netresquset.ResultCallBack;
import com.myplas.q.common.utils.HLog;
import com.myplas.q.common.utils.SharedUtils;
import com.myplas.q.common.utils.TextUtils;
import com.myplas.q.common.view.MyImageView;
import com.myplas.q.common.view.MyListview;
import com.myplas.q.guide.activity.BaseActivity;
import com.myplas.q.myinfo.setting.activity.MyDataActivity;
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
 * 时间：2017/3/23 13:39
 */
public class Contact_Detail_Activity extends BaseActivity implements View.OnClickListener
        , ResultCallBack {

    private boolean isSelf;
    private SharedUtils sharedUtils;

    private MyImageView mHead;
    private ViewPager mViewPager;
    private XTabLayout mTabLayout;
    private LinearLayout mLayoutInfo;
    private MyListview mListview_Sup, mListview_Dem;
    private ImageView mStart, mFollow, mCall, mBack;
    private TextView mCompany, mName, mLevel, mFans, mFollow_, mIntrudtion;

    private Map<String, String> map;
    private ContactSupDemBean p1, p2;
    private Contact_Detail_LV_Adapter p_adapter;
    private List<ContactSupDemBean.DataBean> list;

    private List<String> mStringList;
    private List<Fragment> mFragmentList;
    private Fragment_Contact_Detail mFragment_Detail;
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

        map = new HashMap<String, String>();
        sharedUtils = SharedUtils.getSharedUtils();

        mHead = F(R.id.xq_tx);
        mStart = F(R.id.xq_rz);
        mFans = F(R.id.wd_text_fans);
        mViewPager = F(R.id.viewpager);
        mTabLayout = F(R.id.tabLayout);
        mFollow_ = F(R.id.wd_text_follow);
        mCall = F(R.id.titlebar_img_right);
        mName = F(R.id.contact_detail_name);
        mLevel = F(R.id.contact_detail_fans);
        mLayoutInfo = F(R.id.contact_info_ll);
        mIntrudtion = F(R.id.wd_text_introdus);
        mFollow = F(R.id.contact_detail_follow);
        mCompany = F(R.id.contact_detail_company);

        mHead.setBorderColor(getResources().getColor(R.color.color_white));

        mFans.setOnClickListener(this);
        mCall.setOnClickListener(this);
        mFollow.setOnClickListener(this);
        mFollow_.setOnClickListener(this);
        mLayoutInfo.setOnClickListener(this);
        mIntrudtion.setOnClickListener(this);
    }

    private void initViewPager() {
        mFragmentList = new ArrayList<>();
        mStringList = Arrays.asList("供给信息", "求购信息");

        for (int i = 0; i < mStringList.size(); i++) {
            mTabLayout.addTab(mTabLayout.newTab().setText(mStringList.get(i)));
            mFragment_Detail = new Fragment_Contact_Detail();
            mFragment_Detail.getTaPur((i + 1) + "", i);
            mFragmentList.add(mFragment_Detail);
        }

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
    public void onClick(View v) {
        switch (v.getId()) {
//                case R.id.more_demand:
//                    Intent intent = new Intent(this, LookPersonInfoActivity.class);
//                    intent.putExtra("tel", personinfo.getData().getMobile());
//                    intent.putExtra("bean", p2);
//                    intent.putExtra("type", "供给信息");
//                    startActivity(intent);
//                    break;
//                case R.id.more_supply:
//                    Intent intent1 = new Intent(this, LookPersonInfoActivity.class);
//                    intent1.putExtra("tel", personinfo.getData().getMobile());
//                    intent1.putExtra("bean", p1);
//                    intent1.putExtra("type", "求购信息");
//                    startActivity(intent1);
//                    break;
//                case R.id.personinfo_care_btn:
//                    focusOrCance();
//                    break;
//                case R.id.personinfo_call_img:
//                    Intent intent3 = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + personinfo.getData().getMobile()));
//                    intent3.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                    startActivity(intent3);
//                    break;
//                case R.id.xq_tx:
//                    Intent In = new Intent(this, BigImageViewActivity.class);
//                    In.putExtra("imgurl", personinfo.getData().getThumb());
////                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
////                        startActivity(In, ActivityOptions.makeSceneTransitionAnimation(this
////                                , mFLHead
////                                , "bigImageView").toBundle());
////                    } else {
//                    startActivity(In);
////                    }
//                    break;
//                case R.id.wd_zl_show:
//                    Intent In1 = new Intent(this, BigImageViewActivity.class);
//                    In1.putExtra("imgurl", personinfo.getData().getThumbcard());
////                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
////                        startActivity(In1, ActivityOptions.makeSceneTransitionAnimation(this
////                                , mFLShow
////                                , "bigImageView").toBundle());
////                    } else {
//                    startActivity(In1);
////                    }
//                    break;
            case R.id.contact_detail_follow:
                follow();
                break;

            case R.id.contact_info_ll:
                Intent intent = new Intent(this, MyDataActivity.class);
                intent.putExtra("userid", "12");
                startActivity(intent);
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
        map.put("showType", "1");
        map.put("token", sharedUtils.getData(this, "token"));
        map.put("user_id", getIntent().getStringExtra("userid"));
        String url = API.BASEURL + API.GET_ZONE_FRIEND;
        postAsyn(this, url, map, this, 1);
    }

    @Override
    public void callBack(Object object, int type) {
        try {
            Gson gson = new Gson();
            String err = new JSONObject(object.toString()).getString("err");
            if (type == 1 && err.equals("0")) {
                ContactInfoBean contactBean = gson.fromJson(object.toString(), ContactInfoBean.class);
                showInfo(contactBean);
            }

            if (type == 2 && err.equals("0")) {
                String msg = new JSONObject(object.toString()).getString("msg");
                TextUtils.Toast(this, msg);
                mFollow.setImageResource(msg.equals("关注成功")
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

            mLevel.setText("粉丝：" + contactBean.getData().getFans()
                    + "  等级：" + contactBean.getData().getMember_level());

            mFans.setText(contactBean.getData().getFans());
            mFollow_.setText(contactBean.getData().getFollowers());
            mIntrudtion.setText(contactBean.getData().getRecommendation());

            Glide.with(this)
                    .load(contactBean.getData().getThumb())
                    .placeholder((contactBean.getData().getSex().equals("男"))
                            ? (R.drawable.contact_image_defaul_male)
                            : (R.drawable.contact_image_defaul_female))
                    .into(mHead);

            mStart.setImageResource((contactBean.getData().getIs_vip().equals("0"))
                    ? (R.drawable.icon_identity)
                    : (R.drawable.icon_identity_hl));

            mFollow.setImageResource(contactBean.getData().getIs_follow().equals("关注")
                    ? R.drawable.img_supdem_detail_follow
                    : R.drawable.img_supdem_detail_followed);

            isSelf = (contactBean.getData().getUser_id())
                    .equals(sharedUtils.getData(this, Constant.USERID));
            mFollow.setVisibility(isSelf ? View.GONE : View.VISIBLE);

        } catch (Exception e) {
            HLog.e(this, e.toString());
        }
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
