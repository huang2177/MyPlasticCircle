package com.myplas.q.supdem.Beans;

import java.util.List;

/**
 * 编写： 黄双
 * 电话：15378412400
 * 邮箱：15378412400@163.com
 * 时间：2017/3/31 20:51
 */
public class DeliverPriceBean {

    /**
     * err : 0
     * data : {"count":"2","data":[{"id":"39","pur_id":"47992","send_id":"9266","user_id":"7489","price":"9100.00","type":"2","status":"0","desc":"","input_name":null,"input_time":"91天前","info":{"user_id":"9266","name":"成平","c_id":"4016","is_pass":"0","mobile":"13701995655","sex":"男","thumb":"http://statics.myplas.com/upload/16/10/25/580ebd01ea3db.png","thumbqq":"http://statics.myplas.com/upload/16/10/25/580ebd01ea3db.png","thumbcard":"http://statics.myplas.com/upload/16/10/20/58088c04f1043.png","c_name":"上海梓辰实业有限公司","need_product":"7000F","address":"广灵四路655号"}},{"id":"38","pur_id":"47992","send_id":"9266","user_id":"7489","price":"8990.00","type":"2","status":"0","desc":"","input_name":null,"input_time":"91天前","info":{"user_id":"9266","name":"成平","c_id":"4016","is_pass":"0","mobile":"13701995655","sex":"男","thumb":"http://statics.myplas.com/upload/16/10/25/580ebd01ea3db.png","thumbqq":"http://statics.myplas.com/upload/16/10/25/580ebd01ea3db.png","thumbcard":"http://statics.myplas.com/upload/16/10/20/58088c04f1043.png","c_name":"上海梓辰实业有限公司","need_product":"7000F","address":"广灵四路655号"}}]}
     */

    private int err;
    private DataBeanX data;

    public int getErr() {
        return err;
    }

    public void setErr(int err) {
        this.err = err;
    }

    public DataBeanX getData() {
        return data;
    }

    public void setData(DataBeanX data) {
        this.data = data;
    }

    public static class DataBeanX {
        /**
         * count : 2
         * data : [{"id":"39","pur_id":"47992","send_id":"9266","user_id":"7489","price":"9100.00","type":"2","status":"0","desc":"","input_name":null,"input_time":"91天前","info":{"user_id":"9266","name":"成平","c_id":"4016","is_pass":"0","mobile":"13701995655","sex":"男","thumb":"http://statics.myplas.com/upload/16/10/25/580ebd01ea3db.png","thumbqq":"http://statics.myplas.com/upload/16/10/25/580ebd01ea3db.png","thumbcard":"http://statics.myplas.com/upload/16/10/20/58088c04f1043.png","c_name":"上海梓辰实业有限公司","need_product":"7000F","address":"广灵四路655号"}},{"id":"38","pur_id":"47992","send_id":"9266","user_id":"7489","price":"8990.00","type":"2","status":"0","desc":"","input_name":null,"input_time":"91天前","info":{"user_id":"9266","name":"成平","c_id":"4016","is_pass":"0","mobile":"13701995655","sex":"男","thumb":"http://statics.myplas.com/upload/16/10/25/580ebd01ea3db.png","thumbqq":"http://statics.myplas.com/upload/16/10/25/580ebd01ea3db.png","thumbcard":"http://statics.myplas.com/upload/16/10/20/58088c04f1043.png","c_name":"上海梓辰实业有限公司","need_product":"7000F","address":"广灵四路655号"}}]
         */

        private String count;
        private List<DataBean> data;

        public String getCount() {
            return count;
        }

        public void setCount(String count) {
            this.count = count;
        }

        public List<DataBean> getData() {
            return data;
        }

        public void setData(List<DataBean> data) {
            this.data = data;
        }

        public static class DataBean {
            /**
             * id : 39
             * pur_id : 47992
             * send_id : 9266
             * user_id : 7489
             * price : 9100.00
             * type : 2
             * status : 0
             * desc :
             * input_name : null
             * input_time : 91天前
             * info : {"user_id":"9266","name":"成平","c_id":"4016","is_pass":"0","mobile":"13701995655","sex":"男","thumb":"http://statics.myplas.com/upload/16/10/25/580ebd01ea3db.png","thumbqq":"http://statics.myplas.com/upload/16/10/25/580ebd01ea3db.png","thumbcard":"http://statics.myplas.com/upload/16/10/20/58088c04f1043.png","c_name":"上海梓辰实业有限公司","need_product":"7000F","address":"广灵四路655号"}
             */

            private String id;
            private String pur_id;
            private String send_id;
            private String user_id;
            private String price;
            private String type;
            private String status;
            private String desc;
            private Object input_name;
            private String input_time;
            private InfoBean info;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getPur_id() {
                return pur_id;
            }

            public void setPur_id(String pur_id) {
                this.pur_id = pur_id;
            }

            public String getSend_id() {
                return send_id;
            }

            public void setSend_id(String send_id) {
                this.send_id = send_id;
            }

            public String getUser_id() {
                return user_id;
            }

            public void setUser_id(String user_id) {
                this.user_id = user_id;
            }

            public String getPrice() {
                return price;
            }

            public void setPrice(String price) {
                this.price = price;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public String getStatus() {
                return status;
            }

            public void setStatus(String status) {
                this.status = status;
            }

            public String getDesc() {
                return desc;
            }

            public void setDesc(String desc) {
                this.desc = desc;
            }

            public Object getInput_name() {
                return input_name;
            }

            public void setInput_name(Object input_name) {
                this.input_name = input_name;
            }

            public String getInput_time() {
                return input_time;
            }

            public void setInput_time(String input_time) {
                this.input_time = input_time;
            }

            public InfoBean getInfo() {
                return info;
            }

            public void setInfo(InfoBean info) {
                this.info = info;
            }

            public static class InfoBean {
                /**
                 * user_id : 9266
                 * name : 成平
                 * c_id : 4016
                 * is_pass : 0
                 * mobile : 13701995655
                 * sex : 男
                 * thumb : http://statics.myplas.com/upload/16/10/25/580ebd01ea3db.png
                 * thumbqq : http://statics.myplas.com/upload/16/10/25/580ebd01ea3db.png
                 * thumbcard : http://statics.myplas.com/upload/16/10/20/58088c04f1043.png
                 * c_name : 上海梓辰实业有限公司
                 * need_product : 7000F
                 * address : 广灵四路655号
                 */

                private String user_id;
                private String name;
                private String c_id;
                private String is_pass;
                private String mobile;
                private String sex;
                private String thumb;
                private String thumbqq;
                private String thumbcard;
                private String c_name;
                private String need_product;
                private String address;

                public String getUser_id() {
                    return user_id;
                }

                public void setUser_id(String user_id) {
                    this.user_id = user_id;
                }

                public String getName() {
                    return name;
                }

                public void setName(String name) {
                    this.name = name;
                }

                public String getC_id() {
                    return c_id;
                }

                public void setC_id(String c_id) {
                    this.c_id = c_id;
                }

                public String getIs_pass() {
                    return is_pass;
                }

                public void setIs_pass(String is_pass) {
                    this.is_pass = is_pass;
                }

                public String getMobile() {
                    return mobile;
                }

                public void setMobile(String mobile) {
                    this.mobile = mobile;
                }

                public String getSex() {
                    return sex;
                }

                public void setSex(String sex) {
                    this.sex = sex;
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

                public String getThumbcard() {
                    return thumbcard;
                }

                public void setThumbcard(String thumbcard) {
                    this.thumbcard = thumbcard;
                }

                public String getC_name() {
                    return c_name;
                }

                public void setC_name(String c_name) {
                    this.c_name = c_name;
                }

                public String getNeed_product() {
                    return need_product;
                }

                public void setNeed_product(String need_product) {
                    this.need_product = need_product;
                }

                public String getAddress() {
                    return address;
                }

                public void setAddress(String address) {
                    this.address = address;
                }
            }
        }
    }
}
