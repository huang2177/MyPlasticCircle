package com.myplas.q.homepage.activity;

import android.os.Bundle;
import android.support.animation.SpringAnimation;
import android.support.animation.SpringForce;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.myplas.q.R;
import com.myplas.q.common.utils.ScreenUtils;
import com.myplas.q.common.utils.StatusUtils;
import com.myplas.q.app.activity.BaseActivity;

/**
 * 编写： 黄双
 * 电话：15378412400
 * 邮箱：15378412400@163.com
 * 时间：2017/4/17 20:27
 */
public class DaliySignDialogActivtiy extends BaseActivity {
    private SpringAnimation anim;

    private FrameLayout layout;
    private ImageView imageView, close;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.contact_layout_daliysign);

        int screenHeight = ScreenUtils.getScreenHeight(this);
        int statusBarHeight = StatusUtils.getStatusBarHeight(this);
        int dialogHeight = screenHeight - statusBarHeight;
        getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, dialogHeight == 0
                ? ViewGroup.LayoutParams.MATCH_PARENT
                : dialogHeight);

        layout = F(R.id.root);
        imageView = F(R.id.cover_img);
        close = F(R.id.cover_img_close);

        SpringForce springForce = new SpringForce(0)
                .setDampingRatio(0.45f)
                .setStiffness(70f);
        anim = new SpringAnimation(layout, SpringAnimation.TRANSLATION_Y).setSpring(springForce);
        anim.setStartValue(-ScreenUtils.getScreenHeight(this) / 2 - 750);
        anim.start();

        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (anim != null) {
            anim.cancel();
            anim = null;
        }
    }
}
