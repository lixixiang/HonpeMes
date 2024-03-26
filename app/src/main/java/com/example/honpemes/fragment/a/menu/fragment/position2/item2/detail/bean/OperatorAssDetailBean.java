package com.example.honpemes.fragment.a.menu.fragment.position2.item2.detail.bean;

import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.example.honpemes.fragment.a.menu.fragment.position2.item2.bean.OperatorAssBean;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * 作者：asus on 2023/12/22 10:11
 * 注释：
 */
public class OperatorAssDetailBean {



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

    public static class DataBean implements MultiItemEntity {
        public static final int TYPE_1 = 1;
        public static final int TYPE_2 = 2;
        public static final int TYPE_3 = 3;
        public int itemType;

        public OperatorAssBean operatorAssBean;

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
         * 指派单号
         */
        @SerializedName("指派单号")
        public String 指派单号;
        /**
         * 机台编码
         */
        @SerializedName("机台编码")
        public String 机台编码;
        /**
         * 加工数量
         */
        @SerializedName("加工数量")
        public Integer 加工数量;
        /**
         * 加工人员
         */
        @SerializedName("加工人员")
        public String 加工人员;
        /**
         * 加工执行次数
         */
        @SerializedName("加工执行次数")
        public Integer 加工执行次数;
        /**
         * 预计加工工时
         */
        @SerializedName("预计加工工时")
        public Integer 预计加工工时;
        /**
         * 要求完成时间
         */
        @SerializedName("要求完成时间")
        public String 要求完成时间;
        /**
         * 仓库开料
         */
        @SerializedName("仓库开料")
        public Boolean 仓库开料;
        /**
         * 刀具配置
         */
        @SerializedName("刀具配置")
        public Boolean 刀具配置;
        /**
         * 刀具配置人员
         */
        @SerializedName("刀具配置人员")
        public String 刀具配置人员;
        /**
         * 刀具配置时间
         */
        @SerializedName("刀具配置时间")
        public String 刀具配置时间;
        /**
         * 加工备注
         */
        @SerializedName("加工备注")
        public String 加工备注;
        /**
         * 上机时间
         */
        @SerializedName("上机时间")
        public String 上机时间;
        /**
         * 完成数量
         */
        @SerializedName("完成数量")
        public Integer 完成数量;
        /**
         * 完工次数
         */
        @SerializedName("完工次数")
        public Integer 完工次数;
        /**
         * 作废标识
         */
        @SerializedName("作废标识")
        public Integer 作废标识;
        /**
         * 作废人
         */
        @SerializedName("作废人")
        public String 作废人;
        /**
         * 作废时间
         */
        @SerializedName("作废时间")
        public String 作废时间;
        /**
         * 单据来源
         */
        @SerializedName("单据来源")
        public Integer 单据来源;
        /**
         * 来源单号
         */
        @SerializedName("来源单号")
        public String 来源单号;

        @Override
        public int getItemType() {
            return itemType;
        }
    }
}
