package com.myplas.q.myinfo.beans;

/**
 * 编写： 黄双
 * 电话：15378412400
 * 邮箱：15378412400@163.com
 * 时间：2017/3/28 10:18
 */
public class VersionBean {

    /**
     * err : 0
     * data : {"serverFlag":"date_selected","lastForce":"date_selected","updateUrl":"http://www.myplas.com","upgradeInfo":"更新了，哈哈sssssssssssss"}
     */

    private int err;
    private DataBean data;

    public int getErr() {
        return err;
    }

    public void setErr(int err) {
        this.err = err;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * serverFlag : date_selected
         * lastForce : date_selected
         * updateUrl : http://www.myplas.com
         * upgradeInfo : 更新了，哈哈sssssssssssss
         */

        private String serverFlag;
        private String lastForce;
        private String updateUrl;
        private String upgradeInfo;

        public String getServerFlag() {
            return serverFlag;
        }

        public void setServerFlag(String serverFlag) {
            this.serverFlag = serverFlag;
        }

        public String getLastForce() {
            return lastForce;
        }

        public void setLastForce(String lastForce) {
            this.lastForce = lastForce;
        }

        public String getUpdateUrl() {
            return updateUrl;
        }

        public void setUpdateUrl(String updateUrl) {
            this.updateUrl = updateUrl;
        }

        public String getUpgradeInfo() {
            return upgradeInfo;
        }

        public void setUpgradeInfo(String upgradeInfo) {
            this.upgradeInfo = upgradeInfo;
        }
    }
}
