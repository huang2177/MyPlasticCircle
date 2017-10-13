package com.myplas.q.addresslist;

import android.widget.AbsListView;

import com.myplas.q.common.view.CommonDialog;

/**
 * 作者:huangshuang
 * 事件 2017/10/13 0013.
 * 邮箱： 15378412400@163.com
 */

public class MyOnScrollListener implements AbsListView.OnScrollListener {
    private int visibleItemCount;
    private OnLoadMoreListener mOnLoadMoreListener;

    public MyOnScrollListener(OnLoadMoreListener mOnLoadMoreListener) {
        this.mOnLoadMoreListener = mOnLoadMoreListener;
    }

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {
        if (mOnLoadMoreListener != null
                && view.getCount() > visibleItemCount
                && view.getLastVisiblePosition() == view.getCount() - 1
                && scrollState == AbsListView.OnScrollListener.SCROLL_STATE_IDLE) {
            mOnLoadMoreListener.onLoadMore();
        }
    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
        MyOnScrollListener.this.visibleItemCount = visibleItemCount;
    }

    /**
     * 加载更多的监听
     */
    public static interface OnLoadMoreListener {
        public void onLoadMore();
    }

    /**
     * 设置加载监听
     *
     * @param mOnLoadMoreListener
     */
    public void setOnLoadMoreListener(OnLoadMoreListener mOnLoadMoreListener) {
        this.mOnLoadMoreListener = mOnLoadMoreListener;
    }
}
