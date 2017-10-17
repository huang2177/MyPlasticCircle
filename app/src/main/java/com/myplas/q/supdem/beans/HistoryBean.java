package com.myplas.q.supdem.beans;

import java.util.List;

/**
 * 编写：黄双
 * 邮箱：15378412400@163.com
 * 时间： 2017/6/281408.
 */

public class HistoryBean {


    /**
     * err : 0
     * history : ["7000f"]
     * recommend : ["7000F","7042","2426H","BL3","52518","5000S"]
     * hot_search : 上海
     */

    private int err;
    private String hot_search;
    private List<String> history;
    private List<String> recommend;

    public int getErr() {
        return err;
    }

    public void setErr(int err) {
        this.err = err;
    }

    public String getHot_search() {
        return hot_search;
    }

    public void setHot_search(String hot_search) {
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
}
