package com.myplas.q.myself.beans;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * 编写： 黄双
 * 电话：15378412400
 * 邮箱：15378412400@163.com
 * 时间：2017/3/27 16:08
 */
public class MyZone implements Serializable {


    /**
     * code : 0
     * s_in_count : 2
     * s_out_count : 0
     * points : {"quan_points":2015}
     * leaveword : 7
     * myviewhistory : 0
     * message : 5
     * introduction : 0
     * myfans : 2
     * myconcerns : 12
     * data : {"user_id":41497,"name":"黄双","c_id":5041,"mobile":"15378412400","is_pass":0,"thumb":"http://myplas.ufile.ucloud.com.cn/upload/2018/04/teqsxs1mat.png","thumbcard":"http://myplas.ufile.ucloud.com.cn/upload/2018/04/rz0l3dfrew.png","c_name":"上海中晨电子商务股份有限公司","credit_level":"AAAAA","credit_limit":"600.00","is_credit":1,"pre_credit_limit":"600.00","credit_time":1488357059,"sex":0,"isshop":1,"rank":5275,"member_level":"列兵","shop_audit_status":0,"customerVip":0,"applyCustomerVip":0,"headingVip":0}
     * personal_banner : {"show":0,"img":"http://statics.myplas.com/myapp/img/banner_for_shop.jpg","native":1,"title":"标题","url":"m.myplas.com","type":"shop","id":0,"membership":0,"customer":0}
     * help : ["http://statics.myplas.com/myapp/img/h1.jpg","http://statics.myplas.com/myapp/img/h2.jpg","http://statics.myplas.com/myapp/img/h3.jpg","http://statics.myplas.com/myapp/img/h4.jpg","http://statics.myplas.com/myapp/img/h5.jpg"]
     */

    private String code;
    private String s_in_count;
    private String s_out_count;
    private PointsBean points;
    private String leaveword;
    private String myviewhistory;
    private String message;
    private String introduction;
    private String myfans;
    private String myconcerns;
    private DataBean data;
    private PersonalBannerBean personal_banner;
    private List<String> help;
    private String mStatus;

    public void setStatus(String status) {
        mStatus = status;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getS_in_count() {
        return s_in_count;
    }

    public void setS_in_count(String s_in_count) {
        this.s_in_count = s_in_count;
    }

    public String getS_out_count() {
        return s_out_count;
    }

    public void setS_out_count(String s_out_count) {
        this.s_out_count = s_out_count;
    }

    public PointsBean getPoints() {
        return points;
    }

    public void setPoints(PointsBean points) {
        this.points = points;
    }

    public String getLeaveword() {
        return leaveword;
    }

    public void setLeaveword(String leaveword) {
        this.leaveword = leaveword;
    }

    public String getMyviewhistory() {
        return myviewhistory;
    }

    public void setMyviewhistory(String myviewhistory) {
        this.myviewhistory = myviewhistory;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public String getMyfans() {
        return myfans;
    }

    public void setMyfans(String myfans) {
        this.myfans = myfans;
    }

    public String getMyconcerns() {
        return myconcerns;
    }

    public void setMyconcerns(String myconcerns) {
        this.myconcerns = myconcerns;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public PersonalBannerBean getPersonal_banner() {
        return personal_banner;
    }

    public void setPersonal_banner(PersonalBannerBean personal_banner) {
        this.personal_banner = personal_banner;
    }

    public List<String> getHelp() {
        return help;
    }

    public void setHelp(List<String> help) {
        this.help = help;
    }

    public CharSequence getStatus() {
        return mStatus;
    }

    public static class PointsBean {
        /**
         * quan_points : 2015
         */

        private String quan_points;

        public String getQuan_points() {
            return quan_points;
        }

        public void setQuan_points(String quan_points) {
            this.quan_points = quan_points;
        }
    }

    public static class DataBean implements Serializable {
        /**
         * user_id : 41497
         * name : 黄双
         * c_id : 5041
         * mobile : 15378412400
         * is_pass : 0
         * thumb : http://myplas.ufile.ucloud.com.cn/upload/2018/04/teqsxs1mat.png
         * thumbcard : http://myplas.ufile.ucloud.com.cn/upload/2018/04/rz0l3dfrew.png
         * c_name : 上海中晨电子商务股份有限公司
         * credit_level : AAAAA
         * credit_limit : 600.00
         * is_credit : 1
         * pre_credit_limit : 600.00
         * credit_time : 1488357059
         * sex : 0
         * isshop : 1
         * rank : 5275
         * member_level : 列兵
         * shop_audit_status : 0
         * customerVip : 0
         * applyCustomerVip : 0
         * headingVip : 0
         */

        private String user_id;
        private String name;
        private String c_id;
        private String mobile;
        private String is_pass;
        private String thumb;
        private String thumbcard;
        private String c_name;
        private String credit_level;
        private String credit_limit;
        private String is_credit;
        private String pre_credit_limit;
        private String credit_time;
        private String sex;
        private String isshop;
        private String rank;
        private String member_level;
        private String shop_audit_status;
        private String customerVip;
        private String applyCustomerVip;
        private String headingVip;

        public String getUser_id() {
            return user_id;
        }

        public void setUser_id(String user_id) {
            this.user_id = user_id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getC_id() {
            return c_id;
        }

        public void setC_id(String c_id) {
            this.c_id = c_id;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public String getIs_pass() {
            return is_pass;
        }

        public void setIs_pass(String is_pass) {
            this.is_pass = is_pass;
        }

        public String getThumb() {
            return thumb;
        }

        public void setThumb(String thumb) {
            this.thumb = thumb;
        }

        public String getThumbcard() {
            return thumbcard;
        }

        public void setThumbcard(String thumbcard) {
            this.thumbcard = thumbcard;
        }

        public String getC_name() {
            return c_name;
        }

        public void setC_name(String c_name) {
            this.c_name = c_name;
        }

        public String getCredit_level() {
            return credit_level;
        }

        public void setCredit_level(String credit_level) {
            this.credit_level = credit_level;
        }

        public String getCredit_limit() {
            return credit_limit;
        }

        public void setCredit_limit(String credit_limit) {
            this.credit_limit = credit_limit;
        }

        public String getIs_credit() {
            return is_credit;
        }

        public void setIs_credit(String is_credit) {
            this.is_credit = is_credit;
        }

        public String getPre_credit_limit() {
            return pre_credit_limit;
        }

        public void setPre_credit_limit(String pre_credit_limit) {
            this.pre_credit_limit = pre_credit_limit;
        }

        public String getCredit_time() {
            return credit_time;
        }

        public void setCredit_time(String credit_time) {
            this.credit_time = credit_time;
        }

        public String getSex() {
            return sex;
        }

        public void setSex(String sex) {
            this.sex = sex;
        }

        public String getIsshop() {
            return isshop;
        }

        public void setIsshop(String isshop) {
            this.isshop = isshop;
        }

        public String getRank() {
            return rank;
        }

        public void setRank(String rank) {
            this.rank = rank;
        }

        public String getMember_level() {
            return member_level;
        }

        public void setMember_level(String member_level) {
            this.member_level = member_level;
        }

        public String getShop_audit_status() {
            return shop_audit_status;
        }

        public void setShop_audit_status(String shop_audit_status) {
            this.shop_audit_status = shop_audit_status;
        }

        public String getCustomerVip() {
            return customerVip;
        }

        public void setCustomerVip(String customerVip) {
            this.customerVip = customerVip;
        }

        public String getApplyCustomerVip() {
            return applyCustomerVip;
        }

        public void setApplyCustomerVip(String applyCustomerVip) {
            this.applyCustomerVip = applyCustomerVip;
        }

        public String getHeadingVip() {
            return headingVip;
        }

        public void setHeadingVip(String headingVip) {
            this.headingVip = headingVip;
        }
    }

    public static class PersonalBannerBean {
        /**
         * show : 0
         * img : http://statics.myplas.com/myapp/img/banner_for_shop.jpg
         * native : 1
         * title : 标题
         * url : m.myplas.com
         * type : shop
         * id : 0
         * membership : 0
         * customer : 0
         */

        private String show;
        private String img;
        private String isNative;
        private String title;
        private String url;
        private String type;
        private String id;
        private String membership;
        private String customer;

        public String getShow() {
            return show;
        }

        public void setShow(String show) {
            this.show = show;
        }

        public String getImg() {
            return img;
        }

        public void setImg(String img) {
            this.img = img;
        }

        public String getIsNative() {
            return isNative == null ? "" : isNative;
        }

        public void setIsNative(String isNative) {
            this.isNative = isNative;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getMembership() {
            return membership;
        }

        public void setMembership(String membership) {
            this.membership = membership;
        }

        public String getCustomer() {
            return customer;
        }

        public void setCustomer(String customer) {
            this.customer = customer;
        }
    }
}
