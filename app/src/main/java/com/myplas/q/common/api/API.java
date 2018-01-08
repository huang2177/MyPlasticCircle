package com.myplas.q.common.api;

import com.myplas.q.BuildConfig;

/**
 * @author 黄双
 *         电话：15378412400
 *         邮箱：15378412400@163.com
 *         时间：2017/5/16 12:10
 */
public class API {
    /**
     * base url
     */
    public final static String BASEURL = (BuildConfig.API_ENV)
            ? ("http://api.91su.cn/")
            : ("http://api.91su.cn/");
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
    public final static String CHECK_VERSION = BASEURL + "Version";
    /**
     * APP推荐更新
     */
    public final static String CHECKAPPVERSION = BASEURL + "AppVersion";

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
    public final static String JPUSHSET = BASEURL + "JpushSet";


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
    public final static String CREDIT_CERTIFICATE = BASEURL + "CreditCertificate";
    /**
     * 塑料配资
     */
    public final static String CREDIT_LIMIT_PAGE = BASEURL + "CreditLimitPage";
    /**
     * 企业信用额度
     */
    public final static String CREDIT_PAGE = BASEURL + "CreditPage";


    /*User*/
    /**
     * 发送验证码
     */
    public final static String SEND_MSG = BASEURL + "sendMsg";
    /**
     * 找回密码
     */
    public final static String FINF_MY_PWD = "finfMyPwd";
    /**
     * 注册
     */
    public final static String REGISTER = "register";
    /**
     * 普通登陆
     */
    public final static String LOGIN = BASEURL + "Login";
    //
    /**
     * 快速登陆
     */
    public final static String SIMPLE_LOGIN = BASEURL_API + "simpleLogin";
    /**
     * 退出登录
     */
    public final static String LOGOUT = BASEURL + "Logout";
    /**
     * 检查登录状态
     */
    public final static String VALIDUSERTOKEN = API.BASEURL + "validUserToken";
    /**
     * 验证用户注册手机号
     */
    public final static String VALIDUSERMOBILE = BASEURL + "validUserMobile";
    /**
     * 验证验证码
     */
    public final static String VALIDVERIFICATIONCODE = BASEURL + "validVerificationCode";
    /**
     * 发送验证码
     */
    public final static String SEND_MOBILE_MSG = "sendMobileMsg";
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
    public final static String FOCUS_OR_CANCEL = BASEURL + "FocusOrCancel";

    /**
     * 获取好友资料
     */
    public final static String GET_ZONE_FRIEND = BASEURL + "users/details";
    /**
     * 获取好友资料时 检查权限
     */
    public final static String PERMISSIONS = BASEURL + "users/access/permissions";
    /**
     * 获取ta的求购或供给
     */
    public final static String GET_TA_PUR = "TaPurNew";
    /**
     * 获取通讯录首页数据
     */
    public final static String PLASTICPERSON = BASEURL + "users";
    /**
     * 获得通讯录搜索记录和推荐
     */
    public final static String GETF_RECORD = BASEURL + "FriendSearchRecord";
    /**
     * 删除通讯录搜索记录
     */
    public final static String DELETEF_RECORD = BASEURL + "deleteFriendSearchRecord";
    /**
     * 获取用户资料
     */
    public final static String GETINFORMATION = BASEURL + "Information";
    /**
     * 获取用户关注
     */
    public final static String GETFOLLOWERS = BASEURL + "Followers";
    /**
     * 获取用户粉丝
     */
    public final static String GETRECOMMENDATION = BASEURL + "Recommendation";
    /**
     * 获取用户引荐
     */
    public final static String GETFANS = BASEURL + "Fans";



    /*myInfo*/
    /**
     * [我的]首页数据
     */
    public final static String MY_ZONE = BASEURL + "MyZone";

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
    public final static String SAVE_SELFINFO = BASEURL + "saveSelfInfo";
    /**
     * 获取我的资料
     */
    public final static String GET_SELF_INFO = BASEURL + "SelfInfo";
    /**
     * 偏好设置
     */
    public final static String FAVORATE_SET = BASEURL + "favorateSet";
    /**
     * 塑料圈联系人的-我的消息
     */
    public final static String GET_ZONE_CONTACT_MSG = "myInfo/getZoneContactMsg";
    /**
     * 获取我的引荐
     */
    public final static String GET_MY_INTRODUCTION = BASEURL + "MyIntroduction";
    /**
     * 获取我的粉丝
     */
    public final static String GET_MY_FUNS = BASEURL + "MyFuns";

    /**
     * 获取谁看过我详情数据
     */
    public final static String GET_VIEW_HISTORY_DETAILS = BASEURL + "ViewHistoryDetails";
    /**
     * 获取格式化中国的地区
     */
    public final static String GET_ALL_REGIONS = BASEURL + "AllRegions";
    /**
     * 获取我的消息
     */
    public final static String MYMSG = BASEURL + "myMsg";
    /**
     * 获取我的消息--供求信息
     */
    public final static String PLASTICMSG = BASEURL + "plasticMsg";
    /**
     * 获取我的消息--出价消息
     */
    public final static String CHUJIAMSG = BASEURL + "chuJiaMsg";
    /**
     * 获取我的消息--回复消息
     */
    public final static String HUIFUMSG = BASEURL + "huiFuMsg";
    /**
     * 获取我的消息--互动消息
     */
    public final static String INTERMSG = BASEURL + "interMsg";


    /*product*/
    /**
     * 塑料圈app之兑换置顶信息
     */
    public final static String NEW_EXCHANGE_SUPPLYORDEMAND = BASEURL + "exchanges/mall/goods/topcards";
    /**
     * 塑料圈app之积分商品列表
     */
    public final static String GET_PRODUCT_LIST = BASEURL + "mall/goods";
    /**
     * 塑料圈app之积分商品可选日期
     */
    public final static String GET_VALID_DATE = "product/getValidDate";
    /**
     * 塑料圈app之购买记录
     */
    public final static String GET_PURCHASE_RECORD = BASEURL + "histories/exchanges";
    /**
     * 塑料圈app之购买头条
     */
    public final static String NEW_EXCHANGE_TOUTIAO = BASEURL + "exchanges/mall/goods/toutiao";


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
    public final static String SCORE_RECORD = BASEURL + "histories/points";




    /*releaseMsg*/
    /**
     * 获取供求首页数据
     */
    public final static String RELEASE_MSG = BASEURL + "requirements";
    /**
     * 中间供求信息-获取供求发布(详情)
     */
    public final static String RELEASEMSGDETAIL = BASEURL + "requirements/details";
    /**
     * 获取供求发布(详情)的消息回复
     */
    public final static String COMMENTS = BASEURL + "requirements/comments";
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
    public final static String OFFERS = "requirements/offers";
    /**
     * 获取我的留言
     */
    public final static String GET_MY_COMMENT = "releaseMsg/getMyComment";
    /**
     * 获取我的供给或求购
     */
    public final static String GET_MY_MSG = BASEURL + "requirements/owns";
    /**
     * 搜索记录查询
     */
    public final static String SEARCH_RECORD = BASEURL + "histories/requirements/search";
    /**
     * 物性表查询列表
     */
    public final static String PHYSICAL_SEARCH = "releaseMsg/physicalSearch";
    /**
     * 搜塑料app接口
     */
    public final static String PLASTIC_SEARCH = BASEURL + "requirements/search";
    /**
     * 供求信息置顶之搜索选项配置栏目
     */
    public final static String GET_TAB_CONFIG = BASEURL + "configurations/requirements";
    /**
     * 删除搜索历史记录
     */
    public final static String DEL_SEARCH_RECORD = BASEURL + "histories/requirements/search";
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
    public final static String BILLINGLIST = BASEURL + "billingList";
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
     * 塑料头条-头条推荐
     */
    public final static String GET_SUBSCRIBE = BASEURL + "subscribe";
    /**
     * 塑料头条
     */
    public final static String GET_CATE_LIST = BASEURL + "cateList";
    /**
     * 塑料头条-获取订阅频道
     */
    public final static String GET_SELECT_CATE = BASEURL + "selectCate";
    /**
     * 塑料头条-获取订阅频道
     */
    public final static String SAVE_SELECT_CATE = BASEURL + "updateusershowcateinfo";
    /**
     * 塑料头条-详情列表
     */
    public final static String GET_DETAIL_INFO = BASEURL + "detailInfo";
    /**
     * 头条搜索记录及相关推荐
     */
    public final static String TOUTIAO_SEARCH_LOG = BASEURL + "toutiaoSearchLog";
    /**
     * 清空头条搜索记录
     */
    public final static String DEL_TOUTIAO_SEARCH_LOG = BASEURL + "delToutiaoSearchLog";
    /**
     * 塑料头条-检查是否有文章阅读权限
     */
    public final static String IS_PAID_SUBSCRIPTION = BASEURL + "isPaidSubscription";


    /*files*/
    /**
     * UCLOUD上传回调接口
     */
    public final static String UPLOADNOTIFY = "uploadNotify";
    /**
     * ucloud文件上传获取配置文件
     */
    public final static String CONFIG = "config";

    /**
     * 上传个人头像到服务器
     */
    public final static String USERPICUPLOAD = "shopAudit/UserPicUpload";
    /**
     * 上传个人头像到服务器
     */
    public final static String BUSINESSLICENSEUPLOAD = "shopAudit/businessLicenseUpload";
    /**
     * 提交申请
     */
    public final static String SUBMISSION = "shopAudit/submission";


}
