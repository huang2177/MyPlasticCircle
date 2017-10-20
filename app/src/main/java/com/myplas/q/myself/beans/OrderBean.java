package com.myplas.q.myself.beans;

import com.google.gson.annotations.SerializedName;

/**
 * 编写： 黄双
 * 电话：15378412400
 * 邮箱：15378412400@163.com
 * 时间：2017/5/24 16:38
 */
public class OrderBean {


    /**
     * err : 0
     * msg : 订单生成成功
     * data : {"appid":"wxc0eb2ef58d5df955","noncestr":"wp2EP75XphXzjqiRe5MqYDMSXxl5uioq","package":"Sign=WXPay","partnerid":"1473441002","prepayid":"wx20170606112500649488bbe60351571135","timestamp":1496719501,"sign":"6121E69C5EC299AE2F6DD553C73A508E"}
     */

    private int err;
    private String msg;
    private DataBean data;
    /**
     * order_id : 2017060648519710
     */

    private String order_id;

    public int getErr() {
        return err;
    }

    public void setErr(int err) {
        this.err = err;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public String getOrder_id() {
        return order_id;
    }

    public void setOrder_id(String order_id) {
        this.order_id = order_id;
    }

    public static class DataBean {
        /**
         * appid : wxc0eb2ef58d5df955
         * noncestr : wp2EP75XphXzjqiRe5MqYDMSXxl5uioq
         * package : Sign=WXPay
         * partnerid : 1473441002
         * prepayid : wx20170606112500649488bbe60351571135
         * timestamp : 1496719501
         * sign : 6121E69C5EC299AE2F6DD553C73A508E
         */

        private String appid;
        private String noncestr;
        @SerializedName("package")
        private String packageX;
        private String partnerid;
        private String prepayid;
        private int timestamp;
        private String sign;

        public String getAppid() {
            return appid;
        }

        public void setAppid(String appid) {
            this.appid = appid;
        }

        public String getNoncestr() {
            return noncestr;
        }

        public void setNoncestr(String noncestr) {
            this.noncestr = noncestr;
        }

        public String getPackageX() {
            return packageX;
        }

        public void setPackageX(String packageX) {
            this.packageX = packageX;
        }

        public String getPartnerid() {
            return partnerid;
        }

        public void setPartnerid(String partnerid) {
            this.partnerid = partnerid;
        }

        public String getPrepayid() {
            return prepayid;
        }

        public void setPrepayid(String prepayid) {
            this.prepayid = prepayid;
        }

        public int getTimestamp() {
            return timestamp;
        }

        public void setTimestamp(int timestamp) {
            this.timestamp = timestamp;
        }

        public String getSign() {
            return sign;
        }

        public void setSign(String sign) {
            this.sign = sign;
        }
    }
}
