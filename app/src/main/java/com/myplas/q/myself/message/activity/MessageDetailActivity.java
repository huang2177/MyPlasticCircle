package com.myplas.q.myself.message.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.google.gson.Gson;
import com.myplas.q.R;
import com.myplas.q.common.appcontext.Constant;
import com.myplas.q.common.utils.TextUtils;
import com.myplas.q.common.view.EmptyView;
import com.myplas.q.app.activity.BaseActivity;
import com.myplas.q.common.net.ResultCallBack;
import com.myplas.q.myself.beans.MsgChJBean;
import com.myplas.q.myself.beans.MsgHFBean;
import com.myplas.q.myself.beans.MsgSupDemBean;
import com.myplas.q.myself.beans.MsgSystem;
import com.myplas.q.myself.message.adapter.MessageCHJAdapter;
import com.myplas.q.myself.message.adapter.MessageHFAdapter;
import com.myplas.q.common.api.API;
import com.myplas.q.myself.message.adapter.MessageSupDemAdapter;
import com.myplas.q.myself.message.adapter.MessageSystemAdapter;
import com.myplas.q.sockethelper.RabbitMQConfig;

import org.json.JSONException;
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
    private List<MsgSystem.DataBean> mListSystem;
    private List<MsgSupDemBean.DataBean> mListSupDem;

    private EmptyView mEmptyView;
    private RecyclerView mRecyclerView;
    private MessageHFAdapter mHFAdapter;
    private MessageCHJAdapter mCHJAdapter;
    private MessageSystemAdapter mSystemAdapter;
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
        mListSystem = new ArrayList<>();
        mRecyclerView = F(R.id.wd_gj_listview);
        mEmptyView = F(R.id.mysupdem_noresultlayout);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);//设置为一个1列的纵向网格布局
        mRecyclerView.setLayoutManager(mLayoutManager);
    }


    public void getMyMsg(String page, String title) {
        int type;
        if ("供求消息".equals(title)) {
            type = 1;
            method = API.PLASTICMSG;
        } else if ("出价消息".equals(title)) {
            type = 2;
            method = API.CHUJIAMSG;
        } else if ("回复消息".equals(title)) {
            type = 3;
            method = API.HUIFUMSG;
        } else if ("互动消息".equals(title)) {
            type = 4;
            method = API.INTERMSG;
        } else {
            type = 5;
            method = API.SYSTEMMSG;
        }
        Map<String, String> map = new HashMap<String, String>();
        map.put("page", page);
        map.put("size", "30");
        getAsyn(this, method, map, this, type);
    }

    @Override
    public void callBack(Object object, int type) {
        try {
            Gson gson = new Gson();
            String err = new JSONObject(object.toString()).getString("code");
            if (type == 1) {
                if ("0".equals(err)) {
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
                    RabbitMQConfig.getInstance(this).readMsg(Constant.R_SUPDEM_MSG, 15);
                }
            }
            if (type == 2) {
                if ("0".equals(err)) {
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
                    RabbitMQConfig.getInstance(this).readMsg(Constant.R_PUR_MSG, 16);
                }
            }
            if (type == 3 || type == 4) {
                if ("0".equals(err)) {
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
                    RabbitMQConfig.getInstance(this).readMsg(type == 3 ? Constant.R_REPLY_MSG : Constant.R_INTER_MSG
                            , (type == 3 ? 17 : 18));
                }
            }
            if (type == 5 && "0".equals(err)) {
                mEmptyView.setVisibility(View.GONE);
                mRecyclerView.setVisibility(View.VISIBLE);
                MsgSystem msgSystem = gson.fromJson(object.toString(), MsgSystem.class);
                if (page == 1) {
                    mSystemAdapter = new MessageSystemAdapter(this, msgSystem.getData());
                    mRecyclerView.setAdapter(mSystemAdapter);

                    mListSystem.clear();
                    mListSystem.addAll(msgSystem.getData());
                } else {
                    mListSystem.addAll(msgSystem.getData());
                    mSystemAdapter.setList(mListSystem);
                    mSystemAdapter.notifyDataSetChanged();
                }
                RabbitMQConfig.getInstance(this).readMsg(Constant.R_SYSTEM_MSG, 19);
            }
        } catch (Exception e) {
        }
    }

    @Override
    public void failCallBack(int type, String message, int httpCode) {
        try {
            JSONObject jsonObject = new JSONObject(message);
            String msg = jsonObject.getString("message");
            if (page == 1 && httpCode == 404) {
                mRecyclerView.setVisibility(View.GONE);
                mEmptyView.setVisibility(View.VISIBLE);
                mEmptyView.setMyManager(R.drawable.icon_follow1);
                mEmptyView.setNoMessageText(msg);
            } else if (page != 1) {
                TextUtils.toast(this, msg);
            }
        } catch (JSONException e) {

        }
    }
}
