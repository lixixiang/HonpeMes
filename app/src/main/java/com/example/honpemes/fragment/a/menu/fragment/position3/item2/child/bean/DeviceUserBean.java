package com.example.honpemes.fragment.a.menu.fragment.position3.item2.child.bean;

import com.chad.library.adapter.base.entity.MultiItemEntity;

import java.util.List;

/**
 * Created by Lixixiang on 2023/3/16 10:57
 */
public class DeviceUserBean implements MultiItemEntity {

    private int ItemType;
    private String title;
    private String des;
    private String userRate;
    private int totalCount;//某个的次数
    private int sum; //整个的次数
    private String LineRate;
    private int[] colors; //双色 一种是已合计，总合计数
    private String accountTime;//累计时间
    private int spanSize;
    private List<DeviceStatusBean.DataBean> AllList;
    private List<DeviceStatusBean.DataBean> SaveList;
    private List<DeviceStatusBean.DataBean> SaveList2;
    private List<DeviceStatusBean.DataBean> SaveList3;
    private List<DeviceStatusBean.DataBean> SaveList4;

    public List<DeviceStatusBean.DataBean> getAllList() {
        return AllList;
    }

    public void setAllList(List<DeviceStatusBean.DataBean> allList) {
        AllList = allList;
    }

    public List<DeviceStatusBean.DataBean> getSaveList() {
        return SaveList;
    }

    public void setSaveList(List<DeviceStatusBean.DataBean> saveList) {
        SaveList = saveList;
    }

    public List<DeviceStatusBean.DataBean> getSaveList2() {
        return SaveList2;
    }

    public void setSaveList2(List<DeviceStatusBean.DataBean> saveList2) {
        SaveList2 = saveList2;
    }

    public List<DeviceStatusBean.DataBean> getSaveList3() {
        return SaveList3;
    }

    public void setSaveList3(List<DeviceStatusBean.DataBean> saveList3) {
        SaveList3 = saveList3;
    }

    public List<DeviceStatusBean.DataBean> getSaveList4() {
        return SaveList4;
    }

    public void setSaveList4(List<DeviceStatusBean.DataBean> saveList4) {
        SaveList4 = saveList4;
    }

    public int getSpanSize() {
        return spanSize;
    }

    public void setSpanSize(int spanSize) {
        this.spanSize = spanSize;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

    public void setItemType(int itemType) {
        ItemType = itemType;
    }

    public String getAccountTime() {
        return accountTime;
    }

    public void setAccountTime(String accountTime) {
        this.accountTime = accountTime;
    }

    public int[] getColors() {
        return colors;
    }

    public void setColors(int[] colors) {
        this.colors = colors;
    }

    public int getSum() {
        return sum;
    }

    public void setSum(int sum) {
        this.sum = sum;
    }

    public String getUserRate() {
        return userRate;
    }

    public void setUserRate(String userRate) {
        this.userRate = userRate;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public String getLineRate() {
        return LineRate;
    }

    public void setLineRate(String lineRate) {
        LineRate = lineRate;
    }

    @Override
    public int getItemType() {
        return ItemType;
    }
}
