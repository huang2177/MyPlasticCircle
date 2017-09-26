package com.myplas.q.common.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.View;
import android.widget.EditText;

import com.myplas.q.common.utils.TextUtils;

/**
 * 作者:huangshuang
 * 事件 2017/9/26 0026.
 * 邮箱： 15378412400@163.com
 */

public class MyEditText extends EditText implements TextWatcher {
    private OnTextWatcher mOnTextWatcher;

    public MyEditText(Context context) {
        super(context);
    }

    public MyEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        addTextChangedListener(this);
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        String string = s.toString();
        if (TextUtils.isNullOrEmpty(string)) {
            mOnTextWatcher.onTextChanged(this, string);
        }
    }

    @Override
    public void afterTextChanged(Editable s) {

    }

    public interface OnTextWatcher {
        void onTextChanged(View v, String s);
    }

    public void addOnTextWatcher(OnTextWatcher onTextWatcher) {
        this.mOnTextWatcher = onTextWatcher;
    }
}
