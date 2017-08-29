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
     * data : []
     * show_msg :
     * banner : [{"id":"44829","title":"安徽华塑PVC最新报价","img":"http://pic.myplas.com/upload/17/08/25/599fe56f97e3c.jpg"},{"id":"44828","title":"7月25聚烯烃石化库存","img":"http://pic.myplas.com/upload/17/08/25/599fe56f97e3c.jpg"},{"id":"44827","title":"中煤蒙大PE装置动态","img":"http://pic.myplas.com/upload/17/08/25/599fe56f97e3c.jpg"}]
     */

    private int err;
    private String show_msg;
    private List<DataBean> data;
    private List<BannerBean> banner;

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

    public List<BannerBean> getBanner() {
        return banner;
    }

    public void setBanner(List<BannerBean> banner) {
        this.banner = banner;
    }

    public static class BannerBean {
        /**
         * id : 44829
         * title : 安徽华塑PVC最新报价
         * img : http://pic.myplas.com/upload/17/08/25/599fe56f97e3c.jpg
         */

        private String id;
        private String title;
        private String img;

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

        public String getImg() {
            return img;
        }

        public void setImg(String img) {
            this.img = img;
        }
    }
    public static class DataBean {
        /**
         * id : 29334
         * title : 上游早报：原油及PE单体4月27日收盘价格
         * description : 上游早报：原油及PE单体4月19日收盘价格...
         * cate_id : 2
         * author :
         * input_time : 2017-04-28
         * type : PE
         * pv : 753
         * cate_name : 塑料上游
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
