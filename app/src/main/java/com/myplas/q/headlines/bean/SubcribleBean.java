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
     * code : 0
     * news : [{"id":59397,"title":"666","cate_id":11,"input_time":"1月4日 11:35","pv":564,"is_free":0,"physical_label":"其他"},{"id":59396,"title":"999","cate_id":9,"input_time":"1月4日 11:34","pv":567,"is_free":1,"physical_label":"高压涂覆"},{"id":59379,"title":"12月PE价格走势","cate_id":20,"input_time":"2017-12-21","pv":521,"is_free":1,"physical_label":"茂金属"},{"id":59377,"title":"重大news!!!","cate_id":9,"input_time":"2017-12-21","pv":566,"is_free":1,"physical_label":"高压吹膜"},{"id":59376,"title":"重大news!!!","cate_id":9,"input_time":"2017-12-21","pv":612,"is_free":1,"physical_label":"高压吹膜"},{"id":59375,"title":"重大news!!!","cate_id":9,"input_time":"2017-12-21","pv":531,"is_free":1,"physical_label":"高压吹膜"},{"id":59374,"title":"重大news!!!","cate_id":9,"input_time":"2017-12-21","pv":547,"is_free":1,"physical_label":"共聚注塑"},{"id":59373,"title":"重大news!!!","cate_id":9,"input_time":"2017-12-21","pv":595,"is_free":1,"physical_label":"共聚注塑"},{"id":59372,"title":"重大news!!!","cate_id":9,"input_time":"2017-12-21","pv":574,"is_free":1,"physical_label":"高压吹膜"},{"id":59371,"title":"cece","cate_id":2,"input_time":"2017-12-18","pv":579,"is_free":1,"physical_label":"高压涂覆"}]
     * total_found :
     * banners : []
     */

    private String code;
    private String total_found;
    private List<NewsBean> news;
    private List<BannerBean> banners;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getTotal_found() {
        return total_found;
    }

    public void setTotal_found(String total_found) {
        this.total_found = total_found;
    }

    public List<NewsBean> getNews() {
        return news;
    }

    public void setNews(List<NewsBean> news) {
        this.news = news;
    }

    public List<BannerBean> getBanners() {
        return banners;
    }

    public void setBanners(List<BannerBean> banners) {
        this.banners = banners;
    }

    public static class NewsBean {
        /**
         * id : 59397
         * title : 666
         * cate_id : 11
         * input_time : 1月4日 11:35
         * pv : 564
         * is_free : 0
         * physical_label : 其他
         */

        private String id;
        private String title;
        private String cate_id;
        private String input_time;
        private String pv;
        private String is_free;
        private String physical_label;

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

        public String getCate_id() {
            return cate_id;
        }

        public void setCate_id(String cate_id) {
            this.cate_id = cate_id;
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

        public String getPhysical_label() {
            return physical_label;
        }

        public void setPhysical_label(String physical_label) {
            this.physical_label = physical_label;
        }
    }
    public static class BannerBean {
        /**
         * id : 44969
         * title : 中晨塑说：看了那么多环保消息，塑料人，你该看基本面了
         * img : http://pic.myplas.com/upload/17/09/12/59b73ef04af59.jpg
         */

        private String id;
        private String title;
        private String img;
        private String is_free;

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

        public String getIs_free() {
            return is_free;
        }

        public void setIs_free(String is_free) {
            this.is_free = is_free;
        }
    }
}
