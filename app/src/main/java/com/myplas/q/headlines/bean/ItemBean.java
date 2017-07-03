package com.myplas.q.headlines.bean;

/**
 * 编写： 黄双
 * 电话：15378412400
 * 邮箱：15378412400@163.com
 * 时间：2017/4/2 22:09
 */
public class ItemBean {
    String string;
    boolean isclicked;
    int color;
    String cate_id;
    public void setColor(int color) {
        this.color = color;
    }

    public int getColor() {

        return color;
    }

    public String getCate_id() {
        return cate_id;
    }

    public void setCate_id(String cate_id) {

        this.cate_id = cate_id;
    }

    public String getString() {
        return string;
    }
    public boolean isclicked() {
        return isclicked;
    }
    public void setString(String string) {
        this.string = string;
    }
    public void setIsclicked(boolean isclicked) {
        this.isclicked = isclicked;
    }
}
