package com.myplas.q.common.view.marqueeview;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.myplas.q.R;

import java.util.Arrays;
import java.util.List;

/**
 * @author 黄双
 * @date 2017/11/28 0028
 */

public class MarqueeViewHelper<E> {
    private View root;
    private boolean isHided;
    private MarqueeView marqueeView;
    private ImageView ivMarquee, marqueeClose;
    private SimpleMF<String> marqueeFactory;

    private void initMarquee(Context context, final View root) {
        this.root = root;
        if (marqueeView == null) {
            ivMarquee = (ImageView) root.findViewById(R.id.notify_img);
            marqueeView = (MarqueeView) root.findViewById(R.id.marqueeView);
            marqueeClose = (ImageView) root.findViewById(R.id.notify_img_close);

            Glide.with(context).load(R.drawable.icon_voice).into(ivMarquee);

            marqueeClose.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    hide();
                }
            });
        }
    }

    public void onResume(Context context, View root, List datas, MarqueeFactory.OnItemClickListener listener) {
        initMarquee(context, root);
        if (marqueeFactory == null) {
            marqueeFactory = new SimpleMF<>(context);
            marqueeFactory.setOnItemClickListener(listener);
            marqueeView.setMarqueeFactory(marqueeFactory);
        }
        marqueeFactory.setData(datas);

        if (!marqueeView.isFlipping()) {
            marqueeView.startFlipping();
        }

    }

    /**
     * 页面显示时调用
     */
    public void start() {
        if (marqueeView != null
                && !marqueeView.isFlipping()
                && root.getVisibility() == View.VISIBLE) {
            marqueeView.startFlipping();
        }
    }

    /**
     * 隐藏时调用
     */
    public void hide() {
        stop();
        isHided = true;
        if (root != null) {
            root.setVisibility(View.GONE);
        }
    }

    /**
     * 页面挂起时调用 --但不需要隐藏
     */
    public void stop() {
        if (marqueeView != null) {
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

    public boolean isHided() {
        return isHided;
    }
}
