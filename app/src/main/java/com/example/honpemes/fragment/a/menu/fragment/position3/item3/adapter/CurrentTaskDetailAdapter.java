package com.example.honpemes.fragment.a.menu.fragment.position3.item3.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.example.honpemes.R;

import org.jetbrains.annotations.NotNull;

/**
 * 作者：asus on 2023/12/25 15:46
 * 注释：
 */
public class CurrentTaskDetailAdapter extends BaseQuickAdapter<String, BaseViewHolder> {

    public CurrentTaskDetailAdapter() {
        super(R.layout.item_single_text);
    }

    @Override
    protected void convert(@NotNull BaseViewHolder holder, String s) {
        holder.setText(R.id.tv_item_title, s)
                .setTextColorRes(R.id.tv_item_title, R.color.green_l);
    }
}
