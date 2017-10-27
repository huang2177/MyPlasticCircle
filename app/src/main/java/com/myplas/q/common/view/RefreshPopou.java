package com.myplas.q.common.view;

import android.app.Activity;
import android.graphics.Color;
import android.os.Handler;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

import com.huangbryant.hindicator.HIndicatorAdapter;
import com.huangbryant.hindicator.HIndicatorBuilder;
import com.huangbryant.hindicator.HIndicatorDialog;
import com.myplas.q.R;
import com.myplas.q.common.utils.ScreenUtils;
import com.myplas.q.common.utils.TextUtils;

/**
 * 作者:huangshuang
 * 事件 2017/10/11 0011.
 * 邮箱： 15378412400@163.com
 */

public class RefreshPopou {
    private Handler mHandler;
    private boolean canShowPopou;
    private StringBuffer defautContent;

    private Activity mContext;
    private HIndicatorDialog dialog;

    public RefreshPopou(Activity content, int type) {
        this.mContext = content;
        mHandler = new Handler();
        if (type == 1) {
            defautContent = new StringBuffer("已是最新头条信息！");
        } else if (type == 2) {
            defautContent = new StringBuffer("已是最新通讯录信息！");
        } else {
            defautContent = new StringBuffer("已是最新供求信息！");
        }
    }

    public void show(View locationView, String content) {
        if (canShowPopou) {
            canShowPopou = false;
            dialog = new HIndicatorBuilder(mContext)
                    .width(ScreenUtils.getScreenWidth(mContext))
                    .height(-1)
                    .ArrowDirection(HIndicatorBuilder.TOP)
                    .bgColor(Color.parseColor("#00000000"))
                    .gravity(HIndicatorBuilder.GRAVITY_LEFT)
                    .radius(0)
                    .animator(R.style.my_anim_popou)
                    .arrowWidth(1)
                    .ArrowRectage(0.1f)
                    .layoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false))
                    .dimEnabled(true)
                    .dimAmount(0f)
                    .adapter(new MyAdapter(content))
                    .create();
            dialog.setCanceledOnTouchOutside(true);
            dialog.show(locationView);
            mHandler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    dialog.dismiss();
                }
            }, 1800);
        }
    }

    public void dismiss() {
        if (dialog != null && dialog.getDialog().isShowing()) {
            dialog.dismiss();
        }
    }


    public void setCanShowPopou(boolean canShowPopou) {
        this.canShowPopou = canShowPopou;
    }

    public boolean isCanShowPopou() {
        return canShowPopou;
    }

    public class MyAdapter extends HIndicatorAdapter {
        String content;

        public MyAdapter(String content) {
            this.content = content;
        }

        @Override
        public void onBindView(BaseViewHolder holder, int position) {
            TextView tv = holder.getView(R.id.text_refresh_fragement);
            tv.setText((TextUtils.isNullOrEmpty(content))
                    ? (content)
                    : (defautContent));
        }

        @Override
        public int getLayoutID(int position) {
            return R.layout.layout_refresh_popou;
        }

        @Override
        public boolean clickable() {
            return true;
        }


        @Override
        public int getItemCount() {
            return 1;
        }
    }
}
