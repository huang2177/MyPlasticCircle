package com.myplas.q.supdem.beans;

import java.util.List;

/**
 * 编写：黄双
 * 邮箱：15378412400@163.com
 * 时间： 2017/6/291724.
 */

public class SearchResultDetailBean {


    /**
     * err : 0
     * data : {"id":153727,"store_house":"天津","model":"BL3","vendor":"","unit_price":0,"c_name":"中化塑料有限公司","cargo_type":"现货","qq_nick":"老富","thumb_qq":"","qq":1057533814,"type":1,"show_information":[],"find_relevant":[{"c_name":"上海华浩彤贸易有限公司","mobile":15896851245,"model":"BL3","thumbqq":"http://q2.qlogo.cn/headimg_dl?bs=qq&dst_uin=3227657064&src_uin=*&fid=*&spec=100&url_enc=0&referer=bu_interface&term_type=PC"},{"c_name":"辽宁岩峰塑料有限公司","mobile":18804176777,"model":"BL3","thumbqq":"http://q2.qlogo.cn/headimg_dl?bs=qq&dst_uin=2835499925&src_uin=*&fid=*&spec=100&url_enc=0&referer=bu_interface&term_type=PC"},{"c_name":"上海仕进国际贸易有限公司","mobile":13654569856,"model":"BL3","thumbqq":"http://q2.qlogo.cn/headimg_dl?bs=qq&dst_uin=23527625&src_uin=*&fid=*&spec=100&url_enc=0&referer=bu_interface&term_type=PC"},{"c_name":"苍南县同瑞塑料工艺礼品厂","mobile":13587833361,"model":"BL3","thumbqq":"http://q2.qlogo.cn/headimg_dl?bs=qq&dst_uin=32078306&src_uin=*&fid=*&spec=100&url_enc=0&referer=bu_interface&term_type=PC"}],"mobile_list":[{"mobile":13052397612,"name":"曹亚梅"},{"mobile":13472575470,"name":"赵礼明"},{"mobile":13600146698,"name":"罗启美"},{"mobile":13709410353,"name":"张燕"},{"mobile":13801006858,"name":"李琳"}],"physical":"","operate":0}
     */

    private DataBean data;
    private List<String> qapp_status;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public void setQapp_status(List<String> qapp_status) {
        this.qapp_status = qapp_status;
    }

    public List<String> getQapp_status() {
        return qapp_status;
    }

    public static class DataBean {
        /**
         * id : 153727
         * store_house : 天津
         * model : BL3
         * vendor :
         * unit_price : 0
         * c_name : 中化塑料有限公司
         * cargo_type : 现货
         * qq_nick : 老富
         * thumb_qq :
         * qq : 1057533814
         * type : 1
         * show_information : []
         * find_relevant : [{"c_name":"上海华浩彤贸易有限公司","mobile":15896851245,"model":"BL3","thumbqq":"http://q2.qlogo.cn/headimg_dl?bs=qq&dst_uin=3227657064&src_uin=*&fid=*&spec=100&url_enc=0&referer=bu_interface&term_type=PC"},{"c_name":"辽宁岩峰塑料有限公司","mobile":18804176777,"model":"BL3","thumbqq":"http://q2.qlogo.cn/headimg_dl?bs=qq&dst_uin=2835499925&src_uin=*&fid=*&spec=100&url_enc=0&referer=bu_interface&term_type=PC"},{"c_name":"上海仕进国际贸易有限公司","mobile":13654569856,"model":"BL3","thumbqq":"http://q2.qlogo.cn/headimg_dl?bs=qq&dst_uin=23527625&src_uin=*&fid=*&spec=100&url_enc=0&referer=bu_interface&term_type=PC"},{"c_name":"苍南县同瑞塑料工艺礼品厂","mobile":13587833361,"model":"BL3","thumbqq":"http://q2.qlogo.cn/headimg_dl?bs=qq&dst_uin=32078306&src_uin=*&fid=*&spec=100&url_enc=0&referer=bu_interface&term_type=PC"}]
         * mobile_list : [{"mobile":13052397612,"name":"曹亚梅"},{"mobile":13472575470,"name":"赵礼明"},{"mobile":13600146698,"name":"罗启美"},{"mobile":13709410353,"name":"张燕"},{"mobile":13801006858,"name":"李琳"}]
         * physical :
         * operate : 0
         */

        private String id;
        private String storehouse;
        private String model;
        private String vendor;
        private String price;
        private String c_name;
        private String transaction_type;
        private String qq_nick;
        private String thumb_qq;
        private String qq;
        private String type;
        private String physical;
        private List<ShowInformationBean> show_information;
        private String mobile;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getStorehouse() {
            return storehouse;
        }

        public void setStorehouse(String storehouse) {
            this.storehouse = storehouse;
        }

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

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public String getC_name() {
            return c_name;
        }

        public void setC_name(String c_name) {
            this.c_name = c_name;
        }

        public String getTransaction_type() {
            return transaction_type;
        }

        public void setTransaction_type(String transaction_type) {
            this.transaction_type = transaction_type;
        }

        public String getQq_nick() {
            return qq_nick;
        }

        public void setQq_nick(String qq_nick) {
            this.qq_nick = qq_nick;
        }

        public String getThumb_qq() {
            return thumb_qq;
        }

        public void setThumb_qq(String thumb_qq) {
            this.thumb_qq = thumb_qq;
        }

        public String getQq() {
            return qq;
        }

        public void setQq(String qq) {
            this.qq = qq;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getPhysical() {
            return physical;
        }

        public void setPhysical(String physical) {
            this.physical = physical;
        }


        public List<ShowInformationBean> getShow_information() {
            return show_information;
        }

        public void setShow_information(List<ShowInformationBean> show_information) {
            this.show_information = show_information;
        }


        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public String getMobile() {
            return mobile;
        }

        public static class ShowInformationBean {
            /**
             * id : 40426
             * title : 中沙天津石化HDPE装置开车动态
             * cate_name : 装置动态
             */

            private String id;
            private String title;
            private String cate_name;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getCate_name() {
                return cate_name;
            }

            public void setCate_name(String cate_name) {
                this.cate_name = cate_name;
            }
        }
    }
}
