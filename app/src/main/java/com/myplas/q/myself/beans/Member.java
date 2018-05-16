package com.myplas.q.myself.beans;

/**
 * @author Huangshuang  2018/5/2 0002
 */

public class Member {

    /**
     * code : 0
     * data : {"user_id":41497,"c_id":5041,"name":"黄双","mobile":"15378412400","c_name":"上海中晨电子商务股份有限公司","thumb":"http://myplas.ufile.ucloud.com.cn/upload/2018/04/3yjm9lb58f.png","type":2,"customerVip":0,"headingVip":1,"applyCustomerVip":0,"end_time":"2019-09_27"}
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
         * c_id : 5041
         * name : 黄双
         * mobile : 15378412400
         * c_name : 上海中晨电子商务股份有限公司
         * thumb : http://myplas.ufile.ucloud.com.cn/upload/2018/04/3yjm9lb58f.png
         * type : 2
         * customerVip : 0
         * headingVip : 1
         * applyCustomerVip : 0
         * end_time : 2019-09_27
         */

        private String user_id;
        private String c_id;
        private String name;
        private String mobile;
        private String c_name;
        private String thumb;
        private String type;
        private String customerVip;
        private String headingVip;
        private String applyCustomerVip;
        private String end_time;

        public String getUser_id() {
            return user_id;
        }

        public void setUser_id(String user_id) {
            this.user_id = user_id;
        }

        public String getC_id() {
            return c_id;
        }

        public void setC_id(String c_id) {
            this.c_id = c_id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public String getC_name() {
            return c_name;
        }

        public void setC_name(String c_name) {
            this.c_name = c_name;
        }

        public String getThumb() {
            return thumb;
        }

        public void setThumb(String thumb) {
            this.thumb = thumb;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getCustomerVip() {
            return customerVip;
        }

        public void setCustomerVip(String customerVip) {
            this.customerVip = customerVip;
        }

        public String getHeadingVip() {
            return headingVip;
        }

        public void setHeadingVip(String headingVip) {
            this.headingVip = headingVip;
        }

        public String getApplyCustomerVip() {
            return applyCustomerVip;
        }

        public void setApplyCustomerVip(String applyCustomerVip) {
            this.applyCustomerVip = applyCustomerVip;
        }

        public String getEnd_time() {
            return end_time;
        }

        public void setEnd_time(String end_time) {
            this.end_time = end_time;
        }
    }
}
