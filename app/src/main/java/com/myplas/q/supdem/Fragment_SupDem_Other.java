package com.myplas.q.supdem;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.myplas.q.R;
import com.myplas.q.common.api.API;
import com.myplas.q.common.appcontext.ActivityManager;
import com.myplas.q.common.netresquset.ResultCallBack;
import com.myplas.q.common.utils.SharedUtils;
import com.myplas.q.common.utils.TextUtils;
import com.myplas.q.common.view.CommonDialog;
import com.myplas.q.contact.activity.NewContactDetailActivity;
import com.myplas.q.app.activity.BaseActivity;
import com.myplas.q.app.activity.MainActivity;
import com.myplas.q.myself.integral.activity.IntegralPayActivtity;
import com.myplas.q.release.ReleaseActivity;
import com.myplas.q.supdem.activity.SupDem_Detail_Activity;
import com.myplas.q.supdem.activity.SupDem_QQ_DetailActivity;
import com.myplas.q.supdem.adapter.SupDem_LV_Adapter;
import com.myplas.q.supdem.beans.ConfigData;
import com.myplas.q.supdem.beans.SupDemBean;

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
public class Fragment_SupDem_Other extends Fragment implements CommonDialog.DialogShowInterface,
        ResultCallBack, View.OnClickListener {
    private SharedUtils sharedUtils;
    public int page, visibleItemCount, position;

    private String mLastData;
    private SupDemBean mSupDemBean;
    public String follow_release, user_id, type;
    private SupDem_LV_Adapter mSupDemLVAdapter;

    private View view;
    private ListView mListView;
    private MainActivity mainActivity;
    private LinearLayout linearLayout_prompt;
    private List<SupDemBean.DataBean> mDataBeanList;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
    }

    private void initView() {
        page = 1;
        type = "0";
        mDataBeanList = new ArrayList<>();
        sharedUtils = SharedUtils.getSharedUtils();

        view = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_layout_supdem_other, null, false);
        mListView = F(R.id.fragment_other_listview);
        linearLayout_prompt = F(R.id.supply_demand_prompt_linear);

        mListView.setHeaderDividersEnabled(false);
        mListView.setFooterDividersEnabled(false);

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                goToDetail(mDataBeanList.get(position).getC_name()
                        , mDataBeanList.get(position).getModel()
                        , mDataBeanList.get(position).getId()
                        , mDataBeanList.get(position).getUser_id()
                        , "1".equals(mDataBeanList.get(position).getFrom()));
            }
        });

        mListView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                if (scrollState == AbsListView.OnScrollListener.SCROLL_STATE_IDLE && mListView.getCount() > visibleItemCount) {
                    if (view.getLastVisiblePosition() == view.getCount() - 1) {
                        page++;
                        getNetData(page + "", false);
                    }
                }
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                Fragment_SupDem_Other.this.visibleItemCount = visibleItemCount;
            }
        });
        ConfigData.setCurrentItem(position);
        getNetData("1", false);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        return view;
    }

    public void getNetData(String page, boolean isShowLoading) {
        Map<String, String> map = new HashMap<String, String>();
        map.put("page", page);
        map.put("size", "15");
        map.put("keywords", "");
        map.put("type", type);
        map.put("sortField1", ConfigData.sortField1);
        map.put("sortField2", ConfigData.sortField2);
        map.put("token", sharedUtils.getData(getActivity(), "token"));
        BaseActivity.postAsyn1(getActivity(), API.BASEURL + API.GET_RELEASE_MSG, map, this, 1, isShowLoading);
    }

    //判断是否消耗积分
    public void getPersonInfoData(String userid, String showtype, int type) {
        Map<String, String> map = new HashMap<String, String>();
        map.put("token", sharedUtils.getData(getActivity(), "token"));
        map.put("user_id", userid);
        map.put("showType", showtype);
        String url = API.BASEURL + API.GET_ZONE_FRIEND;
        BaseActivity.postAsyn(getActivity(), url, map, this, type);
    }

    //跳转至详情
    public void goToDetail(String company, String model, String id_, String userid, boolean isFromSupdem) {
        if (isFromSupdem) {
            Intent intent = new Intent(getActivity(), SupDem_Detail_Activity.class);
            intent.putExtra("id", id_);
            intent.putExtra("userid", userid);
            startActivity(intent);
        } else {
            Intent intent = new Intent(getActivity(), SupDem_QQ_DetailActivity.class);
            intent.putExtra("company", company);
            intent.putExtra("plastic_number", model);
        }
    }

    public <T extends View> T F(int id) {
        return (T) view.findViewById(id);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.supply_demand_follow:
                if (follow_release.equals("follow")) {
                    mainActivity = (MainActivity) ActivityManager.getActivity(MainActivity.class);
                    mainActivity.firstInto();
                } else {
                    startActivity(new Intent(getActivity(), ReleaseActivity.class));
                }
                break;
            default:
                break;
        }
    }

    @Override
    public void callBack(Object object, int type) {
        try {
            Gson gson = new Gson();
            String result = new JSONObject(object.toString()).getString("err");
            if (type == 1) {
                if (result.equals("0")) {
                    mSupDemBean = gson.fromJson(object.toString(), SupDemBean.class);
                    mListView.setVisibility(View.VISIBLE);
                    linearLayout_prompt.setVisibility(View.GONE);
                    if (page == 1) {
                        mSupDemLVAdapter = new SupDem_LV_Adapter(getActivity(), mSupDemBean.getData());
                        mListView.setAdapter(mSupDemLVAdapter);
                        mDataBeanList.clear();
                        mDataBeanList.addAll(mSupDemBean.getData());
                        //加载更多
                    } else {
                        mDataBeanList.addAll(mSupDemBean.getData());
                        mSupDemLVAdapter.setList(mDataBeanList);
                        mSupDemLVAdapter.notifyDataSetChanged();
                    }

                } else {//显示提示信息：
                    if (page == 1) {
                        mListView.setVisibility(View.GONE);
                        linearLayout_prompt.setVisibility(View.VISIBLE);
                        linearLayout_prompt.removeAllViews();
                        switch (result) {
                            case "1":
                            case "998":
                                sharedUtils.setData(getActivity(), "token", "");
                                sharedUtils.setData(getActivity(), "userid", "");
                                sharedUtils.setBooloean(getActivity(), "logined", false);
                                sharedUtils.setData(getActivity(), "toast_msg", new JSONObject(object.toString()).getString("msg"));
                                break;
                            //没有更多数据
                            case "2":
                                View v = View.inflate(getActivity(), R.layout.layout_supplydemand_prompt_release2, null);
                                linearLayout_prompt.addView(v);
                                TextView text = (TextView) v.findViewById(R.id.supply_demand_text);
                                text.setText(new JSONObject(object.toString()).getString("msg"));
                                break;
                            //未匹配
                            case "4":
                                linearLayout_prompt.addView(View.inflate(getActivity(), R.layout.layout_supplydemand_prompt_release2, null));
                                break;
                            case "9"://去关注
                                follow_release = "follow";
                                View view = View.inflate(getActivity(), R.layout.layout_supplydemand_prompt_care1, null);
                                linearLayout_prompt.addView(view);
                                view.findViewById(R.id.supply_demand_follow).setOnClickListener(this);
                                break;
                            //关注 ——未发布
                            case "6":
                                linearLayout_prompt.addView(View.inflate(getActivity(), R.layout.layout_supplydemand_prompt_care2, null));
                                break;
                            //职能推荐——区发布
                            case "7":
                                follow_release = "release";
                                View view3 = View.inflate(getActivity(), R.layout.layout_supplydemand_prompt_release1, null);
                                linearLayout_prompt.addView(view3);
                                TextView t = (TextView) view3.findViewById(R.id.supply_demand_text);
                                t.setText(new JSONObject(object.toString()).getString("msg"));
                                view3.findViewById(R.id.supply_demand_follow).setOnClickListener(this);
                                view3.findViewById(R.id.img_supplydemad_down).setVisibility(View.VISIBLE);
                                break;
                            //我的供求——去发布
                            case "8":
                                follow_release = "release";
                                View view2 = View.inflate(getActivity(), R.layout.layout_supplydemand_prompt_release1, null);
                                linearLayout_prompt.addView(view2);
                                TextView textView = (TextView) view2.findViewById(R.id.supply_demand_text);
                                textView.setText(new JSONObject(object.toString()).getString("msg"));
                                view2.findViewById(R.id.supply_demand_follow).setOnClickListener(this);
                                view2.findViewById(R.id.img_supplydemad_down).setVisibility(View.GONE);
                                break;
                            default:
                                break;
                        }
                    } else {
                        TextUtils.toast(getContext(), "没有更多数据了！");
                    }
                }
            }
            //是否消耗积分
            if (type == 2 && result.equals("99")) {
                String content = new JSONObject(object.toString()).getString("msg");
                CommonDialog commonDialog = new CommonDialog();
                commonDialog.showDialog(getActivity(), content, 1, this);
            }
            //已经消费了积分
            if (type == 2 && result.equals("0")) {
                Intent intent = new Intent(getActivity(), NewContactDetailActivity.class);
                intent.putExtra("userid", user_id);
                intent.putExtra("id", user_id);
                startActivity(intent);
            }
            //减积分成功
            if (type == 3 && result.equals("0")) {
                Intent intent = new Intent(getActivity(), NewContactDetailActivity.class);
                intent.putExtra("userid", user_id);
                intent.putExtra("id", user_id);
                startActivity(intent);

            }
            //积分不够
            if (type == 3 && !result.equals("0")) {
                String content = new JSONObject(object.toString()).getString("msg");
                CommonDialog commonDialog = new CommonDialog();
                commonDialog.showDialog(getActivity(), content, (result.equals("100")) ? (2) : (3), this);
            }
        } catch (Exception e) {
        }
    }

    @Override
    public void failCallBack(int type) {
        if (mDataBeanList.size() == 0) {
            mListView.setVisibility(View.GONE);
            linearLayout_prompt.setVisibility(View.VISIBLE);
            linearLayout_prompt.removeAllViews();

            ImageButton imageButton = new ImageButton(getActivity());
            imageButton.setImageResource(R.drawable.img_reload);
            linearLayout_prompt.addView(imageButton);
            imageButton.setBackgroundColor(getResources().getColor(R.color.color_white));
            imageButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    page = 1;
                    getNetData("1", true);
                }
            });
        }
    }

    //dialog回调
    @Override
    public void dialogClick(int type) {
        switch (type) {
            case 1:
                getPersonInfoData(user_id, "5", 3);
                break;
            case 2:
                startActivity(new Intent(getActivity(), IntegralPayActivtity.class));
                break;
            default:
                break;
        }
    }

}

