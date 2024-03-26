package com.example.honpemes.fragment.a.menu.fragment.position2.item1.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.example.honpemes.R;
import com.example.honpemes.fragment.a.menu.fragment.position2.item1.bean.OutSendOrderBean;

import org.jetbrains.annotations.NotNull;

/**
 * 作者：asus on 2023/12/21 15:37
 * 注释：
 */
public class OutSendOrderAdapter extends BaseQuickAdapter<OutSendOrderBean.DataBean, BaseViewHolder> {


    public OutSendOrderAdapter() {
        super(R.layout.item_out_order_send);
    }

    @Override
    protected void convert(@NotNull BaseViewHolder holder, OutSendOrderBean.DataBean bean) {
        holder.setText(R.id.item_1,bean.生产单号)
                .setText(R.id.item_2,bean.数量)
                .setText(R.id.item_3,bean.外发项目.trim())
                .setText(R.id.item_4,bean.外发时间)
                .setText(R.id.item_5,bean.外协供应商)
                .setText(R.id.item_6,bean.备注)
                .setGone(R.id.ll_out_send_order,false);
    }
}
