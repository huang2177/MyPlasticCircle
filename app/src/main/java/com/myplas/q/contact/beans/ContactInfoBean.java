package com.myplas.q.contact.beans;

import java.io.Serializable;
import java.util.List;

/**
 * 作者:huangshuang
 * 事件 2017/10/17 0017.
 * 邮箱： 15378412400@163.com
 */

public class ContactInfoBean implements Serializable {


    /**
     * err : 0
     * data : {"user_id":"53441","name":"韩梅梅","c_id":"50377","mobile":"15921211765","sex":"女","member_level":"列兵","thumb":"http://pic.myplas.com/myapp/img/female.jpg","thumbqq":"","c_name":"中国能之光新材料科技股份有限公司（宁波分公司）","type":"1","is_follow":"1","ranking":36,"is_vip":0,"followers":8,"fans":5,"recommendation":0,"supplies":[{"pur_id":"436","model":"9001","f_name":"台湾塑胶                ","store_house":"上海","unit_price":"11000.00","cargo_type":0,"input_time":"10月25日 14:11","bid":"1","reply":"1","user_id":53441,"thumb":"http://pic.myplas.com/myapp/img/female.jpg"},{"pur_id":"435","model":"BL3","f_name":"伊朗石化","store_house":"宁波","unit_price":"10500.00","cargo_type":1,"input_time":"10月25日 14:11","bid":"0","reply":"0","user_id":53441,"thumb":"http://pic.myplas.com/myapp/img/female.jpg"},{"pur_id":"415","model":"7000F","f_name":"伊朗石化","store_house":"","unit_price":"0.00","cargo_type":0,"input_time":"10月24日 18:14","bid":"1","reply":"1","user_id":53441,"thumb":"http://pic.myplas.com/myapp/img/female.jpg"},{"pur_id":"405","model":"7000F","f_name":"伊朗石化","store_house":"","unit_price":"10500.00","cargo_type":0,"input_time":"10月24日 17:58","bid":"0","reply":"0","user_id":53441,"thumb":"http://pic.myplas.com/myapp/img/female.jpg"},{"pur_id":"386","model":"9001","f_name":"台湾塑胶                ","store_house":"上海","unit_price":"11000.00","cargo_type":1,"input_time":"10月24日 16:36","bid":"0","reply":"0","user_id":53441,"thumb":"http://pic.myplas.com/myapp/img/female.jpg"}],"demand":[{"pur_id":"387","model":"9001","f_name":"台湾塑胶","store_house":"广州","unit_price":"10800.00","cargo_type":1,"input_time":"10月24日 16:36","bid":"0","reply":"0","thumb":"http://pic.myplas.com/myapp/img/female.jpg","user_id":53441},{"pur_id":"352","model":"9001","f_name":"台湾塑胶                ","store_house":"广州","unit_price":"10900.00","cargo_type":1,"input_time":"10月24日 11:43","bid":"0","reply":"0","thumb":"http://pic.myplas.com/myapp/img/female.jpg","user_id":53441},{"pur_id":"351","model":"9001","f_name":"台湾塑胶                ","store_house":"上海","unit_price":"11000.00","cargo_type":1,"input_time":"10月24日 11:43","bid":"0","reply":"0","thumb":"http://pic.myplas.com/myapp/img/female.jpg","user_id":53441},{"pur_id":"350","model":"BL3","f_name":"伊朗石化","store_house":"宁波","unit_price":"10500.00","cargo_type":1,"input_time":"10月24日 11:43","bid":"0","reply":"0","thumb":"http://pic.myplas.com/myapp/img/female.jpg","user_id":53441},{"pur_id":"338","model":"9001","f_name":"台湾塑胶                ","store_house":"广州","unit_price":"10900.00","cargo_type":1,"input_time":"10月24日 11:33","bid":"0","reply":"0","thumb":"http://pic.myplas.com/myapp/img/female.jpg","user_id":53441}]}
     */

    private String err;
    private DataBean data;

    public String getErr() {
        return err;
    }

    public void setErr(String err) {
        this.err = err;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean implements Serializable {
        /**
         * user_id : 53441
         * name : 韩梅梅
         * c_id : 50377
         * mobile : 15921211765
         * sex : 女
         * member_level : 列兵
         * thumb : http://pic.myplas.com/myapp/img/female.jpg
         * thumbqq :
         * c_name : 中国能之光新材料科技股份有限公司（宁波分公司）
         * type : 1
         * is_follow : 1
         * ranking : 36
         * is_vip : 0
         * followers : 8
         * fans : 5
         * recommendation : 0
         * supplies : [{"pur_id":"436","model":"9001","f_name":"台湾塑胶                ","store_house":"上海","unit_price":"11000.00","cargo_type":0,"input_time":"10月25日 14:11","bid":"1","reply":"1","user_id":53441,"thumb":"http://pic.myplas.com/myapp/img/female.jpg"},{"pur_id":"435","model":"BL3","f_name":"伊朗石化","store_house":"宁波","unit_price":"10500.00","cargo_type":1,"input_time":"10月25日 14:11","bid":"0","reply":"0","user_id":53441,"thumb":"http://pic.myplas.com/myapp/img/female.jpg"},{"pur_id":"415","model":"7000F","f_name":"伊朗石化","store_house":"","unit_price":"0.00","cargo_type":0,"input_time":"10月24日 18:14","bid":"1","reply":"1","user_id":53441,"thumb":"http://pic.myplas.com/myapp/img/female.jpg"},{"pur_id":"405","model":"7000F","f_name":"伊朗石化","store_house":"","unit_price":"10500.00","cargo_type":0,"input_time":"10月24日 17:58","bid":"0","reply":"0","user_id":53441,"thumb":"http://pic.myplas.com/myapp/img/female.jpg"},{"pur_id":"386","model":"9001","f_name":"台湾塑胶                ","store_house":"上海","unit_price":"11000.00","cargo_type":1,"input_time":"10月24日 16:36","bid":"0","reply":"0","user_id":53441,"thumb":"http://pic.myplas.com/myapp/img/female.jpg"}]
         * demand : [{"pur_id":"387","model":"9001","f_name":"台湾塑胶","store_house":"广州","unit_price":"10800.00","cargo_type":1,"input_time":"10月24日 16:36","bid":"0","reply":"0","thumb":"http://pic.myplas.com/myapp/img/female.jpg","user_id":53441},{"pur_id":"352","model":"9001","f_name":"台湾塑胶                ","store_house":"广州","unit_price":"10900.00","cargo_type":1,"input_time":"10月24日 11:43","bid":"0","reply":"0","thumb":"http://pic.myplas.com/myapp/img/female.jpg","user_id":53441},{"pur_id":"351","model":"9001","f_name":"台湾塑胶                ","store_house":"上海","unit_price":"11000.00","cargo_type":1,"input_time":"10月24日 11:43","bid":"0","reply":"0","thumb":"http://pic.myplas.com/myapp/img/female.jpg","user_id":53441},{"pur_id":"350","model":"BL3","f_name":"伊朗石化","store_house":"宁波","unit_price":"10500.00","cargo_type":1,"input_time":"10月24日 11:43","bid":"0","reply":"0","thumb":"http://pic.myplas.com/myapp/img/female.jpg","user_id":53441},{"pur_id":"338","model":"9001","f_name":"台湾塑胶                ","store_house":"广州","unit_price":"10900.00","cargo_type":1,"input_time":"10月24日 11:33","bid":"0","reply":"0","thumb":"http://pic.myplas.com/myapp/img/female.jpg","user_id":53441}]
         */

        private String user_id;
        private String name;
        private String c_id;
        private String mobile;
        private String sex;
        private String member_level;
        private String thumb;
        private String thumbqq;
        private String c_name;
        private String type;
        private String is_follow;
        private String ranking;
        private String is_vip;
        private String followers;
        private String fans;
        private String recommendation;
        private List<SuppliesBean> supplies;
        private List<DemandBean> demand;

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

        public String getC_name() {
            return c_name;
        }

        public void setC_name(String c_name) {
            this.c_name = c_name;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getIs_follow() {
            return is_follow;
        }

        public void setIs_follow(String is_follow) {
            this.is_follow = is_follow;
        }

        public String getRanking() {
            return ranking;
        }

        public void setRanking(String ranking) {
            this.ranking = ranking;
        }

        public String getIs_vip() {
            return is_vip;
        }

        public void setIs_vip(String is_vip) {
            this.is_vip = is_vip;
        }

        public String getFollowers() {
            return followers;
        }

        public void setFollowers(String followers) {
            this.followers = followers;
        }

        public String getFans() {
            return fans;
        }

        public void setFans(String fans) {
            this.fans = fans;
        }

        public String getRecommendation() {
            return recommendation;
        }

        public void setRecommendation(String recommendation) {
            this.recommendation = recommendation;
        }

        public List<SuppliesBean> getSupplies() {
            return supplies;
        }

        public void setSupplies(List<SuppliesBean> supplies) {
            this.supplies = supplies;
        }

        public List<DemandBean> getDemand() {
            return demand;
        }

        public void setDemand(List<DemandBean> demand) {
            this.demand = demand;
        }

        public static class SuppliesBean implements Serializable {
            /**
             * pur_id : 436
             * model : 9001
             * f_name : 台湾塑胶
             * store_house : 上海
             * unit_price : 11000.00
             * cargo_type : 0
             * input_time : 10月25日 14:11
             * bid : 1
             * reply : 1
             * user_id : 53441
             * thumb : http://pic.myplas.com/myapp/img/female.jpg
             */

            private String pur_id;
            private String model;
            private String f_name;
            private String store_house;
            private String unit_price;
            private String cargo_type;
            private String input_time;
            private String bid;
            private String reply;
            private String user_id;
            private String thumb;

            public String getPur_id() {
                return pur_id;
            }

            public void setPur_id(String pur_id) {
                this.pur_id = pur_id;
            }

            public String getModel() {
                return model;
            }

            public void setModel(String model) {
                this.model = model;
            }

            public String getF_name() {
                return f_name;
            }

            public void setF_name(String f_name) {
                this.f_name = f_name;
            }

            public String getStore_house() {
                return store_house;
            }

            public void setStore_house(String store_house) {
                this.store_house = store_house;
            }

            public String getUnit_price() {
                return unit_price;
            }

            public void setUnit_price(String unit_price) {
                this.unit_price = unit_price;
            }

            public String getCargo_type() {
                return cargo_type;
            }

            public void setCargo_type(String cargo_type) {
                this.cargo_type = cargo_type;
            }

            public String getInput_time() {
                return input_time;
            }

            public void setInput_time(String input_time) {
                this.input_time = input_time;
            }

            public String getBid() {
                return bid;
            }

            public void setBid(String bid) {
                this.bid = bid;
            }

            public String getReply() {
                return reply;
            }

            public void setReply(String reply) {
                this.reply = reply;
            }

            public String getUser_id() {
                return user_id;
            }

            public void setUser_id(String user_id) {
                this.user_id = user_id;
            }

            public String getThumb() {
                return thumb;
            }

            public void setThumb(String thumb) {
                this.thumb = thumb;
            }
        }

        public static class DemandBean implements Serializable {
            /**
             * pur_id : 387
             * model : 9001
             * f_name : 台湾塑胶
             * store_house : 广州
             * unit_price : 10800.00
             * cargo_type : 1
             * input_time : 10月24日 16:36
             * bid : 0
             * reply : 0
             * thumb : http://pic.myplas.com/myapp/img/female.jpg
             * user_id : 53441
             */

            private String pur_id;
            private String model;
            private String f_name;
            private String store_house;
            private String unit_price;
            private String cargo_type;
            private String input_time;
            private String bid;
            private String reply;
            private String thumb;
            private String user_id;

            public String getPur_id() {
                return pur_id;
            }

            public void setPur_id(String pur_id) {
                this.pur_id = pur_id;
            }

            public String getModel() {
                return model;
            }

            public void setModel(String model) {
                this.model = model;
            }

            public String getF_name() {
                return f_name;
            }

            public void setF_name(String f_name) {
                this.f_name = f_name;
            }

            public String getStore_house() {
                return store_house;
            }

            public void setStore_house(String store_house) {
                this.store_house = store_house;
            }

            public String getUnit_price() {
                return unit_price;
            }

            public void setUnit_price(String unit_price) {
                this.unit_price = unit_price;
            }

            public String getCargo_type() {
                return cargo_type;
            }

            public void setCargo_type(String cargo_type) {
                this.cargo_type = cargo_type;
            }

            public String getInput_time() {
                return input_time;
            }

            public void setInput_time(String input_time) {
                this.input_time = input_time;
            }

            public String getBid() {
                return bid;
            }

            public void setBid(String bid) {
                this.bid = bid;
            }

            public String getReply() {
                return reply;
            }

            public void setReply(String reply) {
                this.reply = reply;
            }

            public String getThumb() {
                return thumb;
            }

            public void setThumb(String thumb) {
                this.thumb = thumb;
            }

            public String getUser_id() {
                return user_id;
            }

            public void setUser_id(String user_id) {
                this.user_id = user_id;
            }
        }
    }
}
