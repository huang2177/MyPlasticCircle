package com.myplas.q.supdem.Beans;

import java.util.List;

/**
 * 编写：黄双
 * 邮箱：15378412400@163.com
 * 时间： 2017/6/291724.
 */

public class SearchResultBean {
    private String err;
    private String total;
    private String search;
    private String combine;
    private List<ListBean> list;

    public String getErr() {
        return err;
    }

    public void setErr(String err) {
        this.err = err;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getSearch() {
        return search;
    }

    public void setSearch(String search) {
        this.search = search;
    }

    public String getCombine() {
        return combine;
    }

    public void setCombine(String combine) {
        this.combine = combine;
    }

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    public static class ListBean {
        private String Company;
        private String Production;
        private String ISForward;
        private String Price;
        private String PlsticNumber;
        private String QQName;
        private String QQNumber;
        private String UpdateTime;
        private String Isbuy;
        private String GoodssPosition;
        private String new_id;
        private String Production_one;
        private String type;
        private String id;
        private String p_id;
        private String user_id;
        private String content;
        private String name;
        private String thumb;
        private String thumbqq;
        private String sex;
        private String mobile_province;
        private String is_pass;
        private List<String> QQImage;
        private List<String> Iphone_list;
        private List<String> QQName_Number;

        public String getCompany() {
            return Company;
        }

        public void setCompany(String Company) {
            this.Company = Company;
        }

        public String getProduction() {
            return Production;
        }

        public void setProduction(String Production) {
            this.Production = Production;
        }

        public String getISForward() {
            return ISForward;
        }

        public void setISForward(String ISForward) {
            this.ISForward = ISForward;
        }

        public String getPrice() {
            return Price;
        }

        public void setPrice(String Price) {
            this.Price = Price;
        }

        public String getPlsticNumber() {
            return PlsticNumber;
        }

        public void setPlsticNumber(String PlsticNumber) {
            this.PlsticNumber = PlsticNumber;
        }

        public String getQQName() {
            return QQName;
        }

        public void setQQName(String QQName) {
            this.QQName = QQName;
        }

        public String getQQNumber() {
            return QQNumber;
        }

        public void setQQNumber(String QQNumber) {
            this.QQNumber = QQNumber;
        }

        public String getUpdateTime() {
            return UpdateTime;
        }

        public void setUpdateTime(String UpdateTime) {
            this.UpdateTime = UpdateTime;
        }

        public String getIsbuy() {
            return Isbuy;
        }

        public void setIsbuy(String Isbuy) {
            this.Isbuy = Isbuy;
        }

        public String getGoodssPosition() {
            return GoodssPosition;
        }

        public void setGoodssPosition(String GoodssPosition) {
            this.GoodssPosition = GoodssPosition;
        }

        public String getNew_id() {
            return new_id;
        }

        public void setNew_id(String new_id) {
            this.new_id = new_id;
        }

        public String getProduction_one() {
            return Production_one;
        }

        public void setProduction_one(String Production_one) {
            this.Production_one = Production_one;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getP_id() {
            return p_id;
        }

        public void setP_id(String p_id) {
            this.p_id = p_id;
        }

        public String getUser_id() {
            return user_id;
        }

        public void setUser_id(String user_id) {
            this.user_id = user_id;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getThumb() {
            return thumb;
        }

        public void setThumb(String thumb) {
            this.thumb = thumb;
        }

        public String getThumbqq() {
            return thumbqq;
        }

        public void setThumbqq(String thumbqq) {
            this.thumbqq = thumbqq;
        }

        public String getSex() {
            return sex;
        }

        public void setSex(String sex) {
            this.sex = sex;
        }

        public String getMobile_province() {
            return mobile_province;
        }

        public void setMobile_province(String mobile_province) {
            this.mobile_province = mobile_province;
        }

        public String getIs_pass() {
            return is_pass;
        }

        public void setIs_pass(String is_pass) {
            this.is_pass = is_pass;
        }

        public List<String> getQQImage() {
            return QQImage;
        }

        public void setQQImage(List<String> QQImage) {
            this.QQImage = QQImage;
        }

        public List<String> getIphone_list() {
            return Iphone_list;
        }

        public void setIphone_list(List<String> Iphone_list) {
            this.Iphone_list = Iphone_list;
        }

        public List<String> getQQName_Number() {
            return QQName_Number;
        }

        public void setQQName_Number(List<String> QQName_Number) {
            this.QQName_Number = QQName_Number;
        }
    }
}
