package com.myplas.q.myinfo.beans;

import java.util.List;

/**
 * 编写： 黄双
 * 电话：15378412400
 * 邮箱：15378412400@163.com
 * 时间：2017/3/28 14:41
 */
public class IntegralBean {


    /**
     * err : 0
     * info : [{"id":"12","cate_id":"9","thumb":"http://pic.myplas.com/upload/17/04/07/58e759146ce08.gif","name":"单条供求信息一天置顶卡","points":"100","myMsg":[{"id":"50254","p_id":"5084","user_id":"3858","model":"7000F","unit_price":"9000.00","store_house":"上海","f_name":"上海","input_time":"04-08 11:07","type":"2","content":"ddddd","name":"李一帆","c_name":"上海中晨电子商务","is_pass":"0","thumb":"http://pic.myplas.com/upload/17/03/29/58db68941ae4e.jpg","contents":"价格9000.00元左右/7000F/上海/上海/ddddd","saysCount":0,"deliverPriceCount":"0"},{"id":"50246","p_id":"0","user_id":"3858","model":null,"unit_price":"0.00","store_house":"","f_name":null,"input_time":"04-07 21:15","type":"date_selected","content":"111111","name":"李一帆","c_name":"上海中晨电子商务","is_pass":"0","thumb":"http://pic.myplas.com/upload/17/03/29/58db68941ae4e.jpg","contents":"111111","saysCount":0,"deliverPriceCount":"0"},{"id":"50243","p_id":"0","user_id":"3858","model":null,"unit_price":"0.00","store_house":"","f_name":null,"input_time":"04-07 19:16","type":"2","content":"ddfffff","name":"李一帆","c_name":"上海中晨电子商务","is_pass":"0","thumb":"http://pic.myplas.com/upload/17/03/29/58db68941ae4e.jpg","contents":"ddfffff","saysCount":0,"deliverPriceCount":"0"},{"id":"50242","p_id":"0","user_id":"3858","model":null,"unit_price":"0.00","store_house":"","f_name":null,"input_time":"04-07 19:15","type":"date_selected","content":"11111155555","name":"李一帆","c_name":"上海中晨电子商务","is_pass":"0","thumb":"http://pic.myplas.com/upload/17/03/29/58db68941ae4e.jpg","contents":"11111155555","saysCount":0,"deliverPriceCount":"0"},{"id":"50241","p_id":"0","user_id":"3858","model":null,"unit_price":"0.00","store_house":"","f_name":null,"input_time":"04-07 19:06","type":"2","content":"ddddd","name":"李一帆","c_name":"上海中晨电子商务","is_pass":"0","thumb":"http://pic.myplas.com/upload/17/03/29/58db68941ae4e.jpg","contents":"ddddd","saysCount":0,"deliverPriceCount":"0"}]}]
     * pointsAll : 1589
     */

    private int err;
    private String pointsAll;
    private List<InfoBean> info;

    public int getErr() {
        return err;
    }

    public void setErr(int err) {
        this.err = err;
    }

    public String getPointsAll() {
        return pointsAll;
    }

    public void setPointsAll(String pointsAll) {
        this.pointsAll = pointsAll;
    }

    public List<InfoBean> getInfo() {
        return info;
    }

    public void setInfo(List<InfoBean> info) {
        this.info = info;
    }

    public static class InfoBean {
        /**
         * id : 12
         * cate_id : 9
         * thumb : http://pic.myplas.com/upload/17/04/07/58e759146ce08.gif
         * name : 单条供求信息一天置顶卡
         * points : 100
         * myMsg : [{"id":"50254","p_id":"5084","user_id":"3858","model":"7000F","unit_price":"9000.00","store_house":"上海","f_name":"上海","input_time":"04-08 11:07","type":"2","content":"ddddd","name":"李一帆","c_name":"上海中晨电子商务","is_pass":"0","thumb":"http://pic.myplas.com/upload/17/03/29/58db68941ae4e.jpg","contents":"价格9000.00元左右/7000F/上海/上海/ddddd","saysCount":0,"deliverPriceCount":"0"},{"id":"50246","p_id":"0","user_id":"3858","model":null,"unit_price":"0.00","store_house":"","f_name":null,"input_time":"04-07 21:15","type":"date_selected","content":"111111","name":"李一帆","c_name":"上海中晨电子商务","is_pass":"0","thumb":"http://pic.myplas.com/upload/17/03/29/58db68941ae4e.jpg","contents":"111111","saysCount":0,"deliverPriceCount":"0"},{"id":"50243","p_id":"0","user_id":"3858","model":null,"unit_price":"0.00","store_house":"","f_name":null,"input_time":"04-07 19:16","type":"2","content":"ddfffff","name":"李一帆","c_name":"上海中晨电子商务","is_pass":"0","thumb":"http://pic.myplas.com/upload/17/03/29/58db68941ae4e.jpg","contents":"ddfffff","saysCount":0,"deliverPriceCount":"0"},{"id":"50242","p_id":"0","user_id":"3858","model":null,"unit_price":"0.00","store_house":"","f_name":null,"input_time":"04-07 19:15","type":"date_selected","content":"11111155555","name":"李一帆","c_name":"上海中晨电子商务","is_pass":"0","thumb":"http://pic.myplas.com/upload/17/03/29/58db68941ae4e.jpg","contents":"11111155555","saysCount":0,"deliverPriceCount":"0"},{"id":"50241","p_id":"0","user_id":"3858","model":null,"unit_price":"0.00","store_house":"","f_name":null,"input_time":"04-07 19:06","type":"2","content":"ddddd","name":"李一帆","c_name":"上海中晨电子商务","is_pass":"0","thumb":"http://pic.myplas.com/upload/17/03/29/58db68941ae4e.jpg","contents":"ddddd","saysCount":0,"deliverPriceCount":"0"}]
         */

        private String id;
        private String cate_id;
        private String thumb;
        private String name;
        private String type;
        private String points;
        private List<MyMsgBean> myMsg;

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getCate_id() {
            return cate_id;
        }

        public void setCate_id(String cate_id) {
            this.cate_id = cate_id;
        }

        public String getThumb() {
            return thumb;
        }

        public void setThumb(String thumb) {
            this.thumb = thumb;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPoints() {
            return points;
        }

        public void setPoints(String points) {
            this.points = points;
        }

        public List<MyMsgBean> getMyMsg() {
            return myMsg;
        }

        public void setMyMsg(List<MyMsgBean> myMsg) {
            this.myMsg = myMsg;
        }

        public static class MyMsgBean {
            /**
             * id : 50254
             * p_id : 5084
             * user_id : 3858
             * model : 7000F
             * unit_price : 9000.00
             * store_house : 上海
             * f_name : 上海
             * input_time : 04-08 11:07
             * type :
             * content : ddddd 2
             * name : 李一帆
             * c_name : 上海中晨电子商务
             * is_pass : 0
             * thumb : http://pic.myplas.com/upload/17/03/29/58db68941ae4e.jpg
             * contents : 价格9000.00元左右/7000F/上海/上海/ddddd
             * saysCount : 0
             * deliverPriceCount : 0
             */

            private String id;
            private String p_id;
            private String user_id;
            private String model;
            private String unit_price;
            private String store_house;
            private String f_name;
            private String input_time;
            private String type;
            private String content;
            private String name;
            private String c_name;
            private String is_pass;
            private String thumb;
            private String contents;
            private int saysCount;
            private String deliverPriceCount;

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

            public String getC_name() {
                return c_name;
            }

            public void setC_name(String c_name) {
                this.c_name = c_name;
            }

            public String getIs_pass() {
                return is_pass;
            }

            public void setIs_pass(String is_pass) {
                this.is_pass = is_pass;
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

            public int getSaysCount() {
                return saysCount;
            }

            public void setSaysCount(int saysCount) {
                this.saysCount = saysCount;
            }

            public String getDeliverPriceCount() {
                return deliverPriceCount;
            }

            public void setDeliverPriceCount(String deliverPriceCount) {
                this.deliverPriceCount = deliverPriceCount;
            }
        }
    }
}
