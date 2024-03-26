package com.example.honpemes.fragment.a.menu.fragment.position1.item3.bean;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * @author: asus
 * @date: 2022/11/16
 * @Description:
 */
public class ChangeOrderBean implements Serializable {
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

    public static class DataBean {
        private int id;
        private String 生产单号;
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

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String get生产单号() {
            return 生产单号;
        }

        public void set生产单号(String 生产单号) {
            this.生产单号 = 生产单号;
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

    }
}
