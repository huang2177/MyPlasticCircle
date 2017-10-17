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
     * data : [{"id":"110885","name":"hh","sync":"6","model":"","type":"1","from":"","c_name":"上海中晨电子商务股份有限公司","user_id":"53402","f_name":"","input_time":"09-04 18:26","cargo_type":"1","unit_price":"0.00","store_house":"","img":"http://pic.myplas.com/myapp/img/icon_purchase.png"},{"id":"110855","name":"hh","sync":"6","model":"","type":"1","from":"","c_name":"上海中晨电子商务股份有限公司","user_id":"53402","f_name":"","input_time":"08-29 14:15","cargo_type":"1","unit_price":"0.00","store_house":"","img":"http://pic.myplas.com/myapp/img/icon_purchase.png"},{"id":"114111","name":"李一帆","sync":"6","model":"7000F","type":"2","from":"","c_name":"上海中晨电子商务股份有限公司","user_id":"3858","f_name":"日本三井化学","input_time":"09-29 14:34","cargo_type":"1","unit_price":"1300.00","store_house":"杭州","img":"http://pic.myplas.com/myapp/img/icon_supply.png"},{"id":"114110","name":"李一帆","sync":"6","model":"7000F","type":"2","from":"","c_name":"上海中晨电子商务股份有限公司","user_id":"3858","f_name":"日本三井化学","input_time":"09-29 14:22","cargo_type":"1","unit_price":"1300.00","store_house":"上海","img":"http://pic.myplas.com/myapp/img/icon_supply.png"},{"id":"114109","name":"李一帆","sync":"6","model":"7000F","type":"2","from":"","c_name":"上海中晨电子商务股份有限公司","user_id":"3858","f_name":"日本三井化学","input_time":"09-29 14:21","cargo_type":"1","unit_price":"1300.00","store_house":"苏州","img":"http://pic.myplas.com/myapp/img/icon_supply.png"},{"id":"114108","name":"李一帆","sync":"6","model":"7000F","type":"2","from":"","c_name":"上海中晨电子商务股份有限公司","user_id":"3858","f_name":"日本三井化学","input_time":"09-29 14:19","cargo_type":"1","unit_price":"1300.00","store_house":"杭州","img":"http://pic.myplas.com/myapp/img/icon_supply.png"},{"id":"114107","name":"李一帆","sync":"6","model":"7000F","type":"2","from":"","c_name":"上海中晨电子商务股份有限公司","user_id":"3858","f_name":"日本三井化学","input_time":"09-29 14:19","cargo_type":"1","unit_price":"1300.00","store_house":"上海","img":"http://pic.myplas.com/myapp/img/icon_supply.png"},{"id":"114106","name":"李一帆","sync":"6","model":"7000F","type":"2","from":"","c_name":"上海中晨电子商务股份有限公司","user_id":"3858","f_name":"日本三井化学","input_time":"09-29 14:34","cargo_type":"1","unit_price":"1300.00","store_house":"杭州","img":"http://pic.myplas.com/myapp/img/icon_supply.png"},{"id":"114105","name":"李一帆","sync":"6","model":"7000F","type":"2","from":"","c_name":"上海中晨电子商务股份有限公司","user_id":"3858","f_name":"日本三井化学","input_time":"09-29 14:22","cargo_type":"2","unit_price":"1300.00","store_house":"杭州","img":"http://pic.myplas.com/myapp/img/icon_supply.png"},{"id":"114104","name":"李一帆","sync":"6","model":"7000F","type":"2","from":"","c_name":"上海中晨电子商务股份有限公司","user_id":"3858","f_name":"日本三井化学","input_time":"09-29 14:21","cargo_type":"2","unit_price":"1300.00","store_house":"上海","img":"http://pic.myplas.com/myapp/img/icon_supply.png"},{"id":"114103","name":"李一帆","sync":"6","model":"7000F","type":"2","from":"","c_name":"上海中晨电子商务股份有限公司","user_id":"3858","f_name":"日本三井化学","input_time":"09-29 14:19","cargo_type":"2","unit_price":"1300.00","store_house":"苏州","img":"http://pic.myplas.com/myapp/img/icon_supply.png"},{"id":"114102","name":"李一帆","sync":"6","model":"7000F","type":"2","from":"","c_name":"上海中晨电子商务股份有限公司","user_id":"3858","f_name":"日本三井化学","input_time":"09-29 14:19","cargo_type":"2","unit_price":"1300.00","store_house":"杭州","img":"http://pic.myplas.com/myapp/img/icon_supply.png"},{"id":"114101","name":"李一帆","sync":"6","model":"7000F","type":"2","from":"","c_name":"上海中晨电子商务股份有限公司","user_id":"3858","f_name":"日本三井化学","input_time":"09-29 14:19","cargo_type":"2","unit_price":"1300.00","store_house":"上海","img":"http://pic.myplas.com/myapp/img/icon_supply.png"},{"id":"114100","name":"李一帆","sync":"6","model":"7000F","type":"2","from":"","c_name":"上海中晨电子商务股份有限公司","user_id":"3858","f_name":"日本三井化学","input_time":"09-29 14:34","cargo_type":"1","unit_price":"1200.00","store_house":"杭州","img":"http://pic.myplas.com/myapp/img/icon_supply.png"},{"id":"114099","name":"李一帆","sync":"6","model":"7000F","type":"1","from":"","c_name":"上海中晨电子商务股份有限公司","user_id":"3858","f_name":"日本三井化学","input_time":"09-29 14:22","cargo_type":"1","unit_price":"1200.00","store_house":"上海","img":"http://pic.myplas.com/myapp/img/icon_purchase.png"}]
     * top : {"id":"110731","p_id":"0","user_id":"53405","model":null,"unit_price":"0.00","store_house":"","cargo_type":"1","f_name":null,"type":"1","content":"7000f","input_time":"07-24 17:02","contents":"7000f","b_and_s":"","deal_price":"","saysCount":0,"deliverPriceCount":0,"name":"小胖","c_id":"53436","mobile":"15921211767","member_level":"列兵","sex":"男","is_pass":"0","thumb":"http://pic.myplas.com/upload/17/09/05/59ae0cd00dd3e.PNG","thumbqq":"http://pic.myplas.com/upload/17/09/05/59ae0cd00dd3e.PNG","c_name":"上海中信有限公司","need_product":"2119","fans":"8","buy_count":"8","sale_count":0,"status":"已关注","thumbcard":"http://pic.myplas.com/upload/17/06/16/59437d4cccf0f.jpg","address":"上海市浦东新区金桥路1108 号金茂大厦11楼","main_product":"3024","month_consum":"89"}
     * hot_search : 5000S
     * show_msg :
     */

    private String err;
    private TopBean top;
    private String hot_search;
    private String show_msg;
    private List<DataBean> data;

    public String getErr() {
        return err;
    }

    public void setErr(String err) {
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
         * id : 110731
         * p_id : 0
         * user_id : 53405
         * model : null
         * unit_price : 0.00
         * store_house :
         * cargo_type : 1
         * f_name : null
         * type : 1
         * content : 7000f
         * input_time : 07-24 17:02
         * contents : 7000f
         * b_and_s :
         * deal_price :
         * saysCount : 0
         * deliverPriceCount : 0
         * name : 小胖
         * c_id : 53436
         * mobile : 15921211767
         * member_level : 列兵
         * sex : 男
         * is_pass : 0
         * thumb : http://pic.myplas.com/upload/17/09/05/59ae0cd00dd3e.PNG
         * thumbqq : http://pic.myplas.com/upload/17/09/05/59ae0cd00dd3e.PNG
         * c_name : 上海中信有限公司
         * need_product : 2119
         * fans : 8
         * buy_count : 8
         * sale_count : 0
         * status : 已关注
         * thumbcard : http://pic.myplas.com/upload/17/06/16/59437d4cccf0f.jpg
         * address : 上海市浦东新区金桥路1108 号金茂大厦11楼
         * main_product : 3024
         * month_consum : 89
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
        private String content;
        private String input_time;
        private String contents;
        private String b_and_s;
        private String deal_price;
        private String saysCount;
        private String deliverPriceCount;
        private String name;
        private String c_id;
        private String mobile;
        private String member_level;
        private String sex;
        private String is_pass;
        private String thumb;
        private String thumbqq;
        private String c_name;
        private String need_product;
        private String fans;
        private String buy_count;
        private String sale_count;
        private String status;
        private String thumbcard;
        private String address;
        private String main_product;
        private String month_consum;

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

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getInput_time() {
            return input_time;
        }

        public void setInput_time(String input_time) {
            this.input_time = input_time;
        }

        public String getContents() {
            return contents;
        }

        public void setContents(String contents) {
            this.contents = contents;
        }

        public String getB_and_s() {
            return b_and_s;
        }

        public void setB_and_s(String b_and_s) {
            this.b_and_s = b_and_s;
        }

        public String getDeal_price() {
            return deal_price;
        }

        public void setDeal_price(String deal_price) {
            this.deal_price = deal_price;
        }

        public String getSaysCount() {
            return saysCount;
        }

        public void setSaysCount(String saysCount) {
            this.saysCount = saysCount;
        }

        public String getDeliverPriceCount() {
            return deliverPriceCount;
        }

        public void setDeliverPriceCount(String deliverPriceCount) {
            this.deliverPriceCount = deliverPriceCount;
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

        public String getMember_level() {
            return member_level;
        }

        public void setMember_level(String member_level) {
            this.member_level = member_level;
        }

        public String getSex() {
            return sex;
        }

        public void setSex(String sex) {
            this.sex = sex;
        }

        public String getIs_pass() {
            return is_pass;
        }

        public void setIs_pass(String is_pass) {
            this.is_pass = is_pass;
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

        public String getNeed_product() {
            return need_product;
        }

        public void setNeed_product(String need_product) {
            this.need_product = need_product;
        }

        public String getFans() {
            return fans;
        }

        public void setFans(String fans) {
            this.fans = fans;
        }

        public String getBuy_count() {
            return buy_count;
        }

        public void setBuy_count(String buy_count) {
            this.buy_count = buy_count;
        }

        public String getSale_count() {
            return sale_count;
        }

        public void setSale_count(String sale_count) {
            this.sale_count = sale_count;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getThumbcard() {
            return thumbcard;
        }

        public void setThumbcard(String thumbcard) {
            this.thumbcard = thumbcard;
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
    }

    public static class DataBean {
        /**
         * id : 110885
         * name : hh
         * sync : 6
         * model :
         * type : 1
         * from :
         * c_name : 上海中晨电子商务股份有限公司
         * user_id : 53402
         * f_name :
         * input_time : 09-04 18:26
         * cargo_type : 1
         * unit_price : 0.00
         * store_house :
         * img : http://pic.myplas.com/myapp/img/icon_purchase.png
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
    }
}
