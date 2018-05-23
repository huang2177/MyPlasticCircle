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
     * data : {"user_id":81009,"name":"李煜","c_id":607723,"mobile":"18817392629","sex":0,"thumb":"http://myplas.ufile.ucloud.com.cn/upload/18/01/31/3668WZGZZE.jpg","thumbcard":"","c_name":"wwwqq","need_product":"2119","address":"","main_product":"2119，5000s","month_consum":"0.00","type":2,"isshop":1,"com_intro":"wwwww","member_level":"列兵","china_area":"华东","business_licence_pic":"http://myplas.ufile.ucloud.com.cn/upload/18/01/31/PSJPABZ526.jpg","followers":0,"fans":1,"recommendation":0,"supplies":[{"id":197742,"p_id":8098,"user_id":81009,"model":"2119","unit_price":"10200.00","store_house":"上海","cargo_type":2,"f_name":"上海","type":2,"input_time":"14:17","comments":0,"offers":0,"name":"李煜","c_name":"wwwqq","img":"http://myplas.ufile.ucloud.com.cn//myapp/img/icon_supply.png"},{"id":197738,"p_id":10780,"user_id":81009,"model":"5000S","unit_price":"10300.00","store_house":"青岛","cargo_type":1,"f_name":"大庆石化","type":2,"input_time":"09:46","comments":9,"offers":2,"name":"李煜","c_name":"wwwqq","img":"http://myplas.ufile.ucloud.com.cn//myapp/img/icon_supply.png"},{"id":197735,"p_id":10780,"user_id":81009,"model":"5000S","unit_price":"10500.00","store_house":"上海","cargo_type":2,"f_name":"大庆石化","type":2,"input_time":"09:44","comments":0,"offers":0,"name":"李煜","c_name":"wwwqq","img":"http://myplas.ufile.ucloud.com.cn//myapp/img/icon_supply.png"}],"demand":[{"id":197741,"p_id":8098,"user_id":81009,"model":"2119","unit_price":"10500.00","store_house":"临沂","cargo_type":1,"f_name":"上海","type":1,"input_time":"14:15","comments":0,"offers":0,"name":"李煜","c_name":"wwwqq","img":"http://myplas.ufile.ucloud.com.cn//myapp/img/icon_purchase.png"},{"id":197737,"p_id":10780,"user_id":81009,"model":"5000S","unit_price":"10300.00","store_house":"青岛","cargo_type":2,"f_name":"大庆石化","type":1,"input_time":"09:46","comments":0,"offers":0,"name":"李煜","c_name":"wwwqq","img":"http://myplas.ufile.ucloud.com.cn//myapp/img/icon_purchase.png"},{"id":197736,"p_id":10780,"user_id":81009,"model":"5000S","unit_price":"10300.00","store_house":"青岛","cargo_type":1,"f_name":"大庆石化","type":1,"input_time":"09:45","comments":0,"offers":0,"name":"李煜","c_name":"wwwqq","img":"http://myplas.ufile.ucloud.com.cn//myapp/img/icon_purchase.png"},{"id":197734,"p_id":2737,"user_id":81009,"model":"7000f","unit_price":"10200.00","store_house":"上海","cargo_type":1,"f_name":"伊朗","type":1,"input_time":"09:42","comments":0,"offers":0,"name":"李煜","c_name":"wwwqq","img":"http://myplas.ufile.ucloud.com.cn//myapp/img/icon_purchase.png"}],"heat_score":"353","is_follow":0,"shop_audit_status":0}
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
         * user_id : 81009
         * name : 李煜
         * c_id : 607723
         * mobile : 18817392629
         * sex : 0
         * thumb : http://myplas.ufile.ucloud.com.cn/upload/18/01/31/3668WZGZZE.jpg
         * thumbcard :
         * c_name : wwwqq
         * need_product : 2119
         * address :
         * main_product : 2119，5000s
         * month_consum : 0.00
         * type : 2
         * isshop : 1
         * com_intro : wwwww
         * member_level : 列兵
         * china_area : 华东
         * business_licence_pic : http://myplas.ufile.ucloud.com.cn/upload/18/01/31/PSJPABZ526.jpg
         * followers : 0
         * fans : 1
         * recommendation : 0
         * supplies : [{"id":197742,"p_id":8098,"user_id":81009,"model":"2119","unit_price":"10200.00","store_house":"上海","cargo_type":2,"f_name":"上海","type":2,"input_time":"14:17","comments":0,"offers":0,"name":"李煜","c_name":"wwwqq","img":"http://myplas.ufile.ucloud.com.cn//myapp/img/icon_supply.png"},{"id":197738,"p_id":10780,"user_id":81009,"model":"5000S","unit_price":"10300.00","store_house":"青岛","cargo_type":1,"f_name":"大庆石化","type":2,"input_time":"09:46","comments":9,"offers":2,"name":"李煜","c_name":"wwwqq","img":"http://myplas.ufile.ucloud.com.cn//myapp/img/icon_supply.png"},{"id":197735,"p_id":10780,"user_id":81009,"model":"5000S","unit_price":"10500.00","store_house":"上海","cargo_type":2,"f_name":"大庆石化","type":2,"input_time":"09:44","comments":0,"offers":0,"name":"李煜","c_name":"wwwqq","img":"http://myplas.ufile.ucloud.com.cn//myapp/img/icon_supply.png"}]
         * demand : [{"id":197741,"p_id":8098,"user_id":81009,"model":"2119","unit_price":"10500.00","store_house":"临沂","cargo_type":1,"f_name":"上海","type":1,"input_time":"14:15","comments":0,"offers":0,"name":"李煜","c_name":"wwwqq","img":"http://myplas.ufile.ucloud.com.cn//myapp/img/icon_purchase.png"},{"id":197737,"p_id":10780,"user_id":81009,"model":"5000S","unit_price":"10300.00","store_house":"青岛","cargo_type":2,"f_name":"大庆石化","type":1,"input_time":"09:46","comments":0,"offers":0,"name":"李煜","c_name":"wwwqq","img":"http://myplas.ufile.ucloud.com.cn//myapp/img/icon_purchase.png"},{"id":197736,"p_id":10780,"user_id":81009,"model":"5000S","unit_price":"10300.00","store_house":"青岛","cargo_type":1,"f_name":"大庆石化","type":1,"input_time":"09:45","comments":0,"offers":0,"name":"李煜","c_name":"wwwqq","img":"http://myplas.ufile.ucloud.com.cn//myapp/img/icon_purchase.png"},{"id":197734,"p_id":2737,"user_id":81009,"model":"7000f","unit_price":"10200.00","store_house":"上海","cargo_type":1,"f_name":"伊朗","type":1,"input_time":"09:42","comments":0,"offers":0,"name":"李煜","c_name":"wwwqq","img":"http://myplas.ufile.ucloud.com.cn//myapp/img/icon_purchase.png"}]
         * heat_score : 353
         * is_follow : 0
         * shop_audit_status : 0
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
        private String business_licence_pic;
        private String followers;
        private String fans;
        private String recommendation;
        private String heat_score;
        private String is_follow;
        private String shop_audit_status;
        private List<SuppliesBean> supplies;
        private List<DemandBean> demand;
        private String business_licence;

        public void setBusiness_licence(String business_licence) {
            this.business_licence = business_licence;
        }

        public String getBusiness_licence() {
            return business_licence;
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

        public String getBusiness_licence_pic() {
            return business_licence_pic;
        }

        public void setBusiness_licence_pic(String business_licence_pic) {
            this.business_licence_pic = business_licence_pic;
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

        public String getShop_audit_status() {
            return shop_audit_status;
        }

        public void setShop_audit_status(String shop_audit_status) {
            this.shop_audit_status = shop_audit_status;
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
             * id : 197742
             * p_id : 8098
             * user_id : 81009
             * model : 2119
             * unit_price : 10200.00
             * store_house : 上海
             * cargo_type : 2
             * f_name : 上海
             * type : 2
             * input_time : 14:17
             * comments : 0
             * offers : 0
             * name : 李煜
             * c_name : wwwqq
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
             * id : 197741
             * p_id : 8098
             * user_id : 81009
             * model : 2119
             * unit_price : 10500.00
             * store_house : 临沂
             * cargo_type : 1
             * f_name : 上海
             * type : 1
             * input_time : 14:15
             * comments : 0
             * offers : 0
             * name : 李煜
             * c_name : wwwqq
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
