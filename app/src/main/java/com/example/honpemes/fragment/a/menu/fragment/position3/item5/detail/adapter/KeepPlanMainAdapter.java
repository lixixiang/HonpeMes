package com.example.honpemes.fragment.a.menu.fragment.position3.item5.detail.adapter;

import com.chad.library.adapter.base.BaseNodeAdapter;
import com.chad.library.adapter.base.entity.node.BaseNode;
import com.example.honpemes.fragment.a.menu.fragment.position3.item5.detail.bean.KeepDetailBean;
import com.example.honpemes.fragment.a.menu.fragment.position3.item5.detail.bean.KeepPlanBean;

import org.jetbrains.annotations.NotNull;

import java.util.List;

/**
 * Created by Lixixiang on 2023/2/15 8:44
 */
public class KeepPlanMainAdapter extends BaseNodeAdapter {


    public KeepPlanMainAdapter() {
        addNodeProvider(new KeepPlanAdapter());
        addNodeProvider(new KeepPlanContentAdapter());
    }

    @Override
    protected int getItemType(@NotNull List<? extends BaseNode> list, int i) {
        BaseNode node = list.get(i);
        if (node instanceof KeepPlanBean) {
            return 1;
        }else if (node instanceof KeepDetailBean){
            return 2;
        }
        return 0;
    }
}
