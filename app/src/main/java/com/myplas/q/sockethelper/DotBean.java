package com.myplas.q.sockethelper;

/**
 * 作者:huangshuang
 * 事件 2017/10/26 0026.
 * 邮箱： 15378412400@163.com
 */

public class DotBean {


    /**
     * from : -1
     * type : system
     * data : {"to":"53402","key":"unread_supply_and_demand","value":3}
     * timestamp : 1509003998
     * err : 0
     */

    private int from;
    private String type;
    private DataBean data;
    private int timestamp;
    private int err;

    public int getFrom() {
        return from;
    }

    public void setFrom(int from) {
        this.from = from;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public int getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(int timestamp) {
        this.timestamp = timestamp;
    }

    public int getErr() {
        return err;
    }

    public void setErr(int err) {
        this.err = err;
    }

    public static class DataBean {
        /**
         * to : 53402
         * key : unread_supply_and_demand
         * value : 3
         */

        private String to;
        private String key;
        private String value;

        public String getTo() {
            return to;
        }

        public void setTo(String to) {
            this.to = to;
        }

        public String getKey() {
            return key;
        }

        public void setKey(String key) {
            this.key = key;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }
    }
}
