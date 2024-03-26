package com.example.honpemes.fragment.a.menu.fragment.position11.item2.bean;

import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Lixixiang on 2023/2/20 10:08
 */
public class MeterManagerBean {

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
        private long id;
        private String elename;
        private String eleshortname;
        private int magnification;
        private String eleremarks;
        private int baseisdelete;
        private String basecreatetime;
        private String basemodifytime;
        private long basecreatorid;
        private long basemodifierid;
        private String baseversion;
        private String readinstruction;
        private String usingtime;
        private String usingenery;
        private String currentenergy;
        private String remarks;
        private int itemType;
        private List<String> headList;
        private double userRate;
        private ArrayList<DataBean> dataBeanArrayList;
        private float add_up; //累计电量
        private String time;
        private boolean isOpen;//是否打开

        public boolean isOpen() {
            return isOpen;
        }

        public void setOpen(boolean open) {
            isOpen = open;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }

        public float getAdd_up() {
            return add_up;
        }

        public void setAdd_up(float add_up) {
            this.add_up = add_up;
        }


        public double getUserRate() {
            return userRate;
        }

        public void setUserRate(double userRate) {
            this.userRate = userRate;
        }

        public List<String> getHeadList() {
            return headList;
        }

        public void setHeadList(List<String> headList) {
            this.headList = headList;
        }


        public ArrayList<DataBean> getDataBeanArrayList() {
            return dataBeanArrayList;
        }

        public void setDataBeanArrayList(ArrayList<DataBean> dataBeanArrayList) {
            this.dataBeanArrayList = dataBeanArrayList;
        }

        public void setItemType(int itemType) {
            this.itemType = itemType;
        }

        public String getUsingtime() {
            return usingtime;
        }

        public void setUsingtime(String usingtime) {
            this.usingtime = usingtime;
        }

        public String getUsingenery() {
            return usingenery;
        }

        public void setUsingenery(String usingenery) {
            this.usingenery = usingenery;
        }

        public String getCurrentenergy() {
            return currentenergy;
        }

        public void setCurrentenergy(String currentenergy) {
            this.currentenergy = currentenergy;
        }

        public String getRemarks() {
            return remarks;
        }

        public void setRemarks(String remarks) {
            this.remarks = remarks;
        }

        public long getId() {
            return id;
        }

        public void setId(long id) {
            this.id = id;
        }

        public String getElename() {
            return elename;
        }

        public void setElename(String elename) {
            this.elename = elename;
        }

        public String getEleshortname() {
            return eleshortname;
        }

        public void setEleshortname(String eleshortname) {
            this.eleshortname = eleshortname;
        }

        public int getMagnification() {
            return magnification;
        }

        public void setMagnification(int magnification) {
            this.magnification = magnification;
        }

        public String getEleremarks() {
            return eleremarks;
        }

        public void setEleremarks(String eleremarks) {
            this.eleremarks = eleremarks;
        }

        public int getBaseisdelete() {
            return baseisdelete;
        }

        public void setBaseisdelete(int baseisdelete) {
            this.baseisdelete = baseisdelete;
        }

        public String getBasecreatetime() {
            return basecreatetime;
        }

        public void setBasecreatetime(String basecreatetime) {
            this.basecreatetime = basecreatetime;
        }

        public String getBasemodifytime() {
            return basemodifytime;
        }

        public void setBasemodifytime(String basemodifytime) {
            this.basemodifytime = basemodifytime;
        }

        public long getBasecreatorid() {
            return basecreatorid;
        }

        public void setBasecreatorid(long basecreatorid) {
            this.basecreatorid = basecreatorid;
        }

        public long getBasemodifierid() {
            return basemodifierid;
        }

        public void setBasemodifierid(long basemodifierid) {
            this.basemodifierid = basemodifierid;
        }

        public String getBaseversion() {
            return baseversion;
        }

        public void setBaseversion(String baseversion) {
            this.baseversion = baseversion;
        }

        public String getReadinstruction() {
            return readinstruction;
        }

        public void setReadinstruction(String readinstruction) {
            this.readinstruction = readinstruction;
        }

        @Override
        public int getItemType() {
            return itemType;
        }
    }
}
