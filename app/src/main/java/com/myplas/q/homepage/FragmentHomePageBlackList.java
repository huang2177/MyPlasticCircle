package com.myplas.q.homepage;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;

import com.myplas.q.R;
import com.myplas.q.app.fragment.BaseFragment;
import com.myplas.q.common.view.RoundCornerImageView;
import com.myplas.q.homepage.adapter.BlackListAdapter;

/**
 * @author 黄双
 * @date 2018/1/11 0011
 */

public class FragmentHomePageBlackList extends BaseFragment {
    private View view;
    private RecyclerView mRecyclerView;
    private SwipeRefreshLayout mRefreshLayout;
    private ImageView mActionButton;

    private BlackListAdapter mAdapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        view = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_layout_homepage_blacklist, null, false);
        mRecyclerView = F(view, R.id.blacklist_listview);
        mActionButton = F(view, R.id.blacklist_floatingbtn);
        mRefreshLayout = F(view, R.id.blacklist_refreshlayout);

        mRefreshLayout.setColorSchemeColors(ContextCompat.getColor(getActivity(), R.color.color_red));

        LinearLayoutManager manager = new LinearLayoutManager(getActivity());
        mAdapter = new BlackListAdapter(getActivity());
        mRecyclerView.setLayoutManager(manager);
        mRecyclerView.setAdapter(mAdapter);

        mActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return view;
    }

    /**
     * 监听是否对用户可见
     *
     * @param isVisibleToUser
     */
    public void setUserVisible(boolean isVisibleToUser) {
        if (mActionButton == null) {
            return;
        }
        if (isVisibleToUser) {
            ObjectAnimator animator = ObjectAnimator.ofFloat(mActionButton, "scaleX", 0f, 1f);
            ObjectAnimator animator1 = ObjectAnimator.ofFloat(mActionButton, "scaleY", 0f, 1f);
            AnimatorSet set = new AnimatorSet();
            set.play(animator).with(animator1);
            set.setDuration(500);
            set.start();
        } else {
            ObjectAnimator animator = ObjectAnimator.ofFloat(mActionButton, "scaleX", 1f, 0f);
            ObjectAnimator animator1 = ObjectAnimator.ofFloat(mActionButton, "scaleY", 1f, 0f);
            AnimatorSet set = new AnimatorSet();
            set.play(animator).with(animator1);
            set.setDuration(500);
            set.start();
        }
    }
}
