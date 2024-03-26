package com.example.honpemes.fragment.a.menu.fragment.position2.item3.bean;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * 作者：asus on 2023/12/7 08:47
 * 注释：
 */
public class ProduceMachineHoursBean {

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
         * 操作员
         */
        @SerializedName("操作员")
        public String 操作员;
        /**
         * 总计完成数量
         */
        @SerializedName("总计完成数量")
        public Integer 总计完成数量;
        /**
         * 总计工时分钟
         */
        @SerializedName("总计工时分钟")
        public Integer 总计工时分钟;
    }
}
