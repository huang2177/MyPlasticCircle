package com.myplas.q.supdem.Beans;

import java.util.List;

/**
 * 编写：黄双
 * 邮箱：15378412400@163.com
 * 时间： 2017/6/281408.
 */

public class HistoryBean {


    /**
     * err : 0
     * history : ["人民币","分分孤狐给","pp","7000f","哈哈"]
     * recommend : ["HDPE","or","储蓄罐","哈哈","人民币","测试","PVCbaa","50","7000y"]
     * hot_search : {"content":"7000F","num":"315"}
     */

    private int err;
    private HotSearchBean hot_search;
    private List<String> history;
    private List<String> recommend;

    public int getErr() {
        return err;
    }

    public void setErr(int err) {
        this.err = err;
    }

    public HotSearchBean getHot_search() {
        return hot_search;
    }

    public void setHot_search(HotSearchBean hot_search) {
        this.hot_search = hot_search;
    }

    public List<String> getHistory() {
        return history;
    }

    public void setHistory(List<String> history) {
        this.history = history;
    }

    public List<String> getRecommend() {
        return recommend;
    }

    public void setRecommend(List<String> recommend) {
        this.recommend = recommend;
    }

    public static class HotSearchBean {
        /**
         * content : 7000F
         * num : 315
         */

        private String content;
        private String num;

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getNum() {
            return num;
        }

        public void setNum(String num) {
            this.num = num;
        }
    }
}
