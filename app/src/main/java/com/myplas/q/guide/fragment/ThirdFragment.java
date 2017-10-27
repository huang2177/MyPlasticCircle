package com.myplas.q.guide.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.myplas.q.R;
import com.myplas.q.common.utils.SharedUtils;
import com.myplas.q.guide.activity.MainActivity;

/**
 * 编写： 黄双
 * 电话：15378412400
 * 邮箱：15378412400@163.com
 * 时间：2017/5/27 15:53
 */
public class ThirdFragment extends Fragment {
    private Button button;
    private ImageView imageView;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate( R.layout.layout_guide,null,false);
        button= (Button) view.findViewById(R.id.guide_button);
        imageView= (ImageView) view.findViewById(R.id.guide_img);
        imageView.setImageResource(R.drawable.launchimage_headline);
        //跳转到首页-通讯录
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedUtils.getSharedUtils().setBooloean(getActivity(),"isGuided",true);
                startActivity(new Intent(getActivity(), MainActivity.class));
                getActivity().finish();
            }
        });
        return view;
    }
}
