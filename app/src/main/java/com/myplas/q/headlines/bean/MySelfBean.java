package com.myplas.q.headlines.bean;

import java.io.Serializable;

/**
 * 编写： 黄双
 * 电话：15378412400
 * 邮箱：15378412400@163.com
 * 时间：2017/4/5 16:19
 */
public class MySelfBean implements Serializable{

    /**
     * err : 0
     * data : {"user_id":"9","c_name":"上海八通石化有限公司","credit_level":"AAAAA","credit_limit":2000000,"is_credit":"date_selected","pre_credit_limit":2000000,"credit_time":"1493282979"}
     */

    private DataBean data;


    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean implements Serializable {
        /**
         * user_id : 9
         * c_name : 上海八通石化有限公司
         * credit_level : AAAAA
         * credit_limit : 2000000
         * is_credit : date_selected
         * pre_credit_limit : 2000000
         * credit_time : 1493282979
         */

        private String user_id;
        private String c_name;
        private String credit_level;
        private int credit_limit;
        private String is_credit;
        private int pre_credit_limit;
        private String credit_time;

        public String getUser_id() {
            return user_id;
        }

        public void setUser_id(String user_id) {
            this.user_id = user_id;
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

        public int getCredit_limit() {
            return credit_limit;
        }

        public void setCredit_limit(int credit_limit) {
            this.credit_limit = credit_limit;
        }

        public String getIs_credit() {
            return is_credit;
        }

        public void setIs_credit(String is_credit) {
            this.is_credit = is_credit;
        }

        public int getPre_credit_limit() {
            return pre_credit_limit;
        }

        public void setPre_credit_limit(int pre_credit_limit) {
            this.pre_credit_limit = pre_credit_limit;
        }

        public String getCredit_time() {
            return credit_time;
        }

        public void setCredit_time(String credit_time) {
            this.credit_time = credit_time;
        }
    }
}
