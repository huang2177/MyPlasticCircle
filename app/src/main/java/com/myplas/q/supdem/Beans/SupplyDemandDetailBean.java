package com.myplas.q.supdem.Beans;

/**
 * 编写： 黄双
 * 电话：15378412400
 * 邮箱：15378412400@163.com
 * 时间：2017/3/31 17:47
 */
public class SupplyDemandDetailBean {

    /**
     * err : 0
     * data : {"id":"85426","p_id":"0","user_id":"42644","model":null,"unit_price":"0.00","store_house":"","f_name":null,"type":"2","content":"PP拉丝：九江T30、SK1003等。PE线型：武汉35K、武汉35B、大庆7042等。PE低压：大庆6097、SK5301等。PE高压：大庆2426H（神华也有）。PVC：5型8型。三房巷PET。","input_time":"03-31 18:06","contents":"PP拉丝：九江T30、SK1003等。PE线型：武汉35K、武汉35B、大庆7042等。PE低压：大庆6097、SK5301等。PE高压：大庆2426H（神华也有）。PVC：5型8型。三房巷PET。","b_and_s":"","deal_price":"","saysCount":0,"deliverPriceCount":"0","info":{"name":"","c_name":"","need_product":"","thumb":"http://statics.myplas.com/upload/16/09/02/logos.jpg","fans":0,"member_level":null,"sex":null,"buy_count":"","sale_count":"","status":"关注"}}
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
         * id : 85426
         * p_id : 0
         * user_id : 42644
         * model : null
         * unit_price : 0.00
         * store_house :
         * f_name : null
         * type : 2
         * content : PP拉丝：九江T30、SK1003等。PE线型：武汉35K、武汉35B、大庆7042等。PE低压：大庆6097、SK5301等。PE高压：大庆2426H（神华也有）。PVC：5型8型。三房巷PET。
         * input_time : 03-31 18:06
         * contents : PP拉丝：九江T30、SK1003等。PE线型：武汉35K、武汉35B、大庆7042等。PE低压：大庆6097、SK5301等。PE高压：大庆2426H（神华也有）。PVC：5型8型。三房巷PET。
         * b_and_s :
         * deal_price :
         * saysCount : 0
         * deliverPriceCount : 0
         * info : {"name":"","c_name":"","need_product":"","thumb":"http://statics.myplas.com/upload/16/09/02/logos.jpg","fans":0,"member_level":null,"sex":null,"buy_count":"","sale_count":"","status":"关注"}
         */

        private String id;
        private String p_id;
        private String user_id;
        private Object model;
        private String unit_price;
        private String store_house;
        private Object f_name;
        private String type;
        private String content;
        private String input_time;
        private String contents;
        private String b_and_s;
        private String deal_price;
        private int saysCount;
        private String deliverPriceCount;
        private InfoBean info;

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

        public void setModel(Object model) {
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

        public void setF_name(Object f_name) {
            this.f_name = f_name;
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

        public String getInput_time() {
            return input_time;
        }

        public void setInput_time(String input_time) {
            this.input_time = input_time;
        }

        public String getContents() {
            return contents;
        }

        public void setContents(String contents) {
            this.contents = contents;
        }

        public String getB_and_s() {
            return b_and_s;
        }

        public void setB_and_s(String b_and_s) {
            this.b_and_s = b_and_s;
        }

        public String getDeal_price() {
            return deal_price;
        }

        public void setDeal_price(String deal_price) {
            this.deal_price = deal_price;
        }

        public int getSaysCount() {
            return saysCount;
        }

        public void setSaysCount(int saysCount) {
            this.saysCount = saysCount;
        }

        public String getDeliverPriceCount() {
            return deliverPriceCount;
        }

        public void setDeliverPriceCount(String deliverPriceCount) {
            this.deliverPriceCount = deliverPriceCount;
        }

        public InfoBean getInfo() {
            return info;
        }

        public void setInfo(InfoBean info) {
            this.info = info;
        }

        public static class InfoBean {
            /**
             * name :
             * c_name :
             * need_product :
             * thumb : http://statics.myplas.com/upload/16/09/02/logos.jpg
             * fans : 0
             * member_level : null
             * sex : null
             * buy_count :
             * sale_count :
             * status : 关注
             */

            private String name;
            private String c_name;
            private String need_product;
            private String thumb;
            private int fans;
            private Object member_level;
            private Object sex;
            private String buy_count;
            private String sale_count;
            private String status;

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getC_name() {
                return c_name;
            }

            public void setC_name(String c_name) {
                this.c_name = c_name;
            }

            public String getNeed_product() {
                return need_product;
            }

            public void setNeed_product(String need_product) {
                this.need_product = need_product;
            }

            public String getThumb() {
                return thumb;
            }

            public void setThumb(String thumb) {
                this.thumb = thumb;
            }

            public int getFans() {
                return fans;
            }

            public void setFans(int fans) {
                this.fans = fans;
            }

            public Object getMember_level() {
                return member_level;
            }

            public void setMember_level(Object member_level) {
                this.member_level = member_level;
            }

            public Object getSex() {
                return sex;
            }

            public void setSex(Object sex) {
                this.sex = sex;
            }

            public String getBuy_count() {
                return buy_count;
            }

            public void setBuy_count(String buy_count) {
                this.buy_count = buy_count;
            }

            public String getSale_count() {
                return sale_count;
            }

            public void setSale_count(String sale_count) {
                this.sale_count = sale_count;
            }

            public String getStatus() {
                return status;
            }

            public void setStatus(String status) {
                this.status = status;
            }
        }
    }
}
