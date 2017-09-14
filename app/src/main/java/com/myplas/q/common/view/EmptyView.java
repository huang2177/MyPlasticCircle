package com.myplas.q.common.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.myplas.q.R;
import com.myplas.q.common.utils.ScreenUtils;

/**
 * 作者：  黄双
 * 事件 2017/9/12 0012.
 * 邮箱： 15378412400@163.com
 */

public class EmptyView extends LinearLayout {
    private TextView tv_no_message;
    private ImageView iv_empety_mageger;
    private LinearLayout ll_no_message_root;

    public EmptyView(Context context) {
        super(context);
        init();
        // TODO Auto-generated constructor stub
    }

    public EmptyView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        // TODO Auto-generated method stub
        LayoutInflater inflater = LayoutInflater.from(getContext());
        View view = inflater.inflate(R.layout.linearlayout_noresult, null);
        iv_empety_mageger = (ImageView) view.findViewById(R.id.noresult_image);
        tv_no_message = (TextView) view.findViewById(R.id.noresult_text);
        ll_no_message_root = (LinearLayout) view.findViewById(R.id.ll_no_message_root);
        addView(view);

    }

    /**
     * 一定要调用该方法
     *
     * @param
     */
    public View mustCallInitWay(View view) {
        if (view != null) {
            ViewGroup.LayoutParams params = ll_no_message_root.getLayoutParams();
            params.width = ScreenUtils.getScreenWidth(getContext());
            params.height = ScreenUtils.getScreenHeight(getContext()) - ScreenUtils.dp2px(getContext(), 50);
            ((ViewGroup) view.getParent()).addView(this, params);
        }
        return this;
    }

    public void setNoMessageText(CharSequence text) {
        tv_no_message.setText(text);
        tv_no_message.setVisibility(View.VISIBLE);
    }

    /********修改文字的颜色**********/
    public void setMessageTextColor2(int colorResId) {
        tv_no_message.setTextColor(colorResId);
        tv_no_message.setVisibility(View.VISIBLE);
    }

    /**
     * 显示不同的文字提示及其图片提示
     */
    public View setMyManager(String showNoTip, int showNoIamgeViewResId, int textSize, int colorResId) {
        iv_empety_mageger.setImageResource(showNoIamgeViewResId);
        tv_no_message.setTextColor(colorResId);
        tv_no_message.setText(showNoTip);
        tv_no_message.setTextSize(textSize);
        tv_no_message.setVisibility(View.VISIBLE);
        return this;
    }

    /**
     * 显示不同的文字提示及其图片提示
     */
    public View setMyManager(String showNoTip, int showNoIamgeViewResId) {
        iv_empety_mageger.setImageResource(showNoIamgeViewResId);
        tv_no_message.setText(showNoTip);
        tv_no_message.setVisibility(View.VISIBLE);
        return this;
    }

    /**
     * 显示不同的文字提示及其图片提示
     */
    public View setMyManager(int showNoIamgeViewResId) {
        iv_empety_mageger.setImageResource(showNoIamgeViewResId);
        iv_empety_mageger.setVisibility(View.VISIBLE);
        return this;
    }

    /**
     * 显示不同的文字提示
     */
    public View setMyManager(String showNoTip) {
        tv_no_message.setText(showNoTip);
        tv_no_message.setVisibility(View.VISIBLE);
        return this;
    }

    /**
     * 是否显示文字
     */
    public View isShowTextTipMassager(boolean isShow) {
        if (isShow) {
            tv_no_message.setVisibility(View.VISIBLE);
        }
        return this;
    }

    /**
     * 是否显示文字
     */
    public View isShowTextTipMassager(boolean isShow, String showNoTip) {
        if (isShow) {
            tv_no_message.setVisibility(View.VISIBLE);
            tv_no_message.setText(showNoTip);
        } else {
            tv_no_message.setVisibility(View.GONE);
            tv_no_message.setText(showNoTip);
        }
        return this;
    }

    /**
     * 是否显示图片
     */
    public View isShowIamgeMassager(boolean isShow, int showNoIamgeViewResId) {
        if (isShow) {
            iv_empety_mageger.setVisibility(View.VISIBLE);
            iv_empety_mageger.setImageResource(showNoIamgeViewResId);
        } else {
            iv_empety_mageger.setVisibility(View.GONE);
        }
        return this;
    }

    /**
     * 是否显示图片
     */
    public View isShowIamgeMassager(boolean isShow) {
        if (isShow) {
            iv_empety_mageger.setVisibility(View.VISIBLE);
        } else {
            iv_empety_mageger.setVisibility(View.GONE);
        }
        return this;
    }
}
