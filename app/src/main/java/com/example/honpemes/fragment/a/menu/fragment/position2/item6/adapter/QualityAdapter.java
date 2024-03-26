package com.example.honpemes.fragment.a.menu.fragment.position2.item6.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.example.honpemes.R;
import com.example.honpemes.fragment.a.menu.fragment.position2.item6.bean.QualityBean;

import org.jetbrains.annotations.NotNull;

/**
 * 作者：asus on 2023/12/13 12:13
 * 注释：
 */
public class QualityAdapter extends BaseQuickAdapter<QualityBean.DataBean, BaseViewHolder> {


    public QualityAdapter() {
        super(R.layout.item_work_title);
    }

    @Override
    protected void convert(@NotNull BaseViewHolder holder, QualityBean.DataBean bean) {
        holder.setGone(R.id.ll_head_bg, false);
        holder.setText(R.id.tv_part, bean.生产部门);
        holder.setText(R.id.tv_team, bean.生产组别);
        holder.setText(R.id.tv_operator, bean.零件数量);
        holder.setText(R.id.tv_finish_num, bean.良品数量);
        holder.setText(R.id.tv_work_time, bean.良品率);
    }
}
























