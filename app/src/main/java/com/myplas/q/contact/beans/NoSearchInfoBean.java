package com.myplas.q.contact.beans;

import java.util.List;

/**
 * 作者:huangshuang
 * 事件 2017/10/24 0024.
 * 邮箱： 15378412400@163.com
 */

public class NoSearchInfoBean {

    /**
     * err : 2
     * msg : 没有相关数据
     * recommendation : ["HDPE","pp","低压","7000F","张"]
     */

    private String msg;
    private List<String> recommendation;


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
