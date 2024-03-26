package com.example.honpemes.bean;

import com.chad.library.adapter.base.entity.MultiItemEntity;

import java.io.Serializable;
import java.util.List;

/**
 * @author: asus
 * @date: 2022/9/24
 * @Description:
 */
public class MenuBean implements Serializable, MultiItemEntity {
    public static final int TYPE_1 = 1;
    public static final int TYPE_2 = 2;

    private String id;
    private String title;
    private String content;
    private String ico;
    private String sort;
    private int icons;
    private String num = "0";
    private boolean isEnable = false; //是否可以使用
    private boolean select = false;
    private boolean isCheck;
    private List<MenuBean> detail;
    public static final int SINGLE_TEXT = 1;
    public static final int TEXT_IMG = 2;
    private int itemType;
    private int spanSize;

    public boolean isCheck() {
        return isCheck;
    }

    public void setCheck(boolean check) {
        isCheck = check;
    }

    public int getSpanSize() {
        return spanSize;
    }

    public void setSpanSize(int spanSize) {
        this.spanSize = spanSize;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getIcons() {
        return icons;
    }

    public void setIcons(int icons) {
        this.icons = icons;
    }

    public void setItemType(int itemType) {
        this.itemType = itemType;
    }

    public boolean isEnable() {
        return isEnable;
    }

    public void setEnable(boolean enable) {
        isEnable = enable;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIco() {
        return ico;
    }

    public void setIco(String ico) {
        this.ico = ico;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public boolean isSelect() {
        return select;
    }

    public void setSelect(boolean select) {
        this.select = select;
    }

    public List<MenuBean> getDetail() {
        return detail;
    }

    public void setDetail(List<MenuBean> detail) {
        this.detail = detail;
    }

    @Override
    public int getItemType() {
        return itemType;
    }
}
