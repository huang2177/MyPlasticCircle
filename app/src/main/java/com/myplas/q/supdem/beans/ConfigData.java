package com.myplas.q.supdem.beans;

/**
 * 编写： 黄双
 * 电话：15378412400
 * 邮箱：15378412400@163.com
 * 时间：2017/5/25 15:39
 */
public class ConfigData {
    public static String type;
    public static String what;
    public static String sortField1;
    public static String sortField2;

    public final static ConfigData itemBean = new ConfigData();

    private ConfigData() {
        type = "0";
        what = "1";
    }

    public static void setCurrentItem(int position) {
        switch (position) {
            case 0://全   部
                what = "1";
                sortField2 = "";
                sortField1 = "ALL";
                break;
            case 1://智能推荐
                what = "2";
                sortField1 = "ALL";
                sortField2 = "AUTO";
                break;
            case 2://我的关注
                what = "3";
                sortField1 = "";
                sortField2 = "CONCERN";
                break;
            case 3://我的供求
                what = "4";
                sortField1 = "";
                sortField2 = "DEMANDORSUPPLY";
                break;
        }
    }


    public static String getType(int position) {
        switch (position) {
            case 0:
                type = "0";
                break;
            case 1:
                type = "2";
                break;
            case 2:
                type = "1";
                break;
            default:
                type = "0";
                break;
        }
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
