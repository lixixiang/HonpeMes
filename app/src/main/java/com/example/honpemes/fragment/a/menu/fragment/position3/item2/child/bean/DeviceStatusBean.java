package com.example.honpemes.fragment.a.menu.fragment.position3.item2.child.bean;

import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.github.mikephil.charting.data.Entry;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Lixixiang on 2023/3/2 14:47
 * 设备状态
 */
public class DeviceStatusBean implements Serializable {

    @SerializedName("Total")
    private int total;
    @SerializedName("Data")
    private List<DataBean> data;
    @SerializedName("Tag")
    private int tag;
    @SerializedName("Message")
    private String message;
    @SerializedName("Description")
    private Object description;

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

    public Object getDescription() {
        return description;
    }

    public void setDescription(Object description) {
        this.description = description;
    }

    public static class DataBean implements MultiItemEntity,Serializable {
        public static final int TYPE_1 = 1;
        public static final int TYPE_2 = 2;
        public static final int TYPE_3 = 3;
        public static final int TYPE_4 = 4;
        private int spanSize;
        private int itemType;
        private int id;
        private String 机台编码;
        private String 状态;
        private String 时间;
        private String 原始记录;
        private int rowid;
        private String 机台编号;
        private String 机台状态;
        private String 机台类型;
        private String 机台型号;
        private String 出厂编号;
        private String 生产日期;
        private String 购买日期;
        private String 机器行程;
        private String 主轴转速;
        private String 切削速度;
        private String 使用部门;
        private String 制作组别;
        private String 零件编码;
        private String 加工人员;
        private String 上机时间;
        private int 待机数量;
        private String 机台备注;
        private Object 机台序号;
        private String 固定资产编号;
        private int 预计加工工时;
        private String 指派单号;
        private String 操机员id;
        private String 操机员;
        private String 签到日期;
        private String 入库时间;

        private String 操机员ID;

        private ArrayList<Entry> mEntryList;

        public ArrayList<Entry> getmEntryList() {
            return mEntryList;
        }

        public void setmEntryList(ArrayList<Entry> mEntryList) {
            this.mEntryList = mEntryList;
        }

        public void setItemType(int itemType) {
            this.itemType = itemType;
        }

        public String get操机员ID() {
            return 操机员ID;
        }

        public void set操机员ID(String 操机员ID) {
            this.操机员ID = 操机员ID;
        }

        public String get入库时间() {
            return 入库时间;
        }

        public void set入库时间(String 入库时间) {
            this.入库时间 = 入库时间;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String get机台编码() {
            return 机台编码;
        }

        public void set机台编码(String 机台编码) {
            this.机台编码 = 机台编码;
        }

        public String get状态() {
            return 状态;
        }

        public void set状态(String 状态) {
            this.状态 = 状态;
        }

        public String get时间() {
            return 时间;
        }

        public void set时间(String 时间) {
            this.时间 = 时间;
        }

        public String get原始记录() {
            return 原始记录;
        }

        public void set原始记录(String 原始记录) {
            this.原始记录 = 原始记录;
        }

        public int getRowid() {
            return rowid;
        }

        public void setRowid(int rowid) {
            this.rowid = rowid;
        }

        public String get机台编号() {
            return 机台编号;
        }

        public void set机台编号(String 机台编号) {
            this.机台编号 = 机台编号;
        }

        public String get机台状态() {
            return 机台状态;
        }

        public void set机台状态(String 机台状态) {
            this.机台状态 = 机台状态;
        }

        public String get机台类型() {
            return 机台类型;
        }

        public void set机台类型(String 机台类型) {
            this.机台类型 = 机台类型;
        }

        public String get机台型号() {
            return 机台型号;
        }

        public void set机台型号(String 机台型号) {
            this.机台型号 = 机台型号;
        }

        public String get出厂编号() {
            return 出厂编号;
        }

        public void set出厂编号(String 出厂编号) {
            this.出厂编号 = 出厂编号;
        }

        public String get生产日期() {
            return 生产日期;
        }

        public void set生产日期(String 生产日期) {
            this.生产日期 = 生产日期;
        }

        public String get购买日期() {
            return 购买日期;
        }

        public void set购买日期(String 购买日期) {
            this.购买日期 = 购买日期;
        }

        public String get机器行程() {
            return 机器行程;
        }

        public void set机器行程(String 机器行程) {
            this.机器行程 = 机器行程;
        }

        public String get主轴转速() {
            return 主轴转速;
        }

        public void set主轴转速(String 主轴转速) {
            this.主轴转速 = 主轴转速;
        }

        public String get切削速度() {
            return 切削速度;
        }

        public void set切削速度(String 切削速度) {
            this.切削速度 = 切削速度;
        }

        public String get使用部门() {
            return 使用部门;
        }

        public void set使用部门(String 使用部门) {
            this.使用部门 = 使用部门;
        }

        public String get制作组别() {
            return 制作组别;
        }

        public void set制作组别(String 制作组别) {
            this.制作组别 = 制作组别;
        }

        public String get零件编码() {
            return 零件编码;
        }

        public void set零件编码(String 零件编码) {
            this.零件编码 = 零件编码;
        }

        public String get加工人员() {
            return 加工人员;
        }

        public void set加工人员(String 加工人员) {
            this.加工人员 = 加工人员;
        }

        public String get上机时间() {
            return 上机时间;
        }

        public void set上机时间(String 上机时间) {
            this.上机时间 = 上机时间;
        }

        public int get待机数量() {
            return 待机数量;
        }

        public void set待机数量(int 待机数量) {
            this.待机数量 = 待机数量;
        }

        public String get机台备注() {
            return 机台备注;
        }

        public void set机台备注(String 机台备注) {
            this.机台备注 = 机台备注;
        }

        public Object get机台序号() {
            return 机台序号;
        }

        public void set机台序号(Object 机台序号) {
            this.机台序号 = 机台序号;
        }

        public String get固定资产编号() {
            return 固定资产编号;
        }

        public void set固定资产编号(String 固定资产编号) {
            this.固定资产编号 = 固定资产编号;
        }

        public int get预计加工工时() {
            return 预计加工工时;
        }

        public void set预计加工工时(int 预计加工工时) {
            this.预计加工工时 = 预计加工工时;
        }

        public String get指派单号() {
            return 指派单号;
        }

        public void set指派单号(String 指派单号) {
            this.指派单号 = 指派单号;
        }

        public String get操机员id() {
            return 操机员id;
        }

        public void set操机员id(String 操机员id) {
            this.操机员id = 操机员id;
        }

        public String get操机员() {
            return 操机员;
        }

        public void set操机员(String 操机员) {
            this.操机员 = 操机员;
        }

        public String get签到日期() {
            return 签到日期;
        }

        public void set签到日期(String 签到日期) {
            this.签到日期 = 签到日期;
        }

        @Override
        public int getItemType() {
            return itemType;
        }
    }
}
