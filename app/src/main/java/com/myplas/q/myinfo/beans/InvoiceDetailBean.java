package com.myplas.q.myinfo.beans;

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
     * data : [{"invoice_sn":"567956790","order_sn":"SO2017080415282419","total_price":"150.00","input_time":"2017-08-04","payment_time":"1501832670","billing_price":"50.00","unbilling_price":"100.00"},{"invoice_sn":"90876887654","order_sn":"SO2017080415282419","total_price":"150.00","input_time":"2017-08-04","payment_time":"1501833535","billing_price":"50.00","unbilling_price":"50.00"},{"invoice_sn":"9875432145","order_sn":"SO2017080415282419","total_price":"150.00","input_time":"2017-08-04","payment_time":"1501833731","billing_price":"50.00","unbilling_price":"0.00"}]
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
         * invoice_sn : 567956790
         * order_sn : SO2017080415282419
         * total_price : 150.00
         * input_time : 2017-08-04
         * payment_time : 1501832670
         * billing_price : 50.00
         * unbilling_price : 100.00
         */

        private String invoice_sn;
        private String order_sn;
        private String total_price;
        private String input_time;
        private String payment_time;
        private String billing_price;
        private String unbilling_price;

        public String getInvoice_sn() {
            return invoice_sn;
        }

        public void setInvoice_sn(String invoice_sn) {
            this.invoice_sn = invoice_sn;
        }

        public String getOrder_sn() {
            return order_sn;
        }

        public void setOrder_sn(String order_sn) {
            this.order_sn = order_sn;
        }

        public String getTotal_price() {
            return total_price;
        }

        public void setTotal_price(String total_price) {
            this.total_price = total_price;
        }

        public String getInput_time() {
            return input_time;
        }

        public void setInput_time(String input_time) {
            this.input_time = input_time;
        }

        public String getPayment_time() {
            return payment_time;
        }

        public void setPayment_time(String payment_time) {
            this.payment_time = payment_time;
        }

        public String getBilling_price() {
            return billing_price;
        }

        public void setBilling_price(String billing_price) {
            this.billing_price = billing_price;
        }

        public String getUnbilling_price() {
            return unbilling_price;
        }

        public void setUnbilling_price(String unbilling_price) {
            this.unbilling_price = unbilling_price;
        }
    }
}
