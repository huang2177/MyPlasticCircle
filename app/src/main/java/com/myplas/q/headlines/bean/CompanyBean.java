package com.myplas.q.headlines.bean;

import java.util.List;

/**
 * 编写： 黄双
 * 电话：15378412400
 * 邮箱：15378412400@163.com
 * 时间：2017/4/5 18:08
 */
public class CompanyBean {

    /**
     * err : 0
     * data : [{"c_id":"date_selected","c_name":"上海久红贸易有限公司","contact_id":"date_selected"},{"c_id":"3","c_name":"北大方正物产集团（上海）有限公司","contact_id":"3"},{"c_id":"6","c_name":"上海申北化工原料有限公司","contact_id":"6"},{"c_id":"9","c_name":"上海八通石化有限公司","contact_id":"9"},{"c_id":"11","c_name":"上海塑米信息科技有限公司","contact_id":"11"},{"c_id":"15","c_name":"上海攀润禾贸易发展有限公司","contact_id":"15"},{"c_id":"48","c_name":"志鹏国际贸易（上海）有限公司","contact_id":"48"},{"c_id":"271","c_name":"泽太化纤（上海）有限公司","contact_id":"271"},{"c_id":"274","c_name":"允微电子科技（上海）有限公司","contact_id":"274"},{"c_id":"279","c_name":"原上海增强塑胶有限公司，现苏州鸿创利包装材料有限公司","contact_id":"279"}]
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
         * c_id : date_selected
         * c_name : 上海久红贸易有限公司
         * contact_id : date_selected
         */

        private String c_id;
        private String c_name;
        private String contact_id;

        public String getC_id() {
            return c_id;
        }

        public void setC_id(String c_id) {
            this.c_id = c_id;
        }

        public String getC_name() {
            return c_name;
        }

        public void setC_name(String c_name) {
            this.c_name = c_name;
        }

        public String getContact_id() {
            return contact_id;
        }

        public void setContact_id(String contact_id) {
            this.contact_id = contact_id;
        }
    }
}
