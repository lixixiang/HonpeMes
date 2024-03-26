package com.example.honpemes.utils.tree;

import com.chad.library.adapter.base.entity.node.BaseExpandNode;
import com.chad.library.adapter.base.entity.node.BaseNode;

import org.jetbrains.annotations.Nullable;

import java.util.List;

/**
 * 作者：asus on 2024/1/23 14:45
 * 注释：
 */
public class FirstNode extends BaseExpandNode {

    private List<BaseNode> childNode;
    private String title;
    private String create;
    private String date;
    private String remark;

    public FirstNode() {
        setExpanded(true);
    }

    public void setChildNode(List<BaseNode> childNode) {
        this.childNode = childNode;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCreate() {
        return create;
    }

    public void setCreate(String create) {
        this.create = create;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Nullable
    @Override
    public List<BaseNode> getChildNode() {
        return childNode;
    }
}
