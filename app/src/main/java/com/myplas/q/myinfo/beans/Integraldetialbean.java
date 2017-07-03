package com.myplas.q.myinfo.beans;

import java.util.List;

/**
 * 编写： 黄双
 * 电话：15378412400
 * 邮箱：15378412400@163.com
 * 时间：2017/3/29 10:56
 */
public class Integraldetialbean {

    /**
     * err : 0
     * data : [{"id":"23460","addtime":"10:00","points":"10","typename":"登陆"},{"id":"22947","addtime":"2017-03-28","points":"10","typename":"登陆"},{"id":"22683","addtime":"2017-03-27","points":"20","typename":"发布采购"},{"id":"22648","addtime":"2017-03-27","points":"10","typename":"登陆"},{"id":"22380","addtime":"2017-03-24","points":"10","typename":"登陆"},{"id":"22142","addtime":"2017-03-23","points":"10","typename":"登陆"},{"id":"21954","addtime":"2017-03-22","points":"10","typename":"登陆"},{"id":"21832","addtime":"2017-03-21","points":"10","typename":"登陆"},{"id":"21592","addtime":"2017-03-20","points":"10","typename":"登陆"},{"id":"21427","addtime":"2017-03-18","points":"10","typename":"登陆"}]
     * pointsAll : 1140
     */

    private int err;
    private String pointsAll;
    private List<DataBean> data;

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

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * id : 23460
         * addtime : 10:00
         * points : 10
         * typename : 登陆
         */

        private String id;
        private String addtime;
        private String points;
        private String typename;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getAddtime() {
            return addtime;
        }

        public void setAddtime(String addtime) {
            this.addtime = addtime;
        }

        public String getPoints() {
            return points;
        }

        public void setPoints(String points) {
            this.points = points;
        }

        public String getTypename() {
            return typename;
        }

        public void setTypename(String typename) {
            this.typename = typename;
        }
    }
}
