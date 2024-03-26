package com.example.honpemes.fragment.a.menu.fragment.position3.item5.detail.bean;

import com.chad.library.adapter.base.entity.node.BaseNode;

import org.jetbrains.annotations.Nullable;

import java.util.List;

/**
 * Created by Lixixiang on 2023/2/15 9:07
 */
public class KeepDetailBean extends BaseNode {

   private String content;
   private String name;
   private String status;
   private String date;
   private int id;

    public KeepDetailBean() {
    }

    public KeepDetailBean(String content, String name, String status, String date) {
        this.content = content;
        this.name = name;
        this.status = status;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Nullable
    @Override
    public List<BaseNode> getChildNode() {
        return null;
    }
}
