package com.myplas.q.myinfo.beans;

import java.util.List;

/**
 * 编写： 黄双
 * 电话：15378412400
 * 邮箱：15378412400@163.com
 * 时间：2017/5/26 16:26
 */
public class RecordBean {


    /**
     * err : 0
     * data : [{"id":"187","status":"订单完成","create_time":"2017-05-31 10:26","order_id":"2017053199525610","goods_id":"12","receiver":"","phone":"","address":"2017-06-01","uid":"53402","update_time":"0","remark":"通讯录置顶卡","usepoints":"100","company":"","ship_sn":"","outpu_time":"1496197596","num":"1","pur_id":"53402","name":"hh","type":2,"contents":"","image":"http://pic.myplas.com/myapp/img/img_mail_list@2x.png","thumb":"http://pic.myplas.com/myapp/img/img_mail_list@2x.png","goods_name":"通讯录置顶卡"},{"id":"173","status":"订单完成","create_time":"2017-05-26 17:07","order_id":"2017052649995110","goods_id":"12","receiver":"","phone":"","address":"2017-06-16","uid":"53402","update_time":"0","remark":"通讯录置顶卡","usepoints":"100","company":"","ship_sn":"","outpu_time":"1495789665","num":"1","pur_id":"53402","name":"hh","type":2,"contents":"","image":"http://pic.myplas.com/myapp/img/img_mail_list@2x.png","thumb":"http://pic.myplas.com/myapp/img/img_mail_list@2x.png","goods_name":"通讯录置顶卡"},{"id":"172","status":"订单完成","create_time":"2017-05-26 16:26","order_id":"2017052656571015","goods_id":"11","receiver":"","phone":"","address":"2017-06-24,2017-06-25","uid":"53402","update_time":"0","remark":"供求消息置顶卡","usepoints":"200","company":"","ship_sn":"","outpu_time":"1495787192","num":"2","pur_id":"85750","type":1,"name":"","image":"http://pic.myplas.com/myapp/img/img_supply@2x.png","thumb":"http://pic.myplas.com/myapp/img/img_supply@2x.png","contents":"700f","b_and_s":"","deal_price":"","goods_name":"供求消息置顶卡"},{"id":"171","status":"订单完成","create_time":"2017-05-26 16:25","order_id":"2017052610149564","goods_id":"12","receiver":"","phone":"","address":"2017-05-30,2017-05-31","uid":"53402","update_time":"0","remark":"通讯录置顶卡","usepoints":"200","company":"","ship_sn":"","outpu_time":"1495787118","num":"2","pur_id":"53402","name":"hh","type":2,"contents":"","image":"http://pic.myplas.com/myapp/img/img_mail_list@2x.png","thumb":"http://pic.myplas.com/myapp/img/img_mail_list@2x.png","goods_name":"通讯录置顶卡"}]
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
         * id : 187
         * status : 订单完成
         * create_time : 2017-05-31 10:26
         * order_id : 2017053199525610
         * goods_id : 12
         * receiver :
         * phone :
         * address : 2017-06-01
         * uid : 53402
         * update_time : 0
         * remark : 通讯录置顶卡
         * usepoints : 100
         * company :
         * ship_sn :
         * outpu_time : 1496197596
         * num : 1
         * pur_id : 53402
         * name : hh
         * type : 2
         * contents :
         * image : http://pic.myplas.com/myapp/img/img_mail_list@2x.png
         * thumb : http://pic.myplas.com/myapp/img/img_mail_list@2x.png
         * goods_name : 通讯录置顶卡
         * b_and_s :
         * deal_price :
         */

        private String id;
        private String status;
        private String create_time;
        private String order_id;
        private String goods_id;
        private String receiver;
        private String phone;
        private String address;
        private String uid;
        private String update_time;
        private String remark;
        private String usepoints;
        private String company;
        private String ship_sn;
        private String outpu_time;
        private String num;
        private String pur_id;
        private String name;
        private int type;
        private String contents;
        private String image;
        private String thumb;
        private String goods_name;
        private String b_and_s;
        private String deal_price;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getCreate_time() {
            return create_time;
        }

        public void setCreate_time(String create_time) {
            this.create_time = create_time;
        }

        public String getOrder_id() {
            return order_id;
        }

        public void setOrder_id(String order_id) {
            this.order_id = order_id;
        }

        public String getGoods_id() {
            return goods_id;
        }

        public void setGoods_id(String goods_id) {
            this.goods_id = goods_id;
        }

        public String getReceiver() {
            return receiver;
        }

        public void setReceiver(String receiver) {
            this.receiver = receiver;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getUid() {
            return uid;
        }

        public void setUid(String uid) {
            this.uid = uid;
        }

        public String getUpdate_time() {
            return update_time;
        }

        public void setUpdate_time(String update_time) {
            this.update_time = update_time;
        }

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }

        public String getUsepoints() {
            return usepoints;
        }

        public void setUsepoints(String usepoints) {
            this.usepoints = usepoints;
        }

        public String getCompany() {
            return company;
        }

        public void setCompany(String company) {
            this.company = company;
        }

        public String getShip_sn() {
            return ship_sn;
        }

        public void setShip_sn(String ship_sn) {
            this.ship_sn = ship_sn;
        }

        public String getOutpu_time() {
            return outpu_time;
        }

        public void setOutpu_time(String outpu_time) {
            this.outpu_time = outpu_time;
        }

        public String getNum() {
            return num;
        }

        public void setNum(String num) {
            this.num = num;
        }

        public String getPur_id() {
            return pur_id;
        }

        public void setPur_id(String pur_id) {
            this.pur_id = pur_id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public String getContents() {
            return contents;
        }

        public void setContents(String contents) {
            this.contents = contents;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public String getThumb() {
            return thumb;
        }

        public void setThumb(String thumb) {
            this.thumb = thumb;
        }

        public String getGoods_name() {
            return goods_name;
        }

        public void setGoods_name(String goods_name) {
            this.goods_name = goods_name;
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
    }
}
