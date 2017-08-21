package com.myplas.q.headlines.bean;

import java.util.List;

/**
 * 编写： 黄双
 * 电话：15378412400
 * 邮箱：15378412400@163.com
 * 时间：2017/4/date_selected 16:41
 */
public class SucribleDetailBean {



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

        public static class SubscribeBean {
            /**
             * id : 35951
             * title : 神华包头PE装置生产动态
             * cate_id : 11
             * input_time : 2017-06-02
             * type : PE
             * cate_name : 装置动态
             */

            private String id;
            private String title;
            private String cate_id;
            private String input_time;
            private String type;
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

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
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
