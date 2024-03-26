package com.example.honpemes.fragment.a.menu.fragment.position2.item5.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.example.honpemes.R;
import com.example.honpemes.fragment.a.menu.fragment.position2.item5.bean.WorkTimeBean;
import com.example.honpemes.utils.DateUtil;

import org.jetbrains.annotations.NotNull;

/**
 * 作者：asus on 2023/12/13 12:13
 * 注释：
 */
public class WorkTimerAdapter extends BaseQuickAdapter<WorkTimeBean.DataBean, BaseViewHolder> {


    public WorkTimerAdapter() {
        super(R.layout.item_work_title);
    }

    @Override
    protected void convert(@NotNull BaseViewHolder holder, WorkTimeBean.DataBean bean) {
        holder.setGone(R.id.ll_head_bg, false);
        holder.setText(R.id.tv_part, bean.生产部门);
        holder.setText(R.id.tv_team, bean.生产组别);
        holder.setText(R.id.tv_operator, bean.操作员);
        holder.setText(R.id.tv_finish_num, bean.总计完成数量 + "");
        holder.setText(R.id.tv_work_time, DateUtil.CountTMS((long)bean.总计工时分钟 * 1000* 60));
    }
}
























