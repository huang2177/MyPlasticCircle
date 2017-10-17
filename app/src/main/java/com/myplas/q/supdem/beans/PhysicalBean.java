package com.myplas.q.supdem.beans;

import java.io.Serializable;
import java.util.List;

/**
 * 编写：黄双
 * 邮箱：15378412400@163.com
 * 时间： 2017/6/291724.
 */

public class PhysicalBean implements Serializable {

    /**
     * err : 0
     * data : [{"lid":"638","type":"HDPE","name":"7000F","company":"燕山石化","purpose":"大棚膜、商品袋、废品袋","standard":"其它 薄膜 其它","color":"","remark":"类型：挤塑"},{"lid":"2803","type":"HDPE","name":"7000F","company":"乐天化学","purpose":"用途：一般薄膜，购物袋，农膜，薄膜。","standard":"高强度 薄膜 挤出","color":"","remark":"特性 ：优良的抗撕裂强度"},{"lid":"7731","type":"HDPE","name":"7000F","company":"三井化学","purpose":"挤出薄膜类。其机械强度和刚性十分优良，特别在高速条件下的加工性能尤为优异，产品的耐气候性能也很突出。主要用来制取高强度薄膜、各类商业用袋和易处理废物袋等","standard":"其它 其它 其它","color":"","remark":""},{"lid":"15978","type":"PC/ABS","name":"TN-7000F","company":"日本帝人","purpose":"","standard":"阻燃 其它 其它","color":"","remark":""},{"lid":"19938","type":"HDPE","name":"7000F","company":"日本普瑞曼","purpose":"超薄强化薄膜","standard":"其它 薄膜 其它","color":"","remark":"较好的冲击强度"},{"lid":"36409","type":"HDPE","name":"7000F","company":"齐鲁石化","purpose":"","standard":"其它 其它 其它","color":"","remark":""},{"lid":"59558","type":"HDPE","name":"7000F","company":"伊朗Mehr","purpose":"增强超薄膜","standard":"其它 薄膜 其它","color":"","remark":""}]
     */

    private int err;
    private List<DataBean> data;

    public int getErr() {
        return err;
    }

    public void setErr(int err) {
        this.err = err;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean implements Serializable {
        /**
         * lid : 638
         * type : HDPE
         * name : 7000F
         * company : 燕山石化
         * purpose : 大棚膜、商品袋、废品袋
         * standard : 其它 薄膜 其它
         * color :
         * remark : 类型：挤塑
         */

        private String lid;
        private String type;
        private String name;
        private String company;
        private String purpose;
        private String standard;
        private String color;
        private String remark;

        public String getLid() {
            return lid;
        }

        public void setLid(String lid) {
            this.lid = lid;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getCompany() {
            return company;
        }

        public void setCompany(String company) {
            this.company = company;
        }

        public String getPurpose() {
            return purpose;
        }

        public void setPurpose(String purpose) {
            this.purpose = purpose;
        }

        public String getStandard() {
            return standard;
        }

        public void setStandard(String standard) {
            this.standard = standard;
        }

        public String getColor() {
            return color;
        }

        public void setColor(String color) {
            this.color = color;
        }

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }
    }
}
