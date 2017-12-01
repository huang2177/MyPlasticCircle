package com.myplas.q.common.view.marqueeview;

import android.content.Context;
import android.widget.TextView;

import com.myplas.q.sockethelper.DefConfigBean;


/**
 * @author 黄双
 * @date 17/9/15
 */
public class SimpleMF<E extends Object> extends MarqueeFactory<TextView, E> {
    public SimpleMF(Context mContext) {
        super(mContext);
    }

    @Override
    public TextView generateMarqueeItemView(E data) {
        TextView mView = new TextView(mContext);
        DefConfigBean.NoticeBean.CommunicateContentBean bean1;
        DefConfigBean.NoticeBean.ToutiaoContentBean bean2;
        DefConfigBean.NoticeBean.PurchaseContentBean bean3;
        if (data instanceof DefConfigBean.NoticeBean.CommunicateContentBean) {
            bean1 = (DefConfigBean.NoticeBean.CommunicateContentBean) data;
            mView.setText(bean1.getInfo());
        } else if (data instanceof DefConfigBean.NoticeBean.ToutiaoContentBean) {
            bean2 = (DefConfigBean.NoticeBean.ToutiaoContentBean) data;
            mView.setText(bean2.getInfo());
        } else {
            bean3 = (DefConfigBean.NoticeBean.PurchaseContentBean) data;
            mView.setText(bean3.getInfo());
        }
        return mView;
    }
}