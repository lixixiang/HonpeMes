package com.example.honpemes.fragment.a.menu.fragment.position3.item3.bean;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * 作者：asus on 2023/12/25 13:46
 * 注释：
 */
public class CurrentTaskBean {

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
         * id
         */
        @SerializedName("Id")
        public Integer id;
        /**
         * machineIP
         */
        @SerializedName("machineIP")
        public String machineIP;
        /**
         * baseCreateTime
         */
        @SerializedName("BaseCreateTime")
        public String baseCreateTime;
        /**
         * baseModifyTime
         */
        @SerializedName("BaseModifyTime")
        public Object baseModifyTime;
        /**
         * baseCreatorId
         */
        @SerializedName("BaseCreatorId")
        public Long baseCreatorId;
        /**
         * baseCreator
         */
        @SerializedName("BaseCreator")
        public String baseCreator;
        /**
         * baseModifierId
         */
        @SerializedName("BaseModifierId")
        public Object baseModifierId;
        /**
         * baseVersion
         */
        @SerializedName("BaseVersion")
        public String baseVersion;
        /**
         * remark
         */
        @SerializedName("Remark")
        public String remark;
        /**
         * fileName
         */
        @SerializedName("FileName")
        public String fileName;
        /**
         * taskName
         */
        @SerializedName("TaskName")
        public String taskName;
        /**
         * mainRunProg
         */
        @SerializedName("MainRunProg")
        public String mainRunProg;
        /**
         * mainProgs
         */
        @SerializedName("MainProgs")
        public String mainProgs;
        /**
         * subProgs
         */
        @SerializedName("SubProgs")
        public String subProgs;

        public boolean isOpen;
    }
}
