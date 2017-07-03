package com.myplas.q.myinfo.beans;

/**
 * 编写： 黄双
 * 电话：15378412400
 * 邮箱：15378412400@163.com
 * 时间：2017/3/27 19:16
 */
public class SaveMySelfInfoBean {

    /**
     * err : 0
     * data : {"user_id":"3858","name":"李一帆","c_id":"28803","mobile":"13764999601","adistinct":"华南","sex":"男","member_level":"排长","thumb":"http://statics.myplas.com/upload/16/11/08/58212ce337ad0.jpeg","thumbqq":"http://statics.myplas.com/upload/16/11/08/58212ce337ad0.jpeg","thumbcard":"http://static.nsuliao.com/upload/17/01/12/5876dcc6759ea.jpg","allow_send":{"focus":0,"repeat":0,"show":date_selected},"c_name":"上海中晨电子商务","need_product":"pp","address":"上海","buy":"65","sale":"59","total":10845,"rank":"1643","fans":"6","concern_model":""}
     */
    /**
     * user_id : 3858
     * name : 李一帆
     * c_id : 28803
     * mobile : 13764999601
     * adistinct : 华南
     * sex : 男
     * member_level : 排长
     * thumb : http://statics.myplas.com/upload/16/11/08/58212ce337ad0.jpeg
     * thumbqq : http://statics.myplas.com/upload/16/11/08/58212ce337ad0.jpeg
     * thumbcard : http://static.nsuliao.com/upload/17/01/12/5876dcc6759ea.jpg
     * allow_send : {"focus":0,"repeat":0,"show":date_selected}
     * c_name : 上海中晨电子商务
     * need_product : pp
     * address : 上海
     * buy : 65
     * sale : 59
     * total : 10845
     * rank : 1643
     * fans : 6
     * concern_model :
     */
    private String sex;
   // private AllowSendBean allow_send;
    private String address;
    private String buy;


    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getBuy() {
        return buy;
    }

    public void setBuy(String buy) {
        this.buy = buy;
    }


//        public static class AllowSendBean {
//            /**
//             * focus : 0
//             * repeat : 0
//             * show : date_selected
//             */
//
//            private String focus;
//            private String repeat;
//            private String show;
//
//            public String getFocus() {
//                return focus;
//            }
//
//            public void setFocus(String focus) {
//                this.focus = focus;
//            }
//
//            public String getRepeat() {
//                return repeat;
//            }
//
//            public void setRepeat(String repeat) {
//                this.repeat = repeat;
//            }
//
//            public String getShow() {
//                return show;
//            }
//
//            public void setShow(String show) {
//                this.show = show;
//            }
//        }

}
