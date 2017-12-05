package com.myplas.q.myself.message.adapter;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
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
import com.myplas.q.myself.beans.MsgSupDemBean;
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
public class MessageSupDemAdapter extends RecyclerView.Adapter {
    Context context;
    List<MsgSupDemBean.DataBean> mListSupDem;
    Map<Integer, TextView> mViewMap;
    Map<Integer, viewHolder> mHolderMap;

    public MessageSupDemAdapter(Context context, List<MsgSupDemBean.DataBean> mListSupDem) {
        this.mListSupDem = mListSupDem;
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
        viewHolder.time.setText(mListSupDem.get(position).getInput_time());
        String supdem = mListSupDem.get(position).getType().equals("2")
                ? "供给"
                : "求购";
        String title = "您关注的“<font color='#ff5000'>"
                + mListSupDem.get(position).getUser_name()
                + "</font>”发布新的<font color='#ff5000'>"
                + supdem
                + "</font>消息啦！";
        viewHolder.title.setText(Html.fromHtml(title));
        viewHolder.company.setText(mListSupDem.get(position).getC_name()
                + "  "
                + mListSupDem.get(position).getUser_name());

        viewHolder.pro.setText(supdem + mListSupDem.get(position).getContent());

        viewHolder.ll_detail.setOnClickListener(new MyOnClickListener(position));

    }


    @Override
    public int getItemCount() {
        return mListSupDem != null ? mListSupDem.size() : 0;
    }

    public void setList(List<MsgSupDemBean.DataBean> list) {
        this.mListSupDem = list;
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

            tel.setVisibility(View.GONE);
            content.setVisibility(View.GONE);
        }
    }

    //长按监听类
    public class MyOnLongClickListener implements View.OnLongClickListener {
        int position;

        public MyOnLongClickListener(int position) {
            this.position = position;
        }

        @Override
        public boolean onLongClick(View v) {
            CommonDialog commonDialog = new CommonDialog();
            commonDialog.showDialog(context, "确认删除？", 1, null);
            return true;
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
            String id = mListSupDem.get(position).getId();
            String userid = mListSupDem.get(position).getUser_id();

            Bundle bundle = new Bundle();
            bundle.putInt("position", 0);

            intent.putExtra("id", id);
            intent.putExtra("userid", userid);
            intent.putExtra("bundle", bundle);

            context.startActivity(intent);
        }
    }
}
