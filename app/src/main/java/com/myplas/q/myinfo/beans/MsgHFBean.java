package com.myplas.q.myinfo.beans;

import java.util.List;

/**
 * 作者：  黄双
 * 事件 2017/8/31 0031.
 * 邮箱： 15378412400@163.com
 */

public class MsgHFBean {


    /**
     * err : 0
     * count : 1
     * data : [{"id":"1610","user_id":"53402","name":"hh","c_name":"上海中晨电子商务股份有限公司","mobile":"15378412400","type":"1","hui_content":"还好","model":"","fa_content":"pp","hui_time":"8月21日 10:30","fa_time":"8月18日 15:28","f_name":"","detail_url":"/plasticzone/plastic#/releasedetail?id=1610&userid=53402&tab=2"}]
     */

    private int err;
    private String count;
    private List<DataBean> data;

    public int getErr() {
        return err;
    }

    public void setErr(int err) {
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
         * id : 1610
         * user_id : 53402
         * name : hh
         * c_name : 上海中晨电子商务股份有限公司
         * mobile : 15378412400
         * type : 1
         * hui_content : 还好
         * model :
         * fa_content : pp
         * hui_time : 8月21日 10:30
         * fa_time : 8月18日 15:28
         * f_name :
         * detail_url : /plasticzone/plastic#/releasedetail?id=1610&userid=53402&tab=2
         */

        private String id;
        private String user_id;
        private String name;
        private String c_name;
        private String mobile;
        private String type;
        private String hui_content;
        private String model;
        private String fa_content;
        private String hui_time;
        private String fa_time;
        private String f_name;
        private String detail_url;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getUser_id() {
            return user_id;
        }

        public void setUser_id(String user_id) {
            this.user_id = user_id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getC_name() {
            return c_name;
        }

        public void setC_name(String c_name) {
            this.c_name = c_name;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getHui_content() {
            return hui_content;
        }

        public void setHui_content(String hui_content) {
            this.hui_content = hui_content;
        }

        public String getModel() {
            return model;
        }

        public void setModel(String model) {
            this.model = model;
        }

        public String getFa_content() {
            return fa_content;
        }

        public void setFa_content(String fa_content) {
            this.fa_content = fa_content;
        }

        public String getHui_time() {
            return hui_time;
        }

        public void setHui_time(String hui_time) {
            this.hui_time = hui_time;
        }

        public String getFa_time() {
            return fa_time;
        }

        public void setFa_time(String fa_time) {
            this.fa_time = fa_time;
        }

        public String getF_name() {
            return f_name;
        }

        public void setF_name(String f_name) {
            this.f_name = f_name;
        }

        public String getDetail_url() {
            return detail_url;
        }

        public void setDetail_url(String detail_url) {
            this.detail_url = detail_url;
        }
    }
}
