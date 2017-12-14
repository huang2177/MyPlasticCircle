package com.myplas.q.myself.beans;

import java.util.List;

/**
 * 编写： 黄双
 * 电话：15378412400
 * 邮箱：15378412400@163.com
 * 时间：2017/3/27 19:16
 */
public class LookMeBean {


    /**
     * err : 0
     * data : {"history":[{"time":"2017年10月","person":[{"userid":"2062","company":"上海中信有限公司","name":"胖墩","date":"10月25日 14：51","thumb":"http://pic.myplas.com/upload/17/07/12/5965b7ff066cf.jpg","isvip":0,"type":1},{"userid":"53405","company":"上海中信有限公司","name":"小胖","date":"10月24日 15：18","thumb":"http://pic.myplas.com/upload/17/09/05/59ae0cd00dd3e.PNG","isvip":0,"type":1},{"userid":"53402","company":"上海中晨电子商务股份有限公司","name":"hh","date":"10月24日 14：40","thumb":"http://pic.myplas.com/upload/17/09/06/59af96a6a157f.PNG","isvip":0,"type":1},{"userid":"53441","company":"中国能之光新材料科技股份有限公司（宁波分公司）","name":"韩梅梅","date":"10月24日 11：30","thumb":"http://pic.myplas.com/myapp/img/female.jpg","isvip":0,"type":1},{"userid":"53475","company":"香港TVB塑料塑料","name":"欧阳震华","date":"10月24日 11：15","thumb":"http://pic.myplas.com/myapp/img/male.jpg","isvip":0,"type":1},{"userid":"3858","company":"上海中晨电子商务股份有限公司","name":"李一帆","date":"10月24日 11：15","thumb":"http://pic.myplas.com/upload/17/08/03/5982a32bc386f.PNG","isvip":0,"type":1},{"userid":"53453","company":"上海晨达物流有限公司","name":"娟娟","date":"10月24日 10：32","thumb":"http://pic.myplas.com/upload/17/09/01/59a93aba83fc0.PNG","isvip":0,"type":4},{"userid":"2062","company":"上海中信有限公司","name":"胖墩","date":"10月23日 15：03","thumb":"http://pic.myplas.com/upload/17/07/12/5965b7ff066cf.jpg","isvip":0,"type":1},{"userid":"3858","company":"上海中晨电子商务股份有限公司","name":"李一帆","date":"10月20日 10：55","thumb":"http://pic.myplas.com/upload/17/08/03/5982a32bc386f.PNG","isvip":0,"type":1},{"userid":"53397","company":"上海测试科技","name":"测试","date":"10月19日 10：16","thumb":"http://pic.myplas.com/upload/17/10/17/59e5a50c68b53.jpg","isvip":0,"type":2}]}],"totals":691,"today":4}
     */

    private String err;
    private DataBean data;

    public String getErr() {
        return err;
    }

    public void setErr(String err) {
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
         * history : [{"time":"2017年10月","person":[{"userid":"2062","company":"上海中信有限公司","name":"胖墩","date":"10月25日 14：51","thumb":"http://pic.myplas.com/upload/17/07/12/5965b7ff066cf.jpg","isvip":0,"type":1},{"userid":"53405","company":"上海中信有限公司","name":"小胖","date":"10月24日 15：18","thumb":"http://pic.myplas.com/upload/17/09/05/59ae0cd00dd3e.PNG","isvip":0,"type":1},{"userid":"53402","company":"上海中晨电子商务股份有限公司","name":"hh","date":"10月24日 14：40","thumb":"http://pic.myplas.com/upload/17/09/06/59af96a6a157f.PNG","isvip":0,"type":1},{"userid":"53441","company":"中国能之光新材料科技股份有限公司（宁波分公司）","name":"韩梅梅","date":"10月24日 11：30","thumb":"http://pic.myplas.com/myapp/img/female.jpg","isvip":0,"type":1},{"userid":"53475","company":"香港TVB塑料塑料","name":"欧阳震华","date":"10月24日 11：15","thumb":"http://pic.myplas.com/myapp/img/male.jpg","isvip":0,"type":1},{"userid":"3858","company":"上海中晨电子商务股份有限公司","name":"李一帆","date":"10月24日 11：15","thumb":"http://pic.myplas.com/upload/17/08/03/5982a32bc386f.PNG","isvip":0,"type":1},{"userid":"53453","company":"上海晨达物流有限公司","name":"娟娟","date":"10月24日 10：32","thumb":"http://pic.myplas.com/upload/17/09/01/59a93aba83fc0.PNG","isvip":0,"type":4},{"userid":"2062","company":"上海中信有限公司","name":"胖墩","date":"10月23日 15：03","thumb":"http://pic.myplas.com/upload/17/07/12/5965b7ff066cf.jpg","isvip":0,"type":1},{"userid":"3858","company":"上海中晨电子商务股份有限公司","name":"李一帆","date":"10月20日 10：55","thumb":"http://pic.myplas.com/upload/17/08/03/5982a32bc386f.PNG","isvip":0,"type":1},{"userid":"53397","company":"上海测试科技","name":"测试","date":"10月19日 10：16","thumb":"http://pic.myplas.com/upload/17/10/17/59e5a50c68b53.jpg","isvip":0,"type":2}]}]
         * totals : 691
         * today : 4
         */

        private String totals;
        private String today;
        private List<HistoryBean> history;

        public String getTotals() {
            return totals;
        }

        public void setTotals(String totals) {
            this.totals = totals;
        }

        public String getToday() {
            return today;
        }

        public void setToday(String today) {
            this.today = today;
        }

        public List<HistoryBean> getHistory() {
            return history;
        }

        public void setHistory(List<HistoryBean> history) {
            this.history = history;
        }

        public static class HistoryBean {
            /**
             * time : 2017年10月
             * person : [{"userid":"2062","company":"上海中信有限公司","name":"胖墩","date":"10月25日 14：51","thumb":"http://pic.myplas.com/upload/17/07/12/5965b7ff066cf.jpg","isvip":0,"type":1},{"userid":"53405","company":"上海中信有限公司","name":"小胖","date":"10月24日 15：18","thumb":"http://pic.myplas.com/upload/17/09/05/59ae0cd00dd3e.PNG","isvip":0,"type":1},{"userid":"53402","company":"上海中晨电子商务股份有限公司","name":"hh","date":"10月24日 14：40","thumb":"http://pic.myplas.com/upload/17/09/06/59af96a6a157f.PNG","isvip":0,"type":1},{"userid":"53441","company":"中国能之光新材料科技股份有限公司（宁波分公司）","name":"韩梅梅","date":"10月24日 11：30","thumb":"http://pic.myplas.com/myapp/img/female.jpg","isvip":0,"type":1},{"userid":"53475","company":"香港TVB塑料塑料","name":"欧阳震华","date":"10月24日 11：15","thumb":"http://pic.myplas.com/myapp/img/male.jpg","isvip":0,"type":1},{"userid":"3858","company":"上海中晨电子商务股份有限公司","name":"李一帆","date":"10月24日 11：15","thumb":"http://pic.myplas.com/upload/17/08/03/5982a32bc386f.PNG","isvip":0,"type":1},{"userid":"53453","company":"上海晨达物流有限公司","name":"娟娟","date":"10月24日 10：32","thumb":"http://pic.myplas.com/upload/17/09/01/59a93aba83fc0.PNG","isvip":0,"type":4},{"userid":"2062","company":"上海中信有限公司","name":"胖墩","date":"10月23日 15：03","thumb":"http://pic.myplas.com/upload/17/07/12/5965b7ff066cf.jpg","isvip":0,"type":1},{"userid":"3858","company":"上海中晨电子商务股份有限公司","name":"李一帆","date":"10月20日 10：55","thumb":"http://pic.myplas.com/upload/17/08/03/5982a32bc386f.PNG","isvip":0,"type":1},{"userid":"53397","company":"上海测试科技","name":"测试","date":"10月19日 10：16","thumb":"http://pic.myplas.com/upload/17/10/17/59e5a50c68b53.jpg","isvip":0,"type":2}]
             */

            private String time;
            private List<PersonBean> person;

            public String getTime() {
                return time;
            }

            public void setTime(String time) {
                this.time = time;
            }

            public List<PersonBean> getPerson() {
                return person;
            }

            public void setPerson(List<PersonBean> person) {
                this.person = person;
            }

            public static class PersonBean {
                /**
                 * userid : 2062
                 * company : 上海中信有限公司
                 * name : 胖墩
                 * date : 10月25日 14：51
                 * thumb : http://pic.myplas.com/upload/17/07/12/5965b7ff066cf.jpg
                 * isvip : 0
                 * type : 1
                 */

                private String userid;
                private String company;
                private String name;
                private String date;
                private String thumb;
                private String isvip;
                private String type;
                private String merge_three;

                public void setMerge_three(String merge_three) {
                    this.merge_three = merge_three;
                }

                public String getMerge_three() {
                    return merge_three;
                }
                public String getUserid() {
                    return userid;
                }

                public void setUserid(String userid) {
                    this.userid = userid;
                }

                public String getCompany() {
                    return company;
                }

                public void setCompany(String company) {
                    this.company = company;
                }

                public String getName() {
                    return name;
                }

                public void setName(String name) {
                    this.name = name;
                }

                public String getDate() {
                    return date;
                }

                public void setDate(String date) {
                    this.date = date;
                }

                public String getThumb() {
                    return thumb;
                }

                public void setThumb(String thumb) {
                    this.thumb = thumb;
                }

                public String getIsvip() {
                    return isvip;
                }

                public void setIsvip(String isvip) {
                    this.isvip = isvip;
                }

                public String getType() {
                    return type;
                }

                public void setType(String type) {
                    this.type = type;
                }
            }
        }
    }
}
