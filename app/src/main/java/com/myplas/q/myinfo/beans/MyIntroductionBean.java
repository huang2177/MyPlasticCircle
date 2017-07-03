package com.myplas.q.myinfo.beans;

import java.util.List;

/**
 * 编写： 黄双
 * 电话：15378412400
 * 邮箱：15378412400@163.com
 * 时间：2017/3/29 17:18
 */
public class MyIntroductionBean {

    /**
     * err : 0
     * data : [{"user_id":"35942","name":"顾晓懿","mobile":"1377682****","is_pass":"0","c_name":"上海中晨电子商务股份有限公司","thumb":"http://statics.myplas.com/upload/16/09/02/logos.jpg","buy":"0","sale":"0"},{"user_id":"35934","name":"朱彼德","mobile":"1505841****","is_pass":"0","c_name":"蓝鲸产品厂家直销","thumb":"http://statics.myplas.com/upload/16/09/02/logos.jpg","buy":"0","sale":"0"},{"user_id":"35913","name":"张YAN","mobile":"1381021****","is_pass":"0","c_name":"群星集团公司","thumb":"http://statics.myplas.com/upload/16/09/02/logos.jpg","buy":"0","sale":"0"},{"user_id":"35911","name":"上官","mobile":"1865390****","is_pass":"0","c_name":"临沂裕田塑料制品厂","thumb":"http://statics.myplas.com/upload/16/09/02/logos.jpg","buy":"0","sale":"0"},{"user_id":"35905","name":"小段","mobile":"1370178****","is_pass":"0","c_name":"上海瑞藩实业有限公司","thumb":"http://statics.myplas.com/upload/16/09/02/logos.jpg","buy":"0","sale":"0"},{"user_id":"35903","name":"孙永征","mobile":"1390539****","is_pass":"0","c_name":"山东临沂永征进出口有限公司","thumb":"http://statics.myplas.com/upload/16/09/02/logos.jpg","buy":"0","sale":"0"},{"user_id":"35902","name":"罗立成","mobile":"1391325****","is_pass":"date_selected","c_name":"江苏金塑德贸易有限公司","thumb":"http://statics.myplas.com/upload/16/09/02/logos.jpg","buy":"0","sale":"0"},{"user_id":"35897","name":"谢坤衡","mobile":"1390633****","is_pass":"0","c_name":"莒县永达工贸有限公司","thumb":"http://statics.myplas.com/upload/16/09/02/logos.jpg","buy":"0","sale":"0"},{"user_id":"35795","name":"PK","mobile":"1861633****","is_pass":"0","c_name":"中泰证券研究所","thumb":"http://statics.myplas.com/upload/16/09/02/logos.jpg","buy":"0","sale":"0"},{"user_id":"35584","name":"周思宇","mobile":"1361087****","is_pass":"0","c_name":"盘锦鑫宇盈塑料有限公司","thumb":"http://statics.myplas.com/upload/16/09/02/logos.jpg","buy":"0","sale":"0"},{"user_id":"35545","name":"郑永光","mobile":"1348279****","is_pass":"0","c_name":"塑料个体户","thumb":"http://statics.myplas.com/upload/16/09/02/logos.jpg","buy":"0","sale":"0"},{"user_id":"35330","name":"常兵","mobile":"1300889****","is_pass":"0","c_name":"深圳市华科兄弟颜料科技有限公司","thumb":"http://statics.myplas.com/upload/16/09/02/logos.jpg","buy":"0","sale":"0"},{"user_id":"34505","name":"许宁","mobile":"1337563****","is_pass":"0","c_name":"新泰市三泰工贸有限责任公司","thumb":"http://statics.myplas.com/upload/16/09/02/logos.jpg","buy":"0","sale":"0"},{"user_id":"33322","name":"李华乾","mobile":"1393112****","is_pass":"0","c_name":"乾坤塑料回收","thumb":"http://statics.myplas.com/upload/16/09/02/logos.jpg","buy":"0","sale":"0"},{"user_id":"32995","name":"李玉峰","mobile":"1325552****","is_pass":"0","c_name":"PVC废旧塑料回收","thumb":"http://statics.myplas.com/upload/16/09/02/logos.jpg","buy":"0","sale":"0"},{"user_id":"32856","name":"陈平","mobile":"1312925****","is_pass":"0","c_name":"广东省佛山市旭盈塑料厂","thumb":"http://statics.myplas.com/upload/16/09/02/logos.jpg","buy":"0","sale":"0"},{"user_id":"32789","name":"李云","mobile":"1861849****","is_pass":"0","c_name":"山东济宁恒恺科技有限公司","thumb":"http://statics.myplas.com/upload/16/09/02/logos.jpg","buy":"0","sale":"15"},{"user_id":"32731","name":"何生","mobile":"1369027****","is_pass":"0","c_name":"佛山弘振塑料有限公司","thumb":"http://statics.myplas.com/upload/16/09/02/logos.jpg","buy":"0","sale":"0"},{"user_id":"32325","name":"严海韬","mobile":"1801867****","is_pass":"0","c_name":"上海星捷塑料贸易有限公司","thumb":"http://statics.myplas.com/upload/16/09/02/logos.jpg","buy":"0","sale":"0"}]
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
         * user_id : 35942
         * name : 顾晓懿
         * mobile : 1377682****
         * is_pass : 0
         * c_name : 上海中晨电子商务股份有限公司
         * thumb : http://statics.myplas.com/upload/16/09/02/logos.jpg
         * buy : 0
         * sale : 0
         */

        private String user_id;
        private String name;
        private String mobile;
        private String is_pass;
        private String c_name;
        private String thumb;
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
