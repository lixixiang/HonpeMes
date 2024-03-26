package com.example.honpemes.fragment.a.menu.fragment.position11.item1.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.example.honpemes.R;
import com.example.honpemes.fragment.a.menu.fragment.position11.item1.bean.ItemFormStatusBean;
import com.example.honpemes.utils.StringUtil;
import com.flyco.roundview.RoundTextView;

import org.jetbrains.annotations.NotNull;

import static com.example.honpemes.api.DataClass.COLORS;


/**
 * Created by Lixixiang on 2023/2/8 14:30
 */
public class ItemOrderFormAdapter extends BaseQuickAdapter<ItemFormStatusBean, BaseViewHolder> {

    public ItemOrderFormAdapter() {
        super(R.layout.item_form_rate);
    }

    @Override
    protected void convert(@NotNull BaseViewHolder holder, ItemFormStatusBean bean) {
        if (Integer.parseInt(bean.getSum()) != 0) {
            double sRate = (Double.parseDouble(bean.getNum()) / Double.parseDouble(bean.getSum())) * 100;
            holder.setText(R.id.tv_rate, StringUtil.formatDouble(sRate) + "%");
        }
        RoundTextView tvId = holder.getView(R.id.tv_id);
        holder.setText(R.id.tv_id, bean.get_id())
                .setText(R.id.tv_status, bean.getName())
                .setText(R.id.tv_num, bean.getNum() + "台");
        tvId.setText(bean.get_id());
        tvId.getDelegate().setBackgroundColor(COLORS[holder.getAdapterPosition()]);
    }


}
