package com.myplas.q.sockethelper;

import java.io.Serializable;
import java.util.List;

/**
 * 作者:huangshuang
 * 事件 2017/10/16 0016.
 * 邮箱： 15378412400@163.com
 */

public class DefConfigBean implements Serializable {


    /**
     * err : 0
     * config : {"host":"116.62.179.6","port":15674,"user_name":"admin","password":"admin","route_key":"","vhost":"test_client","flags":"","single":{"queue_config":{"name":"queue.single_41497","passive":false,"durable":false,"exclusive":false,"auto_delete":true},"exchange_config":{"name":"exchange.single","type":"direct","passive":false,"durable":true,"auto_delete":false}},"all":{"queue_config":{"name":"queue.all_41497","passive":false,"durable":false,"exclusive":false,"auto_delete":true},"exchange_config":{"name":"exchange.all","type":"fanout","passive":false,"durable":true,"auto_delete":false}}}
     * redDot : {"is_socket_connected":1,"unread_supply_and_demand":5,"unread_customer":0,"unread_myorder":0,"unread_who_saw_me":0,"unread_recommend_update":0,"unread_reply_user_msg":0,"unread_reply_purchase_msg":1,"unread_plastic_msg":0,"unread_purchase_msg":23,"unread_mymsg":1}
     * notice : {"toutiao_content":[{"info":"公众号行情中心 ：价格变动","id":59346},{"info":"期货资讯：关于增设线型低密度聚乙烯、聚丙烯指定交割仓库的通知","id":59345},{"info":"装置动态：国内PE装置动态报道汇总（20171103）","id":59344},{"info":"美金市场：PE进口市场线性价格即时报盘（20171103）","id":59343},{"info":"企业动态：中景石化PP价格报道","id":59342}],"purchase_content":[{"info":"供：上海 2119 8655.00 期货","id":196483,"user_id":53991},{"info":"供：上海 2119 8655.00 期货","id":196482,"user_id":53991},{"info":"供：上海 2119 8655.00 期货","id":196481,"user_id":53991},{"info":"供：上海 2119 8655.00 期货","id":196480,"user_id":53991},{"info":"供：上海 2119 8655.00 期货","id":196479,"user_id":53991}],"communicate_content":[{"info":"安徽正枘塑料包装有限司加入塑料圈 ","id":80990},{"info":"上海测试封装有限公司加入塑料圈 ","id":80989},{"info":"无锡市元捷塑业有限公司加入塑料圈 ","id":80988},{"info":"哈哈哈哈哈哈加入塑料圈 ","id":80987},{"info":"浙江长兴紫鑫科技有限公司加入塑料圈 ","id":80986}]}
     */

    private int err;
    private ConfigBean config;
    private RedDotBean redDot;
    private NoticeBean notice;

    public int getErr() {
        return err;
    }

    public void setErr(int err) {
        this.err = err;
    }

    public ConfigBean getConfig() {
        return config;
    }

    public void setConfig(ConfigBean config) {
        this.config = config;
    }

    public RedDotBean getRedDot() {
        return redDot;
    }

    public void setRedDot(RedDotBean redDot) {
        this.redDot = redDot;
    }

    public NoticeBean getNotice() {
        return notice;
    }

    public void setNotice(NoticeBean notice) {
        this.notice = notice;
    }

    public static class ConfigBean {
        /**
         * host : 116.62.179.6
         * port : 15674
         * user_name : admin
         * password : admin
         * route_key :
         * vhost : test_client
         * flags :
         * single : {"queue_config":{"name":"queue.single_41497","passive":false,"durable":false,"exclusive":false,"auto_delete":true},"exchange_config":{"name":"exchange.single","type":"direct","passive":false,"durable":true,"auto_delete":false}}
         * all : {"queue_config":{"name":"queue.all_41497","passive":false,"durable":false,"exclusive":false,"auto_delete":true},"exchange_config":{"name":"exchange.all","type":"fanout","passive":false,"durable":true,"auto_delete":false}}
         */

        private String host;
        private int port;
        private String user_name;
        private String password;
        private String route_key;
        private String vhost;
        private String flags;
        private SingleBean single;
        private AllBean all;

        public String getHost() {
            return host;
        }

        public void setHost(String host) {
            this.host = host;
        }

        public int getPort() {
            return port;
        }

        public void setPort(int port) {
            this.port = port;
        }

        public String getUser_name() {
            return user_name;
        }

        public void setUser_name(String user_name) {
            this.user_name = user_name;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getRoute_key() {
            return route_key;
        }

        public void setRoute_key(String route_key) {
            this.route_key = route_key;
        }

        public String getVhost() {
            return vhost;
        }

        public void setVhost(String vhost) {
            this.vhost = vhost;
        }

        public String getFlags() {
            return flags;
        }

        public void setFlags(String flags) {
            this.flags = flags;
        }

        public SingleBean getSingle() {
            return single;
        }

        public void setSingle(SingleBean single) {
            this.single = single;
        }

        public AllBean getAll() {
            return all;
        }

        public void setAll(AllBean all) {
            this.all = all;
        }

        public static class SingleBean {
            /**
             * queue_config : {"name":"queue.single_41497","passive":false,"durable":false,"exclusive":false,"auto_delete":true}
             * exchange_config : {"name":"exchange.single","type":"direct","passive":false,"durable":true,"auto_delete":false}
             */

            private QueueConfigBean queue_config;
            private ExchangeConfigBean exchange_config;

            public QueueConfigBean getQueue_config() {
                return queue_config;
            }

            public void setQueue_config(QueueConfigBean queue_config) {
                this.queue_config = queue_config;
            }

            public ExchangeConfigBean getExchange_config() {
                return exchange_config;
            }

            public void setExchange_config(ExchangeConfigBean exchange_config) {
                this.exchange_config = exchange_config;
            }

            public static class QueueConfigBean {
                /**
                 * name : queue.single_41497
                 * passive : false
                 * durable : false
                 * exclusive : false
                 * auto_delete : true
                 */

                private String name;
                private boolean passive;
                private boolean durable;
                private boolean exclusive;
                private boolean auto_delete;

                public String getName() {
                    return name;
                }

                public void setName(String name) {
                    this.name = name;
                }

                public boolean isPassive() {
                    return passive;
                }

                public void setPassive(boolean passive) {
                    this.passive = passive;
                }

                public boolean isDurable() {
                    return durable;
                }

                public void setDurable(boolean durable) {
                    this.durable = durable;
                }

                public boolean isExclusive() {
                    return exclusive;
                }

                public void setExclusive(boolean exclusive) {
                    this.exclusive = exclusive;
                }

                public boolean isAuto_delete() {
                    return auto_delete;
                }

                public void setAuto_delete(boolean auto_delete) {
                    this.auto_delete = auto_delete;
                }
            }

            public static class ExchangeConfigBean {
                /**
                 * name : exchange.single
                 * type : direct
                 * passive : false
                 * durable : true
                 * auto_delete : false
                 */

                private String name;
                private String type;
                private boolean passive;
                private boolean durable;
                private boolean auto_delete;

                public String getName() {
                    return name;
                }

                public void setName(String name) {
                    this.name = name;
                }

                public String getType() {
                    return type;
                }

                public void setType(String type) {
                    this.type = type;
                }

                public boolean isPassive() {
                    return passive;
                }

                public void setPassive(boolean passive) {
                    this.passive = passive;
                }

                public boolean isDurable() {
                    return durable;
                }

                public void setDurable(boolean durable) {
                    this.durable = durable;
                }

                public boolean isAuto_delete() {
                    return auto_delete;
                }

                public void setAuto_delete(boolean auto_delete) {
                    this.auto_delete = auto_delete;
                }
            }
        }

        public static class AllBean {
            /**
             * queue_config : {"name":"queue.all_41497","passive":false,"durable":false,"exclusive":false,"auto_delete":true}
             * exchange_config : {"name":"exchange.all","type":"fanout","passive":false,"durable":true,"auto_delete":false}
             */

            private QueueConfigBeanX queue_config;
            private ExchangeConfigBeanX exchange_config;

            public QueueConfigBeanX getQueue_config() {
                return queue_config;
            }

            public void setQueue_config(QueueConfigBeanX queue_config) {
                this.queue_config = queue_config;
            }

            public ExchangeConfigBeanX getExchange_config() {
                return exchange_config;
            }

            public void setExchange_config(ExchangeConfigBeanX exchange_config) {
                this.exchange_config = exchange_config;
            }

            public static class QueueConfigBeanX {
                /**
                 * name : queue.all_41497
                 * passive : false
                 * durable : false
                 * exclusive : false
                 * auto_delete : true
                 */

                private String name;
                private boolean passive;
                private boolean durable;
                private boolean exclusive;
                private boolean auto_delete;

                public String getName() {
                    return name;
                }

                public void setName(String name) {
                    this.name = name;
                }

                public boolean isPassive() {
                    return passive;
                }

                public void setPassive(boolean passive) {
                    this.passive = passive;
                }

                public boolean isDurable() {
                    return durable;
                }

                public void setDurable(boolean durable) {
                    this.durable = durable;
                }

                public boolean isExclusive() {
                    return exclusive;
                }

                public void setExclusive(boolean exclusive) {
                    this.exclusive = exclusive;
                }

                public boolean isAuto_delete() {
                    return auto_delete;
                }

                public void setAuto_delete(boolean auto_delete) {
                    this.auto_delete = auto_delete;
                }
            }

            public static class ExchangeConfigBeanX {
                /**
                 * name : exchange.all
                 * type : fanout
                 * passive : false
                 * durable : true
                 * auto_delete : false
                 */

                private String name;
                private String type;
                private boolean passive;
                private boolean durable;
                private boolean auto_delete;

                public String getName() {
                    return name;
                }

                public void setName(String name) {
                    this.name = name;
                }

                public String getType() {
                    return type;
                }

                public void setType(String type) {
                    this.type = type;
                }

                public boolean isPassive() {
                    return passive;
                }

                public void setPassive(boolean passive) {
                    this.passive = passive;
                }

                public boolean isDurable() {
                    return durable;
                }

                public void setDurable(boolean durable) {
                    this.durable = durable;
                }

                public boolean isAuto_delete() {
                    return auto_delete;
                }

                public void setAuto_delete(boolean auto_delete) {
                    this.auto_delete = auto_delete;
                }
            }
        }
    }

    public static class RedDotBean {
        /**
         * is_socket_connected : 1
         * unread_supply_and_demand : 156
         * unread_customer : 123
         * unread_myorder : 0
         * unread_who_saw_me : 0
         * unread_recommend_update : 0
         * unread_reply_user_msg : 0
         * unread_reply_purchase_msg : 0
         * unread_plastic_msg : 0
         * unread_purchase_msg : 8
         * unread_mymsg : 0
         * toutiao_status : 0
         * purchase_status : 1
         * communicate_status : 0
         * toutiao_content : []
         * purchase_content : ["供：u4e0au6d77 2119 9800.00 u73b0u8d27","供：u4e0au6d77 2119 95000.00 u73b0u8d27","供：u4e0au6d77 2119 95000.00 u73b0u8d27","供：u4e0au6d77 2119 95000.00 u73b0u8d27","供：u4e0au6d77 2119 95000.00 u73b0u8d27"]
         * communicate_content : []
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

    }

    public static class NoticeBean implements Serializable {
        private List<ToutiaoContentBean> toutiao_content;
        private List<PurchaseContentBean> purchase_content;
        private List<CommunicateContentBean> communicate_content;

        public List<ToutiaoContentBean> getToutiao_content() {
            return toutiao_content;
        }

        public void setToutiao_content(List<ToutiaoContentBean> toutiao_content) {
            this.toutiao_content = toutiao_content;
        }

        public List<PurchaseContentBean> getPurchase_content() {
            return purchase_content;
        }

        public void setPurchase_content(List<PurchaseContentBean> purchase_content) {
            this.purchase_content = purchase_content;
        }

        public List<CommunicateContentBean> getCommunicate_content() {
            return communicate_content;
        }

        public void setCommunicate_content(List<CommunicateContentBean> communicate_content) {
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
