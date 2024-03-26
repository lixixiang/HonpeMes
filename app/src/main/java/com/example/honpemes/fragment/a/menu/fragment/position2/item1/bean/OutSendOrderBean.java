package com.example.honpemes.fragment.a.menu.fragment.position2.item1.bean;

import android.nfc.Tag;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * 作者：asus on 2023/12/21 15:35
 * 注释：
 */
public class OutSendOrderBean {
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
         * 生产单号
         */
        @SerializedName("生产单号")
        public String 生产单号;
        /**
         * 零件编码
         */
        @SerializedName("零件编码")
        public String 零件编码;
        /**
         * 数量
         */
        @SerializedName("数量")
        public String 数量;
        /**
         * 外发项目
         */
        @SerializedName("外发项目")
        public String 外发项目;
        /**
         * 外发时间
         */
        @SerializedName("外发时间")
        public String 外发时间;
        /**
         * 完成时间
         */
        @SerializedName("完成时间")
        public String 完成时间;
        /**
         * 外协供应商
         */
        @SerializedName("外协供应商")
        public String 外协供应商;
        /**
         * 外协电话
         */
        @SerializedName("外协电话")
        public String 外协电话;
        /**
         * 备注
         */
        @SerializedName("备注")
        public String 备注;

    }
}