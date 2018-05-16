package com.myplas.q.myself.login;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;

import com.bigkoo.pickerview.OptionsPickerView;
import com.bigkoo.pickerview.lib.WheelView;
import com.google.gson.Gson;
import com.myplas.q.R;
import com.myplas.q.app.fragment.BaseFragment;
import com.myplas.q.common.api.API;
import com.myplas.q.common.appcontext.Constant;
import com.myplas.q.common.listener.BaseInterface;
import com.myplas.q.common.net.ResultCallBack;
import com.myplas.q.common.utils.ACache;
import com.myplas.q.common.utils.SharedUtils;
import com.myplas.q.common.utils.TextUtils;
import com.myplas.q.common.view.MyEditText;

import org.json.JSONObject;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 黄双
 * @date 2017/11/1 0001
 */

public class FragmentRegister2 extends BaseFragment implements View.OnClickListener
        , ResultCallBack
        , MyEditText.OnTextWatcher {
    private View mView;
    private Button buttonNext;
    private MyEditText mName, mCompany, mCompanyType;

    public BaseInterface mBaseInterface;

    private List<String> mList;
    private OptionsPickerView pvOptions;
    private String phone, passWord, indentify, companyType;

    private ACache mACache;
    private SharedUtils mShareUtils;

    public static FragmentRegister2 newInstance() {
        FragmentRegister2 fragment = new FragmentRegister2();
        return fragment;

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mACache = ACache.get(getActivity());
        mShareUtils = SharedUtils.getSharedUtils();
        mList = Arrays.asList("塑料制品厂", "原料供应商", "物流服务商");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        mView = View.inflate(container.getContext(), R.layout.fragment_register2_layout, null);
        buttonNext = (Button) mView.findViewById(R.id.register_ok);
        mName = (MyEditText) mView.findViewById(R.id.register_name);
        mCompany = (MyEditText) mView.findViewById(R.id.register_company);
        mCompanyType = (MyEditText) mView.findViewById(R.id.register_type);

        buttonNext.setOnClickListener(this);
        mCompanyType.setOnClickListener(this);
        mCompanyType.setOnClickListener(this);

        mName.addOnTextWatcher(this);
        mCompany.addOnTextWatcher(this);
        mCompanyType.addOnTextWatcher(this);

        return mView;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.register_ok:
                buttonNext.setClickable(false);
                getNetData();
                break;
            case R.id.register_type:
                openButtomDialog();
                break;
            default:
                break;
        }
    }

    @Override
    public void onTextChanged(View v, String s) {
        buttonNext.setClickable(true);
        boolean isNomalNull = TextUtils.notEmpty(mName.getText().toString())
                && TextUtils.notEmpty(mCompany.getText().toString())
                && TextUtils.notEmpty(mCompanyType.getText().toString());
        buttonNext.setBackgroundResource(isNomalNull
                ? R.drawable.login_btn_shape_hl
                : R.drawable.login_btn_shape);
    }

    //点击确定
    public void getNetData() {
        String name = mName.getText().toString();
        String company = mCompany.getText().toString();

        if (!TextUtils.notEmpty(companyType)
                || !TextUtils.notEmpty(name)
                || !TextUtils.notEmpty(company)) {

            TextUtils.toast(getActivity(), "请输入完整信息！");
            return;
        }

        Map<String, String> map = new HashMap<String, String>(20);
        map.put("code", "");
        map.put("name", name);
        map.put("chanel", "6");
        map.put("mobile", phone);
        map.put("quan_type", "1");
        map.put("c_name", company);
        map.put("password", passWord);
        map.put("c_type", companyType);

        postAsyn(getActivity(), API.REGISTER, map, this, 1, false);
    }

    private void openButtomDialog() {
        InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(mName.getWindowToken(), 0);
        if (pvOptions == null) {
            pvOptions = new OptionsPickerView.Builder(getActivity(), new OptionsPickerView.OnOptionsSelectListener() {
                @Override
                public void onOptionsSelect(int options1, int option2, int options3, View v) {
                    companyType = options1 == 0
                            ? "1"
                            : options1 == 1 ? "2" : "4";
                    mCompanyType.setText(mList.get(options1));
                }
            })
                    .setSubmitText("确定")//确定按钮文字
                    .setCancelText("取消")//取消按钮文字
                    .setTitleText("企业类型选择")//标题
                    .setSubCalSize(16)//确定和取消文字大小
                    .setTitleSize(18)//标题文字大小
                    .setLineSpacingMultiplier(1.5f)
                    .setDividerType(WheelView.DividerType.FILL)
                    .setTitleColor(Color.BLACK)//标题文字颜色
                    .setSubmitColor(Color.parseColor("#0893b9"))//确定按钮文字颜色
                    .setCancelColor(Color.parseColor("#0893b9"))//取消按钮文字颜色
                    .setTitleBgColor(Color.parseColor("#ffffff"))//标题背景颜色 Night mode
                    .setBgColor(Color.WHITE)//滚轮背景颜色 Night mode
                    .setContentTextSize(20)//滚轮文字大小
                    .setDividerColor(Color.WHITE)
                    .setTextColorCenter(Color.BLACK)
                    .setTextColorOut(Color.LTGRAY)
                    .setLinkage(true)//设置是否联动，默认true
                    .setLabels("", "", "")//设置选择的三级单位
                    .setCyclic(false, false, false)//循环与否
                    .setSelectOptions(0)  //设置默认选中项
                    .setOutSideCancelable(true)//点击外部dismiss default true
                    .isDialog(false)//是否显示为对话框样式
                    .build();
            pvOptions.setPicker(mList);//添加数据源
            pvOptions.show();
        } else {
            pvOptions.show();
        }
    }

    @Override
    public void callBack(Object object, int type) {
        try {
            JSONObject jsonObject = new JSONObject(object.toString());
            if (type == 1) {
                if ("0".equals(jsonObject.getString("code"))) {
                    Gson gson = new Gson();
                    RegisterBean bean = gson.fromJson(object.toString(), RegisterBean.class);
                    setRedDots(bean);
                    if (mBaseInterface != null) {
                        mBaseInterface.complete(2, null);
                        mBaseInterface.dataBack(this, Arrays.asList(bean.getRegister_ranking()));
                    }
                }
            }
        } catch (Exception e) {
        }
    }

    /**
     * 保存token userId
     *
     * @param object
     */
    private void setRedDots(RegisterBean bean) {
        mShareUtils.setData(getActivity(), Constant.TOKEN, bean.getToken());
        mShareUtils.setBooloean(getActivity(), Constant.LOGINED, true);
        mShareUtils.setData(getActivity(), Constant.USERID, bean.getUser_id());
    }

    @Override
    public void failCallBack(int type, String message, int httpCode) {
        try {
            JSONObject jsonObject = new JSONObject(message);
            buttonNext.setClickable(true);
            buttonNext.setBackgroundResource(R.drawable.login_btn_shape_hl);
            TextUtils.toast(getActivity(), jsonObject.getString("message"));
        } catch (Exception e) {

        }
    }

    /**
     * 从前一个页面传过来的数据
     *
     * @param agrs0
     */
    public void setData(List agrs) {
        if (agrs != null && agrs.size() != 0) {
            phone = agrs.get(0).toString();
            passWord = agrs.get(1).toString();
            indentify = agrs.get(2).toString();
        }
    }
}
