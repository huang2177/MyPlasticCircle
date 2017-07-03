package com.myplas.q.release.bean;

/**
 * 编写： 黄双
 * 电话：15378412400
 * 邮箱：15378412400@163.com
 * 时间：2017/4/6 14:34
 */
public class SecondPurBean {

    /**
     * err : 0
     * data : {"id":"75228","p_id":"8943","user_id":"3858","model":"PVC","unit_price":"9999.00","store_house":"上海","f_name":"上海","type":"date_selected","content":"","input_time":"1488001476","f_type":date_selected}
     */

    private int err;
    private DataBean data;

    public int getErr() {
        return err;
    }

    public void setErr(int err) {
        this.err = err;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * id : 75228
         * p_id : 8943
         * user_id : 3858
         * model : PVC
         * unit_price : 9999.00
         * store_house : 上海
         * f_name : 上海
         * type : date_selected
         * content :
         * input_time : 1488001476
         * f_type : date_selected
         */

        private String id;
        private String p_id;
        private String user_id;
        private String model;
        private String unit_price;
        private String store_house;
        private String f_name;
        private String type;
        private String content;
        private String input_time;
        private int f_type;

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

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getInput_time() {
            return input_time;
        }

        public void setInput_time(String input_time) {
            this.input_time = input_time;
        }

        public int getF_type() {
            return f_type;
        }

        public void setF_type(int f_type) {
            this.f_type = f_type;
        }
    }
}
