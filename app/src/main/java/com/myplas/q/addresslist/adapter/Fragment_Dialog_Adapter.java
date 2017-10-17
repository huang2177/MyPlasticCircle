package com.myplas.q.addresslist.adapter;

import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.huangbryant.hindicator.HIndicatorAdapter;
import com.myplas.q.R;

import java.util.Arrays;
import java.util.List;

/**
 * Created by 黄双 on 2017/10/15.
 */

public abstract class Fragment_Dialog_Adapter extends HIndicatorAdapter {
    private int type;
    private List<String> mListShow;
    private List<String> mListValue;

    public Fragment_Dialog_Adapter(int type) {
        this.type = type;
        mListValue = (type == 1) ? Arrays.asList("0", "1", "2", "4", "5")
                : Arrays.asList("0", "1", "3", "2", "4");
        mListShow = (type == 1) ? Arrays.asList("全部分类", "塑料制品厂", "原料供应商", "物流服务商", "其他")
                : Arrays.asList("全国", "华东", "华南", "华北", "其他");
    }

    @Override
    public void onBindView(BaseViewHolder holder, int position) {
        TextView tv = holder.getView(R.id.item_tv);
        tv.setText(mListShow.get(position));
        tv.setGravity(Gravity.LEFT);
        tv.setTextColor(Color.BLACK);
        //tv.setCompoundDrawablesWithIntrinsicBounds(mICons.get(position), 0, 0, 0);
        if (position == mListShow.size() - 1) {
            holder.setVisibility(R.id.item_line, BaseViewHolder.GONE);
        } else {
            holder.setVisibility(R.id.item_line, BaseViewHolder.VISIBLE);
        }
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
        onItemSelected(mListShow.get(position), mListValue.get(position));
    }

    @Override
    public int getItemCount() {
        return mListShow.size();
    }

    public void onItemSelected(String show, String value) {
    }
}
