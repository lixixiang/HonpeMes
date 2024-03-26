package com.example.honpemes.fragment.a.menu.fragment.position2.item3.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.example.honpemes.R;
import com.example.honpemes.fragment.a.menu.fragment.position2.item3.bean.ProduceProgressBean;
import com.flyco.roundview.RoundTextView;

import org.jetbrains.annotations.NotNull;

/**
 * 作者：asus on 2023/12/9 17:22
 * 注释：生产进度
 */
public class ProduceDetailAdapter extends BaseQuickAdapter<ProduceProgressBean.DataBean, BaseViewHolder> {


    public ProduceDetailAdapter() {
        super(R.layout.item_produce_detail);
    }

    @Override
    protected void convert(@NotNull BaseViewHolder holder, ProduceProgressBean.DataBean bean) {
        holder.setText(R.id.tv_item_id, holder.getAdapterPosition()+1 + "")
        .setText(R.id.tv_item_event,bean.工序名称);
        RoundTextView tvItemId =holder.getView(R.id.tv_item_id);

        if (bean.工序完成) {
            tvItemId.getDelegate().setBackgroundColor(getContext().getResources().getColor(R.color.green_l));
            holder.setTextColorRes(R.id.tv_item_event,R.color.green_l)
                    .setTextColorRes(R.id.tv_item_content,R.color.green_l)
                    .setText(R.id.tv_item_content,"登记人："+bean.完成登记人+" "+bean.实际完成日期);

        } else {
            tvItemId.getDelegate().setBackgroundColor(getContext().getResources().getColor(R.color.grey_l));
            holder.setTextColorRes(R.id.tv_item_event,R.color.grey_l)
                    .setTextColorRes(R.id.tv_item_content,R.color.grey_l)
                    .setText(R.id.tv_item_content,"");
        }

    }
}
