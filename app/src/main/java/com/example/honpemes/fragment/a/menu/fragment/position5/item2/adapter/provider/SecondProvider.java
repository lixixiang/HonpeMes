package com.example.honpemes.fragment.a.menu.fragment.position5.item2.adapter.provider;

import com.chad.library.adapter.base.entity.node.BaseNode;
import com.chad.library.adapter.base.provider.BaseNodeProvider;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.example.honpemes.R;
import com.example.honpemes.fragment.a.menu.fragment.position5.item2.bean.BoardBean;

import org.jetbrains.annotations.NotNull;

public class SecondProvider extends BaseNodeProvider {


    @Override
    public int getItemViewType() {
        return 2;
    }

    @Override
    public int getLayoutId() {
        return R.layout.item_enter_board2;
    }

    @Override
    public void convert(@NotNull BaseViewHolder holder, BaseNode baseNode) {
        BoardBean.SecondBoarBean bean = (BoardBean.SecondBoarBean) baseNode;
        holder.setText(R.id.tv_item1,"产品："+bean.produceName)
                .setText(R.id.tv_item2,"入库数："+bean.produceNum)
                .setText(R.id.tv_item3,"型号："+bean.produceType)
                .setText(R.id.tv_item4,"单位："+bean.unit);

    }
}
