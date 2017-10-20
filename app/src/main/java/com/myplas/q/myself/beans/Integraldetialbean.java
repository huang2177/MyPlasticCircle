package com.myplas.q.myself.beans;

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
     * msg :
     * data : {"records":[{"time":"2017年8月","record":[{"id":"52651","addtime":"8月21日 18:37","points":"-340","share_type":"0","typename":"兑换礼品","img":""},{"id":"52650","addtime":"8月21日 18:36","points":"-1020","share_type":"0","typename":"兑换礼品","img":""},{"id":"52640","addtime":"8月21日 09:39","points":"10","share_type":"0","typename":"登录","img":"http://pic.myplas.com/myapp/img/scoreFromLogin.png"},{"id":"52634","addtime":"8月18日 22:29","points":"50","share_type":"0","typename":"登录","img":"http://pic.myplas.com/myapp/img/scoreFromLogin.png"},{"id":"52627","addtime":"8月18日 15:28","points":"10","share_type":"0","typename":"发布采购","img":"http://pic.myplas.com/myapp/img/scoreFromIssuePurchase.png"},{"id":"52612","addtime":"8月17日 21:29","points":"40","share_type":"0","typename":"登录","img":"http://pic.myplas.com/myapp/img/scoreFromLogin.png"},{"id":"52587","addtime":"8月16日 13:52","points":"30","share_type":"0","typename":"登录","img":"http://pic.myplas.com/myapp/img/scoreFromLogin.png"},{"id":"52584","addtime":"8月16日 11:28","points":"20","share_type":"0","typename":"发布标准采购","img":"http://pic.myplas.com/myapp/img/scoreFromIssueStandardPurchase.png"},{"id":"52579","addtime":"8月16日 11:09","points":"20","share_type":"0","typename":"发布标准采购","img":"http://pic.myplas.com/myapp/img/scoreFromIssueStandardPurchase.png"},{"id":"52578","addtime":"8月16日 11:09","points":"20","share_type":"0","typename":"发布标准采购","img":"http://pic.myplas.com/myapp/img/scoreFromIssueStandardPurchase.png"},{"id":"52563","addtime":"8月15日 13:39","points":"20","share_type":"0","typename":"登录","img":"http://pic.myplas.com/myapp/img/scoreFromLogin.png"},{"id":"52556","addtime":"8月14日 11:01","points":"10","share_type":"0","typename":"登录","img":"http://pic.myplas.com/myapp/img/scoreFromLogin.png"},{"id":"52548","addtime":"8月11日 18:23","points":"10","share_type":"0","typename":"登录","img":"http://pic.myplas.com/myapp/img/scoreFromLogin.png"},{"id":"52517","addtime":"8月9日 10:04","points":"30","share_type":"0","typename":"登录","img":"http://pic.myplas.com/myapp/img/scoreFromLogin.png"},{"id":"52507","addtime":"8月8日 10:50","points":"20","share_type":"0","typename":"登录","img":"http://pic.myplas.com/myapp/img/scoreFromLogin.png"},{"id":"52503","addtime":"8月7日 12:37","points":"10","share_type":"0","typename":"登录","img":"http://pic.myplas.com/myapp/img/scoreFromLogin.png"},{"id":"52482","addtime":"8月4日 10:05","points":"50","share_type":"0","typename":"登录","img":"http://pic.myplas.com/myapp/img/scoreFromLogin.png"},{"id":"52475","addtime":"8月3日 13:45","points":"40","share_type":"0","typename":"登录","img":"http://pic.myplas.com/myapp/img/scoreFromLogin.png"},{"id":"52469","addtime":"8月2日 19:21","points":"30","share_type":"0","typename":"登录","img":"http://pic.myplas.com/myapp/img/scoreFromLogin.png"},{"id":"52464","addtime":"8月2日 10:28","points":"100","share_type":"0","typename":"现金充值","img":"http://pic.myplas.com/myapp/img/scoreFromRecharge.png"},{"id":"52453","addtime":"8月1日 09:41","points":"20","share_type":"0","typename":"登录","img":"http://pic.myplas.com/myapp/img/scoreFromLogin.png"}]}]}
     * points : 1905
     */

    private int err;
    private String msg;
    private DataBean data;
    private String points;

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

    public String getPoints() {
        return points;
    }

    public void setPoints(String points) {
        this.points = points;
    }

    public static class DataBean {
        private List<RecordsBean> records;

        public List<RecordsBean> getRecords() {
            return records;
        }

        public void setRecords(List<RecordsBean> records) {
            this.records = records;
        }

        public static class RecordsBean {
            /**
             * time : 2017年8月
             * record : [{"id":"52651","addtime":"8月21日 18:37","points":"-340","share_type":"0","typename":"兑换礼品","img":""},{"id":"52650","addtime":"8月21日 18:36","points":"-1020","share_type":"0","typename":"兑换礼品","img":""},{"id":"52640","addtime":"8月21日 09:39","points":"10","share_type":"0","typename":"登录","img":"http://pic.myplas.com/myapp/img/scoreFromLogin.png"},{"id":"52634","addtime":"8月18日 22:29","points":"50","share_type":"0","typename":"登录","img":"http://pic.myplas.com/myapp/img/scoreFromLogin.png"},{"id":"52627","addtime":"8月18日 15:28","points":"10","share_type":"0","typename":"发布采购","img":"http://pic.myplas.com/myapp/img/scoreFromIssuePurchase.png"},{"id":"52612","addtime":"8月17日 21:29","points":"40","share_type":"0","typename":"登录","img":"http://pic.myplas.com/myapp/img/scoreFromLogin.png"},{"id":"52587","addtime":"8月16日 13:52","points":"30","share_type":"0","typename":"登录","img":"http://pic.myplas.com/myapp/img/scoreFromLogin.png"},{"id":"52584","addtime":"8月16日 11:28","points":"20","share_type":"0","typename":"发布标准采购","img":"http://pic.myplas.com/myapp/img/scoreFromIssueStandardPurchase.png"},{"id":"52579","addtime":"8月16日 11:09","points":"20","share_type":"0","typename":"发布标准采购","img":"http://pic.myplas.com/myapp/img/scoreFromIssueStandardPurchase.png"},{"id":"52578","addtime":"8月16日 11:09","points":"20","share_type":"0","typename":"发布标准采购","img":"http://pic.myplas.com/myapp/img/scoreFromIssueStandardPurchase.png"},{"id":"52563","addtime":"8月15日 13:39","points":"20","share_type":"0","typename":"登录","img":"http://pic.myplas.com/myapp/img/scoreFromLogin.png"},{"id":"52556","addtime":"8月14日 11:01","points":"10","share_type":"0","typename":"登录","img":"http://pic.myplas.com/myapp/img/scoreFromLogin.png"},{"id":"52548","addtime":"8月11日 18:23","points":"10","share_type":"0","typename":"登录","img":"http://pic.myplas.com/myapp/img/scoreFromLogin.png"},{"id":"52517","addtime":"8月9日 10:04","points":"30","share_type":"0","typename":"登录","img":"http://pic.myplas.com/myapp/img/scoreFromLogin.png"},{"id":"52507","addtime":"8月8日 10:50","points":"20","share_type":"0","typename":"登录","img":"http://pic.myplas.com/myapp/img/scoreFromLogin.png"},{"id":"52503","addtime":"8月7日 12:37","points":"10","share_type":"0","typename":"登录","img":"http://pic.myplas.com/myapp/img/scoreFromLogin.png"},{"id":"52482","addtime":"8月4日 10:05","points":"50","share_type":"0","typename":"登录","img":"http://pic.myplas.com/myapp/img/scoreFromLogin.png"},{"id":"52475","addtime":"8月3日 13:45","points":"40","share_type":"0","typename":"登录","img":"http://pic.myplas.com/myapp/img/scoreFromLogin.png"},{"id":"52469","addtime":"8月2日 19:21","points":"30","share_type":"0","typename":"登录","img":"http://pic.myplas.com/myapp/img/scoreFromLogin.png"},{"id":"52464","addtime":"8月2日 10:28","points":"100","share_type":"0","typename":"现金充值","img":"http://pic.myplas.com/myapp/img/scoreFromRecharge.png"},{"id":"52453","addtime":"8月1日 09:41","points":"20","share_type":"0","typename":"登录","img":"http://pic.myplas.com/myapp/img/scoreFromLogin.png"}]
             */

            private String time;
            private List<RecordBean> record;

            public String getTime() {
                return time;
            }

            public void setTime(String time) {
                this.time = time;
            }

            public List<RecordBean> getRecord() {
                return record;
            }

            public void setRecord(List<RecordBean> record) {
                this.record = record;
            }

            public static class RecordBean {
                /**
                 * id : 52651
                 * addtime : 8月21日 18:37
                 * points : -340
                 * share_type : 0
                 * typename : 兑换礼品
                 * img :
                 */

                private String id;
                private String addtime;
                private String points;
                private String share_type;
                private String typename;
                private String img;

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

                public String getShare_type() {
                    return share_type;
                }

                public void setShare_type(String share_type) {
                    this.share_type = share_type;
                }

                public String getTypename() {
                    return typename;
                }

                public void setTypename(String typename) {
                    this.typename = typename;
                }

                public String getImg() {
                    return img;
                }

                public void setImg(String img) {
                    this.img = img;
                }
            }
        }
    }
}
