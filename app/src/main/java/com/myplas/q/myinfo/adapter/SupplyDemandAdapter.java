package com.myplas.q.myinfo.adapter;

import android.content.Context;
import android.content.Intent;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.myplas.q.R;
import com.myplas.q.common.utils.DialogShowUtils;
import com.myplas.q.guide.activity.BaseActivity;
import com.myplas.q.guide.activity.ShareActivity;
import com.myplas.q.common.netresquset.ResultCallBack;
import com.myplas.q.common.utils.SharedUtils;
import com.myplas.q.common.api.API;
import com.myplas.q.myinfo.beans.MyCommentBean;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 编写： 黄双
 * 电话：15378412400
 * 邮箱：15378412400@163.com
 * 时间：2017/3/23 16:29
 */
public class SupplyDemandAdapter extends BaseAdapter implements ResultCallBack ,DialogShowUtils.DialogShowInterface{
    String type;
    Context context;
    MyInterface myInterface;
    SharedUtils sharedUtils;
    Map<Integer, View> viewMap;
    int lastPosition,position;
    viewHolder viewHolder = null;
    List<MyCommentBean.DataBean> list;

    public void setLastPosition(int lastPosition) {
        this.lastPosition = lastPosition;
    }

    public void setList(List<MyCommentBean.DataBean> list) {
        this.list = list;
    }

    public SupplyDemandAdapter(Context context, List<MyCommentBean.DataBean> list, String type, MyInterface myInterface) {
        this.context = context;
        this.list = list;
        this.type = type;
        this.myInterface = myInterface;
        viewMap = new HashMap<>();
        sharedUtils = SharedUtils.getSharedUtils();
    }

    @Override
    public int getCount() {
        if (list != null)
            return list.size();
        return 0;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        if (viewMap.get(position) == null) {
            viewHolder = new viewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.wd_qg_listview_item, parent, false);
            viewMap.put(position, convertView);
            viewHolder.xtxx = (TextView) convertView.findViewById(R.id.wd_qg_xtxx);
            viewHolder.shc = (TextView) convertView.findViewById(R.id.wd_wdqg_shc);
            viewHolder.pirce = (TextView) convertView.findViewById(R.id.wd_qg_price);
            viewHolder.time = (TextView) convertView.findViewById(R.id.wd_wdqg_shj);
            viewHolder.share = (TextView) convertView.findViewById(R.id.wd_wdqg_share);
            viewHolder.suliao_img = (TextView) convertView.findViewById(R.id.suliao_img);
            viewHolder.content = (TextView) convertView.findViewById(R.id.wd_qg_content);
            viewHolder.linearLayout = (LinearLayout) convertView.findViewById(R.id.mycomment_says);
            convertView.setTag(viewHolder);
        } else {
            convertView = viewMap.get(position);
            viewHolder = (viewHolder) convertView.getTag();
        }
        if (type.equals("1")) {
            viewHolder.content.setText("  我的求购：" + list.get(position).getContents());
        } else {
            viewHolder.content.setText("  我的供给：" + list.get(position).getContents());
        }
        viewHolder.time.setText(list.get(position).getInput_time());
        viewHolder.xtxx.setText("在信息库中，没有找到在卖（买）此牌号的商家！");
        viewHolder.pirce.setText("塑料圈查无此价格！");
        if (list.get(position).getSays().size() != 0) {
            TextView textView = null;
            viewHolder.suliao_img.setVisibility(View.VISIBLE);
            //判断父布局中不为空；
            if (textView == null && viewHolder.linearLayout.getChildAt(0) == null) {
                textView = new TextView(context);
                viewHolder.linearLayout.addView(textView);
                LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams) textView.getLayoutParams();
                lp.setMargins(10, 15, 10, 15);
                textView.setLayoutParams(lp);
                for (int i = 0; i < list.get(position).getSays().size(); i++) {
                    if (i == 0) {
                        String html = "<font color='#36648B'>" + list.get(position).getSays().get(0).getUser_name() + "</font>" + " 说：" + list.get(position).getSays().get(0).getContent();
                        textView.setText(Html.fromHtml(html));
                    } else {
                        textView = new TextView(context);
                        viewHolder.linearLayout.addView(textView);
                        LinearLayout.LayoutParams lp1 = (LinearLayout.LayoutParams) textView.getLayoutParams();
                        lp1.setMargins(10, 10, 10, 15);
                        textView.setLayoutParams(lp1);
                        String html1 = "<font color='#36648B'>" + list.get(position).getSays().get(i).getUser_name() + "</font>" + "说：" + list.get(position).getSays().get(i).getContent();
                        textView.setText(Html.fromHtml(html1));
                    }
                }
            }
        }else {
            viewHolder.suliao_img.setVisibility(View.GONE);
        }
        //删除
        viewHolder.shc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SupplyDemandAdapter.this.position=position;
                DialogShowUtils dialogShowUtils = new DialogShowUtils();
                dialogShowUtils.showDialog(context, "确定删除?", 1, SupplyDemandAdapter.this);
            }
        });
        //分享
        viewHolder.share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s = (list.get(position).getType().equals("1")) ? ("求购信息:") : ("供给信息：");
                Intent in = new Intent(context, ShareActivity.class);
                in.putExtra("type", "4");
                in.putExtra("id", list.get(position).getId());
                in.putExtra("title", s + list.get(position).getContents());
                in.putExtra("t", list.get(position).getType());
                context.startActivity(in);
            }
        });
        return convertView;
    }

    class viewHolder {

        LinearLayout linearLayout;
        TextView shc, content, time, xtxx, pirce, share, suliao_img;
    }
    @Override
    public void callBack(Object object, int type) {
        try {
            JSONObject jsonObject = new JSONObject(object.toString());
            if (type == 1 && jsonObject.getString("err").equals("0")) {
                myInterface.reQuestNet();
                if (list.size() == 1) {
                    list.remove(0);
                    notifyDataSetChanged();
                }
            }
        } catch (Exception e) {
        }
    }

    @Override
    public void ok(int type) {
        switch (type) {
            case 1:
                Map<String, String> map = new HashMap<String, String>();
                map.put("token", sharedUtils.getData(context, "token"));
                map.put("id", list.get(position).getId());
                BaseActivity.postAsyn(context, API.BASEURL + API.DELETE_MY_MSG, map, SupplyDemandAdapter.this, 1);
                break;
        }
    }
    @Override
    public void failCallBack(int type) {

    }
    public interface MyInterface {
        void reQuestNet();
    }
}
