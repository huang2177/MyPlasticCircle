package com.myplas.q.release.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetDialog;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.myplas.q.R;
import com.myplas.q.guide.activity.BaseActivity;

/**
 * 作者:huangshuang
 * 事件 2017/10/7.
 * 邮箱： 15378412400@163.com
 */

public class StandardFragment extends Fragment implements View.OnClickListener {
    private View view;
    private TextView tvNowF, tvType;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        view = View.inflate(getActivity(), R.layout.fragment_layout_release_standard, null);
        tvNowF = F(R.id.release_ev_nowfutrue);
        tvType = F(R.id.release_ev_type);

        tvNowF.setOnClickListener(this);
        tvType.setOnClickListener(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return view;
    }

    public <T extends View> T F(int id) {
        return (T) view.findViewById(id);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.release_ev_nowfutrue:
                openBottom(1);
                break;
            case R.id.release_ev_type:
                openBottom(2);
                break;
        }
    }

    private void openBottom(int type) {
//        TextView textView1, textView2;
//        View view = View.inflate(getActivity(), R.layout.release_buttom_dialog_layout, null);
//        textView1= (TextView) view.findViewById(R.id.buttom_dialog_tv1);
//        textView2= (TextView) view.findViewById(R.id.buttom_dialog_tv2);
//
//        final MyBottomSheetDialog dialog = new MyBottomSheetDialog(getActivity());
//        dialog.setContentView(view);
//        dialog.show();
    }
}
