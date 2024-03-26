package com.example.honpemes.fragment.a.menu.fragment.position5.item3.adapter;

import androidx.annotation.NonNull;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.example.honpemes.R;
import com.example.honpemes.fragment.a.menu.fragment.position5.item3.bean.OutBoardBean;
import com.example.honpemes.utils.DateUtil;

/**
 * 出库
 */
public class OutBoardAdapter extends BaseQuickAdapter<OutBoardBean, BaseViewHolder> {

    public OutBoardAdapter() {
        super(R.layout.item_out_board);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder holder, OutBoardBean bean) {
        String week = DateUtil.getWeek(bean.date, DateUtil.ymd);
        String date =  DateUtil.dd.format(DateUtil.setDate(DateUtil.ymd,bean.date));
        holder.setText(R.id.tv_item1, date+"/"+week)
                .setText(R.id.tv_item2,bean.deliver)
                .setText(R.id.tv_item3,bean.num)
                .setText(R.id.tv_item4,bean.product)
                .setText(R.id.tv_item5,bean._id)
                .setText(R.id.tv_item6,bean.store);
    }
}
