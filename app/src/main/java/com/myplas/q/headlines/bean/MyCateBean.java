package com.myplas.q.headlines.bean;

import java.io.Serializable;
import java.util.List;

/**
 * 编写： 黄双
 * 电话：15378412400
 * 邮箱：15378412400@163.com
 * 时间：2017/4/5 13:58
 */
public class MyCateBean implements Serializable{
    /**
     * err : 0
     * data : {"subscribe":["2","1"],"unconcealed_subscribe":[2,20,21,26],"property":["1","2"]}
     */

    private int err;
    private DataBean data;

    public int getErr() {
        return err;
    }

    public void setErr(int err) {
        this.err = err;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean implements Serializable{
        private List<String> subscribe;
        private List<String> unconcealed_subscribe;
        private List<String> property;

        public List<String> getSubscribe() {
            return subscribe;
        }

        public void setSubscribe(List<String> subscribe) {
            this.subscribe = subscribe;
        }

        public List<String> getUnconcealed_subscribe() {
            return unconcealed_subscribe;
        }

        public void setUnconcealed_subscribe(List<String> unconcealed_subscribe) {
            this.unconcealed_subscribe = unconcealed_subscribe;
        }

        public List<String> getProperty() {
            return property;
        }

        public void setProperty(List<String> property) {
            this.property = property;
        }
    }
}
