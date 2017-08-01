package com.myplas.q.myinfo.setting.adapter;

import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.myplas.q.R;
import com.myplas.q.common.api.API;
import com.myplas.q.common.utils.FileUtils;
import com.myplas.q.myinfo.beans.MyMessageBean;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 编写： 黄双
 * 电话：15378412400
 * 邮箱：15378412400@163.com
 * 时间：2017/3/29 10:57
 */
public class SettingAdapter extends BaseAdapter {
    Context context;
    List<String> list;
    List<Integer> mList;
    mySwitchCheckedListenler mListenler;

    Map<Integer, View> mViewMap;
    Map<Integer, Switch> mSwitchMap;

    public SettingAdapter(Context context, List<String> list, List<Integer> list1) {
        this.context = context;
        this.list = list;
        this.mList = list1;
        mViewMap = new HashMap<>();
        mSwitchMap = new HashMap<>();
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
        viewHolder viewHolder = null;
        if (mViewMap.get(position) == null) {
            viewHolder = new viewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.item_layout_setting, parent, false);
            viewHolder.mSwitch = (Switch) convertView.findViewById(R.id.item_lv_setting_switch);
            viewHolder.mTextView = (TextView) convertView.findViewById(R.id.item_lv_setting_text);
            viewHolder.mTextView1 = (TextView) convertView.findViewById(R.id.item_lv_setting_text1);
            viewHolder.mImageView = (ImageView) convertView.findViewById(R.id.item_lv_setting_img);
            convertView.setTag(viewHolder);
            mViewMap.put(position, convertView);
        } else {
            convertView = mViewMap.get(position);
            viewHolder = (viewHolder) convertView.getTag();
        }
        if (position == 1) {
            mSwitchMap.put(position, viewHolder.mSwitch);
            viewHolder.mTextView1.setVisibility(View.GONE);
            viewHolder.mSwitch.setVisibility(View.VISIBLE);
            viewHolder.mSwitch.setOnCheckedChangeListener(new myOnCheckedChangeListener());
            if (Build.VERSION.SDK_INT >= 19) {
                viewHolder.mSwitch.setThumbResource(R.drawable.switch_thumb);
                viewHolder.mSwitch.setTrackResource(R.drawable.switch_track);
            }
        }
        if (position == 6) {
            String cacheSize = FileUtils.getTotalCacheSize(context);
            viewHolder.mTextView1.setText(cacheSize + "  ");
        }
        viewHolder.mTextView.setText(list.get(position));
        viewHolder.mImageView.setImageResource(mList.get(position));
        return convertView;
    }

    class viewHolder {
        Switch mSwitch;
        ImageView mImageView;
        TextView mTextView, mTextView1;
    }

    public void setSwitchChecked(boolean checked) {
        mSwitchMap.get(1).setChecked(checked);
    }

    public class myOnCheckedChangeListener implements CompoundButton.OnCheckedChangeListener {
        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            switch (buttonView.getId()) {
                case R.id.item_lv_setting_switch:
                    String allow_sendmsg_gk = (isChecked) ? "0" : "1";
                    mListenler.onChecked(allow_sendmsg_gk);
                    break;
            }
        }
    }

    public interface mySwitchCheckedListenler {
        void onChecked(String s);
    }

    public void setMySwitchCheckedListenler(mySwitchCheckedListenler mySwitchCheckedListenler) {
        this.mListenler = mySwitchCheckedListenler;
    }
}
