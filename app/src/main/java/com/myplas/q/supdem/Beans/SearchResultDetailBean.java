package com.myplas.q.supdem.Beans;

import java.util.List;

/**
 * 编写：黄双
 * 邮箱：15378412400@163.com
 * 时间： 2017/6/291724.
 */

public class SearchResultDetailBean {

    /**
     * err : 0
     * data : {"c_name":"明年我的时候","produce_place":"","cargo_type":"现货","unit_price":"0","model":"7000F","qq_name":"灿灿1943","qq":"183097697","thumbqq":"http://q2.qlogo.cn/headimg_dl?bs=qq&dst_uin=183097697&src_uin=*&fid=*&spec=100&url_enc=0&referer=bu_interface&term_type=PC","is_buy":"1","store_house":"上海","show_information":[{"id":"40426","title":"中沙天津石化HDPE装置开车动态","cate_name":"装置动态"},{"id":"43110","title":"上游早报：原油及PVC单体7月12日收盘价格","cate_name":"上游动态"},{"id":"43250","title":"新闻早提示","cate_name":"早提示"}],"find_relevant":[{"c_name":"上海天业国际贸易有限公司","mobile":"15618650630","model":"7000F","thumbqq":"http://q2.qlogo.cn/headimg_dl?bs=qq&dst_uin=1516657714&src_uin=*&fid=*&spec=100&url_enc=0&referer=bu_interface&term_type=PC"},{"c_name":"上海田塑化工有限公司","mobile":"13388572729","model":"7000F","thumbqq":"http://q2.qlogo.cn/headimg_dl?bs=qq&dst_uin=4972776&src_uin=*&fid=*&spec=100&url_enc=0&referer=bu_interface&term_type=PC"}],"mobile_list":[{"mobile":"13806260431","name":"刘招"}],"physical":""}
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
         * c_name : 明年我的时候
         * produce_place :
         * cargo_type : 现货
         * unit_price : 0
         * model : 7000F
         * qq_name : 灿灿1943
         * qq : 183097697
         * thumbqq : http://q2.qlogo.cn/headimg_dl?bs=qq&dst_uin=183097697&src_uin=*&fid=*&spec=100&url_enc=0&referer=bu_interface&term_type=PC
         * is_buy : 1
         * store_house : 上海
         * show_information : [{"id":"40426","title":"中沙天津石化HDPE装置开车动态","cate_name":"装置动态"},{"id":"43110","title":"上游早报：原油及PVC单体7月12日收盘价格","cate_name":"上游动态"},{"id":"43250","title":"新闻早提示","cate_name":"早提示"}]
         * find_relevant : [{"c_name":"上海天业国际贸易有限公司","mobile":"15618650630","model":"7000F","thumbqq":"http://q2.qlogo.cn/headimg_dl?bs=qq&dst_uin=1516657714&src_uin=*&fid=*&spec=100&url_enc=0&referer=bu_interface&term_type=PC"},{"c_name":"上海田塑化工有限公司","mobile":"13388572729","model":"7000F","thumbqq":"http://q2.qlogo.cn/headimg_dl?bs=qq&dst_uin=4972776&src_uin=*&fid=*&spec=100&url_enc=0&referer=bu_interface&term_type=PC"}]
         * mobile_list : [{"mobile":"13806260431","name":"刘招"}]
         * physical :
         */

        private String c_name;
        private String produce_place;
        private String cargo_type;
        private String unit_price;
        private String model;
        private String qq_name;
        private String qq;
        private String thumbqq;
        private String is_buy;
        private String store_house;
        private String physical;
        private List<ShowInformationBean> show_information;
        private List<FindRelevantBean> find_relevant;
        private List<MobileListBean> mobile_list;

        public String getC_name() {
            return c_name;
        }

        public void setC_name(String c_name) {
            this.c_name = c_name;
        }

        public String getProduce_place() {
            return produce_place;
        }

        public void setProduce_place(String produce_place) {
            this.produce_place = produce_place;
        }

        public String getCargo_type() {
            return cargo_type;
        }

        public void setCargo_type(String cargo_type) {
            this.cargo_type = cargo_type;
        }

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

        public String getQq_name() {
            return qq_name;
        }

        public void setQq_name(String qq_name) {
            this.qq_name = qq_name;
        }

        public String getQq() {
            return qq;
        }

        public void setQq(String qq) {
            this.qq = qq;
        }

        public String getThumbqq() {
            return thumbqq;
        }

        public void setThumbqq(String thumbqq) {
            this.thumbqq = thumbqq;
        }

        public String getIs_buy() {
            return is_buy;
        }

        public void setIs_buy(String is_buy) {
            this.is_buy = is_buy;
        }

        public String getStore_house() {
            return store_house;
        }

        public void setStore_house(String store_house) {
            this.store_house = store_house;
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
             * c_name : 上海天业国际贸易有限公司
             * mobile : 15618650630
             * model : 7000F
             * thumbqq : http://q2.qlogo.cn/headimg_dl?bs=qq&dst_uin=1516657714&src_uin=*&fid=*&spec=100&url_enc=0&referer=bu_interface&term_type=PC
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
             * mobile : 13806260431
             * name : 刘招
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
