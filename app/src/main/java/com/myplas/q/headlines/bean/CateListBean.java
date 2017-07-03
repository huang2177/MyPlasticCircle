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
     * err : 0
     * info : [{"id":"29334","title":"上游早报：原油及PE单体4月27日收盘价格","description":"上游早报：原油及PE单体4月19日收盘价格...","cate_id":"2","author":"","input_time":"2017-04-28","type":"PE","pv":"753","cate_name":"塑料上游"},{"id":"29332","title":"上游早报：原油及PP单体4月27日收盘价格","description":"上游早报：原油及PE单体4月19日收盘价格...","cate_id":"2","author":"","input_time":"2017-04-28","type":"PP","pv":"754","cate_name":"塑料上游"},{"id":"29327","title":"上游早报：原油及PVC单体4月27日收盘价格","description":"上游早报：原油及PVC单体4月27日收盘价格...","cate_id":"2","author":"","input_time":"2017-04-28","type":"PVC","pv":"762","cate_name":"塑料上游"},{"id":"29318","title":"4月27日国际原油价格下跌","description":"WTI原油期货收跌0.65美元，跌幅1.31%，报48.97美元/桶。布伦特原油期货收跌0.38美元...","cate_id":"2","author":"","input_time":"2017-04-28","type":"PP","pv":"747","cate_name":"塑料上游"},{"id":"29079","title":"上游早报：原油及PVC单体4月26日收盘价格","description":"上游早报：原油及PVC单体4月26日收盘价格...","cate_id":"2","author":"","input_time":"2017-04-27","type":"PVC","pv":"861","cate_name":"塑料上游"},{"id":"29077","title":"上游早报：原油及PP单体4月26日收盘价格","description":"上游早报：原油及PE单体4月19日收盘价格...","cate_id":"2","author":"","input_time":"2017-04-27","type":"PP","pv":"767","cate_name":"塑料上游"},{"id":"29076","title":"上游早报：原油及PE单体4月26日收盘价格","description":"上游早报：原油及PE单体4月19日收盘价格...","cate_id":"2","author":"","input_time":"2017-04-27","type":"PE","pv":"746","cate_name":"塑料上游"},{"id":"29064","title":"4月26日国际原油价格涨跌不一","description":"WTI 6月原油期货收涨0.06美元，涨幅0.12%，报49.62美元/桶。\r\n布伦特6月原油期货收...","cate_id":"2","author":"","input_time":"2017-04-27","type":"PP","pv":"891","cate_name":"塑料上游"},{"id":"28823","title":"上游早报：原油及PE单体4月25日收盘价格","description":"上游早报：原油及PE单体4月19日收盘价格...","cate_id":"2","author":"","input_time":"2017-04-26","type":"PE","pv":"1336","cate_name":"塑料上游"},{"id":"28822","title":"上游早报：原油及PVC单体4月25日收盘价格","description":"上游早报：原油及PVC单体4月25日收盘价格...","cate_id":"2","author":"","input_time":"2017-04-26","type":"PVC","pv":"1248","cate_name":"塑料上游"}]
     * show_msg :
     */

    private int err;
    private String show_msg;
    private List<InfoBean> info;

    public int getErr() {
        return err;
    }

    public void setErr(int err) {
        this.err = err;
    }

    public String getShow_msg() {
        return show_msg;
    }

    public void setShow_msg(String show_msg) {
        this.show_msg = show_msg;
    }

    public List<InfoBean> getInfo() {
        return info;
    }

    public void setInfo(List<InfoBean> info) {
        this.info = info;
    }

    public static class InfoBean {
        /**
         * id : 29334
         * title : 上游早报：原油及PE单体4月27日收盘价格
         * description : 上游早报：原油及PE单体4月19日收盘价格...
         * cate_id : 2
         * author :
         * input_time : 2017-04-28
         * type : PE
         * pv : 753
         * cate_name : 塑料上游
         */

        private String id;
        private String title;
        private String description;
        private String cate_id;
        private String author;
        private String input_time;
        private String type;
        private String pv;
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

        public String getCate_name() {
            return cate_name;
        }

        public void setCate_name(String cate_name) {
            this.cate_name = cate_name;
        }
    }
}
