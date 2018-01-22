package com.myplas.q.homepage.beans;

import java.util.List;

/**
 * @author 黄双
 * @date 2018/1/22 0022
 */

public class BlackListsDetailBean {

    /**
     * code : 0
     * blacklist : {"id":16,"subject":"大概好久看囧囧囧","created_at":"1月22日 17:42","pv":535,"illustration":["http://myplas.ufile.ucloud.com.cn/upload/2018/01/o3mtw8k98m.jpg",""],"content":"放个假","comments":[{"id":11,"blacklist_id":16,"comment":"更何况","parent_comment_id":0,"created_at":"1月22日 17:42","mobile":"18817392632","avatar":"http://statics.myplas.com/upload/17/06/05/593501f1bd99b.jpg","c_name":"嘉兴鼎辉信息科技有限公司","name":"王铭","comments":[{"id":12,"blacklist_id":16,"comment":"计较","parent_comment_id":11,"created_at":"1月22日 17:42","mobile":"18817392632","avatar":"http://statics.myplas.com/upload/17/06/05/593501f1bd99b.jpg","c_name":"嘉兴鼎辉信息科技有限公司","name":"王铭"}]}]}
     */

    private String code;
    private BlacklistBean blacklist;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public BlacklistBean getBlacklist() {
        return blacklist;
    }

    public void setBlacklist(BlacklistBean blacklist) {
        this.blacklist = blacklist;
    }

    public static class BlacklistBean {
        /**
         * id : 16
         * subject : 大概好久看囧囧囧
         * created_at : 1月22日 17:42
         * pv : 535
         * illustration : ["http://myplas.ufile.ucloud.com.cn/upload/2018/01/o3mtw8k98m.jpg",""]
         * content : 放个假
         * comments : [{"id":11,"blacklist_id":16,"comment":"更何况","parent_comment_id":0,"created_at":"1月22日 17:42","mobile":"18817392632","avatar":"http://statics.myplas.com/upload/17/06/05/593501f1bd99b.jpg","c_name":"嘉兴鼎辉信息科技有限公司","name":"王铭","comments":[{"id":12,"blacklist_id":16,"comment":"计较","parent_comment_id":11,"created_at":"1月22日 17:42","mobile":"18817392632","avatar":"http://statics.myplas.com/upload/17/06/05/593501f1bd99b.jpg","c_name":"嘉兴鼎辉信息科技有限公司","name":"王铭"}]}]
         */

        private String id;
        private String subject;
        private String created_at;
        private String pv;
        private String content;
        private List<String> illustration;
        private List<CommentsBeanX> comments;

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

        public String getPv() {
            return pv;
        }

        public void setPv(String pv) {
            this.pv = pv;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public List<String> getIllustration() {
            return illustration;
        }

        public void setIllustration(List<String> illustration) {
            this.illustration = illustration;
        }

        public List<CommentsBeanX> getComments() {
            return comments;
        }

        public void setComments(List<CommentsBeanX> comments) {
            this.comments = comments;
        }

        public static class CommentsBeanX {
            /**
             * id : 11
             * blacklist_id : 16
             * comment : 更何况
             * parent_comment_id : 0
             * created_at : 1月22日 17:42
             * mobile : 18817392632
             * avatar : http://statics.myplas.com/upload/17/06/05/593501f1bd99b.jpg
             * c_name : 嘉兴鼎辉信息科技有限公司
             * name : 王铭
             * comments : [{"id":12,"blacklist_id":16,"comment":"计较","parent_comment_id":11,"created_at":"1月22日 17:42","mobile":"18817392632","avatar":"http://statics.myplas.com/upload/17/06/05/593501f1bd99b.jpg","c_name":"嘉兴鼎辉信息科技有限公司","name":"王铭"}]
             */

            private String id;
            private String blacklist_id;
            private String comment;
            private String parent_comment_id;
            private String created_at;
            private String mobile;
            private String avatar;
            private String c_name;
            private String name;
            private List<CommentsBean> comments;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getBlacklist_id() {
                return blacklist_id;
            }

            public void setBlacklist_id(String blacklist_id) {
                this.blacklist_id = blacklist_id;
            }

            public String getComment() {
                return comment;
            }

            public void setComment(String comment) {
                this.comment = comment;
            }

            public String getParent_comment_id() {
                return parent_comment_id;
            }

            public void setParent_comment_id(String parent_comment_id) {
                this.parent_comment_id = parent_comment_id;
            }

            public String getCreated_at() {
                return created_at;
            }

            public void setCreated_at(String created_at) {
                this.created_at = created_at;
            }

            public String getMobile() {
                return mobile;
            }

            public void setMobile(String mobile) {
                this.mobile = mobile;
            }

            public String getAvatar() {
                return avatar;
            }

            public void setAvatar(String avatar) {
                this.avatar = avatar;
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

            public List<CommentsBean> getComments() {
                return comments;
            }

            public void setComments(List<CommentsBean> comments) {
                this.comments = comments;
            }

            public static class CommentsBean {
                /**
                 * id : 12
                 * blacklist_id : 16
                 * comment : 计较
                 * parent_comment_id : 11
                 * created_at : 1月22日 17:42
                 * mobile : 18817392632
                 * avatar : http://statics.myplas.com/upload/17/06/05/593501f1bd99b.jpg
                 * c_name : 嘉兴鼎辉信息科技有限公司
                 * name : 王铭
                 */

                private String id;
                private String blacklist_id;
                private String comment;
                private String parent_comment_id;
                private String created_at;
                private String mobile;
                private String avatar;
                private String c_name;
                private String name;

                public String getId() {
                    return id;
                }

                public void setId(String id) {
                    this.id = id;
                }

                public String getBlacklist_id() {
                    return blacklist_id;
                }

                public void setBlacklist_id(String blacklist_id) {
                    this.blacklist_id = blacklist_id;
                }

                public String getComment() {
                    return comment;
                }

                public void setComment(String comment) {
                    this.comment = comment;
                }

                public String getParent_comment_id() {
                    return parent_comment_id;
                }

                public void setParent_comment_id(String parent_comment_id) {
                    this.parent_comment_id = parent_comment_id;
                }

                public String getCreated_at() {
                    return created_at;
                }

                public void setCreated_at(String created_at) {
                    this.created_at = created_at;
                }

                public String getMobile() {
                    return mobile;
                }

                public void setMobile(String mobile) {
                    this.mobile = mobile;
                }

                public String getAvatar() {
                    return avatar;
                }

                public void setAvatar(String avatar) {
                    this.avatar = avatar;
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
            }
        }
    }
}
