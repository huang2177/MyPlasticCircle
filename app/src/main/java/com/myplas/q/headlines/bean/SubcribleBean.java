package com.myplas.q.headlines.bean;

import java.util.List;

/**
 * 编写： 黄双
 * 电话：15378412400
 * 邮箱：15378412400@163.com
 * 时间：2017/4/date_selected 15:46
 */
public class SubcribleBean {

    /**
     * code : 0
     * data : [{"id":59397,"title":"666","cate_id":11,"description":"90088你不够格...","input_time":"2018-01-04","pv":555,"cate_name":["装置动态"],"type":"低压拉丝","is_free":0},{"id":59396,"title":"999","cate_id":9,"description":"BFFF人同意...","input_time":"2018-01-04","pv":550,"cate_name":["企业动态"],"type":"高压涂覆","is_free":1},{"id":59395,"title":"888","cate_id":7,"description":"婆婆家你你你...","input_time":"2018-01-04","pv":576,"cate_name":["今日焦点"],"type":"低压拉丝","is_free":0},{"id":59390,"title":"asdasd","cate_id":1,"description":"asdasdasd...","input_time":"2017-12-25","pv":535,"cate_name":["早提示"],"type":"管材","is_free":1},{"id":59389,"title":"阿三大苏打","cate_id":1,"description":"asdasd...","input_time":"2017-12-25","pv":540,"cate_name":["早提示"],"type":"高压重包","is_free":1},{"id":59387,"title":"阿斯顿","cate_id":1,"description":"阿三大苏打...","input_time":"2017-12-25","pv":603,"cate_name":["早提示"],"type":"高压重包","is_free":1},{"id":59386,"title":"阿斯顿","cate_id":1,"description":"阿三大苏打...","input_time":"2017-12-25","pv":616,"cate_name":["早提示"],"type":"高压重包","is_free":1},{"id":59385,"title":"阿斯顿","cate_id":1,"description":"阿三大苏打...","input_time":"2017-12-25","pv":593,"cate_name":["早提示"],"type":"高压重包","is_free":1},{"id":59383,"title":"阿斯顿","cate_id":1,"description":"阿三大苏打...","input_time":"2017-12-25","pv":625,"cate_name":["早提示"],"type":"高压重包","is_free":1},{"id":59382,"title":"阿斯顿","cate_id":1,"description":"阿三大苏打...","input_time":"2017-12-25","pv":607,"cate_name":["早提示"],"type":"高压重包","is_free":1}]
     * show_msg : 更新了10条数据
     * banner : []
     * hot_search : 5000S
     */

    private String show_msg;
    private String hot_search;
    private List<DataBean> data;
    private List<BannerBean> banner;

    public String getShow_msg() {
        return show_msg;
    }

    public void setShow_msg(String show_msg) {
        this.show_msg = show_msg;
    }

    public String getHot_search() {
        return hot_search;
    }

    public void setHot_search(String hot_search) {
        this.hot_search = hot_search;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public List<BannerBean> getBanner() {
        return banner;
    }

    public void setBanner(List<BannerBean> banner) {
        this.banner = banner;
    }

    public static class DataBean {
        /**
         * id : 59397
         * title : 666
         * cate_id : 11
         * description : 90088你不够格...
         * input_time : 2018-01-04
         * pv : 555
         * cate_name : ["装置动态"]
         * type : 低压拉丝
         * is_free : 0
         */

        private String id;
        private String title;
        private String cate_id;
        private String description;
        private String input_time;
        private String pv;
        private String type;
        private String is_free;
        private List<String> cate_name;

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

        public String getCate_id() {
            return cate_id;
        }

        public void setCate_id(String cate_id) {
            this.cate_id = cate_id;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getInput_time() {
            return input_time;
        }

        public void setInput_time(String input_time) {
            this.input_time = input_time;
        }

        public String getPv() {
            return pv;
        }

        public void setPv(String pv) {
            this.pv = pv;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getIs_free() {
            return is_free;
        }

        public void setIs_free(String is_free) {
            this.is_free = is_free;
        }

        public List<String> getCate_name() {
            return cate_name;
        }

        public void setCate_name(List<String> cate_name) {
            this.cate_name = cate_name;
        }
    }
    public static class BannerBean {
        /**
         * id : 44969
         * title : 中晨塑说：看了那么多环保消息，塑料人，你该看基本面了
         * img : http://pic.myplas.com/upload/17/09/12/59b73ef04af59.jpg
         */

        private String id;
        private String title;
        private String img;
        private String is_free;

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

        public String getImg() {
            return img;
        }

        public void setImg(String img) {
            this.img = img;
        }

        public String getIs_free() {
            return is_free;
        }

        public void setIs_free(String is_free) {
            this.is_free = is_free;
        }
    }
}
