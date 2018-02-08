package com.myplas.q.myself.beans;

import java.util.List;

/**
 * @author 黄双
 * @date 2018/2/5 0005
 */

public class NewIntergralBean {


    /**
     * code : 0
     * data : [{"id":74627,"addtime":"2月1日 22:32","points":"+50","type":"发布标准报价","share_type":0,"gid":0,"remainder":8455},{"id":74615,"addtime":"2月1日 16:39","points":-10,"type":"查看通讯录","share_type":0,"gid":81008,"remainder":8465},{"id":74609,"addtime":"2月1日 15:27","points":-10,"type":"查看通讯录","share_type":0,"gid":32300,"remainder":8475},{"id":74600,"addtime":"2月1日 14:36","points":-10,"type":"查看通讯录","share_type":0,"gid":29357,"remainder":8485},{"id":74596,"addtime":"2月1日 14:08","points":-10,"type":"查看通讯录","share_type":0,"gid":38393,"remainder":8495},{"id":74595,"addtime":"2月1日 14:07","points":-10,"type":"查看通讯录","share_type":0,"gid":5258,"remainder":8505},{"id":74592,"addtime":"2月1日 13:57","points":"+100","type":"登录","share_type":0,"gid":0,"remainder":8405},{"id":74591,"addtime":"2月1日 13:55","points":-10,"type":"查看通讯录","share_type":0,"gid":81009,"remainder":8415},{"id":74522,"addtime":"1月31日 14:53","points":-10,"type":"查看通讯录","share_type":0,"gid":5245,"remainder":8425},{"id":74521,"addtime":"1月31日 14:53","points":-10,"type":"查看通讯录","share_type":0,"gid":44019,"remainder":8435}]
     * points : 8505
     */

    private String code;
    private String points;
    private List<DataBean> data;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getPoints() {
        return points;
    }

    public void setPoints(String points) {
        this.points = points;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * id : 74627
         * addtime : 2月1日 22:32
         * points : +50
         * type : 发布标准报价
         * share_type : 0
         * gid : 0
         * remainder : 8455
         */

        private String id;
        private String addtime;
        private String points;
        private String type;
        private String share_type;
        private String gid;
        private String remainder;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getAddtime() {
            return addtime;
        }

        public void setAddtime(String addtime) {
            this.addtime = addtime;
        }

        public String getPoints() {
            return points;
        }

        public void setPoints(String points) {
            this.points = points;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getShare_type() {
            return share_type;
        }

        public void setShare_type(String share_type) {
            this.share_type = share_type;
        }

        public String getGid() {
            return gid;
        }

        public void setGid(String gid) {
            this.gid = gid;
        }

        public String getRemainder() {
            return remainder;
        }

        public void setRemainder(String remainder) {
            this.remainder = remainder;
        }
    }
}
