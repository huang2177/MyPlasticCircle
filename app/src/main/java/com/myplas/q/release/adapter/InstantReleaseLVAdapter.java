package com.myplas.q.release.adapter;

import android.content.Context;
import android.content.Intent;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.myplas.q.R;
import com.myplas.q.common.api.API;
import com.myplas.q.common.netresquset.ResultCallBack;
import com.myplas.q.common.utils.SharedUtils;
import com.myplas.q.common.utils.TextUtils;
import com.myplas.q.common.view.CommonDialog;
import com.myplas.q.guide.activity.BaseActivity;
import com.myplas.q.guide.activity.ShareActivity;
import com.myplas.q.myself.beans.MyFollowBean;
import com.myplas.q.myself.beans.MySupDemBean;
import com.myplas.q.myself.supdem.SupDemAdapter;
import com.myplas.q.release.ReleaseActivity;
import com.myplas.q.release.bean.PreViewBean;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 编写： 黄双
 * 电话：15378412400
 * 邮箱：15378412400@163.com
 * 时间：2017/3/20 22:33
 */
public class InstantReleaseLVAdapter extends BaseAdapter implements CommonDialog.DialogShowInterface
        , ResultCallBack {

    String type;
    int position;
    Context context;
    MyInterface myInterface;
    SharedUtils sharedUtils;
    Map<Integer, View> viewMap;
    List<PreViewBean.DataBean> list;


    public void setList(List<PreViewBean.DataBean> list) {
        this.list = list;
    }

    public InstantReleaseLVAdapter(Context context, List<PreViewBean.DataBean> list, MyInterface myInterface, String type) {
        this.list = list;
        this.type = type;
        this.context = context;
        viewMap = new HashMap<>();
        this.myInterface = myInterface;
        sharedUtils = SharedUtils.getSharedUtils();
    }

    @Override
    public int getCount() {
        return list != null ? list.size() : 0;
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
        viewHolder viewHolder = null;
        if (convertView == null) {
            viewHolder = new viewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.item_release_pre_dialog_layout, parent, false);
            viewMap.put(position, convertView);
            viewHolder.name = (TextView) convertView.findViewById(R.id.gq_listview_name);
            viewHolder.typeSupDem = (ImageView) convertView.findViewById(R.id.supdem_img_type);
            viewHolder.content = (TextView) convertView.findViewById(R.id.supply_demand_content);
            viewHolder.mImageS = (ImageView) convertView.findViewById(R.id.myself_supdem_img_modify);
            viewHolder.mImageD = (ImageView) convertView.findViewById(R.id.myself_supdem_img_delete);
            viewHolder.mLayout = (LinearLayout) convertView.findViewById(R.id.supply_demand_company);
            viewHolder.typeNowFutures = (ImageView) convertView.findViewById(R.id.supply_demand_now_futures);
            try {
                viewHolder.content.setText(Html.fromHtml(getContent(position)));

                viewHolder.typeSupDem.setImageResource(type.equals("1")
                        ? R.drawable.icon_supdem_purchase
                        : R.drawable.icon_supdem_supply);
                viewHolder.typeNowFutures.setImageResource(list.get(position).getTransaction_type().equals("1")
                        ? R.drawable.icon_now
                        : R.drawable.icon_futures);

                //删除
                viewHolder.mImageD.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        InstantReleaseLVAdapter.this.position = position;
                        CommonDialog commonDialog = new CommonDialog();
                        commonDialog.showDialog(context, "确定删除?", 1, InstantReleaseLVAdapter.this);
                    }
                });

                //修改
                viewHolder.mImageS.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                });
            } catch (Exception e) {
            }
        }
        return convertView;
    }

    private String getContent(int position) {
        return "<font color='#9c9c9c'>" + " 货物位置:" + "</font>" + list.get(position).getStorehouse()
                + "<font color='#9c9c9c'>" + " 牌号:" + "</font>" + list.get(position).getModel()
                + "<font color='#9c9c9c'>" + " 厂家:" + "</font>" + list.get(position).getVendor()
                + "<font color='#9c9c9c'>" + " 价格:" + "</font>" + list.get(position).getPrice();
    }

    class viewHolder {
        LinearLayout mLayout;
        TextView name, content;
        ImageView typeSupDem, typeNowFutures, mImageS, mImageD;
    }

    @Override
    public void callBack(Object object, int type) {
        try {
            JSONObject jsonObject = new JSONObject(object.toString());
            TextUtils.Toast(context, jsonObject.getString("msg"));
            if (jsonObject.getString("err").equals("0")) {
                if (myInterface != null) {
                    myInterface.reQuestNet();
                }
                if (list.size() == 1) {
                    list.remove(0);
                    notifyDataSetChanged();
                }
            }
        } catch (Exception e) {
        }
    }

    @Override
    public void failCallBack(int type) {

    }

    @Override
    public void ok(int type) {
//        Map<String, String> map = new HashMap<String, String>();
//        map.put("token", sharedUtils.getData(context, "token"));
//        map.put("id", list.get(position).getId());
//        BaseActivity.postAsyn(context, API.BASEURL + API.DELETE_MY_MSG, map, this, 1);
    }

    public interface MyInterface {
        void reQuestNet();
    }
}
