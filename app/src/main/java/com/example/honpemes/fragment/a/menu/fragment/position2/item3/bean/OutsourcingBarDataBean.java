package com.example.honpemes.fragment.a.menu.fragment.position2.item3.bean;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * 作者：asus on 2023/12/7 09:09
 * 注释：
 */
public class OutsourcingBarDataBean {

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
    public Object message;
    /**
     * description
     */
    @SerializedName("Description")
    public String description;

    public static class DataBean {
        /**
         * times
         */
        @SerializedName("times")
        public String times;
        /**
         * counts
         */
        @SerializedName("Counts")
        public Integer counts;
        /**
         * outgoing
         */
        @SerializedName("Outgoing")
        public Double outgoing;
    }
}
