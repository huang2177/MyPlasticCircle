package com.myplas.q.contact.beans;

import java.io.Serializable;
import java.util.List;

/**
 * 编写： 黄双
 * 电话：15378412400
 * 邮箱：15378412400@163.com
 * 时间：2017/3/30 17:05
 */
public class ContactSupDemBean implements Serializable {


    /**
     * err : 0
     * data : [{"id":"114118","p_id":"9481","user_id":"53453","model":"7000F","unit_price":"10000.00","store_house":"上海","f_name":"神华","input_time":"10-13 14:42","type":"2","content":"","name":"娟娟","is_pass":"0","c_name":"上海晨达物流有限公司","thumb":"http://pic.myplas.com/upload/17/09/01/59a93aba83fc0.PNG","contents":"价格10000.00元左右/7000F/神华/上海","says":[]},{"id":"114120","p_id":"9481","user_id":"53453","model":"7000F","unit_price":"10000.00","store_house":"上海","f_name":"神华","input_time":"10-13 14:42","type":"2","content":"伊朗石化7000F 上海提 12000","name":"娟娟","is_pass":"0","c_name":"上海晨达物流有限公司","thumb":"http://pic.myplas.com/upload/17/09/01/59a93aba83fc0.PNG","contents":"价格10000.00元左右/7000F/神华/上海/伊朗石化7000F 上海提 12000","says":[]},{"id":"114117","p_id":"0","user_id":"53402","model":null,"unit_price":"0.00","store_house":"","f_name":"","input_time":"10-12 18:16","type":"2","content":"7000f 上海","name":"hh","is_pass":"0","c_name":"上海中晨电子商务股份有限公司","thumb":"http://pic.myplas.com/upload/17/09/06/59af96a6a157f.PNG","contents":"7000f 上海","says":[]},{"id":"114116","p_id":"0","user_id":"53402","model":null,"unit_price":"0.00","store_house":"","f_name":"","input_time":"10-12 18:10","type":"2","content":"什么鬼","name":"hh","is_pass":"0","c_name":"上海中晨电子商务股份有限公司","thumb":"http://pic.myplas.com/upload/17/09/06/59af96a6a157f.PNG","contents":"什么鬼","says":[]},{"id":"114115","p_id":"0","user_id":"3858","model":null,"unit_price":"0.00","store_house":"","f_name":"","input_time":"10-11 16:12","type":"2","content":"测试记录","name":"李一帆","is_pass":"0","c_name":"上海中晨电子商务股份有限公司","thumb":"http://pic.myplas.com/upload/17/08/03/5982a32bc386f.PNG","contents":"测试记录","says":[]},{"id":"114114","p_id":"0","user_id":"53405","model":null,"unit_price":"0.00","store_house":"","f_name":"","input_time":"10-10 09:27","type":"2","content":"呃呃呃擦擦擦v","name":"小胖","is_pass":"0","c_name":"上海中信有限公司","thumb":"http://pic.myplas.com/upload/17/09/05/59ae0cd00dd3e.PNG","contents":"呃呃呃擦擦擦v","says":[]},{"id":"114112","p_id":"0","user_id":"53402","model":null,"unit_price":"0.00","store_house":"","f_name":"","input_time":"09-29 15:28","type":"2","content":"pp","name":"hh","is_pass":"0","c_name":"上海中晨电子商务股份有限公司","thumb":"http://pic.myplas.com/upload/17/09/06/59af96a6a157f.PNG","contents":"pp","says":[]},{"id":"114080","p_id":"2596","user_id":"3858","model":"7000F","unit_price":"1200.00","store_house":"杭州","f_name":"日本三井化学","input_time":"09-29 14:34","type":"2","content":"dfdf","name":"李一帆","is_pass":"0","c_name":"上海中晨电子商务股份有限公司","thumb":"http://pic.myplas.com/upload/17/08/03/5982a32bc386f.PNG","contents":"价格1200.00元左右/7000F/日本三井化学/杭州/dfdf","says":[]},{"id":"114095","p_id":"2596","user_id":"3858","model":"7000F","unit_price":"1200.00","store_house":"杭州","f_name":"日本三井化学","input_time":"09-29 14:34","type":"1","content":"求 7000f","name":"李一帆","is_pass":"0","c_name":"上海中晨电子商务股份有限公司","thumb":"http://pic.myplas.com/upload/17/08/03/5982a32bc386f.PNG","contents":"价格1200.00元左右/7000F/日本三井化学/杭州/求 7000f","says":[]},{"id":"114100","p_id":"2596","user_id":"3858","model":"7000F","unit_price":"1200.00","store_house":"杭州","f_name":"日本三井化学","input_time":"09-29 14:34","type":"2","content":"dfdf","name":"李一帆","is_pass":"0","c_name":"上海中晨电子商务股份有限公司","thumb":"http://pic.myplas.com/upload/17/08/03/5982a32bc386f.PNG","contents":"价格1200.00元左右/7000F/日本三井化学/杭州/dfdf","says":[]},{"id":"114106","p_id":"2596","user_id":"3858","model":"7000F","unit_price":"1300.00","store_house":"杭州","f_name":"日本三井化学","input_time":"09-29 14:34","type":"2","content":"求 7000f","name":"李一帆","is_pass":"0","c_name":"上海中晨电子商务股份有限公司","thumb":"http://pic.myplas.com/upload/17/08/03/5982a32bc386f.PNG","contents":"价格1300.00元左右/7000F/日本三井化学/杭州/求 7000f","says":[{"id":"1700","rev_id":"3858","user_id":"53402","is_read":"1","content":"123","input_time":"10-10 07:45","rev_name":"李一帆","user_name":"hh"},{"id":"1770","rev_id":"53402","user_id":"53405","is_read":"1","content":"我就问问价格","input_time":"10-12 09:36","rev_name":"hh","user_name":"小胖"}]},{"id":"114111","p_id":"2596","user_id":"3858","model":"7000F","unit_price":"1300.00","store_house":"杭州","f_name":"日本三井化学","input_time":"09-29 14:34","type":"2","content":"dfdf","name":"李一帆","is_pass":"0","c_name":"上海中晨电子商务股份有限公司","thumb":"http://pic.myplas.com/upload/17/08/03/5982a32bc386f.PNG","contents":"价格1300.00元左右/7000F/日本三井化学/杭州/dfdf","says":[{"id":"2411","rev_id":"3858","user_id":"3858","is_read":"1","content":"一个人","input_time":"10-12 13:48","rev_name":"李一帆","user_name":"李一帆"},{"id":"2414","rev_id":"3858","user_id":"3858","is_read":"1","content":"一凡你好吗","input_time":"10-12 13:51","rev_name":"李一帆","user_name":"李一帆"},{"id":"2416","rev_id":"3858","user_id":"3858","is_read":"1","content":"三级回复","input_time":"10-12 13:51","rev_name":"李一帆","user_name":"李一帆"},{"id":"2443","rev_id":"3858","user_id":"3858","is_read":"1","content":"hello","input_time":"10-12 14:34","rev_name":"李一帆","user_name":"李一帆"},{"id":"2444","rev_id":"3858","user_id":"3858","is_read":"1","content":"二级","input_time":"10-12 14:35","rev_name":"李一帆","user_name":"李一帆"},{"id":"2445","rev_id":"3858","user_id":"3858","is_read":"1","content":"三级","input_time":"10-12 14:35","rev_name":"李一帆","user_name":"李一帆"},{"id":"2446","rev_id":"3858","user_id":"3858","is_read":"1","content":"四级","input_time":"10-12 14:35","rev_name":"李一帆","user_name":"李一帆"},{"id":"2447","rev_id":"3858","user_id":"3858","is_read":"1","content":"五级","input_time":"10-12 14:35","rev_name":"李一帆","user_name":"李一帆"},{"id":"2477","rev_id":"3858","user_id":"53453","is_read":"1","content":"hello too","input_time":"10-12 17:25","rev_name":"李一帆","user_name":"娟娟"}]},{"id":"114094","p_id":"2596","user_id":"3858","model":"7000F","unit_price":"1200.00","store_house":"杭州","f_name":"日本三井化学","input_time":"09-29 14:22","type":"1","content":"dfdf","name":"李一帆","is_pass":"0","c_name":"上海中晨电子商务股份有限公司","thumb":"http://pic.myplas.com/upload/17/08/03/5982a32bc386f.PNG","contents":"价格1200.00元左右/7000F/日本三井化学/杭州/dfdf","says":[]},{"id":"114099","p_id":"2596","user_id":"3858","model":"7000F","unit_price":"1200.00","store_house":"上海","f_name":"日本三井化学","input_time":"09-29 14:22","type":"1","content":"求 5000S","name":"李一帆","is_pass":"0","c_name":"上海中晨电子商务股份有限公司","thumb":"http://pic.myplas.com/upload/17/08/03/5982a32bc386f.PNG","contents":"价格1200.00元左右/7000F/日本三井化学/上海/求 5000S","says":[]},{"id":"114105","p_id":"2596","user_id":"3858","model":"7000F","unit_price":"1300.00","store_house":"杭州","f_name":"日本三井化学","input_time":"09-29 14:22","type":"2","content":"dfdf","name":"李一帆","is_pass":"0","c_name":"上海中晨电子商务股份有限公司","thumb":"http://pic.myplas.com/upload/17/08/03/5982a32bc386f.PNG","contents":"价格1300.00元左右/7000F/日本三井化学/杭州/dfdf","says":[]}]
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
         * id : 114118
         * p_id : 9481
         * user_id : 53453
         * model : 7000F
         * unit_price : 10000.00
         * store_house : 上海
         * f_name : 神华
         * input_time : 10-13 14:42
         * type : 2
         * content :
         * name : 娟娟
         * is_pass : 0
         * c_name : 上海晨达物流有限公司
         * thumb : http://pic.myplas.com/upload/17/09/01/59a93aba83fc0.PNG
         * contents : 价格10000.00元左右/7000F/神华/上海
         * says : []
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
        private String name;
        private String is_pass;
        private String c_name;
        private String thumb;
        private String contents;
        private List<?> says;

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

        public String getModel() {
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

        public String getF_name() {
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

        public String getC_name() {
            return c_name;
        }

        public void setC_name(String c_name) {
            this.c_name = c_name;
        }

        public String getThumb() {
            return thumb;
        }

        public void setThumb(String thumb) {
            this.thumb = thumb;
        }

        public String getContents() {
            return contents;
        }

        public void setContents(String contents) {
            this.contents = contents;
        }

        public List<?> getSays() {
            return says;
        }

        public void setSays(List<?> says) {
            this.says = says;
        }
    }
}
