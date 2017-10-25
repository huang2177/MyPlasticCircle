package com.myplas.q.myself.message.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.myplas.q.R;
import com.myplas.q.common.view.CommonDialog;
import com.myplas.q.myself.beans.MsgChJBean;
import com.myplas.q.myself.message.activity.NoInfoActivity;
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
public class MessageCHJAdapter extends RecyclerView.Adapter {
    Context context;
    List<MsgChJBean.DataBean> mListChJ;
    Map<Integer, TextView> mViewMap;
    Map<Integer, viewHolder> mHolderMap;

    public MessageCHJAdapter(Context context, List<MsgChJBean.DataBean> mListChJ) {
        this.mListChJ = mListChJ;
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
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        viewHolder viewHolder = mHolderMap.get(position);
        String supdem = mListChJ.get(position).getType().equals("2")
                ? "供给："
                : "求购：";
        String title = "“<font color='#ff5000'>"
                + mListChJ.get(position).getUser_name()
                + "</font>”回复您的消息啦！";
        viewHolder.time.setText(mListChJ.get(position).getFa_time());

        viewHolder.title.setText(Html.fromHtml(title));

        viewHolder.company.setText(mListChJ.get(position).getC_name()
                + "  " + mListChJ.get(position).getUser_name());

        viewHolder.tel.setText("联系电话：" + mListChJ.get(position).getUser_mobile());

        viewHolder.pro.setText(supdem + mListChJ.get(position).getFa_content());

        viewHolder.content.setText(Html.fromHtml("出价价格：<font color='#ff5000'>¥"
                + mListChJ.get(position).getPrice()
                + "</font>"));
        viewHolder.ll_detail.setOnClickListener(new MyOnClickListener(position));
    }

    @Override
    public int getItemCount() {
        return mListChJ != null ? mListChJ.size() : 0;
    }

    public void setList(List<MsgChJBean.DataBean> list) {
        this.mListChJ = list;
    }

    class viewHolder extends RecyclerView.ViewHolder {
        LinearLayout ll_detail;
        TextView title, company, tel, type, pro, content, time, del;

        public viewHolder(View itemView, String type1, int position) {
            super(itemView);
            time = (TextView) itemView.findViewById(R.id.msg_detail_time);
            title = (TextView) itemView.findViewById(R.id.msg_detail_title);
            company = (TextView) itemView.findViewById(R.id.msg_detail_company);
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
            if ("2".equals(mListChJ.get(position).getPur_sign())) {
                Intent intent = new Intent(context, NoInfoActivity.class);
                intent.putExtra("msg", mListChJ.get(position).getPur_sign());
                context.startActivity(intent);
            } else {
                Intent intent = new Intent(context, SupDem_Detail_Activity.class);
                String id = mListChJ.get(position).getId();
                String userid = mListChJ.get(position).getUser_id();

                Bundle bundle = new Bundle();
                bundle.putInt("position", 0);

                intent.putExtra("id", id);
                intent.putExtra("userid", userid);
                intent.putExtra("bundle", bundle);

                context.startActivity(intent);
            }
        }
    }
}
