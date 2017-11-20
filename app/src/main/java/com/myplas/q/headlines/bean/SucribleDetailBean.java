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
     * err : 0
     * info : {"id":"44959","title":"哈哈哈","cateId":"9","input_time":"2017-09-11","source_from":"本站原创","author":"中晨","pv":601,"true_pv":1,"type":"pp","lastOne":"44954","nextOne":"","content":"的点点滴滴","cate_name":"企业动态","subscribe":[{"id":"44962","title":"早盘预测：听说，今天塑料市场能反弹","input_time":"09:31","pv":"559","physical_label":"高压重包","is_free":0},{"id":"44958","title":"呃呃呃","input_time":"2017-09-11","pv":"548","physical_label":"低压注塑","is_free":0},{"id":"8353","title":"【早盘预测】涨？跌？塑料市场走势任性","input_time":"2016-11-18","pv":"7127","physical":"","is_free":0},{"id":"8340","title":"PP进口市场膜料价格即时报盘（20161117）","input_time":"2016-11-17","pv":"6076","physical":"","is_free":0},{"id":"1733","title":"关于2016年国庆节放假期间调整各品种最低交易保证金标准和涨跌停板幅度及夜盘交易时间的通知","input_time":"2016-09-30","pv":"10750","physical":"","is_free":0}],"hot":[{"id":"44969","title":"中晨塑说：看了那么多环保消息，塑料人，你该看基本面了","pv":"558","free":"0","physical_label":"低压拉丝","time":"09:57","is_free":0},{"id":"44966","title":"内蒙君正化工PVC报价平稳","pv":"554","free":"1","physical_label":"均聚拉丝","time":"09:56","is_free":1},{"id":"44965","title":"齐鲁化工城PVC塑料市场早盘报价小幅下调","pv":"554","free":"0","physical_label":"低压拉丝","time":"09:56","is_free":0},{"id":"44964","title":"中晨塑说：刘官庄那些关停的塑料厂，现在怎么样了？","pv":"500","free":"0","physical_label":"管材","time":"09:32","is_free":0},{"id":"44963","title":"9月12日财经要闻","pv":"508","free":"0","physical_label":"管材","time":"09:31","is_free":0}]}
     */

    private int err;
    private InfoBean info;

    public int getErr() {
        return err;
    }

    public void setErr(int err) {
        this.err = err;
    }

    public InfoBean getInfo() {
        return info;
    }

    public void setInfo(InfoBean info) {
        this.info = info;
    }

    public static class InfoBean {
        /**
         * id : 44959
         * title : 哈哈哈
         * cateId : 9
         * input_time : 2017-09-11
         * source_from : 本站原创
         * author : 中晨
         * pv : 601
         * true_pv : 1
         * type : pp
         * lastOne : 44954
         * nextOne :
         * content : 的点点滴滴
         * cate_name : 企业动态
         * subscribe : [{"id":"44962","title":"早盘预测：听说，今天塑料市场能反弹","input_time":"09:31","pv":"559","physical_label":"高压重包","is_free":0},{"id":"44958","title":"呃呃呃","input_time":"2017-09-11","pv":"548","physical_label":"低压注塑","is_free":0},{"id":"8353","title":"【早盘预测】涨？跌？塑料市场走势任性","input_time":"2016-11-18","pv":"7127","physical":"","is_free":0},{"id":"8340","title":"PP进口市场膜料价格即时报盘（20161117）","input_time":"2016-11-17","pv":"6076","physical":"","is_free":0},{"id":"1733","title":"关于2016年国庆节放假期间调整各品种最低交易保证金标准和涨跌停板幅度及夜盘交易时间的通知","input_time":"2016-09-30","pv":"10750","physical":"","is_free":0}]
         * hot : [{"id":"44969","title":"中晨塑说：看了那么多环保消息，塑料人，你该看基本面了","pv":"558","free":"0","physical_label":"低压拉丝","time":"09:57","is_free":0},{"id":"44966","title":"内蒙君正化工PVC报价平稳","pv":"554","free":"1","physical_label":"均聚拉丝","time":"09:56","is_free":1},{"id":"44965","title":"齐鲁化工城PVC塑料市场早盘报价小幅下调","pv":"554","free":"0","physical_label":"低压拉丝","time":"09:56","is_free":0},{"id":"44964","title":"中晨塑说：刘官庄那些关停的塑料厂，现在怎么样了？","pv":"500","free":"0","physical_label":"管材","time":"09:32","is_free":0},{"id":"44963","title":"9月12日财经要闻","pv":"508","free":"0","physical_label":"管材","time":"09:31","is_free":0}]
         */

        private String id;
        private String title;
        private String cate_id;
        private String input_time;
        private String source_from;
        private String author;
        private String pv;
        private String true_pv;
        private String type;
        private String lastOne;
        private String nextOne;
        private String content;
        private String cate_name;
        private List<SubscribeBean> subscribe;
        private List<HotBean> hot;

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

        public String getInput_time() {
            return input_time;
        }

        public void setInput_time(String input_time) {
            this.input_time = input_time;
        }

        public String getSource_from() {
            return source_from;
        }

        public void setSource_from(String source_from) {
            this.source_from = source_from;
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

        public String getTrue_pv() {
            return true_pv;
        }

        public void setTrue_pv(String true_pv) {
            this.true_pv = true_pv;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
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

        public List<SubscribeBean> getSubscribe() {
            return subscribe;
        }

        public void setSubscribe(List<SubscribeBean> subscribe) {
            this.subscribe = subscribe;
        }

        public List<HotBean> getHot() {
            return hot;
        }

        public void setHot(List<HotBean> hot) {
            this.hot = hot;
        }

        public static class SubscribeBean {
            /**
             * id : 44962
             * title : 早盘预测：听说，今天塑料市场能反弹
             * input_time : 09:31
             * pv : 559
             * physical_label : 高压重包
             * is_free : 0
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

        public static class HotBean {
            /**
             * id : 44969
             * title : 中晨塑说：看了那么多环保消息，塑料人，你该看基本面了
             * pv : 558
             * free : 0
             * physical_label : 低压拉丝
             * time : 09:57
             * is_free : 0
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
    }
}
