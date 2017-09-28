package com.myplas.q.headlines.bean;

import java.util.List;

/**
 * 编写： 黄双
 * 电话：15378412400
 * 邮箱：15378412400@163.com
 * 时间：2017/4/date_selected 15:46
 */
public class SubcribleBean {

    /**
     * err : 0
     * data : [{"id":"44962","title":"早盘预测：听说，今天塑料市场能反弹","cate_id":"2","hot":"0","source_from":"本站原创","keywords":"市场价格,走跌,主流,小幅,线性,塑料,部分,塑料网,期货,市场","description":"导读：近期塑料市场一直处于下跌阴霾笼罩之下。但早盘部分业内人士断言今日塑料市场有望止跌，个别小幅高报...","status":"1","sort_order":"0","author":"","pv":"558","type":"线型","true_pv":"1","admin_name":"admin","input_time":"09:31","update_time":"1505179860","cate_name":"上游动态","is_free":0},{"id":"44963","title":"9月12日财经要闻","cate_id":"7","hot":"0","source_from":"本站原创","keywords":"特斯拉,中国,央行,人民币,金融,美联储,高盛,支出,加息,电动汽车","description":"人民币大跌，日内一度下挫超400点媒体称中国央行停征外汇风险准备金。分析认为，此举释放了央行不愿人民...","status":"1","sort_order":"0","author":"","pv":"508","type":"管材","true_pv":"1","admin_name":"admin","input_time":"09:31","update_time":"1505179894","cate_name":"今日焦点","is_free":0},{"id":"44964","title":"中晨塑说：刘官庄那些关停的塑料厂，现在怎么样了？","cate_id":"76","hot":"0","source_from":"本站原创","keywords":"塑料,官庄,官庄镇,产业,关停,督查,镇内,莒县,环保,生产","description":"日照市莒县刘官庄镇,一个因塑料产业而兴、因塑料产业而强、也因塑料产业而闻名全国的乡镇，被业内誉为\u201c中...","status":"1","sort_order":"0","author":"","pv":"500","type":"管材","true_pv":"0","admin_name":"admin","input_time":"09:32","update_time":"1505179932","cate_name":"中晨塑说","is_free":0},{"id":"44966","title":"内蒙君正化工PVC报价平稳","cate_id":"9","hot":"1","source_from":"本站原创","keywords":"装置,内蒙,承兑,电石,塑料网,出厂,开工,平稳,正常,今日","description":"我的塑料网（www.myplas.com）9月12日报道：内蒙君正化工70万吨/年（新装置36万吨）...","status":"1","sort_order":"0","author":"","pv":"553","type":"高压涂覆","true_pv":"0","admin_name":"admin","input_time":"09:56","update_time":"1505181410","cate_name":"企业动态","is_free":0}]
     * show_msg : 更新了4条数据
     * banner : [{"id":"44969","title":"中晨塑说：看了那么多环保消息，塑料人，你该看基本面了","img":"http://pic.myplas.com/upload/17/09/12/59b73ef04af59.jpg"},{"id":"44966","title":"内蒙君正化工PVC报价平稳","img":"http://pic.myplas.com/upload/17/09/12/59b73ed16e162.jpg"},{"id":"44965","title":"齐鲁化工城PVC塑料市场早盘报价小幅下调","img":"http://pic.myplas.com/upload/17/09/12/59b73e822dcb2.jpg"}]
     */

    private int err;
    private String show_msg;
    private List<DataBean> data;
    private List<BannerBean> banner;
    private String hot_search;

    public int getErr() {
        return err;
    }

    public void setErr(int err) {
        this.err = err;
    }

    public String getShow_msg() {
        return show_msg;
    }

    public void setShow_msg(String show_msg) {
        this.show_msg = show_msg;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public List<BannerBean> getBanner() {
        return banner;
    }

    public void setBanner(List<BannerBean> banner) {
        this.banner = banner;
    }

    public String getHot_search() {
        return hot_search;
    }

    public void setHot_search(String hot_search) {
        this.hot_search = hot_search;
    }

    public static class DataBean {
        /**
         * id : 44962
         * title : 早盘预测：听说，今天塑料市场能反弹
         * cate_id : 2
         * hot : 0
         * source_from : 本站原创
         * keywords : 市场价格,走跌,主流,小幅,线性,塑料,部分,塑料网,期货,市场
         * description : 导读：近期塑料市场一直处于下跌阴霾笼罩之下。但早盘部分业内人士断言今日塑料市场有望止跌，个别小幅高报...
         * status : 1
         * sort_order : 0
         * author :
         * pv : 558
         * type : 线型
         * true_pv : 1
         * admin_name : admin
         * input_time : 09:31
         * update_time : 1505179860
         * cate_name : 上游动态
         * is_free : 0
         */

        private String id;
        private String title;
        private String cate_id;
        private String hot;
        private String source_from;
        private String keywords;
        private String description;
        private String status;
        private String sort_order;
        private String author;
        private String pv;
        private String type;
        private String true_pv;
        private String admin_name;
        private String input_time;
        private String update_time;
        private String cate_name;
        private String is_free;


        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getCate_id() {
            return cate_id;
        }

        public void setCate_id(String cate_id) {
            this.cate_id = cate_id;
        }

        public String getHot() {
            return hot;
        }

        public void setHot(String hot) {
            this.hot = hot;
        }

        public String getSource_from() {
            return source_from;
        }

        public void setSource_from(String source_from) {
            this.source_from = source_from;
        }

        public String getKeywords() {
            return keywords;
        }

        public void setKeywords(String keywords) {
            this.keywords = keywords;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getSort_order() {
            return sort_order;
        }

        public void setSort_order(String sort_order) {
            this.sort_order = sort_order;
        }

        public String getAuthor() {
            return author;
        }

        public void setAuthor(String author) {
            this.author = author;
        }

        public String getPv() {
            return pv;
        }

        public void setPv(String pv) {
            this.pv = pv;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getTrue_pv() {
            return true_pv;
        }

        public void setTrue_pv(String true_pv) {
            this.true_pv = true_pv;
        }

        public String getAdmin_name() {
            return admin_name;
        }

        public void setAdmin_name(String admin_name) {
            this.admin_name = admin_name;
        }

        public String getInput_time() {
            return input_time;
        }

        public void setInput_time(String input_time) {
            this.input_time = input_time;
        }

        public String getUpdate_time() {
            return update_time;
        }

        public void setUpdate_time(String update_time) {
            this.update_time = update_time;
        }

        public String getCate_name() {
            return cate_name;
        }

        public void setCate_name(String cate_name) {
            this.cate_name = cate_name;
        }

        public String getIs_free() {
            return is_free;
        }

        public void setIs_free(String is_free) {
            this.is_free = is_free;
        }
    }

    public static class BannerBean {
        /**
         * id : 44969
         * title : 中晨塑说：看了那么多环保消息，塑料人，你该看基本面了
         * img : http://pic.myplas.com/upload/17/09/12/59b73ef04af59.jpg
         */

        private String id;
        private String title;
        private String img;
        private String is_free;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getImg() {
            return img;
        }

        public void setImg(String img) {
            this.img = img;
        }

        public String getIs_free() {
            return is_free;
        }

        public void setIs_free(String is_free) {
            this.is_free = is_free;
        }
    }
}
