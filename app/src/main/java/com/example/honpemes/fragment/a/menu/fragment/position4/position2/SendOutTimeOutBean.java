package com.example.honpemes.fragment.a.menu.fragment.position4.position2;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * 作者：asus  on 2024/3/7 9:48
 * 注释：
 */
public class SendOutTimeOutBean implements Serializable {


    @SerializedName("Total")
    public int total;
    @SerializedName("Data")
    public List<DataBean> data;
    @SerializedName("Tag")
    public int tag;
    @SerializedName("Message")
    public String message;
    @SerializedName("Description")
    public String description;

   

    public static class DataBean {
        public int rowid;
        public String 申请单号;
        public String 申请人代码;
        public String 申请人;
        public String 申请部门;
        public String 业务组别;
        public String 申请日期;
        public String 特殊事项;
        public String 生产单号;
        public String 供应商名称;
        public double 报价金额;
        public String 交货日期;
        public int 供应商确认;
        public String 采购备注;
        public String 记账状态;
        public boolean 资料同步;
        public String 采购分类;
        public String 下单日期;
        public String 订单交期;
        public String 跟单负责人;
        public String 申请组别;
        public String 制作组别;
        public String 订单组别;
        public String 订单状态;
        public String 制单;
        public int 审批状态;
        public boolean 作废;
        public boolean 整单外发;
        public String 入库单号;
        public String 收货日期;
        public int 数量;
        public String 状态;
        public String 订单策划;
        public String 要求交货日期;
        public String 要求交期;
        public String 客户代码;
        public String 收货超时天数;


    }
}
