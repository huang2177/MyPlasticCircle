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
     */

    private String err;
    private ConfigBean config;

    public String getErr() {
        return err;
    }

    public void setErr(String err) {
        this.err = err;
    }

    public ConfigBean getConfig() {
        return config;
    }

    public void setConfig(ConfigBean config) {
        this.config = config;
    }

    public class ConfigBean implements Serializable {
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
        private String flags;
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

        public String getFlags() {
            return flags;
        }

        public void setFlags(String flags) {
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

        public class QueueConfigBean implements Serializable {
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

        public class ExchangeConfigBean {
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
}
