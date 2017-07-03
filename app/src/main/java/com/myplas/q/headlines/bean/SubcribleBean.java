package com.myplas.q.headlines.bean;

import java.util.List;

/**
 * 编写： 黄双
 * 电话：15378412400
 * 邮箱：15378412400@163.com
 * 时间：2017/4/date_selected 15:46
 */
public class SubcribleBean {


    /**
     * err : 0
     * data : [{"id":"448","title":"9月独山子石化PE排产计划","description":"9月独山子石化PE装置排产计划如下：老低压装置计划1-17日产4801,18-30日产5420。老全...","cate_id":"27","author":"我的塑料网","input_time":"2016-09-23","type":"PE","pv":"580","cate_name":"独家解读"},{"id":"13956","title":"2016年11月PVC进口数据统计","description":"抓取聚氯乙烯原料的进口、出口等数据。...","cate_id":"29","author":"","input_time":"2016-12-27","type":"PVC","pv":"383","cate_name":"独家解读"},{"id":"375","title":"9月扬子石化PP排产计划","description":"【排产计划】9月份扬子石化PP排产计划...","cate_id":"27","author":"","input_time":"2016-09-23","type":"PP","pv":"583","cate_name":"独家解读"},{"id":"4935","title":"2016年9月PVC进口数据统计","description":"2016年9月PVC进口数据统计...","cate_id":"29","author":"","input_time":"2016-10-26","type":"PVC","pv":"612","cate_name":"独家解读"},{"id":"319","title":"2016年7月PVC进口数据统计","description":"2016年7月PVC进口数据统计...","cate_id":"29","author":"","input_time":"2016-09-23","type":"PVC","pv":"694","cate_name":"独家解读"},{"id":"381","title":"9月抚顺石化PP排产计划","description":"【排产计划】9月份抚顺石化PP排产计划...","cate_id":"27","author":"","input_time":"2016-09-23","type":"PP","pv":"567","cate_name":"独家解读"}]
     * show_msg : 更新了6条数据
     */

    private int err;
    private String show_msg;
    private List<DataBean> data;

    public int getErr() {
        return err;
    }

    public void setErr(int err) {
        this.err = err;
    }

    public String getShow_msg() {
        return show_msg;
    }

    public void setShow_msg(String show_msg) {
        this.show_msg = show_msg;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * id : 448
         * title : 9月独山子石化PE排产计划
         * description : 9月独山子石化PE装置排产计划如下：老低压装置计划1-17日产4801,18-30日产5420。老全...
         * cate_id : 27
         * author : 我的塑料网
         * input_time : 2016-09-23
         * type : PE
         * pv : 580
         * cate_name : 独家解读
         */

        private String id;
        private String title;
        private String description;
        private String cate_id;
        private String author;
        private String input_time;
        private String type;
        private String pv;
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

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getCate_id() {
            return cate_id;
        }

        public void setCate_id(String cate_id) {
            this.cate_id = cate_id;
        }

        public String getAuthor() {
            return author;
        }

        public void setAuthor(String author) {
            this.author = author;
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

        public String getPv() {
            return pv;
        }

        public void setPv(String pv) {
            this.pv = pv;
        }

        public String getCate_name() {
            return cate_name;
        }

        public void setCate_name(String cate_name) {
            this.cate_name = cate_name;
        }
    }
}
