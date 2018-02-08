package com.myplas.q.supdem.beans;

import java.util.List;

/**
 * 编写：黄双
 * 邮箱：15378412400@163.com
 * 时间： 2017/6/291724.
 */

public class SearchNoResultBean {


    /**
     * code : 404
     * message : 没有相关信息
     * recommendation : ["7000F","2119","负荷开关","5000s","7042"]
     */

    private int code;
    private String message;
    private List<String> recommendation;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<String> getRecommendation() {
        return recommendation;
    }

    public void setRecommendation(List<String> recommendation) {
        this.recommendation = recommendation;
    }
}
