package com.myplas.q.supdem.Beans;

import java.util.List;

/**
 * 编写： 黄双
 * 电话：15378412400
 * 邮箱：15378412400@163.com
 * 时间：2017/3/31 20:18
 */
public class ReplyBean {

    /**
     * err : 0
     * data : {"count":"date_selected","data":[{"id":"1451","rev_id":"3858","user_id":"32788","is_read":"date_selected","content":"什么料？","input_time":"4天前","info":{"user_id":"32788","name":"戴先生","c_id":"39566","is_pass":"0","mobile":"15017069076","sex":"男","thumb":"http://statics.myplas.com/upload/16/09/02/logos.jpg","thumbqq":"","thumbcard":"","c_name":"双益塑胶有限公司","need_product":"尼龙，轮胎料，ABS.PC.PC/ABS","address":"东莞市"}}]}
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
         * count : date_selected
         * data : [{"id":"1451","rev_id":"3858","user_id":"32788","is_read":"date_selected","content":"什么料？","input_time":"4天前","info":{"user_id":"32788","name":"戴先生","c_id":"39566","is_pass":"0","mobile":"15017069076","sex":"男","thumb":"http://statics.myplas.com/upload/16/09/02/logos.jpg","thumbqq":"","thumbcard":"","c_name":"双益塑胶有限公司","need_product":"尼龙，轮胎料，ABS.PC.PC/ABS","address":"东莞市"}}]
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
             * id : 1451
             * rev_id : 3858
             * user_id : 32788
             * is_read : date_selected
             * content : 什么料？
             * input_time : 4天前
             * info : {"user_id":"32788","name":"戴先生","c_id":"39566","is_pass":"0","mobile":"15017069076","sex":"男","thumb":"http://statics.myplas.com/upload/16/09/02/logos.jpg","thumbqq":"","thumbcard":"","c_name":"双益塑胶有限公司","need_product":"尼龙，轮胎料，ABS.PC.PC/ABS","address":"东莞市"}
             */

            private String id;
            private String rev_id;
            private String user_id;
            private String is_read;
            private String content;
            private String input_time;
            private InfoBean info;

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

            public InfoBean getInfo() {
                return info;
            }

            public void setInfo(InfoBean info) {
                this.info = info;
            }

            public static class InfoBean {
                /**
                 * user_id : 32788
                 * name : 戴先生
                 * c_id : 39566
                 * is_pass : 0
                 * mobile : 15017069076
                 * sex : 男
                 * thumb : http://statics.myplas.com/upload/16/09/02/logos.jpg
                 * thumbqq :
                 * thumbcard :
                 * c_name : 双益塑胶有限公司
                 * need_product : 尼龙，轮胎料，ABS.PC.PC/ABS
                 * address : 东莞市
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
