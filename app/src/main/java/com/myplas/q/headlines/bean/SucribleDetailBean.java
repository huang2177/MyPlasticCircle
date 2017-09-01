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
         * id : 44461
         * title : 竞拍分析：本周神华PE竞拍详情分析
         * cate_id : 76
         * input_time : 2017-07-21
         * source_from : 本站原创
         * author : 中晨
         * pv : 1699
         * true_pv : 195
         * type : pe
         * lastOne : 44455
         * nextOne : 44917
         * cate_name : 中晨塑说
         * subscribe : [{"id":"44910","title":"噜啦啦","input_time":"09:56","pv":"544","physical_label":"低压拉丝"},{"id":"44896","title":"装置动态","input_time":"2017-08-31","pv":"563","physical_label":"茂金属"},{"id":"44885","title":"早盘预测：油价连续两日暴跌，塑料市场能否延续强势","input_time":"2017-08-30","pv":"528","physical":""},{"id":"8340","title":"PP进口市场膜料价格即时报盘（20161117）","input_time":"2016-11-17","pv":"6075","physical":""},{"id":"1733","title":"关于2016年国庆节放假期间调整各品种最低交易保证金标准和涨跌停板幅度及夜盘交易时间的通知","input_time":"2016-09-30","pv":"10744","physical":""}]
         * hot : [{"id":"44918","title":"独家解读","pv":"505","physical_label":"高压吹膜","time":"11:28"},{"id":"44917","title":"中晨塑说","pv":"538","physical_label":"高压涂覆","time":"11:27"},{"id":"44916","title":"666","pv":"520","physical_label":"茂金属","time":"11:19"},{"id":"44914","title":"哈哈哈","pv":"566","physical_label":"高压涂覆","time":"11:15"},{"id":"44912","title":"企业动态","pv":"564","physical_label":"共聚注塑","time":"11:14"}]
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
             * id : 44910
             * title : 噜啦啦
             * input_time : 09:56
             * pv : 544
             * physical_label : 低压拉丝
             * physical :
             */

            private String id;
            private String title;
            private String input_time;
            private String pv;
            private String physical_label;
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

            public String getPhysical() {
                return physical;
            }

            public void setPhysical(String physical) {
                this.physical = physical;
            }
        }

        public static class HotBean {
            /**
             * id : 44918
             * title : 独家解读
             * pv : 505
             * physical_label : 高压吹膜
             * time : 11:28
             */

            private String id;
            private String title;
            private String pv;
            private String physical_label;
            private String time;

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
        }
    }
}
