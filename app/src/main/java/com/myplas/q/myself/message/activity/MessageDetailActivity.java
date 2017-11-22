package com.myplas.q.myself.message.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.google.gson.Gson;
import com.myplas.q.R;
import com.myplas.q.common.utils.TextUtils;
import com.myplas.q.common.view.EmptyView;
import com.myplas.q.guide.activity.BaseActivity;
import com.myplas.q.common.netresquset.ResultCallBack;
import com.myplas.q.myself.beans.MsgChJBean;
import com.myplas.q.myself.beans.MsgHFBean;
import com.myplas.q.myself.beans.MsgSupDemBean;
import com.myplas.q.myself.message.adapter.MessageCHJAdapter;
import com.myplas.q.myself.message.adapter.MessageHFAdapter;
import com.myplas.q.common.api.API;
import com.myplas.q.myself.message.adapter.MessageSupDemAdapter;
import com.myplas.q.sockethelper.RabbitMQConfig;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 编写： 黄双
 * 电话：15378412400
 * 邮箱：15378412400@163.com
 * 时间：2017/3/23 16:22
 */
public class MessageDetailActivity extends BaseActivity implements ResultCallBack {
    private int page, count;
    private String method, title;
    private List<MsgHFBean.DataBean> mListHF;
    private List<MsgChJBean.DataBean> mListChJ;
    private List<MsgSupDemBean.DataBean> mListSupDem;

    private EmptyView mEmptyView;
    private RecyclerView mRecyclerView;
    private MessageHFAdapter mHFAdapter;
    private MessageCHJAdapter mCHJAdapter;
    private MessageSupDemAdapter mSupDemAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_layout_myself_msg);
        initTileBar();
        title = getIntent().getStringExtra("title");
        setTitle(title);
        initView();
        getMyMsg("1", title);

    }

    private void initView() {
        page = 1;
        mListHF = new ArrayList<>();
        mListChJ = new ArrayList<>();
        mListSupDem = new ArrayList<>();
        mRecyclerView = F(R.id.wd_gj_listview);
        mEmptyView = F(R.id.mysupdem_noresultlayout);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);//设置为一个1列的纵向网格布局
        mRecyclerView.setLayoutManager(mLayoutManager);
    }


    public void getMyMsg(String page, String title) {
        int type;
        if (title.equals("供求消息")) {
            type = 1;
            method = API.PLASTICMSG;
        } else if (title.equals("出价消息")) {
            type = 2;
            method = API.CHUJIAMSG;
        } else if (title.equals("回复消息")) {
            type = 3;
            method = API.HUIFUMSG;
        } else {
            type = 4;
            method = API.INTERMSG;
        }
        Map<String, String> map = new HashMap<String, String>();
        map.put("page", page);
        map.put("size", "30");
        postAsyn(this, API.BASEURL + method, map, this, type);
    }

    @Override
    public void callBack(Object object, int type) {
        try {
            Gson gson = new Gson();
            String err = new JSONObject(object.toString()).getString("err");
            if (type == 1) {
                if (err.equals("0")) {
                    mEmptyView.setVisibility(View.GONE);
                    mRecyclerView.setVisibility(View.VISIBLE);
                    MsgSupDemBean msgSupDemBean = gson.fromJson(object.toString(), MsgSupDemBean.class);
                    if (page == 1) {
                        mSupDemAdapter = new MessageSupDemAdapter(this, msgSupDemBean.getData());
                        mRecyclerView.setAdapter(mSupDemAdapter);

                        mListSupDem.clear();
                        mListSupDem.addAll(msgSupDemBean.getData());
                    } else {
                        mListSupDem.addAll(msgSupDemBean.getData());
                        mSupDemAdapter.setList(mListSupDem);
                        mSupDemAdapter.notifyDataSetChanged();
                    }
                    count = mListSupDem.size() - 1;
                    RabbitMQConfig.getInstance(this).readMsg("unread_reply_user_msg", 15);
                } else {
                    if (page == 1) {
                        mRecyclerView.setVisibility(View.GONE);
                        mEmptyView.setVisibility(View.VISIBLE);
                        mEmptyView.setMyManager(R.drawable.icon_follow1);
                        mEmptyView.setNoMessageText(new JSONObject(object.toString()).getString("msg"));

                    } else {
                        TextUtils.toast(this, new JSONObject(object.toString()).getString("msg"));
                    }
                }
            }
            if (type == 2) {
                if (err.equals("0")) {
                    mEmptyView.setVisibility(View.GONE);
                    mRecyclerView.setVisibility(View.VISIBLE);
                    MsgChJBean msgChJBean = gson.fromJson(object.toString(), MsgChJBean.class);
                    if (page == 1) {
                        mCHJAdapter = new MessageCHJAdapter(this, msgChJBean.getData());
                        mRecyclerView.setAdapter(mCHJAdapter);

                        mListChJ.clear();
                        mListChJ.addAll(msgChJBean.getData());
                    } else {
                        mListChJ.addAll(msgChJBean.getData());
                        mCHJAdapter.setList(mListChJ);
                        mCHJAdapter.notifyDataSetChanged();
                    }
                    RabbitMQConfig.getInstance(this).readMsg("unread_reply_user_msg", 16);
                } else {
                    if (page == 1) {
                        mRecyclerView.setVisibility(View.GONE);
                        mEmptyView.setVisibility(View.VISIBLE);
                        mEmptyView.setMyManager(R.drawable.icon_follow1);
                        mEmptyView.setNoMessageText(new JSONObject(object.toString()).getString("msg"));
                    } else {
                        TextUtils.toast(this, new JSONObject(object.toString()).getString("msg"));
                    }
                }

            }
            if (type == 3 || type == 4) {
                if (err.equals("0")) {
                    mEmptyView.setVisibility(View.GONE);
                    mRecyclerView.setVisibility(View.VISIBLE);
                    MsgHFBean msgHFBean = gson.fromJson(object.toString(), MsgHFBean.class);
                    if (page == 1) {
                        mHFAdapter = new MessageHFAdapter(this, msgHFBean.getData());
                        mRecyclerView.setAdapter(mHFAdapter);

                        mListHF.clear();
                        mListHF.addAll(msgHFBean.getData());
                    } else {
                        mListHF.addAll(msgHFBean.getData());
                        mHFAdapter.setList(mListHF);
                        mHFAdapter.notifyDataSetChanged();
                    }
                    count = mListHF.size() - 1;
                    RabbitMQConfig.getInstance(this).readMsg("unread_reply_user_msg", (type == 3 ? 17 : 18));
                } else {
                    if (page == 1) {
                        mRecyclerView.setVisibility(View.GONE);
                        mEmptyView.setVisibility(View.VISIBLE);
                        mEmptyView.setMyManager(R.drawable.icon_follow1);
                        mEmptyView.setNoMessageText(new JSONObject(object.toString()).getString("msg"));
                    } else {
                        TextUtils.toast(this, new JSONObject(object.toString()).getString("msg"));
                    }
                }
            }
        } catch (Exception e) {
        }
    }

    @Override
    public void failCallBack(int type) {

    }

}
