package com.myplas.q.myself.beans;

import java.util.List;

/**
 * 编写： 黄双
 * 电话：15378412400
 * 邮箱：15378412400@163.com
 * 时间：2017/3/29 09:40
 */
public class InvoiceDetailBean {


    /**
     * err : 0
     * data : [{"invoice_sn":"33333333","input_time":"2017-08-09","billing_price":"240.00","invoice_status":"2","order_sn":"SO2017080911188350"},{"invoice_sn":"eeeeeeeeeee","input_time":"2017-08-09","billing_price":"20.00","invoice_status":"2","order_sn":"SO2017080911188350"},{"invoice_sn":"p987765","input_time":"2017-08-09","billing_price":"10.00","invoice_status":"2","order_sn":"SO2017080911188350"}]
     */

    private List<DataBean> data;


    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * invoice_sn : 33333333
         * input_time : 2017-08-09
         * billing_price : 240.00
         * invoice_status : 2
         * order_sn : SO2017080911188350
         */

        private String invoice_sn;
        private String input_time;
        private String billing_price;
        private String invoice_status;
        private String order_sn;

        public String getInvoice_sn() {
            return invoice_sn;
        }

        public void setInvoice_sn(String invoice_sn) {
            this.invoice_sn = invoice_sn;
        }

        public String getInput_time() {
            return input_time;
        }

        public void setInput_time(String input_time) {
            this.input_time = input_time;
        }

        public String getBilling_price() {
            return billing_price;
        }

        public void setBilling_price(String billing_price) {
            this.billing_price = billing_price;
        }

        public String getInvoice_status() {
            return invoice_status;
        }

        public void setInvoice_status(String invoice_status) {
            this.invoice_status = invoice_status;
        }

        public String getOrder_sn() {
            return order_sn;
        }

        public void setOrder_sn(String order_sn) {
            this.order_sn = order_sn;
        }
    }
}
