package com.myplas.q.headlines.bean;

import java.util.List;

/**
 * 作者:huangshuang
 * 事件 2017/9/27 0027.
 * 邮箱： 15378412400@163.com
 */

public class HeadSearchBean {


    /**
     * code : 0
     * news : [{"id":25669,"title":"PE塑料震荡收涨 建议依托五日线做多","cate_id":21,"input_time":"2017-4-7","pv":4098,"is_free":0,"physical_label":""},{"id":25664,"title":"本周收评：上涨or下跌，塑料魔鬼般的步伐","cate_id":12,"input_time":"2017-4-7","pv":6052,"is_free":0,"physical_label":""},{"id":25658,"title":"周报：多方利好支撑  PE市场否极泰来","cate_id":12,"input_time":"2017-4-7","pv":3825,"is_free":0,"physical_label":""},{"id":25602,"title":"兰州石化PE装置生产动态","cate_id":11,"input_time":"2017-4-7","pv":3425,"is_free":0,"physical_label":""},{"id":25571,"title":"神华PE竞拍结果（20170407）","cate_id":22,"input_time":"2017-4-7","pv":1484,"is_free":0,"physical_label":""},{"id":25570,"title":"神华PE竞拍结果（20170407）","cate_id":6,"input_time":"2017-4-7","pv":1459,"is_free":0,"physical_label":""},{"id":25563,"title":"广州PE塑料市场价格小幅上涨","cate_id":8,"input_time":"2017-4-7","pv":1481,"is_free":0,"physical_label":""},{"id":25556,"title":"武汉PE塑料价格部分小涨","cate_id":8,"input_time":"2017-4-7","pv":1441,"is_free":0,"physical_label":""},{"id":25489,"title":"独山子石化装置生产动态","cate_id":11,"input_time":"2017-4-7","pv":3596,"is_free":0,"physical_label":""},{"id":25476,"title":"四川石化PE装置生产动态","cate_id":7,"input_time":"2017-4-7","pv":1362,"is_free":0,"physical_label":""},{"id":25449,"title":"多伦煤制烯烃项目复产工作最新进展","cate_id":19,"input_time":"2017-4-7","pv":1701,"is_free":0,"physical_label":""},{"id":25448,"title":"燕山石化低压1线装置转产动态","cate_id":11,"input_time":"2017-4-7","pv":1431,"is_free":0,"physical_label":""},{"id":25414,"title":"交易收评：带动因素不同，塑料市场走势不一","cate_id":12,"input_time":"2017-4-6","pv":5140,"is_free":0,"physical_label":""},{"id":25405,"title":"日评：期货和石化合力助推  PE市场能否如虎添翼？","cate_id":12,"input_time":"2017-4-6","pv":3690,"is_free":0,"physical_label":""},{"id":25332,"title":"神华PE竞拍结果（20170406）","cate_id":6,"input_time":"2017-4-6","pv":1184,"is_free":0,"physical_label":""}]
     * total_found : 1000
     */

    private String code;
    private String total_found;
    private List<NewsBean> news;

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

    public static class NewsBean {
        /**
         * id : 25669
         * title : PE塑料震荡收涨 建议依托五日线做多
         * cate_id : 21
         * input_time : 2017-4-7
         * pv : 4098
         * is_free : 0
         * physical_label : 
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
}
