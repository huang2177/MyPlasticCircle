package com.myplas.q.supdem.beans;

import java.util.List;

/**
 * 编写：黄双
 * 邮箱：15378412400@163.com
 * 时间： 2017/6/291724.
 */

public class SearchResultBean {


    /**
     * err : 0
     * list : [{"id":"24","c_name":"上海中信有限公司","store_house":"上海","user_id":"53405","f_name":"伊朗石化","cargo_type":"1","model":"7000F","unit_price":"6800.00","thumbqq":"http://pic.myplas.com/upload/17/09/05/59ae0cd00dd3e.PNG","thumb":"17/08/10/598c2e0a42725.jpg","qq":"2865085207","input_time":"1507875068","type":"1","qq_name":"","from":"1","name":"小胖"}]
     * total : 1
     * search : 7000F
     */

    private int err;
    private String total;
    private String search;
    private List<ListBean> list;

    public int getErr() {
        return err;
    }

    public void setErr(int err) {
        this.err = err;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getSearch() {
        return search;
    }

    public void setSearch(String search) {
        this.search = search;
    }

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    public static class ListBean {
        /**
         * id : 24
         * c_name : 上海中信有限公司
         * store_house : 上海
         * user_id : 53405
         * f_name : 伊朗石化
         * cargo_type : 1
         * model : 7000F
         * unit_price : 6800.00
         * thumbqq : http://pic.myplas.com/upload/17/09/05/59ae0cd00dd3e.PNG
         * thumb : 17/08/10/598c2e0a42725.jpg
         * qq : 2865085207
         * input_time : 1507875068
         * type : 1
         * qq_name :
         * from : 1
         * name : 小胖
         */

        private String id;
        private String c_name;
        private String store_house;
        private String user_id;
        private String f_name;
        private String cargo_type;
        private String model;
        private String unit_price;
        private String thumbqq;
        private String thumb;
        private String qq;
        private String input_time;
        private String type;
        private String qq_name;
        private String from;
        private String name;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getC_name() {
            return c_name;
        }

        public void setC_name(String c_name) {
            this.c_name = c_name;
        }

        public String getStore_house() {
            return store_house;
        }

        public void setStore_house(String store_house) {
            this.store_house = store_house;
        }

        public String getUser_id() {
            return user_id;
        }

        public void setUser_id(String user_id) {
            this.user_id = user_id;
        }

        public String getF_name() {
            return f_name;
        }

        public void setF_name(String f_name) {
            this.f_name = f_name;
        }

        public String getCargo_type() {
            return cargo_type;
        }

        public void setCargo_type(String cargo_type) {
            this.cargo_type = cargo_type;
        }

        public String getModel() {
            return model;
        }

        public void setModel(String model) {
            this.model = model;
        }

        public String getUnit_price() {
            return unit_price;
        }

        public void setUnit_price(String unit_price) {
            this.unit_price = unit_price;
        }

        public String getThumbqq() {
            return thumbqq;
        }

        public void setThumbqq(String thumbqq) {
            this.thumbqq = thumbqq;
        }

        public String getThumb() {
            return thumb;
        }

        public void setThumb(String thumb) {
            this.thumb = thumb;
        }

        public String getQq() {
            return qq;
        }

        public void setQq(String qq) {
            this.qq = qq;
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

        public String getQq_name() {
            return qq_name;
        }

        public void setQq_name(String qq_name) {
            this.qq_name = qq_name;
        }

        public String getFrom() {
            return from;
        }

        public void setFrom(String from) {
            this.from = from;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
