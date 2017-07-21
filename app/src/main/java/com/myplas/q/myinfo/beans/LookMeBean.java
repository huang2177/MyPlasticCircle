package com.myplas.q.myinfo.beans;

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
     * data : {"history":[{"time":"2017年7月","person":[{"userid":"3858","company":"上海中晨电子商务股份有限公司","name":"李一帆","date":"7月20日 12：39","thumb":"http://pic.myplas.com/upload/17/07/14/59683e9ac80d3.png","isvip":"0"},{"userid":"3858","company":"上海中晨电子商务股份有限公司","name":"李一帆","date":"7月20日 11：54","thumb":"http://pic.myplas.com/upload/17/07/14/59683e9ac80d3.png","isvip":"0"},{"userid":"3858","company":"上海中晨电子商务股份有限公司","name":"李一帆","date":"7月20日 11：53","thumb":"http://pic.myplas.com/upload/17/07/14/59683e9ac80d3.png","isvip":"0"},{"userid":"53475","company":"香港TVB塑料塑料","name":"欧阳震华","date":"7月20日 10：37","thumb":"http://pic.myplas.com/myapp/img/male.jpg","isvip":"0"},{"userid":"53475","company":"香港TVB塑料塑料","name":"欧阳震华","date":"7月20日 10：37","thumb":"http://pic.myplas.com/myapp/img/male.jpg","isvip":"0"},{"userid":"53402","company":"上海中晨电子商务股份有限公司","name":"hh","date":"7月19日 19：34","thumb":"http://pic.myplas.com/upload/17/07/07/595f0ab4ea011.jpg","isvip":"0"},{"userid":"53402","company":"上海中晨电子商务股份有限公司","name":"hh","date":"7月19日 19：34","thumb":"http://pic.myplas.com/upload/17/07/07/595f0ab4ea011.jpg","isvip":"0"},{"userid":"53402","company":"上海中晨电子商务股份有限公司","name":"hh","date":"7月19日 19：34","thumb":"http://pic.myplas.com/upload/17/07/07/595f0ab4ea011.jpg","isvip":"0"},{"userid":"53402","company":"上海中晨电子商务股份有限公司","name":"hh","date":"7月19日 19：34","thumb":"http://pic.myplas.com/upload/17/07/07/595f0ab4ea011.jpg","isvip":"0"},{"userid":"53402","company":"上海中晨电子商务股份有限公司","name":"hh","date":"7月19日 19：33","thumb":"http://pic.myplas.com/upload/17/07/07/595f0ab4ea011.jpg","isvip":"0"},{"userid":"53402","company":"上海中晨电子商务股份有限公司","name":"hh","date":"7月19日 19：33","thumb":"http://pic.myplas.com/upload/17/07/07/595f0ab4ea011.jpg","isvip":"0"},{"userid":"53402","company":"上海中晨电子商务股份有限公司","name":"hh","date":"7月19日 19：33","thumb":"http://pic.myplas.com/upload/17/07/07/595f0ab4ea011.jpg","isvip":"0"},{"userid":"53402","company":"上海中晨电子商务股份有限公司","name":"hh","date":"7月19日 19：33","thumb":"http://pic.myplas.com/upload/17/07/07/595f0ab4ea011.jpg","isvip":"0"},{"userid":"53402","company":"上海中晨电子商务股份有限公司","name":"hh","date":"7月19日 19：33","thumb":"http://pic.myplas.com/upload/17/07/07/595f0ab4ea011.jpg","isvip":"0"},{"userid":"53402","company":"上海中晨电子商务股份有限公司","name":"hh","date":"7月19日 19：33","thumb":"http://pic.myplas.com/upload/17/07/07/595f0ab4ea011.jpg","isvip":"0"},{"userid":"3858","company":"上海中晨电子商务股份有限公司","name":"李一帆","date":"7月11日 11：20","thumb":"http://pic.myplas.com/upload/17/07/14/59683e9ac80d3.png","isvip":"0"},{"userid":"3858","company":"上海中晨电子商务股份有限公司","name":"李一帆","date":"7月6日 14：37","thumb":"http://pic.myplas.com/upload/17/07/14/59683e9ac80d3.png","isvip":"0"},{"userid":"3858","company":"上海中晨电子商务股份有限公司","name":"李一帆","date":"7月6日 14：36","thumb":"http://pic.myplas.com/upload/17/07/14/59683e9ac80d3.png","isvip":"0"},{"userid":"3858","company":"上海中晨电子商务股份有限公司","name":"李一帆","date":"7月5日 09：09","thumb":"http://pic.myplas.com/upload/17/07/14/59683e9ac80d3.png","isvip":"0"},{"userid":"3858","company":"上海中晨电子商务股份有限公司","name":"李一帆","date":"7月4日 17：58","thumb":"http://pic.myplas.com/upload/17/07/14/59683e9ac80d3.png","isvip":"0"}]}],"totals":"575","today":5}
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
         * history : [{"time":"2017年7月","person":[{"userid":"3858","company":"上海中晨电子商务股份有限公司","name":"李一帆","date":"7月20日 12：39","thumb":"http://pic.myplas.com/upload/17/07/14/59683e9ac80d3.png","isvip":"0"},{"userid":"3858","company":"上海中晨电子商务股份有限公司","name":"李一帆","date":"7月20日 11：54","thumb":"http://pic.myplas.com/upload/17/07/14/59683e9ac80d3.png","isvip":"0"},{"userid":"3858","company":"上海中晨电子商务股份有限公司","name":"李一帆","date":"7月20日 11：53","thumb":"http://pic.myplas.com/upload/17/07/14/59683e9ac80d3.png","isvip":"0"},{"userid":"53475","company":"香港TVB塑料塑料","name":"欧阳震华","date":"7月20日 10：37","thumb":"http://pic.myplas.com/myapp/img/male.jpg","isvip":"0"},{"userid":"53475","company":"香港TVB塑料塑料","name":"欧阳震华","date":"7月20日 10：37","thumb":"http://pic.myplas.com/myapp/img/male.jpg","isvip":"0"},{"userid":"53402","company":"上海中晨电子商务股份有限公司","name":"hh","date":"7月19日 19：34","thumb":"http://pic.myplas.com/upload/17/07/07/595f0ab4ea011.jpg","isvip":"0"},{"userid":"53402","company":"上海中晨电子商务股份有限公司","name":"hh","date":"7月19日 19：34","thumb":"http://pic.myplas.com/upload/17/07/07/595f0ab4ea011.jpg","isvip":"0"},{"userid":"53402","company":"上海中晨电子商务股份有限公司","name":"hh","date":"7月19日 19：34","thumb":"http://pic.myplas.com/upload/17/07/07/595f0ab4ea011.jpg","isvip":"0"},{"userid":"53402","company":"上海中晨电子商务股份有限公司","name":"hh","date":"7月19日 19：34","thumb":"http://pic.myplas.com/upload/17/07/07/595f0ab4ea011.jpg","isvip":"0"},{"userid":"53402","company":"上海中晨电子商务股份有限公司","name":"hh","date":"7月19日 19：33","thumb":"http://pic.myplas.com/upload/17/07/07/595f0ab4ea011.jpg","isvip":"0"},{"userid":"53402","company":"上海中晨电子商务股份有限公司","name":"hh","date":"7月19日 19：33","thumb":"http://pic.myplas.com/upload/17/07/07/595f0ab4ea011.jpg","isvip":"0"},{"userid":"53402","company":"上海中晨电子商务股份有限公司","name":"hh","date":"7月19日 19：33","thumb":"http://pic.myplas.com/upload/17/07/07/595f0ab4ea011.jpg","isvip":"0"},{"userid":"53402","company":"上海中晨电子商务股份有限公司","name":"hh","date":"7月19日 19：33","thumb":"http://pic.myplas.com/upload/17/07/07/595f0ab4ea011.jpg","isvip":"0"},{"userid":"53402","company":"上海中晨电子商务股份有限公司","name":"hh","date":"7月19日 19：33","thumb":"http://pic.myplas.com/upload/17/07/07/595f0ab4ea011.jpg","isvip":"0"},{"userid":"53402","company":"上海中晨电子商务股份有限公司","name":"hh","date":"7月19日 19：33","thumb":"http://pic.myplas.com/upload/17/07/07/595f0ab4ea011.jpg","isvip":"0"},{"userid":"3858","company":"上海中晨电子商务股份有限公司","name":"李一帆","date":"7月11日 11：20","thumb":"http://pic.myplas.com/upload/17/07/14/59683e9ac80d3.png","isvip":"0"},{"userid":"3858","company":"上海中晨电子商务股份有限公司","name":"李一帆","date":"7月6日 14：37","thumb":"http://pic.myplas.com/upload/17/07/14/59683e9ac80d3.png","isvip":"0"},{"userid":"3858","company":"上海中晨电子商务股份有限公司","name":"李一帆","date":"7月6日 14：36","thumb":"http://pic.myplas.com/upload/17/07/14/59683e9ac80d3.png","isvip":"0"},{"userid":"3858","company":"上海中晨电子商务股份有限公司","name":"李一帆","date":"7月5日 09：09","thumb":"http://pic.myplas.com/upload/17/07/14/59683e9ac80d3.png","isvip":"0"},{"userid":"3858","company":"上海中晨电子商务股份有限公司","name":"李一帆","date":"7月4日 17：58","thumb":"http://pic.myplas.com/upload/17/07/14/59683e9ac80d3.png","isvip":"0"}]}]
         * totals : 575
         * today : 5
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
             * time : 2017年7月
             * person : [{"userid":"3858","company":"上海中晨电子商务股份有限公司","name":"李一帆","date":"7月20日 12：39","thumb":"http://pic.myplas.com/upload/17/07/14/59683e9ac80d3.png","isvip":"0"},{"userid":"3858","company":"上海中晨电子商务股份有限公司","name":"李一帆","date":"7月20日 11：54","thumb":"http://pic.myplas.com/upload/17/07/14/59683e9ac80d3.png","isvip":"0"},{"userid":"3858","company":"上海中晨电子商务股份有限公司","name":"李一帆","date":"7月20日 11：53","thumb":"http://pic.myplas.com/upload/17/07/14/59683e9ac80d3.png","isvip":"0"},{"userid":"53475","company":"香港TVB塑料塑料","name":"欧阳震华","date":"7月20日 10：37","thumb":"http://pic.myplas.com/myapp/img/male.jpg","isvip":"0"},{"userid":"53475","company":"香港TVB塑料塑料","name":"欧阳震华","date":"7月20日 10：37","thumb":"http://pic.myplas.com/myapp/img/male.jpg","isvip":"0"},{"userid":"53402","company":"上海中晨电子商务股份有限公司","name":"hh","date":"7月19日 19：34","thumb":"http://pic.myplas.com/upload/17/07/07/595f0ab4ea011.jpg","isvip":"0"},{"userid":"53402","company":"上海中晨电子商务股份有限公司","name":"hh","date":"7月19日 19：34","thumb":"http://pic.myplas.com/upload/17/07/07/595f0ab4ea011.jpg","isvip":"0"},{"userid":"53402","company":"上海中晨电子商务股份有限公司","name":"hh","date":"7月19日 19：34","thumb":"http://pic.myplas.com/upload/17/07/07/595f0ab4ea011.jpg","isvip":"0"},{"userid":"53402","company":"上海中晨电子商务股份有限公司","name":"hh","date":"7月19日 19：34","thumb":"http://pic.myplas.com/upload/17/07/07/595f0ab4ea011.jpg","isvip":"0"},{"userid":"53402","company":"上海中晨电子商务股份有限公司","name":"hh","date":"7月19日 19：33","thumb":"http://pic.myplas.com/upload/17/07/07/595f0ab4ea011.jpg","isvip":"0"},{"userid":"53402","company":"上海中晨电子商务股份有限公司","name":"hh","date":"7月19日 19：33","thumb":"http://pic.myplas.com/upload/17/07/07/595f0ab4ea011.jpg","isvip":"0"},{"userid":"53402","company":"上海中晨电子商务股份有限公司","name":"hh","date":"7月19日 19：33","thumb":"http://pic.myplas.com/upload/17/07/07/595f0ab4ea011.jpg","isvip":"0"},{"userid":"53402","company":"上海中晨电子商务股份有限公司","name":"hh","date":"7月19日 19：33","thumb":"http://pic.myplas.com/upload/17/07/07/595f0ab4ea011.jpg","isvip":"0"},{"userid":"53402","company":"上海中晨电子商务股份有限公司","name":"hh","date":"7月19日 19：33","thumb":"http://pic.myplas.com/upload/17/07/07/595f0ab4ea011.jpg","isvip":"0"},{"userid":"53402","company":"上海中晨电子商务股份有限公司","name":"hh","date":"7月19日 19：33","thumb":"http://pic.myplas.com/upload/17/07/07/595f0ab4ea011.jpg","isvip":"0"},{"userid":"3858","company":"上海中晨电子商务股份有限公司","name":"李一帆","date":"7月11日 11：20","thumb":"http://pic.myplas.com/upload/17/07/14/59683e9ac80d3.png","isvip":"0"},{"userid":"3858","company":"上海中晨电子商务股份有限公司","name":"李一帆","date":"7月6日 14：37","thumb":"http://pic.myplas.com/upload/17/07/14/59683e9ac80d3.png","isvip":"0"},{"userid":"3858","company":"上海中晨电子商务股份有限公司","name":"李一帆","date":"7月6日 14：36","thumb":"http://pic.myplas.com/upload/17/07/14/59683e9ac80d3.png","isvip":"0"},{"userid":"3858","company":"上海中晨电子商务股份有限公司","name":"李一帆","date":"7月5日 09：09","thumb":"http://pic.myplas.com/upload/17/07/14/59683e9ac80d3.png","isvip":"0"},{"userid":"3858","company":"上海中晨电子商务股份有限公司","name":"李一帆","date":"7月4日 17：58","thumb":"http://pic.myplas.com/upload/17/07/14/59683e9ac80d3.png","isvip":"0"}]
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
                 * userid : 3858
                 * company : 上海中晨电子商务股份有限公司
                 * name : 李一帆
                 * date : 7月20日 12：39
                 * thumb : http://pic.myplas.com/upload/17/07/14/59683e9ac80d3.png
                 * isvip : 0
                 */

                private String userid;
                private String company;
                private String name;
                private String date;
                private String thumb;
                private String isvip;

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
            }
        }
    }
}
