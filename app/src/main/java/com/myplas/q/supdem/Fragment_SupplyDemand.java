package com.myplas.q.supdem;

import android.app.ActionBar;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.myplas.q.R;
import com.myplas.q.common.utils.TextUtils;
import com.myplas.q.common.view.CustomPopupWindow;
import com.myplas.q.common.view.MyViewPager;
import com.myplas.q.supdem.Beans.ItemBean;
import com.myplas.q.supdem.activity.SupDem_Search_Activity;
import com.myplas.q.supdem.adapter.SupDem_ViewPager_Adapter;
import com.umeng.analytics.MobclickAgent;


import java.util.ArrayList;
import java.util.List;

/**
 * 编写： 黄双
 * 电话：15378412400
 * 邮箱：15378412400@163.com
 * 时间：2017/3/17 14:45
 */
public class Fragment_SupplyDemand extends Fragment implements View.OnClickListener, Fragment_SupDem_All.ShowRefreshPopouinterface {
    private Handler handler;
    private Resources resources;
    public int visibleItemCount;

    public String user_id;
    private EditText editText;
    private ItemBean itemBean;
    private View view, imageView_sd;
    private MyViewPager myViewPager;
    private RadioGroup radioGroup_sd;
    private List<Fragment> fragmentlist;
    private Fragment_SupDem_All fragment_all;
    private Fragment_SupDem_Other fragment_other;
    private SupDem_ViewPager_Adapter viewPager_adapter;
    private CustomPopupWindow mPopupWindow2, popupWindow;
    private LinearLayout linear_qb, linear_zn, linear_wdgq, Linear_wdgz;
    private TextView textView_qb, textView_zntj, textView_wdgz, textView_wdgq, button, textView_search, textView_refresh;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        view = View.inflate(getActivity(), R.layout.layout_supdem_fragment, null);
        handler = new Handler();
        fragmentlist = new ArrayList<>();

        myViewPager = F(R.id.supdem_viewpager);
        button = F(R.id.supplydemand_btn);
        linear_qb = F(R.id.gq_titlebar_qb);
        linear_zn = F(R.id.gq_titlebar_zntj);
        editText = F(R.id.supplydemand_edit);
        linear_wdgq = F(R.id.gq_titlebar_wdgq);
        Linear_wdgz = F(R.id.gq_titlebar_wdgz);

        resources = getActivity().getResources();
        textView_qb = F(R.id.gq_titlebar_text_qb);
        textView_zntj = F(R.id.gq_titlebar_text_zntj);
        textView_wdgq = F(R.id.gq_titlebar_text_wdgq);
        textView_wdgz = F(R.id.gq_titlebar_text_wdgz);
        //textView_search = F(R.id.supplydemand_btn_search);

        button.setOnClickListener(this);
        editText.setOnClickListener(this);
        linear_qb.setOnClickListener(this);
        linear_zn.setOnClickListener(this);
        linear_wdgq.setOnClickListener(this);
        Linear_wdgz.setOnClickListener(this);
//        textView_search.setOnClickListener(this);

        fragment_all = new Fragment_SupDem_All();
        fragmentlist.add(fragment_all);
        fragment_other = new Fragment_SupDem_Other();
        fragmentlist.add(fragment_other);
        fragment_all.setShowRefreshPopouinterface(this);

        viewPager_adapter = new SupDem_ViewPager_Adapter(getChildFragmentManager(), fragmentlist);
        myViewPager.setAdapter(viewPager_adapter);

        //供求选择
        View view2 = LayoutInflater.from(getActivity()).inflate(R.layout.supplydemand_popou_layout, null, false);
        mPopupWindow2 = new CustomPopupWindow(view2, ActionBar.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.MATCH_PARENT);
        mPopupWindow2.setInputMethodMode(PopupWindow.INPUT_METHOD_NEEDED);
        mPopupWindow2.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        mPopupWindow2.setFocusable(true);
        mPopupWindow2.setOutsideTouchable(true);
        mPopupWindow2.setAnimationStyle(R.style.my_anim_popou);
        imageView_sd = (View) view2.findViewById(R.id.img_adress);
        imageView_sd.setOnClickListener(this);
        radioGroup_sd = (RadioGroup) view2.findViewById(R.id.radio_danxuan);

        radioGroup_sd.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton radioButton_address = (RadioButton) group.findViewById(checkedId);
                String radio_choice = radioButton_address.getText().toString();
                switch (radio_choice) {
                    case "全部":
                        itemBean.type = "0";
                        break;
                    case "求购":
                        itemBean.type = "1";
                        break;
                    case "供给":
                        itemBean.type = "2";
                        break;
                }
                searchData();
                mPopupWindow2.dismiss();
                button.setText(radio_choice);
                button.setTextColor(getResources().getColor(R.color.color_red));
            }
        });
        //edittext的回车监听
        editText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView arg0, int arg1, KeyEvent arg2) {
                if (arg1 == EditorInfo.IME_ACTION_SEARCH | (arg2 != null && arg2.getAction() == KeyEvent.ACTION_DOWN)) {
                    searchData();
                    return true;
                }
                return false;
            }
        });

        initData();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return view;
    }

    public void initData() {
        clearColor();
        itemBean = ItemBean.getItemBean();
        itemBean.page = 1;
        itemBean.type = "0";
        itemBean.what = "1";
        itemBean.keywords = "";
        itemBean.sortField2 = "";
        itemBean.sortField1 = "ALL";
        itemBean.hasMoreData = true;
        myViewPager.setCurrentItem(0);
        textView_qb.setTextColor(resources.getColor(R.color.color_red));
    }

    public <T extends View> T F(int id) {
        return (T) view.findViewById(id);
    }

    @Override
    public void onClick(View v) {
        itemBean = ItemBean.getItemBean();
        switch (v.getId()) {
            case R.id.supplydemand_edit:
                Intent intent = new Intent(getActivity(), SupDem_Search_Activity.class);
                startActivity(intent);
                getActivity().overridePendingTransition(R.anim.fade, R.anim.fade);
                break;
            case R.id.supplydemand_btn:
                showPopou(mPopupWindow2);
                break;
            case R.id.img_adress:
                mPopupWindow2.dismiss();
                break;
            case R.id.gq_titlebar_qb:
                clearColor();
                myViewPager.setCurrentItem(0);
                itemBean.page = 1;
                itemBean.what = "1";
                itemBean.sortField2 = "";
                itemBean.sortField1 = "ALL";
                itemBean.hasMoreData = true;
                itemBean.keywords = editText.getText().toString();
                textView_qb.setTextColor(resources.getColor(R.color.color_red));
                fragment_all.getNetData(getActivity(), "1", itemBean.keywords, "ALL", "", itemBean.type, true);
                break;

            case R.id.gq_titlebar_zntj:
                clearColor();
                myViewPager.setCurrentItem(1);
                itemBean.page = 1;
                itemBean.what = "2";
                itemBean.sortField1 = "ALL";
                itemBean.sortField2 = "AUTO";
                itemBean.hasMoreData = true;
                itemBean.keywords = editText.getText().toString();
                textView_zntj.setTextColor(resources.getColor(R.color.color_red));
                fragment_other.getNetData(getActivity(), "1", itemBean.keywords, "ALL", "AUTO", itemBean.type, true);
                break;

            case R.id.gq_titlebar_wdgz:
                myViewPager.setCurrentItem(1);
                clearColor();
                itemBean.page = 1;
                itemBean.what = "3";
                itemBean.sortField1 = "";
                itemBean.hasMoreData = true;
                itemBean.sortField2 = "CONCERN";
                itemBean.keywords = editText.getText().toString();
                textView_wdgz.setTextColor(resources.getColor(R.color.color_red));
                fragment_other.getNetData(getActivity(), "1", itemBean.keywords, "", "CONCERN", itemBean.type, true);
                break;

            case R.id.gq_titlebar_wdgq:
                myViewPager.setCurrentItem(1);
                itemBean.page = 1;
                clearColor();
                itemBean.what = "4";
                itemBean.sortField1 = "";
                itemBean.hasMoreData = true;
                itemBean.sortField2 = "DEMANDORSUPPLY";
                itemBean.keywords = editText.getText().toString();
                textView_wdgq.setTextColor(resources.getColor(R.color.color_red));
                fragment_other.getNetData(getActivity(), "1", itemBean.keywords, "", "DEMANDORSUPPLY", itemBean.type, true);
                break;
//            case R.id.supplydemand_btn_search:
//                searchData();
//                break;
        }
    }

    //点击搜索
    public void searchData() {
        itemBean.page = 1;
        itemBean.hasMoreData = true;
        itemBean.keywords = editText.getText().toString();
        if (itemBean.what.equals("1")) {
            myViewPager.setCurrentItem(0);
            fragment_all.getNetData(getActivity(), "1", itemBean.keywords, itemBean.sortField1, itemBean.sortField2, itemBean.type, true);
        } else {
            myViewPager.setCurrentItem(1);
            fragment_other.getNetData(getActivity(), "1", itemBean.keywords, itemBean.sortField1, itemBean.sortField2, itemBean.type, true);
        }
        InputMethodManager imm1 = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        imm1.hideSoftInputFromWindow(editText.getWindowToken(), 0);
    }

    public void clearColor() {
        resources = getActivity().getResources();
        textView_qb.setTextColor(resources.getColor(R.color.color_balank));
        textView_zntj.setTextColor(resources.getColor(R.color.color_balank));
        textView_wdgq.setTextColor(resources.getColor(R.color.color_balank));
        textView_wdgz.setTextColor(resources.getColor(R.color.color_balank));
    }

    //从其他页面跳转回调的刷新。。。
    public void refreshData(int type) {
        try {
            clearColor();
            if (type == 1) {
                itemBean.page = 1;
                itemBean.what = "1";
                itemBean.hasMoreData = true;
                itemBean.sortField1 = "ALL";
                itemBean.sortField2 = "";
                myViewPager.setCurrentItem(0);
                textView_qb.setTextColor(resources.getColor(R.color.color_red));
                fragment_all.getNetData(getActivity(), "1", "", "ALL", "", "0", true);
            } else {
                itemBean.page = 1;
                itemBean.what = "4";
                itemBean.hasMoreData = true;
                itemBean.sortField1 = "";
                itemBean.sortField2 = "DEMANDORSUPPLY";
                myViewPager.setCurrentItem(1);
                textView_wdgq.setTextColor(resources.getColor(R.color.color_red));
                fragment_other.getNetData(getActivity(), "1", "", "", "DEMANDORSUPPLY", "0", true);
            }
        } catch (Exception e) {
        }
    }

    public void showPopou(CustomPopupWindow popupWindow) {
        if (android.os.Build.VERSION.SDK_INT >= 24) {
            int[] a = new int[2];
            editText.getLocationInWindow(a);
            popupWindow.showAtLocation(getActivity().getWindow().getDecorView(), Gravity.NO_GRAVITY, 0, a[1] + editText.getHeight());
        } else {
            popupWindow.showAsDropDown(editText);
        }
    }

    //展示刷新多少数据的popou
    @Override
    public void showRefreshPopou(String hotSearch, String content, boolean isRefresh) {
        if (isRefresh) {
            fragment_all.isRefresh = false;
            if (popupWindow == null) {
                View view = View.inflate(getActivity(), R.layout.layout_refresh_popou, null);
                textView_refresh = (TextView) view.findViewById(R.id.text_refresh_fragement);
                popupWindow = new CustomPopupWindow(view, android.support.v7.app.ActionBar.LayoutParams.MATCH_PARENT, android.support.v7.app.ActionBar.LayoutParams.MATCH_PARENT);
                popupWindow.setFocusable(true);
                popupWindow.setOutsideTouchable(true);
                popupWindow.setAnimationStyle(R.style.my_anim_popou);
            }
            textView_refresh.setText((TextUtils.isNullOrEmpty(content))
                    ? (content)
                    : ("已是最新供求信息！"));
            showPopou(popupWindow);
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    popupWindow.dismiss();
                }
            }, 1500);
//            TextUtils.topTSnackbar(editText, (TextUtils.isNullOrEmpty(s)) ? (s) : ("已是最新头条信息！"));
        }
        //editText.setHint(hotSearch.equals("") ? "大家都在搜：" + hotSearch : "大家都在搜：7000F");
    }

    public void onResume() {
        super.onResume();
        MobclickAgent.onPageStart("MainScreen"); //统计页面，"MainScreen"为页面名称，可自定义
    }

    public void onPause() {
        super.onPause();
        MobclickAgent.onPageEnd("MainScreen");
    }
}
