package com.myplas.q.contact.beans;

import java.util.List;

/**
 * 作者:huangshuang
 * 事件 2017/10/18 0018.
 * 邮箱： 15378412400@163.com
 */

public class RecordBean {

    /**
     * err : 0
     * recommendation : ["7000F","低压","pp","HDPE"]
     * search_records : []
     */

    private int err;
    private List<String> recommendation;
    private List<String> search_records;

    public int getErr() {
        return err;
    }

    public void setErr(int err) {
        this.err = err;
    }

    public List<String> getRecommendation() {
        return recommendation;
    }

    public void setRecommendation(List<String> recommendation) {
        this.recommendation = recommendation;
    }

    public List<String> getSearch_records() {
        return search_records;
    }

    public void setSearch_records(List<String> search_records) {
        this.search_records = search_records;
    }
}
