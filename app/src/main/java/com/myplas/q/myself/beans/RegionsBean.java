package com.myplas.q.myself.beans;

import com.bigkoo.pickerview.model.IPickerViewData;

import java.util.List;

/**
 * 作者：  黄双
 * 事件 2017/8/23 0023.
 * 邮箱： 15378412400@163.com
 */

public class RegionsBean {

    /**
     * err : 0
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

    public static class DataBean implements IPickerViewData {
        /**
         * label : 北京
         * value : 2
         */

        private String label;
        private String value;
        private List<ChildrenBeanX> children;

        public String getLabel() {
            return label;
        }

        public void setLabel(String label) {
            this.label = label;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }

        public List<ChildrenBeanX> getChildren() {
            return children;
        }

        public void setChildren(List<ChildrenBeanX> children) {
            this.children = children;
        }

        @Override
        public String getPickerViewText() {
            return label;
        }

        public static class ChildrenBeanX implements IPickerViewData {
            /**
             * label : 北京
             * value : 52
             */

            private String label;
            private String value;
            private List<ChildrenBean> children;

            public String getLabel() {
                return label;
            }

            public void setLabel(String label) {
                this.label = label;
            }

            public String getValue() {
                return value;
            }

            public void setValue(String value) {
                this.value = value;
            }

            public List<ChildrenBean> getChildren() {
                return children;
            }

            public void setChildren(List<ChildrenBean> children) {
                this.children = children;
            }

            @Override
            public String getPickerViewText() {
                return label;
            }

            public static class ChildrenBean implements IPickerViewData {
                /**
                 * label : 东城区
                 * value : 500
                 */

                private String label;
                private String value;

                public String getLabel() {
                    return label;
                }

                public void setLabel(String label) {
                    this.label = label;
                }

                public String getValue() {
                    return value;
                }

                public void setValue(String value) {
                    this.value = value;
                }

                @Override
                public String getPickerViewText() {
                    return label;
                }
            }
        }
    }
}
