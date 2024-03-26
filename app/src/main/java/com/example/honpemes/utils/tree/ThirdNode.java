package com.example.honpemes.utils.tree;

import com.chad.library.adapter.base.entity.node.BaseNode;
import com.example.honpemes.fragment.a.menu.fragment.position6.item1.bean.FileManagerBean;

import org.jetbrains.annotations.Nullable;

import java.util.List;

public class ThirdNode extends BaseNode {
   private FileManagerBean.DataBean.DocumentManagementBean documentManagement;
   private int _id;

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public FileManagerBean.DataBean.DocumentManagementBean getDocumentManagement() {
        return documentManagement;
    }

    public void setDocumentManagement(FileManagerBean.DataBean.DocumentManagementBean documentManagement) {
        this.documentManagement = documentManagement;
    }

    @Nullable
    @Override
    public List<BaseNode> getChildNode() {
        return null;
    }
}
