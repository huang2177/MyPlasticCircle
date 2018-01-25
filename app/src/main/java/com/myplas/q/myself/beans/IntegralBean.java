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
     * code : 0
     * info : [{"id":"22","cateId":"9","thumb":"http://pic.myplas.com/myapp/img/img_mail_list@2x.png","image":"http://pic.myplas.com/myapp/img/img_mail_list@2x.png","name":"独家解读包月卡","points":"340","type":"3","extra_config":[{"cateId":"22","cate_name":"独家解读","children":[{"cateId":"23","cate_name":"下游"},{"cateId":"24","cate_name":"库存"},{"cateId":"25","cate_name":"产量"},{"cateId":"26","cate_name":"装置检修"},{"cateId":"27","cate_name":"排产"},{"cateId":"28","cate_name":"进出口"},{"cateId":"32","cate_name":"网拍"},{"cateId":"29","cate_name":"进口"},{"cateId":"30","cate_name":"出口"}]}]},{"id":"21","cateId":"9","thumb":"http://pic.myplas.com/myapp/img/img_mail_list@2x.png","image":"http://pic.myplas.com/myapp/img/img_mail_list@2x.png","name":"期刊报告包月卡","points":"340","type":"3","extra_config":[{"cateId":"21","cate_name":"期货资讯"}]},{"id":"20","cateId":"9","thumb":"http://pic.myplas.com/myapp/img/img_mail_list@2x.png","image":"http://pic.myplas.com/myapp/img/img_mail_list@2x.png","name":"装置动态包月卡","points":"340","type":"3","extra_config":[{"cateId":"20","cate_name":"美金市场"}]},{"id":"19","cateId":"9","thumb":"http://pic.myplas.com/myapp/img/img_mail_list@2x.png","image":"http://pic.myplas.com/myapp/img/img_mail_list@2x.png","name":"期货资讯包月卡","points":"340","type":"3","extra_config":[{"cateId":"19","cate_name":"行业要闻"}]},{"id":"18","cateId":"9","thumb":"http://pic.myplas.com/myapp/img/img_mail_list@2x.png","image":"http://pic.myplas.com/myapp/img/img_mail_list@2x.png","name":"美金市场包月卡","points":"340","type":"3","extra_config":null},{"id":"17","cateId":"9","thumb":"http://pic.myplas.com/myapp/img/img_mail_list@2x.png","image":"http://pic.myplas.com/myapp/img/img_mail_list@2x.png","name":"中晨塑说包月卡","points":"340","type":"3","extra_config":[{"cateId":"62","cate_name":"中晨塑说"}]},{"id":"16","cateId":"9","thumb":"http://pic.myplas.com/myapp/img/img_mail_list@2x.png","image":"http://pic.myplas.com/myapp/img/img_mail_list@2x.png","name":"企业动态包月卡","points":"340","type":"3","extra_config":[{"cateId":"9","cate_name":"企业动态"}]},{"id":"15","cateId":"9","thumb":"http://pic.myplas.com/myapp/img/img_mail_list@2x.png","image":"http://pic.myplas.com/myapp/img/img_mail_list@2x.png","name":"早盘预报包月卡","points":"340","type":"3","extra_config":[{"cateId":"7","cate_name":"今日焦点"}]},{"id":"14","cateId":"9","thumb":"http://pic.myplas.com/myapp/img/img_mail_list@2x.png","image":"http://pic.myplas.com/myapp/img/img_mail_list@2x.png","name":"塑料上游包月卡","points":"340","type":"3","extra_config":[{"cateId":"2","cate_name":"上游动态"}]},{"id":"13","cateId":"9","thumb":"http://pic.myplas.com/myapp/img/img_mail_list@2x.png","image":"http://pic.myplas.com/myapp/img/img_mail_list@2x.png","name":"通讯录一天置顶卡","points":"100","type":"2","extra_config":""},{"id":"12","cateId":"9","thumb":"http://pic.myplas.com/myapp/img/img_supply@2x.png","image":"http://pic.myplas.com/myapp/img/img_supply@2x.png","name":"供求信息一天置顶卡","points":"100","type":"1","extra_config":"","myMsg":[{"id":"110848","p_id":"0","user_id":"53402","model":null,"unit_price":"0.00","store_house":"","f_name":null,"input_time":"08-18 15:28","type":"1","content":"pp","c_name":"上海中晨电子商务股份有限公司","name":"hh","thumb":"http://pic.myplas.com/upload/17/08/22/599bb87bd79ef.PNG","thumbqq":"http://pic.myplas.com/upload/17/08/22/599bb87bd79ef.PNG","sex":"0","mobile_province":"四川","is_pass":"0","contents":"pp","saysCount":1,"deliverPriceCount":0}]}]
     * pointsAll : 1975
     */

    private String pointsAll;
    private List<InfoBean> info;

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
         * cateId : 9
         * thumb : http://pic.myplas.com/myapp/img/img_mail_list@2x.png
         * image : http://pic.myplas.com/myapp/img/img_mail_list@2x.png
         * name : 独家解读包月卡
         * points : 340
         * type : 3
         * extra_config : [{"cateId":"22","cate_name":"独家解读","children":[{"cateId":"23","cate_name":"下游"},{"cateId":"24","cate_name":"库存"},{"cateId":"25","cate_name":"产量"},{"cateId":"26","cate_name":"装置检修"},{"cateId":"27","cate_name":"排产"},{"cateId":"28","cate_name":"进出口"},{"cateId":"32","cate_name":"网拍"},{"cateId":"29","cate_name":"进口"},{"cateId":"30","cate_name":"出口"}]}]
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
             * cateId : 22
             * cate_name : 独家解读
             * children : [{"cateId":"23","cate_name":"下游"},{"cateId":"24","cate_name":"库存"},{"cateId":"25","cate_name":"产量"},{"cateId":"26","cate_name":"装置检修"},{"cateId":"27","cate_name":"排产"},{"cateId":"28","cate_name":"进出口"},{"cateId":"32","cate_name":"网拍"},{"cateId":"29","cate_name":"进口"},{"cateId":"30","cate_name":"出口"}]
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
                 * cateId : 23
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

            private String c_name;
            private String cargo_type;
            private String f_name;
            private String id;
            private String img;
            private String input_time;
            private String model;
            private String name;
            private String p_id;
            private String release_offer_total;
            private String release_reply_total;
            private String store_house;
            private String type;
            private String unit_price;
            private String user_id;

            public void setC_name(String c_name) {
                this.c_name = c_name;
            }

            public void setCargo_type(String cargo_type) {
                this.cargo_type = cargo_type;
            }

            public void setF_name(String f_name) {
                this.f_name = f_name;
            }

            public void setId(String id) {
                this.id = id;
            }

            public void setImg(String img) {
                this.img = img;
            }

            public void setInput_time(String input_time) {
                this.input_time = input_time;
            }

            public void setModel(String model) {
                this.model = model;
            }

            public void setName(String name) {
                this.name = name;
            }

            public void setP_id(String p_id) {
                this.p_id = p_id;
            }

            public void setRelease_offer_total(String release_offer_total) {
                this.release_offer_total = release_offer_total;
            }

            public void setRelease_reply_total(String release_reply_total) {
                this.release_reply_total = release_reply_total;
            }

            public void setStore_house(String store_house) {
                this.store_house = store_house;
            }

            public void setType(String type) {
                this.type = type;
            }

            public void setUnit_price(String unit_price) {
                this.unit_price = unit_price;
            }

            public void setUser_id(String user_id) {
                this.user_id = user_id;
            }

            public String getC_name() {
                return c_name;
            }

            public String getCargo_type() {
                return cargo_type;
            }

            public String getF_name() {
                return f_name;
            }

            public String getId() {
                return id;
            }

            public String getImg() {
                return img;
            }

            public String getInput_time() {
                return input_time;
            }

            public String getModel() {
                return model;
            }

            public String getName() {
                return name;
            }

            public String getP_id() {
                return p_id;
            }

            public String getRelease_offer_total() {
                return release_offer_total;
            }

            public String getRelease_reply_total() {
                return release_reply_total;
            }

            public String getStore_house() {
                return store_house;
            }

            public String getType() {
                return type;
            }

            public String getUnit_price() {
                return unit_price;
            }

            public String getUser_id() {
                return user_id;
            }
        }
    }
}
