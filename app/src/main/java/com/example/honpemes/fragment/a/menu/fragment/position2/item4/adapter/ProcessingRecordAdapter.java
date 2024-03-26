package com.example.honpemes.fragment.a.menu.fragment.position2.item4.adapter;


import android.graphics.Color;
import android.view.View;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.example.honpemes.R;
import com.example.honpemes.fragment.a.menu.fragment.position2.item4.bean.ProcessingRecordBean;
import com.example.honpemes.utils.DateUtil;

import org.jetbrains.annotations.NotNull;

/**
 * 作者：asus on 2023/12/21 09:19
 * 注释：
 */
public class ProcessingRecordAdapter extends BaseQuickAdapter<ProcessingRecordBean.DataBean, BaseViewHolder> {


    public ProcessingRecordAdapter() {
        super(R.layout.item_process_record);
    }

    @Override
    protected void convert(@NotNull BaseViewHolder holder, ProcessingRecordBean.DataBean bean) {
        holder.setGone(R.id.ll_process_record_bg, false);
        String createTime = bean.createTime.replace("T", " ");
        String ymd = DateUtil.ymd.format(DateUtil.setDate(DateUtil.ymdhms, createTime));
        holder.setText(R.id.item_1, DateUtil.dd.format(DateUtil.setDate(DateUtil.ymd,ymd)) + "/" + DateUtil.getWeek(ymd, DateUtil.ymd));
        holder.setText(R.id.item_2, bean.workName)
                .setText(R.id.item_3, bean.machineNO + "\n" + bean.机台类型)
                .setText(R.id.item_4, bean.workpieceCount + "\n" + bean.待机数量)
                .setText(R.id.item_5, bean.使用部门 + "/" + bean.制作组别);

        ImageView ivRight = holder.getView(R.id.iv_right);
        ivRight.setVisibility(View.VISIBLE);
        ivRight.setColorFilter(Color.BLACK);

        if (holder.getAdapterPosition() % 2 == 0) {
            holder.setBackgroundResource(R.id.ll_process_record_bg, R.color.tu_yellow);
        } else {
            holder.setBackgroundResource(R.id.ll_process_record_bg, R.color.white);
        }

    }
}









































