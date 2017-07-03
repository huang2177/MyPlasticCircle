package com.myplas.q.supdem.Beans;

import java.util.List;

/**
 * 编写：黄双
 * 邮箱：15378412400@163.com
 * 时间： 2017/6/291724.
 */

public class TabCofigBean {


    private String err;
    private DataBeanXXX data;

    public String getErr() {
        return err;
    }

    public void setErr(String err) {
        this.err = err;
    }

    public DataBeanXXX getData() {
        return data;
    }

    public void setData(DataBeanXXX data) {
        this.data = data;
    }

    public static class DataBeanXXX {


        private TimeBean time;
        private AreaBean area;

        public TimeBean getTime() {
            return time;
        }

        public void setTime(TimeBean time) {
            this.time = time;
        }

        public AreaBean getArea() {
            return area;
        }

        public void setArea(AreaBean area) {
            this.area = area;
        }

        public static class TimeBean {
            /**
             * currentItem : 1
             * currentValue : 3
             * data : [{"show":"全部信息","value":"0"},{"show":"最近3天","value":"3"},{"show":"最近7天","value":"7"},{"show":"最近15天","value":"15"}]
             */

            private String currentItem;
            private String currentValue;
            private List<DataBean> data;

            public String getCurrentItem() {
                return currentItem;
            }

            public void setCurrentItem(String currentItem) {
                this.currentItem = currentItem;
            }

            public String getCurrentValue() {
                return currentValue;
            }

            public void setCurrentValue(String currentValue) {
                this.currentValue = currentValue;
            }

            public List<DataBean> getData() {
                return data;
            }

            public void setData(List<DataBean> data) {
                this.data = data;
            }

            public static class DataBean {
                /**
                 * show : 全部信息
                 * value : 0
                 */

                private String show;
                private String value;

                public String getShow() {
                    return show;
                }

                public void setShow(String show) {
                    this.show = show;
                }

                public String getValue() {
                    return value;
                }

                public void setValue(String value) {
                    this.value = value;
                }
            }
        }

        public static class AreaBean {

            private String currentItem;
            private String currentValue;
            private List<DataBeanXX> data;

            public String getCurrentItem() {
                return currentItem;
            }

            public void setCurrentItem(String currentItem) {
                this.currentItem = currentItem;
            }

            public String getCurrentValue() {
                return currentValue;
            }

            public void setCurrentValue(String currentValue) {
                this.currentValue = currentValue;
            }

            public List<DataBeanXX> getData() {
                return data;
            }

            public void setData(List<DataBeanXX> data) {
                this.data = data;
            }

            public static class DataBeanXX {
                /**
                 * show : 北京
                 * data : [{"show":"全北京","value":"2"},{"show":"北京","value":"52"}]
                 * currentItem : 0
                 * currentValue : 25
                 */

                private String show;
                private String currentItem;
                private String currentValue;
                private List<DataBeanX> data;

                public String getShow() {
                    return show;
                }

                public void setShow(String show) {
                    this.show = show;
                }

                public String getCurrentItem() {
                    return currentItem;
                }

                public void setCurrentItem(String currentItem) {
                    this.currentItem = currentItem;
                }

                public String getCurrentValue() {
                    return currentValue;
                }

                public void setCurrentValue(String currentValue) {
                    this.currentValue = currentValue;
                }

                public List<DataBeanX> getData() {
                    return data;
                }

                public void setData(List<DataBeanX> data) {
                    this.data = data;
                }

                public static class DataBeanX {
                    /**
                     * show : 全北京
                     * value : 2
                     */

                    private String show;
                    private String value;

                    public String getShow() {
                        return show;
                    }

                    public void setShow(String show) {
                        this.show = show;
                    }

                    public String getValue() {
                        return value;
                    }

                    public void setValue(String value) {
                        this.value = value;
                    }
                }
            }
        }
    }
}
