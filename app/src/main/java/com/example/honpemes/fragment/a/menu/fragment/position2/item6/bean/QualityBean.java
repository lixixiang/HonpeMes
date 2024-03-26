package com.example.honpemes.fragment.a.menu.fragment.position2.item6.bean;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * 作者：asus on 2023/12/13 14:31
 * 注释：
 */
public class QualityBean {

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
         * 生产部门
         */
        @SerializedName("生产部门")
        public String 生产部门;
        /**
         * 生产组别
         */
        @SerializedName("生产组别")
        public String 生产组别;
        /**
         * 订单数量
         */
        @SerializedName("订单数量")
        public String 订单数量;
        /**
         * 零件数量
         */
        @SerializedName("零件数量")
        public String 零件数量;
        /**
         * 良品数量
         */
        @SerializedName("良品数量")
        public String 良品数量;
        /**
         * 良品率
         */
        @SerializedName("良品率")
        public String 良品率;
        /**
         * 返工数量
         */
        @SerializedName("返工数量")
        public String 返工数量;
        /**
         * 返工率
         */
        @SerializedName("返工率")
        public String 返工率;
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
