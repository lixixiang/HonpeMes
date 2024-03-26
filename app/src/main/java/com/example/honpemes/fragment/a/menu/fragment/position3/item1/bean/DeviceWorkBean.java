package com.example.honpemes.fragment.a.menu.fragment.position3.item1.bean;

import com.example.honpemes.fragment.a.menu.fragment.position1.item4.bean.OrderStatBean;
import com.example.honpemes.fragment.a.menu.fragment.position3.item1.detail.bean.DeviceInDetailBean;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * 作者：asus on 2023/12/23 14:48
 * 注释：
 */
public class DeviceWorkBean {
    /**
     * total
     */
    @SerializedName("Total")
    public Integer total;
    /**
     * data
     */
    @SerializedName("Data")
    public List<DeviceInBean> data;

    @SerializedName("dataDetail")
    public List<DeviceInDetailBean> dataDetail;

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
}
