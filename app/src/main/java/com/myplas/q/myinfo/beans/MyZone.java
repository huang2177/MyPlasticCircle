package com.myplas.q.myinfo.beans;

import java.io.Serializable;
import java.util.List;

/**
 * 编写： 黄双
 * 电话：15378412400
 * 邮箱：15378412400@163.com
 * 时间：2017/3/27 16:08
 */
public class MyZone implements Serializable{

    /**
     * err : 0
     * s_in_count : 12
     * s_out_count : 23
     * points : 1875
     * leaveword : 0
     * message : 0
     * introduction : 0
     * myfans : 5
     * myconcerns : 4
     * data : {"user_id":"53402","name":"hh","c_id":"5041","mobile":"15378412400","is_pass":"0","thumb":"http://pic.myplas.com/upload/17/06/02/5930dda0bd3b9.jpg","thumbqq":"http://pic.myplas.com/upload/17/06/02/5930dda0bd3b9.jpg","thumbcard":"http://pic.myplas.com/upload/17/05/31/592e692398985.jpg","c_name":"上海中晨电子商务股份有限公司","credit_level":"AAAAA","credit_limit":"600.00","is_credit":"1","pre_credit_limit":"600.00","credit_time":"1488357059","sex":"0"}
     * help : ["http://statics.myplas.com/myapp/img/h1.jpg","http://statics.myplas.com/myapp/img/h2.jpg","http://statics.myplas.com/myapp/img/h3.jpg","http://statics.myplas.com/myapp/img/h4.jpg","http://statics.myplas.com/myapp/img/h5.jpg"]
     */

    private int err;
    private String s_in_count;
    private String s_out_count;
    private String points;
    private int leaveword;
    private int message;
    private int introduction;
    private int myfans;
    private int myconcerns;
    private DataBean data;
    private List<String> help;

    public int getErr() {
        return err;
    }

    public void setErr(int err) {
        this.err = err;
    }

    public String getS_in_count() {
        return s_in_count;
    }

    public void setS_in_count(String s_in_count) {
        this.s_in_count = s_in_count;
    }

    public String getS_out_count() {
        return s_out_count;
    }

    public void setS_out_count(String s_out_count) {
        this.s_out_count = s_out_count;
    }

    public String getPoints() {
        return points;
    }

    public void setPoints(String points) {
        this.points = points;
    }

    public int getLeaveword() {
        return leaveword;
    }

    public void setLeaveword(int leaveword) {
        this.leaveword = leaveword;
    }

    public int getMessage() {
        return message;
    }

    public void setMessage(int message) {
        this.message = message;
    }

    public int getIntroduction() {
        return introduction;
    }

    public void setIntroduction(int introduction) {
        this.introduction = introduction;
    }

    public int getMyfans() {
        return myfans;
    }

    public void setMyfans(int myfans) {
        this.myfans = myfans;
    }

    public int getMyconcerns() {
        return myconcerns;
    }

    public void setMyconcerns(int myconcerns) {
        this.myconcerns = myconcerns;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public List<String> getHelp() {
        return help;
    }

    public void setHelp(List<String> help) {
        this.help = help;
    }

    public static class DataBean {
        /**
         * user_id : 53402
         * name : hh
         * c_id : 5041
         * mobile : 15378412400
         * is_pass : 0
         * thumb : http://pic.myplas.com/upload/17/06/02/5930dda0bd3b9.jpg
         * thumbqq : http://pic.myplas.com/upload/17/06/02/5930dda0bd3b9.jpg
         * thumbcard : http://pic.myplas.com/upload/17/05/31/592e692398985.jpg
         * c_name : 上海中晨电子商务股份有限公司
         * credit_level : AAAAA
         * credit_limit : 600.00
         * is_credit : 1
         * pre_credit_limit : 600.00
         * credit_time : 1488357059
         * sex : 0
         */

        private String user_id;
        private String name;
        private String c_id;
        private String mobile;
        private String is_pass;
        private String thumb;
        private String thumbqq;
        private String thumbcard;
        private String c_name;
        private String credit_level;
        private String credit_limit;
        private String is_credit;
        private String pre_credit_limit;
        private String credit_time;
        private String sex;

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

        public String getIs_pass() {
            return is_pass;
        }

        public void setIs_pass(String is_pass) {
            this.is_pass = is_pass;
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

        public String getThumbcard() {
            return thumbcard;
        }

        public void setThumbcard(String thumbcard) {
            this.thumbcard = thumbcard;
        }

        public String getC_name() {
            return c_name;
        }

        public void setC_name(String c_name) {
            this.c_name = c_name;
        }

        public String getCredit_level() {
            return credit_level;
        }

        public void setCredit_level(String credit_level) {
            this.credit_level = credit_level;
        }

        public String getCredit_limit() {
            return credit_limit;
        }

        public void setCredit_limit(String credit_limit) {
            this.credit_limit = credit_limit;
        }

        public String getIs_credit() {
            return is_credit;
        }

        public void setIs_credit(String is_credit) {
            this.is_credit = is_credit;
        }

        public String getPre_credit_limit() {
            return pre_credit_limit;
        }

        public void setPre_credit_limit(String pre_credit_limit) {
            this.pre_credit_limit = pre_credit_limit;
        }

        public String getCredit_time() {
            return credit_time;
        }

        public void setCredit_time(String credit_time) {
            this.credit_time = credit_time;
        }

        public String getSex() {
            return sex;
        }

        public void setSex(String sex) {
            this.sex = sex;
        }
    }
}
