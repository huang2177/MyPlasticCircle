package com.myplas.q.supdem.beans;

import java.util.List;

/**
 * 编写： 黄双
 * 电话：15378412400
 * 邮箱：15378412400@163.com
 * 时间：2017/3/19 14:57
 */
public class SupDemBean {


    /**
     * err : 0
     * data : [{"id":"373","name":"胖墩","sync":"6","model":"9001","type":"2","from":"1","c_name":"上海中信有限公司","user_id":"2062","f_name":"台湾塑胶","input_time":"10-24 14:51","cargo_type":"2","unit_price":"10900.00","store_house":"广州","img":"http://pic.myplas.com/myapp/img/icon_supply.png"},{"id":"372","name":"胖墩","sync":"6","model":"9001","type":"2","from":"1","c_name":"上海中信有限公司","user_id":"2062","f_name":"台湾塑胶","input_time":"10-24 14:50","cargo_type":"2","unit_price":"10900.00","store_house":"广州","img":"http://pic.myplas.com/myapp/img/icon_supply.png"},{"id":"371","name":"胖墩","sync":"6","model":"9001","type":"1","from":"1","c_name":"上海中信有限公司","user_id":"2062","f_name":"台湾塑胶","input_time":"10-24 14:50","cargo_type":"2","unit_price":"10900.00","store_house":"广州","img":"http://pic.myplas.com/myapp/img/icon_purchase.png"},{"id":"370","name":"胖墩","sync":"6","model":"9001","type":"1","from":"1","c_name":"上海中信有限公司","user_id":"2062","f_name":"台湾塑胶","input_time":"10-24 14:50","cargo_type":"2","unit_price":"11000.00","store_house":"上海","img":"http://pic.myplas.com/myapp/img/icon_purchase.png"},{"id":"369","name":"胖墩","sync":"6","model":"BL3 ","type":"1","from":"1","c_name":"上海中信有限公司","user_id":"2062","f_name":"伊朗石化","input_time":"10-24 14:50","cargo_type":"2","unit_price":"10500.00","store_house":"宁波","img":"http://pic.myplas.com/myapp/img/icon_purchase.png"},{"id":"368","name":"胖墩","sync":"6","model":"9001","type":"1","from":"1","c_name":"上海中信有限公司","user_id":"2062","f_name":"台湾塑胶","input_time":"10-24 14:49","cargo_type":"2","unit_price":"10900.00","store_house":"广州","img":"http://pic.myplas.com/myapp/img/icon_purchase.png"},{"id":"367","name":"胖墩","sync":"6","model":"9001","type":"2","from":"1","c_name":"上海中信有限公司","user_id":"2062","f_name":"台湾塑胶","input_time":"10-24 14:48","cargo_type":"2","unit_price":"10900.00","store_house":"广州","img":"http://pic.myplas.com/myapp/img/icon_supply.png"},{"id":"366","name":"胖墩","sync":"6","model":"9001","type":"2","from":"1","c_name":"上海中信有限公司","user_id":"2062","f_name":"台湾塑胶","input_time":"10-24 14:48","cargo_type":"2","unit_price":"11000.00","store_house":"上海","img":"http://pic.myplas.com/myapp/img/icon_supply.png"},{"id":"365","name":"胖墩","sync":"6","model":"BL3 ","type":"2","from":"1","c_name":"上海中信有限公司","user_id":"2062","f_name":"伊朗石化","input_time":"10-24 14:48","cargo_type":"2","unit_price":"10500.00","store_house":"宁波","img":"http://pic.myplas.com/myapp/img/icon_supply.png"},{"id":"364","name":"韩梅梅","sync":"6","model":"2119","type":"2","from":"1","c_name":"中国能之光新材料科技股份有限公司（宁波分公司）","user_id":"53441","f_name":"伊朗石化","input_time":"10-24 14:44","cargo_type":"1","unit_price":"19000.00","store_house":"上海","img":"http://pic.myplas.com/myapp/img/icon_supply.png"}]
     * top : {"id":"307","p_id":"3444","from":"1","user_id":"53441","model":"7000F","unit_price":"0.00","store_house":"","cargo_type":"1","f_name":"伊朗石化","type":"2","input_time":"10-19 16:17","top":"0","name":"韩梅梅","fans":"6","c_name":"中国能之光新材料科技股份有限公司（宁波分公司）","is_pass":"0","member_level":"列兵","status":"已关注","img":"http://pic.myplas.com/myapp/img/icon_supply.png"}
     * hot_search : 7000F
     * show_msg :
     */

    private int err;
    private TopBean top;
    private String hot_search;
    private String show_msg;
    private List<DataBean> data;

    public int getErr() {
        return err;
    }

    public void setErr(int err) {
        this.err = err;
    }

    public TopBean getTop() {
        return top;
    }

    public void setTop(TopBean top) {
        this.top = top;
    }

    public String getHot_search() {
        return hot_search;
    }

    public void setHot_search(String hot_search) {
        this.hot_search = hot_search;
    }

    public String getShow_msg() {
        return show_msg;
    }

    public void setShow_msg(String show_msg) {
        this.show_msg = show_msg;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class TopBean {
        /**
         * id : 307
         * p_id : 3444
         * from : 1
         * user_id : 53441
         * model : 7000F
         * unit_price : 0.00
         * store_house :
         * cargo_type : 1
         * f_name : 伊朗石化
         * type : 2
         * input_time : 10-19 16:17
         * top : 0
         * name : 韩梅梅
         * fans : 6
         * c_name : 中国能之光新材料科技股份有限公司（宁波分公司）
         * is_pass : 0
         * member_level : 列兵
         * status : 已关注
         * img : http://pic.myplas.com/myapp/img/icon_supply.png
         */

        private String id;
        private String p_id;
        private String from;
        private String user_id;
        private String model;
        private String unit_price;
        private String store_house;
        private String cargo_type;
        private String f_name;
        private String type;
        private String input_time;
        private String top;
        private String name;
        private String fans;
        private String c_name;
        private String is_pass;
        private String member_level;
        private String status;
        private String img;

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

        public String getMember_level() {
            return member_level;
        }

        public void setMember_level(String member_level) {
            this.member_level = member_level;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getImg() {
            return img;
        }

        public void setImg(String img) {
            this.img = img;
        }
    }

    public static class DataBean {
        /**
         * id : 196438
         * name : 王铭
         * sync : 6
         * model : 2119
         * type : 1
         * from : 1
         * c_name : 上海梓晨实业有限公司
         * user_id : 53991
         * f_name : 上海
         * input_time : 11-29 10:21
         * cargo_type : 1
         * unit_price : 9500.00
         * store_house : 上海
         * img : http://pic.myplas.com/myapp/img/icon_purchase.png
         * platicCount : 0
         * replyCount : 0
         */

        private String id;
        private String name;
        private String sync;
        private String model;
        private String type;
        private String from;
        private String c_name;
        private String user_id;
        private String f_name;
        private String input_time;
        private String cargo_type;
        private String unit_price;
        private String store_house;
        private String img;
        private String platicCount;
        private String replyCount;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getSync() {
            return sync;
        }

        public void setSync(String sync) {
            this.sync = sync;
        }

        public String getModel() {
            return model;
        }

        public void setModel(String model) {
            this.model = model;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getFrom() {
            return from;
        }

        public void setFrom(String from) {
            this.from = from;
        }

        public String getC_name() {
            return c_name;
        }

        public void setC_name(String c_name) {
            this.c_name = c_name;
        }

        public String getUser_id() {
            return user_id;
        }

        public void setUser_id(String user_id) {
            this.user_id = user_id;
        }

        public String getF_name() {
            return f_name;
        }

        public void setF_name(String f_name) {
            this.f_name = f_name;
        }

        public String getInput_time() {
            return input_time;
        }

        public void setInput_time(String input_time) {
            this.input_time = input_time;
        }

        public String getCargo_type() {
            return cargo_type;
        }

        public void setCargo_type(String cargo_type) {
            this.cargo_type = cargo_type;
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

        public String getImg() {
            return img;
        }

        public void setImg(String img) {
            this.img = img;
        }

        public String getPlaticCount() {
            return platicCount;
        }

        public void setPlaticCount(String platicCount) {
            this.platicCount = platicCount;
        }

        public String getReplyCount() {
            return replyCount;
        }

        public void setReplyCount(String replyCount) {
            this.replyCount = replyCount;
        }
    }
}
