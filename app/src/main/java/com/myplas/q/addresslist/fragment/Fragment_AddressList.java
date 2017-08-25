package com.myplas.q.addresslist.fragment;

import android.app.ActionBar;
import android.content.Context;
import android.content.Intent;
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
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
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
import com.myplas.q.addresslist.adapter.AddressList_Listview_Adapter;
import com.myplas.q.common.api.API;
import com.myplas.q.common.netresquset.ResultCallBack;
import com.myplas.q.common.utils.DialogShowUtils;
import com.myplas.q.common.utils.SharedUtils;
import com.myplas.q.common.utils.TextUtils;
import com.myplas.q.common.view.CustomPopupWindow;
import com.myplas.q.common.view.PinnedHeaderListView;
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
public class Fragment_AddressList extends Fragment implements View.OnClickListener
        , ResultCallBack, PinnedHeaderListView.IXListViewListener, DialogShowUtils.DialogShowInterface {
    public String content;
    private SharedUtils sharedUtils;
    private int page, position, visibleItemCount;
    private boolean islogin, hasMoerData = true, isRefresh = false;
    private String radio_choice, userid, keywords = "", region = "0", c_type = "0", banner_url, img_url, jumpToWhere, jumpTitle;

    private TXL_Bean.TopBean topBean;
    private List<TXL_Bean.PersonsBean> list, list_more;
    private AddressList_Listview_Adapter txl_listview_adapter;

    private Handler handler;
    private TXL_Bean txlBean;
    private EditText editText;
    private ImageButton imageButton;
    private CustomPopupWindow popupWindow;
    private PinnedHeaderListView listView;
    private ImageView imageView_intergral, jia;
    private RadioGroup radioGroup_address, radioGroup_time;
    private RadioButton radioButton_address, radioButton_time;
    private View view, viewheader, imageView_tm, imageView_adress;
    public CustomPopupWindow mPopupWindow1, mPopupWindow2, mPopupWindow3;
    private TextView rs, zh_zj, wgz, gzw, textView_address, search_text, textView_refresh;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        page = 1;
        region = "0";
        c_type = "0";
        keywords = "";
        hasMoerData = true;
        list_more = new ArrayList();
        content = "您还未登录,请先登录塑料圈!";

        handler = new Handler();
        sharedUtils = SharedUtils.getSharedUtils();
        islogin = sharedUtils.getBoolean(getActivity(), "logined");
        view = LayoutInflater.from(getActivity()).inflate(R.layout.layout_addresslist_fragment, null, false);

        zh_zj = (TextView) view.findViewById(R.id.zh_zj);
        rs = (TextView) view.findViewById(R.id.title_rs);
        jia = (ImageView) view.findViewById(R.id.title_jia);
        editText = (EditText) view.findViewById(R.id.txl_edit);
        imageButton = (ImageButton) view.findViewById(R.id.img_reload);
        textView_address = (TextView) view.findViewById(R.id.address);
        search_text = (TextView) view.findViewById(R.id.search_src_text);
        listView = (PinnedHeaderListView) view.findViewById(R.id.txl_listview);
        viewheader = View.inflate(getActivity(), R.layout.layout_contact_listview_header, null);

        editText.setText("");
        listView.addHeaderView(viewheader);

        zh_zj.setOnClickListener(this);
        editText.setOnClickListener(this);
        listView.setXListViewListener(this);
        listView.setPullRefreshEnable(true);
        search_text.setOnClickListener(this);
        textView_address.setOnClickListener(this);

        //排序
        View view1 = LayoutInflater.from(getActivity()).inflate(R.layout.txl_dropdown_popouwindow, null, false);
        mPopupWindow1 = new CustomPopupWindow(view1, ActionBar.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.MATCH_PARENT);
        setPoPouProperty(mPopupWindow1, true);
        imageView_tm = (View) view1.findViewById(R.id.img_tm);
        radioGroup_time = (RadioGroup) view1.findViewById(R.id.radio_danxuan);
        imageView_tm.setOnClickListener(this);
        //地区
        View view2 = LayoutInflater.from(getActivity()).inflate(R.layout.txl_dropdown_popouwindow_address, null, false);
        mPopupWindow2 = new CustomPopupWindow(view2, ActionBar.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.MATCH_PARENT);
        setPoPouProperty(mPopupWindow2, true);
        imageView_adress = (View) view2.findViewById(R.id.img_adress);
        imageView_adress.setOnClickListener(this);
        radioGroup_address = (RadioGroup) view2.findViewById(R.id.radio_danxuan);
        //search
        View view3 = LayoutInflater.from(getActivity()).inflate(R.layout.layout_address_search_popou, null, false);
        mPopupWindow3 = new CustomPopupWindow(view3, ActionBar.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.MATCH_PARENT);
        setPoPouProperty(mPopupWindow3, false);
        view3.findViewById(R.id.view_search).setOnClickListener(this);
        radioGroup_time.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                page = 1;
                hasMoerData = true;
                if (list != null) {
                    txl_listview_adapter.setList(list);
                    txl_listview_adapter.notifyDataSetChanged();
                }
                keywords = editText.getText().toString();
                radioButton_time = (RadioButton) group.findViewById(checkedId);
                radio_choice = radioButton_time.getText().toString();
                switch (radio_choice) {
                    case "所有分类":
                        c_type = "0";
                        break;
                    case "塑料制品厂":
                        c_type = "1";
                        break;
                    case "原料供应商":
                        c_type = "2";
                        break;
                    case "物流服务商":
                        c_type = "4";
                        break;
                    case "其他":
                        c_type = "5";
                        break;
                }
                getNetData("1", keywords, c_type, region, true);
                mPopupWindow1.dismiss();
                zh_zj.setTextColor(getResources().getColor(R.color.color_red));
                zh_zj.setText("  " + radio_choice);
            }
        });
        radioGroup_address.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                page = 1;
                hasMoerData = true;
                if (list != null) {
                    txl_listview_adapter.setList(list);
                    txl_listview_adapter.notifyDataSetChanged();
                }
                keywords = editText.getText().toString();
                radioButton_address = (RadioButton) group.findViewById(checkedId);
                radio_choice = radioButton_address.getText().toString();
                switch (radio_choice) {
                    case "全国站":
                        region = "0";
                        break;
                    case "华北":
                        region = "2";
                        break;
                    case "华南":
                        region = "3";
                        break;
                    case "华东":
                        region = "1";
                        break;
                    case "其他":
                        region = "4";
                        break;
                }
                getNetData("1", keywords, c_type, region, true);
                mPopupWindow2.dismiss();
                textView_address.setTextColor(getResources().getColor(R.color.color_red));
                textView_address.setText("  " + radio_choice);
            }
        });
        //点击item判断是否消耗积分
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int rawPosition, long id) {
                try {
                    islogin = sharedUtils.getBoolean(getActivity(), "logined");
                    if (islogin) {
                        if (topBean != null) {
                            if (rawPosition == 2 || rawPosition - listView.getFirstVisiblePosition() <= 1) {
                                Fragment_AddressList.this.position = -1;
                                userid = topBean.getUser_id();
                            } else {
                                Fragment_AddressList.this.position = rawPosition - 3;
                                userid = list_more.get(position).getUser_id();
                            }
                        } else {
                            Fragment_AddressList.this.position = rawPosition - 3;
                            userid = list_more.get(position).getUser_id();
                        }
                        getPersonInfoData(userid, "1", 2);
                    } else {
                        DialogShowUtils dialogShowUtils = new DialogShowUtils();
                        dialogShowUtils.showDialog(getActivity(), content, 4, Fragment_AddressList.this);
                    }
                } catch (Exception e) {
                }
            }
        });

        //edittext的回车监听
        editText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView arg0, int arg1, KeyEvent arg2) {
                page = 1;
                hasMoerData = true;
                txl_listview_adapter.setList(list);
                txl_listview_adapter.notifyDataSetChanged();
                if (arg1 == EditorInfo.IME_ACTION_SEARCH | (arg2 != null && arg2.getAction() == KeyEvent.ACTION_DOWN)) {
                    keywords = (editText.getText().toString().equals("")) ? ("") : (editText.getText().toString());
                    getNetData(page + "", keywords, c_type, region, true);
                    search_text.setVisibility(View.GONE);
                    textView_address.setVisibility(View.VISIBLE);
                    zh_zj.setVisibility(View.VISIBLE);
                    hideKey(true);
                    return true;
                }
                return false;
            }
        });
        //edittext焦点事件
        editText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                try {
                    islogin = sharedUtils.getBoolean(getActivity(), "logined");
                    if (islogin) {
                        if (hasFocus) {
                            hideKey(false);
                            showPopou(mPopupWindow3);
                            search_text.setVisibility(View.VISIBLE);
                            textView_address.setVisibility(View.GONE);
                            zh_zj.setVisibility(View.GONE);
                        }
                    } else {
                        DialogShowUtils dialogShowUtils = new DialogShowUtils();
                        dialogShowUtils.showDialog(getActivity(), content, 4, Fragment_AddressList.this);
                    }
                } catch (Exception e) {
                }
            }
        });
        //加载更多
        listView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                if (scrollState == AbsListView.OnScrollListener.SCROLL_STATE_IDLE && listView.getCount() > visibleItemCount) {
                    if (view.getLastVisiblePosition() == view.getCount() - 1) {
                        page++;
                        islogin = sharedUtils.getBoolean(getActivity(), "logined");
                        if (islogin || page <= 4) {
                            if (hasMoerData) {
                                getNetData(page + "", keywords, c_type, region, false);
                            } else {
                                TextUtils.Toast(getActivity(), "没有更多数据了！");
                            }
                        } else if (page > 4) {
                            DialogShowUtils dialogShowUtils = new DialogShowUtils();
                            dialogShowUtils.showDialog(getActivity(), content, 4, Fragment_AddressList.this);
                        }
                    }
                }
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                Fragment_AddressList.this.visibleItemCount = visibleItemCount;
            }
        });
        //填充数据
        parse_ShowData(new Gson(), sharedUtils.getData(getActivity(), "txlBean"), false);
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
        jia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                if (SharedUtils.getSharedUtils().getBoolean(getActivity(), "logined")) {
//                    TextUtils.Toast(getActivity(), "您已登录塑料圈通讯录！");
//                } else {
//                    Intent in = new Intent(getActivity(), LoginActivity.class);
//                    startActivity(in);
//                }
                Intent in = new Intent(getActivity(), LoginActivity.class);
                startActivity(in);
            }
        });
        return view;
    }

    public void setPoPouProperty(CustomPopupWindow popupWindow, boolean b) {
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
                case R.id.zh_zj:
                    keywords = editText.getText().toString();
                    hideKey(false);
                    showPopou(mPopupWindow1);
                    break;
                case R.id.address:
                    keywords = editText.getText().toString();
                    hideKey(false);
                    showPopou(mPopupWindow2);
                    break;
                case R.id.text_wgz:
                    Intent intent4 = new Intent(getActivity(), MyFansFollowActivity.class);
                    intent4.putExtra("titlename", "我的关注");
                    intent4.putExtra("type", "2");
                    startActivity(intent4);
                    break;
                case R.id.text_gzw:
                    Intent intent3 = new Intent(getActivity(), MyFansFollowActivity.class);
                    intent3.putExtra("titlename", "我的粉丝");
                    intent3.putExtra("type", "1");
                    startActivity(intent3);
                    break;
                case R.id.txl_edit:
                    editText.requestFocus();
                    if (mPopupWindow1.isShowing()) {
                        mPopupWindow1.dismiss();
                    }
                    if (mPopupWindow2.isShowing()) {
                        mPopupWindow2.dismiss();
                    }
                    if (!mPopupWindow3.isShowing()) {
                        showPopou(mPopupWindow3);
                    }
                    search_text.setVisibility(View.VISIBLE);
                    textView_address.setVisibility(View.GONE);
                    zh_zj.setVisibility(View.GONE);
                    break;
                case R.id.contact_img:
                    JumpToWhere(jumpToWhere);
                    break;
                case R.id.img_tm:
                    mPopupWindow1.dismiss();
                    break;
                case R.id.img_adress:
                    mPopupWindow2.dismiss();
                    break;

                case R.id.view_search:
                    search_text.setVisibility(View.GONE);
                    textView_address.setVisibility(View.VISIBLE);
                    zh_zj.setVisibility(View.VISIBLE);
                    hideKey(true);
                    break;
                //搜索
                case R.id.search_src_text:
                    page = 1;
                    hasMoerData = true;
                    if (list != null) {
                        txl_listview_adapter.setList(list);
                        txl_listview_adapter.notifyDataSetChanged();
                    }
                    keywords = editText.getText().toString();
                    getNetData(page + "", keywords, c_type, region, true);
                    search_text.setVisibility(View.GONE);
                    textView_address.setVisibility(View.VISIBLE);
                    zh_zj.setVisibility(View.VISIBLE);
                    hideKey(true);
                    break;
            }
        } else {
            DialogShowUtils dialogShowUtils = new DialogShowUtils();
            dialogShowUtils.showDialog(getActivity(), content, 4, Fragment_AddressList.this);
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
        BaseActivity.postAsyn(getActivity(), url, map, this, type);
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
                    parse_ShowData(gson, object.toString(), true);
                }
                if (err.equals("2") || err.equals("3")) {
                    list = null;
                    isRefresh = false;
                    hasMoerData = false;
                    listView.stopRefresh();
                    TextUtils.Toast(getActivity(), new JSONObject(object.toString()).getString("msg"));
                }
                if (err.equals("1") || "998".equals(err)) {
                    listView.stopRefresh();
                    sharedUtils.setBooloean(getActivity(), "logined", false);
                    sharedUtils.setData(getActivity(), "token", "");
                    sharedUtils.setData(getActivity(), "userid", "");
                }
            }
            //是否消耗积分
            if (type == 2 && err.equals("99")) {
                String content = new JSONObject(object.toString()).getString("msg");
                DialogShowUtils dialogShowUtils = new DialogShowUtils();
                dialogShowUtils.showDialog(getActivity(), content, 1, this);
            }
            //已经消费了积分
            if (type == 2 && err.equals("0")) {
                Intent intent = new Intent(getActivity(), PersonInfoActivity.class);
                intent.putExtra("userid", userid);
                intent.putExtra("id", userid);
                startActivity(intent);
            }
            //减积分成功
            if (type == 3 && err.equals("0")) {
                Intent intent = new Intent(getActivity(), PersonInfoActivity.class);
                intent.putExtra("userid", userid);
                intent.putExtra("id", userid);
                startActivity(intent);

            }
            //积分不够
            if (type == 3 && !err.equals("0")) {
                String content = new JSONObject(object.toString()).getString("msg");
                DialogShowUtils dialogShowUtils = new DialogShowUtils();
                dialogShowUtils.showDialog(getActivity(), content, (err.equals("100")) ? (2) : (3), this);
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

    public void parse_ShowData(Gson gson, String json, boolean isShowCover) {
        try {
            txlBean = gson.fromJson(json, TXL_Bean.class);
            list = txlBean.getPersons();
            //展示头部
            topBean = (new JSONObject(json).getJSONObject("top").length() != 0) ? txlBean.getTop() : (null);
            if (page == 1) {
                rs.setText("塑料圈通讯录(" + new JSONObject(json).getString("member") + "人)");
                //显示数据
                txl_listview_adapter = new AddressList_Listview_Adapter(getActivity(), list, topBean);
                listView.setAdapter(txl_listview_adapter);
                listView.stopRefresh();
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

                    viewheader.findViewById(R.id.linearlayout_care_fans).setVisibility(View.GONE);
                    viewheader.findViewById(R.id.contact_img).setVisibility(View.VISIBLE);
                    imageView_intergral = (ImageView) viewheader.findViewById(R.id.contact_img);
                    Glide.with(getContext()).load(img_url).into(imageView_intergral);
                    imageView_intergral.setOnClickListener(this);
                } else {
                    viewheader.findViewById(R.id.linearlayout_care_fans).setVisibility(View.VISIBLE);
                    viewheader.findViewById(R.id.contact_img).setVisibility(View.GONE);
                    wgz = (TextView) viewheader.findViewById(R.id.text_wgz);
                    gzw = (TextView) viewheader.findViewById(R.id.text_gzw);
                    wgz.setOnClickListener(this);
                    gzw.setOnClickListener(this);
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
                isRefresh = false;
                listView.stopLoadMore();
                list_more.addAll(list);
                txl_listview_adapter.setList(list_more);
                txl_listview_adapter.notifyDataSetChanged();
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
            InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(editText.getWindowToken(), 0);
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
        isRefresh = true;
        hasMoerData = true;
        getNetData("1", keywords, c_type, region, false);

    }

    @Override
    public void onLoadMore() {
    }

    //展示刷新后的popou
    public void showRefreshPopou(String text) {
        if (list.size() != 0 && isRefresh) {
            isRefresh = false;
            if (TextUtils.isNullOrEmpty(text)) {
                View view = View.inflate(getActivity(), R.layout.layout_refresh_popou, null);
                textView_refresh = (TextView) view.findViewById(R.id.text_refresh_fragement);
                popupWindow = new CustomPopupWindow(view, ActionBar.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.MATCH_PARENT);
                popupWindow.setFocusable(true);
                popupWindow.setOutsideTouchable(true);
                textView_refresh.setText(text);
                showPopou(popupWindow);
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        popupWindow.dismiss();
                    }
                }, 1500);
            } else {
                TextUtils.Toast(getActivity(), "已是最新通信录信息！");
            }
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

    @Override
    public void onResume() {
        super.onResume();
        //统计页面，"MainScreen"为页面名称，可自定义
        MobclickAgent.onPageStart("MainScreen");
        //检查登录状态
        BaseActivity.postAsyn(getActivity(), API.BASEURL + API.VALIDUSERTOKEN, null, this, 10);
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
            search_text.setVisibility(View.GONE);
            textView_address.setVisibility(View.VISIBLE);
            zh_zj.setVisibility(View.VISIBLE);
            mPopupWindow3.dismiss();
            return true;
        }
        return false;
    }
}
