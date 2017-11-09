package com.myplas.q.contact.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.myplas.q.R;
import com.myplas.q.common.view.datehelper.CalendarPickerView;
import com.myplas.q.guide.activity.BaseActivity;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 * @author 黄双
 * @date 2017/11/9 0009
 */

public class ContactDaliySignActivity extends BaseActivity {

    private CalendarPickerView mPickerView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_layout_contact_daliysign);
        mPickerView = F(R.id.calendar_view);

        Calendar calendar = Calendar.getInstance();
        Calendar calendar1 = Calendar.getInstance();
        int lastYear = calendar.get(Calendar.YEAR) - 1;
        int nextYear = calendar.get(Calendar.YEAR) + 1;
        calendar.set(lastYear, calendar.get(Calendar.MONTH), calendar.get(Calendar.DATE));
        calendar1.set(nextYear, calendar.get(Calendar.MONTH), calendar.get(Calendar.DATE));
        mPickerView.init(calendar.getTime()
                , calendar1.getTime()
                , new ArrayList<String>())
                .inMode(CalendarPickerView.SelectionMode.SINGLE);
    }
}
