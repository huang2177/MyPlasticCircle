package com.myplas.q.myinfo.beans;

import java.io.Serializable;
import java.util.List;

/**
 * 编写： 黄双
 * 电话：15378412400
 * 邮箱：15378412400@163.com
 * 时间：2017/3/30 17:05
 */
public class PersonSupplyDemadBean implements Serializable{

    /**
     * err : 0
     * data : [{"id":"83875","p_id":"0","user_id":"3858","model":null,"unit_price":"0.00","store_house":"","f_name":"","input_time":"03-27 13:54","type":"date_selected","content":"塑料原料","name":"李一帆","is_pass":"0","c_name":"上海中晨电子商务","thumb":"http://statics.myplas.com/upload/17/03/28/58da3d016c98a.jpg","contents":"塑料原料","says":[{"id":"1453","rev_id":"3858","user_id":"40377","is_read":"date_selected","content":"需要什么料？","input_time":"03-27 20:12","rev_name":"李一帆","user_name":"黄先生"}]},{"id":"83868","p_id":"0","user_id":"3858","model":null,"unit_price":"0.00","store_house":"","f_name":"","input_time":"03-27 13:48","type":"date_selected","content":"塑料原料","name":"李一帆","is_pass":"0","c_name":"上海中晨电子商务","thumb":"http://statics.myplas.com/upload/17/03/28/58da3d016c98a.jpg","contents":"塑料原料","says":[]},{"id":"83867","p_id":"0","user_id":"3858","model":null,"unit_price":"0.00","store_house":"","f_name":"","input_time":"03-27 13:47","type":"date_selected","content":"塑料原料","name":"李一帆","is_pass":"0","c_name":"上海中晨电子商务","thumb":"http://statics.myplas.com/upload/17/03/28/58da3d016c98a.jpg","contents":"塑料原料","says":[]},{"id":"83866","p_id":"0","user_id":"3858","model":null,"unit_price":"0.00","store_house":"","f_name":"","input_time":"03-27 13:47","type":"date_selected","content":"塑料原料","name":"李一帆","is_pass":"0","c_name":"上海中晨电子商务","thumb":"http://statics.myplas.com/upload/17/03/28/58da3d016c98a.jpg","contents":"塑料原料","says":[{"id":"1451","rev_id":"3858","user_id":"32788","is_read":"date_selected","content":"什么料？","input_time":"03-27 14:25","rev_name":"李一帆","user_name":"戴先生"}]},{"id":"83864","p_id":"0","user_id":"3858","model":null,"unit_price":"0.00","store_house":"","f_name":"","input_time":"03-27 13:45","type":"date_selected","content":"看看看拉拉手机打开垃圾袋","name":"李一帆","is_pass":"0","c_name":"上海中晨电子商务","thumb":"http://statics.myplas.com/upload/17/03/28/58da3d016c98a.jpg","contents":"看看看拉拉手机打开垃圾袋","says":[]},{"id":"83863","p_id":"0","user_id":"3858","model":null,"unit_price":"0.00","store_house":"","f_name":"","input_time":"03-27 13:45","type":"date_selected","content":"看看看拉拉手机打开垃圾袋","name":"李一帆","is_pass":"0","c_name":"上海中晨电子商务","thumb":"http://statics.myplas.com/upload/17/03/28/58da3d016c98a.jpg","contents":"看看看拉拉手机打开垃圾袋","says":[]},{"id":"83862","p_id":"0","user_id":"3858","model":null,"unit_price":"0.00","store_house":"","f_name":"","input_time":"03-27 13:45","type":"date_selected","content":"看看看拉拉手机打开垃圾袋","name":"李一帆","is_pass":"0","c_name":"上海中晨电子商务","thumb":"http://statics.myplas.com/upload/17/03/28/58da3d016c98a.jpg","contents":"看看看拉拉手机打开垃圾袋","says":[]},{"id":"75228","p_id":"8943","user_id":"3858","model":"PVC","unit_price":"9999.00","store_house":"上海","f_name":"上海","input_time":"02-25 13:44","type":"date_selected","content":"","name":"李一帆","is_pass":"0","c_name":"上海中晨电子商务","thumb":"http://statics.myplas.com/upload/17/03/28/58da3d016c98a.jpg","contents":"价格9999.00元左右/PVC/上海/上海","says":[]},{"id":"24608","p_id":"3333","user_id":"3858","model":"2119","unit_price":"9000.00","store_house":"123","f_name":"伊朗","input_time":"09-28 13:53","type":"date_selected","content":"伊朗","name":"李一帆","is_pass":"0","c_name":"上海中晨电子商务","thumb":"http://statics.myplas.com/upload/17/03/28/58da3d016c98a.jpg","contents":"价格9000.00元左右/2119/伊朗/123/伊朗","says":[]}]
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

    public static class DataBean implements Serializable{
        /**
         * id : 83875
         * p_id : 0
         * user_id : 3858
         * model : null
         * unit_price : 0.00
         * store_house :
         * f_name :
         * input_time : 03-27 13:54
         * type : date_selected
         * content : 塑料原料
         * name : 李一帆
         * is_pass : 0
         * c_name : 上海中晨电子商务
         * thumb : http://statics.myplas.com/upload/17/03/28/58da3d016c98a.jpg
         * contents : 塑料原料
         * says : [{"id":"1453","rev_id":"3858","user_id":"40377","is_read":"date_selected","content":"需要什么料？","input_time":"03-27 20:12","rev_name":"李一帆","user_name":"黄先生"}]
         */

        private String id;
        private String p_id;
        private String user_id;
        private Object model;
        private String unit_price;
        private String store_house;
        private String f_name;
        private String input_time;
        private String type;
        private String content;
        private String name;
        private String is_pass;
        private String c_name;
        private String thumb;
        private String contents;
        private List<SaysBean> says;

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

        public Object getModel() {
            return model;
        }

        public void setModel(Object model) {
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

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getIs_pass() {
            return is_pass;
        }

        public void setIs_pass(String is_pass) {
            this.is_pass = is_pass;
        }

        public String getC_name() {
            return c_name;
        }

        public void setC_name(String c_name) {
            this.c_name = c_name;
        }

        public String getThumb() {
            return thumb;
        }

        public void setThumb(String thumb) {
            this.thumb = thumb;
        }

        public String getContents() {
            return contents;
        }

        public void setContents(String contents) {
            this.contents = contents;
        }

        public List<SaysBean> getSays() {
            return says;
        }

        public void setSays(List<SaysBean> says) {
            this.says = says;
        }

        public static class SaysBean implements Serializable{
            /**
             * id : 1453
             * rev_id : 3858
             * user_id : 40377
             * is_read : date_selected
             * content : 需要什么料？
             * input_time : 03-27 20:12
             * rev_name : 李一帆
             * user_name : 黄先生
             */

            private String id;
            private String rev_id;
            private String user_id;
            private String is_read;
            private String content;
            private String input_time;
            private String rev_name;
            private String user_name;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getRev_id() {
                return rev_id;
            }

            public void setRev_id(String rev_id) {
                this.rev_id = rev_id;
            }

            public String getUser_id() {
                return user_id;
            }

            public void setUser_id(String user_id) {
                this.user_id = user_id;
            }

            public String getIs_read() {
                return is_read;
            }

            public void setIs_read(String is_read) {
                this.is_read = is_read;
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

            public String getRev_name() {
                return rev_name;
            }

            public void setRev_name(String rev_name) {
                this.rev_name = rev_name;
            }

            public String getUser_name() {
                return user_name;
            }

            public void setUser_name(String user_name) {
                this.user_name = user_name;
            }
        }
    }
}
