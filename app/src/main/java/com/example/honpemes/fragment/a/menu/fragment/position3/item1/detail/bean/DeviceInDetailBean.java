package com.example.honpemes.fragment.a.menu.fragment.position3.item1.detail.bean;

import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.example.honpemes.fragment.a.menu.fragment.position3.item1.bean.DeviceInBean;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * 作者：asus on 2023/12/25 09:48
 * 注释：
 */
public class DeviceInDetailBean {

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

    public static class DataBean implements Serializable, MultiItemEntity {

        public static final int TYPE_1 = 1;
        public static final int TYPE_2 = 2;
        public static final int TYPE_3 = 3;

        public int itemType;


        public DeviceInBean deviceInBean;


        @Override
        public int getItemType() {
            return itemType;
        }

        /**
         * id
         */
        @SerializedName("Id")
        public Integer id;
        /**
         * 生产单号
         */
        @SerializedName("生产单号")
        public String 生产单号;
        /**
         * 主件编号
         */
        @SerializedName("主件编号")
        public String 主件编号;
        /**
         * 零件编号
         */
        @SerializedName("零件编号")
        public String 零件编号;
        /**
         * 零件名称
         */
        @SerializedName("零件名称")
        public String 零件名称;
        /**
         * 零件材质
         */
        @SerializedName("零件材质")
        public String 零件材质;
        /**
         * 零件尺寸
         */
        @SerializedName("零件尺寸")
        public String 零件尺寸;
        /**
         * 制作方式
         */
        @SerializedName("制作方式")
        public String 制作方式;
        /**
         * 登记人
         */
        @SerializedName("登记人")
        public String 登记人;
        /**
         * 编程人员
         */
        @SerializedName("编程人员")
        public String 编程人员;
        /**
         * 编程时间
         */
        @SerializedName("编程时间")
        public String 编程时间;
        /**
         * 程式单
         */
        @SerializedName("程式单")
        public Boolean 程式单;
        /**
         * nc程式单
         */
        @SerializedName("NC程式单")
        public Boolean nc程式单;
        /**
         * 订单数量
         */
        @SerializedName("订单数量")
        public Integer 订单数量;
        /**
         * 制作数量
         */
        @SerializedName("制作数量")
        public Integer 制作数量;
        /**
         * 完成数量
         */
        @SerializedName("完成数量")
        public Integer 完成数量;
        /**
         * 待加工数量
         */
        @SerializedName("待加工数量")
        public Integer 待加工数量;
        /**
         * 备注
         */
        @SerializedName("备注")
        public String 备注;
        /**
         * 主件编码
         */
        @SerializedName("主件编码")
        public String 主件编码;
        /**
         * 零件编码
         */
        @SerializedName("零件编码")
        public String 零件编码;
        /**
         * 项目预审说明
         */
        @SerializedName("项目预审说明")
        public String 项目预审说明;
        /**
         * 工艺要求
         */
        @SerializedName("工艺要求")
        public String 工艺要求;
        /**
         * 存放位置
         */
        @SerializedName("存放位置")
        public String 存放位置;
        /**
         * 文件类型
         */
        @SerializedName("文件类型")
        public String 文件类型;
        /**
         * 手工完成数量
         */
        @SerializedName("手工完成数量")
        public Integer 手工完成数量;
        /**
         * 编程人员代码
         */
        @SerializedName("编程人员代码")
        public String 编程人员代码;
        /**
         * 五金加工
         */
        @SerializedName("五金加工")
        public Boolean 五金加工;
        /**
         * 零件取消
         */
        @SerializedName("零件取消")
        public Boolean 零件取消;
        /**
         * 委外工艺
         */
        @SerializedName("委外工艺")
        public String 委外工艺;
        /**
         * 制程设定
         */
        @SerializedName("制程设定")
        public Boolean 制程设定;
        /**
         * baseIsDelete
         */
        @SerializedName("BaseIsDelete")
        public Object baseIsDelete;
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
        public Object baseCreatorId;
        /**
         * baseModifierId
         */
        @SerializedName("BaseModifierId")
        public Object baseModifierId;
        /**
         * baseVersion
         */
        @SerializedName("BaseVersion")
        public Object baseVersion;
    }
}
