package com.myplas.q.sockethelper;

import java.io.Serializable;
import java.util.List;

/**
 * 作者:huangshuang
 * 事件 2017/10/26 0026.
 * 邮箱： 15378412400@163.com
 */

public class DotBean {


    /**
     * err : 0
     * msg : 更新成功
     * data : {"is_socket_connected":"1","unread_supply_and_demand":"3","unread_customer":"0","unread_myorder":"0","unread_who_saw_me":"0","unread_recommend_update":"0","unread_reply_user_msg":"0","unread_reply_purchase_msg":"8","unread_plastic_msg":"13","unread_purchase_msg":"24","unread_mymsg":"29","toutiao_status":"0","purchase_status":"1","communicate_status":"0"}
     * notice : {"toutiao_content":[],"purchase_content":[{"info":"供：上海 2119 9500.00 现货","id":"196451"},{"info":"供：上海 2119 9500.00 现货","id":"196450"},{"info":"供：上海 2119 9500.00 现货","id":"196449"},{"info":"供：上海 2119 10500.00 现货","id":"196448"},{"info":"供：伊朗 7000F 12000.00 现货","id":"196447"}],"communicate_content":[]}
     */

    private int err;
    private String msg;
    private DataBean data;
    private NoticeBean notice;

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

    public NoticeBean getNotice() {
        return notice;
    }

    public void setNotice(NoticeBean notice) {
        this.notice = notice;
    }

    public static class DataBean {
        /**
         * is_socket_connected : 1
         * unread_supply_and_demand : 3
         * unread_customer : 0
         * unread_myorder : 0
         * unread_who_saw_me : 0
         * unread_recommend_update : 0
         * unread_reply_user_msg : 0
         * unread_reply_purchase_msg : 8
         * unread_plastic_msg : 13
         * unread_purchase_msg : 24
         * unread_mymsg : 29
         * toutiao_status : 0
         * purchase_status : 1
         * communicate_status : 0
         */

        private String is_socket_connected;
        private String unread_supply_and_demand;
        private String unread_customer;
        private String unread_myorder;
        private String unread_who_saw_me;
        private String unread_recommend_update;
        private String unread_reply_user_msg;
        private String unread_reply_purchase_msg;
        private String unread_plastic_msg;
        private String unread_purchase_msg;
        private String unread_mymsg;
        private String toutiao_status;
        private String purchase_status;
        private String communicate_status;

        public String getIs_socket_connected() {
            return is_socket_connected;
        }

        public void setIs_socket_connected(String is_socket_connected) {
            this.is_socket_connected = is_socket_connected;
        }

        public String getUnread_supply_and_demand() {
            return unread_supply_and_demand;
        }

        public void setUnread_supply_and_demand(String unread_supply_and_demand) {
            this.unread_supply_and_demand = unread_supply_and_demand;
        }

        public String getUnread_customer() {
            return unread_customer;
        }

        public void setUnread_customer(String unread_customer) {
            this.unread_customer = unread_customer;
        }

        public String getUnread_myorder() {
            return unread_myorder;
        }

        public void setUnread_myorder(String unread_myorder) {
            this.unread_myorder = unread_myorder;
        }

        public String getUnread_who_saw_me() {
            return unread_who_saw_me;
        }

        public void setUnread_who_saw_me(String unread_who_saw_me) {
            this.unread_who_saw_me = unread_who_saw_me;
        }

        public String getUnread_recommend_update() {
            return unread_recommend_update;
        }

        public void setUnread_recommend_update(String unread_recommend_update) {
            this.unread_recommend_update = unread_recommend_update;
        }

        public String getUnread_reply_user_msg() {
            return unread_reply_user_msg;
        }

        public void setUnread_reply_user_msg(String unread_reply_user_msg) {
            this.unread_reply_user_msg = unread_reply_user_msg;
        }

        public String getUnread_reply_purchase_msg() {
            return unread_reply_purchase_msg;
        }

        public void setUnread_reply_purchase_msg(String unread_reply_purchase_msg) {
            this.unread_reply_purchase_msg = unread_reply_purchase_msg;
        }

        public String getUnread_plastic_msg() {
            return unread_plastic_msg;
        }

        public void setUnread_plastic_msg(String unread_plastic_msg) {
            this.unread_plastic_msg = unread_plastic_msg;
        }

        public String getUnread_purchase_msg() {
            return unread_purchase_msg;
        }

        public void setUnread_purchase_msg(String unread_purchase_msg) {
            this.unread_purchase_msg = unread_purchase_msg;
        }

        public String getUnread_mymsg() {
            return unread_mymsg;
        }

        public void setUnread_mymsg(String unread_mymsg) {
            this.unread_mymsg = unread_mymsg;
        }

        public String getToutiao_status() {
            return toutiao_status;
        }

        public void setToutiao_status(String toutiao_status) {
            this.toutiao_status = toutiao_status;
        }

        public String getPurchase_status() {
            return purchase_status;
        }

        public void setPurchase_status(String purchase_status) {
            this.purchase_status = purchase_status;
        }

        public String getCommunicate_status() {
            return communicate_status;
        }

        public void setCommunicate_status(String communicate_status) {
            this.communicate_status = communicate_status;
        }
    }

    public static class NoticeBean implements Serializable {
        private List<DotBean.NoticeBean.ToutiaoContentBean> toutiao_content;
        private List<DotBean.NoticeBean.PurchaseContentBean> purchase_content;
        private List<DotBean.NoticeBean.CommunicateContentBean> communicate_content;

        public List<DotBean.NoticeBean.ToutiaoContentBean> getToutiao_content() {
            return toutiao_content;
        }

        public void setToutiao_content(List<DotBean.NoticeBean.ToutiaoContentBean> toutiao_content) {
            this.toutiao_content = toutiao_content;
        }

        public List<DotBean.NoticeBean.PurchaseContentBean> getPurchase_content() {
            return purchase_content;
        }

        public void setPurchase_content(List<DotBean.NoticeBean.PurchaseContentBean> purchase_content) {
            this.purchase_content = purchase_content;
        }

        public List<DotBean.NoticeBean.CommunicateContentBean> getCommunicate_content() {
            return communicate_content;
        }

        public void setCommunicate_content(List<DotBean.NoticeBean.CommunicateContentBean> communicate_content) {
            this.communicate_content = communicate_content;
        }

        public static class ToutiaoContentBean implements Serializable {
            /**
             * info : 公众号行情中心 ：价格变动
             * id : 59346
             */

            private String info;
            private String id;
            private String free;

            public void setFree(String free) {
                this.free = free;
            }

            public String getFree() {
                return free;
            }

            public String getInfo() {
                return info;
            }

            public void setInfo(String info) {
                this.info = info;
            }

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }
        }

        public static class PurchaseContentBean implements Serializable {
            /**
             * info : 供：上海 2119 8655.00 期货
             * id : 196483
             * user_id : 53991
             */

            private String info;
            private String id;
            private String user_id;

            public String getInfo() {
                return info;
            }

            public void setInfo(String info) {
                this.info = info;
            }

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getUser_id() {
                return user_id;
            }

            public void setUser_id(String user_id) {
                this.user_id = user_id;
            }
        }

        public static class CommunicateContentBean implements Serializable {
            /**
             * info : 安徽正枘塑料包装有限司加入塑料圈
             * id : 80990
             */

            private String info;
            private String id;
            private String merge_three;

            public void setMerge_three(String merge_three) {
                this.merge_three = merge_three;
            }

            public String getMerge_three() {
                return merge_three;
            }

            public String getInfo() {
                return info;
            }

            public void setInfo(String info) {
                this.info = info;
            }

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }
        }
    }
}
