package com.example.honpemes.fragment.a.menu.fragment.position2.item3.bean;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * 作者：asus on 2023/12/7 09:07
 * 注释：
 */
public class OutsourcingPurchaseBean {

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
        /**
         * 业务组别
         */
        @SerializedName("业务组别")
        public String 业务组别;
        /**
         * 制作组别
         */
        @SerializedName("制作组别")
        public String 制作组别;
        /**
         * 外发订单数
         */
        @SerializedName("外发订单数")
        public String 外发订单数;
        /**
         * 外发零件数
         */
        @SerializedName("外发零件数")
        public String 外发零件数;
        /**
         * 确认收货订单数
         */
        @SerializedName("确认收货订单数")
        public String 确认收货订单数;
        /**
         * 确认收货零件数
         */
        @SerializedName("确认收货零件数")
        public String 确认收货零件数;
        /**
         * 报价订单数
         */
        @SerializedName("报价订单数")
        public String 报价订单数;
        /**
         * 报价金额
         */
        @SerializedName("报价金额")
        public Double 报价金额;
        @SerializedName("确认��货零件数")
        public String _$245;// FIXME check this code
    }
}
