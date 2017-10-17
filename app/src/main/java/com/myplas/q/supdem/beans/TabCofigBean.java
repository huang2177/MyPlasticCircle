package com.myplas.q.supdem.beans;

import java.util.List;

/**
 * 编写：黄双
 * 邮箱：15378412400@163.com
 * 时间： 2017/6/291724.
 */

public class TabCofigBean {

    private int err;
    private DataBeanXXX data;

    public int getErr() {
        return err;
    }

    public void setErr(int err) {
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
            /**
             * currentItem : 0
             * currentValue : 0
             * data : [{"show":"全中国","data":[{"show":"全中国","value":"0"}]},{"show":"北京","data":[{"show":"全北京","value":"2"},{"show":"北京","value":"52"}]},{"show":"安徽","data":[{"show":"全安徽","value":"3"},{"show":"安庆","value":"36"},{"show":"蚌埠","value":"37"},{"show":"巢湖","value":"38"},{"show":"池州","value":"39"},{"show":"滁州","value":"40"},{"show":"阜阳","value":"41"},{"show":"淮北","value":"42"},{"show":"淮南","value":"43"},{"show":"黄山","value":"44"},{"show":"六安","value":"45"},{"show":"马鞍山","value":"46"},{"show":"宿州","value":"47"},{"show":"铜陵","value":"48"},{"show":"芜湖","value":"49"},{"show":"宣城","value":"50"},{"show":"亳州","value":"51"},{"show":"合肥","value":"3401"}]},{"show":"福建","data":[{"show":"全福建","value":"4"},{"show":"福州","value":"53"},{"show":"龙岩","value":"54"},{"show":"南平","value":"55"},{"show":"宁德","value":"56"},{"show":"莆田","value":"57"},{"show":"泉州","value":"58"},{"show":"三明","value":"59"},{"show":"厦门","value":"60"},{"show":"漳州","value":"61"}]},{"show":"甘肃","data":[{"show":"全甘肃","value":"5"},{"show":"兰州","value":"62"},{"show":"白银","value":"63"},{"show":"定西","value":"64"},{"show":"甘南","value":"65"},{"show":"嘉峪关","value":"66"},{"show":"金昌","value":"67"},{"show":"酒泉","value":"68"},{"show":"临夏","value":"69"},{"show":"陇南","value":"70"},{"show":"平凉","value":"71"},{"show":"庆阳","value":"72"},{"show":"天水","value":"73"},{"show":"武威","value":"74"},{"show":"张掖","value":"75"}]},{"show":"广东","data":[{"show":"全广东","value":"6"},{"show":"广州","value":"76"},{"show":"深圳","value":"77"},{"show":"潮州","value":"78"},{"show":"东莞","value":"79"},{"show":"佛山","value":"80"},{"show":"河源","value":"81"},{"show":"惠州","value":"82"},{"show":"江门","value":"83"},{"show":"揭阳","value":"84"},{"show":"茂名","value":"85"},{"show":"梅州","value":"86"},{"show":"清远","value":"87"},{"show":"汕头","value":"88"},{"show":"汕尾","value":"89"},{"show":"韶关","value":"90"},{"show":"阳江","value":"91"},{"show":"云浮","value":"92"},{"show":"湛江","value":"93"},{"show":"肇庆","value":"94"},{"show":"中山","value":"95"},{"show":"珠海","value":"96"}]},{"show":"广西","data":[{"show":"全广西","value":"7"},{"show":"南宁","value":"97"},{"show":"桂林","value":"98"},{"show":"百色","value":"99"},{"show":"北海","value":"100"},{"show":"崇左","value":"101"},{"show":"防城港","value":"102"},{"show":"贵港","value":"103"},{"show":"河池","value":"104"},{"show":"贺州","value":"105"},{"show":"来宾","value":"106"},{"show":"柳州","value":"107"},{"show":"钦州","value":"108"},{"show":"梧州","value":"109"},{"show":"玉林","value":"110"}]},{"show":"贵州","data":[{"show":"全贵州","value":"8"},{"show":"贵阳","value":"111"},{"show":"安顺","value":"112"},{"show":"毕节","value":"113"},{"show":"六盘水","value":"114"},{"show":"黔东南","value":"115"},{"show":"黔南","value":"116"},{"show":"黔西南","value":"117"},{"show":"铜仁","value":"118"},{"show":"遵义","value":"119"}]},{"show":"海南","data":[{"show":"全海南","value":"9"},{"show":"海口","value":"120"},{"show":"三亚","value":"121"},{"show":"白沙","value":"122"},{"show":"保亭","value":"123"},{"show":"昌江","value":"124"},{"show":"澄迈县","value":"125"},{"show":"定安县","value":"126"},{"show":"东方","value":"127"},{"show":"乐东","value":"128"},{"show":"临高县","value":"129"},{"show":"陵水","value":"130"},{"show":"琼海","value":"131"},{"show":"琼中","value":"132"},{"show":"屯昌县","value":"133"},{"show":"万宁","value":"134"},{"show":"文昌","value":"135"},{"show":"五指山","value":"136"},{"show":"儋州","value":"137"}]},{"show":"河北","data":[{"show":"全河北","value":"10"},{"show":"石家庄","value":"138"},{"show":"保定","value":"139"},{"show":"沧州","value":"140"},{"show":"承德","value":"141"},{"show":"邯郸","value":"142"},{"show":"衡水","value":"143"},{"show":"廊坊","value":"144"},{"show":"秦皇岛","value":"145"},{"show":"唐山","value":"146"},{"show":"邢台","value":"147"},{"show":"张家口","value":"148"}]},{"show":"河南","data":[{"show":"全河南","value":"11"},{"show":"郑州","value":"149"},{"show":"洛阳","value":"150"},{"show":"开封","value":"151"},{"show":"安阳","value":"152"},{"show":"鹤壁","value":"153"},{"show":"焦作","value":"155"},{"show":"南阳","value":"156"},{"show":"平顶山","value":"157"},{"show":"三门峡","value":"158"},{"show":"商丘","value":"159"},{"show":"新乡","value":"160"},{"show":"信阳","value":"161"},{"show":"许昌","value":"162"},{"show":"周口","value":"163"},{"show":"驻马店","value":"164"},{"show":"漯河","value":"165"},{"show":"濮阳","value":"166"}]},{"show":"黑龙江","data":[{"show":"全黑龙江","value":"12"},{"show":"哈尔滨","value":"167"},{"show":"大庆","value":"168"},{"show":"大兴安岭","value":"169"},{"show":"鹤岗","value":"170"},{"show":"黑河","value":"171"},{"show":"鸡西","value":"172"},{"show":"佳木斯","value":"173"},{"show":"牡丹江","value":"174"},{"show":"七台河","value":"175"},{"show":"齐齐哈尔","value":"176"},{"show":"双鸭山","value":"177"},{"show":"绥化","value":"178"},{"show":"伊春","value":"179"}]},{"show":"湖北","data":[{"show":"全湖北","value":"13"},{"show":"武汉","value":"180"},{"show":"仙桃","value":"181"},{"show":"鄂州","value":"182"},{"show":"黄冈","value":"183"},{"show":"黄石","value":"184"},{"show":"荆门","value":"185"},{"show":"荆州","value":"186"},{"show":"潜江","value":"187"},{"show":"神农架林区","value":"188"},{"show":"十堰","value":"189"},{"show":"随州","value":"190"},{"show":"天门","value":"191"},{"show":"咸宁","value":"192"},{"show":"襄樊","value":"193"},{"show":"孝感","value":"194"},{"show":"宜昌","value":"195"},{"show":"恩施","value":"196"}]},{"show":"湖南","data":[{"show":"全湖南","value":"14"},{"show":"长沙","value":"197"},{"show":"张家界","value":"198"},{"show":"常德","value":"199"},{"show":"郴州","value":"200"},{"show":"衡阳","value":"201"},{"show":"怀化","value":"202"},{"show":"娄底","value":"203"},{"show":"邵阳","value":"204"},{"show":"湘潭","value":"205"},{"show":"湘西","value":"206"},{"show":"益阳","value":"207"},{"show":"永州","value":"208"},{"show":"岳阳","value":"209"},{"show":"株洲","value":"210"}]},{"show":"吉林","data":[{"show":"全吉林","value":"15"},{"show":"长春","value":"211"},{"show":"吉林","value":"212"},{"show":"白城","value":"213"},{"show":"白山","value":"214"},{"show":"辽源","value":"215"},{"show":"四平","value":"216"},{"show":"松原","value":"217"},{"show":"通化","value":"218"},{"show":"延边","value":"219"}]},{"show":"江苏","data":[{"show":"全江苏","value":"16"},{"show":"南京","value":"220"},{"show":"苏州","value":"221"},{"show":"无锡","value":"222"},{"show":"常州","value":"223"},{"show":"淮安","value":"224"},{"show":"连云港","value":"225"},{"show":"南通","value":"226"},{"show":"宿迁","value":"227"},{"show":"泰州","value":"228"},{"show":"徐州","value":"229"},{"show":"盐城","value":"230"},{"show":"扬州","value":"231"},{"show":"镇江","value":"232"}]},{"show":"江西","data":[{"show":"全江西","value":"17"},{"show":"南昌","value":"233"},{"show":"抚州","value":"234"},{"show":"赣州","value":"235"},{"show":"吉安","value":"236"},{"show":"景德镇","value":"237"},{"show":"九江","value":"238"},{"show":"萍乡","value":"239"},{"show":"上饶","value":"240"},{"show":"新余","value":"241"},{"show":"宜春","value":"242"},{"show":"鹰潭","value":"243"}]},{"show":"辽宁","data":[{"show":"全辽宁","value":"18"},{"show":"沈阳","value":"244"},{"show":"大连","value":"245"},{"show":"鞍山","value":"246"},{"show":"本溪","value":"247"},{"show":"朝阳","value":"248"},{"show":"丹东","value":"249"},{"show":"抚顺","value":"250"},{"show":"阜新","value":"251"},{"show":"葫芦岛","value":"252"},{"show":"锦州","value":"253"},{"show":"辽阳","value":"254"},{"show":"盘锦","value":"255"},{"show":"铁岭","value":"256"},{"show":"营口","value":"257"}]},{"show":"内蒙古","data":[{"show":"全内蒙古","value":"19"},{"show":"呼和浩特","value":"258"},{"show":"阿拉善盟","value":"259"},{"show":"巴彦淖尔盟","value":"260"},{"show":"包头","value":"261"},{"show":"赤峰","value":"262"},{"show":"鄂尔多斯","value":"263"},{"show":"呼伦贝尔","value":"264"},{"show":"通辽","value":"265"},{"show":"乌海","value":"266"},{"show":"乌兰察布市","value":"267"},{"show":"锡林郭勒盟","value":"268"},{"show":"兴安盟","value":"269"}]},{"show":"宁夏","data":[{"show":"全宁夏","value":"20"},{"show":"银川","value":"270"},{"show":"固原","value":"271"},{"show":"石嘴山","value":"272"},{"show":"吴忠","value":"273"},{"show":"中卫","value":"274"}]},{"show":"青海","data":[{"show":"全青海","value":"21"},{"show":"西宁","value":"275"},{"show":"果洛","value":"276"},{"show":"海北","value":"277"},{"show":"海东","value":"278"},{"show":"海南","value":"279"},{"show":"海西","value":"280"},{"show":"黄南","value":"281"},{"show":"玉树","value":"282"}]},{"show":"山东","data":[{"show":"全山东","value":"22"},{"show":"济南","value":"283"},{"show":"青岛","value":"284"},{"show":"滨州","value":"285"},{"show":"德州","value":"286"},{"show":"东营","value":"287"},{"show":"菏泽","value":"288"},{"show":"济宁","value":"289"},{"show":"莱芜","value":"290"},{"show":"聊城","value":"291"},{"show":"临沂","value":"292"},{"show":"日照","value":"293"},{"show":"泰安","value":"294"},{"show":"威海","value":"295"},{"show":"潍坊","value":"296"},{"show":"烟台","value":"297"},{"show":"枣庄","value":"298"},{"show":"淄博","value":"299"}]},{"show":"山西","data":[{"show":"全山西","value":"23"},{"show":"太原","value":"300"},{"show":"长治","value":"301"},{"show":"大同","value":"302"},{"show":"晋城","value":"303"},{"show":"晋中","value":"304"},{"show":"临汾","value":"305"},{"show":"吕梁","value":"306"},{"show":"朔州","value":"307"},{"show":"忻州","value":"308"},{"show":"阳泉","value":"309"},{"show":"运城","value":"310"}]},{"show":"陕西","data":[{"show":"全陕西","value":"24"},{"show":"西安","value":"311"},{"show":"安康","value":"312"},{"show":"宝鸡","value":"313"},{"show":"汉中","value":"314"},{"show":"商洛","value":"315"},{"show":"铜川","value":"316"},{"show":"渭南","value":"317"},{"show":"咸阳","value":"318"},{"show":"延安","value":"319"},{"show":"榆林","value":"320"}]},{"show":"上海","data":[{"show":"全上海","value":"25"},{"show":"上海","value":"321"}]},{"show":"四川","data":[{"show":"全四川","value":"26"},{"show":"成都","value":"322"},{"show":"绵阳","value":"323"},{"show":"阿坝","value":"324"},{"show":"巴中","value":"325"},{"show":"达州","value":"326"},{"show":"德阳","value":"327"},{"show":"甘孜","value":"328"},{"show":"广安","value":"329"},{"show":"广元","value":"330"},{"show":"乐山","value":"331"},{"show":"凉山","value":"332"},{"show":"眉山","value":"333"},{"show":"南充","value":"334"},{"show":"内江","value":"335"},{"show":"攀枝花","value":"336"},{"show":"遂宁","value":"337"},{"show":"雅安","value":"338"},{"show":"宜宾","value":"339"},{"show":"资阳","value":"340"},{"show":"自贡","value":"341"},{"show":"泸州","value":"342"}]},{"show":"天津","data":[{"show":"全天津","value":"27"},{"show":"天津","value":"343"}]},{"show":"西藏","data":[{"show":"全西藏","value":"28"},{"show":"拉萨","value":"344"},{"show":"阿里","value":"345"},{"show":"昌都","value":"346"},{"show":"林芝","value":"347"},{"show":"那曲","value":"348"},{"show":"日喀则","value":"349"},{"show":"山南","value":"350"}]},{"show":"新疆","data":[{"show":"全新疆","value":"29"},{"show":"乌鲁木齐","value":"351"},{"show":"阿克苏","value":"352"},{"show":"巴音郭楞","value":"354"},{"show":"博尔塔拉","value":"355"},{"show":"昌吉","value":"356"},{"show":"哈密","value":"357"},{"show":"和田","value":"358"},{"show":"喀什","value":"359"},{"show":"克拉玛依","value":"360"},{"show":"克孜勒苏","value":"361"},{"show":"吐鲁番","value":"364"},{"show":"省直辖","value":"365"},{"show":"伊犁","value":"366"},{"show":"塔城地区","value":"3414"},{"show":"阿勒泰地区","value":"3422"}]},{"show":"云南","data":[{"show":"全云南","value":"30"},{"show":"昆明","value":"367"},{"show":"怒江","value":"368"},{"show":"思茅","value":"369"},{"show":"丽江","value":"370"},{"show":"保山","value":"371"},{"show":"楚雄","value":"372"},{"show":"大理","value":"373"},{"show":"德宏","value":"374"},{"show":"迪庆","value":"375"},{"show":"红河","value":"376"},{"show":"临沧","value":"377"},{"show":"曲靖","value":"378"},{"show":"文山","value":"379"},{"show":"西双版纳","value":"380"},{"show":"玉溪","value":"381"},{"show":"昭通","value":"382"}]},{"show":"浙江","data":[{"show":"全浙江","value":"31"},{"show":"杭州","value":"383"},{"show":"湖州","value":"384"},{"show":"嘉兴","value":"385"},{"show":"金华","value":"386"},{"show":"丽水","value":"387"},{"show":"宁波","value":"388"},{"show":"绍兴","value":"389"},{"show":"台州","value":"390"},{"show":"温州","value":"391"},{"show":"舟山","value":"392"},{"show":"衢州","value":"393"}]},{"show":"重庆","data":[{"show":"全重庆","value":"32"},{"show":"重庆","value":"394"}]},{"show":"香港","data":[{"show":"全香港","value":"33"},{"show":"香港","value":"395"}]},{"show":"澳门","data":[{"show":"全澳门","value":"34"},{"show":"澳门","value":"396"}]},{"show":"台湾","data":[{"show":"全台湾","value":"35"},{"show":"台湾","value":"397"}]}]
             */

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
                 * show : 全中国
                 * data : [{"show":"全中国","value":"0"}]
                 */

                private String show;
                private List<DataBeanX> data;

                public String getShow() {
                    return show;
                }

                public void setShow(String show) {
                    this.show = show;
                }

                public List<DataBeanX> getData() {
                    return data;
                }

                public void setData(List<DataBeanX> data) {
                    this.data = data;
                }

                public static class DataBeanX {
                    /**
                     * show : 全中国
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
        }
    }
}
