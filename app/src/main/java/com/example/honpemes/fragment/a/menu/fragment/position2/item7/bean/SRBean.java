package com.example.honpemes.fragment.a.menu.fragment.position2.item7.bean;

import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * 作者：asus on 2023/12/28 11:36
 * 注释：报废补料
 */
public class SRBean {


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
    public Object message;
    /**
     * description
     */
    @SerializedName("Description")
    public Object description;

    public static class DataBean implements MultiItemEntity {

        public static final int type_1 = 0x1;
        public static final int type_2 = 0x2;

        public int itemType;
        public String title;
        public int count;
        public List<DataBean> chatList;

        /**
         * 责任部门
         */
        @SerializedName("责任部门")
        public String 责任部门;
        /**
         * 补料次数
         */
        @SerializedName("补料次数")
        public String 补料次数;
        /**
         * 占比
         */
        @SerializedName("占比")
        public String 占比;
        /**
         * 报废主要原因
         */
        @SerializedName("报废主要原因")
        public String 报废主要原因;
        /**
         * 改善措施
         */
        @SerializedName("改善措施")
        public String 改善措施;
        /**
         * 预计完成时间
         */
        @SerializedName("预计完成时间")
        public String 预计完成时间;

        @Override
        public int getItemType() {
            return itemType;
        }
    }

}
