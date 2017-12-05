package com.myplas.q.common.view.marqueeview;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.myplas.q.R;
import com.myplas.q.sockethelper.DefConfigBean;

import java.util.Arrays;
import java.util.List;

/**
 * @author 黄双
 * @date 2017/11/28 0028
 */

public class MarqueeViewHelper<E> {
    private View root;
    private Context context;
    private boolean isPlaying;
    private LinearLayout layout;
    private MarqueeView marqueeView;
    private ImageView ivMarquee, marqueeClose;
    private SimpleMF<String> marqueeFactory;


    public void onResume(Context context, View root, List datas, int type, MarqueeFactory.OnItemClickListener listener) {
        this.root = root;
        this.context = context;
        if (root.getVisibility() == View.GONE) {
            root.setVisibility(View.VISIBLE);
            onResume(datas, listener);
        } else if (root.getVisibility() == View.VISIBLE && type == 1) {
            onResume(datas, listener);
        }
    }

    private void onResume(List datas, MarqueeFactory.OnItemClickListener listener) {
        stop();
        releaseView();
        initMarquee();
        marqueeFactory = new SimpleMF<>(context);
        marqueeFactory.setOnItemClickListener(listener);
        marqueeView.setMarqueeFactory(marqueeFactory);

        marqueeFactory.setData(datas);

        if (!marqueeView.isFlipping()) {
            marqueeView.startFlipping();
        }
    }

    /**
     * 初始化marqueeview
     */
    private void initMarquee() {
        if (ivMarquee == null) {
            layout = (LinearLayout) root.findViewById(R.id.ll_marquee);
            ivMarquee = (ImageView) root.findViewById(R.id.notify_img);
            marqueeClose = (ImageView) root.findViewById(R.id.notify_img_close);

            Glide.with(context).load(R.drawable.icon_voice).into(ivMarquee);
        }
        marqueeView = new MarqueeView(context);

        marqueeView.setFlipInterval(2800);
        marqueeView.setAnimDuration(1800);
        layout.addView(marqueeView);

        marqueeClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hide();
            }
        });
    }

    /**
     * 将marqueeview释放
     */
    private void releaseView() {
        marqueeView = null;
        marqueeFactory = null;
        if (layout != null) {
            layout.removeAllViews();
        }
    }

    /**
     * 页面显示时调用
     */
    public void start() {
        if (marqueeView != null
                && !marqueeView.isFlipping()
                && root.getVisibility() == View.VISIBLE) {
            isPlaying = true;
            marqueeView.startFlipping();
        }
    }

    /**
     * 隐藏时调用
     */
    public void hide() {
        stop();
        if (root != null) {
            root.setVisibility(View.GONE);
        }
    }

    /**
     * 页面挂起时调用 --但不需要隐藏
     */
    public void stop() {
        if (marqueeView != null) {
            isPlaying = false;
            marqueeView.stopFlipping();
        }
    }

    /**
     * 退出程序时调用
     */
    public void onDestroy() {
        hide();
        Glide.clear(ivMarquee);
    }

    public boolean isPlaying() {
        return isPlaying = true;
    }
}
