package com.myplas.q.headlines.bean;

import java.util.List;

/**
 * 编写： 黄双
 * 电话：15378412400
 * 邮箱：15378412400@163.com
 * 时间：2017/4/date_selected 16:41
 */
public class SucribleDetailBean {


    /**
     * code : 0
     * data : {"id":59371,"title":"cece","img":"","cate_id":2,"hot":[{"id":59390,"title":"asdasd","pv":533,"free":1,"physical_label":"管材","time":"2017-12-25","is_free":1},{"id":59389,"title":"阿三大苏打","pv":536,"free":1,"physical_label":"高压重包","time":"2017-12-25","is_free":1},{"id":59387,"title":"阿斯顿","pv":602,"free":1,"physical_label":"高压涂覆","time":"2017-12-25","is_free":1},{"id":59386,"title":"阿斯顿","pv":615,"free":1,"physical_label":"高压重包","time":"2017-12-25","is_free":1},{"id":59385,"title":"阿斯顿","pv":590,"free":1,"physical_label":"高压重包","time":"2017-12-25","is_free":1}],"source_from":"本站原创","link_url":"","keywords":"测试","description":"c测试测试测试","status":1,"sort_order":0,"input_time":"2017-12-18","update_time":1514361631,"admin_name":"admin","sm_img":"","author":"中晨","pv":577,"type":"pp","free":1,"true_pv":9,"physical":"2,10","thumb_up":0,"lastOne":59370,"nextOne":59378,"content":"c测试测试测试","cate_name":"上游动态","subscribe":[{"id":59390,"title":"asdasd","input_time":"2017-12-25","pv":533,"physical_label":"高压重包","is_free":1},{"id":35970,"title":"国内PE装置动态报道汇总（20170602）","input_time":"2017-06-02","pv":7057,"physical":"","is_free":0},{"id":8351,"title":"11月17日原油期货继续下跌","input_time":"2016-11-18","pv":7717,"physical":"","is_free":0},{"id":8340,"title":"PP进口市场膜料价格即时报盘（20161117）","input_time":"2016-11-17","pv":6329,"physical":"","is_free":0},{"id":1733,"title":"关于2016年国庆节放假期间调整各品种最低交易保证金标准和涨跌停板幅度及夜盘交易时间的通知","input_time":"2016-09-30","pv":11023,"physical":"","is_free":0}]}
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
         * id : 59371
         * title : cece
         * img : 
         * cate_id : 2
         * hot : [{"id":59390,"title":"asdasd","pv":533,"free":1,"physical_label":"管材","time":"2017-12-25","is_free":1},{"id":59389,"title":"阿三大苏打","pv":536,"free":1,"physical_label":"高压重包","time":"2017-12-25","is_free":1},{"id":59387,"title":"阿斯顿","pv":602,"free":1,"physical_label":"高压涂覆","time":"2017-12-25","is_free":1},{"id":59386,"title":"阿斯顿","pv":615,"free":1,"physical_label":"高压重包","time":"2017-12-25","is_free":1},{"id":59385,"title":"阿斯顿","pv":590,"free":1,"physical_label":"高压重包","time":"2017-12-25","is_free":1}]
         * source_from : 本站原创
         * link_url : 
         * keywords : 测试
         * description : c测试测试测试
         * status : 1
         * sort_order : 0
         * input_time : 2017-12-18
         * update_time : 1514361631
         * admin_name : admin
         * sm_img : 
         * author : 中晨
         * pv : 577
         * type : pp
         * free : 1
         * true_pv : 9
         * physical : 2,10
         * thumb_up : 0
         * lastOne : 59370
         * nextOne : 59378
         * content : c测试测试测试
         * cate_name : 上游动态
         * subscribe : [{"id":59390,"title":"asdasd","input_time":"2017-12-25","pv":533,"physical_label":"高压重包","is_free":1},{"id":35970,"title":"国内PE装置动态报道汇总（20170602）","input_time":"2017-06-02","pv":7057,"physical":"","is_free":0},{"id":8351,"title":"11月17日原油期货继续下跌","input_time":"2016-11-18","pv":7717,"physical":"","is_free":0},{"id":8340,"title":"PP进口市场膜料价格即时报盘（20161117）","input_time":"2016-11-17","pv":6329,"physical":"","is_free":0},{"id":1733,"title":"关于2016年国庆节放假期间调整各品种最低交易保证金标准和涨跌停板幅度及夜盘交易时间的通知","input_time":"2016-09-30","pv":11023,"physical":"","is_free":0}]
         */

        private String id;
        private String title;
        private String img;
        private String cate_id;
        private String source_from;
        private String link_url;
        private String keywords;
        private String description;
        private String status;
        private String sort_order;
        private String input_time;
        private String update_time;
        private String admin_name;
        private String sm_img;
        private String author;
        private String pv;
        private String type;
        private String free;
        private String true_pv;
        private String physical;
        private String thumb_up;
        private String lastOne;
        private String nextOne;
        private String content;
        private String cate_name;
        private List<HotBean> hot;
        private List<SubscribeBean> subscribe;

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

        public String getCate_id() {
            return cate_id;
        }

        public void setCate_id(String cate_id) {
            this.cate_id = cate_id;
        }

        public String getSource_from() {
            return source_from;
        }

        public void setSource_from(String source_from) {
            this.source_from = source_from;
        }

        public String getLink_url() {
            return link_url;
        }

        public void setLink_url(String link_url) {
            this.link_url = link_url;
        }

        public String getKeywords() {
            return keywords;
        }

        public void setKeywords(String keywords) {
            this.keywords = keywords;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getSort_order() {
            return sort_order;
        }

        public void setSort_order(String sort_order) {
            this.sort_order = sort_order;
        }

        public String getInput_time() {
            return input_time;
        }

        public void setInput_time(String input_time) {
            this.input_time = input_time;
        }

        public String getUpdate_time() {
            return update_time;
        }

        public void setUpdate_time(String update_time) {
            this.update_time = update_time;
        }

        public String getAdmin_name() {
            return admin_name;
        }

        public void setAdmin_name(String admin_name) {
            this.admin_name = admin_name;
        }

        public String getSm_img() {
            return sm_img;
        }

        public void setSm_img(String sm_img) {
            this.sm_img = sm_img;
        }

        public String getAuthor() {
            return author;
        }

        public void setAuthor(String author) {
            this.author = author;
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

        public String getFree() {
            return free;
        }

        public void setFree(String free) {
            this.free = free;
        }

        public String getTrue_pv() {
            return true_pv;
        }

        public void setTrue_pv(String true_pv) {
            this.true_pv = true_pv;
        }

        public String getPhysical() {
            return physical;
        }

        public void setPhysical(String physical) {
            this.physical = physical;
        }

        public String getThumb_up() {
            return thumb_up;
        }

        public void setThumb_up(String thumb_up) {
            this.thumb_up = thumb_up;
        }

        public String getLastOne() {
            return lastOne;
        }

        public void setLastOne(String lastOne) {
            this.lastOne = lastOne;
        }

        public String getNextOne() {
            return nextOne;
        }

        public void setNextOne(String nextOne) {
            this.nextOne = nextOne;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getCate_name() {
            return cate_name;
        }

        public void setCate_name(String cate_name) {
            this.cate_name = cate_name;
        }

        public List<HotBean> getHot() {
            return hot;
        }

        public void setHot(List<HotBean> hot) {
            this.hot = hot;
        }

        public List<SubscribeBean> getSubscribe() {
            return subscribe;
        }

        public void setSubscribe(List<SubscribeBean> subscribe) {
            this.subscribe = subscribe;
        }

        public static class HotBean {
            /**
             * id : 59390
             * title : asdasd
             * pv : 533
             * free : 1
             * physical_label : 管材
             * time : 2017-12-25
             * is_free : 1
             */

            private String id;
            private String title;
            private String pv;
            private String free;
            private String physical_label;
            private String time;
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

            public String getPv() {
                return pv;
            }

            public void setPv(String pv) {
                this.pv = pv;
            }

            public String getFree() {
                return free;
            }

            public void setFree(String free) {
                this.free = free;
            }

            public String getPhysical_label() {
                return physical_label;
            }

            public void setPhysical_label(String physical_label) {
                this.physical_label = physical_label;
            }

            public String getTime() {
                return time;
            }

            public void setTime(String time) {
                this.time = time;
            }

            public String getIs_free() {
                return is_free;
            }

            public void setIs_free(String is_free) {
                this.is_free = is_free;
            }
        }

        public static class SubscribeBean {
            /**
             * id : 59390
             * title : asdasd
             * input_time : 2017-12-25
             * pv : 533
             * physical_label : 高压重包
             * is_free : 1
             * physical : 
             */

            private String id;
            private String title;
            private String input_time;
            private String pv;
            private String physical_label;
            private String is_free;
            private String physical;

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

            public String getPhysical_label() {
                return physical_label;
            }

            public void setPhysical_label(String physical_label) {
                this.physical_label = physical_label;
            }

            public String getIs_free() {
                return is_free;
            }

            public void setIs_free(String is_free) {
                this.is_free = is_free;
            }

            public String getPhysical() {
                return physical;
            }

            public void setPhysical(String physical) {
                this.physical = physical;
            }
        }
    }
}
