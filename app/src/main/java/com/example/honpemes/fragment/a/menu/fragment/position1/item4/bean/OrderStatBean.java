package com.example.honpemes.fragment.a.menu.fragment.position1.item4.bean;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * 作者：asus on 2023/12/8 11:17
 * 注释：
 */
public class OrderStatBean implements Serializable {

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
    public Object description;

    public static class DataBean {
        /**
         * 订单制作组别
         */
        @SerializedName("订单制作组别")
        public String 订单制作组别;
        /**
         * 订单数量
         */
        @SerializedName("订单数量")
        public String 订单数量;
        /**
         * 完成数量
         */
        @SerializedName("完成数量")
        public String 完成数量;
        /**
         * 完成率
         */
        @SerializedName("完成率")
        public String 完成率;
        /**
         * 延迟数量
         */
        @SerializedName("延迟数量")
        public String 延迟数量;
        /**
         * 延迟率
         */
        @SerializedName("延迟率")
        public String 延迟率;
        /**
         * 报废数量
         */
        @SerializedName("报废数量")
        public String 报废数量;
        /**
         * 报废率
         */
        @SerializedName("报废率")
        public String 报废率;
    }
}
