package com.example.honpemes.fragment.a.menu.fragment.position3.item4.bean;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * 作者：asus on 2023/12/25 16:35
 * 注释：
 */
public class CheckRepairBean {

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
         * rowid
         */
        @SerializedName("rowid")
        public Integer rowid;
        /**
         * 机台编号
         */
        @SerializedName("机台编号")
        public String 机台编号;
        /**
         * 检修次数
         */
        @SerializedName("检修次数")
        public Integer 检修次数;
        /**
         * 开始时间
         */
        @SerializedName("开始时间")
        public String 开始时间;
        /**
         * 结束时间
         */
        @SerializedName(value = "结束时间", alternate = {""})
        public String 结束时间;
        /**
         * 检修工时
         */
        @SerializedName("检修工时")
        public Double 检修工时;
        /**
         * 机台状态
         */
        @SerializedName("机台状态")
        public String 机台状态;
        /**
         * 创建时间
         */
        @SerializedName("创建时间")
        public String 创建时间;
        /**
         * 创建人
         */
        @SerializedName("创建人")
        public String 创建人;
    }
}
