package com.myplas.q.supdem.beans;

import java.util.List;

/**
 * 编写： 黄双
 * 电话：15378412400
 * 邮箱：15378412400@163.com
 * 时间：2017/3/31 17:47
 */
public class SupDemDetailBean {


    /**
     * err : 0
     * data : {"id":"195914","p_id":"3595","mobile":"18817392631","from":"1","user_id":"2062","stype":"1","model":"BL3","unit_price":"10500.00","store_house":"宁波","cargo_type":"2","f_name":"伊朗石化","type":"2","input_time":"11-03 13:58","top":"0","status":0,"name":"胖墩","fans":"218","thumb":"http://pic.myplas.com/upload/17/07/12/5965b7ff066cf.jpg","c_name":"上海中信有限公司","is_pass":"","mobile1":"18817392631","member_level":"列兵"}
     * qapp_status : ["common_user"]
     */

    private int err;
    private DataBean data;
    private List<String> qapp_status;

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

    public List<String> getQapp_status() {
        return qapp_status;
    }

    public void setQapp_status(List<String> qapp_status) {
        this.qapp_status = qapp_status;
    }

    public static class DataBean {
        /**
         * id : 195914
         * p_id : 3595
         * mobile : 18817392631
         * from : 1
         * user_id : 2062
         * stype : 1
         * model : BL3
         * unit_price : 10500.00
         * store_house : 宁波
         * cargo_type : 2
         * f_name : 伊朗石化
         * type : 2
         * input_time : 11-03 13:58
         * top : 0
         * status : 0
         * name : 胖墩
         * fans : 218
         * thumb : http://pic.myplas.com/upload/17/07/12/5965b7ff066cf.jpg
         * c_name : 上海中信有限公司
         * is_pass :
         * mobile1 : 18817392631
         * member_level : 列兵
         */

        private String id;
        private String p_id;
        private String mobile;
        private String from;
        private String user_id;
        private String stype;
        private String model;
        private String unit_price;
        private String store_house;
        private String cargo_type;
        private String f_name;
        private String type;
        private String input_time;
        private String top;
        private String status;
        private String name;
        private String fans;
        private String thumb;
        private String c_name;
        private String is_pass;
        private String mobile1;
        private String member_level;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getP_id() {
            return p_id;
        }

        public void setP_id(String p_id) {
            this.p_id = p_id;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public String getFrom() {
            return from;
        }

        public void setFrom(String from) {
            this.from = from;
        }

        public String getUser_id() {
            return user_id;
        }

        public void setUser_id(String user_id) {
            this.user_id = user_id;
        }

        public String getStype() {
            return stype;
        }

        public void setStype(String stype) {
            this.stype = stype;
        }

        public String getModel() {
            return model;
        }

        public void setModel(String model) {
            this.model = model;
        }

        public String getUnit_price() {
            return unit_price;
        }

        public void setUnit_price(String unit_price) {
            this.unit_price = unit_price;
        }

        public String getStore_house() {
            return store_house;
        }

        public void setStore_house(String store_house) {
            this.store_house = store_house;
        }

        public String getCargo_type() {
            return cargo_type;
        }

        public void setCargo_type(String cargo_type) {
            this.cargo_type = cargo_type;
        }

        public String getF_name() {
            return f_name;
        }

        public void setF_name(String f_name) {
            this.f_name = f_name;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getInput_time() {
            return input_time;
        }

        public void setInput_time(String input_time) {
            this.input_time = input_time;
        }

        public String getTop() {
            return top;
        }

        public void setTop(String top) {
            this.top = top;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getFans() {
            return fans;
        }

        public void setFans(String fans) {
            this.fans = fans;
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

        public String getIs_pass() {
            return is_pass;
        }

        public void setIs_pass(String is_pass) {
            this.is_pass = is_pass;
        }

        public String getMobile1() {
            return mobile1;
        }

        public void setMobile1(String mobile1) {
            this.mobile1 = mobile1;
        }

        public String getMember_level() {
            return member_level;
        }

        public void setMember_level(String member_level) {
            this.member_level = member_level;
        }
    }
}
