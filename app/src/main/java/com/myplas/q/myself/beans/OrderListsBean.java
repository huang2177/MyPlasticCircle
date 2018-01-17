package com.myplas.q.myself.beans;

import java.util.List;

/**
 * Created by Administrator on 2017/8/3 0003.
 */

public class OrderListsBean {


    /**
     * code : 0
     * data : [{"o_id":73600,"order_sn":"SO2018011516042236","order_type":1,"input_time":"2018-01-15 16:05:03","join_id":0,"invoice_status":1,"order_status":2,"c_name":"上海中晨电子商务股份有限公司","collection_status":3,"transport_status":2,"out_storage_status":3,"in_storage_status":1,"store_o_id":73467,"total_num":"5.0000","total_price":"50,000.00","sign":1,"sign_status":1,"product":[{"unit_price":"10000.00","model":"18B01","init":5,"f_name":"伊朗"}],"billing_status":1,"billing_list_status":0,"last_invoice_status":1,"last_collection_status":1,"confirm_receipt_char":"已签收","confirm_receipt":1,"apply_billing_char":"申请开票","apply_billing":0,"billing_detail_list_char":"","billing_detail_list":0},{"o_id":73600,"order_sn":"SO2017120818308250","order_type":1,"input_time":"2017-12-08 18:31:10","join_id":0,"invoice_status":1,"order_status":2,"c_name":"上海中晨电子商务股份有限公司","collection_status":1,"transport_status":2,"out_storage_status":3,"in_storage_status":1,"store_o_id":73349,"total_num":"1.0000","total_price":"111,111.00","sign":0,"sign_status":1,"product":[{"unit_price":"10000.00","model":"18B01","init":5,"f_name":"伊朗"}],"billing_status":1,"billing_list_status":0,"last_invoice_status":1,"last_collection_status":1,"confirm_receipt_char":"订单未全部发货，暂无法签收","confirm_receipt":2,"apply_billing_info":"订单未签收或未付款，暂无法开票","apply_billing_char":"申请开票","apply_billing":2,"billing_detail_list_char":"","billing_detail_list":0},{"o_id":73600,"order_sn":"SO2017120818031365","order_type":1,"input_time":"2017-12-08 18:04:41","join_id":0,"invoice_status":1,"order_status":2,"c_name":"上海中晨电子商务股份有限公司","collection_status":3,"transport_status":2,"out_storage_status":3,"in_storage_status":1,"store_o_id":73349,"total_num":"1.0000","total_price":"1,000.00","sign":0,"sign_status":1,"product":[{"unit_price":"10000.00","model":"18B01","init":5,"f_name":"伊朗"}],"billing_status":1,"billing_list_status":0,"last_invoice_status":1,"last_collection_status":1,"confirm_receipt_char":"订单未全部发货，暂无法签收","confirm_receipt":2,"apply_billing_info":"订单未全部付款，暂无法开票","apply_billing_char":"申请开票","apply_billing":2,"billing_detail_list_char":"","billing_detail_list":0},{"o_id":73600,"order_sn":"SO2017120818017024","order_type":1,"input_time":"2017-12-08 18:01:57","join_id":0,"invoice_status":1,"order_status":2,"c_name":"上海中晨电子商务股份有限公司","collection_status":3,"transport_status":2,"out_storage_status":3,"in_storage_status":1,"store_o_id":73349,"total_num":"1.0000","total_price":"1.00","sign":0,"sign_status":1,"product":[{"unit_price":"10000.00","model":"18B01","init":5,"f_name":"伊朗"}],"billing_status":1,"billing_list_status":0,"last_invoice_status":1,"last_collection_status":1,"confirm_receipt_char":"订单未全部发货，暂无法签收","confirm_receipt":2,"apply_billing_info":"订单未全部付款，暂无法开票","apply_billing_char":"申请开票","apply_billing":2,"billing_detail_list_char":"","billing_detail_list":0},{"o_id":73600,"order_sn":"SO2017120818007873","order_type":1,"input_time":"2017-12-08 18:01:27","join_id":0,"invoice_status":1,"order_status":2,"c_name":"上海中晨电子商务股份有限公司","collection_status":3,"transport_status":2,"out_storage_status":3,"in_storage_status":1,"store_o_id":73349,"total_num":"1.0000","total_price":"1,111.00","sign":0,"sign_status":1,"product":[{"unit_price":"10000.00","model":"18B01","init":5,"f_name":"伊朗"}],"billing_status":1,"billing_list_status":0,"last_invoice_status":1,"last_collection_status":1,"confirm_receipt_char":"订单未全部发货，暂无法签收","confirm_receipt":2,"apply_billing_info":"订单未全部付款，暂无法开票","apply_billing_char":"申请开票","apply_billing":2,"billing_detail_list_char":"","billing_detail_list":0}]
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

    public static class DataBean {
        /**
         * o_id : 73600
         * order_sn : SO2018011516042236
         * order_type : 1
         * input_time : 2018-01-15 16:05:03
         * join_id : 0
         * invoice_status : 1
         * order_status : 2
         * c_name : 上海中晨电子商务股份有限公司
         * collection_status : 3
         * transport_status : 2
         * out_storage_status : 3
         * in_storage_status : 1
         * store_o_id : 73467
         * total_num : 5.0000
         * total_price : 50,000.00
         * sign : 1
         * sign_status : 1
         * product : [{"unit_price":"10000.00","model":"18B01","init":5,"f_name":"伊朗"}]
         * billing_status : 1
         * billing_list_status : 0
         * last_invoice_status : 1
         * last_collection_status : 1
         * confirm_receipt_char : 已签收
         * confirm_receipt : 1
         * apply_billing_char : 申请开票
         * apply_billing : 0
         * billing_detail_list_char : 
         * billing_detail_list : 0
         * apply_billing_info : 订单未签收或未付款，暂无法开票
         */

        private String o_id;
        private String order_sn;
        private String order_type;
        private String input_time;
        private String join_id;
        private String invoice_status;
        private String order_status;
        private String c_name;
        private String collection_status;
        private String transport_status;
        private String out_storage_status;
        private String in_storage_status;
        private String store_o_id;
        private String total_num;
        private String total_price;
        private String sign;
        private String sign_status;
        private String billing_status;
        private String billing_list_status;
        private String last_invoice_status;
        private String last_collection_status;
        private String confirm_receipt_char;
        private String confirm_receipt;
        private String apply_billing_char;
        private String apply_billing;
        private String billing_detail_list_char;
        private String billing_detail_list;
        private String apply_billing_info;
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

        public String getOrder_type() {
            return order_type;
        }

        public void setOrder_type(String order_type) {
            this.order_type = order_type;
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

        public String getIn_storage_status() {
            return in_storage_status;
        }

        public void setIn_storage_status(String in_storage_status) {
            this.in_storage_status = in_storage_status;
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

        public String getTotal_price() {
            return total_price;
        }

        public void setTotal_price(String total_price) {
            this.total_price = total_price;
        }

        public String getSign() {
            return sign;
        }

        public void setSign(String sign) {
            this.sign = sign;
        }

        public String getSign_status() {
            return sign_status;
        }

        public void setSign_status(String sign_status) {
            this.sign_status = sign_status;
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

        public String getLast_collection_status() {
            return last_collection_status;
        }

        public void setLast_collection_status(String last_collection_status) {
            this.last_collection_status = last_collection_status;
        }

        public String getConfirm_receipt_char() {
            return confirm_receipt_char;
        }

        public void setConfirm_receipt_char(String confirm_receipt_char) {
            this.confirm_receipt_char = confirm_receipt_char;
        }

        public String getConfirm_receipt() {
            return confirm_receipt;
        }

        public void setConfirm_receipt(String confirm_receipt) {
            this.confirm_receipt = confirm_receipt;
        }

        public String getApply_billing_char() {
            return apply_billing_char;
        }

        public void setApply_billing_char(String apply_billing_char) {
            this.apply_billing_char = apply_billing_char;
        }

        public String getApply_billing() {
            return apply_billing;
        }

        public void setApply_billing(String apply_billing) {
            this.apply_billing = apply_billing;
        }

        public String getBilling_detail_list_char() {
            return billing_detail_list_char;
        }

        public void setBilling_detail_list_char(String billing_detail_list_char) {
            this.billing_detail_list_char = billing_detail_list_char;
        }

        public String getBilling_detail_list() {
            return billing_detail_list;
        }

        public void setBilling_detail_list(String billing_detail_list) {
            this.billing_detail_list = billing_detail_list;
        }

        public String getApply_billing_info() {
            return apply_billing_info;
        }

        public void setApply_billing_info(String apply_billing_info) {
            this.apply_billing_info = apply_billing_info;
        }

        public List<ProductBean> getProduct() {
            return product;
        }

        public void setProduct(List<ProductBean> product) {
            this.product = product;
        }

        public static class ProductBean {
            /**
             * unit_price : 10000.00
             * model : 18B01
             * init : 5
             * f_name : 伊朗
             */

            private String unit_price;
            private String model;
            private String init;
            private String f_name;

            public String getUnit_price() {
                return unit_price;
            }

            public void setUnit_price(String unit_price) {
                this.unit_price = unit_price;
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
