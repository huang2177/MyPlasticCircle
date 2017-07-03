package com.myplas.q.addresslist.Beans;

import java.util.List;

/**
 * 编写： 黄双
 * 电话：15378412400
 * 邮箱：15378412400@163.com
 * 时间：2017/3/20 22:53
 */
public class MyFansBean {

    /**
     * err : 0
     * data : [{"user_id":{"user_id":"41812","name":"王槐武","mobile":"1508875****","is_pass":"0","c_name":"长江期货有限公司","thumb":"http://statics.myplas.com/upload/16/09/02/logos.jpg","thumbqq":"","buy":"0","sale":"32"},"focused_id":"3858"},{"user_id":{"user_id":"41389","name":"熊发元","mobile":"1830196****","is_pass":"0","c_name":"上海田强环保科技股份有限公司","thumb":"http://statics.myplas.com/upload/16/09/02/logos.jpg","thumbqq":"","buy":"0","sale":"5"},"focused_id":"3858"},{"user_id":{"user_id":"3858","name":"李一帆","mobile":"1376499****","is_pass":"0","c_name":"上海中晨电子商务","thumb":"http://statics.myplas.com/upload/17/03/28/58da3d016c98a.jpg","thumbqq":"http://statics.myplas.com/upload/17/03/28/58da3d016c98a.jpg","buy":"10","sale":"3"},"focused_id":"3858"},{"user_id":{"user_id":"39715","name":"王小姐","mobile":"1866268****","is_pass":"0","c_name":"格润泰有限公司","thumb":"http://statics.myplas.com/upload/16/09/02/logos.jpg","thumbqq":"","buy":"0","sale":"date_selected"},"focused_id":"3858"},{"user_id":{"user_id":"35942","name":"顾晓懿","mobile":"1377682****","is_pass":"0","c_name":"上海中晨电子商务股份有限公司","thumb":"http://statics.myplas.com/upload/16/09/02/logos.jpg","thumbqq":"","buy":"0","sale":"0"},"focused_id":"3858"},{"user_id":{"user_id":"35934","name":"朱彼德","mobile":"1505841****","is_pass":"0","c_name":"蓝鲸产品厂家直销","thumb":"http://statics.myplas.com/upload/16/11/25/58381e83402b6.jpg","thumbqq":"http://statics.myplas.com/upload/16/11/25/58381e83402b6.jpg","buy":"0","sale":"0"},"focused_id":"3858"},{"user_id":{"user_id":"35913","name":"张YAN","mobile":"1381021****","is_pass":"0","c_name":"群星集团公司","thumb":"http://statics.myplas.com/upload/16/09/02/logos.jpg","thumbqq":"","buy":"0","sale":"0"},"focused_id":"3858"},{"user_id":{"user_id":"35911","name":"上官","mobile":"1865390****","is_pass":"0","c_name":"临沂裕田塑料制品厂","thumb":"http://statics.myplas.com/upload/16/09/02/logos.jpg","thumbqq":"","buy":"0","sale":"0"},"focused_id":"3858"},{"user_id":{"user_id":"35905","name":"小段","mobile":"1370178****","is_pass":"0","c_name":"上海瑞藩实业有限公司","thumb":"http://statics.myplas.com/upload/16/09/02/logos.jpg","thumbqq":"","buy":"0","sale":"0"},"focused_id":"3858"},{"user_id":{"user_id":"35903","name":"孙永征","mobile":"1390539****","is_pass":"0","c_name":"山东临沂永征进出口有限公司","thumb":"http://statics.myplas.com/upload/16/11/22/5834386387232.png","thumbqq":"http://statics.myplas.com/upload/16/11/22/5834386387232.png","buy":"0","sale":"0"},"focused_id":"3858"},{"user_id":{"user_id":"35902","name":"罗立成","mobile":"1391325****","is_pass":"date_selected","c_name":"江苏金塑德贸易有限公司","thumb":"http://statics.myplas.com/upload/16/09/02/logos.jpg","thumbqq":"","buy":"0","sale":"0"},"focused_id":"3858"},{"user_id":{"user_id":"35897","name":"谢坤衡","mobile":"1390633****","is_pass":"0","c_name":"莒县永达工贸有限公司","thumb":"http://statics.myplas.com/upload/16/09/02/logos.jpg","thumbqq":"","buy":"0","sale":"0"},"focused_id":"3858"},{"user_id":{"user_id":"35795","name":"PK","mobile":"1861633****","is_pass":"0","c_name":"中泰证券研究所","thumb":"http://statics.myplas.com/upload/16/09/02/logos.jpg","thumbqq":"","buy":"0","sale":"0"},"focused_id":"3858"},{"user_id":{"user_id":"35545","name":"郑永光","mobile":"1348279****","is_pass":"0","c_name":"塑料个体户","thumb":"http://statics.myplas.com/upload/16/09/02/logos.jpg","thumbqq":"","buy":"0","sale":"0"},"focused_id":"3858"},{"user_id":{"user_id":"35330","name":"常兵","mobile":"1300889****","is_pass":"0","c_name":"深圳市华科兄弟颜料科技有限公司","thumb":"http://statics.myplas.com/upload/16/09/02/logos.jpg","thumbqq":"","buy":"0","sale":"0"},"focused_id":"3858"}]
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
         * user_id : {"user_id":"41812","name":"王槐武","mobile":"1508875****","is_pass":"0","c_name":"长江期货有限公司","thumb":"http://statics.myplas.com/upload/16/09/02/logos.jpg","thumbqq":"","buy":"0","sale":"32"}
         * focused_id : 3858
                */

        private UserIdBean user_id;
        private String focused_id;

        public UserIdBean getUser_id() {
            return user_id;
        }

        public void setUser_id(UserIdBean user_id) {
            this.user_id = user_id;
        }

        public String getFocused_id() {
            return focused_id;
        }

        public void setFocused_id(String focused_id) {
            this.focused_id = focused_id;
        }

        public static class UserIdBean {
            /**
             * user_id : 41812
             * name : 王槐武
             * mobile : 1508875****
             * is_pass : 0
             * c_name : 长江期货有限公司
             * thumb : http://statics.myplas.com/upload/16/09/02/logos.jpg
             * thumbqq :
             * buy : 0
             * sale : 32
             */

            private String user_id;
            private String name;
            private String mobile;
            private String is_pass;
            private String c_name;
            private String thumb;
            private String thumbqq;
            private String buy;
            private String sale;

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

            public String getMobile() {
                return mobile;
            }

            public void setMobile(String mobile) {
                this.mobile = mobile;
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

            public String getThumbqq() {
                return thumbqq;
            }

            public void setThumbqq(String thumbqq) {
                this.thumbqq = thumbqq;
            }

            public String getBuy() {
                return buy;
            }

            public void setBuy(String buy) {
                this.buy = buy;
            }

            public String getSale() {
                return sale;
            }

            public void setSale(String sale) {
                this.sale = sale;
            }
        }
    }
}
