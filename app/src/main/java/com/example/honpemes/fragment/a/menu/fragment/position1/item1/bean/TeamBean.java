package com.example.honpemes.fragment.a.menu.fragment.position1.item1.bean;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * @author: asus
 * @date: 2022/11/10
 * @Description:
 */
public class TeamBean implements Serializable {

    @SerializedName("Total")
    private Integer total;
    @SerializedName("Data")
    private List<DataBean> data;
    @SerializedName("Tag")
    private Integer tag;
    @SerializedName("Message")
    private String message;
    @SerializedName("Description")
    private Object description;

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public Integer getTag() {
        return tag;
    }

    public void setTag(Integer tag) {
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
        private String 组别代码;
        private String 组别名称;
        private String 类别;

        public String get组别代码() {
            return 组别代码;
        }

        public void set组别代码(String 组别代码) {
            this.组别代码 = 组别代码;
        }

        public String get组别名称() {
            return 组别名称;
        }

        public void set组别名称(String 组别名称) {
            this.组别名称 = 组别名称;
        }

        public String get类别() {
            return 类别;
        }

        public void set类别(String 类别) {
            this.类别 = 类别;
        }
    }
}
