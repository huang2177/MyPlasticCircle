package com.myplas.q.homepage.beans;

import java.io.Serializable;
import java.util.List;

/**
 * 作者:huangshuang
 * 事件 2017/10/17 0017.
 * 邮箱： 15378412400@163.com
 */

public class ContactInfoBean implements Serializable {


    /**
     * code : 0
     * data : {"user_id":56656,"name":"哈妹","c_id":607560,"mobile":"18817391111","sex":1,"thumb":"http://newstatics.myplas.com/upload/17/12/25/5a40791c62c8a.PNG","thumbcard":"//newstatics.myplas.com/upload/17/12/25/5a40cc452ff83.PNG","c_name":"上海鼎辉信息技术","need_product":"7000","address":"福建福州仓山区|创南路189号","main_product":"7000,2119","month_consum":"55","type":2,"isshop":0,"com_intro":"","member_level":"列兵","china_area":"华东","followers":12,"fans":8,"recommendation":0,"supplies":[{"id":197313,"p_id":8098,"user_id":56656,"model":"2119","unit_price":"95600.00","store_house":"上海","cargo_type":1,"f_name":"上海","type":2,"input_time":"2017-12-28","comments":0,"offers":0,"name":"哈妹","c_name":"上海鼎辉信息技术","img":"http://myplas.ufile.ucloud.com.cn//myapp/img/icon_supply.png"},{"id":197097,"p_id":9481,"user_id":56656,"model":"7000f","unit_price":"9550.00","store_house":"山海","cargo_type":1,"f_name":"神华","type":2,"input_time":"2017-12-19","comments":0,"offers":0,"name":"哈妹","c_name":"上海鼎辉信息技术","img":"http://myplas.ufile.ucloud.com.cn//myapp/img/icon_supply.png"},{"id":197096,"p_id":9481,"user_id":56656,"model":"7000f","unit_price":"9550.00","store_house":"山海","cargo_type":1,"f_name":"神华","type":2,"input_time":"2017-12-19","comments":0,"offers":0,"name":"哈妹","c_name":"上海鼎辉信息技术","img":"http://myplas.ufile.ucloud.com.cn//myapp/img/icon_supply.png"},{"id":197091,"p_id":3934,"user_id":56656,"model":"5000A","unit_price":"0.00","store_house":"宁波","cargo_type":1,"f_name":"巴西","type":2,"input_time":"2017-12-19","comments":0,"offers":0,"name":"哈妹","c_name":"上海鼎辉信息技术","img":"http://myplas.ufile.ucloud.com.cn//myapp/img/icon_supply.png"},{"id":197061,"p_id":4336,"user_id":56656,"model":"FT7236","unit_price":"9850.00","store_house":"上海","cargo_type":2,"f_name":"博禄","type":2,"input_time":"2017-12-18","comments":0,"offers":0,"name":"哈妹","c_name":"上海鼎辉信息技术","img":"http://myplas.ufile.ucloud.com.cn//myapp/img/icon_supply.png"}],"demand":[{"id":197612,"p_id":9481,"user_id":56656,"model":"7000F","unit_price":"9500.00","store_house":"上海","cargo_type":1,"f_name":"神华","type":1,"input_time":"1月16日 17:15","comments":7,"offers":3,"name":"哈妹","c_name":"上海鼎辉信息技术","img":"http://myplas.ufile.ucloud.com.cn//myapp/img/icon_purchase.png"},{"id":197611,"p_id":9481,"user_id":56656,"model":"7000F","unit_price":"9500.00","store_house":"上海","cargo_type":1,"f_name":"神华","type":1,"input_time":"1月16日 17:15","comments":0,"offers":0,"name":"哈妹","c_name":"上海鼎辉信息技术","img":"http://myplas.ufile.ucloud.com.cn//myapp/img/icon_purchase.png"},{"id":197610,"p_id":9481,"user_id":56656,"model":"7000F","unit_price":"9500.00","store_house":"上海","cargo_type":1,"f_name":"神华","type":1,"input_time":"1月16日 16:45","comments":0,"offers":0,"name":"哈妹","c_name":"上海鼎辉信息技术","img":"http://myplas.ufile.ucloud.com.cn//myapp/img/icon_purchase.png"},{"id":197609,"p_id":9481,"user_id":56656,"model":"7000F","unit_price":"9500.00","store_house":"上海","cargo_type":1,"f_name":"神华","type":1,"input_time":"1月16日 16:39","comments":0,"offers":1,"name":"哈妹","c_name":"上海鼎辉信息技术","img":"http://myplas.ufile.ucloud.com.cn//myapp/img/icon_purchase.png"},{"id":197608,"p_id":9481,"user_id":56656,"model":"7000F","unit_price":"9500.00","store_house":"上海","cargo_type":1,"f_name":"神华","type":1,"input_time":"1月16日 16:38","comments":0,"offers":0,"name":"哈妹","c_name":"上海鼎辉信息技术","img":"http://myplas.ufile.ucloud.com.cn//myapp/img/icon_purchase.png"}],"heat_score":"1426","is_follow":1}
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
         * user_id : 56656
         * name : 哈妹
         * c_id : 607560
         * mobile : 18817391111
         * sex : 1
         * thumb : http://newstatics.myplas.com/upload/17/12/25/5a40791c62c8a.PNG
         * thumbcard : //newstatics.myplas.com/upload/17/12/25/5a40cc452ff83.PNG
         * c_name : 上海鼎辉信息技术
         * need_product : 7000
         * address : 福建福州仓山区|创南路189号
         * main_product : 7000,2119
         * month_consum : 55
         * type : 2
         * isshop : 0
         * com_intro : 
         * member_level : 列兵
         * china_area : 华东
         * followers : 12
         * fans : 8
         * recommendation : 0
         * supplies : [{"id":197313,"p_id":8098,"user_id":56656,"model":"2119","unit_price":"95600.00","store_house":"上海","cargo_type":1,"f_name":"上海","type":2,"input_time":"2017-12-28","comments":0,"offers":0,"name":"哈妹","c_name":"上海鼎辉信息技术","img":"http://myplas.ufile.ucloud.com.cn//myapp/img/icon_supply.png"},{"id":197097,"p_id":9481,"user_id":56656,"model":"7000f","unit_price":"9550.00","store_house":"山海","cargo_type":1,"f_name":"神华","type":2,"input_time":"2017-12-19","comments":0,"offers":0,"name":"哈妹","c_name":"上海鼎辉信息技术","img":"http://myplas.ufile.ucloud.com.cn//myapp/img/icon_supply.png"},{"id":197096,"p_id":9481,"user_id":56656,"model":"7000f","unit_price":"9550.00","store_house":"山海","cargo_type":1,"f_name":"神华","type":2,"input_time":"2017-12-19","comments":0,"offers":0,"name":"哈妹","c_name":"上海鼎辉信息技术","img":"http://myplas.ufile.ucloud.com.cn//myapp/img/icon_supply.png"},{"id":197091,"p_id":3934,"user_id":56656,"model":"5000A","unit_price":"0.00","store_house":"宁波","cargo_type":1,"f_name":"巴西","type":2,"input_time":"2017-12-19","comments":0,"offers":0,"name":"哈妹","c_name":"上海鼎辉信息技术","img":"http://myplas.ufile.ucloud.com.cn//myapp/img/icon_supply.png"},{"id":197061,"p_id":4336,"user_id":56656,"model":"FT7236","unit_price":"9850.00","store_house":"上海","cargo_type":2,"f_name":"博禄","type":2,"input_time":"2017-12-18","comments":0,"offers":0,"name":"哈妹","c_name":"上海鼎辉信息技术","img":"http://myplas.ufile.ucloud.com.cn//myapp/img/icon_supply.png"}]
         * demand : [{"id":197612,"p_id":9481,"user_id":56656,"model":"7000F","unit_price":"9500.00","store_house":"上海","cargo_type":1,"f_name":"神华","type":1,"input_time":"1月16日 17:15","comments":7,"offers":3,"name":"哈妹","c_name":"上海鼎辉信息技术","img":"http://myplas.ufile.ucloud.com.cn//myapp/img/icon_purchase.png"},{"id":197611,"p_id":9481,"user_id":56656,"model":"7000F","unit_price":"9500.00","store_house":"上海","cargo_type":1,"f_name":"神华","type":1,"input_time":"1月16日 17:15","comments":0,"offers":0,"name":"哈妹","c_name":"上海鼎辉信息技术","img":"http://myplas.ufile.ucloud.com.cn//myapp/img/icon_purchase.png"},{"id":197610,"p_id":9481,"user_id":56656,"model":"7000F","unit_price":"9500.00","store_house":"上海","cargo_type":1,"f_name":"神华","type":1,"input_time":"1月16日 16:45","comments":0,"offers":0,"name":"哈妹","c_name":"上海鼎辉信息技术","img":"http://myplas.ufile.ucloud.com.cn//myapp/img/icon_purchase.png"},{"id":197609,"p_id":9481,"user_id":56656,"model":"7000F","unit_price":"9500.00","store_house":"上海","cargo_type":1,"f_name":"神华","type":1,"input_time":"1月16日 16:39","comments":0,"offers":1,"name":"哈妹","c_name":"上海鼎辉信息技术","img":"http://myplas.ufile.ucloud.com.cn//myapp/img/icon_purchase.png"},{"id":197608,"p_id":9481,"user_id":56656,"model":"7000F","unit_price":"9500.00","store_house":"上海","cargo_type":1,"f_name":"神华","type":1,"input_time":"1月16日 16:38","comments":0,"offers":0,"name":"哈妹","c_name":"上海鼎辉信息技术","img":"http://myplas.ufile.ucloud.com.cn//myapp/img/icon_purchase.png"}]
         * heat_score : 1426
         * is_follow : 1
         */

        private String user_id;
        private String name;
        private String c_id;
        private String mobile;
        private String sex;
        private String thumb;
        private String thumbcard;
        private String c_name;
        private String need_product;
        private String address;
        private String main_product;
        private String month_consum;
        private String type;
        private String isshop;
        private String com_intro;
        private String member_level;
        private String china_area;
        private String followers;
        private String fans;
        private String recommendation;
        private String heat_score;
        private String is_follow;
        private List<SuppliesBean> supplies;
        private List<DemandBean> demand;
        private String shop_audit_status;

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

        public String getThumb() {
            return thumb;
        }

        public void setThumb(String thumb) {
            this.thumb = thumb;
        }

        public String getThumbcard() {
            return thumbcard;
        }

        public void setThumbcard(String thumbcard) {
            this.thumbcard = thumbcard;
        }

        public String getC_name() {
            return c_name;
        }

        public void setC_name(String c_name) {
            this.c_name = c_name;
        }

        public String getNeed_product() {
            return need_product;
        }

        public void setNeed_product(String need_product) {
            this.need_product = need_product;
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

        public String getMonth_consum() {
            return month_consum;
        }

        public void setMonth_consum(String month_consum) {
            this.month_consum = month_consum;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getIsshop() {
            return isshop;
        }

        public void setIsshop(String isshop) {
            this.isshop = isshop;
        }

        public String getCom_intro() {
            return com_intro;
        }

        public void setCom_intro(String com_intro) {
            this.com_intro = com_intro;
        }

        public String getMember_level() {
            return member_level;
        }

        public void setMember_level(String member_level) {
            this.member_level = member_level;
        }

        public String getChina_area() {
            return china_area;
        }

        public void setChina_area(String china_area) {
            this.china_area = china_area;
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

        public String getHeat_score() {
            return heat_score;
        }

        public void setHeat_score(String heat_score) {
            this.heat_score = heat_score;
        }

        public String getIs_follow() {
            return is_follow;
        }

        public void setIs_follow(String is_follow) {
            this.is_follow = is_follow;
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

        public String getShop_audit_status() {
            return shop_audit_status;
        }

        public void setShop_audit_status(String shop_audit_status) {
            this.shop_audit_status = shop_audit_status;
        }

        public static class SuppliesBean {
            /**
             * id : 197313
             * p_id : 8098
             * user_id : 56656
             * model : 2119
             * unit_price : 95600.00
             * store_house : 上海
             * cargo_type : 1
             * f_name : 上海
             * type : 2
             * input_time : 2017-12-28
             * comments : 0
             * offers : 0
             * name : 哈妹
             * c_name : 上海鼎辉信息技术
             * img : http://myplas.ufile.ucloud.com.cn//myapp/img/icon_supply.png
             */

            private String id;
            private String p_id;
            private String user_id;
            private String model;
            private String unit_price;
            private String store_house;
            private String cargo_type;
            private String f_name;
            private String type;
            private String input_time;
            private String comments;
            private String offers;
            private String name;
            private String c_name;
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

            public String getComments() {
                return comments;
            }

            public void setComments(String comments) {
                this.comments = comments;
            }

            public String getOffers() {
                return offers;
            }

            public void setOffers(String offers) {
                this.offers = offers;
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

            public String getImg() {
                return img;
            }

            public void setImg(String img) {
                this.img = img;
            }
        }

        public static class DemandBean {
            /**
             * id : 197612
             * p_id : 9481
             * user_id : 56656
             * model : 7000F
             * unit_price : 9500.00
             * store_house : 上海
             * cargo_type : 1
             * f_name : 神华
             * type : 1
             * input_time : 1月16日 17:15
             * comments : 7
             * offers : 3
             * name : 哈妹
             * c_name : 上海鼎辉信息技术
             * img : http://myplas.ufile.ucloud.com.cn//myapp/img/icon_purchase.png
             */

            private String id;
            private String p_id;
            private String user_id;
            private String model;
            private String unit_price;
            private String store_house;
            private String cargo_type;
            private String f_name;
            private String type;
            private String input_time;
            private String comments;
            private String offers;
            private String name;
            private String c_name;
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

            public String getComments() {
                return comments;
            }

            public void setComments(String comments) {
                this.comments = comments;
            }

            public String getOffers() {
                return offers;
            }

            public void setOffers(String offers) {
                this.offers = offers;
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

            public String getImg() {
                return img;
            }

            public void setImg(String img) {
                this.img = img;
            }
        }
    }
}
