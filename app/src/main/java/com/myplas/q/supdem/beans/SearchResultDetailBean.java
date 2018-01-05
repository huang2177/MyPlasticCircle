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
     * data : {"id":153727,"store_house":"天津","model":"BL3","f_name":"","unit_price":0,"c_name":"中化塑料有限公司","cargo_type":"现货","qq_name":"老富","thumb_qq":"","qq":1057533814,"type":1,"show_information":[],"find_relevant":[{"c_name":"上海华浩彤贸易有限公司","mobile":15896851245,"model":"BL3","thumbqq":"http://q2.qlogo.cn/headimg_dl?bs=qq&dst_uin=3227657064&src_uin=*&fid=*&spec=100&url_enc=0&referer=bu_interface&term_type=PC"},{"c_name":"辽宁岩峰塑料有限公司","mobile":18804176777,"model":"BL3","thumbqq":"http://q2.qlogo.cn/headimg_dl?bs=qq&dst_uin=2835499925&src_uin=*&fid=*&spec=100&url_enc=0&referer=bu_interface&term_type=PC"},{"c_name":"上海仕进国际贸易有限公司","mobile":13654569856,"model":"BL3","thumbqq":"http://q2.qlogo.cn/headimg_dl?bs=qq&dst_uin=23527625&src_uin=*&fid=*&spec=100&url_enc=0&referer=bu_interface&term_type=PC"},{"c_name":"苍南县同瑞塑料工艺礼品厂","mobile":13587833361,"model":"BL3","thumbqq":"http://q2.qlogo.cn/headimg_dl?bs=qq&dst_uin=32078306&src_uin=*&fid=*&spec=100&url_enc=0&referer=bu_interface&term_type=PC"}],"mobile_list":[{"mobile":13052397612,"name":"曹亚梅"},{"mobile":13472575470,"name":"赵礼明"},{"mobile":13600146698,"name":"罗启美"},{"mobile":13709410353,"name":"张燕"},{"mobile":13801006858,"name":"李琳"}],"physical":"","operate":0}
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
         * id : 153727
         * store_house : 天津
         * model : BL3
         * f_name :
         * unit_price : 0
         * c_name : 中化塑料有限公司
         * cargo_type : 现货
         * qq_name : 老富
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
        private String store_house;
        private String model;
        private String f_name;
        private String unit_price;
        private String c_name;
        private String cargo_type;
        private String qq_name;
        private String thumb_qq;
        private String qq;
        private String type;
        private String physical;
        private String operate;
        private List<ShowInformationBean> show_information;
        private List<FindRelevantBean> find_relevant;
        private List<MobileListBean> mobile_list;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getStore_house() {
            return store_house;
        }

        public void setStore_house(String store_house) {
            this.store_house = store_house;
        }

        public String getModel() {
            return model;
        }

        public void setModel(String model) {
            this.model = model;
        }

        public String getF_name() {
            return f_name;
        }

        public void setF_name(String f_name) {
            this.f_name = f_name;
        }

        public String getUnit_price() {
            return unit_price;
        }

        public void setUnit_price(String unit_price) {
            this.unit_price = unit_price;
        }

        public String getC_name() {
            return c_name;
        }

        public void setC_name(String c_name) {
            this.c_name = c_name;
        }

        public String getCargo_type() {
            return cargo_type;
        }

        public void setCargo_type(String cargo_type) {
            this.cargo_type = cargo_type;
        }

        public String getQq_name() {
            return qq_name;
        }

        public void setQq_name(String qq_name) {
            this.qq_name = qq_name;
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

        public String getOperate() {
            return operate;
        }

        public void setOperate(String operate) {
            this.operate = operate;
        }

        public List<ShowInformationBean> getShow_information() {
            return show_information;
        }

        public void setShow_information(List<ShowInformationBean> show_information) {
            this.show_information = show_information;
        }

        public List<FindRelevantBean> getFind_relevant() {
            return find_relevant;
        }

        public void setFind_relevant(List<FindRelevantBean> find_relevant) {
            this.find_relevant = find_relevant;
        }

        public List<MobileListBean> getMobile_list() {
            return mobile_list;
        }

        public void setMobile_list(List<MobileListBean> mobile_list) {
            this.mobile_list = mobile_list;
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
        public static class FindRelevantBean {
            /**
             * c_name : 上海华浩彤贸易有限公司
             * mobile : 15896851245
             * model : BL3
             * thumbqq : http://q2.qlogo.cn/headimg_dl?bs=qq&dst_uin=3227657064&src_uin=*&fid=*&spec=100&url_enc=0&referer=bu_interface&term_type=PC
             */

            private String c_name;
            private String mobile;
            private String model;
            private String thumbqq;

            public String getC_name() {
                return c_name;
            }

            public void setC_name(String c_name) {
                this.c_name = c_name;
            }

            public String getMobile() {
                return mobile;
            }

            public void setMobile(String mobile) {
                this.mobile = mobile;
            }

            public String getModel() {
                return model;
            }

            public void setModel(String model) {
                this.model = model;
            }

            public String getThumbqq() {
                return thumbqq;
            }

            public void setThumbqq(String thumbqq) {
                this.thumbqq = thumbqq;
            }
        }

        public static class MobileListBean {
            /**
             * mobile : 13052397612
             * name : 曹亚梅
             */

            private String mobile;
            private String name;

            public String getMobile() {
                return mobile;
            }

            public void setMobile(String mobile) {
                this.mobile = mobile;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }
        }
    }
}
