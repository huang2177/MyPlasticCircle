package com.myplas.q.addresslist.beans;

import java.util.List;

/**
 * 作者:huangshuang
 * 事件 2017/10/17 0017.
 * 邮箱： 15378412400@163.com
 */

public class ContactInfoBean {

    /**
     * err : 0
     * data : {"user_id":"53620","name":"江海","c_id":"53436","mobile":"18817393322","sex":"男","member_level":"列兵","thumb":"http://pic.myplas.com/myapp/img/male.jpg","thumbqq":"","c_name":"上海中信有限公司","is_follow":"关注","ranking":"350","is_vip":"0","followers":0,"fans":0,"recommendation":"0","supplies":[],"demand":[]}
     */

    private String err;
    private DataBean data;

    public String getErr() {
        return err;
    }

    public void setErr(String err) {
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
         * user_id : 53620
         * name : 江海
         * c_id : 53436
         * mobile : 18817393322
         * sex : 男
         * member_level : 列兵
         * thumb : http://pic.myplas.com/myapp/img/male.jpg
         * thumbqq :
         * c_name : 上海中信有限公司
         * is_follow : 关注
         * ranking : 350
         * is_vip : 0
         * followers : 0
         * fans : 0
         * recommendation : 0
         * supplies : []
         * demand : []
         */

        private String user_id;
        private String name;
        private String c_id;
        private String mobile;
        private String sex;
        private String member_level;
        private String thumb;
        private String thumbqq;
        private String c_name;
        private String is_follow;
        private String ranking;
        private String is_vip;
        private String followers;
        private String fans;
        private String recommendation;
        private List<?> supplies;
        private List<?> demand;

        public String getUser_id() {
            return user_id;
        }

        public void setUser_id(String user_id) {
            this.user_id = user_id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getC_id() {
            return c_id;
        }

        public void setC_id(String c_id) {
            this.c_id = c_id;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public String getSex() {
            return sex;
        }

        public void setSex(String sex) {
            this.sex = sex;
        }

        public String getMember_level() {
            return member_level;
        }

        public void setMember_level(String member_level) {
            this.member_level = member_level;
        }

        public String getThumb() {
            return thumb;
        }

        public void setThumb(String thumb) {
            this.thumb = thumb;
        }

        public String getThumbqq() {
            return thumbqq;
        }

        public void setThumbqq(String thumbqq) {
            this.thumbqq = thumbqq;
        }

        public String getC_name() {
            return c_name;
        }

        public void setC_name(String c_name) {
            this.c_name = c_name;
        }

        public String getIs_follow() {
            return is_follow;
        }

        public void setIs_follow(String is_follow) {
            this.is_follow = is_follow;
        }

        public String getRanking() {
            return ranking;
        }

        public void setRanking(String ranking) {
            this.ranking = ranking;
        }

        public String getIs_vip() {
            return is_vip;
        }

        public void setIs_vip(String is_vip) {
            this.is_vip = is_vip;
        }

        public String getFollowers() {
            return followers;
        }

        public void setFollowers(String followers) {
            this.followers = followers;
        }

        public String getFans() {
            return fans;
        }

        public void setFans(String fans) {
            this.fans = fans;
        }

        public String getRecommendation() {
            return recommendation;
        }

        public void setRecommendation(String recommendation) {
            this.recommendation = recommendation;
        }

        public List<?> getSupplies() {
            return supplies;
        }

        public void setSupplies(List<?> supplies) {
            this.supplies = supplies;
        }

        public List<?> getDemand() {
            return demand;
        }

        public void setDemand(List<?> demand) {
            this.demand = demand;
        }
    }
}
