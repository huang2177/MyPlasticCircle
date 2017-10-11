package com.myplas.q.myinfo.beans;

import java.util.List;

/**
 * 编写： 黄双
 * 电话：15378412400
 * 邮箱：15378412400@163.com
 * 时间：2017/3/29 17:31
 */
public class MySupDemBean {
    /**
     * err : 0
     * data : [{"id":"110885","p_id":"0","user_id":"53402","model":"","unit_price":"0.00","store_house":"","cargo_type":"1","f_name":"","type":"1","c_name":"上海中晨电子商务股份有限公司","hui_count":"1","chu_count":"3","name":"hh","img":"http://pic.myplas.com/myapp/img/icon_purchase.png"},{"id":"110855","p_id":"0","user_id":"53402","model":"","unit_price":"0.00","store_house":"","cargo_type":"1","f_name":"","type":"1","c_name":"上海中晨电子商务股份有限公司","hui_count":"1","chu_count":"0","name":"hh","img":"http://pic.myplas.com/myapp/img/icon_purchase.png"},{"id":"110848","p_id":"0","user_id":"53402","model":"","unit_price":"0.00","store_house":"","cargo_type":"1","f_name":"","type":"1","c_name":"上海中晨电子商务股份有限公司","hui_count":"1","chu_count":"0","name":"hh","img":"http://pic.myplas.com/myapp/img/icon_purchase.png"}]
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
         * id : 110885
         * p_id : 0
         * user_id : 53402
         * model :
         * unit_price : 0.00
         * store_house :
         * cargo_type : 1
         * f_name :
         * type : 1
         * c_name : 上海中晨电子商务股份有限公司
         * hui_count : 1
         * chu_count : 3
         * name : hh
         * img : http://pic.myplas.com/myapp/img/icon_purchase.png
         */

        private String id;
        private String p_id;
        private String user_id;
        private String model;
        private String unit_price;
        private String store_house;
        private String cargo_type;
        private String f_name;
        private String type;
        private String c_name;
        private String hui_count;
        private String chu_count;
        private String name;
        private String img;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getP_id() {
            return p_id;
        }

        public void setP_id(String p_id) {
            this.p_id = p_id;
        }

        public String getUser_id() {
            return user_id;
        }

        public void setUser_id(String user_id) {
            this.user_id = user_id;
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

        public String getStore_house() {
            return store_house;
        }

        public void setStore_house(String store_house) {
            this.store_house = store_house;
        }

        public String getCargo_type() {
            return cargo_type;
        }

        public void setCargo_type(String cargo_type) {
            this.cargo_type = cargo_type;
        }

        public String getF_name() {
            return f_name;
        }

        public void setF_name(String f_name) {
            this.f_name = f_name;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getC_name() {
            return c_name;
        }

        public void setC_name(String c_name) {
            this.c_name = c_name;
        }

        public String getHui_count() {
            return hui_count;
        }

        public void setHui_count(String hui_count) {
            this.hui_count = hui_count;
        }

        public String getChu_count() {
            return chu_count;
        }

        public void setChu_count(String chu_count) {
            this.chu_count = chu_count;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getImg() {
            return img;
        }

        public void setImg(String img) {
            this.img = img;
        }
    }
}
