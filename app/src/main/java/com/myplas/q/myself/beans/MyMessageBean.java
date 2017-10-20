package com.myplas.q.myself.beans;

import java.util.List;

/**
 * 编写： 黄双
 * 电话：15378412400
 * 邮箱：15378412400@163.com
 * 时间：2017/3/29 14:39
 */
public class MyMessageBean {

    /**
     * err : 0
     * data : [{"msg":"您有新消息,\u201chh\u201d回复了您的信息！","input_time":"8月21日 10:30","type":3},{"msg":"暂无消息哦！","input_time":"","type":2},{"msg":"您关注的\u201c李一帆\u201d发布新的求购信息啦！","input_time":"8月18日 10:55","count":"64","type":1}]
     */

    private int err;
    private List<DataBean> data;

    public int getErr() {
        return err;
    }

    public void setErr(int err) {
        this.err = err;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * msg : 您有新消息,“hh”回复了您的信息！
         * input_time : 8月21日 10:30
         * type : 3
         * count : 64
         */

        private String msg;
        private String input_time;
        private String type;
        private String count;

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

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getCount() {
            return count;
        }

        public void setCount(String count) {
            this.count = count;
        }
    }
}
