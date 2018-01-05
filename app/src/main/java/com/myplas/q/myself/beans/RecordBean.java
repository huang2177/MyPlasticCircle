package com.myplas.q.myself.beans;

import java.util.List;

/**
 * 编写： 黄双
 * 电话：15378412400
 * 邮箱：15378412400@163.com
 * 时间：2017/5/26 16:26
 */
public class RecordBean {


    /**
     * err : 0
     * data : [{"cost_points":250,"goods_id":19,"purchase_date":"2017-08-31 15:51","goods_num":0,"start_time":"1970-01-01~1970-01-01","category_name":"期货资讯","goods_name":"期货资讯包月卡","image":"http://pic.myplas.com/myapp/img/img_futures_information@2x.png","user_name":"hh","type":3},{"cost_points":1250,"goods_id":22,"purchase_date":"2017-08-30 17:23","goods_num":0,"start_time":"1970-01-01~1970-01-01","category_name":"独家解读","goods_name":"独家解读包月卡","image":"http://pic.myplas.com/myapp/img/img_exclusive_interpretation@2x.png","user_name":"hh","type":3},{"cost_points":590,"goods_id":21,"purchase_date":"2017-08-30 17:23","goods_num":0,"start_time":"1970-01-01~1970-01-01","category_name":"期刊报告","goods_name":"期刊报告包月卡","image":"http://pic.myplas.com/myapp/img/img_journal_report@2x.png","user_name":"hh","type":3},{"cost_points":590,"goods_id":21,"purchase_date":"2017-08-30 17:23","goods_num":0,"start_time":"1970-01-01~1970-01-01","category_name":"期刊报告","goods_name":"期刊报告包月卡","image":"http://pic.myplas.com/myapp/img/img_journal_report@2x.png","user_name":"hh","type":3},{"cost_points":590,"goods_id":21,"purchase_date":"2017-08-30 17:23","goods_num":0,"start_time":"1970-01-01~1970-01-01","category_name":"期刊报告","goods_name":"期刊报告包月卡","image":"http://pic.myplas.com/myapp/img/img_journal_report@2x.png","user_name":"hh","type":3},{"cost_points":590,"goods_id":21,"purchase_date":"2017-08-30 17:23","goods_num":0,"start_time":"1970-01-01~1970-01-01","category_name":"期刊报告","goods_name":"期刊报告包月卡","image":"http://pic.myplas.com/myapp/img/img_journal_report@2x.png","user_name":"hh","type":3},{"cost_points":250,"goods_id":15,"purchase_date":"2017-08-30 16:48","goods_num":0,"start_time":"1970-01-01~1970-01-01","category_name":"早盘预报","goods_name":"早盘预报包月卡","image":"http://pic.myplas.com/myapp/img/img_early_trading@2x.png","user_name":"hh","type":3},{"cost_points":250,"goods_id":22,"purchase_date":"2017-08-30 16:44","goods_num":0,"start_time":"1970-01-01~1970-01-01","category_name":"独家解读","goods_name":"独家解读包月卡","image":"http://pic.myplas.com/myapp/img/img_exclusive_interpretation@2x.png","user_name":"hh","type":3},{"cost_points":5000,"goods_id":22,"purchase_date":"2017-08-30 16:43","goods_num":0,"start_time":"1970-01-01~1970-01-01","category_name":"独家解读","goods_name":"独家解读包月卡","image":"http://pic.myplas.com/myapp/img/img_exclusive_interpretation@2x.png","user_name":"hh","type":3},{"cost_points":340,"goods_id":15,"purchase_date":"2017-08-29 14:14","goods_num":0,"start_time":"1970-01-01~1970-01-01","category_name":"早盘预报","goods_name":"早盘预报包月卡","image":"http://pic.myplas.com/myapp/img/img_early_trading@2x.png","user_name":"hh","type":3},{"cost_points":"100","goods_id":"12","purchase_date":"2017-08-25 13:54","goods_num":"1","start_time":"2017-08-25","contents":"pp","goods_name":"供求信息一天置顶卡","image":"http://pic.myplas.com/myapp/img/img_supply@2x.png","user_name":"hh","type":1},{"cost_points":"100","goods_id":"13","purchase_date":"2017-08-25 13:54","goods_num":"1","start_time":"2017-09-07","contents":"价格9980.00元左右///天津","goods_name":"通讯录一天置顶卡","image":"http://pic.myplas.com/myapp/img/img_mail_list@2x.png","user_name":"hh","type":2},{"cost_points":"100","goods_id":"13","purchase_date":"2017-08-25 13:51","goods_num":"1","start_time":"2017-09-08","contents":"价格9980.00元左右///天津","goods_name":"通讯录一天置顶卡","image":"http://pic.myplas.com/myapp/img/img_mail_list@2x.png","user_name":"hh","type":2}]
     */

    private List<DataBean> data;


    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * cost_points : 250
         * goods_id : 19
         * purchase_date : 2017-08-31 15:51
         * goods_num : 0
         * start_time : 1970-01-01~1970-01-01
         * category_name : 期货资讯
         * goods_name : 期货资讯包月卡
         * image : http://pic.myplas.com/myapp/img/img_futures_information@2x.png
         * user_name : hh
         * type : 3
         * contents : pp
         */

        private String cost_points;
        private String goods_id;
        private String purchase_date;
        private String goods_num;
        private String start_time;
        private String category_name;
        private String goods_name;
        private String image;
        private String user_name;
        private String type;
        private String contents;

        public String getCost_points() {
            return cost_points;
        }

        public void setCost_points(String cost_points) {
            this.cost_points = cost_points;
        }

        public String getGoods_id() {
            return goods_id;
        }

        public void setGoods_id(String goods_id) {
            this.goods_id = goods_id;
        }

        public String getPurchase_date() {
            return purchase_date;
        }

        public void setPurchase_date(String purchase_date) {
            this.purchase_date = purchase_date;
        }

        public String getGoods_num() {
            return goods_num;
        }

        public void setGoods_num(String goods_num) {
            this.goods_num = goods_num;
        }

        public String getStart_time() {
            return start_time;
        }

        public void setStart_time(String start_time) {
            this.start_time = start_time;
        }

        public String getCategory_name() {
            return category_name;
        }

        public void setCategory_name(String category_name) {
            this.category_name = category_name;
        }

        public String getGoods_name() {
            return goods_name;
        }

        public void setGoods_name(String goods_name) {
            this.goods_name = goods_name;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public String getUser_name() {
            return user_name;
        }

        public void setUser_name(String user_name) {
            this.user_name = user_name;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getContents() {
            return contents;
        }

        public void setContents(String contents) {
            this.contents = contents;
        }
    }
}
