package com.myplas.q.myinfo.beans;

import java.util.List;

/**
 * 作者：  黄双
 * 事件 2017/8/31 0031.
 * 邮箱： 15378412400@163.com
 */

public class MsgChJBean {


    /**
     * err : 0
     * count : 10
     * data : [{"id":"214","user_id":"88","user_name":"","c_name":"","user_mobile":"","fa_content":"","fa_time":"7月05日 13:37","price":"1533.00","type":"2","model":"","chu_time":"8月31日 15:51","store_house":"宝山","detail_url":"/plasticzone/plastic#/releasedetail?id=214&userid=88&tab=1"},{"id":"187","user_id":"3858","user_name":"","c_name":"","user_mobile":"","fa_content":"pvc","fa_time":"4月06日 14:59","price":"9968.00","type":"2","model":"2119","chu_time":"6月16日 14:12","store_house":"123","detail_url":"/plasticzone/plastic#/releasedetail?id=187&userid=3858&tab=1"},{"id":"184","user_id":"40211","user_name":"","c_name":"","user_mobile":"","fa_content":"超低价出：PP过渡料8150常州提，要的来谈","fa_time":"4月06日 12:01","price":"999.00","type":"2","model":"","chu_time":"6月13日 09:54","store_house":"","detail_url":"/plasticzone/plastic#/releasedetail?id=184&userid=40211&tab=1"},{"id":"177","user_id":"42955","user_name":"","c_name":"","user_mobile":"","fa_content":"出神华2426H、S1003，中天合创35B                 欢迎来电咨询157 1496 5678","fa_time":"4月06日 12:48","price":"23.00","type":"2","model":"","chu_time":"6月09日 16:44","store_house":"","detail_url":"/plasticzone/plastic#/releasedetail?id=177&userid=42955&tab=1"},{"id":"173","user_id":"36860","user_name":"","c_name":"","user_mobile":"","fa_content":"","fa_time":"4月06日 12:07","price":"7000.00","type":"1","model":"22401","chu_time":"6月08日 17:54","store_house":"上海","detail_url":"/plasticzone/plastic#/releasedetail?id=173&userid=36860&tab=1"},{"id":"166","user_id":"36860","user_name":"","c_name":"","user_mobile":"","fa_content":"","fa_time":"4月06日 12:07","price":"10000.00","type":"2","model":"2023","chu_time":"6月08日 09:21","store_house":"上海","detail_url":"/plasticzone/plastic#/releasedetail?id=166&userid=36860&tab=1"},{"id":"165","user_id":"36860","user_name":"","c_name":"","user_mobile":"","fa_content":"","fa_time":"4月06日 12:07","price":"12333.00","type":"2","model":"2023","chu_time":"6月08日 09:15","store_house":"上海","detail_url":"/plasticzone/plastic#/releasedetail?id=165&userid=36860&tab=1"},{"id":"162","user_id":"36860","user_name":"","c_name":"","user_mobile":"","fa_content":"","fa_time":"4月06日 12:07","price":"3986.00","type":"2","model":"2023","chu_time":"6月07日 17:24","store_house":"上海","detail_url":"/plasticzone/plastic#/releasedetail?id=162&userid=36860&tab=1"},{"id":"110","user_id":"29508","user_name":"罗伟强","c_name":"","user_mobile":"13905848877","fa_content":"实盘求：  齐鲁  TN26   Q281   一车     自提   配送余姚\r\n实盘求：  齐鲁  TN26   Q281   一车     自提   配送余姚","fa_time":"4月06日 09:18","price":"8963.00","type":"1","model":"","chu_time":"5月31日 17:24","store_house":"","detail_url":"/plasticzone/plastic#/releasedetail?id=110&userid=29508&tab=1"},{"id":"109","user_id":"29508","user_name":"罗伟强","c_name":"","user_mobile":"13905848877","fa_content":"实盘求：  齐鲁  TN26   Q281   一车     自提   配送余姚\r\n实盘求：  齐鲁  TN26   Q281   一车     自提   配送余姚","fa_time":"4月06日 09:18","price":"8963.00","type":"1","model":"","chu_time":"5月31日 17:24","store_house":"","detail_url":"/plasticzone/plastic#/releasedetail?id=109&userid=29508&tab=1"}]
     */

    private int err;
    private String count;
    private List<DataBean> data;

    public int getErr() {
        return err;
    }

    public void setErr(int err) {
        this.err = err;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * id : 214
         * user_id : 88
         * user_name :
         * c_name :
         * user_mobile :
         * fa_content :
         * fa_time : 7月05日 13:37
         * price : 1533.00
         * type : 2
         * model :
         * chu_time : 8月31日 15:51
         * store_house : 宝山
         * detail_url : /plasticzone/plastic#/releasedetail?id=214&userid=88&tab=1
         */

        private String id;
        private String user_id;
        private String user_name;
        private String c_name;
        private String user_mobile;
        private String fa_content;
        private String fa_time;
        private String price;
        private String type;
        private String model;
        private String chu_time;
        private String store_house;
        private String detail_url;

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

        public String getUser_name() {
            return user_name;
        }

        public void setUser_name(String user_name) {
            this.user_name = user_name;
        }

        public String getC_name() {
            return c_name;
        }

        public void setC_name(String c_name) {
            this.c_name = c_name;
        }

        public String getUser_mobile() {
            return user_mobile;
        }

        public void setUser_mobile(String user_mobile) {
            this.user_mobile = user_mobile;
        }

        public String getFa_content() {
            return fa_content;
        }

        public void setFa_content(String fa_content) {
            this.fa_content = fa_content;
        }

        public String getFa_time() {
            return fa_time;
        }

        public void setFa_time(String fa_time) {
            this.fa_time = fa_time;
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

        public String getModel() {
            return model;
        }

        public void setModel(String model) {
            this.model = model;
        }

        public String getChu_time() {
            return chu_time;
        }

        public void setChu_time(String chu_time) {
            this.chu_time = chu_time;
        }

        public String getStore_house() {
            return store_house;
        }

        public void setStore_house(String store_house) {
            this.store_house = store_house;
        }

        public String getDetail_url() {
            return detail_url;
        }

        public void setDetail_url(String detail_url) {
            this.detail_url = detail_url;
        }
    }
}
