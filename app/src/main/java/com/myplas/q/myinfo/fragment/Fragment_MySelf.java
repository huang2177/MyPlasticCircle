package com.myplas.q.myinfo.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.myplas.q.appupdate.DownLoadUtils;
import com.myplas.q.appupdate.DownloadApk;
import com.myplas.q.common.utils.DialogShowUtils;
import com.myplas.q.common.utils.NetUtils;
import com.myplas.q.guide.activity.ShareActivity;
import com.myplas.q.common.utils.GetNumUtil;
import com.myplas.q.R;
import com.myplas.q.myinfo.activity.LookMeActivity;
import com.myplas.q.myinfo.activity.MyFansFollowActivity;
import com.myplas.q.guide.activity.BaseActivity;
import com.myplas.q.common.netresquset.ResultCallBack;
import com.myplas.q.common.utils.SharedUtils;
import com.myplas.q.common.utils.TextUtils;
import com.myplas.q.common.utils.VersionUtils;
import com.myplas.q.common.view.MyImageView;
import com.myplas.q.myinfo.activity.HelpActivity;
import com.myplas.q.myinfo.activity.IntegralActivity;
import com.myplas.q.myinfo.activity.LineOfCreditActivity;
import com.myplas.q.myinfo.activity.MyCommentActivity;
import com.myplas.q.myinfo.activity.MyMessageActivity;
import com.myplas.q.myinfo.activity.MySupplyDemandActivity;
import com.myplas.q.myinfo.activity.MyIntroductionActivity;
import com.myplas.q.myinfo.activity.MyDataActivity;
import com.myplas.q.myinfo.activity.FindPSWActivity;
import com.myplas.q.common.api.API;
import com.myplas.q.myinfo.activity.PlasticMoneyActivity;
import com.myplas.q.myinfo.beans.MyZone;
import com.myplas.q.guide.activity.MainActivity;
import com.umeng.analytics.MobclickAgent;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * 编写： 黄双
 * 电话：15378412400
 * 邮箱：15378412400@163.com
 * 时间：2017/3/17 14:45
 */

public class Fragment_MySelf extends Fragment implements View.OnClickListener, ResultCallBack, DialogShowUtils.DialogShowInterface, DownloadApk.InstallInterface {
    private View view;
    private MyZone myZone;
    private Button linear_tc;
    private ImageView image_rz;
    private MyImageView image_tx;
    private ImageButton imageButton;
    private ScrollView scrollingView;
    private SharedUtils sharedUtils = SharedUtils.getSharedUtils();
    private TextView text_gj, text_qg, text_yj, text_fs, text_gz,text_look, text_ly, text_xx, text_gx, text_gs, text_name_tel;
    private TextView text_title_gj, text_title_qg, text_title_ly, text_title_yj, text_title_fs, text_title_gz, text_title_jf;
    private LinearLayout linear_changepass, linear_title, linear_qg, linear_gj, linear_yj, linear_fs, linear_gz, linear_look, linear_ly, linear_xx, linear_jf, linear_bz, linear_gx, linear_edu, linear_pz, linear_share;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        view = LayoutInflater.from(getActivity()).inflate(R.layout.layout_myselffragment, null, false);
        //获取登陆后相关数据
        image_tx = f(R.id.xq_tx);
        image_rz = f(R.id.xq_rz);
        text_gs = f(R.id.wd_title_gs);
        linear_share = f(R.id.wd_linear_share);
        text_name_tel = f(R.id.wd_title_name);
        text_title_gj = f(R.id.wd_title_gj);
        text_title_qg = f(R.id.wd_title_qg);
        text_title_ly = f(R.id.wd_title_ly);
        text_title_yj = f(R.id.wd_title_yj);
        text_title_fs = f(R.id.wd_title_fs);
        text_title_gz = f(R.id.wd_title_gz);
        text_title_jf = f(R.id.wd_title_jf);

        text_gj = f(R.id.wd_text_gj);
        text_qg = f(R.id.wd_text_qg);
        text_yj = f(R.id.wd_text_yj);
        text_fs = f(R.id.wd_text_fs);
        text_gz = f(R.id.wd_text_gz);
        text_look=f(R.id.wd_text_look);
        text_ly = f(R.id.wd_text_ly);
        text_xx = f(R.id.wd_text_xx);
        text_gx = f(R.id.wd_text_gx);

        linear_gj = f(R.id.wd_linear_gj);
        linear_qg = f(R.id.wd_linear_qg);
        linear_yj = f(R.id.wd_linear_yj);
        linear_fs = f(R.id.wd_linear_fs);
        linear_gz = f(R.id.wd_linear_gz);
        linear_look = f(R.id.wd_linear_look);
        linear_ly = f(R.id.wd_linear_ly);
        linear_jf = f(R.id.wd_linear_jf);
        linear_bz = f(R.id.wd_linear_bz);
        linear_gx = f(R.id.wd_linear_gx);
        linear_xx = f(R.id.wd_linear_xx);
        linear_tc = f(R.id.wd_linear_tc);
        linear_pz = f(R.id.wd_linear_pz);
        linear_edu = f(R.id.wd_linear_edu);
        linear_title = f(R.id.wd_linear_title);
        imageButton = f(R.id.img_reload);
        scrollingView = f(R.id.scrollView_myself);
        linear_changepass = f(R.id.wd_linear_changepass);

        linear_changepass.setOnClickListener(this);
        linear_share.setOnClickListener(this);
        linear_title.setOnClickListener(this);
        linear_gj.setOnClickListener(this);
        linear_qg.setOnClickListener(this);
        linear_yj.setOnClickListener(this);
        linear_fs.setOnClickListener(this);
        linear_gz.setOnClickListener(this);
        linear_look.setOnClickListener(this);
        linear_ly.setOnClickListener(this);
        linear_xx.setOnClickListener(this);
        linear_jf.setOnClickListener(this);
        linear_bz.setOnClickListener(this);
        linear_gx.setOnClickListener(this);
        linear_tc.setOnClickListener(this);
        linear_pz.setOnClickListener(this);
        linear_edu.setOnClickListener(this);
        imageButton.setOnClickListener(this);
        //请求数据
        //getLoginInfo(false);
        text_gx.setText("当前版本 " + VersionUtils.getVersionName(getActivity()) + "  ");
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return view;
    }

    public <T extends View> T f(int id) {
        return (T) view.findViewById(id);
    }

    @Override
    public void onClick(View v) {
        if (NetUtils.isNetworkStateed(getActivity())) {
            switch (v.getId()) {
                case R.id.img_reload:
                    getLoginInfo(true);
                    break;
                case R.id.wd_linear_share:
                    Intent in = new Intent(getActivity(), ShareActivity.class);
                    in.putExtra("type", "5");
                    startActivity(in);
                    break;
                case R.id.wd_linear_changepass:
                    Intent i = new Intent(getActivity(), FindPSWActivity.class);
                    i.putExtra("title", "修改密码");
                    startActivity(i);
                    break;
                case R.id.wd_linear_gj:
                    Intent intent = new Intent(getActivity(), MySupplyDemandActivity.class);
                    intent.putExtra("title", "我的供给");
                    intent.putExtra("type", "2");
                    startActivity(intent);
                    break;
                case R.id.wd_linear_qg:
                    Intent intent1 = new Intent(getActivity(), MySupplyDemandActivity.class);
                    intent1.putExtra("title", "我的求购");
                    intent1.putExtra("type", "1");
                    startActivity(intent1);
                    break;
                case R.id.wd_linear_yj:
                    Intent intent2 = new Intent(getActivity(), MyIntroductionActivity.class);
                    startActivity(intent2);
                    break;
                case R.id.wd_linear_fs:
                    Intent intent3 = new Intent(getActivity(), MyFansFollowActivity.class);
                    intent3.putExtra("titlename", "我的粉丝");
                    intent3.putExtra("type", "1");
                    startActivity(intent3);
                    break;
                case R.id.wd_linear_gz:
                    Intent intent4 = new Intent(getActivity(), MyFansFollowActivity.class);
                    intent4.putExtra("titlename", "我的关注");
                    intent4.putExtra("type", "2");
                    startActivity(intent4);
                    break;
                case R.id.wd_linear_look:
                    Intent intent0 = new Intent(getActivity(), LookMeActivity.class);
                    startActivity(intent0);
                    break;
                case R.id.wd_linear_ly:
                    Intent intent5 = new Intent(getActivity(), MyCommentActivity.class);
                    startActivity(intent5);
                    break;
                case R.id.wd_linear_xx:
                    startActivity(new Intent(getActivity(), MyMessageActivity.class));
                    break;
                case R.id.wd_linear_jf:
                    startActivity(new Intent(getActivity(), IntegralActivity.class));
                    break;
                case R.id.wd_linear_bz:
                    startActivity(new Intent(getActivity(), HelpActivity.class));
                    break;
                case R.id.wd_linear_edu:
                    startActivity(new Intent(getActivity(), LineOfCreditActivity.class));
                    break;
                case R.id.wd_linear_pz:
                    startActivity(new Intent(getActivity(), PlasticMoneyActivity.class));
                    break;
                case R.id.wd_linear_gx:
                    getVersion();
                    break;
                case R.id.wd_linear_tc:
                    String content = "确定退出？";
                    DialogShowUtils dialogShowUtils = new DialogShowUtils();
                    dialogShowUtils.showDialog(getActivity(), content, 4, this);
                    break;
                case R.id.wd_linear_title:
                    Intent intent6 = new Intent(getActivity(), MyDataActivity.class);
                    startActivity(intent6);
                    break;
            }
        }
    }

    public void resquestNetData(String method, Map map, int type, boolean isShow) {
        try {
            String url = API.BASEURL + method;
            BaseActivity.postAsyn1(getActivity(), url, map, this, type, isShow);
        } catch (Exception e) {
        }
    }

    public void getLoginInfo(boolean isShow) {
        try {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("token", sharedUtils.getData(getActivity(), "token"));
            resquestNetData(API.MY_ZONE, map, 2, isShow);
        } catch (Exception e) {
        }
    }

    public void getVersion() {
        Map<String, String> map = new HashMap<String, String>();
        String version_local = VersionUtils.getVersionName(getActivity());
        map.put("version", version_local);
        map.put("platform", "android");
        new BaseActivity().postAsyn(getActivity(), API.BASEURL + API.CHECK_VERSION, map, this, 3);
    }

    @Override
    public void callBack(Object object, int type) {
        try {
            scrollingView.setVisibility(View.VISIBLE);
            imageButton.setVisibility(View.GONE);
            JSONObject jsonObject = new JSONObject(object.toString());
            Gson gson = new Gson();
            if (type == 1) {
                String s = jsonObject.getString("err");
                if (("0").equals(s) || "1".equals(s) || "998".equals(s)) {
                    sharedUtils.setBooloean(getActivity(), "logined", false);
                    sharedUtils.setData(getActivity(), "token", "");
                    sharedUtils.setData(getActivity(), "userid", "");
                    MainActivity.firstInto();
                }
            }
            if (type == 2) {
                if ("0".equals(jsonObject.getString("err"))) {
                    myZone = gson.fromJson(object.toString(), MyZone.class);
                    showInfo(myZone);
                    Log.e("------",object.toString());
                } else if ("1".equals(jsonObject.getString("err")) || "998".equals(jsonObject.getString("err"))) {
                    sharedUtils.setData(getActivity(), "token", "");
                    sharedUtils.setData(getActivity(), "userid", "");
                    sharedUtils.setBooloean(getActivity(), "logined", false);
                }
            }
            if (type == 3) {
                String s = jsonObject.getString("err");
                if (s.equals("1")) {
                    int version_last = GetNumUtil.getNum(VersionUtils.getVersionName(getActivity()));
                    int version_now = GetNumUtil.getNum(new JSONObject(object.toString()).getString("new_version"));
                    String url = new JSONObject(object.toString()).getString("url");
                    //比较版本号
                    if (version_now > version_last) {
                        //如果手机是否安装apk
                        DownloadApk downloadApk=new DownloadApk(this);
                        downloadApk.downloadApk(getActivity(), url, "塑料圈通讯录更新...", "塑料圈通讯录");
                    }
                }
                if (s.equals("0")) {
                    TextUtils.Toast(getActivity(), "当前版本已是最新版本！");
                }
            }
        } catch (Exception e) {
        }
    }
    @Override
    public void ok(int type) {
        if (type == 4) {
            //退出登陆；
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("token", sharedUtils.getData(getActivity(), "token"));
            resquestNetData(API.LOGOUT, map, 1,true);
        }
    }
    @Override
    public void failCallBack(int type) {
        if (myZone==null) {
            scrollingView.setVisibility(View.GONE);
            imageButton.setVisibility(View.VISIBLE);
        }
    }

    public void showInfo(MyZone myZone) {
        try {
            String ispass = myZone.getData().getIs_pass();
            Glide.with(getActivity()).load(myZone.getData().getThumb()).placeholder(R.drawable.contact_image_defaul_male).into(image_tx);
            image_rz.setImageResource((ispass.equals("0")) ? (R.drawable.icon_identity) : (R.drawable.icon_identity_hl));
            text_gs.setText(myZone.getData().getC_name());
            text_name_tel.setText(myZone.getData().getName() + "  " + myZone.getData().getMobile());
            text_title_gj.setText(myZone.getS_out_count() + "\n供给");
            text_title_qg.setText(myZone.getS_in_count() + "\n求购");
            text_title_ly.setText(myZone.getLeaveword() + "\n留言");
            text_title_yj.setText(myZone.getIntroduction() + "\n引荐");
            text_title_fs.setText(myZone.getMyfans() + "\n粉丝");
            text_title_gz.setText(myZone.getMyconcerns() + "\n关注");
            text_title_jf.setText(myZone.getPoints() + "\n塑豆");

            text_gj.setText(myZone.getS_out_count() + "  ");
            text_qg.setText(myZone.getS_in_count() + "  ");
            text_yj.setText(myZone.getIntroduction() + "  ");
            text_fs.setText(myZone.getMyfans() + "  ");
            text_gz.setText(myZone.getMyconcerns() + "  ");
            text_look.setText(myZone.getMyviewhistory()+"  ");
            text_ly.setText("未读留言" + myZone.getLeaveword() + "  ");
            text_xx.setText("未读消息" + myZone.getMessage() + "  ");
        } catch (Exception e) {
        }
    }

    public void onResume() {
        super.onResume();
        getLoginInfo(false);
        MobclickAgent.onPageStart("MainScreen"); //统计页面，"MainScreen"为页面名称，可自定义
    }

    public void onPause() {
        super.onPause();
        MobclickAgent.onPageEnd("MainScreen");
    }
    @Override
    public void install() {
        DownLoadUtils.getInstance(getActivity()).installApk(getActivity());
    }
}
