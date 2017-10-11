package com.myplas.q.supdem;

import android.app.ActionBar;
import android.app.Activity;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;

import com.myplas.q.R;
import com.myplas.q.common.utils.ScreenUtils;
import com.myplas.q.common.utils.SharedUtils;
import com.myplas.q.common.utils.SystemUtils;
import com.myplas.q.common.view.CustomPopupWindow;
import com.myplas.q.supdem.Beans.TabCofigBean;
import com.myplas.q.supdem.adapter.AddressSelectAdapter;
import com.myplas.q.supdem.adapter.TimeSelectAdapter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.umeng.analytics.pro.x.I;
import static com.umeng.analytics.pro.x.b;


/**
 * 编写：黄双
 * 邮箱：15378412400@163.com
 * 时间： 2017/6/261855.
 */

public class PopouShowUtils implements View.OnClickListener, AdapterView.OnItemClickListener {
    private Activity context;
    private int pro;
    private View imageView_sd;
    private LinearLayout layout;
    private CustomPopupWindow mPopupWindow2;
    private TimeSelectAdapter adapter_time;
    private AddressSelectAdapter adapter_add_province,adapter_add_city;
    private ListView mListView_province, mListView_city, mListView_time;

    private SharedUtils sharedUtils;
    private List<ItemBean> list_IsSelect;
    private poPouCallBackInterface backInterface;
    private List<TabCofigBean.DataBeanXXX.AreaBean.DataBeanXX> list_area;
    private List<TabCofigBean.DataBeanXXX.TimeBean.DataBean> list_time;
    public PopouShowUtils(Activity context,
                          int resId,
                          int type,
                          List<TabCofigBean.DataBeanXXX.AreaBean.DataBeanXX> list_area,
                          List<TabCofigBean.DataBeanXXX.TimeBean.DataBean> list_time) {
        this.context = context;
        this.list_area=list_area;
        sharedUtils = SharedUtils.getSharedUtils();

        View view2 = LayoutInflater.from(context).inflate(resId, null, false);
        mPopupWindow2 = new CustomPopupWindow(view2, ActionBar.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.MATCH_PARENT);
        mPopupWindow2.setInputMethodMode(PopupWindow.INPUT_METHOD_NEEDED);
        mPopupWindow2.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        mPopupWindow2.setFocusable(true);
        mPopupWindow2.setOutsideTouchable(true);
        imageView_sd = (View) view2.findViewById(R.id.img_dismiss);
        imageView_sd.setOnClickListener(this);
        if (type == 0) {
            String po = sharedUtils.getData(context, "position");
            initData(list_time.size(), ("".equals(po)) ? (0) : (Integer.parseInt(po)));
            adapter_time = new TimeSelectAdapter(context, list_time,list_IsSelect);
            mListView_time = (ListView) view2.findViewById(R.id.mlistview_time);

            mListView_time.setAdapter(adapter_time);
            mListView_time.setOnItemClickListener(this);
        } else {
            layout= (LinearLayout) view2.findViewById(R.id.titlebar);
            int screenH= ScreenUtils.getScreenHeight(context);
            LinearLayout.LayoutParams lp= (LinearLayout.LayoutParams) layout.getLayoutParams();
            lp.height=(int) (screenH*3.35)/5;
            layout.setLayoutParams(lp);

            pro = Integer.parseInt(sharedUtils.getData(context, "position_pro"));
            int city = Integer.parseInt(sharedUtils.getData(context, "position_city"));
            adapter_add_province = new AddressSelectAdapter(context, 0, pro, list_area, null);
            adapter_add_city = new AddressSelectAdapter(context, 1, city, null, list_area.get(pro).getData());
            mListView_city = (ListView) view2.findViewById(R.id.mlistview_city);
            mListView_province = (ListView) view2.findViewById(R.id.mlistview_pro);

            adapter_add_province.setSelectedItem(true);
            mListView_province.setAdapter(adapter_add_province);
            mListView_city.setAdapter(adapter_add_city);

            mListView_province.setSelection(pro);

            mListView_city.setOnItemClickListener(this);
            mListView_province.setOnItemClickListener(this);
        }
    }

    public void showAsDropDown(View t) {
        if (android.os.Build.VERSION.SDK_INT >= 24) {
            int[] a = new int[2];
            t.getLocationInWindow(a);
            mPopupWindow2.showAtLocation(context.getWindow().getDecorView(), Gravity.NO_GRAVITY, 0, a[1] + t.getHeight());
        } else {
            mPopupWindow2.showAsDropDown(t);
        }
    }

    @Override
    public void onClick(View v) {
        mPopupWindow2.dismiss();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        switch (parent.getId()) {
            case R.id.mlistview_time:
                sharedUtils.setData(context, "position", position + "");
                adapter_time.setPosition(position);
                backInterface.timeCallBack(position);
                mPopupWindow2.dismiss();
                break;
            case R.id.mlistview_pro:
                adapter_add_province.setSelectedItem(false);
                adapter_add_province.notifyDataSetChanged();
                SharedUtils.getSharedUtils().setData(context, "position_pro", position + "");
                int city = Integer.parseInt(sharedUtils.getData(context, "position_city"));
                adapter_add_city = new AddressSelectAdapter(context, 1, city, null, list_area.get(position).getData());
                mListView_city.setAdapter(adapter_add_city);
                break;
            case R.id.mlistview_city:
                SharedUtils.getSharedUtils().setData(context, "position_city", position + "");
                int pro = Integer.parseInt(sharedUtils.getData(context, "position_pro"));
                backInterface.addCallBack(pro, position);
                mPopupWindow2.dismiss();
                break;
        }
    }

    public void initData(int size, int current) {
        list_IsSelect = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            ItemBean itemBean = new ItemBean();
            itemBean.setSelected((i == current) ? (true) : (false));
            list_IsSelect.add(itemBean);
        }
    }
    public interface poPouCallBackInterface{
        void addCallBack(int po_pro,int position);
        void timeCallBack(int position);
    }
    public void setPoPouCallBackInterface(poPouCallBackInterface backInterface){
        this.backInterface=backInterface;
    }

    public class ItemBean {
        private boolean isSelected;

        public void setSelected(boolean selected) {
            isSelected = selected;
        }

        public boolean isSelected() {
            return isSelected;
        }
    }
}
