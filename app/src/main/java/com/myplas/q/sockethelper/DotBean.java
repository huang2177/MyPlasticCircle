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
     * data : {"to":"53402","value":2,"key":"unread_supply_and_demand"}
     * timestamp : 1509087802
     * err : 0
     * user_id : 53402
     */

    private String from;
    private String type;
    private DataBean data;
    private String timestamp;
    private String err;
    private String user_id;

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
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

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getErr() {
        return err;
    }

    public void setErr(String err) {
        this.err = err;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public static class DataBean {
        /**
         * to : 53402
         * value : 2
         * key : unread_supply_and_demand
         */

        private String to;
        private String value;
        private String key;

        public String getTo() {
            return to;
        }

        public void setTo(String to) {
            this.to = to;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }

        public String getKey() {
            return key;
        }

        public void setKey(String key) {
            this.key = key;
        }
    }
}
