package com.myplas.q.myself.setting;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.myplas.q.R;
import com.myplas.q.common.utils.FileUtils;
import com.myplas.q.common.utils.NumUtils;
import com.myplas.q.common.view.SwitchButton;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 编写： 黄双
 * 电话：15378412400
 * 邮箱：15378412400@163.com
 * 时间：2017/3/29 10:57
 */
public class SettingAdapter extends RecyclerView.Adapter {
    int versionImg;
    Context context;
    List<String> list;
    List<Integer> mList;
    mySwitchCheckedListenler mListenler;
    OnItemClickListener mOnItemClickListener;

    Map<Integer, View> mViewMap;
    Map<Integer, SwitchButton> mSwitchMap;
    private boolean notificationsEnabled;

    public SettingAdapter(Context context, List<String> list, List<Integer> list1) {
        this.list = list;
        this.mList = list1;
        this.context = context;
        mViewMap = new HashMap<>();
        mSwitchMap = new HashMap<>();
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, final int viewType) {
        View convertView = LayoutInflater.from(context).inflate(R.layout.item_layout_setting, parent, false);
        viewHolder viewHolder = new viewHolder(convertView);
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mOnItemClickListener.onItemClick(viewType);
            }
        });
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        try {
            viewHolder viewHolder = (viewHolder) holder;
            if (position == 1) {
                mSwitchMap.put(position, viewHolder.mSwitch);
                viewHolder.mTextView1.setVisibility(View.GONE);
                viewHolder.mSwitch.setVisibility(View.VISIBLE);
                viewHolder.mSwitch.setOnCheckedChangeListener(new myOnCheckedChangeListener());
            }
            if (position == 3) {
                viewHolder.mTextView1.setText(notificationsEnabled ? "已开启" : "已关闭，去开启");
            }
            if (position == 8) {
                viewHolder.mTextView1.setCompoundDrawablesWithIntrinsicBounds(versionImg
                        , 0
                        , R.drawable.icon_more
                        , 0);
            }
            viewHolder.mTextView.setText(list.get(position));
            viewHolder.mImageView.setImageResource(mList.get(position));

            if (position == 7) {
                String cacheSize = FileUtils.getTotalCacheSize(context);
                viewHolder.mTextView1.setText(cacheSize);
                if (!cacheSize.contains("小于")) {
                    String str = cacheSize.substring(0, cacheSize.indexOf("M"));
                    if (Double.parseDouble(str) > 10) {
                        viewHolder.mTextView1.setText("10.3M");
                    }
                }
            }
        } catch (Exception e) {
        }
    }

    public void setVersionImg(int versionImg) {
        this.versionImg = versionImg;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public int getItemCount() {
        return list != null ? list.size() : 0;
    }

    public void setNotificationsEnabled(boolean notificationsEnabled) {
        this.notificationsEnabled = notificationsEnabled;
    }


    class viewHolder extends RecyclerView.ViewHolder {
        SwitchButton mSwitch;
        ImageView mImageView;
        TextView mTextView, mTextView1;

        public viewHolder(View convertView) {
            super(convertView);
            mTextView = (TextView) convertView.findViewById(R.id.item_lv_setting_text);
            mImageView = (ImageView) convertView.findViewById(R.id.item_lv_setting_img);
            mTextView1 = (TextView) convertView.findViewById(R.id.item_lv_setting_text1);
            mSwitch = (SwitchButton) convertView.findViewById(R.id.item_lv_setting_switch);
        }
    }

    public void setSwitchChecked(boolean checked) {
        mSwitchMap.get(1).setChecked(checked);
    }

    public class myOnCheckedChangeListener implements SwitchButton.OnCheckedChangeListener {
        @Override
        public void onCheckedChanged(SwitchButton view, boolean isChecked) {
            String allow_sendmsg_gk = (isChecked) ? "0" : "1";
            mListenler.onChecked(allow_sendmsg_gk);
        }
    }

    public interface mySwitchCheckedListenler {
        void onChecked(String s);
    }

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public void setMySwitchCheckedListenler(mySwitchCheckedListenler mySwitchCheckedListenler) {
        this.mListenler = mySwitchCheckedListenler;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
    }
}
