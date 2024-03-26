package com.example.honpemes.fragment.a.menu.fragment.position5.item1.adapter;

import androidx.annotation.NonNull;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.example.honpemes.R;
import com.example.honpemes.fragment.a.menu.fragment.position5.item2.bean.BoardBean;
import com.example.honpemes.utils.ChartUtil;
import com.github.mikephil.charting.charts.LineChart;

/**
 * 看板列表
 */
public class InventoryBoardAdapter extends BaseQuickAdapter<BoardBean, BaseViewHolder> {


    public InventoryBoardAdapter() {
        super(R.layout.item_line_chat_enter_board);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder holder, BoardBean bean) {
        holder.setText(R.id.tv_line_title, bean.produceType)
                .setText(R.id.tv_item1, "采购入库 " + bean.item1)
                .setText(R.id.tv_item2, "调拔入库 " + bean.item2)
                .setText(R.id.tv_item3, "盘盈入库 " + bean.item3)
                .setText(R.id.tv_item4, "供应商受赠入库 " + bean.item4);
        LineChart lineChart =holder.getView(R.id.m_line_chart);

        ChartUtil.setLineBoard(lineChart,bean);
    }
}
