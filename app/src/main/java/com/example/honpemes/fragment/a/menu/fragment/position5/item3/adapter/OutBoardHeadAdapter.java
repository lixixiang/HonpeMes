package com.example.honpemes.fragment.a.menu.fragment.position5.item3.adapter;

import android.graphics.Typeface;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.example.honpemes.R;
import com.example.honpemes.fragment.a.menu.fragment.position5.item3.bean.OutBoardBean;

import org.jetbrains.annotations.NotNull;

/**
 * 入库产品适配
 */
public class OutBoardHeadAdapter extends BaseQuickAdapter<OutBoardBean, BaseViewHolder> {


    public OutBoardHeadAdapter() {
        super(R.layout.item_out_board);
    }

    @Override
    protected void convert(@NotNull BaseViewHolder holder, OutBoardBean bean) {
        TextView tvItem1 = holder.getView(R.id.tv_item1);
        TextView tvItem2 = holder.getView(R.id.tv_item2);
        TextView tvItem3 = holder.getView(R.id.tv_item3);
        TextView tvItem4 = holder.getView(R.id.tv_item4);
        TextView tvItem5 = holder.getView(R.id.tv_item5);
        TextView tvItem6 = holder.getView(R.id.tv_item6);

        tvItem1.setTypeface(Typeface.DEFAULT_BOLD);
        tvItem2.setTypeface(Typeface.DEFAULT_BOLD);
        tvItem3.setTypeface(Typeface.DEFAULT_BOLD);
        tvItem4.setTypeface(Typeface.DEFAULT_BOLD);
        tvItem5.setTypeface(Typeface.DEFAULT_BOLD);
        tvItem6.setTypeface(Typeface.DEFAULT_BOLD);

        holder.setText(R.id.tv_item1,bean.date)
                .setText(R.id.tv_item2,bean.deliver)
                .setText(R.id.tv_item3,bean.num)
                .setText(R.id.tv_item4,bean.product)
                .setText(R.id.tv_item5,bean._id)
                .setText(R.id.tv_item6,bean.store);
    }
}
