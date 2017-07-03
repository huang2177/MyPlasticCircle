package com.myplas.q.headlines.bean;

import java.io.Serializable;

/**
 * 编写： 黄双
 * 电话：15378412400
 * 邮箱：15378412400@163.com
 * 时间：2017/4/5 19:09
 */
public class CompanyDetailBean implements Serializable{

    /**
     * err : 0
     * data : {"id":"3","name":"上海中晨电子商务股份有限公司","register_no":"","belong_org":"上海市工商局","oper_name":"李铁道","start_date":"2007-04-11","end_date":"0","status":"存续（在营、开业、在册）","province":"SH","update_date":"2017-03-13","credit_code":"91310000660746422P","register_capi":"3456.620900","econkind":"股份有限公司（非上市）","industry":"批发和零售业","sub_industry":"批发业","address":"上海市广灵二路122号415室","scope":"以电子商务方式从事塑料材料、金属材料及制品、化工原料（除危险品）、建筑装潢材料及制品、橡胶制品、木材、五金交电的销售，计算机软件、网络技术的开发，网络系统集成，广告的设计，利用自有媒体发布广告，会展服务，市场信息咨询与调查（不得从事是社会调查、社会调研、民意调查、民意测验），从事货物及技术的进出口业务，企业管理咨询，投资咨询，企业形象策划，市场营销策划，电信业务（第二类增值电信业务中的信息服务业务（仅限互联网信息服务））。\r\n【依法须经批准的项目，经相关部门批准后方可开展经营活动】","term_start":"2007-04-11","term_end":"0","check_date":"2007-04-11","phone_number":"021-60295176","email":"83080667@qq.com","website_name":"","website_url":"http://"}
     */

    private int err;
    private DataBean data;

    public int getErr() {
        return err;
    }

    public void setErr(int err) {
        this.err = err;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean implements Serializable {
        /**
         * id : 3
         * name : 上海中晨电子商务股份有限公司
         * register_no :
         * belong_org : 上海市工商局
         * oper_name : 李铁道
         * start_date : 2007-04-11
         * end_date : 0
         * status : 存续（在营、开业、在册）
         * province : SH
         * update_date : 2017-03-13
         * credit_code : 91310000660746422P
         * register_capi : 3456.620900
         * econkind : 股份有限公司（非上市）
         * industry : 批发和零售业
         * sub_industry : 批发业
         * address : 上海市广灵二路122号415室
         * scope : 以电子商务方式从事塑料材料、金属材料及制品、化工原料（除危险品）、建筑装潢材料及制品、橡胶制品、木材、五金交电的销售，计算机软件、网络技术的开发，网络系统集成，广告的设计，利用自有媒体发布广告，会展服务，市场信息咨询与调查（不得从事是社会调查、社会调研、民意调查、民意测验），从事货物及技术的进出口业务，企业管理咨询，投资咨询，企业形象策划，市场营销策划，电信业务（第二类增值电信业务中的信息服务业务（仅限互联网信息服务））。
         【依法须经批准的项目，经相关部门批准后方可开展经营活动】
         * term_start : 2007-04-11
         * term_end : 0
         * check_date : 2007-04-11
         * phone_number : 021-60295176
         * email : 83080667@qq.com
         * website_name :
         * website_url : http://
         */

        private String id;
        private String name;
        private String register_no;
        private String belong_org;
        private String oper_name;
        private String start_date;
        private String end_date;
        private String status;
        private String province;
        private String update_date;
        private String credit_code;
        private String register_capi;
        private String econkind;
        private String industry;
        private String sub_industry;
        private String address;
        private String scope;
        private String term_start;
        private String term_end;
        private String check_date;
        private String phone_number;
        private String email;
        private String website_name;
        private String website_url;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getRegister_no() {
            return register_no;
        }

        public void setRegister_no(String register_no) {
            this.register_no = register_no;
        }

        public String getBelong_org() {
            return belong_org;
        }

        public void setBelong_org(String belong_org) {
            this.belong_org = belong_org;
        }

        public String getOper_name() {
            return oper_name;
        }

        public void setOper_name(String oper_name) {
            this.oper_name = oper_name;
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

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getProvince() {
            return province;
        }

        public void setProvince(String province) {
            this.province = province;
        }

        public String getUpdate_date() {
            return update_date;
        }

        public void setUpdate_date(String update_date) {
            this.update_date = update_date;
        }

        public String getCredit_code() {
            return credit_code;
        }

        public void setCredit_code(String credit_code) {
            this.credit_code = credit_code;
        }

        public String getRegister_capi() {
            return register_capi;
        }

        public void setRegister_capi(String register_capi) {
            this.register_capi = register_capi;
        }

        public String getEconkind() {
            return econkind;
        }

        public void setEconkind(String econkind) {
            this.econkind = econkind;
        }

        public String getIndustry() {
            return industry;
        }

        public void setIndustry(String industry) {
            this.industry = industry;
        }

        public String getSub_industry() {
            return sub_industry;
        }

        public void setSub_industry(String sub_industry) {
            this.sub_industry = sub_industry;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getScope() {
            return scope;
        }

        public void setScope(String scope) {
            this.scope = scope;
        }

        public String getTerm_start() {
            return term_start;
        }

        public void setTerm_start(String term_start) {
            this.term_start = term_start;
        }

        public String getTerm_end() {
            return term_end;
        }

        public void setTerm_end(String term_end) {
            this.term_end = term_end;
        }

        public String getCheck_date() {
            return check_date;
        }

        public void setCheck_date(String check_date) {
            this.check_date = check_date;
        }

        public String getPhone_number() {
            return phone_number;
        }

        public void setPhone_number(String phone_number) {
            this.phone_number = phone_number;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getWebsite_name() {
            return website_name;
        }

        public void setWebsite_name(String website_name) {
            this.website_name = website_name;
        }

        public String getWebsite_url() {
            return website_url;
        }

        public void setWebsite_url(String website_url) {
            this.website_url = website_url;
        }
    }
}
