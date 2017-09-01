package com.myplas.q.headlines.bean;

import java.util.List;

/**
 * 作者：  黄双
 * 事件 2017/9/1 0001.
 * 邮箱： 15378412400@163.com
 */

public class SearchNoResultBean {

    /**
     * err : 2
     * recommendation : ["福建","上海","5000S","2118","7000","AA","pp","666","PE"]
     * msg : 没有相关数据
     */

    private int err;
    private String msg;
    private List<String> recommendation;

    public int getErr() {
        return err;
    }

    public void setErr(int err) {
        this.err = err;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<String> getRecommendation() {
        return recommendation;
    }

    public void setRecommendation(List<String> recommendation) {
        this.recommendation = recommendation;
    }
}
