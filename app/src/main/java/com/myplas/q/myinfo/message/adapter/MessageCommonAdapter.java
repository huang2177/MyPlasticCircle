package com.myplas.q.myinfo.message.adapter;

import android.app.Dialog;
import android.content.Context;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.myplas.q.R;
import com.myplas.q.common.utils.DialogShowUtils;
import com.myplas.q.myinfo.beans.MyCommentBean;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 编写： 黄双
 * 电话：15378412400
 * 邮箱：15378412400@163.com
 * 时间：2017/3/23 16:29
 */
public class MessageCommonAdapter extends RecyclerView.Adapter {
    Context context;
    List<MyCommentBean.DataBean> list;
    Map<Integer, TextView> mViewMap;
    Map<Integer, viewHolder> mHolderMap;

    public MessageCommonAdapter(Context context, List<MyCommentBean.DataBean> list) {
        this.list = list;
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
        view.setOnLongClickListener(new MyOnLongClickListener(viewType));
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        viewHolder viewHolder = mHolderMap.get(position);
//        viewHolder.time.setText();
//        viewHolder.title.setText();
//        viewHolder.company.setText();
//        viewHolder.tel.setText();
//        viewHolder.pro.setText();
//        viewHolder.content.setText();
        viewHolder.ll_detail.setOnClickListener(new MyOnClickListener(position));
    }


    @Override
    public int getItemCount() {
//        if (list != null)
//            return list.size();
        return 7;
    }

    public void setList(List<MyCommentBean.DataBean> list) {
        this.list = list;
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
            del = (TextView) itemView.findViewById(R.id.msg_detail_del);
        }
    }

    //长按监听类
    public class MyOnLongClickListener implements View.OnLongClickListener, DialogShowUtils.DialogShowInterface {
        int position;

        public MyOnLongClickListener(int position) {
            this.position = position;
        }

        @Override
        public boolean onLongClick(View v) {
            DialogShowUtils dialogShowUtils = new DialogShowUtils();
            dialogShowUtils.showDialog(context, "确认删除？", 1, this);
            return true;
        }

        @Override
        public void ok(int type) {
            Log.e("------", "------------");
        }
    }

    public class MyOnClickListener implements View.OnClickListener {
        int position;

        public MyOnClickListener(int position) {
            this.position = position;
        }

        @Override
        public void onClick(View v) {
            Log.e("**********", "********" + position);
        }
    }
}
