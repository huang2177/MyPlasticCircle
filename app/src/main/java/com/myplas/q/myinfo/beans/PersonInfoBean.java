package com.myplas.q.myinfo.beans;

/**
 * 编写： 黄双
 * 电话：15378412400
 * 邮箱：15378412400@163.com
 * 时间：2017/3/30 15:30
 */
public class PersonInfoBean {

    /**
     * err : 0
     * data : {"user_id":"38560","name":"lili","c_id":"36654","is_pass":"0","mobile":"15136107074","sex":"男","thumb":"http://statics.myplas.com/myapp/img/male.jpg","thumbqq":"","thumbcard":"","c_name":"zhongchen","need_product":"","address":"","main_product":"","month_consum":"0.00","type":"0","buy":0,"sale":0,"status":"关注"}
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
         * user_id : 38560
         * name : lili
         * c_id : 36654
         * is_pass : 0
         * mobile : 15136107074
         * sex : 男
         * thumb : http://statics.myplas.com/myapp/img/male.jpg
         * thumbqq :
         * thumbcard :
         * c_name : zhongchen
         * need_product :
         * address :
         * main_product :
         * month_consum : 0.00
         * type : 0
         * buy : 0
         * sale : 0
         * status : 关注
         */

        private String user_id;
        private String name;
        private String c_id;
        private String is_pass;
        private String mobile;
        private String sex;
        private String thumb;
        private String thumbqq;
        private String thumbcard;
        private String c_name;
        private String need_product;
        private String address;
        private String main_product;
        private String month_consum;
        private String type;
        private int buy;
        private int sale;
        private String status;

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

        public String getIs_pass() {
            return is_pass;
        }

        public void setIs_pass(String is_pass) {
            this.is_pass = is_pass;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public String getSex() {
            return sex;
        }

        public void setSex(String sex) {
            this.sex = sex;
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

        public String getMain_product() {
            return main_product;
        }

        public void setMain_product(String main_product) {
            this.main_product = main_product;
        }

        public String getMonth_consum() {
            return month_consum;
        }

        public void setMonth_consum(String month_consum) {
            this.month_consum = month_consum;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public int getBuy() {
            return buy;
        }

        public void setBuy(int buy) {
            this.buy = buy;
        }

        public int getSale() {
            return sale;
        }

        public void setSale(int sale) {
            this.sale = sale;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }
    }
}
