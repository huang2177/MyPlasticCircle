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
     * data : {"user_id":"56656","name":"哈妹","c_id":"4016","is_pass":"0","mobile":"18817391111","sex":"女","thumb":"//pic.myplas.com/upload/17/11/23/5a1686a0151be.PNG","thumbqq":"//pic.myplas.com/upload/17/11/23/5a1686a0151be.PNG","c_name":"嘉兴鼎辉信息科技有限公司","address":"安徽安庆迎江区|哈哈","main_product":"PP，PE，PVC","type":"2","com_intro":"公司于2015年由原上海中晨塑料有限公司改制建立，专注于塑料化工原料领域的B2B电子商务交易，为各类塑料化工原料领域的上下游企业提供全方位的交易及相关增值服务。","is_follow":1,"ranking":18479,"member_level":"","is_vip":0,"followers":9,"fans":5,"recommendation":0,"supplies":[{"pur_id":"196138","model":"7000F","f_name":"伊朗","store_house":"上海","unit_price":"10000.00","cargo_type":0,"input_time":"11月15日 17:14","bid":"5","reply":"0","user_id":56656,"thumb":"//pic.myplas.com/upload/17/11/23/5a1686a0151be.PNG"},{"pur_id":"195140","model":"2119","f_name":"上海","store_house":"上海","unit_price":"20000.00","cargo_type":0,"input_time":"10月27日 11:17","bid":"0","reply":"0","user_id":56656,"thumb":"//pic.myplas.com/upload/17/11/23/5a1686a0151be.PNG"},{"pur_id":"186769","model":"","f_name":"","store_house":"","unit_price":"0.00","cargo_type":0,"input_time":"09月24日 22:09","bid":"0","reply":"0","user_id":56656,"thumb":"//pic.myplas.com/upload/17/11/23/5a1686a0151be.PNG"},{"pur_id":"186766","model":"","f_name":"","store_house":"","unit_price":"0.00","cargo_type":0,"input_time":"09月24日 20:47","bid":"0","reply":"0","user_id":56656,"thumb":"//pic.myplas.com/upload/17/11/23/5a1686a0151be.PNG"},{"pur_id":"186765","model":"","f_name":"","store_house":"","unit_price":"0.00","cargo_type":0,"input_time":"09月24日 20:46","bid":"0","reply":"0","user_id":56656,"thumb":"//pic.myplas.com/upload/17/11/23/5a1686a0151be.PNG"}],"demand":[{"pur_id":"196394","model":"2119","f_name":"上海","store_house":"上海","unit_price":"95000.00","cargo_type":0,"input_time":"11月27日 13:33","bid":"0","reply":"0","thumb":"//pic.myplas.com/upload/17/11/23/5a1686a0151be.PNG","user_id":56656},{"pur_id":"196393","model":"2119","f_name":"上海","store_house":"上海","unit_price":"95000.00","cargo_type":0,"input_time":"11月27日 13:32","bid":"0","reply":"0","thumb":"//pic.myplas.com/upload/17/11/23/5a1686a0151be.PNG","user_id":56656},{"pur_id":"196392","model":"2119","f_name":"上海","store_house":"上海","unit_price":"95000.00","cargo_type":0,"input_time":"11月27日 13:31","bid":"0","reply":"0","thumb":"//pic.myplas.com/upload/17/11/23/5a1686a0151be.PNG","user_id":56656},{"pur_id":"196386","model":"2119","f_name":"上海","store_house":"上海","unit_price":"9500.00","cargo_type":0,"input_time":"11月24日 17:42","bid":"0","reply":"0","thumb":"//pic.myplas.com/upload/17/11/23/5a1686a0151be.PNG","user_id":56656},{"pur_id":"196376","model":"2119","f_name":"青岛","store_house":"上海","unit_price":"9500.00","cargo_type":0,"input_time":"11月24日 17:04","bid":"0","reply":"0","thumb":"//pic.myplas.com/upload/17/11/23/5a1686a0151be.PNG","user_id":56656}],"heat":0}
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
         * user_id : 56656
         * name : 哈妹
         * c_id : 4016
         * is_pass : 0
         * mobile : 18817391111
         * sex : 女
         * thumb : //pic.myplas.com/upload/17/11/23/5a1686a0151be.PNG
         * thumbqq : //pic.myplas.com/upload/17/11/23/5a1686a0151be.PNG
         * c_name : 嘉兴鼎辉信息科技有限公司
         * address : 安徽安庆迎江区|哈哈
         * main_product : PP，PE，PVC
         * type : 2
         * com_intro : 公司于2015年由原上海中晨塑料有限公司改制建立，专注于塑料化工原料领域的B2B电子商务交易，为各类塑料化工原料领域的上下游企业提供全方位的交易及相关增值服务。
         * is_follow : 1
         * ranking : 18479
         * member_level :
         * is_vip : 0
         * followers : 9
         * fans : 5
         * recommendation : 0
         * supplies : [{"pur_id":"196138","model":"7000F","f_name":"伊朗","store_house":"上海","unit_price":"10000.00","cargo_type":0,"input_time":"11月15日 17:14","bid":"5","reply":"0","user_id":56656,"thumb":"//pic.myplas.com/upload/17/11/23/5a1686a0151be.PNG"},{"pur_id":"195140","model":"2119","f_name":"上海","store_house":"上海","unit_price":"20000.00","cargo_type":0,"input_time":"10月27日 11:17","bid":"0","reply":"0","user_id":56656,"thumb":"//pic.myplas.com/upload/17/11/23/5a1686a0151be.PNG"},{"pur_id":"186769","model":"","f_name":"","store_house":"","unit_price":"0.00","cargo_type":0,"input_time":"09月24日 22:09","bid":"0","reply":"0","user_id":56656,"thumb":"//pic.myplas.com/upload/17/11/23/5a1686a0151be.PNG"},{"pur_id":"186766","model":"","f_name":"","store_house":"","unit_price":"0.00","cargo_type":0,"input_time":"09月24日 20:47","bid":"0","reply":"0","user_id":56656,"thumb":"//pic.myplas.com/upload/17/11/23/5a1686a0151be.PNG"},{"pur_id":"186765","model":"","f_name":"","store_house":"","unit_price":"0.00","cargo_type":0,"input_time":"09月24日 20:46","bid":"0","reply":"0","user_id":56656,"thumb":"//pic.myplas.com/upload/17/11/23/5a1686a0151be.PNG"}]
         * demand : [{"pur_id":"196394","model":"2119","f_name":"上海","store_house":"上海","unit_price":"95000.00","cargo_type":0,"input_time":"11月27日 13:33","bid":"0","reply":"0","thumb":"//pic.myplas.com/upload/17/11/23/5a1686a0151be.PNG","user_id":56656},{"pur_id":"196393","model":"2119","f_name":"上海","store_house":"上海","unit_price":"95000.00","cargo_type":0,"input_time":"11月27日 13:32","bid":"0","reply":"0","thumb":"//pic.myplas.com/upload/17/11/23/5a1686a0151be.PNG","user_id":56656},{"pur_id":"196392","model":"2119","f_name":"上海","store_house":"上海","unit_price":"95000.00","cargo_type":0,"input_time":"11月27日 13:31","bid":"0","reply":"0","thumb":"//pic.myplas.com/upload/17/11/23/5a1686a0151be.PNG","user_id":56656},{"pur_id":"196386","model":"2119","f_name":"上海","store_house":"上海","unit_price":"9500.00","cargo_type":0,"input_time":"11月24日 17:42","bid":"0","reply":"0","thumb":"//pic.myplas.com/upload/17/11/23/5a1686a0151be.PNG","user_id":56656},{"pur_id":"196376","model":"2119","f_name":"青岛","store_house":"上海","unit_price":"9500.00","cargo_type":0,"input_time":"11月24日 17:04","bid":"0","reply":"0","thumb":"//pic.myplas.com/upload/17/11/23/5a1686a0151be.PNG","user_id":56656}]
         * heat : 0
         */

        private String user_id;
        private String name;
        private String c_id;
        private String is_pass;
        private String mobile;
        private String sex;
        private String thumb;
        private String thumbqq;
        private String c_name;
        private String address;
        private String main_product;
        private String type;
        private String com_intro;
        private String is_follow;
        private String ranking;
        private String member_level;
        private String is_vip;
        private String followers;
        private String fans;
        private String recommendation;
        private String heat;
        private String merge_three;
        private List<SuppliesBean> supplies;
        private List<DemandBean> demand;

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

        public String getC_name() {
            return c_name;
        }

        public void setC_name(String c_name) {
            this.c_name = c_name;
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

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getCom_intro() {
            return com_intro;
        }

        public void setCom_intro(String com_intro) {
            this.com_intro = com_intro;
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

        public String getMember_level() {
            return member_level;
        }

        public void setMember_level(String member_level) {
            this.member_level = member_level;
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

        public String getHeat() {
            return heat;
        }

        public void setHeat(String heat) {
            this.heat = heat;
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

        public static class SuppliesBean {
            /**
             * pur_id : 196138
             * model : 7000F
             * f_name : 伊朗
             * store_house : 上海
             * unit_price : 10000.00
             * cargo_type : 0
             * input_time : 11月15日 17:14
             * bid : 5
             * reply : 0
             * user_id : 56656
             * thumb : //pic.myplas.com/upload/17/11/23/5a1686a0151be.PNG
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

        public static class DemandBean {
            /**
             * pur_id : 196394
             * model : 2119
             * f_name : 上海
             * store_house : 上海
             * unit_price : 95000.00
             * cargo_type : 0
             * input_time : 11月27日 13:33
             * bid : 0
             * reply : 0
             * thumb : //pic.myplas.com/upload/17/11/23/5a1686a0151be.PNG
             * user_id : 56656
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
