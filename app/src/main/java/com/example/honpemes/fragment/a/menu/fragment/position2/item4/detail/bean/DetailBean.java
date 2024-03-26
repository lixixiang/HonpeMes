package com.example.honpemes.fragment.a.menu.fragment.position2.item4.detail.bean;

import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.example.honpemes.fragment.a.menu.fragment.position2.item4.bean.ProcessingRecordBean;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * 作者：asus on 2023/12/21 11:14
 * 注释：
 */
public class DetailBean {
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

    public static class DataBean  implements MultiItemEntity {
        public static final int TYPE_1 = 1;
        public static final int TYPE_2 = 2;
        public static final int TYPE_3 = 3;
        public static final int TYPE_4 = 4;
        public static final int TYPE_5 = 5;
        public static final int TYPE_6 = 6;
        public static final int TYPE_7 = 7;
        public static final int TYPE_8 = 8;
        public static final int TYPE_9 = 9;

        public int itemType;

        public ProcessingRecordBean.DataBean dataBean;

        /**
         * id
         */
        @SerializedName("Id")
        public Integer id;
        /**
         * workTaskId
         */
        @SerializedName("WorkTaskId")
        public Integer workTaskId;
        /**
         * mainProgNo
         */
        @SerializedName("MainProgNo")
        public Integer mainProgNo;
        /**
         * startTime
         */
        @SerializedName("StartTime")
        public String startTime;
        /**
         * endTime
         */
        @SerializedName("EndTime")
        public String endTime;
        /**
         * timeSpan
         */
        @SerializedName("TimeSpan")
        public String timeSpan;
        /**
         * createTime
         */
        @SerializedName("CreateTime")
        public String createTime;

        @Override
        public int getItemType() {
            return itemType;
        }
    }
}
