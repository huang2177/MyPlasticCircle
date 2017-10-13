package com.myplas.q.addresslist;

import android.app.ActionBar;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.myplas.q.R;
import com.myplas.q.addresslist.Beans.TXL_Bean;
import com.myplas.q.addresslist.activity.AD_DialogActivtiy;
import com.myplas.q.addresslist.activity.Cover_WebActivity;
import com.myplas.q.addresslist.adapter.Fragment_Conact_LV_Adapter;
import com.myplas.q.common.api.API;
import com.myplas.q.common.netresquset.ResultCallBack;
import com.myplas.q.common.view.CommonDialog;
import com.myplas.q.common.utils.SharedUtils;
import com.myplas.q.common.utils.TextUtils;
import com.myplas.q.common.view.CustomPopupWindow;
import com.myplas.q.common.view.MyListview;
import com.myplas.q.common.view.MyNestedScrollView;
import com.myplas.q.common.view.PinnedHeaderListView;
import com.myplas.q.common.view.RefreshPopou;
import com.myplas.q.guide.activity.BaseActivity;
import com.myplas.q.myinfo.integral.activity.IntegralActivity;
import com.myplas.q.myinfo.integral.activity.IntegralPayActivtity;
import com.myplas.q.myinfo.login.LoginActivity;
import com.myplas.q.myinfo.fans.activity.MyFansFollowActivity;
import com.myplas.q.myinfo.fans.activity.PersonInfoActivity;
import com.umeng.analytics.MobclickAgent;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 编写： 黄双
 * 电话：15378412400
 * 邮箱：15378412400@163.com
 * 时间：2017/3/17 14:45
 */
public class Fragment_Conact extends Fragment implements View.OnClickListener
        , ResultCallBack
        , CommonDialog.DialogShowInterface
        , MyNestedScrollView.onScrollIterface
        , SwipeRefreshLayout.OnRefreshListener {

    public String content;
    private boolean islogin;
    private SharedUtils sharedUtils;
    private int page, position, visibleItemCount;
    private String radio_choice, userid, keywords = "", region = "0", c_type = "0", banner_url, img_url, jumpToWhere, jumpTitle;

    private TXL_Bean.TopBean topBean;
    private List<TXL_Bean.PersonsBean> list, list_more;
    private Fragment_Conact_LV_Adapter mLVAdapter;

    private Handler handler;
    private TXL_Bean txlBean;
    private ImageButton imageButton;
    private CustomPopupWindow popupWindow;
    private ListView listView;
    private ImageView imageView_intergral;
    private View view, imageView_tm, imageView_adress;
    public CustomPopupWindow mPopupWindow1, mPopupWindow2, mPopupWindow3;

    private AppBarLayout appBarLayout;
    private RefreshPopou mRefreshPopou;
    private MyNestedScrollView mScrollView;
    private SwipeRefreshLayout mRefreshLayout;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        page = 1;
        region = "0";
        c_type = "0";
        keywords = "";
        list_more = new ArrayList();
        content = "您还未登录,请先登录塑料圈!";

        handler = new Handler();
        mRefreshPopou = new RefreshPopou(getActivity(), 2);
        sharedUtils = SharedUtils.getSharedUtils();
        islogin = sharedUtils.getBoolean(getActivity(), "logined");
        view = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_layout_conact, null, false);

        listView = (ListView) view.findViewById(R.id.conact_lv);
        imageButton = (ImageButton) view.findViewById(R.id.img_reload);
        appBarLayout = (AppBarLayout) view.findViewById(R.id.conact_appbarlayout);
        mScrollView = (MyNestedScrollView) view.findViewById(R.id.conact_scrollview);
        mRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.conact_swipelayout);

        mScrollView.setOnScrollIterface(this);
        mRefreshLayout.setOnRefreshListener(this);
        mRefreshLayout.setColorSchemeResources(R.color.color_red);
        //排序
        View view1 = LayoutInflater.from(getActivity()).inflate(R.layout.txl_dropdown_popouwindow, null, false);
        mPopupWindow1 = new CustomPopupWindow(view1, ActionBar.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.WRAP_CONTENT);
        setPoPouProperty(mPopupWindow1);
        mPopupWindow1.setAnimationStyle(R.style.my_anim_popou);
        imageView_tm = (View) view1.findViewById(R.id.img_tm);
        imageView_tm.setOnClickListener(this);
        //地区
        View view2 = LayoutInflater.from(getActivity()).inflate(R.layout.txl_dropdown_popouwindow_address, null, false);
        mPopupWindow2 = new CustomPopupWindow(view2, ActionBar.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.MATCH_PARENT);
        setPoPouProperty(mPopupWindow2);
        mPopupWindow2.setAnimationStyle(R.style.my_anim_popou);
        imageView_adress = (View) view2.findViewById(R.id.img_adress);
        imageView_adress.setOnClickListener(this);
        //search
        View view3 = LayoutInflater.from(getActivity()).inflate(R.layout.layout_address_search_popou, null, false);
        mPopupWindow3 = new CustomPopupWindow(view3, ActionBar.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.MATCH_PARENT);
        setPoPouProperty(mPopupWindow3);
        view3.findViewById(R.id.view_search).setOnClickListener(this);
//        radioGroup_time.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(RadioGroup group, int checkedId) {
//                page = 1;
//                hasMoerData = true;
//                if (list != null && mLVAdapter != null) {
//                    mLVAdapter.setList(list);
//                    mLVAdapter.notifyDataSetChanged();
//                }
//                keywords = editText.getText().toString();
//                radioButton_time = (RadioButton) group.findViewById(checkedId);
//                radio_choice = radioButton_time.getText().toString();
//                switch (radio_choice) {
//                    case "所有分类":
//                        c_type = "0";
//                        break;
//                    case "塑料制品厂":
//                        c_type = "1";
//                        break;
//                    case "原料供应商":
//                        c_type = "2";
//                        break;
//                    case "物流服务商":
//                        c_type = "4";
//                        break;
//                    case "其他":
//                        c_type = "5";
//                        break;
//                }
//                getNetData("1", keywords, c_type, region, true);
//                mPopupWindow1.dismiss();
//                zh_zj.setTextColor(getResources().getColor(R.color.color_red));
//                zh_zj.setText(radio_choice);
//            }
//        });
//        radioGroup_address.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(RadioGroup group, int checkedId) {
//                page = 1;
//                hasMoerData = true;
//                if (list != null && mLVAdapter != null) {
//                    mLVAdapter.setList(list);
//                    mLVAdapter.notifyDataSetChanged();
//                }
//                keywords = editText.getText().toString();
//                radioButton_address = (RadioButton) group.findViewById(checkedId);
//                radio_choice = radioButton_address.getText().toString();
//                switch (radio_choice) {
//                    case "全国站":
//                        region = "0";
//                        break;
//                    case "华北":
//                        region = "2";
//                        break;
//                    case "华南":
//                        region = "3";
//                        break;
//                    case "华东":
//                        region = "1";
//                        break;
//                    case "其他":
//                        region = "4";
//                        break;
//                }
//                getNetData("1", keywords, c_type, region, true);
//                mPopupWindow2.dismiss();
//                textView_address.setTextColor(getResources().getColor(R.color.color_red));
//                textView_address.setText(radio_choice);
//            }
//        });
        //点击item判断是否消耗积分
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int rawPosition, long id) {
                try {
                    islogin = sharedUtils.getBoolean(getActivity(), "logined");
                    if (islogin) {
                        if (topBean != null) {
                            if (rawPosition == 2 || rawPosition - listView.getFirstVisiblePosition() <= 1) {
//                                Fragment_Conact.this.position = -1;
//                                userid = topBean.getUser_id();
                            } else {
                                Fragment_Conact.this.position = rawPosition - 3;
                                userid = list_more.get(position).getUser_id();
                            }
                        } else {
                            Fragment_Conact.this.position = rawPosition - 3;
                            userid = list_more.get(position).getUser_id();
                        }
                        getPersonInfoData(userid, "1", 2);
                    } else {
                        CommonDialog commonDialog = new CommonDialog();
                        commonDialog.showDialog(getActivity(), content, 4, Fragment_Conact.this);
                    }
                } catch (Exception e) {
                }
            }
        });

//        //edittext的回车监听
//        editText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
//            @Override
//            public boolean onEditorAction(TextView arg0, int arg1, KeyEvent arg2) {
//                page = 1;
//                hasMoerData = true;
//                mLVAdapter.setList(list);
//                mLVAdapter.notifyDataSetChanged();
//                if (arg1 == EditorInfo.IME_ACTION_SEARCH | (arg2 != null && arg2.getAction() == KeyEvent.ACTION_DOWN)) {
//                    keywords = (editText.getText().toString().equals("")) ? ("") : (editText.getText().toString());
//                    getNetData(page + "", keywords, c_type, region, true);
//                    search_text.setVisibility(View.GONE);
//                    textView_address.setVisibility(View.VISIBLE);
//                    zh_zj.setVisibility(View.VISIBLE);
//                    hideKey(true);
//                    return true;
//                }
//                return false;
//            }
//        });
        //edittext焦点事件
//        editText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
//            @Override
//            public void onFocusChange(View v, boolean hasFocus) {
//                try {
//                    islogin = sharedUtils.getBoolean(getActivity(), "logined");
//                    if (islogin) {
//                        if (hasFocus) {
//                            hideKey(false);
//                            showPopou(mPopupWindow3);
//                            search_text.setVisibility(View.VISIBLE);
//                            textView_address.setVisibility(View.GONE);
//                            zh_zj.setVisibility(View.GONE);
//                        }
//                    } else {
//                        CommonDialog commonDialog = new CommonDialog();
//                        commonDialog.showDialog(getActivity(), content, 4, Fragment_Conact.this);
//                    }
//                } catch (Exception e) {
//                }
//            }
//        });
//        //加载更多
//        listView.setOnScrollListener(new AbsListView.OnScrollListener() {
//            @Override
//            public void onScrollStateChanged(AbsListView view, int scrollState) {
//                if (scrollState == AbsListView.OnScrollListener.SCROLL_STATE_IDLE && listView.getCount() >= visibleItemCount) {
//                    if (view.getLastVisiblePosition() == view.getCount() - 1) {
//                        page++;
//                        islogin = sharedUtils.getBoolean(getActivity(), "logined");
//                        if (islogin || page <= 4) {
//                            getNetData(page + "", keywords, c_type, region, false);
//                        } else if (page > 4) {
//                            CommonDialog commonDialog = new CommonDialog();
//                            commonDialog.showDialog(getActivity(), content, 4, Fragment_Conact.this);
//                        }
//                    }
//                }
//            }
//
//            @Override
//            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
//                Fragment_Conact.this.visibleItemCount = visibleItemCount;
//            }
//        });
//        mScrollView.getViewTreeObserver().addOnScrollChangedListener(new ViewTreeObserver.OnScrollChangedListener() {
//            @Override
//            public void onScrollChanged() {
//                mRefreshLayout.setEnabled(mScrollView.getScrollY() == 0);
//            }
//        });
//        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
//            @Override
//            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
//                if (!mRefreshLayout.isRefreshing()) {    //如果不在刷新状态
//                    //判断是否滑动到最顶部
//                    mRefreshLayout.setEnabled(mScrollView.getScrollY() == verticalOffset);
//                }
//            }
//        });
        //填充数据
        loadCacheData(new Gson(), sharedUtils.getData(getActivity(), "txlBean"), false);
        //首次进入 请求数据
        getNetData("1", "", "0", "0", true);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getNetData("1", "", "0", "0", true);
            }
        });
        return view;
    }

    public void setPoPouProperty(CustomPopupWindow popupWindow) {
        //popupWindow.setFocusable(true);
        //popupWindow.setOutsideTouchable(true);
//        ColorDrawable dw = new ColorDrawable(0000000000);
//        popupWindow.setBackgroundDrawable(dw);
        popupWindow.setInputMethodMode(PopupWindow.INPUT_METHOD_NEEDED);//设置弹出窗体需要软键盘，
        popupWindow.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);  //再设置模式，和Activity的一样，覆盖。
    }

    public void JumpToWhere(String where) {
        if ("1".equals(where)) {        //原生
            Intent intent = new Intent(getActivity(), IntegralActivity.class);
            startActivity(intent);
        } else {                        //指定的url
            Intent intent = new Intent(getActivity(), Cover_WebActivity.class);
            intent.putExtra("url", banner_url);
            intent.putExtra("title", jumpTitle);
            startActivity(intent);
        }
    }

    @Override
    public void onClick(View v) {
        boolean logined = sharedUtils.getBoolean(getActivity(), "logined");
        if (logined) {
            switch (v.getId()) {
//                case R.id.zh_zj:
//                    keywords = editText.getText().toString();
//                    hideKey(false);
//                    showPopou(mPopupWindow1);
//                    break;
//                case R.id.address:
//                    keywords = editText.getText().toString();
//                    hideKey(false);
//                    showPopou(mPopupWindow2);
//                    break;
//                case R.id.text_wgz:
//                    Intent intent4 = new Intent(getActivity(), MyFansFollowActivity.class);
//                    intent4.putExtra("titlename", "我的关注");
//                    intent4.putExtra("type", "2");
//                    startActivity(intent4);
//                    break;
//                case R.id.text_gzw:
//                    Intent intent3 = new Intent(getActivity(), MyFansFollowActivity.class);
//                    intent3.putExtra("titlename", "我的粉丝");
//                    intent3.putExtra("type", "1");
//                    startActivity(intent3);
//                    break;
//                case R.id.txl_edit:
//                    editText.requestFocus();
//                    if (mPopupWindow1.isShowing()) {
//                        mPopupWindow1.dismiss();
//                    }
//                    if (mPopupWindow2.isShowing()) {
//                        mPopupWindow2.dismiss();
//                    }
//                    if (!mPopupWindow3.isShowing()) {
//                        showPopou(mPopupWindow3);
//                    }
//                    search_text.setVisibility(View.VISIBLE);
//                    textView_address.setVisibility(View.GONE);
//                    zh_zj.setVisibility(View.GONE);
//                    break;
                case R.id.contact_img:
                    JumpToWhere(jumpToWhere);
                    break;
                case R.id.img_tm:
                    mPopupWindow1.dismiss();
                    break;
                case R.id.img_adress:
                    mPopupWindow2.dismiss();
                    break;

//                //搜索
//                case R.id.search_src_text:
//                    page = 1;
//                    hasMoerData = true;
//                    if (list != null) {
//                        mLVAdapter.setList(list);
//                        mLVAdapter.notifyDataSetChanged();
//                    }
//                    keywords = editText.getText().toString();
//                    getNetData(page + "", keywords, c_type, region, true);
//                    search_text.setVisibility(View.GONE);
//                    textView_address.setVisibility(View.VISIBLE);
//                    zh_zj.setVisibility(View.VISIBLE);
//                    hideKey(true);
//                    break;
            }
        } else {
            CommonDialog commonDialog = new CommonDialog();
            commonDialog.showDialog(getActivity(), content, 4, Fragment_Conact.this);
        }
    }

    public void getNetData(String page, String keywords, String c_type, String region, boolean isShowDialog) {
        sharedUtils = SharedUtils.getSharedUtils();
        Map<String, String> map = new HashMap<String, String>();
        map.put("token", sharedUtils.getData(getActivity(), "token"));
        map.put("page", page);
        map.put("size", "10");
        map.put("keywords", keywords);
        map.put("c_type", c_type);
        map.put("region", region);
        BaseActivity.postAsyn1(getActivity(), API.BASEURL + API.GET_PLASTIC_PERSON, map, this, 1, isShowDialog);
    }

    public void getPersonInfoData(String userid, String showtype, int type) {
        Map<String, String> map = new HashMap<String, String>();
        map.put("token", sharedUtils.getData(getActivity(), "token"));
        map.put("user_id", userid);
        map.put("showType", showtype);
        String url = API.BASEURL + API.GET_ZONE_FRIEND;
        BaseActivity.postAsyn1(getActivity(), url, map, this, type, false);
    }

    @Override
    public void callBack(Object object, int type) {
        try {
            listView.setVisibility(View.VISIBLE);
            imageButton.setVisibility(View.GONE);
            Gson gson = new Gson();
            String err = new JSONObject(object.toString()).getString("err");
            if (type == 1) {
                if (err.equals("0")) {
                    sharedUtils.setData(getActivity(), "txlBean", object.toString());
                    loadCacheData(gson, object.toString(), true);
                }
                if (err.equals("2") || err.equals("3")) {
                    list = null;
                    TextUtils.Toast(getActivity(), new JSONObject(object.toString()).getString("msg"));
                }
                if (err.equals("1") || "998".equals(err)) {
                    sharedUtils.setData(getActivity(), "token", "");
                    sharedUtils.setData(getActivity(), "userid", "");
                    sharedUtils.setBooloean(getActivity(), "logined", false);
                }
            }
            //是否消耗积分
            if (type == 2 && err.equals("99")) {
                String content = new JSONObject(object.toString()).getString("msg");
                CommonDialog commonDialog = new CommonDialog();
                commonDialog.showDialog(getActivity(), content, 1, this);
            }
            //已经消费了积分
            if (type == 2 && err.equals("0")) {
                Intent intent = new Intent(getActivity(), PersonInfoActivity.class);
                intent.putExtra("userid", userid);
                intent.putExtra("id", userid);
                startActivity(intent);
//                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
//                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//                        startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(getActivity()
//                                , mLVAdapter.mMap.get(position)
//                                , "sharedView").toBundle());
//                    }
//                }
            }
            //减积分成功
            if (type == 3 && err.equals("0")) {
                Intent intent = new Intent(getActivity(), PersonInfoActivity.class);
                intent.putExtra("userid", userid);
                intent.putExtra("id", userid);
                startActivity(intent);
//                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
//                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//                        startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(getActivity()
//                                , mLVAdapter.mMap.get(position)
//                                , "sharedView").toBundle());
//                    }
//                }
            }
            //积分不够
            if (type == 3 && !err.equals("0")) {
                String content = new JSONObject(object.toString()).getString("msg");
                CommonDialog commonDialog = new CommonDialog();
                commonDialog.showDialog(getActivity(), content, (err.equals("100")) ? (2) : (3), this);
            }
            if (type == 10 && !err.equals("0")) {
                sharedUtils.setData(getActivity(), "token", "");
                sharedUtils.setData(getActivity(), "userid", "");
                sharedUtils.setBooloean(getActivity(), "logined", false);
                content = new JSONObject(object.toString()).getString("msg");
            }
        } catch (Exception e) {
        }
    }

    public void loadCacheData(Gson gson, String json, boolean isShowCover) {
        try {
            txlBean = gson.fromJson(json, TXL_Bean.class);
            list = txlBean.getPersons();
            //展示头部
            topBean = (new JSONObject(json).getJSONObject("top").length() != 0) ? txlBean.getTop() : (null);
            if (page == 1) {
                //rs.setText("塑料圈通讯录(" + new JSONObject(json).getString("member") + "人)");
                //editText.setHint(txlBean.getHot_search().equals("") ? "大家都在搜：" + txlBean.getHot_search() : "大家都在搜：7000F");
                //显示数据
                mLVAdapter = new Fragment_Conact_LV_Adapter(getActivity(), list, topBean);
                listView.setAdapter(mLVAdapter);
                mRefreshLayout.setRefreshing(false);
                list_more.clear();
                list_more = list;
                //展示已更新多少数据
                showRefreshPopou(txlBean.getShow_msg());
                //判断是否显示banner ；
                if (txlBean.getIs_show_banner() == 1 && isShowCover) {
                    img_url = txlBean.getBanner_url();
                    banner_url = txlBean.getBanner_jump_url();
                    jumpToWhere = txlBean.getIs_banner_jump_native() + "";
                    jumpTitle = txlBean.getBanner_jump_url_title();

                }
                //判断图层是否显示
                boolean isshow = sharedUtils.getBoolean(getActivity(), "isshow");
                if (txlBean.getIs_show_cover() == 1 && isshow) {
                    Intent intent = new Intent(getActivity(), AD_DialogActivtiy.class);
                    intent.putExtra("url", txlBean.getCover_jump_url());
                    intent.putExtra("imgurl", txlBean.getCover_url());
                    intent.putExtra("title", txlBean.getCover_jump_url_title());
                    startActivity(intent);
                }
            } else {

                list_more.addAll(list);
                mLVAdapter.setList(list_more);
                mLVAdapter.notifyDataSetChanged();
            }
        } catch (Exception e) {
        }
    }

    @Override
    public void failCallBack(int type) {
        if (list_more.size() == 0) {
            listView.setVisibility(View.GONE);
            imageButton.setVisibility(View.VISIBLE);
        }
    }

    //dialog接口回调
    @Override
    public void ok(int type) {
        switch (type) {
            case 1:
                getPersonInfoData(userid, "5", 3);
                break;
            case 2:
                startActivity(new Intent(getActivity(), IntegralPayActivtity.class));
                break;
            case 4:
                startActivity(new Intent(getActivity(), LoginActivity.class));
                break;
        }
    }

    public void hideKey(boolean hidekey) {
        if (hidekey) {
            //InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
            //imm.hideSoftInputFromWindow(editText.getWindowToken(), 0);
        }
        if (mPopupWindow1.isShowing()) {
            mPopupWindow1.dismiss();
        }
        if (mPopupWindow2.isShowing()) {
            mPopupWindow2.dismiss();
        }
        if (mPopupWindow3.isShowing()) {
            mPopupWindow3.dismiss();
        }
    }

    @Override
    public void onRefresh() {
        page = 1;
        mRefreshPopou.setCanShowPopou(true);
        getNetData("1", keywords, c_type, region, false);

    }


    //展示刷新后的popou
    public void showRefreshPopou(String text) {
        if (list.size() != 0) {
            mRefreshPopou.setCanShowPopou(false);
//            TextUtils.topTSnackbar(editText, (TextUtils.isNullOrEmpty(text)) ? (text) : ("已是最新通信录信息！"));
            if (popupWindow == null) {
                View view = View.inflate(getActivity(), R.layout.layout_refresh_popou, null);
                popupWindow = new CustomPopupWindow(view, ActionBar.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.WRAP_CONTENT);
                popupWindow.setBackgroundDrawable(new BitmapDrawable());
                popupWindow.setAnimationStyle(R.style.my_anim_popou);
                popupWindow.setOutsideTouchable(true);
                popupWindow.setFocusable(true);
                popupWindow.update();
            }
//            textView_refresh.setText((TextUtils.isNullOrEmpty(text))
//                    ? (text)
//                    : ("已是最新通信录信息！"));
//            showPopou(popupWindow);
//            if (popupWindow.isShowing()) {
//                handler.postDelayed(new Runnable() {
//                    @Override
//                    public void run() {
//                        popupWindow.dismiss();
//                    }
//                }, 1500);
//            }
        }
    }

    public void setBackgroundAlpha(float bgAlpha) {
        WindowManager.LayoutParams lp = getActivity().getWindow().getAttributes();
        lp.alpha = bgAlpha;
        getActivity().getWindow().setAttributes(lp);
    }

    public void showPopou(CustomPopupWindow popupWindow) {
//        if (android.os.Build.VERSION.SDK_INT >= 24) {
//            int[] a = new int[2];
//            editText.getLocationInWindow(a);
//            popupWindow.showAtLocation(getActivity().getWindow().getDecorView(), Gravity.NO_GRAVITY, 0, a[1] + editText.getHeight());
//        } else {
//            popupWindow.showAsDropDown(editText);
//        }
    }

    @Override
    public void onResume() {
        super.onResume();
        //统计页面，"MainScreen"为页面名称，可自定义
        MobclickAgent.onPageStart("MainScreen");
        //检查登录状态
        BaseActivity.postAsyn1(getActivity(), API.BASEURL + API.VALIDUSERTOKEN, null, this, 10, false);
    }

    public void onPause() {
        super.onPause();
        MobclickAgent.onPageEnd("MainScreen");
    }

    public boolean onKeyDown() {
        if (mPopupWindow1 != null && mPopupWindow1.isShowing()) {
            mPopupWindow1.dismiss();
            return true;
        }
        if (mPopupWindow2 != null && mPopupWindow2.isShowing()) {
            mPopupWindow2.dismiss();
            return true;
        }
        if (mPopupWindow3 != null && mPopupWindow3.isShowing()) {
//            search_text.setVisibility(View.GONE);
//            textView_address.setVisibility(View.VISIBLE);
            mPopupWindow3.dismiss();
            return true;
        }
        return false;
    }


    @Override
    public void loadMore() {
        page++;
        islogin = sharedUtils.getBoolean(getActivity(), "logined");
        if (islogin || page <= 4) {
            getNetData(page + "", keywords, c_type, region, false);
        } else if (page > 4) {
            CommonDialog commonDialog = new CommonDialog();
            commonDialog.showDialog(getActivity(), content, 4, Fragment_Conact.this);
        }
    }
}
