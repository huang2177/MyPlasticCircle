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
     * err : 0
     * data : {"user_id":"53402","name":"hh","c_id":"5041","mobile":"15378412400","adistinct":"华北","sex":"男","member_level":"列兵","thumb":"http://pic.myplas.com/upload/17/08/22/599bb87bd79ef.PNG","thumbqq":"http://pic.myplas.com/upload/17/08/22/599bb87bd79ef.PNG","thumbcard":"http://pic.myplas.com/upload/17/08/14/599116f59415e.jpg","allow_send":{"focus":1,"repeat":0,"show":0},"c_name":"上海中晨电子商务股份有限公司","need_product":"5000F|2179","address":"北京北京东城区|上海","type":"1","month_consum":"100","main_product":"5000","origin":"|","buy":"1","sale":0,"total":3349,"rank":"22","fans":"7","concern_model":"500"}
     */

    private int err;
    private DataBean data;

    public int getErr() {
        return err;
    }

    public void setErr(int err) {
        this.err = err;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * user_id : 53402
         * name : hh
         * c_id : 5041
         * mobile : 15378412400
         * adistinct : 华北
         * sex : 男
         * member_level : 列兵
         * thumb : http://pic.myplas.com/upload/17/08/22/599bb87bd79ef.PNG
         * thumbqq : http://pic.myplas.com/upload/17/08/22/599bb87bd79ef.PNG
         * thumbcard : http://pic.myplas.com/upload/17/08/14/599116f59415e.jpg
         * allow_send : {"focus":1,"repeat":0,"show":0}
         * c_name : 上海中晨电子商务股份有限公司
         * need_product : 5000F|2179
         * address : 北京北京东城区|上海
         * type : 1
         * month_consum : 100
         * main_product : 5000
         * origin : |
         * buy : 1
         * sale : 0
         * total : 3349
         * rank : 22
         * fans : 7
         * concern_model : 500
         */

        private String user_id;
        private String name;
        private String c_id;
        private String mobile;
        private String adistinct;
        private String sex;
        private String member_level;
        private String thumb;
        private String thumbqq;
        private String thumbcard;
        private AllowSendBean allow_send;
        private String c_name;
        private String need_product;
        private String address;
        private String type;
        private String month_consum;
        private String main_product;
        private String origin;
        private String buy;
        private String sale;
        private String total;
        private String rank;
        private String fans;

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

        public String getThumbqq() {
            return thumbqq;
        }

        public void setThumbqq(String thumbqq) {
            this.thumbqq = thumbqq;
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

        public static class AllowSendBean implements Serializable {
            /**
             * focus : 1
             * repeat : 0
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
