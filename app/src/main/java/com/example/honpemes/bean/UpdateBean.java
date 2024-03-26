package com.example.honpemes.bean;

import com.google.gson.annotations.SerializedName;

/**
 * 作者：asus on 2023/10/23 10:17
 * 注释：
 */
public class UpdateBean {

    /**
     * code
     */
    @SerializedName("code")
    public Integer code;
    /**
     * info
     */
    @SerializedName("info")
    public String info;
    /**
     * data
     */
    @SerializedName("data")
    public DataBean data;

    public static class DataBean {
        /**
         * ver
         */
        @SerializedName("ver")
        public String ver;
        /**
         * description
         */
        @SerializedName("description")
        public String description;
    }
}
