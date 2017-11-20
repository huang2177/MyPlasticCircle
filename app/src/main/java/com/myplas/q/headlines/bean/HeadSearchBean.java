package com.myplas.q.headlines.bean;

import java.util.List;

/**
 * 作者:huangshuang
 * 事件 2017/9/27 0027.
 * 邮箱： 15378412400@163.com
 */

public class HeadSearchBean {

    /**
     * err : 0
     * data : [{"id":"50493","cateId":"13","title":"交易收评：贸易商今天啥都没干，就跟着别人涨价了！","type":"低压注塑","input_time":"2017-09-01","pv":"1531","is_free":0},{"id":"50492","cateId":"13","title":"日评：涨价不可怕！可怕的是涨价还没货","type":"均聚拉丝","input_time":"2017-09-01","pv":"1182","is_free":0},{"id":"44419","cateId":"32","title":"神华PE竞拍结果（20170721）","type":"高压吹膜","input_time":"2017-07-21","pv":"1798","is_free":0},{"id":"44228","cateId":"32","title":"神华PE竞拍结果（20170720）","type":"高压吹膜","input_time":"2017-07-20","pv":"1669","is_free":0},{"id":"29526","cateId":"15","title":"月报：期货反弹涨逾4% PP月末又见利好","type":"","input_time":"2017-04-28","pv":"6483","is_free":0},{"id":"25131","cateId":"53","title":"期货周报：美元加息预期，塑料期货步步走低","type":"","input_time":"2017-04-05","pv":"891","is_free":0},{"id":"24695","cateId":"15","title":"本月收评：塑市整体势弱，空头压制难言反弹","type":"","input_time":"2017-03-31","pv":"5231","is_free":0},{"id":"21984","cateId":"14","title":"本周收评：期货/石化联手唱空 塑市跌势难止","type":"","input_time":"2017-03-10","pv":"4453","is_free":0},{"id":"21981","cateId":"76","title":"期货周报：美元加息预期，塑料期货步步走低","type":"","input_time":"2017-03-10","pv":"1393","is_free":0},{"id":"13603","cateId":"14","title":"周报：西北氯碱联合体保价，PVC市场深跌后反弹","type":"","input_time":"2016-12-23","pv":"2490","is_free":0}]
     * total : 14
     */

    private int err;
    private String total;
    private List<DataBean> data;

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

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * id : 50493
         * cateId : 13
         * title : 交易收评：贸易商今天啥都没干，就跟着别人涨价了！
         * type : 低压注塑
         * input_time : 2017-09-01
         * pv : 1531
         * is_free : 0
         */

        private String id;
        private String cate_id;
        private String title;
        private String type;
        private String input_time;
        private String pv;
        private String is_free;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getCate_id() {
            return cate_id;
        }

        public void setCate_id(String cate_id) {
            this.cate_id = cate_id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
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

        public String getIs_free() {
            return is_free;
        }

        public void setIs_free(String is_free) {
            this.is_free = is_free;
        }
    }
}
