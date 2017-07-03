package com.myplas.q.myinfo.beans;

import java.util.List;

/**
 * 编写： 黄双
 * 电话：15378412400
 * 邮箱：15378412400@163.com
 * 时间：2017/3/29 09:40
 */
public class EDuBean {

    /**
     * err : 0
     * c_name : 上海中晨电子商务股份有限公司
     * data : [{"q":"<span>如何获得授信？<\/span>","a":"<span>你可以根据以下方式与我们联系：<\/span><br/><span>邮箱：info@myplas.com<\/span><br/><span>客服热线：400-6129-965<\/span><br/><span>前台热线：021-61070985<\/span>"},{"q":"<span>如何提升额度？<\/span>","a":"<span>一，在我的塑料网上形成交易，并确保交易信用良好<\/span><br/><span>二，在塑料圈通讯录上多发布供求信息<\/span><br><span>为塑料圈通讯录引入新的塑料交易人员<\/span>"}]
     */

    private int err;
    private String c_name;
    private List<DataBean> data;

    public int getErr() {
        return err;
    }

    public void setErr(int err) {
        this.err = err;
    }

    public String getC_name() {
        return c_name;
    }

    public void setC_name(String c_name) {
        this.c_name = c_name;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * q : <span>如何获得授信？</span>
         * a : <span>你可以根据以下方式与我们联系：</span><br/><span>邮箱：info@myplas.com</span><br/><span>客服热线：400-6129-965</span><br/><span>前台热线：021-61070985</span>
         */

        private String q;
        private String a;

        public String getQ() {
            return q;
        }

        public void setQ(String q) {
            this.q = q;
        }

        public String getA() {
            return a;
        }

        public void setA(String a) {
            this.a = a;
        }
    }
}
