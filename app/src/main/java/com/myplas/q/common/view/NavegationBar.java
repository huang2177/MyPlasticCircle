package com.myplas.q.common.view;

import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Build;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.myplas.q.R;
import com.myplas.q.common.appcontext.Constant;
import com.myplas.q.common.utils.ACache;
import com.myplas.q.common.utils.SharedUtils;
import com.myplas.q.common.utils.TextUtils;
import com.myplas.q.common.view.CommonDialog;
import com.myplas.q.common.view.DragView;
import com.myplas.q.myself.login.LoginActivity;
import com.myplas.q.release.ReleaseActivity;

/**
 * @author 黄双
 * @date 2017/12/19 0019
 */

public class NavegationBar extends LinearLayout implements View.OnClickListener
        , CommonDialog.DialogShowInterface {

    private ImageView mIVRelease;
    private DragView mMsgContact, mMsgSupDem, mMsgMySelf;
    private TextView textviewGq, textviewWd, textviewFx, textviewTxl;
    private ImageView imageviewGq, imageviewWd, imageviewFx, imageviewTxl;
    private LinearLayout layoutGq, layoutTxl, layoutJia, layoutFx, layoutWd;


    private Context context;
    private Resources resources;
    private SharedUtils sharedUtils;
    private OnItemSelectedListener onItemSelectedListener;

    public NavegationBar(Context context) {
        super(context);
        this.context = context;
        init();
    }

    public NavegationBar(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        init();
    }

    public NavegationBar(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        init();
    }

    public void init() {
        resources = context.getResources();
        sharedUtils = SharedUtils.getSharedUtils();
        LayoutInflater.from(context).inflate(R.layout.layout_navegationbar, this, true);

        layoutGq = f(R.id.buttom_linear_gq);
        layoutWd = f(R.id.buttom_linear_wd);
        layoutFx = f(R.id.buttom_linear_fx);
        mIVRelease = f(R.id.buttom_img_jia);
        layoutTxl = f(R.id.buttom_linear_txl);
        layoutJia = f(R.id.buttom_linear_jia);

        imageviewFx = f(R.id.buttom_img_fx);
        imageviewGq = f(R.id.buttom_img_gq);
        imageviewWd = f(R.id.buttom_img_wd);
        imageviewTxl = f(R.id.buttom_img_txl);

        textviewFx = f(R.id.buttom_text_fx);
        textviewGq = f(R.id.buttom_text_gq);
        textviewWd = f(R.id.buttom_text_wd);
        textviewTxl = f(R.id.buttom_text_txl);

        mMsgSupDem = f(R.id.dragview_supdem);
        mMsgMySelf = f(R.id.dragview_myself);
        mMsgContact = f(R.id.dragview_contact);

        layoutFx.setOnClickListener(this);
        layoutWd.setOnClickListener(this);
        layoutGq.setOnClickListener(this);
        layoutJia.setOnClickListener(this);
        layoutTxl.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (!TextUtils.isLogin(context, this)) {
            return;
        }
        switch (v.getId()) {
            case R.id.buttom_linear_gq:
                goToSupDem();
                break;
            case R.id.buttom_linear_txl:
                firstInto();
                break;
            case R.id.buttom_linear_fx:
                goToHeadLine();
                break;
            case R.id.buttom_linear_wd:
                goToMySelf();
                break;
            case R.id.buttom_linear_jia:
                if (onItemSelectedListener != null) {
                    onItemSelectedListener.onItemSelected(-1, mIVRelease);
                }
                break;
            default:
                break;
        }
    }

    /**
     * Clear color.
     */
    public void clearColor() {
        imageviewWd.setImageResource(R.drawable.tabbar_me);
        imageviewGq.setImageResource(R.drawable.tabbar_trade);
        imageviewFx.setImageResource(R.drawable.tabbar_headlines);
        imageviewTxl.setImageResource(R.drawable.tabbar_contacts);

        textviewFx.setTextColor(resources.getColor(R.color.color_gray));
        textviewWd.setTextColor(resources.getColor(R.color.color_gray));
        textviewTxl.setTextColor(resources.getColor(R.color.color_gray));
        textviewGq.setTextColor(resources.getColor(R.color.color_gray));
    }


    /**
     * First into.
     */
    public void firstInto() {
        clearColor();
        imageviewTxl.setImageResource(R.drawable.tabbar_contacts_hl);
        textviewTxl.setTextColor(resources.getColor(R.color.color_red));
        if (onItemSelectedListener != null) {
            onItemSelectedListener.onItemSelected(0, null);
        }
    }

    /**
     * Go to headline.
     */
    public void goToHeadLine() {
        clearColor();
        imageviewFx.setImageResource(R.drawable.tabbar_headlines_hl);
        textviewFx.setTextColor(resources.getColor(R.color.color_red));
        if (onItemSelectedListener != null) {
            onItemSelectedListener.onItemSelected(1, null);
        }
    }

    /**
     * Go to supdem.
     */
    public void goToSupDem() {
        clearColor();
        imageviewGq.setImageResource(R.drawable.tabbar_trade_hl);
        textviewGq.setTextColor(resources.getColor(R.color.color_red));
        if (onItemSelectedListener != null) {
            onItemSelectedListener.onItemSelected(2, null);
        }
    }

    /**
     * Go to myself.
     */
    public void goToMySelf() {
        clearColor();
        imageviewWd.setImageResource(R.drawable.tabbar_mehl);
        textviewWd.setTextColor(resources.getColor(R.color.color_red));
        if (onItemSelectedListener != null) {
            onItemSelectedListener.onItemSelected(3, null);
        }
    }

    /**
     * F t.
     *
     * @param <T> the type parameter
     * @param id  the id
     * @return the t
     */
    public <T extends View> T f(int id) {
        return (T) findViewById(id);
    }

    /**
     * 回调后处理红点消息
     *
     * @param isShowRedDot
     */
    public void showRedDots(boolean isShowRedDot) {
        try {
            boolean showDot = !isShowRedDot || !(sharedUtils.getBoolean(context, Constant.LOGINED));

            int numContact = sharedUtils.getInt(context, Constant.R_CONTACT);
            int numSupDem = sharedUtils.getInt(context, Constant.R_SUPDEM);
            int numMySelf = sharedUtils.getInt(context, Constant.R_SEEME)
                    + sharedUtils.getInt(context, Constant.R_MYORDER)
                    + sharedUtils.getInt(context, Constant.R_PUR_MSG)
                    + sharedUtils.getInt(context, Constant.R_REPLY_MSG)
                    + sharedUtils.getInt(context, Constant.R_INTER_MSG)
                    + sharedUtils.getInt(context, Constant.R_SUPDEM_MSG)
                    + sharedUtils.getInt(context, Constant.R_SYSTEM_MSG);

            mMsgSupDem.setVisibility(showDot || 0 == numSupDem
                    ? View.GONE
                    : View.VISIBLE);
            mMsgMySelf.setVisibility(showDot || 0 == numMySelf
                    ? View.GONE
                    : View.VISIBLE);
            mMsgContact.setVisibility(showDot || 0 == numContact
                    ? View.GONE
                    : View.VISIBLE);

            if (isShowRedDot) {
                mMsgMySelf.setText(numMySelf > 99 ? "..." : numMySelf + "");
                mMsgSupDem.setText(numSupDem > 99 ? "..." : numSupDem + "");
            }
        } catch (Exception e) {
            e.toString();
        }
    }

    @Override
    public void dialogClick(int type) {
        if (type == 4) {
            context.startActivity(new Intent(context, LoginActivity.class));
        }
    }


    public interface OnItemSelectedListener {
        void onItemSelected(int position, View view);
    }

    public void setOnItemSelectedListener(OnItemSelectedListener onItemSelectedListener) {
        this.onItemSelectedListener = onItemSelectedListener;
    }
}
