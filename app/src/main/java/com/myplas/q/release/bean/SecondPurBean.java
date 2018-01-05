package com.myplas.q.release.bean;

/**
 * 编写： 黄双
 * 电话：15378412400
 * 邮箱：15378412400@163.com
 * 时间：2017/4/6 14:34
 */
public class SecondPurBean {

    /**
     * err : 0
     * data : {"model":"1000","vendor":"伊朗石化","storehouse":"上海","price":"0.00","transaction_type":0,"type":"2"}
     */

    private DataBean data;


    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * model : 1000
         * vendor : 伊朗石化
         * storehouse : 上海
         * price : 0.00
         * transaction_type : 0
         * type : 2
         */

        private String model;
        private String vendor;
        private String storehouse;
        private String price;
        private String transaction_type;
        private String type;

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

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public String getTransaction_type() {
            return transaction_type;
        }

        public void setTransaction_type(String transaction_type) {
            this.transaction_type = transaction_type;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }
    }
}
