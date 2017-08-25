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
         * id : 44724
         * title : 上游早报：原油及PE单体7月24日收盘价格
         * cate_id : 2
         * input_time : 2017-07-25
         * source_from : 本站原创
         * author : 中晨
         * pv : 3
         * true_pv : 3
         * type : pe
         * lastOne : 44723
         * nextOne : 44848
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
             * id : 44864
             * title : 测试999
             * input_time : 2017-08-23
             * pv : 590
             * physical_label : 高压重包
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
             * id : 44875
             * title : 大师傅士大夫但是
             * pv : 533
             * physical_label : 线型
             * time : 2017-08-23
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
