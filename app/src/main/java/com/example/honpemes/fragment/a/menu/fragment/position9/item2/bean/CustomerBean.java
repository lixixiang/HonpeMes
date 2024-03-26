package com.example.honpemes.fragment.a.menu.fragment.position9.item2.bean;

import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * @author: asus
 * @date: 2022/11/23
 * @Description:
 */
public class CustomerBean {

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

    public static class DataBean implements Serializable, MultiItemEntity {
        public static final int TYPE_1 = 1;
        public static final int TYPE_2 = 2;
        public static final int TYPE_3 = 3;

        private int itemType;
        private int rowid;
        private String 生产单号;
        private String 客诉单号;
        private String 制单人员;
        private String 制单日期;
        private String 投诉日期;
        private String 投诉问题;
        private String 责任人员;
        private String 责任部门;
        private String 原因分析;
        private String 改善措施;
        private String 预防对策;
        private String 效果确认;
        private String 结案时间;
        private String 项目结案;
        private String 客户名称;
        private String 产品名称;
        private String 订单组别;
        private String 制作组别;
        private String 下单日期;
        private String 交货日期;
        private String 实际交货日期;
        private String 业务审核;
        private String 业务确认;
        private String 业务审核时间;
        private String 业务确认时间;
        private String 责任审核;
        private String 责任审核时间;
        private String 责任确认;
        private String 责任确认时间;
        private String 否定结案;
        private String 结案否定时间;
        private String 客诉类型;
        private String 客诉要求;

        private int id;
        private String 变更单号;
        private String 变更日期;
        private String 变更人;
        private String 变更内容;
        private String 变更描述;
        private double 签阅情况;
        private int 附件数量;
        private String 确认人;
        private String 确认日期;
        private String 项目确认;
        private String 项目确认时间;
        private Object baseisdelete;
        private Object basecreatetime;
        private Object basemodifytime;
        private Object basecreatorid;
        private Object basemodifierid;
        private Object baseversion;


        private String 文件编码;
        private String 文件名称;
        private String 上传日期;
        private String 上传人员;
        private String 最后变更人;
        private String 最后变更日期;
        private Integer 变更次数;
        private String 文件类型;
        private String 存放位置;
        private String 文件分类;


        private boolean Expanded;

        public String get文件编码() {
            return 文件编码;
        }

        public void set文件编码(String 文件编码) {
            this.文件编码 = 文件编码;
        }

        public String get文件名称() {
            return 文件名称;
        }

        public void set文件名称(String 文件名称) {
            this.文件名称 = 文件名称;
        }

        public String get上传日期() {
            return 上传日期;
        }

        public void set上传日期(String 上传日期) {
            this.上传日期 = 上传日期;
        }

        public String get上传人员() {
            return 上传人员;
        }

        public void set上传人员(String 上传人员) {
            this.上传人员 = 上传人员;
        }

        public String get最后变更人() {
            return 最后变更人;
        }

        public void set最后变更人(String 最后变更人) {
            this.最后变更人 = 最后变更人;
        }

        public String get最后变更日期() {
            return 最后变更日期;
        }

        public void set最后变更日期(String 最后变更日期) {
            this.最后变更日期 = 最后变更日期;
        }

        public Integer get变更次数() {
            return 变更次数;
        }

        public void set变更次数(Integer 变更次数) {
            this.变更次数 = 变更次数;
        }

        public String get文件类型() {
            return 文件类型;
        }

        public void set文件类型(String 文件类型) {
            this.文件类型 = 文件类型;
        }

        public String get存放位置() {
            return 存放位置;
        }

        public void set存放位置(String 存放位置) {
            this.存放位置 = 存放位置;
        }

        public String get文件分类() {
            return 文件分类;
        }

        public void set文件分类(String 文件分类) {
            this.文件分类 = 文件分类;
        }

        public void setItemType(int itemType) {
            this.itemType = itemType;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String get变更单号() {
            return 变更单号;
        }

        public void set变更单号(String 变更单号) {
            this.变更单号 = 变更单号;
        }

        public String get变更日期() {
            return 变更日期;
        }

        public void set变更日期(String 变更日期) {
            this.变更日期 = 变更日期;
        }

        public String get变更人() {
            return 变更人;
        }

        public void set变更人(String 变更人) {
            this.变更人 = 变更人;
        }

        public String get变更内容() {
            return 变更内容;
        }

        public void set变更内容(String 变更内容) {
            this.变更内容 = 变更内容;
        }

        public String get变更描述() {
            return 变更描述;
        }

        public void set变更描述(String 变更描述) {
            this.变更描述 = 变更描述;
        }

        public double get签阅情况() {
            return 签阅情况;
        }

        public void set签阅情况(double 签阅情况) {
            this.签阅情况 = 签阅情况;
        }

        public int get附件数量() {
            return 附件数量;
        }

        public void set附件数量(int 附件数量) {
            this.附件数量 = 附件数量;
        }

        public String get确认人() {
            return 确认人;
        }

        public void set确认人(String 确认人) {
            this.确认人 = 确认人;
        }

        public String get确认日期() {
            return 确认日期;
        }

        public void set确认日期(String 确认日期) {
            this.确认日期 = 确认日期;
        }

        public String get项目确认() {
            return 项目确认;
        }

        public void set项目确认(String 项目确认) {
            this.项目确认 = 项目确认;
        }

        public String get项目确认时间() {
            return 项目确认时间;
        }

        public void set项目确认时间(String 项目确认时间) {
            this.项目确认时间 = 项目确认时间;
        }

        public Object getBaseisdelete() {
            return baseisdelete;
        }

        public void setBaseisdelete(Object baseisdelete) {
            this.baseisdelete = baseisdelete;
        }

        public Object getBasecreatetime() {
            return basecreatetime;
        }

        public void setBasecreatetime(Object basecreatetime) {
            this.basecreatetime = basecreatetime;
        }

        public Object getBasemodifytime() {
            return basemodifytime;
        }

        public void setBasemodifytime(Object basemodifytime) {
            this.basemodifytime = basemodifytime;
        }

        public Object getBasecreatorid() {
            return basecreatorid;
        }

        public void setBasecreatorid(Object basecreatorid) {
            this.basecreatorid = basecreatorid;
        }

        public Object getBasemodifierid() {
            return basemodifierid;
        }

        public void setBasemodifierid(Object basemodifierid) {
            this.basemodifierid = basemodifierid;
        }

        public Object getBaseversion() {
            return baseversion;
        }

        public void setBaseversion(Object baseversion) {
            this.baseversion = baseversion;
        }

        public boolean isExpanded() {
            return Expanded;
        }

        public void setExpanded(boolean expanded) {
            Expanded = expanded;
        }

        public int getRowid() {
            return rowid;
        }

        public void setRowid(int rowid) {
            this.rowid = rowid;
        }

        public String get生产单号() {
            return 生产单号;
        }

        public void set生产单号(String 生产单号) {
            this.生产单号 = 生产单号;
        }

        public String get客诉单号() {
            return 客诉单号;
        }

        public void set客诉单号(String 客诉单号) {
            this.客诉单号 = 客诉单号;
        }

        public String get制单人员() {
            return 制单人员;
        }

        public void set制单人员(String 制单人员) {
            this.制单人员 = 制单人员;
        }

        public String get制单日期() {
            return 制单日期;
        }

        public void set制单日期(String 制单日期) {
            this.制单日期 = 制单日期;
        }

        public String get投诉日期() {
            return 投诉日期;
        }

        public void set投诉日期(String 投诉日期) {
            this.投诉日期 = 投诉日期;
        }

        public String get投诉问题() {
            return 投诉问题;
        }

        public void set投诉问题(String 投诉问题) {
            this.投诉问题 = 投诉问题;
        }

        public String get责任人员() {
            return 责任人员;
        }

        public void set责任人员(String 责任人员) {
            this.责任人员 = 责任人员;
        }

        public String get责任部门() {
            return 责任部门;
        }

        public void set责任部门(String 责任部门) {
            this.责任部门 = 责任部门;
        }

        public String get原因分析() {
            return 原因分析;
        }

        public void set原因分析(String 原因分析) {
            this.原因分析 = 原因分析;
        }

        public String get改善措施() {
            return 改善措施;
        }

        public void set改善措施(String 改善措施) {
            this.改善措施 = 改善措施;
        }

        public String get预防对策() {
            return 预防对策;
        }

        public void set预防对策(String 预防对策) {
            this.预防对策 = 预防对策;
        }

        public String get效果确认() {
            return 效果确认;
        }

        public void set效果确认(String 效果确认) {
            this.效果确认 = 效果确认;
        }

        public String get结案时间() {
            return 结案时间;
        }

        public void set结案时间(String 结案时间) {
            this.结案时间 = 结案时间;
        }

        public String get项目结案() {
            return 项目结案;
        }

        public void set项目结案(String 项目结案) {
            this.项目结案 = 项目结案;
        }

        public String get客户名称() {
            return 客户名称;
        }

        public void set客户名称(String 客户名称) {
            this.客户名称 = 客户名称;
        }

        public String get产品名称() {
            return 产品名称;
        }

        public void set产品名称(String 产品名称) {
            this.产品名称 = 产品名称;
        }

        public String get订单组别() {
            return 订单组别;
        }

        public void set订单组别(String 订单组别) {
            this.订单组别 = 订单组别;
        }

        public String get制作组别() {
            return 制作组别;
        }

        public void set制作组别(String 制作组别) {
            this.制作组别 = 制作组别;
        }

        public String get下单日期() {
            return 下单日期;
        }

        public void set下单日期(String 下单日期) {
            this.下单日期 = 下单日期;
        }

        public String get交货日期() {
            return 交货日期;
        }

        public void set交货日期(String 交货日期) {
            this.交货日期 = 交货日期;
        }

        public String get实际交货日期() {
            return 实际交货日期;
        }

        public void set实际交货日期(String 实际交货日期) {
            this.实际交货日期 = 实际交货日期;
        }

        public String get业务审核() {
            return 业务审核;
        }

        public void set业务审核(String 业务审核) {
            this.业务审核 = 业务审核;
        }

        public String get业务确认() {
            return 业务确认;
        }

        public void set业务确认(String 业务确认) {
            this.业务确认 = 业务确认;
        }

        public String get业务审核时间() {
            return 业务审核时间;
        }

        public void set业务审核时间(String 业务审核时间) {
            this.业务审核时间 = 业务审核时间;
        }

        public String get业务确认时间() {
            return 业务确认时间;
        }

        public void set业务确认时间(String 业务确认时间) {
            this.业务确认时间 = 业务确认时间;
        }

        public String get责任审核() {
            return 责任审核;
        }

        public void set责任审核(String 责任审核) {
            this.责任审核 = 责任审核;
        }

        public String get责任审核时间() {
            return 责任审核时间;
        }

        public void set责任审核时间(String 责任审核时间) {
            this.责任审核时间 = 责任审核时间;
        }

        public String get责任确认() {
            return 责任确认;
        }

        public void set责任确认(String 责任确认) {
            this.责任确认 = 责任确认;
        }

        public String get责任确认时间() {
            return 责任确认时间;
        }

        public void set责任确认时间(String 责任确认时间) {
            this.责任确认时间 = 责任确认时间;
        }

        public String get否定结案() {
            return 否定结案;
        }

        public void set否定结案(String 否定结案) {
            this.否定结案 = 否定结案;
        }

        public String get结案否定时间() {
            return 结案否定时间;
        }

        public void set结案否定时间(String 结案否定时间) {
            this.结案否定时间 = 结案否定时间;
        }

        public String get客诉类型() {
            return 客诉类型;
        }

        public void set客诉类型(String 客诉类型) {
            this.客诉类型 = 客诉类型;
        }

        public String get客诉要求() {
            return 客诉要求;
        }

        public void set客诉要求(String 客诉要求) {
            this.客诉要求 = 客诉要求;
        }

        @Override
        public int getItemType() {
            return itemType;
        }
    }
}
