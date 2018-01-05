package com.myplas.q.supdem.beans;

import java.util.List;

/**
 * 编写：黄双
 * 邮箱：15378412400@163.com
 * 时间： 2017/6/291724.
 */

public class SearchNoResultBean {

    /**
     * err : 130
     * msg : 抱歉，未找到相关信息
     * combine : ["7000f","2119","218wj","7000","q281","5000S"]
     */

    private String msg;
    private List<String> combine;


    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<String> getCombine() {
        return combine;
    }

    public void setCombine(List<String> combine) {
        this.combine = combine;
    }
}
