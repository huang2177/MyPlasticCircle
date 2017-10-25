package com.myplas.q.sockethelper;

import java.io.Serializable;

/**
 * 作者:huangshuang
 * 事件 2017/10/16 0016.
 * 邮箱： 15378412400@163.com
 */

public class _ConfigBean implements Serializable {


    /**
     * err : 0
     * config : {"host":"116.62.179.6","port":"5672","user_name":"admin","password":"admin","route_key":"","vhost":"client","exchange_type":"direct","flags":2,"queue_config":{"name":"queue.user_53402","passive":false,"durable":false,"exclusive":false,"auto_delete":true},"exchange_config":{"name":"exchange.user","type":"direct","passive":false,"durable":true,"auto_delete":false}}
     * redDot : {"is_socket_connected":"1","unread_mymsg":"17","unread_supply_and_demand":"52","unread_customer":"700","unread_myorder":"0","unread_who_saw_me":"0","unread_recommend_update":"0"}
     */

    private int err;
    public ConfigBean config;
    public RedDotBean redDot;

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

    public static class ConfigBean {
        /**
         * host : 116.62.179.6
         * port : 5672
         * user_name : admin
         * password : admin
         * route_key :
         * vhost : client
         * exchange_type : direct
         * flags : 2
         * queue_config : {"name":"queue.user_53402","passive":false,"durable":false,"exclusive":false,"auto_delete":true}
         * exchange_config : {"name":"exchange.user","type":"direct","passive":false,"durable":true,"auto_delete":false}
         */

        private String host;
        private int port;
        private String user_name;
        private String password;
        private String route_key;
        private String vhost;
        private String exchange_type;
        private int flags;
        private QueueConfigBean queue_config;
        private ExchangeConfigBean exchange_config;

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

        public String getExchange_type() {
            return exchange_type;
        }

        public void setExchange_type(String exchange_type) {
            this.exchange_type = exchange_type;
        }

        public int getFlags() {
            return flags;
        }

        public void setFlags(int flags) {
            this.flags = flags;
        }

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
             * name : queue.user_53402
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
             * name : exchange.user
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

    public static class RedDotBean {
        /**
         * is_socket_connected : 1
         * unread_mymsg : 17
         * unread_supply_and_demand : 52
         * unread_customer : 700
         * unread_myorder : 0
         * unread_who_saw_me : 0
         * unread_recommend_update : 0
         */

        private String is_socket_connected;
        private String unread_mymsg;
        private String unread_supply_and_demand;
        private String unread_customer;
        private String unread_myorder;
        private String unread_who_saw_me;
        private String unread_recommend_update;

        public String getIs_socket_connected() {
            return is_socket_connected;
        }

        public void setIs_socket_connected(String is_socket_connected) {
            this.is_socket_connected = is_socket_connected;
        }

        public String getUnread_mymsg() {
            return unread_mymsg;
        }

        public void setUnread_mymsg(String unread_mymsg) {
            this.unread_mymsg = unread_mymsg;
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
    }
}
