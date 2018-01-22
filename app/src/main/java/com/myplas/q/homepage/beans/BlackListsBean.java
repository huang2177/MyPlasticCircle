package com.myplas.q.homepage.beans;

import java.util.List;

/**
 * @author 黄双
 * @date 2018/1/22 0022
 */

public class BlackListsBean {

    /**
     * code : 0
     * blacklists : [{"id":25,"subject":"大概好久看囧囧囧","created_at":"1月22日 15:46","updated_at":0,"pv":535,"illustration":"","status":1},{"id":24,"subject":"大概好久看囧囧囧","created_at":"1月22日 15:46","updated_at":0,"pv":535,"illustration":"http://myplas.ufile.ucloud.com.cn/upload/2018/01/o3mtw8k98m.jpg","status":1},{"id":23,"subject":"大概好久看囧囧囧","created_at":"1月22日 15:46","updated_at":0,"pv":535,"illustration":"http://myplas.ufile.ucloud.com.cn/upload/2018/01/o3mtw8k98m.jpg","status":1},{"id":22,"subject":"大概好久看囧囧囧","created_at":"1月22日 15:46","updated_at":0,"pv":535,"illustration":"","status":1},{"id":21,"subject":"大概好久看囧囧囧","created_at":"1月22日 15:46","updated_at":0,"pv":535,"illustration":"","status":1},{"id":20,"subject":"大概好久看囧囧囧","created_at":"1月22日 15:46","updated_at":0,"pv":535,"illustration":"http://myplas.ufile.ucloud.com.cn/upload/2018/01/o3mtw8k98m.jpg","status":1},{"id":19,"subject":"大概好久看囧囧囧","created_at":"1月22日 15:46","updated_at":0,"pv":535,"illustration":"","status":1},{"id":18,"subject":"大概好久看囧囧囧","created_at":"1月22日 15:46","updated_at":0,"pv":535,"illustration":"","status":1},{"id":17,"subject":"大概好久看囧囧囧","created_at":"1月22日 15:46","updated_at":0,"pv":535,"illustration":"http://myplas.ufile.ucloud.com.cn/upload/2018/01/o3mtw8k98m.jpg","status":1},{"id":16,"subject":"大概好久看囧囧囧","created_at":"1月22日 15:46","updated_at":0,"pv":535,"illustration":"http://myplas.ufile.ucloud.com.cn/upload/2018/01/o3mtw8k98m.jpg","status":1}]
     */

    private String code;
    private List<BlacklistsBean> blacklists;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public List<BlacklistsBean> getBlacklists() {
        return blacklists;
    }

    public void setBlacklists(List<BlacklistsBean> blacklists) {
        this.blacklists = blacklists;
    }

    public static class BlacklistsBean {
        /**
         * id : 25
         * subject : 大概好久看囧囧囧
         * created_at : 1月22日 15:46
         * updated_at : 0
         * pv : 535
         * illustration :
         * status : 1
         */

        private String id;
        private String subject;
        private String created_at;
        private String updated_at;
        private String pv;
        private String illustration;
        private String status;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getSubject() {
            return subject;
        }

        public void setSubject(String subject) {
            this.subject = subject;
        }

        public String getCreated_at() {
            return created_at;
        }

        public void setCreated_at(String created_at) {
            this.created_at = created_at;
        }

        public String getUpdated_at() {
            return updated_at;
        }

        public void setUpdated_at(String updated_at) {
            this.updated_at = updated_at;
        }

        public String getPv() {
            return pv;
        }

        public void setPv(String pv) {
            this.pv = pv;
        }

        public String getIllustration() {
            return illustration;
        }

        public void setIllustration(String illustration) {
            this.illustration = illustration;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }
    }
}
