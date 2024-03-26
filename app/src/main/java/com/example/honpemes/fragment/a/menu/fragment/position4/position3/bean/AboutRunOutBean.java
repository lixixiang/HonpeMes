package com.example.honpemes.fragment.a.menu.fragment.position4.position3.bean;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class AboutRunOutBean {


    @SerializedName("Total")
    private int total;
    @SerializedName("Data")
    private List<DataBean> data;
    @SerializedName("Tag")
    private int tag;
    @SerializedName("Message")
    private String message;
    @SerializedName("Description")
    private String description;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public int getTag() {
        return tag;
    }

    public void setTag(int tag) {
        this.tag = tag;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public static class DataBean {
        private int rowid;
        private String 申请单号;
        private String 申请人代码;
        private String 申请人;
        private String 申请部门;
        private String 业务组别;
        private String 申请日期;
        private String 特殊事项;
        private String 生产单号;
        private String 供应商名称;
        private double 报价金额;
        private String 交货日期;
        private int 供应商确认;
        private String 采购备注;
        private String 记账状态;
        private boolean 资料同步;
        private String 采购分类;
        private String 下单日期;
        private String 订单交期;
        private String 跟单负责人;
        private String 申请组别;
        private String 制作组别;
        private String 订单组别;
        private String 订单状态;
        private String 制单;
        private int 审批状态;
        private boolean 作废;
        private boolean 整单外发;
        private String 入库单号;
        private String 收货日期;
        private int 数量;
        private String 状态;
        private String 订单策划;
        private String 要求交货日期;
        private String 要求交期;
        private String 客户代码;
        private String 收货超时天数;

        private String tips;

        public String getTips() {
            return tips;
        }

        public void setTips(String tips) {
            this.tips = tips;
        }

        public int getRowid() {
            return rowid;
        }

        public void setRowid(int rowid) {
            this.rowid = rowid;
        }

        public String get申请单号() {
            return 申请单号;
        }

        public void set申请单号(String 申请单号) {
            this.申请单号 = 申请单号;
        }

        public String get申请人代码() {
            return 申请人代码;
        }

        public void set申请人代码(String 申请人代码) {
            this.申请人代码 = 申请人代码;
        }

        public String get申请人() {
            return 申请人;
        }

        public void set申请人(String 申请人) {
            this.申请人 = 申请人;
        }

        public String get申请部门() {
            return 申请部门;
        }

        public void set申请部门(String 申请部门) {
            this.申请部门 = 申请部门;
        }

        public String get业务组别() {
            return 业务组别;
        }

        public void set业务组别(String 业务组别) {
            this.业务组别 = 业务组别;
        }

        public String get申请日期() {
            return 申请日期;
        }

        public void set申请日期(String 申请日期) {
            this.申请日期 = 申请日期;
        }

        public String get特殊事项() {
            return 特殊事项;
        }

        public void set特殊事项(String 特殊事项) {
            this.特殊事项 = 特殊事项;
        }

        public String get生产单号() {
            return 生产单号;
        }

        public void set生产单号(String 生产单号) {
            this.生产单号 = 生产单号;
        }

        public String get供应商名称() {
            return 供应商名称;
        }

        public void set供应商名称(String 供应商名称) {
            this.供应商名称 = 供应商名称;
        }

        public double get报价金额() {
            return 报价金额;
        }

        public void set报价金额(double 报价金额) {
            this.报价金额 = 报价金额;
        }

        public String get交货日期() {
            return 交货日期;
        }

        public void set交货日期(String 交货日期) {
            this.交货日期 = 交货日期;
        }

        public int get供应商确认() {
            return 供应商确认;
        }

        public void set供应商确认(int 供应商确认) {
            this.供应商确认 = 供应商确认;
        }

        public String get采购备注() {
            return 采购备注;
        }

        public void set采购备注(String 采购备注) {
            this.采购备注 = 采购备注;
        }

        public String get记账状态() {
            return 记账状态;
        }

        public void set记账状态(String 记账状态) {
            this.记账状态 = 记账状态;
        }

        public boolean is资料同步() {
            return 资料同步;
        }

        public void set资料同步(boolean 资料同步) {
            this.资料同步 = 资料同步;
        }

        public String get采购分类() {
            return 采购分类;
        }

        public void set采购分类(String 采购分类) {
            this.采购分类 = 采购分类;
        }

        public String get下单日期() {
            return 下单日期;
        }

        public void set下单日期(String 下单日期) {
            this.下单日期 = 下单日期;
        }

        public String get订单交期() {
            return 订单交期;
        }

        public void set订单交期(String 订单交期) {
            this.订单交期 = 订单交期;
        }

        public String get跟单负责人() {
            return 跟单负责人;
        }

        public void set跟单负责人(String 跟单负责人) {
            this.跟单负责人 = 跟单负责人;
        }

        public String get申请组别() {
            return 申请组别;
        }

        public void set申请组别(String 申请组别) {
            this.申请组别 = 申请组别;
        }

        public String get制作组别() {
            return 制作组别;
        }

        public void set制作组别(String 制作组别) {
            this.制作组别 = 制作组别;
        }

        public String get订单组别() {
            return 订单组别;
        }

        public void set订单组别(String 订单组别) {
            this.订单组别 = 订单组别;
        }

        public String get订单状态() {
            return 订单状态;
        }

        public void set订单状态(String 订单状态) {
            this.订单状态 = 订单状态;
        }

        public String get制单() {
            return 制单;
        }

        public void set制单(String 制单) {
            this.制单 = 制单;
        }

        public int get审批状态() {
            return 审批状态;
        }

        public void set审批状态(int 审批状态) {
            this.审批状态 = 审批状态;
        }

        public boolean is作废() {
            return 作废;
        }

        public void set作废(boolean 作废) {
            this.作废 = 作废;
        }

        public boolean is整单外发() {
            return 整单外发;
        }

        public void set整单外发(boolean 整单外发) {
            this.整单外发 = 整单外发;
        }

        public String get入库单号() {
            return 入库单号;
        }

        public void set入库单号(String 入库单号) {
            this.入库单号 = 入库单号;
        }

        public String get收货日期() {
            return 收货日期;
        }

        public void set收货日期(String 收货日期) {
            this.收货日期 = 收货日期;
        }

        public int get数量() {
            return 数量;
        }

        public void set数量(int 数量) {
            this.数量 = 数量;
        }

        public String get状态() {
            return 状态;
        }

        public void set状态(String 状态) {
            this.状态 = 状态;
        }

        public String get订单策划() {
            return 订单策划;
        }

        public void set订单策划(String 订单策划) {
            this.订单策划 = 订单策划;
        }

        public String get要求交货日期() {
            return 要求交货日期;
        }

        public void set要求交货日期(String 要求交货日期) {
            this.要求交货日期 = 要求交货日期;
        }

        public String get要求交期() {
            return 要求交期;
        }

        public void set要求交期(String 要求交期) {
            this.要求交期 = 要求交期;
        }

        public String get客户代码() {
            return 客户代码;
        }

        public void set客户代码(String 客户代码) {
            this.客户代码 = 客户代码;
        }

        public String get收货超时天数() {
            return 收货超时天数;
        }

        public void set收货超时天数(String 收货超时天数) {
            this.收货超时天数 = 收货超时天数;
        }
    }
}
