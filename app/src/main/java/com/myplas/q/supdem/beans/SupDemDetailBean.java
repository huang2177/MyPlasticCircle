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
     * code : 0
     * data : {"id":197833,"user_id":81088,"model":"7000F","unit_price":"12500.00","store_house":"上海","cargo_type":1,"f_name":"神华","type":1,"input_time":"02-06 17:48","member_level":"列兵","is_pass":0,"name":"郑成功","c_name":"上海测试封装有限公司","status":1,"mobile":"18817392655","mobile1":"18817392655","thumb":"http://myplas.ufile.ucloud.com.cn/upload/18/01/17/V41CW99H1F.jpg","fans":2}
     * qapp_status : ["common_user"]
     */

    private String code;
    private DataBean data;
    private List<String> qapp_status;

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

    public List<String> getQapp_status() {
        return qapp_status;
    }

    public void setQapp_status(List<String> qapp_status) {
        this.qapp_status = qapp_status;
    }

    public static class DataBean {
        /**
         * id : 197833
         * user_id : 81088
         * model : 7000F
         * unit_price : 12500.00
         * store_house : 上海
         * cargo_type : 1
         * f_name : 神华
         * type : 1
         * input_time : 02-06 17:48
         * member_level : 列兵
         * is_pass : 0
         * name : 郑成功
         * c_name : 上海测试封装有限公司
         * status : 1
         * mobile : 18817392655
         * mobile1 : 18817392655
         * thumb : http://myplas.ufile.ucloud.com.cn/upload/18/01/17/V41CW99H1F.jpg
         * fans : 2
         */

        private String id;
        private String user_id;
        private String model;
        private String unit_price;
        private String store_house;
        private String cargo_type;
        private String f_name;
        private String type;
        private String input_time;
        private String member_level;
        private String is_pass;
        private String name;
        private String c_name;
        private String status;
        private String mobile;
        private String mobile1;
        private String thumb;
        private String fans;
        private String isshop;
        private String company_type;

        public void setCompany_type(String company_type) {
            this.company_type = company_type;
        }

        public String getCompany_type() {
            return company_type;
        }

        public void setIsshop(String isshop) {
            this.isshop = isshop;
        }

        public String getIsshop() {
            return isshop;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getUser_id() {
            return user_id;
        }

        public void setUser_id(String user_id) {
            this.user_id = user_id;
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

        public String getMember_level() {
            return member_level;
        }

        public void setMember_level(String member_level) {
            this.member_level = member_level;
        }

        public String getIs_pass() {
            return is_pass;
        }

        public void setIs_pass(String is_pass) {
            this.is_pass = is_pass;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getC_name() {
            return c_name;
        }

        public void setC_name(String c_name) {
            this.c_name = c_name;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public String getMobile1() {
            return mobile1;
        }

        public void setMobile1(String mobile1) {
            this.mobile1 = mobile1;
        }

        public String getThumb() {
            return thumb;
        }

        public void setThumb(String thumb) {
            this.thumb = thumb;
        }

        public String getFans() {
            return fans;
        }

        public void setFans(String fans) {
            this.fans = fans;
        }
    }
}
