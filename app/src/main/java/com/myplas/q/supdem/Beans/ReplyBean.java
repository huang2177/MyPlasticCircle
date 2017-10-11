package com.myplas.q.supdem.Beans;

import java.util.List;

/**
 * 编写： 黄双
 * 电话：15378412400
 * 邮箱：15378412400@163.com
 * 时间：2017/3/31 20:18
 */
public class ReplyBean {


    /**
     * err : 0
     * data : [{"id":"1706","rev_id":"3858","user_id":"53397","is_read":"1","content":"多少吨？","input_time":"10-10 10:07","pur_id":"114111","reply_id":"0","name":"测试","thumb":"http://pic.myplas.com/upload/17/09/14/59b9f291e30dc.png","c_name":"上海测试科技","mobile":"18817391111","thumbqq":"","is_pass":"0","replay":[{"id":"1733","rev_id":"9266","reply_id":"1730","user_id":"3858","is_read":"1","content":"YAO5","input_time":"01-01 08:00:00","pur_id":"114111","name":"成平","is_pass":"0","mobile":"13701995655","thumb":"17/09/13/59b8f2d78f813.jpg","thumbqq":"http://statics.myplas.com/upload/16/10/25/580ebd01ea3db.png","c_name":"上海梓辰实业有限公司","hui_name":"李一帆"},{"id":"1734","rev_id":"53397","reply_id":"1731","user_id":"3858","is_read":"1","content":"YAO8","input_time":"01-01 08:00:00","pur_id":"114111","name":"测试","is_pass":"0","mobile":"18817391111","thumb":"17/09/14/59b9f291e30dc.png","thumbqq":"","c_name":"上海测试科技","hui_name":"李一帆"},{"id":"1730","rev_id":"9266","reply_id":"1707","user_id":"3858","is_read":"1","content":"yao5","input_time":"10-10 16:22:25","pur_id":"114111","name":"成平","is_pass":"0","mobile":"13701995655","thumb":"17/09/13/59b8f2d78f813.jpg","thumbqq":"http://statics.myplas.com/upload/16/10/25/580ebd01ea3db.png","c_name":"上海梓辰实业有限公司","hui_name":"李一帆"},{"id":"1731","rev_id":"53397","reply_id":"1708","user_id":"3858","is_read":"1","content":"yao8","input_time":"10-10 16:22:27","pur_id":"114111","name":"测试","is_pass":"0","mobile":"18817391111","thumb":"17/09/14/59b9f291e30dc.png","thumbqq":"","c_name":"上海测试科技","hui_name":"李一帆"},{"id":"1732","rev_id":"9266","reply_id":"1717","user_id":"3858","is_read":"1","content":"yao10","input_time":"10-10 16:22:30","pur_id":"114111","name":"成平","is_pass":"0","mobile":"13701995655","thumb":"17/09/13/59b8f2d78f813.jpg","thumbqq":"http://statics.myplas.com/upload/16/10/25/580ebd01ea3db.png","c_name":"上海梓辰实业有限公司","hui_name":"李一帆"}]}]
     */

    private int err;
    private List<DataBean> data;

    public int getErr() {
        return err;
    }

    public void setErr(int err) {
        this.err = err;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * id : 1706
         * rev_id : 3858
         * user_id : 53397
         * is_read : 1
         * content : 多少吨？
         * input_time : 10-10 10:07
         * pur_id : 114111
         * reply_id : 0
         * name : 测试
         * thumb : http://pic.myplas.com/upload/17/09/14/59b9f291e30dc.png
         * c_name : 上海测试科技
         * mobile : 18817391111
         * thumbqq :
         * is_pass : 0
         * replay : [{"id":"1733","rev_id":"9266","reply_id":"1730","user_id":"3858","is_read":"1","content":"YAO5","input_time":"01-01 08:00:00","pur_id":"114111","name":"成平","is_pass":"0","mobile":"13701995655","thumb":"17/09/13/59b8f2d78f813.jpg","thumbqq":"http://statics.myplas.com/upload/16/10/25/580ebd01ea3db.png","c_name":"上海梓辰实业有限公司","hui_name":"李一帆"},{"id":"1734","rev_id":"53397","reply_id":"1731","user_id":"3858","is_read":"1","content":"YAO8","input_time":"01-01 08:00:00","pur_id":"114111","name":"测试","is_pass":"0","mobile":"18817391111","thumb":"17/09/14/59b9f291e30dc.png","thumbqq":"","c_name":"上海测试科技","hui_name":"李一帆"},{"id":"1730","rev_id":"9266","reply_id":"1707","user_id":"3858","is_read":"1","content":"yao5","input_time":"10-10 16:22:25","pur_id":"114111","name":"成平","is_pass":"0","mobile":"13701995655","thumb":"17/09/13/59b8f2d78f813.jpg","thumbqq":"http://statics.myplas.com/upload/16/10/25/580ebd01ea3db.png","c_name":"上海梓辰实业有限公司","hui_name":"李一帆"},{"id":"1731","rev_id":"53397","reply_id":"1708","user_id":"3858","is_read":"1","content":"yao8","input_time":"10-10 16:22:27","pur_id":"114111","name":"测试","is_pass":"0","mobile":"18817391111","thumb":"17/09/14/59b9f291e30dc.png","thumbqq":"","c_name":"上海测试科技","hui_name":"李一帆"},{"id":"1732","rev_id":"9266","reply_id":"1717","user_id":"3858","is_read":"1","content":"yao10","input_time":"10-10 16:22:30","pur_id":"114111","name":"成平","is_pass":"0","mobile":"13701995655","thumb":"17/09/13/59b8f2d78f813.jpg","thumbqq":"http://statics.myplas.com/upload/16/10/25/580ebd01ea3db.png","c_name":"上海梓辰实业有限公司","hui_name":"李一帆"}]
         */

        private String id;
        private String rev_id;
        private String user_id;
        private String is_read;
        private String content;
        private String input_time;
        private String pur_id;
        private String reply_id;
        private String name;
        private String thumb;
        private String c_name;
        private String mobile;
        private String thumbqq;
        private String is_pass;
        private List<ReplayBean> replay;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getRev_id() {
            return rev_id;
        }

        public void setRev_id(String rev_id) {
            this.rev_id = rev_id;
        }

        public String getUser_id() {
            return user_id;
        }

        public void setUser_id(String user_id) {
            this.user_id = user_id;
        }

        public String getIs_read() {
            return is_read;
        }

        public void setIs_read(String is_read) {
            this.is_read = is_read;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getInput_time() {
            return input_time;
        }

        public void setInput_time(String input_time) {
            this.input_time = input_time;
        }

        public String getPur_id() {
            return pur_id;
        }

        public void setPur_id(String pur_id) {
            this.pur_id = pur_id;
        }

        public String getReply_id() {
            return reply_id;
        }

        public void setReply_id(String reply_id) {
            this.reply_id = reply_id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getThumb() {
            return thumb;
        }

        public void setThumb(String thumb) {
            this.thumb = thumb;
        }

        public String getC_name() {
            return c_name;
        }

        public void setC_name(String c_name) {
            this.c_name = c_name;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public String getThumbqq() {
            return thumbqq;
        }

        public void setThumbqq(String thumbqq) {
            this.thumbqq = thumbqq;
        }

        public String getIs_pass() {
            return is_pass;
        }

        public void setIs_pass(String is_pass) {
            this.is_pass = is_pass;
        }

        public List<ReplayBean> getReplay() {
            return replay;
        }

        public void setReplay(List<ReplayBean> replay) {
            this.replay = replay;
        }

        public static class ReplayBean {
            /**
             * id : 1733
             * rev_id : 9266
             * reply_id : 1730
             * user_id : 3858
             * is_read : 1
             * content : YAO5
             * input_time : 01-01 08:00:00
             * pur_id : 114111
             * name : 成平
             * is_pass : 0
             * mobile : 13701995655
             * thumb : 17/09/13/59b8f2d78f813.jpg
             * thumbqq : http://statics.myplas.com/upload/16/10/25/580ebd01ea3db.png
             * c_name : 上海梓辰实业有限公司
             * hui_name : 李一帆
             */

            private String id;
            private String rev_id;
            private String reply_id;
            private String user_id;
            private String is_read;
            private String content;
            private String input_time;
            private String pur_id;
            private String name;
            private String is_pass;
            private String mobile;
            private String thumb;
            private String thumbqq;
            private String c_name;
            private String hui_name;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getRev_id() {
                return rev_id;
            }

            public void setRev_id(String rev_id) {
                this.rev_id = rev_id;
            }

            public String getReply_id() {
                return reply_id;
            }

            public void setReply_id(String reply_id) {
                this.reply_id = reply_id;
            }

            public String getUser_id() {
                return user_id;
            }

            public void setUser_id(String user_id) {
                this.user_id = user_id;
            }

            public String getIs_read() {
                return is_read;
            }

            public void setIs_read(String is_read) {
                this.is_read = is_read;
            }

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
            }

            public String getInput_time() {
                return input_time;
            }

            public void setInput_time(String input_time) {
                this.input_time = input_time;
            }

            public String getPur_id() {
                return pur_id;
            }

            public void setPur_id(String pur_id) {
                this.pur_id = pur_id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getIs_pass() {
                return is_pass;
            }

            public void setIs_pass(String is_pass) {
                this.is_pass = is_pass;
            }

            public String getMobile() {
                return mobile;
            }

            public void setMobile(String mobile) {
                this.mobile = mobile;
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

            public String getHui_name() {
                return hui_name;
            }

            public void setHui_name(String hui_name) {
                this.hui_name = hui_name;
            }
        }
    }
}
