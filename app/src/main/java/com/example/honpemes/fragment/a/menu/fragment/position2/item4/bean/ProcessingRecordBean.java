package com.example.honpemes.fragment.a.menu.fragment.position2.item4.bean;

import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * 作者：asus on 2023/12/21 09:19
 * 注释：
 */
public class ProcessingRecordBean {

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



    public static class DataBean implements MultiItemEntity, Serializable {
        public static final int TYPE_1 = 1;
        public static final int TYPE_2 = 2;
        public static final int TYPE_3 = 3;
        public static final int TYPE_4 = 4;
        public static final int TYPE_5 = 5;
        public static final int TYPE_6 = 6;
        public static final int TYPE_7 = 7;
        public static final int TYPE_8 = 8;
        public static final int TYPE_9 = 9;
        public long diffTime;

        public int ItemType;
        /**
         * id
         */
        @SerializedName("Id")
        public Integer id;
        /**
         * machineNO
         */
        @SerializedName("MachineNO")
        public String machineNO;
        /**
         * iPAddress
         */
        @SerializedName("IPAddress")
        public String iPAddress;
        /**
         * workpieceCount
         */
        @SerializedName("WorkpieceCount")
        public Integer workpieceCount;
        /**
         * dailyRunTime
         */
        @SerializedName("DailyRunTime")
        public Integer dailyRunTime;
        /**
         * dailyCutTime
         */
        @SerializedName("DailyCutTime")
        public Integer dailyCutTime;
        /**
         * dailyWaitTime
         */
        @SerializedName("DailyWaitTime")
        public Integer dailyWaitTime;
        /**
         * infoDate
         */
        @SerializedName("infoDate")
        public String infoDate;
        /**
         * startTime
         */
        @SerializedName("StartTime")
        public Integer startTime;
        /**
         * endTime
         */
        @SerializedName("EndTime")
        public Integer endTime;
        /**
         * workName
         */
        @SerializedName("WorkName")
        public String workName;
        /**
         * createTime
         */
        @SerializedName("CreateTime")
        public String createTime;
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
         * 机台类型
         */
        @SerializedName("机台类型")
        public String 机台类型;
        /**
         * 机台型号
         */
        @SerializedName("机台型号")
        public String 机台型号;
        /**
         * 出厂编号
         */
        @SerializedName("出厂编号")
        public String 出厂编号;
        /**
         * 生产日期
         */
        @SerializedName("生产日期")
        public String 生产日期;
        /**
         * 购买日期
         */
        @SerializedName("购买日期")
        public String 购买日期;
        /**
         * 机器行程
         */
        @SerializedName("机器行程")
        public String 机器行程;
        /**
         * 主轴转速
         */
        @SerializedName("主轴转速")
        public String 主轴转速;
        /**
         * 切削速度
         */
        @SerializedName("切削速度")
        public String 切削速度;
        /**
         * 使用部门
         */
        @SerializedName("使用部门")
        public String 使用部门;
        /**
         * 制作组别
         */
        @SerializedName("制作组别")
        public String 制作组别;
        /**
         * 待机数量
         */
        @SerializedName("待机数量")
        public Integer 待机数量;
        /**
         * 机台备注
         */
        @SerializedName("机台备注")
        public String 机台备注;
        /**
         * 固定资产编号
         */
        @SerializedName("固定资产编号")
        public Object 固定资产编号;


        @Override
        public int getItemType() {
            return ItemType;
        }
    }
}
