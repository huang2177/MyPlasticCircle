package com.myplas.q.supdem.beans;

import java.util.List;

/**
 * 编写： 黄双
 * 电话：15378412400
 * 邮箱：15378412400@163.com
 * 时间：2017/3/31 20:18
 */
public class ReplyBean {

    /**
     * code : 0
     * data : [{"id":2735,"rev_id":56656,"user_id":41497,"is_read":0,"content":"123","input_time":"1月18日 14:49","pur_id":197612,"reply_id":0,"mobile":"15378412400","thumb":"http:myplas.ufile.ucloud.com.cn/upload/2018/1/whnpugd6qj.jpg","c_name":"打得过","name":"黄双","is_pass":0,"comments":[{"id":2739,"rev_id":56656,"user_id":45782,"is_read":0,"content":"刚回家","input_time":"14:48","pur_id":197612,"reply_id":2735,"mobile":"18817392632","thumb":"http://statics.myplas.com/upload/17/06/05/593501f1bd99b.jpg","c_name":"嘉兴鼎辉信息科技有限公司","name":"王铭","is_pass":""}]},{"id":2734,"rev_id":56656,"user_id":41497,"is_read":0,"content":"粉红","input_time":"1月18日 14:40","pur_id":197612,"reply_id":0,"mobile":"15378412400","thumb":"http:myplas.ufile.ucloud.com.cn/upload/2018/1/whnpugd6qj.jpg","c_name":"打得过","name":"黄双","is_pass":0,"comments":[]},{"id":2731,"rev_id":56656,"user_id":81088,"is_read":0,"content":"1111","input_time":"1月17日 17:18","pur_id":197612,"reply_id":0,"mobile":"18817392655","thumb":"http://myplas.ufile.ucloud.com.cn/upload/18/01/17/V41CW99H1F.jpg","c_name":"上海测试封装有限公司","name":"郑成功","is_pass":0,"comments":[{"id":2738,"rev_id":56656,"user_id":45782,"is_read":0,"content":"波哥","input_time":"14:43","pur_id":197612,"reply_id":2731,"mobile":"18817392632","thumb":"http://statics.myplas.com/upload/17/06/05/593501f1bd99b.jpg","c_name":"嘉兴鼎辉信息科技有限公司","name":"王铭","is_pass":""},{"id":2738,"rev_id":56656,"user_id":45782,"is_read":0,"content":"波哥","input_time":"14:43","pur_id":197612,"reply_id":2731,"mobile":"18817392632","thumb":"http://statics.myplas.com/upload/17/06/05/593501f1bd99b.jpg","c_name":"嘉兴鼎辉信息科技有限公司","name":"王铭","is_pass":""}]}]
     */

    private String code;
    private List<DataBean> data;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * id : 2735
         * rev_id : 56656
         * user_id : 41497
         * is_read : 0
         * content : 123
         * input_time : 1月18日 14:49
         * pur_id : 197612
         * reply_id : 0
         * mobile : 15378412400
         * thumb : http:myplas.ufile.ucloud.com.cn/upload/2018/1/whnpugd6qj.jpg
         * c_name : 打得过
         * name : 黄双
         * is_pass : 0
         * comments : [{"id":2739,"rev_id":56656,"user_id":45782,"is_read":0,"content":"刚回家","input_time":"14:48","pur_id":197612,"reply_id":2735,"mobile":"18817392632","thumb":"http://statics.myplas.com/upload/17/06/05/593501f1bd99b.jpg","c_name":"嘉兴鼎辉信息科技有限公司","name":"王铭","is_pass":""}]
         */

        private String id;
        private String rev_id;
        private String user_id;
        private String is_read;
        private String content;
        private String input_time;
        private String pur_id;
        private String reply_id;
        private String mobile;
        private String thumb;
        private String c_name;
        private String name;
        private String is_pass;
        private List<CommentsBean> comments;

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

        public String getC_name() {
            return c_name;
        }

        public void setC_name(String c_name) {
            this.c_name = c_name;
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

        public List<CommentsBean> getComments() {
            return comments;
        }

        public void setComments(List<CommentsBean> comments) {
            this.comments = comments;
        }

        public static class CommentsBean {
            /**
             * id : 2739
             * rev_id : 56656
             * user_id : 45782
             * is_read : 0
             * content : 刚回家
             * input_time : 14:48
             * pur_id : 197612
             * reply_id : 2735
             * mobile : 18817392632
             * thumb : http://statics.myplas.com/upload/17/06/05/593501f1bd99b.jpg
             * c_name : 嘉兴鼎辉信息科技有限公司
             * name : 王铭
             * is_pass : 
             */

            private String id;
            private String rev_id;
            private String user_id;
            private String is_read;
            private String content;
            private String input_time;
            private String pur_id;
            private String reply_id;
            private String mobile;
            private String thumb;
            private String c_name;
            private String name;
            private String is_pass;

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

            public String getC_name() {
                return c_name;
            }

            public void setC_name(String c_name) {
                this.c_name = c_name;
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
        }
    }
}
