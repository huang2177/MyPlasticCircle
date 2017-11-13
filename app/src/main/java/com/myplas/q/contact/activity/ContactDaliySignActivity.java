package com.myplas.q.contact.activity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.animation.SpringAnimation;
import android.support.animation.SpringForce;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.huangbryant.calendarview.bean.DateBean;
import com.huangbryant.calendarview.listener.OnPagerChangeListener;
import com.huangbryant.calendarview.weiget.HCalendarView;
import com.myplas.q.R;
import com.myplas.q.common.utils.StatusUtils;
import com.myplas.q.common.view.LoadingDialog;
import com.myplas.q.guide.activity.BaseActivity;

/**
 * @author 黄双
 * @date 2017/11/9 0009
 */

public class ContactDaliySignActivity extends BaseActivity {

    private TextView mTitleCalendar;
    private HCalendarView mHCalendar;
    private ImageView monthLast, monthNext;

    private Button sign;
    private Dialog normalDialog;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StatusUtils.setStatusBar(this, false, false);
        StatusUtils.setStatusTextColor(false, this);
        setContentView(R.layout.activity_layout_contact_daliysign);

        initTileBar();
        setTitle("签到送塑豆");
        setTitleBarBackground(R.color.color_transparent_);

        sign = F(R.id.sign);
        mHCalendar = F(R.id.contact_calendar);
        mTitleCalendar = F(R.id.contact_title);

        mHCalendar.setStartEndDate("2016.12", "2018.12")
                .setInitDate("2017.11")
                .init();

        DateBean d = mHCalendar.getSingleDate();

        mTitleCalendar.setText(d.getSolar()[0] + "年" + d.getSolar()[1] + "月");

        mHCalendar.setOnPagerChangeListener(new OnPagerChangeListener() {
            @Override
            public void onPagerChanged(int[] date) {
                mTitleCalendar.setText(date[0] + "年" + date[1] + "月");
            }
        });

        sign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ContactDaliySignActivity.this, DaliySignDialogActivtiy.class));
            }
        });
    }

    /**
     * 上一个月
     *
     * @param view
     */
    public void lastMonth(View view) {
        mHCalendar.lastMonth();
    }

    /**
     * 下一个月
     *
     * @param view
     */
    public void nextMonth(View view) {
        mHCalendar.nextMonth();
    }
}
