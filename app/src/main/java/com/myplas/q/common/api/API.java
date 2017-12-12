package com.myplas.q.common.api;

import com.myplas.q.BuildConfig;

/**
 * @author 黄双
 * 电话：15378412400
 * 邮箱：15378412400@163.com
 * 时间：2017/5/16 12:10
 */
public class API {
    /**
     * base url
     */
    public final static String BASEURL = (BuildConfig.API_ENV)
            ? ("https://api.myplas.com/qapi_4/")
            : ("https://ssl.myplas.com/qapi_4/");
    /**
     * 正式_快速登陆
     */
    public final static String BASEURL_API = (BuildConfig.API_ENV)
            ? "https://api.myplas.com/"
            : "https://ssl.myplas.com/";

    /*common*/
    /**
     * APP更新
     */
    public final static String CHECK_VERSION = "common/checkVersion";
    /**
     * APP推荐更新
     */
    public final static String CHECKAPPVERSION = "common/checkAppVersion";

    /**
     * 微信分享缩略图
     */
    public final static String LOG_HL_URL = "http://pic.myplas.com/myapp/img/toutiaoLogo.png";
    public final static String LOG_SD_URL = "http://pic.myplas.com/myapp/img/gongqiuLogo.png";

    /**
     * 微信appid
     */
    public final static String WXAPI = "wxc0eb2ef58d5df955";

    /**
     * 分享授信
     */
    public final static String PLASTIC_CREDIT = "http://q.myplas.com/plasticzone/plastic#/credit2/";

    /**
     * 分享成功日志
     */
    public final static String SAVE_SHARE_LOG = "wechat/saveShareLog";

    /**
     * 分享店铺
     */
    public final static String PLASTIC_CONTACT = (BuildConfig.API_ENV)
            ? "http://q.myplas.com/plasticzone/plastic#/personinfoshare/"
            : "http://test.myplas.com/plasticzone/plastic#/personinfoshare/";
    /**
     * 分享文章
     */
    public final static String PLASTIC_SUCRIBLE = (BuildConfig.API_ENV)
            ? "http://q.myplas.com/plasticzone/plastic#/headlinedetail/"
            : "http://test.myplas.com/plasticzone/plastic#/headlinedetail/";
    /**
     * 分享供求
     */
    public final static String PLASTIC_SUPPLY_DEMAND = (BuildConfig.API_ENV)
            ? "http://q.myplas.com/plasticzone/plastic#/supplybuydetail/"
            : "http://test.myplas.com/plasticzone/plastic#/supplybuydetail/";
    /**
     * 分享供求-QQ
     */
    public final static String PLASTIC_SUPPLY_DEMAND_QQ = (BuildConfig.API_ENV)
            ? "http://q.myplas.com/plasticzone/plastic#/qqinfo?id="
            : "http://test.myplas.com/plasticzone/plastic#/qqinfo?id=";
    /**
     * 极光推送用户个人偏好设置
     */
    public final static String JPUSHSET = "common/jpushSet";


    /*socket*/
    /**
     * rabbitmq链接配置文件
     */
    public final static String INIT = "socket/init";
    /**
     * rabbitmq链接关闭后回调通知服务器
     */
    public final static String CLOSED = "socket/closed";
    /**
     * rabbitmq链接成功后回调通知服务器
     */
    public final static String CONNECTED = "socket/connected";
    /**
     * 更新已读的红点推送
     */
    public final static String READ = "socket/read";
    /**
     * 推送后获取数据
     */
    public final static String GETREDDOTINFO = "socket/getRedDotInfo";


    /*Credit*/
    /**
     * 获取证书
     */
    public final static String CREDIT_CERTIFICATE = "credit/creditCertificate";
    /**
     * 塑料配资
     */
    public final static String CREDIT_LIMIT_PAGE = "credit/creditLimitPage";
    /**
     * 企业信用额度
     */
    public final static String CREDIT_PAGE = "credit/creditPage";


    /*User*/
    /**
     * 发送验证码
     */
    public final static String SEND_MSG = "user/sendMsg";
    /**
     * 找回密码
     */
    public final static String FINF_MY_PWD = "user/finfMyPwd";
    /**
     * 注册
     */
    public final static String REGISTER = "user/register";
    /**
     * 普通登陆
     */
    public final static String LOGIN = "user/login";
    //
    /**
     * 快速登陆
     */
    public final static String SIMPLE_LOGIN = "user/simpleLogin";
    /**
     * 退出登录
     */
    public final static String LOGOUT = "user/logout";
    /**
     * 检查登录状态
     */
    public final static String VALIDUSERTOKEN = "user/validUserToken";
    /**
     * 验证用户注册手机号
     */
    public final static String VALIDUSERMOBILE = "user/validUserMobile";
    /**
     * 验证验证码
     */
    public final static String VALIDVERIFICATIONCODE = "user/validVerificationCode";
    /**
     * 发送验证码
     */
    public final static String SEND_MOBILE_MSG = "user/login/sendMobileMsg";
    /**
     * 获取验证码--仅供APP使用
     */
    public final static String VCODE = "api/vcode/app";
    /**
     * 验证码
     */
    public final static String CHK_VCODE = "api/vcode/chkVcode";


    /*friend*/
    /**
     * 取消或关注
     */
    public final static String FOCUS_OR_CANCEL = "friend/focusOrCancel";
    /**
     * 发送回复消息
     */
    public final static String SEND_ZONE_CONTACTMSG = "friend/sendZoneContactMsg";
    /**
     * 获取好友资料
     */
    public final static String GET_ZONE_FRIEND = "friend/getZoneFriend";
    /**
     * 获取ta的求购或供给
     */
    public final static String GET_TA_PUR = "/friend/getTaPurNew";
    /**
     * 获取通讯录首页数据
     */
    public final static String GET_PLASTIC_PERSON = "friend/getPlasticPerson";
    /**
     * 获得通讯录搜索记录和推荐
     */
    public final static String GETF_RECORD = "friend/getFriendSearchRecord";
    /**
     * 删除通讯录搜索记录
     */
    public final static String DELETEF_RECORD = "friend/deleteFriendSearchRecord";
    /**
     * 获取用户资料
     */
    public final static String GETINFORMATION = "friend/getInformation";
    /**
     * 获取用户关注
     */
    public final static String GETFOLLOWERS = "friend/getFollowers";
    /**
     * 获取用户粉丝
     */
    public final static String GETRECOMMENDATION = "friend/getRecommendation";
    /**
     * 获取用户引荐
     */
    public final static String GETFANS = "friend/getFans";



    /*myInfo*/
    /**
     * [我的]首页数据
     */
    public final static String MY_ZONE = "myInfo/myZone";
    /**
     * 保存名片到服务器
     */
    public final static String SAVE_CARD_IMG = "myInfo/saveCardImg";
    /**
     * 保存头像到服务器
     */
    public final static String SAVE_PIC_TO_SERVER = "myInfo/savePicToServer";
    /**
     * 保存资料
     */
    public final static String SAVE_SELFINFO = "myInfo/saveSelfInfo";
    /**
     * 获取我的资料
     */
    public final static String GET_SELF_INFO = "myInfo/getSelfInfo";
    /**
     * 偏好设置
     */
    public final static String FAVORATE_SET = "myInfo/favorateSet";
    /**
     * 塑料圈联系人的-我的消息
     */
    public final static String GET_ZONE_CONTACT_MSG = "myInfo/getZoneContactMsg";
    /**
     * 获取我的引荐
     */
    public final static String GET_MY_INTRODUCTION = "myInfo/getMyIntroduction";
    /**
     * 获取我的关注 粉丝
     */
    public final static String GET_MY_FUNS = "myInfo/getMyFuns";
    /**
     * 获取谁看过我详情数据
     */
    public final static String GET_VIEW_HISTORY_DETAILS = "myInfo/getViewHistoryDetails";
    /**
     * 获取格式化中国的地区
     */
    public final static String GET_ALL_REGIONS = "myInfo/getAllRegions";
    /**
     * 获取我的消息
     */
    public final static String MYMSG = "myInfo/myMsg";
    /**
     * 获取我的消息--供求信息
     */
    public final static String PLASTICMSG = "myInfo/plasticMsg";
    /**
     * 获取我的消息--出价消息
     */
    public final static String CHUJIAMSG = "myInfo/chuJiaMsg";
    /**
     * 获取我的消息--回复消息
     */
    public final static String HUIFUMSG = "myInfo/huiFuMsg";
    /**
     * 获取我的消息--互动消息
     */
    public final static String INTERMSG = "myInfo/interMsg";


    /*product*/
    /**
     * 塑料圈app之兑换置顶信息
     */
    public final static String NEW_EXCHANGE_SUPPLYORDEMAND = "product/newExchangeSupplyOrDemand";
    /**
     * 塑料圈app之积分商品列表
     */
    public final static String GET_PRODUCT_LIST = "product/getProductList";
    /**
     * 塑料圈app之积分商品可选日期
     */
    public final static String GET_VALID_DATE = "product/getValidDate";
    /**
     * 塑料圈app之购买记录
     */
    public final static String GET_PURCHASE_RECORD = "product/getPurchaseRecord";
    /**
     * 塑料圈app之购买头条
     */
    public final static String NEW_EXCHANGE_TOUTIAO = "product/newExchangeToutiao";


    /*pay*/
    /**
     * 塑料圈app之获取订单
     */
    public final static String GET_PREPAY_ORDER = "pay/getPrePayOrder";
    /**
     * 塑料圈app之获取可选金额
     */
    public final static String GET_PAY_CONFIG = "pay/getPayConfig";
    /**
     * 塑料圈app之获取固定金额
     */
    public final static String GET_EXACT_AMOUNT = "pay/getExactAmount";
    /**
     * 塑料圈app之订单状态
     */
    public final static String UPDATE_ORDER_STATUS = "pay/updateOrderStatus";


    /*score*/
    /**
     * 塑料圈app之积分记录
     */
    public final static String SCORE_RECORD = "score/getScoreRecords";




    /*releaseMsg*/
    /**
     * 获取通讯录首页数据
     */
    public final static String GET_RELEASE_MSG = "releaseMsg/getReleaseMsg";
    /**
     * 中间供求信息-获取供求发布(详情)
     */
    public final static String GET_RELEASE_MSG_DETAIL = "releaseMsg/getReleaseMsgDetail";
    /**
     * 中间供求信息)获取供求发布(详情)的消息回复
     */
    public final static String GET_RELEASE_MSG_DETAIL_REPLY = "releaseMsg/getReleaseMsgDetailReply";
    /**
     * 二次发布
     */
    public final static String SECOND_PUB = "releaseMsg/repeatRelease";
    /**
     * 供求信息置顶之供求信息列表
     */
    public final static String SUPPLYDEMAND_LIST = "releaseMsg/supplyDemandList";
    /**
     * 供求消息中的出价
     */
    public final static String DELIVER_PRICE = "releaseMsg/deliverPrice";
    /**
     * 删除我的供给或求购
     */
    public final static String DELETE_MY_MSG = "releaseMsg/deleteMyMsg";
    /**
     * 判断提交的发布报价(采购1、报价2)
     */
    public final static String PUB = "releaseMsg/releaseNewDemand";

    /**
     * 解析后重新发布
     */
    public final static String INSTANTRELEASE = "releaseMsg/instantRelease";
    /**
     * 回复供求消息
     */
    public final static String SAVE_MSG = "releaseMsg/saveMsg";
    /**
     * 获取供求消息的出价
     */
    public final static String GET_DELIVER_PRICE = "releaseMsg/getDeliverPrice";
    /**
     * 获取我的留言
     */
    public final static String GET_MY_COMMENT = "releaseMsg/getMyComment";
    /**
     * 获取我的供给或求购
     */
    public final static String GET_MY_MSG = "releaseMsg/getMyMsg";
    /**
     * 搜索记录查询
     */
    public final static String SEARCH_RECORD = "releaseMsg/getSearchLogNew";
    /**
     * 物性表查询列表
     */
    public final static String PHYSICAL_SEARCH = "releaseMsg/physicalSearch";
    /**
     * 搜塑料app接口
     */
    public final static String PLASTIC_SEARCH = "releaseMsg/getplasticSearch";
    /**
     * 供求信息置顶之搜索选项配置栏目
     */
    public final static String GET_TAB_CONFIG = "releaseMsg/getTabConfig";
    /**
     * 删除搜索历史记录
     */
    public final static String DEL_SEARCH_RECORD = "releaseMsg/delplasticSearchRecord";
    /**
     * 搜塑料app接口
     */
    public final static String PLASTIC_SEARCH_DETAIL = "releaseMsg/plasticSearchDetail";
    /**
     * 物性表查询详情
     */
    public final static String PHYSICAL_DETAIL = "releaseMsg/physicalDetail";



    /*billingInfo*/
    /**
     * 我的发票列表
     */
    public final static String BILLINGLIST = "billingInfo/billingList";
    /**
     * 开票基本信息以及开票明细
     */
    public final static String INVOICE = "billingInfo/invoice";
    /**
     * 申请开票
     */
    public final static String INVOICEDETAILADD = "billingInfo/invoiceDetailAdd";
    /**
     * 确认签收
     */
    public final static String ORDERSIGN = "billingInfo/orderSign";
    /**
     * 发票详情列表
     */
    public final static String BILLINGDETAILLIST = "billingInfo/billingDetailList";



    /*toutiao*/
    /**
     * 塑料头条
     */
    public final static String TOP_LINE = "toutiao/topLine";
    /**
     * 塑料头条-头条推荐
     */
    public final static String GET_SUBSCRIBE = "toutiao/getSubscribe";
    /**
     * 塑料头条
     */
    public final static String GET_CATE_LIST = "toutiao/getCateList";
    /**
     * 塑料头条-获取订阅频道
     */
    public final static String GET_SELECT_CATE = "toutiao/getSelectCate";
    /**
     * 塑料头条-详情列表
     */
    public final static String GET_DETAIL_INFO = "toutiao/getDetailInfo";
    /**
     * 头条搜索记录及相关推荐
     */
    public final static String TOUTIAO_SEARCH_LOG = "toutiao/ToutiaoSearchLog";
    /**
     * 清空头条搜索记录
     */
    public final static String DEL_TOUTIAO_SEARCH_LOG = "toutiao/delToutiaoSearchLog";
    /**
     * 塑料头条-检查是否有文章阅读权限
     */
    public final static String IS_PAID_SUBSCRIPTION = "toutiao/isPaidSubscription";

}
