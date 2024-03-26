package com.example.honpemes.fragment.a.menu.fragment.position6.item1.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.example.honpemes.R;
import com.example.honpemes.bean.HomeBean;

import org.jetbrains.annotations.NotNull;

/**
 * 作者：asus on 2024/1/23 10:57
 * 注释：
 */
public class HeadHorizonAdapter extends BaseQuickAdapter<HomeBean,BaseViewHolder> {


    public HeadHorizonAdapter() {
        super(R.layout.item_head_title);
    }

    @Override
    protected void convert(@NotNull BaseViewHolder holder, HomeBean bean) {
        holder.setText(R.id.head_title, bean.getTitle());
    }
}
