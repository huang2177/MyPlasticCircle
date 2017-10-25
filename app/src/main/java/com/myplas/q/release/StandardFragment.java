package com.myplas.q.release;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.google.gson.Gson;
import com.myplas.q.R;
import com.myplas.q.common.api.API;
import com.myplas.q.common.appcontext.ActivityManager;
import com.myplas.q.common.appcontext.Constant;
import com.myplas.q.common.netresquset.ResultCallBack;
import com.myplas.q.common.utils.SharedUtils;
import com.myplas.q.common.utils.TextUtils;
import com.myplas.q.common.view.MyBottomSheetDialog;
import com.myplas.q.guide.activity.BaseActivity;
import com.myplas.q.guide.activity.MainActivity;
import com.myplas.q.myself.supdem.MySupDemActivity;
import com.myplas.q.release.bean.SecondPurBean;
import com.myplas.q.supdem.activity.SupDem_Detail_Activity;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * 作者:huangshuang
 * 事件 2017/10/7.
 * 邮箱： 15378412400@163.com
 */

public class StandardFragment extends Fragment implements View.OnClickListener
        , ResultCallBack {
    private View view;
    private MyBottomSheetDialog dialog;
    private EditText mEditModel, mEditF_Name, mEdit_JH, mEditPirce, mTV_NF, mTV_Type;

    private int which = 0;
    private SharedUtils mSharedUtils;
    private String mode, model, production, jhd, pirce, nf, type, id;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initView();
    }

    private void initView() {
        mode = "2";
        mSharedUtils = SharedUtils.getSharedUtils();
        id = getActivity().getIntent().getStringExtra("id");
        view = View.inflate(getActivity(), R.layout.fragment_layout_release_standard, null);

        mEdit_JH = f(R.id.release_ev_jh);
        mTV_Type = f(R.id.release_ev_type);
        mEditModel = f(R.id.release_ev_mode);
        mEditPirce = f(R.id.release_ev_pirce);
        mTV_NF = f(R.id.release_ev_nowfutrue);
        mEditF_Name = f(R.id.release_ev_production);

        mTV_NF.setOnClickListener(this);
        mTV_Type.setOnClickListener(this);
        if (id != null) {
            mode = "3";
            secondPub();
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return view;
    }

    public <T extends View> T f(int id) {
        return (T) view.findViewById(id);
    }

    public void pub() {
        if (!isInputContent(2)) {
            return;
        }
        Map<String, String> map = new HashMap<>();
        if (id != null) {
            map.put("id", id);
        }
        map.put("mode", mode);
        map.put("type", type);
        map.put("content", "");
        map.put("channel", "1");
        map.put("model", model);
        map.put("price", pirce);
        map.put("storehouse", jhd);
        map.put("is_preview", "0");
        map.put("vendor", production);
        map.put("transaction_type", nf);
        BaseActivity.postAsyn(getActivity(), API.BASEURL + API.PUB, map, this, 1);
    }

    public void secondPub() {
        Map<String, String> map = new HashMap<String, String>();
        map.put("id", id);
        BaseActivity.postAsyn(getActivity(), API.BASEURL + API.SECOND_PUB, map, this, 2);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.release_ev_nowfutrue:
                which = 0;
                openBottom(which);
                break;
            case R.id.release_ev_type:
                which = 1;
                openBottom(which);
                break;
            case R.id.buttom_dialog_tv1:
                dialog.dismiss();
                if (which == 0) {
                    mTV_NF.setText("现货");
                } else {
                    mTV_Type.setText("供给");
                }

                break;
            case R.id.buttom_dialog_tv2:
                dialog.dismiss();
                if (which == 0) {
                    mTV_NF.setText("期货");
                } else {
                    mTV_Type.setText("求购");
                }
                break;
            default:
                break;
        }
    }

    private void openBottom(int type) {
        TextView textView1, textView2;
        View view = View.inflate(getActivity(), R.layout.release_buttom_dialog_layout, null);
        textView1 = (TextView) view.findViewById(R.id.buttom_dialog_tv1);
        textView2 = (TextView) view.findViewById(R.id.buttom_dialog_tv2);

        textView1.setOnClickListener(this);
        textView2.setOnClickListener(this);
        textView1.setText(type == 0 ? "现货" : "供给");
        textView2.setText(type == 0 ? "期货" : "求购");

        dialog = new MyBottomSheetDialog(getActivity());
        dialog.setContentView(view);
        dialog.show();
    }

    @Override
    public void callBack(Object object, int type) {
        try {
            Gson gson = new Gson();
            String err = new JSONObject(object.toString()).getString("err");
            if (type == 1) {
                if ("0".equals(err)) {
                    //关闭activity
                    MainActivity mainActivity = (MainActivity) ActivityManager.getActivity(MainActivity.class);
                    mainActivity.goToSupDem();

                    //跳转到供求详情
                    Intent intent1 = new Intent(getActivity(), SupDem_Detail_Activity.class);
                    intent1.putExtra("id", new JSONObject(object.toString()).getString("id"));
                    intent1.putExtra("userid", mSharedUtils.getData(getActivity(), Constant.USERID));
                    startActivity(intent1);

                    ActivityManager.finishActivity(ReleaseActivity.class);
                    if (id != null) {
                        ActivityManager.finishActivity(MySupDemActivity.class);
                    }
                }else {
                    TextUtils.Toast(getActivity(), new JSONObject(object.toString()).getString("msg"));
                }
            }
            if (type == 2) {
                if (err.equals("0")) {
                    SecondPurBean secondPurBean = gson.fromJson(object.toString(), SecondPurBean.class);
                    this.type = secondPurBean.getData().getType();
                    mEditPirce.setText(secondPurBean.getData().getPrice());
                    mEditModel.setText(secondPurBean.getData().getModel());
                    mEditF_Name.setText(secondPurBean.getData().getVendor());
                    mEdit_JH.setText(secondPurBean.getData().getStorehouse());

                    mTV_Type.setText(secondPurBean.getData().getType().equals("2")
                            ? "供给"
                            : "求购");
                    mTV_NF.setText(secondPurBean.getData().getTransaction_type().equals("0")
                            ? "现货"
                            : "期货");

                    mEdit_JH.setSelection(mEdit_JH.getText().length());
                    mEditModel.setSelection(mEditModel.getText().length());
                    mEditPirce.setSelection(mEditPirce.getText().length());
                    mEditF_Name.setSelection(mEditF_Name.getText().length());
                }
            }
        } catch (Exception e) {
        }
    }

    @Override
    public void failCallBack(int type) {

    }

    public boolean isInputContent(int _type) {
        jhd = mEdit_JH.getText().toString();
        model = mEditModel.getText().toString();
        pirce = mEditPirce.getText().toString();
        String t = mTV_Type.getText().toString();
        String nowF = mTV_NF.getText().toString();
        production = mEditF_Name.getText().toString();
        nf = ("现货".equals(nowF)) ? ("0") : ("1");
        type = ("供给".equals(t)) ? ("2") : ("1");

        if (_type == 1) {
            return !TextUtils.isNullOrEmpty(jhd)
                    && !TextUtils.isNullOrEmpty(t)
                    && !TextUtils.isNullOrEmpty(nowF)
                    && !TextUtils.isNullOrEmpty(model)
                    && !TextUtils.isNullOrEmpty(pirce)
                    && !TextUtils.isNullOrEmpty(production) ? false : true;

        } else {
            if (!TextUtils.isNullOrEmpty(jhd)
                    || !TextUtils.isNullOrEmpty(t)
                    || !TextUtils.isNullOrEmpty(nowF)
                    || !TextUtils.isNullOrEmpty(model)
                    || !TextUtils.isNullOrEmpty(pirce)
                    || !TextUtils.isNullOrEmpty(production)) {
                TextUtils.Toast(getActivity(), "请输入完整的数据！");
                return false;
            } else {
                return true;
            }
        }

    }
}
