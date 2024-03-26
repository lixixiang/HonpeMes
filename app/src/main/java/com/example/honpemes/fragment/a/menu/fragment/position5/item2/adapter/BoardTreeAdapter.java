package com.example.honpemes.fragment.a.menu.fragment.position5.item2.adapter;

import com.chad.library.adapter.base.BaseNodeAdapter;
import com.chad.library.adapter.base.entity.node.BaseNode;
import com.example.honpemes.fragment.a.menu.fragment.position5.item2.adapter.provider.FirstProvider;
import com.example.honpemes.fragment.a.menu.fragment.position5.item2.adapter.provider.SecondProvider;
import com.example.honpemes.fragment.a.menu.fragment.position5.item2.bean.BoardBean;

import org.jetbrains.annotations.NotNull;

import java.util.List;

/**
 * 库存类型item
 */
public class BoardTreeAdapter extends BaseNodeAdapter {

    public BoardTreeAdapter() {
        addNodeProvider(new FirstProvider());
        addNodeProvider(new SecondProvider());
    }

    @Override
    protected int getItemType(@NotNull List<? extends BaseNode> list, int i) {
        BaseNode node = list.get(i);
        if (node instanceof BoardBean) {
            return 1;
        } else if (node instanceof BoardBean.SecondBoarBean) {
            return 2;
        }
        return -1;
    }
}
