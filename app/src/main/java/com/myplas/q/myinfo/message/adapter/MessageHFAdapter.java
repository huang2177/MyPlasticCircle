package com.myplas.q.myinfo.message.adapter;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.myplas.q.R;
import com.myplas.q.common.view.CommonDialog;
import com.myplas.q.myinfo.beans.MsgHFBean;
import com.myplas.q.supdem.activity.SupDem_Detail_Activity;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 编写： 黄双
 * 电话：15378412400
 * 邮箱：15378412400@163.com
 * 时间：2017/3/23 16:29
 */
public class MessageHFAdapter extends RecyclerView.Adapter {
    Context context;
    List<MsgHFBean.DataBean> mListHF;
    Map<Integer, TextView> mViewMap;
    Map<Integer, viewHolder> mHolderMap;

    public MessageHFAdapter(Context context, List<MsgHFBean.DataBean> mListHF) {
        this.mListHF = mListHF;
        this.context = context;
        mViewMap = new HashMap<>();
        mHolderMap = new HashMap<>();
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_lv_msgcommon, parent, false);
        viewHolder viewHolder = new viewHolder(view, "", viewType);
        mHolderMap.put(viewType, viewHolder);
        //view.setOnLongClickListener(new MyOnLongClickListener(viewType));
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        viewHolder viewHolder = mHolderMap.get(position);
        String title = "“<font color='#ff5000'>"
                + mListHF.get(position).getName()
                + "</font>”回复您的消息啦！";
        viewHolder.title.setText(Html.fromHtml(title));
        viewHolder.time.setText(mListHF.get(position).getHui_time());
        viewHolder.company.setText(mListHF.get(position).getC_name()
                + "  "
                + mListHF.get(position).getName());

        String supdem = mListHF.get(position).getType().equals("2")
                ? "供给"
                : "求购";
        viewHolder.type.setText(supdem);

        viewHolder.tel.setText("联系电话：" + mListHF.get(position).getMobile());
        viewHolder.pro.setText("产品：" + mListHF.get(position).getFa_content());
        viewHolder.content.setText("回复内容：" + mListHF.get(position).getHui_content());
        viewHolder.ll_detail.setOnClickListener(new MyOnClickListener(position));

        int imgRes = mListHF.get(position).getType().equals("2")
                ? R.drawable.icon_supply
                : R.drawable.icon_purchase;
        viewHolder.type.setCompoundDrawablesWithIntrinsicBounds(imgRes, 0, 0, 0); //设置左图标
    }


    @Override
    public int getItemCount() {
        if (mListHF != null)
            return mListHF.size();
        return 0;
    }

    public void setList(List<MsgHFBean.DataBean> list) {
        this.mListHF = list;
    }

    class viewHolder extends RecyclerView.ViewHolder {
        LinearLayout ll_detail;
        TextView title, company, tel, type, pro, content, time, del;

        public viewHolder(View itemView, String type1, int position) {
            super(itemView);
            time = (TextView) itemView.findViewById(R.id.msg_detail_time);
            title = (TextView) itemView.findViewById(R.id.msg_detail_title);
            company = (TextView) itemView.findViewById(R.id.msg_detail_company);
            type = (TextView) itemView.findViewById(R.id.msg_detail_type);
            tel = (TextView) itemView.findViewById(R.id.msg_detail_tel);
            pro = (TextView) itemView.findViewById(R.id.msg_detail_pro);
            content = (TextView) itemView.findViewById(R.id.msg_detail_content);
            ll_detail = (LinearLayout) itemView.findViewById(R.id.msg_detail_detail);
        }
    }

    //长按监听类
    public class MyOnLongClickListener implements View.OnLongClickListener, CommonDialog.DialogShowInterface {
        int position;

        public MyOnLongClickListener(int position) {
            this.position = position;
        }

        @Override
        public boolean onLongClick(View v) {
            CommonDialog commonDialog = new CommonDialog();
            commonDialog.showDialog(context, "确认删除？", 1, this);
            return true;
        }

        @Override
        public void ok(int type) {
        }
    }

    public class MyOnClickListener implements View.OnClickListener {
        int position;

        public MyOnClickListener(int position) {
            this.position = position;
        }

        @Override
        public void onClick(View v) {
            Intent intent = new Intent(context, SupDem_Detail_Activity.class);
            String id = mListHF.get(position).getId();
            String userid = mListHF.get(position).getUser_id();
            Bundle bundle = new Bundle();
            bundle.putInt("position", 1);

            intent.putExtra("id", id);
            intent.putExtra("bundle", bundle);
            intent.putExtra("userid", userid);

            context.startActivity(intent);
        }
    }
}
