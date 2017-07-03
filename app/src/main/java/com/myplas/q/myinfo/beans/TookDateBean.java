package com.myplas.q.myinfo.beans;

import java.util.List;

/**
 * 编写： 黄双
 * 电话：15378412400
 * 邮箱：15378412400@163.com
 * 时间：2017/5/17 15:09
 */
public class TookDateBean {

    /**
     * err : 0
     * took_date : ["2017-05-19","2017-05-26"]
     * start_date : 2017-05-17
     * end_date : 2017-06-17
     */

    private int err;
    private String start_date;
    private String end_date;
    private List<String> took_date;

    public int getErr() {
        return err;
    }

    public void setErr(int err) {
        this.err = err;
    }

    public String getStart_date() {
        return start_date;
    }

    public void setStart_date(String start_date) {
        this.start_date = start_date;
    }

    public String getEnd_date() {
        return end_date;
    }

    public void setEnd_date(String end_date) {
        this.end_date = end_date;
    }

    public List<String> getTook_date() {
        return took_date;
    }

    public void setTook_date(List<String> took_date) {
        this.took_date = took_date;
    }
}
