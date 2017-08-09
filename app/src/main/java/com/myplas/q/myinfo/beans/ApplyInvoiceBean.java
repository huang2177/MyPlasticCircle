package com.myplas.q.myinfo.beans;

import java.util.List;

/**
 * 编写： 黄双
 * 电话：15378412400
 * 邮箱：15378412400@163.com
 * 时间：2017/3/29 09:40
 */
public class ApplyInvoiceBean {


    /**
     * err : 0
     * data : {"detail":{"total_price":"6.00","rise":"上海梓辰实业有限公司","order_sn":"SO2017080913461634","unbilling_price":"0.9980","billing_price":"0.9980"},"list":[{"id":"236","f_name":"泰国","model":"7420D","number":"2.0000","unit_price":"2.00","price":"0.9980","total_num":"3.0000","b_number":"0.4990","last_num":1.501}],"page":""}
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
         * detail : {"total_price":"6.00","rise":"上海梓辰实业有限公司","order_sn":"SO2017080913461634","unbilling_price":"0.9980","billing_price":"0.9980"}
         * list : [{"id":"236","f_name":"泰国","model":"7420D","number":"2.0000","unit_price":"2.00","price":"0.9980","total_num":"3.0000","b_number":"0.4990","last_num":1.501}]
         * page :
         */

        private DetailBean detail;
        private String page;
        private List<ListBean> list;

        public DetailBean getDetail() {
            return detail;
        }

        public void setDetail(DetailBean detail) {
            this.detail = detail;
        }

        public String getPage() {
            return page;
        }

        public void setPage(String page) {
            this.page = page;
        }

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class DetailBean {
            /**
             * total_price : 6.00
             * rise : 上海梓辰实业有限公司
             * order_sn : SO2017080913461634
             * unbilling_price : 0.9980
             * billing_price : 0.9980
             */

            private String total_price;
            private String rise;
            private String order_sn;
            private String unbilling_price;
            private String billing_price;

            public String getTotal_price() {
                return total_price;
            }

            public void setTotal_price(String total_price) {
                this.total_price = total_price;
            }

            public String getRise() {
                return rise;
            }

            public void setRise(String rise) {
                this.rise = rise;
            }

            public String getOrder_sn() {
                return order_sn;
            }

            public void setOrder_sn(String order_sn) {
                this.order_sn = order_sn;
            }

            public String getUnbilling_price() {
                return unbilling_price;
            }

            public void setUnbilling_price(String unbilling_price) {
                this.unbilling_price = unbilling_price;
            }

            public String getBilling_price() {
                return billing_price;
            }

            public void setBilling_price(String billing_price) {
                this.billing_price = billing_price;
            }
        }

        public static class ListBean {
            /**
             * id : 236
             * f_name : 泰国
             * model : 7420D
             * number : 2.0000
             * unit_price : 2.00
             * price : 0.9980
             * total_num : 3.0000
             * b_number : 0.4990
             * last_num : 1.501
             */

            private String id;
            private String f_name;
            private String model;
            private double number;
            private double unit_price;
            private double price;
            private double total_num;
            private double b_number;
            private double last_num;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getF_name() {
                return f_name;
            }

            public void setF_name(String f_name) {
                this.f_name = f_name;
            }

            public String getModel() {
                return model;
            }

            public void setModel(String model) {
                this.model = model;
            }

            public double getNumber() {
                return number;
            }

            public void setNumber(double number) {
                this.number = number;
            }

            public double getUnit_price() {
                return unit_price;
            }

            public void setUnit_price(double unit_price) {
                this.unit_price = unit_price;
            }

            public double getPrice() {
                return price;
            }

            public void setPrice(double price) {
                this.price = price;
            }

            public double getTotal_num() {
                return total_num;
            }

            public void setTotal_num(double total_num) {
                this.total_num = total_num;
            }

            public double getB_number() {
                return b_number;
            }

            public void setB_number(double b_number) {
                this.b_number = b_number;
            }

            public double getLast_num() {
                return last_num;
            }

            public void setLast_num(double last_num) {
                this.last_num = last_num;
            }
        }
    }
}
