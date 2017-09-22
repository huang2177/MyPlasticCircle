package com.myplas.q.headlines.bean;

import java.util.List;

/**
 * 编写： 黄双
 * 电话：15378412400
 * 邮箱：15378412400@163.com
 * 时间：2017/4/5 10:40
 */
public class CateListBean {


    /**
     * err : 0
     * info : [{"id":"44919","title":"早提示：一天之计在于晨","description":"早上好...","cate_id":"1","author":"","input_time":"2017-09-04","type":"高压涂覆","pv":"610","physical":"1,2","cate_name":"早盘预测","is_free":0},{"id":"44891","title":"新闻早提示","description":"一日之计在于晨...","cate_id":"1","author":"","input_time":"2017-08-31","type":"管材","pv":"531","physical":"9","cate_name":"早盘预测","is_free":0},{"id":"44885","title":"早盘预测：油价连续两日暴跌，塑料市场能否延续强势","description":"导读：近日，因飓风影响导致多家炼油企业被迫关闭，投资者持续担心供应短期内得不到消化，致使油价连续两日...","cate_id":"1","author":"中晨","input_time":"2017-08-30","type":"","pv":"586","physical":"","cate_name":"早盘预测","is_free":0},{"id":"44732","title":"早盘预测：艳阳高照  塑料市场持续升温","description":"导读：上周塑料市场整体受期货带动明显，报价小幅走高。然国际原油价格遭遇重挫约2.5%，加之期货快速拉...","cate_id":"1","author":"","input_time":"2017-07-25","type":"均聚拉丝","pv":"809","physical":"7,1,2,8,9,3,5,6,4,10,11,12,13","cate_name":"早盘预测","is_free":0},{"id":"44725","title":"7月25日财经要闻","description":"沪指涨破3250创三个月新高，上证50创两年新高，创业板反弹乏力。\r\n周一，沪指涨0.39%创三个月...","cate_id":"1","author":"","input_time":"2017-07-25","type":"","pv":"773","physical":"","cate_name":"早盘预测","is_free":0},{"id":"44721","title":"7月25聚丙烯PP早提示","description":"7月13聚氯乙烯PVC早提示...","cate_id":"1","author":"","input_time":"2017-07-25","type":"均聚拉丝","pv":"656","physical":"9,10,12","cate_name":"早盘预测","is_free":0},{"id":"44718","title":"7月25聚乙烯PE早提示","description":"昨日国内PE市场价格小幅上涨。线性期货高开震荡，周初石化多数上调出厂价格，市场受到支撑，贸易商多随行...","cate_id":"1","author":"","input_time":"2017-07-25","type":"高压重包","pv":"658","physical":"9,3,2,8,1,7,13,4,5,6","cate_name":"早盘预测","is_free":0},{"id":"44717","title":"7月25聚氯乙烯PVC早提示","description":"7月20聚氯乙烯PVC早提示...","cate_id":"1","author":"","input_time":"2017-07-25","type":"","pv":"604","physical":"","cate_name":"早盘预测","is_free":0},{"id":"44497","title":"早盘预测：国际原油遭遇重挫  塑料市场严阵以待","description":"导读：上周塑料市场整体受期货带动明显，报价小幅走高。然国际原油价格遭遇重挫约2.5%，加之期货快速拉...","cate_id":"1","author":"","input_time":"2017-07-24","type":"低压拉丝","pv":"1236","physical":"7,1,2,8,9,3,5,6,4,10,11,12,13","cate_name":"早盘预测","is_free":0},{"id":"44493","title":"7月24日财经要闻","description":"创业板再度跳水！两日累跌2.8%，\u201c漂亮50\u201d盘中创新高。\r\n\r\n沪深两市临近收盘双双跳水翻绿。两市...","cate_id":"1","author":"","input_time":"2017-07-24","type":"","pv":"955","physical":"","cate_name":"早盘预测","is_free":0}]
     * show_msg :
     */

    private int err;
    private String show_msg;
    private String hot_search;
    private List<InfoBean> info;

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

    public List<InfoBean> getInfo() {
        return info;
    }

    public void setInfo(List<InfoBean> info) {
        this.info = info;
    }

    public String getHot_search() {
        return hot_search;
    }

    public void setHot_search(String hot_search) {
        this.hot_search = hot_search;
    }

    public static class InfoBean {
        /**
         * id : 44919
         * title : 早提示：一天之计在于晨
         * description : 早上好...
         * cate_id : 1
         * author :
         * input_time : 2017-09-04
         * type : 高压涂覆
         * pv : 610
         * physical : 1,2
         * cate_name : 早盘预测
         * is_free : 0
         */

        private String id;
        private String title;
        private String description;
        private String cate_id;
        private String author;
        private String input_time;
        private String type;
        private String pv;
        private String physical;
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

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getCate_id() {
            return cate_id;
        }

        public void setCate_id(String cate_id) {
            this.cate_id = cate_id;
        }

        public String getAuthor() {
            return author;
        }

        public void setAuthor(String author) {
            this.author = author;
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

        public String getPv() {
            return pv;
        }

        public void setPv(String pv) {
            this.pv = pv;
        }

        public String getPhysical() {
            return physical;
        }

        public void setPhysical(String physical) {
            this.physical = physical;
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
}
