package com.myplas.q.contact.adapter;

import android.graphics.Color;
import android.util.SparseArray;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.huangbryant.hindicator.HIndicatorAdapter;
import com.myplas.q.R;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 作者:huangshuang
 * 事件 2017/10/15.
 * 邮箱： 15378412400@163.com
 */

public abstract class Fragment_Dialog_Adapter extends HIndicatorAdapter {
    private int type;
    private List<String> mListShow;
    private List<String> mListValue;
    private SparseArray<Integer> map;

    public Fragment_Dialog_Adapter(int type, SparseArray<Integer> map) {
        this.map = map;
        this.type = type;
        mListValue = (type == 1) ? Arrays.asList("0", "7", "1", "2", "4", "5")
                : Arrays.asList("0", "1", "3", "2", "4");
        mListShow = (type == 1) ? Arrays.asList("全部分类", "店铺", "塑料制品厂", "原料供应商", "物流服务商", "其他")
                : Arrays.asList("全国", "华东", "华南", "华北", "其他");
    }

    @Override
    public void onBindView(BaseViewHolder holder, int position) {
        TextView tv = holder.getView(R.id.item_tv);
        ImageView iv = holder.getView(R.id.item_iv);
        tv.setText(mListShow.get(position));
        tv.setGravity(Gravity.LEFT);
        tv.setTextColor(Color.BLACK);
        boolean checked = map.get(type) != null && position == map.get(type)
                || map.get(type) == null && position == 0;
        if (checked) {
            iv.setImageResource(R.drawable.icon_check);
        }
        holder.setVisibility(R.id.item_line, position == mListShow.size() - 1
                ? BaseViewHolder.GONE
                : BaseViewHolder.VISIBLE);
    }

    @Override
    public int getLayoutID(int position) {
        return R.layout.hindicator_item_layout;
    }

    @Override
    public boolean clickable() {
        return true;
    }

    @Override
    public void onItemClick(View v, int position) {
        map.put(type, position);
        onItemSelected(mListShow.get(position), mListValue.get(position));
    }

    @Override
    public int getItemCount() {
        return mListShow.size();
    }

    public void onItemSelected(String show, String value) {
    }
}
