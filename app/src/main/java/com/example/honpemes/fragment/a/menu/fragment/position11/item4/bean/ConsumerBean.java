package com.example.honpemes.fragment.a.menu.fragment.position11.item4.bean;

import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * 作者：asus on 2024/1/2 10:31
 * 注释：
 */
public class ConsumerBean {

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

    public static class DataBean  implements Serializable, MultiItemEntity{

        public static final int Item1 = 0x01;
        public static final int Item2 = 0x02;
        public static final int Item3 = 0x03;
        public static final int Item4 = 0x04;

        public boolean Expanded;

        public String title;
        public List<ConsumerBean.DataBean> mList;
        public int itemType;
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
         * 客诉单号
         */
        @SerializedName("客诉单号")
        public String 客诉单号;
        /**
         * 制单人员
         */
        @SerializedName("制单人员")
        public String 制单人员;
        /**
         * 制单日期
         */
        @SerializedName("制单日期")
        public String 制单日期;
        /**
         * 投诉日期
         */
        @SerializedName("投诉日期")
        public String 投诉日期;
        /**
         * 投诉问题
         */
        @SerializedName("投诉问题")
        public String 投诉问题;
        /**
         * 责任人员
         */
        @SerializedName("责任人员")
        public String 责任人员;
        /**
         * 责任部门
         */
        @SerializedName("责任部门")
        public String 责任部门;
        /**
         * 原因分析
         */
        @SerializedName("原因分析")
        public String 原因分析;
        /**
         * 改善措施
         */
        @SerializedName("改善措施")
        public String 改善措施;
        /**
         * 预防对策
         */
        @SerializedName("预防对策")
        public String 预防对策;
        /**
         * 效果确认
         */
        @SerializedName("效果确认")
        public String 效果确认;
        /**
         * 结案时间
         */
        @SerializedName("结案时间")
        public String 结案时间;
        /**
         * 项目结案
         */
        @SerializedName("项目结案")
        public String 项目结案;
        /**
         * 客户名称
         */
        @SerializedName("客户名称")
        public String 客户名称;
        /**
         * 产品名称
         */
        @SerializedName("产品名称")
        public String 产品名称;
        /**
         * 订单组别
         */
        @SerializedName("订单组别")
        public String 订单组别;
        /**
         * 制作组别
         */
        @SerializedName("制作组别")
        public String 制作组别;
        /**
         * 下单日期
         */
        @SerializedName("下单日期")
        public String 下单日期;
        /**
         * 交货日期
         */
        @SerializedName("交货日期")
        public String 交货日期;
        /**
         * 实际交货日期
         */
        @SerializedName("实际交货日期")
        public String 实际交货日期;
        /**
         * 业务审核
         */
        @SerializedName("业务审核")
        public String 业务审核;
        /**
         * 业务确认
         */
        @SerializedName("业务确认")
        public String 业务确认;
        /**
         * 业务审核时间
         */
        @SerializedName("业务审核时间")
        public String 业务审核时间;
        /**
         * 业务确认时间
         */
        @SerializedName("业务确认时间")
        public String 业务确认时间;
        /**
         * 责任审核
         */
        @SerializedName("责任审核")
        public String 责任审核;
        /**
         * 责任审核时间
         */
        @SerializedName("责任审核时间")
        public String 责任审核时间;
        /**
         * 责任确认
         */
        @SerializedName("责任确认")
        public String 责任确认;
        /**
         * 责任确认时间
         */
        @SerializedName("责任确认时间")
        public String 责任确认时间;
        /**
         * 否定结案
         */
        @SerializedName("否定结案")
        public String 否定结案;
        /**
         * 结案否定时间
         */
        @SerializedName("结案否定时间")
        public String 结案否定时间;
        /**
         * 客诉类型
         */
        @SerializedName("客诉类型")
        public String 客诉类型;
        /**
         * 客诉要求
         */
        @SerializedName("客诉要求")
        public String 客诉要求;




        @Override
        public int getItemType() {
            return itemType;
        }
    }
}
