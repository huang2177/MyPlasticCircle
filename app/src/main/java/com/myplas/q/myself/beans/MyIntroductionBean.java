package com.myplas.q.myself.beans;

import java.util.List;

/**
 * 编写： 黄双
 * 电话：15378412400
 * 邮箱：15378412400@163.com
 * 时间：2017/3/29 17:18
 */
public class MyIntroductionBean {


    /**
     * err : 0
     * data : [{"user_id":"9266","name":"成平","mobile":"1370199****","is_pass":"","c_name":"上海梓辰实业有限公司","thumb":"http://pic.myplas.com/upload/","type":"1","buy":"","sale":""}]
     * count : 1
     */

    private int err;
    private String count;
    private List<DataBean> data;

    public int getErr() {
        return err;
    }

    public void setErr(int err) {
        this.err = err;
    }

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
         * user_id : 9266
         * name : 成平
         * mobile : 1370199****
         * is_pass :
         * c_name : 上海梓辰实业有限公司
         * thumb : http://pic.myplas.com/upload/
         * type : 1
         * buy :
         * sale :
         */

        private String user_id;
        private String name;
        private String mobile;
        private String is_pass;
        private String c_name;
        private String thumb;
        private String type;
        private String buy;
        private String sale;
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

        public String getIs_pass() {
            return is_pass;
        }

        public void setIs_pass(String is_pass) {
            this.is_pass = is_pass;
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
    }
}
