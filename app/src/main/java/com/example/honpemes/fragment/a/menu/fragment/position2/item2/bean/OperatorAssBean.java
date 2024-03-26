package com.example.honpemes.fragment.a.menu.fragment.position2.item2.bean;

import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * 作者：asus on 2023/12/22 08:45
 * 注释：
 */
public class OperatorAssBean implements Serializable, MultiItemEntity {

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
     * 机台类型
     */
    @SerializedName("机台类型")
    public String 机台类型;
    /**
     * 仓库开料出单时间
     */
    @SerializedName("仓库开料出单时间")
    public String 仓库开料出单时间;
    /**
     * 指派人员
     */
    @SerializedName("指派人员")
    public String 指派人员;
    /**
     * 指派时间
     */
    @SerializedName("指派时间")
    public String 指派时间;
    /**
     * 制作数量
     */
    @SerializedName("制作数量")
    public Integer 制作数量;
    /**
     * 开料尺寸
     */
    @SerializedName("开料尺寸")
    public String 开料尺寸;
    /**
     * 执行次数
     */
    @SerializedName("执行次数")
    public Integer 执行次数;
    /**
     * 补数申请次数
     */
    @SerializedName("补数申请次数")
    public Integer 补数申请次数;
    /**
     * 材料长度
     */
    @SerializedName("材料长度")
    public Double 材料长度;
    /**
     * 材料高度
     */
    @SerializedName("材料高度")
    public Double 材料高度;
    /**
     * 材料宽度
     */
    @SerializedName("材料宽度")
    public Double 材料宽度;
    /**
     * 开料备注
     */
    @SerializedName("开料备注")
    public String 开料备注;

    @Override
    public int getItemType() {
        return 0;
    }
}
