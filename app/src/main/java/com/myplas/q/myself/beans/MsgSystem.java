package com.myplas.q.myself.beans;

import java.util.List;

/**
 * @author Huangshuang  2018/5/2 0002
 */

public class MsgSystem {


    /**
     * code : 0
     * data : [{"id":2642,"type":9,"msg":"您的头条会员将于2018-06-01到期~","input_time":"5月2日 15:33"},{"id":2643,"type":9,"msg":"您的头条会员将于2018-06-01到期~","input_time":"5月2日 15:33"}]
     */

    private String code;
    private List<DataBean> data;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * id : 2642
         * type : 9
         * msg : 您的头条会员将于2018-06-01到期~
         * input_time : 5月2日 15:33
         */

        private String id;
        private String type;
        private String msg;
        private String input_time;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getMsg() {
            return msg;
        }

        public void setMsg(String msg) {
            this.msg = msg;
        }

        public String getInput_time() {
            return input_time;
        }

        public void setInput_time(String input_time) {
            this.input_time = input_time;
        }
    }
}
