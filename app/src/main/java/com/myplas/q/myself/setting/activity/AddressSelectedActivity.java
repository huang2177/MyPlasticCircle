package com.myplas.q.myself.setting.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bigkoo.pickerview.OptionsPickerView;
import com.bigkoo.pickerview.lib.WheelView;
import com.google.gson.Gson;
import com.myplas.q.R;
import com.myplas.q.common.api.API;
import com.myplas.q.common.netresquset.ResultCallBack;
import com.myplas.q.common.utils.NetUtils;
import com.myplas.q.common.utils.TextUtils;
import com.myplas.q.app.activity.BaseActivity;
import com.myplas.q.common.view.EditTextField;
import com.myplas.q.myself.beans.RegionsBean;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


/**
 * 编写： 黄双
 * 电话：15378412400
 * 邮箱：15378412400@163.com
 * 时间：2017/3/28 10:25
 */
public class AddressSelectedActivity extends BaseActivity implements ResultCallBack, View.OnClickListener {
    private TextView mTextViewOK;
    private ImageView mImageView;
    private EditTextField mTextField1, mTextField2;
    private String address, addressId, dataBack, logisticsStartData, logisticsEndData;

    private RegionsBean mBean;
    private List<RegionsBean.DataBean> mPList;
    private List<List<RegionsBean.DataBean.ChildrenBeanX>> mCList;
    private List<List<List<RegionsBean.DataBean.ChildrenBeanX.ChildrenBean>>> mDList;

    private boolean isFormatAdd;
    private LinearLayout mLayout;
    private int options1, options2, options3;
    private String tx, inPut, optionsId1, optionsId2, optionsId3, StringData;

    private OptionsPickerView pvOptions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_layout_setting_addressselected);
        init();
        showInfo();
        getCityNetData(1);
    }

    private void init() {
        initTileBar();
        setRightTVVisibility(View.VISIBLE);
        setTitle(getIntent().getStringExtra("title"));
        address = getIntent().getStringExtra("address");
        addressId = getIntent().getStringExtra("location");

        mPList = new ArrayList<>();
        mCList = new ArrayList<>();
        mDList = new ArrayList<>();
        mImageView = F(R.id.address_img);
        mLayout = F(R.id.addselected_ll);
        mTextViewOK = F(R.id.titlebar_text_right);
        mTextField2 = F(R.id.address_edit_ending);
        mTextField1 = F(R.id.address_edit_starting);

        mLayout.setOnClickListener(this);
        mTextViewOK.setOnClickListener(this);
        mTextField1.setOnClickListener(this);
    }


    private void showInfo() {
        //showInPutKeybord();
        isFormatAdd = "|".equals(addressId);
        if (address.contains("|") && !isFormatAdd) {
            tx = address.substring(0, address.indexOf("|"));
            mTextField1.setText(tx);
            mTextField2.setText(address.substring(address.indexOf("|") + 1));
            mTextField2.setSelection((address.substring(address.indexOf("|") + 1)).length());
        } else {
            mTextField2.setText(address);
            mTextField2.setSelection(address.length());
        }
    }

    public void getCityNetData(int type) {
        String url = API.BASEURL + API.GET_ALL_REGIONS;
        postAsyn(this, url, null, this, type);
    }

    private void selectAddress() {
        if (pvOptions == null) {
            pvOptions = new OptionsPickerView.Builder(this, new OptionsPickerView.OnOptionsSelectListener() {
                @Override
                public void onOptionsSelect(int options1, int option2, int options3, View v) {
                    //返回的分别是三个级别的选中位置
                    tx = mPList.get(options1).getLabel()
                            + mCList.get(options1).get(option2).getLabel()
                            + mDList.get(options1).get(option2).get(options3).getLabel();
                    addressId = mPList.get(options1).getValue() + "|"
                            + mCList.get(options1).get(option2).getValue() + "|"
                            + mDList.get(options1).get(option2).get(options3).getValue();
                    mTextField1.setText(tx);
                }
            })
                    .setSubmitText("确定")//确定按钮文字
                    .setCancelText("取消")//取消按钮文字
                    .setTitleText("地址选择")//标题
                    .setSubCalSize(16)//确定和取消文字大小
                    .setTitleSize(18)//标题文字大小
                    .setLineSpacingMultiplier(2f)
                    .setDividerType(WheelView.DividerType.WRAP)
                    .setTitleColor(Color.BLACK)//标题文字颜色
                    .setSubmitColor(Color.parseColor("#0893b9"))//确定按钮文字颜色
                    .setCancelColor(Color.parseColor("#0893b9"))//取消按钮文字颜色
                    .setTitleBgColor(Color.parseColor("#e9e9e9"))//标题背景颜色 Night mode
                    .setBgColor(Color.WHITE)//滚轮背景颜色 Night mode
                    .setContentTextSize(18)//滚轮文字大小
                    .setDividerColor(Color.LTGRAY)
                    .setTextColorCenter(Color.BLACK)
                    .setTextColorOut(Color.LTGRAY)
                    .setLinkage(true)//设置是否联动，默认true
                    .setLabels("", "", "")//设置选择的三级单位
                    .setCyclic(false, false, false)//循环与否
                    .setSelectOptions(options1, options2, options3)  //设置默认选中项
                    .setOutSideCancelable(true)//点击外部dismiss default true
                    .isDialog(false)//是否显示为对话框样式
                    .build();

            pvOptions.setPicker(mPList, mCList, mDList);//添加数据源
            pvOptions.show();
        } else {
            pvOptions.show();
        }
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.addselected_ll
                || v.getId() == R.id.address_edit_starting) {
            if (mBean != null) {
                selectAddress();
            } else {
                getCityNetData(2);
            }
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(mTextField1.getWindowToken(), 0);
        } else {
            setDataBack();
        }
    }

    private void setDataBack() {
        inPut = mTextField2.getText().toString();
        if (NetUtils.isNetworkStateed(this)) {
            if (TextUtils.notEmpty(inPut)) {
                Intent intent = new Intent("com.broadcast.databack");
                intent.putExtra("type", "2");
                intent.putExtra("updateData", tx + "|" + inPut);
                intent.putExtra("location", addressId);
                sendBroadcast(intent);
                finish();
            } else {
                TextUtils.toast(this, "详细地址不能为空！");
            }
        }
    }

    @Override
    public void callBack(Object object, int type) {
        try {
            Gson gson = new Gson();
            String s = new JSONObject(object.toString()).getString("err");
            if ("0".equals(s)) {
                mBean = gson.fromJson(object.toString(), RegionsBean.class);
                mPList.addAll(mBean.getData());
                for (int i = 0; i < mBean.getData().size(); ++i) {
                    mCList.add(mBean.getData().get(i).getChildren());
                    List<List<RegionsBean.DataBean.ChildrenBeanX.ChildrenBean>> mcList = new ArrayList<>();
//                    if (mBean.getData().get(i).getValue().equals(optionsId1)) {
//                        options1 = i;
//                    }
                    for (int j = 0; j < mBean.getData().get(i).getChildren().size(); ++j) {
                        List<RegionsBean.DataBean.ChildrenBeanX.ChildrenBean> cdist = mBean.getData().get(i).getChildren().get(j).getChildren() == null
                                ? new ArrayList<RegionsBean.DataBean.ChildrenBeanX.ChildrenBean>()
                                : mBean.getData().get(i).getChildren().get(j).getChildren();
                        mcList.add(cdist);
//                        if (mBean.getData().get(i).getChildren().get(j).getValue().equals(optionsId2)) {
//                            options2 = j;
//                        }
//                        for (int k = 0; k < mBean.getData().get(i).getChildren().get(j).getChildren().size(); ++k) {
//                            if (mBean.getData().get(i).getChildren().get(j).getChildren().get(k).getValue().equals(optionsId3)) {
//                                options3 = j;
//                            }
//                        }
                    }
                    mDList.add(mcList);
                }
            }
            if (type == 2 && "0".equals(s)) {
                selectAddress();
            }
        } catch (Exception e) {
        }
    }

    public String getFomatData(String data) {
        if (!"".equals(data) && data != null) {
            return data
                    .replace("  ", "|")
                    .replace(" ", "|")
                    .replace("，", "|")
                    .replace("|", "|")
                    .replace("、", "|")
                    .replace("/", "|")
                    .replace("。", "|")
                    .replace(".", "|");
        } else {
            return "";
        }
    }

    @Override
    public void failCallBack(int type) {

    }
}
