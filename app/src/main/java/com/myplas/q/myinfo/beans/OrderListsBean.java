package com.myplas.q.myinfo.beans;

import java.util.List;

/**
 * Created by Administrator on 2017/8/3 0003.
 */

public class OrderListsBean {

    /**
     * err : 0
     * data : {"list":[{"o_id":"83","order_sn":"SO2017080215492688","input_time":"2017-08-02 15:51:53","join_id":"0","invoice_status":"1","order_status":"2","c_name":"上海诚信木业有限公司","collection_status":"3","transport_status":"2","out_storage_status":"3","store_o_id":"64","total_num":"10.0000","count":2,"product":[{"price":"9000.00","model":"DF640","init":10,"f_name":"三井化学"},{"price":"9000.00","model":"DF640","init":10,"f_name":"三井化学"}],"sign":0,"billing_status":"","billing_list_status":0,"last_invoice_status":0,"transport":0,"total_price":0},{"o_id":"84","order_sn":"SO2017080215583908","input_time":"2017-08-02 16:00:51","join_id":"85","invoice_status":"1","order_status":"2","c_name":"上海诚信木业有限公司","collection_status":"1","transport_status":"2","out_storage_status":"1","store_o_id":"0","total_num":"10.0000","count":1,"product":[{"price":"8900.00","model":"2119","init":10,"f_name":"伊朗"}],"sign":0,"billing_status":"","billing_list_status":0,"last_invoice_status":0,"transport":0,"total_price":0}],"page":""}
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
         * list : [{"o_id":"83","order_sn":"SO2017080215492688","input_time":"2017-08-02 15:51:53","join_id":"0","invoice_status":"1","order_status":"2","c_name":"上海诚信木业有限公司","collection_status":"3","transport_status":"2","out_storage_status":"3","store_o_id":"64","total_num":"10.0000","count":2,"product":[{"price":"9000.00","model":"DF640","init":10,"f_name":"三井化学"},{"price":"9000.00","model":"DF640","init":10,"f_name":"三井化学"}],"sign":0,"billing_status":"","billing_list_status":0,"last_invoice_status":0,"transport":0,"total_price":0},{"o_id":"84","order_sn":"SO2017080215583908","input_time":"2017-08-02 16:00:51","join_id":"85","invoice_status":"1","order_status":"2","c_name":"上海诚信木业有限公司","collection_status":"1","transport_status":"2","out_storage_status":"1","store_o_id":"0","total_num":"10.0000","count":1,"product":[{"price":"8900.00","model":"2119","init":10,"f_name":"伊朗"}],"sign":0,"billing_status":"","billing_list_status":0,"last_invoice_status":0,"transport":0,"total_price":0}]
         * page :
         */

        private String page;
        private List<ListBean> list;

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

        public static class ListBean {
            /**
             * o_id : 83
             * order_sn : SO2017080215492688
             * input_time : 2017-08-02 15:51:53
             * join_id : 0
             * invoice_status : 1
             * order_status : 2
             * c_name : 上海诚信木业有限公司
             * collection_status : 3
             * transport_status : 2
             * out_storage_status : 3
             * store_o_id : 64
             * total_num : 10.0000
             * count : 2
             * product : [{"price":"9000.00","model":"DF640","init":10,"f_name":"三井化学"},{"price":"9000.00","model":"DF640","init":10,"f_name":"三井化学"}]
             * sign : 0
             * billing_status :
             * billing_list_status : 0
             * last_invoice_status : 0
             * transport : 0
             * total_price : 0
             */

            private String o_id;
            private String order_sn;
            private String input_time;
            private String join_id;
            private String invoice_status;
            private String order_status;
            private String c_name;
            private String collection_status;
            private String transport_status;
            private String out_storage_status;
            private String store_o_id;
            private String total_num;
            private String count;
            private String sign;
            private String billing_status;
            private String billing_list_status;
            private String last_invoice_status;
            private String transport;
            private String total_price;
            private List<ProductBean> product;

            public String getO_id() {
                return o_id;
            }

            public void setO_id(String o_id) {
                this.o_id = o_id;
            }

            public String getOrder_sn() {
                return order_sn;
            }

            public void setOrder_sn(String order_sn) {
                this.order_sn = order_sn;
            }

            public String getInput_time() {
                return input_time;
            }

            public void setInput_time(String input_time) {
                this.input_time = input_time;
            }

            public String getJoin_id() {
                return join_id;
            }

            public void setJoin_id(String join_id) {
                this.join_id = join_id;
            }

            public String getInvoice_status() {
                return invoice_status;
            }

            public void setInvoice_status(String invoice_status) {
                this.invoice_status = invoice_status;
            }

            public String getOrder_status() {
                return order_status;
            }

            public void setOrder_status(String order_status) {
                this.order_status = order_status;
            }

            public String getC_name() {
                return c_name;
            }

            public void setC_name(String c_name) {
                this.c_name = c_name;
            }

            public String getCollection_status() {
                return collection_status;
            }

            public void setCollection_status(String collection_status) {
                this.collection_status = collection_status;
            }

            public String getTransport_status() {
                return transport_status;
            }

            public void setTransport_status(String transport_status) {
                this.transport_status = transport_status;
            }

            public String getOut_storage_status() {
                return out_storage_status;
            }

            public void setOut_storage_status(String out_storage_status) {
                this.out_storage_status = out_storage_status;
            }

            public String getStore_o_id() {
                return store_o_id;
            }

            public void setStore_o_id(String store_o_id) {
                this.store_o_id = store_o_id;
            }

            public String getTotal_num() {
                return total_num;
            }

            public void setTotal_num(String total_num) {
                this.total_num = total_num;
            }

            public String getCount() {
                return count;
            }

            public void setCount(String count) {
                this.count = count;
            }

            public String getSign() {
                return sign;
            }

            public void setSign(String sign) {
                this.sign = sign;
            }

            public String getBilling_status() {
                return billing_status;
            }

            public void setBilling_status(String billing_status) {
                this.billing_status = billing_status;
            }

            public String getBilling_list_status() {
                return billing_list_status;
            }

            public void setBilling_list_status(String billing_list_status) {
                this.billing_list_status = billing_list_status;
            }

            public String getLast_invoice_status() {
                return last_invoice_status;
            }

            public void setLast_invoice_status(String last_invoice_status) {
                this.last_invoice_status = last_invoice_status;
            }

            public String getTransport() {
                return transport;
            }

            public void setTransport(String transport) {
                this.transport = transport;
            }

            public String getTotal_price() {
                return total_price;
            }

            public void setTotal_price(String total_price) {
                this.total_price = total_price;
            }

            public List<ProductBean> getProduct() {
                return product;
            }

            public void setProduct(List<ProductBean> product) {
                this.product = product;
            }

            public static class ProductBean {
                /**
                 * price : 9000.00
                 * model : DF640
                 * init : 10
                 * f_name : 三井化学
                 */

                private String price;
                private String model;
                private String init;
                private String f_name;

                public String getPrice() {
                    return price;
                }

                public void setPrice(String price) {
                    this.price = price;
                }

                public String getModel() {
                    return model;
                }

                public void setModel(String model) {
                    this.model = model;
                }

                public String getInit() {
                    return init;
                }

                public void setInit(String init) {
                    this.init = init;
                }

                public String getF_name() {
                    return f_name;
                }

                public void setF_name(String f_name) {
                    this.f_name = f_name;
                }
            }
        }
    }
}
