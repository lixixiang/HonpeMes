package com.example.honpemes.fragment.a.menu.fragment.position2.item3.bean;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * 作者：asus on 2023/12/7 08:42
 * 注释：
 */
public class ProduceProgressBean {

    /**
     * total
     */
    @SerializedName("Total")
    public Integer total;
    /**
     * data
     */
    @SerializedName("Data")
    public List<DataBean> data;
    /**
     * tag
     */
    @SerializedName("Tag")
    public Integer tag;
    /**
     * message
     */
    @SerializedName("Message")
    public String message;
    /**
     * description
     */
    @SerializedName("Description")
    public String description;

    public static class DataBean {

        public boolean Expanded;
        /**
         * 生产单号
         */
        @SerializedName("生产单号")
        public String 生产单号;
        /**
         * 订单状态
         */
        @SerializedName("订单状态")
        public String 订单状态;
        /**
         * 下单人员
         */
        @SerializedName("下单人员")
        public String 下单人员;
        /**
         * 订单组别
         */
        @SerializedName("订单组别")
        public String 订单组别;
        /**
         * 制作组别
         */
        @SerializedName("制作组别")
        public String 制作组别;
        /**
         * 下单日期
         */
        @SerializedName("下单日期")
        public String 下单日期;
        /**
         * 交货日期
         */
        @SerializedName("交货日期")
        public String 交货日期;
        /**
         * 项目跟单
         */
        @SerializedName("项目跟单")
        public String 项目跟单;
        /**
         * 工序序号
         */
        @SerializedName("工序序号")
        public Integer 工序序号;
        /**
         * 工序名称
         */
        @SerializedName("工序名称")
        public String 工序名称;
        /**
         * 计划完成日期
         */
        @SerializedName("计划完成日期")
        public String 计划完成日期;
        /**
         * 调整后计划日期
         */
        @SerializedName("调整后计划日期")
        public String 调整后计划日期;
        /**
         * 工序完成
         */
        @SerializedName("工序完成")
        public Boolean 工序完成;
        /**
         * 实际完成日期
         */
        @SerializedName("实际完成日期")
        public String 实际完成日期;
        /**
         * 延误天数
         */
        @SerializedName("延误天数")
        public String 延误天数;
        /**
         * 延误说明
         */
        @SerializedName("延误说明")
        public String 延误说明;
        /**
         * 完成备注
         */
        @SerializedName("完成备注")
        public String 完成备注;
        /**
         * 完成登记人
         */
        @SerializedName("完成登记人")
        public String 完成登记人;
        /**
         * 完成登记日期
         */
        @SerializedName("完成登记日期")
        public String 完成登记日期;
        /**
         * 调整人
         */
        @SerializedName("调整人")
        public String 调整人;
        /**
         * 调整日期
         */
        @SerializedName("调整日期")
        public String 调整日期;


        /**
         * 客户代码
         */
        @SerializedName("客户代码")
        public String 客户代码;

        /**
         * 产品名称
         */
        @SerializedName("产品名称")
        public String 产品名称;
        /**
         * 订单备注
         */
        @SerializedName("订单备注")
        public String 订单备注;
        /**
         * 客户名称
         */
        @SerializedName("客户名称")
        public String 客户名称;

        /**
         * 制作组别名称
         */
        @SerializedName("制作组别名称")
        public String 制作组别名称;


        /**
         * 订单数量
         */
        @SerializedName("订单数量")
        public Integer 订单数量;
        /**
         * 制作数量
         */
        @SerializedName("制作数量")
        public Integer 制作数量;


    }
}
