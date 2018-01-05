package com.myplas.q.myself.beans;

import java.util.List;

/**
 * 编写：黄双
 * 邮箱：15378412400@163.com
 * 时间： 2017/6/61143.
 */

public class SelectableBean {

    /**
     * err : 0
     * data : [{"money":10,"plasticBean":100},{"money":20,"plasticBean":200},{"money":30,"plasticBean":300},{"money":50,"plasticBean":500},{"money":100,"plasticBean":1000},{"money":200,"plasticBean":2000},{"money":300,"plasticBean":3000},{"money":500,"plasticBean":500},{"money":600,"plasticBean":600}]
     */

    private List<DataBean> data;


    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * money : 10
         * plasticBean : 100
         */

        private int money;
        private int plasticBean;

        public int getMoney() {
            return money;
        }

        public void setMoney(int money) {
            this.money = money;
        }

        public int getPlasticBean() {
            return plasticBean;
        }

        public void setPlasticBean(int plasticBean) {
            this.plasticBean = plasticBean;
        }
    }
}
