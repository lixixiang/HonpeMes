package com.example.honpemes.fragment.a.menu.fragment.position6.item1.bean;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Lixixiang on 2023/2/6 10:58
 */
public class FileManagerBean {

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

    public static class DataBean {
        /**
         * productName
         */
        @SerializedName("ProductName")
        public String productName;
        /**
         * baseCreater
         */
        @SerializedName("BaseCreater")
        public String baseCreater;
        /**
         * baseModifier
         */
        @SerializedName("BaseModifier")
        public String baseModifier;
        /**
         * remarks
         */
        @SerializedName("Remarks")
        public String remarks;
        /**
         * baseIsDelete
         */
        @SerializedName("BaseIsDelete")
        public Integer baseIsDelete;
        /**
         * baseCreateTime
         */
        @SerializedName("BaseCreateTime")
        public String baseCreateTime;
        /**
         * baseModifyTime
         */
        @SerializedName("BaseModifyTime")
        public String baseModifyTime;
        /**
         * documentManagement
         */
        @SerializedName("DocumentManagement")
        public List<DocumentManagementBean> documentManagement;
        /**
         * id
         */
        @SerializedName("Id")
        public String id;
        /**
         * token
         */
        @SerializedName("Token")
        public String token;

        public static class DocumentManagementBean {
            /**
             * id
             */
            @SerializedName("Id")
            public Integer id;
            /**
             * systemClassification
             */
            @SerializedName("SystemClassification")
            public String systemClassification;
            /**
             * documentClassification
             */
            @SerializedName("DocumentClassification")
            public String documentClassification;
            /**
             * documentCode
             */
            @SerializedName("DocumentCode")
            public String documentCode;
            /**
             * documentName
             */
            @SerializedName("DocumentName")
            public String documentName;
            /**
             * designer
             */
            @SerializedName("Designer")
            public String designer;
            /**
             * productName
             */
            @SerializedName("ProductName")
            public String productName;
            /**
             * productNameID
             */
            @SerializedName("ProductNameID")
            public String productNameID;
            /**
             * remarks
             */
            @SerializedName("Remarks")
            public String remarks;
            /**
             * baseCreator
             */
            @SerializedName("BaseCreator")
            public String baseCreator;
            /**
             * baseModifier
             */
            @SerializedName("BaseModifier")
            public String baseModifier;
            /**
             * numberChanges
             */
            @SerializedName("NumberChanges")
            public Integer numberChanges;
            /**
             * fileType
             */
            @SerializedName("FileType")
            public String fileType;
            /**
             * storageLocation
             */
            @SerializedName("StorageLocation")
            public String storageLocation;
            /**
             * versionNo
             */
            @SerializedName("VersionNo")
            public String versionNo;
            /**
             * baseIsDelete
             */
            @SerializedName("BaseIsDelete")
            public Integer baseIsDelete;
            /**
             * baseCreateTime
             */
            @SerializedName("BaseCreateTime")
            public String baseCreateTime;
            /**
             * forceTime
             */
            @SerializedName("ForceTime")
            public String forceTime;
            /**
             * baseModifyTime
             */
            @SerializedName("BaseModifyTime")
            public String baseModifyTime;
            /**
             * baseCreatorId
             */
            @SerializedName("BaseCreatorId")
            public Long baseCreatorId;
            /**
             * baseModifierId
             */
            @SerializedName("BaseModifierId")
            public Long baseModifierId;
            /**
             * baseVersion
             */
            @SerializedName("BaseVersion")
            public Integer baseVersion;
            /**
             * state
             */
            @SerializedName("State")
            public String state;
        }
    }
}
