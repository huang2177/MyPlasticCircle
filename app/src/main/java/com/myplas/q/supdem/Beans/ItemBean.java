package com.myplas.q.supdem.Beans;

/**
 * 编写： 黄双
 * 电话：15378412400
 * 邮箱：15378412400@163.com
 * 时间：2017/5/25 15:39
 */
public class ItemBean {
    public final static ItemBean itemBean = new ItemBean();

    private ItemBean() {
    }

    public static ItemBean getItemBean() {
        return itemBean;
    }

    public int page;

    public void setCurrent(int current) {
        this.current = current;
    }

    public int getCurrent() {

        return current;
    }

    public int current;
    public boolean hasMoreData;
    public String keywords, type, sortField1, sortField2, what = "1";

    public void setHasMoreData(boolean hasMoreData) {
        this.hasMoreData = hasMoreData;
    }

    public boolean isHasMoreData() {

        return hasMoreData;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getPage() {

        return page;
    }

    public String getKeywords() {
        return keywords;
    }

    public String getType() {
        return type;
    }

    public String getSortField1() {
        return sortField1;
    }

    public String getSortField2() {
        return sortField2;
    }

    public String getWhat() {
        return what;
    }

    public void setKeywords(String keywords) {

        this.keywords = keywords;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setSortField1(String sortField1) {
        this.sortField1 = sortField1;
    }

    public void setSortField2(String sortField2) {
        this.sortField2 = sortField2;
    }

    public void setWhat(String what) {
        this.what = what;
    }
}
