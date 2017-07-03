package com.myplas.q.myinfo.beans;

import java.util.List;

/**
 * 编写： 黄双
 * 电话：15378412400
 * 邮箱：15378412400@163.com
 * 时间：2017/3/29 17:31
 */
public class MyCommentBean {


    /**
     * err : 0
     * data : [{"id":"83875","pzid":"1453","p_id":"0","user_id":"3858","model":null,"unit_price":"0.00","store_house":"","f_name":null,"type":"date_selected","content":"塑料原料","input_time":"03-27 13:54","says":[{"id":"1453","rev_id":"3858","user_id":"40377","is_read":"date_selected","content":"需要什么料？","input_time":"03-27 20:12","rev_name":"李一帆","user_name":"黄先生"}],"deal_price":"","b_and_s":"","contents":"塑料原料"},{"id":"83866","pzid":"1451","p_id":"0","user_id":"3858","model":null,"unit_price":"0.00","store_house":"","f_name":null,"type":"date_selected","content":"塑料原料","input_time":"03-27 13:47","says":[{"id":"1451","rev_id":"3858","user_id":"32788","is_read":"date_selected","content":"什么料？","input_time":"03-27 14:25","rev_name":"李一帆","user_name":"戴先生"}],"deal_price":"","b_and_s":"","contents":"塑料原料"},{"id":"20677","pzid":"28","p_id":"5003","user_id":"3858","model":"7000F","unit_price":"0.00","store_house":"","f_name":"伊朗","type":"2","content":"1123","input_time":"09-19 16:34","says":[{"id":"28","rev_id":"3858","user_id":"9266","is_read":"date_selected","content":"祝大家节日快乐[微笑]","input_time":"09-20 11:29","rev_name":"李一帆","user_name":"成平"},{"id":"607","rev_id":"9266","user_id":"3858","is_read":"date_selected","content":"1111","input_time":"10-26 11:25","rev_name":"成平","user_name":"李一帆"}],"deal_price":"","b_and_s":[false,false],"contents":"价格0.00元左右/7000F/伊朗//1123"},{"id":"19879","pzid":"19","p_id":"3444","user_id":"3858","model":"7000F","unit_price":"0.00","store_house":"","f_name":"伊朗石化","type":"2","content":"出售","input_time":"09-14 14:52","says":[{"id":"19","rev_id":"3858","user_id":"9270","is_read":"date_selected","content":"阳澄湖大闸蟹有卖 欢迎求购 998","input_time":"09-14 14:58","rev_name":"李一帆","user_name":"郭世鹏"},{"id":"839","rev_id":"9270","user_id":"3858","is_read":"date_selected","content":"123","input_time":"12-12 10:05","rev_name":"郭世鹏","user_name":"李一帆"}],"deal_price":"10600.00","b_and_s":[{"id":"9215","name":"徐经理","mobile":"15267840445","user_id":"9729","c_name":"上海苍穹投资股份有限公司"},{"id":"43511","name":"韦一","mobile":"13166219651","user_id":"28849","c_name":"上海中晨电子商务股份有限公司"}],"contents":"价格0.00元左右/7000F/伊朗石化//出售"}]
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
         * id : 83875
         * pzid : 1453
         * p_id : 0
         * user_id : 3858
         * model : null
         * unit_price : 0.00
         * store_house :
         * f_name : null
         * type : date_selected
         * content : 塑料原料
         * input_time : 03-27 13:54
         * says : [{"id":"1453","rev_id":"3858","user_id":"40377","is_read":"date_selected","content":"需要什么料？","input_time":"03-27 20:12","rev_name":"李一帆","user_name":"黄先生"}]
         * deal_price :
         * b_and_s :
         * contents : 塑料原料
         */

        private String id;
        private String pzid;
        private String p_id;
        private String user_id;
        private Object model;
        private String unit_price;
        private String store_house;
        private Object f_name;
        private String type;
        private String content;
        private String input_time;
        private String deal_price;

        private String contents;
        private List<SaysBean> says;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getPzid() {
            return pzid;
        }

        public void setPzid(String pzid) {
            this.pzid = pzid;
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

        public Object getF_name() {
            return f_name;
        }

        public void setF_name(Object f_name) {
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

        public String getDeal_price() {
            return deal_price;
        }

        public void setDeal_price(String deal_price) {
            this.deal_price = deal_price;
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

        public static class SaysBean {
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
