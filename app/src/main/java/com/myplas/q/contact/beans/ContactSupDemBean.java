package com.myplas.q.contact.beans;

import java.io.Serializable;
import java.util.List;

/**
 * 编写： 黄双
 * 电话：15378412400
 * 邮箱：15378412400@163.com
 * 时间：2017/3/30 17:05
 */
public class ContactSupDemBean implements Serializable {


    /**
     * err : 0
     * data : [{"id":15510,"thumb":"http://pic.myplas.com/upload/17/07/12/5965b7ff066cf.jpg","thumbqq":"http://pic.myplas.com/upload/17/07/12/5965b7ff066cf.jpg","p_id":8045,"user_id":2062,"model":"2119 ","unit_price":89000,"store_house":"上海","cargo_type":1,"f_name":"上海","type":2,"input_time":"10-27 15:28","hui_count":0,"chu_count":0},{"id":15509,"thumb":"http://pic.myplas.com/upload/17/07/12/5965b7ff066cf.jpg","thumbqq":"http://pic.myplas.com/upload/17/07/12/5965b7ff066cf.jpg","p_id":3444,"user_id":2062,"model":"7000F","unit_price":0,"store_house":"","cargo_type":1,"f_name":"伊朗石化","type":2,"input_time":"10-27 15:27","hui_count":0,"chu_count":1},{"id":15508,"thumb":"http://pic.myplas.com/upload/17/07/12/5965b7ff066cf.jpg","thumbqq":"http://pic.myplas.com/upload/17/07/12/5965b7ff066cf.jpg","p_id":3444,"user_id":2062,"model":"7000F","unit_price":8900,"store_house":"上海","cargo_type":1,"f_name":"伊朗石化","type":2,"input_time":"10-27 15:26","hui_count":0,"chu_count":0},{"id":15507,"thumb":"http://pic.myplas.com/upload/17/07/12/5965b7ff066cf.jpg","thumbqq":"http://pic.myplas.com/upload/17/07/12/5965b7ff066cf.jpg","p_id":3444,"user_id":2062,"model":"7000F","unit_price":0,"store_house":"","cargo_type":1,"f_name":"伊朗石化","type":2,"input_time":"10-27 15:23","hui_count":0,"chu_count":0},{"id":15435,"thumb":"http://pic.myplas.com/upload/17/07/12/5965b7ff066cf.jpg","thumbqq":"http://pic.myplas.com/upload/17/07/12/5965b7ff066cf.jpg","p_id":5180,"user_id":2062,"model":"5000S","unit_price":10500,"store_house":"成都","cargo_type":2,"f_name":"大庆石化","type":2,"input_time":"10-27 11:44","hui_count":2,"chu_count":3},{"id":429,"thumb":"http://pic.myplas.com/upload/17/07/12/5965b7ff066cf.jpg","thumbqq":"http://pic.myplas.com/upload/17/07/12/5965b7ff066cf.jpg","p_id":3327,"user_id":2062,"model":"7000F","unit_price":8900,"store_house":"宝山","cargo_type":2,"f_name":"伊朗","type":2,"input_time":"10-25 11:26","hui_count":3,"chu_count":2},{"id":428,"thumb":"http://pic.myplas.com/upload/17/07/12/5965b7ff066cf.jpg","thumbqq":"http://pic.myplas.com/upload/17/07/12/5965b7ff066cf.jpg","p_id":3461,"user_id":2062,"model":9001,"unit_price":10900,"store_house":"广州","cargo_type":2,"f_name":"台湾塑胶","type":2,"input_time":"10-25 10:48","hui_count":0,"chu_count":0},{"id":427,"thumb":"http://pic.myplas.com/upload/17/07/12/5965b7ff066cf.jpg","thumbqq":"http://pic.myplas.com/upload/17/07/12/5965b7ff066cf.jpg","p_id":3461,"user_id":2062,"model":9001,"unit_price":11000,"store_house":"上海","cargo_type":2,"f_name":"台湾塑胶","type":2,"input_time":"10-25 10:48","hui_count":0,"chu_count":0},{"id":426,"thumb":"http://pic.myplas.com/upload/17/07/12/5965b7ff066cf.jpg","thumbqq":"http://pic.myplas.com/upload/17/07/12/5965b7ff066cf.jpg","p_id":3595,"user_id":2062,"model":"BL3 ","unit_price":10500,"store_house":"宁波","cargo_type":2,"f_name":"伊朗石化","type":2,"input_time":"10-25 10:48","hui_count":0,"chu_count":0},{"id":423,"thumb":"http://pic.myplas.com/upload/17/07/12/5965b7ff066cf.jpg","thumbqq":"http://pic.myplas.com/upload/17/07/12/5965b7ff066cf.jpg","p_id":3595,"user_id":2062,"model":"BL3 ","unit_price":10500,"store_house":"宁波","cargo_type":2,"f_name":"伊朗石化","type":2,"input_time":"10-25 10:47","hui_count":0,"chu_count":0},{"id":425,"thumb":"http://pic.myplas.com/upload/17/07/12/5965b7ff066cf.jpg","thumbqq":"http://pic.myplas.com/upload/17/07/12/5965b7ff066cf.jpg","p_id":3461,"user_id":2062,"model":9001,"unit_price":10900,"store_house":"广州","cargo_type":2,"f_name":"台湾塑胶","type":2,"input_time":"10-25 10:47","hui_count":0,"chu_count":0},{"id":424,"thumb":"http://pic.myplas.com/upload/17/07/12/5965b7ff066cf.jpg","thumbqq":"http://pic.myplas.com/upload/17/07/12/5965b7ff066cf.jpg","p_id":3461,"user_id":2062,"model":9001,"unit_price":11000,"store_house":"上海","cargo_type":2,"f_name":"台湾塑胶","type":2,"input_time":"10-25 10:47","hui_count":0,"chu_count":0},{"id":373,"thumb":"http://pic.myplas.com/upload/17/07/12/5965b7ff066cf.jpg","thumbqq":"http://pic.myplas.com/upload/17/07/12/5965b7ff066cf.jpg","p_id":3461,"user_id":2062,"model":9001,"unit_price":10900,"store_house":"广州","cargo_type":2,"f_name":"台湾塑胶","type":2,"input_time":"10-24 14:51","hui_count":13,"chu_count":1},{"id":372,"thumb":"http://pic.myplas.com/upload/17/07/12/5965b7ff066cf.jpg","thumbqq":"http://pic.myplas.com/upload/17/07/12/5965b7ff066cf.jpg","p_id":3461,"user_id":2062,"model":9001,"unit_price":10900,"store_house":"广州","cargo_type":2,"f_name":"台湾塑胶","type":2,"input_time":"10-24 14:50","hui_count":0,"chu_count":0},{"id":366,"thumb":"http://pic.myplas.com/upload/17/07/12/5965b7ff066cf.jpg","thumbqq":"http://pic.myplas.com/upload/17/07/12/5965b7ff066cf.jpg","p_id":3461,"user_id":2062,"model":9001,"unit_price":11000,"store_house":"上海","cargo_type":2,"f_name":"台湾塑胶","type":2,"input_time":"10-24 14:48","hui_count":0,"chu_count":0}]
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
         * id : 15510
         * thumb : http://pic.myplas.com/upload/17/07/12/5965b7ff066cf.jpg
         * thumbqq : http://pic.myplas.com/upload/17/07/12/5965b7ff066cf.jpg
         * p_id : 8045
         * user_id : 2062
         * model : 2119 
         * unit_price : 89000
         * store_house : 上海
         * cargo_type : 1
         * f_name : 上海
         * type : 2
         * input_time : 10-27 15:28
         * hui_count : 0
         * chu_count : 0
         */

        private String id;
        private String thumb;
        private String thumbqq;
        private String p_id;
        private String user_id;
        private String model;
        private String unit_price;
        private String store_house;
        private String cargo_type;
        private String f_name;
        private String type;
        private String input_time;
        private String hui_count;
        private String chu_count;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getThumb() {
            return thumb;
        }

        public void setThumb(String thumb) {
            this.thumb = thumb;
        }

        public String getThumbqq() {
            return thumbqq;
        }

        public void setThumbqq(String thumbqq) {
            this.thumbqq = thumbqq;
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

        public String getInput_time() {
            return input_time;
        }

        public void setInput_time(String input_time) {
            this.input_time = input_time;
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
    }
}
