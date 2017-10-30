package com.myplas.q.guide.activity;

import android.content.pm.PackageManager;
import android.graphics.Bitmap;
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
    private int flag;
    private ImageView shareTocircle, sharetofriends;
    private View view;
    private String type;
    private boolean isShareed;
    private IWXAPI api;
    //private MyBroadcastReceiver myBroadcastReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.wd_share_popou);
        getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);
        type = getIntent().getStringExtra("type");
        view = findViewById(R.id.share_view);
        shareTocircle = (ImageView) findViewById(R.id.share_py);
        sharetofriends = (ImageView) findViewById(R.id.share_wx);
        view.setOnClickListener(this);
        sharetofriends.setOnClickListener(this);
        shareTocircle.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.share_py:
            case R.id.share_wx:
                flag = (v.getId() == R.id.share_py) ? (0) : (1);
                switch (type) {
                    case "2"://文章
                        isShareed = shareToWX(API.PLASTIC_SUCRIBLE + getIntent().getStringExtra("id")
                                , ""
                                , getIntent().getStringExtra("title"));
                        shareLog("3"
                                , getIntent().getStringExtra("id"));//加积分
                        break;
                    case "3"://授信
                        String userid = getIntent().getStringExtra("id");
                        shareToWX(API.PLASTIC_CREDIT + userid
                                , ""
                                , getIntent().getStringExtra("title"));
                        break;
                    case "4"://供求
                        isShareed = shareToWX(API.PLASTIC_SUPPLY_DEMAND
                                        + getIntent().getStringExtra("id")
                                        + "/"
                                        + getIntent().getStringExtra("userid")
                                , ""
                                , getIntent().getStringExtra("title"));

                        shareLog(getIntent().getStringExtra("t")
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

    public boolean shareToWX(String url, String text, String title) {
        if (isWebchatAvaliable()) {
            String APP_ID = API.WXAPI;

            api = WXAPIFactory.createWXAPI(this, APP_ID);
            api.registerApp(APP_ID);
            WXWebpageObject webpage = new WXWebpageObject();
            webpage.webpageUrl = url;
            //创建一个WXMediaMessage对象
            WXMediaMessage msg = new WXMediaMessage(webpage);
            msg.description = "我的塑料网-塑料圈通讯录";
            msg.title = title;
            getBitMap(msg);
//            Bitmap bp = BitmapFactory.decodeResource(getResources(), R.drawable.sharelogo);
//            Bitmap thumbBmp = Bitmap.createScaledBitmap(bp, 150, 150, true);
//
//            thumbBmp.recycle();
//            bp.recycle();
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

    public void getBitMap(final WXMediaMessage msg) {
        Glide.with(ShareActivity.this)
                .load(API.LOG_IMG_URL)
                .asBitmap()
                .into(new SimpleTarget<Bitmap>() {
                    @Override
                    public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                        msg.thumbData = Util.bmpToByteArray(resource, true);
                        resource.recycle();
                    }
                });
    }

    public void shareLog(String type, String id) {
        if (isShareed) {
            Map<String, String> map = new HashMap<String, String>();
            map.put("token", SharedUtils.getSharedUtils().getData(this, "token"));
            map.put("type", type);
            map.put("id", id);
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
