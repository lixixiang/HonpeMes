package com.example.honpemes.fragment.a.menu.fragment.position11.item1.bean;

import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Lixixiang on 2023/2/4 11:01
 */
public class OrderFormBean implements Serializable, MultiItemEntity {
    public static final int TYPE_1 = 1;
    public static final int TYPE_2 = 2;
    public static final int TYPE_3 = 3;

    private int spanSize;
    private int itemType;
    @SerializedName("Total")
    private int total;
    @SerializedName("Data")
    private DataBean data;
    @SerializedName("Tag")
    private int tag;
    @SerializedName("Message")
    private String message;
    @SerializedName("Description")
    private Object description;

    public int getSpanSize() {
        return spanSize;
    }

    public void setSpanSize(int spanSize) {
        this.spanSize = spanSize;
    }

    public void setItemType(int itemType) {
        this.itemType = itemType;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public int getTag() {
        return tag;
    }

    public void setTag(int tag) {
        this.tag = tag;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getDescription() {
        return description;
    }

    public void setDescription(Object description) {
        this.description = description;
    }

    @Override
    public int getItemType() {
        return itemType;
    }

    public static class DataBean implements Serializable{
        private List<加工机台状态Bean> 加工机台状态;
        private List<订单统计分析Bean> 订单统计分析;
        private List<近一年业务接单数Bean> 近一年业务接单数;
        private List<销售年月接单数Bean> 销售年月接单数;

        public List<加工机台状态Bean> get加工机台状态() {
            return 加工机台状态;
        }

        public void set加工机台状态(List<加工机台状态Bean> 加工机台状态) {
            this.加工机台状态 = 加工机台状态;
        }

        public List<订单统计分析Bean> get订单统计分析() {
            return 订单统计分析;
        }

        public void set订单统计分析(List<订单统计分析Bean> 订单统计分析) {
            this.订单统计分析 = 订单统计分析;
        }

        public List<近一年业务接单数Bean> get近一年业务接单数() {
            return 近一年业务接单数;
        }

        public void set近一年业务接单数(List<近一年业务接单数Bean> 近一年业务接单数) {
            this.近一年业务接单数 = 近一年业务接单数;
        }

        public List<销售年月接单数Bean> get销售年月接单数() {
            return 销售年月接单数;
        }

        public void set销售年月接单数(List<销售年月接单数Bean> 销售年月接单数) {
            this.销售年月接单数 = 销售年月接单数;
        }

        public static class 加工机台状态Bean implements Serializable{
            private String 制作组别;
            private String 机台类型;
            private String 机台状态;
            private int cn;

            public String get制作组别() {
                return 制作组别;
            }

            public void set制作组别(String 制作组别) {
                this.制作组别 = 制作组别;
            }

            public String get机台类型() {
                return 机台类型;
            }

            public void set机台类型(String 机台类型) {
                this.机台类型 = 机台类型;
            }

            public String get机台状态() {
                return 机台状态;
            }

            public void set机台状态(String 机台状态) {
                this.机台状态 = 机台状态;
            }

            public int getCn() {
                return cn;
            }

            public void setCn(int cn) {
                this.cn = cn;
            }
        }

        public static class 订单统计分析Bean implements Serializable,MultiItemEntity{

            public static final int TYPE_1 = 1;
            public static final int TYPE_2 = 2;
            private int spanSize;
            private int itemType;
            private String 组别;
            private int 订单数量;
            private int 完成数量;
            private int 延期数量;
            private int 报废数量;

            public int getSpanSize() {
                return spanSize;
            }

            public void setSpanSize(int spanSize) {
                this.spanSize = spanSize;
            }

            public void setItemType(int itemType) {
                this.itemType = itemType;
            }

            public String get组别() {
                return 组别;
            }

            public void set组别(String 组别) {
                this.组别 = 组别;
            }

            public int get订单数量() {
                return 订单数量;
            }

            public void set订单数量(int 订单数量) {
                this.订单数量 = 订单数量;
            }

            public int get完成数量() {
                return 完成数量;
            }

            public void set完成数量(int 完成数量) {
                this.完成数量 = 完成数量;
            }

            public int get延期数量() {
                return 延期数量;
            }

            public void set延期数量(int 延期数量) {
                this.延期数量 = 延期数量;
            }

            public int get报废数量() {
                return 报废数量;
            }

            public void set报废数量(int 报废数量) {
                this.报废数量 = 报废数量;
            }

            @Override
            public int getItemType() {
                return itemType;
            }
        }

        public static class 近一年业务接单数Bean implements Serializable{
            private String 组别名称;
            private String 组别代码;
            private int 订单数量;
            private int 完成数量;
            private int 延期数量;
            private int 报废数量;

            public String get组别名称() {
                return 组别名称;
            }

            public void set组别名称(String 组别名称) {
                this.组别名称 = 组别名称;
            }

            public String get组别代码() {
                return 组别代码;
            }

            public void set组别代码(String 组别代码) {
                this.组别代码 = 组别代码;
            }

            public int get订单数量() {
                return 订单数量;
            }

            public void set订单数量(int 订单数量) {
                this.订单数量 = 订单数量;
            }

            public int get完成数量() {
                return 完成数量;
            }

            public void set完成数量(int 完成数量) {
                this.完成数量 = 完成数量;
            }

            public int get延期数量() {
                return 延期数量;
            }

            public void set延期数量(int 延期数量) {
                this.延期数量 = 延期数量;
            }

            public int get报废数量() {
                return 报废数量;
            }

            public void set报废数量(int 报废数量) {
                this.报废数量 = 报废数量;
            }
        }

        public static class 销售年月接单数Bean implements Serializable{
            private String 销售年月;
            private int 订单数量;
            private int 完成数量;
            private int 延期数量;
            private int 报废数量;

            public String get销售年月() {
                return 销售年月;
            }

            public void set销售年月(String 销售年月) {
                this.销售年月 = 销售年月;
            }

            public int get订单数量() {
                return 订单数量;
            }

            public void set订单数量(int 订单数量) {
                this.订单数量 = 订单数量;
            }

            public int get完成数量() {
                return 完成数量;
            }

            public void set完成数量(int 完成数量) {
                this.完成数量 = 完成数量;
            }

            public int get延期数量() {
                return 延期数量;
            }

            public void set延期数量(int 延期数量) {
                this.延期数量 = 延期数量;
            }

            public int get报废数量() {
                return 报废数量;
            }

            public void set报废数量(int 报废数量) {
                this.报废数量 = 报废数量;
            }
        }
    }
}
