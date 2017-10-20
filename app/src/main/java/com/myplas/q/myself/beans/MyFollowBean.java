package com.myplas.q.myself.beans;

import java.util.List;

/**
 * 编写： 黄双
 * 电话：15378412400
 * 邮箱：15378412400@163.com
 * 时间：2017/3/29 16:13
 */
public class MyFollowBean {

    /**
     * err : 0
     * data : [{"user_id":"3858","focused_id":{"user_id":"9266","name":"成平","mobile":"1370199****","is_pass":"0","c_name":"上海梓辰实业有限公司","thumb":"http://statics.myplas.com/upload/16/10/25/580ebd01ea3db.png","thumbqq":"http://statics.myplas.com/upload/16/10/25/580ebd01ea3db.png","buy":"date_selected","sale":"5"}},{"user_id":"3858","focused_id":{"user_id":"8282","name":"倪同鑫","mobile":"1732102****","is_pass":"date_selected","c_name":"上海塑米信息科技有限公司","thumb":"http://statics.myplas.com/myapp/weixin/1475127862.jpg","thumbqq":"http://statics.myplas.com/myapp/weixin/1475127862.jpg","buy":"56","sale":"173"}},{"user_id":"3858","focused_id":{"user_id":"29268","name":"初晓静","mobile":"1861635****","is_pass":"0","c_name":"上海朔嘉实业有限公司","thumb":"http://statics.myplas.com/myapp/weixin/1475146990.jpg","thumbqq":"http://statics.myplas.com/myapp/weixin/1475146990.jpg","buy":"date_selected","sale":"2"}},{"user_id":"3858","focused_id":{"user_id":"3858","name":"李一帆","mobile":"1376499****","is_pass":"0","c_name":"上海中晨电子商务","thumb":"http://statics.myplas.com/upload/17/03/28/58da3d016c98a.jpg","thumbqq":"http://statics.myplas.com/upload/17/03/28/58da3d016c98a.jpg","buy":"10","sale":"3"}},{"user_id":"3858","focused_id":{"user_id":"28922","name":"王云霞","mobile":"1882751****","is_pass":"0","c_name":"沧县广友塑料制品有限公司","thumb":"http://statics.myplas.com/upload/17/03/27/58d913e898928.jpg","thumbqq":"http://statics.myplas.com/upload/17/03/27/58d913e898928.jpg","buy":"3","sale":"9"}},{"user_id":"3858","focused_id":{"user_id":"3862","name":"张晓星","mobile":"1522119****","is_pass":"0","c_name":"上海中晨电子商务股份有限公司","thumb":"http://statics.myplas.com/myapp/weixin/1475034543.jpg","thumbqq":"http://statics.myplas.com/myapp/weixin/1475034543.jpg","buy":"0","sale":"0"}},{"user_id":"3858","focused_id":{"user_id":"29489","name":"冯光文","mobile":"1520666****","is_pass":"date_selected","c_name":"山东省塑料工业有限公司","thumb":"http://statics.myplas.com/upload/16/10/27/5811d1930c6d9.jpg","thumbqq":"http://statics.myplas.com/upload/16/10/27/5811d1930c6d9.jpg","buy":"date_selected","sale":"29"}},{"user_id":"3858","focused_id":{"user_id":"5189","name":"二哥","mobile":"1586697****","is_pass":"date_selected","c_name":"临沂庆博塑化有限公司","thumb":"http://statics.myplas.com/myapp/weixin/1476266067.jpg","thumbqq":"http://statics.myplas.com/myapp/weixin/1476266067.jpg","buy":"20","sale":"180"}},{"user_id":"3858","focused_id":{"user_id":"29066","name":"王晓莉","mobile":"1369417****","is_pass":"0","c_name":"营口泰洋包装制品有限公司","thumb":"http://statics.myplas.com/myapp/weixin/1475035387.jpg","thumbqq":"http://statics.myplas.com/myapp/weixin/1475035387.jpg","buy":"0","sale":"0"}},{"user_id":"3858","focused_id":{"user_id":"29280","name":"逄勃","mobile":"1391631****","is_pass":"date_selected","c_name":"上海在金贸易有限公司","thumb":"http://statics.myplas.com/upload/17/02/04/5894d51c65cee.JPG","thumbqq":"http://statics.myplas.com/upload/17/02/04/5894d51c65cee.JPG","buy":"date_selected","sale":"45"}},{"user_id":"3858","focused_id":{"user_id":"28973","name":"李高芳","mobile":"1881103****","is_pass":"0","c_name":"中农集团通用化工有限公司","thumb":"http://statics.myplas.com/myapp/weixin/1476405048.jpg","thumbqq":"http://statics.myplas.com/myapp/weixin/1476405048.jpg","buy":"0","sale":"10"}},{"user_id":"3858","focused_id":{"user_id":"2062","name":"李铁道","mobile":"1376468****","is_pass":"date_selected","c_name":"上海中晨电子商务股份有限公司","thumb":"http://wx.qlogo.cn/mmopen/1A74iasgreQJdjYz06ib0QJTYV7fN8iaWJ2PKRzyiaM6ibtsFiaznERIouY9rCSObhN6jgPhWjYl3vJQIPkOHHic0tqS7Zmg1ibjIpz1/0","thumbqq":"http://wx.qlogo.cn/mmopen/1A74iasgreQJdjYz06ib0QJTYV7fN8iaWJ2PKRzyiaM6ibtsFiaznERIouY9rCSObhN6jgPhWjYl3vJQIPkOHHic0tqS7Zmg1ibjIpz1/0","buy":"date_selected","sale":"8"}}]
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
         * user_id : 3858
         * focused_id : {"user_id":"9266","name":"成平","mobile":"1370199****","is_pass":"0","c_name":"上海梓辰实业有限公司","thumb":"http://statics.myplas.com/upload/16/10/25/580ebd01ea3db.png","thumbqq":"http://statics.myplas.com/upload/16/10/25/580ebd01ea3db.png","buy":"date_selected","sale":"5"}
         */

        private String user_id;
        private FocusedIdBean focused_id;

        public String getUser_id() {
            return user_id;
        }

        public void setUser_id(String user_id) {
            this.user_id = user_id;
        }

        public FocusedIdBean getFocused_id() {
            return focused_id;
        }

        public void setFocused_id(FocusedIdBean focused_id) {
            this.focused_id = focused_id;
        }

        public static class FocusedIdBean {
            /**
             * user_id : 9266
             * name : 成平
             * mobile : 1370199****
             * is_pass : 0
             * c_name : 上海梓辰实业有限公司
             * thumb : http://statics.myplas.com/upload/16/10/25/580ebd01ea3db.png
             * thumbqq : http://statics.myplas.com/upload/16/10/25/580ebd01ea3db.png
             * buy : date_selected
             * sale : 5
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
