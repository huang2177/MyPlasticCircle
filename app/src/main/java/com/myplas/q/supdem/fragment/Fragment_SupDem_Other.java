package com.myplas.q.supdem.fragment;

import android.app.Activity;
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
import com.myplas.q.guide.activity.BaseActivity;
import com.myplas.q.common.api.API;
import com.myplas.q.common.netresquset.ResultCallBack;
import com.myplas.q.common.utils.DialogShowUtils;
import com.myplas.q.common.utils.SharedUtils;
import com.myplas.q.common.utils.TextUtils;
import com.myplas.q.myinfo.integral.activity.IntegralPayActivtity;
import com.myplas.q.myinfo.fans.activity.PersonInfoActivity;
import com.myplas.q.release.activity.ReleaseActivity;
import com.myplas.q.guide.activity.MainActivity;
import com.myplas.q.supdem.Beans.ItemBean;
import com.myplas.q.supdem.Beans.Supply_DemandBean;
import com.myplas.q.supdem.activity.SupDem_Detail_Activity;
import com.myplas.q.supdem.adapter.GQ_ListviewAdapter;

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
public class Fragment_SupDem_Other extends Fragment implements DialogShowUtils.DialogShowInterface,
        ResultCallBack, View.OnClickListener {
    public int visibleItemCount;
    private SharedUtils sharedUtils;

    public String follow_release, user_id;
    private Supply_DemandBean supply_demandBean;
    private GQ_ListviewAdapter gq_listviewAdapter;

    private View view;
    public ItemBean itemBean;
    private ListView listView;
    private LinearLayout linearLayout_prompt;
    private List<Supply_DemandBean.DataBean> list, list_more;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        view = LayoutInflater.from(getActivity()).inflate(R.layout.layout_fragment_other, null, false);
        list_more = new ArrayList<>();
        itemBean=ItemBean.getItemBean();
        listView = (ListView) view.findViewById(R.id.fragment_other_listview);
        linearLayout_prompt = (LinearLayout) view.findViewById(R.id.supply_demand_prompt_linear);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                try {
                    Intent intent = new Intent(getActivity(), SupDem_Detail_Activity.class);
                    String id_ = list_more.get(position).getId();
                    String userid = list_more.get(position).getUser_id();
                    String user_id = sharedUtils.getData(getActivity(), "userid");

                    intent.putExtra("id", id_);
                    intent.putExtra("type", "0");
                    intent.putExtra("userid", userid);
                    intent.putExtra("what", (user_id.equals(userid))?("5"):(itemBean.what));

                    startActivity(intent);
                } catch (Exception e) {
                }
            }
        });

        listView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                if (scrollState == AbsListView.OnScrollListener.SCROLL_STATE_IDLE && listView.getCount() >= visibleItemCount) {
                    if (view.getLastVisiblePosition() == view.getCount() - 1) {
                        itemBean.page++;
                        if (itemBean.hasMoreData) {
                            getNetData(getActivity(), itemBean.page + "", itemBean.keywords, itemBean.sortField1, itemBean.sortField2, itemBean.type, false);
                        } else {
                            TextUtils.Toast(getActivity(), "没有更多数据了！");
                        }
                    }
                }
            }
            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
            }
        });
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        return view;
    }
    public void getNetData(Activity context, String page, String keywords, String sortField1, String sortField2, String type, boolean a) {
        try {
            Map<String, String> map = new HashMap<String, String>();
            sharedUtils = SharedUtils.getSharedUtils();
            map.put("token", sharedUtils.getData(getActivity(), "token"));
            map.put("page", page);
            map.put("size", "15");
            map.put("keywords", keywords);
            map.put("sortField1", sortField1);
            map.put("sortField2", sortField2);
            map.put("type", type);
            BaseActivity.postAsyn1(context, API.BASEURL + API.GET_RELEASE_MSG, map, this, 1, a);
        } catch (Exception e) {
        }
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

    public <T extends View> T F(int id) {
        return (T) view.findViewById(id);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.supply_demand_follow:
                if (follow_release.equals("follow")) {
                    MainActivity.firstInto();
                } else if (follow_release.equals("release")) {
                    startActivity(new Intent(getActivity(), ReleaseActivity.class));
                }
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
                    supply_demandBean = gson.fromJson(object.toString(), Supply_DemandBean.class);
                    listView.setVisibility(View.VISIBLE);
                    linearLayout_prompt.setVisibility(View.GONE);
                    list = supply_demandBean.getData();
                    if (itemBean.page == 1) {
                        gq_listviewAdapter = new GQ_ListviewAdapter(itemBean.what, getActivity(), list);
                        listView.setAdapter(gq_listviewAdapter);
                        list_more = list;
                        list = null;
                        //加载更多
                    } else if (list.size() != 0) {
                        list_more.addAll(list);
                        gq_listviewAdapter.setList(list_more);
                        gq_listviewAdapter.notifyDataSetChanged();
                    }

                } else {//显示提示信息：
                    itemBean.hasMoreData = false;
                    if (itemBean.page == 1) {
                        listView.setVisibility(View.GONE);
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
                        }
                    }
                }
            }
            //是否消耗积分
            if (type == 2 && result.equals("99")) {
                String content = new JSONObject(object.toString()).getString("msg");
                DialogShowUtils dialogShowUtils = new DialogShowUtils();
                dialogShowUtils.showDialog(getActivity(), content, 1, this);
            }
            //已经消费了积分
            if (type == 2 && result.equals("0")) {
                Intent intent = new Intent(getActivity(), PersonInfoActivity.class);
                intent.putExtra("userid", user_id);
                intent.putExtra("id", user_id);
                startActivity(intent);
            }
            //减积分成功
            if (type == 3 && result.equals("0")) {
                Intent intent = new Intent(getActivity(), PersonInfoActivity.class);
                intent.putExtra("userid", user_id);
                intent.putExtra("id", user_id);
                startActivity(intent);

            }
            //积分不够
            if (type == 3 && !result.equals("0")) {
                String content = new JSONObject(object.toString()).getString("msg");
                DialogShowUtils dialogShowUtils = new DialogShowUtils();
                dialogShowUtils.showDialog(getActivity(), content, (result.equals("100")) ? (2) : (3), this);
            }
        } catch (Exception e) {
        }
    }

    @Override
    public void failCallBack(int type) {
        if (list_more.size()==0) {
            listView.setVisibility(View.GONE);
            linearLayout_prompt.setVisibility(View.VISIBLE);
            linearLayout_prompt.removeAllViews();

            ImageButton imageButton=new ImageButton(getActivity());
            imageButton.setImageResource(R.drawable.img_reload);
            linearLayout_prompt.addView(imageButton);
            imageButton.setBackgroundColor(getResources().getColor(R.color.color_white));
            imageButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    itemBean = ItemBean.getItemBean();
                    itemBean.page = 1;
                    itemBean.hasMoreData = true;
                    getNetData(getActivity(), "1", itemBean.keywords, itemBean.sortField1, itemBean.sortField2, itemBean.type, true);
                }
            });
        }
    }

    //dialog回调
    @Override
    public void ok(int type) {
        switch (type) {
            case 1:
                getPersonInfoData(user_id, "5", 3);
                break;
            case 2:
                startActivity(new Intent(getActivity(), IntegralPayActivtity.class));
                break;
        }
    }

}

