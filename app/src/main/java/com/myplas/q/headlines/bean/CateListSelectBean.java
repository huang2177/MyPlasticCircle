package com.myplas.q.headlines.bean;

/**
 * 编写： 黄双
 * 电话：15378412400
 * 邮箱：15378412400@163.com
 * 时间：2017/4/2 22:09
 */
public class CateListSelectBean {
    String string;
    String img;
    String cate_id;
    boolean selected;
    boolean isSaveed;
    boolean isUnSelecteable;

    public void setSaveed(boolean saveed) {
        isSaveed = saveed;
    }

    public void setUnSelecteable(boolean unSelecteable) {
        isUnSelecteable = unSelecteable;
    }

    public boolean isUnSelecteable() {

        return isUnSelecteable;
    }

    public boolean isSaveed() {
        return isSaveed;
    }

    public void setIssaveed(boolean issaveed) {

        this.isSaveed = issaveed;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public boolean isSelected() {

        return selected;
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

    public void setString(String string) {
        this.string = string;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {

        this.img = img;
    }
}
