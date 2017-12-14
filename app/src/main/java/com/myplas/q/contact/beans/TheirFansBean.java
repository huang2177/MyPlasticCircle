package com.myplas.q.contact.beans;

import java.util.List;

/**
 * 作者:huangshuang
 * 事件 2017/10/19 0019.
 * 邮箱： 15378412400@163.com
 */

public class TheirFansBean {

    /**
     * err : 0
     * data : [{"user_id":"53405","c_name":"上海中信有限公司","is_pass":0,"mobile":"1592121****","name":"小胖","thumb":"http://pic.myplas.com/upload/17/09/05/59ae0cd00dd3e.PNG","type":"1"},{"user_id":"53397","c_name":"上海测试科技","is_pass":0,"mobile":"1881739****","name":"测试","thumb":"http://pic.myplas.com/upload/17/10/17/59e5a50c68b53.jpg","type":"2"},{"user_id":"53402","c_name":"上海中晨电子商务股份有限公司","is_pass":0,"mobile":"1537841****","name":"hh","thumb":"http://pic.myplas.com/upload/17/09/06/59af96a6a157f.PNG","type":"1"},{"user_id":"53453","c_name":"上海晨达物流有限公司","is_pass":0,"mobile":"1881739****","name":"娟娟","thumb":"http://pic.myplas.com/upload/17/09/01/59a93aba83fc0.PNG","type":"4"}]
     * count : 4
     */

    private String err;
    private String count;
    private List<DataBean> data;

    public String getErr() {
        return err;
    }

    public void setErr(String err) {
        this.err = err;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * user_id : 53405
         * c_name : 上海中信有限公司
         * is_pass : 0
         * mobile : 1592121****
         * name : 小胖
         * thumb : http://pic.myplas.com/upload/17/09/05/59ae0cd00dd3e.PNG
         * type : 1
         */

        private String user_id;
        private String c_name;
        private String is_pass;
        private String mobile;
        private String name;
        private String thumb;
        private String type;
        private String merge_three;

        public void setMerge_three(String merge_three) {
            this.merge_three = merge_three;
        }

        public String getMerge_three() {
            return merge_three;
        }
        public String getUser_id() {
            return user_id;
        }

        public void setUser_id(String user_id) {
            this.user_id = user_id;
        }

        public String getC_name() {
            return c_name;
        }

        public void setC_name(String c_name) {
            this.c_name = c_name;
        }

        public String getIs_pass() {
            return is_pass;
        }

        public void setIs_pass(String is_pass) {
            this.is_pass = is_pass;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getThumb() {
            return thumb;
        }

        public void setThumb(String thumb) {
            this.thumb = thumb;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }
    }
}
