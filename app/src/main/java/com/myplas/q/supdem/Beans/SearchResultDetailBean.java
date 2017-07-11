package com.myplas.q.supdem.Beans;

import java.util.List;

/**
 * 编写：黄双
 * 邮箱：15378412400@163.com
 * 时间： 2017/6/291724.
 */

public class SearchResultDetailBean {

    /**
     * err : 0
     * data : {"Company":"广东金柏能新材料有限公司","Production":"P-伊朗石化(火炬)","ISForward":"现货","Price":"0","PlsticNumber":"7000F","QQName":"金柏能 林晓君","QQNumber":"852049434","QQImage":"http://q2.qlogo.cn/headimg_dl?bs=qq&dst_uin=852049434&src_uin=*&fid=*&spec=100&url_enc=0&referer=bu_interface&term_type=PC","Isbuy":"0","GoodssPosition":"上海","ShowInformation":[{"id":"29795","title":"齐鲁石化HDPE装置停车","cate_name":"装置动态"},{"id":"29821","title":"上游早报：原油及PVC单体5月2日收盘价格","cate_name":"上游动态"},false],"FriendSearch":[{"company":"山东日聚电子商务有限公司","mobile":"15666617220","content":"7000F","QQImage":"http://q2.qlogo.cn/headimg_dl?bs=qq&dst_uin=282443191&src_uin=*&fid=*&spec=100&url_enc=0&referer=bu_interface&term_type=PC"},{"company":"上海中晨电子商务股份有限公司","mobile":"13816297484","content":"7000F","QQImage":"http://q2.qlogo.cn/headimg_dl?bs=qq&dst_uin=137111752&src_uin=*&fid=*&spec=100&url_enc=0&referer=bu_interface&term_type=PC"},{"company":"上海华浩彤贸易有限公司","mobile":"15896851245","content":"7000f","QQImage":"http://q2.qlogo.cn/headimg_dl?bs=qq&dst_uin=2694067915&src_uin=*&fid=*&spec=100&url_enc=0&referer=bu_interface&term_type=PC"},{"company":"上海塑米信息科技有限公司","mobile":"13658961254","content":"7000F","QQImage":"http://q2.qlogo.cn/headimg_dl?bs=qq&dst_uin=1172786508&src_uin=*&fid=*&spec=100&url_enc=0&referer=bu_interface&term_type=PC"},{"company":"中化塑料有限公司","mobile":"15900859805","content":"7000F","QQImage":"http://q2.qlogo.cn/headimg_dl?bs=qq&dst_uin=2430778164&src_uin=*&fid=*&spec=100&url_enc=0&referer=bu_interface&term_type=PC"}],"IphoneList":[{"Iphone":"13502944480","UserName":"刘旭春"},{"Iphone":"13502977802","UserName":"佘松鑫"},{"Iphone":"13592821215","UserName":"许华泳"},{"Iphone":"13592862198","UserName":"林晓君"}],"PlasticPhysicalTable":""}
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

    public static class DataBean {
        /**
         * Company : 广东金柏能新材料有限公司
         * Production : P-伊朗石化(火炬)
         * ISForward : 现货
         * Price : 0
         * PlsticNumber : 7000F
         * QQName : 金柏能 林晓君
         * QQNumber : 852049434
         * QQImage : http://q2.qlogo.cn/headimg_dl?bs=qq&dst_uin=852049434&src_uin=*&fid=*&spec=100&url_enc=0&referer=bu_interface&term_type=PC
         * Isbuy : 0
         * GoodssPosition : 上海
         * ShowInformation : [{"id":"29795","title":"齐鲁石化HDPE装置停车","cate_name":"装置动态"},{"id":"29821","title":"上游早报：原油及PVC单体5月2日收盘价格","cate_name":"上游动态"},false]
         * FriendSearch : [{"company":"山东日聚电子商务有限公司","mobile":"15666617220","content":"7000F","QQImage":"http://q2.qlogo.cn/headimg_dl?bs=qq&dst_uin=282443191&src_uin=*&fid=*&spec=100&url_enc=0&referer=bu_interface&term_type=PC"},{"company":"上海中晨电子商务股份有限公司","mobile":"13816297484","content":"7000F","QQImage":"http://q2.qlogo.cn/headimg_dl?bs=qq&dst_uin=137111752&src_uin=*&fid=*&spec=100&url_enc=0&referer=bu_interface&term_type=PC"},{"company":"上海华浩彤贸易有限公司","mobile":"15896851245","content":"7000f","QQImage":"http://q2.qlogo.cn/headimg_dl?bs=qq&dst_uin=2694067915&src_uin=*&fid=*&spec=100&url_enc=0&referer=bu_interface&term_type=PC"},{"company":"上海塑米信息科技有限公司","mobile":"13658961254","content":"7000F","QQImage":"http://q2.qlogo.cn/headimg_dl?bs=qq&dst_uin=1172786508&src_uin=*&fid=*&spec=100&url_enc=0&referer=bu_interface&term_type=PC"},{"company":"中化塑料有限公司","mobile":"15900859805","content":"7000F","QQImage":"http://q2.qlogo.cn/headimg_dl?bs=qq&dst_uin=2430778164&src_uin=*&fid=*&spec=100&url_enc=0&referer=bu_interface&term_type=PC"}]
         * IphoneList : [{"Iphone":"13502944480","UserName":"刘旭春"},{"Iphone":"13502977802","UserName":"佘松鑫"},{"Iphone":"13592821215","UserName":"许华泳"},{"Iphone":"13592862198","UserName":"林晓君"}]
         * PlasticPhysicalTable :
         */

        private String Company;
        private String Production;
        private String ISForward;
        private String Price;
        private String PlsticNumber;
        private String QQName;
        private String QQNumber;
        private String QQImage;
        private String Isbuy;
        private String GoodssPosition;
        private String PlasticPhysicalTable;
        private List<ShowInformationBean> ShowInformation;
        private List<FriendSearchBean> FriendSearch;
        private List<IphoneListBean> IphoneList;

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

        public String getQQImage() {
            return QQImage;
        }

        public void setQQImage(String QQImage) {
            this.QQImage = QQImage;
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

        public String getPlasticPhysicalTable() {
            return PlasticPhysicalTable;
        }

        public void setPlasticPhysicalTable(String PlasticPhysicalTable) {
            this.PlasticPhysicalTable = PlasticPhysicalTable;
        }

        public List<ShowInformationBean> getShowInformation() {
            return ShowInformation;
        }

        public void setShowInformation(List<ShowInformationBean> ShowInformation) {
            this.ShowInformation = ShowInformation;
        }

        public List<FriendSearchBean> getFriendSearch() {
            return FriendSearch;
        }

        public void setFriendSearch(List<FriendSearchBean> FriendSearch) {
            this.FriendSearch = FriendSearch;
        }

        public List<IphoneListBean> getIphoneList() {
            return IphoneList;
        }

        public void setIphoneList(List<IphoneListBean> IphoneList) {
            this.IphoneList = IphoneList;
        }

        public static class ShowInformationBean {
            /**
             * id : 29795
             * title : 齐鲁石化HDPE装置停车
             * cate_name : 装置动态
             */

            private String id;
            private String title;
            private String cate_name;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getCate_name() {
                return cate_name;
            }

            public void setCate_name(String cate_name) {
                this.cate_name = cate_name;
            }
        }

        public static class FriendSearchBean {
            /**
             * company : 山东日聚电子商务有限公司
             * mobile : 15666617220
             * content : 7000F
             * QQImage : http://q2.qlogo.cn/headimg_dl?bs=qq&dst_uin=282443191&src_uin=*&fid=*&spec=100&url_enc=0&referer=bu_interface&term_type=PC
             */

            private String company;
            private String mobile;
            private String content;
            private String QQImage;

            public String getCompany() {
                return company;
            }

            public void setCompany(String company) {
                this.company = company;
            }

            public String getMobile() {
                return mobile;
            }

            public void setMobile(String mobile) {
                this.mobile = mobile;
            }

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
            }

            public String getQQImage() {
                return QQImage;
            }

            public void setQQImage(String QQImage) {
                this.QQImage = QQImage;
            }
        }

        public static class IphoneListBean {
            /**
             * Iphone : 13502944480
             * UserName : 刘旭春
             */

            private String Iphone;
            private String UserName;

            public String getIphone() {
                return Iphone;
            }

            public void setIphone(String Iphone) {
                this.Iphone = Iphone;
            }

            public String getUserName() {
                return UserName;
            }

            public void setUserName(String UserName) {
                this.UserName = UserName;
            }
        }
    }
}
