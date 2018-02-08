package com.myplas.q.common.api;

import android.widget.BaseAdapter;

import com.myplas.q.BuildConfig;
import com.myplas.q.app.fragment.BaseFragment;

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
            ? ("https://api2.myplas.com/")
            : ("http://api.91su.cn/");

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
    public final static String SAVE_SHARE_LOG = BASEURL + "records";

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
     * 分享黑名单
     */
    public final static String PLASTIC_BLACKLIST = (BuildConfig.API_ENV)
            ? "http://q.myplas.com/plasticzone/plastic?#/blackdetail/"
            : "http://test.myplas.com/plasticzone/plastic?#/blackdetail/";
    /**
     * 极光推送用户个人偏好设置
     */
    public final static String JPUSHSET = BASEURL + "JpushSet";


    /*socket*/
    /**
     * rabbitmq链接配置文件
     */
    public final static String INIT = BASEURL + "socket/initialization";
    /**
     * rabbitmq链接关闭后回调通知服务器
     */
    public final static String CLOSED = BASEURL + "socket/closed";
    /**
     * rabbitmq链接成功后回调通知服务器
     */
    public final static String CONNECTED = BASEURL + "socket/queue";
    /**
     * 推送后获取数据
     */
    public final static String GETREDDOTINFO = BASEURL + "socket/getRedDotInfo";


    /*Credit*/
    /**
     * 获取证书
     */
    public final static String CREDIT_CERTIFICATE = BASEURL + "creditCertificate";
    /**
     * 塑料配资
     */
    public final static String CREDIT_LIMIT_PAGE = BASEURL + "CreditLimitPage";
    /**
     * 企业信用额度
     */
    public final static String CREDIT_PAGE = BASEURL + "creditPage";


    /*User*/
    /**
     * 发送验证码
     */
    public final static String SEND_MSG = BASEURL + "sendMsg";
    /**
     * 找回密码
     */
    public final static String FINF_MY_PWD = BASEURL + "finfMyPwd";
    /**
     * 注册
     */
    public final static String REGISTER = BASEURL + "user/register";
    /**
     * 普通登陆
     */
    public final static String LOGIN = BASEURL + "user/login";
    //
    /**
     * 快速登陆
     */
    public final static String SIMPLE_LOGIN = BASEURL + "user/simpleLogin";
    /**
     * 退出登录
     */
    public final static String LOGOUT = BASEURL + "user/logout";
    /**
     * 检查登录状态
     */
    public final static String VALIDUSERTOKEN = API.BASEURL + "validations";
    /**
     * 验证用户注册手机号
     */
    public final static String VALIDUSERMOBILE = BASEURL + "validUserMobile";
    /**
     * 验证验证码
     */
    public final static String VALIDVERIFICATIONCODE = BASEURL + "validVerificationCode";
    /**
     * 获取验证码--仅供APP使用
     */
    public final static String VCODE = BASEURL + "appcode";
    /**
     * 验证码
     */
    public final static String CHK_VCODE = BASEURL + "checkVcode";


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


    /*myInfo*/
    /**
     * [我的]首页数据
     */
    public final static String MY_ZONE = BASEURL + "MyZone";

    /**
     * 保存名片到服务器
     */
    public final static String SAVE_CARD_IMG = BASEURL + "users/cards";
    /**
     * 保存头像到服务器
     */
    public final static String SAVE_PIC_TO_SERVER = BASEURL + "users/avatars";
    /**
     * 保存资料
     */
    public final static String SAVE_SELFINFO = BASEURL + "users/profiles";
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
    public final static String GET_ALL_REGIONS = BASEURL + "regions/formatter";
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
    public final static String GET_VALID_DATE = BASEURL + "validDate";
    /**
     * 塑料圈app之购买记录
     */
    public final static String GET_PURCHASE_RECORD = BASEURL + "histories/exchanges";
    /**
     * 塑料圈app之购买头条
     */
    public final static String NEW_EXCHANGE_TOUTIAO = BASEURL + "exchanges/mall/goods/toutiao";
    /**
     * 新塑料商城
     */
    public final static String POINTSBILLLOG = BASEURL + "user/pointsbilllog";


    /*pay*/
    /**
     * 塑料圈app之获取订单
     */
    public final static String GET_PREPAY_ORDER = BASEURL + "prePayOrder";
    /**
     * 塑料圈app之获取可选金额
     */
    public final static String GET_PAY_CONFIG = BASEURL + "PayConfig";
    /**
     * 塑料圈app之获取固定金额
     */
    public final static String GET_EXACT_AMOUNT = BASEURL + "points/relations";
    /**
     * 塑料圈app之订单状态
     */
    public final static String UPDATE_ORDER_STATUS = BASEURL + "payments/orders";


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
     * 获取解析内容
     */
    public final static String ANALYSIS = BASEURL + "requirements/analysis";

    /**
     * 获取供求消息的出价
     */
    public final static String OFFERS = BASEURL + "requirements/offers";
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
    public final static String PHYSICAL_SEARCH = BASEURL + "properties";
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
     * 物性表查询详情
     */
    public final static String PHYSICAL_DETAIL = BASEURL + "properties/tables";



    /*billingInfo*/
    /**
     * 我的发票列表
     */
    public final static String BILLINGLIST = BASEURL + "billingList";
    /**
     * 开票基本信息以及开票明细
     */
    public final static String INVOICE = BASEURL + "invoice";
    /**
     * 申请开票
     */
    public final static String INVOICEDETAILADD = BASEURL + "invoiceDetail";
    /**
     * 确认签收
     */
    public final static String ORDERSIGN = BASEURL + "orderSign";
    /**
     * 发票详情列表
     */
    public final static String BILLINGDETAILLIST = BASEURL + "billingDetailList";



    /*toutiao*/
    /**
     * 塑料头条
     */
    public final static String GET_CATE_LIST = BASEURL + "news";
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
    public final static String UPLOADNOTIFY = BASEURL + "files/storage/notification";
    /**
     * 提交申请
     */
    public final static String SHOPS = BASEURL + "shops";

    /*blacklists*/
    /**
     * 塑料黑名单用户爆料接口
     */
    public final static String BLACKLISTS = BASEURL + "blacklists";
    /**
     * 塑料黑名单用户留言接口
     */
    public final static String BLACKLISTSCOMMENTS = BASEURL + "blacklists/comments";

}
