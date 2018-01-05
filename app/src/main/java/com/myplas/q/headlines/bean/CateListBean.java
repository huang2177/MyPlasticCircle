package com.myplas.q.headlines.bean;

import java.util.List;

/**
 * 编写： 黄双
 * 电话：15378412400
 * 邮箱：15378412400@163.com
 * 时间：2017/4/5 10:40
 */
public class CateListBean {


    /**
     * code : 0
     * data : [{"id":59378,"title":"","description":"dfdfdfdfdf...","cate_id":2,"author":"","input_time":"2017-12-21","type":"","pv":530,"physical":"","cate_name":"塑料上游","is_free":1},{"id":59371,"title":"cece","description":"c测试测试测试...","cate_id":2,"author":"","input_time":"2017-12-18","type":"均聚拉丝","pv":576,"physical":"2,10","cate_name":"塑料上游","is_free":1},{"id":59368,"title":"cece","description":"c测试测试测试...","cate_id":2,"author":"","input_time":"2017-12-18","type":"均聚拉丝","pv":519,"physical":"2,10","cate_name":"塑料上游","is_free":0},{"id":59369,"title":"cece","description":"c测试测试测试...","cate_id":2,"author":"","input_time":"2017-12-18","type":"高压涂覆","pv":554,"physical":"2,10","cate_name":"塑料上游","is_free":1},{"id":59370,"title":"cece","description":"c测试测试测试...","cate_id":2,"author":"","input_time":"2017-12-18","type":"高压涂覆","pv":510,"physical":"2,10","cate_name":"塑料上游","is_free":1},{"id":59365,"title":"cece","description":"c测试测试测试...","cate_id":2,"author":"","input_time":"2017-12-18","type":"均聚拉丝","pv":562,"physical":"2,10","cate_name":"塑料上游","is_free":1},{"id":59366,"title":"cece","description":"c测试测试测试...","cate_id":2,"author":"","input_time":"2017-12-18","type":"均聚拉丝","pv":597,"physical":"2,10","cate_name":"塑料上游","is_free":0},{"id":59367,"title":"cece","description":"c测试测试测试...","cate_id":2,"author":"","input_time":"2017-12-18","type":"均聚拉丝","pv":538,"physical":"2,10","cate_name":"塑料上游","is_free":0},{"id":59363,"title":"cece","description":"c测试测试测试...","cate_id":2,"author":"","input_time":"2017-12-18","type":"均聚拉丝","pv":513,"physical":"2,10","cate_name":"塑料上游","is_free":1},{"id":59364,"title":"cece","description":"c测试测试测试...","cate_id":2,"author":"","input_time":"2017-12-18","type":"高压涂覆","pv":523,"physical":"2,10","cate_name":"塑料上游","is_free":1}]
     * hot_search : 5000S
     * show_msg :
     */

    private String hot_search;
    private String show_msg;
    private List<DataBean> data;


    public String getHot_search() {
        return hot_search;
    }

    public void setHot_search(String hot_search) {
        this.hot_search = hot_search;
    }

    public String getShow_msg() {
        return show_msg;
    }

    public void setShow_msg(String show_msg) {
        this.show_msg = show_msg;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * id : 59378
         * title :
         * description : dfdfdfdfdf...
         * cate_id : 2
         * author :
         * input_time : 2017-12-21
         * type :
         * pv : 530
         * physical :
         * cate_name : 塑料上游
         * is_free : 1
         */

        private String id;
        private String title;
        private String description;
        private String cate_id;
        private String author;
        private String input_time;
        private String type;
        private String pv;
        private String physical;
        private String cate_name;
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

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getCate_id() {
            return cate_id;
        }

        public void setCate_id(String cate_id) {
            this.cate_id = cate_id;
        }

        public String getAuthor() {
            return author;
        }

        public void setAuthor(String author) {
            this.author = author;
        }

        public String getInput_time() {
            return input_time;
        }

        public void setInput_time(String input_time) {
            this.input_time = input_time;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getPv() {
            return pv;
        }

        public void setPv(String pv) {
            this.pv = pv;
        }

        public String getPhysical() {
            return physical;
        }

        public void setPhysical(String physical) {
            this.physical = physical;
        }

        public String getCate_name() {
            return cate_name;
        }

        public void setCate_name(String cate_name) {
            this.cate_name = cate_name;
        }

        public String getIs_free() {
            return is_free;
        }

        public void setIs_free(String is_free) {
            this.is_free = is_free;
        }
    }
}
