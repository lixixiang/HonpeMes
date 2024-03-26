package com.example.honpemes.fragment.a.menu.fragment.position5.item2.adapter;

import android.graphics.Typeface;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.example.honpemes.R;
import com.example.honpemes.fragment.a.menu.fragment.position5.item2.bean.BoardBean;

import org.jetbrains.annotations.NotNull;

/**
 * 入库产品适配
 */
public class BoardHeadAdapter extends BaseQuickAdapter<BoardBean, BaseViewHolder> {


    public BoardHeadAdapter() {
        super(R.layout.item_enter_board);
    }

    @Override
    protected void convert(@NotNull BaseViewHolder holder, BoardBean bean) {

        TextView tvItem1 = holder.getView(R.id.tv_item1);
        TextView tvItem2 = holder.getView(R.id.tv_item2);
        TextView tvItem3 = holder.getView(R.id.tv_item3);
        TextView tvItem4 = holder.getView(R.id.tv_item4);
        TextView tvItem5 = holder.getView(R.id.tv_item5);

        tvItem1.setTypeface(Typeface.DEFAULT_BOLD);
        tvItem2.setTypeface(Typeface.DEFAULT_BOLD);
        tvItem3.setTypeface(Typeface.DEFAULT_BOLD);
        tvItem4.setTypeface(Typeface.DEFAULT_BOLD);
        tvItem5.setTypeface(Typeface.DEFAULT_BOLD);

        holder.setText(R.id.tv_item1,bean.date)
                .setText(R.id.tv_item2,bean.produceID)
                .setText(R.id.tv_item3,bean.produceType)
                .setText(R.id.tv_item4,bean.produceNum)
                .setText(R.id.tv_item5,bean.unit);
    }
}
