package com.example.honpemes.widget.pop.bean;

import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.example.honpemes.bean.MenuBean;

import java.io.Serializable;
import java.util.List;

/**
 * 作者：asus on 2023/12/26 15:35
 * 注释：
 */
public class SelectorBean implements Serializable, MultiItemEntity {
    public static final int TYPE_1 = 1;
    public static final int TYPE_2 = 2;
    public static final int TYPE_3 = 3;
    public static final int TYPE_4 = 4;
    public static final int TYPE_5 = 5;
    public static final int TYPE_6 = 6;

    private int itemType;
    private int spanSize;

    private String title;
    private String content;//内容
    private String content2;//内容2
    private String startTime;
    private String endTime;

    private String hint;
    private String hint2;
    private List<MenuBean> menuBeanList;
    private List<String> stringList;

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public List<String> getStringList() {
        return stringList;
    }

    public void setStringList(List<String> stringList) {
        this.stringList = stringList;
    }

    public List<MenuBean> getMenuBeanList() {
        return menuBeanList;
    }

    public void setMenuBeanList(List<MenuBean> menuBeanList) {
        this.menuBeanList = menuBeanList;
    }

    public String getHint2() {
        return hint2;
    }

    public void setHint2(String hint2) {
        this.hint2 = hint2;
    }

    public String getContent2() {
        return content2;
    }

    public void setContent2(String content2) {
        this.content2 = content2;
    }

    public String getHint() {
        return hint;
    }

    public void setHint(String hint) {
        this.hint = hint;
    }

    public void setItemType(int itemType) {
        this.itemType = itemType;
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public int getItemType() {
        return itemType;
    }
}
