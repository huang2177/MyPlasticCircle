package com.myplas.q.myself.beans;

import java.util.List;

/**
 * 编写： 黄双
 * 电话：15378412400
 * 邮箱：15378412400@163.com
 * 时间：2017/3/28 14:41
 */
public class IntegralBean {


    /**
     * err : 0
     * info : [{"id":"22","cate_id":"9","thumb":"http://pic.myplas.com/myapp/img/img_mail_list@2x.png","image":"http://pic.myplas.com/myapp/img/img_mail_list@2x.png","name":"独家解读包月卡","points":"340","type":"3","extra_config":[{"cate_id":"22","cate_name":"独家解读","children":[{"cate_id":"23","cate_name":"下游"},{"cate_id":"24","cate_name":"库存"},{"cate_id":"25","cate_name":"产量"},{"cate_id":"26","cate_name":"装置检修"},{"cate_id":"27","cate_name":"排产"},{"cate_id":"28","cate_name":"进出口"},{"cate_id":"32","cate_name":"网拍"},{"cate_id":"29","cate_name":"进口"},{"cate_id":"30","cate_name":"出口"}]}]},{"id":"21","cate_id":"9","thumb":"http://pic.myplas.com/myapp/img/img_mail_list@2x.png","image":"http://pic.myplas.com/myapp/img/img_mail_list@2x.png","name":"期刊报告包月卡","points":"340","type":"3","extra_config":[{"cate_id":"21","cate_name":"期货资讯"}]},{"id":"20","cate_id":"9","thumb":"http://pic.myplas.com/myapp/img/img_mail_list@2x.png","image":"http://pic.myplas.com/myapp/img/img_mail_list@2x.png","name":"装置动态包月卡","points":"340","type":"3","extra_config":[{"cate_id":"20","cate_name":"美金市场"}]},{"id":"19","cate_id":"9","thumb":"http://pic.myplas.com/myapp/img/img_mail_list@2x.png","image":"http://pic.myplas.com/myapp/img/img_mail_list@2x.png","name":"期货资讯包月卡","points":"340","type":"3","extra_config":[{"cate_id":"19","cate_name":"行业要闻"}]},{"id":"18","cate_id":"9","thumb":"http://pic.myplas.com/myapp/img/img_mail_list@2x.png","image":"http://pic.myplas.com/myapp/img/img_mail_list@2x.png","name":"美金市场包月卡","points":"340","type":"3","extra_config":null},{"id":"17","cate_id":"9","thumb":"http://pic.myplas.com/myapp/img/img_mail_list@2x.png","image":"http://pic.myplas.com/myapp/img/img_mail_list@2x.png","name":"中晨塑说包月卡","points":"340","type":"3","extra_config":[{"cate_id":"62","cate_name":"中晨塑说"}]},{"id":"16","cate_id":"9","thumb":"http://pic.myplas.com/myapp/img/img_mail_list@2x.png","image":"http://pic.myplas.com/myapp/img/img_mail_list@2x.png","name":"企业动态包月卡","points":"340","type":"3","extra_config":[{"cate_id":"9","cate_name":"企业动态"}]},{"id":"15","cate_id":"9","thumb":"http://pic.myplas.com/myapp/img/img_mail_list@2x.png","image":"http://pic.myplas.com/myapp/img/img_mail_list@2x.png","name":"早盘预报包月卡","points":"340","type":"3","extra_config":[{"cate_id":"7","cate_name":"今日焦点"}]},{"id":"14","cate_id":"9","thumb":"http://pic.myplas.com/myapp/img/img_mail_list@2x.png","image":"http://pic.myplas.com/myapp/img/img_mail_list@2x.png","name":"塑料上游包月卡","points":"340","type":"3","extra_config":[{"cate_id":"2","cate_name":"上游动态"}]},{"id":"13","cate_id":"9","thumb":"http://pic.myplas.com/myapp/img/img_mail_list@2x.png","image":"http://pic.myplas.com/myapp/img/img_mail_list@2x.png","name":"通讯录一天置顶卡","points":"100","type":"2","extra_config":""},{"id":"12","cate_id":"9","thumb":"http://pic.myplas.com/myapp/img/img_supply@2x.png","image":"http://pic.myplas.com/myapp/img/img_supply@2x.png","name":"供求信息一天置顶卡","points":"100","type":"1","extra_config":"","myMsg":[{"id":"110848","p_id":"0","user_id":"53402","model":null,"unit_price":"0.00","store_house":"","f_name":null,"input_time":"08-18 15:28","type":"1","content":"pp","c_name":"上海中晨电子商务股份有限公司","name":"hh","thumb":"http://pic.myplas.com/upload/17/08/22/599bb87bd79ef.PNG","thumbqq":"http://pic.myplas.com/upload/17/08/22/599bb87bd79ef.PNG","sex":"0","mobile_province":"四川","is_pass":"0","contents":"pp","saysCount":1,"deliverPriceCount":0}]}]
     * pointsAll : 1975
     */

    private int err;
    private String pointsAll;
    private List<InfoBean> info;

    public int getErr() {
        return err;
    }

    public void setErr(int err) {
        this.err = err;
    }

    public String getPointsAll() {
        return pointsAll;
    }

    public void setPointsAll(String pointsAll) {
        this.pointsAll = pointsAll;
    }

    public List<InfoBean> getInfo() {
        return info;
    }

    public void setInfo(List<InfoBean> info) {
        this.info = info;
    }

    public static class InfoBean {
        /**
         * id : 22
         * cate_id : 9
         * thumb : http://pic.myplas.com/myapp/img/img_mail_list@2x.png
         * image : http://pic.myplas.com/myapp/img/img_mail_list@2x.png
         * name : 独家解读包月卡
         * points : 340
         * type : 3
         * extra_config : [{"cate_id":"22","cate_name":"独家解读","children":[{"cate_id":"23","cate_name":"下游"},{"cate_id":"24","cate_name":"库存"},{"cate_id":"25","cate_name":"产量"},{"cate_id":"26","cate_name":"装置检修"},{"cate_id":"27","cate_name":"排产"},{"cate_id":"28","cate_name":"进出口"},{"cate_id":"32","cate_name":"网拍"},{"cate_id":"29","cate_name":"进口"},{"cate_id":"30","cate_name":"出口"}]}]
         * myMsg : [{"id":"110848","p_id":"0","user_id":"53402","model":null,"unit_price":"0.00","store_house":"","f_name":null,"input_time":"08-18 15:28","type":"1","content":"pp","c_name":"上海中晨电子商务股份有限公司","name":"hh","thumb":"http://pic.myplas.com/upload/17/08/22/599bb87bd79ef.PNG","thumbqq":"http://pic.myplas.com/upload/17/08/22/599bb87bd79ef.PNG","sex":"0","mobile_province":"四川","is_pass":"0","contents":"pp","saysCount":1,"deliverPriceCount":0}]
         */

        private String id;
        private String cate_id;
        private String thumb;
        private String image;
        private String name;
        private String points;
        private String type;
        private List<ExtraConfigBean> extra_config;
        private List<MyMsgBean> myMsg;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getCate_id() {
            return cate_id;
        }

        public void setCate_id(String cate_id) {
            this.cate_id = cate_id;
        }

        public String getThumb() {
            return thumb;
        }

        public void setThumb(String thumb) {
            this.thumb = thumb;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPoints() {
            return points;
        }

        public void setPoints(String points) {
            this.points = points;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public List<ExtraConfigBean> getExtra_config() {
            return extra_config;
        }

        public void setExtra_config(List<ExtraConfigBean> extra_config) {
            this.extra_config = extra_config;
        }

        public List<MyMsgBean> getMyMsg() {
            return myMsg;
        }

        public void setMyMsg(List<MyMsgBean> myMsg) {
            this.myMsg = myMsg;
        }

        public static class ExtraConfigBean {
            /**
             * cate_id : 22
             * cate_name : 独家解读
             * children : [{"cate_id":"23","cate_name":"下游"},{"cate_id":"24","cate_name":"库存"},{"cate_id":"25","cate_name":"产量"},{"cate_id":"26","cate_name":"装置检修"},{"cate_id":"27","cate_name":"排产"},{"cate_id":"28","cate_name":"进出口"},{"cate_id":"32","cate_name":"网拍"},{"cate_id":"29","cate_name":"进口"},{"cate_id":"30","cate_name":"出口"}]
             */

            private String cate_id;
            private String cate_name;
            private List<ChildrenBean> children;

            public String getCate_id() {
                return cate_id;
            }

            public void setCate_id(String cate_id) {
                this.cate_id = cate_id;
            }

            public String getCate_name() {
                return cate_name;
            }

            public void setCate_name(String cate_name) {
                this.cate_name = cate_name;
            }

            public List<ChildrenBean> getChildren() {
                return children;
            }

            public void setChildren(List<ChildrenBean> children) {
                this.children = children;
            }

            public static class ChildrenBean {
                /**
                 * cate_id : 23
                 * cate_name : 下游
                 */

                private String cate_id;
                private String cate_name;

                public String getCate_id() {
                    return cate_id;
                }

                public void setCate_id(String cate_id) {
                    this.cate_id = cate_id;
                }

                public String getCate_name() {
                    return cate_name;
                }

                public void setCate_name(String cate_name) {
                    this.cate_name = cate_name;
                }
            }
        }

        public static class MyMsgBean {
            /**
             * id : 110848
             * p_id : 0
             * user_id : 53402
             * model : null
             * unit_price : 0.00
             * store_house :
             * f_name : null
             * input_time : 08-18 15:28
             * type : 1
             * content : pp
             * c_name : 上海中晨电子商务股份有限公司
             * name : hh
             * thumb : http://pic.myplas.com/upload/17/08/22/599bb87bd79ef.PNG
             * thumbqq : http://pic.myplas.com/upload/17/08/22/599bb87bd79ef.PNG
             * sex : 0
             * mobile_province : 四川
             * is_pass : 0
             * contents : pp
             * saysCount : 1
             * deliverPriceCount : 0
             */

            private String id;
            private String p_id;
            private String user_id;
            private String model;
            private String unit_price;
            private String store_house;
            private String f_name;
            private String input_time;
            private String type;
            private String content;
            private String c_name;
            private String name;
            private String thumb;
            private String thumbqq;
            private String sex;
            private String mobile_province;
            private String is_pass;
            private String contents;
            private int saysCount;
            private int deliverPriceCount;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getP_id() {
                return p_id;
            }

            public void setP_id(String p_id) {
                this.p_id = p_id;
            }

            public String getUser_id() {
                return user_id;
            }

            public void setUser_id(String user_id) {
                this.user_id = user_id;
            }

            public Object getModel() {
                return model;
            }

            public void setModel(String model) {
                this.model = model;
            }

            public String getUnit_price() {
                return unit_price;
            }

            public void setUnit_price(String unit_price) {
                this.unit_price = unit_price;
            }

            public String getStore_house() {
                return store_house;
            }

            public void setStore_house(String store_house) {
                this.store_house = store_house;
            }

            public Object getF_name() {
                return f_name;
            }

            public void setF_name(String f_name) {
                this.f_name = f_name;
            }

            public String getInput_time() {
                return input_time;
            }

            public void setInput_time(String input_time) {
                this.input_time = input_time;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
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

            public String getSex() {
                return sex;
            }

            public void setSex(String sex) {
                this.sex = sex;
            }

            public String getMobile_province() {
                return mobile_province;
            }

            public void setMobile_province(String mobile_province) {
                this.mobile_province = mobile_province;
            }

            public String getIs_pass() {
                return is_pass;
            }

            public void setIs_pass(String is_pass) {
                this.is_pass = is_pass;
            }

            public String getContents() {
                return contents;
            }

            public void setContents(String contents) {
                this.contents = contents;
            }

            public int getSaysCount() {
                return saysCount;
            }

            public void setSaysCount(int saysCount) {
                this.saysCount = saysCount;
            }

            public int getDeliverPriceCount() {
                return deliverPriceCount;
            }

            public void setDeliverPriceCount(int deliverPriceCount) {
                this.deliverPriceCount = deliverPriceCount;
            }
        }
    }
}
