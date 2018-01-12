package com.myplas.q.homepage.beans;

import java.util.List;

/**
 * 编写： 黄双
 * 电话：15378412400
 * 邮箱：15378412400@163.com
 * 时间：2017/3/20 22:53
 */
public class MyFansBean {

    /**
     * err : 0
     * data : [{"user_id":"5","buy":"5","c_name":"5","is_pass":"5","mobile":"5","name":"5","sale":"5","thumb":"5","type":"5"},{"user_id":"5","buy":"5","c_name":"5","is_pass":"5","mobile":"5","name":"5","sale":"5","thumb":"5","type":"5"},{"user_id":"5","buy":"5","c_name":"5","is_pass":"5","mobile":"5","name":"5","sale":"5","thumb":"5","type":"5"},{"user_id":"5","buy":"5","c_name":"5","is_pass":"5","mobile":"5","name":"5","sale":"5","thumb":"5","type":"5"},{"user_id":"5","buy":"5","c_name":"5","is_pass":"5","mobile":"5","name":"5","sale":"5","thumb":"5","type":"5"},{"user_id":"5","buy":"5","c_name":"5","is_pass":"5","mobile":"5","name":"5","sale":"5","thumb":"5","type":"5"},{"user_id":"5","buy":"5","c_name":"5","is_pass":"5","mobile":"5","name":"5","sale":"5","thumb":"5","type":"5"},{"user_id":"5","buy":"5","c_name":"5","is_pass":"5","mobile":"5","name":"5","sale":"5","thumb":"5","type":"5"}]
     * count : 8
     */

    private String count;
    private List<DataBean> data;


    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * user_id : 5
         * buy : 5
         * c_name : 5
         * is_pass : 5
         * mobile : 5
         * name : 5
         * sale : 5
         * thumb : 5
         * type : 5
         */

        private String user_id;
        private String buy;
        private String c_name;
        private String is_pass;
        private String mobile;
        private String name;
        private String sale;
        private String thumb;
        private String type;
        private String merge_three;

        public void setMerge_three(String merge_three) {
            this.merge_three = merge_three;
        }

        public String getMerge_three() {
            return merge_three;
        }

        public String getUser_id() {
            return user_id;
        }

        public void setUser_id(String user_id) {
            this.user_id = user_id;
        }

        public String getBuy() {
            return buy;
        }

        public void setBuy(String buy) {
            this.buy = buy;
        }

        public String getC_name() {
            return c_name;
        }

        public void setC_name(String c_name) {
            this.c_name = c_name;
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

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getSale() {
            return sale;
        }

        public void setSale(String sale) {
            this.sale = sale;
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
    }
}
