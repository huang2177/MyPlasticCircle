package com.myplas.q.myself.beans;

import java.io.Serializable;

/**
 * 编写： 黄双
 * 电话：15378412400
 * 邮箱：15378412400@163.com
 * 时间：2017/3/27 19:16
 */
public class MySelfInfo implements Serializable {


    /**
     * code : 0
     * data : {"user_id":41497,"name":"黄双","c_id":607629,"mobile":"15378412400","adistinct":"华南","sex":"男","member_level":"列兵","thumb":"http://myplas.ufile.ucloud.com.cn/upload/2018/1/whnpugd6qj.jpg","thumbcard":"//newstatics.myplas.com/upload/17/12/26/5a41c4b1abf78.PNG","allow_send":{"focus":0,"repeat":1,"show":0},"is_allow_jpush":1,"c_name":"打得过","need_product":"","address":"","type":7,"month_consum":"","main_product":"pp","origin":"","business_licence_pic":"http://newstatics.myplas.com/upload/18/01/09/5a5423025159b.PNG","buy":2,"sale":10,"total":4401,"rank":7012,"fans":8,"concern_model":""}
     */

    private String code;
    private DataBean data;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * user_id : 41497
         * name : 黄双
         * c_id : 607629
         * mobile : 15378412400
         * adistinct : 华南
         * sex : 男
         * member_level : 列兵
         * thumb : http://myplas.ufile.ucloud.com.cn/upload/2018/1/whnpugd6qj.jpg
         * thumbcard : //newstatics.myplas.com/upload/17/12/26/5a41c4b1abf78.PNG
         * allow_send : {"focus":0,"repeat":1,"show":0}
         * is_allow_jpush : 1
         * c_name : 打得过
         * need_product : 
         * address : 
         * type : 7
         * month_consum : 
         * main_product : pp
         * origin : 
         * business_licence_pic : http://newstatics.myplas.com/upload/18/01/09/5a5423025159b.PNG
         * buy : 2
         * sale : 10
         * total : 4401
         * rank : 7012
         * fans : 8
         * concern_model : 
         */

        private String user_id;
        private String name;
        private String c_id;
        private String mobile;
        private String adistinct;
        private String sex;
        private String member_level;
        private String thumb;
        private String thumbcard;
        private AllowSendBean allow_send;
        private String is_allow_jpush;
        private String c_name;
        private String need_product;
        private String address;
        private String type;
        private String month_consum;
        private String main_product;
        private String origin;
        private String business_licence_pic;
        private String buy;
        private String sale;
        private String total;
        private String rank;
        private String fans;
        private String concern_model;

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

        public String getAdistinct() {
            return adistinct;
        }

        public void setAdistinct(String adistinct) {
            this.adistinct = adistinct;
        }

        public String getSex() {
            return sex;
        }

        public void setSex(String sex) {
            this.sex = sex;
        }

        public String getMember_level() {
            return member_level;
        }

        public void setMember_level(String member_level) {
            this.member_level = member_level;
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

        public AllowSendBean getAllow_send() {
            return allow_send;
        }

        public void setAllow_send(AllowSendBean allow_send) {
            this.allow_send = allow_send;
        }

        public String getIs_allow_jpush() {
            return is_allow_jpush;
        }

        public void setIs_allow_jpush(String is_allow_jpush) {
            this.is_allow_jpush = is_allow_jpush;
        }

        public String getC_name() {
            return c_name;
        }

        public void setC_name(String c_name) {
            this.c_name = c_name;
        }

        public String getNeed_product() {
            return need_product;
        }

        public void setNeed_product(String need_product) {
            this.need_product = need_product;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getMonth_consum() {
            return month_consum;
        }

        public void setMonth_consum(String month_consum) {
            this.month_consum = month_consum;
        }

        public String getMain_product() {
            return main_product;
        }

        public void setMain_product(String main_product) {
            this.main_product = main_product;
        }

        public String getOrigin() {
            return origin;
        }

        public void setOrigin(String origin) {
            this.origin = origin;
        }

        public String getBusiness_licence_pic() {
            return business_licence_pic;
        }

        public void setBusiness_licence_pic(String business_licence_pic) {
            this.business_licence_pic = business_licence_pic;
        }

        public String getBuy() {
            return buy;
        }

        public void setBuy(String buy) {
            this.buy = buy;
        }

        public String getSale() {
            return sale;
        }

        public void setSale(String sale) {
            this.sale = sale;
        }

        public String getTotal() {
            return total;
        }

        public void setTotal(String total) {
            this.total = total;
        }

        public String getRank() {
            return rank;
        }

        public void setRank(String rank) {
            this.rank = rank;
        }

        public String getFans() {
            return fans;
        }

        public void setFans(String fans) {
            this.fans = fans;
        }

        public String getConcern_model() {
            return concern_model;
        }

        public void setConcern_model(String concern_model) {
            this.concern_model = concern_model;
        }

        public static class AllowSendBean {
            /**
             * focus : 0
             * repeat : 1
             * show : 0
             */

            private String focus;
            private String repeat;
            private String show;

            public String getFocus() {
                return focus;
            }

            public void setFocus(String focus) {
                this.focus = focus;
            }

            public String getRepeat() {
                return repeat;
            }

            public void setRepeat(String repeat) {
                this.repeat = repeat;
            }

            public String getShow() {
                return show;
            }

            public void setShow(String show) {
                this.show = show;
            }
        }
    }
}
