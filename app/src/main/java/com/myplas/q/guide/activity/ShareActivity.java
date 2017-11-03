package com.myplas.q.guide.activity;

import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.myplas.q.R;
import com.myplas.q.common.netresquset.ResultCallBack;
import com.myplas.q.common.utils.SharedUtils;
import com.myplas.q.common.utils.TextUtils;
import com.myplas.q.common.api.API;
import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.SendMessageToWX;
import com.tencent.mm.sdk.openapi.WXAPIFactory;
import com.tencent.mm.sdk.openapi.WXMediaMessage;
import com.tencent.mm.sdk.openapi.WXWebpageObject;
import com.tencent.mm.sdk.platformtools.Util;
import com.umeng.analytics.MobclickAgent;

import java.util.HashMap;
import java.util.Map;

/**
 * 编写： 黄双
 * 电话：15378412400
 * 邮箱：15378412400@163.com
 * 时间：2017/4/14 15:32
 */
public class ShareActivity extends BaseActivity implements View.OnClickListener, ResultCallBack {
    private View view;
    private IWXAPI api;
    private ImageView shareTocircle, sharetofriends;

    private int flag;
    private String type;
    private boolean isShareed;
    private String description = "塑料圈通讯录-塑料人都在用";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.wd_share_popou);
        getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);

        view = F(R.id.share_view);
        shareTocircle = F(R.id.share_py);
        sharetofriends = F(R.id.share_wx);
        type = getIntent().getStringExtra("type");

        view.setOnClickListener(this);
        shareTocircle.setOnClickListener(this);
        sharetofriends.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.share_py:
            case R.id.share_wx:
                flag = (v.getId() == R.id.share_py) ? (0) : (1);
                switch (type) {
                    case "2"://文章
                        isShareed = shareToWX(API.PLASTIC_SUCRIBLE
                                        + getIntent().getStringExtra("id")
                                , getIntent().getStringExtra("title")
                                , R.drawable.toutiaologo);

                        shareLog("3", getIntent().getStringExtra("id"));//加积分
                        break;
                    case "3"://授信
                        shareToWX(API.PLASTIC_CREDIT + getIntent().getStringExtra("id")
                                , getIntent().getStringExtra("title")
                                , R.drawable.sharelog);
                        break;
                    case "4"://供求
                        isShareed = shareToWX(API.PLASTIC_SUPPLY_DEMAND
                                        + getIntent().getStringExtra("id")
                                        + "/"
                                        + getIntent().getStringExtra("userid")
                                , getIntent().getStringExtra("title")
                                , R.drawable.gongqiulogo);

                        shareLog(getIntent().getStringExtra("supdemType")
                                , getIntent().getStringExtra("id"));//加积分
                        break;
                    case "5"://供求-QQ
                        isShareed = shareToWX(API.PLASTIC_SUPPLY_DEMAND_QQ
                                        + getIntent().getStringExtra("id")
                                , getIntent().getStringExtra("title")
                                , R.drawable.gongqiulogo);

                        shareLog(getIntent().getStringExtra("supdemType")
                                , getIntent().getStringExtra("id"));//加积分
                        break;
                    default:
                        break;
                }
                break;
            case R.id.share_view:
                finish();
                break;
            default:
                break;
        }
    }

    public boolean shareToWX(String url, String title, int resId) {
        if (isWebchatAvaliable()) {

            api = WXAPIFactory.createWXAPI(this, API.WXAPI);
            api.registerApp(API.WXAPI);
            WXWebpageObject webpage = new WXWebpageObject();
            webpage.webpageUrl = url;

            //创建一个WXMediaMessage对象
            WXMediaMessage msg = new WXMediaMessage(webpage);
            msg.title = title;
            msg.description = description;

            Bitmap bp = BitmapFactory.decodeResource(getResources(), resId);
            Bitmap thumbBmp = Bitmap.createScaledBitmap(bp, 150, 150, true);
            msg.thumbData = Util.bmpToByteArray(thumbBmp, true);
            ;
            thumbBmp.recycle();
            bp.recycle();

            SendMessageToWX.Req req = new SendMessageToWX.Req();
            req.transaction = String.valueOf(System.currentTimeMillis());//transaction字段用于唯一标识一个请求，这个必须有，否则会出错
            req.message = msg;
            //表示发送给朋友圈  WXSceneTimeline  表示发送给朋友  WXSceneSession
            req.scene = flag == 1
                    ? SendMessageToWX.Req.WXSceneSession
                    : SendMessageToWX.Req.WXSceneTimeline;

            return api.sendReq(req);
        } else {
            TextUtils.Toast(this, "你的手机没有安装微信！");
            return false;
        }
    }

    public void shareLog(String type, String id) {
        if (isShareed) {
            Map<String, String> map = new HashMap<String, String>();
            map.put("id", id);
            map.put("type", type);
            map.put("token", SharedUtils.getSharedUtils().getData(this, "token"));
            postAsyn(this, API.BASEURL + API.SAVE_SHARE_LOG, map, this, 1);
        }
    }

    @Override
    public void callBack(Object object, int type) {
    }

    @Override
    public void failCallBack(int type) {

    }

    private boolean isWebchatAvaliable() {
        try {
            getPackageManager().getPackageInfo("com.tencent.mm", PackageManager.GET_ACTIVITIES);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
