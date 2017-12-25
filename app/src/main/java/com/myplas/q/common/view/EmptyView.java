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
    private ImageView ivEmpetyMageger;
    private LinearLayout llNoMessageRoot;
    private TextView tvNoMessage, tvMessageBold;

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
        View view = inflater.inflate(R.layout.emptyview_ll_layout, null);
        tvNoMessage = (TextView) view.findViewById(R.id.noresult_text);
        tvMessageBold = (TextView) view.findViewById(R.id.noresult_bold_text);
        ivEmpetyMageger = (ImageView) view.findViewById(R.id.noresult_image);
        llNoMessageRoot = (LinearLayout) view.findViewById(R.id.ll_no_message_root);
        addView(view);
    }

    /**
     * 一定要调用该方法
     *
     * @param
     */
    public View mustCallInitWay(View view) {
        if (view != null) {
            ViewGroup.LayoutParams params = llNoMessageRoot.getLayoutParams();
            params.width = ScreenUtils.getScreenWidth(getContext());
            params.height = ScreenUtils.getScreenHeight(getContext()) - ScreenUtils.dp2px(getContext(), 50);
            ((ViewGroup) view.getParent()).addView(this, params);
        }
        return this;
    }

    public void setNoMessageText(CharSequence text) {
        tvNoMessage.setText(text);
        tvNoMessage.setVisibility(View.VISIBLE);
    }

    public void setNoMessageText1(CharSequence text) {
        tvMessageBold.setText(text);
        tvMessageBold.setVisibility(View.VISIBLE);
    }

    /********修改文字的颜色**********/
    public void setMessageTextColor2(int colorResId) {
        tvNoMessage.setTextColor(colorResId);
        tvNoMessage.setVisibility(View.VISIBLE);
    }

    /**
     * 显示不同的文字提示及其图片提示
     */
    public View setMyManager(String showNoTip, int showNoIamgeViewResId, int textSize, int colorResId) {
        ivEmpetyMageger.setImageResource(showNoIamgeViewResId);
        tvNoMessage.setTextColor(colorResId);
        tvNoMessage.setText(showNoTip);
        tvNoMessage.setTextSize(textSize);
        tvNoMessage.setVisibility(View.VISIBLE);
        return this;
    }

    /**
     * 显示不同的文字提示及其图片提示
     */
    public View setMyManager(String showNoTip, int showNoIamgeViewResId) {
        ivEmpetyMageger.setImageResource(showNoIamgeViewResId);
        tvNoMessage.setText(showNoTip);
        tvNoMessage.setVisibility(View.VISIBLE);
        return this;
    }

    /**
     * 显示不同的文字提示及其图片提示
     */
    public View setMyManager(int showNoIamgeViewResId) {
        ivEmpetyMageger.setImageResource(showNoIamgeViewResId);
        ivEmpetyMageger.setVisibility(View.VISIBLE);
        return this;
    }

    /**
     * 显示不同的文字提示
     */
    public View setMyManager(String showNoTip) {
        tvNoMessage.setText(showNoTip);
        tvNoMessage.setVisibility(View.VISIBLE);
        return this;
    }

    /**
     * 是否显示文字
     */
    public View isShowTextTipMassager(boolean isShow) {
        if (isShow) {
            tvNoMessage.setVisibility(View.VISIBLE);
        }
        return this;
    }

    /**
     * 是否显示文字
     */
    public View isShowTextTipMassager(boolean isShow, String showNoTip) {
        if (isShow) {
            tvNoMessage.setVisibility(View.VISIBLE);
            tvNoMessage.setText(showNoTip);
        } else {
            tvNoMessage.setVisibility(View.GONE);
            tvNoMessage.setText(showNoTip);
        }
        return this;
    }

    /**
     * 是否显示图片
     */
    public View isShowIamgeMassager(boolean isShow, int showNoIamgeViewResId) {
        if (isShow) {
            ivEmpetyMageger.setVisibility(View.VISIBLE);
            ivEmpetyMageger.setImageResource(showNoIamgeViewResId);
        } else {
            ivEmpetyMageger.setVisibility(View.GONE);
        }
        return this;
    }

    /**
     * 是否显示图片
     */
    public View isShowIamgeMassager(boolean isShow) {
        if (isShow) {
            ivEmpetyMageger.setVisibility(View.VISIBLE);
        } else {
            ivEmpetyMageger.setVisibility(View.GONE);
        }
        return this;
    }
}
