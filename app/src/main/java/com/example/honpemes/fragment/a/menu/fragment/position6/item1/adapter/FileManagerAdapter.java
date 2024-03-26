package com.example.honpemes.fragment.a.menu.fragment.position6.item1.adapter;

import com.chad.library.adapter.base.BaseNodeAdapter;
import com.chad.library.adapter.base.entity.node.BaseNode;
import com.example.honpemes.fragment.a.menu.fragment.position6.item1.provider.FirstProvider;
import com.example.honpemes.fragment.a.menu.fragment.position6.item1.provider.ThirdProvider;
import com.example.honpemes.utils.tree.FirstNode;
import com.example.honpemes.utils.tree.ThirdNode;

import org.jetbrains.annotations.NotNull;

import java.util.List;

/**
 * Created by Lixixiang on 2023/2/6 9:43
 * 文档适配器
 */
public class FileManagerAdapter extends BaseNodeAdapter {

    public FileManagerAdapter() {
        addNodeProvider(new FirstProvider());
        addNodeProvider(new ThirdProvider());

    }

    @Override
    protected int getItemType(@NotNull List<? extends BaseNode> data, int position) {
        BaseNode node = data.get(position);
        if (node instanceof FirstNode) {
            return 1;
        } else if (node instanceof ThirdNode) {
            return 2;
        }
        return -1;
    }

    public static final int EXPAND_COLLAPSE_PAYLOAD = 110;

}




























