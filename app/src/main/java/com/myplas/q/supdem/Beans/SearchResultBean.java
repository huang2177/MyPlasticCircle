package com.myplas.q.supdem.Beans;

import java.util.List;

/**
 * 编写：黄双
 * 邮箱：15378412400@163.com
 * 时间： 2017/6/291724.
 */

public class SearchResultBean {

    /**
     * err : 0
     * list : [{"c_name":"上海田塑化工有限公司","produce_place":"伊朗石化","cargo_type":"现货","unit_price":"0","model":"7000F","thumbqq":["http://q2.qlogo.cn/headimg_dl?bs=qq&dst_uin=4972776&src_uin=*&fid=*&spec=100&url_enc=0&referer=bu_interface&term_type=PC"],"qq_name":"遇见","qq":"4972776","update_time":"07-18 00:01","is_buy":"1","store_house":"宁波","news_id":43050,"mobile_list":[{"name":"李慧丹","mobile":"13355778767"}],"qq_name_qq":[{"qq_name":"遇见","qq":"4972776"}],"produce_place_one":"伊朗石化","type":9,"user_id":"53402"},{"c_name":"上海天业国际贸易有限公司","produce_place":"伊朗石化","cargo_type":"7月底","unit_price":"9600","model":"7000F","thumbqq":["http://q2.qlogo.cn/headimg_dl?bs=qq&dst_uin=1516657714&src_uin=*&fid=*&spec=100&url_enc=0&referer=bu_interface&term_type=PC"],"qq_name":"上海天业（中萱）巽通-孔","qq":"1516657714","update_time":"07-18 00:01","is_buy":"1","store_house":"宁波","news_id":43050,"mobile_list":{"3":{"name":"张永明","mobile":"15618650630"}},"qq_name_qq":{"1":{"qq_name":"上海天业（中萱）巽通-孔","qq":"1516657714"}},"produce_place_one":"伊朗石化","type":9,"user_id":"53402"},{"c_name":"明年我的时候","produce_place":"","cargo_type":"现货","unit_price":"0","model":"7000F","thumbqq":["http://q2.qlogo.cn/headimg_dl?bs=qq&dst_uin=183097697&src_uin=*&fid=*&spec=100&url_enc=0&referer=bu_interface&term_type=PC"],"qq_name":"灿灿1943","qq":"183097697","update_time":"07-18 00:01","is_buy":"1","store_house":"上海","news_id":43050,"mobile_list":{"2":{"name":"刘招","mobile":"13806260431"}},"qq_name_qq":{"2":{"qq_name":"灿灿1943","qq":"183097697"}},"produce_place_one":"","type":9,"user_id":"53402"}]
     * total : 3
     * search : 7000F
     */

    private int err;
    private String total;
    private String search;
    private List<ListBean> list;

    public int getErr() {
        return err;
    }

    public void setErr(int err) {
        this.err = err;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getSearch() {
        return search;
    }

    public void setSearch(String search) {
        this.search = search;
    }

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    public static class ListBean {
        /**
         * c_name : 上海田塑化工有限公司
         * produce_place : 伊朗石化
         * cargo_type : 现货
         * unit_price : 0
         * model : 7000F
         * thumbqq : ["http://q2.qlogo.cn/headimg_dl?bs=qq&dst_uin=4972776&src_uin=*&fid=*&spec=100&url_enc=0&referer=bu_interface&term_type=PC"]
         * qq_name : 遇见
         * qq : 4972776
         * update_time : 07-18 00:01
         * is_buy : 1
         * store_house : 宁波
         * news_id : 43050
         * mobile_list : [{"name":"李慧丹","mobile":"13355778767"}]
         * qq_name_qq : [{"qq_name":"遇见","qq":"4972776"}]
         * produce_place_one : 伊朗石化
         * type : 9
         * user_id : 53402
         */

        private String c_name;
        private String produce_place;
        private String cargo_type;
        private String unit_price;
        private String model;
        private String qq_name;
        private String qq;
        private String update_time;
        private String is_buy;
        private String store_house;
        private String news_id;
        private String produce_place_one;
        private String type;
        private String user_id;
        private List<String> thumbqq;

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

        public String getUpdate_time() {
            return update_time;
        }

        public void setUpdate_time(String update_time) {
            this.update_time = update_time;
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

        public String getNews_id() {
            return news_id;
        }

        public void setNews_id(String news_id) {
            this.news_id = news_id;
        }

        public String getProduce_place_one() {
            return produce_place_one;
        }

        public void setProduce_place_one(String produce_place_one) {
            this.produce_place_one = produce_place_one;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getUser_id() {
            return user_id;
        }

        public void setUser_id(String user_id) {
            this.user_id = user_id;
        }

        public List<String> getThumbqq() {
            return thumbqq;
        }

        public void setThumbqq(List<String> thumbqq) {
            this.thumbqq = thumbqq;
        }
    }
}
