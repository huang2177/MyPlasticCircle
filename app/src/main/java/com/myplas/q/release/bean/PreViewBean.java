package com.myplas.q.release.bean;

import java.io.Serializable;
import java.util.List;

/**
 * 作者:huangshuang
 * 事件 2017/10/18 0018.
 * 邮箱： 15378412400@163.com
 */

public class PreViewBean implements Serializable {


    /**
     * code : 0
     * data : [{"model":"7000F","vendor":"伊朗","storehouse":"上海","transaction_type":0,"price":10000,"type":"2","company":"打得过","username":"黄双"}]
     */

    private String code;
    private List<DataBean> data;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean implements Serializable {
        /**
         * model : 7000F
         * vendor : 伊朗
         * storehouse : 上海
         * transaction_type : 0
         * price : 10000
         * type : 2
         * company : 打得过
         * username : 黄双
         */

        private String model;
        private String vendor;
        private String storehouse;
        private String transaction_type;
        private String price;
        private String type;
        private String company;
        private String username;

        public String getModel() {
            return model;
        }

        public void setModel(String model) {
            this.model = model;
        }

        public String getVendor() {
            return vendor;
        }

        public void setVendor(String vendor) {
            this.vendor = vendor;
        }

        public String getStorehouse() {
            return storehouse;
        }

        public void setStorehouse(String storehouse) {
            this.storehouse = storehouse;
        }

        public String getTransaction_type() {
            return transaction_type;
        }

        public void setTransaction_type(String transaction_type) {
            this.transaction_type = transaction_type;
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

        public String getCompany() {
            return company;
        }

        public void setCompany(String company) {
            this.company = company;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }
    }
}
