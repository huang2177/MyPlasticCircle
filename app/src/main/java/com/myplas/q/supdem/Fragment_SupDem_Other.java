package com.myplas.q.supdem;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.myplas.q.R;
import com.myplas.q.app.fragment.BaseFragment;
import com.myplas.q.common.api.API;
import com.myplas.q.common.appcontext.ActivityManager;
import com.myplas.q.common.listener.MyOnItemClickListener;
import com.myplas.q.common.netresquset.ResultCallBack;
import com.myplas.q.common.utils.ContactAccessUtils;
import com.myplas.q.common.utils.SharedUtils;
import com.myplas.q.common.utils.TextUtils;
import com.myplas.q.common.view.CommonDialog;
import com.myplas.q.contact.activity.NewContactDetailActivity;
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
public class Fragment_SupDem_Other extends BaseFragment implements
        ResultCallBack, View.OnClickListener, MyOnItemClickListener {
    private SharedUtils sharedUtils;
    public int page, visibleItemCount, position;

    private String mLastData;
    private SupDemBean mSupDemBean;
    private SupDem_LV_Adapter mSupDemLVAdapter;
    public String follow_release, user_id, type, mergeThere;

    private View view;
    private ListView mListView;
    private MainActivity mainActivity;
    private LinearLayout linearLayout_prompt;
    private List<SupDemBean.DataBean> mDataBeanList;

    private ContactAccessUtils utils;

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
        utils = new ContactAccessUtils(getActivity());

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
        getAsyn(getActivity(), API.RELEASE_MSG, map, this, 1, isShowLoading);
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
            String result = new JSONObject(object.toString()).getString("code");
            if (type == 1) {
                if (result.equals("0")) {
                    mSupDemBean = gson.fromJson(object.toString(), SupDemBean.class);
                    mListView.setVisibility(View.VISIBLE);
                    linearLayout_prompt.setVisibility(View.GONE);
                    if (page == 1) {
                        mSupDemLVAdapter = new SupDem_LV_Adapter(getActivity(), mSupDemBean.getData());
                        mSupDemLVAdapter.setListener(this);
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

                    } else {
                        TextUtils.toast(getContext(), "没有更多数据了！");
                    }
                }
            }
        } catch (Exception e) {
        }
    }

    @Override
    public void failCallBack(int type, String message, int httpCode) {
        try {
            if (type == 1) {
                String result = new JSONObject(message).getString("code");
                String msg = new JSONObject(message).getString("message");
                if (mDataBeanList.size() == 0) {
                    mListView.setVisibility(View.GONE);
                    linearLayout_prompt.setVisibility(View.VISIBLE);
                    linearLayout_prompt.removeAllViews();

//                    ImageButton imageButton = new ImageButton(getActivity());
//                    imageButton.setImageResource(R.drawable.img_reload);
//                    linearLayout_prompt.addView(imageButton);
//                    imageButton.setBackgroundColor(getResources().getColor(R.color.color_white));
//                    imageButton.setOnClickListener(new View.OnClickListener() {
//                        @Override
//                        public void onClick(View v) {
//                            page = 1;
//                            getNetData("1", true);
//                        }
//                    });
                    switch (httpCode) {
                        case 404:
                            switch (result) {
                                //没有更多数据
                                case "3":
                                    View v = View.inflate(getActivity(), R.layout.layout_supplydemand_prompt_release2, null);
                                    linearLayout_prompt.addView(v);
                                    TextView text = (TextView) v.findViewById(R.id.supply_demand_text);
                                    text.setText(msg);
                                    break;
                                //未匹配
                                case "26":
                                    linearLayout_prompt.addView(View.inflate(getActivity(), R.layout.layout_supplydemand_prompt_release2, null));
                                    break;
                                case "23"://去关注
                                    follow_release = "follow";
                                    View view = View.inflate(getActivity(), R.layout.layout_supplydemand_prompt_care1, null);
                                    linearLayout_prompt.addView(view);
                                    view.findViewById(R.id.supply_demand_follow).setOnClickListener(this);
                                    break;
                                //关注 ——未发布
                                case "27":
                                    linearLayout_prompt.addView(View.inflate(getActivity(), R.layout.layout_supplydemand_prompt_care2, null));
                                    break;
                                //职能推荐——区发布
                                case "24":
                                    follow_release = "release";
                                    View view3 = View.inflate(getActivity(), R.layout.layout_supplydemand_prompt_release1, null);
                                    linearLayout_prompt.addView(view3);
                                    TextView t = (TextView) view3.findViewById(R.id.supply_demand_text);
                                    t.setText(msg);
                                    view3.findViewById(R.id.supply_demand_follow).setOnClickListener(this);
                                    view3.findViewById(R.id.img_supplydemad_down).setVisibility(View.VISIBLE);
                                    break;
                                //我的供求——去发布
                                case "28":
                                    follow_release = "release";
                                    View view2 = View.inflate(getActivity(), R.layout.layout_supplydemand_prompt_release1, null);
                                    linearLayout_prompt.addView(view2);
                                    TextView textView = (TextView) view2.findViewById(R.id.supply_demand_text);
                                    textView.setText(msg);
                                    view2.findViewById(R.id.supply_demand_follow).setOnClickListener(this);
                                    view2.findViewById(R.id.img_supplydemad_down).setVisibility(View.GONE);
                                    break;
                                default:
                                    break;
                            }
                            break;
                        case 401:
                            if ("1".equals(result)) {
                                sharedUtils.setData(getActivity(), "token", "");
                                sharedUtils.setData(getActivity(), "userid", "");
                                sharedUtils.setBooloean(getActivity(), "logined", false);
                                sharedUtils.setData(getActivity(), "toast_msg", msg);
                            }
                            break;
                        default:
                            break;
                    }

                }
            }
        } catch (Exception e) {

        }
    }

    @Override
    public void onItemClick(String userId, String flag, String id, String pur_id, String user_id) {
        utils.checkPremissions(userId, flag);
    }
}

