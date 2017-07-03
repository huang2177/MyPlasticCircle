package com.myplas.q.myinfo.beans;

import java.util.List;

/**
 * 编写： 黄双
 * 电话：15378412400
 * 邮箱：15378412400@163.com
 * 时间：2017/3/29 14:39
 */
public class MyMessageBean {

    /**
     * err : 0
     * data : [{"id":"15594","pur_id":"83875","pur_user_id":"3858","user_id":"3858","content":"您于 03-27 13:54 发布的求购信息:塑料原料收到一条回复:需要什么料？","input_time":"03-27 20:12:29","is_read":"date_selected","type":"2"},{"id":"15580","pur_id":"83866","pur_user_id":"3858","user_id":"3858","content":"您于 03-27 13:47 发布的求购信息:塑料原料收到一条回复:什么料？","input_time":"03-27 14:25:01","is_read":"date_selected","type":"2"},{"id":"15479","pur_id":"83864","pur_user_id":"3858","user_id":"3858","content":"您关注的李一帆发布了1条求购信息，信息内容为:看看看拉拉手机打开垃圾袋","input_time":"03-27 13:45:57","is_read":"date_selected","type":"date_selected"},{"id":"15461","pur_id":"83863","pur_user_id":"3858","user_id":"3858","content":"您关注的李一帆发布了1条求购信息，信息内容为:看看看拉拉手机打开垃圾袋","input_time":"03-27 13:45:26","is_read":"date_selected","type":"date_selected"},{"id":"15441","pur_id":"83862","pur_user_id":"3858","user_id":"3858","content":"您关注的李一帆发布了1条求购信息，信息内容为:看看看拉拉手机打开垃圾袋","input_time":"03-27 13:45:08","is_read":"date_selected","type":"date_selected"},{"id":"13139","pur_id":"81192","pur_user_id":"28922","user_id":"3858","content":"您关注的王云霞发布了1条供给信息，信息内容为:价格8000.00元左右/700F/上海/上海","input_time":"03-16 11:50:15","is_read":"date_selected","type":"date_selected"},{"id":"12181","pur_id":"79390","pur_user_id":"5189","user_id":"3858","content":"您关注的二哥发布了1条供给信息，信息内容为:临沂出：伊朗62N07        伊朗52518        神华8007       临沂出：伊朗62N07        伊朗52518        神华8007    ","input_time":"03-10 16:26:32","is_read":"date_selected","type":"date_selected"},{"id":"11396","pur_id":"79389","pur_user_id":"5189","user_id":"3858","content":"您关注的二哥发布了1条供给信息，信息内容为:临沂出：伊朗62N07        伊朗52518        神华8007        独山子8008青岛出：伊朗52518        伊朗62N07        乌兹别克斯","input_time":"03-10 16:25:21","is_read":"date_selected","type":"date_selected"},{"id":"9250","pur_id":"76497","pur_user_id":"9271","user_id":"3858","content":"您关注的徐前进发布了1条求购信息，信息内容为:11111","input_time":"03-01 15:34:42","is_read":"date_selected","type":"date_selected"},{"id":"9089","pur_id":"76287","pur_user_id":"2062","user_id":"3858","content":"您关注的李铁道发布了1条供给信息，信息内容为:2119  上海99吨  实盘谈","input_time":"03-01 08:00:03","is_read":"date_selected","type":"date_selected"},{"id":"7560","pur_id":"75233","pur_user_id":"3858","user_id":"3858","content":"您于 02-25 14:08 发布的供给信息:价格7000.00元左右/7000/上海/上海收到一条回复:ok","input_time":"02-25 14:10:19","is_read":"date_selected","type":"2"},{"id":"7559","pur_id":"75233","pur_user_id":"3858","user_id":"3858","content":"您于 02-25 14:08 发布的供给信息:价格7000.00元左右/7000/上海/上海收到一条回复:最低多少","input_time":"02-25 14:10:08","is_read":"date_selected","type":"2"},{"id":"7542","pur_id":"75231","pur_user_id":"28922","user_id":"3858","content":"您关注的王云霞发布了1条供给信息，信息内容为:价格4000.00元左右/7042/上海/上海","input_time":"02-25 13:54:58","is_read":"date_selected","type":"date_selected"},{"id":"7537","pur_id":"75230","pur_user_id":"28922","user_id":"3858","content":"您关注的王云霞发布了1条供给信息，信息内容为:求7042","input_time":"02-25 13:54:32","is_read":"date_selected","type":"date_selected"},{"id":"7516","pur_id":"75227","pur_user_id":"28922","user_id":"3858","content":"您关注的王云霞发布了1条供给信息，信息内容为:价格4000.00元左右/500F/上海/上海","input_time":"02-25 13:43:21","is_read":"date_selected","type":"date_selected"},{"id":"6849","pur_id":"73456","pur_user_id":"28922","user_id":"3858","content":"您关注的王云霞发布了1条求购信息，信息内容为:价格5000.00元左右/500F/上海/上海","input_time":"02-22 10:12:34","is_read":"date_selected","type":"date_selected"}]
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
         * id : 15594
         * pur_id : 83875
         * pur_user_id : 3858
         * user_id : 3858
         * content : 您于 03-27 13:54 发布的求购信息:塑料原料收到一条回复:需要什么料？
         * input_time : 03-27 20:12:29
         * is_read : date_selected
         * type : 2
         */

        private String id;
        private String pur_id;
        private String pur_user_id;
        private String user_id;
        private String content;
        private String input_time;
        private String is_read;
        private String type;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getPur_id() {
            return pur_id;
        }

        public void setPur_id(String pur_id) {
            this.pur_id = pur_id;
        }

        public String getPur_user_id() {
            return pur_user_id;
        }

        public void setPur_user_id(String pur_user_id) {
            this.pur_user_id = pur_user_id;
        }

        public String getUser_id() {
            return user_id;
        }

        public void setUser_id(String user_id) {
            this.user_id = user_id;
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

        public String getIs_read() {
            return is_read;
        }

        public void setIs_read(String is_read) {
            this.is_read = is_read;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }
    }
}
