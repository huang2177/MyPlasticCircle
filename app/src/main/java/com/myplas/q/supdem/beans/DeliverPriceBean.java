package com.myplas.q.supdem.beans;

import java.util.List;

/**
 * 编写： 黄双
 * 电话：15378412400
 * 邮箱：15378412400@163.com
 * 时间：2017/3/31 20:51
 */
public class DeliverPriceBean {


    /**
     * err : 0
     * data : [{"id":"309","pur_id":"114111","send_id":"53397","user_id":"3858","price":"55.00","type":"2","status":"0","input_time":"10-10 16:40","is_read":"0","name":"测试","thumb":"http://pic.myplas.com/upload/17/09/14/59b9f291e30dc.png","c_name":"上海测试科技","mobile":"18817391111","thumbqq":"","is_pass":"0"},{"id":"308","pur_id":"114111","send_id":"53397","user_id":"3858","price":"22.00","type":"2","status":"0","input_time":"10-10 16:38","is_read":"0","name":"测试","thumb":"http://pic.myplas.com/upload/17/09/14/59b9f291e30dc.png","c_name":"上海测试科技","mobile":"18817391111","thumbqq":"","is_pass":"0"},{"id":"307","pur_id":"114111","send_id":"53397","user_id":"3858","price":"33.00","type":"2","status":"0","input_time":"10-10 16:37","is_read":"0","name":"测试","thumb":"http://pic.myplas.com/upload/17/09/14/59b9f291e30dc.png","c_name":"上海测试科技","mobile":"18817391111","thumbqq":"","is_pass":"0"},{"id":"306","pur_id":"114111","send_id":"53397","user_id":"3858","price":"32.00","type":"2","status":"0","input_time":"10-10 11:22","is_read":"0","name":"测试","thumb":"http://pic.myplas.com/upload/17/09/14/59b9f291e30dc.png","c_name":"上海测试科技","mobile":"18817391111","thumbqq":"","is_pass":"0"},{"id":"305","pur_id":"114111","send_id":"53397","user_id":"3858","price":"23.00","type":"2","status":"0","input_time":"10-10 11:22","is_read":"0","name":"测试","thumb":"http://pic.myplas.com/upload/17/09/14/59b9f291e30dc.png","c_name":"上海测试科技","mobile":"18817391111","thumbqq":"","is_pass":"0"},{"id":"304","pur_id":"114111","send_id":"53397","user_id":"3858","price":"2323.00","type":"2","status":"0","input_time":"10-10 11:22","is_read":"0","name":"测试","thumb":"http://pic.myplas.com/upload/17/09/14/59b9f291e30dc.png","c_name":"上海测试科技","mobile":"18817391111","thumbqq":"","is_pass":"0"},{"id":"303","pur_id":"114111","send_id":"53397","user_id":"3858","price":"32.00","type":"2","status":"0","input_time":"10-10 11:10","is_read":"0","name":"测试","thumb":"http://pic.myplas.com/upload/17/09/14/59b9f291e30dc.png","c_name":"上海测试科技","mobile":"18817391111","thumbqq":"","is_pass":"0"},{"id":"302","pur_id":"114111","send_id":"53397","user_id":"3858","price":"23.00","type":"2","status":"0","input_time":"10-10 10:08","is_read":"0","name":"测试","thumb":"http://pic.myplas.com/upload/17/09/14/59b9f291e30dc.png","c_name":"上海测试科技","mobile":"18817391111","thumbqq":"","is_pass":"0"}]
     */

    private int err;
    private List<DataBean> data;

    public int getErr() {
        return err;
    }

    public void setErr(int err) {
        this.err = err;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * id : 309
         * pur_id : 114111
         * send_id : 53397
         * user_id : 3858
         * price : 55.00
         * type : 2
         * status : 0
         * input_time : 10-10 16:40
         * is_read : 0
         * name : 测试
         * thumb : http://pic.myplas.com/upload/17/09/14/59b9f291e30dc.png
         * c_name : 上海测试科技
         * mobile : 18817391111
         * thumbqq :
         * is_pass : 0
         */

        private String id;
        private String pur_id;
        private String send_id;
        private String user_id;
        private String price;
        private String type;
        private String status;
        private String input_time;
        private String is_read;
        private String name;
        private String thumb;
        private String c_name;
        private String mobile;
        private String thumbqq;
        private String is_pass;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getPur_id() {
            return pur_id;
        }

        public void setPur_id(String pur_id) {
            this.pur_id = pur_id;
        }

        public String getSend_id() {
            return send_id;
        }

        public void setSend_id(String send_id) {
            this.send_id = send_id;
        }

        public String getUser_id() {
            return user_id;
        }

        public void setUser_id(String user_id) {
            this.user_id = user_id;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getInput_time() {
            return input_time;
        }

        public void setInput_time(String input_time) {
            this.input_time = input_time;
        }

        public String getIs_read() {
            return is_read;
        }

        public void setIs_read(String is_read) {
            this.is_read = is_read;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getThumb() {
            return thumb;
        }

        public void setThumb(String thumb) {
            this.thumb = thumb;
        }

        public String getC_name() {
            return c_name;
        }

        public void setC_name(String c_name) {
            this.c_name = c_name;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public String getThumbqq() {
            return thumbqq;
        }

        public void setThumbqq(String thumbqq) {
            this.thumbqq = thumbqq;
        }

        public String getIs_pass() {
            return is_pass;
        }

        public void setIs_pass(String is_pass) {
            this.is_pass = is_pass;
        }
    }
}
